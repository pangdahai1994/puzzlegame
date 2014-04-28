package DataServer;

import Objcts.*;

public class GameTasker {
	public boolean changeuseful=true;  //若为false，则会取消先前移动
	public int unusefulx1,unusefuly1,unusefulx2,unusefuly2;
	public boolean moving=false;  //是否有砖块正在移动
	public boolean added=true;
	public boolean broken=false;    //是否存在未消除的
	public int score=0;                  //加多少分 是否已加
	public static BrickContainer brickc[][]=new BrickContainer[9][9];
	
	public GameTasker(){
		rndnew();
		quchong();
		guiwei();
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
    
    public void move(int i1,int j1,int i2,int j2){
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
   
    public void changedh(int i1,int j1,int i2,int j2){
    	Brick tempbrick;
    	tempbrick=brickc[i1][j1].brick;
    	brickc[i1][j1].brick=brickc[i2][j2].brick;
    	brickc[i2][j2].brick=tempbrick;
    	
    	brickc[i1][j1].brick.targetx=brickc[i2][j2].brick.locatx;
    	brickc[i1][j1].brick.targety=brickc[i2][j2].brick.locaty;
    	brickc[i2][j2].brick.targetx=brickc[i1][j1].brick.locatx;
    	brickc[i2][j2].brick.targety=brickc[i1][j1].brick.locaty;
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
    	
    	for (int i=8;i>=0;i--){
    		for (int j=0;j<=8;j++){
    			if (brickc[i][j].brick.style<0) score++;
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
    
    void caculscore(){
    	int e=0;
    	for (int i=0;i<=9;i++){
    		for (int j=0;j<=9;j++){
    			if (brickc[i][j].brick.style<0){
    				score+=1;
    			}
    		}
    	}
    	//addscore+=(int)Math.pow(100, (e+10)/13);
    	//newbrick();
    }
    
    void newbrick(){
    	int[] e=new int[9];
    	for (int i=8;i>=0;i--){
    		for (int j=0;j<=9;j++){
    			if (brickc[i][j].brick.style<0){
    				//e[]
    			}
    		}
    	}
    	//addscore+=(int)Math.pow(100, (e+10)/13);
    }
}
