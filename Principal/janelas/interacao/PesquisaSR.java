package janelas.interacao;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.RoundRectangle2D;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import backend.Atributos;

import janelas.selecao.SR;
import mssql.SRs;

public class PesquisaSR extends JFrame {
	static final int PW = 600, PH = 250;

	Atributos att;

	private JPanel painel;
	public int bt = 0;
	public JTextField inBusca;
	private JLabel desc;
	private JLabel titulo;
	public JButton botao;

	PesquisaSR entrada = this;

	TecladoNumericoPesquisaSR numPad = new TecladoNumericoPesquisaSR(entrada);
	int escolha = 1;

	private final ButtonGroup tipoBusca = new ButtonGroup();
	private JToggleButton chsProjeto;
	private JToggleButton chsWo;

	public PesquisaSR(SR pai) {
		//Atributos do componente chamador
		pai.setVisible(false);
		this.att = pai.att;

		//Atributos do painel
		painel = new JPanel();
		painel.setBorder(new EmptyBorder(5, 5, 5, 5));
		painel.setLayout(null);

		pai.getContentPane().add(painel);
		pai.setEnabled(false);

		pai.addContainerListener(new ContainerAdapter() {
			@Override
			public void componentRemoved(ContainerEvent e) {
				pai.setVisible(true);
				pai.setEnabled(true);
			}
		});


		//Atributos da janela
		setBounds(100, 100, PW, PH);
		setLocationRelativeTo(null);
		setUndecorated(true);
		setShape(new RoundRectangle2D.Double(0,0,PW,PH,5,5));
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setContentPane(painel);

		//Componentes
		inBusca = new JTextField();
		inBusca.setHorizontalAlignment(SwingConstants.CENTER);
		titulo = new JLabel("PESQUISAR S.R.");
		botao = new JButton("Pesquisar".toUpperCase());
		desc = new JLabel("ENTRE COM A S.R. PARA PESQUISAR");
		JButton voltar = new JButton("<");
		JToggleButton teclado = new JToggleButton("\uD83D\uDDA9");
		chsProjeto = new JToggleButton("S.R.");
		chsWo = new JToggleButton("W.O.");

		//Adição
		painel.add(titulo);
		painel.add(desc);
		painel.add(botao);
		painel.add(teclado);
		painel.add(chsProjeto);
		painel.add(chsWo);

		teclado.setHorizontalTextPosition(SwingConstants.CENTER);
		teclado.setIconTextGap(0);
		teclado.setBorderPainted(false);
		teclado.setContentAreaFilled(false);
		teclado.setFocusPainted(false);
		teclado.setMinimumSize(new Dimension(40, 40));
		teclado.setMaximumSize(new Dimension(40, 40));
		teclado.setFont(new Font("SansSerif", Font.PLAIN, 33));
		teclado.setForeground(Color.DARK_GRAY);
		teclado.setBounds(520, 73, 50, 50);
		teclado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				numPad.setVisible(teclado.isSelected());
				
				if (!teclado.isSelected())
				{
					inBusca.grabFocus();
					inBusca.requestFocus();
				}
			}
		});
		painel.add(inBusca);
		painel.add(voltar);

		//Título
		titulo.setBounds(182, 13, 235, 15);
		titulo.setFont(new Font("Tahoma", Font.BOLD, 12));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setForeground(Color.DARK_GRAY);

		//Botão 1
		botao.setBounds(240, 185, 120, 50);
		botao.setForeground(Color.DARK_GRAY);
		botao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				avancar(pai);
			}
		});

		//Descrição
		desc.setForeground(Color.DARK_GRAY);
		desc.setBounds(166, 41, 267, 30);
		desc.setHorizontalAlignment(SwingConstants.CENTER);

		//Campo de Busca
		inBusca.setForeground(Color.DARK_GRAY);
		inBusca.setFont(new Font("Tahoma", Font.BOLD, 18));
		inBusca.setBounds(30, 84, 540, 40);
		inBusca.setColumns(10);
		inBusca.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					avancar(pai);
				}
			}
		});

		//Botão Voltar
		voltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pai.setVisible(true);
				pai.setEnabled(true);
				dispose();
				numPad.dispose();
			}
		});
		voltar.setForeground(Color.DARK_GRAY);
		voltar.setFont(new Font("SansSerif", Font.BOLD, 11));
		voltar.setBackground(Color.LIGHT_GRAY);
		voltar.setBounds(-5, -5, 35, 35);


		tipoBusca.add(chsProjeto);
		chsProjeto.setSelected(true);
		chsProjeto.setForeground(Color.DARK_GRAY);
		chsProjeto.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chsProjeto.setBounds(133, 137, 100, 35);
		chsProjeto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desc.setText("Entre com a S.R. para pesquisar".toUpperCase());
				escolha = 1;
				
				inBusca.grabFocus();
				inBusca.requestFocus();
			}
		});

		tipoBusca.add(chsWo);
		chsWo.setForeground(Color.DARK_GRAY);
		chsWo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chsWo.setBounds(366, 137, 100, 35);
		chsWo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desc.setText("Entre com a W.O. para pesquisar".toUpperCase());
				escolha = 2;
				
				inBusca.grabFocus();
				inBusca.requestFocus();
			}
		});

		
		
		
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				inBusca.grabFocus();
				inBusca.requestFocus();
			}
		});
	}

	public void avancar(SR pai)
	{
		if (inBusca.getText().isEmpty())
			JOptionPane.showMessageDialog(painel, "Faça uma busca", "Campo vazio", JOptionPane.WARNING_MESSAGE);
		else
		{
			numPad.dispose();
			entrada.dispose();
			int contagem = 0;

			try {
				ResultSet rs = SRs.pesquisaSR(inBusca.getText(), escolha);
				while (rs.next())
				{
					contagem++;
				}

				if (contagem < 1)
				{
					JOptionPane.showMessageDialog(painel, "Não há resultados de busca", "Sem resultados", JOptionPane.INFORMATION_MESSAGE);
					pai.listaSRs = SRs.retornar();
					pai.atualizarLista();
				}
				else
				{
					pai.listaSRs = rs;
					pai.atualizarLista();
				}
			} catch (SQLException ex)
			{
				System.out.println("erro");
			}

			pai.setEnabled(true);
			pai.setVisible(true);
			dispose();
			numPad.dispose();
		}
	}
}
