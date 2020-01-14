package threads;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

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
		try {
			terminal = InetAddress.getLocalHost().getHostName().contains("RELGER");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
}