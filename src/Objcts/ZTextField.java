package Objcts;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import UI.ZFrame;

public class ZTextField extends JTextField{
    BufferedImage bmg;  
    TexturePaint texture;  
    int bwidth,bheight;
    public ZTextField(BufferedImage img,int width,int height){
    	this.bwidth=width;
    	this.bheight=height;
    	
    	bmg=img;
        Rectangle rect = new Rectangle(0,0,  
                img.getWidth(null),img.getHeight(null));  
        texture = new TexturePaint(img, rect);  
        setOpaque(false);  
    }  
      
    public void paintComponent(Graphics g) {  
        //先画背景  
        Graphics2D g2 = (Graphics2D)g;  
        g2.setPaint(texture);  
        g.fillRect(0,0,getWidth(),getHeight());  
        //然后画控件，不然控件内容就被背景覆盖了  
        super.paintComponent(g);  
    }  

}