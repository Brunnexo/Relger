package backend;

import java.awt.TrayIcon.MessageType;
import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

import janelas.SystemTrayNotification;

public class Executar {
	public final static String VERSAO = "1.3.2";
	
	static boolean JCIFSConectado;
	static boolean SQLConectado;

	public static void main(String[] e) throws IOException, InterruptedException, ExecutionException, SQLException {
		
		//Altera a aparência da janela
		LookAndFeel.alterar("Nimbus");
		
		SystemTrayNotification.notifyTray("Conectando com o servidor...", "O tempo depende da qualidade da conexão", MessageType.INFO);
	}
}