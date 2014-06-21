package DataServer;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.Remote;
import java.util.Timer;

import Objcts.*;
import UI.GameFrame;

import java.rmi.RemoteException;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;
public class GameTasker extends UnicastRemoteObject implements GameTask,Serializable {
	public GameTasker() throws RemoteException {
		    rndnew();
		    quchong();
		    guiwei();
	}

	//private static final long serialVersionUID = -3286564461647015367L;
	public boolean changeuseful=true;  //若为false，则会取消先前移动
	public int unusefulx1,unusefuly1,unusefulx2,unusefuly2;
	public boolean moving=false;  //是否有砖块正在移动
	public boolean added=true;
	public boolean broken=false;    //是否存在未消除的
	public int score=0,godtimebreaklast=1000;                  //加多少分 是否已加
	public BrickContainer brickc[][]=new BrickContainer[9][9];
	public GameTasker zhuji;
	public boolean useitem1,useitem2,useitem3;
	public int item1=2,item2=2,item3=2,hit=0,godhit=0;
	public boolean godmodel=false,usedaojuA,usedaojuB,usedaojuC;
	public int lianjitime=1000;
	public int wanjia=1;
	public boolean started=false;
	public int timeleft=60000,tishitime=3000,tishitimeleft=3000;
	public boolean tishi=false;
	public int[][] tishihang=new int[8][9];
	public int[][] tishilie=new int[9][8];
	
	public void addwanjia(){
		wanjia++;
	}
	
	public BrickContainer[][] getbrickc(){
		return brickc;
	}
	
	public GameTasker gettasker() throws RemoteException{
		tishihang=scanDispelLie(getbricknum());
		tishilie=scanDispelLie(getbricknum());
        return this;
	}
	
	public void guiwei(){
		for (int i=0;i<=8;i++){
    		for (int j=0;j<=8;j++){
    			brickc[i][j].brick.locatx=60*i;
    			brickc[i][j].brick.locaty=60*j;
    			brickc[i][j].brick.targetx=60*i;
    			brickc[i][j].brick.targety=60*j;
    		}
    	}
	}
	
	public boolean bofangwancheng(){ //判断是否有动画在播放
		for (int i=0;i<=8;i++){
    		for (int j=0;j<=8;j++){
    			if (brickc[i][j].brick.locaty!=brickc[i][j].brick.targety){
    				return false;
    			}
    		}
    	}
		return true;
	}
	
	public void quchong(){
		while (havexiao()){
			rndnew();
		}
	}
	
	public void rndnew(){
    	for (int i=0;i<=8;i++){
    		for (int j=0;j<=8;j++){
    			brickc[i][j]=new BrickContainer();
    		}
    	}
    }
    
    public boolean havexiao(){
    	for (int i=0;i<=8;i++){
    		for (int j=0;j<=6;j++){
    			if ((brickc[i][j].brick.style==brickc[i][j+1].brick.style)&&(brickc[i][j+2].brick.style==brickc[i][j].brick.style)){
    				return true;
    			}
    		}
    	}//竖过来消除
    	for (int i=0;i<=6;i++){
    		for (int j=0;j<=8;j++){
    			if ((brickc[i][j].brick.style==brickc[i+1][j].brick.style)&&(brickc[i][j].brick.style==brickc[i+2][j].brick.style)){
    				return true;
    			}
    		}
    	}//横过来
    	return false;
    }
    
    public void movedonghua(int i1,int j1,int i2,int j2){
    	brickc[i1][j1].brick.targetx=brickc[i2][j2].brick.locatx;
    	brickc[i1][j1].brick.targety=brickc[i2][j2].brick.locaty;
    	brickc[i2][j2].brick.targetx=brickc[i1][j2].brick.locatx;
    	brickc[i2][j2].brick.targety=brickc[i1][j2].brick.locaty;
    }
    
    public void move(int i1,int j1,int i2,int j2) throws RemoteException{
    	change(i1,j1,i2,j2);
    	if (havexiao()){
    		breakbrick();
    	}else{
    		change(i1,j1,i2,j2);
    	}
    }
    
