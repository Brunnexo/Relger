package janelas.interacao;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;



public class Interacao extends JFrame {

	static final int INTERROGACAO = 1;
	static final int EXCLAMACAO_SIMPLES = 2;
	static final int EXCLAMACAO_CRITICO = 3;
	
	private JPanel painel;
	public int bt = 0;

	public Interacao(JPanel pai, String tit, String descr, int tipo, String btn1, String btn2) {
		//Atributos do componente chamador
		pai.setEnabled(false);
		
		//Atributos do painel
		painel = new JPanel();
		painel.setBorder(new LineBorder(new Color(64, 64, 64), 2, true));
		painel.setLayout(null);
		//Atributos da janela
		setContentPane(painel);
		setBounds(100, 100, 300, 170);
		setLocationRelativeTo(null);
		setUndecorated(true);
		setShape(new RoundRectangle2D.Double(0,0,300,170,5,5));
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		//Componentes
		JLabel titulo = new JLabel(tit.toUpperCase());
		JButton exit = new JButton("X");
		JButton botao1 = new JButton(btn1);
		JButton botao2 = new JButton(btn2);
		JLabel desc = new JLabel(descr);
		JLabel icone = new JLabel();

		//Adição
		painel.add(titulo);
		painel.add(exit);
		painel.add(botao1);
		painel.add(desc);
		painel.add(botao2);
		painel.add(icone);

		//Título
		titulo.setBounds(32, 10, 235, 15);
		titulo.setFont(new Font("Tahoma", Font.BOLD, 12));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setForeground(Color.DARK_GRAY);

		//Botão Sair
		exit.setBounds(270, -5, 35, 35);
		exit.setBorder(null);
		exit.setForeground(Color.WHITE);
		exit.setBackground(new Color(178, 34, 34));
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				dispose();
			}
		});
		exit.setFont(new Font("SansSerif", Font.BOLD, 10));

		//Botão 1
		if ((tipo == 3) || (tipo == 1))
		{
			botao1.setBounds(90, 115, 120, 50);
			botao1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if ((tipo == 3) || (tipo == 1)) {
						bt = 1;
						dispose();
					}
				}
			});
		}
		else if (tipo == 2)
		{
			botao1.setBounds(5, 115, 120, 50);
		}

		botao1.setForeground(Color.DARK_GRAY);

		//Botão 2
		botao2.setBounds(175, 115, 120, 50);
		botao2.setForeground(Color.DARK_GRAY);

		if ((tipo == 3) || (tipo == 1))
		{
			botao2.setContentAreaFilled(false);
			botao2.setEnabled(false);
		}

		//Descrição
		desc.setForeground(Color.DARK_GRAY);
		desc.setBounds(70, 35, 225, 75);
		desc.setHorizontalAlignment(SwingConstants.CENTER);

		//Icone
		icone.setBounds(10, 45, 50, 50);
		
		//Definir ícone de janela
		switch (tipo)
		{
		case 1:
			icone.setIcon(new ImageIcon(Interacao.class.getResource("/icones/question.png"))); //Interrogação
			break;
		case 2:
			icone.setIcon(new ImageIcon(Interacao.class.getResource("/icones/exclamation_s.png"))); //Exclamação simples
			break;
		case 3:
			icone.setIcon(new ImageIcon(Interacao.class.getResource("/icones/exclamation.png"))); //Exclamação critica
			break;
		}
	}

	public void mostrar()
	{	
		this.setVisible(true);
		this.setAlwaysOnTop(true);
	}
}
