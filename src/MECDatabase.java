

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * ���๩��������ɶ����ݿ�����ӣ��������ݽ��в����Ĺ���
 * @author Administrator
 *
 */
public class MECDatabase {
	
	private int databaseType;	/*Ҫ���ӵ����ݿ�����*/

	private static final String[] DB_DRIVER = {"oracle.jdbc.OracleDriver",		/*�������ݿ������*/
												"sun.jdbc.odbc.JdbcOdbcDriver",
												"com.mysql.jdbc.Driver"};
	
	private static final String[] URL = {"jdbc:oracle:thin:@","jdbc:odbc:","jdbc:mysql://"};	/*�������ݿ�ģգң�*/
	
	private static final int ORACLE = 0;	/*���ݿ�����*/
	private static final int ACCESS = 1;
	private static final int MYSQL = 2;
	
	private Statement stmt;
	private Connection con;
	private ResultSet rst;

	/**
	 * ���εĹ��췽��
	 * @param databaseType ����Ҫ���ӵ����ݿ�����(int ����)
	 * 						ORACLE��ʾ����Oracle���ݿ�
	 * 						ACCESS��ʾ����Access���ݿ�
	 * 						MYSQL��ʾ����MySql���ݿ�
	 */
	public MECDatabase(int databaseType){
		
		this.databaseType = databaseType;
		stmt = null;
		con = null;
		rst = null;
	}
	
	/**
	 * ������ݿ�����
	 * @return �������ݿ�����
	 */
	private int getDatabaseType() {
		return databaseType;
	}

	/**
	 * ���Ӳ���ҪIP�Ͷ˿ںŵ����ݿ�
	 * @param databaseName ����Դ����
	 * @throws Exception	�������ݿ�ʱ�������쳣
	 */
	public void connection(String databaseName) throws Exception{
		
		try {
				Class.forName(DB_DRIVER[ACCESS]);
				con = DriverManager.getConnection(URL[ACCESS]+databaseName);
				stmt = con.createStatement();
		} catch (ClassNotFoundException e) {
			throw new Exception("����" + e.getMessage() + "\n" +
					"�ࣺMECDatabase\n������public void connection() throws Exception\n\n" +
					"����ѯ΢���뿪��������Ա�����������⣡");
		
		}
	}
	
	/**
	 * �������ݿ�
	 * @param hostIp 		����IP��ַ
	 * @param port			�˿ں�
	 * @param databaseName	����Դ����
	 * @param userName		�û���
	 * @param password		����
	 * @throws Exception	�������ݿ����������쳣
	 */
	public void connection(String hostIp,int port,String databaseName,String userName,String password) throws Exception {
		
		
		try {
				if(this.getDatabaseType() == ORACLE){
					Class.forName(DB_DRIVER[ORACLE]);
					DriverManager.getConnection(URL[ORACLE]+hostIp+":"+port+"/"+databaseName,userName,password);
				}else{
					Class.forName(DB_DRIVER[MYSQL]);
					DriverManager.getConnection(URL[MYSQL]+hostIp+":"+port+"/"+databaseName,userName,password);
				}
					
				
		
		} catch (ClassNotFoundException e1) {
			throw new Exception("����" + e1.getMessage() + "\n" +
					"�ࣺMECDatabase\n������public void connection() throws Exception\n\n" +
					"����ѯ΢���뿪��������Ա�����������⣡");
			
		} catch (SQLException e2) {
			throw new Exception("����" + e2.getMessage() + "\n" +
					"�ࣺMECDatabase\n������public void connection() throws Exception\n\n" +
					"����ѯ΢���뿪��������Ա�����������⣡");	
		}
	}	
	
	/**
	 * �ر��뵱ǰ���ݿ������
	 * @throws Exception �ر����ݿ�����ʱ�������쳣
	 */
	public void disConnection()throws Exception{
		
		try
		{
			if(rst != null)
				rst.close();
			if(stmt != null)
				stmt.close();
			if(con != null)
				con.close();
		}catch(SQLException sqle)
		{
			throw new Exception("����" + sqle.getMessage() + "\n" +
					"�ࣺMECDatabase\n������public void disConnection() throws Exception\n");
		}
	}
	
	/**
	 * ִ��SQL��䣬doSql����ֻ����ɲ�ѯ����
	 * @param sqlSwing �����SQL���
	 * @return ���ز�ѯ�Ķ���
	 * @throws Exception ִ��SQL���ʱ�������쳣
	 */
	public ResultSet doSql(String sql)throws Exception{
		
		if(stmt != null){
			try{
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
				rst = stmt.executeQuery(sql);
			}catch(SQLException sqlE){
				
				throw new Exception("����" + sqlE.getMessage() + "\n" +
						"�ࣺMECDatabase\n" +
						"������public ResultSet doSql(String SQLString) throws Exception\n");
			}
		}
		return rst;
	}
	
	/**
	 * ִ��SQL��䣬upDate����������ɴ������޸ģ����룬ɾ���ȹ���
	 * @param sqlSwing �����SQL���
	 * @return ���ظò����Ƿ�ɹ�
	 * @throws Exception ִ��SQL���ʱ�������쳣
	 */
	public boolean update(String sql)throws Exception{
		
		boolean flag = false;
		
		if(stmt != null){
			try{
				flag = stmt.execute(sql);
			}catch(SQLException sqlE){
				
				throw new Exception("����" + sqlE.getMessage() + "\n" +
						"�ࣺMECDatabase\n" +
						"������public ResultSet doSql(String SQLString) throws Exception\n");
			}
		}
		return flag;
	}

}
