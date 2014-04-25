package UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;

import javax.imageio.ImageIO;

import UI.MediaPlayer.PlayThread;

public class MainFrame {
	//static int jindu=0;
	static int x1=0,y1=0; //开场动画
    static int state=0;
    static int bg=1,jia=1,change=0,playernum=0,mousex,mousey;
    static ZFrame mainframe;
    static Timer timer2;
    static MediaPlayer mp;
    static BufferedImage Mimage=new BufferedImage(1366,768,BufferedImage.TYPE_INT_RGB);
    static BufferedImage[] button=new BufferedImage[10];
    static BufferedImage[] start1=new BufferedImage[328];
    static BufferedImage[] start2=new BufferedImage[126];
    static ArrayList<String> players=new ArrayList<String>(3);
	public static void main(String[] args) throws IOException {
		//TODO Auto-generated method stub
        //displaymode dpm=new displaymode();
        //dpm.setto1024();
		//new GameFrame();
		button[0] = ImageIO.read(new FileInputStream("按钮1.png"));
		button[1] = ImageIO.read(new FileInputStream("按钮2.png"));
		button[2] = ImageIO.read(new FileInputStream("开始游戏.png"));
		button[3] = ImageIO.read(new FileInputStream("继续游戏.png"));
		button[4] = ImageIO.read(new FileInputStream("结束游戏.png"));
		for (int i=1;i<=327;i++){
			if (i<10){
				start1[i]=ImageIO.read(new FileInputStream("ui\\ui1\\bg00"+i+".jpg"));
        	}else if (i<100){
        		start1[i]=ImageIO.read(new FileInputStream("ui\\ui1\\bg0"+i+".jpg"));
        	}else{
        		start1[i]=ImageIO.read(new FileInputStream("ui\\ui1\\bg"+i+".jpg"));
        	}
		}
		for (int i=1;i<=125;i++){
			if (i<10){
				start2[i]=ImageIO.read(new FileInputStream("ui\\ui2\\bg00"+i+".jpg"));
        	}else if (i<100){
        		start2[i]=ImageIO.read(new FileInputStream("ui\\ui2\\bg0"+i+".jpg"));
        	}else{
        		start2[i]=ImageIO.read(new FileInputStream("ui\\ui2\\bg"+i+".jpg"));
        	}
		}
		
        mainframe=new ZFrame();
        mainframe.FullScreen();
        BufferedReader br=new BufferedReader(new FileReader("playerInfo\\players"));
        String line=null;
        while ((line=br.readLine())!=null){
        	playernum++;
        	players.add(line);
        }
        br.close();
        
        mainframe.addMouseMotionListener(new MouseAdapter(){
			 public void mouseMoved(MouseEvent event){
				 mousex=event.getX();
				 mousey=event.getY();
			 }
   	    });
        mainframe.addMouseListener(new MouseAdapter(){
   		     public void mousePressed(MouseEvent event){
		       // if (jindu<1080) jindu=1080;
		        if (state==0) x1=325;
		        
		        if ((state==1)&&(mousex<790)&&(mousex>480)&&(mousey>480)&&(mousey<585)) System.exit(0);
		        if ((state==1)&&(mousex<790)&&(mousex>480)&&(mousey>280)&&(mousey<385)){
		        	//继续游戏
		        	timer2.cancel();
		        }
		        if ((state==1)&&(mousex<790)&&(mousex>480)&&(mousey>80)&&(mousey<185)){
		        	//开始游戏
		        	timer2.cancel();
		        	state=2;
		        	try {
						GameFrame gf=new GameFrame();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        	
		        }
   		     }
   	    }); 
        startframe0();
	}
    
	public static void startframe0(){
		mp = new MediaPlayer("ui\\mp3\\Sound1.mp3");
		//mainframe.paint("bg.bmp", 0, 0);
		Timer timer = new Timer();
	/*	Graphics painter=mainframe.zpanel.getGraphics();
		painter.setColor(Color.green);
		painter.fill3DRect(120, 674, 1082, 70, false);
		painter.setColor(Color.white);
		painter.setFont(new Font("宋体",Font.ITALIC,30));
		painter.drawString("载入中...", 600, 650);
		mainframe.paint("zairu.png",110,666);*/
        timer.schedule(new MyTask1(),0, 15);
	}
	
	public static void changeframe(){
		mainframe.zpanel.save();
		BufferedImage img=mainframe.zpanel.screenshot;
		
	}
	
	public static void startframe2(){
		//mp.stopmedia();
		//changeframe();
        //mp = new MediaPlayer("bgm_ivent8.ogg");
		//mainframe.paint("bg.bmp", 0, 0);
		//Graphics painter=mainframe.zpanel.getGraphics();
		//painter.setColor(Color.green);
		//painter.fill3DRect(120, 674, 1082, 70, false);
		//painter.setColor(Color.white);
		//painter.setFont(new Font("宋体",Font.ITALIC,30));
		//painter.drawString("载入中...", 600, 650);
		//mainframe.paint("zairu.png",110,666);
	}
	
	static class MyTask1 extends java.util.TimerTask{ 
        public void run() {
        	BufferedImage img=null;
        	try {
        	x1++;
        	Mimage.getGraphics().drawImage(start1[x1],0,0,1366,768,null);
        	mainframe.paint(Mimage);
        	if (x1==327){
        		startframe1()
        		this.cancel();
        	}
        	}catch(Exception e){
        		e.printStackTrace();
        	}
        }
    } 
	public static void startframe1(){
		mainframe.zpanel.save();
		//Timer timer1 = new Timer();
		//timer1.schedule(new movebg(),10, 20);
		state=1;
		timer2 = new Timer();
		timer2.schedule(new drawbg(),0, 25);
	}
	
	static class drawbg extends java.util.TimerTask{ 
        public void run() { 
        	BufferedImage image=null;
			try {
				y1++;
				if ((mp.hasStop)||(!mp.path.equals("ui\\mp3\\Sound2.mp3"))) {
					mp.isStop=true;
					mp=new MediaPlayer("ui\\mp3\\Sound2.mp3"); 
				}
	        	if (y1<10){
	        		image = ImageIO.read(new FileInputStream("ui\\ui2\\bg00"+y1+".jpg"));
	        	}else if (y1<100){
	        		image = ImageIO.read(new FileInputStream("ui\\ui2\\bg0"+y1+".jpg"));
	        	}else if (y1<=125){
	        		image = ImageIO.read(new FileInputStream("ui\\ui2\\bg"+y1+".jpg"));
	            }
	        	if (y1==125) y1=0;
	        	Mimage.getGraphics().drawImage(image, 0,0,1366,768, null);
	        	
				image = button[2];
				Mimage.getGraphics().drawImage(image, 535,105, null);
				if ((mousex<790)&&(mousex>480)&&(mousey>80)&&(mousey<185))
				    image = button[0];
				else
					image = button[1];
				Mimage.getGraphics().drawImage(image, 400,0, null);
				
				image = button[3];
				Mimage.getGraphics().drawImage(image, 535,305, null);
				if ((mousex<790)&&(mousex>480)&&(mousey>280)&&(mousey<385))
				    image = button[0];
				else
					image = button[1];
				Mimage.getGraphics().drawImage(image, 400,200, null);
				
				image = button[4];
				Mimage.getGraphics().drawImage(image, 535,505, null);
				if ((mousex<790)&&(mousex>480)&&(mousey>480)&&(mousey<585))
				    image = button[0];
				else
					image = button[1];
				Mimage.getGraphics().drawImage(image, 400,400, null);
				
				
				mainframe.paint(Mimage);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	if ((bg==1)&&(jia==0)){
        		jia=1;
        	}
        	if ((bg==52)&&(jia==1)){
        		jia=0;
        	}
        	if (jia==1) bg++;
        	if (jia==0) bg--;
        }
    } 
}
