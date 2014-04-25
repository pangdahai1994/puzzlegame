package UI;

import Objcts.*;
import DataServer.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Time;
import java.util.Timer;

import javax.imageio.ImageIO;

import UI.MainFrame.MyTask1;

public class GameFrame extends ZFrame{
	static boolean changing=false;
	static int ii=1,x1=0,highest=0;;
	static GameTasker tasker=new GameTasker();
	static int time=60000;
	static Graphics g;
    static BufferedImage Mimage=new BufferedImage(1366,768,BufferedImage.TYPE_INT_RGB);
    static int mousex=0,mousey=0;
    static int mousedownx=0,mousedowny=0,mouseupx=0,mouseupy=0;
    static boolean mousedown=false,mouseup=false;
    static BufferedImage iimage[]=new BufferedImage[7];
    GameFrame() throws FileNotFoundException, IOException{
    	int i = 0;
    	for (i=1;i<=6;i++){
    	    iimage[i]= ImageIO.read(new FileInputStream("bgs\\b"+i+".png"));
    	}
    	iimage[0]= ImageIO.read(new FileInputStream("ui\\label2.png"));
    	
    	this.FullScreen();
    	g=this.zpanel.getGraphics();
    	this.addMouseMotionListener(new MouseAdapter(){
        	public void mouseMoved(MouseEvent event){
				 mousex=event.getX();
		         mousey=event.getY();
			 }
        	public void mouseDragged(MouseEvent event){
				 mousex=event.getX();
		         mousey=event.getY();
			 }
   	    }); 
    	
        this.addMouseListener(new MouseAdapter(){
   		     public void mousePressed(MouseEvent event){
		          mousedownx=event.getX();
		          mousedowny=event.getY();
		          mousedown=true;
   		     }
   		      public void mouseReleased(MouseEvent event){
	              mouseupx=event.getX();
	              mouseupy=event.getY();
	              mousedown=false;
		     }
   	    }); 
        Timer tasker=new Timer();
        tasker.schedule(new brushtask(),10, 40);
    }
    
