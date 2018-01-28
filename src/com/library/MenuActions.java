package com.library;

import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JInternalFrame;

import com.library.model.Operator;
import com.library.ui.*;
import com.library.util.*;

/**
 * 菜单和按钮的Action对象
 * 
 */
public class MenuActions {
	
	private static Map<String, JInternalFrame> frames; // 子窗体集合

	public static PasswordModiAction MODIFY_PASSWORD; //修改密码窗体动作
	public static UserModiAction USER_MODIFY; // 修改用户资料窗体动作
	public static UserAddAction USER_ADD; // 用户添加窗体动作
	public static BookSearchAction BOOK_SEARCH; // 图书搜索窗体动作
	public static GiveBackAction GIVE_BACK; // 图书归还窗体动作
	public static BorrowAction BORROW; // 图书借阅窗体动作
	public static BookTypeModiAction BOOKTYPE_MODIFY; // 图书类型修改窗体动作
	public static BookTypeAddAction BOOKTYPE_ADD; // 图书类型添加窗体动作
	public static ReaderModiAction READER_MODIFY; // 读者信息修改窗体动作
	public static ReaderAddAction READER_ADD; // 读者信息添加窗体动作
	public static BookModiAction BOOK_MODIFY; // 图书信息修改窗体动作
	public static BookAddAction BOOK_ADD; // 图书信息添加窗体动作
	public static ExitAction EXIT; // 系统退出动作
	public static CheckBookInfoAction CHECK_BOOKINFO;//查看个人借阅信息
	public static Welcome WELCOME;
	public static Operator operator;
	public static UpdateNewsAction UPDATE_NEWS;

	static {
		frames = new HashMap<String, JInternalFrame>();
		MODIFY_PASSWORD = new PasswordModiAction();
		USER_MODIFY = new UserModiAction();
		USER_ADD = new UserAddAction();
		BOOK_SEARCH = new BookSearchAction();
		GIVE_BACK = new GiveBackAction();
		BORROW = new BorrowAction();
		BOOKTYPE_MODIFY = new BookTypeModiAction();
		BOOKTYPE_ADD = new BookTypeAddAction();
		READER_MODIFY = new ReaderModiAction();
		READER_ADD = new ReaderAddAction();
		BOOK_MODIFY = new BookModiAction();
		BOOK_ADD = new BookAddAction();
		EXIT = new ExitAction();
		CHECK_BOOKINFO = new CheckBookInfoAction();
		WELCOME = new Welcome();
		UPDATE_NEWS = new UpdateNewsAction();
	}
	
