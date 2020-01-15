package janelas;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class PendenciasNotificacao extends JFrame {
	static final int PW = 300, PH = 170;

	public JLabel desc;
	private JPanel painel;
	int x = 0, y = 0;
	
	public Movimentos mov;

	public PendenciasNotificacao(LocalDate tempo, File arquivo) {
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

		JLabel titulo = new JLabel("RELATÓRIO - " + tempo.toString());
		JLabel icone = new JLabel();
		desc = new JLabel("<html><center>" + "RELATÓRIO DE PENDÊNCIAS CRIADO!" + "</center></html>");

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
		desc.setBounds(10, 45, 280, 45);
		desc.setHorizontalAlignment(SwingConstants.CENTER);

		JButton ir = new JButton("<html><center>VISUALIZAR NO COMPUTADOR</center></html>");
		ir.setBounds(48, 105, 204, 50);
		painel.add(ir);

		String caminho = arquivo.getPath();

		ir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Runtime.getRuntime().exec("explorer.exe /select," + caminho);
					mov.fechar();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void notificar(LocalDate tempo, File arquivo)
	{
		PendenciasNotificacao notif = new PendenciasNotificacao(tempo, arquivo);
		Movimentos mov = new Movimentos();
		notif.mov = mov;
		mov.deslizar(notif, Movimentos.SUPERIOR_DIREITO, true);
	}
}