package eg.edu.alexu.csd.oop;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ProductLine {


	public List <Integer> queueProductNo=new ArrayList<>();
	public List <String> MachineCurrentProduct=new ArrayList<>();
    private List <Thread> machs;
    private List <Machine> cmachs;

    private List <ProductQueue> pq;

    private List <Integer> Output; 
    public int finished=0;
    private Originator org =new Originator();
    private CareTaker ct =new CareTaker(); 

    private int proNo;
    private int m;
    private int q;
  
	 public  ProductLine() {
		 
	 }
  
  public  ProductLine(int m,int q ,List <Integer> Output, int proNo	) {
  this.Output=Output;
	  this.proNo=proNo;
	 //queueProductNo.add(x);
	 for(int i=0; i<q; i++) {
		 queueProductNo.add(0);
	 }
	 for(int i=0; i<m; i++) {
		 MachineCurrentProduct.add("empty");
	 }
	 //this.org.setState(queueProductNo, MachineCurrentProduct,0);
	// printt();
  }
  public void setMachs(List <Thread>machs ,List <Machine> cmachs) {
		 this.machs=machs;
		 this.cmachs=cmachs;
	  
  }
  public void setQueues(ArrayList<ProductQueue> pq) {
		this.pq=pq;
	}
//	 public  void updateMachine (String p,int i) {
//		 synchronized(MachineCurrentProduct) {
//			 MachineCurrentProduct.set(i-1, p);
//			 printt();
//			 }
//	 }
//	 public  void updateQueue (int p,int i) {
//		 synchronized(queueProductNo) {
//		 queueProductNo.set(i-1, p);
//		 printt();
//		 }
//	 } 
	 
	 public synchronized void   update(int p,int i,String p1,int i1) {
		 if(p==-1) {
			 
				 MachineCurrentProduct.set(i1, p1);
				// printt();
				 
		 }
		 else if(i1==-1) {
				 queueProductNo.set(i, p);
				// printt();
				 
			 
		 }
		 else {
				 queueProductNo.set(i, p);				 
				 MachineCurrentProduct.set(i1, p1);
				
				 
			 
		 }
		 long st = System.nanoTime();
         st=st/1000000;
         
    	 this.org.setState(queueProductNo, MachineCurrentProduct,st);
    	 this.ct.add(this.org.savestateToMemento());

		
		
		// printt();
		 finishSim();
		 
		 
	 }
	 private void finishSim() {
		 int sum=0;
		 boolean flag=false;
		 for(int i=0; i<Output.size(); i++) {
			 sum+=queueProductNo.get(Output.get(i));
		 }
		 for(int i=0; i<Output.size(); i++) {
           if(!MachineCurrentProduct.get(i).equalsIgnoreCase("empty")) {
        	   flag=true;
        	   break;
           }
		 } 
		// System.out.println("sum = "+sum);
		 if(sum==proNo && !flag) {
			 this.finished=1;
			 
			 for(int i=0; i<pq.size(); i++) {
				 synchronized(pq.get(i).lock){
				 pq.get(i).lock.notifyAll();
				 }
				 }
			 for(int j=0; j<machs.size(); j++) {
			 MachineCurrentProduct.set(j, "empty");
			 machs.get(j).interrupt();
		 }
			 for(int i=0; i<cmachs.size(); i++) {
				
				 cmachs.get(i).doStop();
				 
				 }
		//  for(int k=0; k<10; k++) {
			 for(int i=0; i<pq.size(); i++) {
				 synchronized(pq.get(i).lock){
				 pq.get(i).lock.notifyAll();
				 }
				 }
			 //}
			 
			 
			 for(int j=0; j<machs.size(); j++) {
			 MachineCurrentProduct.set(j, "empty");
			 machs.get(j).interrupt();
		 }
               
			 for(int j=0; j<machs.size(); j++) {  
                 if(machs.get(j).isAlive()) {
				 machs.get(j).stop();}
			 }
	
		 
		 
		 }
		 
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

	public void startSim() {
     	List<Integer> lplp =new ArrayList<>();
     	List<String> lplpa =new ArrayList<>();

     	for(int i=0; i<q; i++) {
			 lplp.add(0);
		 }
		 for(int i=0; i<m; i++) {
			 lplpa.add("empty");
		 }
		 long st = System.nanoTime();
           st=st/1000000;
           
      	 this.org.setState(lplp, lplpa,st);
      	 this.ct.add(this.org.savestateToMemento());
      	 
   
           
		
		
	}

	
	public CareTaker getCareTaker() {
		return this.ct;
	}
	
	
	 
  
  
//	for(int i=0; i<pq.size(); i++) {
//		 synchronized(pq.get(i)){
//		 pq.get(i).notifyAll();
//		 }
//		 }
//	 
//	 for(int j=0; j<machs.size(); j++) {
//		 MachineCurrentProduct.set(j, "empty");
//		 machs.get(j).doStop();
//	 }
//	 for(int i=0; i<pq.size(); i++) {
//		 synchronized(pq.get(i)){
//		 pq.get(i).notifyAll();
//		 }
//		 }
  
  
}
