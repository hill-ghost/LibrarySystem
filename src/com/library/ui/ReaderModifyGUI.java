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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.library.dao.Dao;
import com.library.model.Reader;
import com.library.util.CreatecdIcon;
import com.library.util.MyDocument;

public class ReaderModifyGUI extends JInternalFrame {

	/*
	 * 读者信息修改窗口
	 */
	
	private JTextField keepmoney;
	private ButtonGroup buttonGroup = new ButtonGroup();
	private JTable table;
	private JTextField readerId;
	private JTextField grade;
	private JTextField tel;
	private JTextField date;
	private JTextField maxnumber;
	private JTextField bztime;
	private JTextField zjnumber;
	private JTextField city;
	private JComboBox comboBox;
	private JTextField age;
	private JTextField readername;
	private JRadioButton JRadioButton1;
	private JRadioButton JRadioButton2;
	private String[] columnNames={ "编号", "姓名","性别","证件号码","年龄","年级","办证日期","有效日期",
			"最大借书量", "电话","押金","籍贯" };
	private String[] array=new String[]{"身份证","学生证"};

	private Object[][] getFileStates(List list){
		Object[][]results=new Object[list.size()][columnNames.length];
		for(int i=0;i<list.size();i++){
			Reader reader=(Reader)list.get(i);
			results[i][0]=reader.getId();
			results[i][1]=reader.getName();
			results[i][2]=reader.getSex();			
			results[i][3]=reader.getIdcard();
			results[i][4]=reader.getAge();
			results[i][5]=reader.getGrade();
			results[i][6]=reader.getStart();
			results[i][7]=reader.getEnd();
			results[i][8]=reader.getMax();
			results[i][9]=reader.getTel();
			results[i][10]=reader.getMoney();
			results[i][11]=reader.getCity();
		}
		return results;	         		
	}
	
	public ReaderModifyGUI() {
		super();
		setIconifiable(true);
		setClosable(true);
		setTitle("读者信息修改与删除");
		setBounds(100, 100, 600, 800);

		final JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setPreferredSize(new Dimension(400, 80));
		getContentPane().add(panel, BorderLayout.NORTH);

		final JLabel logoLabel = new JLabel();
		ImageIcon readerModiAndDelIcon=CreatecdIcon.add("readerMotify.png");
		logoLabel.setIcon(readerModiAndDelIcon);
		logoLabel.setBackground(Color.CYAN);
		logoLabel.setOpaque(true);
		logoLabel.setPreferredSize(new Dimension(400, 80));
		panel.add(logoLabel);
		logoLabel.setText("读者信息修改logo（400*80）");

		final JPanel panel_1 = new JPanel();
		panel_1.setLayout(new BorderLayout());
		getContentPane().add(panel_1);

		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(0, 350));
		panel_1.add(scrollPane, BorderLayout.NORTH);

		
		final DefaultTableModel model=new DefaultTableModel();
		Object[][] results=getFileStates(Dao.countOperator("R"));
		model.setDataVector(results,columnNames);
		
		table = new JTable();
		table.setModel(model);
		TableColumn column;
		for(int i=0;i<table.getColumnCount();i++) {
			column = table.getColumnModel().getColumn(i);
			column.setPreferredWidth(120);
		}
		table.setRowHeight(27);
		scrollPane.setViewportView(table);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.addMouseListener(new TableListener());

		final JPanel panel_2 = new JPanel();
		final GridLayout gridLayout = new GridLayout(0, 4);
		gridLayout.setVgap(9);
		panel_2.setLayout(gridLayout);
		panel_2.setPreferredSize(new Dimension(0, 280));
		panel_1.add(panel_2, BorderLayout.CENTER);

		final JLabel label_1 = new JLabel();
		label_1.setText("  姓    名：");
		panel_2.add(label_1);

		readername = new JTextField();
		readername.setDocument(new MyDocument(10));
		panel_2.add(readername);

		JLabel label_2 = new JLabel();
		label_2.setText("  性    别：");
		panel_2.add(label_2);

		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout_1 = new FlowLayout();
		flowLayout_1.setVgap(0);
		panel_3.setLayout(flowLayout_1);
		panel_2.add(panel_3);

