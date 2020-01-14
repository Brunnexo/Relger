package backend;

import java.time.LocalDateTime;
import java.util.TimerTask;

public abstract class TokenGen extends TimerTask {

	public String token;
	
	@Override
	public final void run() {
		LocalDateTime data = LocalDateTime.now();
		String[] tok = data.toString().split("-");

		String caractere;

		if (data.getHour() % 3 == 0)
			caractere = "A";
		else if (data.getHour() % 2 == 0)
			caractere = "B";
		else
			caractere = "C";

		String token = caractere + tok[0] + tok[1] + (data.getHour() - 1);
		
		this.setToken(token);
	}
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	public static boolean checaToken(String entrada)
	{
		String[] tok = LocalDateTime.now().toString().split("-");

		String caractere;

		if (LocalDateTime.now().getHour() % 3 == 0)
			caractere = "A";
		else if (LocalDateTime.now().getHour() % 2 == 0)
			caractere = "B";
		else
			caractere = "C";

		String token = caractere + tok[0] + tok[1] + (LocalDateTime.now().getHour() - 1);
		
		return token.equals(entrada);
	}
	
}