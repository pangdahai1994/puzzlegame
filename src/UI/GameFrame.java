package UI;

import Objcts.*;
import DataServer.*;
import java.awt.Color;
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
	static int ii=1;
	static GameTasker tasker=new GameTasker();
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
        tasker.schedule(new brushtask(),10, 20);
    }
    
	static class brushtask extends java.util.TimerTask{ 
        public void run() {
        	BufferedImage image;
        	int i,j=0;
				try {
					ii++;
					if (ii>10) ii=1;
					image = ImageIO.read(new FileInputStream("bg2\\bg ("+ii+").JPG"));
			        Mimage.getGraphics().drawImage(image,0,0,1366,768,null);
			        
			        if((mousedown)&&(tasker.mousex(mousedownx)>=0)&&(tasker.mousey(mousedowny)>=0)&&(!changing)){
			        	if((mousex-mousedownx>=40)&&(tasker.mousex(mousedownx)<8)){
			        		tasker.changedh(tasker.mousex(mousedownx), tasker.mousey(mousedowny), tasker.mousex(mousedownx)+1, tasker.mousey(mousedowny));
			        		mousedown=false;
			        	}else
			        	if((mousey-mousedowny>=40)&&(tasker.mousey(mousedowny)<8)){
			        		mousedown=false;
			        		tasker.changedh(tasker.mousex(mousedownx), tasker.mousey(mousedowny), tasker.mousex(mousedownx), tasker.mousey(mousedowny)+1);
			        	}else
                        if((mousex-mousedownx<=-40)&&(tasker.mousex(mousedownx)>0)){
                        	tasker.changedh(tasker.mousex(mousedownx), tasker.mousey(mousedowny), tasker.mousex(mousedownx)-1, tasker.mousey(mousedowny));
                        	mousedown=false;
                        }else
			        	if((mousey-mousedowny<=-40)&&(tasker.mousey(mousedowny)>0)){
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
					        		changing=false;
					        	}
					        }
					        if (tasker.brickc[i][j].brick.locaty>tasker.brickc[i][j].brick.targety){
					        	tasker.brickc[i][j].brick.locaty-=tasker.brickc[i][j].brick.movespeedy;
					        	changing=true;
					        	if (tasker.brickc[i][j].brick.locaty<=tasker.brickc[i][j].brick.targety){
					        		tasker.brickc[i][j].brick.locaty=tasker.brickc[i][j].brick.targety;
					        		changing=false;
					        	}
					        }
					        if (tasker.brickc[i][j].brick.locatx<tasker.brickc[i][j].brick.targetx){
					        	changing=true;
					        	tasker.brickc[i][j].brick.locatx+=tasker.brickc[i][j].brick.movespeedx;
					        	if (tasker.brickc[i][j].brick.locatx>=tasker.brickc[i][j].brick.targetx){
					        		tasker.brickc[i][j].brick.locatx=tasker.brickc[i][j].brick.targetx;
					        		changing=false;
					        	}
					        }
					        if (tasker.brickc[i][j].brick.locatx>tasker.brickc[i][j].brick.targetx){
					        	changing=true;
					        	tasker.brickc[i][j].brick.locatx-=tasker.brickc[i][j].brick.movespeedx;
					        	if (tasker.brickc[i][j].brick.locatx<=tasker.brickc[i][j].brick.targetx){
					        		tasker.brickc[i][j].brick.locatx=tasker.brickc[i][j].brick.targetx;
					        		changing=false;
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
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				g.drawImage(Mimage, 0, 0, null);
        	}
        	
        
    }
}
