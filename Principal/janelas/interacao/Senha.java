package janelas.interacao;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.RoundRectangle2D;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import backend.Crypto;
import janelas.menus.GerenciarFuncionarios;
import janelas.menus.Inicio;
import mssql.Senhas;

public class Senha extends JFrame {

	public int bt = 0;

	private JPanel painel;
	private JPasswordField inSenha;
	private JTextField inCracha;

	int cracha;
	String senha;

	Inicio paiInicio;

	public Senha(Inicio pai) {
		//Atributos pai
		this.paiInicio = pai;
		pai.setEnabled(false);
		//Atributos do Painel
		painel = new JPanel();
		painel.setBorder(new LineBorder(new Color(64, 64, 64), 2, true));
		painel.setLayout(null);

		//Atributos da Janela
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 300, 170);
		setContentPane(painel);
		setLocationRelativeTo(null);
		setUndecorated(true);
		setAlwaysOnTop(true);
		setShape(new RoundRectangle2D.Double(0,0,300,170,5,5));

		//Componentes do Painel
		JLabel titulo = new JLabel("ÁREA RESTRITA");
		JLabel instruSenha = new JLabel("SENHA");
		JButton btnOk = new JButton("OK");
		JButton btnCancelar = new JButton("CANCELAR");
		JLabel instruCracha = new JLabel("CRACH\u00C1");
		inSenha = new JPasswordField();
		inCracha = new JTextField();


		//Adição dos Componentes
		painel.add(inCracha);
		painel.add(inSenha);
		painel.add(titulo);
		painel.add(instruSenha);
		painel.add(btnOk);
		painel.add(btnCancelar);
		painel.add(instruCracha);


		//Título
		titulo.setBounds(32, 10, 235, 15);
		titulo.setFont(new Font("Tahoma", Font.BOLD, 12));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setForeground(Color.DARK_GRAY);

		//Crachá
		inCracha.setHorizontalAlignment(SwingConstants.CENTER);
		inCracha.setBounds(120, 43, 160, 27);
		inCracha.setColumns(10);
		inCracha.setForeground(Color.DARK_GRAY);
		inCracha.setFont(new Font("Tahoma", Font.PLAIN, 12));

		//Senha
		inSenha.setHorizontalAlignment(SwingConstants.CENTER);
		inSenha.setForeground(Color.DARK_GRAY);
		inSenha.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					try
					{
						cracha = Integer.parseInt(inCracha.getText());
					} catch (NumberFormatException ex)
					{
						JOptionPane.showMessageDialog(painel, "Crachá inválido", "Erro", JOptionPane.ERROR_MESSAGE);
					}
					senha = String.valueOf(inSenha.getPassword());
					if (inCracha.getText().equals(""))
					{
						JOptionPane.showMessageDialog(painel, "Insira o crachá", "Erro", JOptionPane.WARNING_MESSAGE);
					}
					else if (Arrays.equals(inSenha.getPassword(), "".toCharArray()))
					{
						JOptionPane.showMessageDialog(painel, "Insira a senha", "Erro", JOptionPane.WARNING_MESSAGE);
					}
					else if (Senhas.login(cracha, Crypto.criptografar(senha)))
					{
						dispose();
						GerenciarFuncionarios menu = new GerenciarFuncionarios(pai);
						menu.setVisible(true);
					}
					else
					{
						JOptionPane.showMessageDialog(painel, "Crachá ou senha incorretos!", "Erro", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		inSenha.setEchoChar('•');
		inSenha.setFont(new Font("Tahoma", Font.BOLD, 12));
		inSenha.setBounds(120, 70, 160, 27);

		//Instrução de Senha
		instruSenha.setHorizontalAlignment(SwingConstants.CENTER);
		instruSenha.setForeground(Color.DARK_GRAY);
		instruSenha.setBounds(20, 75, 52, 16);

		//Botão OK
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					cracha = Integer.parseInt(inCracha.getText());
				} catch (NumberFormatException ex)
				{
					JOptionPane.showMessageDialog(painel, "Crachá inválido", "Erro", JOptionPane.ERROR_MESSAGE);
				}
				
				senha = String.valueOf(inSenha.getPassword());
				
				
				if (inCracha.getText().equals(""))
				{
					JOptionPane.showMessageDialog(painel, "Insira o crachá", "Erro", JOptionPane.WARNING_MESSAGE);
				}
				else if (Arrays.equals(inSenha.getPassword(), "".toCharArray()))
				{
					JOptionPane.showMessageDialog(painel, "Insira a senha", "Erro", JOptionPane.WARNING_MESSAGE);
				}
				else if (Senhas.login(cracha, senha))
				{
					dispose();
					GerenciarFuncionarios menu = new GerenciarFuncionarios(pai);
					menu.setVisible(true);
				}
				else
				{
					JOptionPane.showMessageDialog(painel, "Crachá ou senha incorretos!", "Erro", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnOk.setBounds(20, 115, 120, 50);
		btnOk.setForeground(Color.DARK_GRAY);

		//Botão Cancelar
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pai.setEnabled(true);
				dispose();
			}
		});
		btnCancelar.setForeground(Color.DARK_GRAY);
		btnCancelar.setBounds(160, 115, 120, 50);

		instruCracha.setHorizontalAlignment(SwingConstants.CENTER);
		instruCracha.setForeground(Color.DARK_GRAY);
		instruCracha.setBounds(20, 48, 52, 16);

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				inCracha.grabFocus();
				inCracha.requestFocus();
			}
		});
	}
	
	public void mostrar()
	{
		this.setVisible(true);
		this.setAlwaysOnTop(true);
	}
}