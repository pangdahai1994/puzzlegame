package Objcts;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.xml.bind.Marshaller.Listener;

public class ZButton {
	public MouseAdapter ma=null;MouseListener mb=null;
	public int sitate=0;
	//public Font font=new Font();
    public BufferedImage[] state=new BufferedImage[3];
    public int locatex,locatey,width,height;
    ZButton(BufferedImage free,BufferedImage moveon,BufferedImage clickdown,int locatex,int locatey,int width,int height) throws FileNotFoundException, IOException{
    	state[0]=free;
    	state[1]=moveon;
    	state[2]=clickdown;
    	this.locatex=locatex;
    	this.locatey=locatey;
    	this.width=width;
    	this.height=height;
    }
    void drawString(String s,int x,int y){
    	Graphics g=state[0].getGraphics();
    	//g.setColor(color);
    	//g.setFont(font);
    	g.drawString(s, x, y);
    }
    void addListener(MouseAdapter ma){
    	this.ma=ma;
    }
    void addListener(MouseListener mb){
    	this.mb=mb;
    }
}
