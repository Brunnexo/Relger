package janelas.menus;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import backend.Atributos;
import backend.Config;
import janelas.interacao.Resumo;
import janelas.interacao.SelecionarAtividade;
import mssql.Extra;
import mssql.Funcionarios;
import threads.Saida;

public class Identificacao extends JFrame
{
	final static int PW = 450, PH = 170;

	static boolean terminal = false;
	static boolean adm = false;

	private JPanel painel = new JPanel(); //Declara painel
	private JTextField cracha;
	private JButton ok;

	//Definição de Janela
	Identificacao identificacao = this;

	public boolean override = false;

	String[] botoes = {"Sim", "Não"};

	//Definição de Resumo
	public Resumo res;

	//Definição de Atributos
	public Atributos att;
	private JButton voltar;

	public Identificacao(Inicio pai)
	{
		config();
		//Interação de Janela
		pai.setVisible(false);

		painel.setBorder(new EmptyBorder(5, 5, 5, 5));
		painel.setLayout(null);

		if (terminal)
			setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		else
			setDefaultCloseOperation(EXIT_ON_CLOSE);

		setBounds(100, 100, PW, PH);
		setContentPane(painel);
		setLocationRelativeTo(null);
		setUndecorated(true);
		setShape(new RoundRectangle2D.Double(0,0,PW,PH,5,5));

		cracha = new JTextField();
		ok = new JButton("OK");
		JButton sair = new JButton("X");
		JLabel instrucao = new JLabel("CRACHÁ");
		voltar = new JButton("<");
		
		painel.add(voltar);
		painel.add(cracha);
		painel.add(instrucao);
		painel.add(sair);
		painel.add(ok);

		cracha.setForeground(Color.DARK_GRAY);
		cracha.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased (KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_SHIFT)
					override = false;
			}
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_SHIFT)
					override = true;

				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					proximo(override);
			}
		});
		cracha.setFont(new Font("Tahoma", Font.PLAIN, 30));
		cracha.setHorizontalAlignment(SwingConstants.CENTER);
		cracha.setToolTipText("Entre com o número do crachá");
		cracha.setBounds(50, 47, 350, 50);
		cracha.setColumns(10);

		voltar.setVisible(adm);
		voltar.setForeground(Color.DARK_GRAY);
		voltar.setFont(new Font("SansSerif", Font.BOLD, 11));
		voltar.setBackground(Color.LIGHT_GRAY);
		voltar.setBounds(-5, -5, 35, 35);
		
		voltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pai.setVisible(true);
				dispose();
			}
		});
		
		ok.setForeground(Color.DARK_GRAY);
		ok.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased (KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_SHIFT)
					override = false;
			}
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_SHIFT)
					override = true;

				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					proximo(override);
			}
		});

		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				proximo(override);
			}

		});
		ok.setBounds(150, 107, 150, 50);

		sair.setVisible(!terminal);
		sair.setForeground(Color.WHITE);
		sair.setFont(new Font("SansSerif", Font.BOLD, 10));
		sair.setBackground(new Color(178, 34, 34));
		sair.setBounds(420, -5, 35, 35);

		sair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Saida.sair();
			}
		});

		instrucao.setForeground(Color.DARK_GRAY);
		instrucao.setHorizontalAlignment(SwingConstants.CENTER);
		instrucao.setFont(new Font("Tahoma", Font.PLAIN, 22));
		instrucao.setBounds(183, 10, 83, 27);

		EventQueue.invokeLater(new Runnable() {

			public void run() {
				cracha.grabFocus();
				cracha.requestFocus();
			}
		});
	}

	public Identificacao()
	{
		config();
		painel.setLayout(null);

		if (terminal)
			setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		else
			setDefaultCloseOperation(EXIT_ON_CLOSE);

		setBounds(100, 100, PW, PH);
		setContentPane(painel);
		setLocationRelativeTo(null);
		setUndecorated(true);
		setShape(new RoundRectangle2D.Double(0,0,450,170,5,5));

		cracha = new JTextField();
		ok = new JButton("OK");
		JLabel instrucao = new JLabel("CRACHÁ");
		JButton sair = new JButton("X");

		painel.add(cracha);
		painel.add(ok);
		painel.add(instrucao);
		painel.add(sair);

		cracha.setForeground(Color.DARK_GRAY);
		cracha.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased (KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_SHIFT)
					override = false;
			}
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_SHIFT)
					override = true;

				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					proximo(override);
			}
		});
		cracha.setFont(new Font("Tahoma", Font.PLAIN, 30));
		cracha.setHorizontalAlignment(SwingConstants.CENTER);
		cracha.setToolTipText("Entre com o número do crachá");
		cracha.setBounds(50, 47, 350, 50);
		cracha.setColumns(10);

		ok.setForeground(Color.DARK_GRAY);
		ok.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased (KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_SHIFT)
					override = false;
			}
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_SHIFT)
					override = true;

				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					proximo(override);
			}
		});
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				proximo(override);
			}

		});
		ok.setBounds(150, 107, 150, 50);

		instrucao.setForeground(Color.DARK_GRAY);
		instrucao.setHorizontalAlignment(SwingConstants.CENTER);
		instrucao.setFont(new Font("Tahoma", Font.PLAIN, 22));
		instrucao.setBounds(183, 10, 83, 27);

		sair.setVisible(!terminal);
		sair.setForeground(Color.WHITE);
		sair.setFont(new Font("SansSerif", Font.BOLD, 10));
		sair.setBackground(new Color(178, 34, 34));
		sair.setBounds(420, -5, 35, 35);
		
		sair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Saida.sair();
			}
		});

		EventQueue.invokeLater(new Runnable() {

			public void run() {
				cracha.grabFocus();
				cracha.requestFocus();
			}
		});
	}

	public void proximo(boolean override)
	{
		att = new Atributos();
		att.setOverride(override);
		this.override = false;

		if (this.cracha.getText().equals(""))
		{
			this.cracha.setText("");
			JOptionPane.showMessageDialog(this.painel, "Insira o número do crachá", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			try
			{
				att.escreveValores(Funcionarios.pesquisarCracha(Integer.parseInt(this.cracha.getText())));

			} catch (NumberFormatException ex)
			{
				JOptionPane.showMessageDialog(this.painel, "Dados inválidos!", "Erro", JOptionPane.ERROR_MESSAGE);
			}
			if (att.getCrachaFunc() == 0)
			{
				this.cracha.setText("");
				JOptionPane.showMessageDialog(this.painel, "Crachá não encontrado no banco de dados", "Erro", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				if (Extra.retornar(Integer.parseInt(this.cracha.getText()), att.dataCondicionalFormatada()))
				{
					switch (JOptionPane.showOptionDialog(painel, "Deseja registrar suas horas extras?", "Hora extra marcada", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, botoes, botoes[1]))
					{
					case 0:
						att.sethExtraRegistro(true);
						res = new Resumo(Integer.parseInt(this.cracha.getText()), att);
						res.setVisible(true);
						break;
					case 1:
						att.sethExtraRegistro(false);
						res = new Resumo(Integer.parseInt(this.cracha.getText()), att);
						res.setVisible(true);
						break;
					}
					SelecionarAtividade selec = new SelecionarAtividade(identificacao);
					selec.setVisible(true);
				}
				else
				{
					res = new Resumo(Integer.parseInt(this.cracha.getText()), att);
					res.setVisible(true);

					SelecionarAtividade selec = new SelecionarAtividade(identificacao);
					selec.setVisible(true);
				}
			}
		}
	}

	static void config()
	{
		ArrayList<String> config = Config.lerConfig();
		boolean iniciarLeitura = false;

		for (int i = 0; i < config.size(); i++)
		{
			if (iniciarLeitura == false)
			{
				if (config.get(i).contains("[::Desktop]"))
					iniciarLeitura = true;
			}
			else
			{
				if (config.get(i).contains("//"))
					System.out.println("Comentário da linha " + (i+1) + " do arquivo config.txt: " + config.get(i).replaceAll("//", ""));
				else if (config.get(i).contains("[terminal]"))
				{
					if (config.get(i).toUpperCase().contains("SIM"))
						terminal = true;
					else if (config.get(i).toUpperCase().contains("NÃO"))
						terminal = false;
				}
				else if (config.get(i).contains("[adm]"))
				{
					if (config.get(i).toUpperCase().contains("SIM"))
						adm = true;
					else if (config.get(i).toUpperCase().contains("NÃO"))
						adm = false;
				}
				else if (config.get(i).equals("[::]"))
					break;
			}
		}
	}
}
