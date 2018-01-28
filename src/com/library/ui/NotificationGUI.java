package com.library.ui;

import java.awt.*;
import java.util.List;

import javax.swing.*;

import com.library.Library;
import com.library.dao.Dao;
import com.library.model.Borrow;
import com.library.model.Operator;

/**
 * ϵͳ֪ͨ
 */
public class NotificationGUI extends JInternalFrame {

	Operator operator = Library.getUser();
	
	public NotificationGUI() {
		super();
		setIconifiable(true);
		setClosable(true);
		setTitle("ϵͳ֪ͨ");
		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = (int)screensize.getWidth();
		int screenHeight = (int)screensize.getHeight();
		setBounds(screenWidth-300, screenHeight-350, 300, 150);
		
		
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setPreferredSize(new Dimension(0, 50));
		getContentPane().add(tabbedPane);

		JPanel panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(280,80));
		JTextArea text = new JTextArea();
		text.setPreferredSize(new Dimension(280,80));
		text.setEditable(false);
		text.setLineWrap(true);
		text.setFont(new Font("Serif",0,20));
		text.setText(Dao.getNews());
		panel_1.add(text);


		JPanel panel_2 = new JPanel();
		JLabel label_2 = new JLabel("���� "+getNum()+" ����������");
		panel_2.add(label_2);
		
		List list = Dao.selectBorrow(Library.getUser().getId());
		
		tabbedPane.addTab("��Ϣ����", panel_1);
		if(operator.getAuthority().equals("1")) {
			tabbedPane.addTab("��������", panel_2);
		}
 
		//չʾҳ��
		setVisible(true);
	}
	
	public int getNum() {
		int num = 0;
		List list = Dao.selectBorrow(operator.getId());
		for(int i = 0; i < list.size(); i++) {
			Borrow borrow = (Borrow) list.get(i);
			if(borrow.getStatus().equals("����")) {
				num++;
			}
		}
		return num;
	}
}
