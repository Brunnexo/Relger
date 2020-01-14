package threads;

import java.io.IOException;
import java.util.ArrayList;

import backend.Config;

public class Saida{

	static boolean terminal = false;

	public static void sair()
	{
		config();

		if (terminal)
		{
			try {
				Runtime.getRuntime().exec("cmd.exe /C explorer.exe");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.exit(1);
	}

	static void config()
	{
		ArrayList<String> config = Config.lerConfig();
		boolean iniciarLeitura = false;

		for (int i = 0; i < config.size(); i++)
		{
			if (iniciarLeitura == false)
			{
				if (config.get(i).contains("[::Desktop]"))
					iniciarLeitura = true;
			}
			else
			{
				if (config.get(i).contains("[terminal]"))
				{
					if (config.get(i).toUpperCase().contains("SIM"))
						terminal = true;
					else if (config.get(i).toUpperCase().contains("NÃO"))
						terminal = false;
				}
				else if (config.get(i).equals("[::]"))
					break;
			}
		}
	}
}