package janelas.menus;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import backend.Atributos;
import janelas.interacao.PesquisaProjeto;
import janelas.interacao.Resumo;
import mssql.Projetos;

public class SelecionarMontadora extends JFrame
{
	public Atributos att;
	public Resumo res;

	final static int PW = 850, PH = 540;
	final static int BTNW = 200, BTNH = 150;

	private JPanel painel;
	private JLabel instrucao;


	/**
	 * General Motors:	1
	 * Volkswagen:		2
	 * Ford:			3
	 * FCA:				4
	 * Renault:			5
	 * Honda:			6
	 * Nissan:			7
	 * Toyota:			8
	 * Hyundai:			9
	 * Mercedes:		10
	 * PSA:				11
	 * MAN:				12
	 **/

	ResultSet pesquisa;
	public String busca = new String();
	
	SelecionarMontadora selMont = this;

	public SelecionarMontadora(DescricoesSub pai)
	{
		//Janela
		pai.setVisible(false);
		att = pai.att;
		res = pai.res;

		//Atributos do painel
		painel = new JPanel();
		painel.setBorder(new EmptyBorder(5, 5, 5, 5));
		painel.setLayout(null);

		//Atributos da janela
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setBounds(100,100,PW,PH);
		setContentPane(painel);
		setLocationRelativeTo(null);
		setUndecorated(true);
		setShape(new RoundRectangle2D.Double(0,0,PW,PH,5,5));

		//TEXTO INSTRUCIONAL
		instrucao = new JLabel("SELECIONE A MONTADORA");
		instrucao.setForeground(Color.DARK_GRAY);
		instrucao.setFont(new Font("Tahoma", Font.PLAIN, 25));
		instrucao.setHorizontalAlignment(SwingConstants.CENTER);
		instrucao.setBounds(204, 10, 442, 30);

		//Componentes do Painel
		JButton pesquisar = new JButton();
		JButton gm = new JButton();
		JButton vw = new JButton();
		JButton ford = new JButton();
		JButton fca = new JButton();
		JButton renault = new JButton();
		JButton honda = new JButton();
		JButton nissan = new JButton();
		JButton toyota = new JButton();
		JButton hyundai = new JButton();
		JButton mercedes = new JButton();
		JButton psa = new JButton();
		JButton man = new JButton();
		JButton back = new JButton("<");
		JLabel nome = new JLabel(("Crachá: " + att.getCrachaFunc() + " | " + "Nome: " + att.getNomeFunc() + " | " + "Função: " + att.getNomeFuncao() + " | " + "Descrição: " + att.getDescTrabalho()).toUpperCase());
		JButton outros = new JButton();

		//Adição
		painel.add(pesquisar);
		painel.add(gm);
		painel.add(vw);
		painel.add(ford);
		painel.add(fca);
		painel.add(renault);
		painel.add(honda);
		painel.add(nissan);
		painel.add(toyota);
		painel.add(hyundai);
		painel.add(mercedes);
		painel.add(psa);
		painel.add(man);
		painel.add(back);
		painel.add(nome);
		painel.add(instrucao);
		painel.add(outros);
		
		//BOTÃO PESQUISAR
		pesquisar.setIconTextGap(0);
		pesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PesquisaProjeto pesqProj = new PesquisaProjeto(selMont);
				pesqProj.setVisible(true);
			}
		});
		pesquisar.setIcon(new ImageIcon(SelecionarMontadora.class.getResource("/icones/lupa.png")));
		pesquisar.setBounds(805, -5, 50, 50);

		//GENERAL MOTORS
		gm.setIcon(new ImageIcon(SelecionarMontadora.class.getResource("/logos/generalmotors.png")));
		gm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				att.setMontadora(1);

				SelecionarProjeto selProj = new SelecionarProjeto(selMont);
				selProj.setVisible(true);
			}
		});
		gm.setBounds(430, 70, BTNW, BTNH);
		gm.setForeground(Color.DARK_GRAY);

		//VOLKSWAGEN
		vw.setIcon(new ImageIcon(SelecionarMontadora.class.getResource("/logos/vw.png")));
		vw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				att.setMontadora(2);

				SelecionarProjeto menu = new SelecionarProjeto(selMont);
				menu.setVisible(true);
			}
		});
		vw.setBounds(640, 385, BTNW, BTNH);
		vw.setForeground(Color.DARK_GRAY);

		//FORD
		ford.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				att.setMontadora(3);

				SelecionarProjeto menu = new SelecionarProjeto(selMont);
				menu.setVisible(true);
			}
		});
		ford.setIcon(new ImageIcon(SelecionarMontadora.class.getResource("/logos/ford.png")));
		ford.setBounds(220, 70, BTNW, BTNH);
		ford.setForeground(Color.DARK_GRAY);

		//FCA
		fca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				att.setMontadora(4);

				SelecionarProjeto menu = new SelecionarProjeto(selMont);
				menu.setVisible(true);
			}
		});
		fca.setToolTipText("Fiat e Jeep");
		fca.setIcon(new ImageIcon(SelecionarMontadora.class.getResource("/logos/fca.png")));
		fca.setBounds(10, 70, BTNW, BTNH);
		fca.setForeground(Color.DARK_GRAY);

		//RENAULT
		renault.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				att.setMontadora(5);

				SelecionarProjeto menu = new SelecionarProjeto(selMont);
				menu.setVisible(true);
			}
		});
		renault.setToolTipText("Renault");
		renault.setIcon(new ImageIcon(SelecionarMontadora.class.getResource("/logos/renault.png")));
		renault.setBounds(220, 385, BTNW, BTNH);
		renault.setForeground(Color.DARK_GRAY);

		//HONDA
		honda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				att.setMontadora(6);

				SelecionarProjeto menu = new SelecionarProjeto(selMont);
				menu.setVisible(true);
			}
		});
		honda.setIcon(new ImageIcon(SelecionarMontadora.class.getResource("/logos/honda.png")));
		honda.setBounds(640, 70, BTNW, BTNH);
		honda.setForeground(Color.DARK_GRAY);

		//NISSAN
		nissan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				att.setMontadora(7);

				SelecionarProjeto menu = new SelecionarProjeto(selMont);
				menu.setVisible(true);
			}
		});
		nissan.setIcon(new ImageIcon(SelecionarMontadora.class.getResource("/logos/nissan.png")));
		nissan.setBounds(640, 230, BTNW, BTNH);
		nissan.setForeground(Color.DARK_GRAY);

		//TOYOTA
		toyota.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				att.setMontadora(8);

				SelecionarProjeto menu = new SelecionarProjeto(selMont);
				menu.setVisible(true);
			}
		});
		toyota.setIcon(new ImageIcon(SelecionarMontadora.class.getResource("/logos/toyota.png")));
		toyota.setForeground(Color.DARK_GRAY);
		toyota.setBounds(430, 385, BTNW, BTNH);

		//HYUNDAI
		hyundai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				att.setMontadora(9);

				SelecionarProjeto menu = new SelecionarProjeto(selMont);
				menu.setVisible(true);
			}
		});
		hyundai.setIcon(new ImageIcon(SelecionarMontadora.class.getResource("/logos/hyundai.png")));
		hyundai.setForeground(Color.DARK_GRAY);
		hyundai.setBounds(10, 230, BTNW, BTNH);

		//MERCEDES
		mercedes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				att.setMontadora(10);

				SelecionarProjeto menu = new SelecionarProjeto(selMont);
				menu.setVisible(true);
			}
		});
		mercedes.setIcon(new ImageIcon(SelecionarMontadora.class.getResource("/logos/mercedes.png")));
		mercedes.setForeground(Color.DARK_GRAY);
		mercedes.setBounds(430, 230, BTNW, BTNH);

		//PSA
		psa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				att.setMontadora(11);

				SelecionarProjeto menu = new SelecionarProjeto(selMont);
				menu.setVisible(true);
			}
		});
		psa.setToolTipText("Peugeot e Citr\u00F6en");
		psa.setIcon(new ImageIcon(SelecionarMontadora.class.getResource("/logos/psa.png")));
		psa.setForeground(Color.DARK_GRAY);
		psa.setBounds(10, 385, BTNW, BTNH);

		//MAN
		man.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				att.setMontadora(12);

				SelecionarProjeto menu = new SelecionarProjeto(selMont);
				menu.setVisible(true);
			}
		});
		man.setIcon(new ImageIcon(SelecionarMontadora.class.getResource("/logos/man.png")));
		man.setForeground(Color.DARK_GRAY);
		man.setBounds(220, 230, BTNW, BTNH);

		//VOLTAR
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pai.setVisible(true);
				dispose();
			}
		});
		back.setForeground(Color.DARK_GRAY);
		back.setFont(new Font("SansSerif", Font.BOLD, 11));
		back.setBackground(Color.LIGHT_GRAY);
		back.setBounds(-5, -5, 35, 35);

		//Nome
		nome.setHorizontalAlignment(SwingConstants.CENTER);
		nome.setForeground(Color.DARK_GRAY);
		nome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		nome.setBounds(87, 50, 675, 15);
		
		//Outros
		outros.setForeground(Color.DARK_GRAY);
		outros.setFont(new Font("Tahoma", Font.BOLD, 15));
		outros.setText("OUTROS");
		outros.setIconTextGap(0);
		outros.setBounds(700, -5, 93, 50);
		
		outros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResultSet outros = Projetos.pesquisarGeral();
				
				SelecionarProjeto menu = new SelecionarProjeto(selMont, outros);
				menu.setVisible(true);
			}
		});
	}
}
