package backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import janelas.Movimentos;
import janelas.Notificacao;

public class Config {

	public static ArrayList<String> lerConfig(){

		ArrayList<String> config = new ArrayList<String>();
		
		File arquivo = new File("Dependências/config.txt");
		try {
			//Scanner leitor = new Scanner(new File(System.getenv("USERPROFILE") + "/Documents/Relger" + "/config.txt"));
			Scanner leitor = new Scanner(arquivo);

			while (leitor.hasNextLine()) {
				config.add(leitor.nextLine());
			}
			leitor.close();
			return config;
		} catch (FileNotFoundException e) {
			Movimentos mov = new Movimentos();
			mov.deslizar(new Notificacao("Erro!", e.getMessage(), Notificacao.EXCLAMACAO_CRITICA), Movimentos.INFERIOR_DIREITO, false);
		}
		return config;
	}
}