package backend;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.RoundRectangle2D;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import janelas.Notificacao;
import janelas.PendenciasNotificacao;
import janelas.Progresso;
import janelas.interacao.Resumo;

public class Metricas {

	static Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
	static Dimension telaReferencia = new Dimension(1920, 1080);

	static double proporcaoX = tela.getWidth() / telaReferencia.getWidth();
	static double proporcaoY = ((tela.getWidth() / 1.777777777777778) / telaReferencia.getHeight());

	JFrame tempPai;
	Dimension dimensaoPai;

	JPanel tempPanel;

	JLabel tempLabel;

	JButton tempButton;

	Component[] lista;

	/**
	 * @param pai
	 * JFrame da janela (objeto raíz)
	 * @param lista
	 * Array de componentes do contentPane definido
	 */
	public Metricas(JFrame pai, JPanel painel, Component[] lista)
	{
		this.lista = lista;
		this.tempPai = pai;
		this.tempPanel = painel;
		this.dimensaoPai = tempPai.getSize();
	}

	//Proporcao relacionada a janela, nao à area de trabalho

	public static RoundRectangle2D.Double arredondarCantos(int largura, int altura, int raio)
	{
		return new RoundRectangle2D.Double(0, 0, larguraProporcional(largura), alturaProporcional(altura), raio, raio);
	}

	public static int larguraProporcional(int largura)
	{
		Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension telaReferencia = new Dimension(1920, 1080);

		double proporcaoX = tela.getWidth() / telaReferencia.getWidth();

		int larguraNova = (int) ((double) largura * proporcaoX);

		return larguraNova;
	}

	public static int alturaProporcional(int altura)
	{
		Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension telaReferencia = new Dimension(1920, 1080);

		double proporcaoY = ((tela.getWidth() / 1.777777777777778) / telaReferencia.getHeight());

		int larguraNova = (int) ((double) altura * proporcaoY);

		return larguraNova;
	}

	public static Rectangle boundProporcional(int largura, int altura)
	{
		Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension telaReferencia = new Dimension(1920, 1080);

		double proporcaoX = tela.getWidth() / telaReferencia.getWidth();
		double proporcaoY = ((tela.getWidth() / 1.777777777777778) / telaReferencia.getHeight());

		int larguraNova = (int) ((double) largura * proporcaoX);
		int alturaNova = (int) ((double) altura * proporcaoY);

		return new Rectangle(larguraNova, alturaNova);
	}

	public static Rectangle boundProporcional(Rectangle r)
	{
		Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension telaReferencia = new Dimension(1920, 1080);

		double proporcaoX = tela.getWidth() / telaReferencia.getWidth();
		double proporcaoY = ((tela.getWidth() / 1.777777777777778) / telaReferencia.getHeight());

		int larguraNova = (int) ((double) r.width * proporcaoX);
		int alturaNova = (int) ((double) r.height * proporcaoY);

		return new Rectangle(larguraNova, alturaNova);
	}

