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
		setTitle("图书馆管理系统登录");
		setBounds(100, 100, 285, 220);

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBorder(new EmptyBorder(0, 0, 0, 0));
		getContentPane().add(panel);
		
		//图片显示
		JLabel imageLabel = new JLabel();
		ImageIcon loginIcon=CreatecdIcon.add("login.jpg");
		imageLabel.setIcon(loginIcon);
		imageLabel.setOpaque(true);
		imageLabel.setBackground(Color.GREEN);
		imageLabel.setPreferredSize(new Dimension(260, 60));
		panel.add(imageLabel, BorderLayout.NORTH);

		//中间部分，包含2个输入框和2个label
		JPanel panel_2 = new JPanel();
		GridLayout gridLayout = new GridLayout(0, 2);
		gridLayout.setHgap(5);
		gridLayout.setVgap(15);
		panel_2.setLayout(gridLayout);
		panel_2.setBorder(new EmptyBorder(10, 0, 10, 10));
		panel.add(panel_2);

		JLabel label1 = new JLabel();
		label1.setText("用户编号：");
		label1.setHorizontalAlignment(SwingConstants.CENTER);
		label1.setPreferredSize(new Dimension(0, 0));
		label1.setMinimumSize(new Dimension(0, 0));
		panel_2.add(label1);

		userid = new JTextField(20);
		userid.setPreferredSize(new Dimension(0, 0));
		panel_2.add(userid);

		JLabel label2 = new JLabel();
		label2.setText("用户密码：");
		label2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(label2);

		password = new JPasswordField(20);
		password.setDocument(new MyDocument(6));
		//设置密码框的回显字符
		password.setEchoChar('*');
		//当按下键盘确定键时转化成鼠标点击事件
		password.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10)
					login.doClick();
			}
		});
		panel_2.add(password);
		
		//放置按钮的面板
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.SOUTH);

		login=new JButton();
		login.setText("登录");
		login.addActionListener(new BookLoginAction());
		
		reset=new JButton();
		reset.setText("重置");
		reset.addActionListener(new BookResetAction());
		
		panel_1.add(login);
		panel_1.add(reset);

		setVisible(true);
		setResizable(false);
	}
	
	//重置按钮的点击事件
	private class BookResetAction implements ActionListener {
		public void actionPerformed(final ActionEvent e){
			userid.setText("");
			password.setText("");			
		}
	}
	
	//登陆按钮的点击事件
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
				JOptionPane.showMessageDialog(null, "账号密码错误，请重新输入！");
				userid.setText("");
				password.setText("");
			}
		}
	}	

}
