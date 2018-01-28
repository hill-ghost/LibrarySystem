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
	 * �鼮���Ĵ���
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
		setTitle("ͼ�������ԤԼ����");
		setIconifiable(true); // ���ô������С������������
		setClosable(true); // ���ô���ɹرգ���������
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
		label.setText("���߱�ţ�");
		panel_3.add(label);

		readerId = new JTextField();
		readerId.addKeyListener(new readerListenerlostFocus());
		panel_3.add(readerId);

		JLabel label_1 = new JLabel();
		label_1.setText("����������");
		panel_3.add(label_1);

		readerName = new JTextField();
		readerName.setEditable(false);
		panel_3.add(readerName);

		JLabel label_2 = new JLabel();
		label_2.setText("�ɽ�������");
		panel_3.add(label_2);

		number = new JTextField();
		number.setEditable(false);
		panel_3.add(number);

		JLabel label_4 = new JLabel();
		label_4.setText("Ѻ    ��");
		panel_3.add(label_4);

		keepMoney = new JTextField();
		keepMoney.setEditable(false);
		panel_3.add(keepMoney);
		
		JLabel label_5 = new JLabel();
		label_5.setText("��ԤԼ����");
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
		label_9.setText("�鼮��ţ�");
		panel_4.add(label_9);

		bookId = new JTextField();
		bookId.addKeyListener(new bookListenerlostFocus());
		panel_4.add(bookId);

		JLabel label_6 = new JLabel();
		label_6.setText("�鼮���ƣ�");
		panel_4.add(label_6);

		bookName = new JTextField();
		bookName.setEditable(false);
		panel_4.add(bookName);

		JLabel label_7 = new JLabel();
		label_7.setText("�鼮���");
		panel_4.add(label_7);

		bookType = new JTextField();
		bookType.setEditable(false);
		panel_4.add(bookType);

		JLabel label_8 = new JLabel();
		label_8.setText("�鼮������");
		panel_4.add(label_8);

		rest = new JTextField();
		rest.setEditable(false);
		panel_4.add(rest);
		
		JLabel label_14 = new JLabel();
		label_14.setText("�ѱ�ԤԼ��:");
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
		label_15.setText("��ǰʱ�䣺");
		panel_7.add(label_15);

		todaydate = new JTextField();
		todaydate.setEditable(false);
		todaydate.setPreferredSize(new Dimension(0, 0));
		todaydate.addActionListener(new TimeActionListener());
		todaydate.setFocusable(false);
		panel_7.add(todaydate);

		JLabel label_11 = new JLabel();
		label_11.setText("����Ա��");
		panel_7.add(label_11);

		operator = new JTextField(user.getName());
		operator.setEditable(false);
		panel_7.add(operator);

		JPanel panel_8 = new JPanel();
		panel_8.setLayout(new FlowLayout());
		panel_8.setPreferredSize(new Dimension(200, 80));
		panel_2.add(panel_8);
		
		JButton buttonOrder = new JButton();
		buttonOrder.setText("ԤԼ��ǰͼ��");
		buttonOrder.addActionListener(new OrderActionListener());
		panel_8.add(buttonOrder);

		JButton buttonBorrow = new JButton();
		buttonBorrow.setText("�����ǰͼ��");
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
	
	//���㻹��ʱ��
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
	
	//�����鼮��������
	class bookListenerlostFocus extends KeyAdapter {
		public void keyTyped(KeyEvent e) {
			if(e.getKeyChar() == '\n') { 
				if(bookId.getText().trim().length()!=0) {
					String id = bookId.getText().trim();
					BookInfo book = Dao.selectBookInfo(id);
					if(book == null) {
						JOptionPane.showMessageDialog(null,"��ͼ���û�д��飬��ѯ����ͼ�����Ƿ�����");
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

	//�������߱�������
	class readerListenerlostFocus extends KeyAdapter {
		public void keyTyped(KeyEvent e) {
			if (e.getKeyChar() == '\n') {
				String id = readerId.getText().trim();
				Reader reader = Dao.selectReader(id);
				if (reader == null) {
					JOptionPane.showMessageDialog(null,
							"�˶��߱��û��ע�ᣬ��ѯ������߱���Ƿ�����");
				}
				readerName.setText(reader.getName());
				number.setText(reader.getMax());
				keepMoney.setText(reader.getMoney() + "");
				readerOrder.setText(Dao.selectOrder(id).size() + "");
			}
		}
	}

	//��������ť�����¼�
	class BorrowActionListener implements ActionListener { 
		public void actionPerformed(final ActionEvent e) {
			if(Dao.selectBorrow(readerId.getText().trim(), bookId.getText().trim()).size()>0) {
				JOptionPane.showMessageDialog(null, "�ö����ѽ��Ĵ��顣");
				return;
			}
			if(bookName.getText().equals("")||readerName.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "������Ϣ���鼮��Ϣ��ȡ����");
				return;
			}
			if(Integer.parseInt(rest.getText())<=0) {
				if(!((Dao.selectOrderred(bookId.getText()).size()>0)&&(Dao.selectOrder(readerId.getText()).size()>0))){
					JOptionPane.showMessageDialog(null, "����û�п�档");
					return;
				}
				else {
					if(Integer.parseInt(rest.getText())<0) {
						JOptionPane.showMessageDialog(null, "������δ�黹��");
						return;
					}
				}
			}
			if(number.getText().equals("0")) {
				JOptionPane.showMessageDialog(null, "���˽��������Ѵ����ޡ�");
				return;
			}
			String bookIds=bookId.getText().trim();
			String readerIds=readerId.getText().trim();
			String bookNames=bookName.getText().trim();
			String operatorName=user.getName();
			String borrowDate=myfmt.format(new java.util.Date());
			String backDate=myfmt.format(getBackTime());
			String status = "�ȴ��黹";
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
				JOptionPane.showMessageDialog(null, "ͼ�������ɣ�");
				doDefaultCloseAction();
			}
		}
	}
	
	//���ԤԼ��ť�����¼�
	class OrderActionListener implements ActionListener { 
		public void actionPerformed(final ActionEvent e) {
			if(Dao.selectBorrow(readerId.getText().trim(), bookId.getText().trim()).size()>0) {
				JOptionPane.showMessageDialog(null, "�ö����ѽ��Ĵ��顣");
				return;
			}
			if(bookName.getText().equals("")||readerName.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "������Ϣ���鼮��Ϣ��ȡ����");
				return;
			}
			if(Integer.parseInt(rest.getText())>0) {
				JOptionPane.showMessageDialog(null, "�����п�治��ҪԤԼ��");
				return;
			}
			if(Integer.parseInt(rest.getText())<0) {
				JOptionPane.showMessageDialog(null, "�����ѱ�ԤԼ��");
				return;
			}
			if(number.getText().equals("0")) {
				JOptionPane.showMessageDialog(null, "���˽��������Ѵ����ޡ�");
				return;
			}
			String bookIds=bookId.getText().trim();
			String readerIds=readerId.getText().trim();
			String bookNames=bookName.getText().trim();
			String operatorName=user.getName();
			String borrowDate=myfmt.format(new java.util.Date());
			String status = "ԤԼ��";
			int i=Dao.InsertBookBorrow(bookIds, readerIds, java.sql.Timestamp.valueOf(borrowDate), null,operatorName,status);
			Dao.borrowBook(bookIds, Integer.parseInt(rest.getText())-1+"", readerIds, Integer.parseInt(number.getText())-1+"");
			if(i==1){
				JOptionPane.showMessageDialog(null, "ͼ��ԤԼ��ɣ�");
				doDefaultCloseAction();
			}
		}
	}
	
	//��ʾ��ǰʱ��
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
