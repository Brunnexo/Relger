package janelas;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;



import java.awt.Cursor;

public class Progresso extends JFrame {
	static final int PW = 300, PH = 170;

	public JProgressBar progresso;
	public JLabel desc;
	private JPanel painel;

	public Progresso(String tit, String descr) {
		super("Progresso");
		
		painel = new JPanel();
		painel.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		painel.setBorder(new LineBorder(new Color(64, 64, 64), 2, true));
		setContentPane(painel);
		
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
		Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
		
		int x = ((int) rect.getMaxX() - PW);
		int y = ((int) rect.getMaxY() - PH);
		
		setBounds(x, y, PW, PH);
		
		painel.setLayout(null);
		setUndecorated(true);
		setShape(new RoundRectangle2D.Double(0,0,PW,PH,5,5));
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		//Título
		JLabel titulo = new JLabel(tit.toUpperCase());
		titulo.setBounds(32, 10, 235, 15);
		titulo.setFont(new Font("Tahoma", Font.BOLD, 12));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setForeground(Color.DARK_GRAY);
		getContentPane().add(titulo);

		//Descrição
		desc = new JLabel("<html>" + "<body>" + "<center>" + descr + "</center>" + "</body>" + "</html>");
		desc.setForeground(Color.DARK_GRAY);
		desc.setBounds(5, 35, 290, 75);
		desc.setHorizontalAlignment(SwingConstants.CENTER);
		painel.add(desc);

		progresso = new JProgressBar();
		progresso.setBounds(5, 115, 290, 50);
		progresso.setIndeterminate(true);
		progresso.setValue(0);
		painel.add(progresso);
	}

	public void altProgresso(int atual, int max)
	{
		double porc = (double) atual/max;
		porc = porc * 100.0;
		progresso.setIndeterminate(false);
		progresso.setValue((int) porc);
	}

	public void altDescr(String descr)
	{
		desc.setText("<html>" + "<body>" + "<center>" + descr + "</center>" + "</body>" + "</html>");
	}

	public void finalizar(String texto)
	{
		progresso.setValue(100);
		desc.setText(("<html>" + "<body>" + "<center>" + texto + "</center>" + "</body>" + "</html>").toUpperCase());
		desc.setFont(new Font("Tahoma", Font.BOLD, 12));
		try
		{
			Thread.sleep(5000);
		}
		catch(InterruptedException e)
		{
			Thread.currentThread().interrupt();
		}
		dispose();
	}

	public Progresso mostrar()
	{
		Progresso prog = this;
		this.setAlwaysOnTop(true);
		this.setVisible(true);
		return prog;
	}
}
