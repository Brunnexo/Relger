package janelas;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;


import janelas.Arrastar;
import janelas.principais.Inicio;
import mssql.Funcionarios;

public class GerenciarFuncionarios extends JFrame
{
	//Componentes da Janela
	final static int PW = 530, PH = 380;

	private JPanel painel;

	private JTextField inCracha;
	private JTextField inNome;
	private JTextField inCc;

	private final ButtonGroup tipo = new ButtonGroup();

	boolean editar = false;
	//Estruturais
	String[] botoes = {"Sim", "Não"};

	ResultSet listaFuncionarios = Funcionarios.retornar();

	private JCheckBox chkEng;
	private JCheckBox chkProg;
	private JCheckBox chkEle;
	private JCheckBox chkMec;
	private JCheckBox chkProj;
	private JCheckBox chkAdm;
	private JRadioButton chsMensalista;
	private JRadioButton chsHorista;

	Inicio pai;
	GerenciarFuncionarios gerFun = this;

	HoraExtra he;

	private JButton confirmar;
	private JButton apagar;
	private JButton novo;
	private JToggleButton hExtra;

	public GerenciarFuncionarios(Inicio pai)
	{
		this.pai = pai;
		pai.setVisible(false);

		try {
			he = new HoraExtra(gerFun);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		//Componentes
		painel = new JPanel();
		JLabel instruTituloReg = new JLabel("REGISTRAR FUNCION\u00C1RIO");
		JLabel instruNome = new JLabel("NOME");
		JLabel instruCracha = new JLabel("CRACH\u00C1");
		chkEle = new JCheckBox("ELETRICISTA");
		chkMec = new JCheckBox("MEC\u00C2NICO");
		chkProj = new JCheckBox("PROJETISTA");
		chkProg = new JCheckBox("PROGRAMADOR");
		chkEng = new JCheckBox("ENGENHEIRO");
		chkAdm = new JCheckBox("ADMINISTRATIVO");
		chsHorista = new JRadioButton("HORISTA");
		chsMensalista = new JRadioButton("MENSALISTA");
		JButton voltar = new JButton("<");
		JLabel instruTituloEditar = new JLabel("FUNCIONÁRIOS REGISTRADOS");
		JSeparator separator = new JSeparator();
		JLabel instruSel = new JLabel("SELECIONE O FUNCION\u00C1RIO");
		confirmar = new JButton("\u2713");
		JComboBox funcSel = new JComboBox();
		apagar = new JButton("\u232B");
		novo = new JButton("NOVO");
		hExtra = new JToggleButton("H.E.");
		inNome = new JTextField();
		inCracha = new JTextField();
		JLabel instruFunc = new JLabel("SELECIONE AS FUN\u00C7\u00D5ES ATRIBU\u00CDDAS");

		ArrayList<JComponent> lista = new ArrayList<JComponent>();

		ArrayList<JLabel> instrucoes = new ArrayList<JLabel>();
		ArrayList<JCheckBox> checks = new ArrayList<JCheckBox>();
		ArrayList<JRadioButton> radios = new ArrayList<JRadioButton>();

		painel.add(inCracha);
		painel.add(inNome);

		instrucoes.add(instruCracha);
		instrucoes.add(instruFunc);
		instrucoes.add(instruTituloReg);
		instrucoes.add(instruTituloEditar);
		instrucoes.add(instruNome);
		instrucoes.add(instruSel);

		for (int i = 0; i < instrucoes.size(); i++)
			painel.add(instrucoes.get(i));

		checks.add(chkEle);
		checks.add(chkMec);
		checks.add(chkProj);
		checks.add(chkProg);
		checks.add(chkEng);
		checks.add(chkAdm);

		for (int i = 0; i < checks.size(); i++)
			painel.add(checks.get(i));

		radios.add(chsHorista);
		radios.add(chsMensalista);

		for (int i = 0; i < radios.size(); i++)
			painel.add(radios.get(i));

		lista.add(voltar);
		lista.add(confirmar);
		lista.add(separator);

		for (int i = 0; i < lista.size(); i++)
			painel.add(lista.get(i));

		//Janela
		painel.setBorder(new EmptyBorder(5, 5, 5, 5));
		painel.setLayout(null);

		//Propriedades da Janela
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, PW, PH);
		setContentPane(painel);
		setLocationRelativeTo(null);
		setUndecorated(true);
		setShape(new RoundRectangle2D.Double(0,0,PW,PH,5,5));

		//REGISTRAR
		//Título

		instruTituloReg.setBounds(140, 10, 250, 15);
		instruTituloReg.setFont(new Font("Tahoma", Font.BOLD, 12));
		instruTituloReg.setForeground(Color.DARK_GRAY);
		instruTituloReg.setHorizontalAlignment(SwingConstants.CENTER);

		//Nome

		instruNome.setBounds(6, 45, 36, 15);
		instruNome.setForeground(Color.DARK_GRAY);
		instruNome.setHorizontalAlignment(SwingConstants.CENTER);
		instruNome.setFont(new Font("Tahoma", Font.PLAIN, 12));

		inNome.setForeground(Color.DARK_GRAY);
		inNome.setToolTipText("D\u00EA a prefer\u00EAncia ao nome igual ao do crach\u00E1");
		inNome.setBounds(48, 38, 130, 28);
		inNome.setColumns(10);

		//Crachá
		instruCracha.setBounds(184, 44, 50, 15);
		instruCracha.setForeground(Color.DARK_GRAY);
		instruCracha.setHorizontalAlignment(SwingConstants.CENTER);
		instruCracha.setFont(new Font("Tahoma", Font.PLAIN, 12));

		inCracha.setForeground(Color.DARK_GRAY);
		inCracha.setToolTipText("N\u00FAmero do crach\u00E1");
		inCracha.setBounds(240, 37, 50, 28);
		inCracha.setFont(UIManager.getFont("TextField.font"));
		inCracha.setColumns(10);

		//Funções
		instruFunc.setFont(new Font("Tahoma", Font.PLAIN, 12));
		instruFunc.setForeground(Color.DARK_GRAY);
		instruFunc.setBounds(150, 98, 229, 16);
		instruFunc.setHorizontalAlignment(SwingConstants.CENTER);

		chkEle.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chkEle.setForeground(Color.DARK_GRAY);
		chkEle.setBounds(5, 134, 130, 18);

		chkMec.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chkMec.setForeground(Color.DARK_GRAY);
		chkMec.setBounds(200, 134, 130, 18);

		chkProj.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chkProj.setForeground(Color.DARK_GRAY);
		chkProj.setBounds(393, 134, 130, 18);

		chkProg.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chkProg.setForeground(Color.DARK_GRAY);
		chkProg.setBounds(5, 160, 130, 18);

		chkEng.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chkEng.setForeground(Color.DARK_GRAY);
		chkEng.setBounds(200, 160, 130, 18);

		chkAdm.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chkAdm.setForeground(Color.DARK_GRAY);
		chkAdm.setBounds(393, 160, 130, 18);

		chsHorista.setForeground(Color.DARK_GRAY);
		chsHorista.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tipo.add(chsHorista);
		chsHorista.setBounds(408, 32, 110, 18);

		chsMensalista.setForeground(Color.DARK_GRAY);
		chsMensalista.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tipo.add(chsMensalista);
		chsMensalista.setBounds(408, 52, 110, 18);

		//Voltar
		voltar.setForeground(Color.DARK_GRAY);
		voltar.setFont(new Font("SansSerif", Font.BOLD, 11));
		voltar.setBackground(Color.LIGHT_GRAY);
		voltar.setBounds(-5, -5, 35, 35);

		voltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pai.setVisible(true);
				pai.setEnabled(true);
				he.dispose();
				dispose();
			}
		});

		//EDITAR
		//Título
		instruTituloEditar.setHorizontalAlignment(SwingConstants.CENTER);
		instruTituloEditar.setForeground(Color.DARK_GRAY);
		instruTituloEditar.setFont(new Font("Tahoma", Font.BOLD, 12));
		instruTituloEditar.setBounds(140, 225, 250, 15);

		//Separador
		separator.setBounds(5, 215, (PW - 10), 5);

		//Instrução
		instruSel.setForeground(Color.DARK_GRAY);
		instruSel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		instruSel.setBounds(186, 255, 157, 15);

		//Confirmar
		confirmar.setEnabled(false);
		confirmar.setForeground(Color.WHITE);
		confirmar.setFont(new Font("SansSerif", Font.BOLD, 11));
		confirmar.setBackground(new Color(0, 128, 0));
		confirmar.setBounds((PW - 35), (PH - 35), 40, 40);

		confirmar.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				confirmar.setEnabled(validar());
			}
		});

		confirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					registrar();
				} catch (NumberFormatException | HeadlessException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});

		//Combobox
		funcSel.setFont(new Font("Tahoma", Font.BOLD, 20));
		funcSel.addItem("");

		try {
			while(listaFuncionarios.next())
			{
				funcSel.addItem(listaFuncionarios.getString(2));
			}
		} catch (SQLException e) {}

		funcSel.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				try {
					funcSel.removeItem("");
					listaFuncionarios.absolute((funcSel.getSelectedIndex() + 1));

					editar = true;
					novo.setEnabled(editar);
					apagar.setEnabled(editar);

					hExtra.setEnabled(true);

					he.defineCracha(listaFuncionarios.getInt(3));

					inNome.setText(listaFuncionarios.getString(2));
					inCracha.setText(listaFuncionarios.getString(3));
					inCc.setText(listaFuncionarios.getString(4));
					chkEle.setSelected(listaFuncionarios.getBoolean(5));
					chkMec.setSelected(listaFuncionarios.getBoolean(6));
					chkProj.setSelected(listaFuncionarios.getBoolean(7));
					chkProg.setSelected(listaFuncionarios.getBoolean(8));
					chkEng.setSelected(listaFuncionarios.getBoolean(9));
					chkAdm.setSelected(listaFuncionarios.getBoolean(10));
					chsHorista.setSelected(listaFuncionarios.getBoolean(11));
					chsMensalista.setSelected(listaFuncionarios.getBoolean(12));

					validar();
				} catch (SQLException ex) {}
			}
		});
		funcSel.setBounds(43, 285, 350, 50);
		painel.add(funcSel);

		//Botão apagar
		apagar.setEnabled(false);
		apagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				apagar();
			}
		});
		apagar.setForeground(Color.WHITE);
		apagar.setFont(new Font("SansSerif", Font.BOLD, 15));
		apagar.setBackground(Color.RED);
		apagar.setBounds(-5, (PH - 45), 50, 50);
		painel.add(apagar);

		novo.setEnabled(false);
		novo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!funcSel.getSelectedItem().equals(""))
					atualizar();
			}
		});
		novo.setForeground(Color.DARK_GRAY);
		novo.setFont(new Font("SansSerif", Font.BOLD, 11));
		novo.setBackground(Color.LIGHT_GRAY);
		novo.setBounds(455, -5, 80, 35);
		painel.add(novo);

		JLabel instruCc = new JLabel("CC");
		instruCc.setHorizontalAlignment(SwingConstants.CENTER);
		instruCc.setForeground(Color.DARK_GRAY);
		instruCc.setFont(new Font("Tahoma", Font.PLAIN, 12));
		instruCc.setBounds(296, 44, 50, 15);
		painel.add(instruCc);

		inCc = new JTextField();
		inCc.setForeground(Color.DARK_GRAY);
		inCc.setFont(UIManager.getFont("TextField.font"));
		inCc.setToolTipText("N\u00FAmero do crach\u00E1");
		inCc.setColumns(10);
		inCc.setBounds(352, 37, 50, 28);
		painel.add(inCc);

		hExtra.setEnabled(false);
		hExtra.setFont(new Font("Tahoma", Font.BOLD, 10));
		hExtra.setForeground(Color.DARK_GRAY);
		hExtra.setBounds(436, 285, 50, 50);
		hExtra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (hExtra.isSelected())
					he.setVisible(hExtra.isSelected());
				else
					he.setVisible(hExtra.isSelected());
			}
		});

		painel.add(hExtra);
		
		
		
		
		Arrastar drag = new Arrastar();
		this.addMouseListener(drag);
		this.addMouseMotionListener(drag);
	}

	public void registrar() throws NumberFormatException, HeadlessException, SQLException
	{
		if (!editar)
		{
			switch (JOptionPane.showOptionDialog(this.painel, "Deseja registrar " + this.inNome.getText() + " no banco de dados?","Confirmação", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,	null, botoes, botoes[1]))
			{
			case 0:
				switch(Funcionarios.inserir(this.inNome.getText(), Integer.parseInt(this.inCracha.getText()), Integer.parseInt(this.inCc.getText()), this.chkEle.isSelected(), this.chkMec.isSelected(), this.chkProj.isSelected(), this.chkProg.isSelected(), this.chkEng.isSelected(), this.chkAdm.isSelected(), this.chsHorista.isSelected(), this.chsMensalista.isSelected()))
				{
				case 1:
					JOptionPane.showMessageDialog(this.painel, "Registro feito com sucesso", "Operação concluída", JOptionPane.INFORMATION_MESSAGE);
					atualizar();
					break;
				case 0:
					JOptionPane.showMessageDialog(this.painel, "Erro no SQL", "Erro", JOptionPane.ERROR_MESSAGE);
					break;
				}
				break;
			case 1:
				JOptionPane.showMessageDialog(this.painel, "Registro não efetuado", "Operação não concluída", JOptionPane.INFORMATION_MESSAGE);
				break;
			}
		}
		else
		{
			switch (JOptionPane.showOptionDialog(this.painel, "Deseja alterar os dados de " + this.inNome.getText() + "?","Confirmação", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,	null, botoes, botoes[1]))
			{
			case 0:
				switch(Funcionarios.alterar(this.inNome.getText(), Integer.parseInt(this.inCracha.getText()), Integer.parseInt(this.inCc.getText()), this.chkEle.isSelected(), this.chkMec.isSelected(), this.chkProj.isSelected(), this.chkProg.isSelected(), this.chkEng.isSelected(), this.chkAdm.isSelected(), this.chsHorista.isSelected(), this.chsMensalista.isSelected(), listaFuncionarios.getInt(1)))
				{
				case 1:
					JOptionPane.showMessageDialog(this.painel, "Alteração feita com sucesso", "Operação concluída", JOptionPane.INFORMATION_MESSAGE);
					atualizar();
					break;
				case 0:
					JOptionPane.showMessageDialog(this.painel, "Erro no SQL", "Erro", JOptionPane.ERROR_MESSAGE);
					break;
				}
				break;
			case 1:
				JOptionPane.showMessageDialog(this.painel, "Alteração não efetuada", "Operação não concluída", JOptionPane.INFORMATION_MESSAGE);
				break;
			}
		}
	}

	public boolean validar()
	{
		boolean cc, cracha;
		inNome.setText(inNome.getText().replaceAll("[0-9]", ""));

		try
		{
			Integer.parseInt(inCc.getText());
			cc = true;
		} catch (NumberFormatException ex)
		{
			cc = false;
		}

		try
		{
			Integer.parseInt(inCracha.getText());
			cracha = true;
		} catch (NumberFormatException ex)
		{
			cracha = false;
		}

		if (cc && cracha && !inNome.getText().isEmpty() && (chkEle.isSelected() || chkMec.isSelected() || chkAdm.isSelected() || chkEng.isSelected() || chkProg.isSelected() || chkProj.isSelected()) && (chsHorista.isSelected() || chsMensalista.isSelected()))
			return true;
		else
			return false;
	}

	public void apagar()
	{
		switch (JOptionPane.showOptionDialog(painel,
				"Deseja realmente apagar este funcionário do banco de dados?",
				"Confirmação",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,
				botoes,
				botoes[1]))
		{
		case 0:
			try {
				switch (Funcionarios.apagar(listaFuncionarios.getInt(1)))
				{
				case 1:
					JOptionPane.showMessageDialog(painel, "Funcionário deletado!", "Operação concluída", JOptionPane.INFORMATION_MESSAGE);
					atualizar();
					break;
				case 2:
					JOptionPane.showMessageDialog(painel, "Erro no SQLite", "Erro", JOptionPane.ERROR_MESSAGE);
					break;
				}
			} catch (HeadlessException | SQLException e1) {
				e1.printStackTrace();
			}
			break;
		case 1:
			JOptionPane.showMessageDialog(this.painel, "Exclusão não efetuada", "Operação não concluída", JOptionPane.INFORMATION_MESSAGE);
			break;
		}
	}

	public void atualizar()
	{
		GerenciarFuncionarios novo = new GerenciarFuncionarios(pai);
		novo.setVisible(true);

		GerenciarFuncionarios antigo = this;
		EventQueue.invokeLater(new Runnable() {
			public void run()
			{
				antigo.dispose();
			}
		});
	}
}
