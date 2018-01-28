package com.library.ui;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

import com.library.Library;
import com.library.common.MapPz;
import com.library.dao.Dao;
import com.library.model.BookInfo;
import com.library.model.BookType;
import com.library.model.Borrow;
import com.library.model.Operator;
import com.library.model.Reader;
import com.library.util.MyDocument;

public class BookBorrowGUI extends JInternalFrame {
	
	/*
	 * 书籍借阅窗口
	 */
	private Operator user = Library.getUser(); 	
	private JTextField todaydate;
	private JTextField rest;
	private JTextField bookType;
	private JTextField bookName;
	private JTextField bookId;
	private JTextField keepMoney;
	private JTextField number;
	private JTextField readerName;
	private JTextField readerId;
	private JTextField operator;
	private JTextField readerOrder;
	private JTextField bookOrder;
	private Map map = MapPz.getMap();
	SimpleDateFormat myfmt=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

	public BookBorrowGUI() {
		super();
		setTitle("图书借阅与预约管理");
		setIconifiable(true); // 设置窗体可最小化－－－必须
		setClosable(true); // 设置窗体可关闭－－－必须
		setBounds(100, 100, 500, 460);

		JPanel panel = new JPanel();
		getContentPane().add(panel);

		JPanel panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(0, 245));
		getContentPane().add(panel_1, BorderLayout.NORTH);

		JSplitPane splitPane = new JSplitPane();
		panel_1.add(splitPane);

		JPanel panel_3 = new JPanel();
		panel_3.setPreferredSize(new Dimension(240, 240));
		splitPane.setLeftComponent(panel_3);

		GridLayout gridLayout = new GridLayout(0, 2);
		gridLayout.setVgap(10);
		panel_3.setLayout(gridLayout);

		JLabel label = new JLabel();
		label.setText("读者编号：");
		panel_3.add(label);

		readerId = new JTextField();
		readerId.addKeyListener(new readerListenerlostFocus());
		panel_3.add(readerId);

		JLabel label_1 = new JLabel();
		label_1.setText("读者姓名：");
		panel_3.add(label_1);

		readerName = new JTextField();
		readerName.setEditable(false);
		panel_3.add(readerName);

		JLabel label_2 = new JLabel();
		label_2.setText("可借数量：");
		panel_3.add(label_2);

		number = new JTextField();
		number.setEditable(false);
		panel_3.add(number);

		JLabel label_4 = new JLabel();
		label_4.setText("押    金：");
		panel_3.add(label_4);

		keepMoney = new JTextField();
		keepMoney.setEditable(false);
		panel_3.add(keepMoney);
		
		JLabel label_5 = new JLabel();
		label_5.setText("已预约数：");
		panel_3.add(label_5);
		
		readerOrder = new JTextField();
		readerOrder.setEditable(false);
		panel_3.add(readerOrder);

		JPanel panel_4 = new JPanel();
		GridLayout gridLayout_1 = new GridLayout(0, 2);
		gridLayout_1.setVgap(10);
		panel_4.setLayout(gridLayout_1);
		panel_4.setPreferredSize(new Dimension(240, 240));
		splitPane.setRightComponent(panel_4);

		JLabel label_9 = new JLabel();
		label_9.setText("书籍编号：");
		panel_4.add(label_9);

		bookId = new JTextField();
		bookId.addKeyListener(new bookListenerlostFocus());
		panel_4.add(bookId);

		JLabel label_6 = new JLabel();
		label_6.setText("书籍名称：");
		panel_4.add(label_6);

		bookName = new JTextField();
		bookName.setEditable(false);
		panel_4.add(bookName);

		JLabel label_7 = new JLabel();
		label_7.setText("书籍类别：");
		panel_4.add(label_7);

		bookType = new JTextField();
		bookType.setEditable(false);
		panel_4.add(bookType);

		JLabel label_8 = new JLabel();
		label_8.setText("书籍存量：");
		panel_4.add(label_8);

		rest = new JTextField();
		rest.setEditable(false);
		panel_4.add(rest);
		
		JLabel label_14 = new JLabel();
		label_14.setText("已被预约数:");
		panel_4.add(label_14);
		
