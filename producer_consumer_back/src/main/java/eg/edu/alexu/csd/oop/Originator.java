package eg.edu.alexu.csd.oop;

import java.util.ArrayList;
import java.util.List;

public class Originator {
	private List <Integer> queueProductNo=new ArrayList<>();
	private List <String> MachineCurrentProduct=new ArrayList<>();
	private long startTime;
	
	
	public void setState(List <Integer> qpn ,List <String> mcp ,long st) {
		this.queueProductNo=qpn;
		this.MachineCurrentProduct=mcp;
		this.startTime=st;
	}
	public Memento savestateToMemento() {
		 List <Integer> q =new ArrayList<>();
		 List <String> m =new ArrayList<>();
		for(int i=0; i<queueProductNo.size(); i++) {
			q.add(queueProductNo.get(i));
		}
		for(int i=0; i<MachineCurrentProduct.size(); i++){
			m.add(MachineCurrentProduct.get(i));
		}
		
		
		return new Memento(q,m, startTime);
	}

}
