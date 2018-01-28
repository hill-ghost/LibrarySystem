package com.library.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.library.dao.Dao;
import com.library.model.Librarian;
import com.library.util.MyDocument;

public class ManagerModifyGUI extends JInternalFrame {
	private JTextField text_id;
	private JTextField text_password;
	private JTextField text_idCard;
	private JTextField text_tel;
	private JTextField text_grade;
	private JTextField text_age;
	private JTextField text_name;
	private JTable table;
	private String[] str = {"ͼ�����Ա���", "����", "�Ա�", "����", "�꼶","�绰","���֤","����"};
	private ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton JRadioButton1,JRadioButton2;
	
	private Object[][] getFileStates(List list){
		Object[][] librarians = new Object[list.size()][str.length];
		for(int i=0;i<list.size();i++){
			Librarian librarian=(Librarian)list.get(i);
			librarians[i][0]=librarian.getId();
			librarians[i][1]=librarian.getName();
			librarians[i][2]=librarian.getSex();
			librarians[i][3]=librarian.getAge();
			librarians[i][4]=librarian.getGrade();
			librarians[i][5]=librarian.getTel();
			librarians[i][6]=librarian.getIdCard();
			librarians[i][7]=librarian.getPassword();
		}
		return librarians;	         		
	}
	
