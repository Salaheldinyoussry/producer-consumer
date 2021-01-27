package eg.edu.alexu.csd.oop;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {



	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
//	
//		ArrayList <Integer> machineIn =new ArrayList<>();
//		ArrayList <Integer> machineOut=new ArrayList<>(); 
//		machineIn.add(0);
//		machineIn.add(1);
////		machineIn.add(2);
////		machineIn.add(2);
////		machineIn.add(1);
////		machineIn.add(3);
////     
////		machineOut.add(2);
////		machineOut.add(1);
////		machineOut.add(3);
////		machineOut.add(3);
//		machineOut.add(1);
//		machineOut.add(2);
//		  inputThread it=new inputThread(2, 3, 5,machineIn,machineOut);
//			 it.startSim(); 

//	}

}}

//    
// 
//	public static void main(String[] args) {
//	//  Machine m =new Machine(5);m ,
////	  Thread t1 =new Thread(m);
////	  Thread t2 =new Thread(m);
//
////		t1.setName("Jack");
////		t2.setName("Joya");
//		
////		t1.start();
////		t2.start();
//	  
////	  Thread [] a=new Thread[3];
////	  for(int i=0; i<3; i++) {
////		  a[i]=new Thread(m);
////		  String v="M"+(i);
////		  a[i].setName(v);
////	  }
////	  a[1].start();
////	  a[0].start();
//		
////		List<Machine> t=new ArrayList<>();
////		List<String> mn=new ArrayList<>();
////		List<String> p=new ArrayList<>();
////
////		List<Machine> t1=new ArrayList<>();
////		List<String> mn1=new ArrayList<>();
////		List<String> p1=new ArrayList<>();
////		
////		mn.add("m1");
////		mn.add("m2");
////		
////		ProductLine pl =new ProductLine(2,2);
////		
////	Machine m1 =new Machine("m1",pl);
////	Machine m2 =new Machine("m2",pl);
////	t.add(m2);
////	t.add(m1);
////	p.add("p1");
////	p.add("p2");
////	p.add("p3");
////
////	ProductQueue q1=new ProductQueue(t,mn,p,"q1",pl); 
////	ProductQueue q2=new ProductQueue(t1,mn1,p1,"q2",pl); 
////
////	
////
////
////	m1.setQueues(q1, q2);
////	m2.setQueues(q1, q2);
////	m1.setName("mach1");
////	m2.setName("mach2");
////
////	m2.start();
////	m1.start();
////	
////	
////	
////	try {
////		m1.join();
////		m2.join();
////
////	} catch (InterruptedException e) {
////		// TODO Auto-generated catch block
////		e.printStackTrace();
////	}
////	
////	q1.enqueue("p1");
////	q1.enqueue("p2");
////	q1.enqueue("p3");
//	
//
//
////		Random randomGenerator = new Random();
////		int red = randomGenerator.nextInt(256);
////		int green = randomGenerator.nextInt(256);
////		int blue = randomGenerator.nextInt(256);
////
////		Color randomColour = new Color(red,green,blue);
////		System.out.println(randomColour);
////	
////		
////		
//   
//		ArrayList <Integer> machineIn =new ArrayList<>();
//		ArrayList <Integer> machineOut=new ArrayList<>(); 
//		machineIn.add(0);
//		machineIn.add(0);
//		machineOut.add(1);
//		machineOut.add(1);
//
//		
//		inputThread it =new inputThread(2, 2, 3,machineIn,machineOut);
//		it.startSim();
//		//it.run();
////		long startTime = System.nanoTime();
//// System.out.print(startTime);
//		
//	}
//  
//  
//}
