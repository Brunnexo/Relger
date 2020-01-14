package backend;

import java.time.LocalDateTime;

public abstract class Token {

	public static boolean checaToken(String entrada)
	{
		try {
			boolean car1, car2;
			boolean split1, split2;

			String[] tok = LocalDateTime.now().toString().split("-");

			String caractere1;

			if (LocalDateTime.now().getHour() % 3 == 0)
				caractere1 = "A";
			else if (LocalDateTime.now().getHour() % 2 == 0)
				caractere1 = "B";
			else
				caractere1 = "C";

			car1 = (caractere1.equals(String.valueOf(entrada.charAt(0))));

			String caractere2 = String.valueOf(entrada.substring((caractere1 + tok[0] + tok[1]).length()).charAt(0));
			int rand = Integer.parseInt(entrada.substring((caractere1 + tok[0] + tok[1] + " " + (LocalDateTime.now().getHour() - 1) + (Integer.parseInt(tok[0]) / LocalDateTime.now().getHour())).length()));

			if (rand % 6 == 0)
				car2 = caractere2.contains("U");
			else if (rand % 5 == 0)
				car2 = caractere2.contains("V");
			else if (rand % 4 == 0)
				car2 = caractere2.contains("W");
			else if (rand % 3 == 0)
				car2 = caractere2.contains("X");
			else if (rand % 2 == 0)
				car2 = caractere2.contains("Y");
			else
				car2 = caractere2.contains("Z");

			String comparativo = Integer.toString(LocalDateTime.now().getHour() - 1) + Integer.toString(Integer.parseInt(tok[0]) / LocalDateTime.now().getHour()) + rand;

			split1 = entrada.substring(1, entrada.indexOf(caractere2)).equals(tok[0] + tok[1]);
			split2 = entrada.substring(entrada.indexOf(caractere2) + 1).equals(comparativo);

			return ((car1 && car2) && (split1 && split2));
		} catch (StringIndexOutOfBoundsException ex) {return false;}

	}
}