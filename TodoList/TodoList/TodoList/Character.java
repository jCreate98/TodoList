package TodoList;

import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Character implements Serializable{
	public boolean flag;
	public Info A;
	String[] names = {"자쿰", "매그너스", "힐라", "파풀라투스", "피에르", "반반",
					  "블러디퀸", "벨룸", "핑크빈", "시그너스", "스우", "데미안",
					  "가디언 엔젤 슬라임", "루시드", "윌", "더스크", "진 힐라",
					  "듄켈", "검은 마법사", "선택받은 세렌", "감시자 칼로스", "카링"};
	
	JButton edit = new JButton("Edit");
	JTextField namef;
	JTextField jobf;
	
	public Character(String name, String level, String job) {
		A = new Info(name,job);
		this.flag = false;
		edit.setEnabled(false);

		if(A.list.isEmpty()) {
			for(int i = 0 ; i < names.length; i++) {
				A.list.add(new Pair(names[i], false));
			}
		}
	}

	public void init() {
		namef = new JTextField(this.A.name, 20);
		jobf = new JTextField(this.A.job, 20);
		
		flag = false;
		namef.setEnabled(flag);
		jobf.setEnabled(flag);
		
		edit.addActionListener(e -> {
			this.namef.setEnabled(!flag);
			this.jobf.setEnabled(!flag);
			this.flag = !this.flag;
			System.out.println("edited");
			if(!flag) {
				this.A.name = namef.getText().trim();
				this.A.job = jobf.getText().trim();
			}
			}
		);
	}
}
