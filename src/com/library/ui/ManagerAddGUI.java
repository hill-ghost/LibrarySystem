package com.library.ui;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.library.dao.Dao;
import com.library.util.MyDocument;

/**
 * ���ͼ�����Ա�����Ա
 */
public class ManagerAddGUI extends JInternalFrame {
	
	private JTextField text_idCard;
	private JTextField text_tel;
	private JTextField text_age;
	private JTextField text_grade;
	private JTextField text_name;
	private JTextField text_password;
	private JButton bt_confirm,bt_cancel;
	private ButtonGroup bg_type,bg_sex;
	private JRadioButton radioButton_l,radioButton_s;
	private JRadioButton radioButton_b,radioButton_g;
	
	public ManagerAddGUI() {
		super();	
		//ʹ��Сʹ����������½�
		setIconifiable(true);
		setClosable(true);
		setTitle("�û���Ϣ���");
		setBounds(120, 120, 600, 470);

		FlowLayout flowLayout = new FlowLayout();
		flowLayout.setHgap(0);
		flowLayout.setVgap(0);
		
		//�����
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1,BorderLayout.CENTER);

		JPanel panel_2 = new JPanel();
		GridLayout gridLayout = new GridLayout(0, 2);
		gridLayout.setVgap(5);
		panel_2.setLayout(gridLayout);
		panel_2.setPreferredSize(new Dimension(500, 350));
		panel_1.add(panel_2);

//		JLabel label_type = new JLabel();
//		label_type.setText("�û�����");
//		panel_2.add(label_type);
//		
//		JPanel panel_tpchoice = new JPanel();
//		panel_tpchoice.setLayout(flowLayout);
//		panel_2.add(panel_tpchoice);

//		bg_type = new ButtonGroup();
//		radioButton_l = new JRadioButton();
//		radioButton_l.setText("ͼ�����Ա");	
//		radioButton_l.setSelected(true);
//		bg_type.add(radioButton_l);
//		panel_tpchoice.add(radioButton_l);	
//		radioButton_s = new JRadioButton();
//		radioButton_s.setText("ϵͳ����Ա");
//		radioButton_s.setSelected(true);
//		bg_type.add(radioButton_s);
//		panel_tpchoice.add(radioButton_s);
		
		JLabel label_name = new JLabel();
		label_name.setText("�û�������");
		panel_2.add(label_name);
		text_name = new JTextField();
		text_name.setHorizontalAlignment(JTextField.CENTER);		
		panel_2.add(text_name);

		JLabel label_sex = new JLabel();
		label_sex.setText("��    ��");
		panel_2.add(label_sex);

		JPanel panel_8 = new JPanel();
		panel_8.setLayout(flowLayout);
		panel_1.add(panel_8);

		bg_sex = new ButtonGroup();
		radioButton_b = new JRadioButton();
		radioButton_b.setText("��");	
		radioButton_b.setSelected(true);
		bg_sex.add(radioButton_b);
		panel_8.add(radioButton_b);	
		radioButton_g = new JRadioButton();
		radioButton_g.setText("Ů");
		radioButton_g.setSelected(true);
		bg_sex.add(radioButton_g);
		panel_8.add(radioButton_g);

		panel_2.add(panel_8);

		JLabel label_age = new JLabel();
		label_age.setText("��    �䣺");
		panel_2.add(label_age);
		text_age = new JTextField();
		text_age.setDocument(new MyDocument(2)); 
		text_age.setHorizontalAlignment(JTextField.CENTER);
		text_age.setColumns(2);
		text_age.addKeyListener(new NumberListener());
		panel_2.add(text_age);
		
		JLabel label_grade = new JLabel();
		label_grade.setText("��    ����");
		panel_2.add(label_grade);
		text_grade = new JTextField();
		text_grade.setHorizontalAlignment(JTextField.CENTER);
		text_grade.setColumns(2);
		panel_2.add(text_grade);

