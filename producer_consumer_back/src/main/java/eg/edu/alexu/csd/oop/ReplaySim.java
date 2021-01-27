package eg.edu.alexu.csd.oop;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

public class ReplaySim extends Thread{
    public int finished=0;


	public List <Integer> queueProductNo;
	public List <String> MachineCurrentProduct;
	private CareTaker ct;
	private ProductLineReplay plr =new ProductLineReplay();
	
	public ReplaySim() {
	//	this.ct=ct;
//		 for(int i=0; i<q; i++) {
//			 queueProductNo.add(0);
//		 }
//		 for(int i=0; i<m; i++) {
//			 MachineCurrentProduct.add("empty");
//		 }
	}
	public void setCareTaker(CareTaker ct) {
		this.ct=ct;
	}
	
   @Override
  public void run() {
	   this.plr.finished=0;
	   synchronized(this) {
	   for(int i=0; i<ct.getStatesNumber()-1; i++) {
		   Memento m =ct.getState(i);
		   Memento m1 =ct.getState(i+1);
               
		   this.queueProductNo=m.getQueueState();
		   this.MachineCurrentProduct=m.getMachineState();
		   synchronized(plr) {
		   setState();
		   }
		   //printt();
	        try {
	        	//System.out.println(m1.getStarttime()-m.getStarttime());
				long x=m1.getStarttime()-m.getStarttime();
				if(x>0) {
	        	wait(x);}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	   
	   }
	   Memento m2 =ct.getState(ct.getStatesNumber()-1);
	   this.queueProductNo=m2.getQueueState();
	   this.MachineCurrentProduct=m2.getMachineState();
	   setState();
	  // printt();
       this.finished=1;
       plr.finishedreplay();
     //  System.out.println("f finished");

	   }
		
	}
	 

	 private void setState() {
		 List <Integer> q =new ArrayList<>();
		 List <String> m =new ArrayList<>();
		for(int i=0; i<queueProductNo.size(); i++) {
			q.add(queueProductNo.get(i));
		}
		for(int i=0; i<MachineCurrentProduct.size(); i++){
			m.add(MachineCurrentProduct.get(i));
		}
		
		plr.setState(q, m);
		 
	
	
}
	 public ProductLineReplay getData() {
		 return this.plr;
	 }
	 
	public void printt() {
		 for(int i=0; i< MachineCurrentProduct.size(); i++) {
			 System.out.print("M"+(i+1)+" "+MachineCurrentProduct.get(i)+"  ");
		 
		 }
		 
		 for(int i=0; i< queueProductNo.size(); i++) {
			 System.out.print("Q"+(i)+" "+queueProductNo.get(i)+"  ");
		                
		 }  
		 System.out.print("\n  ");
	
  }



}



