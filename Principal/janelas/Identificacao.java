package janelas;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import backend.Executar;
import backend.Token;
import extensores.JPanelEx;
import objetos.Colaborador;
import threads.Saida;

public class Identificacao extends JFrame {
	final static int PW = 450, PH = 170;

	private JPanelEx painel;
	private JTextField cracha;
	private JButton ok;

	//Definição de Janela
	public boolean override = false, dataOverride = false;

	//Definição de Resumo
	public Resumo res;

	//Definição de Atributos
	Colaborador col;

	public Identificacao() {
		//Interação de Janela
		painel = new JPanelEx();
		painel.setBorder(new EmptyBorder(5, 5, 5, 5));
		painel.setLayout(null);

		setBounds(100, 100, PW, PH);
		setLocationRelativeTo(null);
		setContentPane(painel);
		setUndecorated(true);
		setShape(new RoundRectangle2D.Double(0, 0, PW, PH, 5, 5));

		JComponent[] componentes = {
				(JTextFieldnew JTextField(), //0
				new JButton("Ok"), //1
				(JButton) new JButton("X"), //2
				new JLabel("REGISTRO"), //3
				(JButton) new JButton("<"), //4
				new JLabel(Executar.VERSAO) //5
		};

		painel.add(componentes);
		
		cracha.setForeground(Color.DARK_GRAY);
		cracha.setFont(new Font("Tahoma", Font.PLAIN, 30));
		cracha.setHorizontalAlignment(SwingConstants.CENTER);
		cracha.setToolTipText("Entre com o número do registro");
		cracha.setBounds(50, 47, 350, 50);
		cracha.setColumns(10);

		componentes[4].setForeground(Color.DARK_GRAY);
		componentes[4].setFont(new Font("SansSerif", Font.BOLD, 11));
		componentes[4].setBackground(Color.LIGHT_GRAY);
		componentes[4].setBounds(-5, -5, 35, 35);

		((JButton) componentes[4]).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		ok.setForeground(Color.DARK_GRAY);
		ok.setBounds(150, 107, 150, 50);

		((JButton) componentes[2]).setForeground(Color.WHITE);
		((JButton) componentes[2]).setFont(new Font("SansSerif", Font.BOLD, 10));
		((JButton) componentes[2]).setBackground(new Color(178, 34, 34));
		((JButton) componentes[2]).setBounds(420, -5, 35, 35);

		((JButton) componentes[2]).addActionListener(new ActionListener() {
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

	boolean token() {
		boolean tokenConfere = false;
		String inToken = new String();
		while (!tokenConfere) {
			inToken = JOptionPane.showInputDialog(null, "Insira o TOKEN", "Token", JOptionPane.QUESTION_MESSAGE);
			System.out.println(inToken);
			if (inToken == null) {
				tokenConfere = false;
				break;
			}
			else if (Token.checaToken(inToken))
				tokenConfere = true;
			else if (!Token.checaToken(inToken)) {
				tokenConfere = false;
				JOptionPane.showMessageDialog(null, "TOKEN inválido!", "Erro", JOptionPane.ERROR_MESSAGE, null);
				break;
			}
		}
		return true;
		//return tokenConfere;
	}
}
