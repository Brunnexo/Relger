package mssql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.microsoft.sqlserver.jdbc.SQLServerResultSet;

import rede.Conn;

public class Extra {

	public static ResultSet retornar(int cracha) {
		try {
			String sql = "SELECT [E].[ITEM],[E].[CRACHA],[E].[DATA],[E].[DESCRICAO],CASE WHEN [FINALIZADO] <> 0 THEN 'SIM' ELSE 'NÃO' END AS [FINALIZADO] FROM [dbo].[EXTRA] AS [E] INNER JOIN [dbo].[FUNCIONARIOS] AS [F] ON [E].[CRACHA] = [F].[CRACHA] WHERE [F].[CRACHA] = ?";
			PreparedStatement st = Conn.connection.prepareStatement(sql, SQLServerResultSet.TYPE_SCROLL_INSENSITIVE, SQLServerResultSet.CONCUR_READ_ONLY);
			st.setInt(1, cracha);

			return st.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static ResultSet retornaAbertos(int cracha)
	{
		try {
			String sql = "SELECT [E].[ITEM],[E].[CRACHA],[E].[DATA],[E].[DESCRICAO],CASE WHEN [FINALIZADO] <> 0 THEN 'SIM' ELSE 'NÃO' END AS [FINALIZADO] FROM [dbo].[EXTRA] AS [E] INNER JOIN [dbo].[FUNCIONARIOS] AS [F] ON [E].[CRACHA] = [F].[CRACHA] WHERE [F].[CRACHA] = ? AND [FINALIZADO] = 'FALSE'";
			PreparedStatement st = Conn.connection.prepareStatement(sql, SQLServerResultSet.TYPE_SCROLL_INSENSITIVE, SQLServerResultSet.CONCUR_READ_ONLY);
			st.setInt(1, cracha);

			return st.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static boolean retornar(int cracha, String data) {
		try {
			String sql = "SELECT [E].[FINALIZADO] FROM [dbo].[EXTRA] AS [E] WHERE [E].[CRACHA] = " + cracha + " AND [E].[DATA] <= '" + data + "'";
			Statement st = Conn.connection.createStatement();

			ResultSet rs = st.executeQuery(sql);

			ArrayList<Boolean> retorno = new ArrayList<Boolean>();
			boolean ret = false;

			while (rs.next())
			{
				retorno.add(rs.getBoolean(1));
			}

			for (int i = 0; i < retorno.size(); i++)
			{
				ret = (ret || !retorno.get(i));
			}

			return ret;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static int inserir(int cracha, String descricao, String data)
	{
		try {
			String sql = "INSERT INTO [dbo].[EXTRA] ([CRACHA],[DATA],[DESCRICAO],[FINALIZADO]) VALUES (?,?,?,?)";
			PreparedStatement st = Conn.connection.prepareStatement(sql, SQLServerResultSet.TYPE_SCROLL_INSENSITIVE, SQLServerResultSet.CONCUR_READ_ONLY);
			st.setInt(1, cracha);
			st.setString(2, data);
			st.setString(3, descricao);
			st.setBoolean(4, false);

			st.execute();
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public static int apagar(int item)
	{
		try {
			String sql = "DELETE FROM [dbo].[EXTRA] WHERE [ITEM] = ?";
			PreparedStatement st = Conn.connection.prepareStatement(sql, SQLServerResultSet.TYPE_SCROLL_INSENSITIVE, SQLServerResultSet.CONCUR_READ_ONLY);
			st.setInt(1, item);

			st.execute();
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public static int atualizar(int cracha,  boolean finalizado, String data)
	{
		try {
			String sql = "UPDATE [dbo].[EXTRA] SET [FINALIZADO] = ? WHERE [CRACHA] = ? AND [DATA] = ?";
			PreparedStatement st = Conn.connection.prepareStatement(sql, SQLServerResultSet.TYPE_SCROLL_INSENSITIVE, SQLServerResultSet.CONCUR_READ_ONLY);

			st.setBoolean(1, finalizado);
			st.setInt(2, cracha);
			st.setString(3, data);
			
			st.execute();
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	//UPDATE [dbo].[EXTRA] SET [CRACHA] = <CRACHA, int,> ,[DATA] = <DATA, date,> ,[DESCRICAO] = <DESCRICAO, varchar(50),> ,[FINALIZADO] = <FINALIZADO, bit,> WHERE <Critérios de Pesquisa,,>

}