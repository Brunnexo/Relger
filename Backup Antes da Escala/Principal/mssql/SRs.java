package mssql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.microsoft.sqlserver.jdbc.SQLServerResultSet;

import rede.Conn;

public class SRs {


	public static int inserir(String[] linha) {
		try {
			String sql = "INSERT INTO [dbo].[SRs] ([SR],[WO],[DESCRICAO],[SOLICITANTE],[RESP_EA],[TIPO]) VALUES (?,?,?,?,?,?)";
			PreparedStatement st = Conn.connection.prepareStatement(sql);

			st.setString(1, linha[0]);
			st.setString(2, linha[1]);
			st.setString(3, linha[2]);
			st.setString(4, linha[3]);
			st.setString(5, linha[4]);
			st.setString(6, linha[5]);

			st.executeUpdate();

			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public static ResultSet retornar() {
		try {
			String sql = "SELECT [SR],[WO],[DESCRICAO],[SOLICITANTE],[RESP_EA],[TIPO] FROM [dbo].[SRs]";
			PreparedStatement st = Conn.connection.prepareStatement(sql, SQLServerResultSet.TYPE_SCROLL_INSENSITIVE, SQLServerResultSet.CONCUR_READ_ONLY);

			return st.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static ResultSet pesquisaSR(String campo, int escolha) {
		try {
			String sql = new String();
			switch (escolha)
			{
			case 1:
				sql = "SELECT [SR],[WO],[DESCRICAO],[SOLICITANTE],[RESP_EA],[TIPO] FROM [dbo].[SRs] WHERE [SR] LIKE '%" + campo + "%'";
				break;
			case 2:
				sql = "SELECT [SR],[WO],[DESCRICAO],[SOLICITANTE],[RESP_EA],[TIPO] FROM [dbo].[SRs] WHERE [WO] LIKE '%" + campo + "%'";
				break;
			}
			PreparedStatement st = Conn.connection.prepareStatement(sql, SQLServerResultSet.TYPE_SCROLL_INSENSITIVE, SQLServerResultSet.CONCUR_READ_ONLY);

			return st.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}