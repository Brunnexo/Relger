package backend;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

public class Metricas {

	static Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
	
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