		JRadioButton1 = new JRadioButton();
		JRadioButton1.setSelected(true);
		buttonGroup.add(JRadioButton1);
		panel_3.add(JRadioButton1);
		JRadioButton1.setText("男");

		JRadioButton2 = new JRadioButton();
		buttonGroup.add(JRadioButton2);
		panel_3.add(JRadioButton2);
		JRadioButton2.setText("女");

		final JLabel label_3 = new JLabel();
		label_3.setText("  年    龄：");
		panel_2.add(label_3);

		age = new JTextField();
		age.setDocument(new MyDocument(2));
		age.addKeyListener(new NumberListener());
		panel_2.add(age);

		JLabel label_5 = new JLabel();
		label_5.setText("  年    级：");
		panel_2.add(label_5);

		grade = new JTextField();
		panel_2.add(grade);

		JLabel label = new JLabel();
		label.setText("  有效证件：");
		panel_2.add(label);

		comboBox = new JComboBox();

		
		comboBox.setModel(new DefaultComboBoxModel(array));
		for(int i=1;i<array.length;i++){
			comboBox.setSelectedIndex(i);
			comboBox.setSelectedItem(array);
		}
		panel_2.add(comboBox);

		final JLabel label_6 = new JLabel();
		label_6.setText("  证件号码：");
		panel_2.add(label_6);

		zjnumber = new JTextField();
		zjnumber.addKeyListener(new NumberListener());
		panel_2.add(zjnumber);

		final JLabel label_7 = new JLabel();
		label_7.setText("  办证日期：");
		panel_2.add(label_7);

		SimpleDateFormat myfmt=new SimpleDateFormat("yyyy-MM-dd");

		bztime = new JFormattedTextField(myfmt.getDateInstance());
		
		panel_2.add(bztime);
		
		final JLabel label_13 = new JLabel();
		label_13.setText("  有效日期：");
		panel_2.add(label_13);

		date = new JFormattedTextField(myfmt.getDateInstance());		
		panel_2.add(date);

		final JLabel label_8 = new JLabel();
		label_8.setText("  电    话：");
		panel_2.add(label_8);

		tel = new JFormattedTextField();
		tel.addKeyListener(new TelListener());
		tel.setDocument(new MyDocument(11));
		panel_2.add(tel);
		
		JLabel label_19 = new JLabel();
		label_19.setText("  籍    贯：");
		panel_2.add(label_19);

		city = new JTextField();
		panel_2.add(city);

		final JLabel label_14 = new JLabel();
		label_14.setText("  押    金：");
		panel_2.add(label_14);

		keepmoney = new JTextField();
		keepmoney.addKeyListener(new KeepmoneyListener());
		panel_2.add(keepmoney);
		
		final JLabel label_9 = new JLabel();
		label_9.setText("  最大借书量：");
		panel_2.add(label_9);

		maxnumber = new JTextField();
		maxnumber.addKeyListener(new NumberListener());
		panel_2.add(maxnumber);

		final JLabel label_4 = new JLabel();
		label_4.setText("  读者编号：");
		panel_2.add(label_4);

		readerId = new JTextField();
		readerId.setEditable(false);
		panel_2.add(readerId);

		final JPanel panel_4 = new JPanel();
		panel_4.setMaximumSize(new Dimension(0, 0));
		final FlowLayout flowLayout = new FlowLayout();
		flowLayout.setVgap(0);
		flowLayout.setHgap(4);
		panel_4.setLayout(flowLayout);
		panel_2.add(panel_4);

		final JButton button = new JButton();
		button.setHorizontalTextPosition(SwingConstants.CENTER);
		panel_4.add(button);
		button.setText("修改");
		button.addActionListener(new ModiButtonListener(model));
		
		
		

