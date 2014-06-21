package DataServer;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Timer;

import Objcts.*;


public interface GameTask extends Remote {

	public BrickContainer[][] getbrickc()throws RemoteException;
	public void changedh(int i1, int j1, int i2, int j2) throws RemoteException;
	public GameTask gettasker()throws RemoteException;
}
