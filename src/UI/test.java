package UI;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;

import DataServer.GameTask;
import rmi.RemoteFactory;
import rmi.StartService;

public class test {
    public static GameFrame gf;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request=ServletActionContext.getRequest(); request.getRemoteAddr();
		String ip=addr.getHostAddress().toString();//获得本机IP
		System.out.print(ip);
        // gf=new GameFrame();
        // StartService sf=new StartService();
         //sf.startService();
        // new GameFrame2("192.168.174.1","1099");
		//RemoteFactory rm=new RemoteFactory();
		//GameTask gm=(GameTask)rm.getRmiGameTaskerService();
	}

}
