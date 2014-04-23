
public class Brick {
    int locatx,locaty; //在第x行第y个容器里
    int x,y;   //在x,y像素
    int style;  //砖块类型 1~6
    boolean moving=false; //是否正在移动
    int movespeedx,movespeedy; //x，y轴移动速度
    int targetx,targety; //目标位置
}
