package Objcts;
public class Brick {
    public int locatx,locaty; //在第x行第y个容器里
    public int x,y;   //在x,y像素
    public int style;  //砖块类型 1~6
    public boolean moving=false; //是否正在移动
    public int movespeedx=15,movespeedy=15; //x，y轴移动速度
    public int targetx,targety; //目标位置
    public Brick(){
    	this.style=(int)(Math.random()*6)+1;
    }
}
