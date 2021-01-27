package eg.edu.alexu.csd.oop;

import java.util.ArrayList;
import java.util.List;

public class Memento {
	private List <Integer> queueProductNo=new ArrayList<>();
	private List <String> MachineCurrentProduct=new ArrayList<>();
	private long startTime;
	
	public Memento(List <Integer> qpn ,List <String> mcp ,long st) {
		this.queueProductNo=qpn;
		this.MachineCurrentProduct=mcp;
		this.startTime=st;
	}
	public List <Integer> getQueueState(){
		return this.queueProductNo;
	}
	public List <String> getMachineState(){
		return this.MachineCurrentProduct;
	}
	public Long getStarttime() {
		return startTime;
	}
}
