package mssql;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.microsoft.sqlserver.jdbc.SQLServerResultSet;

import backend.Atributos;
import janelas.Notificacao;
import rede.Conn;

public class Relatorios {

	public static int registrar(Atributos att, int tempo)
	{
		try
		{
			String sql = "INSERT INTO [dbo].[RELATORIOS] ([DATA],[CRACHA],[FUNCAO],[WO],[DESCRICAO],[TEMPO],[HE]) VALUES(?,?,?,?,?,?,?)";
			PreparedStatement pRel = Conn.connection.prepareStatement(sql);

			pRel.setString(1, att.dataCondicionalFormatada());
			pRel.setInt(2, att.getCrachaFunc());
			pRel.setString(3, att.getNomeFuncao());

			if (att.getWo().equals(""))
				pRel.setString(4, "-");
			else
				pRel.setString(4, att.getWo());

			pRel.setString(5, att.getDescTrabalho());
			pRel.setInt(6, tempo);
			pRel.setBoolean(7, att.ishExtra());
			pRel.executeUpdate();

			System.out.println("Registro bem-sucedido!");
			return 1;
		} catch(SQLException e)
		{
			if (e.getMessage().contains("estouro") && e.getMessage().contains("IDENTITY"))
			{
				try {
					String sql = "DBCC CHECKIDENT ('[RELATORIOS]', reseed, 0)";
					Statement st = Conn.connection.createStatement();
					st.execute(sql);
					JOptionPane.showMessageDialog(null, "A ID foi reiniciada por atingir o limite físico de variável", "ID reiniciada", JOptionPane.INFORMATION_MESSAGE);
					return Relatorios.registrar(att, tempo);
				} catch (SQLException ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Falha ao reiniciar ID do banco de dados, contate o desenvolvedor", "Falha", JOptionPane.ERROR_MESSAGE);
					return 2;
				}
			}
			return 2;			
		}
	}

	public static int deletar(int id, int cracha, String wo)
	{
		try
		{
			String sql = "DELETE FROM [dbo].[RELATORIOS] WHERE ID = ? AND CRACHA = ? AND WO = ?";
			PreparedStatement pRel = Conn.connection.prepareStatement(sql);
			pRel.setInt(1, id);
			pRel.setInt(2, cracha);
			pRel.setString(3, wo);
			pRel.executeUpdate();
			return 1;
		} catch(SQLException e)
		{
			System.out.println(e.getMessage());
			return 2;			
		}
	}

