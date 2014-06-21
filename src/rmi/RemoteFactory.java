package rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JOptionPane;

import DataServer.GameTask;
import DataServer.GameTasker2;
import blservice.PlayerBLService;

public class RemoteFactory {
	public static String host = "172.26.48.5";
    public static String port = "1099";
	
	public GameTask getRmiGameTaskerService(){
		GameTask rmiService=null;
		PlayerBLService playerService=null;
		 try {
			  rmiService =(GameTask)Naming.lookup("rmi://" + host + ":" + port
	                 + "/MyGameTasker");
			  playerService=(PlayerBLService)Naming.lookup("rmi://" + host + ":" + port
		                 + "/MyPlayer");
	     } catch (Exception e) {
             e.printStackTrace();
	     }
		return rmiService;
		 
	}
	public PlayerBLService getPlayerBLService(){
		
		PlayerBLService playerService=null;
		 try {
			 
			  playerService=(PlayerBLService)Naming.lookup("rmi://" + host + ":" + port
		                 + "/MyPlayer");
	     } catch (NotBoundException e) {
	    	 JOptionPane.showMessageDialog(null, "�������ӳ�ʱ...");
	     } catch (MalformedURLException e) {
	    	 JOptionPane.showMessageDialog(null, "�������ӳ�ʱ...");
	     } catch (RemoteException e) {
	    	 JOptionPane.showMessageDialog(null, "�������ӳ�ʱ...");
	     }
		return playerService;
		 
	}
	
}
