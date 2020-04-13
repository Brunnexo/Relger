package threads;

import javax.swing.SwingWorker;

import rede.JCIFS;

public class POI {
	public boolean executando = false;
	public boolean finalizado = false;
	SwingWorker lerPlanilhas;
	
	public POI() {
		lerPlanilhas = new SwingWorker() {
			@Override
			protected Object doInBackground() throws Exception {
				executando = true;
				threads.POISRs.LerPlanilha(JCIFS.sharedFile);
				threads.POIProjetos.LerPlanilha(JCIFS.sharedFile);
				return null;
			}

			protected void done() {
				finalizado = true;
			}
		};
	}
	public void executar()
	{
		lerPlanilhas.execute();
	}
}