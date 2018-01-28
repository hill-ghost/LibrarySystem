package com.library.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.library.common.MapPz;
import com.library.dao.Dao;
import com.library.model.BookInfo;

import java.awt.*;

public class BookSearchGUI extends JInternalFrame {

	private JTextField textField_1;
	private JTable table_1, table_2;
	private JComboBox choice;
	private JScrollPane scrollPane_1, scrollPane_2;
	private Map m=MapPz.getMap();
	String booksearch[] = { "名称","编号", "分类", "作者", "译者","出版社",  "出版日期", "单价" ,"总量","可借数"};

	/**
	 * 图书搜索窗口
	 */
	public BookSearchGUI() {
		super();
		setIconifiable(true);
		setClosable(true);
		setTitle("图书详细查询");
		setBounds(100, 100, 800, 550);

		
		setVisible(true);

		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setPreferredSize(new Dimension(0, 50));
		getContentPane().add(tabbedPane);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(new BorderLayout());
		tabbedPane.addTab("条件查询", null, panel_1, null);

		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBorder(new TitledBorder(null, "请选择查询项目", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		panel_1_1.setPreferredSize(new Dimension(0, 70));
		panel_1.add(panel_1_1, BorderLayout.NORTH);
        choice=new JComboBox();
		String[] array={"图书名称","图书作者","图书类别"};
		for(int i=0;i<array.length;i++){
			choice.addItem(array[i]);
			
		}
		panel_1_1.add(choice);
		textField_1 = new JTextField();
		textField_1.setColumns(20);
		panel_1_1.add(textField_1);
        
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "查询结果显示", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		panel_1.add(panel);
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setPreferredSize(new Dimension(780, 300));
		panel.add(scrollPane_2);

		JPanel panel_2_1 = new JPanel();
		panel_2_1.setPreferredSize(new Dimension(0, 50));
		panel_1.add(panel_2_1, BorderLayout.SOUTH);

		final JButton button = new JButton();
		button.setText("查询");
		panel_2_1.add(button);

		button.addActionListener(new ActionListener(){			
				public void actionPerformed(ActionEvent arg0) {
					String name=(String)choice.getSelectedItem();
					if(name.equals("图书名称")){						
						Object[][] results2=getselect(Dao.selectbookmohu(textField_1.getText()));
						table_2 = new JTable(results2,booksearch);
						//设置列宽
						TableColumn column2;
						for(int i=0;i<table_2.getColumnCount();i++) {
							column2 = table_2.getColumnModel().getColumn(i);
							column2.setPreferredWidth(120);
						}
						table_2.setRowHeight(27);
						table_2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
						scrollPane_2.setViewportView(table_2);
					}
					else if(name.equals("图书作者")){
						
						Object[][] results2=getselect(Dao.selectbookmohuwriter(textField_1.getText()));
						table_2 = new JTable(results2,booksearch);
						//设置列宽
						TableColumn column2;
						for(int i=0;i<table_2.getColumnCount();i++) {
							column2 = table_2.getColumnModel().getColumn(i);
							column2.setPreferredWidth(120);
						}
						table_2.setRowHeight(27);
						table_2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
						scrollPane_2.setViewportView(table_2);
					}
					else if(name.equals("图书类别")) {
						Object[][] results2=getselect(Dao.selectbookmohutype(textField_1.getText()));
						table_2 = new JTable(results2,booksearch);
						//设置列宽
						TableColumn column2;
						for(int i=0;i<table_2.getColumnCount();i++) {
							column2 = table_2.getColumnModel().getColumn(i);
							column2.setPreferredWidth(120);
						}
						table_2.setRowHeight(27);
						table_2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
						scrollPane_2.setViewportView(table_2);						
					}
				}
	        	
	        	
	        });
		
		
		JButton button_1 = new JButton();
		button_1.setText("退出");
		panel_2_1.add(button_1);
		button_1.addActionListener(new CloseActionListener());
		
		setVisible(true);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("显示全部图书", null, panel_2, null);
         
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setPreferredSize(new Dimension(780, 440));
		panel_2.add(scrollPane_1);
		
		Object[][] results=getselect(Dao.selectbooksearch());
		table_1 = new JTable(results,booksearch);
		//设置列宽
		TableColumn column;
		for(int i=0;i<table_1.getColumnCount();i++) {
			column = table_1.getColumnModel().getColumn(i);
			column.setPreferredWidth(120);
		}

		table_1.setRowHeight(27);
		scrollPane_1.setViewportView(table_1);
		table_1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane_1.setViewportView(table_1);		
	}
	
	
	private Object[][] getselect(List list) {
		Object[][] s = new Object[list.size()][10];
		for (int i = 0; i < list.size(); i++) {
			BookInfo book = (BookInfo) list.get(i);
			s[i][0] = book.getName();
			s[i][1] = book.getId();
			String booktypename=String.valueOf(MapPz.getMap().get(book.getTypeId()));
			s[i][2] = booktypename;
			s[i][3] = book.getWriter();
			s[i][4] = book.getTranslator();
			s[i][5] = book.getPublisher();
			s[i][6] = book.getDate();
			s[i][7] = book.getPrice();
			s[i][8] = book.getNum();
			s[i][9] = book.getRest();
		}
		return s;
	}
	
	class CloseActionListener implements ActionListener {			// 添加关闭按钮的事件监听器
		public void actionPerformed(final ActionEvent e) {
			doDefaultCloseAction();
		}
	}
}
