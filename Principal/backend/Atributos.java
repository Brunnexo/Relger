package backend;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjusters;
import java.util.GregorianCalendar;

import mssql.Extra;

public class Atributos
{
	LocalDate ld = LocalDate.parse(dataCondicionalFormatada());

	final static int TEMPO_BANCO_HORAS = 78;
	final static int TEMPO_HORA_EXTRA_DIARIA = 60;

	//Atributos do funcionário
	private String nomeFunc = new String();

	private int crachaFunc;

	private boolean ele, mec, prog, eng, proj, adm;

	private boolean horista, mensalista;
	//Atributos do funcionário

	private boolean override;
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
	private boolean tEle, tMec, tProg, tEng, tProj, tAdm;

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
	public boolean isOverride() {
		return override;
	}
	public void setOverride(boolean override) {
		this.override = override;
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
	public boolean istEle() {
		return tEle;
	}
	public void settEle(boolean tEle) {
		this.tEle = tEle;
	}
	public boolean istMec() {
		return tMec;
	}
	public void settMec(boolean tMec) {
		this.tMec = tMec;
	}
	public boolean istProg() {
		return tProg;
	}
	public void settProg(boolean tProg) {
		this.tProg = tProg;
	}
	public boolean istEng() {
		return tEng;
	}
	public void settEng(boolean tEng) {
		this.tEng = tEng;
	}
	public boolean istProj() {
		return tProj;
	}
	public void settProj(boolean tProj) {
		this.tProj = tProj;
	}
	public boolean istAdm() {
		return tAdm;
	}
	public void settAdm(boolean tAdm) {
		this.tAdm = tAdm;
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

	//Relacionamento entre janelas
	public void voltar_funcoes()
	{
		this.tAdm = false;
		this.tEle = false;
		this.tEng = false;
		this.tMec = false;
		this.tProj = false;
		this.tProg = false;
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
			return "N/A";
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


		LocalDate data = LocalDate.now(); 
		GregorianCalendar gc = new GregorianCalendar();


		String retorno = new String();
		String[] retornoParse = new String[3];

		if (this.hExtraProgramada && this.hExtraRegistro)
		{
			retornoParse = dataResult.split("-");
		}
		else
		{
			if (gc.get(GregorianCalendar.HOUR_OF_DAY) <= 12)
			{
				if(data.getDayOfWeek() == DayOfWeek.MONDAY) //Retorna a ultima sexta
					retorno = data.with(TemporalAdjusters.previous(DayOfWeek.FRIDAY)).toString();
				else
					retorno = data.minusDays(1).toString();
			}
			else
				retorno = data.toString();

			retornoParse = retorno.split("-");

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

		LocalDate dataHoje = LocalDate.now();
		LocalDate dataHExtra;

		try {
			dataHExtra = LocalDate.parse(dataResult);
		} catch (DateTimeParseException ex)
		{
			dataHExtra = dataHoje;
		}

		GregorianCalendar gc = new GregorianCalendar();

		if (this.hExtraProgramada && this.hExtraRegistro && (dataHoje.compareTo(dataHExtra) > 0))
		{
			return dataResult;
		}
		else
		{
			if (gc.get(GregorianCalendar.HOUR_OF_DAY) <= 12)
			{
				if(dataHoje.getDayOfWeek() == DayOfWeek.MONDAY) //Retorna a ultima sexta
					return dataHoje.with(TemporalAdjusters.previous(DayOfWeek.FRIDAY)).toString();
				else
					return dataHoje.minusDays(1).toString();
			}
			else
			{
				return dataHoje.toString();
			}
		}
	}

	public static String ultimoDiaUtil()
	{
		//Define se há registro de hora extra agendada no crachá
		LocalDate dataHoje = LocalDate.now();

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

	public boolean horaExtra()
	{
		boolean fimDeSemana = (ld.getDayOfWeek() == DayOfWeek.SATURDAY || ld.getDayOfWeek() == DayOfWeek.SUNDAY);
		boolean sextaFeira = (ld.getDayOfWeek() == DayOfWeek.FRIDAY);

		if (this.override | this.hExtraMensalista | (this.hExtraProgramada && this.hExtraRegistro))
			this.hExtra = true;
		else if (fimDeSemana)
			this.hExtra = true;
		else if (this.isHorista() && this.tempoTrabalhado >= this.tempo())
			this.hExtra = true;
		else if (sextaFeira && (this.isMensalista() && this.tempoTrabalhado >= (this.tempo() + TEMPO_BANCO_HORAS + 60)))
			this.hExtra = true;
		else if (!sextaFeira && this.isMensalista() && this.tempoTrabalhado >= (this.tempo() + TEMPO_BANCO_HORAS))
			this.hExtra = true;
		else
			this.hExtra = false;

		return this.hExtra;
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
		if (tempo <=  this.tempoRestante())
			return true;
		else
			return false;
	}

	public int tempoRestante()
	{
		boolean fimDeSemana = (ld.getDayOfWeek() == DayOfWeek.SATURDAY || ld.getDayOfWeek() == DayOfWeek.SUNDAY);
		boolean sextaFeira = (ld.getDayOfWeek() == DayOfWeek.FRIDAY);

		int tempoRestante = 0;

		if (this.override)
			tempoRestante = 999;

		else if (fimDeSemana)
			tempoRestante = (this.tempo() - this.tempoExtraTrabalhado);

		else if (!(this.hExtraProgramada && this.hExtraRegistro) && this.horaExtra())
			tempoRestante = (TEMPO_HORA_EXTRA_DIARIA - this.tempoExtraTrabalhado);

		else if (this.hExtraProgramada && this.hExtraRegistro)
			tempoRestante = (tempo() - this.tempoExtraTrabalhado);

		else if (sextaFeira && this.isMensalista() && (this.tempoTrabalhado >= this.tempo()) && (this.tempoTrabalhado <= (this.tempo() + TEMPO_BANCO_HORAS + 60)))
			tempoRestante = ((this.tempo() + TEMPO_BANCO_HORAS + 60) - this.tempoTrabalhado);

		else if (!sextaFeira && this.isMensalista() && (this.tempoTrabalhado >= (this.tempo() + TEMPO_BANCO_HORAS)))
			tempoRestante = (TEMPO_HORA_EXTRA_DIARIA - this.tempoExtraTrabalhado);

		else if (!sextaFeira && this.isMensalista() && (this.tempoTrabalhado >= this.tempo()) && (this.tempoTrabalhado <= (this.tempo() + TEMPO_BANCO_HORAS)))
			tempoRestante = ((this.tempo() + TEMPO_BANCO_HORAS) - this.tempoTrabalhado);

		else if (this.isHorista() && (this.tempoTrabalhado >= this.tempo()))
			tempoRestante = (TEMPO_HORA_EXTRA_DIARIA - this.tempoExtraTrabalhado);

		else if (this.tempoTrabalhado <= this.tempo())
			tempoRestante = (this.tempo() - this.tempoTrabalhado);

		return tempoRestante;
	}

	//Tempo por CC
	public int tempo()
	{
		//Se for SÁBADO e DOMINGO
		if (ld.getDayOfWeek() == DayOfWeek.SATURDAY || ld.getDayOfWeek() == DayOfWeek.SUNDAY || (this.hExtraProgramada && this.hExtraRegistro))
		{
			if (this.isHorista())
				return 840;
			else
				return 840;
		}
		//Se for DIA DA SEMANA
		else {
			if (this.isHorista())
				return 528;
			else
			{
				//Se for SEXTA
				if (ld.getDayOfWeek() == DayOfWeek.FRIDAY)
					return 462;
				else
					return 522;
			}
		}
	}

	//Formatar para HTML
	public static String html(String texto)
	{
		return ("<html>" + "<center>" + texto + "</center>" + "</html>");
	}
}