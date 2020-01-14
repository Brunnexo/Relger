package janelas.menus;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import backend.Atributos;
import janelas.DefinirTempo;
import janelas.interacao.Resumo;
import janelas.selecao.Montadora;
import mssql.Projetos;

public class DescricoesSub extends JFrame
{
	public Atributos att;
	public Resumo res;

	private JPanel painel;
	private JLabel instrucao;

	final static int BTNW = 200, BTNH = 150;
	final static int PW = 640, PH = 390;

	String[] botoes = {"Sim", "Não"};
	String[][] descr;

	int botao = 0;
	
	DescricoesSub descSub = this;
	
	public DescricoesSub(DescricoesGenericas pai)
	{
		//Janelas
		pai.setVisible(false);
		att = pai.att;
		res = pai.res;
		botao = pai.escolha;
		
		
		//TEXTOS DE DESCRIÇÕES
		descr = new String[][] {};

		String[][] trabAdm =
			{
					{"CONTROLE DE COMPRAS", "CONTROLE DE DEMANDA", "CONTROLE DE HORAS"}, //[2]
					{"DOCUMENTAÇÃO DE MÁQUINA", "DOCUMENTAÇÃO", "PROJETO"}, //[1]
					{"ORÇAMENTO", "GESTÃO DE PROJETO"}, //[1]
					{"RETRABALHO INTERNO", "RETRABALHO EXTERNO", "MELHORIAS"}, //[1]
					{"TESTES E PROTÓTIPOS", "MANUTENÇÃO", "ALMOXARIFADO", "5S", "NÃO PRODUTIVOS", "AUSÊNCIA"} //[5]
					//[7]
			};

		String[][] trabProjEng =
			{
					{"DESENVOLVIMENTO DE PROJETO", "CONTROLE DE PROJETO", "LISTA DE PEÇAS", "DESIGN REVIEW"}, //[2]
					{"VISITA TÉCNICA"}, //[0]
					{"FOLLOW UP E DEMANDA"}, //[0]
					{"ELABORAÇÃO DE TERMO DE ABERTURA", "ELABORAÇÃO DE ESCOPO"}, //[1]
					{"ACOMPANHAMENTO DE MONTAGEM", "ACOMPANHAMENTO DE S.A.", "ACOMPANHAMENTO DE EVENTO", "GESTÃO DE PROJETO"}, //[2]
					{"DOCUMENTAÇÃO DE MÁQUINA", "DOCUMENTAÇÃO", "ORÇAMENTO", "GESTÃO DE PROJETO"}, //[3]
					{"RETRABALHO INTERNO", "RETRABALHO EXTERNO", "MELHORIAS"},
					{"TESTES E PROTÓTIPOS", "MANUTENÇÃO", "ALMOXARIFADO", "5S", "NÃO PRODUTIVOS", "AUSÊNCIA"} //[5]
					//[7]
			};
		String[][] trabEle =
			{
					{"MONTAGEM DE PAINEL ELÉTRICO", "MONTAGEM DE MÁQUINA", "MONTAGEM GERAL"},
					{"TESTES"},
					{"IDENTIFICAÇÃO DE MÁQUINAS", "IDENTIFICAÇÃO DE PAINEL ELÉTRICO"},
					{"ESQUEMA ELÉTRICO", "LISTA DE PEÇAS", "DOCUMENTAÇÃO"},
					{"ORÇAMENTO"},
					{"GESTÃO DE PROJETO", "ACOMPANHAMENTO DE S.A.", "ACOMPANHAMENTO DE PRODUÇÃO", "ACOMPANHAMENTO DE FORNCEDOR", "ACOMPANHAMENTO DE EVENTO", "ACOMPANHAMENTO DE MONTAGEM"},
					{"RETRABALHO INTERNO", "RETRABALHO EXTERNO", "MELHORIAS"},
					{"TESTES E PROTÓTIPOS", "MANUTENÇÃO", "ALMOXARIFADO", "5S", "NÃO PRODUTIVOS", "AUSÊNCIA"}
					//[5]
			};
		String[][] trabProg =
			{
					{"PROGRAMAÇÃO", "DESENVOLVIMENTO DE PROGRAMA", "AJUSTE DE PROGRAMA", "AUXÍLIO EM PROGRAMAÇÃO"},
					{"PROJETO ELÉTRICO", "CORREÇÃO DE PROJETO ELÉTRICO"},
					{"DOCUMENTAÇÃO"},
					{"ORÇAMENTO"},
					{"GESTÃO DE PROJETO"},
					{"RETRABALHO INTERNO", "RETRABALHO EXTERNO", "MELHORIAS"},
					{"TESTES E PROTÓTIPOS", "MANUTENÇÃO", "ALMOXARIFADO", "5S", "NÃO PRODUTIVOS", "AUSÊNCIA"}
					//[4]
			};
		String[][] trabMec =
			{
					{"MONTAGEM GERAL", "MONTAGEM DE SISTEMA PNEUMÁTICO"},
					{"USINAGEM FRESADORA", "USINAGEM TORNO", "USINAGEM FURADEIRA", "USINAGEM GERAL"},
					{"AJUSTES"},
					{"CALDEIRARIA - CHAPAS", "CALDEIRARIA - CORTE", "CALDEIRARIA - DOBRA", "CALDEIRARIA - SOLDA", "CALDEIRARIA - TRAÇAGEM"},
					{"DOCUMENTAÇÃO", "LISTA DE MATERIAIS"},
					{"ORÇAMENTO"},
					{"GESTÃO DE PROJETO"},
					{"RETRABALHO INTERNO", "RETRABALHO EXTERNO", "MELHORIAS"},
					{"TESTES E PROTÓTIPOS", "MANUTENÇÃO", "ALMOXARIFADO", "5S", "NÃO PRODUTIVOS", "AUSÊNCIA"}
					//[5]
			};



		//Define função selecionada
		if (att.istAdm())
			descr = trabAdm;
		else if (att.istProj() || att.istEng())
			descr = trabProjEng;
		else if (att.istEle())
			descr = trabEle;
		else if (att.istProg())
			descr = trabProg;
		else if (att.istMec())
			descr = trabMec;

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
		descr1.setAlignmentX(Component.CENTER_ALIGNMENT);
		descr1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				att.setDescTrabalho(descr[botao][0]);
				proximo(0);
			}
		});
		descr1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		descr1.setBounds(10, 70, BTNW, BTNH);
		descr1.setForeground(Color.DARK_GRAY);
		descr1.setEnabled(false);
		painel.add(descr1);

		//Botão 2
		JButton descr2 = new JButton();
		descr2.setAlignmentX(Component.CENTER_ALIGNMENT);
		descr2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				att.setDescTrabalho(descr[botao][1]);
				proximo(1);
			}
		});
		descr2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		descr2.setBounds(220, 70, BTNW, BTNH);
		descr2.setForeground(Color.DARK_GRAY);
		descr2.setEnabled(false);
		painel.add(descr2);

		//Botão 3
		JButton descr3 = new JButton();
		descr3.setAlignmentX(Component.CENTER_ALIGNMENT);
		descr3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				att.setDescTrabalho(descr[botao][2]);
				proximo(2);
			}
		});
		descr3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		descr3.setBounds(430, 70, BTNW, BTNH);
		descr3.setForeground(Color.DARK_GRAY);
		descr3.setEnabled(false);
		painel.add(descr3);

		//Botão 4
		JButton descr4 = new JButton();
		descr4.setAlignmentX(Component.CENTER_ALIGNMENT);
		descr4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				att.setDescTrabalho(descr[botao][3]);
				proximo(3);
			}
		});
		descr4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		descr4.setForeground(Color.DARK_GRAY);
		descr4.setBounds(10, 230, BTNW, BTNH);
		descr4.setEnabled(false);
		painel.add(descr4);

		//Botão 5
		JButton descr5 = new JButton();
		descr5.setAlignmentX(Component.CENTER_ALIGNMENT);
		descr5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				att.setDescTrabalho(descr[botao][4]);
				proximo(4);
			}
		});
		descr5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		descr5.setBounds(220, 230, BTNW, BTNH);
		descr5.setForeground(Color.DARK_GRAY);
		descr5.setEnabled(false);
		painel.add(descr5);

		//Botão 6
		JButton descr6 = new JButton();
		descr6.setAlignmentX(Component.CENTER_ALIGNMENT);
		descr6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				att.setDescTrabalho(descr[botao][5]);
				proximo(5);
			}
		});
		descr6.setFont(new Font("Tahoma", Font.PLAIN, 20));
		descr6.setBounds(430, 230, BTNW, BTNH);
		descr6.setForeground(Color.DARK_GRAY);
		descr6.setEnabled(false);
		painel.add(descr6);

		//Adicionar Descrições
		for (int i = 0; i < descr[botao].length; i++)
		{
			switch(i)
			{
			case 0:
				descr1.setText(("<html>" + "<center>" + descr[botao][i] + "</center>" + "</html>").toUpperCase());
				descr1.setEnabled(true);
				break;
			case 1:
				descr2.setText(("<html>" + "<center>" + descr[botao][i] + "</center>" + "</html>").toUpperCase());
				descr2.setEnabled(true);
				break;
			case 2:
				descr3.setText(("<html>" + "<center>" + descr[botao][i] + "</center>" + "</html>").toUpperCase());
				descr3.setEnabled(true);
				break;
			case 3:
				descr4.setText(("<html>" + "<center>" + descr[botao][i] + "</center>" + "</html>").toUpperCase());
				descr4.setEnabled(true);
				break;
			case 4:
				descr5.setText(("<html>" + "<center>" + descr[botao][i] + "</center>" + "</html>").toUpperCase());
				descr5.setEnabled(true);
				break;
			case 5:
				descr6.setText(("<html>" + "<center>" + descr[botao][i] + "</center>" + "</html>").toUpperCase());
				descr6.setEnabled(true);
				break;
			}
		}

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

		//Resumo
		JLabel nome = new JLabel(("Crachá: " + att.getCrachaFunc() + " | " + "Nome: " + att.getNomeFunc() + " | " + "Função: " + att.getNomeFuncao()).toUpperCase());
		nome.setHorizontalAlignment(SwingConstants.CENTER);
		nome.setForeground(Color.DARK_GRAY);
		nome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		nome.setBounds(70, 50, 500, 15);
		painel.add(nome);
	}

	public void proximo(int i)
	{
		//Caso seja administrativo
		if (att.istAdm())
		{
			att.setWo(Projetos.geralWo(att.getWoFunc(), descr[botao][i]));
			trabComentado();
		}
		else
		{
			if (att.trabGeral())
			{
				att.setWo(Projetos.geralWo(att.getWoFunc(), descr[botao][i]));
				trabComentado();
			}
			else
			{
				Montadora selM = new Montadora(descSub);
				selM.setVisible(true);
			}
		}
	}

	public void trabComentado()
	{
		switch (JOptionPane.showOptionDialog(this.painel,
				"Deseja adicionar uma observação?",
				"Adicionar comentário",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,
				botoes,
				botoes[1]))
		{
		case 0:
			try
			{
				
				att.setDescTrabalho(att.getDescTrabalho() + " | " + 
						JOptionPane.showInputDialog(
								this.painel,
								"Observação a respeito do serviço",
								"Observação", JOptionPane.INFORMATION_MESSAGE).toUpperCase());
			} catch (NullPointerException ex)
			{

			}
			DefinirTempo tempo0 = new DefinirTempo(descSub);
			dispose();
			tempo0.setVisible(true);
			break;
		case 1:
			DefinirTempo tempo1 = new DefinirTempo(descSub);
			dispose();
			tempo1.setVisible(true);
			break;
		}
	}
}
