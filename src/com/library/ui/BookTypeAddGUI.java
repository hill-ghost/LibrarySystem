package com.library.ui;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.library.dao.Dao;
import com.library.util.CreatecdIcon;
import com.library.util.MyDocument;

public class BookTypeAddGUI extends JInternalFrame {

	private JFormattedTextField days;
	private JTextField bookTypeName;
	private JTextField fakuan;
	
	
	/**
	 * ͼ��������ӵĴ���
	 */
	public BookTypeAddGUI() {
		super();
		setIconifiable(true);							// ���ô������С������������
		setClosable(true);
		setTitle("ͼ��������");
		setBounds(100, 100, 500, 270);

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setPreferredSize(new Dimension(500, 80));
		getContentPane().add(panel, BorderLayout.NORTH);

		JLabel label_4 = new JLabel();
		ImageIcon bookTypeAddIcon=CreatecdIcon.add("bookTypeAdd.png");
		label_4.setIcon(bookTypeAddIcon);
		label_4.setPreferredSize(new Dimension(400, 80));
		label_4.setText("ͼ�����ͼƬ��400*80��");
		panel.add(label_4);

		JPanel panel_3 = new JPanel();
		panel_3.setLayout(new FlowLayout());
		getContentPane().add(panel_3, BorderLayout.CENTER);

		JLabel label_2 = new JLabel();
		label_2.setPreferredSize(new Dimension(160, 20));
		label_2.setText("ͼ���������");
		panel_3.add(label_2);

		bookTypeName = new JTextField();
		bookTypeName.setDocument(new MyDocument(20));
		bookTypeName.setColumns(30);
		panel_3.add(bookTypeName);

		JLabel label_3 = new JLabel();
		label_3.setPreferredSize(new Dimension(160, 20));
		label_3.setText("�ɽ�����");
		panel_3.add(label_3);

		days = new JFormattedTextField(NumberFormat.getIntegerInstance());
		days.setColumns(30);
		days.setValue(30);
		panel_3.add(days);
        
		JLabel label_5 = new JLabel();
		label_5.setPreferredSize(new Dimension(160,20));
		label_5.setText("����(��/��)");
		panel_3.add(label_5);

		fakuan = new JTextField();
		fakuan.setColumns(30);
		fakuan.addKeyListener(new NumberListener());
		  
		
		panel_3.add(fakuan);

		final JButton button = new JButton();
		button.setText("����");
		button.addActionListener(new ActionListener(){
			public void actionPerformed(final ActionEvent e) {
				if(bookTypeName.getText().length()==0){
					JOptionPane.showMessageDialog(null, "ͼ������ı��򲻿�Ϊ��");
					return;
				}
				if(days.getText().length()==0){
					JOptionPane.showMessageDialog(null, "�ɽ������ı��򲻿�Ϊ��");
					return;
				}
				if(fakuan.getText().length()==0){
					JOptionPane.showMessageDialog(null, "�����ı��򲻿�Ϊ��");
					return;
				}
				int i=Dao.InsertBookType(bookTypeName.getText().trim(), days.getText().trim(),Double.valueOf(fakuan.getText().trim())/10);
				if(i==1){
					JOptionPane.showMessageDialog(null, "��ӳɹ���");
					doDefaultCloseAction();
				}
			}
		});
		panel_3.add(button);

		final JButton buttonDel = new JButton();
		buttonDel.setText("�ر�");
		buttonDel.addActionListener(new ActionListener(){
			public void actionPerformed(final ActionEvent e) {
				doDefaultCloseAction();
			}
		});
		panel_3.add(buttonDel);
		setVisible(true);
		//
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
