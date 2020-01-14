package janelas.menus;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
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


import janelas.Arrastar;
import janelas.interacao.Senha;
import rede.Conn;
import threads.POI;
import threads.Saida;
public class Inicio extends JFrame {
	//Janela
	static final String VERSAO = "1.0.0";
	
	Inicio inicio = this;
	boolean conectado = false;

	private JPanel painel;
	private JButton gerenciarFunc;
	private JButton atualizarProj;
	private JButton iniciar;
	private JButton gerar;

	int contador = 0;
	private JLabel versao;
	private JLabel creditos;
	private JLabel titulo;
	private JButton sair;

	public Inicio() {
		super("Relger - Controle de Horas");

		//324, 220
		Dimension tamanhoJanela = Metricas.proporcao(0.1692708333333333, 0.2037037037037037);
		Dimension tamanhoBotoes = Metricas.proporcao(0.078125, 0.0462962962962963);
		Dimension tamanhoTitulo = Metricas.proporcao(0.078125, 0.0398148148148148);
		
		ImageIcon icone = new ImageIcon(Inicio.class.getResource("/logos/Joyson.png"));
		Image imagem = icone.getImage().getScaledInstance((int) tamanhoTitulo.getWidth(), (int) tamanhoTitulo.getHeight(), Image.SCALE_SMOOTH);
		
		//Construtor
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setSize(tamanhoJanela);
		painel = new JPanel();
		painel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(painel);
		setLocationRelativeTo(null);
		setUndecorated(true);
		painel.setLayout(null);
		setShape(new RoundRectangle2D.Double(0,0,tamanhoJanela.getWidth(),tamanhoJanela.getHeight(),5,5));
		
		//Gerenciar funcionários
		gerenciarFunc = new JButton("<html><center>GERENCIAR<br/>FUNCION\u00C1RIOS</center></html>");
		atualizarProj = new JButton("<html><center>ATUALIZAR<br/>BANCOS DE DADOS</center></html>");
		iniciar = new JButton("INICIAR");
		titulo = new JLabel();
		creditos = new JLabel("<html><center>Software: Bruno Costa<br />Idealiza\u00E7\u00E3o: Elizabeth Pizol</center></html>");
		sair = new JButton("X");
		gerar = new JButton("GERAR PLANILHA");
		versao = new JLabel(VERSAO);
		
		painel.add(gerenciarFunc);
		painel.add(atualizarProj);
		painel.add(iniciar);
		painel.add(titulo);
		painel.add(creditos);
		painel.add(sair);
		painel.add(gerar);
		painel.add(versao);

		gerenciarFunc.setForeground(Color.DARK_GRAY);
		gerenciarFunc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Senha entrada = new Senha(inicio);
				entrada.mostrar();
			}
		});
		gerenciarFunc.setToolTipText("Registra ou gerencia dados dos funcion\u00E1rios no banco de dados");
		//Atualizar projetos
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

		//Iniciar programa
		iniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Identificacao menu = new Identificacao(inicio);
				menu.setVisible(true);
			}
		});
		iniciar.setForeground(Color.DARK_GRAY);
		iniciar.setToolTipText("Inicia o programa");
		
		//Título
		titulo.setIcon(new ImageIcon(imagem));
		titulo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(contador >= 10)
				{
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
		creditos.setForeground(Color.DARK_GRAY);
		creditos.setHorizontalAlignment(SwingConstants.CENTER);

		//Botão sair
		sair.setForeground(Color.WHITE);
		sair.setBackground(new Color(178, 34, 34));
		sair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				Saida.sair();
			}
		});

		//Gerar Relatórios
		gerar.setActionCommand("<html><center>GERAR PLANILHA</center></html>");
		gerar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GerarRelatorio gerRel = new GerarRelatorio();
				gerRel.setVisible(true);
			}
		});
		gerar.setToolTipText("Inicia o programa");
		gerar.setForeground(Color.DARK_GRAY);
		
		//Versão do Software
		versao.setForeground(Color.DARK_GRAY);
		versao.setBounds(295, 205, 29, 15);

		//Parametrização do Layout Tamanho
		titulo.setSize(tamanhoTitulo);
		
		gerar.setSize(tamanhoBotoes);
		iniciar.setSize(tamanhoBotoes);
		atualizarProj.setSize(tamanhoBotoes);
		gerenciarFunc.setSize(tamanhoBotoes);
		sair.setSize(Metricas.proporcao(0.0182291666666667, 0.0324074074074074));
		creditos.setSize(Metricas.proporcao(0.075, 0.0277777777777778));
		
		//Parametrização do Layout Localização
		titulo.setLocation(Metricas.posicaoRelativa(this.getSize(), 0.2685185185185185, 0.0454545454545455));
		gerar.setLocation(Metricas.posicaoRelativa(this.getSize(), 0.5246913580246914, 0.5454545454545455));
		iniciar.setLocation(Metricas.posicaoRelativa(this.getSize(), 0.5246913580246914, 0.3181818181818182));
		atualizarProj.setLocation(Metricas.posicaoRelativa(this.getSize(), 0.0154320987654321, 0.5454545454545455));
		gerenciarFunc.setLocation(Metricas.posicaoRelativa(this.getSize(), 0.0154320987654321, 0.3181818181818182));
		sair.setLocation((((int) tamanhoJanela.getWidth()) - ((int) sair.getSize().getWidth()) + 5 ),-5);
		creditos.setLocation(Metricas.posicaoRelativa(this.getSize(), 0.2777777777777778, 0.8636363636363636));
		
		//Parametrização do Layout Fontes
		versao.setFont(new Font("Tahoma", Font.PLAIN, Metricas.tamanhoFonte(0.0111111111111111)));
		gerar.setFont(new Font("Tahoma", Font.PLAIN, Metricas.tamanhoFonte(0.0101851851851852)));
		sair.setFont(new Font("Tahoma", Font.PLAIN, Metricas.tamanhoFonte(0.0101851851851852)));
		iniciar.setFont(new Font("Tahoma", Font.PLAIN, Metricas.tamanhoFonte(0.0101851851851852)));
		gerenciarFunc.setFont(new Font("Tahoma", Font.PLAIN, Metricas.tamanhoFonte(0.0101851851851852)));
		atualizarProj.setFont(new Font("Tahoma", Font.PLAIN, Metricas.tamanhoFonte(0.0101851851851852)));
		sair.setFont(new Font("Tahoma", Font.BOLD, Metricas.tamanhoFonte(0.0101851851851852)));
		creditos.setFont(new Font("Tahoma", Font.PLAIN, Metricas.tamanhoFonte(0.0111111111111111)));
		
		Arrastar drag = new Arrastar();
		this.addMouseListener(drag);
		this.addMouseMotionListener(drag);
	}
}
