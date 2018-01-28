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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.library.Library;
import com.library.common.MapPz;
import com.library.dao.Dao;
import com.library.model.Back;
import com.library.model.BookInfo;
import com.library.model.BookType;
import com.library.model.Borrow;
import com.library.model.Operator;
import com.library.model.Reader;
import com.library.ui.BookModifyGUI.TableListener;
import com.library.util.MyDocument;

/**
 * 归还图书的窗口
 */
public class BookBackGUI extends JInternalFrame {
	private Operator user = Library.getUser(); 
	private JTable table;
	private JTextField operator;
	private JTextField todaydate;
	private JTextField fkmoney;
	private JTextField ccdays;
	private JTextField realdays;
	private JTextField borrowdays;
	private JTextField borrowDate;
	private JTextField readerId;
	private JScrollPane scrollPane;
	private String[] columnNames = { "图书名称", "图书编码","图书类型","读者姓名","读者编码","借书时间","应还时间","借阅状态" };
	DefaultTableModel model = new DefaultTableModel();
	private SimpleDateFormat myfmt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	private SimpleDateFormat myfmt2 = new SimpleDateFormat("yyyy-MM-dd");
	private String bookIds=null;
	private String readerIds=null;
	private String operatorName = Library.getUser().getName();
	
	public BookBackGUI() {
		super();
		setIconifiable(true);							// 设置窗体可最小化－－－必须
		setClosable(true);								// 设置窗体可关闭－－－必须
		setTitle("图书归还与续借管理");
		setBounds(100, 100, 800, 550);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "基本信息", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		panel.setPreferredSize(new Dimension(0, 250));
		getContentPane().add(panel, BorderLayout.NORTH);

		JPanel panel_5 = new JPanel();
		GridLayout gridLayout_1 = new GridLayout(0, 2);
		gridLayout_1.setVgap(5);
		panel_5.setLayout(gridLayout_1);
		panel_5.setPreferredSize(new Dimension(200, 30));
		panel.add(panel_5);

		JLabel label_4 = new JLabel();
		label_4.setText("读者编号：");
		panel_5.add(label_4);

		readerId = new JTextField();
		readerId.setDocument(new MyDocument(13));
		readerId.addKeyListener(new readerIdListenerlostFocus());
		panel_5.add(readerId);

		JPanel panel_4 = new JPanel();
		panel_4.setLayout(new FlowLayout());
		panel_4.setPreferredSize(new Dimension(780, 230));
		panel.add(panel_4);

		scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(780, 180));
		panel_4.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		model.setColumnIdentifiers(columnNames);
		table.setModel(model);
		TableColumn column2;
		for(int i=0;i<table.getColumnCount();i++) {
			column2 = table.getColumnModel().getColumn(i);
			column2.setPreferredWidth(120);
		}
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setRowHeight(27);
		table.addMouseListener(new TableListener());
		

		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1);

		JPanel panel_2 = new JPanel();
		GridLayout gridLayout_2 = new GridLayout(0, 2);
		gridLayout_2.setVgap(20);
		panel_2.setLayout(gridLayout_2);
		panel_2.setBorder(new TitledBorder(null, "罚款信息", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		panel_2.setPreferredSize(new Dimension(300, 230));
		panel_1.add(panel_2);

		JLabel label_11 = new JLabel();
		label_11.setText("借书日期：");
		panel_2.add(label_11);

		borrowDate = new JTextField();
		borrowDate.setEditable(false);

		panel_2.add(borrowDate);

		JLabel label_12 = new JLabel();
		label_12.setText("规定天数：");
		panel_2.add(label_12);

		borrowdays = new JTextField();
		borrowdays.setEditable(false);
		panel_2.add(borrowdays);

		JLabel label_13 = new JLabel();
		label_13.setText("实际天数：");
		panel_2.add(label_13);

		realdays = new JTextField();
		realdays.setEditable(false);
		panel_2.add(realdays);

		JLabel label_14 = new JLabel();
		label_14.setText("超出天数：");
		panel_2.add(label_14);

		ccdays = new JTextField();
		ccdays.setEditable(false);
		panel_2.add(ccdays);

		JLabel label_15 = new JLabel();
		label_15.setText("罚款金额：");
		panel_2.add(label_15);

		fkmoney = new JTextField();
		fkmoney.setEditable(false);
		panel_2.add(fkmoney);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "系统信息", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		panel_3.setPreferredSize(new Dimension(410, 230));
		panel_1.add(panel_3);

		JPanel panel_7 = new JPanel();
		GridLayout gridLayout_3 = new GridLayout(0, 2);
		gridLayout_3.setVgap(35);
		panel_7.setLayout(gridLayout_3);
		panel_7.setPreferredSize(new Dimension(380, 120));
		panel_3.add(panel_7);

		JLabel label_10_1 = new JLabel();
		label_10_1.setText("当前时间：");
		panel_7.add(label_10_1);

		todaydate = new JTextField();
		todaydate.setEditable(false);
		todaydate.setPreferredSize(new Dimension(0, 30));
		todaydate.addActionListener(new TimeActionListener());
		todaydate.setFocusable(false);
		panel_7.add(todaydate);

		JLabel label_11_1 = new JLabel();
		label_11_1.setText("操作员：");
		panel_7.add(label_11_1);

		operator  =new JTextField(user.getName());
		operator.setPreferredSize(new Dimension(0, 30));
		operator.setEditable(false);
		panel_7.add(operator);

		JButton buttonback = new JButton();
		buttonback.setText("图书归还");
		buttonback.addActionListener(new BookBackActionListener(model));
		panel_3.add(buttonback);
		
		JButton buttonRenew = new JButton();
		buttonRenew.setText("图书续借");
		buttonRenew.addActionListener(new BookRenewActionListener(model));
		panel_3.add(buttonRenew);

		JButton buttonExit= new JButton();
		buttonExit.setText("退出");
		buttonExit.addActionListener(new CloseActionListener());
		panel_3.add(buttonExit);
		setVisible(true);
		//
	}
	
	private Object[][] getselect(List list) {
		Object[][] str = new Object[list.size()][10];
		for (int i = 0; i < list.size(); i++) {
			Back back=(Back)list.get(i);
			str[i][0] = back.getBookName();
			str[i][1] = back.getBookId();
			str[i][2] = back.getBookType();
			str[i][3] = back.getReaderName();
			str[i][4] = back.getReaderId();
			str[i][5] = back.getBorrowDate();
			str[i][6] = back.getBackDate();
			str[i][7] = back.getStatus();
		}
		return str;
	}
	
	//监听读者编号输入框
	class readerIdListenerlostFocus extends KeyAdapter{
		public void keyTyped(KeyEvent e) {
			if (e.getKeyChar() == '\n') {	
				readerIds=readerId.getText().trim();
				Object[][] results=getselect(Dao.selectBookBack(readerIds));
				table = new JTable(results,columnNames);
				//设置列宽
				TableColumn column;
				for(int i=0;i<table.getColumnCount();i++) {
					column = table.getColumnModel().getColumn(i);
					column.setPreferredWidth(120);
				}
				table.setRowHeight(27);
				table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				table.addMouseListener(new TableListener());
				scrollPane.setViewportView(table);
			}
		}
	}
	
	//监听时间变化
	class TimeActionListener implements ActionListener{
		public TimeActionListener(){
			Timer t=new Timer(1000,this);
			t.start();
		}
		public void actionPerformed(ActionEvent ae){
			todaydate.setText(myfmt.format(new java.util.Date()).toString());
		}
	}
	
	
	class TableListener extends MouseAdapter {
		public void mouseClicked(final MouseEvent e) {			
			java.util.Date date = new java.util.Date();
			String fk="";
			long days1;
			int selRow=table.getSelectedRow();
			List list =Dao.selectBookTypeFk(table.getValueAt(selRow, 2).toString().trim());
			for(int i=0;i<list.size();i++){
				BookType booktype=(BookType)list.get(i);
				fk=booktype.getFk();
			}
			borrowDate.setText(table.getValueAt(selRow, 5).toString().trim());
			bookIds = table.getValueAt(selRow, 1).toString().trim();
			long days2;
			int days3;
			days1=(java.sql.Timestamp.valueOf(table.getValueAt(selRow, 6).toString().trim()+" 00:00:00.00").getTime()-java.sql.Timestamp.valueOf(table.getValueAt(selRow, 5).toString().trim()+" 00:00:00.00").getTime())/1000/60/60/24;
			borrowdays.setText(days1+"");
			days2=((new java.sql.Timestamp(date.getTime())).getTime()-java.sql.Timestamp.valueOf(table.getValueAt(selRow, 5).toString().trim()+" 00:00:00.00").getTime())/1000/60/60/24;
			realdays.setText(days2+"");
			days3=(int) (days2-days1);
			if(days3>0){
				ccdays.setText(days3+"");
				Double zfk=Double.valueOf(fk)*days3;
				fkmoney.setText(zfk+"元");
			}
			else{
				ccdays.setText("无逾期");
				fkmoney.setText("0");
			}
		}
	}
	
	//监听归还按钮
	class BookBackActionListener implements ActionListener{
		private final DefaultTableModel model;
		BookBackActionListener(DefaultTableModel model) {
			this.model = model;
		}
		public void actionPerformed(ActionEvent e) {
			if(readerIds==null){
				JOptionPane.showMessageDialog(null, "请输入读者编号！");
				return;
			}
			if(table.getSelectedRow()==-1){
				JOptionPane.showMessageDialog(null, "请选择所要归还的图书！");
				return;	
			}
			int i=Dao.updateBookBack(bookIds, readerIds, operatorName);
			BookInfo book = Dao.selectBookInfo(bookIds);
			Reader reader = Dao.selectReader(readerIds);
			Dao.borrowBook(bookIds,Integer.parseInt(book.getRest())+1+"", readerIds, Integer.parseInt(reader.getMax())+1+"");
			if(i!=-1){			
				readerIds=readerId.getText().trim();
				Object[][] results=getselect(Dao.selectBookBack(readerIds));
				table = new JTable(results,columnNames);
				//设置列宽
				TableColumn column;
				for(int j = 0;j<table.getColumnCount();j++) {
					column = table.getColumnModel().getColumn(j);
					column.setPreferredWidth(120);
				}
				table.setRowHeight(27);
				table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				table.addMouseListener(new TableListener());
				scrollPane.setViewportView(table);
				JOptionPane.showMessageDialog(null, "还书操作完成！");		
			}
		}
	}
	
	//续借按钮
	class BookRenewActionListener implements ActionListener{
		private final DefaultTableModel model;
		BookRenewActionListener(DefaultTableModel model) {
			this.model = model;
		}
		public void actionPerformed(ActionEvent e) {
			if(2*Integer.parseInt(borrowdays.getText().trim())<=Integer.parseInt(realdays.getText().trim())) {
				JOptionPane.showMessageDialog(null, "逾期时间过长不可续借！");
				return;
			}
			if(readerIds==null){
				JOptionPane.showMessageDialog(null, "请输入读者编号！");
				return;
			}
			if(table.getSelectedRow()==-1){
				JOptionPane.showMessageDialog(null, "请选择所要续借的图书！");
				return;	
			}
			String days1="";
			int selRow=table.getSelectedRow();
			List list =Dao.selectBookTypeFk(table.getValueAt(selRow, 2).toString().trim());
			for(int i=0;i<list.size();i++){
				BookType booktype=(BookType)list.get(i);
				days1=booktype.getDays();
			}
			bookIds = table.getValueAt(selRow, 1).toString().trim();
			java.util.Date days = null;
			try {
				days = new SimpleDateFormat("yyyy-MM-dd").parse(table.getValueAt(selRow, 6).toString().trim());
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			days.setDate(days.getDate() + Integer.parseInt(days1));
			int i=Dao.renewBookBorrow(bookIds, readerIds, myfmt.format(days));
			BookInfo book = Dao.selectBookInfo(bookIds);
			Reader reader = Dao.selectReader(readerIds);
			if(i==1){			
				readerIds=readerId.getText().trim();
				Object[][] results=getselect(Dao.selectBookBack(readerIds));
				table = new JTable(results,columnNames);
				//设置列宽
				TableColumn column;
				for(int j = 0;j<table.getColumnCount();j++) {
					column = table.getColumnModel().getColumn(j);
					column.setPreferredWidth(120);
				}
				table.setRowHeight(27);
				table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				table.addMouseListener(new TableListener());
				scrollPane.setViewportView(table);
				JOptionPane.showMessageDialog(null, "续借操作完成！");		
			}
		}
	}
	
	class CloseActionListener implements ActionListener {			// 添加关闭按钮的事件监听器
		public void actionPerformed(final ActionEvent e) {
			doDefaultCloseAction();
		}
	}
	
}
