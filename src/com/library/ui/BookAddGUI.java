package com.library.ui;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.library.common.Item;
import com.library.dao.Dao;
import com.library.model.BookType;
import com.library.util.CreatecdIcon;
import com.library.util.MyDocument;

/**
 * 名称：图书添加窗体
 * 
 */
public class BookAddGUI extends JInternalFrame {
	private JComboBox publisher;
	private JTextField   price;
	private JFormattedTextField pubDate;
	private JTextField translator;
	private JTextField writer;
	private JTextField id;
	private JTextField num;
	private JTextField bookName;
	private JComboBox bookType;
	private JButton buttonadd;
	private JButton buttonclose;
	DefaultComboBoxModel bookTypeModel;
	
	public BookAddGUI() {
		super();
		BorderLayout borderLayout = new BorderLayout();
		getContentPane().setLayout(borderLayout);
		setIconifiable(true);							// 设置窗体可最小化－－－必须
		setClosable(true);								// 设置窗体可关闭－－－必须
		setTitle("图书信息添加");						// 设置窗体标题－－－必须
		setBounds(100, 100, 605, 370);					// 设置窗体位置和大小－－－必须

		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 10, 5, 10));
		GridLayout gridLayout = new GridLayout(0, 4);
		gridLayout.setVgap(20);
		gridLayout.setHgap(5);
		panel.setLayout(gridLayout);
		getContentPane().add(panel);

		JLabel label_2 = new JLabel();
		label_2.setText("图书编号：");
		panel.add(label_2);

		id = new JTextField();
		id.setColumns(13);
		id.addKeyListener(new ISBNkeyListener());
		id.addFocusListener(new ISBNFocusListener());
		panel.add(id);

		JLabel label = new JLabel();
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setText("类别：");
		panel.add(label);

		bookType = new JComboBox();
		bookTypeModel= (DefaultComboBoxModel)bookType.getModel();
		
		//从数据库中取出图书类别
		List list=Dao.selectBookCategory();
		for(int i=0;i<list.size();i++){
			BookType booktype=(BookType)list.get(i);
			Item item=new Item();
			item.setId((String)booktype.getId());
			item.setName((String)booktype.getName());
			bookTypeModel.addElement(item);
		}
		panel.add(bookType);

		JLabel label_1 = new JLabel();
		label_1.setText("书名：");
		panel.add(label_1);

		bookName = new JTextField();
		panel.add(bookName);

		JLabel label_3 = new JLabel();
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setText("作者：");
		panel.add(label_3);

		writer = new JTextField();
		writer.setDocument(new MyDocument(10));
		panel.add(writer);

		final JLabel label_2_1 = new JLabel();
		label_2_1.setText("出版社：");
		panel.add(label_2_1);

		publisher = new JComboBox();
		String[]array=new String[]{"***出版社","**信息出版社","**大型出版社","***小型出版社"};
		publisher.setModel(new DefaultComboBoxModel(array));
		panel.add(publisher);

		final JLabel label_4 = new JLabel();
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setText("译者：");
		panel.add(label_4);

		translator = new JTextField();
		translator.setDocument(new MyDocument(10));
		panel.add(translator);

		final JLabel label_1_1 = new JLabel();
		label_1_1.setText("出版日期：");
		panel.add(label_1_1);

		SimpleDateFormat myfmt=new SimpleDateFormat("yyyy-MM-dd");
		pubDate= new JFormattedTextField(myfmt.getDateInstance());
		pubDate.setValue(new java.util.Date());
		panel.add(pubDate);
		final JLabel label_3_1 = new JLabel();
		label_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_3_1.setText("单价：");
		panel.add(label_3_1);
		price=   new   JTextField();
		price.setDocument(new MyDocument(5));
		price.addKeyListener(new NumberListener());
		panel.add(price);
		
		JLabel label_num = new JLabel();
		label_num.setText("数量：");
		panel.add(label_num);
		num = new JTextField();
		num.addKeyListener(new NumberListener());
		panel.add(num);
		

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(SystemColor.activeCaptionBorder, 1, false));
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		FlowLayout flowLayout = new FlowLayout();
		flowLayout.setVgap(2);
		flowLayout.setHgap(30);
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel_1.setLayout(flowLayout);

		buttonadd= new JButton();
		buttonadd.addActionListener(new addBookActionListener());
		buttonadd.setText("添加");
		panel_1.add(buttonadd);

		buttonclose = new JButton();
		buttonclose.addActionListener(new CloseActionListener());
		buttonclose.setText("关闭");
		panel_1.add(buttonclose);

		JLabel label_5 = new JLabel();
		ImageIcon bookAddIcon=CreatecdIcon.add("bookAdd.png");
		label_5.setIcon(bookAddIcon);
		label_5.setPreferredSize(new Dimension(500, 80));
		label_5.setBorder(new LineBorder(SystemColor.activeCaptionBorder, 1, false));
		getContentPane().add(label_5, BorderLayout.NORTH);
		label_5.setText("新书定购(LOGO图片)");
		
		setVisible(true);											// 显示窗体可关闭－－－必须在添加所有控件之后执行该语句
	}
	
	class ISBNFocusListener extends FocusAdapter {
		public void focusLost(FocusEvent e){
			if(Dao.selectBookInfo(id.getText()) == null){
				JOptionPane.showMessageDialog(null, "添加书号重复！");
				return;
			}
		}
	}
	class ISBNkeyListener extends KeyAdapter {
		public void keyPressed(final KeyEvent e) {
			if (e.getKeyCode() == 13){
				buttonadd.doClick();
			}
		
		}
	}
	
	//添加关闭按钮的事件监听器
	class CloseActionListener implements ActionListener {			
		public void actionPerformed(final ActionEvent e) {
			doDefaultCloseAction();
		}
	}
	
	//添加按钮的单击事件监听器
	class addBookActionListener implements ActionListener {		
		public void actionPerformed(final ActionEvent e) {			

			if(id.getText().length()==0){
				JOptionPane.showMessageDialog(null, "书号文本框不可以为空");
				return;
			}
			if(bookName.getText().length()==0){
				JOptionPane.showMessageDialog(null, "图书名称文本框不可以为空");
				return;
			}
			if(writer.getText().length()==0){
				JOptionPane.showMessageDialog(null, "作者文本框不可以为空");
				return;
			}
			if(pubDate.getText().length()==0){
				JOptionPane.showMessageDialog(null, "出版日期文本框不可以为空");
				return;
			}
			if(price.getText().length()==0){
				JOptionPane.showMessageDialog(null, "单价文本框不可以为空");
				return;
			}
			if(num.getText().length()==0){
				JOptionPane.showMessageDialog(null, "数量文本框不可以为空");
				return;
			}

			
			String ids=id.getText().trim();
			
			//分类
			Object selectedItem = bookType.getSelectedItem();
			if (selectedItem == null)
				return;
			Item item = (Item) selectedItem;
			
			String bookTypes=item.getId();			
			String translators=translator.getText().trim();
			String names=bookName.getText().trim();
			String writers=writer.getText().trim();
			String publishers=(String)publisher.getSelectedItem();
			String pubDates=pubDate.getText().trim();
			String prices=price.getText().trim();
			String nums = num.getText().trim();
			int i=Dao.Insertbook(ids, bookTypes, writers, translators, publishers, java.sql.Date.valueOf(pubDates),Double.parseDouble(prices), names, nums);
				
			if(i==1){			
				JOptionPane.showMessageDialog(null, "添加成功");
				doDefaultCloseAction();
			}
		}
	}
	
	class NumberListener extends KeyAdapter {
		public void keyTyped(KeyEvent e) {
			String numStr="0123456789."+(char)8;
			if(numStr.indexOf(e.getKeyChar())<0){
				e.consume();
			}
		}
	}
	
}
