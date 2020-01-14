package backend;

public class TratarLinhaProjeto {
	public static String[] tratar(String linha)
	{
		if (!linha.startsWith(";"))
		{
			linha = ";" + linha;
		}

		String[] textoTratado = linha.split(";");

		for (int i = 0; i < textoTratado.length; i++)
		{
			if (textoTratado[i].equals("-"))
				textoTratado[i] = "";
			
			textoTratado[i] = textoTratado[i].replace(".0", ""); //Remove casas decimais desnecessárias
			
			if(i < 5) //Até a coluna descrição
			{
				if (textoTratado[i].contains(" / "))
				{
					textoTratado[i] = textoTratado[i].replace(" / ", "/");
				}
				else if ((textoTratado[i].contains("Fiat")) || (textoTratado[i].contains("Jeep")))
				{
					textoTratado[i] = textoTratado[i].replace("Fiat", "FCA");
					textoTratado[i] = textoTratado[i].replace("Jeep", "FCA");
				}
				else if ((textoTratado[i].contains("Fiat".toUpperCase())) || (textoTratado[i].contains("Jeep".toUpperCase())))
				{
					textoTratado[i] = textoTratado[i].replace("FIAT", "FCA");
					textoTratado[i] = textoTratado[i].replace("JEEP", "FCA");
				}
				else if (textoTratado[i].contains("EngAut"))
				{
					textoTratado[i] = textoTratado[i].replace("EngAut", "Automação");
				}
				else if (textoTratado[i].equals(""))
				{
					textoTratado[i] = "-";
				}
			}
			else if (i == 6) //Coluna equipamento
			{
				if (textoTratado[i].equals(""))
				{
					textoTratado[i] = "-";
				}
			}
			else if (i == 7) //Coluna OS
			{
				if (textoTratado[i].contains(" / "))
				{
					textoTratado[i] = textoTratado[i].replace(" / ", "/");
				}
			}
			else if(i > 7) //Colunas WOs
			{
				if (textoTratado[i].equals(""))
				{
					textoTratado[i] = "-";
				}
			}
		}
		return textoTratado;
	}
}