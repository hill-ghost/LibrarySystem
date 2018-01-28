package com.library.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import com.library.dao.Dao;
import com.library.model.BookType;
import com.library.util.CreatecdIcon;

public class BookTypeModifyGUI extends JInternalFrame {

	private JTextField days,fk;
	private JTextField BookTypeId;
	private JTextField BookTypeName;
	private JTable table;
	private String[] columnNames={ "图书类别编号", "图书类别名称", "可借天数","罚款"};
	DefaultTableModel model;
		
	public BookTypeModifyGUI() {
		super();
		setTitle("图书类别修改");
		setBounds(100, 100, 500, 500);
		setIconifiable(true);
		setClosable(true);
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		getContentPane().add(panel, BorderLayout.NORTH);

		JLabel logoLabel = new JLabel();
		
		ImageIcon bookTypeModiAndDelIcon=CreatecdIcon.add("bookTypemodianddel.png");
		logoLabel.setIcon(bookTypeModiAndDelIcon);
		
		logoLabel.setPreferredSize(new Dimension(480, 80));
		logoLabel.setText("logo");
		panel.add(logoLabel);

		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(480, 250));
		panel_1.add(scrollPane);

		model=new DefaultTableModel();
		Object[][] results=getFileStates(Dao.selectBookCategory());
		model.setDataVector(results,columnNames);
		table = new JTable();
		table.setModel(model);
		table.setRowHeight(27);
		table.addMouseListener(new TableListener());
		scrollPane.setViewportView(table);
		

		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		GridLayout gridLayout = new GridLayout(0, 4);
		gridLayout.setVgap(5);
		gridLayout.setHgap(5);
		panel_2.setLayout(gridLayout);
		panel_2.setPreferredSize(new Dimension(480, 60));

		JLabel label = new JLabel();
		label.setText("类别编号：");
		panel_2.add(label);

		BookTypeId = new JTextField();
		BookTypeId.setFocusable(false);
		panel_2.add(BookTypeId);

		JLabel label_1 = new JLabel();
		label_1.setText("类别名称：");
		panel_2.add(label_1);
		
		BookTypeName = new JTextField();
		panel_2.add(BookTypeName);
		
		//从数据库中取出图书类别
		List list=Dao.selectBookCategory();

		JLabel label_2 = new JLabel();
		label_2.setText("可借天数：");
		panel_2.add(label_2);

		days = new JTextField();
		panel_2.add(days);
		
		final JLabel label_3 = new JLabel();
		label_3.setText("罚款：");
		panel_2.add(label_3);
		
		fk = new JTextField();
		panel_2.add(fk);
		
		final JPanel panel_4 = new JPanel();
		panel_1.add(panel_4);

		final JButton buttonMod = new JButton();
		buttonMod.setText("修改");
		buttonMod.addActionListener(new ButtonAddListener());
		panel_4.add(buttonMod);

		final JButton buttonExit = new JButton();
		buttonExit.setText("退出");
		buttonExit.addActionListener(new CloseActionListener());
		panel_4.add(buttonExit);
		
		setVisible(true);	
	}
	
	private Object[][] getFileStates(List list){
		Object[][]results=new Object[list.size()][columnNames.length];
		for(int i=0;i<list.size();i++){
			BookType booktype=(BookType)list.get(i);
			results[i][0]=booktype.getId();
			results[i][1]=booktype.getName();
			results[i][2]=booktype.getDays();
            results[i][3]=booktype.getFk();
		}
		return results;
	         		
	}
	
	class TableListener extends MouseAdapter {
		public void mouseClicked(final MouseEvent e) {			
			int selRow = table.getSelectedRow();
			BookTypeId.setText(table.getValueAt(selRow, 0).toString().trim());
			BookTypeName.setText(table.getValueAt(selRow, 1).toString().trim());
			days.setText(table.getValueAt(selRow, 2).toString().trim());
			fk.setText(table.getValueAt(selRow, 3).toString().trim());			
		}
	}
	
	class ButtonAddListener implements ActionListener{
		public void actionPerformed(ActionEvent e){		
			int i=Dao.UpdatebookType(BookTypeId.getText().trim(),BookTypeName.getText().trim(), days.getText().trim(),fk.getText().trim());
			System.out.println(i);
			if(i==1){
				JOptionPane.showMessageDialog(null, "修改成功");
				Object[][] results=getFileStates(Dao.selectBookCategory());
				model.setDataVector(results,columnNames);
				table.setModel(model);
				
			}
		}
	}
	
	/*class ButtonDelListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			int i=Dao.DelbookType(BookTypeId.getText().trim());
			if(i==1){
				JOptionPane.showMessageDialog(null, "删除成功");
				Object[][] results=getFileStates(Dao.selectBookCategory());
				model.setDataVector(results,columnNames);
				table.setModel(model);
			}
		}
	}*/
	
	class CloseActionListener implements ActionListener {
		public void actionPerformed(final ActionEvent e) {
			doDefaultCloseAction();
		}
	}
}