	public ManagerModifyGUI() {
		super();
		setIconifiable(true);
		setClosable(true);
		setTitle("�û���Ϣ�޸���ɾ��");
		setBounds(100, 100, 800, 650);

		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(0, 220));
		getContentPane().add(panel, BorderLayout.NORTH);

		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(780, 220));
		panel.add(scrollPane);

		Object[][] results=getFileStates(Dao.selectuser());
		table = new JTable(results,str);
		//�����п�
		TableColumn column2;
		for(int i=0;i<table.getColumnCount();i++) {
			column2 = table.getColumnModel().getColumn(i);
			if(i == 0) {
				column2.setPreferredWidth(150);
			}
			else {
				column2.setPreferredWidth(120);
			}
		}
		table.setRowHeight(27);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(final MouseEvent e) {				
				String id,name,sex,age,grade,idCard,tel,password;
				int selRow = table.getSelectedRow();
				id = table.getValueAt(selRow, 0).toString().trim();
				name = table.getValueAt(selRow, 1).toString().trim();
				if(table.getValueAt(selRow, 2).toString().trim().equals("��"))
					JRadioButton1.setSelected(true);
				else
					JRadioButton2.setSelected(true);
				age = table.getValueAt(selRow, 3).toString().trim();
				grade = table.getValueAt(selRow, 4).toString().trim();
				tel = table.getValueAt(selRow, 5).toString().trim();
				idCard = table.getValueAt(selRow, 6).toString().trim();
				password = table.getValueAt(selRow, 7).toString().trim();
				text_id.setText(id);
				text_name.setText(name);
				text_age.setText(age);
				text_idCard.setText(idCard);
				text_grade.setText(grade);
				text_tel.setText(tel);
				text_password.setText(password);				
			}
		});
		
		scrollPane.setViewportView(table);
		
		
		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout_1 = new FlowLayout();
		flowLayout_1.setHgap(2);
		flowLayout_1.setVgap(9);
		panel_2.setLayout(flowLayout_1);
		getContentPane().add(panel_2);
		panel_2.setPreferredSize(new Dimension(400, 400));

		JPanel panel_4 = new JPanel();
		GridLayout gridLayout = new GridLayout(0, 2);
		gridLayout.setVgap(8);
		panel_4.setLayout(gridLayout);
		panel_4.setPreferredSize(new Dimension(400, 300));
		panel_2.add(panel_4);
		final JLabel label_8 = new JLabel();
		panel_4.add(label_8);
		label_8.setText("    �� �� �� �ţ�");

		text_id = new JTextField();
		text_id.setEditable(false);
		panel_4.add(text_id);
		
		final JLabel label = new JLabel();
		panel_4.add(label);
		label.setText("    �� �� �� ����");

		text_name = new JTextField();
		panel_4.add(text_name);

		final JLabel label_1 = new JLabel();
		panel_4.add(label_1);
		label_1.setText("    ��       ��");
		final JPanel panel_3 = new JPanel();
		panel_4.add(panel_3);
		final FlowLayout flowLayout = new FlowLayout();
		flowLayout.setVgap(0);
		panel_3.setLayout(flowLayout);

		JRadioButton1 = new JRadioButton();
		JRadioButton1.setSelected(true);
		buttonGroup.add(JRadioButton1);
		panel_3.add(JRadioButton1);
		JRadioButton1.setText("��");
		JRadioButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		JRadioButton2 = new JRadioButton();
		JRadioButton2.setSelected(true);
		buttonGroup.add(JRadioButton2);
		panel_3.add(JRadioButton2);
		JRadioButton2.setText("Ů");
		

		final JLabel label_2 = new JLabel();
		panel_4.add(label_2);
		label_2.setText("    ��       �䣺");

		text_age = new JTextField();
		text_age.setMinimumSize(new Dimension(0, 1));
		panel_4.add(text_age);
		text_age.setDocument(new MyDocument(2)); 
			
		text_age.setColumns(2);
		text_age.addKeyListener(new NListener());

		JLabel label_grade = new JLabel();
		panel_4.add(label_grade);
		label_grade.setText("    ��       ����");
		text_grade = new JTextField();
		panel_4.add(text_grade);
		
		JLabel label_idCard = new JLabel();
		panel_4.add(label_idCard);
		label_idCard.setText("    ��   ��  ֤��");

		text_idCard = new JTextField();
		panel_4.add(text_idCard);
		text_idCard.setColumns(20);

		final JLabel label_4 = new JLabel();
		panel_4.add(label_4);
		label_4.setText("    �� ϵ �� ����");
        
		
		text_tel = new JTextField("�绰�ű�����ʮһλ",11);
		panel_4.add(text_tel);
		text_tel.setDocument(new MyDocument(11)); 
		
		text_tel.setColumns(11);
		text_tel.addKeyListener(new NListener());

		
		
		final JLabel label_5 = new JLabel();
		panel_4.add(label_5);
		label_5.setText("    ��       �룺");

		text_password = new JTextField();
		panel_4.add(text_password);

		final JPanel panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(0, 50));
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		final JButton button = new JButton();
		button.setText("�޸�");
		panel_1.add(button);
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {				
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
								
				String id=text_id.getText();
				String name=text_name.getText();
				String sex="��";
				if(JRadioButton2.isSelected()){
					sex="Ů";}
				String age=text_age.getText();
				String idCard=text_idCard.getText();
				String grade=text_grade.getText();
				String tel=text_tel.getText();
				String password=text_password.getText();
				Dao.updateOperator(id, name, password);
				int i = Dao.updateReader(id, name, sex, age, grade, idCard, tel);
				if(i==1){
					JOptionPane.showMessageDialog(null, "�޸ĳɹ�");
					Object[][] results=getFileStates(Dao.selectuser());
					DefaultTableModel model=new DefaultTableModel();
					table.setModel(model);
					model.setDataVector(results,str);
					TableColumn column2;
					for(int j=0;j<table.getColumnCount();j++) {
						column2 = table.getColumnModel().getColumn(j);
						if(j == 0) {
							column2.setPreferredWidth(150);
						}
						else {
							column2.setPreferredWidth(120);
						}
					}
					table.setRowHeight(27);
				}
			}
			
		});
		
		JButton button_2 = new JButton();
		button_2.setText("ɾ��");
		panel_1.add(button_2);
		button_2.addActionListener(new ActionListener(){
			public void actionPerformed(final ActionEvent e) {
				String id=text_id.getText();
				int i=Dao.delReader(id);
				if(i==1){
					JOptionPane.showMessageDialog(null, "ɾ���ɹ�");
					Object[][] results=getFileStates(Dao.selectuser());
					DefaultTableModel model=new DefaultTableModel();
					table.setModel(model);
					model.setDataVector(results,str);
					TableColumn column2;
					for(int j=0;j<table.getColumnCount();j++) {
						column2 = table.getColumnModel().getColumn(j);
						if(j == 0) {
							column2.setPreferredWidth(150);
						}
						else {
							column2.setPreferredWidth(120);
						}
					}
					table.setRowHeight(27);
				}
			}
		});
		
		
		final JButton button_1 = new JButton();
		button_1.setText("�˳�");
		panel_1.add(button_1);
        button_1.addActionListener(new CloseActionListener());
		
		setVisible(true);
	}
	
	// ��ӹرհ�ť���¼�������
	class CloseActionListener implements ActionListener {
		public void actionPerformed(final ActionEvent e) {
			doDefaultCloseAction();
		}
	}

}

//ֻ������������ļ���
class NListener extends KeyAdapter {
	public void keyTyped(KeyEvent e) {
		String numStr="0123456789."+(char)8;
		if(numStr.indexOf(e.getKeyChar())<0){
			e.consume();
		}
	}
}