	static class brushtask extends java.util.TimerTask{ 
        public void run() {
        	time-=80;
        	BufferedImage image;
        	int i,j=0;
				try {
					ii++;
					if (ii>10) ii=1;
		        	BufferedImage img=null;
		        	try {
		        	x1++;
		        	if (x1<10){
						img=ImageIO.read(new FileInputStream("ui\\ui2\\bg00"+x1+".jpg"));
		        	}else if (x1<100){
		        		img=ImageIO.read(new FileInputStream("ui\\ui2\\bg0"+x1+".jpg"));
		        	}else if (x1<=125){
		        		img=ImageIO.read(new FileInputStream("ui\\ui2\\bg"+x1+".jpg"));
		        	}
		        	Mimage.getGraphics().drawImage(img,0,0,1366,768,null);
		        	Mimage.getGraphics().drawImage(iimage[0],0,0,1366,768,null);
		        	Graphics g=Mimage.getGraphics();
		        	g.setFont(new Font("宋体",Font.PLAIN,32));
		        	g.drawString(""+(tasker.score*100), 300, 136);
		        	g.drawString(""+(time/1000), 370, 251);
		        	g.drawString("历史最高分："+highest*100, 270, 400);
		        	if (time<=0){
		        		if (tasker.score>highest){
		        			highest=tasker.score;
		        		}
		        		time=60000;
		        		tasker.score=0;
		        		tasker.rndnew();
		        	}
		        	
		        	}catch(Exception e){
		        		e.printStackTrace();
		        	}
		        	if (x1==125) x1=0;
			    
					changing=false;
					for (i=0;i<9;i++){
		        		for (j=0;j<9;j++){
		        			if (((tasker.brickc[i][j].brick.locatx!=tasker.brickc[i][j].brick.targetx)||(tasker.brickc[i][j].brick.locaty!=tasker.brickc[i][j].brick.targety)))
		        		    changing=true;
		        		}
		        	}
					
					 if((!tasker.havexiao())&&(tasker.changeuseful)&&(mousedown)&&(tasker.mousex(mousedownx)>=0)&&(tasker.mousey(mousedowny)>=0)&&(!changing)){
				        	if((mousex-mousedownx>=20)&&(tasker.mousex(mousedownx)<8)){
				        		tasker.changedh(tasker.mousex(mousedownx), tasker.mousey(mousedowny), tasker.mousex(mousedownx)+1, tasker.mousey(mousedowny));
				        		mousedown=false;
				        	}else
				        	if((mousey-mousedowny>=20)&&(tasker.mousey(mousedowny)<8)){
				        		mousedown=false;
				        		tasker.changedh(tasker.mousex(mousedownx), tasker.mousey(mousedowny), tasker.mousex(mousedownx), tasker.mousey(mousedowny)+1);
				        	}else
	                        if((mousex-mousedownx<=-20)&&(tasker.mousex(mousedownx)>0)){
	                        	tasker.changedh(tasker.mousex(mousedownx), tasker.mousey(mousedowny), tasker.mousex(mousedownx)-1, tasker.mousey(mousedowny));
	                        	mousedown=false;
	                        }else
				        	if((mousey-mousedowny<=-20)&&(tasker.mousey(mousedowny)>0)){
				        		tasker.changedh(tasker.mousex(mousedownx), tasker.mousey(mousedowny), tasker.mousex(mousedownx), tasker.mousey(mousedowny)-1);
				        		mousedown=false;
				        	}
				        }
					 
						
					for (i=0;i<9;i++){
		        		for (j=0;j<9;j++){
					        Mimage.getGraphics().drawImage(iimage[tasker.brickc[i][j].brick.style],600+tasker.brickc[i][j].brick.locatx,100+tasker.brickc[i][j].brick.locaty,120,120, null);
					        if (tasker.brickc[i][j].brick.locaty<tasker.brickc[i][j].brick.targety){
					        	tasker.brickc[i][j].brick.locaty+=tasker.brickc[i][j].brick.movespeedy;
					        	changing=true;
					        	if (tasker.brickc[i][j].brick.locaty>=tasker.brickc[i][j].brick.targety){
					        		tasker.brickc[i][j].brick.locaty=tasker.brickc[i][j].brick.targety;
					        	}
					        }
					        if (tasker.brickc[i][j].brick.locaty>tasker.brickc[i][j].brick.targety){
					        	tasker.brickc[i][j].brick.locaty-=tasker.brickc[i][j].brick.movespeedy;
					        	changing=true;
					        	if (tasker.brickc[i][j].brick.locaty<=tasker.brickc[i][j].brick.targety){
					        		tasker.brickc[i][j].brick.locaty=tasker.brickc[i][j].brick.targety;
					        	}
					        }
					        if (tasker.brickc[i][j].brick.locatx<tasker.brickc[i][j].brick.targetx){
					        	changing=true;
					        	tasker.brickc[i][j].brick.locatx+=tasker.brickc[i][j].brick.movespeedx;
					        	if (tasker.brickc[i][j].brick.locatx>=tasker.brickc[i][j].brick.targetx){
					        		tasker.brickc[i][j].brick.locatx=tasker.brickc[i][j].brick.targetx;
					        	}
					        }
					        if (tasker.brickc[i][j].brick.locatx>tasker.brickc[i][j].brick.targetx){
					        	changing=true;
					        	tasker.brickc[i][j].brick.locatx-=tasker.brickc[i][j].brick.movespeedx;
					        	if (tasker.brickc[i][j].brick.locatx<=tasker.brickc[i][j].brick.targetx){
					        		tasker.brickc[i][j].brick.locatx=tasker.brickc[i][j].brick.targetx;
					        	}
					        }
		        		}
					}
                    
					tasker.moving=changing;
					if ((!changing)&&(!tasker.changeuseful)){
			        	tasker.changecancel();
			        	changing=true;
			        }
					if ((!changing)&&(tasker.havexiao())){
			        	tasker.breakbrick();
			        }
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				g.drawImage(Mimage, 0, 0, null);
        	}
        	
        
    }
}
