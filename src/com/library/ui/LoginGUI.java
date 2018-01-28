package com.library.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.library.Library;
import com.library.dao.Dao;
import com.library.model.Operator;
import com.library.util.CreatecdIcon;
import com.library.util.MyDocument;

public class LoginGUI extends JFrame {
	
	private JPasswordField password;
	private JTextField userid;
	private JButton login;
	private JButton reset;
	private Operator user;

	public LoginGUI() {
		super();
		BorderLayout borderLayout = new BorderLayout();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		borderLayout.setVgap(20);
		getContentPane().setLayout(borderLayout);
		setTitle("ͼ��ݹ���ϵͳ��¼");
		setBounds(100, 100, 285, 220);

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBorder(new EmptyBorder(0, 0, 0, 0));
		getContentPane().add(panel);
		
		//ͼƬ��ʾ
		JLabel imageLabel = new JLabel();
		ImageIcon loginIcon=CreatecdIcon.add("login.jpg");
		imageLabel.setIcon(loginIcon);
		imageLabel.setOpaque(true);
		imageLabel.setBackground(Color.GREEN);
		imageLabel.setPreferredSize(new Dimension(260, 60));
		panel.add(imageLabel, BorderLayout.NORTH);

		//�м䲿�֣�����2��������2��label
		JPanel panel_2 = new JPanel();
		GridLayout gridLayout = new GridLayout(0, 2);
		gridLayout.setHgap(5);
		gridLayout.setVgap(15);
		panel_2.setLayout(gridLayout);
		panel_2.setBorder(new EmptyBorder(10, 0, 10, 10));
		panel.add(panel_2);

		JLabel label1 = new JLabel();
		label1.setText("�û���ţ�");
		label1.setHorizontalAlignment(SwingConstants.CENTER);
		label1.setPreferredSize(new Dimension(0, 0));
		label1.setMinimumSize(new Dimension(0, 0));
		panel_2.add(label1);

		userid = new JTextField(20);
		userid.setPreferredSize(new Dimension(0, 0));
		panel_2.add(userid);

		JLabel label2 = new JLabel();
		label2.setText("�û����룺");
		label2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(label2);

		password = new JPasswordField(20);
		password.setDocument(new MyDocument(6));
		//���������Ļ����ַ�
		password.setEchoChar('*');
		//�����¼���ȷ����ʱת����������¼�
		password.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10)
					login.doClick();
			}
		});
		panel_2.add(password);
		
		//���ð�ť�����
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.SOUTH);

		login=new JButton();
		login.setText("��¼");
		login.addActionListener(new BookLoginAction());
		
		reset=new JButton();
		reset.setText("����");
		reset.addActionListener(new BookResetAction());
		
		panel_1.add(login);
		panel_1.add(reset);

		setVisible(true);
		setResizable(false);
	}
	
	//���ð�ť�ĵ���¼�
	private class BookResetAction implements ActionListener {
		public void actionPerformed(final ActionEvent e){
			userid.setText("");
			password.setText("");			
		}
	}
	
	//��½��ť�ĵ���¼�
	private class BookLoginAction implements ActionListener {
		public void actionPerformed(final ActionEvent e) {
			user = Dao.check(userid.getText(), password.getText());
			if (user.getName() != null) {
				try {
					Library frame = new Library(user);
					frame.setVisible(true);
					LoginGUI.this.setVisible(false);
				} 
				catch (Exception ex) {
					ex.printStackTrace();
				}
			} 
			else {
				JOptionPane.showMessageDialog(null, "�˺�����������������룡");
				userid.setText("");
				password.setText("");
			}
		}
	}	

}
