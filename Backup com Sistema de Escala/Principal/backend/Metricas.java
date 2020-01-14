package backend;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

public class Metricas {

	public static Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
	
	final static int ESQUERDA = 0;
	final static int DIREITA = 1;
	final static int CIMA = 2;
	final static int BAIXO = 3;
	
	public static Dimension proporcao(double proporcaoX, double proporcaoY)
	{
		double largura = tela.getWidth() * proporcaoX;
		double altura = tela.getHeight() * proporcaoY;
		
		return new Dimension((int) largura, (int) altura);
	}
	
	public static Dimension proporcao(int largura, int altura)
	{
		//1,777777777777778
		
		double proporcaoX = (double) largura / 1920.0;
		double proporcaoY = (double) altura / 1080.0;
		
		double l = tela.getWidth() * proporcaoX;
		double a = (tela.getWidth() / 1.777777777777778) * proporcaoY;
		
		return new Dimension((int) l, (int) a);
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
	
	public static int tamanhoFonte(int tamanho)
	{
		double proporcao = (double) tamanho / 1080.0;
		
		double retorno = tela.getHeight() * proporcao;
		
		return (int) retorno;
	}
	
}