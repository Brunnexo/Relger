package threads;

import java.io.IOException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import jcifs.smb.SmbFile;

public class ModCheck {

	public static void checar(SmbFile arquivo)
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
}