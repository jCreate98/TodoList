package TodoList;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class cardlay extends JFrame implements Serializable{
	
	String path = System.getProperty("user.dir");
	String file = "Data.txt";
	String filename = path + "\\" + file;
	
	Toolkit tk = Toolkit.getDefaultToolkit();
	Dimension de = tk.getScreenSize();
	dialog di = null;
	
	ArrayList<Character> cha = new ArrayList<>();
	CardLayout c1;
	JPanel chara;
	ArrayList<JPanel> card = new ArrayList<>();
	
	public cardlay() {
		this("TodoList");
	}
	
	public cardlay(String title) {
		super(title);
		this.setSize(de.width/4, de.height/2);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocation(de.width/4, de.height/4);
		load();
		init();
		this.setVisible(true);
		this.setIconImage(tk.getImage("img/char1.png"));
	}
	
	@SuppressWarnings("unchecked")
	public void load() {
		System.out.println(filename);
		try(Scanner scan = new Scanner(new File(filename))){
			FileInputStream fin = new FileInputStream(filename);
			ObjectInputStream ois = new ObjectInputStream(fin);
			cha = (ArrayList<Character>)ois.readObject();
			ois.close();
		}
		catch (IOException | ClassNotFoundException e1) {
			try {
				FileOutputStream fout = new FileOutputStream(filename, false);
				ObjectOutputStream out = new ObjectOutputStream(fout);
				out.writeObject(cha);
				out.flush();
				out.close();
				}
			catch (IOException e2) {
				e1.printStackTrace();
				e2.printStackTrace();
			}
		}
	}
	
	public void init() {
		Container c = this.getContentPane();

		JPanel buttons = new JPanel();
		JPanel bottom = new JPanel();
		
		c1 = new CardLayout();
		chara = new JPanel(c1);
		for(int i = 0; i < cha.size(); i++) {
			this.addinfo(cha.get(i));
		}
		
		buttons.setLayout(new GridLayout(1, 3, 3, 3));
		buttons.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		
		JButton add = new JButton("Add");
		
		add.addActionListener(e ->{
			if(di == null) {
				di = new dialog(this, "Add Character", false);
				di.setVisible(true);
			}
			else 
				di.requestFocus();
		});
		
		JButton left = new JButton("<<");
		left.addActionListener(e -> {
			c1.previous(chara);
		});
		JButton right = new JButton(">>");
		right.addActionListener(e -> {
			c1.next(chara);
		});
		
		JButton save = new JButton("Save");
		save.addActionListener(e -> {
			this.save();
		});
		JButton init = new JButton("Init");
		init.addActionListener(e -> {
			for(int i = 0; i < cha.size(); i++) {
				for(int j = 0; j < cha.get(i).A.list.size(); j++) {
					cha.get(i).A.list.get(j).clear = false;
					cha.get(i).A.list.get(j).box().setSelected(false);
				}
			}
		});
		
		bottom.add(save);
		bottom.add(add);
		bottom.add(init);
		
		buttons.add(left);
		buttons.add(right);
		
		c.add(bottom, BorderLayout.SOUTH);
		c.add(buttons, BorderLayout.NORTH);
		c.add(chara, BorderLayout.CENTER);
	}

	public void addinfo(Character input) {
		JPanel temp = new JPanel();
		temp.setLayout(new GridLayout(0, 3));
		input.init();
		temp.add(input.namef);
		temp.add(input.jobf);
		temp.add(input.edit);
		
		for(int j = 0; j < input.A.list.size(); j++) {
			temp.add(input.A.list.get(j).box());
		} 
		
		card.add(temp);
		chara.add(card.get(card.size() - 1), String.valueOf(card.size() - 1));
		c1.next(chara);
	}
	
	public void save() {
		try {
			FileOutputStream fout = new FileOutputStream(filename, false);
			ObjectOutputStream out = new ObjectOutputStream(fout);
			out.writeObject(cha);
			out.flush();
			out.close();
			}
		catch (IOException e) {
			System.out.println("Serializable Error");
			e.printStackTrace();
		}
	}
}
