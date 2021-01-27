package eg.edu.alexu.csd.oop;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
public class controller {
 int counter=0;
  //inputThread it; 
  List <inputThread> allit =new ArrayList<>();

 @GetMapping("getSessionKey")
 public int key() {
	int tmp=counter;
	counter++;
	allit.add(null);
	return tmp;
 }
  
 @PostMapping("startSim/{sessionKey}")
 public  void sim(@PathVariable int sessionKey, @RequestBody UserInput ui) {

	 allit.set(sessionKey,  new inputThread(ui.machineNo, ui.queueNo, ui.productNo,ui.machineIn,ui.machineOut));

//		ArrayList <Integer> machineIn =new ArrayList<>();
//		ArrayList <Integer> machineOut=new ArrayList<>(); 
//		machineIn.add(0);
//		machineIn.add(0);
//		machineOut.add(1);
//		machineOut.add(1);
//
//		
//		 it =new inputThread(2, 2, 3,machineIn,machineOut);
	 allit.get(sessionKey).startSim();
 
 }
 


@GetMapping("getUpdate/{sessionKey}")
public ProductLine getUpdate(@PathVariable int sessionKey) {
  	if(allit.get(sessionKey)==null) {
  		return null;
  	}
	return this.allit.get(sessionKey).getData();
}


@GetMapping ("replay/{sessionKey}")
public String replay(@PathVariable int sessionKey) {
if(allit.get(sessionKey)==null) {
	return "invalid";
}
else {
	allit.get(sessionKey).replay();
return "valid";
	
}
}

@GetMapping("getReplayUpdate/{sessionKey}")
public ProductLineReplay getReplayUpdate(@PathVariable int sessionKey) {
  	//System.out.println("wtf jjj");
	if(allit.get(sessionKey)==null) {
  		return null;
  	}
	return this.allit.get(sessionKey).getReplayUpdate();
}




}
