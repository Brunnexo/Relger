package threads;

import java.time.LocalDateTime;
import java.util.TimerTask;

public abstract class PendenciasCheck extends TimerTask{
	
	private boolean executado;
	
	public PendenciasCheck()
	{
		this.executado = false;
	}
	
	@Override
	public void run() {
		LocalDateTime ldt = LocalDateTime.now();

		if (ldt.getHour() >= 12 && !(this.executado))
		{
			exec();
			this.executado = true;
		}
	}

	protected abstract void exec();
}