package com.library;

import java.awt.BorderLayout;
import java.awt.Dimension;
//import java.awt.Dialog.ModalExclusionType;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;

import com.library.model.Operator;
import com.library.ui.LoginGUI;
import com.library.ui.ManagerModifyGUI;
import com.library.util.CreatecdIcon;;

/**
 * 主窗体，程序的入口
 * 
 */

public class Library extends JFrame {
	
	private static Operator operator;
	private static final JDesktopPane DESKTOP_PANE = new JDesktopPane();
	
	public static void main(String[] args) {		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new LoginGUI();//登录窗口
		} 
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}	
	public static void addIFame(JInternalFrame iframe) { // 添加子窗体的方法
		DESKTOP_PANE.add(iframe);
	}
	
	public Library(Operator operator) {
		super();
		this.operator = operator;
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLocationByPlatform(true);
//		setSize(1024, 768);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setTitle("图书馆管理系统");
		JMenuBar menuBar = createMenu(); // 调用创建菜单栏的方法
		setJMenuBar(menuBar);
		JToolBar toolBar = createToolBar(); // 调用创建工具栏的方法
		getContentPane().add(toolBar, BorderLayout.NORTH);
		final JLabel label = new JLabel();
		label.setBounds(0, 0, 0, 0);
		label.setIcon(null); // 窗体背景

		DESKTOP_PANE.addComponentListener(new ComponentAdapter() {
			public void componentResized(final ComponentEvent e) {
				Dimension size = e.getComponent().getSize();
				label.setSize(e.getComponent().getSize());
				label.setText("<html><img width=" + size.width + " height="
						+ size.height + " src='"
						+ this.getClass().getResource("/backImg2.jpg")
						+ "'></html>");
			}
		});
		DESKTOP_PANE.add(label,new Integer(Integer.MIN_VALUE));
		getContentPane().add(DESKTOP_PANE);
		
		MenuActions.WELCOME.welcome();
	}
	
	private JToolBar createToolBar() {
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.setBorder(new BevelBorder(BevelBorder.RAISED));
		
		//图书搜索
		JButton bookSearchButton=new JButton(MenuActions.BOOK_SEARCH);
		ImageIcon booksearchicon=CreatecdIcon.add("image2.jpg");
		bookSearchButton.setIcon(booksearchicon);
		bookSearchButton.setHideActionText(true);
		toolBar.add(bookSearchButton);
		
		//图书信息添加
		JButton bookAddButton=new JButton(MenuActions.BOOK_ADD);
		ImageIcon icon=new ImageIcon(Library.class.getResource("/image11.jpg"));	
		bookAddButton.setIcon(icon);
		bookAddButton.setHideActionText(true);
		
		//bookAddButton.setToolTipText("fjdkjfk");//图片上提示字
		toolBar.add(bookAddButton);
		//toolBar.add(MenuActions.BOOK_MODIFY);
		
		//图书信息修改
		JButton bookModiAndDelButton=new JButton(MenuActions.BOOK_MODIFY);
		ImageIcon bookmodiicon=CreatecdIcon.add("image1.jpg");
		bookModiAndDelButton.setIcon(bookmodiicon);
		bookModiAndDelButton.setHideActionText(true);
		toolBar.add(bookModiAndDelButton);
		
		//图书类别添加
		JButton bookTypeAddButton=new JButton(MenuActions.BOOKTYPE_ADD);
		ImageIcon bookTypeAddicon=CreatecdIcon.add("image9.jpg");
		bookTypeAddButton.setIcon(bookTypeAddicon);
		bookTypeAddButton.setHideActionText(true);
		toolBar.add(bookTypeAddButton);
		
		//图书类别修改
		JButton bookTypeMotifyButton=new JButton(MenuActions.BOOKTYPE_MODIFY);
		ImageIcon bookTypeMotifyicon=CreatecdIcon.add("image4.jpg");
		bookTypeMotifyButton.setIcon(bookTypeMotifyicon);
		bookTypeMotifyButton.setHideActionText(true);
		toolBar.add(bookTypeMotifyButton);
		
		//图书借阅管理
		JButton bookBorrowButton=new JButton(MenuActions.BORROW);
		ImageIcon bookBorrowicon=CreatecdIcon.add("image10.jpg");//创建图标方法
		bookBorrowButton.setIcon(bookBorrowicon);
		bookBorrowButton.setHideActionText(true);
		toolBar.add(bookBorrowButton);
		
		//图书归还管理
		JButton bookBackButton=new JButton(MenuActions.GIVE_BACK);
		ImageIcon bookBackicon=CreatecdIcon.add("image6.jpg");//创建图标方法
		bookBackButton.setIcon(bookBackicon);
		bookBackButton.setHideActionText(true);
		toolBar.add(bookBackButton);
						
		//读者信息添加
		JButton readerAddButton=new JButton(MenuActions.READER_ADD);
		ImageIcon readerAddicon=CreatecdIcon.add("image3.jpg");
		readerAddButton.setIcon(readerAddicon);
		readerAddButton.setHideActionText(true);
		toolBar.add(readerAddButton);
		
		//读者信息管理
		JButton readerModiAndDelButton=new JButton(MenuActions.READER_MODIFY);
		ImageIcon readerModiAndDelicon=CreatecdIcon.add("image7.jpg");
		readerModiAndDelButton.setIcon(readerModiAndDelicon);
		readerModiAndDelButton.setHideActionText(true);
		toolBar.add(readerModiAndDelButton);
		
		//退出系统
		JButton ExitButton=new JButton(MenuActions.EXIT);
		ImageIcon Exiticon=CreatecdIcon.add("image8.jpg");
		ExitButton.setIcon(Exiticon);
		ExitButton.setHideActionText(true);
		toolBar.add(ExitButton);
		return toolBar;
	}
	
	/**
	 * 创建菜单栏
	 */
	private JMenuBar createMenu() { // 创建菜单栏的方法
		JMenuBar menuBar = new JMenuBar();
		
		//个人信息
		JMenu infoMenu = new JMenu();
		infoMenu.setIcon(CreatecdIcon.add("btinfo.jpg"));
		infoMenu.add(MenuActions.WELCOME);
		infoMenu.add(MenuActions.CHECK_BOOKINFO);
		
		//基础数据维护菜单
		JMenu baseMenu = new JMenu();
		baseMenu.setIcon(CreatecdIcon.add("btbase.jpg"));
		JMenu bookTypeManageMItem = new JMenu("图书类别管理");
		bookTypeManageMItem.add(MenuActions.BOOKTYPE_ADD);
		bookTypeManageMItem.add(MenuActions.BOOKTYPE_MODIFY);

		JMenu bookInfoManagemenu = new JMenu("图书信息管理");
		bookInfoManagemenu.add(MenuActions.BOOK_ADD);
		bookInfoManagemenu.add(MenuActions.BOOK_MODIFY);

		baseMenu.add(bookTypeManageMItem);
		baseMenu.add(bookInfoManagemenu);		
		
		//借阅管理
		JMenu borrowManageMenu = new JMenu();
		borrowManageMenu.setIcon(CreatecdIcon.add("btmanage.jpg"));
		borrowManageMenu.add(MenuActions.BORROW); // 借阅
		borrowManageMenu.add(MenuActions.GIVE_BACK); // 归还
		borrowManageMenu.add(MenuActions.BOOK_SEARCH); // 搜索

		//系统维护
		JMenu sysManageMenu = new JMenu(); // 系统维护
		sysManageMenu.setIcon(CreatecdIcon.add("btsystem.jpg"));
		JMenu userManageMItem = new JMenu("用户管理"); //用户管理
		userManageMItem.add(MenuActions.READER_ADD);
		userManageMItem.add(MenuActions.READER_MODIFY);
		userManageMItem.add(MenuActions.USER_ADD);
		userManageMItem.add(MenuActions.USER_MODIFY);
		
		sysManageMenu.add(MenuActions.MODIFY_PASSWORD);
		sysManageMenu.add(userManageMItem);
		sysManageMenu.add(MenuActions.UPDATE_NEWS);
		sysManageMenu.addSeparator();
		sysManageMenu.add(MenuActions.EXIT);

		menuBar.add(infoMenu);
		menuBar.add(baseMenu);
		menuBar.add(borrowManageMenu);
		menuBar.add(sysManageMenu);
		return menuBar;
	}
	
	public static Operator getUser() {
		return operator;
	}
}
