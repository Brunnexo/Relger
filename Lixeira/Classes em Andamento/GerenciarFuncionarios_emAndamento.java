package janelas.menus;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import backend.Validacoes;
import dbs.janelas.Funcionarios;

public class GerenciarFuncionarios_emAndamento extends JFrame
{
	//Componentes da Janela
	private JPanel painel;

	//Família dos RadioButtons
	private final ButtonGroup tipo = new ButtonGroup();

	//Dimensões da Janela
	final static int PW = 530, PH = 380;

	//Listas
	ArrayList<Integer> lisCracha;
	ArrayList<String> lisNome;
	ArrayList<Boolean> lisEle;
	ArrayList<Boolean> lisMec;
	ArrayList<Boolean> lisProg;
	ArrayList<Boolean> lisEng;
	ArrayList<Boolean> lisProj;
	ArrayList<Boolean> lisAdm;
	ArrayList<Boolean> lisHor;
	ArrayList<Boolean> lisMen;

	@SuppressWarnings("rawtypes")
	ArrayList<ArrayList> listao = new ArrayList<ArrayList>();
	
	
	//Funcionais
	boolean editar = false;
	String nomeAntigo = new String();
	
	boolean nomeValidado, crachaValidado, ccValidado, tipoValidado, funcoesValidadas;

	//Estruturais
	String[] botoes = {"Sim", "Não"};
	
	//Caixas
	private JCheckBox chkProg;
	private JCheckBox chkEle;
	private JCheckBox chkEng;
	private JCheckBox chkMec;
	private JCheckBox chkAdm;
	private JCheckBox chkProj;
	private JButton confirmar;
	private JButton apagar;

