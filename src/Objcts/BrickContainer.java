package Objcts;

import java.io.Serializable;
import java.rmi.Remote;


public class BrickContainer implements Remote,Serializable{
	public boolean isempty=false;
	public Brick brick=new Brick();
}