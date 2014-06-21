package data;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import po.PlayerPO;
import util.JdbcHelper;
import dataservice.PlayerDataService;

public class PlayerData implements PlayerDataService{
	String sql="";
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	public PlayerData() throws RemoteException{
		init();
	}
	
	@Override
	public PlayerPO find(String name) throws RemoteException {
		// TODO Auto-generated method stub
		PlayerPO player=null;
		sql="select * from player_info where name=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, name);
			rs=ps.executeQuery();
			
			if(rs.next()){
			player=new PlayerPO(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4),rs.getInt(5),rs.getInt(6),rs.getFloat(7),rs.getLong(8),rs.getInt(9),rs.getInt(10),rs.getInt(11));
	
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return player;
	}

	@Override
	public void finish() throws RemoteException {
		
		JdbcHelper.closeDB();
	}

	@Override
	public void insertPlayer(PlayerPO po) throws RemoteException {
		sql="insert into player_info(name,password,gameCount,avgScore,maxHitCount,maxScore,maxGodTime) values (?,?,?,?,?,?,?)";
		//String name=po.getName();
		//String password=po.getPassword();
		//int gameCount=po.getGameCount();
		
		
		try {
			ps=conn.prepareStatement(sql);
		//	ps.setString(1, name);
		//	ps.setString(2,	password);
		//	ps.setString(3, name);
	
			int n=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(PlayerPO po) throws RemoteException {
		// TODO Auto-generated method stub
		sql="delete from player_info where name=?";
		//String name=po.getName();
		
		try {
			ps=conn.prepareStatement(sql);
			//ps.setString(1, name);
			int n=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void update(PlayerPO po) throws RemoteException {
		// TODO Auto-generated method stub
	//	String name=po.getName();
	sql="update  player_info set name=?,password=?,gameCount=?,avgScore=?,maxHitCount=?,maxScore=?,maxGodTime=?";
		
		try {
			ps=conn.prepareStatement(sql);
		//	ps.setString(1, id);
		//	ps.setString(2,	password);
		//	ps.setString(3, name);
		//	ps.setString(4, type);
		//	ps.setString(5, id);
			int n=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void init() throws RemoteException {
		// TODO Auto-generated method stub
		JdbcHelper.openDB();
		this.conn=JdbcHelper.conn;
		this.ps=JdbcHelper.ps;
		this.rs=JdbcHelper.rs;
	}

}
