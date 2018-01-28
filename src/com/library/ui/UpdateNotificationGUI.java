package com.library.ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

import com.library.Library;
import com.library.dao.Dao;
import com.library.model.Operator;

/**
 * ֪ͨ����
 */
public class UpdateNotificationGUI extends JInternalFrame {

	JTextArea text;
	Operator operator = Library.getUser();
	
	public UpdateNotificationGUI() {
		super();
		setIconifiable(true);
		setClosable(true);
		setTitle("��Ϣ����");
		setBounds(100, 100, 500, 340);
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(480,290));
		getContentPane().add(panel);
		
		JPanel panel1 = new JPanel();
		panel1.setPreferredSize(new Dimension(480,240));
		panel.add(panel1);
		
		text = new JTextArea();
		text.setFont(new Font("Serif",0,20));
		text.setText(Dao.getNews());
		text.setLineWrap(true);
		
		JScrollPane scroll = new JScrollPane(text);
		scroll.setPreferredSize(new Dimension(480,240));
		panel1.add(scroll);	
 
		JPanel panel2 = new JPanel();
		panel2.setPreferredSize(new Dimension(480,40));
		panel.add(panel2);
		
		JButton bt_confirm = new JButton("����");
		panel2.add(bt_confirm);
		bt_confirm.addActionListener(new ActionListener(){			
			public void actionPerformed(ActionEvent event) {
				if(Dao.setNews(text.getText()) == 1) {
					JOptionPane.showMessageDialog(null, "��Ϣ�����ɹ���");
					doDefaultCloseAction();
				};				
			}
		});
		JButton bt_close = new JButton("�ر�");
		bt_close.addActionListener(new ActionListener(){			
			public void actionPerformed(ActionEvent event) {
					doDefaultCloseAction();			
			}
		});
		panel2.add(bt_close);
		//չʾҳ��
		setVisible(true);
	}
}
