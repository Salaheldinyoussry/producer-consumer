package eg.edu.alexu.csd.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Machine extends Observeable  implements Runnable  {
	 ProductQueue out ;
	 ProductQueue MachineInQueue;
	 int name;
	 ProductLine pl;
	 boolean state =true;
	 private volatile boolean stop=false;
	 int serveTime=0;
	public	String product="empty";
	public Machine(int name ,ProductLine pl ) {
		this.name=name;
	  this.pl=pl;
		Random rand = new Random(); 
		this.serveTime=Math.max(rand.nextInt(45000),15000);  // serving time interval
		
	}
	public void setQueues(ProductQueue in, ProductQueue out) {
		this.MachineInQueue=in;
		this.mq=in;
		this.out=out;
			
	}
	@Override
	public void run() {
		
		while(!stop) {
	          this.product=MachineInQueue.dequeue(name);
	          if(stop) {
	        	  break;
	          }
	          try {
	 	         if(!stop) {

	        	  Thread.sleep(serveTime);
	 	         }
	 	         } catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				//e1.printStackTrace();
			}
	          if(stop) {
	        	  break;
	          }
	          String y=this.product;
	         this.product="green"; 
	         updateObservers(this);

	         try {
		         if(!stop) {

				Thread.sleep(500);    // flashing time
		         }
				this.product=y; 
		         updateObservers(this);

	         } catch (InterruptedException e) {
				// TODO Auto-generated catch block
			//	e.printStackTrace();
			}
	         if(stop) {
	        	  break;
	          }
	         this.product="empty"; 
	         updateObservers(this);
      
	         out.enqueue(y);

       	//  System.out.println("M"+this.name+"  finished "+this.product);
	      


	          
	       }
	}
//	public void updateObservers() {
//	    //int h=Integer.parseInt(""+name.charAt(1));
//
//	//synchronized(MachineInQueue) {
//	//}
//	}
	
	
	public void doStop() {
		stop=true;
		
	}
	

}
