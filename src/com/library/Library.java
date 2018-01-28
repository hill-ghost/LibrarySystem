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
 * �����壬��������
 * 
 */

public class Library extends JFrame {
	
	private static Operator operator;
	private static final JDesktopPane DESKTOP_PANE = new JDesktopPane();
	
	public static void main(String[] args) {		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new LoginGUI();//��¼����
		} 
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}	
	public static void addIFame(JInternalFrame iframe) { // ����Ӵ���ķ���
		DESKTOP_PANE.add(iframe);
	}
	
	public Library(Operator operator) {
		super();
		this.operator = operator;
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLocationByPlatform(true);
//		setSize(1024, 768);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setTitle("ͼ��ݹ���ϵͳ");
		JMenuBar menuBar = createMenu(); // ���ô����˵����ķ���
		setJMenuBar(menuBar);
		JToolBar toolBar = createToolBar(); // ���ô����������ķ���
		getContentPane().add(toolBar, BorderLayout.NORTH);
		final JLabel label = new JLabel();
		label.setBounds(0, 0, 0, 0);
		label.setIcon(null); // ���屳��

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
		
		//ͼ������
		JButton bookSearchButton=new JButton(MenuActions.BOOK_SEARCH);
		ImageIcon booksearchicon=CreatecdIcon.add("image2.jpg");
		bookSearchButton.setIcon(booksearchicon);
		bookSearchButton.setHideActionText(true);
		toolBar.add(bookSearchButton);
		
		//ͼ����Ϣ���
		JButton bookAddButton=new JButton(MenuActions.BOOK_ADD);
		ImageIcon icon=new ImageIcon(Library.class.getResource("/image11.jpg"));	
		bookAddButton.setIcon(icon);
		bookAddButton.setHideActionText(true);
		
		//bookAddButton.setToolTipText("fjdkjfk");//ͼƬ����ʾ��
		toolBar.add(bookAddButton);
		//toolBar.add(MenuActions.BOOK_MODIFY);
		
		//ͼ����Ϣ�޸�
		JButton bookModiAndDelButton=new JButton(MenuActions.BOOK_MODIFY);
		ImageIcon bookmodiicon=CreatecdIcon.add("image1.jpg");
		bookModiAndDelButton.setIcon(bookmodiicon);
		bookModiAndDelButton.setHideActionText(true);
		toolBar.add(bookModiAndDelButton);
		
		//ͼ��������
		JButton bookTypeAddButton=new JButton(MenuActions.BOOKTYPE_ADD);
		ImageIcon bookTypeAddicon=CreatecdIcon.add("image9.jpg");
		bookTypeAddButton.setIcon(bookTypeAddicon);
		bookTypeAddButton.setHideActionText(true);
		toolBar.add(bookTypeAddButton);
		
		//ͼ������޸�
		JButton bookTypeMotifyButton=new JButton(MenuActions.BOOKTYPE_MODIFY);
		ImageIcon bookTypeMotifyicon=CreatecdIcon.add("image4.jpg");
		bookTypeMotifyButton.setIcon(bookTypeMotifyicon);
		bookTypeMotifyButton.setHideActionText(true);
		toolBar.add(bookTypeMotifyButton);
		
		//ͼ����Ĺ���
		JButton bookBorrowButton=new JButton(MenuActions.BORROW);
		ImageIcon bookBorrowicon=CreatecdIcon.add("image10.jpg");//����ͼ�귽��
		bookBorrowButton.setIcon(bookBorrowicon);
		bookBorrowButton.setHideActionText(true);
		toolBar.add(bookBorrowButton);
		
		//ͼ��黹����
		JButton bookBackButton=new JButton(MenuActions.GIVE_BACK);
		ImageIcon bookBackicon=CreatecdIcon.add("image6.jpg");//����ͼ�귽��
		bookBackButton.setIcon(bookBackicon);
		bookBackButton.setHideActionText(true);
		toolBar.add(bookBackButton);
						
		//������Ϣ���
		JButton readerAddButton=new JButton(MenuActions.READER_ADD);
		ImageIcon readerAddicon=CreatecdIcon.add("image3.jpg");
		readerAddButton.setIcon(readerAddicon);
		readerAddButton.setHideActionText(true);
		toolBar.add(readerAddButton);
		
		//������Ϣ����
		JButton readerModiAndDelButton=new JButton(MenuActions.READER_MODIFY);
		ImageIcon readerModiAndDelicon=CreatecdIcon.add("image7.jpg");
		readerModiAndDelButton.setIcon(readerModiAndDelicon);
		readerModiAndDelButton.setHideActionText(true);
		toolBar.add(readerModiAndDelButton);
		
		//�˳�ϵͳ
		JButton ExitButton=new JButton(MenuActions.EXIT);
		ImageIcon Exiticon=CreatecdIcon.add("image8.jpg");
		ExitButton.setIcon(Exiticon);
		ExitButton.setHideActionText(true);
		toolBar.add(ExitButton);
		return toolBar;
	}
	
	/**
	 * �����˵���
	 */
	private JMenuBar createMenu() { // �����˵����ķ���
		JMenuBar menuBar = new JMenuBar();
		
		//������Ϣ
		JMenu infoMenu = new JMenu();
		infoMenu.setIcon(CreatecdIcon.add("btinfo.jpg"));
		infoMenu.add(MenuActions.WELCOME);
		infoMenu.add(MenuActions.CHECK_BOOKINFO);
		
		//��������ά���˵�
		JMenu baseMenu = new JMenu();
		baseMenu.setIcon(CreatecdIcon.add("btbase.jpg"));
		JMenu bookTypeManageMItem = new JMenu("ͼ��������");
		bookTypeManageMItem.add(MenuActions.BOOKTYPE_ADD);
		bookTypeManageMItem.add(MenuActions.BOOKTYPE_MODIFY);

		JMenu bookInfoManagemenu = new JMenu("ͼ����Ϣ����");
		bookInfoManagemenu.add(MenuActions.BOOK_ADD);
		bookInfoManagemenu.add(MenuActions.BOOK_MODIFY);

		baseMenu.add(bookTypeManageMItem);
		baseMenu.add(bookInfoManagemenu);		
		
		//���Ĺ���
		JMenu borrowManageMenu = new JMenu();
		borrowManageMenu.setIcon(CreatecdIcon.add("btmanage.jpg"));
		borrowManageMenu.add(MenuActions.BORROW); // ����
		borrowManageMenu.add(MenuActions.GIVE_BACK); // �黹
		borrowManageMenu.add(MenuActions.BOOK_SEARCH); // ����

		//ϵͳά��
		JMenu sysManageMenu = new JMenu(); // ϵͳά��
		sysManageMenu.setIcon(CreatecdIcon.add("btsystem.jpg"));
		JMenu userManageMItem = new JMenu("�û�����"); //�û�����
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
