package backend;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjusters;

import mssql.Extra;

public class Atributos
{
	LocalDate ld = WebAPI.dataAtual();
	LocalDateTime ldt = WebAPI.horaAtual();
	LocalDate ldCondicional = LocalDate.parse(this.dataCondicionalFormatada());

	private String dataOvr;

	final static int TEMPO_BANCO_HORAS = 78;
	final static int TEMPO_HORA_EXTRA_DIARIA = 60;

	//Atributos do funcionário
	private String nomeFunc = new String();

	private int crachaFunc;

	private boolean ele, mec, prog, eng, proj, adm;

	private boolean horista, mensalista;
	//Atributos do funcionário

	private boolean override, dataOverride;
	private boolean hExtraMensalista;


	//Atributos do Projeto
	/*
	 * General Motors:	1
	 * Volkswagen:		2
	 * Ford:			3
	 * FCA:				4
	 * Renault:			5
	 * Honda:			6
	 * Nissan:			7
	 * Toyota:			8
	 * Hyundai:			9
	 * Mercedes:		10
	 * PSA:				11
	 * MAN:				12
	 ********************/

	private int montadora;

	//Atributos do Projeto
	private String wo = new String();

	//Atributos do trabalho

	private String descTrabalho;

	private boolean hExtraProgramada, hExtraRegistro = true;

	private boolean hExtra, bHoras;

	private int tempoTrabalhado = 0, tempoExtraTrabalhado = 0;

	//Atributos do trabalho
	//GETTERS E SETTERS
	public boolean ishExtraMensalista() {
		return hExtraMensalista;
	}
	public void sethExtraMensalista(boolean hExtraMensalista) {
		this.hExtraMensalista = hExtraMensalista;
	}
	public boolean isDataOverride() {
		return dataOverride;
	}
	public void setDataOverride(boolean dataOverride) {
		this.dataOverride = dataOverride;
	}
	public boolean isOverride() {
		return override;
	}
	public void setOverride(boolean override) {
		this.override = override;
	}
	public String getDataOvr() {
		return dataOvr;
	}
	public void setDataOvr(String dataOvr) {
		this.dataOvr = dataOvr;
	}
	public void setDataOvr(LocalDate dataOvr) {
		this.dataOvr = dataOvr.toString();
	}
	public int getTempoTrabalhado() {
		return tempoTrabalhado;
	}
	public void setTempoTrabalhado(int tempoTrabalhado) {
		this.tempoTrabalhado = tempoTrabalhado;
	}
	public int getTempoExtraTrabalhado() {
		return tempoExtraTrabalhado;
	}
	public void setTempoExtraTrabalhado(int tempoExtraTrabalhado) {
		this.tempoExtraTrabalhado = tempoExtraTrabalhado;
	}
	public boolean ishExtraRegistro() {
		return hExtraRegistro;
	}
	public void sethExtraRegistro(boolean hExtraRegistro) {
		this.hExtraRegistro = hExtraRegistro;
	}
	public boolean ishExtraProgramada() {
		return hExtraProgramada;
	}
	public void sethExtraProgramada(boolean hExtraProgramada) {
		this.hExtraProgramada = hExtraProgramada;
	}	
	public boolean isbHoras() {
		return bHoras;
	}
	public void setbHoras(boolean bHoras) {
		this.bHoras = bHoras;
	}
	public boolean ishExtra() {
		return hExtra;
	}
	public void sethExtra(boolean hExtra) {
		this.hExtra = hExtra;
	}
	public String getWo() {
		return wo;
	}
	public void setWo(String os) {
		this.wo = os;
	}
	public int getMontadora() {
		return montadora;
	}
	public void setMontadora(int montadora) {
		this.montadora = montadora;
	}
	public String getNomeFunc() {
		return nomeFunc;
	}
	public void setNomeFunc(String nomeFunc) {
		this.nomeFunc = nomeFunc;
	}
	public int getCrachaFunc() {
		return crachaFunc;
	}
	public void setCrachaFunc(int crachaFunc) {
		this.crachaFunc = crachaFunc;
	}
	public boolean isEle() {
		return ele;
	}
	public void setEle(boolean ele) {
		this.ele = ele;
	}
	public boolean isMec() {
		return mec;
	}
	public void setMec(boolean mec) {
		this.mec = mec;
	}
	public boolean isProg() {
		return prog;
	}
	public void setProg(boolean prog) {
		this.prog = prog;
	}
	public boolean isEng() {
		return eng;
	}
	public void setEng(boolean eng) {
		this.eng = eng;
	}
	public boolean isProj() {
		return proj;
	}
	public void setProj(boolean proj) {
		this.proj = proj;
	}
	public boolean isAdm() {
		return adm;
	}
	public void setAdm(boolean adm) {
		this.adm = adm;
	}
	public boolean isHorista() {
		return horista;
	}
	public void setHorista(boolean horista) {
		this.horista = horista;
	}
	public boolean isMensalista() {
		return mensalista;
	}
	public void setMensalista(boolean mensalista) {
		this.mensalista = mensalista;
	}