	public static ResultSet retornar(int cracha, String data)
	{
		try {
			String sql = "SELECT [ID],[DATA],[CRACHA],[FUNCAO],[WO],[DESCRICAO],[TEMPO],[HE] FROM [dbo].[RELATORIOS] WHERE CRACHA = ? AND DATA = ?";
			PreparedStatement st = Conn.connection.prepareStatement(sql, SQLServerResultSet.TYPE_SCROLL_INSENSITIVE, SQLServerResultSet.CONCUR_READ_ONLY);
			st.setInt(1, cracha);
			st.setString(2, data);
			return st.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static ResultSet retornar(int cracha)
	{
		try {
			String sql = "SELECT [ID],[DATA],[CRACHA],[FUNCAO],[WO],[DESCRICAO],[TEMPO],[HE] FROM [dbo].[RELATORIOS] WHERE CRACHA = ? ORDER BY [DATA] DESC";
			PreparedStatement st = Conn.connection.prepareStatement(sql, SQLServerResultSet.TYPE_SCROLL_INSENSITIVE, SQLServerResultSet.CONCUR_READ_ONLY);
			st.setInt(1, cracha);
			return st.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void carregarTotal(int cc)
	{
		Thread executar = new Thread()
		{
			public void run()
			{
				executarCarregarTotal(cc);
			}
		};
		executar.start();
	}

	public static void executarCarregarTotal(int cc)
	{
		janelas.Progresso progresso = new janelas.Progresso("Carregando...", "Criando planilhas...");
		progresso.mostrar();
		try
		{
			int contagem = 0;

			Statement pRelst = Conn.connection.createStatement();
			ResultSet rsSt = pRelst.executeQuery("SELECT COUNT(*) FROM RELATORIOS AS [R] INNER JOIN [dbo].[FUNCIONARIOS] AS [F] ON [R].[CRACHA] = [F].[CRACHA] WHERE [F].[CC] = " + cc);

			while(rsSt.next())
			{
				contagem = rsSt.getInt(1);
				System.out.println("Contagem: " + contagem);
			}
			PreparedStatement pRel = Conn.connection.prepareStatement("SELECT [R].[ID], [R].[DATA], [R].[CRACHA], [R].[FUNCAO], [R].[WO], [R].[DESCRICAO], [R].[TEMPO], [R].[HE]  FROM [dbo].[RELATORIOS] AS [R] INNER JOIN [dbo].[FUNCIONARIOS] AS [F] ON [R].[CRACHA] = [F].[CRACHA] WHERE [F].[CC] = ? ORDER BY [DATA], [CRACHA]");
			pRel.setInt(1, cc);
			pRel.execute();

			ResultSet rs = pRel.executeQuery();

			@SuppressWarnings("resource")
			XSSFWorkbook tabela = new XSSFWorkbook();
			XSSFSheet planilha = tabela.createSheet("Planilha");

			//LINHA 1
			XSSFRow linha = planilha.createRow(0);
			XSSFCell celula;
			celula = linha.createCell(0);
			celula.setCellValue(1);
			celula = linha.createCell(1);
			celula.setCellValue(2);
			celula = linha.createCell(2);
			celula.setCellValue("03/01/1900");
			celula = linha.createCell(3);
			celula.setCellValue(4);
			celula = linha.createCell(4);
			celula.setCellValue("00:00");
			celula = linha.createCell(5);
			celula.setCellValue("00:00");
			celula = linha.createCell(6);
			celula.setCellValue("00:00");
			celula = linha.createCell(7);
			celula.setCellValue(8);
			celula = linha.createCell(8);
			celula.setCellValue(9);
			celula = linha.createCell(9);
			celula.setCellValue(10);
			celula = linha.createCell(10);
			celula.setCellValue(11);

			//LINHA 2
			linha = planilha.createRow(1);
			celula = linha.createCell(0);
			celula.setCellValue("WO No");
			celula = linha.createCell(1);
			celula.setCellValue("Site");
			celula = linha.createCell(2);
			celula.setCellValue("Effective Date");
			celula = linha.createCell(3);
			celula.setCellValue("Employee");
			celula = linha.createCell(4);
			celula.setCellValue("time start");
			celula = linha.createCell(5);
			celula.setCellValue("time finish");
			celula = linha.createCell(6);
			celula.setCellValue("lunch_time");
			celula = linha.createCell(7);
			celula.setCellValue("TIME");
			celula = linha.createCell(8);
			celula.setCellValue("pay_add_code");
			celula = linha.createCell(9);
			celula.setCellValue("pay_mult_code");
			celula = linha.createCell(10);
			celula.setCellValue("comment");

			//Linha 3
			linha = planilha.createRow(2);
			celula = linha.createCell(0);
			celula.setCellValue("Ordem Servico");
			celula = linha.createCell(1);
			celula.setCellValue("site_code");
			celula = linha.createCell(2);
			celula.setCellValue("dd/mm/aaaa");
			celula = linha.createCell(3);
			celula.setCellValue("Registro");
			celula = linha.createCell(4);
			celula.setCellValue("hh:mm");
			celula = linha.createCell(5);
			celula.setCellValue("hh:mm");
			celula = linha.createCell(6);
			celula.setCellValue("hh:mm");
			celula = linha.createCell(7);
			celula.setCellValue("hh:mm");
			celula = linha.createCell(8);
			celula.setCellValue("unidade Prod.");
			celula = linha.createCell(9);
			celula.setCellValue("Codigo Mao Obra");

			//LINHA 4...
			int i = 3; //CORRIGIR I = 3 (caso dê certo) //Original I = 2

			//Máscara de data
			CellStyle dataCelula = tabela.createCellStyle();
			CreationHelper mascaraData = tabela.getCreationHelper();

			dataCelula.setDataFormat(
					mascaraData.createDataFormat().getFormat("dd/mm/yyyy"));

			//Máscara de hora
			CellStyle horaCelula = tabela.createCellStyle();
			CreationHelper mascaraHora = tabela.getCreationHelper();

			horaCelula.setDataFormat(
					mascaraHora.createDataFormat().getFormat("hh:mm"));

			while (rs.next())
			{
				progresso.altProgresso(rs.getRow(), contagem);
				progresso.altDescr("CRACHÁ: " + rs.getInt("CRACHA") + " | " + "WO: " + rs.getString("WO"));
				linha = planilha.createRow(i);

				boolean woValida;

				try {
					Integer.parseInt(rs.getString("WO"));
					woValida = true;
				} catch (NumberFormatException ex) {woValida = false;}

				if (!woValida)
					System.out.println("Sem WO, pulando linha...");
				else
				{
					//WO
					celula = linha.createCell(0);
					celula.setCellValue(rs.getString("WO"));

					//SITE
					celula = linha.createCell(1);
					celula.setCellValue(111);

					//DATA
					celula = linha.createCell(2);
					celula.setCellValue(rs.getDate("DATA"));
					celula.setCellStyle(dataCelula);

					//CRACHÁ
					celula = linha.createCell(3);
					celula.setCellValue(rs.getInt("CRACHA"));

					//TEMPO DE SERVIÇO
					celula = linha.createCell(7);
					celula.setCellValue((double) (rs.getDouble("TEMPO")/60.0/24.0));
					celula.setCellStyle(horaCelula);

					//CÓDIGO DE TRABALHO
					celula = linha.createCell(8);
					celula.setCellValue("00");

					//HORA EXTRA OU NÃO
					celula = linha.createCell(9);
					if (rs.getBoolean("HE"))
						celula.setCellValue("1009");
					else
						celula.setCellValue("00");

					//DESCRIÇÃO DO TRABALHO
					celula = linha.createCell(10);
					celula.setCellValue(rs.getString("DESCRICAO"));

					celula = linha.createCell(11);
					celula = linha.createCell(12);
					celula = linha.createCell(13);
					//LOOP
					i++;
				}
			}
			File caminho = new File(System.getenv("USERPROFILE") + "/Documents/Relatórios/" + Integer.toString(cc));
			File arquivo = new File(System.getenv("USERPROFILE") + "/Documents/Relatórios/" + Integer.toString(cc) + "/post_labor.xlsx");

			if (!caminho.exists())
				caminho.mkdirs();

			FileOutputStream saida = new FileOutputStream(arquivo);

			tabela.write(saida);

			saida.close();
			System.out.println("Planilha escrita com sucesso!");
			//Criando Tabela
			progresso.finalizar("PLANILHA CRIADA!");

		} catch(SQLException e) {
			progresso.dispose();
			System.out.println(e.getLocalizedMessage());
			Notificacao.notificar("Erro", "Erro no SQL: " + e.getLocalizedMessage(), Notificacao.EXCLAMACAO_CRITICA);
		} catch (IOException e) {
			e.printStackTrace();
			progresso.dispose();
			Notificacao.notificar("Erro", e.getLocalizedMessage(), Notificacao.EXCLAMACAO_CRITICA);
		}
	}

	public static void carregarPeriodo(String periodoA, String periodoB, int cc)
	{
		Thread executar = new Thread()
		{
			public void run()
			{
				executarCarregarPeriodo(periodoA, periodoB, cc);
			}
		};
		executar.start();
	}

	public static void executarCarregarPeriodo(String periodoA, String periodoB, int cc)
	{
		janelas.Progresso progresso = new janelas.Progresso("Carregando...", "Criando planilhas...");
		progresso.mostrar();
		try
		{
			int contagem = 0;

			PreparedStatement pRelst = Conn.connection.prepareStatement("SELECT COUNT(*) FROM RELATORIOS AS [R] INNER JOIN [dbo].[FUNCIONARIOS] AS [F] ON [R].[CRACHA] = [F].[CRACHA] WHERE [F].[CC] = ? AND [DATA] BETWEEN ? AND ?");
			pRelst.setInt(1, cc);
			pRelst.setString(2, periodoA);
			pRelst.setString(3, periodoB);

			ResultSet rsSt = pRelst.executeQuery();

			while(rsSt.next())
			{
				contagem = rsSt.getInt(1);
				System.out.println("Contagem: " + contagem);
			}

			//
			PreparedStatement pRel = Conn.connection.prepareStatement("SELECT [R].[ID], [R].[DATA], [R].[CRACHA], [R].[FUNCAO], [R].[WO], [R].[DESCRICAO], [R].[TEMPO], [R].[HE]  FROM [dbo].[RELATORIOS] AS [R] INNER JOIN [dbo].[FUNCIONARIOS] AS [F] ON [R].[CRACHA] = [F].[CRACHA] WHERE [F].[CC] = ? AND [DATA] BETWEEN ? AND ? ORDER BY [DATA], [CRACHA]");
			pRel.setInt(1, cc);
			pRel.setString(2, periodoA);
			pRel.setString(3, periodoB);
			pRel.execute();

			ResultSet rs = pRel.executeQuery();

			@SuppressWarnings("resource")
			XSSFWorkbook tabela = new XSSFWorkbook();
			XSSFSheet planilha = tabela.createSheet("Planilha");

			//LINHA 1
			XSSFRow linha = planilha.createRow(0);
			XSSFCell celula;
			celula = linha.createCell(0);
			celula.setCellValue(1);
			celula = linha.createCell(1);
			celula.setCellValue(2);
			celula = linha.createCell(2);
			celula.setCellValue("03/01/1900");
			celula = linha.createCell(3);
			celula.setCellValue(4);
			celula = linha.createCell(4);
			celula.setCellValue("00:00");
			celula = linha.createCell(5);
			celula.setCellValue("00:00");
			celula = linha.createCell(6);
			celula.setCellValue("00:00");
			celula = linha.createCell(7);
			celula.setCellValue(8);
			celula = linha.createCell(8);
			celula.setCellValue(9);
			celula = linha.createCell(9);
			celula.setCellValue(10);
			celula = linha.createCell(10);
			celula.setCellValue(11);

			//LINHA 2
			linha = planilha.createRow(1);
			celula = linha.createCell(0);
			celula.setCellValue("WO No");
			celula = linha.createCell(1);
			celula.setCellValue("Site");
			celula = linha.createCell(2);
			celula.setCellValue("Effective Date");
			celula = linha.createCell(3);
			celula.setCellValue("Employee");
			celula = linha.createCell(4);
			celula.setCellValue("time start");
			celula = linha.createCell(5);
			celula.setCellValue("time finish");
			celula = linha.createCell(6);
			celula.setCellValue("lunch_time");
			celula = linha.createCell(7);
			celula.setCellValue("TIME");
			celula = linha.createCell(8);
			celula.setCellValue("pay_add_code");
			celula = linha.createCell(9);
			celula.setCellValue("pay_mult_code");
			celula = linha.createCell(10);
			celula.setCellValue("comment");

			//Linha 3
			linha = planilha.createRow(2);
			celula = linha.createCell(0);
			celula.setCellValue("Ordem Servico");
			celula = linha.createCell(1);
			celula.setCellValue("site_code");
			celula = linha.createCell(2);
			celula.setCellValue("dd/mm/aaaa");
			celula = linha.createCell(3);
			celula.setCellValue("Registro");
			celula = linha.createCell(4);
			celula.setCellValue("hh:mm");
			celula = linha.createCell(5);
			celula.setCellValue("hh:mm");
			celula = linha.createCell(6);
			celula.setCellValue("hh:mm");
			celula = linha.createCell(7);
			celula.setCellValue("hh:mm");
			celula = linha.createCell(8);
			celula.setCellValue("unidade Prod.");
			celula = linha.createCell(9);
			celula.setCellValue("Codigo Mao Obra");

			//LINHA 4...
			int i = 3; //CORRIGIR I = 3 (caso dê certo) //Original I = 2


			//Máscara de data
			CellStyle dataCelula = tabela.createCellStyle();
			CreationHelper mascaraData = tabela.getCreationHelper();

			dataCelula.setDataFormat(
					mascaraData.createDataFormat().getFormat("dd/mm/yyyy"));

			//Máscara de hora
			CellStyle horaCelula = tabela.createCellStyle();
			CreationHelper mascaraHora = tabela.getCreationHelper();

			horaCelula.setDataFormat(
					mascaraHora.createDataFormat().getFormat("hh:mm"));

			while (rs.next())
			{
				progresso.altProgresso(rs.getRow(), contagem);
				progresso.altDescr("CRACHÁ: " + rs.getString(2) + " | " + "WO: " + rs.getString(4));
				linha = planilha.createRow(i);

				boolean woValida;
				try {
					Integer.parseInt(rs.getString("WO"));
					woValida = true;
				} catch (NumberFormatException ex) {woValida = false;}

				if (!woValida)
					System.out.println("Sem WO, pulando linha...");
				else
				{
					//WO
					celula = linha.createCell(0);
					celula.setCellValue(rs.getString("WO"));

					//SITE
					celula = linha.createCell(1);
					celula.setCellValue(111);

					//DATA
					celula = linha.createCell(2);
					celula.setCellValue(rs.getDate("DATA"));
					celula.setCellStyle(dataCelula);

					//CRACHÁ
					celula = linha.createCell(3);
					celula.setCellValue(rs.getInt("CRACHA"));

					//TEMPO DE SERVIÇO
					celula = linha.createCell(7);
					celula.setCellValue((double) (rs.getDouble("TEMPO")/60.0/24.0));
					celula.setCellStyle(horaCelula);

					//CÓDIGO DE TRABALHO
					celula = linha.createCell(8);
					celula.setCellValue("00");

					//HORA EXTRA OU NÃO
					celula = linha.createCell(9);
					if (rs.getBoolean("HE"))
						celula.setCellValue("1009");
					else
						celula.setCellValue("00");

					//DESCRIÇÃO DO TRABALHO
					celula = linha.createCell(10);
					celula.setCellValue(rs.getString("DESCRICAO"));
					//LOOP/
					i++;
				}
			}
			File caminho = new File(System.getenv("USERPROFILE") + "/Documents/Relatórios/" + Integer.toString(cc) + "/Período/" + "DE " + converterData(periodoA) + " A " + converterData(periodoB));
			File arquivo = new File(System.getenv("USERPROFILE") + "/Documents/Relatórios/" + Integer.toString(cc) + "/Período/" + "DE " + converterData(periodoA) + " A " + converterData(periodoB) + "/post_labor.xlsx");

			if (!caminho.exists())
				caminho.mkdirs();

			FileOutputStream saida = new FileOutputStream(arquivo);

			tabela.write(saida);
			saida.close();
			System.out.println("Planilha escrita com sucesso!");
			//Criando Tabela
			progresso.finalizar("PLANILHA CRIADA!");

		} catch(SQLException e) {
			System.out.println(e.getMessage());
			Notificacao.notificar("Erro", "Erro no SQL: " + e.getLocalizedMessage(), Notificacao.EXCLAMACAO_CRITICA);
			progresso.dispose();
		} catch (IOException e) {
			progresso.dispose();
			Notificacao.notificar("Erro", e.getLocalizedMessage(), Notificacao.EXCLAMACAO_CRITICA);
		}
	}

	static String converterData (String data)
	{
		String[] separado = data.split("-");
		return separado[2] + "-" + separado[1] + "-" + separado[0];
	}
}