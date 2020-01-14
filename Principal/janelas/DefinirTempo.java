package janelas;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import backend.Atributos;

import janelas.interacao.Resumo;
import janelas.interacao.SelecionarAtividade;
import janelas.menus.DescricoesSub;
import janelas.menus.Identificacao;
import janelas.menus.Inicio;
import janelas.selecao.Projeto;
import janelas.selecao.SR;
import mssql.Extra;
import mssql.Relatorios;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class DefinirTempo extends JFrame {

	public Atributos att;
	public Resumo res;

	String[] botoes = {"Sim", "Não"};

	static final int PW = 310, PH = 520;
	private JPanel painel;
	private JTextField campoNum;
	private int valorNum = 0;

	public DefinirTempo(DescricoesSub pai) {
		//Janela
		pai.setVisible(false);
		att = pai.att;
		res = pai.res;

		//Propriedades do Painel
		painel = new JPanel();
		painel.setBorder(new EmptyBorder(5, 5, 5, 5));
		painel.setLayout(null);

		//Propriedades da Janela
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setBounds(100,100,PW,PH);
		setContentPane(painel);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		setShape(new RoundRectangle2D.Double(0,0,PW,PH,5,5));

		//Componentes
		JButton btnX = new JButton("X"); //APAGAR
		campoNum = new JTextField();
		JButton tecla1 = new JButton("1");
		JButton tecla2 = new JButton("2");
		JButton tecla3 = new JButton("3");
		JButton tecla4 = new JButton("4");
		JButton tecla5 = new JButton("5");
		JButton tecla6 = new JButton("6");
		JButton tecla7 = new JButton("7");
		JButton tecla8 = new JButton("8");
		JButton tecla9 = new JButton("9");
		JButton tecla0 = new JButton("0");
		JButton enter = new JButton("OK");
		JButton backSpc = new JButton("<");

		//Adição
		painel.add(btnX);
		painel.add(campoNum);
		painel.add(tecla1);
		painel.add(tecla2);
		painel.add(tecla3);
		painel.add(tecla4);
		painel.add(tecla5);
		painel.add(tecla6);
		painel.add(tecla7);
		painel.add(tecla8);
		painel.add(tecla9);
		painel.add(tecla0);
		painel.add(enter);
		painel.add(backSpc);

		//Display
		campoNum.setEditable(false);
		campoNum.setFont(new Font("Tahoma", Font.BOLD, 22));
		campoNum.setColumns(10);
		campoNum.setBounds(10, 45, 190, 60);
		campoNum.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (String.valueOf(e.getKeyChar()).matches("\\d+"))
				{
					try
					{
						campoNum.setText(campoNum.getText() + String.valueOf(e.getKeyChar()));
						valorNum = Integer.parseInt(campoNum.getText());
					} catch (NumberFormatException ex)
					{
						campoNum.setText(campoNum.getText().substring(0, (campoNum.getText().length() - 1)));
						valorNum = Integer.parseInt(campoNum.getText());
					}
				}
				else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
				{
					try
					{
						campoNum.setText(campoNum.getText().substring(0, (campoNum.getText().length() - 1)));
						valorNum = Integer.parseInt(campoNum.getText());
					} catch (StringIndexOutOfBoundsException ex) {}
				}
				
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					valorNum = Integer.parseInt(campoNum.getText());
					registrar();
				}
			}
		});

		//Botão APAGAR TUDO
		btnX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				campoNum.setText("");
			}
		});
		btnX.setFocusPainted(false);
		btnX.setFocusTraversalKeysEnabled(false);
		btnX.setFocusable(false);
		btnX.setBorder(null);
		btnX.setBorderPainted(false);
		btnX.setForeground(Color.LIGHT_GRAY);
		btnX.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnX.setContentAreaFilled(false);
		btnX.setRequestFocusEnabled(false);
		btnX.setBounds(160, 55, 40, 40);

		//Tecla 0
		tecla0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				campoNum.setText(campoNum.getText() + "0");
				valorNum = Integer.parseInt(campoNum.getText());
			}
		});
		tecla0.setFont(new Font("Tahoma", Font.BOLD, 22));
		tecla0.setForeground(Color.DARK_GRAY);
		tecla0.setBounds(10, 420, 90, 90);

		//Tecla 1
		tecla1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				campoNum.setText(campoNum.getText() + "1");
				valorNum = Integer.parseInt(campoNum.getText());
			}
		});
		tecla1.setFont(new Font("Tahoma", Font.BOLD, 22));
		tecla1.setForeground(Color.DARK_GRAY);
		tecla1.setBounds(10, 320, 90, 90);

		//Tecla 2
		tecla2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				campoNum.setText(campoNum.getText() + "2");
				valorNum = Integer.parseInt(campoNum.getText());
			}
		});
		tecla2.setForeground(Color.DARK_GRAY);
		tecla2.setFont(new Font("Tahoma", Font.BOLD, 22));
		tecla2.setBounds(110, 320, 90, 90);

		//Tecla 3
		tecla3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				campoNum.setText(campoNum.getText() + "3");
				valorNum = Integer.parseInt(campoNum.getText());
			}
		});
		tecla3.setForeground(Color.DARK_GRAY);
		tecla3.setFont(new Font("Tahoma", Font.BOLD, 22));
		tecla3.setBounds(210, 320, 90, 90);

		//Tecla 4
		tecla4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				campoNum.setText(campoNum.getText() + "4");
				valorNum = Integer.parseInt(campoNum.getText());
			}
		});
		tecla4.setForeground(Color.DARK_GRAY);
		tecla4.setFont(new Font("Tahoma", Font.BOLD, 22));
		tecla4.setBounds(10, 220, 90, 90);

		//Tecla 5
		tecla5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				campoNum.setText(campoNum.getText() + "5");
				valorNum = Integer.parseInt(campoNum.getText());
			}
		});
		tecla5.setForeground(Color.DARK_GRAY);
		tecla5.setFont(new Font("Tahoma", Font.BOLD, 22));
		tecla5.setBounds(110, 220, 90, 90);

		//Tecla 6
		tecla6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				campoNum.setText(campoNum.getText() + "6");
				valorNum = Integer.parseInt(campoNum.getText());
			}
		});
		tecla6.setForeground(Color.DARK_GRAY);
		tecla6.setFont(new Font("Tahoma", Font.BOLD, 22));
		tecla6.setBounds(210, 220, 90, 90);

		//Tecla 7
		tecla7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				campoNum.setText(campoNum.getText() + "7");
				valorNum = Integer.parseInt(campoNum.getText());
			}
		});
		tecla7.setForeground(Color.DARK_GRAY);
		tecla7.setFont(new Font("Tahoma", Font.BOLD, 22));
		tecla7.setBounds(10, 120, 90, 90);

		//Tecla 8
		tecla8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				campoNum.setText(campoNum.getText() + "8");
				valorNum = Integer.parseInt(campoNum.getText());
			}
		});
		tecla8.setForeground(Color.DARK_GRAY);
		tecla8.setFont(new Font("Tahoma", Font.BOLD, 22));
		tecla8.setBounds(110, 120, 90, 90);

		//Tecla 9
		tecla9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				campoNum.setText(campoNum.getText() + "9");
				valorNum = Integer.parseInt(campoNum.getText());
			}
		});
		tecla9.setForeground(Color.DARK_GRAY);
		tecla9.setFont(new Font("Tahoma", Font.BOLD, 22));
		tecla9.setBounds(210, 120, 90, 90);

		//Tecla enter
		enter.setBackground(new Color(0, 100, 0));
		enter.setForeground(Color.WHITE);
		enter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				valorNum = Integer.parseInt(campoNum.getText());
				registrar();
			}
		});
		enter.setFont(new Font("Tahoma", Font.BOLD, 22));
		enter.setBounds(110, 420, 190, 90);

		//Tecla APAGAR
		backSpc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					campoNum.setText(campoNum.getText().substring(0, (campoNum.getText().length() - 1)));

				} catch (StringIndexOutOfBoundsException ex) {}
			}
		});
		backSpc.setBackground(new Color(178, 34, 34));
		backSpc.setForeground(Color.WHITE);
		backSpc.setFont(new Font("Tahoma", Font.BOLD, 22));
		backSpc.setBounds(210, 45, 90, 60);

		JLabel instru = new JLabel("DEFINA O TEMPO");
		instru.setFont(new Font("Tahoma", Font.BOLD, 12));
		instru.setForeground(Color.DARK_GRAY);
		instru.setBounds(108, 5, 105, 15);
		painel.add(instru);

		JButton voltar = new JButton("<");
		voltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pai.setVisible(true);
				dispose();
			}
		});
		voltar.setForeground(Color.DARK_GRAY);
		voltar.setFont(new Font("SansSerif", Font.BOLD, 11));
		voltar.setBackground(Color.LIGHT_GRAY);
		voltar.setBounds(-5, -5, 35, 35);
		painel.add(voltar);
		
		
		
		
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				campoNum.grabFocus();
				campoNum.requestFocus();
			}
		});
	}

	public DefinirTempo(Projeto pai) {
		//Janela
		pai.setVisible(false);
		att = pai.att;
		res = pai.res;

		//Propriedades do Painel
		painel = new JPanel();
		painel.setBorder(new EmptyBorder(5, 5, 5, 5));
		painel.setLayout(null);

		//Propriedades da Janela
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, PW, PH);
		setContentPane(painel);
		setLocationRelativeTo(null);
		setUndecorated(true);
		setShape(new RoundRectangle2D.Double(0,0,PW,PH,5,5));

		//Componentes
		JButton btnX = new JButton("X"); //APAGAR
		campoNum = new JTextField();
		JButton tecla1 = new JButton("1");
		JButton tecla2 = new JButton("2");
		JButton tecla3 = new JButton("3");
		JButton tecla4 = new JButton("4");
		JButton tecla5 = new JButton("5");
		JButton tecla6 = new JButton("6");
		JButton tecla7 = new JButton("7");
		JButton tecla8 = new JButton("8");
		JButton tecla9 = new JButton("9");
		JButton tecla0 = new JButton("0");
		JButton enter = new JButton("OK");
		JButton backSpc = new JButton("<");

		//Adição
		painel.add(btnX);
		painel.add(campoNum);
		painel.add(tecla1);
		painel.add(tecla2);
		painel.add(tecla3);
		painel.add(tecla4);
		painel.add(tecla5);
		painel.add(tecla6);
		painel.add(tecla7);
		painel.add(tecla8);
		painel.add(tecla9);
		painel.add(tecla0);
		painel.add(enter);
		painel.add(backSpc);

		//Display
		campoNum.setEditable(false);
		campoNum.setFont(new Font("Tahoma", Font.BOLD, 22));
		campoNum.setColumns(10);
		campoNum.setBounds(10, 45, 190, 60);
		campoNum.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (String.valueOf(e.getKeyChar()).matches("\\d+"))
				{
					try
					{
						campoNum.setText(campoNum.getText() + String.valueOf(e.getKeyChar()));
						valorNum = Integer.parseInt(campoNum.getText());
					} catch (NumberFormatException ex)
					{
						campoNum.setText(campoNum.getText().substring(0, (campoNum.getText().length() - 1)));
						valorNum = Integer.parseInt(campoNum.getText());
					}
				}
				else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
				{
					try
					{
						campoNum.setText(campoNum.getText().substring(0, (campoNum.getText().length() - 1)));
						valorNum = Integer.parseInt(campoNum.getText());
					} catch (StringIndexOutOfBoundsException ex) {}
				}
				
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					valorNum = Integer.parseInt(campoNum.getText());
					registrar();
				}
			}
		});

		//Botão APAGAR TUDO
		btnX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				campoNum.setText("");
			}
		});
		btnX.setFocusPainted(false);
		btnX.setFocusTraversalKeysEnabled(false);
		btnX.setFocusable(false);
		btnX.setBorder(null);
		btnX.setBorderPainted(false);
		btnX.setForeground(Color.LIGHT_GRAY);
		btnX.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnX.setContentAreaFilled(false);
		btnX.setRequestFocusEnabled(false);
		btnX.setBounds(160, 55, 40, 40);

		//Tecla 0
		tecla0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				campoNum.setText(campoNum.getText() + "0");
				valorNum = Integer.parseInt(campoNum.getText());
			}
		});
		tecla0.setFont(new Font("Tahoma", Font.BOLD, 22));
		tecla0.setForeground(Color.DARK_GRAY);
		tecla0.setBounds(10, 420, 90, 90);

		//Tecla 1
		tecla1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				campoNum.setText(campoNum.getText() + "1");
				valorNum = Integer.parseInt(campoNum.getText());
			}
		});
		tecla1.setFont(new Font("Tahoma", Font.BOLD, 22));
		tecla1.setForeground(Color.DARK_GRAY);
		tecla1.setBounds(10, 320, 90, 90);

		//Tecla 2
		tecla2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				campoNum.setText(campoNum.getText() + "2");
				valorNum = Integer.parseInt(campoNum.getText());
			}
		});
		tecla2.setForeground(Color.DARK_GRAY);
		tecla2.setFont(new Font("Tahoma", Font.BOLD, 22));
		tecla2.setBounds(110, 320, 90, 90);

		//Tecla 3
		tecla3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				campoNum.setText(campoNum.getText() + "3");
				valorNum = Integer.parseInt(campoNum.getText());
			}
		});
		tecla3.setForeground(Color.DARK_GRAY);
		tecla3.setFont(new Font("Tahoma", Font.BOLD, 22));
		tecla3.setBounds(210, 320, 90, 90);

		//Tecla 4
		tecla4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				campoNum.setText(campoNum.getText() + "4");
				valorNum = Integer.parseInt(campoNum.getText());
			}
		});
		tecla4.setForeground(Color.DARK_GRAY);
		tecla4.setFont(new Font("Tahoma", Font.BOLD, 22));
		tecla4.setBounds(10, 220, 90, 90);

		//Tecla 5
		tecla5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				campoNum.setText(campoNum.getText() + "5");
				valorNum = Integer.parseInt(campoNum.getText());
			}
		});
		tecla5.setForeground(Color.DARK_GRAY);
		tecla5.setFont(new Font("Tahoma", Font.BOLD, 22));
		tecla5.setBounds(110, 220, 90, 90);

		//Tecla 6
		tecla6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				campoNum.setText(campoNum.getText() + "6");
				valorNum = Integer.parseInt(campoNum.getText());
			}
		});
		tecla6.setForeground(Color.DARK_GRAY);
		tecla6.setFont(new Font("Tahoma", Font.BOLD, 22));
		tecla6.setBounds(210, 220, 90, 90);

		//Tecla 7
		tecla7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				campoNum.setText(campoNum.getText() + "7");
				valorNum = Integer.parseInt(campoNum.getText());
			}
		});
		tecla7.setForeground(Color.DARK_GRAY);
		tecla7.setFont(new Font("Tahoma", Font.BOLD, 22));
		tecla7.setBounds(10, 120, 90, 90);

		//Tecla 8
		tecla8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				campoNum.setText(campoNum.getText() + "8");
				valorNum = Integer.parseInt(campoNum.getText());
			}
		});
		tecla8.setForeground(Color.DARK_GRAY);
		tecla8.setFont(new Font("Tahoma", Font.BOLD, 22));
		tecla8.setBounds(110, 120, 90, 90);

		//Tecla 9
		tecla9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				campoNum.setText(campoNum.getText() + "9");
				valorNum = Integer.parseInt(campoNum.getText());
			}
		});
		tecla9.setForeground(Color.DARK_GRAY);
		tecla9.setFont(new Font("Tahoma", Font.BOLD, 22));
		tecla9.setBounds(210, 120, 90, 90);

		//Tecla enter
		enter.setBackground(new Color(0, 100, 0));
		enter.setForeground(Color.WHITE);
		enter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				valorNum = Integer.parseInt(campoNum.getText());
				registrar();
			}
		});
		enter.setFont(new Font("Tahoma", Font.BOLD, 22));
		enter.setBounds(110, 420, 190, 90);

		//Tecla APAGAR
		backSpc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					campoNum.setText(campoNum.getText().substring(0, (campoNum.getText().length() - 1)));

				} catch (StringIndexOutOfBoundsException ex) {}
			}
		});
		backSpc.setBackground(new Color(178, 34, 34));
		backSpc.setForeground(Color.WHITE);
		backSpc.setFont(new Font("Tahoma", Font.BOLD, 22));
		backSpc.setBounds(210, 45, 90, 60);

		JLabel instru = new JLabel("DEFINA O TEMPO");
		instru.setFont(new Font("Tahoma", Font.BOLD, 12));
		instru.setForeground(Color.DARK_GRAY);
		instru.setBounds(108, 5, 105, 15);
		painel.add(instru);

		JButton voltar = new JButton("<");
		voltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pai.setVisible(true);
				dispose();
			}
		});
		voltar.setForeground(Color.DARK_GRAY);
		voltar.setFont(new Font("SansSerif", Font.BOLD, 11));
		voltar.setBackground(Color.LIGHT_GRAY);
		voltar.setBounds(-5, -5, 35, 35);
		painel.add(voltar);
		
		
		
		
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				campoNum.grabFocus();
				campoNum.requestFocus();
			}
		});
	}

	public DefinirTempo(SR pai) {
		//Janela
		pai.setVisible(false);
		att = pai.att;
		res = pai.res;

		//Propriedades do Painel
		painel = new JPanel();
		painel.setBorder(new EmptyBorder(5, 5, 5, 5));
		painel.setLayout(null);

		//Propriedades da Janela
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, PW, PH);
		setContentPane(painel);
		setLocationRelativeTo(null);
		setUndecorated(true);
		setShape(new RoundRectangle2D.Double(0,0,PW,PH,5,5));

		//Componentes
		JButton btnX = new JButton("X"); //APAGAR
		campoNum = new JTextField();
		JButton tecla1 = new JButton("1");
		JButton tecla2 = new JButton("2");
		JButton tecla3 = new JButton("3");
		JButton tecla4 = new JButton("4");
		JButton tecla5 = new JButton("5");
		JButton tecla6 = new JButton("6");
		JButton tecla7 = new JButton("7");
		JButton tecla8 = new JButton("8");
		JButton tecla9 = new JButton("9");
		JButton tecla0 = new JButton("0");
		JButton enter = new JButton("OK");
		JButton backSpc = new JButton("<");

		//Adição
		painel.add(btnX);
		painel.add(campoNum);
		painel.add(tecla1);
		painel.add(tecla2);
		painel.add(tecla3);
		painel.add(tecla4);
		painel.add(tecla5);
		painel.add(tecla6);
		painel.add(tecla7);
		painel.add(tecla8);
		painel.add(tecla9);
		painel.add(tecla0);
		painel.add(enter);
		painel.add(backSpc);

		//Display
		campoNum.setEditable(false);
		campoNum.setFont(new Font("Tahoma", Font.BOLD, 22));
		campoNum.setColumns(10);
		campoNum.setBounds(10, 45, 190, 60);
		campoNum.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (String.valueOf(e.getKeyChar()).matches("\\d+"))
				{
					try
					{
						campoNum.setText(campoNum.getText() + String.valueOf(e.getKeyChar()));
						valorNum = Integer.parseInt(campoNum.getText());
					} catch (NumberFormatException ex)
					{
						campoNum.setText(campoNum.getText().substring(0, (campoNum.getText().length() - 1)));
						valorNum = Integer.parseInt(campoNum.getText());
					}
				}
				else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
				{
					try
					{
						campoNum.setText(campoNum.getText().substring(0, (campoNum.getText().length() - 1)));
						valorNum = Integer.parseInt(campoNum.getText());
					} catch (StringIndexOutOfBoundsException ex) {}
				}
				
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					valorNum = Integer.parseInt(campoNum.getText());
					registrar();
				}
			}
		});

		//Botão APAGAR TUDO
		btnX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				campoNum.setText("");
			}
		});
		btnX.setFocusPainted(false);
		btnX.setFocusTraversalKeysEnabled(false);
		btnX.setFocusable(false);
		btnX.setBorder(null);
		btnX.setBorderPainted(false);
		btnX.setForeground(Color.LIGHT_GRAY);
		btnX.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnX.setContentAreaFilled(false);
		btnX.setRequestFocusEnabled(false);
		btnX.setBounds(160, 55, 40, 40);

		//Tecla 0
		tecla0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				campoNum.setText(campoNum.getText() + "0");
				valorNum = Integer.parseInt(campoNum.getText());
			}
		});
		tecla0.setFont(new Font("Tahoma", Font.BOLD, 22));
		tecla0.setForeground(Color.DARK_GRAY);
		tecla0.setBounds(10, 420, 90, 90);

		//Tecla 1
		tecla1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				campoNum.setText(campoNum.getText() + "1");
				valorNum = Integer.parseInt(campoNum.getText());
			}
		});
		tecla1.setFont(new Font("Tahoma", Font.BOLD, 22));
		tecla1.setForeground(Color.DARK_GRAY);
		tecla1.setBounds(10, 320, 90, 90);

		//Tecla 2
		tecla2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				campoNum.setText(campoNum.getText() + "2");
				valorNum = Integer.parseInt(campoNum.getText());
			}
		});
		tecla2.setForeground(Color.DARK_GRAY);
		tecla2.setFont(new Font("Tahoma", Font.BOLD, 22));
		tecla2.setBounds(110, 320, 90, 90);

		//Tecla 3
		tecla3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				campoNum.setText(campoNum.getText() + "3");
				valorNum = Integer.parseInt(campoNum.getText());
			}
		});
		tecla3.setForeground(Color.DARK_GRAY);
		tecla3.setFont(new Font("Tahoma", Font.BOLD, 22));
		tecla3.setBounds(210, 320, 90, 90);

		//Tecla 4
		tecla4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				campoNum.setText(campoNum.getText() + "4");
				valorNum = Integer.parseInt(campoNum.getText());
			}
		});
		tecla4.setForeground(Color.DARK_GRAY);
		tecla4.setFont(new Font("Tahoma", Font.BOLD, 22));
		tecla4.setBounds(10, 220, 90, 90);

		//Tecla 5
		tecla5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				campoNum.setText(campoNum.getText() + "5");
				valorNum = Integer.parseInt(campoNum.getText());
			}
		});
		tecla5.setForeground(Color.DARK_GRAY);
		tecla5.setFont(new Font("Tahoma", Font.BOLD, 22));
		tecla5.setBounds(110, 220, 90, 90);

		//Tecla 6
		tecla6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				campoNum.setText(campoNum.getText() + "6");
				valorNum = Integer.parseInt(campoNum.getText());
			}
		});
		tecla6.setForeground(Color.DARK_GRAY);
		tecla6.setFont(new Font("Tahoma", Font.BOLD, 22));
		tecla6.setBounds(210, 220, 90, 90);

		//Tecla 7
		tecla7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				campoNum.setText(campoNum.getText() + "7");
				valorNum = Integer.parseInt(campoNum.getText());
			}
		});
		tecla7.setForeground(Color.DARK_GRAY);
		tecla7.setFont(new Font("Tahoma", Font.BOLD, 22));
		tecla7.setBounds(10, 120, 90, 90);

		//Tecla 8
		tecla8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				campoNum.setText(campoNum.getText() + "8");
				valorNum = Integer.parseInt(campoNum.getText());
			}
		});
		tecla8.setForeground(Color.DARK_GRAY);
		tecla8.setFont(new Font("Tahoma", Font.BOLD, 22));
		tecla8.setBounds(110, 120, 90, 90);

		//Tecla 9
		tecla9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				campoNum.setText(campoNum.getText() + "9");
				valorNum = Integer.parseInt(campoNum.getText());
			}
		});
		tecla9.setForeground(Color.DARK_GRAY);
		tecla9.setFont(new Font("Tahoma", Font.BOLD, 22));
		tecla9.setBounds(210, 120, 90, 90);

		//Tecla enter
		enter.setBackground(new Color(0, 100, 0));
		enter.setForeground(Color.WHITE);
		enter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				valorNum = Integer.parseInt(campoNum.getText());
				registrar();
			}
		});
		enter.setFont(new Font("Tahoma", Font.BOLD, 22));
		enter.setBounds(110, 420, 190, 90);

		//Tecla APAGAR
		backSpc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					campoNum.setText(campoNum.getText().substring(0, (campoNum.getText().length() - 1)));

				} catch (StringIndexOutOfBoundsException ex) {}
			}
		});
		backSpc.setBackground(new Color(178, 34, 34));
		backSpc.setForeground(Color.WHITE);
		backSpc.setFont(new Font("Tahoma", Font.BOLD, 22));
		backSpc.setBounds(210, 45, 90, 60);

		JLabel instru = new JLabel("DEFINA O TEMPO");
		instru.setFont(new Font("Tahoma", Font.BOLD, 12));
		instru.setForeground(Color.DARK_GRAY);
		instru.setBounds(108, 5, 105, 15);
		painel.add(instru);

		JButton voltar = new JButton("<");
		voltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pai.setVisible(true);
				dispose();
			}
		});
		voltar.setForeground(Color.DARK_GRAY);
		voltar.setFont(new Font("SansSerif", Font.BOLD, 11));
		voltar.setBackground(Color.LIGHT_GRAY);
		voltar.setBounds(-5, -5, 35, 35);
		painel.add(voltar);
		
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				campoNum.grabFocus();
				campoNum.requestFocus();
			}
		});
		
		
		
	}

	public void registrar()
	{
		if (valorNum == 0)
			JOptionPane.showMessageDialog(null, "O valor precisa ser diferente de ZERO", "Campo zerado", JOptionPane.WARNING_MESSAGE);
		else
		{
			if (att.checaEntrada(valorNum))
			{
				if (att.horaExtra())
				{
					switch (JOptionPane.showOptionDialog(this.painel, "Deseja registrar " + this.valorNum + " " + plural(this.valorNum) + " de tempo extra?", "Confirmação", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, botoes, botoes[0]))
					{
					case 0:
						switch (Relatorios.registrar(att, valorNum))
						{
						case 1:
							JOptionPane.showMessageDialog(null, "Registro bem-sucedido!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
							res.atualizar();
							if (continuar())
							{
								SelecionarAtividade sa = new SelecionarAtividade(att, res);
								sa.setVisible(true);

								dispose();
							}
							else
							{
								Identificacao id = new Identificacao(new Inicio());
								id.setVisible(true);
								res.dispose();
								dispose();
							}
							break;
						case 2:
							JOptionPane.showMessageDialog(null, "Erro no SQL", "Erro", JOptionPane.ERROR_MESSAGE);
							break;
						}
						break;
					case 1:
						JOptionPane.showMessageDialog(null, "O registro não foi feito!", "Operação não concluída", JOptionPane.INFORMATION_MESSAGE);
						break;
					}
				}
				else
				{
					switch (JOptionPane.showOptionDialog(this.painel, "Deseja registrar " + this.valorNum + " " + plural(this.valorNum) + "?", "Confirmação", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, botoes, botoes[0]))
					{
					case 0:
						switch (Relatorios.registrar(att, valorNum))
						{
						case 1:
							JOptionPane.showMessageDialog(null, "Registro bem-sucedido!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
							res.atualizar();
							if (continuar())
							{
								SelecionarAtividade sa = new SelecionarAtividade(att, res);
								sa.setVisible(true);

								dispose();
							}
							else
							{
								Identificacao id = new Identificacao(new Inicio());
								id.setVisible(true);
								res.dispose();
								dispose();
							}
							break;
						case 2:
							JOptionPane.showMessageDialog(null, "Erro no SQL", "Erro", JOptionPane.ERROR_MESSAGE);
							break;
						}
						break;
					case 1:
						JOptionPane.showMessageDialog(null, "O registro não foi feito!", "Operação não concluída", JOptionPane.INFORMATION_MESSAGE);
						break;
					}

				}
			}
			else
				JOptionPane.showMessageDialog(null, "Você está tentando registrar " + (valorNum - att.tempoRestante()) + " " + plural((valorNum - att.tempoRestante())) + " a mais", "Erro", JOptionPane.WARNING_MESSAGE);
		}
	}

	public boolean continuar()
	{
		if (att.ishExtraProgramada() && att.ishExtraRegistro())
		{
			switch (JOptionPane.showOptionDialog(this.painel, "Deseja continuar?", "Continuar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, botoes, botoes[0]))
			{
			case 0:
				return true;
			default:
				switch (JOptionPane.showOptionDialog(this.painel, "Finalizar hora-extra?", "Finalizar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, botoes, botoes[0]))
				{
				case 0:
					Extra.atualizar(att.getCrachaFunc(), true, att.dataCondicionalFormatada());
					return false;
				default:
					return false;
				}
			}
		}
		else
		{
			switch (JOptionPane.showOptionDialog(this.painel, "Deseja continuar?", "Continuar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, botoes, botoes[0]))
			{
			case 0:
				return true;
			default:
				return false;
			}
		}
	}

	public static String plural(int tempo)
	{
		//Correção de plural
		if (tempo == 1)
			return "minuto";
		else
			return "minutos";
	}

	public void mostrar()
	{
		this.setVisible(true);
		this.setAlwaysOnTop(true);
	}
}
