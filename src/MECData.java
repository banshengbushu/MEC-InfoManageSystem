

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MECData 
{
	private String dataSource;
	private Statement stmt;
	private Connection conn;
	private ResultSet rs;
	
	public MECData()
	{
		this(null);
	}
	
	public MECData(String dataSource)
	{
		this.dataSource = dataSource;
		conn = null;
		stmt = null;
		rs = null;
	}
	
	public void connectionDatabase(String dataSource) throws Exception //�������ݿ�
	{
		if(dataSource != null)
			this.dataSource = dataSource;
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			conn = DriverManager.getConnection("jdbc:odbc:" + this.dataSource);
			stmt = conn.createStatement();
		}catch(ClassNotFoundException cnfe)
		{
			throw new Exception("����" + cnfe.getMessage() + "\n" +
					"�ࣺMECData\n������public void connectionAccess(String dataSource) throws Exception\n\n" +
					"����ѯ΢���뿪��������Ա�����������⣡");
		} catch (SQLException e) 
		{
			throw new Exception("����" + e.getMessage() + "\n" +
					"�ࣺMECData\n������public void connectionAccess(String dataSource) throws Exception\n\n" +
					"����ѯ΢���뿪��������Ա�����������⣡");
		}
	}
	
	public void connectionDatabase() throws Exception
	{
		connectionDatabase(null);
	}
	
	public void disconnection() throws Exception
	{
		try
		{
			if(rs != null)
				rs.close();
			if(stmt != null)
				stmt.close();
			if(conn != null)
				conn.close(); 
		}catch(SQLException sqle)
		{
			throw new Exception("����" + sqle.getMessage() + "\n" +
					"�ࣺMECData\n������public void disconnection() throws Exception\n");
		}
	}
	
	public boolean update(String SQLString) throws Exception
	{
		boolean rs = false;
		
		if(stmt != null)
			try 
			{
				rs = stmt.execute(SQLString);
			} catch (SQLException sqle) 
			{
				throw new Exception("����" + sqle.getMessage() + "\n�ࣺMECData\n" +
						"������public ResultSet doSQL(String SQLString) throws Exception\n");
			}
		
		return rs;
	}
	
	public ResultSet select(String SQLString) throws Exception
	{
		if(stmt != null)
			try 
			{
				rs = stmt.executeQuery(SQLString);
			} catch (SQLException sqle) 
			{
				throw new Exception("����" + sqle.getMessage() + "\n" +
						"�ࣺMECData\n" +
						"������public ResultSet doSQL(String SQLString) throws Exception\n");
			}
		
		return rs;
	}
}
