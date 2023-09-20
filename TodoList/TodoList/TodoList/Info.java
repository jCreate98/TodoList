package TodoList;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Info implements Serializable{
	public String name;
	public String job;
	public String level;

	ArrayList<Pair> list = new ArrayList<>();
	
	public Info (String name, String job) {
		this.name = name;
		this.job = job;
	}
}
