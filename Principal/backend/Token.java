package backend;

import java.time.LocalDate;
import java.time.LocalDateTime;

public abstract class Token {

	public static boolean checaToken(String entrada)
	{
		try {
			String[] comp = entrada.split(":");
			//183:A:30:F:87:K:46
			boolean anoOk, car1Ok, car2Ok, car3Ok, car4Ok, minutoOk, rand2Ok, rand3Ok;
			
			anoOk = ((LocalDate.now().getYear() / 11) == Integer.parseInt(comp[0]));
			
			int rand1 = Integer.parseInt(comp[2]);
			
			if (rand1 % 3 == 0)
				car1Ok = (comp[1].equals("A"));
			else if (rand1 % 2 == 0)
				car1Ok = (comp[1].equals("B"));
			else
				car1Ok = (comp[1].equals("C"));

			int rand2 = Integer.parseInt(comp[4]);
			rand2Ok = (((rand1 - 1) * 3) == rand2);
			
			if (rand2 <= 10)
				car2Ok = (comp[3].equals("D"));
			else if (rand2 <= 20)
				car2Ok = (comp[3].equals("E"));
			else
				car2Ok = (comp[3].equals("F"));
			
			int rand3 = Integer.parseInt(comp[6]);
			rand3Ok = ((rand1 / 2) == rand3);
			
			if (rand3 <= 30)
				car3Ok = comp[5].equals("G");
			else if (rand3 <= 40)
				car3Ok = comp[5].equals("H");
			else
				car3Ok = comp[5].equals("I");

			int minuto = (LocalDateTime.now().getMinute() * 11);
			
			minutoOk = (minuto == Integer.parseInt(comp[8]));
			
			if (minuto % 3 == 0)
				car4Ok = (comp[7].equals("J"));
			else if (minuto % 2 == 0)
				car4Ok = (comp[7].equals("K"));
			else
				car4Ok = (comp[7].equals("L"));
			
			
			return (anoOk && car1Ok && car2Ok && car3Ok && car4Ok && minutoOk && rand2Ok && rand3Ok);
		} catch (StringIndexOutOfBoundsException | NumberFormatException ex) {return false;}
	}
}