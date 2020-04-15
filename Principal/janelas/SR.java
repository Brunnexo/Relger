package janelas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.geom.RoundRectangle2D;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import backend.Atributos;
import mssql.SRs;


public class SR extends JFrame
{
	//Constantes
	static final int PW = 720;
	static final int PH = 480;

	int woSelecionada = 0;

	//Janela
	private JPanel painel;
	String[] botoes = {"Sim", "Não"};

	boolean atualizando = false;

	public Atributos att;
	public Resumo res;

	SR selSr = this;

	public ResultSet listaSRs = SRs.retornar();
	private JScrollPane rolagem;
	private JTextArea descrSr;
	private JComboBox<String> woSel;
	private JLabel instruSr;
	private JLabel instruWo;
	private JLabel descrWo;

	public SR(SelecionarAtividade pai)
	{
		pai.setVisible(false);
		att = pai.att;
		res = pai.res;

		//Listas

		//Propriedades do Painel
		painel = new JPanel();
		painel.setBorder(new EmptyBorder(5, 5, 5, 5));
		painel.setLayout(null);
		setContentPane(painel);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, PW, PH);
		setLocationRelativeTo(null);
		setUndecorated(true);
		setShape(new RoundRectangle2D.Double(0,0,PW,PH,5,5));

		JButton pesquisar = new JButton();
		JButton voltar = new JButton("<");
		JButton ok = new JButton("OK");

		JLabel instrucao = new JLabel("SERVICE REQUESTS");
		instruSr = new JLabel("<html><center>DESCRI\u00C7\u00C3O DA SR</center></html>");
		instruWo = new JLabel("<html><center>W.O. PARA A SR</center></html>");
		descrWo = new JLabel();
		descrSr = new JTextArea();

		woSel = new JComboBox<String>();

		rolagem = new JScrollPane();

		//Adição do Painel
		painel.add(pesquisar);
		painel.add(voltar);
		painel.add(instrucao);
		painel.add(instruSr);
		painel.add(instruWo);
		painel.add(descrWo);
		painel.add(woSel);
		painel.add(ok);
		painel.add(rolagem);

