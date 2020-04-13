package backend;

import java.awt.TrayIcon.MessageType;
import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

import janelas.SystemTrayNotification;

public class Executar {

<<<<<<< Updated upstream
	public final static String VERSAO = "1.2.7";
=======
	public final static String VERSAO = "1.3.2";
>>>>>>> Stashed changes
	
	static boolean JCIFSConectado;
	static boolean SQLConectado;

<<<<<<< Updated upstream
	public static void main(String[] e) throws IOException, InterruptedException, ExecutionException, SQLException
	{
=======
	public static void main(String[] e) throws IOException, InterruptedException, ExecutionException, SQLException {
		//Altera a apar�ncia da janela
>>>>>>> Stashed changes
		LookAndFeel.alterar("Nimbus");
		SystemTrayNotification.notifyTray("Conectando com o servidor...", "O tempo depende da qualidade da conex�o", MessageType.INFO);
		
		
<<<<<<< Updated upstream
		config();

		boolean conectado = (JCIFSConectado && SQLConectado);

		Movimentos notif = new Movimentos();

		if (!conectado)
		{
			if (!JCIFSConectado)
				notif.deslizar(new Notificacao("Erro", "Erro ao se conectar com a pasta da rede!", Notificacao.EXCLAMACAO_CRITICA), Movimentos.INFERIOR_DIREITO, false);
			else if (!SQLConectado)
				notif.deslizar(new Notificacao("Erro", "Erro ao se conectar com o banco de dados!", Notificacao.EXCLAMACAO_CRITICA), Movimentos.INFERIOR_DIREITO, false);
		}

		if (terminal)
			Runtime.getRuntime().exec("cmd.exe /C taskkill /F /IM explorer.exe");
		

		Inicio inicio = new Inicio();
		Identificacao id = new Identificacao(new Inicio());

		Thread checarPendencias = new Thread()
		{
			public void run()
			{
				TimerTask checar = new PendenciasCheck() {
					@Override
					protected void exec() {
						Relatorios.carregarPendencia(Relatorios.pendentes());
					}
				};
				Timer temporizador = new Timer();
				temporizador.schedule(checar, new Date(), 5000);
			};
		};

		Thread checarArquivoRede = new Thread()
		{
			public void run()
			{
				ModCheck.checarPlanilha(JCIFS.configurarEndereco(false));
			}
		};

		//Abrir menu
		Thread iniciar = new Thread()
		{
			public void run()
			{
				if (conectado)
				{
					if (!adm)
					{
						Movimentos erro = new Movimentos();
						erro.deslizar(new Notificacao("Conectado", "Rede conectada!", Notificacao.EXCLAMACAO_SIMPLES), Movimentos.INFERIOR_DIREITO, false);
						inicio.setVisible(true);
						id.dispose();
					}
					else
					{
						Movimentos erro = new Movimentos();
						erro.deslizar(new Notificacao("Conectado", "Rede conectada!", Notificacao.EXCLAMACAO_SIMPLES), Movimentos.INFERIOR_DIREITO, false);
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

		if (adm)
			checarPendencias.start();
		if (servidor)
			checarArquivoRede.start();
	}

	static void config()
	{
		try {
			terminal = InetAddress.getLocalHost().getHostName().contains("RELGER");
			servidor = InetAddress.getLocalHost().getHostName().equals("RELGER-SERVIDOR");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		ResultSet listaNomesAdm = Funcionarios.nomeAdministrativo();
		ArrayList<String> listaSQL = new ArrayList<String>();

		try {
			String[] nomeUsuario = System.getProperty("user.name").split(" ");
			String usuario;

			if (nomeUsuario.length < 2)
				usuario = nomeUsuario[0];
			else
				usuario = String.valueOf(nomeUsuario[0].charAt(0)) + nomeUsuario[nomeUsuario.length - 1];


			while (listaNomesAdm.next())
			{
				listaSQL.add(listaNomesAdm.getString("NOME"));
			}

			for (int i = 0; i < listaSQL.size(); i++)
			{
				String[] nomeUsuarioComp = listaSQL.get(i).split(" ");
				String usuarioComp = String.valueOf(nomeUsuarioComp[0].charAt(0)) + nomeUsuarioComp[nomeUsuarioComp.length - 1];

				if (usuario.toUpperCase().equals(usuarioComp.toUpperCase()))
					adm = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
=======
>>>>>>> Stashed changes
	}
}