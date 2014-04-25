package Objcts;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ZButton {
	public int sitate=0;
	//public Font font=new Font();
    public BufferedImage[] state=new BufferedImage[2];
    public int x,y,x1,x2,y1,y2;
    ZButton(String path1,String path2,int x,int y,int x1,int x2,int y1,int y2) throws FileNotFoundException, IOException{
    	state[0]=ImageIO.read(new FileInputStream(path1));
    	state[1]=ImageIO.read(new FileInputStream(path2));
    }
    void drawString(String s,int x,int y){
    	Graphics g=state[0].getGraphics();
    	//g.setColor(color);
    	//g.setFont(font);
    	g.drawString(s, x, y);
    }
}
