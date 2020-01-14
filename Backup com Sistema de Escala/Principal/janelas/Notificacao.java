package janelas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.RoundRectangle2D;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;



public class Notificacao extends JFrame {

	int PW, PH;

	public static final int EXCLAMACAO_SIMPLES = 1;
	public static final int EXCLAMACAO_CRITICA = 2;

	public JLabel desc;
	private JPanel painel;
	int x = 0, y = 0;

	public Notificacao(String tit, String descr, int icn) {
		super("Notificação");
		//300,170
		
		Dimension tamanhoJanela = Metricas.proporcao(0.15625, 0.1574074074074074);
		Dimension tamanhoIcone = Metricas.proporcao(0.0260416666666667, 0.0462962962962963);

		ImageIcon simbolo = new ImageIcon();
		
		switch (icn)
		{
		case 1:
			simbolo = new ImageIcon(Notificacao.class.getResource("/icones/exclamation_s.png"));
			break;
		case 2:
			simbolo = new ImageIcon(Notificacao.class.getResource("/icones/exclamation.png"));
			break;
		}
		
		Image imagem = simbolo.getImage().getScaledInstance((int) tamanhoIcone.getWidth(), (int) tamanhoIcone.getHeight(), Image.SCALE_SMOOTH);
		
		
		PW = (int) tamanhoJanela.getWidth();
		PH = (int) tamanhoJanela.getHeight();

		painel = new JPanel();
		painel.setRequestFocusEnabled(false);
		painel.setFocusable(false);
		painel.setFocusTraversalKeysEnabled(false);
		painel.setBorder(new LineBorder(new Color(64, 64, 64), 2, true));
		setContentPane(painel);

		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
		Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();

		x = ((int) rect.getMaxX() - (int) tamanhoJanela.getWidth());
		y = ((int) rect.getMaxY());

		setLocation(x, y);
		setSize(tamanhoJanela);

		painel.setLayout(null);

		setUndecorated(true);

		setShape(new RoundRectangle2D.Double(0,0,(int) tamanhoJanela.getWidth(),(int) tamanhoJanela.getHeight(),5,5));

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
		icone.setHorizontalAlignment(SwingConstants.CENTER);
		icone.setForeground(Color.DARK_GRAY);
		icone.setIcon(new ImageIcon(imagem));
		
		desc.setFocusTraversalKeysEnabled(false);
		desc.setFocusable(false);
		desc.setRequestFocusEnabled(false);

		painel.add(titulo);
		painel.add(desc);
		painel.add(icone);

		//Título
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setForeground(Color.DARK_GRAY);

		//Descrição
		desc.setForeground(Color.DARK_GRAY);
		desc.setHorizontalAlignment(SwingConstants.CENTER);

		//Parâmetros Layout Fonte
		titulo.setFont(new Font("Taroma", Font.BOLD, Metricas.tamanhoFonte(0.0111111111111111)));
		icone.setFont(new Font("Tahoma", Font.PLAIN, Metricas.tamanhoFonte(0.0185185185185185)));
		desc.setFont(new Font("Tahoma", Font.BOLD, Metricas.tamanhoFonte(0.0138888888888889)));
		
		//Parâmetros Localização Layout
		titulo.setLocation(Metricas.posicaoRelativa(this.getSize(), 0.1066666666666667, 0.0882352941176471));
		icone.setLocation(Metricas.posicaoRelativa(this.getSize(), 0.0266666666666667, 0.3411764705882353));
		desc.setLocation(Metricas.posicaoRelativa(this.getSize(), 0.3033333333333333, 0.2764705882352941));
		
		//Parâmetros Tamanho Layout
		titulo.setSize(Metricas.proporcao(0.1197916666666667, 0.0138888888888889));
		icone.setSize(tamanhoIcone);
		desc.setSize(Metricas.proporcao(0.1041666666666667, 0.0694444444444444));
	}

	public static void notificar(String tit, String descr, int icn)
	{
		Notificacao notif = new Notificacao(tit, descr, icn);

		notif.setVisible(true);
		notif.setAlwaysOnTop(true);

		if (icn == Notificacao.EXCLAMACAO_SIMPLES)
		{
			Thread notificacao = new Thread()
			{
				public void run()
				{
					try {

						for (int i = 0; i <= notif.PH; i++)
						{
							notif.setLocation(notif.x, (notif.y - i));
							Thread.sleep(5);
						}
						Thread.sleep(3500);

						for (int i = 0; i <= notif.PH; i++)
						{
							notif.setLocation(notif.x, ((notif.y - notif.PH) + i));
							Thread.sleep(1);
						}

					} catch (InterruptedException e) {e.printStackTrace();}
					notif.dispose();
				}
			};
			notificacao.start();
		}
		else
		{
			try {
				for (int i = 0; i <= notif.PH; i++)
				{
					notif.setLocation(notif.x, (notif.y - i));
					Thread.sleep(5);
				}
				Thread.sleep(3500);

				for (int i = 0; i <= notif.PH; i++)
				{
					notif.setLocation(notif.x, ((notif.y - notif.PH) + i));
					Thread.sleep(1);
				}

			} catch (InterruptedException e) {e.printStackTrace();}
			notif.dispose();
		}
	}
}