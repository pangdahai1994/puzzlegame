package DataServer;

import Objcts.*;

public class GameTasker {
	boolean added=true;
	boolean broken=false;    //是否存在未消除的
	int addscore=0;                  //加多少分 是否已加
	public BrickContainer brickc[][]=new BrickContainer[9][9];
	
	GameTasker(){
		for (int i=0;i<=8;i++){
			for (int j=0;j<=8;j++){
				brickc[i][j]=new BrickContainer();
				brickc[i][j]
			}
		}
	}
	
	BrickContainer[][] getbricks(){
		return brickc;
	}
	
	void quchong(){
		while (havexiao()){
			rndnew();
		}
	}
	
    void rndnew(){
    	for (int i=0;i<=9;i++){
    		for (int j=0;j<=9;j++){
    			brickc[i][j]=new BrickContainer();
    		}
    	}
    }
    
    boolean havexiao(){
    	broken=false;
    	for (int i=0;i<=7;i++){
    		for (int j=0;j<=7;j++){
    			BrickContainer p=new BrickContainer();

    		}
    	}
    }
    
    void breakbrick(int[][] brick){
    	broken=false;
    	for (int i=0;i<=7;i++){
    		for (int j=0;j<=7;j++){
    			if (Math.abs(brick[i][j])==Math.abs(brick[i+1][j])&&(Math.abs(brick[i][j])==Math.abs(brick[i+2][j]))){
    				broken=true;
    				brick[i][j]=-Math.abs(brick[i][j]);
    				brick[i+2][j]=-Math.abs(brick[i][j]);
    				brick[i+1][j]=-Math.abs(brick[i][j]);
    			}
    			if (Math.abs(brick[i][j])==Math.abs(brick[i][j+1])&&(Math.abs(brick[i][j+2])==Math.abs(brick[i][j]))){
    				broken=true;
    				brick[i][j]=-Math.abs(brick[i][j]);
    				brick[i][j+1]=-Math.abs(brick[i][j]);
    				brick[i][j+2]=-Math.abs(brick[i][j]);
    			}
    		}
    	}
    caculscore(brick);
    }
    
    void caculscore(int[][] brick){
    	int e=0;
    	for (int i=0;i<=9;i++){
    		for (int j=0;j<=9;j++){
    			if (brick[i][j]<0){
    				addscore+=1;
    			}
    		}
    	}
    	addscore+=(int)Math.pow(100, (e+10)/13);
    	newbrick(brick);
    }
    
    void newbrick(int[][] brick){
    	int e=0;
    	for (int i=0;i<=9;i++){
    		for (int j=0;j<=9;j++){
    			if (brick[i][j]<0){
    				addscore+=1;
    			}
    		}
    	}
    	addscore+=(int)Math.pow(100, (e+10)/13);
    }
}
