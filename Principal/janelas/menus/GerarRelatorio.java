package janelas.menus;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.geom.RoundRectangle2D;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;


import janelas.Arrastar;
import mssql.Funcionarios;
import mssql.Relatorios;


public class GerarRelatorio extends JFrame
{
	//Interação

	//Constantes
	static final int PW = 720;
	static final int PH = 450;

	String[] botoes = {"Sim", "Não"};

	//Janela
	private JPanel painel;

	private JButton gerarPeriodo, gerarTotal;

	private JComboBox cc;

	ArrayList<JComboBox<Integer>> selecaoA;
	ArrayList<JComboBox<Integer>> selecaoB;

	@SuppressWarnings("static-access")
	public GerarRelatorio()
	{
		Arrastar drag = new Arrastar();
		
		painel = new JPanel();
		painel.setBorder(new EmptyBorder(5, 5, 5, 5));
		painel.setLayout(null);

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, PW, PH);
		setContentPane(painel);
		setLocationRelativeTo(null);
		setUndecorated(true);
		setShape(new RoundRectangle2D.Double(0,0,PW,PH,5,5));

		selecaoA = new ArrayList<JComboBox<Integer>>();
		selecaoB = new ArrayList<JComboBox<Integer>>();

		for (int i = 0; i < 3; i++)
		{
			selecaoA.add(new JComboBox<Integer>());
			selecaoB.add(new JComboBox<Integer>());
		}

		cc = new JComboBox<Integer>();

		JButton fechar = new JButton("X");

		gerarPeriodo = new JButton("<html><center>PER\u00CDODO SELECIONADO</center></html>");
		gerarTotal = new JButton("<html><center>TODO O PER\u00CDODO</center></html>");
		JSeparator separador2 = new JSeparator();

		JLabel instrucao = new JLabel("GERAR PLANILHA");
		JLabel instruPeriodo = new JLabel("SELECIONE O PER\u00CDODO DO RELAT\u00D3RIO");
		JSeparator separador1 = new JSeparator();
		JLabel instruDiaA = new JLabel("DIA");
		JLabel instruDiaB = new JLabel("DIA");
		JLabel instruMesB = new JLabel("M\u00CAS");
		JLabel instruMesA = new JLabel("M\u00CAS");
		JLabel instruAnoB = new JLabel("ANO");
		JLabel instruAnoA = new JLabel("ANO");

		//Adição de Componentes
		painel.add(gerarPeriodo);
		painel.add(gerarTotal);
		painel.add(instrucao);

		for (int i = 0; i < 3; i++)
		{
			painel.add(selecaoA.get(i));
			painel.add(selecaoB.get(i));
		}

		painel.add(cc);

		painel.add(instruPeriodo);
		painel.add(separador1);
		painel.add(instruDiaA);
		painel.add(instruDiaB);
		painel.add(instruMesB);
		painel.add(instruMesA);
		painel.add(instruAnoB);
		painel.add(instruAnoA);
		painel.add(separador2);

		painel.add(fechar);

		//Título
		instrucao.setForeground(Color.DARK_GRAY);
		instrucao.setHorizontalAlignment(SwingConstants.CENTER);
		instrucao.setFont(new Font("Tahoma", Font.PLAIN, 22));
		instrucao.setBounds(241, 10, 245, 27);


		//Botão OK
		gerarPeriodo.setBounds(367, 377, 125, 50);
		gerarPeriodo.setForeground(Color.DARK_GRAY);
		gerarPeriodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String periodoA = new String();
				String periodoB = new String();

				periodoA = ((String) selecaoA.get(2).getSelectedItem().toString() + "-" + selecaoA.get(1).getSelectedItem().toString() + "-" + selecaoA.get(0).getSelectedItem().toString());
				periodoB = ((String) selecaoB.get(2).getSelectedItem().toString() + "-" + selecaoB.get(1).getSelectedItem().toString() + "-" + selecaoB.get(0).getSelectedItem().toString());

