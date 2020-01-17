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


	public static LocalDate dataAtual() {
		String data = new String();
		LocalDate ld = LocalDate.now();
		/*try {
			URL link = new URL("http://worldtimeapi.org/api/timezone/" + CONTINENTE + "/" + REGIAO + ".txt");


			BufferedReader entrada = new BufferedReader(new InputStreamReader(link.openStream()));


			String linhaEntrada;

			while ((linhaEntrada = entrada.readLine()) != null)
			{
				if (linhaEntrada.contains("utc_datetime: "))
					break;
			}
			entrada.close();

			data = linhaEntrada.substring((linhaEntrada.indexOf(": ") + 2), linhaEntrada.indexOf("T"));

			ld = LocalDate.parse(data);
			System.out.println("Data WEB: " + data.toString());
			return ld;
		} catch (IOException e ) {}
		return ld;*/
		return LocalDate.now();
	}

	public static LocalDateTime horaAtual() {
		String hora = new String();
		LocalDateTime ldt = LocalDateTime.now();
		
		/*try {
			URL link = new URL("http://worldtimeapi.org/api/timezone/" + CONTINENTE + "/" + REGIAO + ".txt");
			
			BufferedReader entrada = new BufferedReader (new InputStreamReader(link.openStream()));
			
			String linhaEntrada;
			
			while ((linhaEntrada = entrada.readLine()) != null)
			{
				if (linhaEntrada.contains("datetime: "))
					break;
			}
			entrada.close();
			hora = linhaEntrada.substring((linhaEntrada.indexOf(": ") + 2)).substring(0, 19);
			ldt = LocalDateTime.parse(hora);
			System.out.println("Hora WEB: " + hora);
			return ldt;
			
		} catch (IOException e) {}
		return ldt;*/
		
		return LocalDateTime.now();
	}
}