		bookOrder = new JTextField();
		bookOrder.setEditable(false);
		panel_4.add(bookOrder);

		JPanel panel_2 = new JPanel();
		panel_2.setPreferredSize(new Dimension(0, 200));
		getContentPane().add(panel_2, BorderLayout.CENTER);

		JPanel panel_7 = new JPanel();
		GridLayout gridLayout_2 = new GridLayout(0, 2);
		gridLayout_2.setVgap(10);
		panel_7.setLayout(gridLayout_2);
		panel_7.setPreferredSize(new Dimension(400, 80));
		panel_2.add(panel_7);

		JLabel label_15 = new JLabel();
		label_15.setText("当前时间：");
		panel_7.add(label_15);

		todaydate = new JTextField();
		todaydate.setEditable(false);
		todaydate.setPreferredSize(new Dimension(0, 0));
		todaydate.addActionListener(new TimeActionListener());
		todaydate.setFocusable(false);
		panel_7.add(todaydate);

		JLabel label_11 = new JLabel();
		label_11.setText("操作员：");
		panel_7.add(label_11);

		operator = new JTextField(user.getName());
		operator.setEditable(false);
		panel_7.add(operator);

		JPanel panel_8 = new JPanel();
		panel_8.setLayout(new FlowLayout());
		panel_8.setPreferredSize(new Dimension(200, 80));
		panel_2.add(panel_8);
		
		JButton buttonOrder = new JButton();
		buttonOrder.setText("预约当前图书");
		buttonOrder.addActionListener(new OrderActionListener());
		panel_8.add(buttonOrder);

		JButton buttonBorrow = new JButton();
		buttonBorrow.setText("借出当前图书");
		buttonBorrow.addActionListener(new BorrowActionListener());
		panel_8.add(buttonBorrow);
		
		
		List list = Dao.selectOrderred();
		for(int i = 0; i < list.size(); i++) {
			Borrow borrow = (Borrow)list.get(i);
			int day = (int) ((new Date().getTime() - borrow.getStart().getTime())/1000/60/60/24);
			if(day > 7) {
				Dao.deleteOrder(borrow.getReaderId(), borrow.getBookId());
			}
		}

