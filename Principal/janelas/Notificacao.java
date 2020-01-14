package janelas;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.geom.RoundRectangle2D;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class Notificacao extends JFrame {
	static final int PW = 300;
	static final int PH = 170;

	public static final int EXCLAMACAO_SIMPLES = 1;
	public static final int EXCLAMACAO_CRITICA = 2;

	public JLabel desc;
	private JPanel painel;
	int x = 0, y = 0;

	public Notificacao(String tit, String descr, int icn) {
		super("Notificação");

		painel = new JPanel();
		painel.setRequestFocusEnabled(false);
		painel.setFocusable(false);
		painel.setFocusTraversalKeysEnabled(false);
		painel.setBorder(new LineBorder(new Color(64, 64, 64), 2, true));
		setContentPane(painel);

		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
		Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();

		x = ((int) rect.getMaxX() - PW);
		y = ((int) rect.getMaxY());

		setLocation(x, y);
		setSize(PW, PH);

		painel.setLayout(null);
		setUndecorated(true);
		setShape(new RoundRectangle2D.Double(0,0,PW,PH,5,5));
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		JLabel titulo = new JLabel(tit.toUpperCase());
		JLabel icone = new JLabel();
		desc = new JLabel("<html><center>" + descr.toUpperCase() + "</center></html>");

		titulo.setFocusable(false);
		titulo.setFocusTraversalKeysEnabled(false);
		titulo.setRequestFocusEnabled(false);

		icone.setFocusTraversalKeysEnabled(false);
		icone.setFocusable(false);
		icone.setRequestFocusEnabled(false);

		desc.setFocusTraversalKeysEnabled(false);
		desc.setFocusable(false);
		desc.setRequestFocusEnabled(false);

		painel.add(titulo);
		painel.add(desc);
		painel.add(icone);

		//Título
		titulo.setBounds(32, 10, 235, 15);
		titulo.setFont(new Font("Tahoma", Font.BOLD, 12));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setForeground(Color.DARK_GRAY);

		//Descrição
		desc.setFont(new Font("Tahoma", Font.BOLD, 15));
		desc.setForeground(Color.DARK_GRAY);
		desc.setBounds(91, 47, 200, 75);
		desc.setHorizontalAlignment(SwingConstants.CENTER);

		icone.setHorizontalAlignment(SwingConstants.CENTER);
		icone.setForeground(Color.DARK_GRAY);
		icone.setFont(new Font("Tahoma", Font.PLAIN, 20));
		icone.setBounds(8, 47, 75, 75);

		//Exclamação Simples
		switch (icn)
		{
		case 1:
			icone.setIcon(new ImageIcon(Notificacao.class.getResource("/icones/exclamation_s.png")));
			break;
		case 2:
			icone.setIcon(new ImageIcon(Notificacao.class.getResource("/icones/exclamation.png")));
			break;
		}
	}
}