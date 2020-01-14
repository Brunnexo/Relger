package janelas.selecao;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.geom.RoundRectangle2D;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import backend.Atributos;

import janelas.DefinirTempo;
import janelas.interacao.Resumo;
import mssql.Projetos;


public class Projeto extends JFrame
{
	//Interação
	public Resumo res;
	public Atributos att;

	//Constantes
	static final int PW = 720;
	static final int PH = 480;

	//Janela
	private JPanel painel;
	private JButton ok;
	public boolean woDisp = false;

	String wo;
	String descricao;
	Projeto selProj = this;

	public Projeto(Montadora pai, ResultSet rs)
	{
		//Janela
		pai.setVisible(false);
		att = pai.att;
		res = pai.res;

		painel = new JPanel();
		painel.setBorder(new EmptyBorder(5, 5, 5, 5));
		painel.setLayout(null);

		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, PW, PH);
		setContentPane(painel);
		setLocationRelativeTo(null);
		setUndecorated(true);
		setShape(new RoundRectangle2D.Double(0,0,PW,PH,5,5));

		//Componentes
		JButton voltar = new JButton("<");
		JLabel instrucao = new JLabel("PROJETO");
		JLabel instruProj = new JLabel("<html><center>DESCRI\u00C7\u00C3O DO PROJETO</center></html>");
		JLabel instruWo = new JLabel("<html><center>W.O. PARA A FUN\u00C7\u00C3O</center></html>");
		JLabel descrProj = new JLabel();
		JLabel descrWo = new JLabel();
		JComboBox projSel = new JComboBox();
		projSel.setForeground(Color.DARK_GRAY);
		ok = new JButton("OK");

		//Adição de Componentes
		painel.add(projSel);
		painel.add(ok);
		painel.add(descrWo);
		painel.add(descrProj);
		painel.add(instruWo);
		painel.add(instruProj);
		painel.add(instrucao);
		painel.add(voltar);

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

		//Título
		instrucao.setForeground(Color.DARK_GRAY);
		instrucao.setHorizontalAlignment(SwingConstants.CENTER);
		instrucao.setFont(new Font("Tahoma", Font.PLAIN, 22));
		instrucao.setBounds(237, 10, 245, 27);

		//Instrução de Projeto
		instruProj.setVisible(false);
		instruProj.setForeground(Color.DARK_GRAY);
		instruProj.setHorizontalAlignment(SwingConstants.CENTER);
		instruProj.setFont(new Font("Tahoma", Font.PLAIN, 16));
		instruProj.setBounds(66, 170, 203, 40);

		//Instrução de WO
		instruWo.setVisible(false);
		instruWo.setHorizontalAlignment(SwingConstants.CENTER);
		instruWo.setForeground(Color.DARK_GRAY);
		instruWo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		instruWo.setBounds(80, 275, 174, 40);

		//Descrição de Projeto
		descrProj.setForeground(Color.DARK_GRAY);
		descrProj.setFont(new Font("Tahoma", Font.PLAIN, 16));
		descrProj.setHorizontalAlignment(SwingConstants.CENTER);
		descrProj.setBounds(334, 140, 300, 100);

		//Descrição de WO
		descrWo.setHorizontalAlignment(SwingConstants.CENTER);
		descrWo.setForeground(Color.DARK_GRAY);
		descrWo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		descrWo.setBounds(334, 245, 300, 100);

		//Lista de Seleção de Projetos
		projSel.setFont(new Font("Tahoma", Font.BOLD, 20));
		projSel.setBounds(43, 85, 634, 50);

		//-------------------------------------------------- ADICIONAR RESULTADOS -------------------------------------------------- 
		projSel.addItem("");
		try {
			rs.beforeFirst();
			while(rs.next())
			{
				String os = new String();

				if (rs.getString("OS").length() < 2)
					os = "SEM O.S.";
				else
					os = rs.getString("OS");

				projSel.addItem(os + " - " + rs.getString("DESCRICAO"));
			}
		} catch (SQLException ex) {}
		
