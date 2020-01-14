package janelas;

import java.awt.Toolkit;

import javax.swing.JFrame;

public class Movimentos{

	public final static int SUPERIOR_ESQUERDO = 0;
	public final static int SUPERIOR_DIREITO = 1;

	public final static int INFERIOR_ESQUERDO = 2;
	public final static int INFERIOR_DIREITO = 3;

	public final static int CENTRO_ESQUERDO = 4;
	public final static int CENTRO_DIREITO = 5;

	private int X = 0;
	private int Y = 0;

	private boolean esmaecer = false;
	private boolean deslizar = false;

	private boolean finalizado = false;

	private int local = 0;

	public JFrame componente;

	public boolean isEsmaecer() {
		return esmaecer;
	}
	public void setEsmaecer(boolean esmaecer) {
		this.esmaecer = esmaecer;
	}
	public boolean isDeslizar() {
		return deslizar;
	}
	public void setDeslizar(boolean deslizar) {
		this.deslizar = deslizar;
	}
	public boolean isFinalizado() {
		return finalizado;
	}
	public void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
	}

	public void deslizar(JFrame componente, int localizacao, boolean manter)
	{
		Movimentos mov = this;
		
		this.deslizar = true;
		this.componente = componente;

		this.componente.setVisible(true);
		this.componente.setAlwaysOnTop(true);

		int PW = this.componente.getSize().width;
		int PH = this.componente.getSize().height;

		switch (localizacao)
		{
		case 0:
			this.X = 0;
			this.Y = 0;
			break;
		case 1:
			this.X = Toolkit.getDefaultToolkit().getScreenSize().width - PW;
			this.Y = 0;
			break;
		case 2:
			this.X = 0;
			this.Y = Toolkit.getDefaultToolkit().getScreenSize().height;
			break;
		case 3:
			this.X = Toolkit.getDefaultToolkit().getScreenSize().width - PW;
			this.Y = Toolkit.getDefaultToolkit().getScreenSize().height;
			break;
		case 4:
			this.X = 0;
			this.Y = (Toolkit.getDefaultToolkit().getScreenSize().height / 2) - (PH / 2); 
			break;
		case 5:
			this.X = Toolkit.getDefaultToolkit().getScreenSize().width;
			this.Y = (Toolkit.getDefaultToolkit().getScreenSize().height / 2) - (PH / 2); 
			break;
		}

		Thread funcao = new Thread()
		{
			public void run()
			{
				mov.finalizado = false;

				switch (localizacao)
				{
				case 0:
					try {
						for (int i = 0; i <= PH; i++)
						{
							mov.componente.setLocation(X, ((Y - PH) + i));
							Thread.sleep(5);
						}
						if (!manter)
						{
							Thread.sleep(3500);
							for (int i = 0; i <= PH; i++)
							{
								mov.componente.setLocation(X, (Y - i));
								Thread.sleep(1);
							}
						}
					} catch (InterruptedException e) {e.printStackTrace();}
					break;
				case 1:
					try {
						for (int i = 0; i <= PH; i++)
						{
							mov.componente.setLocation(X, ((Y - PH) + i));
							Thread.sleep(5);
						}
						if (!manter)
						{
							Thread.sleep(3500);
							for (int i = 0; i <= PH; i++)
							{
								mov.componente.setLocation(X, (Y - i));
								Thread.sleep(1);
							}
						}
					} catch (InterruptedException e) {e.printStackTrace();}
					break;
				case 2:
					try {
						for (int i = 0; i <= PH; i++)
						{
							mov.componente.setLocation(X, (Y - i));
							Thread.sleep(5);
						}
						if (!manter)
						{
							Thread.sleep(3500);
							for (int i = 0; i <= PH; i++)
							{
								mov.componente.setLocation(X, ((Y - PH) + i));
								Thread.sleep(1);
							}
						}
					} catch (InterruptedException e) {e.printStackTrace();}
					break;
				case 3:
					try {
						for (int i = 0; i <= PH; i++)
						{
							mov.componente.setLocation(X, (Y - i));
							Thread.sleep(5);
						}
						if (!manter)
						{
							Thread.sleep(3500);
							for (int i = 0; i <= PH; i++)
							{
								mov.componente.setLocation(X, ((Y - PH) + i));
								Thread.sleep(1);
							}
						}
					} catch (InterruptedException e) {e.printStackTrace();}
					break;
				case 4:
					try {
						for (int i = 0; i <= PW; i++)
						{
							mov.componente.setLocation(((X - PW) + i), Y);
							Thread.sleep(5);
						}
						if (!manter)
						{
							Thread.sleep(3500);
							for (int i = 0; i <= PW; i++)
							{
								mov.componente.setLocation((X - i), Y);
								Thread.sleep(1);
							}
						}
					} catch (InterruptedException e) {e.printStackTrace();}
					break;
				case 5:
					try {
						for (int i = 0; i <= PW; i++)
						{
							mov.componente.setLocation((X - i), Y);
							Thread.sleep(5);
						}
						if (!manter)
						{
							Thread.sleep(3500);
							for (int i = 0; i <= PW; i++)
							{
								mov.componente.setLocation(((X - PW) + i), Y);
								Thread.sleep(1);
							}
						}
					} catch (InterruptedException e) {e.printStackTrace();}
					break;
				}
				if (!manter)
					mov.componente.dispose();
				
				mov.finalizado = true;
				mov.local = localizacao;
			}
		};
		funcao.start();
	}

	public void esmaecer(JFrame componente, boolean manter)
	{
		Movimentos mov = this;
		this.componente = componente;
		this.esmaecer = true;

		int PW = this.componente.getSize().width;
		int PH = this.componente.getSize().height;

		this.componente.setVisible(true);
		this.componente.setAlwaysOnTop(true);

		this.componente.setBounds(0, 0, PW, PH);
		this.componente.setLocationRelativeTo(null);

		Thread funcao = new Thread()
		{
			public void run()
			{
				try {
					for (float i = (float) 0.0; i <= 1.0; i = (float) (i = (float) (i + 0.001)))
					{
						mov.componente.setOpacity(i);
						Thread.sleep(1);
					}
					if (!manter)
					{
						Thread.sleep(3500);
						for (float i = (float) 1.0; i >= 0.0; i = (float) (i = (float) (i - 0.001)))
						{
							mov.componente.setOpacity(i);
							Thread.sleep(1);
						}
					}
				} catch (InterruptedException e) {e.printStackTrace();}

				if (!manter)
					mov.componente.dispose();
			}
		};
		funcao.start();
	}

	public void fechar()
	{
		int PW = this.componente.getSize().width;
		int PH = this.componente.getSize().height;

		if (this.esmaecer)
		{
			for (float i = (float) 1.0; i >= 0.0; i = (float) (i = (float) (i - 0.001)))
			{
				try {
					this.componente.setOpacity(i);
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		else if (this.deslizar)
		{
			switch (this.local)
			{
			case 0:
				try {
					for (int i = 0; i <= PH; i++)
					{
						this.componente.setLocation(X, (Y - i));
						Thread.sleep(1);
					}
				} catch (InterruptedException e) {e.printStackTrace();}
				break;
			case 1:
				try {
					for (int i = 0; i <= PH; i++)
					{
						this.componente.setLocation(X, (Y - i));
						Thread.sleep(1);
					}
				} catch (InterruptedException e) {e.printStackTrace();}
				break;
			case 2:
				try {
					for (int i = 0; i <= PH; i++)
					{
						this.componente.setLocation(X, ((Y - PH) + i));
						Thread.sleep(1);
					}
				} catch (InterruptedException e) {e.printStackTrace();}
				break;
			case 3:
				try {
					for (int i = 0; i <= PH; i++)
					{
						this.componente.setLocation(X, ((Y - PH) + i));
						Thread.sleep(1);
					}
				} catch (InterruptedException e) {e.printStackTrace();}
				break;
			case 4:
				try {
					for (int i = 0; i <= PW; i++)
					{
						this.componente.setLocation((X - i), Y);
						Thread.sleep(1);
					}
				} catch (InterruptedException e) {e.printStackTrace();}
				break;
			case 5:
				try {
					for (int i = 0; i <= PW; i++)
					{
						this.componente.setLocation(((X - PW) + i), Y);
						Thread.sleep(1);
					}
				} catch (InterruptedException e) {e.printStackTrace();}
				break;
			}
		}
		
		this.componente.dispose();
		try {
			this.finalize();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}