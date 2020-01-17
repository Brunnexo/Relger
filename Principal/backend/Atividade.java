package backend;

public class Atividade {

	private boolean comentado;
	
	private String[] categoria;
	private String[][] subCategoria;
	
	private int selecao;
	
	//COMENTADO
	public boolean isComentado() {
		return comentado;
	}
	public void setComentado(boolean comentado) {
		this.comentado = comentado;
	}

	// CATEGORIA
	public String[] getCategoria() {
		return categoria;
	}
	public void setCategoria(String[] categoria) {
		this.categoria = categoria;
	}

	// SUB-CATEGORIA
	public String[][] getSubCategoria() {
		return subCategoria;
	}
	public void setSubCategoria(String[][] subCategoria) {
		this.subCategoria = subCategoria;
	}

	// SELEÇÃO
	public int getSelecao() {
		return selecao;
	}
	public void setSelecao(int selecao) {
		this.selecao = selecao;
	}
}
