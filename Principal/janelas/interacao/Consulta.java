package janelas.interacao;

import java.awt.Color;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import backend.Atributos;

import mssql.Relatorios;

public class Consulta extends JFrame {

	private JPanel painel;
	private JTable tabela;
	public int somaTempo = 0;
	public int hExtra = 0;
	public DefaultTableModel modeloTabela;

	int linha = 0;
	int x = 0, y = 0;
	int cracha;
	
	String[] botoes = {"Sim", "Não"};
	Consulta cons = this;

	ResultSet relatorio;

	static final int PW = 850, PH = 480;
	private JTextField txtTempo;
	private JScrollPane rolagem;
	private JTextField txtExtra;
	
	String dataCondicional;
	Atributos att;
	
	public Consulta(int cracha, Atributos att) {
		this.cracha = cracha;
		this.att = att;

		relatorio = Relatorios.retornar(cracha);

		//Propriedades do Painel
		painel = new JPanel();

		painel.setBorder(new LineBorder(Color.DARK_GRAY, 2, true));
		painel.setLayout(null);
		painel.setEnabled(false);

		//Propriedades da Janela
		setContentPane(painel);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(x, y, PW, PH);
		setUndecorated(true);
		setAlwaysOnTop(true);
		setLocationRelativeTo(null);

		JLabel instru = new JLabel("HISTÓRICO DE REGISTROS");
		JLabel instruDiasTrabalhados = new JLabel("<html>DIAS TRABALHADOS <font color=\"red\">*</font></html>");
		instruDiasTrabalhados.setHorizontalAlignment(SwingConstants.CENTER);
		
		rolagem = new JScrollPane();
		txtTempo = new JTextField();

		//Tabela
		modeloTabela = new DefaultTableModel(new Object[] {"DATA", "FUNÇÃO", "WO", "DESCRIÇÃO", "TEMPO", "HE"}, 0);
		tabela = new JTable(modeloTabela);
		rolagem.setViewportView(tabela);
		JLabel instruTempoExtra = new JLabel("TEMPO EXTRA (MINUTOS)");
		txtExtra = new JTextField();
		JLabel advertencia = new JLabel("A QUANTIDADE DE DIAS TRABALHADOS PODE N\u00C3O REPRESENTAR A REALIDADE");

		//Adição de Componentes
		painel.add(instru);
		painel.add(instruDiasTrabalhados);
		painel.add(rolagem);
		painel.add(txtTempo);
		painel.add(txtExtra);
		painel.add(instruTempoExtra);
		painel.add(advertencia);
		
		//Título
		instru.setHorizontalAlignment(SwingConstants.CENTER);
		instru.setForeground(Color.DARK_GRAY);
		instru.setFont(new Font("Tahoma", Font.PLAIN, 20));
		instru.setBounds(168, 5, 513, 25);

		//Instrucional Tempo Total
		instruDiasTrabalhados.setForeground(Color.DARK_GRAY);
		instruDiasTrabalhados.setFont(new Font("Tahoma", Font.BOLD, 12));
		instruDiasTrabalhados.setBounds(56, 55, 220, 15);

		//Campo de Texto Tempo Total
		txtTempo.setHorizontalAlignment(SwingConstants.CENTER);
		txtTempo.setText("0");
		txtTempo.setEditable(false);
		txtTempo.setColumns(10);
		txtTempo.setBounds(332, 50, 65, 25);

		//Tabela
		try
		{
			while (relatorio.next())
			{
				modeloTabela.addRow(new Object[] {dataPadrao(relatorio.getString("DATA")), relatorio.getString("FUNCAO").toUpperCase(), relatorio.getString("WO"), relatorio.getString("DESCRICAO").toUpperCase(), Integer.toString(relatorio.getInt("TEMPO")), boolString(relatorio.getString("HE"))});
			}
		} catch (SQLException ex) {}
		tabela.setSelectionForeground(Color.BLACK);
		tabela.setSelectionBackground(Color.YELLOW);
		tabela.setForeground(Color.DARK_GRAY);
		tabela.setFont(new Font("Tahoma", Font.BOLD, 12));
		tabela.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
		try
		{
			tabela.getColumnModel().getColumn(0).setPreferredWidth(109);
			tabela.getColumnModel().getColumn(1).setPreferredWidth(105);
			tabela.getColumnModel().getColumn(2).setPreferredWidth(93);
			tabela.getColumnModel().getColumn(3).setPreferredWidth(450);
			tabela.getColumnModel().getColumn(4).setPreferredWidth(68);
		} catch (ArrayIndexOutOfBoundsException e) {System.out.println(e.getMessage());}
		
		tabela.setDefaultEditor(Object.class, null);
		tabela.setEnabled(false);

		//Rolagem
		rolagem.setBounds(20, 90, 810, 370);

		//Métodos
		att.setTempoTrabalhado(0);
		att.setTempoExtraTrabalhado(0);

		for (int i = 0; i < tabela.getRowCount(); i++)
		{
			if (tabela.getValueAt(i, 5).toString().equals("SIM"))
				att.setTempoExtraTrabalhado(att.getTempoExtraTrabalhado() + Integer.parseInt(tabela.getValueAt(i, 4).toString()));
			else
				att.setTempoTrabalhado(att.getTempoTrabalhado() + Integer.parseInt(tabela.getValueAt(i, 4).toString()));
		}

		txtTempo.setText(Integer.toString(att.getTempoTrabalhado() / att.tempo()));
		txtExtra.setText(Integer.toString(att.getTempoExtraTrabalhado()));
		
		instruTempoExtra.setHorizontalAlignment(SwingConstants.CENTER);
		instruTempoExtra.setForeground(Color.DARK_GRAY);
		instruTempoExtra.setFont(new Font("Tahoma", Font.BOLD, 12));
		instruTempoExtra.setBounds(453, 55, 220, 15);

		txtExtra.setHorizontalAlignment(SwingConstants.CENTER);
		txtExtra.setEditable(false);
		txtExtra.setColumns(10);
		txtExtra.setBounds(729, 50, 65, 25);
		
		advertencia.setHorizontalAlignment(SwingConstants.CENTER);
		advertencia.setForeground(Color.RED);
		advertencia.setFont(new Font("Tahoma", Font.PLAIN, 12));
		advertencia.setBounds(20, 459, 810, 15);
	}
	
