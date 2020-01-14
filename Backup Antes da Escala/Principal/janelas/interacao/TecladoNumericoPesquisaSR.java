package janelas.interacao;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import janelas.Arrastar;

public class TecladoNumericoPesquisaSR extends JFrame {
	static final int PW = 310, PH = 520;
	private JPanel painel;
	private JTextField campoNum;
	private int x = 0, y = 0;
	
	private ArrayList<JButton> botoes;
	
	public TecladoNumericoPesquisaSR(PesquisaSR pai) {
		botoes = new ArrayList<JButton>();
		
		//Propriedades do Painel
		painel = new JPanel();
		painel.setBorder(new EmptyBorder(5, 5, 5, 5));
		painel.setLayout(null);

		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
		Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();

		//Centro a direita
		x = ((int) rect.getMaxX() - (PW * 2));
		y = (((int) rect.getMaxY() / 2) - (PW));
		
		//Propriedades da Janela
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, PW, PH);
		setContentPane(painel);
		setLocation(x, y);
		setUndecorated(true);
		setShape(new RoundRectangle2D.Double(0,0,PW,PH,5,5));
		JButton btnX = new JButton("X"); //APAGAR
		
		campoNum = new JTextField();
		
		for (int i = 0; i <= 9; i++)
			botoes.add(new JButton(Integer.toString(i)));
		
		JButton backSpc = new JButton("<");

		JLabel instru = new JLabel("TECLADO NUM\u00C9RICO");
		
		//Adição
		painel.add(campoNum);
		
		painel.add(btnX);
		
		for (int i = 0; i <= 9; i++)
			painel.add(botoes.get(i));

		painel.add(backSpc);
		painel.add(instru);

		//Display
		campoNum.setEditable(false);
		campoNum.setFont(new Font("Tahoma", Font.BOLD, 22));
		campoNum.setColumns(10);
		campoNum.setBounds(10, 45, 190, 60);
		
		
		//Botão APAGAR TUDO
		btnX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				campoNum.setText("");
				pai.inBusca.setText("");
			}
		});
		btnX.setFocusPainted(false);
		btnX.setFocusTraversalKeysEnabled(false);
		btnX.setFocusable(false);
		btnX.setBorder(null);
		btnX.setBorderPainted(false);
		btnX.setForeground(Color.LIGHT_GRAY);
		btnX.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnX.setContentAreaFilled(false);
		btnX.setRequestFocusEnabled(false);
		btnX.setBounds(160, 55, 40, 40);

		//Tecla 0
		botoes.get(0).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				campoNum.setText(campoNum.getText() + "0");
				pai.inBusca.setText(pai.inBusca.getText() + "0");
			}
		});
		botoes.get(0).setFont(new Font("Tahoma", Font.BOLD, 22));
		botoes.get(0).setForeground(Color.DARK_GRAY);
		botoes.get(0).setBounds(10, 420, 290, 90);

		//Tecla 1
		botoes.get(1).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				campoNum.setText(campoNum.getText() + "1");
				pai.inBusca.setText(pai.inBusca.getText() + "1");
			}
		});
		botoes.get(1).setFont(new Font("Tahoma", Font.BOLD, 22));
		botoes.get(1).setForeground(Color.DARK_GRAY);
		botoes.get(1).setBounds(10, 320, 90, 90);

		//Tecla 2
		botoes.get(2).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				campoNum.setText(campoNum.getText() + "2");
				pai.inBusca.setText(pai.inBusca.getText() + "2");
			}
		});
		botoes.get(2).setForeground(Color.DARK_GRAY);
		botoes.get(2).setFont(new Font("Tahoma", Font.BOLD, 22));
		botoes.get(2).setBounds(110, 320, 90, 90);

		//Tecla 3
		botoes.get(3).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				campoNum.setText(campoNum.getText() + "3");
				pai.inBusca.setText(pai.inBusca.getText() + "3");
			}
		});
		botoes.get(3).setForeground(Color.DARK_GRAY);
		botoes.get(3).setFont(new Font("Tahoma", Font.BOLD, 22));
		botoes.get(3).setBounds(210, 320, 90, 90);

		//Tecla 4
		botoes.get(4).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				campoNum.setText(campoNum.getText() + "4");
				pai.inBusca.setText(pai.inBusca.getText() + "4");
			}
		});
		botoes.get(4).setForeground(Color.DARK_GRAY);
		botoes.get(4).setFont(new Font("Tahoma", Font.BOLD, 22));
		botoes.get(4).setBounds(10, 220, 90, 90);

		//Tecla 5
		botoes.get(5).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				campoNum.setText(campoNum.getText() + "5");
				pai.inBusca.setText(pai.inBusca.getText() + "5");
			}
		});
		botoes.get(5).setForeground(Color.DARK_GRAY);
		botoes.get(5).setFont(new Font("Tahoma", Font.BOLD, 22));
		botoes.get(5).setBounds(110, 220, 90, 90);

		//Tecla 6
		botoes.get(6).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				campoNum.setText(campoNum.getText() + "6");
				pai.inBusca.setText(pai.inBusca.getText() + "6");
			}
		});
		botoes.get(6).setForeground(Color.DARK_GRAY);
		botoes.get(6).setFont(new Font("Tahoma", Font.BOLD, 22));
		botoes.get(6).setBounds(210, 220, 90, 90);

		//Tecla 7
		botoes.get(7).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				campoNum.setText(campoNum.getText() + "7");
				pai.inBusca.setText(pai.inBusca.getText() + "7");
			}
		});
		botoes.get(7).setForeground(Color.DARK_GRAY);
		botoes.get(7).setFont(new Font("Tahoma", Font.BOLD, 22));
		botoes.get(7).setBounds(10, 120, 90, 90);

		//Tecla 8
		botoes.get(8).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				campoNum.setText(campoNum.getText() + "8");
				pai.inBusca.setText(pai.inBusca.getText() + "8");
			}
		});
		botoes.get(8).setForeground(Color.DARK_GRAY);
		botoes.get(8).setFont(new Font("Tahoma", Font.BOLD, 22));
		botoes.get(8).setBounds(110, 120, 90, 90);

		//Tecla 9
		botoes.get(9).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				campoNum.setText(campoNum.getText() + "9");
				pai.inBusca.setText(pai.inBusca.getText() + "9");
			}
		});
		botoes.get(9).setForeground(Color.DARK_GRAY);
		botoes.get(9).setFont(new Font("Tahoma", Font.BOLD, 22));
		botoes.get(9).setBounds(210, 120, 90, 90);

		//Tecla APAGAR
		backSpc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					campoNum.setText(campoNum.getText().substring(0, (campoNum.getText().length() - 1)));
					pai.inBusca.setText(pai.inBusca.getText().substring(0, (pai.inBusca.getText().length() - 1)));
				} catch (StringIndexOutOfBoundsException ex) {}
			}
		});
		backSpc.setBackground(new Color(178, 34, 34));
		backSpc.setForeground(Color.WHITE);
		backSpc.setFont(new Font("Tahoma", Font.BOLD, 22));
		backSpc.setBounds(210, 45, 90, 60);

		instru.setFont(new Font("Tahoma", Font.BOLD, 12));
		instru.setForeground(Color.DARK_GRAY);
		instru.setBounds(91, 5, 127, 15);
		
		Arrastar drag = new Arrastar();
		this.addMouseListener(drag);
		this.addMouseMotionListener(drag);
	}

	public void mostrar()
	{
		this.setVisible(true);
		this.setAlwaysOnTop(true);
	}
}
