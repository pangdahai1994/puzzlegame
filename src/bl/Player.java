package bl;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import po.PlayerPO;
import blservice.PlayerBLService;
import data.PlayerData;
import dataservice.PlayerDataService;
public class Player  extends UnicastRemoteObject implements  Serializable,PlayerBLService {

	/**
	 * 
	 */
	
    
	private static final long serialVersionUNAME = 1L;

	public Player() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public PlayerPO getpo(String name, String password)throws RemoteException{
		PlayerDataService uData = new PlayerData();
		PlayerPO Playerpo =uData.find(name);
		if(checkPassword(name, password))
		    return Playerpo;
		else return null;
	}
	
	//]��½,������Ϣ
	/* (non-Javadoc)
	 * @see bl.PlayerBLService#login(java.lang.String, java.lang.String)
	 */
	@Override
	public String login(String name, String password)throws RemoteException {
		// TODO Auto-generated method stub
	
		String message="";
	
		try {
			PlayerDataService uData = new PlayerData();
			PlayerPO Playerpo =uData.find(name);
			if(Playerpo==null){
				message="�û���������";
				//�û������ڣ���½ ʧ��
			}else{
				if(!checkPassword(name, password)){
					message="���벻��ȷ";
				}
				}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
	}

	//ǰ���������û�һ������
	/* (non-Javadoc)
	 * @see bl.PlayerBLService#checkPassword(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean checkPassword(String NAME, String password) throws RemoteException{
		// TODO Auto-generated method stub
		
		String correctPassword;
		boolean flag=false;
		
		try {
			
			PlayerDataService uData = new PlayerData();
			PlayerPO Playerpo =uData.find(NAME);
			correctPassword=Playerpo.getPassword();
			if(password.equals(correctPassword)){
				flag=true;
			}
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
		
	}

	/* (non-Javadoc)
	 * @see bl.PlayerBLService#update(po.PlayerPO, int, int, float)
	 */
	@Override
	public void update(PlayerPO po){
		
		try {
			
			PlayerDataService uData = new PlayerData();;
			PlayerPO Playerpo =uData.find(po.getName());
			
			int gameCount=po.getGameCount();
			po.setGameCount(gameCount++);
			int avgScore=(po.getAvgScore()* gameCount+po.getScore())/( gameCount+1);
			po.setAvgScore(avgScore);
			if(hitCount>po.getMaxHitCount()) po.setMaxHitCount(hitCount);
			if(score>po.getMaxScore()) po.setMaxScore(score);
			if(godtime>po.getGodtime()) po.setMaxGodtime(godtime);
			uData.update(po);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
}


}