	public void escalarComponentes()
	{
		tempPai.setSize(new Dimension((int) (dimensaoPai.getWidth() * proporcaoX), (int) (dimensaoPai.getHeight() * proporcaoY)));
		tempPai.setShape(new RoundRectangle2D.Double(0,0,tempPai.getSize().width,tempPai.getSize().height,5,5));

		if (!(
				tempPai instanceof Progresso ||
				tempPai instanceof PendenciasNotificacao ||
				tempPai instanceof Notificacao ||
				tempPai instanceof Resumo))
			tempPai.setLocationRelativeTo(null);

		for (int i = 0; i < this.lista.length; i++)
		{
			if (this.lista[i] instanceof JLabel)
			{
				tempLabel = (JLabel) this.lista[i];

				if (tempLabel.getIcon() != null)
				{
					double tamanhoTexto = tempLabel.getFont().getSize2D();
					String fonteOriginal = tempLabel.getFont().getFamily();
					int estiloFonte = tempLabel.getFont().getStyle();

					double tamanhoOriginalW = tempLabel.getSize().getWidth();
					double tamanhoOriginalH = tempLabel.getSize().getHeight();

					double localRelativoX = tempLabel.getLocation().getX() / this.dimensaoPai.getWidth();
					double localRelativoY = tempLabel.getLocation().getY() / this.dimensaoPai.getHeight();

					ImageIcon icone = (ImageIcon) tempLabel.getIcon();
					Image imagem = icone.getImage().getScaledInstance((int) (tamanhoOriginalW * proporcaoX), (int) (tamanhoOriginalH * (tela.getHeight() / telaReferencia.getHeight())), Image.SCALE_SMOOTH);

					tempLabel.setFont(new Font(fonteOriginal, estiloFonte, (int)(tamanhoTexto * (tela.getHeight() / telaReferencia.getHeight()))));
					tempLabel.setIcon(new ImageIcon(imagem));
					tempLabel.setSize(new Dimension((int)(tamanhoOriginalW * proporcaoX), (int)(tamanhoOriginalH * proporcaoY)));
					tempLabel.setLocation(new Point((int)(tempPai.getSize().getWidth() * localRelativoX), (int)(tempPai.getSize().getHeight() * localRelativoY)));
				}
				else
				{
					double tamanhoTexto = tempLabel.getFont().getSize2D();
					String fonteOriginal = tempLabel.getFont().getFamily();
					int estiloFonte = tempLabel.getFont().getStyle();

					double tamanhoOriginalW = tempLabel.getSize().getWidth();
					double tamanhoOriginalH = tempLabel.getSize().getHeight();

					double localRelativoX = tempLabel.getLocation().getX() / this.dimensaoPai.getWidth();
					double localRelativoY = tempLabel.getLocation().getY() / this.dimensaoPai.getHeight();

					tempLabel.setFont(new Font(fonteOriginal, estiloFonte, (int)(tamanhoTexto * (tela.getHeight() / telaReferencia.getHeight()))));
					tempLabel.setSize(new Dimension((int)(tamanhoOriginalW * proporcaoX), (int)(tamanhoOriginalH * proporcaoY)));
					tempLabel.setLocation(new Point((int)(tempPai.getSize().getWidth() * localRelativoX), (int)(tempPai.getSize().getHeight() * localRelativoY)));
				}
			}
			else if (this.lista[i] instanceof JButton)
			{
				tempButton = (JButton) this.lista[i];

				if (tempButton.getIcon() != null)
				{
					double tamanhoTexto = tempButton.getFont().getSize2D();
					String fonteOriginal = tempButton.getFont().getFamily();
					int estiloFonte = tempButton.getFont().getStyle();

					double tamanhoOriginalW = tempButton.getSize().getWidth();
					double tamanhoOriginalH = tempButton.getSize().getHeight();

					ImageIcon icone = (ImageIcon) tempButton.getIcon();
					Image imagem = icone.getImage().getScaledInstance((int) (tamanhoOriginalW * proporcaoX), (int) (tamanhoOriginalH * (tela.getHeight() / telaReferencia.getHeight())), Image.SCALE_SMOOTH);

					
					double localRelativoX = tempButton.getLocation().getX() / this.dimensaoPai.getWidth();
					double localRelativoY = tempButton.getLocation().getY() / this.dimensaoPai.getHeight();

					tempButton.setIcon(new ImageIcon(imagem));
					tempButton.setFont(new Font(fonteOriginal, estiloFonte, (int)(tamanhoTexto * (tela.getHeight() / telaReferencia.getHeight()))));
					tempButton.setSize(new Dimension((int)(tamanhoOriginalW * proporcaoX), (int)(tamanhoOriginalH * proporcaoY)));
					tempButton.setLocation(new Point((int)(tempPai.getSize().getWidth() * localRelativoX), (int)(tempPai.getSize().getHeight() * localRelativoY)));
				}
				else
				{
					double tamanhoTexto = tempButton.getFont().getSize2D();
					String fonteOriginal = tempButton.getFont().getFamily();
					int estiloFonte = tempButton.getFont().getStyle();


					double tamanhoOriginalW = tempButton.getSize().getWidth();
					double tamanhoOriginalH = tempButton.getSize().getHeight();

					double localRelativoX = tempButton.getLocation().getX() / this.dimensaoPai.getWidth();
					double localRelativoY = tempButton.getLocation().getY() / this.dimensaoPai.getHeight();

					tempButton.setFont(new Font(fonteOriginal, estiloFonte, (int)(tamanhoTexto * (tela.getHeight() / telaReferencia.getHeight()))));
					tempButton.setSize(new Dimension((int)(tamanhoOriginalW * proporcaoX), (int)(tamanhoOriginalH * proporcaoY)));
					tempButton.setLocation(new Point((int)(tempPai.getSize().getWidth() * localRelativoX), (int)(tempPai.getSize().getHeight() * localRelativoY)));
				}
			}
			else
			{
				JComponent componente = (JComponent) this.lista[i];

				double tamanhoTexto = componente.getFont().getSize2D();
				String fonteOriginal = componente.getFont().getFamily();
				int estiloFonte = componente.getFont().getStyle();


				double tamanhoOriginalW = componente.getSize().getWidth();
				double tamanhoOriginalH = componente.getSize().getHeight();

				double localRelativoX = componente.getLocation().getX() / this.dimensaoPai.getWidth();
				double localRelativoY = componente.getLocation().getY() / this.dimensaoPai.getHeight();

				componente.setFont(new Font(fonteOriginal, estiloFonte, (int)(tamanhoTexto * proporcaoY)));

				componente.setSize(new Dimension((int)(tamanhoOriginalW * proporcaoX), (int)(tamanhoOriginalH * proporcaoY)));

				componente.setLocation(new Point((int)(tempPai.getSize().getWidth() * localRelativoX), (int)(tempPai.getSize().getHeight() * localRelativoY)));
			}
		}
	}

	public static Dimension proporcao(double proporcaoX, double proporcaoY)
	{
		double largura = tela.getWidth() * proporcaoX;
		double altura = tela.getHeight() * proporcaoY;

		return new Dimension((int) largura, (int) altura);
	}

	public static Point posicaoRelativa(Dimension dimensao, double proporcaoX, double proporcaoY)
	{
		int localX = (int) ((int) dimensao.getWidth() * proporcaoX);
		int localY = (int) ((int) dimensao.getHeight() * proporcaoY);

		return new Point(localX, localY);
	}

	public static int tamanhoFonte(double proporcao)
	{
		double tamanho = tela.getHeight() * proporcao;
		return (int) tamanho;
	}


}