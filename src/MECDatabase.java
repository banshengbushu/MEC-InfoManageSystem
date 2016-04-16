

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 此类供调用者完成对数据库的链接，并对数据进行操作的工作
 * @author Administrator
 *
 */
public class MECDatabase {
	
	private int databaseType;	/*要链接的数据库类型*/

	private static final String[] DB_DRIVER = {"oracle.jdbc.OracleDriver",		/*链接数据库的驱动*/
												"sun.jdbc.odbc.JdbcOdbcDriver",
												"com.mysql.jdbc.Driver"};
	
	private static final String[] URL = {"jdbc:oracle:thin:@","jdbc:odbc:","jdbc:mysql://"};	/*链接数据库的ＵＲＬ*/
	
	private static final int ORACLE = 0;	/*数据库类型*/
	private static final int ACCESS = 1;
	private static final int MYSQL = 2;
	
	private Statement stmt;
	private Connection con;
	private ResultSet rst;

	/**
	 * 带参的构造方法
	 * @param databaseType 传入要链接的数据库类型(int 类型)
	 * 						ORACLE表示链接Oracle数据库
	 * 						ACCESS表示链接Access数据库
	 * 						MYSQL表示链接MySql数据库
	 */
	public MECDatabase(int databaseType){
		
		this.databaseType = databaseType;
		stmt = null;
		con = null;
		rst = null;
	}
	
	/**
	 * 获得数据库类型
	 * @return 返回数据库类型
	 */
	private int getDatabaseType() {
		return databaseType;
	}

	/**
	 * 链接不需要IP和端口号的数据库
	 * @param databaseName 数据源名称
	 * @throws Exception	链接数据库时遇到的异常
	 */
	public void connection(String databaseName) throws Exception{
		
		try {
				Class.forName(DB_DRIVER[ACCESS]);
				con = DriverManager.getConnection(URL[ACCESS]+databaseName);
				stmt = con.createStatement();
		} catch (ClassNotFoundException e) {
			throw new Exception("错误：" + e.getMessage() + "\n" +
					"类：MECDatabase\n方法：public void connection() throws Exception\n\n" +
					"请咨询微易码开发技术人员，解决这个问题！");
		
		}
	}
	
	/**
	 * 链接数据库
	 * @param hostIp 		主机IP地址
	 * @param port			端口号
	 * @param databaseName	数据源名称
	 * @param userName		用户名
	 * @param password		密码
	 * @throws Exception	连接数据库是遇到的异常
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
			throw new Exception("错误：" + e1.getMessage() + "\n" +
					"类：MECDatabase\n方法：public void connection() throws Exception\n\n" +
					"请咨询微易码开发技术人员，解决这个问题！");
			
		} catch (SQLException e2) {
			throw new Exception("错误：" + e2.getMessage() + "\n" +
					"类：MECDatabase\n方法：public void connection() throws Exception\n\n" +
					"请咨询微易码开发技术人员，解决这个问题！");	
		}
	}	
	
	/**
	 * 关闭与当前数据库的链接
	 * @throws Exception 关闭数据库链接时遇到的异常
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
			throw new Exception("错误：" + sqle.getMessage() + "\n" +
					"类：MECDatabase\n方法：public void disConnection() throws Exception\n");
		}
	}
	
	/**
	 * 执行SQL语句，doSql方法只能完成查询功能
	 * @param sqlSwing 传入的SQL语句
	 * @return 返回查询的对象
	 * @throws Exception 执行SQL语句时遇到的异常
	 */
	public ResultSet doSql(String sql)throws Exception{
		
		if(stmt != null){
			try{
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
				rst = stmt.executeQuery(sql);
			}catch(SQLException sqlE){
				
				throw new Exception("错误：" + sqlE.getMessage() + "\n" +
						"类：MECDatabase\n" +
						"方法：public ResultSet doSql(String SQLString) throws Exception\n");
			}
		}
		return rst;
	}
	
	/**
	 * 执行SQL语句，upDate方法可以完成创建，修改，插入，删除等功能
	 * @param sqlSwing 传入的SQL语句
	 * @return 返回该操作是否成功
	 * @throws Exception 执行SQL语句时遇到的异常
	 */
	public boolean update(String sql)throws Exception{
		
		boolean flag = false;
		
		if(stmt != null){
			try{
				flag = stmt.execute(sql);
			}catch(SQLException sqlE){
				
				throw new Exception("错误：" + sqlE.getMessage() + "\n" +
						"类：MECDatabase\n" +
						"方法：public ResultSet doSql(String SQLString) throws Exception\n");
			}
		}
		return flag;
	}

}
