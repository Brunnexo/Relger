package backend;

public class TratarLinhaSR {
	public static String[] tratar(String linha)
	{
		String[] textoTratado = linha.split(";");
		for (int i = 0; i < textoTratado.length; i++)
		{
			textoTratado[i] = textoTratado[i].replace(".0", "");
		}
		return textoTratado;
	}
}