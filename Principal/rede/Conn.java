package rede;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import backend.Config;

public class Conn
{
	//MS SQL
	static InetAddress localHost;
	static String servidor =		"10.144.201.115";
	static String porta =			":1433";
	static String banco =			"relger";
	static String usuario =			"sa";
	static String senha =			"sa";

	public static Connection connection;

	public static boolean getConnection() {

		String addLocalHost = new String();

		try {
			localHost = InetAddress.getLocalHost();
			addLocalHost = "jdbc:sqlserver://" + localHost.getHostAddress().trim() + porta + ";databaseName=" + banco + ";user=" + usuario + ";password=" + senha + ";";

		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}

		try {
			connection = DriverManager.getConnection(configurarEndereco());
			System.out.println("Conectado ao banco de dados com endereço: " + servidor);
			return true;
		}
		catch (SQLException e) {
			System.out.println("Falha ao conectar no endereço definido: " + e.toString());
			System.out.println("Tentando endereço local...");
			return getConnectionLocal(addLocalHost);
		}
	}

	public static boolean getConnectionLocal(String end)
	{
		try {
			connection = DriverManager.getConnection(end);
			System.out.println("Conectado ao banco de dados com endereço: " + localHost.getHostAddress().trim());
			return true;
		} catch (SQLException e)
		{
			System.out.println("Falha ao conectar no endereço local: " + e.toString());
			System.out.println("Verifique as configurações de conexão!");
			return false;
		}
	}

	public static String configurarEndereco()
	{
		ArrayList<String> config = Config.lerConfig();

		String ip = new String();
		String porta = new String();
		String banco = new String();
		String usuario = new String();
		String senha = new String();

		boolean iniciarLeitura = false;

		for (int i = 0; i < config.size(); i++)
		{
			if (!iniciarLeitura)
			{
				if (config.get(i).contains("[::SQL]"))
					iniciarLeitura = true;
			}
			else
			{
				if (config.get(i).contains("//"))
					System.out.println("Comentário da linha " + (i+1) + " do arquivo config.txt: " + config.get(i).replaceAll("//", ""));
				else
				{
					if (config.get(i).contains("[ip]"))
						ip = config.get(i).replace("[ip]", "").replaceAll("\t", "");
					else if (config.get(i).contains("[porta]"))
						porta = config.get(i).replace("[porta]", "").replaceAll("\t", "");
					else if (config.get(i).contains("[db]"))
						banco = config.get(i).replace("[db]", "").replaceAll("\t", "");
					else if (config.get(i).contains("[usuario]"))
						usuario = config.get(i).replace("[usuario]", "").replaceAll("\t", "");
					else if (config.get(i).contains("[senha]"))
						senha = config.get(i).replace("[senha]", "").replaceAll("\t", "");
					else if (config.get(i).equals("[::]"))
						break;
				}
			}
		}
		return "jdbc:sqlserver://" + ip + ":" + porta + ";databaseName=" + banco + ";user=" + usuario + ";password=" + senha + ";";
	}
}