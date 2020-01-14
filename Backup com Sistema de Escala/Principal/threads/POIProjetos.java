package threads;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import backend.Samba;
import jcifs.smb.SmbFile;
import mssql.Projetos;
import rede.Conn;
//Lê os projetos da planilha e salvam no banco de dados
public class POIProjetos {

	//Caminho do Arquivo
	final static String TEMP = System.getenv("TEMP") + "\\temp.xlsm";

	static File temp = new File(TEMP);

	public static void LerPlanilha(SmbFile arquivo) throws IOException
	{
		while (temp.exists())
		{
			temp.delete();
		}
		Executar(arquivo);
	}

	public static void Executar(SmbFile arquivo) throws IOException
	{
		Samba.converter(arquivo, temp);

		janelas.Progresso progresso = new janelas.Progresso("Carregando", "Carregando planilha...");
		progresso.mostrar();

		//Conexão com arquivo


		FileInputStream fis = new FileInputStream(temp);
		XSSFWorkbook tabela = new XSSFWorkbook(fis); //Conexão com arquivo

		XSSFSheet planilha = tabela.getSheet("Projetos"); //Pega a planilha pelo nome
		Iterator<Row> tabelaLinha = planilha.iterator(); //Iterar linhas

		while(tabelaLinha.hasNext())
		{
			//Enquanto houver célula preenchida na linha
			Row linha = tabelaLinha.next();
			Iterator<Cell> celula = linha.cellIterator();
			String escritaLinha = new String();
			escritaLinha = "";

			while (celula.hasNext())
			{
				//Enquanto houver células com conteúdo na linha	  
				Cell cell = celula.next();
				escritaLinha = escritaLinha + cell.toString() + ";";
			}

			//Pausa

			if (escritaLinha.contains("ABERTO"))
			{
				progresso.altProgresso(linha.getRowNum(), planilha.getPhysicalNumberOfRows());
				System.out.println(Arrays.toString(backend.TratarLinhaProjeto.tratar(escritaLinha)));
				progresso.altDescr(backend.TratarLinhaProjeto.tratar(escritaLinha)[5]);

				//MS SQL
				Projetos.inserir(backend.TratarLinhaProjeto.tratar(escritaLinha));
			}
		}

		try
		{
			Statement stateprojetos = Conn.connection.createStatement();
			stateprojetos.executeUpdate("WITH proj AS (SELECT [WOCOMPRAS],[WOADM],[WOELETRICISTA],[WOMECANICO],[WOENGENHEIRO],[WOPROJETISTA],[WOPROGRAMADOR],[WOFERRAMENTARIA], ROW_NUMBER() OVER (PARTITION BY [WOCOMPRAS],[WOADM],[WOELETRICISTA],[WOMECANICO],[WOENGENHEIRO],[WOPROJETISTA],[WOPROGRAMADOR],[WOFERRAMENTARIA] ORDER BY [CC]) ROW_NUM FROM [dbo].[PROJETOS]) DELETE FROM proj WHERE ROW_NUM > 1;");
		} catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}

		tabela.close(); //Fecha tabela
		temp.delete();
		progresso.finalizar("BANCO DE DADOS ATUALIZADO!");
	}
}