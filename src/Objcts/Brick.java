package Objcts;
public class Brick {
    public int locatx,locaty; //�ڵ�x�е�y��������
    public int x,y;   //��x,y����
    public int style;  //ש������ 1~6
    public boolean moving=false; //�Ƿ������ƶ�
    public int movespeedx=15,movespeedy=15; //x��y���ƶ��ٶ�
    public int targetx,targety; //Ŀ��λ��
    public Brick(){
    	this.style=(int)(Math.random()*6)+1;
    }
}
