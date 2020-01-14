package janelas.menus;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import dbs.janelas.Funcionarios;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class GerenciarFuncionarios_backup extends JFrame
{
	//Componentes da Janela
	private JPanel painel;
	private JTextField inCracha;
	private JTextField inNome;
	private final ButtonGroup tipo = new ButtonGroup();
	final static int PW = 530, PH = 380;

	//Listas
	ArrayList<Integer> lisCracha;
	ArrayList<String> lisNome;
	ArrayList<Integer> lisCc;
	ArrayList<Boolean> lisEle;
	ArrayList<Boolean> lisMec;
	ArrayList<Boolean> lisProg;
	ArrayList<Boolean> lisEng;
	ArrayList<Boolean> lisProj;
	ArrayList<Boolean> lisAdm;
	ArrayList<Boolean> lisHor;
	ArrayList<Boolean> lisMen;

	boolean editar = false;
	String nomeAntigo = new String();

	//Estruturais
	String[] botoes = {"Sim", "Não"};
	private JTextField inCc;

	@SuppressWarnings("unchecked")
	public GerenciarFuncionarios_backup(Inicio pai)
	{
		pai.setVisible(false);

		//Listas
		lisCracha = Funcionarios.listaNum(1);
		lisNome = Funcionarios.listaNome();
		lisCc = Funcionarios.listaNum(3);
		lisEle = Funcionarios.listaBool(4);
		lisMec = Funcionarios.listaBool(5);
		lisProg = Funcionarios.listaBool(6);
		lisEng = Funcionarios.listaBool(7);
		lisProj = Funcionarios.listaBool(8);
		lisAdm = Funcionarios.listaBool(9);
		lisHor = Funcionarios.listaBool(10);
		lisMen = Funcionarios.listaBool(11);

		//Componentes
		painel = new JPanel();
		JLabel instruTituloReg = new JLabel("REGISTRAR FUNCION\u00C1RIO");
		JLabel instruNome = new JLabel("NOME");
		JLabel instruCracha = new JLabel("CRACH\u00C1");
		JCheckBox chkEle = new JCheckBox("ELETRICISTA");
		JCheckBox chkMec = new JCheckBox("MEC\u00C2NICO");
		JCheckBox chkProj = new JCheckBox("PROJETISTA");
		JCheckBox chkProg = new JCheckBox("PROGRAMADOR");
		JCheckBox chkEng = new JCheckBox("ENGENHEIRO");
		JCheckBox chkAdm = new JCheckBox("ADMINISTRATIVO");
		JRadioButton chsHorista = new JRadioButton("HORISTA");
		JRadioButton chsMensalista = new JRadioButton("MENSALISTA");
		JButton voltar = new JButton("<");
		JLabel instruTituloEditar = new JLabel("FUNCIONÁRIOS REGISTRADOS");
		JSeparator separator = new JSeparator();
		JLabel instruSel = new JLabel("SELECIONE O FUNCION\u00C1RIO");
		JButton confirmar = new JButton("\u2713");
		@SuppressWarnings("rawtypes")
		JComboBox funcSel = new JComboBox();
		JButton apagar = new JButton("\u232B");
		JButton novo = new JButton("NOVO");

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
		painel.add(instruTituloReg);

		//Nome

		instruNome.setBounds(6, 45, 36, 15);
		instruNome.setForeground(Color.DARK_GRAY);
		instruNome.setHorizontalAlignment(SwingConstants.CENTER);
		instruNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		painel.add(instruNome);

		inNome = new JTextField();
		inNome.setForeground(Color.DARK_GRAY);
		inNome.setToolTipText("D\u00EA a prefer\u00EAncia ao nome igual ao do crach\u00E1");
		inNome.setBounds(48, 38, 130, 28);
		painel.add(inNome);
		inNome.setColumns(10);

		//Crachá

		instruCracha.setBounds(184, 44, 50, 15);
		instruCracha.setForeground(Color.DARK_GRAY);
		instruCracha.setHorizontalAlignment(SwingConstants.CENTER);
		instruCracha.setFont(new Font("Tahoma", Font.PLAIN, 12));
		painel.add(instruCracha);

		inCracha = new JTextField();
		inCracha.setForeground(Color.DARK_GRAY);
		inCracha.setToolTipText("N\u00FAmero do crach\u00E1");
		inCracha.setBounds(240, 37, 50, 28);
		inCracha.setFont(UIManager.getFont("TextField.font"));
		painel.add(inCracha);
		inCracha.setColumns(10);

		//Funções
		JLabel instruFunc = new JLabel("SELECIONE AS FUN\u00C7\u00D5ES ATRIBU\u00CDDAS");
		instruFunc.setFont(new Font("Tahoma", Font.PLAIN, 12));
		instruFunc.setForeground(Color.DARK_GRAY);
		instruFunc.setBounds(150, 98, 229, 16);
		instruFunc.setHorizontalAlignment(SwingConstants.CENTER);
		painel.add(instruFunc);


		chkEle.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chkEle.setForeground(Color.DARK_GRAY);
		chkEle.setBounds(5, 134, 130, 18);
		painel.add(chkEle);

		chkMec.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chkMec.setForeground(Color.DARK_GRAY);
		chkMec.setBounds(200, 134, 130, 18);
		painel.add(chkMec);

		chkProj.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chkProj.setForeground(Color.DARK_GRAY);
		chkProj.setBounds(393, 134, 130, 18);
		painel.add(chkProj);

		chkProg.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chkProg.setForeground(Color.DARK_GRAY);
		chkProg.setBounds(5, 160, 130, 18);
		painel.add(chkProg);

		chkEng.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chkEng.setForeground(Color.DARK_GRAY);
		chkEng.setBounds(200, 160, 130, 18);
		painel.add(chkEng);

		chkAdm.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chkAdm.setForeground(Color.DARK_GRAY);
		chkAdm.setBounds(393, 160, 130, 18);
		painel.add(chkAdm);

		chsHorista.setForeground(Color.DARK_GRAY);
		chsHorista.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tipo.add(chsHorista);
		chsHorista.setBounds(408, 32, 110, 18);
		painel.add(chsHorista);

		chsMensalista.setForeground(Color.DARK_GRAY);
		chsMensalista.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tipo.add(chsMensalista);
		chsMensalista.setBounds(408, 52, 110, 18);
		painel.add(chsMensalista);

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
		painel.add(voltar);


		//EDITAR
		//Título

		instruTituloEditar.setHorizontalAlignment(SwingConstants.CENTER);
		instruTituloEditar.setForeground(Color.DARK_GRAY);
		instruTituloEditar.setFont(new Font("Tahoma", Font.BOLD, 12));
		instruTituloEditar.setBounds(140, 225, 250, 15);
		painel.add(instruTituloEditar);

		//Separador

		separator.setBounds(5, 215, (PW - 10), 5);
		painel.add(separator);

		//Instrução

		instruSel.setForeground(Color.DARK_GRAY);
		instruSel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		instruSel.setBounds(186, 255, 157, 15);
		painel.add(instruSel);

		//Confirmar

		confirmar.setForeground(Color.WHITE);
		confirmar.setFont(new Font("SansSerif", Font.BOLD, 11));
		confirmar.setBackground(new Color(0, 128, 0));
		confirmar.setBounds((PW - 35), (PH - 35), 40, 40);

		confirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//ERROS
				if(inNome.getText().isEmpty() & inCracha.getText().isEmpty() & inCc.getText().isEmpty())
					JOptionPane.showMessageDialog(painel, "Não foi possível registrar usuário! Nome, crachá e CC obrigatórios", "Operação não concluída", JOptionPane.WARNING_MESSAGE);
				else if (inCc.getText().isEmpty())
					JOptionPane.showMessageDialog(painel, "Não foi possível registrar usuário! Centro de custo obrigatório", "Operação não concluída", JOptionPane.WARNING_MESSAGE);
				else if(inNome.getText().isEmpty())
					JOptionPane.showMessageDialog(painel, "Não foi possível registrar usuário! Nome obrigatório", "Operação não concluída", JOptionPane.WARNING_MESSAGE);
				else if(inCracha.getText().isEmpty())
					JOptionPane.showMessageDialog(painel, "Não foi possível registrar usuário! Número do crachá obrigatório", "Operação não concluída", JOptionPane.WARNING_MESSAGE);
				else if((chkEle.isSelected() == false) &
						(chkMec.isSelected() == false)  &
						(chkProg.isSelected() == false) &
						(chkProj.isSelected() == false) &
						(chkEng.isSelected() == false) &
						(chkAdm.isSelected() == false))
					JOptionPane.showMessageDialog(painel, "Não foi possível registrar usuário! Nenhuma função selecionada", "Operação não concluída", JOptionPane.WARNING_MESSAGE);

				else if((chsHorista.isSelected() == false) &
						(chsMensalista.isSelected() == false))
					JOptionPane.showMessageDialog(painel, "Não foi possível registrar usuário! Selecione horista/mensalista", "Operação não concluída", JOptionPane.WARNING_MESSAGE);
				//ERROS
				else
				{
					if (editar)
					{
						switch (JOptionPane.showOptionDialog(painel,
								"Deseja realmente alterar os dados deste funcionário do banco de dados?",
								"Confirmação",
								JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE,
								null,
								botoes,
								botoes[1]))
						{
						case 0:
							//boolean editar, int cracha, String nomeAntigo, String nome, int cc
							int capt = dbs.janelas.Funcionarios.inserir_Funcionario(editar, Integer.parseInt(inCracha.getText()), nomeAntigo, inNome.getText(), Integer.parseInt(inCc.getText()), chkEle.isSelected(), chkMec.isSelected(), chkProg.isSelected(), chkEng.isSelected(), chkProj.isSelected(), chkAdm.isSelected(), chsHorista.isSelected(), chsMensalista.isSelected());
							if (capt == 2)
								JOptionPane.showMessageDialog(painel, "Não foi possível alterar dados do usuário! Erro no SQLite", "Operação não concluída", JOptionPane.WARNING_MESSAGE);
							//ERROS
							else if (capt == 1)
							{
								JOptionPane.showMessageDialog(painel, "Edição feita com sucesso!", "Operação concluída", JOptionPane.INFORMATION_MESSAGE);
								atualizar(pai);
							}
							break;

						case 1:
							JOptionPane.showMessageDialog(painel, "Operação não realizada", "Operação não concluída", JOptionPane.INFORMATION_MESSAGE);
							break;
						}
					}
					else
					{
						if (lisCracha.contains(Integer.parseInt(inCracha.getText())))
						{
							JOptionPane.showMessageDialog(painel, "Não foi possível registrar usuário! Número de crachá já registrado", "Operação não concluída", JOptionPane.WARNING_MESSAGE);
						}
						else
						{
							switch (JOptionPane.showOptionDialog(painel,
									"Deseja realmente registrar os dados deste funcionário no banco de dados?",
									"Confirmação",
									JOptionPane.YES_NO_OPTION,
									JOptionPane.QUESTION_MESSAGE,
									null,
									botoes,
									botoes[1]))
							{
							case 0:
								//boolean editar, int cracha, String nomeAntigo, String nome, int cc, boolean ele, boolean mec, boolean prog, boolean eng, boolean proj, boolean adm, boolean hor, boolean mens
								int capt = dbs.janelas.Funcionarios.inserir_Funcionario(editar, Integer.parseInt(inCracha.getText()), nomeAntigo, inNome.getText(), Integer.parseInt(inCc.getText()), chkEle.isSelected(), chkMec.isSelected(), chkProg.isSelected(), chkEng.isSelected(), chkProj.isSelected(), chkAdm.isSelected(), chsHorista.isSelected(), chsMensalista.isSelected());
								
								if (capt == 0)
									JOptionPane.showMessageDialog(painel, "Não foi possível registrar usuário! Número de crachá já registrado", "Operação não concluída", JOptionPane.WARNING_MESSAGE);

								else if (capt == 2)
									JOptionPane.showMessageDialog(painel, "Não foi possível registrar usuário! Erro no SQLite", "Operação não concluída", JOptionPane.WARNING_MESSAGE);
								//ERROS
								else if (capt == 1)
								{
									JOptionPane.showMessageDialog(painel, "Registro bem-sucedido!", "Operação concluída", JOptionPane.INFORMATION_MESSAGE);
									atualizar(pai);
								}
								break;

							case 1:
								JOptionPane.showMessageDialog(painel, "Operação não realizada", "Operação não concluída", JOptionPane.INFORMATION_MESSAGE);
								break;
							}
						}
					}
				}
			}
		});
		painel.add(confirmar);		

		//Combobox
		funcSel.setFont(new Font("Tahoma", Font.BOLD, 20));
		funcSel.addItem("");

		for (int i = 0; i < lisNome.size(); i++)
		{
			funcSel.addItem(lisNome.get(i));
		}

		funcSel.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				editar = true;
				novo.setEnabled(true);
				funcSel.removeItem("");
				inNome.setText(lisNome.get(funcSel.getSelectedIndex()));
				instruTituloReg.setText("EDITAR FUNCIONÁRIO");
				inCracha.setText(Integer.toString(lisCracha.get(funcSel.getSelectedIndex())));
				chkEle.setSelected(lisEle.get(funcSel.getSelectedIndex()));
				chkMec.setSelected(lisMec.get(funcSel.getSelectedIndex()));
				chkProj.setSelected(lisProj.get(funcSel.getSelectedIndex()));
				chkProg.setSelected(lisProg.get(funcSel.getSelectedIndex()));
				chkEng.setSelected(lisEng.get(funcSel.getSelectedIndex()));
				chkAdm.setSelected(lisAdm.get(funcSel.getSelectedIndex()));
				chsHorista.setSelected(lisHor.get(funcSel.getSelectedIndex()));
				chsMensalista.setSelected(lisMen.get(funcSel.getSelectedIndex()));
				inCc.setText(Integer.toString(lisCc.get(funcSel.getSelectedIndex())));
				nomeAntigo = inNome.getText();
			}
		});
		funcSel.setBounds(65, 285, 400, 50);
		painel.add(funcSel);

		//Botão apagar

		apagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (funcSel.getSelectedItem().equals(""))
				{
					JOptionPane.showMessageDialog(painel, "Nenhum funcionário selecionado", "Sem seleção", JOptionPane.WARNING_MESSAGE);
				}
				else
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
					atualizar(pai);
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
	}

	public void atualizar(Inicio pai)
	{
		GerenciarFuncionarios_backup novo = new GerenciarFuncionarios_backup(pai);
		novo.setVisible(true);

		GerenciarFuncionarios_backup antigo = this;
		EventQueue.invokeLater(new Runnable() {
			public void run()
			{
				antigo.dispose();
			}
		});
	}
}
