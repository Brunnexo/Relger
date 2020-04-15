package janelas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class DescricoesGenericas extends JFrame
{
	public Resumo res;

	public int escolha = 0;
	
	private JPanel painel;
	private JLabel instrucao;
	
	final static int BTNW = 200, BTNH = 150;
	final static int PW = 640, PH = 550;

	DescricoesGenericas descGen = this;
	
	public DescricoesGenericas(Funcao pai) {
		//Janela
		pai.setVisible(false);
		res = pai.res;
		
		painel = new JPanel();
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setBounds(100,100,PW,PH);
		painel.setBorder(new EmptyBorder(5, 5, 5, 5));
		painel.setLayout(null);
		setContentPane(painel);
		setLocationRelativeTo(null);
		setUndecorated(true);
		setShape(new RoundRectangle2D.Double(0,0,PW,PH,5,5));


		//TEXTO INSTRUCIONAL
		instrucao = new JLabel("SELECIONE A DESCRI\u00C7\u00C3O");
		instrucao.setForeground(Color.DARK_GRAY);
		instrucao.setFont(new Font("Tahoma", Font.PLAIN, 25));
		instrucao.setHorizontalAlignment(SwingConstants.CENTER);
		instrucao.setBounds(174, 10, 291, 31);
		painel.add(instrucao);

		//Botão 1
		JButton descr1 = new JButton();
		descr1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				escolha = 0;
				
				DescricoesSub subMenu = new DescricoesSub(descGen);
				subMenu.setVisible(true);
			}
		});
		descr1.setEnabled(false);
		descr1.setForeground(Color.DARK_GRAY);
		descr1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		descr1.setBounds(10, 70, BTNW, BTNH);
		descr1.setForeground(Color.DARK_GRAY);
		painel.add(descr1);

		//Botão 2
		JButton descr2 = new JButton();
		descr2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				escolha = 1;
				
				DescricoesSub subMenu = new DescricoesSub(descGen);
				subMenu.setVisible(true);
			}
		});
		descr2.setEnabled(false);
		descr2.setForeground(Color.DARK_GRAY);
		descr2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		descr2.setBounds(220, 70, BTNW, BTNH);
		painel.add(descr2);

		//Botão 3
		JButton descr3 = new JButton();
		descr3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				escolha = 2;
				
				DescricoesSub subMenu = new DescricoesSub(descGen);
				subMenu.setVisible(true);
			}
		});
		descr3.setEnabled(false);
		descr3.setForeground(Color.DARK_GRAY);
		descr3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		descr3.setBounds(430, 70, BTNW, BTNH);
		painel.add(descr3);


		//Botão 4
		JButton descr4 = new JButton();
		descr4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				escolha = 3;
				
				DescricoesSub subMenu = new DescricoesSub(descGen);
				subMenu.setVisible(true);
			}
		});
		descr4.setEnabled(false);
		descr4.setForeground(Color.DARK_GRAY);
		descr4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		descr4.setBounds(10, 230, BTNW, BTNH);
		painel.add(descr4);

		//Botão 5
		JButton descr5 = new JButton();
		descr5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				escolha = 4;
				
				DescricoesSub subMenu = new DescricoesSub(descGen);
				subMenu.setVisible(true);
			}
		});
		descr5.setEnabled(false);
		descr5.setForeground(Color.DARK_GRAY);
		descr5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		descr5.setBounds(220, 230, BTNW, BTNH);
		painel.add(descr5);

		//Botão 6
		JButton descr6 = new JButton();
		descr6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				escolha = 5;
				
				DescricoesSub subMenu = new DescricoesSub(descGen);
				subMenu.setVisible(true);
			}
		});
		descr6.setEnabled(false);
		descr6.setForeground(Color.DARK_GRAY);
		descr6.setFont(new Font("Tahoma", Font.PLAIN, 20));
		descr6.setBounds(430, 230, BTNW, BTNH);
		painel.add(descr6);

		//Botão 7
		JButton descr7 = new JButton();
		descr7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				escolha = 6;
				
				DescricoesSub subMenu = new DescricoesSub(descGen);
				subMenu.setVisible(true);
			}
		});
		descr7.setEnabled(false);
		descr7.setForeground(Color.DARK_GRAY);
		descr7.setFont(new Font("Tahoma", Font.PLAIN, 20));
		descr7.setBounds(10, 390, BTNW, BTNH);
		painel.add(descr7);

		//Botão 8
		JButton descr8 = new JButton();
		descr8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				escolha = 7;
				
				DescricoesSub subMenu = new DescricoesSub(descGen);
				subMenu.setVisible(true);
			}
		});
		descr8.setEnabled(false);
		descr8.setForeground(Color.DARK_GRAY);
		descr8.setFont(new Font("Tahoma", Font.PLAIN, 20));
		descr8.setBounds(220, 390, BTNW, BTNH);
		painel.add(descr8);

		//Botão 9
		JButton descr9 = new JButton();
		descr9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				escolha = 8;
				
				DescricoesSub subMenu = new DescricoesSub(descGen);
				subMenu.setVisible(true);
			}
		});
		descr9.setEnabled(false);
		descr9.setForeground(Color.DARK_GRAY);
		descr9.setFont(new Font("Tahoma", Font.PLAIN, 20));
		descr9.setBounds(430, 390, BTNW, BTNH);
		painel.add(descr9);

		//Adicionar Descrições
		for (int i = 0; i < descr.length; i++)
		{
			switch(i)
			{
			case 0:
				descr1.setText(("<html>" + "<body>" + "<center>" + descr[i] + "</center>" + "</body>" + "</html>").toUpperCase());
				descr1.setEnabled(true);
				break;
			case 1:
				descr2.setText(("<html>" + "<body>" + "<center>" + descr[i] + "</center>" + "</body>" + "</html>").toUpperCase());
				descr2.setEnabled(true);
				break;
			case 2:
				descr3.setText(("<html>" + "<body>" + "<center>" + descr[i] + "</center>" + "</body>" + "</html>").toUpperCase());
				descr3.setEnabled(true);
				break;
			case 3:
				descr4.setText(("<html>" + "<body>" + "<center>" + descr[i] + "</center>" + "</body>" + "</html>").toUpperCase());
				descr4.setEnabled(true);
				break;
			case 4:
				descr5.setText(("<html>" + "<body>" + "<center>" + descr[i] + "</center>" + "</body>" + "</html>").toUpperCase());
				descr5.setEnabled(true);
				break;
			case 5:
				descr6.setText(("<html>" + "<body>" + "<center>" + descr[i] + "</center>" + "</body>" + "</html>").toUpperCase());
				descr6.setEnabled(true);
				break;
			case 6:
				descr7.setText(("<html>" + "<body>" + "<center>" + descr[i] + "</center>" + "</body>" + "</html>").toUpperCase());
				descr7.setEnabled(true);
				break;
			case 7:
				descr8.setText(("<html>" + "<body>" + "<center>" + descr[i] + "</center>" + "</body>" + "</html>").toUpperCase());
				descr8.setEnabled(true);
				break;
			case 8:
				descr9.setText(("<html>" + "<body>" + "<center>" + descr[i] + "</center>" + "</body>" + "</html>").toUpperCase());
				descr9.setEnabled(true);
				break;
			}
		}
		
		//TEXTO DESCRITIVO
		JLabel nome = new JLabel((("Crachá: " + att.getCrachaFunc() + " | " + "Nome: " + att.getNomeFunc() + " | " + "Função: " + att.getNomeFuncao()).toUpperCase()));
		nome.setHorizontalAlignment(SwingConstants.CENTER);
		nome.setForeground(Color.DARK_GRAY);
		nome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		nome.setBounds(20, 50, 600, 15);
		painel.add(nome);

		//Voltar
		JButton voltar = new JButton("<");
		voltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				att.voltar_funcoes();
				pai.setVisible(true);
				dispose();
			}
		});
		voltar.setForeground(Color.DARK_GRAY);
		voltar.setFont(new Font("SansSerif", Font.BOLD, 11));
		voltar.setBackground(Color.LIGHT_GRAY);
		voltar.setBounds(-5, -5, 35, 35);
		painel.add(voltar);
		
	}
}