		//BOTÃO PESQUISAR
		pesquisar.setIconTextGap(0);
		pesquisar.setIcon(new ImageIcon(Montadora.class.getResource("/icones/lupa.png")));
		pesquisar.setBounds(658, 59, 50, 50);
		pesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PesquisaSR pesquisa = new PesquisaSR(selSr);
				pesquisa.setVisible(true);
			}
		});

		//Botão Voltar
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

		//Instrução
		instrucao.setForeground(Color.DARK_GRAY);
		instrucao.setHorizontalAlignment(SwingConstants.CENTER);
		instrucao.setFont(new Font("Tahoma", Font.ITALIC, 22));
		instrucao.setBounds(237, 2, 245, 27);

		//Instrução Projeto
		instruSr.setVisible(false);
		instruSr.setForeground(Color.DARK_GRAY);
		instruSr.setHorizontalAlignment(SwingConstants.CENTER);
		instruSr.setFont(new Font("Tahoma", Font.BOLD, 16));
		instruSr.setBounds(258, 125, 203, 40);

		//Instrução WO
		instruWo.setVisible(false);
		instruWo.setHorizontalAlignment(SwingConstants.CENTER);
		instruWo.setForeground(Color.DARK_GRAY);
		instruWo.setFont(new Font("Tahoma", Font.BOLD, 16));
		instruWo.setBounds(258, 295, 203, 40);

		//Descrição WO
		descrWo.setHorizontalAlignment(SwingConstants.CENTER);
		descrWo.setForeground(Color.DARK_GRAY);
		descrWo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		descrWo.setBounds(210, 351, 300, 40);

		//WO Selecionado
		woSel.setForeground(Color.DARK_GRAY);
		woSel.setSize(new Dimension(0, 40));
		woSel.addItem("");
		try {
			while (listaSRs.next())
			{
				if (listaSRs.getString("SR").contains("-"))
				{
					woSel.addItem("SEM S.R." + " - " + listaSRs.getString("DESCRICAO"));
				}
				else
				{
					woSel.addItem("S.R.: " + listaSRs.getString("SR") + " - " + listaSRs.getString("DESCRICAO"));
				}
			}
		} catch (SQLException ex) {}

		woSel.setBounds(12, 59, 634, 50);
		woSel.setFont(new Font("Tahoma", Font.BOLD, 20));

		//Mostra descrição ao selecionar projeto
		woSel.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (!atualizando)
				{
					instruSr.setVisible(true);
					instruWo.setVisible(true);
					woSel.removeItem("");
					rolagem.setVisible(true);

					try {
						listaSRs.absolute((woSel.getSelectedIndex() + 1));

						descrSr.setText(listaSRs.getString("DESCRICAO"));
						descrWo.setText(listaSRs.getString("WO"));
					} catch (StringIndexOutOfBoundsException | SQLException ex) {
						ex.printStackTrace();
					}

					try {
						woSelecionada = Integer.parseInt(descrWo.getText());
						ok.setEnabled(true);
					} catch (NumberFormatException ex)
					{
						ok.setEnabled(false);
					}
				}
			}
		});

		//Botão OK
		ok.setEnabled(false);
		ok.setBounds(285, 415, 150, 50);

		//Componentes
		descrSr.setEditable(false);
		descrSr.setWrapStyleWord(true);
		descrSr.setBorder(null);
		descrSr.setLineWrap(true);

		//Rolagem
		rolagem.setBorder(new LineBorder(Color.LIGHT_GRAY, 2, true));
		rolagem.setBounds(43, 181, 634, 98);
		rolagem.setVisible(false);
		rolagem.setViewportView(descrSr);


		//Descrição SR
		descrSr.setForeground(Color.DARK_GRAY);
		descrSr.setFont(new Font("Tahoma", Font.PLAIN, 16));


		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch (JOptionPane.showOptionDialog(painel, "Deseja continuar com a WO " + woSelecionada + "?", "Confirmação", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, botoes, botoes[1]))
				{
				case 0:
					try {
						att.setWo(listaSRs.getString("WO"));
						att.setDescTrabalho(listaSRs.getString("DESCRICAO"));
						DefinirTempo defTemp = new DefinirTempo(selSr);
						defTemp.setVisible(true);
					} catch (SQLException ex)
					{
						JOptionPane.showMessageDialog(painel, "Erro no SQL", "Erro", JOptionPane.ERROR_MESSAGE);
					}
					break;
				case 1:
					JOptionPane.showMessageDialog(painel, "Selecione alguma SR ou toque em voltar", "Operação cancelada", JOptionPane.PLAIN_MESSAGE);
					break;
				}
			}
		});
		
		
		
	}

	public void atualizarLista()
	{
		atualizando = true;
		try {
			listaSRs.beforeFirst();
			this.woSel.removeAllItems();
			
			this.woSel.addItem("");
			while (listaSRs.next())
			{
				if (listaSRs.getString("SR").contains("-"))
					this.woSel.addItem("SEM S.R." + " - " + listaSRs.getString("DESCRICAO"));
				else
					this.woSel.addItem("S.R.: " + listaSRs.getString("SR") + " - " + listaSRs.getString("DESCRICAO"));
			}
		} catch (SQLException ex) {}
		
		this.instruSr.setVisible(false);
		this.instruWo.setVisible(false);
		
		this.descrWo.setText("");
		this.rolagem.setVisible(false);
		
		atualizando = false;
	}

	public void trabComentado(Atributos att, Resumo res)
	{
		switch (JOptionPane.showOptionDialog(this.painel,
				"(Opcional) Deseja adicionar um comentário?",
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
				att.setDescTrabalho(att.getDescTrabalho() + " | " + JOptionPane.showInputDialog(this.painel, "Comentário a respeito do serviço", "Comentário", JOptionPane.QUESTION_MESSAGE).toUpperCase());
			} catch (NullPointerException ex) {}
			break;
		case 1:
			break;
		}
		DefinirTempo dTempo = new DefinirTempo(selSr);
		dTempo.mostrar();
	}
}