		//------------------------------------------------- SELECIONAR COMPONENTES ------------------------------------------------- 
		projSel.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				try {
					projSel.removeItem("");
					rs.absolute(projSel.getSelectedIndex() + 1);

					instruProj.setVisible(true);
					instruWo.setVisible(true);
					
					descrProj.setText("<html><center>" +  rs.getString("DESCRICAO") + "</html></center>");
					descrWo.setText(rs.getString(att.getWoFunc()));

					Integer.parseInt(rs.getString(att.getWoFunc()));
					ok.setEnabled(true);
				} catch (SQLException ex) {}
				catch (NumberFormatException ex) {ok.setEnabled(false);}
			}
		});

		//Botão OK
		ok.setEnabled(false);
		ok.setBounds(285, 425, 150, 50);
		
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					att.setWo(rs.getString(att.getWoFunc()));

					DefinirTempo dt = new DefinirTempo(selProj);
					dt.setVisible(true);
				} catch (SQLException ex) {}
			}
		});
	}

	public Projeto(Montadora pai)
	{
		//Janela
		pai.setVisible(false);
		att = pai.att;
		res = pai.res;

		ResultSet rs = Projetos.pesquisarMontadora(att.getNomeMontadora());

		painel = new JPanel();
		painel.setBorder(new EmptyBorder(5, 5, 5, 5));
		painel.setLayout(null);

		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, PW, PH);
		setContentPane(painel);
		setLocationRelativeTo(null);
		setUndecorated(true);
		setShape(new RoundRectangle2D.Double(0,0,PW,PH,5,5));

		//Componentes
		JButton voltar = new JButton("<");
		JLabel instrucao = new JLabel("PROJETO");
		JLabel instruProj = new JLabel("<html><center>DESCRI\u00C7\u00C3O DO PROJETO</center></html>");
		JLabel instruWo = new JLabel("<html><center>W.O. PARA A FUN\u00C7\u00C3O</center></html>");
		JLabel descrProj = new JLabel();
		JLabel descrWo = new JLabel();
		JComboBox projSel = new JComboBox();
		projSel.setForeground(Color.DARK_GRAY);
		ok = new JButton("OK");

		//Adição de Componentes
		painel.add(projSel);
		painel.add(ok);
		painel.add(descrWo);
		painel.add(descrProj);
		painel.add(instruWo);
		painel.add(instruProj);
		painel.add(instrucao);
		painel.add(voltar);

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

		//Título
		instrucao.setForeground(Color.DARK_GRAY);
		instrucao.setHorizontalAlignment(SwingConstants.CENTER);
		instrucao.setFont(new Font("Tahoma", Font.PLAIN, 22));
		instrucao.setBounds(237, 10, 245, 27);

		//Instrução de Projeto
		instruProj.setVisible(false);
		instruProj.setForeground(Color.DARK_GRAY);
		instruProj.setHorizontalAlignment(SwingConstants.CENTER);
		instruProj.setFont(new Font("Tahoma", Font.PLAIN, 16));
		instruProj.setBounds(66, 170, 203, 40);

		//Instrução de WO
		instruWo.setVisible(false);
		instruWo.setHorizontalAlignment(SwingConstants.CENTER);
		instruWo.setForeground(Color.DARK_GRAY);
		instruWo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		instruWo.setBounds(80, 275, 174, 40);

		//Descrição de Projeto
		descrProj.setForeground(Color.DARK_GRAY);
		descrProj.setFont(new Font("Tahoma", Font.PLAIN, 16));
		descrProj.setHorizontalAlignment(SwingConstants.CENTER);
		descrProj.setBounds(334, 140, 300, 100);

		//Descrição de WO
		descrWo.setHorizontalAlignment(SwingConstants.CENTER);
		descrWo.setForeground(Color.DARK_GRAY);
		descrWo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		descrWo.setBounds(334, 245, 300, 100);

		//Lista de Seleção de Projetos
		projSel.setFont(new Font("Tahoma", Font.BOLD, 20));
		projSel.setBounds(43, 85, 634, 50);
		projSel.addItem("");
		
		//-------------------------------------------------- ADICIONAR RESULTADOS -------------------------------------------------- 
		try {
			while(rs.next())
			{
				String os = new String();

				if (rs.getString("OS").length() < 2)
					os = "SEM O.S.";
				else
					os = rs.getString("OS");

				projSel.addItem(os + " - " + rs.getString("DESCRICAO"));
			}
		} catch (SQLException ex) {}

		//------------------------------------------------- SELECIONAR COMPONENTES ------------------------------------------------- 
		projSel.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				try {
					projSel.removeItem("");
					rs.absolute(projSel.getSelectedIndex() + 1);

					instruProj.setVisible(true);
					instruWo.setVisible(true);

					descrProj.setText("<html><center>" +  rs.getString("DESCRICAO") + "</html></center>");
					descrWo.setText(rs.getString(att.getWoFunc()));

					if(!descrWo.getText().contains("-"))
						ok.setEnabled(true);
					else if (descrWo.getText().length() < 2)
						ok.setEnabled(false);
					else
						ok.setEnabled(false);
				} catch (SQLException ex) {}
			}
		});
		
		//Botão OK
		ok.setEnabled(false);
		ok.setBounds(285, 425, 150, 50);
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					att.setWo(rs.getString(att.getWoFunc()));

					DefinirTempo dt = new DefinirTempo(selProj);
					dt.setVisible(true);
				} catch (SQLException ex) {}
			}
		});
		
		
		
	}

	public void setGreen(JLabel instrucao)
	{
		instrucao.setForeground(new Color(0, 128, 0));
		instrucao.setFont(new Font("Tahoma", Font.BOLD, 12));
	}

	public void setRed(JLabel instrucao)
	{
		instrucao.setForeground(Color.RED);
		instrucao.setFont(new Font("Tahoma", Font.BOLD, 12));
	}

	public void setNeutral(JLabel instrucao)
	{
		instrucao.setFont(new Font("Tahoma", Font.PLAIN, 12));
		instrucao.setForeground(Color.DARK_GRAY);
	}
}