	public void atualizar()
	{
		relatorio = Relatorios.retornar(this.cracha);

		DefaultTableModel novoModelo = new DefaultTableModel(new Object[] {"DATA", "FUNÇÃO", "WO", "DESCRIÇÃO", "TEMPO", "HE"}, 0);
		
		try
		{
			while (relatorio.next())
			{
				novoModelo.addRow(new Object[] {dataPadrao(relatorio.getString("DATA")), relatorio.getString("FUNCAO").toUpperCase(), relatorio.getString("WO"), relatorio.getString("DESCRICAO").toUpperCase(), Integer.toString(relatorio.getInt("TEMPO")), boolString(relatorio.getString("HE"))});
			}
		} catch (SQLException ex) {}

		tabela.setModel(novoModelo);

		att.setTempoTrabalhado(0);
		att.setTempoExtraTrabalhado(0);
		
		for (int i = 0; i < tabela.getRowCount(); i++)
		{
			if (tabela.getValueAt(i, 5).toString().equals("SIM"))
				att.setTempoExtraTrabalhado(att.getTempoExtraTrabalhado() + Integer.parseInt(tabela.getValueAt(i, 4).toString()));
			else
				att.setTempoTrabalhado(att.getTempoTrabalhado() + Integer.parseInt(tabela.getValueAt(i, 4).toString()));
		}
		try
		{
			tabela.getColumnModel().getColumn(0).setPreferredWidth(109);
			tabela.getColumnModel().getColumn(1).setPreferredWidth(105);
			tabela.getColumnModel().getColumn(2).setPreferredWidth(93);
			tabela.getColumnModel().getColumn(3).setPreferredWidth(450);
			tabela.getColumnModel().getColumn(4).setPreferredWidth(68);
		} catch (ArrayIndexOutOfBoundsException e)
		{
			System.out.println(e.getMessage());
		}

		txtTempo.setText(Integer.toString(att.getTempoTrabalhado() / att.tempo()));
		txtExtra.setText(Integer.toString(att.getTempoExtraTrabalhado()));
		
		
		
	}
	
	public static String dataPadrao(String data)
	{
		String[] conv = data.split("-");
		return conv[2] + "/" + conv[1] + "/" + conv[0];
	}
	
	public static String boolString(String bool)
	{
		if (bool.equals("1"))
			return "SIM";
		else
			return "NÃO";
	}
}