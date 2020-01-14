package janelas.menus;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.geom.RoundRectangle2D;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import janelas.Arrastar;
import mssql.Extra;

public class HoraExtra extends JFrame {

	private JPanel painel;
	private JTable tabela;

	public DefaultTableModel modeloTabela;

	int cracha = 0;
	int linha = 0;
	int x = 0, y = 0;

	String[] botoes = {"Sim", "Não"};

	HoraExtra hextra = this;
	GerenciarFuncionarios pai;

	ResultSet rsHExtra;

	static final int PW = 530, PH = 340;

	private JButton realocar;

	private JTextField inAtividade;

	private JComboBox<Integer> diaSel;
	private JComboBox<Integer> mesSel;
	private JComboBox<Integer> anoSel;

	public HoraExtra(GerenciarFuncionarios pai) throws SQLException {
		this.pai = pai;


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
		JLabel instru = new JLabel("HORA EXTRA");
		JToggleButton editar = new JToggleButton();
		JScrollPane rolagem = new JScrollPane();
		realocar = new JButton("+");
		JLabel instruHistorico = new JLabel("HIST\u00D3RICO");
		JButton apagar = new JButton("\u232B");

		diaSel = new JComboBox<Integer>();
		mesSel = new JComboBox<Integer>();
		anoSel = new JComboBox<Integer>();

		JLabel instruDia = new JLabel("DIA");
		JLabel instruMes = new JLabel("M\u00CAS");
		JLabel instruAno = new JLabel("ANO");
		JLabel instruSelecionarDia = new JLabel("SELECIONE A DATA DA HORA EXTRA");
		JLabel instruAtividade = new JLabel("ENTRE COM A ATIVIDADE A SER REALIZADA");
		inAtividade = new JTextField();
		JSeparator separator = new JSeparator();
		JButton confirmar = new JButton("\u2713");

		//Tabela
		tabela = new JTable();
		rolagem.setViewportView(tabela);

		//Adição de Componentes
		painel.add(instru);
		painel.add(editar);
		painel.add(rolagem);
		painel.add(realocar);
		painel.add(separator);
		painel.add(instruHistorico);
		painel.add(diaSel);
		painel.add(mesSel);
		painel.add(anoSel);
		painel.add(instruDia);
		painel.add(instruMes);
		painel.add(instruAno);
		painel.add(instruSelecionarDia);
		painel.add(instruAtividade);
		painel.add(inAtividade);
		painel.add(confirmar);
		painel.add(apagar);

		//Título
		instru.setHorizontalAlignment(SwingConstants.CENTER);
		instru.setForeground(Color.DARK_GRAY);
		instru.setFont(new Font("Tahoma", Font.PLAIN, 20));
		instru.setBounds(52, 10, 425, 25);

		realocar.setToolTipText("Move a janela para o centro inferior da tela");
		realocar.setForeground(Color.WHITE);
		realocar.setFont(new Font("SansSerif", Font.BOLD, 12));
		realocar.setBackground(new Color(30, 144, 255));
		realocar.setBounds(-5, -5, 35, 35);
		realocar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hextra.setLocation(x, y);
			}
		});

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
				}
			}
		});

		editar.setBackground(Color.ORANGE);
		editar.setForeground(Color.DARK_GRAY);
		editar.setBounds(485, -5, 50, 50);
		editar.setFont(new Font("SansSerif", Font.PLAIN, 22));
		editar.setText("\u270E");


		//Tabela
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
		tabela.setEnabled(false);

		//Rolagem
		rolagem.setBounds(10, 223, 510, 60);

		separator.setBounds(6, 181, 510, 3);

		instruHistorico.setHorizontalAlignment(SwingConstants.CENTER);
		instruHistorico.setForeground(Color.DARK_GRAY);
		instruHistorico.setFont(new Font("Tahoma", Font.PLAIN, 15));
		instruHistorico.setBounds(10, 194, 510, 19);

		diaSel.setMaximumRowCount(10);
		diaSel.setForeground(Color.DARK_GRAY);
		diaSel.setFont(new Font("Tahoma", Font.BOLD, 15));
		diaSel.setBounds(77, 74, 75, 30);

		for (int i = 1; i <= 31; i++)
			diaSel.addItem(i);

		mesSel.setMaximumRowCount(6);
		mesSel.setForeground(Color.DARK_GRAY);
		mesSel.setFont(new Font("Tahoma", Font.BOLD, 15));
		mesSel.setBounds(232, 74, 90, 30);

		for (int i = 1; i <= 12; i++)
			mesSel.addItem(i);

		mesSel.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				checarData();
			}
		});

		anoSel.setMaximumRowCount(5);
		anoSel.setForeground(Color.DARK_GRAY);
		anoSel.setFont(new Font("Tahoma", Font.BOLD, 15));
		anoSel.setBounds(404, 74, 100, 30);

		GregorianCalendar gc = new GregorianCalendar();
		anoSel.addItem(gc.get(GregorianCalendar.YEAR));
		anoSel.addItem(gc.get(GregorianCalendar.YEAR) + 1);

		anoSel.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				checarData();
			}
		});


		instruDia.setHorizontalAlignment(SwingConstants.CENTER);
		instruDia.setForeground(Color.DARK_GRAY);
		instruDia.setFont(new Font("Tahoma", Font.PLAIN, 12));
		instruDia.setBounds(26, 80, 25, 19);

		instruMes.setHorizontalAlignment(SwingConstants.CENTER);
		instruMes.setForeground(Color.DARK_GRAY);
		instruMes.setFont(new Font("Tahoma", Font.PLAIN, 12));
		instruMes.setBounds(178, 80, 28, 19);

		instruAno.setHorizontalAlignment(SwingConstants.CENTER);
		instruAno.setForeground(Color.DARK_GRAY);
		instruAno.setFont(new Font("Tahoma", Font.PLAIN, 12));
		instruAno.setBounds(348, 80, 30, 19);

		instruSelecionarDia.setHorizontalAlignment(SwingConstants.CENTER);
		instruSelecionarDia.setForeground(Color.DARK_GRAY);
		instruSelecionarDia.setFont(new Font("Tahoma", Font.PLAIN, 12));
		instruSelecionarDia.setBounds(35, 45, 460, 19);

		instruAtividade.setHorizontalAlignment(SwingConstants.CENTER);
		instruAtividade.setForeground(Color.DARK_GRAY);
		instruAtividade.setFont(new Font("Tahoma", Font.PLAIN, 12));
		instruAtividade.setBounds(35, 114, 460, 19);

		inAtividade.setForeground(Color.DARK_GRAY);
		inAtividade.setFont(new Font("Tahoma", Font.PLAIN, 12));
		inAtividade.setBounds(10, 143, 510, 28);
		inAtividade.setColumns(10);
		
		confirmar.setForeground(Color.WHITE);
		confirmar.setFont(new Font("SansSerif", Font.BOLD, 11));
		confirmar.setBackground(new Color(0, 128, 0));
		confirmar.setBounds(495, 305, 40, 40);
		
		confirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dataDb = new String();
				
				dataDb = anoSel.getSelectedItem().toString() + "-" + mesSel.getSelectedItem().toString() + "-" + diaSel.getSelectedItem().toString();
				
				if (inAtividade.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(painel, "O campo de atividade não pode estar em branco!", "Erro", JOptionPane.WARNING_MESSAGE);
				}
				else
				{
					switch(JOptionPane.showOptionDialog(painel, "Deseja confirmar a hora extra \"" + inAtividade.getText() + "\"?", "Confirmação", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, botoes, botoes[1]))
					{
					case 0:
						switch(Extra.inserir(cracha, inAtividade.getText(), dataDb))
						{
						case 1:
							JOptionPane.showMessageDialog(painel, "Registro bem sucedido!", "Operação concluída", JOptionPane.INFORMATION_MESSAGE);
							atualizar();
							break;
						case 0:
							JOptionPane.showMessageDialog(painel, "Erro no SQL", "Erro", JOptionPane.ERROR_MESSAGE);
							break;
						}
						break;
					case 1:
						JOptionPane.showMessageDialog(painel, "Registro não efetuado", "Operação abortada", JOptionPane.INFORMATION_MESSAGE);
						break;
					}
					
					
				}
				
			}
		});

		apagar.setForeground(Color.WHITE);
		apagar.setFont(new Font("SansSerif", Font.BOLD, 15));
		apagar.setVisible(false);
		apagar.setBackground(Color.RED);
		apagar.setBounds(-5, 295, 50, 50);

		apagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch (JOptionPane.showOptionDialog(painel, "Deseja remover a linha selecionada do histórico?", "Confirmação", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, botoes, botoes[1]))
				{
				case 0:
					try {
						rsHExtra.absolute(tabela.getSelectedRow() + 1);
						switch(Extra.apagar(rsHExtra.getInt("ITEM")))
						{
						case 1:
							JOptionPane.showMessageDialog(painel, "Registro apagado com sucesso!", "Operação concluída", JOptionPane.INFORMATION_MESSAGE);
							atualizar();
							break;
						case 0:
							JOptionPane.showMessageDialog(painel, "Erro no SQL", "Operação não concluída", JOptionPane.ERROR_MESSAGE);
							break;
						}
					}catch (SQLException ex) {System.out.println("Erro SQL");}
					break;
				case 1:
					JOptionPane.showMessageDialog(painel, "Operação não realizada", "Operação abortada", JOptionPane.INFORMATION_MESSAGE);
					break;
				}
			}
		});

		confirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		//Métodos
		Arrastar drag = new Arrastar();
		this.addMouseListener(drag);
		this.addMouseMotionListener(drag);
	}

	public void atualizar()
	{
		HoraExtra antigo = this;
		
		try {
			HoraExtra novo = new HoraExtra(pai);
			novo.defineCracha(cracha);
			
			pai.he = novo;
			novo.setVisible(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				antigo.dispose();
			}
		});
	}

	public void defineCracha(int cracha) throws SQLException
	{
		this.cracha = cracha;
		rsHExtra = Extra.retornar(cracha);
		modeloTabela = new DefaultTableModel(new Object[] {"CRACHÁ", "DATA", "DESCRIÇÃO", "FINALIZADO"}, 0);

		while (rsHExtra.next())
		{
			modeloTabela.addRow(new Object[] {rsHExtra.getString("CRACHA"), rsHExtra.getString("DATA"), rsHExtra.getString("DESCRICAO"), rsHExtra.getString("FINALIZADO")});
		}

		tabela.setModel(modeloTabela);
	}

	public void checarData()
	{
		GregorianCalendar gg = new GregorianCalendar();

		int dia = (int) diaSel.getSelectedItem();
		switch ((int) mesSel.getSelectedItem())
		{
		case 1: //Janeiro
			diaSel.removeAllItems();
			for (int i = 1; i <= 31; i++)
			{
				diaSel.addItem(i);
			}
			break;
		case 2: //Fevereiro
			if (!gg.isLeapYear((int) anoSel.getSelectedItem()))
			{
				diaSel.removeAllItems();

				for (int i = 1; i <= 28; i++)
				{
					diaSel.addItem(i);
				}
				break;
			}
			else
			{
				diaSel.removeAllItems();
				for (int i = 1; i <= 29; i++)
				{
					diaSel.addItem(i);
				}
				break;
			}
		case 3: //Março
			diaSel.removeAllItems();

			for (int i = 1; i <= 31; i++)
			{
				diaSel.addItem(i);
			}
			break;
		case 4: //Abril
			diaSel.removeAllItems();

			for (int i = 1; i <= 30; i++)
			{
				diaSel.addItem(i);
			}

			break;
		case 5: //Maio
			diaSel.removeAllItems();

			for (int i = 1; i <= 31; i++)
			{
				diaSel.addItem(i);
			}

			break;
		case 6: //Junho
			diaSel.removeAllItems();

			for (int i = 1; i <= 30; i++)
			{
				diaSel.addItem(i);
			}

			break;
		case 7: //Julho
			diaSel.removeAllItems();

			for (int i = 1; i <= 31; i++)
			{
				diaSel.addItem(i);
			}

			break;
		case 8: //Agosto
			diaSel.removeAllItems();

			for (int i = 1; i <= 31; i++)
			{
				diaSel.addItem(i);
			}
			break;
		case 9: //Setembro
			diaSel.removeAllItems();

			for (int i = 1; i <= 30; i++)
			{
				diaSel.addItem(i);
			}

			break;
		case 10: //Outubro
			diaSel.removeAllItems();

			for (int i = 1; i <= 31; i++)
			{
				diaSel.addItem(i);
			}

			break;
		case 11: //Novembro
			diaSel.removeAllItems();

			for (int i = 1; i <= 30; i++)
			{
				diaSel.addItem(i);
			}

			break;
		case 12: //Dezembro
			diaSel.removeAllItems();

			for (int i = 1; i <= 31; i++)
			{
				diaSel.addItem(i);
			}

			break;
		}
		diaSel.setSelectedItem(dia);
	}
}
