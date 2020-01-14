package backend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.LocalDate;

public class WebAPI {

	final static String CONTINENTE = "America";
	final static String REGIAO = "Sao_Paulo";


	public static LocalDate dataAtual() throws IOException {
		String data = new String();

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

		LocalDate ld = LocalDate.parse(data);
		System.out.println("Data WEB: " + data.toString());
		return ld;
	}
}