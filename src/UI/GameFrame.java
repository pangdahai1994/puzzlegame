package UI;

import java.awt.Graphics;

public class GameFrame extends ZFrame{
	Graphics g;
    GameFrame(){
    	this.FullScreen();
    	g=this.getGraphics();
    }
    
}
