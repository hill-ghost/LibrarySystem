package com.library.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.library.dao.Dao;
import com.library.util.CreatecdIcon;
import com.library.util.MyDocument;

public class ReaderAddGUI extends JInternalFrame {

	/*
	 * 读者信息录入窗口
	 */
	
	private JTextField city;
	private ButtonGroup buttonGroup = new ButtonGroup();
	private JFormattedTextField keepmoney;
	private JTextField tel;
	private JFormattedTextField date;
	private JFormattedTextField max;
	private JFormattedTextField start;
	private JTextField idcard;
	private JComboBox comboBox;
	private JTextField grade;
	private JTextField age;
	private JTextField name;
	DefaultComboBoxModel comboBoxModel;
	String [] array;

	public ReaderAddGUI() {
		super();
		setTitle("读者相关信息添加");
		setIconifiable(true);							// 设置窗体可最小化－－－必须
		setClosable(true);								// 设置窗体可关闭－－－必须
														// 设置窗体标题－－－必须
		setBounds(100, 100, 500, 400);

		final JLabel logoLabel = new JLabel();
		ImageIcon readerAddIcon=CreatecdIcon.add("readerAdd.png");
		logoLabel.setIcon(readerAddIcon);
		logoLabel.setOpaque(true);
		logoLabel.setBackground(Color.CYAN);
		logoLabel.setPreferredSize(new Dimension(400, 60));
		getContentPane().add(logoLabel, BorderLayout.NORTH);

		final JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		getContentPane().add(panel);

		final JPanel panel_1 = new JPanel();
		final GridLayout gridLayout = new GridLayout(0, 4);
		gridLayout.setVgap(15);
		gridLayout.setHgap(10);
		panel_1.setLayout(gridLayout);
		panel_1.setPreferredSize(new Dimension(450, 250));
		panel.add(panel_1);

		final JLabel label_2 = new JLabel();
		label_2.setText("姓    名：");
		panel_1.add(label_2);

		name = new JTextField();
		name.setDocument(new MyDocument(10));
		panel_1.add(name);

		final JLabel label_3 = new JLabel();
		label_3.setText("性    别：");
		panel_1.add(label_3);

		final JPanel label_13 = new JPanel();
		final FlowLayout flowLayout = new FlowLayout();
		flowLayout.setHgap(0);
		flowLayout.setVgap(0);
		label_13.setLayout(flowLayout);
		panel_1.add(label_13);

		final JRadioButton radioButton1 = new JRadioButton();
		label_13.add(radioButton1);
		radioButton1.setSelected(true);
		buttonGroup.add(radioButton1);
		radioButton1.setText("男");

		final JRadioButton radioButton2 = new JRadioButton();
		label_13.add(radioButton2);
		buttonGroup.add(radioButton2);
		radioButton2.setText("女");
		


		final JLabel label_4 = new JLabel();
		label_4.setText("年    龄：");
		panel_1.add(label_4);

		age = new JTextField();
		age.setDocument(new MyDocument(2));//设置书号文本框最大输入值为2
		age.addKeyListener(new NumberListener());
		panel_1.add(age);

		final JLabel label_5 = new JLabel();
		label_5.setText("年    级：");
		panel_1.add(label_5);

		grade = new JTextField();
		grade.setDocument(new MyDocument(30));
		panel_1.add(grade);

		final JLabel label_6 = new JLabel();
		label_6.setText("有效证件：");
		panel_1.add(label_6);

		comboBox = new JComboBox();
		//comboBoxModel=(DefaultComboBoxModel)comboBox.getModel();
		array=new String[]{"身份证","学生证"};
		comboBox.setModel(new DefaultComboBoxModel(array));
		for(int i=1;i<array.length;i++){
			comboBox.setSelectedIndex(i);
			comboBox.setSelectedItem(array);
		}
		

		
		
		
		
		panel_1.add(comboBox);

		final JLabel label_7 = new JLabel();
		label_7.setText("证件号码：");
		panel_1.add(label_7);

		idcard = new JTextField();
		idcard.addKeyListener(new NumberListener());
		panel_1.add(idcard);

		SimpleDateFormat myfmt=new SimpleDateFormat("yyyy-MM-dd");
		
		JLabel label = new JLabel();
		label.setText("办证日期：");
		panel_1.add(label);
		
		start = new JFormattedTextField(myfmt.getDateInstance());
		start.setValue(new java.util.Date());
		start.addKeyListener(new DateListener());
		panel_1.add(start);

		final JLabel label_10 = new JLabel();
		label_10.setText("有效日期：");
		panel_1.add(label_10);


		date = new JFormattedTextField(myfmt.getDateInstance());
		java.util.Date date2 = new java.util.Date();
		date2.setDate(date2.getDate() + 365);
		date.setValue(date2);
		date.addKeyListener(new DateListener());
		panel_1.add(date);

		JLabel label_11 = new JLabel();
		label_11.setText("电    话：");
		panel_1.add(label_11);
		
		tel = new JTextField();
		tel.addKeyListener(new TelListener());
		tel.setDocument(new MyDocument(11));
		panel_1.add(tel);
		
		JLabel label_1 = new JLabel();
		label_1.setText("籍    贯：");
		panel_1.add(label_1);
		
		city = new JTextField();
		panel_1.add(city);

		JLabel label_12 = new JLabel();
		label_12.setText("押    金：");
		panel_1.add(label_12);
		
		keepmoney = new JFormattedTextField();
		keepmoney.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				String numStr="0123456789"+(char)8;//只允许输入数字与退格键
				if(numStr.indexOf(e.getKeyChar())<0){
					e.consume();
				}
				if(keepmoney.getText().length()>2||keepmoney.getText().length()<0){
					e.consume();
				}
			}
		});
		panel_1.add(keepmoney);
		
		JLabel label_9 = new JLabel();
		label_9.setText("最大可借：");
		panel_1.add(label_9);
		
		max = new JFormattedTextField();
		max.setDocument(new MyDocument(2));
		max.addKeyListener(new NumberListener());
		panel_1.add(max);

		final JPanel panel_2 = new JPanel();
		panel_2.setPreferredSize(new Dimension(450, 100));
		panel.add(panel_2);

		final JButton save = new JButton();
		panel_2.add(save);
		save.setText("确定");
		save.addActionListener(new ButtonAddListener(radioButton1));
		

		final JButton back = new JButton();
		panel_2.add(back);
		back.setText("关闭");
		back.addActionListener(new CloseActionListener());
		setVisible(true);
		//
	}
	class DateListener extends KeyAdapter {
		public void keyTyped(KeyEvent e) {
			if(start.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "时间格式请使用\"2007-05-10\"格式");
			}
		}
	}
	class NumberListener extends KeyAdapter {
		public void keyTyped(KeyEvent e) {
			String numStr="0123456789"+(char)8;
			if(numStr.indexOf(e.getKeyChar())<0){
				e.consume();
			}
		}
	}
	
	//单击保存事件
	class ButtonAddListener implements ActionListener {
		
		private final JRadioButton button1;

		ButtonAddListener(JRadioButton button1) {
			this.button1 = button1;
		}

		public void actionPerformed(final ActionEvent e) {
			
			if(name.getText().length()==0){
				JOptionPane.showMessageDialog(null, "读者姓名文本框不可为空");
				return;
			}
			if(age.getText().length()==0){
				JOptionPane.showMessageDialog(null, "读者年龄文本框不可为空");
				return;
			}
			
			if(idcard.getText().length()==0){
				JOptionPane.showMessageDialog(null, "证件号码文本框不可为空");
				return;
			}
			if(keepmoney.getText().length()==0){
				JOptionPane.showMessageDialog(null, "押金文本框不可为空");
				return;
			}
			if(grade.getText().length()==0){
				JOptionPane.showMessageDialog(null, "年级文本框不可为空");
				return;
			}
			if(city.getText().length()==0){
				JOptionPane.showMessageDialog(null, "籍贯文本框不可为空");
				return;
			}
			if(tel.getText().length()==0){
				JOptionPane.showMessageDialog(null, "电话号码文本框不可为空");
				return;
			}
			if(tel.getText().length()>11||tel.getText().length()<0){
				JOptionPane.showMessageDialog(null, "电话号码位数小于11位");
				return;
			}
			if(max.getText().length()==0){
				JOptionPane.showMessageDialog(null, "最大借书量文本框不可为空");
				return;
			}
			if(start.getText().isEmpty()||date.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "时间格式请使用\"2007-05-10\"格式");
				return;
			}
		
			String sex="男";
			if(!button1.isSelected()){
				sex="女";}
			String zj=String.valueOf(comboBox.getSelectedIndex());
			String id;
			int size = Dao.findnumber("1").size();
			if(size < 10) {
				id = "R1000"+(size+1);
			}
			else {
				if(size < 100) {
					id = "R100"+(size+1);
				}
				else {
					id = "R10"+(size+1);
				}
			}
			int i=Dao.InsertReader(id, name.getText().trim(),sex.trim(),
					idcard.getText().trim(), age.getText().trim(), grade.getText().trim(),
					Date.valueOf(start.getText().trim()), Date.valueOf(date.getText().trim()),
					max.getText().trim(),tel.getText().trim(), Double.valueOf(keepmoney.getText().trim()),city.getText().trim());
			Dao.InsertOperator(id, name.getText().trim(), "1", "000000");
			if(i==1){
				JOptionPane.showMessageDialog(null, "添加成功！");
				doDefaultCloseAction();
			}			
		}
	}
	
	class TelListener extends KeyAdapter {
		public void keyTyped(KeyEvent e) {
			String numStr="0123456789-"+(char)8;
			if(numStr.indexOf(e.getKeyChar())<0){
				e.consume();
			}
		}
	}
	class CloseActionListener implements ActionListener {			// 添加关闭按钮的事件监听器
		public void actionPerformed(final ActionEvent e) {
			doDefaultCloseAction();
		}
	}

}
