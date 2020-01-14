package backend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;

import com.google.gson.Gson;

/**
 * rom: contato@calendario.com.br, To: efbvzcoi, Date 2020-01-03 16:39:55

Olá,

 Você pode utilizar nossa api através do link abaixo, substituindo o
ANO, ESTADO e CIDADE, pelos desejados:
https://api.calendario.com.br/?ano=2017&estado=SP&cidade=SAO_PAULO&token=ZWZidnpjb2lAc3BhbTQubWUmaGFzaD0xNzI3ODc0Nw

Pode também utilizar o Código IBGE da cidade desejada:
https://api.calendario.com.br/?ano=2017&ibge=3550308&token=ZWZidnpjb2lAc3BhbTQubWUmaGFzaD0xNzI3ODc0Nw

Você receberá um resultado em XML, semelhante a esta imagem:
http://www.calendario.com.br/figs/xml_sample.png

Se preferir o resultado em JSON, basta incluir o parametro json=true na
URL:
https://api.calendario.com.br/?json=true&ano=2017&ibge=3550308&token=ZWZidnpjb2lAc3BhbTQubWUmaGFzaD0xNzI3ODc0Nw

Para acessar a lista de cidades do Banco de Dados, utilize esse link:
http://www.calendario.com.br/api/cities.json

Veja mais informações nesta página:
http://www.calendario.com.br/dev/api_feriados_municipais_estaduais_nacionais.php

atenciosamente,
equipe Calendario.com.br
 * @author Usuario
 *
 */




public class Feriados {
	final static String TOKEN = "ZWZidnpjb2lAc3BhbTQubWUmaGFzaD0xNzI3ODc0Nw";
	final static String ANO = Integer.toString(LocalDate.now().getYear());
	final static String CIDADE = "JUNDIAI";
	final static String ESTADO = "SP";
	
	final static String URL = "https://api.calendario.com.br/?json=true&ano=" + ANO + "&estado=" + ESTADO + "&cidade=" + CIDADE + "&token=" + TOKEN;
	

	public static void API()
	{
		Gson gson = new Gson();
		URL url;
		URLConnection urlConn = null;
		try {
			url = new URL(URL);
			urlConn = url.openConnection();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
			
			RegistroFeriados rF = gson.fromJson(br, RegistroFeriados.class);
			
			System.out.println(rF);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