	public void escreveValores(ResultSet rs)
	{
		try {
			while (rs.next())
			{
				this.crachaFunc = rs.getInt("CRACHA");
				this.nomeFunc = rs.getString("NOME");
				this.ele = rs.getBoolean("ELE");
				this.mec = rs.getBoolean("MEC");
				this.prog = rs.getBoolean("PROG");
				this.eng = rs.getBoolean("ENG");
				this.proj = rs.getBoolean("PROJ");
				this.adm = rs.getBoolean("ADM");
				this.horista = rs.getBoolean("HORISTA");
				this.mensalista = rs.getBoolean("MENSALISTA");
			}
		} catch (SQLException ex) {System.out.println(ex.getMessage());}
	}
	public void voltar_projetos()
	{
		this.montadora = 0;
	}

	public String getNomeFuncao()
	{
		if (this.tAdm)
			return "Administrativo";
		else if (this.tEle)
			return "Eletricista";
		else if (this.tEng)
			return "Engenheiro";
		else if (this.tMec)
			return "Mecânico";
		else if (this.tProg)
			return "Programador";
		else if (this.tProj)
			return "Projetista";
		else
			return "S.R.";
	}


	/**
	 *	Retorna o argumento de pesquisa
	 *	da WO de acordo com a função
	 *	selecionada 
	 */
	public String getWoFunc()
	{
		if (this.istAdm())
			return "WOADM";
		else if (this.istEle())
			return "WOELETRICISTA";
		else if (this.istMec())
			return "WOMECANICO";
		else if (this.istEng())
			return "WOENGENHEIRO";
		else if (this.istProj())
			return "WOPROJETISTA";
		else if (this.istProg())
			return "WOPROGRAMADOR";
		return "";
	}

	public String getNomeMontadora()
	{
		if (this.montadora == 1)
			return "GM";
		else if (this.montadora == 2)
			return "VW";
		else if (this.montadora == 3)
			return "Ford";
		else if (this.montadora == 4)
			return "FCA";
		else if (this.montadora == 5)
			return "Renault";
		else if (this.montadora == 6)
			return "Honda";
		else if (this.montadora == 7)
			return "Nissan";
		else if (this.montadora == 8)
			return "Toyota";
		else if (this.montadora == 9)
			return "Hyundai";
		else if (this.montadora == 10)
			return "Mercedes";
		else if (this.montadora == 11)
			return "PSA";
		else if (this.montadora == 12)
			return "MAN";
		else
			return "-";
	}

	public boolean trabGeral()
	{
		if (this.getDescTrabalho().contains("TESTES E PROTÓTIPOS") ||
				this.getDescTrabalho().contains("MANUTENÇÃO") ||
				this.getDescTrabalho().contains("ALMOXARIFADO") ||
				this.getDescTrabalho().contains("5S") ||
				this.getDescTrabalho().contains("NÃO PRODUTIVOS") ||
				this.getDescTrabalho().contains("AUSÊNCIA") ||
				this.getDescTrabalho().contains("RETRABALHO") ||
				this.getDescTrabalho().contains("MELHORIA") ||
				this.getDescTrabalho().contains("DOCUMENT") ||
				this.getDescTrabalho().contains("ORÇAMENTO") ||
				this.getDescTrabalho().contains("GESTÃO DE") ||
				this.getDescTrabalho().contains("ACOMPANHAMENTO") ||
				this.getDescTrabalho().contains("FOLLOW UP") ||
				this.getDescTrabalho().contains("CONTROLE DE COMPRAS") ||
				this.getDescTrabalho().contains("CONTROLE DE DEMANDA") ||
				this.getDescTrabalho().contains("CONTROLE DE HORAS") ||
				this.getDescTrabalho().contains("CONTROLE"))
			return true;
		else
			return false;
	}

	public String getDescTrabalho() {
		return descTrabalho;
	}

