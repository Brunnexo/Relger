package backend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class WebAPI {

	final static String CONTINENTE = "America";
	final static String REGIAO = "Sao_Paulo";

	public static int difDia, difMes, difAno;
	public static int difHora, difMinuto, difSegundo;

	static boolean acessoData, acessoDataHora;

	public static LocalDate dataAtual() {
		String data = new String();
		LocalDate ld = LocalDate.now();

		if (!acessoData) {
			try {
				URL link = new URL("http://worldtimeapi.org/api/timezone/" + CONTINENTE + "/" + REGIAO + ".txt");
				BufferedReader entrada = new BufferedReader(new InputStreamReader(link.openStream()));
				String linhaEntrada;

				while ((linhaEntrada = entrada.readLine()) != null) {
					if (linhaEntrada.contains("utc_datetime: "))
						break;
				}
				entrada.close();

				data = linhaEntrada.substring((linhaEntrada.indexOf(": ") + 2), linhaEntrada.indexOf("T"));

				ld = LocalDate.parse(data);
				System.out.println("Data WEB: " + data.toString());

				difDia = LocalDate.now().getDayOfMonth() - ld.getDayOfMonth();
				difMes = LocalDate.now().getMonthValue() - ld.getMonthValue();
				difAno = LocalDate.now().getYear() - ld.getYear();

				acessoData = true;
			} catch (IOException e ) {}
		}
		else {
			ld = LocalDate.now().minusDays(difDia).minusMonths(difMes).minusYears(difAno);
			System.out.println("Data WEB: " + ld.toString());
			System.out.println("Correções");
			System.out.println("Diferença em dias: " + difDia);
			System.out.println("Diferença em meses: " + difMes);
			System.out.println("Diferença em anos: " + difAno);
		}
		return ld;
	}

	public static LocalDateTime horaAtual() {
		String hora = new String();
		LocalDateTime ldt = LocalDateTime.now();

		if (!acessoDataHora) {
			try {
				URL link = new URL("http://worldtimeapi.org/api/timezone/" + CONTINENTE + "/" + REGIAO + ".txt");
				BufferedReader entrada = new BufferedReader (new InputStreamReader(link.openStream()));
				String linhaEntrada;
				while ((linhaEntrada = entrada.readLine()) != null) {
					if (linhaEntrada.contains("datetime: "))
						break;
				}
				entrada.close();
				hora = linhaEntrada.substring((linhaEntrada.indexOf(": ") + 2)).substring(0, 19);
				ldt = LocalDateTime.parse(hora);
				System.out.println("Hora WEB: " + hora);

				difDia = LocalDateTime.now().getDayOfMonth() - ldt.getDayOfMonth();
				difMes = LocalDateTime.now().getMonthValue() - ldt.getMonthValue();
				difAno = LocalDateTime.now().getYear() - ldt.getYear();

				difHora = LocalDateTime.now().getHour() - ldt.getHour();
				difMinuto = LocalDateTime.now().getMinute() - ldt.getMinute();
				difSegundo = LocalDateTime.now().getSecond() - ldt.getSecond();

				acessoDataHora = true;
			} catch (IOException e) {}
		}
		else {
			ldt = LocalDateTime.now().minusDays(difDia).minusMonths(difMes).minusYears(difAno).minusHours(difHora).minusMinutes(difMinuto).minusSeconds(difSegundo);
			System.out.println("Hora WEB: " + ldt.toString());
			System.out.println("Correções");
			System.out.println("Diferença em dias: " + difDia);
			System.out.println("Diferença em meses: " + difMes);
			System.out.println("Diferença em anos: " + difAno);
			System.out.println("Diferença em horas: " + difHora);
			System.out.println("Diferença em minutos: " + difMinuto);
			System.out.println("Diferença em segundos: " + difSegundo);
		}
		return ldt;
	}
}