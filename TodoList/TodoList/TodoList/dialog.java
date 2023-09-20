package TodoList;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class dialog extends JDialog {
	JLabel lname = new JLabel("닉네임");
	JLabel ljob = new JLabel("직업");
	JTextField name = new JTextField(10);
	JTextField level = new JTextField(3);
	JTextField job = new JTextField(12);
	JButton ok = new JButton("OK");
	cardlay frame;
	
	public dialog(cardlay frame, String title, boolean modal) {
		super(frame, title, modal);
		this.frame = frame;
		JPanel a = new JPanel();
		JLabel blank = new JLabel();
		a.setLayout(new GridLayout(5,2));
		a.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		a.add(blank);
		a.add(lname);
		a.add(name);
		a.add(blank);
		a.add(ljob);
		a.add(job);
		a.add(blank);
		this.add(a,BorderLayout.CENTER);
		this.add(ok,BorderLayout.SOUTH);
		this.setSize(300,300);
		this.setLocation(frame.de.width/4, frame.de.height/4);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		ok.addActionListener(e -> {
			String iname = name.getText();
			String ijob = job.getText();
			if (iname == null) {
				iname = "name";
			}
			if (ijob == null) {
				ijob = "newbie";
			}
			frame.cha.add(new Character(iname, "200", ijob));
			frame.addinfo(frame.cha.get(frame.cha.size() - 1));
			frame.di = null;
			dispose();
		});
		this.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosed(e);
				frame.di = null;
				name.setText(null);
				level.setText(null);
				job.setText(null);
			}
			
			public void windowClosed(WindowEvent e) {
				name.setText(null);
				level.setText(null);
				job.setText(null);
			}
		});
	}
}
