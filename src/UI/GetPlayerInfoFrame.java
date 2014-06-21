package UI;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;

import po.PlayerPO;
public class GetPlayerInfoFrame{
	public static PlayerPO p;
	public static void main(String args[]){
		p =new PlayerPO("Sam","123",1,1,1,1,1,1,1,1,1);
		new GetPlayerInfoFrame().go(p);
	}
	ZFrame frame;
	JLabel name,gameCount,avgScore,maxHitCount,hitCount,maxScore,score,maxGodtime,godtime;
	JLabel one,two,three,four,five,six,seven,eight,nine;
	void go(PlayerPO p){
		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
		}
		frame=new ZFrame();
		frame.setBackground(Color.yellow);
		//frame.MoveOutTitle();
		frame.setLayout(new GridLayout(9,2));
		
		name=new JLabel("PlayerName:");
		gameCount=new JLabel("gameCount:");
		avgScore=new JLabel("avgScore:");
		maxHitCount=new JLabel("maxHitCount:");
		hitCount=new JLabel("hitCount:");
		maxScore=new JLabel("maxScore:");
		score=new JLabel("score:");
		maxGodtime=new JLabel("maxGodtime:");
		godtime=new JLabel("godtime:");
		
		one=new JLabel(p.getName());
		two=new JLabel(String.valueOf(p.getGameCount()));
		three=new JLabel(String.valueOf(p.getAvgScore()));
		four=new JLabel(String.valueOf(p.getMaxHitCount()));
		five=new JLabel(String.valueOf(p.getHitCount()));
		six=new JLabel(String.valueOf(p.getMaxScore()));
		seven=new JLabel(String.valueOf(p.getScore()));
		eight=new JLabel(String.valueOf(p.getMaxGodtime()));
		nine=new JLabel(String.valueOf(p.getGodtime()));
		
		frame.add(name);
		frame.add(one);

		frame.add(gameCount);
		frame.add(two);

		frame.add(avgScore);
		frame.add(three);
		
		frame.add(maxHitCount);
		frame.add(four);
		
		frame.add(hitCount);
		frame.add(five);
		
		frame.add(maxScore);
		frame.add(six);
		
		frame.add(score);
		frame.add(seven);
		
		frame.add(maxGodtime);
		frame.add(eight);
		
		frame.add(godtime);
		frame.add(nine);
		
		
		frame.setTitle("PalyerInfo");
		frame.setSize(400,300);
		frame.setLocation(520,200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
