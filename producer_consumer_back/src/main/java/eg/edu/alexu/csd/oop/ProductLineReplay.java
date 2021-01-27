package eg.edu.alexu.csd.oop;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ProductLineReplay {
    public int finished=0;

	public List <Integer> queueProductNo=new ArrayList<>();
	public List <String> MachineCurrentProduct=new ArrayList<>();
	
	
	
	public void setState( List <Integer> q,  List <String> m) {
		this.queueProductNo=q;
		this.MachineCurrentProduct=m;
	}
	public void  finishedreplay() {
	    this.finished=1;

	}

}