		JLabel label_tel = new JLabel();
		label_tel.setText("��ϵ�绰��");
		panel_2.add(label_tel);
		text_tel = new JTextField("�绰�ű�����ʮһλ",11);		
        text_tel.setDocument(new MyDocument(11)); 
        text_tel.setHorizontalAlignment(JTextField.CENTER);
		text_tel.setColumns(11);
		text_tel.addKeyListener(new NumberListener());
		panel_2.add(text_tel);
      
		JLabel label_idCard = new JLabel();
		label_idCard.setText("���֤�ţ�");
		panel_2.add(label_idCard);
		text_idCard = new JTextField();
		text_idCard.setHorizontalAlignment(JTextField.CENTER);
		text_idCard.addKeyListener(new NumberListener());
		panel_2.add(text_idCard);
		text_idCard.setColumns(20);
		
		JLabel label_password = new JLabel();
		label_password.setText("��    �룺");
		panel_2.add(label_password);
		text_password = new  JPasswordField();
		text_password.setHorizontalAlignment(JTextField.CENTER);
		panel_2.add(text_password);
		
		JPanel panel_bt = new JPanel();
		bt_confirm = new JButton("����");
		panel_bt.add(bt_confirm);
		
		bt_confirm.addActionListener(new ActionListener(){			
			public void actionPerformed(ActionEvent event) {
				if(event.getSource()==bt_confirm){
					if(text_name.getText().length()==0){
						JOptionPane.showMessageDialog(null, "�û�������Ϊ��");
						return;
					}					
					if(text_age.getText().length()==0){
						JOptionPane.showMessageDialog(null, "���䲻��Ϊ��");
						return;
					}
					if(text_tel.getText().length()==0){
						JOptionPane.showMessageDialog(null, "�绰����Ϊ��");
						return;
					}
					if(text_tel.getText().length()!=11){
						JOptionPane.showMessageDialog(null, "�绰�ű�����ʮһλ");
						return;
					}
					if(text_idCard.getText().length()==0){
						JOptionPane.showMessageDialog(null, "���֤����Ϊ��");
						return;
					}
					if(text_password.getText().length()==0){
						JOptionPane.showMessageDialog(null, "���벻��Ϊ��");
						return;
					}
					if(text_password.getText().length()<6){
						JOptionPane.showMessageDialog(null, "���벻��С��6λ");
						return;
					}
					String authority = "2";
					String id;
					int size = Dao.countOperator("L").size();
					if(size<10) {
						id = "L1000" + (size + 1);
					}
					else {
						if(size < 100){
							id = "L100" + (size + 1);
						}
						else {
							id = "L10" + (size + 1);
						}
					}
//					if(radioButton_S.isSelected()) {
//						authority = "3";
//						id = "S" + (Dao.countOperator("S").size() + 1);
//					}
					String name=text_name.getText();
					String sex="��";				
					if(radioButton_g.isSelected()){
						sex="Ů";
					}
					String age=text_age.getText();
					String grade=text_grade.getText();
					String tel=text_tel.getText();
					String idCard = text_idCard.getText();
					String password=text_password.getText();
					Dao.InsertOperator(id,name,authority,password);
					int i = Dao.InsertReader(id, name, sex, idCard, age, grade,tel);
					if(i==1){
						JOptionPane.showMessageDialog(null, "��ӳɹ���");
						doDefaultCloseAction();
					}
				}
			}			
		});				
		
		bt_cancel = new JButton("ȡ��");
		bt_cancel.addActionListener(new CloseActionListener());
		panel_bt.add(bt_cancel);
		
		panel.add(panel_bt,BorderLayout.SOUTH);
		
		//��ʾҳ��
		setVisible(true);
	}
	
	class CloseActionListener implements ActionListener {			// ��ӹرհ�ť���¼�������
		public void actionPerformed(final ActionEvent e) {
			doDefaultCloseAction();
		}
	}
}

class NumberListener extends KeyAdapter {
	public void keyTyped(KeyEvent e) {
		String numStr="0123456789.X"+(char)8;
		if(numStr.indexOf(e.getKeyChar())<0){
			e.consume();
		}
	}
}
