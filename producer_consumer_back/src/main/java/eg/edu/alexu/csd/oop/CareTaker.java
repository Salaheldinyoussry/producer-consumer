package eg.edu.alexu.csd.oop;

import java.util.ArrayList;
import java.util.List;

public class CareTaker {
private List<Memento> mementoList =new ArrayList<>();

public void add(Memento m) {
	this.mementoList.add(m);
}

public Memento getState(int i) {
	return this.mementoList.get(i);
}
public int getStatesNumber() {
	return this.mementoList.size();
}

}
