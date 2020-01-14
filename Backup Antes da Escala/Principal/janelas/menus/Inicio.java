package janelas.menus;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import janelas.interacao.Senha;
import rede.Conn;
import threads.Saida;
import threads.POI;
public class Inicio extends JFrame {
	//Janela
	final static String VERSAO = "1.0.0";
	
	Inicio inicio = this;
	boolean conectado = false;

	private JPanel painel;
	private JButton gerenciarFunc;
	private JButton atualizarProj;
	private JButton iniciar;
	private JButton gerar;

	int contador = 0;

	public Inicio() {
		super("Relger - Controle de Horas");
		
		//Construtor
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 325, 220);
		painel = new JPanel();
		painel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(painel);
		setLocationRelativeTo(null);
		setUndecorated(true);
		painel.setLayout(null);
		setShape(new RoundRectangle2D.Double(0,0,325,220,5,5));

		
		//Gerenciar funcionários
		gerenciarFunc = new JButton("<html><center>GERENCIAR<br/>FUNCION\u00C1RIOS</center></html>");
		painel.add(gerenciarFunc);
		
		gerenciarFunc.setForeground(Color.DARK_GRAY);
		gerenciarFunc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Senha entrada = new Senha(inicio);
				entrada.setVisible(true);
			}
		});
		gerenciarFunc.setToolTipText("Registra ou gerencia dados dos funcion\u00E1rios no banco de dados");
		gerenciarFunc.setBounds(5, 70, 150, 50);

		
		//Atualizar projetos
		atualizarProj = new JButton("<html><center>ATUALIZAR<br/>BANCOS DE DADOS</center></html>");
		painel.add(atualizarProj);
		
		atualizarProj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				POI poi = new POI();
				Thread lerPlanilhas = new Thread()
				{
					public void run()
					{
						poi.executar();
					}
				};
				lerPlanilhas.start();
			}
		});
		atualizarProj.setToolTipText("Atualiza o banco de dados a partir da planilha");
		atualizarProj.setForeground(Color.DARK_GRAY);
		atualizarProj.setBounds(5, 120, 150, 50);

		
		//Iniciar programa
		iniciar = new JButton("INICIAR");
		painel.add(iniciar);
		
		iniciar.setActionCommand("<html><center>INICIAR</center></html>");
		iniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Identificacao menu = new Identificacao(inicio);
				menu.setVisible(true);
			}
		});
		iniciar.setForeground(Color.DARK_GRAY);
		iniciar.setToolTipText("Inicia o programa");
		iniciar.setBounds(170, 70, 150, 50);

		
		//Título
		JLabel titulo = new JLabel("");
		painel.add(titulo);
		
		titulo.setIcon(new ImageIcon(Inicio.class.getResource("/logos/Joyson.png")));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setForeground(Color.DARK_GRAY);
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 22));
		titulo.setBounds(87, 10, 150, 43);
		titulo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(contador >= 10)
				{
					contador = 0;
					try {
						String sql = "DBCC CHECKIDENT ('[RELATORIOS]', reseed, 0)";
						Statement st = Conn.connection.createStatement();
						st.execute(sql);
						JOptionPane.showMessageDialog(null, "A ID do banco de dados foi reiniciada", "ID reiniciada", JOptionPane.INFORMATION_MESSAGE);
					} catch (SQLException ex) {
						ex.printStackTrace();
						JOptionPane.showMessageDialog(null, "Falha ao reiniciar ID do banco de dados", "Falha", JOptionPane.ERROR_MESSAGE);
					}
				}
				else
				{
					System.out.println("Reiniciando ID do banco de dados em " + (10 - contador) + " cliques...");
					contador++;
				}
			}
		});

		//Créditos
		JLabel creditos = new JLabel("<html><center>Software: Bruno Costa<br />Idealiza\u00E7\u00E3o: Elizabeth Pizol</center></html>");
		painel.add(creditos);
		
		creditos.setForeground(Color.DARK_GRAY);
		creditos.setHorizontalAlignment(SwingConstants.CENTER);
		creditos.setFont(new Font("Tahoma", Font.PLAIN, 12));
		creditos.setBounds(90, 190, 144, 30);

		//Botão sair
		JButton sair = new JButton("X");
		painel.add(sair);
		
		sair.setForeground(Color.WHITE);
		sair.setBackground(new Color(178, 34, 34));
		sair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				Saida.sair();
			}
		});
		sair.setFont(new Font("SansSerif", Font.BOLD, 10));
		sair.setBounds(295, -5, 35, 35);

		//Gerar Relatórios
		gerar = new JButton("GERAR PLANILHA");
		painel.add(gerar);
		
		gerar.setActionCommand("<html><center>GERAR PLANILHA</center></html>");
		gerar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GerarRelatorio gerRel = new GerarRelatorio();
				gerRel.setVisible(true);
			}
		});
		gerar.setToolTipText("Inicia o programa");
		gerar.setForeground(Color.DARK_GRAY);
		gerar.setBounds(170, 120, 150, 50);
		
		//Versão do Software
		JLabel versao = new JLabel(VERSAO);
		painel.add(versao);
		
		versao.setForeground(Color.DARK_GRAY);
		versao.setFont(new Font("Tahoma", Font.PLAIN, 12));
		versao.setBounds(295, 205, 29, 15);
	}
}
