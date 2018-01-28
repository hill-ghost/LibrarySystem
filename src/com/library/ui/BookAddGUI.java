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
 * ���ƣ�ͼ����Ӵ���
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
		setIconifiable(true);							// ���ô������С������������
		setClosable(true);								// ���ô���ɹرգ���������
		setTitle("ͼ����Ϣ���");						// ���ô�����⣭��������
		setBounds(100, 100, 605, 370);					// ���ô���λ�úʹ�С����������

		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 10, 5, 10));
		GridLayout gridLayout = new GridLayout(0, 4);
		gridLayout.setVgap(20);
		gridLayout.setHgap(5);
		panel.setLayout(gridLayout);
		getContentPane().add(panel);

		JLabel label_2 = new JLabel();
		label_2.setText("ͼ���ţ�");
		panel.add(label_2);

		id = new JTextField();
		id.setColumns(13);
		id.addKeyListener(new ISBNkeyListener());
		id.addFocusListener(new ISBNFocusListener());
		panel.add(id);

		JLabel label = new JLabel();
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setText("���");
		panel.add(label);

		bookType = new JComboBox();
		bookTypeModel= (DefaultComboBoxModel)bookType.getModel();
		
		//�����ݿ���ȡ��ͼ�����
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
		label_1.setText("������");
		panel.add(label_1);

		bookName = new JTextField();
		panel.add(bookName);

		JLabel label_3 = new JLabel();
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setText("���ߣ�");
		panel.add(label_3);

		writer = new JTextField();
		writer.setDocument(new MyDocument(10));
		panel.add(writer);

		final JLabel label_2_1 = new JLabel();
		label_2_1.setText("�����磺");
		panel.add(label_2_1);

		publisher = new JComboBox();
		String[]array=new String[]{"***������","**��Ϣ������","**���ͳ�����","***С�ͳ�����"};
		publisher.setModel(new DefaultComboBoxModel(array));
		panel.add(publisher);

		final JLabel label_4 = new JLabel();
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setText("���ߣ�");
		panel.add(label_4);

		translator = new JTextField();
		translator.setDocument(new MyDocument(10));
		panel.add(translator);

		final JLabel label_1_1 = new JLabel();
		label_1_1.setText("�������ڣ�");
		panel.add(label_1_1);

		SimpleDateFormat myfmt=new SimpleDateFormat("yyyy-MM-dd");
		pubDate= new JFormattedTextField(myfmt.getDateInstance());
		pubDate.setValue(new java.util.Date());
		panel.add(pubDate);
		final JLabel label_3_1 = new JLabel();
		label_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_3_1.setText("���ۣ�");
		panel.add(label_3_1);
		price=   new   JTextField();
		price.setDocument(new MyDocument(5));
		price.addKeyListener(new NumberListener());
		panel.add(price);
		
		JLabel label_num = new JLabel();
		label_num.setText("������");
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
		buttonadd.setText("���");
		panel_1.add(buttonadd);

		buttonclose = new JButton();
		buttonclose.addActionListener(new CloseActionListener());
		buttonclose.setText("�ر�");
		panel_1.add(buttonclose);

		JLabel label_5 = new JLabel();
		ImageIcon bookAddIcon=CreatecdIcon.add("bookAdd.png");
		label_5.setIcon(bookAddIcon);
		label_5.setPreferredSize(new Dimension(500, 80));
		label_5.setBorder(new LineBorder(SystemColor.activeCaptionBorder, 1, false));
		getContentPane().add(label_5, BorderLayout.NORTH);
		label_5.setText("���鶨��(LOGOͼƬ)");
		
		setVisible(true);											// ��ʾ����ɹرգ�����������������пؼ�֮��ִ�и����
	}
	
	class ISBNFocusListener extends FocusAdapter {
		public void focusLost(FocusEvent e){
			if(Dao.selectBookInfo(id.getText()) == null){
				JOptionPane.showMessageDialog(null, "�������ظ���");
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
	
	//��ӹرհ�ť���¼�������
	class CloseActionListener implements ActionListener {			
		public void actionPerformed(final ActionEvent e) {
			doDefaultCloseAction();
		}
	}
	
	//��Ӱ�ť�ĵ����¼�������
	class addBookActionListener implements ActionListener {		
		public void actionPerformed(final ActionEvent e) {			

			if(id.getText().length()==0){
				JOptionPane.showMessageDialog(null, "����ı��򲻿���Ϊ��");
				return;
			}
			if(bookName.getText().length()==0){
				JOptionPane.showMessageDialog(null, "ͼ�������ı��򲻿���Ϊ��");
				return;
			}
			if(writer.getText().length()==0){
				JOptionPane.showMessageDialog(null, "�����ı��򲻿���Ϊ��");
				return;
			}
			if(pubDate.getText().length()==0){
				JOptionPane.showMessageDialog(null, "���������ı��򲻿���Ϊ��");
				return;
			}
			if(price.getText().length()==0){
				JOptionPane.showMessageDialog(null, "�����ı��򲻿���Ϊ��");
				return;
			}
			if(num.getText().length()==0){
				JOptionPane.showMessageDialog(null, "�����ı��򲻿���Ϊ��");
				return;
			}

			
			String ids=id.getText().trim();
			
			//����
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
				JOptionPane.showMessageDialog(null, "��ӳɹ�");
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
