package com.library.ui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.text.NumberFormat;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.library.common.Item;
import com.library.common.MapPz;
import com.library.dao.Dao;
import com.library.model.BookInfo;
import com.library.model.BookType;
import com.library.util.CreatecdIcon;
import com.library.util.MyDocument;

/**
 * 名称：图书修改窗体
 *
 */
public class BookModifyGUI extends JInternalFrame {
	private JTable table;
	private JFormattedTextField   price;
	private JFormattedTextField pubDate;
	private JTextField translator;
	private JTextField publisher;
	private JTextField writer;
	private JTextField id;
	private JTextField num;
	private JTextField bookName;
	private JComboBox bookType;
	DefaultComboBoxModel bookTypeModel;
	private Item item;
	Map map=new HashMap();
	private Map m=MapPz.getMap();
	private String[] columnNames = { "图书编号", "图书类别", "图书名称", "作者", "译者", "出版商",
			"出版日期", "价格","总量"};

	public BookModifyGUI() {
		super();
		BorderLayout borderLayout = new BorderLayout();
		getContentPane().setLayout(borderLayout);
		setIconifiable(true);
		setClosable(true);
		setTitle("图书信息修改");
		setBounds(100, 100, 593, 550);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(SystemColor.activeCaptionBorder, 1, false));
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		FlowLayout flowLayout = new FlowLayout();
		flowLayout.setVgap(2);
		flowLayout.setHgap(30);
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel_1.setLayout(flowLayout);

		JButton button = new JButton();
		button.addActionListener(new addBookActionListener());
		button.setText("修改");
		panel_1.add(button);


