package UI;

import java.awt.Color;
import java.awt.Cursor;
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
	static int cursorstate=1;  //1代表原始鼠标 2代表手指
	static boolean mousedown=false;
	static Timer timer,timer2;
	static Graphics g;
	static Font font=new Font("宋体",Font.PLAIN,16);
	static int framey=-700;
	static int x1=1,y1=1; //开场动画
    static int state=0;
    static int change=0,playernum=0,mousex,mousey;
    static ZFrame mainframe;
    static GameFrame gf;
    static MediaPlayer mp,mp2;
    static BufferedImage Mimage=new BufferedImage(1366,768,BufferedImage.TYPE_INT_RGB);
    static BufferedImage[] button=new BufferedImage[10];
    static ArrayList<String> players=new ArrayList<String>(3);
	public static void main(String[] args) throws IOException {
		//TODO Auto-generated method stub
        //displaymode dpm=new displaymode();
        //dpm.setto1024();
		//new GameFrame();
		
		
		button[0] = ImageIO.read(new FileInputStream("ui\\ui4\\log.png"));
		button[1] = ImageIO.read(new FileInputStream("ui\\buttons\\button1.png"));
		button[2] = ImageIO.read(new FileInputStream("ui\\buttons\\button2.png"));
		button[3] = ImageIO.read(new FileInputStream("ui\\buttons\\button3.png"));
		button[4] = ImageIO.read(new FileInputStream("ui\\buttons\\button4.png"));
		button[5] = ImageIO.read(new FileInputStream("ui\\frame.png"));
		
		g=Mimage.getGraphics();
		
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
					if(((mousex<1240)&&(mousex>918)&&(mousey>364)&&(mousey<432))||
					((mousex<1091)&&(mousex>918)&&(mousey>453)&&(mousey<502))||
					((mousex<1230)&&(mousex>1107)&&(mousey>453)&&(mousey<502))||
					((mousex<1240)&&(mousex>918)&&(mousey>533)&&(mousey<592)))
					{
						if ((cursorstate==1)&&(state==3)){
						    mainframe.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
						    cursorstate=2;
						    mp2=new MediaPlayer("ui\\button_click.mp3");
						}
					}else{
						cursorstate=1;
						mainframe.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					}
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
		        	//timer2.cancel();
		        }
		        if ((state==1)&&(mousex<1091)&&(mousex>918)&&(mousey>453)&&(mousey<502)){
		        	//单机游戏
		        	timer.cancel();
		        	timer2.cancel();
		        	//state=2;
		        	try {
		        		mp.isStop=true;
						gf=new GameFrame();
						Thread.sleep(1000);
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
   		     public void mousePressed(MouseEvent event){
		       // if (jindu<1080) jindu=1080;
                 
   		     }
   		     public void mouseReleased(MouseEvent event){
		       // if (jindu<1080) jindu=1080;
		        
		        if ((state==1)&&(mousex<1240)&&(mousex>918)&&(mousey>533)&&(mousey<592)) System.exit(0);
		        if ((state==1)&&(mousex<1240)&&(mousex>918)&&(mousey>364)&&(mousey<432)){
		        	//开始游戏
		        	//timer2.cancel();
		        }
		        if ((mousex<1091)&&(mousex>918)&&(mousey>453)&&(mousey<502)){
		        	//单机游戏
		        	timer.cancel();
		        	timer2.cancel();
		        	//state=2;
		        	try {
		        		mp.isStop=true;
						gf=new GameFrame();
						Thread.sleep(1000);
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
		timer = new Timer();
        timer.schedule(new MyTask1(),0, 40);
        timer2 = new Timer();
        timer2.schedule(new MyTask2(),40, 40);
	}
	
	public static void changeframe(){
		mainframe.zpanel.save();
		BufferedImage img=mainframe.zpanel.screenshot;
		
	}
	

	
	static class MyTask1 extends java.util.TimerTask{ 
        public void run() {
        	BufferedImage img=null;
        	try {
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
        	
        	g.drawImage(img,0,0,1366,768,null);
        	mainframe.paint(Mimage);
        	}catch(Exception e){
        		e.printStackTrace();
        	}
        }
    } 
	
	static class MyTask2 extends java.util.TimerTask{ 
        public void run() {
        	x1++;
        }
    } 
	
	public static void startframe1(){
		mainframe.zpanel.save();
		state=1;
		timer2.cancel();
		timer2=new Timer();
		timer2.schedule(new drawbg(),0, 40);
		timer=new Timer();
		timer.schedule(new countbg(),0,40);
	}
	
	static class countbg extends java.util.TimerTask{ 
        public void run() { 
        	BufferedImage image=null;
				y1++;
				if ((mp.hasStop)||(!mp.path.equals("ui\\mp3\\Sound2.mp3"))) {
					mp.isStop=true;
					mp=new MediaPlayer("ui\\mp3\\Sound2.mp3"); 
				}
	        	if (y1==125) y1=1;
	        	Mimage.getGraphics().drawImage(image, 0,0,1366,768, null);
	        
	        	if (framey<0) framey+=100;
	        	if (framey>0) framey=0;
        }
	}
	static class drawbg extends java.util.TimerTask{ 
        public void run() { 
        	BufferedImage image=null;
			try {
	        	if (y1<10){
	        		image = ImageIO.read(new FileInputStream("ui\\ui2\\bg00"+y1+".jpg"));
	        	}else if (y1<100){
	        		image = ImageIO.read(new FileInputStream("ui\\ui2\\bg0"+y1+".jpg"));
	        	}else if (y1<=125){
	        		image = ImageIO.read(new FileInputStream("ui\\ui2\\bg"+y1+".jpg"));
	            }
	        	Mimage.getGraphics().drawImage(image, 0,0,1366,768, null);
	        
				Mimage.getGraphics().drawImage(button[0], 0,framey, null);
		
				
				if (framey==0){
				if ((mousex<1240)&&(mousex>918)&&(mousey>364)&&(mousey<432)){
				    Mimage.getGraphics().drawImage(button[1], 0,0, null);
				}else
				if ((mousex<1091)&&(mousex>918)&&(mousey>453)&&(mousey<502)){
				    Mimage.getGraphics().drawImage(button[2], 0,0, null);
				}else
				if ((mousex<1230)&&(mousex>1107)&&(mousey>453)&&(mousey<502)){
				    Mimage.getGraphics().drawImage(button[3], 0,0, null);
				}else
				if ((mousex<1240)&&(mousex>918)&&(mousey>533)&&(mousey<592)){
				    Mimage.getGraphics().drawImage(button[4], 0,0, null);
				}
				}
				
				mainframe.paint(Mimage);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    } 
}
