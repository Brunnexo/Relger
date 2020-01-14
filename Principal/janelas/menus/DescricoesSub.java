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

		String[] geral = {"TESTES E PROTÓTIPOS", "MANUTENÇÃO", "ALMOXARIFADO", "5S", "NÃO PRODUTIVOS", "AUSÊNCIA"};
		String[] retrabalhoMelhorias = {"RETRABALHO INTERNO", "RETRABALHO EXTERNO", "MELHORIAS"};
		
		String[][] trabAdm =
			{
					{"CONTROLE DE COMPRAS", "CONTROLE DE DEMANDA", "CONTROLE DE HORAS"}, //[2]
					{"DOCUMENTAÇÃO DE MÁQUINA", "DOCUMENTAÇÃO", "PROJETO"}, //[1]
					{"ORÇAMENTO", "GESTÃO DE PROJETO", "FOLLOW UP E DEMANDA"}, //[1]
					retrabalhoMelhorias, //[1]
					geral //[5]
					//[7]
			};

		String[][] trabProjEng =
			{
					{"DESENVOLVIMENTO DE PROJETO", "CONTROLE DE PROJETO", "LISTA DE PEÇAS", "DESIGN REVIEW"}, //[2]
					{"VISITA TÉCNICA"}, //[0]
					{"ELABORAÇÃO DE TERMO DE ABERTURA", "ELABORAÇÃO DE ESCOPO"}, //[1]
					{"ACOMPANHAMENTO DE MONTAGEM", "ACOMPANHAMENTO DE S.A.", "ACOMPANHAMENTO DE EVENTO", "ACOMPANHAMENTO DE PESSOAL", "GESTÃO DE PROJETO", "FOLLOW UP E DEMANDA"}, //[2]
					{"DOCUMENTAÇÃO DE MÁQUINA", "DOCUMENTAÇÃO", "ORÇAMENTO"}, //[3]
					retrabalhoMelhorias,
					geral //[5]
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
					retrabalhoMelhorias,
					geral
					//[5]
			};
		String[][] trabProg =
			{
					{"PROGRAMAÇÃO", "DESENVOLVIMENTO DE PROGRAMA", "AJUSTE DE PROGRAMA", "AUXÍLIO EM PROGRAMAÇÃO", "REALIZAÇÃO DE BACKUP"},
					{"PROJETO ELÉTRICO", "CORREÇÃO DE PROJETO ELÉTRICO"},
					{"DOCUMENTAÇÃO"},
					{"ORÇAMENTO"},
					{"GESTÃO DE PROJETO"},
					retrabalhoMelhorias,
					geral
					//[4]
			};
		String[][] trabMec =
			{
					{"MONTAGEM GERAL", "MONTAGEM DE SISTEMA PNEUMÁTICO", "MONTAGEM DE COMPONENTES"},
					{"USINAGEM FRESADORA", "USINAGEM TORNO", "USINAGEM FURADEIRA", "USINAGEM GERAL"},
					{"AJUSTES"},
					{"CALDEIRARIA - CHAPAS", "CALDEIRARIA - CORTE", "CALDEIRARIA - DOBRA", "CALDEIRARIA - SOLDA", "CALDEIRARIA - TRAÇAGEM"},
					{"DOCUMENTAÇÃO", "LISTA DE MATERIAIS"},
					{"ORÇAMENTO"},
					{"GESTÃO DE PROJETO"},
					retrabalhoMelhorias,
					geral
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
			att.setWo(Projetos.geralWo(att.getWoFunc(), catBotao(descr[botao][i])));
			trabComentado();
		}
		else
		{
			if (att.trabGeral())
			{
				att.setWo(Projetos.geralWo(att.getWoFunc(), catBotao(descr[botao][i])));
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
				botoes[0]))
		{
		case 0:
			try
			{
				String coment = JOptionPane.showInputDialog(
						this.painel,
						"Observação a respeito do serviço",
						"Observação", JOptionPane.INFORMATION_MESSAGE).toUpperCase();
				if (!coment.isEmpty())
					att.setDescTrabalho(att.getDescTrabalho() + " | " + coment);
				
			} catch (NullPointerException ex) {}
			dispose();
			break;
		case 1:
			dispose();
			break;
		}
		DefinirTempo defTemp = new DefinirTempo(descSub);
		defTemp.setVisible(true);
	}
	
	public String catBotao(String bot)
	{
		String retorno = new String();
		if (bot.contains("DOCUMENT"))
		{
			retorno = "DOCUMENTAÇÃO";
			att.setDescTrabalho(bot);
		}
		else if (bot.contains("ORÇAMENTO"))
		{
			retorno = "ORÇAMENTO";
			att.setDescTrabalho(bot);
		}
		else if (bot.contains("ACOMPANHAMENTO"))
		{
			retorno = "GESTÃO DE PROJETO";
			att.setDescTrabalho(bot);
		}
		else if (bot.contains("FOLLOW UP"))
		{
			retorno = "GESTÃO DE PROJETO";
			att.setDescTrabalho(bot);
		}
		else
			retorno = bot;
		
		return retorno;
	}
}