package UI;

import java.awt.*;

public class displaymode {
	Toolkit tk=Toolkit.getDefaultToolkit();
    int x,y;
    displaymode(){
    	x=tk.getScreenSize().width;
    	y=tk.getScreenSize().height;
    }
    void setto1024(){
    	GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment(); 
    	GraphicsDevice device=environment.getDefaultScreenDevice(); 
    	DisplayMode displayMode=new DisplayMode(1024,768,16,60); 
    	device.setDisplayMode(displayMode); 
    }
    void back(){
    	GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment(); 
    	GraphicsDevice device=environment.getDefaultScreenDevice(); 
    	DisplayMode displayMode=new DisplayMode(x,y,32,60); 
    	device.setDisplayMode(displayMode); 
    }
}