		JButton button_1 = new JButton();
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				doDefaultCloseAction();
			}
		});
		
		button_1.setText("关闭");
		panel_1.add(button_1);

		final JLabel headLogo = new JLabel();
		ImageIcon bookModiAndDelIcon=CreatecdIcon.add("bookModiAndDel.png");
		headLogo.setIcon(bookModiAndDelIcon);
		headLogo.setOpaque(true);
		headLogo.setBackground(Color.CYAN);
		headLogo.setPreferredSize(new Dimension(400, 80));
		headLogo.setBorder(new LineBorder(SystemColor.activeCaptionBorder, 1, false));
		getContentPane().add(headLogo, BorderLayout.NORTH);


		JPanel panel_2 = new JPanel();
		BorderLayout borderLayout_1 = new BorderLayout();
		borderLayout_1.setVgap(5);
		panel_2.setLayout(borderLayout_1);
		panel_2.setBorder(new EmptyBorder(5, 10, 5, 10));
		getContentPane().add(panel_2);

		JScrollPane scrollPane = new JScrollPane();
		panel_2.add(scrollPane);

		Object[][] results=getFileStates(Dao.selectbooksearch());
		table = new JTable(results,columnNames);
		//设置列宽
		TableColumn column2;
		for(int i=0;i<table.getColumnCount();i++) {
			column2 = table.getColumnModel().getColumn(i);
			column2.setPreferredWidth(120);
		}
		table.setRowHeight(27);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		//鼠标单击表格中的内容产生事件,将表格中的内容放入文本框中
		table.addMouseListener(new TableListener());

								
		scrollPane.setViewportView(table);

		final JPanel bookPanel = new JPanel();
		panel_2.add(bookPanel, BorderLayout.SOUTH);
		final GridLayout gridLayout = new GridLayout(0, 4);
		gridLayout.setVgap(5);
		gridLayout.setHgap(5);
		bookPanel.setLayout(gridLayout);

		final JLabel label_2 = new JLabel();
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setText("书       号：");
		bookPanel.add(label_2);

		id = new JTextField();
		bookPanel.add(id);
		final JLabel label = new JLabel();
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setText("类       别：");
		bookPanel.add(label);

		bookType = new JComboBox();
		bookTypeModel= (DefaultComboBoxModel)bookType.getModel();
		List list=Dao.selectBookCategory();
		for(int i=0;i<list.size();i++){
			BookType booktype=(BookType)list.get(i);
			item=new Item();
			item.setId((String)booktype.getId());
			item.setName((String)booktype.getName());
			bookTypeModel.addElement(item);
			map.put(item.getId(), item);
			
		}
		bookPanel.add(bookType);

		final JLabel label_1 = new JLabel();
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setText("书       名：");
		bookPanel.add(label_1);

		bookName = new JTextField();
		bookPanel.add(bookName);

		final JLabel label_3 = new JLabel();
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setText("作       者：");
		bookPanel.add(label_3);

		writer = new JTextField();
		bookPanel.add(writer);

		final JLabel label_2_1 = new JLabel();
		label_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_2_1.setText("出  版  社：");
		bookPanel.add(label_2_1);

		publisher = new JTextField();
		bookPanel.add(publisher);

		final JLabel label_4 = new JLabel();
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setText("译       者：");
		bookPanel.add(label_4);

		translator = new JTextField();
		bookPanel.add(translator);

		JLabel label_1_1 = new JLabel();
		label_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1_1.setText("出 版 日 期：");
		bookPanel.add(label_1_1);


		SimpleDateFormat myfmt=new SimpleDateFormat("yyyy-MM-dd");
		pubDate= new JFormattedTextField(myfmt.getDateInstance());
		pubDate.setValue(new java.util.Date());
		bookPanel.add(pubDate);

		JLabel label_3_1 = new JLabel();
		label_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_3_1.setText("单       价：");
		bookPanel.add(label_3_1);

		price=new JFormattedTextField();
		price.addKeyListener(new NumberListener());
		bookPanel.add(price);
		
		JLabel label_num = new JLabel("数       量:");
		label_num.setHorizontalAlignment(SwingConstants.CENTER);
		bookPanel.add(label_num);
		num = new JTextField();
		bookPanel.add(num);
		
		setVisible(true);
	}
	
	//取数据库中图书相关信息放入表格中
	private Object[][] getFileStates(List list){
		Object[][]results=new Object[list.size()][columnNames.length];		
		for(int i=0;i<list.size();i++){
			BookInfo bookinfo=(BookInfo)list.get(i);
			results[i][0]=bookinfo.getId();
			String booktypename=String.valueOf(MapPz.getMap().get(bookinfo.getTypeId()));
			results[i][1]=booktypename;
			results[i][2]=bookinfo.getName();
			results[i][3]=bookinfo.getWriter();
			results[i][4]=bookinfo.getTranslator();
			results[i][5]=bookinfo.getPublisher();
			results[i][6]=bookinfo.getDate();
			results[i][7]=bookinfo.getPrice();
			results[i][8]=bookinfo.getNum();
		}
		return results;	         		
	}
	
	class TableListener extends MouseAdapter {
		public void mouseClicked(final MouseEvent e) {
			String ids, typeids, bookNames,writers,translators,publishers,dates,prices, nums;
			int selRow = table.getSelectedRow();
			ids = table.getValueAt(selRow, 0).toString().trim();
			typeids = table.getValueAt(selRow, 1).toString().trim();
			bookNames = table.getValueAt(selRow, 2).toString().trim();
			writers = table.getValueAt(selRow, 3).toString().trim();
			if(!(table.getValueAt(selRow, 4)==null)) {
				translators = table.getValueAt(selRow, 4).toString().trim();
			}
			else {
				translators="";
			}
			publishers = table.getValueAt(selRow, 5).toString().trim();
			dates = table.getValueAt(selRow, 6).toString().trim();
			prices = table.getValueAt(selRow, 7).toString().trim();
			nums =  table.getValueAt(selRow, 8).toString().trim();
			
			id.setText(ids);
			
			for(int i=0;i<bookTypeModel.getSize();i++){
				Item o=(Item)bookTypeModel.getElementAt(i);
				if(o.getId().equals(typeids)){
					bookTypeModel.setSelectedItem(o);
				}
			}
			
			bookName.setText(bookNames);
			writer.setText(writers);
			translator.setText(translators);
			publisher.setText(publishers);
			pubDate.setText(dates);
			price.setText(prices);
			num.setText(nums);
		}
	}
	class addBookActionListener implements ActionListener {
		public void actionPerformed(final ActionEvent e) {
			// 修改图书信息表
			if(id.getText().length()==0){
				JOptionPane.showMessageDialog(null, "书号文本框文本框不可以为空");
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
			if(publisher.getText().length()==0){
				JOptionPane.showMessageDialog(null, "出版人文本框不可以为空");
				return;
			}
			//日期与单价进行数字验证代码？
			if(pubDate.getText().length()==0){
				JOptionPane.showMessageDialog(null, "出版日期文本框不可以为空");
				return;
			}
			if(price.getText().length()==0){
				JOptionPane.showMessageDialog(null, "单价文本框不可以为空");
				return;
			}
			
			
			
			String ids=id.getText().trim();
			
			//分类
			Object selectedItem = bookTypeModel.getSelectedItem();
			if (selectedItem == null)
				return;
			Item item = (Item)selectedItem;
			String bookTypes=item.getId();
			System.out.println(bookTypes);
											
			String translators=translator.getText().trim();
			String bookNames=bookName.getText().trim();
			String writers=writer.getText().trim();
			String publishers=publisher.getText().trim();
			String pubDates=pubDate.getText().trim();
			String prices=price.getText().trim();
			String nums = num.getText().trim();
			
			int i=Dao.updatebook(ids, bookTypes, bookNames, writers, translators, publishers, Date.valueOf(pubDates), Double.parseDouble(prices), nums);
			System.out.println(i);
			
			if(i==1){

				JOptionPane.showMessageDialog(null, "修改成功");
				Object[][] results=getFileStates(Dao.selectbooksearch());
				DefaultTableModel model=new DefaultTableModel();
				table.setModel(model);
				model.setDataVector(results, columnNames);	
				TableColumn column2;
				for(int j=0;j<table.getColumnCount();j++) {
					column2 = table.getColumnModel().getColumn(j);
					column2.setPreferredWidth(120);
				}
				table.setRowHeight(27);			
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
