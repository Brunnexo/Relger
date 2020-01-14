package janelas.selecao;

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

import janelas.interacao.Resumo;
import janelas.interacao.SelecionarAtividade;
import janelas.menus.DescricoesGenericas;

public class Funcao extends JFrame
{
	public int selecao;

	private JPanel painel; //Declara painel
	private JLabel instrucao; //Texto fixo

	final static int BTNW = 200, BTNH = 150;
	final static int PW = 640, PH = 390;

	public Resumo res;
	public Atributos att;
	
	Funcao selFunc = this;
	
	public Funcao(SelecionarAtividade pai) //Construtor da área da janela
	{
		pai.setVisible(false);
		res = pai.res;
		att = pai.att;
		
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); //Operação ao sair
		setBounds(100, 100, PW, PH);

		painel = new JPanel(); //Instancia painel/janela
		painel.setBorder(new EmptyBorder(5, 5, 5, 5)); //Define bordas do painel
		setContentPane(painel); //Define painel para conteúdos
		painel.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true); //Transforma a janela em fixa e sem bordas
		setShape(new RoundRectangle2D.Double(0,0,PW,PH,5,5));


		//Texto para instrução
		instrucao = new JLabel("SELECIONE A FUNÇÃO");
		instrucao.setForeground(Color.DARK_GRAY);
		instrucao.setFont(new Font("Tahoma", Font.PLAIN, 25));
		instrucao.setHorizontalAlignment(SwingConstants.CENTER);
		instrucao.setBounds(189, 10, 262, 30);
		painel.add(instrucao);

		//Botão ELETRICISTA
		JButton eletricista = new JButton("ELETRICISTA");
		eletricista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				att.settEle(true);
				
				DescricoesGenericas menu = new DescricoesGenericas(selFunc);
				menu.setVisible(true);
			}
		});
		eletricista.setEnabled(att.isEle());
		eletricista.setForeground(Color.DARK_GRAY);
		eletricista.setFont(new Font("Tahoma", Font.PLAIN, 20));
		eletricista.setBounds(10, 70, BTNW, BTNH); //Dimensões do botão (coordenada X, coordenada Y, largura, altura)
		painel.add(eletricista); //Adiciona o botão ao painel

		//Botão MECÂNICO
		JButton mecanico = new JButton("MECÂNICO");
		mecanico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				att.settMec(true);
				
				DescricoesGenericas menu = new DescricoesGenericas(selFunc);
				menu.setVisible(true);
			}
		});
		mecanico.setEnabled(att.isMec());
		mecanico.setForeground(Color.DARK_GRAY);
		mecanico.setFont(new Font("Tahoma", Font.PLAIN, 20));
		mecanico.setBounds(220, 70, BTNW, BTNH);
		painel.add(mecanico);

		//Botão PROGRAMADOR
		JButton programador = new JButton("PROGRAMADOR");
		programador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				att.settProg(true);
				
				DescricoesGenericas menu = new DescricoesGenericas(selFunc);
				menu.setVisible(true);
			}
		});
		programador.setEnabled(att.isProg());
		programador.setForeground(Color.DARK_GRAY);
		programador.setFont(new Font("Tahoma", Font.PLAIN, 20));
		programador.setBounds(430, 70, BTNW, BTNH);
		painel.add(programador);

		//Botão ENGENHEIRO
		JButton engenheiro = new JButton("ENGENHEIRO");
		engenheiro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				att.settEng(true);
				
				DescricoesGenericas menu = new DescricoesGenericas(selFunc);
				menu.setVisible(true);
			}
		});
		engenheiro.setEnabled(att.isEng());
		engenheiro.setForeground(Color.DARK_GRAY);
		engenheiro.setFont(new Font("Tahoma", Font.PLAIN, 20));
		engenheiro.setBounds(10, 230, BTNW, BTNH);
		painel.add(engenheiro);

		//Botão PROJETISTA
		JButton projetista = new JButton("PROJETISTA");
		projetista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				att.settProj(true);
				
				DescricoesGenericas menu = new DescricoesGenericas(selFunc);
				menu.setVisible(true);
			}
		});
		projetista.setEnabled(att.isProj());
		projetista.setForeground(Color.DARK_GRAY);
		projetista.setFont(new Font("Tahoma", Font.PLAIN, 20));
		projetista.setBounds(220, 230, BTNW, BTNH);
		painel.add(projetista);

		//Botão ADMINISTRATIVO
		JButton administrativo = new JButton("ADMINISTRATIVO");
		administrativo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				att.settAdm(true);
					
				DescricoesGenericas menu = new DescricoesGenericas(selFunc);
				menu.setVisible(true);
			}
		});
		administrativo.setEnabled(att.isAdm());
		administrativo.setForeground(Color.DARK_GRAY);
		administrativo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		administrativo.setBounds(430, 230, BTNW, BTNH);
		painel.add(administrativo);
		
		//Voltar
		JButton voltar = new JButton("<");
		voltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pai.setVisible(true);
				dispose();
			}
		});
		voltar.setForeground(Color.DARK_GRAY);
		voltar.setFont(new Font("SansSerif", Font.BOLD, 11));
		voltar.setBackground(Color.LIGHT_GRAY);
		voltar.setBounds(-5, -5, 35, 35);
		painel.add(voltar);
		
		//Nome
		JLabel nome = new JLabel(("Crachá: " + att.getCrachaFunc() + " | " + "Nome: " + att.getNomeFunc()).toUpperCase());
		nome.setHorizontalAlignment(SwingConstants.CENTER);
		nome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		nome.setForeground(Color.DARK_GRAY);
		nome.setBounds(147, 50, 345, 15);
		painel.add(nome);
		
		
		
	}
}
