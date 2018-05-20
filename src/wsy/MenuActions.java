package wsy;

import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JInternalFrame;

import wsy.framae.BookAddFrame;
import wsy.framae.BookBackFrame;
import wsy.framae.BookBorrowFrame;
import wsy.framae.BookChangeFrame;
import wsy.framae.BookSearchFrame;
import wsy.framae.BookSlectFrame;
import wsy.framae.BookTypeAddFrame;
import wsy.framae.BookTypeChange;
import wsy.framae.CountFrame;
import wsy.framae.ReaderAddFrame;
import wsy.framae.ReaderChangeFrame;
import wsy.framae.UserAddFrame;
import wsy.framae.UserChangeFrame;
import wsy.util.*;
/**
 * �˵��Ͱ�ť��Action����
 * 
 */
public class MenuActions {
	private static Map<String, JInternalFrame> frames; // �Ӵ��弯��

	public static UserChangeAction USER_CHANGE; // �޸��û����ϴ��嶯��
	public static UserAddAction USER_ADD; // �û���Ӵ��嶯��
	public static BookSearchAction BOOK_SEARCH; // ͼ���������嶯��
	public static GiveBackAction GIVE_BACK; // ͼ��黹���嶯��
	public static BorrowAction BORROW; // ͼ����Ĵ��嶯��
	public static BookTypeChangeAction BOOKTYPE_CHANGE; // ͼ�������޸Ĵ��嶯��
	public static BookTypeAddAction BOOKTYPE_ADD; // ͼ��������Ӵ��嶯��
	public static ReaderChangeAction READER_CHANGE; // ������Ϣ�޸Ĵ��嶯��
	public static ReaderAddAction READER_ADD; // ������Ϣ��Ӵ��嶯��
	public static BookModiAction BOOK_MODIFY; // ͼ����Ϣ�޸Ĵ��嶯��
	public static BookAddAction BOOK_ADD; // ͼ����Ϣ��Ӵ��嶯��
	public static BookSelectAction BOOK_SELECT;
	public static BookCountAction BOOK_COUNT;
	public static ExitAction EXIT; // ϵͳ�˳�����

	static {
		frames = new HashMap<String, JInternalFrame>();
		USER_CHANGE= new UserChangeAction();
		USER_ADD = new UserAddAction();
		BOOK_SEARCH = new BookSearchAction();
		GIVE_BACK = new GiveBackAction();
		BORROW = new BorrowAction();
		BOOKTYPE_CHANGE = new BookTypeChangeAction();
		BOOKTYPE_ADD = new BookTypeAddAction();
		READER_CHANGE = new ReaderChangeAction();
		READER_ADD = new ReaderAddAction();
		BOOK_MODIFY = new BookModiAction();
		BOOK_ADD = new BookAddAction();
		EXIT = new ExitAction();
		BOOK_SELECT = new BookSelectAction();
		BOOK_COUNT = new BookCountAction();
	}

	

