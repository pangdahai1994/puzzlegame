package UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
import javax.swing.JTextField;

import UI.MediaPlayer.PlayThread;

public class MainFrame {
	//static int jindu=0;
	static Font font=new Font("宋体",Font.PLAIN,16);
	static int framey=-700;
	static int x1=0,y1=0; //开场动画
    static int state=0;
    static int bg=1,jia=1,change=0,playernum=0,mousex,mousey;
    static ZFrame mainframe;
    static Timer timer2;
    static MediaPlayer mp;
    static BufferedImage Mimage=new BufferedImage(1366,768,BufferedImage.TYPE_INT_RGB);
    static BufferedImage[] button=new BufferedImage[10];
    static ArrayList<String> players=new ArrayList<String>(3);
	public static void main(String[] args) throws IOException {
		//TODO Auto-generated method stub
        //displaymode dpm=new displaymode();
        //dpm.setto1024();
		//new GameFrame();
		button[0] = ImageIO.read(new FileInputStream("ui\\frame.png"));
		button[1] = ImageIO.read(new FileInputStream("ui\\button1.png"));
		button[2] = ImageIO.read(new FileInputStream("ui\\button2.png"));
		button[3] = ImageIO.read(new FileInputStream("ui\\button3.png"));
		button[4] = ImageIO.read(new FileInputStream("ui\\button4.png"));
		button[5] = ImageIO.read(new FileInputStream("ui\\frame.png"));
		
        mainframe=new ZFrame();
        mainframe.setAlwaysOnTop(true);
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
        
        mainframe.addKeyListener(new KeyListener(){

        	   public void keyPressed(KeyEvent e) {
        	    // TODO 自动生成方法存根
        	       if (e.getKeyCode()==27){
        	    	   if (state==0) x1=327;
        	       };
        	   }

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}


			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
        });
        mainframe.addMouseListener(new MouseAdapter(){
   		     public void mouseClicked(MouseEvent event){
		       // if (jindu<1080) jindu=1080;
		        
		        if ((state==1)&&(mousex<1240)&&(mousex>918)&&(mousey>533)&&(mousey<592)) System.exit(0);
		        if ((state==1)&&(mousex<1240)&&(mousex>918)&&(mousey>364)&&(mousey<432)){
		        	//开始游戏
		        	timer2.cancel();
		        }
		        if ((mousex<1074)&&(mousex>918)&&(mousey>453)&&(mousey<502)){
		        	//单机游戏
		        	//timer2.cancel();
		        	//state=2;
		        	try {
						GameFrame gf=new GameFrame();
						gf.setAlwaysOnTop(true);
						mainframe.setAlwaysOnTop(false);
			        	mainframe.removeAll();
			        	mainframe.setVisible(false);
					} catch (Exception e) {
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
		Timer timer = new Timer();
        timer.schedule(new MyTask1(),0, 40);
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
		//painter.setColor(Color.white);
		//painter.setFont(new Font("宋体",Font.ITALIC,30));
		//painter.drawString("载入中...", 600, 650);
		mainframe.paint("zairu.png",110,666);
	}
	
	static class MyTask1 extends java.util.TimerTask{ 
        public void run() {
        	BufferedImage img=null;
        	try {
        	x1++;
        	if ((mp.hasStop)&&(x1>100)) {
        		mp=new MediaPlayer("ui\\mp3\\Sound2.mp3"); 
        	}
        	if (x1<10){
				img=ImageIO.read(new FileInputStream("ui\\ui1\\bg00"+x1+".jpg"));
        	}else if (x1<100){
        		img=ImageIO.read(new FileInputStream("ui\\ui1\\bg0"+x1+".jpg"));
        	}else if (x1<=327){
        		img=ImageIO.read(new FileInputStream("ui\\ui1\\bg"+x1+".jpg"));
        	}else{
            	this.cancel();
            	startframe1();
            }
        	
        	Mimage.getGraphics().drawImage(img,0,0,1366,768,null);
        	mainframe.paint(Mimage);
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
		timer2.schedule(new drawbg(),0, 40);
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
	        
	        	if (framey<0) framey+=100;
	        	if (framey>0) framey=0;
				Mimage.getGraphics().drawImage(button[0], 0,framey, null);
		
				if ((mousex<1240)&&(mousex>918)&&(mousey>364)&&(mousey<432))
				    Mimage.getGraphics().drawImage(button[1], 0,0, null);
				if ((mousex<1074)&&(mousex>918)&&(mousey>453)&&(mousey<502))
				    Mimage.getGraphics().drawImage(button[2], 0,0, null);
				if ((mousex<1240)&&(mousex>1085)&&(mousey>453)&&(mousey<502))
				    Mimage.getGraphics().drawImage(button[3], 0,0, null);
				if ((mousex<1240)&&(mousex>918)&&(mousey>533)&&(mousey<592))
				    Mimage.getGraphics().drawImage(button[4], 0,0, null);
				
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
