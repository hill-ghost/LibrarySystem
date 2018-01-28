package com.library.ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


import com.library.Library;
import com.library.dao.Dao;
import com.library.model.Reader;

public class WelcomeReaderGUI extends JInternalFrame {
	
	private JTextField text_id;
	private JTextField text_name;
	private JTextField text_sex;
	private JTextField text_age;
	private JTextField text_grade;
	private JTextField text_idcard;
	private JTextField text_max;
	private JTextField text_start;
	private JTextField text_end;
	private JTextField text_money;
	private JTextField text_tel;
	private JTextField text_city;
	private Reader reader;
	private JButton bt_modify,bt_cancel;
	private JPanel panel;
	
	public WelcomeReaderGUI() {
		super();
		setIconifiable(true);
		setClosable(true);
		setBounds(900, 100, 500, 700);

		panel = new JPanel();
		GridLayout gridLayout = new GridLayout(0, 2);
		gridLayout.setVgap(8);
		panel.setLayout(gridLayout);
		panel.setPreferredSize(new Dimension(400, 600));
		getContentPane().add(panel);
		
		JLabel label_id = new JLabel();
		label_id.setText("    �� �� �� �ţ�");
		panel.add(label_id);
		text_id = new JTextField();
		text_id.setEditable(false);
		panel.add(text_id);
		
		JLabel label_name = new JLabel();
		label_name.setText("    �� �� �� ����");
		panel.add(label_name);
		text_name = new JTextField();
		text_name.setEditable(false);
		panel.add(text_name);

		JLabel label_sex = new JLabel();
		label_sex.setText("    ��       ��");	
		panel.add(label_sex);
		text_sex = new JTextField();
		text_sex.setEditable(false);
		panel.add(text_sex);
		
		JLabel label_age = new JLabel();
		label_age.setText("    ��       �䣺");
		panel.add(label_age);
		text_age = new JTextField();
		text_age.setEditable(false);
		panel.add(text_age);
		
		JLabel label_grade = new JLabel();
		label_grade.setText("    ��       ����");
		panel.add(label_grade);
		text_grade = new JTextField();
		text_grade.setEditable(false);
		panel.add(text_grade);
		
		JLabel label_city = new JLabel();
		label_city.setText("    ��       �᣺");
		panel.add(label_city);
		text_city = new JTextField();
		text_city.setEditable(false);
		panel.add(text_city);

		JLabel label_idcard = new JLabel();
		label_idcard.setText("    ��   ��  ֤��");
		panel.add(label_idcard);
		text_idcard = new JTextField();
		text_idcard.setEditable(false);
		panel.add(text_idcard);	
		
		JLabel label_max = new JLabel();
		label_max.setText("    �� �� �� �裺");
		panel.add(label_max);
		text_max = new JTextField();
		text_max.setEditable(false);
		panel.add(text_max);
		
		JLabel label_start = new JLabel();
		label_start.setText("    �� ֤ �� �ڣ�");
		panel.add(label_start);
		text_start = new JTextField();
		text_start.setEditable(false);
		panel.add(text_start);
		
		JLabel label_end = new JLabel();
		label_end.setText("    �� Ч �� ����");
		panel.add(label_end);
		text_end = new JTextField();
		text_end.setEditable(false);
		panel.add(text_end);

		JLabel label_tel = new JLabel();
		panel.add(label_tel);
		label_tel.setText("    �� ϵ �� ����");	
		text_tel = new JTextField();
		text_tel.setEditable(false);
		panel.add(text_tel);

		
		JLabel label_money = new JLabel();
		label_money.setText("    Ѻ       ��");
		panel.add(label_money);
		text_money = new JTextField();
		text_money.setEditable(false);
		panel.add(text_money);
		
		bt_modify = new JButton("�޸�");
		bt_modify.addActionListener(new ActionListener(){			
			public void actionPerformed(ActionEvent event) {
				if(bt_modify.getText().equals("�޸�")) {
					bt_modify.setText("����");	
					text_age.setEditable(true);
					text_grade.setEditable(true);
					text_tel.setEditable(true);
					bt_cancel.setVisible(true);
				}
				else {
					bt_modify.setText("�޸�");
					text_age.setEditable(false);
					text_grade.setEditable(false);
					text_tel.setEditable(false);
					bt_cancel.setVisible(false);
					if(text_age.getText().trim().length()!=2 || text_tel.getText().trim().length() != 11) {
						JOptionPane.showMessageDialog(null, "��Ϣ��д����");
					}
					else {
						Dao.updateReader(text_id.getText(),text_age.getText().trim(),text_grade.getText().trim(),text_tel.getText().trim());				
						JOptionPane.showMessageDialog(null, "����ɹ�");
					}
				}
		}
		});
		panel.add(bt_modify);
		bt_cancel = new JButton("ȡ��");
		bt_cancel.setVisible(false);
		bt_cancel.addActionListener(new ActionListener(){			
			public void actionPerformed(ActionEvent event) {
				bt_modify.setText("�޸�");
				text_age.setEditable(false);
				text_grade.setEditable(false);
				text_tel.setEditable(false);
				bt_cancel.setVisible(false);
			}
		});
		panel.add(bt_cancel);

		showInfo(reader = Dao.findReader(Library.getUser().getId()));
		setTitle("��ӭ "+reader.getName()+" ����ͼ�����ϵͳ");
		setVisible(true);
	}
	
	// ��ӹرհ�ť���¼�������
	class CloseActionListener implements ActionListener {
		public void actionPerformed(final ActionEvent e) {
			doDefaultCloseAction();
		}
	}

	public void showInfo(Reader reader){
		text_id.setText(reader.getId());
		text_name.setText(reader.getName());
		text_sex.setText(reader.getSex());
		text_age.setText(reader.getAge());
		text_grade.setText(reader.getGrade());
		text_idcard.setText(reader.getIdcard());
		text_max.setText(reader.getMax());
		text_start.setText(reader.getStart().toString());
		text_end.setText(reader.getEnd().toString());
		text_money.setText(reader.getMoney().toString());
		text_tel.setText(reader.getTel());
		text_city.setText(reader.getCity());
	}
}
