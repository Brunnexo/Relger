package threads;

import java.util.TimerTask;

import jcifs.smb.SmbFile;

public abstract class ModChecker extends TimerTask {
  private long timeStamp;
  private SmbFile arquivo;
  
  public ModChecker(SmbFile arquivo)
  {
	  this.arquivo = arquivo;
	  this.timeStamp = arquivo.getLastModified();
  }
	  
  public final void run()
  {
	  	long timeStamp = arquivo.getLastModified();
	  	
	  	if (this.timeStamp != timeStamp)
	  	{
	  		this.timeStamp = timeStamp;
	  		onChange(arquivo);
	  	}
  }
  
  protected abstract void onChange(SmbFile arquivo);
}