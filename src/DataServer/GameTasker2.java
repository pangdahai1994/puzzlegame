package DataServer;

import java.rmi.RemoteException;

import rmi.RemoteFactory;
import Objcts.*;

public class GameTasker2 {
	public boolean changeuseful=true;  //若为false，则会取消先前移动
	public int unusefulx1,unusefuly1,unusefulx2,unusefuly2;
	public boolean moving=false;  //是否有砖块正在移动
	public boolean added=true;
	public boolean broken=false;    //是否存在未消除的
	public int score=0;                  //加多少分 是否已加
	public static BrickContainer brickc[][]=new BrickContainer[9][9];
	GameTask gm;
	
	public GameTasker2(String ip,String port) throws RemoteException{
		RemoteFactory rm=new RemoteFactory();
		rm.host=ip;
		rm.port=port;
		gm=rm.getRmiGameTaskerService();
		brickc=gm.getbrickc();
	}
	
    public void fresh() throws RemoteException{
    	brickc=gm.getbrickc();
    }
    
    public boolean changing(){
    	boolean moving2=false;
    	for (int i=0;i<9;i++){
    		for (int j=0;j<9;j++){
    			if (((brickc[i][j].brick.locatx!=brickc[i][j].brick.targetx)||(brickc[i][j].brick.locaty!=brickc[i][j].brick.targety)))
    		    moving=true;
    		}
    	}
    	return moving2;
    }
    
    public void changedh2(int i1,int j1,int i2,int j2) throws RemoteException{
    	
    	gm.changedh(i1, j1, i2, j2);
    	
    }
    
    public int mousex(int x){
    	if ((x>600)&&(x<600+60*9))
    	    return (x-600)/60;
    	else
    		return -1;
    }
    
    public int mousey(int y){
    	if ((y>100)&&(y<100+60*9))
    	    return (y-100)/60;
    	else
    		return -1;
    }
}
