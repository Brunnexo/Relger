package janelas.interacao;

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

import backend.Atributos;

import janelas.menus.Identificacao;
import janelas.menus.Inicio;
import janelas.selecao.Funcao;
import janelas.selecao.SR;

public class SelecionarAtividade extends JFrame {

	private JPanel painel;

	static final int PW = 430;
	static final int PH = 200;

	public Resumo res;
	public Atributos att;

	//Definição de Janela
	SelecionarAtividade selecao = this;

	Identificacao voltar;

	public SelecionarAtividade(Identificacao pai) {
		//Interação de Janela
		voltar = pai;
		pai.setVisible(false);
		att = pai.att;
		res = pai.res;

		painel = new JPanel();
		painel.setBorder(new EmptyBorder(5, 5, 5, 5));
		painel.setLayout(null);

		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setContentPane(painel);
		setUndecorated(true);
		setBounds(0, 0, PW, PH);
		setLocationRelativeTo(null);
		setShape(new RoundRectangle2D.Double(0, 0, PW, PH, 5, 5));

		JLabel instru = new JLabel("SELECIONE O TIPO");
		instru.setHorizontalAlignment(SwingConstants.CENTER);
		instru.setForeground(Color.DARK_GRAY);
		instru.setFont(new Font("Tahoma", Font.PLAIN, 20));
		instru.setBounds(100, 5, 229, 25);
		painel.add(instru);

		JButton btnSr = new JButton("<html><center>\uD83D\uDD27 <br /><font size=\"4\">SERVICE REQUESTS</font></center></html>");
		btnSr.setToolTipText("Service Requests");
		btnSr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SR menuSr = new SR(selecao);
				menuSr.setVisible(true);
			}
		});
		btnSr.setBackground(Color.ORANGE);
		btnSr.setForeground(Color.DARK_GRAY);
		btnSr.setFont(new Font("SansSerif", Font.PLAIN, 80));
		btnSr.setBounds(10, 40, 200, 150);
		painel.add(btnSr);

		JButton btnProj = new JButton();
		btnProj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Funcao sel = new Funcao(selecao);
				sel.setVisible(true);
			}
		});
		btnProj.setText("<html><body><center>PROJETOS/ATIVIDADES DO SETOR</center></body></html>");
		btnProj.setForeground(Color.DARK_GRAY);
		btnProj.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnProj.setBounds(220, 40, 200, 150);
		painel.add(btnProj);

		JButton voltar = new JButton("<");
		voltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pai.setVisible(true);
				res.dispose();
				dispose();
			}
		});
		voltar.setForeground(Color.DARK_GRAY);
		voltar.setFont(new Font("SansSerif", Font.BOLD, 11));
		voltar.setBackground(Color.LIGHT_GRAY);
		voltar.setBounds(-5, -5, 35, 35);
		painel.add(voltar);
		
		
		
	}

	public SelecionarAtividade(Atributos att, Resumo res) {
		this.att = att;
		this.res = res;
		Inicio inicio = new Inicio();
		Identificacao pai = new Identificacao(inicio);

		painel = new JPanel();
		painel.setBorder(new EmptyBorder(5, 5, 5, 5));
		painel.setLayout(null);

		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setContentPane(painel);
		setUndecorated(true);
		setBounds(0, 0, PW, PH);
		setLocationRelativeTo(null);
		setShape(new RoundRectangle2D.Double(0,0,PW,PH,5,5));

		JLabel instru = new JLabel("SELECIONE O TIPO");
		instru.setHorizontalAlignment(SwingConstants.CENTER);
		instru.setForeground(Color.DARK_GRAY);
		instru.setFont(new Font("Tahoma", Font.PLAIN, 20));
		instru.setBounds(100, 5, 229, 25);
		painel.add(instru);

		JButton btnSr = new JButton("<html><center>\uD83D\uDD27 <br /><font size=\"4\">SERVICE REQUESTS</font></center></html>");
		btnSr.setToolTipText("Service Requests");
		btnSr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SR menuSr = new SR(selecao);
				dispose();
				menuSr.setVisible(true);
			}
		});
		btnSr.setBackground(Color.ORANGE);
		btnSr.setForeground(Color.DARK_GRAY);
		btnSr.setFont(new Font("SansSerif", Font.PLAIN, 80));
		btnSr.setBounds(10, 40, 200, 150);
		painel.add(btnSr);

		JButton btnProj = new JButton();
		btnProj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Funcao sel = new Funcao(selecao);
				sel.setVisible(true);
			}
		});
		btnProj.setText("<html><body><center>PROJETOS/ATIVIDADES DO SETOR</center></body></html>");
		btnProj.setForeground(Color.DARK_GRAY);
		btnProj.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnProj.setBounds(220, 40, 200, 150);
		painel.add(btnProj);

		JButton voltar = new JButton("<");
		voltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pai.setVisible(true);
				res.dispose();
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
