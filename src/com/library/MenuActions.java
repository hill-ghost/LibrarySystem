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
 * �˵��Ͱ�ť��Action����
 * 
 */
public class MenuActions {
	
	private static Map<String, JInternalFrame> frames; // �Ӵ��弯��

	public static PasswordModiAction MODIFY_PASSWORD; //�޸����봰�嶯��
	public static UserModiAction USER_MODIFY; // �޸��û����ϴ��嶯��
	public static UserAddAction USER_ADD; // �û���Ӵ��嶯��
	public static BookSearchAction BOOK_SEARCH; // ͼ���������嶯��
	public static GiveBackAction GIVE_BACK; // ͼ��黹���嶯��
	public static BorrowAction BORROW; // ͼ����Ĵ��嶯��
	public static BookTypeModiAction BOOKTYPE_MODIFY; // ͼ�������޸Ĵ��嶯��
	public static BookTypeAddAction BOOKTYPE_ADD; // ͼ��������Ӵ��嶯��
	public static ReaderModiAction READER_MODIFY; // ������Ϣ�޸Ĵ��嶯��
	public static ReaderAddAction READER_ADD; // ������Ϣ��Ӵ��嶯��
	public static BookModiAction BOOK_MODIFY; // ͼ����Ϣ�޸Ĵ��嶯��
	public static BookAddAction BOOK_ADD; // ͼ����Ϣ��Ӵ��嶯��
	public static ExitAction EXIT; // ϵͳ�˳�����
	public static CheckBookInfoAction CHECK_BOOKINFO;//�鿴���˽�����Ϣ
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
			super("�ҵĻ�����Ϣ", null);
			putValue(Action.LONG_DESCRIPTION, "��ӭ����ͼ�����ϵͳ");
			putValue(Action.SHORT_DESCRIPTION, "��ӭ����ͼ�����ϵͳ");
			setEnabled(true);
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("��ӭ����ͼ�����ϵͳ")||frames.get("��ӭ����ͼ�����ϵͳ").isClosed()) {
				if(Library.getUser().getAuthority().equals("1")){
					WelcomeReaderGUI iframe = new WelcomeReaderGUI();
					frames.put("��ӭ����ͼ�����ϵͳ", iframe);
				}
				else{
					if(Library.getUser().getAuthority().equals("2")||Library.getUser().getAuthority().equals("3")){
						WelcomeManagerGUI iframe = new WelcomeManagerGUI();
						frames.put("��ӭ����ͼ�����ϵͳ", iframe);
					}
				}
				Library.addIFame(frames.get("��ӭ����ͼ�����ϵͳ"));
			}
		}
		public void welcome() {
			if (!frames.containsKey("��ӭ����ͼ�����ϵͳ")||frames.get("��ӭ����ͼ�����ϵͳ").isClosed()) {
				if(Library.getUser().getAuthority().equals("1")){
					WelcomeReaderGUI iframe = new WelcomeReaderGUI();
					frames.put("��ӭ����ͼ�����ϵͳ", iframe);
				}
				else{
					if(Library.getUser().getAuthority().equals("2")||Library.getUser().getAuthority().equals("3")){
						WelcomeManagerGUI iframe = new WelcomeManagerGUI();
						frames.put("��ӭ����ͼ�����ϵͳ", iframe);
					}
				}
				Library.addIFame(frames.get("��ӭ����ͼ�����ϵͳ"));
			}
			if (!frames.containsKey("ϵͳ֪ͨ")||frames.get("ϵͳ֪ͨ").isClosed()) {
				NotificationGUI iframe2 = new NotificationGUI();
				frames.put("ϵͳ֪ͨ",iframe2);
				Library.addIFame(frames.get("ϵͳ֪ͨ"));
				if(Library.getUser().getAuthority().equals("1")||Library.getUser().getAuthority().equals("2")){
					SearchGUI iframe3 = new SearchGUI();
					frames.put("ͼ���ѯ",iframe3);
					Library.addIFame(frames.get("ͼ���ѯ"));
				}
			}
		}
	}
	
	//1.2
	private static class CheckBookInfoAction extends AbstractAction {
		CheckBookInfoAction() {
			super("�ҵĽ�����Ϣ", null);
			putValue(Action.LONG_DESCRIPTION, "�û����Բ鿴���˽�����Ϣ");
			putValue(Action.SHORT_DESCRIPTION, "�ҵĽ�����Ϣ");
			if(Library.getUser().getAuthority().equals("2")||Library.getUser().getAuthority().equals("3")) {
				setEnabled(false);
			}
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("�ҵĽ�����Ϣ")||frames.get("�ҵĽ�����Ϣ").isClosed()) {
				ReaderBorrowGUI iframe=new ReaderBorrowGUI();
				frames.put("�ҵĽ�����Ϣ", iframe);
				Library.addIFame(frames.get("�ҵĽ�����Ϣ"));
			}
		}
	}
		
	//2.2.1
	private static class BookTypeAddAction extends AbstractAction {
		BookTypeAddAction() {
			super("���ͼ�����", null);
			putValue(Action.LONG_DESCRIPTION, "Ϊͼ�������µ�ͼ�����");
			putValue(Action.SHORT_DESCRIPTION, "���ͼ�����");
			if(Library.getUser().getAuthority().equals("1")||Library.getUser().getAuthority().equals("3")) {
				setEnabled(false);
			}
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("���ͼ�����")||frames.get("���ͼ�����").isClosed()) {
				BookTypeAddGUI iframe=new BookTypeAddGUI();
				frames.put("���ͼ�����", iframe);
				Library.addIFame(frames.get("���ͼ�����"));
			}
		}
	}
	
	//2.2.2
	private static class BookTypeModiAction extends AbstractAction {
		BookTypeModiAction() {
			super("�޸�ͼ�����", null);
			putValue(Action.LONG_DESCRIPTION, "�޸�ͼ��������Ϣ");
			putValue(Action.SHORT_DESCRIPTION, "�޸�ͼ�����");
			if(Library.getUser().getAuthority().equals("1")||Library.getUser().getAuthority().equals("3")) {
				setEnabled(false);
			}
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("�޸�ͼ�����")||frames.get("�޸�ͼ�����").isClosed()) {
				BookTypeModifyGUI iframe=new BookTypeModifyGUI();
				frames.put("�޸�ͼ�����", iframe);
				Library.addIFame(frames.get("�޸�ͼ�����"));
			}
		}
	}
	
	//2.3.1
	private static class BookAddAction extends AbstractAction {
		BookAddAction() {
			super("���ͼ����Ϣ", null);
			//super();
			putValue(Action.LONG_DESCRIPTION, "Ϊͼ�������µ�ͼ����Ϣ");
			putValue(Action.SHORT_DESCRIPTION, "���ͼ����Ϣ");
			if(Library.getUser().getAuthority().equals("1")||Library.getUser().getAuthority().equals("3")) {
				setEnabled(false);
			}
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("���ͼ����Ϣ")||frames.get("���ͼ����Ϣ").isClosed()) {
				BookAddGUI iframe = new BookAddGUI();
				frames.put("���ͼ����Ϣ", iframe);
				Library.addIFame(frames.get("���ͼ����Ϣ"));
			}
		}
	}
	
	//2.3.2
	private static class BookModiAction extends AbstractAction {
		BookModiAction() {
			super("�޸�ͼ����Ϣ", null);
			putValue(Action.LONG_DESCRIPTION, "�޸ĺ�ɾ��ͼ����Ϣ");
			putValue(Action.SHORT_DESCRIPTION, "�޸�ͼ����Ϣ");
			if(Library.getUser().getAuthority().equals("1")||Library.getUser().getAuthority().equals("3")) {
				setEnabled(false);
			}
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("�޸�ͼ����Ϣ")||frames.get("�޸�ͼ����Ϣ").isClosed()) {
				BookModifyGUI iframe=new BookModifyGUI();
				frames.put("�޸�ͼ����Ϣ", iframe);
				Library.addIFame(frames.get("�޸�ͼ����Ϣ"));
			}
		}
	}
	
	//3.1
	private static class BorrowAction extends AbstractAction {
		BorrowAction() {
			super("ͼ�������ԤԼ", null);
			putValue(Action.LONG_DESCRIPTION, "��ͼ��ݽ��Ļ�ԤԼͼ��");
			putValue(Action.SHORT_DESCRIPTION, "ͼ�������ԤԼ");
			if(Library.getUser().getAuthority().equals("1")||Library.getUser().getAuthority().equals("3")) {
				setEnabled(false);
			}
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("ͼ����Ĺ���")||frames.get("ͼ�������ԤԼ����").isClosed()) {
				BookBorrowGUI iframe=new BookBorrowGUI();
				frames.put("ͼ�������ԤԼ����", iframe);
				Library.addIFame(frames.get("ͼ�������ԤԼ����"));
			}
		}
	}

	//3.2
	private static class GiveBackAction extends AbstractAction {
		GiveBackAction() {
			super("ͼ��黹������", null);
			putValue(Action.LONG_DESCRIPTION, "�黹��������ĵ�ͼ��");
			putValue(Action.SHORT_DESCRIPTION, "ͼ��黹������");
			if(Library.getUser().getAuthority().equals("1")||Library.getUser().getAuthority().equals("3")) {
				setEnabled(false);
			}
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("ͼ��黹���������")||frames.get("ͼ��黹���������").isClosed()) {
				BookBackGUI iframe=new BookBackGUI();
				frames.put("ͼ��黹���������", iframe);
				Library.addIFame(frames.get("ͼ��黹���������"));
			}
		}
	}

	//3.3
	public static class BookSearchAction extends AbstractAction {
		BookSearchAction() {
			super("ͼ���ѯ", null);
			putValue(Action.LONG_DESCRIPTION, "��������ͼ����Ϣ");
			putValue(Action.SHORT_DESCRIPTION, "ͼ����ϸ��ѯ");
			if(Library.getUser().getAuthority().equals("3")) {
				setEnabled(false);
			}
		}
		
		public void actionPerformed(ActionEvent e) {
				showView();
		}
		
		public void showView() {
			if (!frames.containsKey("ͼ����ϸ��ѯ")||frames.get("ͼ����ϸ��ѯ").isClosed()) {
				BookSearchGUI iframe=new BookSearchGUI();
				frames.put("ͼ����ϸ��ѯ", iframe);
				Library.addIFame(frames.get("ͼ����ϸ��ѯ"));
			}
		}
	}
	
	//4.1
	private static class PasswordModiAction extends AbstractAction {
		PasswordModiAction() {
			putValue(Action.NAME,"���Ŀ���");
			putValue(Action.LONG_DESCRIPTION, "�޸ĵ�ǰ�û�����");
			putValue(Action.SHORT_DESCRIPTION, "��������");
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("��������")||frames.get("��������").isClosed()) {
				ChangePassword iframe=new ChangePassword();
				frames.put("��������", iframe);
				Library.addIFame(frames.get("��������"));
			}
		}
	}
	
	//4.2.1
	private static class ReaderAddAction extends AbstractAction {
		ReaderAddAction() {
			super("��Ӷ���", null);
			putValue(Action.LONG_DESCRIPTION, "Ϊͼ�������µĶ��߻�Ա��Ϣ");
			putValue(Action.SHORT_DESCRIPTION, "��Ӷ���");
			if(Library.getUser().getAuthority().equals("1")||Library.getUser().getAuthority().equals("3")) {
				setEnabled(false);
			}
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("��Ӷ���")||frames.get("��Ӷ���").isClosed()) {
				ReaderAddGUI iframe=new ReaderAddGUI();
				frames.put("��Ӷ���", iframe);
				Library.addIFame(frames.get("��Ӷ���"));
			}
		}
	}

	//4.2.2
	private static class ReaderModiAction extends AbstractAction {
		ReaderModiAction() {
			super("�޸���ɾ������", null);
			putValue(Action.LONG_DESCRIPTION, "�޸ĺ�ɾ�����ߵĻ�����Ϣ");
			putValue(Action.SHORT_DESCRIPTION, "�޸���ɾ������");
			if(Library.getUser().getAuthority().equals("1")||Library.getUser().getAuthority().equals("3")) {
				setEnabled(false);
			}

		}
		public void actionPerformed(ActionEvent e) {
			
			if (!frames.containsKey("�޸���ɾ������")||frames.get("�޸���ɾ������").isClosed()) {
				ReaderModifyGUI iframe=new ReaderModifyGUI();
				frames.put("�޸���ɾ������", iframe);
				Library.addIFame(frames.get("�޸���ɾ������"));
			}
		}
	}
	
	//4.2.3
	private static class UserAddAction extends AbstractAction {
		UserAddAction() {
			super("��ӹ���Ա", null);
			putValue(Action.LONG_DESCRIPTION, "����µĹ���Ա");
			putValue(Action.SHORT_DESCRIPTION, "��ӹ���Ա");
			if(Library.getUser().getAuthority().equals("1")||Library.getUser().getAuthority().equals("2")) {
				setEnabled(false);
			}
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("��ӹ���Ա")||frames.get("��ӹ���Ա").isClosed()) {
				ManagerAddGUI iframe=new ManagerAddGUI();
				frames.put("��ӹ���Ա", iframe);
				Library.addIFame(frames.get("��ӹ���Ա"));
			}			
		}
	}
	
	//4.2.4
	private static class UserModiAction extends AbstractAction {
		UserModiAction() {
			super("�޸���ɾ������Ա", null);
			putValue(Action.LONG_DESCRIPTION, "�޸���ɾ������Ա");
			putValue(Action.SHORT_DESCRIPTION, "�޸���ɾ������Ա");
			if(Library.getUser().getAuthority().equals("1")||Library.getUser().getAuthority().equals("2")) {
				setEnabled(false);
			}
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("�޸���ɾ������Ա")||frames.get("�޸���ɾ������Ա").isClosed()) {
				ManagerModifyGUI iframe=new ManagerModifyGUI();
				frames.put("�޸���ɾ������Ա", iframe);
				Library.addIFame(frames.get("�޸���ɾ������Ա"));
			}
		}
	}
	
	//4.3
	private static class UpdateNewsAction extends AbstractAction {
		UpdateNewsAction() {
			super("��Ϣ����", null);
			putValue(Action.LONG_DESCRIPTION, "��Ϣ����");
			putValue(Action.SHORT_DESCRIPTION, "��Ϣ����");
			if(Library.getUser().getAuthority().equals("1")) {
				setEnabled(false);
			}
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("��Ϣ����")||frames.get("��Ϣ����").isClosed()) {
				UpdateNotificationGUI iframe=new UpdateNotificationGUI();
				frames.put("��Ϣ����", iframe);
				Library.addIFame(frames.get("��Ϣ����"));
			}
		}
	}
	
	//4.4
	private static class ExitAction extends AbstractAction { // �˳�ϵͳ����
		public ExitAction() {
			super("�˳�ϵͳ", null);
			putValue(Action.LONG_DESCRIPTION, "�˳�ͼ��ݹ���ϵͳ");
			putValue(Action.SHORT_DESCRIPTION, "�˳�ϵͳ");
		}
		public void actionPerformed(final ActionEvent e) {
			System.exit(0);
		}
	}

	private MenuActions() {
		super();
	}
}
