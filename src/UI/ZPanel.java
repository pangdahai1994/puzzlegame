//真男人从不注释
package UI;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ZPanel extends JPanel {
    /*public String path="back.jpg";
    public int x=0,y=0,imgx=0,imgy=0,width=1,height=1;*/
	BufferedImage screenshot;
	Toolkit tk=Toolkit.getDefaultToolkit();
    ZPanel(){
    	this.setLayout(null);
    }
    
    public void paintComponent(Graphics g) {
	      super.paintComponent(g);
		  g.drawImage(screenshot,0,0,null);

	}
	
	/*public void brush(String path,int x,int y){
		this.x=x;
		this.y=y;
		this.path=path;
		imgx=0;imgy=0;
	    BufferedImage image;
		try {
		    image = ImageIO.read(new FileInputStream(path));
		    width=image.getWidth();
		    height=image.getHeight();
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
	    } catch (IOException e) {
		    e.printStackTrace();
	    }
		this.repaint();
	}
	
	public void brush(String path,int x,int y,int imgx,int imgy,int width,int height){
		this.x=x;
		this.y=y;
		this.path=path;
		this.imgx=imgx;this.imgy=imgy;
		BufferedImage image;
		try {
		    image = ImageIO.read(new FileInputStream(path));
		    if (width>image.getWidth()){
		    	this.width=image.getWidth();
		    }else{
		    	this.width=width;
		    }
		    if (height>image.getHeight()){
		    	this.height=image.getHeight();
		    }else{
		    	this.height=height;
		    }
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
	    } catch (IOException e) {
		    e.printStackTrace();
	    }
		this.repaint();
	}*/
	public void paintto(String path,int x,int y,int width,int height){
	    BufferedImage image;
		try {
		    image = ImageIO.read(new FileInputStream(path));
		    BufferedImage image2=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		    image2.getGraphics().drawImage(image, 0, 0, width,height, null);
		    this.getGraphics().drawImage(image2,x,y,null);
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
	    } catch (IOException e) {
		    e.printStackTrace();
	    }
	}
    
	public void paint(String path,int x,int y){
	    BufferedImage image;
		try {
		    image = ImageIO.read(new FileInputStream(path));
		    this.getGraphics().drawImage(image,x,y,null);
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
	    } catch (IOException e) {
		    e.printStackTrace();
	    }
	}
	
	public void paint(String path,int x,int y,int imgx,int imgy,int width,int height){
		BufferedImage image;
		try {
		    image = ImageIO.read(new FileInputStream(path));
		    if (width>image.getWidth()){
		    	width=image.getWidth();
		    }
		    if (height>image.getHeight()){
		    	height=image.getHeight();
		    }
		    image=image.getSubimage(imgx, imgy, width, height);
		    this.getGraphics().drawImage(image,x,y,null);
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
	    } catch (IOException e) {
		    e.printStackTrace();
	    }
	}
	
	public JButton addbutton(String s,int x,int y,int width,int height){
		JButton jb=new JButton();
		jb.setText(s);
		jb.setBounds(x, y, width, height);
		this.add(jb);
		return jb;
	}
	
	public JButton addbutton(String s,int x,int y,int width,int height,Color word,Color back,Font font){
		JButton jb=new JButton();
		jb.setText(s);
		jb.setBounds(x, y, width, height);
	    jb.setFont(font);
	    jb.setForeground(word);
	    jb.setBackground(back);
		this.add(jb);
		return jb;
	}
	
	public void save(){
		//try {
			try {
				screenshot= (new Robot()).createScreenCapture(new Rectangle(0, 0,(int) tk.getScreenSize().getWidth(), (int) tk.getScreenSize().getHeight()));
			} catch (AWTException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            /*File f = new File("temp\\temp");
            //将screenshot对象写入图像文件
            try {
				ImageIO.write(screenshot, "jpg", f);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} catch (HeadlessException e1) {
			e1.printStackTrace();
		}*/
    }
	
	public void save(int x,int y,int width,int height){
		try {
			try {
				screenshot= (new Robot()).createScreenCapture(new Rectangle(0, 0,(int) tk.getScreenSize().getWidth(), (int) tk.getScreenSize().getHeight()));
				screenshot=screenshot.getSubimage(x, y, width, height);
			} catch (AWTException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            File f = new File("temp\\temp");
            //将screenshot对象写入图像文件
            try {
				ImageIO.write(screenshot, "jpg", f);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} catch (HeadlessException e1) {
			e1.printStackTrace();
		}
    }
}
