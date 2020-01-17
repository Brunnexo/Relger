package backend;

public class Colaborador {

	private int cracha;
	private String nome;
	private char tipo;
	private int[] funcoes;
	
	
	public Colaborador() {
		
	}
	
	// CRACHA
	public void setCracha(int cracha) {
		this.cracha = cracha;
	}
	public int getCracha() {
		return this.cracha;
	}
	
	// NOME
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getNome() {
		return this.nome;
	}
	
	// TIPO
	public void setTipo(char tipo) {
		this.tipo = Character.toUpperCase(tipo);
	}
	public char getTipo() {
		return this.tipo;
	}
	
	// FUNÇÕES
	public void setFuncoes(int[] funcoes) {
		this.funcoes = funcoes;
	}
	public int[] getFuncoes() {
		return this.funcoes;
	}
}