	public void setDescTrabalho(String servico) {
		this.descTrabalho = servico;
	}

	//Funcionamento de data
	public String dataCondicional()
	{
		String dataResult = new String();

		//Define se há registro de hora extra agendada no crachá
		try {
			ResultSet rs = Extra.retornaAbertos(this.crachaFunc);
			while (rs.next())
			{
				dataResult = rs.getString("DATA");
				this.hExtraProgramada = !rs.getBoolean("FINALIZADO");
			}
		} catch (SQLException ex) {}	


		String retorno = new String();
		String[] retornoParse = new String[3];

		if (this.dataOverride)
		{
			String[] dataParse = this.dataOvr.split("-");
			return dataParse[2] + "/" + dataParse[1] + "/" + dataParse[0];
		}
		else
		{
			if (this.hExtraProgramada && this.hExtraRegistro)
				retornoParse = dataResult.split("-");
			else
			{
				if (this.ldt.getHour() <= 12)
				{
					if(this.ld.getDayOfWeek() == DayOfWeek.MONDAY) //Retorna a ultima sexta
						retorno = this.ld.with(TemporalAdjusters.previous(DayOfWeek.FRIDAY)).toString();
					else
						retorno = this.ld.minusDays(1).toString();
				}
				else
					retorno = this.ld.toString();

				retornoParse = retorno.split("-");
			}
		}
		return retornoParse[2] + "/" + retornoParse[1] + "/" + retornoParse[0];
	}

	public String dataCondicionalFormatada()
	{
		String dataResult = new String();

		//Define se há registro de hora extra agendada no crachá
		try {
			ResultSet rs = Extra.retornaAbertos(this.crachaFunc);
			while (rs.next())
			{
				dataResult = rs.getString("DATA");
				this.hExtraProgramada = !rs.getBoolean("FINALIZADO");
			}
		} catch (SQLException ex) {}

		LocalDate dataHExtra;

		try {
			dataHExtra = LocalDate.parse(dataResult);
		} catch (DateTimeParseException ex)
		{
			dataHExtra = this.ld;
		}

		if (this.dataOverride)
		{
			ldCondicional = LocalDate.parse(dataOvr);
			return this.dataOvr;
		}
		else
		{
			if (this.hExtraProgramada && this.hExtraRegistro && (this.ld.compareTo(dataHExtra) > 0))
				return dataResult;
			else
			{
				if (this.ldt.getHour() <= 12)
				{
					if(this.ld.getDayOfWeek() == DayOfWeek.MONDAY) //Retorna a ultima sexta
						return this.ld.with(TemporalAdjusters.previous(DayOfWeek.FRIDAY)).toString();
					else
						return this.ld.minusDays(1).toString();
				}
				else
					return this.ld.toString();
			}
		}
		
		
	}

	public static String ultimoDiaUtil()
	{
		//Define se há registro de hora extra agendada no crachá
		LocalDate dataHoje = WebAPI.dataAtual();

		if(dataHoje.getDayOfWeek() == DayOfWeek.MONDAY) //Retorna a ultima sexta
			return dataHoje.with(TemporalAdjusters.previous(DayOfWeek.FRIDAY)).toString();
		else
			return dataHoje.minusDays(1).toString();
	}

	//Checks

	/** @return <code>true</code> se a data condicional não for final de semana */
	public boolean tempoNormal()
	{
		boolean fimDeSemana = (ld.getDayOfWeek() == DayOfWeek.SATURDAY || ld.getDayOfWeek() == DayOfWeek.SUNDAY);

		if (fimDeSemana)
			return false;
		else
			return true;
	}

	
	public boolean fimDeSemana()
	{
		return (ld.getDayOfWeek() == DayOfWeek.SATURDAY || ld.getDayOfWeek() == DayOfWeek.SUNDAY);
	}

	public boolean bancoHoras()
	{
		this.bHoras = (this.isMensalista() && (this.tempoTrabalhado >= tempo()));
		return this.bHoras;
	}

	public boolean checaEntrada(int tempo)
	{
		if (this.isMensalista())
		{
			if (tempo <= this.tempoRestante())
				return true;
			else if ((tempo > this.tempoRestante()) && !this.bancoHoras() && (tempo - this.tempoRestante()) <= TEMPO_BANCO_HORAS)
				return true;
			else
				return false;
		}
		else
		{
			if (tempo <=  this.tempoRestante())
				return true;
			else
				return false;
		}
	}
}