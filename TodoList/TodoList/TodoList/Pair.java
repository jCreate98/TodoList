package TodoList;

import java.io.Serializable;

import javax.swing.JCheckBox;

@SuppressWarnings("serial")
public class Pair implements Serializable{
	public String name;
	public boolean clear;
	public JCheckBox box;
	
	public Pair(String name, boolean clear) {
		this.name = name;
		this.clear = clear;
		this.box = new JCheckBox(name, clear);
		this.box.addActionListener(e -> {
			this.clear = this.box.isSelected();
		});
	}
	
	public JCheckBox box() {
		return this.box;
	}
	
	public String name() {
		return this.name;
	}
	
	public boolean clear() {
		return this.clear;
	}
}
