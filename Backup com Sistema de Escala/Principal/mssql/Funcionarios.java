package mssql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.microsoft.sqlserver.jdbc.SQLServerResultSet;

import rede.Conn;

public class Funcionarios {

	public static int inserir(String nome, int cracha, int cc, boolean ele, boolean mec, boolean proj, boolean prog, boolean eng, boolean adm, boolean horista, boolean mensalista) {
		try {
			String sql = "INSERT INTO [dbo].[FUNCIONARIOS] ([NOME],[CRACHA],[CC],[ELE],[MEC],[PROJ],[PROG],[ENG],[ADM],[HORISTA],[MENSALISTA])VALUES(?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement st = Conn.connection.prepareStatement(sql, SQLServerResultSet.TYPE_SCROLL_INSENSITIVE, SQLServerResultSet.CONCUR_READ_ONLY);

			st.setString(1, nome);
			st.setInt(2, cracha);
			st.setInt(3, cc);
			st.setBoolean(4, ele);
			st.setBoolean(5, mec);
			st.setBoolean(6, proj);
			st.setBoolean(7, prog);
			st.setBoolean(8, eng);
			st.setBoolean(9, adm);
			st.setBoolean(10, horista);
			st.setBoolean(11, mensalista);
			st.executeUpdate();
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public static int alterar(String nome, int cracha, int cc, boolean ele, boolean mec, boolean proj, boolean prog, boolean eng, boolean adm, boolean horista, boolean mensalista, int id) {
		try {
			String sql = "UPDATE [dbo].[FUNCIONARIOS] SET [NOME] = ?, [CRACHA] = ?, [CC] = ?, [ELE] = ?, [MEC] = ?, [PROJ] = ?, [PROG] = ?, [ENG] = ?, [ADM] = ?, [HORISTA] = ?, [MENSALISTA] = ? WHERE id = " + id;
			PreparedStatement st = Conn.connection.prepareStatement(sql, SQLServerResultSet.TYPE_SCROLL_INSENSITIVE, SQLServerResultSet.CONCUR_READ_ONLY);

			st.setString(1, nome);
			st.setInt(2, cracha);
			st.setInt(3, cc);
			st.setBoolean(4, ele);
			st.setBoolean(5, mec);
			st.setBoolean(6, proj);
			st.setBoolean(7, prog);
			st.setBoolean(8, eng);
			st.setBoolean(9, adm);
			st.setBoolean(10, horista);
			st.setBoolean(11, mensalista);
			st.executeUpdate();
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public static int apagar(int id) {
		try {
			String sql = "DELETE FROM [dbo].[FUNCIONARIOS] WHERE id = " + id;
			PreparedStatement st = Conn.connection.prepareStatement(sql, SQLServerResultSet.TYPE_SCROLL_INSENSITIVE, SQLServerResultSet.CONCUR_READ_ONLY);
			st.executeUpdate();
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public static ResultSet retornar() {
		try {
			String sql = "SELECT [ID], [NOME], [CRACHA], [CC], [ELE], [MEC], [PROJ], [PROG], [ENG], [ADM], [HORISTA], [MENSALISTA] FROM [dbo].[FUNCIONARIOS]";
			PreparedStatement st = Conn.connection.prepareStatement(sql, SQLServerResultSet.TYPE_SCROLL_INSENSITIVE, SQLServerResultSet.CONCUR_READ_ONLY);
			
			return st.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static ResultSet pesquisarNome(String pesquisa) {
		try {
			String sql = "SELECT [ID], [NOME], [CRACHA], [CC], [ELE], [MEC], [PROJ], [PROG], [ENG], [ADM], [HORISTA], [MENSALISTA] FROM [dbo].[FUNCIONARIOS] WHERE NOME = ?";
			PreparedStatement st = Conn.connection.prepareStatement(sql, SQLServerResultSet.TYPE_SCROLL_INSENSITIVE, SQLServerResultSet.CONCUR_READ_ONLY);
			st.setString(1, pesquisa);
			
			return st.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static ResultSet pesquisarCracha(int pesquisa) {
		try {
			String sql = "SELECT [ID], [NOME], [CRACHA], [CC], [ELE], [MEC], [PROJ], [PROG], [ENG], [ADM], [HORISTA], [MENSALISTA] FROM [dbo].[FUNCIONARIOS] WHERE CRACHA = ?";
			PreparedStatement st = Conn.connection.prepareStatement(sql, SQLServerResultSet.TYPE_SCROLL_INSENSITIVE, SQLServerResultSet.CONCUR_READ_ONLY);
			st.setInt(1, pesquisa);
			
			return st.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static ResultSet listarCcs() {
		try {
			String sql = "SELECT DISTINCT [F].[CC] FROM [dbo].[FUNCIONARIOS] AS [F]";
			PreparedStatement st = Conn.connection.prepareStatement(sql, SQLServerResultSet.TYPE_SCROLL_INSENSITIVE, SQLServerResultSet.CONCUR_READ_ONLY);
			return st.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