	private static class UserChangeAction extends AbstractAction {
		UserChangeAction() {
			super("�û��޸���ɾ��", null);
			putValue(Action.LONG_DESCRIPTION, "�޸ĺ�ɾ���û���Ϣ");
			putValue(Action.SHORT_DESCRIPTION, "�û��޸���ɾ��");
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("�û���Ϣ�޸���ɾ��")||frames.get("�û���Ϣ�޸���ɾ��").isClosed()) {
				UserChangeFrame iframe=new UserChangeFrame();
				frames.put("�û���Ϣ�޸���ɾ��", iframe);
				Library.addFrame(frames.get("�û���Ϣ�޸���ɾ��"));
			}
		}
	}

	private static class UserAddAction extends AbstractAction {
		UserAddAction() {
			super("�û����", null);
			putValue(Action.LONG_DESCRIPTION, "����µ��û�");
			putValue(Action.SHORT_DESCRIPTION, "�û����");
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("�û���Ϣ���")||frames.get("�û���Ϣ���").isClosed()) {
				UserAddFrame iframe=new UserAddFrame();
				frames.put("�û���Ϣ���", iframe);
				Library.addFrame(frames.get("�û���Ϣ���"));
			}
			
		}
	}

	private static class BookSearchAction extends AbstractAction {
		BookSearchAction() {
			super("ͼ���ѯ", null);
			putValue(Action.LONG_DESCRIPTION, "��������ͼ����Ϣ");
			putValue(Action.SHORT_DESCRIPTION, "ͼ������");
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("ͼ���ѯ")||frames.get("ͼ���ѯ").isClosed()) {
				BookSearchFrame frame=new BookSearchFrame();
				frames.put("ͼ���ѯ", frame);
				Library.addFrame(frames.get("ͼ���ѯ"));
			}
		}
	}
	
	public static class BookSelectAction extends AbstractAction {
		BookSelectAction(){
			super("���߲�ѯ",null);
			putValue(Action.LONG_DESCRIPTION, "��ѯ������Ϣ");
			putValue(Action.SHORT_DESCRIPTION, "��������");
		}
			public void actionPerformed(ActionEvent e){
				if (!frames.containsKey("��ѯ��Ϣ")||frames.get("��ѯ��Ϣ").isClosed()) {
					BookSlectFrame frame=new BookSlectFrame();
					frames.put("��ѯ��Ϣ", frame);
					Library.addFrame(frames.get("��ѯ��Ϣ"));
			}
			
	}
	}
		public static class BookCountAction extends AbstractAction {
			BookCountAction(){
				super("ͳ����Ϣ",null);
			}
				public void actionPerformed(ActionEvent e){
					if (!frames.containsKey("ͳ����Ϣ")||frames.get("ͳ����Ϣ").isClosed()) {
						CountFrame frame=new CountFrame();
						frames.put("ͳ����Ϣ", frame);
						Library.addFrame(frames.get("ͳ����Ϣ"));
				}				
		}
		}

	private static class GiveBackAction extends AbstractAction {
		GiveBackAction() {
			super("ͼ��黹", null);
			putValue(Action.LONG_DESCRIPTION, "�黹���ĵ�ͼ��");
			putValue(Action.SHORT_DESCRIPTION, "ͼ��黹");
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("ͼ��黹����")||frames.get("ͼ��黹����").isClosed()) {
				BookBackFrame iframe=new BookBackFrame();
				frames.put("ͼ��黹����", iframe);
				Library.addFrame(frames.get("ͼ��黹����"));
			}
		}
	}

	private static class BorrowAction extends AbstractAction {
		BorrowAction() {
			super("ͼ�����", null);
			putValue(Action.LONG_DESCRIPTION, "��ͼ��ݽ���ͼ��");
			putValue(Action.SHORT_DESCRIPTION, "ͼ�����");
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("ͼ����Ĺ���")||frames.get("ͼ����Ĺ���").isClosed()) {
				BookBorrowFrame iframe=new BookBorrowFrame();
				frames.put("ͼ����Ĺ���", iframe);
				Library.addFrame(frames.get("ͼ����Ĺ���"));
			}
		}
	}


	private static class BookTypeChangeAction extends AbstractAction {
		BookTypeChangeAction() {
			super("ͼ������޸�", null);
			putValue(Action.LONG_DESCRIPTION, "�޸�ͼ��������Ϣ");
			putValue(Action.SHORT_DESCRIPTION, "ͼ������޸�");
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("ͼ������޸�")||frames.get("ͼ������޸�").isClosed()) {
				BookTypeChange frame=new BookTypeChange();
				frames.put("ͼ������޸�", frame);
				Library.addFrame(frames.get("ͼ������޸�"));
			}
		}
	}

	private static class BookTypeAddAction extends AbstractAction {
		BookTypeAddAction() {
			super("ͼ��������", null);
			putValue(Action.LONG_DESCRIPTION, "Ϊͼ�������µ�ͼ�����");
			putValue(Action.SHORT_DESCRIPTION, "ͼ��������");
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("ͼ��������")||frames.get("ͼ��������").isClosed()) {
				BookTypeAddFrame frame=new BookTypeAddFrame();
				frames.put("ͼ��������", frame);
				Library.addFrame(frames.get("ͼ��������"));
			}
		}
	}
	private static class ReaderChangeAction extends AbstractAction {
		ReaderChangeAction() {
			super("�����޸���ɾ��", null);
			putValue(Action.LONG_DESCRIPTION, "�޸ĺ�ɾ�����ߵĻ�����Ϣ");
			putValue(Action.SHORT_DESCRIPTION, "�����޸���ɾ��");
		}
		public void actionPerformed(ActionEvent e) {
			
			if (!frames.containsKey("������Ϣ�޸���ɾ��")||frames.get("������Ϣ�޸���ɾ��").isClosed()) {
				ReaderChangeFrame frame=new ReaderChangeFrame();
				frames.put("������Ϣ�޸���ɾ��", frame);
				Library.addFrame(frames.get("������Ϣ�޸���ɾ��"));
			}
		}
	}

	private static class ReaderAddAction extends AbstractAction {
		ReaderAddAction() {
			super("������Ϣ���", null);
			putValue(Action.LONG_DESCRIPTION, "Ϊͼ�������µĶ��߻�Ա��Ϣ");
			putValue(Action.SHORT_DESCRIPTION, "������Ϣ���");
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("���������Ϣ���")||frames.get("���������Ϣ���").isClosed()) {
				ReaderAddFrame iframe=new ReaderAddFrame();
				frames.put("���������Ϣ���", iframe);
				Library.addFrame(frames.get("���������Ϣ���"));
			}
		}
	}
	//ͼ���޸���ɾ��
	private static class BookModiAction extends AbstractAction {
		BookModiAction() {
			super("ͼ���޸�", null);
			putValue(Action.LONG_DESCRIPTION, "�޸ĺ�ɾ��ͼ����Ϣ");
			putValue(Action.SHORT_DESCRIPTION, "ͼ���޸�");
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("ͼ���޸�")||frames.get("ͼ���޸�").isClosed()) {
				BookChangeFrame iframe=new BookChangeFrame();
				frames.put("ͼ���޸�", iframe);
				Library.addFrame(frames.get("ͼ���޸�"));
			}
		}
	}
	private static class BookAddAction extends AbstractAction {				// ͼ����Ϣ��ӣ������Ѿ�ʵ�֣������
		BookAddAction() {

			super("ͼ����Ϣ���", null);
			//super();
			putValue(Action.LONG_DESCRIPTION, "Ϊͼ�������µ�ͼ����Ϣ");
			putValue(Action.SHORT_DESCRIPTION, "ͼ����Ϣ���");
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("ͼ����Ϣ���")||frames.get("ͼ����Ϣ���").isClosed()) {
				BookAddFrame iframe = new BookAddFrame();
				frames.put("ͼ����Ϣ���", iframe);
				Library.addFrame(frames.get("ͼ����Ϣ���"));
			}
		}
	}
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