		final JButton buttonDel = new JButton();
		panel_4.add(buttonDel);
		buttonDel.setText("删除");
		buttonDel.addActionListener(new DelButtonListener(model));
		setVisible(true);
		//
	}
	class TableListener extends MouseAdapter {
		public void mouseClicked(final MouseEvent e) {
			
			int selRow = table.getSelectedRow();
			readerId.setText(table.getValueAt(selRow, 0).toString().trim());
			readername.setText(table.getValueAt(selRow, 1).toString().trim());
			if(table.getValueAt(selRow, 2).toString().trim().equals("男"))
				JRadioButton1.setSelected(true);
			else
				JRadioButton2.setSelected(true);
			zjnumber.setText(table.getValueAt(selRow, 3).toString().trim());
			age.setText(table.getValueAt(selRow, 4).toString().trim());
			grade.setText(table.getValueAt(selRow, 5).toString().trim());
			bztime.setText(table.getValueAt(selRow, 6).toString().trim());		
			date.setText(table.getValueAt(selRow, 7).toString().trim());
			maxnumber.setText(table.getValueAt(selRow, 8).toString().trim());
			tel.setText(table.getValueAt(selRow, 9).toString().trim());
			keepmoney.setText(table.getValueAt(selRow, 10).toString().trim());
			city.setText(table.getValueAt(selRow, 11).toString().trim());	
		}
	}
	final class NumberListener extends KeyAdapter {
		public void keyTyped(KeyEvent e) {
			String numStr="0123456789"+(char)8;
			if(numStr.indexOf(e.getKeyChar())<0){
				e.consume();
			}
		}
	}
	private final class DelButtonListener implements ActionListener {
		private final DefaultTableModel model;

		private DelButtonListener(DefaultTableModel model) {
			this.model = model;
		}

		public void actionPerformed(final ActionEvent e) {
			int i=Dao.delReader(readerId.getText().trim());
			if(i==1){
				JOptionPane.showMessageDialog(null, "删除成功");
				Object[][] results=getFileStates(Dao.countOperator("R"));
				model.setDataVector(results,columnNames);
				TableColumn column;
				for(int j=0;j<table.getColumnCount();j++) {
					column = table.getColumnModel().getColumn(j);
					column.setPreferredWidth(120);
				}
				table.setRowHeight(27);
				table.setModel(model);
			}
		}
	}
	
	class ModiButtonListener implements ActionListener {
		private final DefaultTableModel model;

		ModiButtonListener(DefaultTableModel model) {
			this.model = model;
		}

		public void actionPerformed(final ActionEvent e) {
			if(readername.getText().length()==0){
				JOptionPane.showMessageDialog(null, "读者姓名文本框不可为空");
				return;
			}
			if(age.getText().length()==0){
				JOptionPane.showMessageDialog(null, "读者年龄文本框不可为空");
				return;
			}
			
			if(zjnumber.getText().length()==0){
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
			if(bztime.getText().length()==0){
				JOptionPane.showMessageDialog(null, "办证时间文本框不可为空");
				return;
			}
			if(tel.getText().length()==0){
				JOptionPane.showMessageDialog(null, "电话文本框不可为空");
				return;
			}
			if(tel.getText().length()>11||tel.getText().length()<0){
				JOptionPane.showMessageDialog(null, "电话位数小于11位");
				return;
			}
			if(maxnumber.getText().length()==0){
				JOptionPane.showMessageDialog(null, "最大借书量文本框不可为空");
				return;
			}
			String sex="男";
			if(!JRadioButton1.isSelected()){
				sex="女";
			}			
			int i=Dao.updateReader(readerId.getText().trim(), readername.getText().trim(),
					sex,zjnumber.getText().trim(), age.getText().trim(),
					grade.getText().trim(), Date.valueOf(bztime.getText().trim()), 
					Date.valueOf(date.getText().trim()), maxnumber.getText().trim(),
					tel.getText().trim(), Double.valueOf(keepmoney.getText().trim()),
					city.getText().trim());
			Dao.updateOperator(readerId.getText().trim(), readername.getText().trim());
			if(i==1){
				JOptionPane.showMessageDialog(null, "修改成功");
				Object[][] results=getFileStates(Dao.countOperator("R"));
				model.setDataVector(results,columnNames);
				TableColumn column;
				for(int j=0;j<table.getColumnCount();j++) {
					column = table.getColumnModel().getColumn(j);
					column.setPreferredWidth(120);
				}
				table.setRowHeight(27);
				table.setModel(model);
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
	
	class KeepmoneyListener extends KeyAdapter {
		public void keyTyped(KeyEvent e) {
			String numStr="0123456789"+(char)8;//只允许输入数字与退格键
			if(numStr.indexOf(e.getKeyChar())<0){
				e.consume();
			}
			if(keepmoney.getText().length()>2||keepmoney.getText().length()<0){
				e.consume();
			}
		}
	}

}
