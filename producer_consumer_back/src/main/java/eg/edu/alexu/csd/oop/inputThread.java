package eg.edu.alexu.csd.oop;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class inputThread extends Thread {
	ArrayList <ProductQueue> pq =new ArrayList <>();
	ArrayList <Machine> ma =new ArrayList <>();
	ArrayList <Integer> machineIn ;
	ArrayList <Integer> machineOut ;
	int productNo;
	ProductLine pl;
	ReplaySim rs =new ReplaySim();
	List <Thread> mt=new ArrayList<>();

	
	public inputThread(int m, int q,int productNo ,List <Integer> machineIn ,List <Integer> machineOut) {
		this.productNo=productNo;
         List <Integer> output =new ArrayList<>();
         for(int i=0; i<q; i++) {
        	 boolean flag=false;
        	 for(int j=0; j<machineIn.size(); j++) {
        		 if(machineIn.get(j)==i) {
        			 flag=true;
        			 break;
        		 }
        	 }
        		 if(!flag) {
        		 output.add(i);
        	 
        	 }
         }
		 pl =new ProductLine(m,q,output,productNo);
		
		
		for(int i=0; i<m; i++) {
			Machine mach =new Machine(i, pl);
			this.ma.add(mach);
		}
		
		for(int i=0; i<q; i++) {
			ProductQueue queue =new ProductQueue(i, pl);
			this.pq.add(queue);
		}
		
		for(int i=0; i<m; i++) {
			
			this.ma.get(i).setQueues(pq.get(machineIn.get(i)), pq.get(machineOut.get(i)));
			pq.get(machineIn.get(i)).addMachineToList(ma.get(i));
		}
		
		pl.setMachs(mt,ma);
		pl.setQueues(pq);

		
		
	}

	
	@Override
	public void run() {
		Random rand = new Random(); 

			
			synchronized (this){
				for(int i=0; i<productNo; i++) {
					String p="";
					Random randomGenerator = new Random();
					String red = ""+randomGenerator.nextInt(256);
					String green =""+ randomGenerator.nextInt(256);
					String blue = ""+randomGenerator.nextInt(256);
					
					if(red.length()==1) {
						red="00"+red;
					}
					if(red.length()==2) {
						red="0"+red;		
					}
					if(green.length()==1) {
						green="00"+green;
					}
					if(green.length()==2) {
						green="0"+green;		
					}
					if(blue.length()==1) {
						blue="00"+blue;
					}
					if(blue.length()==2) {
						blue="0"+blue;		
					}
					p+=red+green+blue;
					synchronized(pq.get(0)) {
					pq.get(0).enqueue(p);
					}
					int y=rand.nextInt(20000);  /// input rate 
					try {
						sleep(y);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				}
				
			
		           
			
			}

}
   public	ProductLine getData() {
	   ProductLine l;
	   synchronized(pl){
		    l=pl;
	}
	   return l;
	}
	
public void startSim() {
	this.pl.startSim();
	for(int i=0; i<ma.size(); i++) {
		mt.add(new Thread(this.ma.get(i)));
		mt.get(i).start();
//		try {
//			this.ma.get(i).join();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

	}
	run();
	
	for(int i=0; i<ma.size(); i++) {
		
		try {
			mt.get(i).join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
}	



public void replay() {
	rs.setCareTaker(pl.getCareTaker());
	rs.run();
}

public	ProductLineReplay getReplayUpdate() {
	ProductLineReplay l;
	   synchronized(rs.getData()){
		    l=rs.getData();
	}
	   return l;
	}






}