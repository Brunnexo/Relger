package backend;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import janelas.Progresso;

public class Config {

	public static ArrayList<String> lerConfig(){

		ArrayList<String> config = new ArrayList<String>();

		try {
			Scanner leitor = new Scanner(new File(System.getenv("USERPROFILE") + "/Documents/Relger" + "/config.txt"));

			while (leitor.hasNextLine()) {
				config.add(leitor.nextLine());
			}
			leitor.close();
			return config;
		} catch (FileNotFoundException e) {
			LookAndFeel.alterar("Nimbus");
			Progresso prog = new Progresso("Copiando configurações...", "Copiando config.txt...");
			prog.mostrar();
			try {
				File caminho = new File(System.getenv("USERPROFILE") + "/Documents/Relger/config.txt");
				caminho.getParentFile().mkdirs();

				FileInputStream fonte = new FileInputStream(new File("Dependências/config.txt"));
				FileOutputStream saida = new FileOutputStream(caminho);
				
				int buffersize = 1;
				byte[] b = new byte[buffersize];
				
				int bytes = 0;
				while ((bytes = fonte.read(b)) != -1)
				{
					prog.altProgresso(bytes, b.length);
					saida.write(b, 0, bytes);
				}
				prog.finalizar("Arquivo copiado com sucesso!");
				fonte.close();
				saida.close();
				
				Scanner backup = new Scanner(caminho);
				while(backup.hasNextLine()) {
					config.add(backup.nextLine());
				}
				backup.close();
				return config;
			} catch (IOException e1) {
				prog.finalizar("Erro ao copiar arquivo!");
				e1.printStackTrace();
			}
		}
		return config;
	}
}