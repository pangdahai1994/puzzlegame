package UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
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
	static int jindu=0;
    static int state=0;
    static int bg=1,jia=1,change=0,playernum=0,mousex,mousey;
    static ZFrame mainframe;
    static Timer timer2;
    static MediaPlayer mp;
    static BufferedImage Mimage=new BufferedImage(1366,768,BufferedImage.TYPE_INT_RGB);
    static ArrayList<String> players=new ArrayList<String>(3);
	public static void main(String[] args) throws IOException {
		//TODO Auto-generated method stub
        //displaymode dpm=new displaymode();
        //dpm.setto1024();
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
		        if (jindu<1080) jindu=1080;
		        if ((0<change)&&(change<384)) change=383;
		        if ((state==1)&&(mousex<790)&&(mousex>480)&&(mousey>480)&&(mousey<585)) System.exit(0);
		        if ((state==1)&&(mousex<790)&&(mousex>480)&&(mousey>280)&&(mousey<385)){
		        	//继续游戏
		        	timer2.cancel();
		        }
		        if ((state==1)&&(mousex<790)&&(mousex>480)&&(mousey>80)&&(mousey<185)){
		        	//开始游戏
		        	timer2.cancel();
		        	state=2;
		        	GameFrame gf=new GameFrame();
		        	
		        }
   		     }
   	    }); 
        startframe0();
	}
    
	public static void startframe0(){
		mp = new MediaPlayer("1.mp3");
		mainframe.paint("bg.bmp", 0, 0);
		Timer timer = new Timer();
		Graphics painter=mainframe.zpanel.getGraphics();
		painter.setColor(Color.green);
		painter.fill3DRect(120, 674, 1082, 70, false);
		painter.setColor(Color.white);
		painter.setFont(new Font("宋体",Font.ITALIC,30));
		painter.drawString("载入中...", 600, 650);
		mainframe.paint("zairu.png",110,666);
        timer.schedule(new MyTask1(),10, 20);
	}
	
	public static void changeframe(){
		mainframe.zpanel.save();
		BufferedImage img=mainframe.zpanel.screenshot;
		
	}
	
	public static void startframe2(){
		mp.stopmedia();
		changeframe();
        mp = new MediaPlayer("bgm_ivent8.ogg");
		mainframe.paint("bg.bmp", 0, 0);
		Graphics painter=mainframe.zpanel.getGraphics();
		painter.setColor(Color.green);
		painter.fill3DRect(120, 674, 1082, 70, false);
		painter.setColor(Color.white);
		painter.setFont(new Font("宋体",Font.ITALIC,30));
		painter.drawString("载入中...", 600, 650);
		mainframe.paint("zairu.png",110,666);
	}
	
	static class MyTask1 extends java.util.TimerTask{ 
        public void run() {
        	Graphics painter=mainframe.zpanel.getGraphics();
        	painter.setColor(Color.blue);
        	painter.fill3DRect(120+jindu, 674, 4, 70, false);
        	jindu+=4;
        	mainframe.paint("zairu.png",110,666);
        	if (jindu>1080) {
        		this.cancel();
        		MainFrame.startframe1();
        	}
        	
        }
    } 
	public static void startframe1(){
		mainframe.zpanel.save();
		Timer timer1 = new Timer();
		timer1.schedule(new movebg(),10, 20);
		
		while (change<384)
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		state=1;
		timer2 = new Timer();
		timer2.schedule(new drawbg(),10, 70);
	}
	
	static class movebg extends java.util.TimerTask{ 
        public void run() { 
        	if (change<384){
        		mainframe.paint(mainframe.zpanel.screenshot,0,0,0,change,1366,384-change);
        		mainframe.paint("bg2\\bg (1).jpg",0,384-change,0,384-change,1366,2*change+1);
        		mainframe.paint(mainframe.zpanel.screenshot,0,384+change,0,384,1366,384-change);
        		change+=8;
        	}
        }
    } 
	static class drawbg extends java.util.TimerTask{ 
        public void run() { 
        	BufferedImage image;
			try {
				image = ImageIO.read(new FileInputStream("bg2\\bg ("+bg+").jpg"));
				Mimage.getGraphics().drawImage(image, bg*2,0, 1366,768, null);
				image = ImageIO.read(new FileInputStream("开始游戏.png"));
				Mimage.getGraphics().drawImage(image, 535,105, null);
				if ((mousex<790)&&(mousex>480)&&(mousey>80)&&(mousey<185))
				    image = ImageIO.read(new FileInputStream("按钮1.png"));
				else
					image = ImageIO.read(new FileInputStream("按钮2.png"));
				Mimage.getGraphics().drawImage(image, 400,0, null);
				
				image = ImageIO.read(new FileInputStream("继续游戏.png"));
				Mimage.getGraphics().drawImage(image, 535,305, null);
				if ((mousex<790)&&(mousex>480)&&(mousey>280)&&(mousey<385))
				    image = ImageIO.read(new FileInputStream("按钮1.png"));
				else
					image = ImageIO.read(new FileInputStream("按钮2.png"));
				Mimage.getGraphics().drawImage(image, 400,200, null);
				
				image = ImageIO.read(new FileInputStream("结束游戏.png"));
				Mimage.getGraphics().drawImage(image, 535,505, null);
				if ((mousex<790)&&(mousex>480)&&(mousey>480)&&(mousey<585))
				    image = ImageIO.read(new FileInputStream("按钮1.png"));
				else
					image = ImageIO.read(new FileInputStream("按钮2.png"));
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
