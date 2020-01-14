package janelas.menus;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.RoundRectangle2D;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
import backend.Executar;
import backend.Token;
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

	public boolean override = false, dataOverride = false;

	String[] botoes = {"Sim", "Não"};

	//Definição de Resumo
	public Resumo res;

	//Definição de Atributos
	public Atributos att;
	private JButton voltar;
	private JLabel versao;

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
		setLocationRelativeTo(null);
		setContentPane(painel);
		setUndecorated(true);
		setShape(new RoundRectangle2D.Double(0, 0, PW, PH, 5, 5));

		cracha = new JTextField();
		ok = new JButton("OK");
		JButton sair = new JButton("X");
		JLabel instrucao = new JLabel("REGISTRO");
		voltar = new JButton("<");
		versao = new JLabel(Executar.VERSAO);

		painel.add(versao);
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
				if (e.getKeyCode() == KeyEvent.VK_CONTROL)
					dataOverride = false;
			}
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_SHIFT)
					override = true;
				if (e.getKeyCode() == KeyEvent.VK_CONTROL)
					dataOverride = true;

				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					proximo();

			}
		});
		cracha.setFont(new Font("Tahoma", Font.PLAIN, 30));
		cracha.setHorizontalAlignment(SwingConstants.CENTER);
		cracha.setToolTipText("Entre com o número do registro");
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
				if (e.getKeyCode() == KeyEvent.VK_CONTROL)
					dataOverride = false;
			}
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_SHIFT)
					override = true;
				if (e.getKeyCode() == KeyEvent.VK_CONTROL)
					dataOverride = true;

				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					proximo();
			}
		});

		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				proximo();
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
		instrucao.setBounds(50, 10, 350, 27);

		versao.setFont(new Font("Tahoma", Font.PLAIN, 12));
		versao.setBounds(394, 149, 50, 15);
		versao.setHorizontalAlignment(SwingConstants.RIGHT);
		versao.setForeground(Color.LIGHT_GRAY);

		EventQueue.invokeLater(new Runnable() {

			public void run() {
				cracha.grabFocus();
				cracha.requestFocus();
			}
		});
	}

	public void proximo()
	{
		att = new Atributos();

		if (this.override | this.dataOverride)
		{
			if (token())
			{
				if (this.override)
					att.setOverride(true);
				if (this.dataOverride)
				{
					String dataEntrada = JOptionPane.showInputDialog(null, "Insira a data: ","Data",JOptionPane.QUESTION_MESSAGE);
					LocalDate data = null;

					int escolha = 0;
					while (escolha == 0)
					{
						try {
							data = LocalDate.parse(dataEntrada, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

							att.setDataOverride(true);
							att.setDataOvr(data);

							escolha = 1;
							if (data == null)
							{
								switch (JOptionPane.showOptionDialog(null, "Deseja continuar?", "Continuar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[] {"SIM", "NÃO"}, 0))
								{
								case 0:
									break;
								case 1:
									escolha = 1;
									break;
								}
							}
						} catch (DateTimeParseException ex)
						{
							JOptionPane.showMessageDialog(null, "Data inválida!", "Erro", JOptionPane.ERROR_MESSAGE, null);
							escolha = 1;
						}
					}
				}
			}
			else
				JOptionPane.showMessageDialog(null, "TOKEN inválido!", "Erro", JOptionPane.ERROR_MESSAGE, null);
		}

		this.override = false;

		if (this.cracha.getText().equals(""))
		{
			this.cracha.setText("");
			JOptionPane.showMessageDialog(this.painel, "Insira o número do registro", "Erro", JOptionPane.ERROR_MESSAGE);
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
				JOptionPane.showMessageDialog(this.painel, "Registro não encontrado no banco de dados", "Erro", JOptionPane.ERROR_MESSAGE);
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

	boolean token()
	{
		boolean tokenConfere = false;
		String inToken = new String();
		while (!tokenConfere)
		{
			inToken = JOptionPane.showInputDialog(null, "Insira o TOKEN", "Token", JOptionPane.QUESTION_MESSAGE);
			System.out.println(inToken);
			if (inToken == null)
			{
				tokenConfere = false;
				break;
			}
			else if (Token.checaToken(inToken))
				tokenConfere = true;
			else if (!Token.checaToken(inToken))
				tokenConfere = false;
		}

		return tokenConfere;
	}

	static void config()
	{
		try {
			terminal = InetAddress.getLocalHost().getHostName().contains("RELGER");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		ResultSet listaNomesAdm = Funcionarios.nomeAdministrativo();
		ArrayList<String> listaSQL = new ArrayList<String>();

		try {
			String[] nomeUsuario = System.getProperty("user.name").split(" ");
			String usuario;

			if (nomeUsuario.length < 2)
				usuario = nomeUsuario[0];
			else
				usuario = String.valueOf(nomeUsuario[0].charAt(0)) + nomeUsuario[1];


			while (listaNomesAdm.next())
			{
				listaSQL.add(listaNomesAdm.getString("NOME"));
			}

			for (int i = 0; i < listaSQL.size(); i++)
			{
				String[] nomeUsuarioComp = listaSQL.get(i).split(" ");
				String usuarioComp = String.valueOf(nomeUsuarioComp[0].charAt(0)) + nomeUsuarioComp[1];

				if (usuario.toUpperCase().equals(usuarioComp.toUpperCase()))
					adm = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