    public void change(int i1,int j1,int i2,int j2){
    	int k;
    	k=brickc[i1][j1].brick.style;
    	brickc[i1][j1].brick.style=brickc[i2][j2].brick.style;
    	brickc[i2][j2].brick.style=k;
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
   
    public void setfalsechange(int i1, int j1, int i2, int j2){
    	changeuseful=false;
    	
		unusefulx1=i1;
		unusefuly1=j1;
		unusefulx2=i2;
		unusefuly2=j2;
    }
    
    public void changedh(int i1,int j1,int i2,int j2){
    	
    	String clienthost;
		try {
			clienthost = RemoteServer.getClientHost();

    	InetAddress ia;
			ia = java.net.InetAddress.getByName(clienthost);
    	    String clentIp=ia.getHostAddress();
		}catch(Exception e){
    		
    	}
    	
	if (started){	
    	if (!changing()&&!havexiao()){
    	Brick tempbrick;
    	tempbrick=brickc[i1][j1].brick;
    	brickc[i1][j1].brick=brickc[i2][j2].brick;
    	brickc[i2][j2].brick=tempbrick;
    	
    	brickc[i1][j1].brick.targetx=brickc[i2][j2].brick.locatx;
    	brickc[i1][j1].brick.targety=brickc[i2][j2].brick.locaty;
    	brickc[i2][j2].brick.targetx=brickc[i1][j1].brick.locatx;
    	brickc[i2][j2].brick.targety=brickc[i1][j1].brick.locaty;
    	}
    	
		if (havexiao()){
			if (useitem1) lianjitime=2000;
			godtimebreaklast=lianjitime;
		}
    	
    	if (havexiao()){
    		moving=true;
    	}else{
    		changeuseful=false;
    		unusefulx1=i1;
    		unusefuly1=j1;
    		unusefulx2=i2;
    		unusefuly2=j2;
    	}
	}
    }
   
    
    public void changecancel(){
    	Brick tempbrick;
    	tempbrick=brickc[unusefulx1][unusefuly1].brick;
    	brickc[unusefulx1][unusefuly1].brick=brickc[unusefulx2][unusefuly2].brick;
    	brickc[unusefulx2][unusefuly2].brick=tempbrick;
    	
    	brickc[unusefulx1][unusefuly1].brick.targetx=brickc[unusefulx2][unusefuly2].brick.locatx;
    	brickc[unusefulx1][unusefuly1].brick.targety=brickc[unusefulx2][unusefuly2].brick.locaty;
    	brickc[unusefulx2][unusefuly2].brick.targetx=brickc[unusefulx1][unusefuly1].brick.locatx;
    	brickc[unusefulx2][unusefuly2].brick.targety=brickc[unusefulx1][unusefuly1].brick.locaty;
    	changeuseful=true;
    }
    
    public void breakbrick(){
    	tishitimeleft=tishitime;
    	//System.out.print(getScore(getbricknum()));
    	if (godmodel){
    		godhit++;
    	}else{
    		hit++;
    		if (hit>=4) godmodel=true;
    	}
    	broken=false;
    	for (int i=0;i<=8;i++){
    		for (int j=0;j<=6;j++){
    			if (Math.abs(brickc[i][j].brick.style)==Math.abs(brickc[i][j+1].brick.style)&&(Math.abs(brickc[i][j+2].brick.style)==Math.abs(brickc[i][j].brick.style))){
    				broken=true;
    				brickc[i][j].brick.style=-Math.abs(brickc[i][j].brick.style);
    				brickc[i][j+1].brick.style=-Math.abs(brickc[i][j].brick.style);
    				brickc[i][j+2].brick.style=-Math.abs(brickc[i][j].brick.style);
    			}
    		}
    	}
    	for (int i=0;i<=6;i++){
    		for (int j=0;j<=8;j++){
    			if (Math.abs(brickc[i][j].brick.style)==Math.abs(brickc[i+1][j].brick.style)&&(Math.abs(brickc[i][j].brick.style)==Math.abs(brickc[i+2][j].brick.style))){
    				broken=true;
    				brickc[i][j].brick.style=-Math.abs(brickc[i][j].brick.style);
    				brickc[i+2][j].brick.style=-Math.abs(brickc[i][j].brick.style);
    				brickc[i+1][j].brick.style=-Math.abs(brickc[i][j].brick.style);
    			}
    		}
    	}
    	
    	/*for (int i=8;i>=0;i--){
    		for (int j=0;j<=8;j++){
    	        System.out.print(brickc[i][j].brick.style+" ");
    		    }
    		System.out.println();
    		}*/
    	if (godmodel){
            if (useitem2)
    	        score+=getScore(getbricknum())*2.2;
            else
        	    score+=getScore(getbricknum())*2;
    	}else{

            if (useitem2)
    	        score+=getScore(getbricknum())*1.1;
            else
        	    score+=getScore(getbricknum());
    	}
        
    	for (int i=8;i>=0;i--){
    		for (int j=0;j<=8;j++){
    			//if (brickc[i][j].brick.style<0) score++;
    		    if (brickc[i][j].brick.style<0){
    			    for (int k=j;k>0;k--){
    			    	brickc[i][k].brick=brickc[i][k-1].brick;
    			    	brickc[i][k].brick.targety+=60;
    			    }
    			    brickc[i][0].brick=new Brick();
    			    brickc[i][0].brick.locatx=60*i;
        			brickc[i][0].brick.locaty=brickc[i][1].brick.locaty-60;
        			if (brickc[i][0].brick.locaty==0){
        				brickc[i][0].brick.locaty=-60;
        			}
        			brickc[i][0].brick.targetx=60*i;
        			brickc[i][0].brick.targety=0;
    		    }
    		}
    	}
    //caculscore();
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
    
    public void caculscore(){
    	//int e=0;
    	for (int i=0;i<=9;i++){
    		for (int j=0;j<=9;j++){
    			if (brickc[i][j].brick.style<0){
    				//score+=1;
    			}
    		}
    	}
    	//addscore+=(int)Math.pow(100, (e+10)/13);
    	//newbrick();
    }
    
    public void newbrick(){
    	for (int i=8;i>=0;i--){
    		for (int j=0;j<=9;j++){
    			if (brickc[i][j].brick.style<0){
    				//e[]
    			}
    		}
    	}
    	//addscore+=(int)Math.pow(100, (e+10)/13);
    }
    
    public int[][] getbricknum(){
    	int[][] num=new int[9][9];
    	for (int i=0;i<9;i++){
    		for (int j=0;j<9;j++){
    		    num[i][j]=brickc[i][j].brick.style;
       		}
    	}
    	return num;
    }
    
    public int getScore(int[][] num) {
		int score = 0;
		for (int i = 0; i < 9; i++) {
			score += getHangScore(num[i]);
		}
		// 竖向转为横向
		int[][] num1 = new int[9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				num1[i][j] = num[j][i];
			}
		}
		for (int i = 0; i < 9; i++) {
			score += getHangScore(num1[i]);
		}
		System.out.print(score);
		return(score);
		
	}

	public int getHangScore(int[] s) {
		int count = 0;
		int k = 0;
		
		for (int i = 0; i < 8; i++) {
			if((i==0)&&(s[i] < 0)){
				if((s[0]==s[1])&&(s[1]==s[2])&&(s[2]!=s[3])){
					count+=1;
					i+=2;
				}
				if((s[0]==s[1])&&(s[1]==s[2])&&(s[2]==s[3])&&(s[3]!=s[4])){
					count+=2;
					i+=3;
				}
				if((s[0]==s[1])&&(s[1]==s[2])&&(s[2]==s[3])&&(s[4]!=s[5])){
					count+=5;
					i+=4;
				}
			}
			if ((s[i] < 0) && (i >= 1)) {
				if ((i < 6)&&(s[i] == s[i + 1]) && (s[i + 1] == s[i + 2]) && (s[i + 2] != s[i + 3])) {
						count += 1;
						i+=2;
					}
				if ((i == 6)&&(s[i] == s[i + 1]) && (s[i + 1] == s[i + 2])) {
					count += 1;
					i+=2;
				}	
				
				if ((i <= 5)&&(s[i] == s[i + 1]) && (s[i + 1] == s[i + 2])
						&& (s[i + 2] == s[i + 3]) 
						&& (s[i] != s[i - 1])) {
					if (i == 5) {
						count += 2;
					} else if ((i < 5) && (s[i + 3] != s[i + 4])) {
						count += 2;
					}
					i+=3;
				}
				if ((i <= 4)&& (s[i] == s[i + 1]) && (s[i + 1] == s[i + 2])
						&& (s[i + 2] == s[i + 3]) && (s[i + 3] == s[i + 4])
						 && (s[i] != s[i - 1])) {
					if (i == 4) {
						count += 5;
					} else if ((i < 4) && (s[i + 4] != s[i + 5])) {
						count += 5;
					}
					i+=4;
				}

			}
			
		}
        return 100*count;
	}

    
    
    public static boolean scanHang(int[] s) {
		int count = 0;
		int k = 0;
		for (int i = 0; i < 7; i++) {
			if ((s[i] == s[i + 1]) && (s[i] > 0)) {
				count++;
				if (s[i + 1] != s[i + 2]) {
					if (count >= 2) {
						k++;
						count = 0;
					}
					count = 0;
				}
			}
		}
		if (k == 0)
			return false;
		else
			return true;
	}

	public static boolean cheek(int[][] s) {
		int[] s1 = new int[9];
		int count = 0;
		for (int i = 0; i < 9; i++) {
			s1 = s[i];
			if (scanHang(s1)) {
				count++;
			}
		}
		int[][] num = new int[9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				num[i][j] = s[j][i];
			}
		}
		for (int i = 0; i < 9; i++) {
			s1 = num[i];
			if (scanHang(s1)) {
				count++;
			}
		}
		if (count == 0)
			return false;
		else
			return true;
	}

	public int[][] scanDispelLie(int[][] s) {
		int[][] retmp = new int[9][8];
		int[][] ret = new int[8][9];
		int[][] num = new int[9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				num[i][j] = s[j][i];
			}
		}
			retmp = scanDispelHang(num);
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 9; j++) {
				ret[i][j] = retmp[j][i];
			}
		}
		return ret;
	}

	public int[][] scanDispelHang(int[][] s) {
		int a = 0;
		int[][] ret1 = new int[9][8];
		if (!cheek(s)) {
			for (int k = 0; k < 9; k++) {
				int[] s0 = s[k];
				int[] ret = { 1, 1, 1, 1, 1, 1, 1, 1 };
				for (int i = 0; i < 8; i++) {		
					int[][] stmp = s;
					a = s0[i];
					s0[i] = s0[i + 1];
					s0[i + 1] = a;
					stmp[k] = s0;
					if (cheek(stmp)) {
						ret[i] = 0;
					}
					ret1[k] = ret;
					a = s0[i];
					s0[i] = s0[i + 1];
					s0[i + 1] = a;
				}
			}
		} else {
			System.out.println("先消除");
		}
		return ret1;
	}

}
