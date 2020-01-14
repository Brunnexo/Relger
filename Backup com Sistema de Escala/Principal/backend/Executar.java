package backend;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import janelas.Notificacao;
import janelas.menus.Identificacao;
import janelas.menus.Inicio;
import rede.Conn;
import rede.JCIFS;
import threads.ModCheck;

public class Executar {

	static boolean terminal = false;
	static boolean adm = false;
	static boolean servidor = false;
	
	static boolean JCIFSConectado;
	static boolean SQLConectado;

	public static void main(String[] e) throws IOException, InterruptedException, ExecutionException, SQLException
	{
		//Aparência
		LookAndFeel.alterar("Nimbus");
		
		JCIFSConectado = JCIFS.getConnection();
		SQLConectado = Conn.getConnection();
		
		boolean conectado = (JCIFSConectado && SQLConectado);
		
		if (!JCIFSConectado)
			Notificacao.notificar("Erro", "Erro ao conectar ao sistema de arquivos!", Notificacao.EXCLAMACAO_CRITICA);
		if (!SQLConectado)
			Notificacao.notificar("Erro", "Erro ao conectar ao banco de dados!", Notificacao.EXCLAMACAO_CRITICA);
		
		config();
		if (terminal)
			Runtime.getRuntime().exec("cmd.exe /C taskkill /F /IM explorer.exe");

		Inicio inicio = new Inicio();
		Identificacao id = new Identificacao();

		//Threads
		Thread checarArquivoRede = new Thread()
		{
			public void run()
			{
				ModCheck.checar(JCIFS.configurarEndereco(false));
			}
		};

		//Abrir menu
		Thread iniciar = new Thread()
		{
			public void run()
			{
				if (conectado)
				{
					if (adm)
					{
						Notificacao.notificar("Conectado", "Rede conectada!", Notificacao.EXCLAMACAO_SIMPLES);
						inicio.setVisible(true);
						id.dispose();
					}
					else
					{
						Notificacao.notificar("Conectado", "Rede conectada!", Notificacao.EXCLAMACAO_SIMPLES);
						id.setVisible(true);
						inicio.dispose();
					}
				}
				else
				{
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
			}
		};

		//Iniciar Threads
		iniciar.start();
		
		if (servidor)
			checarArquivoRede.start();
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
				if (config.get(i).contains("//"))
					System.out.println("Comentário da linha " + (i+1) + " do arquivo config.txt: " + config.get(i).replaceAll("//", ""));
				else if (config.get(i).contains("[terminal]"))
				{
					if (config.get(i).toUpperCase().contains("SIM"))
						terminal = true;
					else if (config.get(i).toUpperCase().contains("NÃO"))
						terminal = false;
				}
				else if (config.get(i).contains("[adm]"))
				{
					if (config.get(i).toUpperCase().contains("SIM"))
						adm = true;
					else if (config.get(i).toUpperCase().contains("NÃO"))
						adm = false;
				}
				else if (config.get(i).contains("[servidor]"))
				{
					if (config.get(i).toUpperCase().contains("SIM"))
						servidor = true;
					else if (config.get(i).toUpperCase().contains("NÃO"))
						servidor = false;
				}
				else if (config.get(i).equals("[::]"))
					break;
			}
		}
	}
}