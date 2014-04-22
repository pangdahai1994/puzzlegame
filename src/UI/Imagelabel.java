package UI;

import java.awt.Color;
import java.awt.Font;

public class Imagelabel {
	public boolean changeable=true,settodown=false;
	public int state=1;   //anxia 1 buxia 2
    public int x,y,width,height,dx=50,dy=65;
    public Font font=new Font("ËÎÌו",Font.PLAIN,30);
    public Color incolor,outcolor,strcolor;
    public String s="";
    Imagelabel(String s,int x,int y,int width,int height){
    	this.s=s;
    	this.x=x;
    	this.y=y;
    	this.width=width;
    	this.height=height;
    }
}