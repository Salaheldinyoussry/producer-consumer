package eg.edu.alexu.csd.oop;

import java.util.ArrayList;
import java.util.List;

public class Observeable {

	protected Observer mq; 
	 public void updateObservers(Machine m) {
			mq.update(m);

	 }


}
