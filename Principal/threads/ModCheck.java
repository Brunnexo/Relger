package threads;

import java.io.IOException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import jcifs.smb.SmbFile;

public class ModCheck {

	public static void checarPlanilha(SmbFile arquivo)
	{
		TimerTask tarefa = new ModChecker(arquivo) {
			protected void onChange(SmbFile arquivo)
			{
				try {
					POIProjetos.LerPlanilha(arquivo);
					POISRs.LerPlanilha(arquivo);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		Timer temporizador = new Timer();
		temporizador.schedule(tarefa, new Date(), 1000);
	}

	public static void checarPrograma(SmbFile arquivo)
	{
		TimerTask tarefa = new ModChecker(arquivo) {
			protected void onChange(SmbFile arquivo)
			{
				String caminho = arquivo.getPath();
				caminho = caminho.substring(caminho.indexOf('@') + 1);
				caminho = "\\\\" + caminho.replace("/", "\\");
				caminho = "\"" + caminho + "\"";

				try {
					Runtime.getRuntime().exec("java -jar " + caminho);
					
					Thread.sleep(2000);
					
					System.exit(1);
				} catch (IOException | InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		Timer temporizador = new Timer();
		temporizador.schedule(tarefa, new Date(), 1000);
	}
}