				Relatorios.carregarPeriodo(periodoA, periodoB, (int) cc.getSelectedItem());
			}
		});

		gerarTotal.setForeground(Color.DARK_GRAY);
		gerarTotal.setBounds(203, 377, 100, 50);
		gerarTotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Relatorios.carregarTotal((int) cc.getSelectedItem());
			}
		});

		cc.setForeground(Color.DARK_GRAY);
		cc.setFont(new Font("Tahoma", Font.BOLD, 20));
		cc.setBounds(64, 377, 75, 50);

		ResultSet rs = Funcionarios.listarCcs();

		try {
			while(rs.next())
			{
				cc.addItem(rs.getInt(1));
			}
		} catch (SQLException ex) {}

		cc.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
			}
		});

		GregorianCalendar gc = new GregorianCalendar();

		selecaoA.get(0).setMaximumRowCount(10);
		selecaoA.get(0).setFont(new Font("Tahoma", Font.BOLD, 20));
		selecaoA.get(0).setBounds(113, 133, 75, 50);
		for (int i = 1; i <= 31; i++)
			selecaoA.get(0).addItem(i);

		selecaoA.get(1).setMaximumRowCount(6);
		selecaoA.get(1).setFont(new Font("Tahoma", Font.BOLD, 20));
		selecaoA.get(1).setBounds(301, 133, 90, 50);

		for (int i = 1; i <= 12; i++)
			selecaoA.get(1).addItem(i);

		selecaoA.get(1).addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				//-------------------------------------//
				checarData(1);
			}
		});

		selecaoA.get(2).setMaximumRowCount(5);
		selecaoA.get(2).setFont(new Font("Tahoma", Font.BOLD, 20));
		selecaoA.get(2).setBounds(504, 133, 100, 50);

		for (int i = (gc.get(gc.YEAR) - 5); i <= gc.get(gc.YEAR); i++)
			selecaoA.get(2).addItem(i);

		selecaoA.get(2).addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				//-------------------------------------//
				checarData(1);
			}
		});



		selecaoB.get(0).setMaximumRowCount(10);
		selecaoB.get(0).setFont(new Font("Tahoma", Font.BOLD, 20));
		selecaoB.get(0).setBounds(113, 255, 75, 50);

		for (int i = 1; i <= 31; i++)
			selecaoB.get(0).addItem(i);

		selecaoB.get(1).setMaximumRowCount(6);
		selecaoB.get(1).setFont(new Font("Tahoma", Font.BOLD, 20));
		selecaoB.get(1).setBounds(301, 255, 90, 50);

		for (int i = 1; i <= 12; i++)
			selecaoB.get(1).addItem(i);

		selecaoB.get(1).addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				//-------------------------------------//
				checarData(2);
			}
		});

		selecaoB.get(2).setMaximumRowCount(5);
		selecaoB.get(2).setFont(new Font("Tahoma", Font.BOLD, 20));
		selecaoB.get(2).setBounds(504, 255, 100, 50);

		for (int i = (gc.get(gc.YEAR) - 5); i <= gc.get(gc.YEAR); i++)
			selecaoB.get(2).addItem(i);

		selecaoB.get(2).addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				//-------------------------------------//
				checarData(2);
			}
		});

		instruPeriodo.setHorizontalAlignment(SwingConstants.CENTER);
		instruPeriodo.setForeground(Color.DARK_GRAY);
		instruPeriodo.setFont(new Font("Tahoma", Font.BOLD, 15));
		instruPeriodo.setBounds(214, 63, 300, 19);

		separador1.setBounds(14, 201, (PW - 20), 3);
		separador2.setBounds(10, 323, 700, 3);

		instruDiaA.setHorizontalAlignment(SwingConstants.CENTER);
		instruDiaA.setForeground(Color.DARK_GRAY);
		instruDiaA.setFont(new Font("Tahoma", Font.PLAIN, 12));
		instruDiaA.setBounds(113, 100, 75, 15);

		instruDiaB.setHorizontalAlignment(SwingConstants.CENTER);
		instruDiaB.setForeground(Color.DARK_GRAY);
		instruDiaB.setFont(new Font("Tahoma", Font.PLAIN, 12));
		instruDiaB.setBounds(113, 222, 75, 15);

		instruMesB.setHorizontalAlignment(SwingConstants.CENTER);
		instruMesB.setForeground(Color.DARK_GRAY);
		instruMesB.setFont(new Font("Tahoma", Font.PLAIN, 12));
		instruMesB.setBounds(301, 222, 90, 15);

		instruMesA.setHorizontalAlignment(SwingConstants.CENTER);
		instruMesA.setForeground(Color.DARK_GRAY);
		instruMesA.setFont(new Font("Tahoma", Font.PLAIN, 12));
		instruMesA.setBounds(301, 100, 90, 15);

		instruAnoB.setHorizontalAlignment(SwingConstants.CENTER);
		instruAnoB.setForeground(Color.DARK_GRAY);
		instruAnoB.setFont(new Font("Tahoma", Font.PLAIN, 12));
		instruAnoB.setBounds(504, 222, 100, 15);

		instruAnoA.setHorizontalAlignment(SwingConstants.CENTER);
		instruAnoA.setForeground(Color.DARK_GRAY);
		instruAnoA.setFont(new Font("Tahoma", Font.PLAIN, 12));
		instruAnoA.setBounds(504, 100, 100, 15);


		fechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		fechar.setForeground(Color.WHITE);
		fechar.setFont(new Font("SansSerif", Font.BOLD, 10));
		fechar.setBackground(new Color(178, 34, 34));
		fechar.setBounds(690, -5, 35, 35);

		JLabel instruCc = new JLabel("CC");
		instruCc.setHorizontalAlignment(SwingConstants.CENTER);
		instruCc.setForeground(Color.DARK_GRAY);
		instruCc.setFont(new Font("Tahoma", Font.PLAIN, 12));
		instruCc.setBounds(64, 344, 75, 15);
		painel.add(instruCc);

		JLabel instruPeriodoGerar = new JLabel("GERAR RELAT\u00D3RIO");
		instruPeriodoGerar.setHorizontalAlignment(SwingConstants.CENTER);
		instruPeriodoGerar.setForeground(Color.DARK_GRAY);
		instruPeriodoGerar.setFont(new Font("Tahoma", Font.BOLD, 15));
		instruPeriodoGerar.setBounds(214, 342, 300, 19);
		painel.add(instruPeriodoGerar);

		JButton gerarUltSemana = new JButton("<html><center>\u00DALTIMA SEMANA</center></html>");
		gerarUltSemana.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Relatorios.carregarPeriodo(LocalDate.now().with(DayOfWeek.MONDAY).toString(), LocalDate.now().with(DayOfWeek.FRIDAY).toString(), (int) cc.getSelectedItem());
			}
		});
		gerarUltSemana.setForeground(Color.DARK_GRAY);
		gerarUltSemana.setBounds(556, 377, 100, 50);
		painel.add(gerarUltSemana);

		//Arrastar janela
		
		
		
		this.addMouseListener(drag);
		this.addMouseMotionListener(drag);
	}

	public void checarData(int index)
	{
		GregorianCalendar gc = new GregorianCalendar();

		switch (index)
		{	
		case 1:
			int diaSelecionadoA = (int) selecaoA.get(0).getSelectedItem();

			switch ((int) selecaoA.get(1).getSelectedItem())
			{
			case 1: //Janeiro
				selecaoA.get(0).removeAllItems();

				for (int i = 1; i <= 31; i++)
					selecaoA.get(0).addItem(i);

				break;
			case 2: //Fevereiro
				if (!gc.isLeapYear((int) selecaoA.get(2).getSelectedItem()))
				{
					selecaoA.get(0).removeAllItems();

					for (int i = 1; i <= 28; i++)
						selecaoA.get(0).addItem(i);
				}
				else
				{
					selecaoA.get(0).removeAllItems();
					for (int i = 1; i <= 29; i++)
					{
						selecaoA.get(0).addItem(i);
					}
				}
				break;
			case 3: //Março
				selecaoA.get(0).removeAllItems();

				for (int i = 1; i <= 31; i++)
					selecaoA.get(0).addItem(i);

				break;
			case 4: //Abril
				selecaoA.get(0).removeAllItems();

				for (int i = 1; i <= 30; i++)
					selecaoA.get(0).addItem(i);

				break;
			case 5: //Maio
				selecaoA.get(0).removeAllItems();

				for (int i = 1; i <= 31; i++)
					selecaoA.get(0).addItem(i);

				break;
			case 6: //Junho
				selecaoA.get(0).removeAllItems();

				for (int i = 1; i <= 30; i++)
					selecaoA.get(0).addItem(i);

				break;
			case 7: //Julho
				selecaoA.get(0).removeAllItems();

				for (int i = 1; i <= 31; i++)
					selecaoA.get(0).addItem(i);

				break;
			case 8: //Agosto
				selecaoA.get(0).removeAllItems();

				for (int i = 1; i <= 31; i++)
					selecaoA.get(0).addItem(i);
				break;
			case 9: //Setembro
				selecaoA.get(0).removeAllItems();

				for (int i = 1; i <= 30; i++)
					selecaoA.get(0).addItem(i);

				break;
			case 10: //Outubro
				selecaoA.get(0).removeAllItems();

				for (int i = 1; i <= 31; i++)
					selecaoA.get(0).addItem(i);

				break;
			case 11: //Novembro
				selecaoA.get(0).removeAllItems();

				for (int i = 1; i <= 30; i++)
					selecaoA.get(0).addItem(i);

				break;
			case 12: //Dezembro
				selecaoA.get(0).removeAllItems();

				for (int i = 1; i <= 31; i++)
					selecaoA.get(0).addItem(i);

				break;
			}
			selecaoA.get(0).setSelectedItem(diaSelecionadoA);
			break;
		case 2:
			int diaSelecionadoB = (int) selecaoB.get(0).getSelectedItem();

			switch ((int) selecaoB.get(1).getSelectedItem())
			{
			case 1: //Janeiro
				selecaoB.get(0).removeAllItems();

				for (int i = 1; i <= 31; i++)
					selecaoB.get(0).addItem(i);

				break;
			case 2: //Fevereiro
				if (!gc.isLeapYear((int) selecaoB.get(2).getSelectedItem()))
				{
					selecaoB.get(0).removeAllItems();

					for (int i = 1; i <= 28; i++)
						selecaoB.get(0).addItem(i);
				}
				else
				{
					selecaoB.get(0).removeAllItems();
					for (int i = 1; i <= 29; i++)
						selecaoB.get(0).addItem(i);
				}
				break;
			case 3: //Março
				selecaoB.get(0).removeAllItems();

				for (int i = 1; i <= 31; i++)
					selecaoB.get(0).addItem(i);

				break;
			case 4: //Abril
				selecaoB.get(0).removeAllItems();

				for (int i = 1; i <= 30; i++)
					selecaoB.get(0).addItem(i);

				break;
			case 5: //Maio
				selecaoB.get(0).removeAllItems();

				for (int i = 1; i <= 31; i++)
					selecaoB.get(0).addItem(i);

				break;
			case 6: //Junho
				selecaoB.get(0).removeAllItems();

				for (int i = 1; i <= 30; i++)
					selecaoB.get(0).addItem(i);

				break;
			case 7: //Julho
				selecaoB.get(0).removeAllItems();

				for (int i = 1; i <= 31; i++)
					selecaoB.get(0).addItem(i);

				break;
			case 8: //Agosto
				selecaoB.get(0).removeAllItems();

				for (int i = 1; i <= 31; i++)
					selecaoB.get(0).addItem(i);
				break;
			case 9: //Setembro
				selecaoB.get(0).removeAllItems();

				for (int i = 1; i <= 30; i++)
					selecaoB.get(0).addItem(i);

				break;
			case 10: //Outubro
				selecaoB.get(0).removeAllItems();

				for (int i = 1; i <= 31; i++)
					selecaoB.get(0).addItem(i);

				break;
			case 11: //Novembro
				selecaoB.get(0).removeAllItems();

				for (int i = 1; i <= 30; i++)
					selecaoB.get(0).addItem(i);

				break;
			case 12: //Dezembro
				selecaoB.get(0).removeAllItems();

				for (int i = 1; i <= 31; i++)
					selecaoB.get(0).addItem(i);
				break;
			}
			selecaoB.get(0).setSelectedItem(diaSelecionadoB);
			break;
		}
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
