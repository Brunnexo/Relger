package mssql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.microsoft.sqlserver.jdbc.SQLServerResultSet;

import rede.Conn;

public class Senhas {	
	public static boolean login(int cracha, String senha) {
		boolean retorno = false;
		
		try {
			String sql = "SELECT COUNT(*) FROM [dbo].[SENHAS] WHERE [CRACHA] = ? AND [SENHA] = ? COLLATE SQL_Latin1_General_CP1_CS_AS";
			PreparedStatement st = Conn.connection.prepareStatement(sql, SQLServerResultSet.TYPE_SCROLL_INSENSITIVE, SQLServerResultSet.CONCUR_READ_ONLY);
			st.setInt(1, cracha);
			st.setString(2, senha);
			
			ResultSet rs = st.executeQuery();
			
			while (rs.next())
			{
				retorno = rs.getBoolean(1);				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return retorno;
	}
}