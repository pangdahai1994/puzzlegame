public class Brick {
    public int locatx,locaty; //�ڵ�x�е�y��������
    public int x,y;   //��x,y����
    public int style;  //ש������ 1~6
    public boolean moving=false; //�Ƿ������ƶ�
    public int movespeedx=0,movespeedy=20; //x��y���ƶ��ٶ�
    public int targetx,targety; //Ŀ��λ��
    Brick(){
    	this.style=(int)(Math.random()*6);
    }
    int x(){
    	return x;
    }
    int y(){
    	return y;
    }
}
