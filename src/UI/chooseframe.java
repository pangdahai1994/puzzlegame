package UI;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import rmi.StartService;
import Objcts.ZButton;

public class chooseframe extends ZFrame{
	static JTextField jt;
	static chooseframe chose;
	JButton use1,use2,use3;
	static boolean a=false,b=false,c=false;
    chooseframe() throws IOException{
        chose=this;
		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
		}
		this.setLayout(null);
    	this.setSize(600, 300);
    	this.MoveToMid();
    	this.MoveOutTitle();
    	JButton zb=new JButton("作为服务器");
    	JButton zb2=new JButton("作为客户端");
    	
    	use1=new JButton("加强连击");
    	use2=new JButton("加强分数");
        use3=new JButton("加强提示");
    	
        use1.setBounds(50,200,100,50 );
        use2.setBounds(250,200,100,50 );
        use3.setBounds(450,200,100,50 );
        
        use1.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent event){
			    use1.setEnabled(false);
			}
   		});
        use2.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent event){
			    use2.setEnabled(false);
			}
   		});
        use3.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent event){
			    use3.setEnabled(false);
			}
   		});
        
    	zb.setBounds(50,100,200,50 );
    	zb2.setBounds(350,100,200,50);
    	this.add(zb);
    	this.add(zb2);
    	this.add(use1);
    	this.add(use2);
    	this.add(use3);
    	jt =new JTextField();
    	jt.setBounds(80, 50, 400, 30);
    	this.add(jt);
    	zb.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent event){
			    try {
			    	new StartService(a,b,c);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			chose.setVisible(false);
			}
			
   		});
    	
    	zb2.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent event){
			    try {
					new GameFrame2(jt.getText(),"1099");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				chose.setVisible(false);

			}
   		});
    	
    }
}
