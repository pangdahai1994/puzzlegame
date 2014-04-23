package DataServer;

public class GameTasker {
	boolean added=true;
	boolean broken=false;    //是否存在未消除的
	int addscore=0;                  //加多少分 是否已加
    void rndnew(int[][] brick){
    	for (int i=0;i<=9;i++){
    		for (int j=0;j<=9;j++){
    			brick[i][j]=(int)(Math.random()*6)+1;
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