		setVisible(true);
	}
	
	//计算还书时间
	public Date getBackTime() {	
		String days = "0";
		List list = Dao.selectBookCategory(bookType.getText().trim());
		for (int j = 0; j < list.size(); j++) {
			BookType type = (BookType) list.get(j);
			days = type.getDays();
		}
		java.util.Date date = new java.util.Date();
		date.setDate(date.getDate() + Integer.parseInt(days));
		return date;
	}
	
	//监听书籍编号输入框
	class bookListenerlostFocus extends KeyAdapter {
		public void keyTyped(KeyEvent e) {
			if(e.getKeyChar() == '\n') { 
				if(bookId.getText().trim().length()!=0) {
					String id = bookId.getText().trim();
					BookInfo book = Dao.selectBookInfo(id);
					if(book == null) {
						JOptionPane.showMessageDialog(null,"本图书馆没有此书，查询输入图书编号是否有误！");
						return;
					}
					bookName.setText(book.getName());
					bookType.setText(String.valueOf(map.get(book.getTypeId())));
					rest.setText(book.getRest());
					bookOrder.setText(Dao.selectOrderred(id).size() + "");
				}
			}

		}
	}

	//监听读者编号输入框
	class readerListenerlostFocus extends KeyAdapter {
		public void keyTyped(KeyEvent e) {
			if (e.getKeyChar() == '\n') {
				String id = readerId.getText().trim();
				Reader reader = Dao.selectReader(id);
				if (reader == null) {
					JOptionPane.showMessageDialog(null,
							"此读者编号没有注册，查询输入读者编号是否有误！");
				}
				readerName.setText(reader.getName());
				number.setText(reader.getMax());
				keepMoney.setText(reader.getMoney() + "");
				readerOrder.setText(Dao.selectOrder(id).size() + "");
			}
		}
	}

	//点击借出按钮触发事件
	class BorrowActionListener implements ActionListener { 
		public void actionPerformed(final ActionEvent e) {
			if(Dao.selectBorrow(readerId.getText().trim(), bookId.getText().trim()).size()>0) {
				JOptionPane.showMessageDialog(null, "该读者已借阅此书。");
				return;
			}
			if(bookName.getText().equals("")||readerName.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "读者信息和书籍信息获取错误。");
				return;
			}
			if(Integer.parseInt(rest.getText())<=0) {
				if(!((Dao.selectOrderred(bookId.getText()).size()>0)&&(Dao.selectOrder(readerId.getText()).size()>0))){
					JOptionPane.showMessageDialog(null, "此书没有库存。");
					return;
				}
				else {
					if(Integer.parseInt(rest.getText())<0) {
						JOptionPane.showMessageDialog(null, "此书仍未归还。");
						return;
					}
				}
			}
			if(number.getText().equals("0")) {
				JOptionPane.showMessageDialog(null, "此人借书数量已达上限。");
				return;
			}
			String bookIds=bookId.getText().trim();
			String readerIds=readerId.getText().trim();
			String bookNames=bookName.getText().trim();
			String operatorName=user.getName();
			String borrowDate=myfmt.format(new java.util.Date());
			String backDate=myfmt.format(getBackTime());
			String status = "等待归还";
			int i;
			if(!((Dao.selectOrderred(bookId.getText()).size()>0)&&(Dao.selectOrder(readerId.getText()).size()>0))){
				i=Dao.InsertBookBorrow(bookIds, readerIds, java.sql.Timestamp.valueOf(borrowDate), java.sql.Timestamp.valueOf(backDate),operatorName,status);
				Dao.borrowBook(bookIds, Integer.parseInt(rest.getText())-1+"", readerIds, Integer.parseInt(number.getText())-1+"");
			}
			else {
				i=Dao.renewOrder(readerIds,bookIds,java.sql.Timestamp.valueOf(borrowDate).toString());
				Dao.borrowBook(bookIds, Integer.parseInt(rest.getText())+"", readerIds, Integer.parseInt(number.getText())-1+"");
			}
			
			if(i==1){
				JOptionPane.showMessageDialog(null, "图书借阅完成！");
				doDefaultCloseAction();
			}
		}
	}
	
	//点击预约按钮触发事件
	class OrderActionListener implements ActionListener { 
		public void actionPerformed(final ActionEvent e) {
			if(Dao.selectBorrow(readerId.getText().trim(), bookId.getText().trim()).size()>0) {
				JOptionPane.showMessageDialog(null, "该读者已借阅此书。");
				return;
			}
			if(bookName.getText().equals("")||readerName.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "读者信息和书籍信息获取错误。");
				return;
			}
			if(Integer.parseInt(rest.getText())>0) {
				JOptionPane.showMessageDialog(null, "此书有库存不需要预约。");
				return;
			}
			if(Integer.parseInt(rest.getText())<0) {
				JOptionPane.showMessageDialog(null, "此书已被预约。");
				return;
			}
			if(number.getText().equals("0")) {
				JOptionPane.showMessageDialog(null, "此人借书数量已达上限。");
				return;
			}
			String bookIds=bookId.getText().trim();
			String readerIds=readerId.getText().trim();
			String bookNames=bookName.getText().trim();
			String operatorName=user.getName();
			String borrowDate=myfmt.format(new java.util.Date());
			String status = "预约中";
			int i=Dao.InsertBookBorrow(bookIds, readerIds, java.sql.Timestamp.valueOf(borrowDate), null,operatorName,status);
			Dao.borrowBook(bookIds, Integer.parseInt(rest.getText())-1+"", readerIds, Integer.parseInt(number.getText())-1+"");
			if(i==1){
				JOptionPane.showMessageDialog(null, "图书预约完成！");
				doDefaultCloseAction();
			}
		}
	}
	
	//显示当前时间
	class TimeActionListener implements ActionListener{
		public TimeActionListener(){
			Timer t=new Timer(1000,this);
			t.start();
		}
		public void actionPerformed(ActionEvent ae){
			todaydate.setText(myfmt.format(new java.util.Date()).toString());
		}
	}
}
