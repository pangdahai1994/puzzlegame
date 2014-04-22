//真男人从不注释
package UI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class ZFrame extends JFrame {
	Toolkit tk=Toolkit.getDefaultToolkit();
	String titlestring="ZFrame";
	String backgroundpath,sysiconpath;
	ZPanel zpanel=new ZPanel();
	static int width=800;
	static int height=600;
	ZFrame zf=this;
	TrayIcon trayIcon=null;
	SystemTray tray = SystemTray.getSystemTray(); // 获得系统托盘
	Graphics g;
	ZFrame(){
        this(width,height);
        //System.out.print("777");
	}
	
	ZFrame(String s) {
		this(width,height);
	    this.setTitle(s);
	    //System.out.print("666");
	}
	
	ZFrame(int width,int height){
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setVisible(true);
	    this.setSize(width,height);
        this.setTitle(titlestring);
        this.setLocation((tk.getScreenSize().width-this.getWidth())/2, (tk.getScreenSize().height-this.getHeight())/2-15);
	    this.add(zpanel);
	    zpanel.setVisible(true);
	}
	
	public void FullScreen(){
		this.setSize(tk.getScreenSize().width, tk.getScreenSize().height);
		this.setLocation(0, 0);
		MoveOutTitle();
	}
	
	public void MoveOutTitle(){
		this.dispose();
		this.setUndecorated(true); 
		this.setVisible(true); 
	}
	
	public void SetIconPath(String path){
		sysiconpath=path;
	}
	
	/*public void brush(String path,int x,int y){
		zpanel.brush(path, x, y);
	}
	
	public void brush(String path,int x,int y,int imgx,int imgy,int width,int height){
		zpanel.brush(path, x, y, imgx, imgy, width, height);
	}*/
	public void paint(BufferedImage pic){
		//zpanel.getGraphics().drawRect(0, 0, 200, 200);
		zpanel.getGraphics().drawImage(pic, 0, 0, null);
	}
	public void paint(BufferedImage pic,int x,int y){
		//zpanel.getGraphics().drawRect(0, 0, 200, 200);
		zpanel.getGraphics().drawImage(pic, x, y, null);
	}
	public void paint(BufferedImage pic,int posx,int posy,int x,int y,int width,int height){
		//zpanel.getGraphics().drawRect(0, 0, 200, 200);
		BufferedImage image2=pic.getSubimage(x, y, width, height);
		zpanel.getGraphics().drawImage(image2, posx, posy, null);
	}
	
	public void paint(String path,int x,int y){
		zpanel.paint(path, x, y);
	}
	
	public void paint(String path,int x,int y,int imgx,int imgy,int width,int height){
        zpanel.paint(path, x, y, imgx, imgy, width, height);
	}
	public void setIconImage(String path){
        BufferedImage logo;
		try {
			logo = ImageIO.read(new FileInputStream(path));
	        this.setIconImage(logo);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public JLabel addlabel(String s,int x,int y,int width,int height){
		JLabel jl=new JLabel();
		jl.setText(s);
		jl.setBounds(x, y, width, height);
		zpanel.add(jl);
		return jl;
	}
	
	public JLabel addlabel(String s,int x,int y,int width,int height,Color word,Color back,Font font){
		JLabel jl=new JLabel();
		jl.setText(s);
		jl.setBounds(x, y, width, height);
	    jl.setFont(font);
	    jl.setForeground(word);
	    jl.setBackground(back);
		zpanel.add(jl);
		return jl;
	}
	
	public JButton addbutton(String s,int x,int y,int width,int height){
		JButton jb=new JButton();
		jb.setText(s);
		jb.setBounds(x, y, width, height);
		zpanel.add(jb);
		return jb;
	}
	
	public JButton addbutton(String s,int x,int y,int width,int height,Color word,Color back,Font font){
		JButton jb=new JButton();
		jb.setText(s);
		jb.setBounds(x, y, width, height);
	    jb.setFont(font);
	    jb.setForeground(word);
	    jb.setBackground(back);
		zpanel.add(jb);
		return jb;
	}
	
	public JTextField addtextfield(int x,int y,int width,int height,Font font){
		JTextField jt=new JTextField();
		jt.setBounds(x, y, width, height);
		jt.setFont(font);
		zpanel.add(jt);
		return jt;
	}
	
	public JPasswordField addpasswordfield(int x,int y,int width,int height,Font font){
		JPasswordField jp=new JPasswordField();
		jp.setBounds(x, y, width, height);
		jp.setFont(font);
		jp.setEchoChar('*');
		zpanel.add(jp);
		return jp;
	}
	
	public JLabel addlabel(String s,int x,int y,int width,int height,Font font){
		JLabel jl=new JLabel();
		jl.setText(s);
		jl.setBounds(x, y, width, height);
		jl.setFont(font);
		zpanel.add(jl);
		return jl;
	}
	
	public void MinToSys(){         //最小化到系统托盘  
        if (SystemTray.isSupported()) // 判断系统是否支持系统托盘  
        {  
        	zpanel.save();
        	zf.setVisible(false);  
            Image image = Toolkit.getDefaultToolkit().getImage(sysiconpath);//载入图片 
            ActionListener listener = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    zf.setVisible(true);  
                    zf.paint(zpanel.screenshot);
                    tray.remove(trayIcon);
               }  
            };
            // 创建弹出菜单  
            PopupMenu popup = new PopupMenu();  
            MenuItem defaultItem = new MenuItem("回到选课系统");  
            defaultItem.addActionListener(listener);  
            MenuItem exitItem = new MenuItem("退出选课系统");  
            exitItem.addActionListener(new ActionListener() {  
                public void actionPerformed(ActionEvent e) {  
                    System.exit(0);  
                }  
            });  
  
            popup.add(defaultItem);  
            popup.add(exitItem);  
            trayIcon = new TrayIcon(image, "最小化窗口", popup);// 创建trayIcon  
            trayIcon.addActionListener(listener);  
            try {  
                tray.add(trayIcon);  
            } catch (AWTException e1) {  
                e1.printStackTrace();  
            }  
        }
	}
	
	public void addImagelabel(final int locationx,final int locationy,final int width,final int height,final String s,final Color incolor,final Color outcolor){
		this.addMouseMotionListener(new MouseAdapter(){
			 public void mouseMoved(MouseEvent event){
		            int x=event.getX(),y=event.getY();
		            g=zpanel.getGraphics();
	            	g.setFont(new Font("宋体",Font.PLAIN,40));
		            if ((x>=locationx)&&(x<=locationx+width)&&(y>=locationy)&&(y<=locationy+height)){
		            	g.setColor(incolor);
		            	g.fillRect(locationx, locationy, width, height);
		            	g.setColor(Color.white);
		            	g.drawString(s, locationx+50, locationy+62);
		            }else{
		            	g.setColor(outcolor);
		            	g.fillRect(locationx-1, locationy, width, height);
		            	g.setColor(Color.white);
		            	g.drawString(s, locationx+50, locationy+62);
		            }
		     }
		});	
		g=zpanel.getGraphics();
    	g.setColor(outcolor);
    	g.drawRect(locationx, locationy, width, height);
    	g.drawString(s, locationx+50, locationy+62);
	}
	
	public Imagelabel add(final Imagelabel lb){
		this.addMouseMotionListener(new MouseAdapter(){
			 public void mouseMoved(MouseEvent event){
				 if (lb.changeable){
		            int x=event.getX(),y=event.getY();
		            g=zpanel.getGraphics();
	            	g.setFont(lb.font);
		            if ((x>=lb.x)&&(x<=lb.x+lb.width)&&(y>=lb.y)&&(y<=lb.y+lb.height)){
		            	if (lb.state!=1){
		            	g.setColor(lb.incolor);
		            	g.fillRect(lb.x, lb.y, lb.width, lb.height);
		            	g.setColor(Color.white);
		            	g.drawString(lb.s, lb.x+lb.dx, lb.y+lb.dy);
		            	lb.state=1;
		            	}
		            }else{
		            	if (lb.state!=2){
		            	    g.setColor(lb.outcolor);
		            	    g.fillRect(lb.x, lb.y, lb.width, lb.height);
		            	    g.setColor(Color.white);
		            	    g.drawString(lb.s, lb.x+lb.dx, lb.y+lb.dy);
		            	    lb.state=2;
		            	}
		            }
				 }
		     }
		});	
		g=zpanel.getGraphics();
		g.setFont(lb.font);
		g.setColor(lb.outcolor);
    	g.fillRect(lb.x, lb.y, lb.width, lb.height);
    	g.setColor(Color.white);
    	g.drawString(lb.s, lb.x+lb.dx, lb.y+lb.dy);
		this.addMouseListener(new MouseAdapter(){
			 public void mousePressed(MouseEvent event){
				 int x=event.getX(),y=event.getY();
                 if  ((lb.x<255)&&(x<255)&&(y>178)&&(y<578)&&(!((y>lb.y)&&(y<lb.y+100)))){
                	 g=zpanel.getGraphics();
 	            	 g.setFont(lb.font);
		             g.setColor(lb.outcolor);
		             g.fillRect(lb.x, lb.y, lb.width, lb.height);
		             g.setColor(Color.white);
		             g.drawString(lb.s, lb.x+lb.dx, lb.y+lb.dy);
                 }
		     }
		});
    	return lb;
	}
	
	public void addImagebutton(final String state1,final String state2,final String state3,final int locationx,final int locationy,final int width,final int height){
		this.addMouseListener(new MouseAdapter(){
			 public void mousePressed(MouseEvent event){
		            int x=event.getX(),y=event.getY();
		            if ((x>=locationx)&&(x<=locationx+width)&&(y>=locationy)&&(y<=locationy+height)){
		            	zpanel.paint(state3, locationx, locationy, 0, 0, width, height);
		            }
		     }
			 
			 public void mouseReleased(MouseEvent event){
	            	zpanel.paint(state1, locationx, locationy, 0, 0, width, height);
		     }
		});
		this.addMouseMotionListener(new MouseAdapter(){
			 public void mouseMoved(MouseEvent event){
		            int x=event.getX(),y=event.getY();
		            if ((x>=locationx)&&(x<=locationx+width)&&(y>=locationy)&&(y<=locationy+height)){
		            	zpanel.paint(state2, locationx, locationy, 0, 0, width, height);
		            }else{
		            	zpanel.paint(state1, locationx, locationy, 0, 0, width, height);
		            }
		     }
		});	
		zpanel.paint(state1, locationx, locationy, 0, 0, width, height);
	}
}