	@SuppressWarnings("unchecked")
	public GerenciarFuncionarios_emAndamento(Inicio pai)
	{
		listao.add(new ArrayList<Integer>());
		listao.add(new ArrayList<String>());
		for (int i = 0; i < 8; i ++)
			listao.add(new ArrayList<Boolean>());
		
		listao.set(0, Funcionarios.listaCracha());
		listao.set(1, Funcionarios.listaNome());
		for (int i = 2; i < 8; i++)
			listao.set(i, Funcionarios.listaBool((i + 1)));
		
		pai.setVisible(false);

		//Listas
		lisCracha = Funcionarios.listaCracha();
		lisNome = Funcionarios.listaNome();
		lisEle = Funcionarios.listaBool(3);
		lisMec = Funcionarios.listaBool(4);
		lisProg = Funcionarios.listaBool(5);
		lisEng = Funcionarios.listaBool(6);
		lisProj = Funcionarios.listaBool(7);
		lisAdm = Funcionarios.listaBool(8);
		lisHor = Funcionarios.listaBool(9);
		lisMen = Funcionarios.listaBool(10);

		//Componentes
		painel = new JPanel();

		JTextField inCracha = new JTextField();
		JTextField inNome = new JTextField();
		JTextField inCc = new JTextField();

		JLabel instruCracha = new JLabel("CRACH\u00C1");

		chkEle = new JCheckBox("ELETRICISTA");
		chkMec = new JCheckBox("MEC\u00C2NICO");
		chkProj = new JCheckBox("PROJETISTA");
		chkProg = new JCheckBox("PROGRAMADOR");
		chkEng = new JCheckBox("ENGENHEIRO");
		chkAdm = new JCheckBox("ADMINISTRATIVO");
		
		JRadioButton chsHorista = new JRadioButton("HORISTA");
		JRadioButton chsMensalista = new JRadioButton("MENSALISTA");

		confirmar = new JButton("\u2713");
		apagar = new JButton("\u232B");
		
		JButton voltar = new JButton("<");
		JButton novo = new JButton("NOVO");

		JLabel instruTituloReg = new JLabel("REGISTRAR FUNCION\u00C1RIO");
		JLabel instruNome = new JLabel("NOME");
		JLabel instruTituloEditar = new JLabel("FUNCIONÁRIOS REGISTRADOS");
		JLabel instruFunc = new JLabel("SELECIONE AS FUN\u00C7\u00D5ES ATRIBU\u00CDDAS");
		JLabel instruCc = new JLabel("CC");
		JLabel instruSel = new JLabel("SELECIONE O FUNCION\u00C1RIO");

		JSeparator separator = new JSeparator();

		@SuppressWarnings("rawtypes")
		JComboBox funcSel = new JComboBox();

		painel.add(inNome);
		painel.add(inCracha);
		painel.add(inCc);

		painel.add(instruTituloReg);
		painel.add(instruNome);
		painel.add(instruFunc);
		painel.add(instruTituloEditar);
		painel.add(instruCc);
		painel.add(instruSel);

		painel.add(chkEle);
		painel.add(chkMec);
		painel.add(chkProj);
		painel.add(chkProg);
		painel.add(chkEng);
		painel.add(chkAdm);

		painel.add(chsHorista);
		painel.add(chsMensalista);

		painel.add(separator);

		painel.add(confirmar);
		painel.add(voltar);
		painel.add(apagar);
		painel.add(novo);


		painel.add(funcSel);


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
		painel.add(instruCracha);

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

		//Combobox
		funcSel.addItem("");
		funcSel.setFont(new Font("Tahoma", Font.BOLD, 20));
		funcSel.setBounds(65, 285, 400, 50);
		for (int i = 0; i < lisNome.size(); i++)
			funcSel.addItem(lisNome.get(i));

		//Botão apagar
		apagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
					switch (Funcionarios.deletar_Funcionario(Integer.parseInt(inCracha.getText())))
					{
					case 1:
						JOptionPane.showMessageDialog(painel, "Funcionário deletado!", "Operação concluída", JOptionPane.INFORMATION_MESSAGE);
						atualizar(pai);
						break;
					case 2:
						JOptionPane.showMessageDialog(painel, "Erro no SQLite", "Erro", JOptionPane.ERROR_MESSAGE);
						break;
					}
					break;
				case 1:
					break;
				}
			}
		});
		apagar.setEnabled(false);
		apagar.setForeground(Color.WHITE);
		apagar.setFont(new Font("SansSerif", Font.BOLD, 15));
		apagar.setBackground(Color.RED);
		apagar.setBounds(-5, (PH - 45), 50, 50);

		//Botão Novo
		novo.setEnabled(false);
		novo.setForeground(Color.DARK_GRAY);
		novo.setFont(new Font("SansSerif", Font.BOLD, 11));
		novo.setBackground(Color.LIGHT_GRAY);
		novo.setBounds(455, -5, 80, 35);

		//Label CC
		instruCc.setHorizontalAlignment(SwingConstants.CENTER);
		instruCc.setForeground(Color.DARK_GRAY);
		instruCc.setFont(new Font("Tahoma", Font.PLAIN, 12));
		instruCc.setBounds(296, 44, 50, 15);

		//Campo CC
		inCc.setForeground(Color.DARK_GRAY);
		inCc.setFont(UIManager.getFont("TextField.font"));
		inCc.setToolTipText("N\u00FAmero do crach\u00E1");
		inCc.setColumns(10);
		inCc.setBounds(352, 37, 50, 28);

		//Focus Listeners
		inNome.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				nomeValidado = Validacoes.validaCampo(instruNome, inNome);
			}
		});

		inCracha.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				crachaValidado = Validacoes.validaNumero(instruCracha, inCracha);
			}
		});

		inCc.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				ccValidado = Validacoes.validaNumero(instruCc, inCc);
			}
		});

		//Item Listeners
		funcSel.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				funcSel.removeItem("");
				editar = true;
				novo.setEnabled(true);
				inNome.setText(lisNome.get(funcSel.getSelectedIndex()));
				nomeAntigo = inNome.getText();
				inCracha.setText( Integer.toString(lisCracha.get(funcSel.getSelectedIndex())) );
				instruTituloReg.setText("EDITAR FUNCIONÁRIO");
				preencheCampos(funcSel);
				chsHorista.setSelected(lisHor.get(funcSel.getSelectedIndex()));
				chsMensalista.setSelected(lisMen.get(funcSel.getSelectedIndex()));
			}
		});

		//Action Listeners
		novo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!funcSel.getSelectedItem().equals(""))
					atualizar(pai);
			}
		});

		//State Change Listeners
		chsHorista.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (chsHorista.isSelected())
				{
					Validacoes.setGreen(chsHorista);
					tipoValidado = true;
				}
				else
				{
					Validacoes.setNeutral(chsHorista);
				}
			}
		});

		chsMensalista.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (chsMensalista.isSelected())
				{
					Validacoes.setGreen(chsMensalista);
					tipoValidado = true;
				}
				else
				{
					Validacoes.setNeutral(chsMensalista);
				}
			}
		});
		
		chkEle.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (chkEle.isSelected())
				{
					Validacoes.setGreen(chkEle);
					funcoesValidadas = true;
				}
				else
				{
					Validacoes.setNeutral(chkEle);
				}
			}
		});
		
		chkMec.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (chkMec.isSelected())
				{
					Validacoes.setGreen(chkMec);
					funcoesValidadas = true;
				}
				else
				{
					Validacoes.setNeutral(chkMec);
				}
			}
		});
		
		chkProj.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (chkProj.isSelected())
				{
					Validacoes.setGreen(chkProj);
					funcoesValidadas = true;
				}
				else
				{
					Validacoes.setNeutral(chkProj);
				}
			}
		});
		
		chkProg.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (chkProg.isSelected())
				{
					Validacoes.setGreen(chkProg);
					funcoesValidadas = true;
				}
				else
				{
					Validacoes.setNeutral(chkProg);
				}
			}
		});
		
		chkEng.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (chkEng.isSelected())
				{
					Validacoes.setGreen(chkEng);
					funcoesValidadas = true;
				}
				else
				{
					Validacoes.setNeutral(chkEng);
				}
			}
		});
		
		chkAdm.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (chkAdm.isSelected())
				{
					Validacoes.setGreen(chkAdm);
					funcoesValidadas = true;
				}
				else
				{
					Validacoes.setNeutral(chkAdm);
				}
			}
		});
	}

	
	public void checar()
	{
		if(!(chkEle.isSelected() & chkMec.isSelected() & chkProj.isSelected() & chkProg.isSelected() & chkEng.isSelected() & chkAdm.isSelected())) funcoesValidadas = false;
		
		if(nomeValidado & crachaValidado & ccValidado & tipoValidado & funcoesValidadas) confirmar.setEnabled(true);
		else confirmar.setEnabled(false);
	}
	
	public void preencheCampos(JComboBox lista)
	{
		/*
		
		chkEle.setSelected(lisEle.get(lista.getSelectedIndex()));
		Validacoes.setGreen(chkEle);
		
		chkMec.setSelected(lisMec.get(lista.getSelectedIndex()));
		Validacoes.setGreen(chkMec);
		
		chkProj.setSelected(lisProj.get(lista.getSelectedIndex()));
		Validacoes.setGreen(chkProj);
		
		chkProg.setSelected(lisProg.get(lista.getSelectedIndex()));
		Validacoes.setGreen(chkProg);
		
		chkEng.setSelected(lisEng.get(lista.getSelectedIndex()));
		Validacoes.setGreen(chkEng);
		
		chkAdm.setSelected(lisAdm.get(lista.getSelectedIndex()));
		Validacoes.setGreen(chkAdm);
		
		funcoesValidadas = true;*/
	}
	
	public void atualizar(Inicio pai)
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
