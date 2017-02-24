package cn.edu.aust.domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.edu.aust.dto.Player;

public class DataDataBase implements Data {
	
	@Override
	public List<Player> loadData()  {
		/**
		 * 用于从数据库中加载数据
		 * */
		    String sql = "select top 5 tb_game.name, tb_game.point from tb_game order by point desc;" ;
		    List<Player> players=players=new ArrayList<Player>();
		    Connection conn = null;
			Statement st = null;
			ResultSet rs = null;

			try {
			conn = JdbcUtils.getConnection();
				// 建立连接
				if(conn!=null){
					st = conn.createStatement();
					// 创建语句
					rs = st.executeQuery(sql);
					// 执行语句
					while (rs.next()) {
						// 处理结果
						players.add(new Player(rs.getString(1),rs.getInt(2)));
						//rs.getString(i)获取当前行的第i个字段的值
					  }
				}
				
			}catch(SQLException e){
				//此处若数据库连接失败时将不打印异常信息，而是让游戏界面不打印数据库界面
				
				//e.printStackTrace();
			} 
			finally {
				JdbcUtils.free(rs, st, conn);
			}
			return players;
	}

	@Override
	public void saveData(String name,int point) {
		/**
		 * 用于将数据保存至数据库
		 * */
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
		conn = JdbcUtils.getConnection();
			// 建立连接
			if(conn!=null){
					String sql="select * from  tb_game;";
					ps = conn.prepareStatement(sql);
					// 创建语句
					rs = ps.executeQuery();
					// 执行语句
					while (rs.next()) {
						// 处理结果
						
						if(rs.getString(2).equals(name)){
							
							updateDB(conn,ps,name,point);
						}
				}
					inserDB(conn,ps,name,point);
			}
			
		}catch(SQLException e){
			//此处若数据库连接失败时将不打印异常信息，而是让游戏界面不打印数据库界面
			e.printStackTrace();
		} 
		finally {
			JdbcUtils.free(rs, ps, conn);
		}
	}

	private void inserDB(Connection conn, PreparedStatement ps, String name, int point) {
			String sql="insert into tb_game values(?,?);";
			try {
				ps=conn.prepareStatement(sql);
				ps.setString(1, name);
				ps.setInt(2, point);
				ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}

	private void updateDB(Connection conn, PreparedStatement ps, String name, int point) {
		String sql="update tb_game set point=? where name=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, point);
			ps.setString(2, name);
			// 创建语句
		    ps.executeUpdate();
			// 执行语句
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
