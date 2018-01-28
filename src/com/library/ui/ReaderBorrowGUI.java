package com.library.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.library.Library;
import com.library.dao.Dao;
import com.library.model.*;

public class ReaderBorrowGUI extends JInternalFrame {

	/*
	 * 个人借阅信息窗口
	 */
	private JTable table;
	private String[] columnNames={ "书本编号", "书本名称", "借阅日期", "应还日期", "处理状态"};
	private Operator operator;
	private int num_borrow;
	private int num_overdue;

	public ReaderBorrowGUI() {
		super();
		setIconifiable(true);
		setClosable(true);
		setTitle("我的借阅信息");
		setBounds(100, 100, 600, 420);

		operator = Library.getUser();
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		getContentPane().add(panel);


		JPanel panel_1 = new JPanel();
		GridLayout gridLayout = new GridLayout(3, 1);
		gridLayout.setVgap(5);
		panel_1.setLayout(gridLayout);
		panel_1.setPreferredSize(new Dimension(0, 80));
		panel.add(panel_1, BorderLayout.NORTH);

		JLabel label_borrow = new JLabel("外借：",JLabel.CENTER);
		panel_1.add(label_borrow);
		JLabel label_overdue = new JLabel("逾期：",JLabel.CENTER);
		panel_1.add(label_overdue);
		JLabel label_histroy = new JLabel("借阅历史",JLabel.CENTER);
		panel_1.add(label_histroy);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(0, 100));
		panel.add(scrollPane, BorderLayout.CENTER);

		
		DefaultTableModel model=new DefaultTableModel();
		Object[][] results=getFileStates(Dao.selectBorrow(operator.getId()));
		model.setDataVector(results,columnNames);
		
		table = new JTable();
		table.setModel(model);
		//设置列宽
		TableColumn column;
		for(int i=0;i<table.getColumnCount();i++) {
			column = table.getColumnModel().getColumn(i);
			column.setPreferredWidth(120);
		}
		table.setRowHeight(27);
		scrollPane.setViewportView(table);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		label_borrow.setText("外借："+num_borrow+"本");
		label_overdue.setText("逾期："+num_overdue+"本");
		label_overdue.setForeground(Color.red);
		setVisible(true);
	}
	
	private Object[][] getFileStates(List list){
		Object[][]results=new Object[list.size()][columnNames.length];
		for(int i=0;i<list.size();i++){
			Borrow borrow=(Borrow)list.get(i);
			results[i][0]=borrow.getBookId();			
			results[i][1]=borrow.getName();
			results[i][2]=borrow.getStart();
			results[i][3]=borrow.getEnd();
			results[i][4]=borrow.getStatus();
			if(!results[i][4].equals("已归还")) {
				num_borrow++;
			}
			else {
				if(results[i][4].equals("逾期")) {
					num_overdue++;
				}
			}
		}
		return results;	         		
	}
}
