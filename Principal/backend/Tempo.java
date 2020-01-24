package backend;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Tempo {

	public static LocalDateTime tempoWeb = WebAPI.horaAtual();
	public static LocalDateTime tempoLocal = LocalDateTime.now();
	
	private int diferencaHora, diferencaMinuto;
	
	private LocalDateTime tempoObjetivo;
	
	
	public Tempo() {
		this.diferencaHora = (tempoLocal.getHour() - tempoWeb.getHour());
		this.diferencaMinuto = (tempoLocal.getMinute() - tempoWeb.getMinute());
	}
	
	public LocalDateTime tempoAtual() {
		this.tempoObjetivo = LocalDateTime.now().minusHours(this.diferencaHora).minusMinutes(this.diferencaMinuto);
		return this.tempoObjetivo;
	}
	
	/**
	 * @return O último dia útil, se o dia atual for <b>segunda-feira</b>,</br>
	 * então o último dia útil será <b>sexta-feira</b>
	 */
	public static String ultimoDiaUtil() {
		LocalDate data = WebAPI.dataAtual();
		
		if (data.getDayOfWeek() == DayOfWeek.MONDAY)
			return data.minusDays(3).toString();
		else
			return data.minusDays(1).toString();
		
	}
	/*public int tempoMaximo() {
		//Se for SÁBADO e DOMINGO
		if (this.tempoObjetivo.getDayOfWeek() == DayOfWeek.SATURDAY || this.tempoObjetivo.getDayOfWeek() == DayOfWeek.SUNDAY || (this.hExtraProgramada && this.hExtraRegistro)) {
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
				if (this.ldCondicional.getDayOfWeek() == DayOfWeek.FRIDAY)
					return 462;
				else
					return 522;
			}
		}
	}
	
	public int tempoRestante() {
		boolean fimDeSemana = (this.ldCondicional.getDayOfWeek() == DayOfWeek.SATURDAY || this.ldCondicional.getDayOfWeek() == DayOfWeek.SUNDAY);
		boolean sextaFeira = (this.ldCondicional.getDayOfWeek() == DayOfWeek.FRIDAY);

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
	}*/
}