	//1.1
	static class Welcome extends AbstractAction {
		Welcome() {
			super("我的基本信息", null);
			putValue(Action.LONG_DESCRIPTION, "欢迎进入图书馆里系统");
			putValue(Action.SHORT_DESCRIPTION, "欢迎进入图书馆里系统");
			setEnabled(true);
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("欢迎进入图书馆里系统")||frames.get("欢迎进入图书馆里系统").isClosed()) {
				if(Library.getUser().getAuthority().equals("1")){
					WelcomeReaderGUI iframe = new WelcomeReaderGUI();
					frames.put("欢迎进入图书馆里系统", iframe);
				}
				else{
					if(Library.getUser().getAuthority().equals("2")||Library.getUser().getAuthority().equals("3")){
						WelcomeManagerGUI iframe = new WelcomeManagerGUI();
						frames.put("欢迎进入图书馆里系统", iframe);
					}
				}
				Library.addIFame(frames.get("欢迎进入图书馆里系统"));
			}
		}
		public void welcome() {
			if (!frames.containsKey("欢迎进入图书馆里系统")||frames.get("欢迎进入图书馆里系统").isClosed()) {
				if(Library.getUser().getAuthority().equals("1")){
					WelcomeReaderGUI iframe = new WelcomeReaderGUI();
					frames.put("欢迎进入图书馆里系统", iframe);
				}
				else{
					if(Library.getUser().getAuthority().equals("2")||Library.getUser().getAuthority().equals("3")){
						WelcomeManagerGUI iframe = new WelcomeManagerGUI();
						frames.put("欢迎进入图书馆里系统", iframe);
					}
				}
				Library.addIFame(frames.get("欢迎进入图书馆里系统"));
			}
			if (!frames.containsKey("系统通知")||frames.get("系统通知").isClosed()) {
				NotificationGUI iframe2 = new NotificationGUI();
				frames.put("系统通知",iframe2);
				Library.addIFame(frames.get("系统通知"));
				if(Library.getUser().getAuthority().equals("1")||Library.getUser().getAuthority().equals("2")){
					SearchGUI iframe3 = new SearchGUI();
					frames.put("图书查询",iframe3);
					Library.addIFame(frames.get("图书查询"));
				}
			}
		}
	}
	
	//1.2
	private static class CheckBookInfoAction extends AbstractAction {
		CheckBookInfoAction() {
			super("我的借阅信息", null);
			putValue(Action.LONG_DESCRIPTION, "用户可以查看个人借阅信息");
			putValue(Action.SHORT_DESCRIPTION, "我的借阅信息");
			if(Library.getUser().getAuthority().equals("2")||Library.getUser().getAuthority().equals("3")) {
				setEnabled(false);
			}
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("我的借阅信息")||frames.get("我的借阅信息").isClosed()) {
				ReaderBorrowGUI iframe=new ReaderBorrowGUI();
				frames.put("我的借阅信息", iframe);
				Library.addIFame(frames.get("我的借阅信息"));
			}
		}
	}
		
	//2.2.1
	private static class BookTypeAddAction extends AbstractAction {
		BookTypeAddAction() {
			super("添加图书类别", null);
			putValue(Action.LONG_DESCRIPTION, "为图书馆添加新的图书类别");
			putValue(Action.SHORT_DESCRIPTION, "添加图书类别");
			if(Library.getUser().getAuthority().equals("1")||Library.getUser().getAuthority().equals("3")) {
				setEnabled(false);
			}
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("添加图书类别")||frames.get("添加图书类别").isClosed()) {
				BookTypeAddGUI iframe=new BookTypeAddGUI();
				frames.put("添加图书类别", iframe);
				Library.addIFame(frames.get("添加图书类别"));
			}
		}
	}
	
	//2.2.2
	private static class BookTypeModiAction extends AbstractAction {
		BookTypeModiAction() {
			super("修改图书类别", null);
			putValue(Action.LONG_DESCRIPTION, "修改图书的类别信息");
			putValue(Action.SHORT_DESCRIPTION, "修改图书类别");
			if(Library.getUser().getAuthority().equals("1")||Library.getUser().getAuthority().equals("3")) {
				setEnabled(false);
			}
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("修改图书类别")||frames.get("修改图书类别").isClosed()) {
				BookTypeModifyGUI iframe=new BookTypeModifyGUI();
				frames.put("修改图书类别", iframe);
				Library.addIFame(frames.get("修改图书类别"));
			}
		}
	}
	
	//2.3.1
	private static class BookAddAction extends AbstractAction {
		BookAddAction() {
			super("添加图书信息", null);
			//super();
			putValue(Action.LONG_DESCRIPTION, "为图书馆添加新的图书信息");
			putValue(Action.SHORT_DESCRIPTION, "添加图书信息");
			if(Library.getUser().getAuthority().equals("1")||Library.getUser().getAuthority().equals("3")) {
				setEnabled(false);
			}
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("添加图书信息")||frames.get("添加图书信息").isClosed()) {
				BookAddGUI iframe = new BookAddGUI();
				frames.put("添加图书信息", iframe);
				Library.addIFame(frames.get("添加图书信息"));
			}
		}
	}
	
	//2.3.2
	private static class BookModiAction extends AbstractAction {
		BookModiAction() {
			super("修改图书信息", null);
			putValue(Action.LONG_DESCRIPTION, "修改和删除图书信息");
			putValue(Action.SHORT_DESCRIPTION, "修改图书信息");
			if(Library.getUser().getAuthority().equals("1")||Library.getUser().getAuthority().equals("3")) {
				setEnabled(false);
			}
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("修改图书信息")||frames.get("修改图书信息").isClosed()) {
				BookModifyGUI iframe=new BookModifyGUI();
				frames.put("修改图书信息", iframe);
				Library.addIFame(frames.get("修改图书信息"));
			}
		}
	}
	
	//3.1
	private static class BorrowAction extends AbstractAction {
		BorrowAction() {
			super("图书借阅与预约", null);
			putValue(Action.LONG_DESCRIPTION, "从图书馆借阅或预约图书");
			putValue(Action.SHORT_DESCRIPTION, "图书借阅与预约");
			if(Library.getUser().getAuthority().equals("1")||Library.getUser().getAuthority().equals("3")) {
				setEnabled(false);
			}
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("图书借阅管理")||frames.get("图书借阅与预约管理").isClosed()) {
				BookBorrowGUI iframe=new BookBorrowGUI();
				frames.put("图书借阅与预约管理", iframe);
				Library.addIFame(frames.get("图书借阅与预约管理"));
			}
		}
	}

	//3.2
	private static class GiveBackAction extends AbstractAction {
		GiveBackAction() {
			super("图书归还与续借", null);
			putValue(Action.LONG_DESCRIPTION, "归还或续借借阅的图书");
			putValue(Action.SHORT_DESCRIPTION, "图书归还与续借");
			if(Library.getUser().getAuthority().equals("1")||Library.getUser().getAuthority().equals("3")) {
				setEnabled(false);
			}
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("图书归还与续借管理")||frames.get("图书归还与续借管理").isClosed()) {
				BookBackGUI iframe=new BookBackGUI();
				frames.put("图书归还与续借管理", iframe);
				Library.addIFame(frames.get("图书归还与续借管理"));
			}
		}
	}

	//3.3
	public static class BookSearchAction extends AbstractAction {
		BookSearchAction() {
			super("图书查询", null);
			putValue(Action.LONG_DESCRIPTION, "搜索入库的图书信息");
			putValue(Action.SHORT_DESCRIPTION, "图书详细查询");
			if(Library.getUser().getAuthority().equals("3")) {
				setEnabled(false);
			}
		}
		
		public void actionPerformed(ActionEvent e) {
				showView();
		}
		
		public void showView() {
			if (!frames.containsKey("图书详细查询")||frames.get("图书详细查询").isClosed()) {
				BookSearchGUI iframe=new BookSearchGUI();
				frames.put("图书详细查询", iframe);
				Library.addIFame(frames.get("图书详细查询"));
			}
		}
	}
	
	//4.1
	private static class PasswordModiAction extends AbstractAction {
		PasswordModiAction() {
			putValue(Action.NAME,"更改口令");
			putValue(Action.LONG_DESCRIPTION, "修改当前用户密码");
			putValue(Action.SHORT_DESCRIPTION, "更换口令");
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("更改密码")||frames.get("更改密码").isClosed()) {
				ChangePassword iframe=new ChangePassword();
				frames.put("更改密码", iframe);
				Library.addIFame(frames.get("更改密码"));
			}
		}
	}
	
	//4.2.1
	private static class ReaderAddAction extends AbstractAction {
		ReaderAddAction() {
			super("添加读者", null);
			putValue(Action.LONG_DESCRIPTION, "为图书馆添加新的读者会员信息");
			putValue(Action.SHORT_DESCRIPTION, "添加读者");
			if(Library.getUser().getAuthority().equals("1")||Library.getUser().getAuthority().equals("3")) {
				setEnabled(false);
			}
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("添加读者")||frames.get("添加读者").isClosed()) {
				ReaderAddGUI iframe=new ReaderAddGUI();
				frames.put("添加读者", iframe);
				Library.addIFame(frames.get("添加读者"));
			}
		}
	}

	//4.2.2
	private static class ReaderModiAction extends AbstractAction {
		ReaderModiAction() {
			super("修改与删除读者", null);
			putValue(Action.LONG_DESCRIPTION, "修改和删除读者的基本信息");
			putValue(Action.SHORT_DESCRIPTION, "修改与删除读者");
			if(Library.getUser().getAuthority().equals("1")||Library.getUser().getAuthority().equals("3")) {
				setEnabled(false);
			}

		}
		public void actionPerformed(ActionEvent e) {
			
			if (!frames.containsKey("修改与删除读者")||frames.get("修改与删除读者").isClosed()) {
				ReaderModifyGUI iframe=new ReaderModifyGUI();
				frames.put("修改与删除读者", iframe);
				Library.addIFame(frames.get("修改与删除读者"));
			}
		}
	}
	
	//4.2.3
	private static class UserAddAction extends AbstractAction {
		UserAddAction() {
			super("添加管理员", null);
			putValue(Action.LONG_DESCRIPTION, "添加新的管理员");
			putValue(Action.SHORT_DESCRIPTION, "添加管理员");
			if(Library.getUser().getAuthority().equals("1")||Library.getUser().getAuthority().equals("2")) {
				setEnabled(false);
			}
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("添加管理员")||frames.get("添加管理员").isClosed()) {
				ManagerAddGUI iframe=new ManagerAddGUI();
				frames.put("添加管理员", iframe);
				Library.addIFame(frames.get("添加管理员"));
			}			
		}
	}
	
	//4.2.4
	private static class UserModiAction extends AbstractAction {
		UserModiAction() {
			super("修改与删除管理员", null);
			putValue(Action.LONG_DESCRIPTION, "修改与删除管理员");
			putValue(Action.SHORT_DESCRIPTION, "修改与删除管理员");
			if(Library.getUser().getAuthority().equals("1")||Library.getUser().getAuthority().equals("2")) {
				setEnabled(false);
			}
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("修改与删除管理员")||frames.get("修改与删除管理员").isClosed()) {
				ManagerModifyGUI iframe=new ManagerModifyGUI();
				frames.put("修改与删除管理员", iframe);
				Library.addIFame(frames.get("修改与删除管理员"));
			}
		}
	}
	
	//4.3
	private static class UpdateNewsAction extends AbstractAction {
		UpdateNewsAction() {
			super("消息发布", null);
			putValue(Action.LONG_DESCRIPTION, "消息发布");
			putValue(Action.SHORT_DESCRIPTION, "消息发布");
			if(Library.getUser().getAuthority().equals("1")) {
				setEnabled(false);
			}
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("消息发布")||frames.get("消息发布").isClosed()) {
				UpdateNotificationGUI iframe=new UpdateNotificationGUI();
				frames.put("消息发布", iframe);
				Library.addIFame(frames.get("消息发布"));
			}
		}
	}
	
	//4.4
	private static class ExitAction extends AbstractAction { // 退出系统动作
		public ExitAction() {
			super("退出系统", null);
			putValue(Action.LONG_DESCRIPTION, "退出图书馆管理系统");
			putValue(Action.SHORT_DESCRIPTION, "退出系统");
		}
		public void actionPerformed(final ActionEvent e) {
			System.exit(0);
		}
	}

	private MenuActions() {
		super();
	}
}
