package eg.edu.alexu.csd.oop;

import java.util.ArrayList;
import java.util.List;

public class ProductQueue implements Observer {
	//List <String> machinesNames ;
	List <Machine> machines =new ArrayList<>();
	int name;

	List <Boolean> machineState =new ArrayList<>();
	
	 List<String> p=new ArrayList<>();
	 ProductLine pl;
	 
	 Object lock =new Object();
	 
public ProductQueue (int name , ProductLine pl) {
	this.name=name;
	this.pl=pl;
}
public void addMachineToList(Machine m) {
	this.machines.add(m);
	this.machineState.add(true);
	
}
	
	
//	public setQu(List <Machine> t,List <String> machineNames,List <String> p,int name, ProductLine pl) {
//		for(int i=0; i<machineNames.size(); i++) {
//			machineState.add(true);	
//		}
//		this.machinesNames=machineNames;
//		this.machines=t;
//		this.p=p;
//		this.name=name;
//		this.pl=pl;
//		
//	}
	
	 
	 public  void enqueue(String x) {
		 
		 synchronized(lock) {
		    p.add(x);
		    updateProductLine(p.size(),name,"",-1);
            lock.notify();
		 
		 }
		    
		    
		    
		    
		    //			  this.notify();
//
//		 for(int i=0; i<machines.size(); i++) {
//			 if(!machineState.get(i)) {
//				 machineState.set(i, true);
//				  this.notify();
//
//			 }
//		 }
		   // int h=Integer.parseInt(""+name.charAt(1));
           //  pl.update(p.size(),h,"",-1 );
		   // updateProductLine(p.size(),name,"",-1);
	
		    // System.out.println(name+"   has "+p.size()+" p");
		 
	 }
	 public synchronized void  ChangeMachineState(Machine mm) {
         updateProductLine(p.size(),name,mm.product,mm.name);

		// int h=Integer.parseInt(""+name.charAt(1));
		 
		 int ind =0;
		 for(int i=0; i<this.machines.size(); i++) {
			 if(mm.name==machines.get(i).name) {
				 ind=i;
				 break;
			 }
		 }
		 machineState.set(ind, mm.state);
//		 if(!mm.state) {
//
//	         updateProductLine(p.size(),name,"empty",mm.name);
//	         if(p.size()>0) {
//	        	 this.notify();
//	         }
//		 }
//		 else {
//	         updateProductLine(p.size(),name,mm.product,mm.name);
//			 
//		 }
		 
	 }
	 
	 public  String dequeue(int o)  {
		 synchronized(lock) {
			 while(p.size()<=0) {
				 
				 try {
					lock.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
			 }
			 //System.out.println("m"+o +" fwait");
				String x=p.get(0);
				 p.remove(0);
			
			
			 updateProductLine(p.size(),name,x,o);
			 return x;
		 }
      // updateProductLine(p.size(),name,x,o);
		 
		 
//		 //int h=Integer.parseInt(""+name.charAt(1));
//		 if(p.size()<=0) {
//	        // updateProductLine(p.size(),h,"empty",o);
//			 return "empty";
//		 }
//		 String x=p.get(0);
//		 p.remove(0);
//         updateProductLine(p.size(),name,x,o);
//         //p1.update(p.s)
//		// System.out.println(name+"   has "+p.size()+" p");
//
//		 return x;
//	 
	 }
	 
//	 public int getProductQueueSize() {
//		 return p.size();
//	 }
	 
	 
	 private  void  updateProductLine(int a,int b,String c,int d) {
	
		 //   synchronized(this) {
//			    try {
//					wait(1);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
		
		 if(a==-1) {
			 pl.update(-1,-1,c, d);
		 }
		 else if(d==-1) {
			 pl.update(p.size(), b,"",-1);
			 
		 }
		 else {
			 pl.update(p.size(),b,c, d);
			// pl.updateQueue(a, b);

		 }}
		 //}
	
	public void update(Machine m) {
		ChangeMachineState(m);	
	}
		 
	 
	 
}
