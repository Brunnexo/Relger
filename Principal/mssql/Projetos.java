package mssql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.microsoft.sqlserver.jdbc.SQLServerResultSet;

import rede.Conn;

public class Projetos {
	public static int inserir(String[] linha) {
		try {
			String sql = "INSERT INTO [dbo].[PROJETOS] ([CC],[CLIENTE],[PROJETO],[CLASSE],[DESCRICAO],[EQUIPAMENTO],[OS],[WOCOMPRAS],[WOADM],[WOELETRICISTA],[WOMECANICO],[WOENGENHEIRO],[WOPROJETISTA],[WOPROGRAMADOR],[WOFERRAMENTARIA]) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement st = Conn.connection.prepareStatement(sql);			

			for (int i = 1; i < 16; i++)
				st.setString(i, linha[i].toUpperCase());

			st.executeUpdate();
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public static String geralWo (String wo, String descr) //PESQUISA PROJETOS PARA COMBOBOX
	{
		String item = new String();

		try
		{
			PreparedStatement pproj = Conn.connection.prepareStatement("SELECT [" + wo.toUpperCase() + "] FROM [dbo].[PROJETOS] WHERE DESCRICAO LIKE ?");
			pproj.setString(1, "%" + descr + "%");
			pproj.execute();

			ResultSet rs = pproj.executeQuery();
			while (rs.next())
			{
				item = rs.getString(1);
			}
			return item;
		}catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
		return item;
	}

	public static ResultSet pesquisaProjeto(String campo, String wo, int escolha) {
		try {
			String sql = new String();
			switch (escolha)
			{
			case 1: //OS
				sql = "SELECT [CC], [CLIENTE], [PROJETO], [CLASSE], [DESCRICAO],[EQUIPAMENTO], [OS], [WOCOMPRAS], [WOADM], [WOELETRICISTA], [WOMECANICO], [WOENGENHEIRO], [WOPROJETISTA], [WOPROGRAMADOR], [WOFERRAMENTARIA] FROM [dbo].[PROJETOS] WHERE [OS] LIKE '%" + campo + "%'";
				break;
			case 2: //DESCRIÇÃO
				sql = "SELECT [CC], [CLIENTE], [PROJETO], [CLASSE], [DESCRICAO],[EQUIPAMENTO], [OS], [WOCOMPRAS], [WOADM], [WOELETRICISTA], [WOMECANICO], [WOENGENHEIRO], [WOPROJETISTA], [WOPROGRAMADOR], [WOFERRAMENTARIA] FROM [dbo].[PROJETOS] WHERE [DESCRICAO] LIKE '%" + campo + "%'";
				break;
			case 3: //WO
				sql = "SELECT [CC], [CLIENTE], [PROJETO], [CLASSE], [DESCRICAO],[EQUIPAMENTO], [OS], [WOCOMPRAS], [WOADM], [WOELETRICISTA], [WOMECANICO], [WOENGENHEIRO], [WOPROJETISTA], [WOPROGRAMADOR], [WOFERRAMENTARIA] FROM [dbo].[PROJETOS] WHERE [" + wo + "] LIKE '%" + campo + "%'";
				break;
			}
			PreparedStatement st = Conn.connection.prepareStatement(sql, SQLServerResultSet.TYPE_SCROLL_INSENSITIVE, SQLServerResultSet.CONCUR_READ_ONLY);

			return st.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	//
	
	public static ResultSet pesquisarGeral() {
		try {
			String sql = "SELECT [CC], [CLIENTE], [PROJETO], [CLASSE], [DESCRICAO],[EQUIPAMENTO], [OS], [WOCOMPRAS], [WOADM], [WOELETRICISTA], [WOMECANICO], [WOENGENHEIRO], [WOPROJETISTA], [WOPROGRAMADOR], [WOFERRAMENTARIA] FROM [dbo].[PROJETOS] WHERE [CLIENTE] NOT LIKE 'GM' AND [CLIENTE] NOT LIKE 'VW'  AND [CLIENTE] NOT LIKE 'FORD'  AND [CLIENTE] NOT LIKE 'FCA'  AND [CLIENTE] NOT LIKE 'RENAULT'  AND [CLIENTE] NOT LIKE 'HONDA'  AND [CLIENTE] NOT LIKE 'NISSAN' AND [CLIENTE] NOT LIKE 'TOYOTA' AND [CLIENTE] NOT LIKE 'HYUNDAI' AND [CLIENTE] NOT LIKE 'MERCEDES' AND [CLIENTE] NOT LIKE 'PSA' AND [CLIENTE] NOT LIKE 'MAN' AND [CLIENTE] NOT LIKE 'AUTOMAÇÃO'";
			PreparedStatement st = Conn.connection.prepareStatement(sql, SQLServerResultSet.TYPE_SCROLL_INSENSITIVE, SQLServerResultSet.CONCUR_READ_ONLY);

			return st.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static ResultSet pesquisarMontadora(String campo) {
		try {
			String sql = "SELECT [CC], [CLIENTE], [PROJETO], [CLASSE], [DESCRICAO],[EQUIPAMENTO], [OS], [WOCOMPRAS], [WOADM], [WOELETRICISTA], [WOMECANICO], [WOENGENHEIRO], [WOPROJETISTA], [WOPROGRAMADOR], [WOFERRAMENTARIA] FROM [dbo].[PROJETOS] WHERE [CLIENTE] LIKE ?";
			PreparedStatement st = Conn.connection.prepareStatement(sql, SQLServerResultSet.TYPE_SCROLL_INSENSITIVE, SQLServerResultSet.CONCUR_READ_ONLY);
			st.setString(1, "%" + campo + "%");

			return st.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public static ResultSet retornar() {
		try {
			String sql = "SELECT [CC], [CLIENTE], [PROJETO], [CLASSE], [DESCRICAO],[EQUIPAMENTO], [OS], [WOCOMPRAS], [WOADM], [WOELETRICISTA], [WOMECANICO], [WOENGENHEIRO], [WOPROJETISTA], [WOPROGRAMADOR], [WOFERRAMENTARIA] FROM [dbo].[PROJETOS]";
			PreparedStatement st = Conn.connection.prepareStatement(sql, SQLServerResultSet.TYPE_SCROLL_INSENSITIVE, SQLServerResultSet.CONCUR_READ_ONLY);

			return st.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
