package cn.edu.aust.domain;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class JdbcUtils {
	//工具类，一般不允许new出 对象，将构造方法设成私有，且该类一般不允许继承
	private static String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static String url="jdbc:sqlserver://localhost:1433;DatabaseName=game_tetris";
	private static String user="sa";
	private static String password="czc";
	private static boolean isConnected=true;
	
	public static boolean isConnected() {
		return isConnected;
	}

	private JdbcUtils(){
		
	}
	/*
	 * 静态代码块只执行一次，一般定义在类中
	 * 作用：可以完成类的初始化。静态代码块随着类的加载而执行，而且只执行一次
	 * new多个对象都只执行一次
	 * 如果和主函数在同一个类中，优先于主函数执行
	 */
	
	
	static {
		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			//e.printStackTrace();注意此处捕捉异常的方式
			
			throw new ExceptionInInitializerError(e);
		}
	  }
	
	 public static Connection getConnection() {
		 Connection connection=null;
		try {
			connection= DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			 isConnected=false;
			 JOptionPane.showMessageDialog(null, "数据库未连接！");
		}
		return connection;
	  }
	
	 public static void free(ResultSet rs,Statement st,Connection conn){
		       try {
				 if(rs!=null)
				 rs.close();
			  } catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try {
					if(st!=null)
					 st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}finally{
					try {
						if(conn!=null)
						 conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		
	 }
}
