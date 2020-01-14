package janelas.interacao;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import backend.Atributos;
import janelas.Arrastar;
import mssql.Relatorios;

public class Resumo extends JFrame {

	private JPanel painel;

	private JTable tabela;

	public int somaTempo = 0;
	public int hExtra = 0;
	int x = 0, y = 0;
	int cracha = 0;
	int linha = 0;

	public DefaultTableModel modeloTabela;

	String[] botoes = {"Sim", "Não"};
	Resumo res = this;
	Atributos att;
	ResultSet relatorio;

	String dataCondicional;

	public JCheckBox chkHoraExtra;
	public JCheckBox chkBancoDeHoras;
	public JCheckBox chkNormal;

	static final int PW = 850, PH = 280;

	private JTextField txtTempo;
	private JTextField txtTempoRest;
	private JTextField txtNome;
	private JTextField txtCracha;

	private JScrollPane rolagem;

	private JButton apagar;

	private JToggleButton editar;
	private JToggleButton consultar;
	private JToggleButton hExtraMensalista;

	Consulta cons;
	private JButton minimizar;

	public Resumo(int cracha, Atributos att) {
		cons = new Consulta(cracha, att);

		this.cracha = att.getCrachaFunc();
		this.dataCondicional = att.dataCondicionalFormatada();
		this.att = att;

		relatorio = Relatorios.retornar(this.cracha, this.dataCondicional);

		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
		Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();

		//Centro inferior
		x = (((int) rect.getMaxX() / 2) - PW / 2);
		y = ((int) rect.getMaxY() - PH);

		//Propriedades do Painel
		painel = new JPanel();

		painel.setBorder(new EmptyBorder(5, 5, 5, 5));
		painel.setLayout(null);
		painel.setEnabled(false);

		//Propriedades da Janela
		setContentPane(painel);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(x, y, PW, PH);
		setUndecorated(true);
		setShape(new RoundRectangle2D.Double(0,0,PW,PH,5,5));
		setAlwaysOnTop(true);
		JLabel instru = new JLabel("RESUMO DO FUNCION\u00C1RIO - " + att.dataCondicional());
		JButton fechar = new JButton("X");
		fechar.setToolTipText("Fechar");
		fechar.setFocusPainted(false);
		editar = new JToggleButton();
		editar.setFocusPainted(false);
		JLabel instruNome = new JLabel("NOME");
		JLabel instruTempoTotal = new JLabel("TEMPO");
		JLabel instruRegistro = new JLabel("REGISTRO");
		JLabel instruTempoRestante = new JLabel("TEMPO RESTANTE");

		rolagem = new JScrollPane();
		txtNome = new JTextField();
		txtCracha = new JTextField();
		txtTempo = new JTextField();
		txtTempoRest = new JTextField();
		chkNormal = new JCheckBox("NORMAL");
		chkHoraExtra = new JCheckBox("H.E.");
		chkBancoDeHoras = new JCheckBox("B.H.");
		apagar = new JButton("\u232B");
		hExtraMensalista = new JToggleButton("H.E.");
		consultar = new JToggleButton("\u2191");
		minimizar = new JButton("_");

		//Tabela
		modeloTabela = new DefaultTableModel(new Object[] {"FUNÇÃO", "WO", "DESCRIÇÃO", "TEMPO", "HE"}, 0);
		tabela = new JTable(modeloTabela);
		rolagem.setViewportView(tabela);

		//AdiÇÃo de Componentes
		painel.add(instru);
		painel.add(fechar);
		painel.add(editar);
		painel.add(instruNome);
		painel.add(instruTempoTotal);
		painel.add(instruRegistro);
		painel.add(instruTempoRestante);
		painel.add(rolagem);
		painel.add(txtNome);
		painel.add(txtCracha);
		painel.add(txtTempo);
		painel.add(txtTempoRest);
		painel.add(chkNormal);
		painel.add(chkHoraExtra);
		painel.add(chkBancoDeHoras);
		painel.add(apagar);
		painel.add(hExtraMensalista);
		painel.add(consultar);
		painel.add(minimizar);
		//Título
		instru.setHorizontalAlignment(SwingConstants.CENTER);
		instru.setForeground(Color.DARK_GRAY);
		instru.setFont(new Font("Tahoma", Font.PLAIN, 20));
		instru.setBounds(168, 5, 513, 25);

		//Fechar
		fechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				cons.setVisible(false);
			}
		});
		fechar.setForeground(Color.WHITE);
		fechar.setFont(new Font("SansSerif", Font.BOLD, 10));
		fechar.setBackground(new Color(178, 34, 34));
		fechar.setBounds(-5, -5, 35, 35);

		//Editar
		editar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (editar.isSelected())
				{
					editar.setBackground(Color.GRAY);
					editar.setForeground(Color.LIGHT_GRAY);
					tabela.setEnabled(editar.isSelected());
					apagar.setVisible(editar.isSelected());
				}
				else
				{
					editar.setBackground(Color.ORANGE);
					editar.setForeground(Color.DARK_GRAY);
					tabela.setEnabled(editar.isSelected());
					apagar.setVisible(editar.isSelected());
					res.atualizar();
				}
			}
		});

		editar.setBackground(Color.ORANGE);
		editar.setForeground(Color.DARK_GRAY);
		editar.setBounds(805, -5, 50, 50);
		editar.setFont(new Font("SansSerif", Font.PLAIN, 22));
		editar.setText("\u270E");

		//Confirmar
		apagar.setVisible(false);
		apagar.setForeground(Color.WHITE);
		apagar.setFont(new Font("SansSerif", Font.BOLD, 12));
		apagar.setBackground(Color.RED);
		apagar.setBounds(-5, 240, 45, 45);
		apagar.setFocusPainted(false);
		apagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				linha = tabela.getSelectedRow();
				try {
					relatorio.absolute((linha + 1));
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				switch (JOptionPane.showOptionDialog(painel,
						"Deseja apagar a linha selecionada do seu relatório?",
						"Confirmação",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						null,
						botoes,
						botoes[1]))
				{
				case 0:
					try {
						switch(Relatorios.deletar(relatorio.getInt("ID"), att.getCrachaFunc(), relatorio.getString("WO")))
						{
						case 1:
							JOptionPane.showMessageDialog(painel, "Apagado com sucesso!", "Operação concluída", JOptionPane.INFORMATION_MESSAGE);
							atualizar();
							break;
						case 2:
							JOptionPane.showMessageDialog(painel, "Erro no SQLite!", "Erro", JOptionPane.ERROR_MESSAGE);
							break;
						}
					}catch (SQLException ex) {ex.printStackTrace();}
					break;
				case 1:
					JOptionPane.showMessageDialog(painel, "Operação cancelada", "Abortado", JOptionPane.INFORMATION_MESSAGE);
					break;
				}
			}
		});

		//Instrucional Nome
		instruNome.setForeground(Color.DARK_GRAY);
		instruNome.setFont(new Font("Tahoma", Font.BOLD, 12));
		instruNome.setBounds(21, 55, 36, 15);

		//Instrucional Tempo Total
		instruTempoTotal.setForeground(Color.DARK_GRAY);
		instruTempoTotal.setFont(new Font("Tahoma", Font.BOLD, 12));
		instruTempoTotal.setBounds(406, 55, 42, 15);

		//Crachá
		instruRegistro.setFont(new Font("Tahoma", Font.BOLD, 12));
		instruRegistro.setForeground(Color.DARK_GRAY);
		instruRegistro.setBounds(235, 55, 64, 15);

		//Campo de Texto Nome
		txtNome.setHorizontalAlignment(SwingConstants.CENTER);
		txtNome.setEditable(false);
		txtNome.setText(att.getNomeFunc());
		txtNome.setBounds(78, 50, 136, 25);
		txtNome.setColumns(10);

		//Campo de Texto Crachá
		txtCracha.setHorizontalAlignment(SwingConstants.CENTER);
		txtCracha.setText(Integer.toString(att.getCrachaFunc()));
		txtCracha.setEditable(false);
		txtCracha.setBounds(320, 50, 65, 25);
		txtCracha.setColumns(10);

		//Campo de Texto Tempo Total
		txtTempo.setHorizontalAlignment(SwingConstants.CENTER);
		txtTempo.setText("0");
		txtTempo.setEditable(false);
		txtTempo.setColumns(10);
		txtTempo.setBounds(469, 50, 65, 25);

		//Tempo restante
		instruTempoRestante.setHorizontalAlignment(SwingConstants.RIGHT);
		instruTempoRestante.setForeground(Color.DARK_GRAY);
		instruTempoRestante.setFont(new Font("Tahoma", Font.BOLD, 12));
		instruTempoRestante.setBounds(532, 243, 200, 15);

		//Tempo restante
		txtTempoRest.setText("0");
		txtTempoRest.setHorizontalAlignment(SwingConstants.CENTER);
		txtTempoRest.setEditable(false);
		txtTempoRest.setColumns(10);
		txtTempoRest.setBounds(750, 238, 80, 25);

		//Checkbox Atividade Normal
		chkNormal.setEnabled(false);
		chkNormal.setHorizontalAlignment(SwingConstants.CENTER);
		chkNormal.setForeground(Color.DARK_GRAY);
		chkNormal.setFont(new Font("Tahoma", Font.BOLD, 12));
		chkNormal.setBounds(555, 51, 79, 23);

		//Checkbox com Atividade de Hora Extra
		chkHoraExtra.setEnabled(false);
		chkHoraExtra.setHorizontalAlignment(SwingConstants.CENTER);
		chkHoraExtra.setForeground(Color.DARK_GRAY);
		chkHoraExtra.setFont(new Font("Tahoma", Font.BOLD, 12));
		chkHoraExtra.setBounds(655, 51, 75, 23);
		chkHoraExtra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chkHoraExtra.isSelected())
				{
					att.sethExtraMensalista(chkHoraExtra.isSelected());
					res.atualizar();
				}
				else
				{
					att.sethExtraMensalista(chkHoraExtra.isSelected());
					res.atualizar();
				}
			}
		});

		hExtraMensalista.setFocusPainted(false);
		hExtraMensalista.setVisible(att.isMensalista() && !att.fimDeSemana());
		hExtraMensalista.setForeground(Color.DARK_GRAY);
		hExtraMensalista.setFont(new Font("Tahoma", Font.BOLD, 12));
		hExtraMensalista.setBounds(690, -5, 75, 35);
		hExtraMensalista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (hExtraMensalista.isSelected())
				{
					att.sethExtraMensalista(true);
					res.atualizar();
				}
				else
				{
					att.sethExtraMensalista(false);
					res.atualizar();
				}
			}
		});

		//Checkbox de Banco de Horas
		chkBancoDeHoras.setEnabled(false);
		chkBancoDeHoras.setHorizontalAlignment(SwingConstants.CENTER);
		chkBancoDeHoras.setForeground(Color.DARK_GRAY);
		chkBancoDeHoras.setFont(new Font("Tahoma", Font.BOLD, 12));
		chkBancoDeHoras.setBounds(751, 51, 75, 23);

		//Tabela
		try
		{
			while (relatorio.next())
			{
				modeloTabela.addRow(new Object[] {relatorio.getString(4).toUpperCase(), relatorio.getString(5).toUpperCase(),  relatorio.getString(6).toUpperCase(), Integer.toString(relatorio.getInt(7)).toUpperCase(), boolString(relatorio.getString(8))});
			}
		} catch (SQLException ex) {}
		tabela.setSelectionForeground(Color.BLACK);
		tabela.setSelectionBackground(Color.YELLOW);
		tabela.setForeground(Color.DARK_GRAY);
		tabela.setFont(new Font("Tahoma", Font.BOLD, 12));
		tabela.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
		try
		{
			tabela.getColumnModel().getColumn(0).setPreferredWidth(130);
			tabela.getColumnModel().getColumn(1).setPreferredWidth(75);
			tabela.getColumnModel().getColumn(2).setPreferredWidth(485);
			tabela.getColumnModel().getColumn(3).setPreferredWidth(55);
		} catch (ArrayIndexOutOfBoundsException e)
		{
			System.out.println(e.getMessage());
		}
		tabela.setDefaultEditor(Object.class, null);
		tabela.setEnabled(false);

		//Rolagem
		rolagem.setBounds(20, 90, 810, 150);

		//Métodos
		att.setTempoTrabalhado(0);
		att.setTempoExtraTrabalhado(0);

		for (int i = 0; i < tabela.getRowCount(); i++)
		{
			if (tabela.getValueAt(i, 4).toString().contentEquals("SIM"))
				att.setTempoExtraTrabalhado(att.getTempoExtraTrabalhado() + Integer.parseInt(tabela.getValueAt(i, 3).toString()));
			else
				att.setTempoTrabalhado(att.getTempoTrabalhado() + Integer.parseInt(tabela.getValueAt(i, 3).toString()));
		}

		chkBancoDeHoras.setSelected(att.bancoHoras());
		chkHoraExtra.setSelected(att.horaExtra());
		chkNormal.setSelected(att.tempoNormal());

		txtTempo.setText(Integer.toString(att.getTempoTrabalhado() + att.getTempoExtraTrabalhado()));
		txtTempoRest.setText(Integer.toString(att.tempoRestante()));

		consultar.setToolTipText("Ver hist\u00F3rico de registros");
		consultar.setForeground(Color.YELLOW);
		consultar.setFont(new Font("SansSerif", Font.PLAIN, 22));
		consultar.setBackground(Color.DARK_GRAY);
		consultar.setBounds(760, -5, 50, 50);
		consultar.setFocusPainted(false);
		
		minimizar.setToolTipText("Minimizar");
		minimizar.setForeground(Color.DARK_GRAY);
		minimizar.setFont(new Font("SansSerif", Font.BOLD, 12));
		minimizar.setFocusPainted(false);
		minimizar.setBackground(Color.LIGHT_GRAY);
		minimizar.setBounds(25, -5, 35, 35);
		minimizar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setState(ICONIFIED);
			}
		});
		
		consultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cons.setVisible(consultar.isSelected());

				if (consultar.isSelected())
					consultar.setText("\u2193");
				else
					consultar.setText("\u2191");
			}
		});

		
		
		
		Arrastar drag = new Arrastar();
		this.addMouseListener(drag);
		this.addMouseMotionListener(drag);
	}

	public void atualizar()
	{
		cons.atualizar();
		relatorio = Relatorios.retornar(this.cracha, this.dataCondicional);

		DefaultTableModel novoModelo = new DefaultTableModel(new Object[] {"FUNÇÃO", "WO", "DESCRIÇÃO", "TEMPO", "HE"}, 0);

		try
		{
			while (relatorio.next())
			{
				novoModelo.addRow(new Object[] {relatorio.getString(4).toUpperCase(), relatorio.getString(5).toUpperCase(),  relatorio.getString(6).toUpperCase(), Integer.toString(relatorio.getInt(7)).toUpperCase(), boolString(relatorio.getString(8))});
			}
		} catch (SQLException ex) {}

		tabela.setModel(novoModelo);

		att.setTempoTrabalhado(0);
		att.setTempoExtraTrabalhado(0);
		for (int i = 0; i < tabela.getRowCount(); i++)
		{
			if (tabela.getValueAt(i, 4).toString().contentEquals("SIM"))
				att.setTempoExtraTrabalhado(att.getTempoExtraTrabalhado() + Integer.parseInt(tabela.getValueAt(i, 3).toString()));
			else
				att.setTempoTrabalhado(att.getTempoTrabalhado() + Integer.parseInt(tabela.getValueAt(i, 3).toString()));
		}
		try
		{
			tabela.getColumnModel().getColumn(0).setPreferredWidth(130);
			tabela.getColumnModel().getColumn(1).setPreferredWidth(75);
			tabela.getColumnModel().getColumn(2).setPreferredWidth(485);
			tabela.getColumnModel().getColumn(3).setPreferredWidth(55);
		} catch (ArrayIndexOutOfBoundsException e)
		{
			System.out.println(e.getMessage());
		}

		txtTempo.setText(Integer.toString(att.getTempoTrabalhado() + att.getTempoExtraTrabalhado()));
		txtTempoRest.setText(Integer.toString(att.tempoRestante()));

		chkBancoDeHoras.setSelected(att.bancoHoras());
		chkHoraExtra.setSelected(att.horaExtra());
		chkNormal.setSelected(att.tempoNormal());

		this.setVisible(true);
	}
	public static String boolString(String bool)
	{
		if (bool.equals("1"))
			return "SIM";
		else
			return "NÃO";
	}
}