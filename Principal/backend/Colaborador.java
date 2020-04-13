package backend;

import java.sql.ResultSet;
import java.sql.SQLException;

import mssql.Funcionarios;

public class Colaborador {

	public Atividade atividade;
	
	private int cracha = 0;
	private String nome;
	private char tipo;
	private boolean[] funcoes;
	
<<<<<<< Updated upstream
	private int tempoTrabalhado;
	private int tempoExtraTrabalhado;
	
	private int tempoMaximoPermitido;

	public Colaborador() {
	}

	/**
	 * @param Crachá do colaborador</br>
	 * Define as características básicas do colaborador</br>
	 * a partir da pesquisa pelo crachá, como <b>nome</b>,</br>
	 * função e tipo de serviço
	 */
	public Colaborador(int cracha) {
		ResultSet resultado = Funcionarios.pesquisarCracha(cracha);
		try {
			while (resultado.next()) {
				this.cracha = resultado.getInt("CRACHA");
				this.nome = resultado.getString("NOME");
				this.funcoes = new boolean[] {
						resultado.getBoolean("ELE"),
						resultado.getBoolean("MEC"),
						resultado.getBoolean("PROJ"),
						resultado.getBoolean("PROG"),
						resultado.getBoolean("ENG"),
						resultado.getBoolean("ADM")
				};

				if (resultado.getBoolean("HORISTA"))
					this.tipo = 'H';
				else if (resultado.getBoolean("MENSALISTA"))
					this.tipo = 'M';

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.atividade = new Atividade();
	}
=======
>>>>>>> Stashed changes
	// CRACHA
	/**
	 * @param Crachá do colaborador
	 */
	public void setCracha(int cracha) {
		this.cracha = cracha;
	}
	/**
	 * @return Crachá do colaborador
	 */
	public int getCracha() {
		return this.cracha;
	}

	// NOME
	/**
	 * @param Nome do colaborador
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	/**
	 * @return Nome do colaborador
	 */
	public String getNome() {
		return this.nome;
	}

	// TIPO
	/**
	 * @param Tipo de colaborador:</br>
	 * <b>H</b> se for <b>horista</b></br>
	 * <b>M</b> se for <b>mensalista</b>
	 */
	public void setTipo(char tipo) {
		this.tipo = Character.toUpperCase(tipo);
	}
	
	/**
	 * @return Tipo de colaborador:</br>
	 * <b>H</b> se for <b>horista</b></br>
	 * <b>M</b> se for <b>mensalista</b>
	 */
	public char getTipo() {
		return this.tipo;
	}

	// FUNÇÕES
	/**
	 * @param Cadeia de funções habilitadas para o colaborador</br>
	 * (eletricista, mecânico, projetista, programador, engenheiro, administrativo) </br></br>
	 * Exemplo:</br>
	 * Se o colaborador for <b>mecânico</b> e <b>engenheiro</b>:</br>
	 * {<false>false</false>, <true>true</true>, <false>false</false>, <false>false</false>, <true>true</true>, <false>false</false>}
	 */
	public void setFuncoes(boolean[] funcoes) {
		this.funcoes = funcoes;
	}
	/**
	 * @return Cadeia de funções habilitadas para o colaborador</br>
	 * (eletricista, mecânico, projetista, programador, engenheiro, administrativo) </br></br>
	 * Exemplo:</br>
	 * Se o colaborador for <b>mecânico</b> e <b>engenheiro</b>:</br>
	 * {<false>false</false>, <true>true</true>, <false>false</false>, <false>false</false>, <true>true</true>, <false>false</false>}
	 */
	public boolean[] getFuncoes() {
		return this.funcoes;
	}

	public int getTempoTrabalhado() {
		return tempoTrabalhado;
	}
}