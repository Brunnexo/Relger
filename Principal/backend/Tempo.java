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
	
	public String diaFuncional() {
		if (this.tempoAtual().getHour() < 12) {
		}

	}
	
	public int tempoMaximo() {
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
}