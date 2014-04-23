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
	static BrickContainer brick[][]=new BrickContainer[9][9];
	static GameTasker tasker=new GameTasker();
	static Graphics g;
    static BufferedImage Mimage=new BufferedImage(1366,768,BufferedImage.TYPE_INT_RGB);
    GameFrame(){
    	this.FullScreen();
    	g=this.zpanel.getGraphics();
        this.addMouseMotionListener(new MouseAdapter(){
			 public void mouseMoved(MouseEvent event){

			 }
   	    });
        this.addMouseListener(new MouseAdapter(){
   		     public void mousePressed(MouseEvent event){
		          System.out.println(event.getX()+" "+event.getY());
   		     }
   	    }); 
        rndnew();
        Timer tasker=new Timer();
        tasker.schedule(new brushtask(),10, 30);
    }
    
    void rndnew(){
    	for (int i=0;i<=9;i++){
    		for (int j=0;j<=9;j++){
    			brick[i][j]=(int)(Math.random()*6)+1;
    		}
    	}
    }
	static class brushtask extends java.util.TimerTask{ 
        public void run() {
        	BufferedImage image;
        	int i,j=0;
				try {
					image = ImageIO.read(new FileInputStream("bg2\\bg (1).JPG"));
			        Mimage.getGraphics().drawImage(image,0,0,1366,768,null);
			        
					for (i=0;i<10;i++){
		        		for (j=0;j<10;j++){
					        image = ImageIO.read(new FileInputStream("bgs\\b"+brick[i][j]+".png"));
					        Mimage.getGraphics().drawImage(image,600+60*j,100+60*i,120,120, null);
		        		}
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
