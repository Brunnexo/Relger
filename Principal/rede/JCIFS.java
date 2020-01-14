package rede;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.util.ArrayList;

import backend.Config;
import backend.Crypto;
import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbAuthException;
import jcifs.smb.SmbFile;

public class JCIFS {

	public static SmbFile configurarEndereco(String endereco, String caminho, String arq)
	{
		String dominio = new String();
		String usuario = new String();
		String senha = new String();

		ArrayList<String> config = Config.lerConfig();

		boolean iniciarLeitura = false;


		for (int i = 0; i < config.size(); i++)
		{
			if (iniciarLeitura == false)
			{
				if (config.get(i).contains("[::SharedFolder]"))
					iniciarLeitura = true;
			}
			else
			{
				if (config.get(i).contains("//"))
					System.out.println("Comentário da linha " + (i+1) + " do arquivo config.txt: " + config.get(i).replaceAll("//", ""));
				else
				{
					if (config.get(i).contains("[dominio]"))
						dominio = config.get(i).replace("[dominio]", "").replaceAll("\t", "");
					else if (config.get(i).contains("[usuario]"))
						usuario = Crypto.descriptogragar(config.get(i).replace("[usuario]", "").replaceAll("\t", ""));
					else if (config.get(i).contains("[senha]"))
						senha = Crypto.descriptogragar(config.get(i).replace("[senha]", "").replaceAll("\t", ""));
					else if (config.get(i).equals("[::]"))
						break;
				}
			}
		}
		NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication(dominio, usuario, senha);

		String URL = "smb://share:@" + endereco + caminho + arq;

		try {
			SmbFile arquivo = new SmbFile(URL, auth);
			try {
				arquivo.connect();
				return arquivo;
			} catch (SmbAuthException e) {
				e.printStackTrace();
				return null;
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static SmbFile configurarEndereco(boolean temp)
	{
		String dominio = new String();
		String usuario = new String();
		String senha = new String();
		String endereco = new String();
		String caminho = new String();
		String arq = new String();

		ArrayList<String> config = Config.lerConfig();

		boolean iniciarLeitura = false;

		for (int i = 0; i < config.size(); i++)
		{
			LocalDate ld = LocalDate.now();
			if (iniciarLeitura == false)
			{
				if (config.get(i).contains("[::SharedFolder]"))
					iniciarLeitura = true;
			}
			else
			{
				if (config.get(i).contains("//"))
					System.out.println("Comentário da linha " + (i+1) + " do arquivo config.txt: " + config.get(i).replaceAll("//", ""));
				else
				{
					if (config.get(i).contains("[dominio]"))
						dominio = config.get(i).replace("[dominio]", "").replaceAll("\t", "");
					else if (config.get(i).contains("[usuario]"))
						usuario = Crypto.descriptogragar(config.get(i).replace("[usuario]", "").replaceAll("\t", ""));
					else if (config.get(i).contains("[senha]"))
						senha = Crypto.descriptogragar(config.get(i).replace("[senha]", "").replaceAll("\t", ""));
					else if (config.get(i).contains("[endereco]"))
						endereco = config.get(i).replace("[endereco]", "").replaceAll("\t", "");
					else if (config.get(i).contains("[caminho]"))
						caminho = config.get(i).replace("[caminho]", "").replaceAll("\t", "").replace("<ano>", Integer.toString(ld.getYear()));
					else if (config.get(i).contains("[arquivo]"))
						arq = config.get(i).replace("[arquivo]", "").replaceAll("\t", "");
					else if (config.get(i).equals("[::]"))
						break;
				}
			}
		}
		NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication(dominio, usuario, senha);

		String cop = new String();

		if (temp)
			cop = "temp.xlsm";
		else
			cop = arq;

		String URL = "smb://share:@" + endereco + caminho + cop;

		try {
			SmbFile arquivo = new SmbFile(URL, auth);
			try {
				arquivo.connect();
				return arquivo;
			} catch (SmbAuthException e) {
				e.printStackTrace();
				return null;
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static boolean getConnection()
	{
		SmbFile temp = JCIFS.configurarEndereco(false);

		try {
			if (temp.exists())
				return true;
			else
				return false;
		} catch (Exception e) {
			return false;
		}
	}


}