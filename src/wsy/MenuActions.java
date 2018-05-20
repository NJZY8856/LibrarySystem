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
 * 菜单和按钮的Action对象
 * 
 */
public class MenuActions {
	private static Map<String, JInternalFrame> frames; // 子窗体集合

	public static UserChangeAction USER_CHANGE; // 修改用户资料窗体动作
	public static UserAddAction USER_ADD; // 用户添加窗体动作
	public static BookSearchAction BOOK_SEARCH; // 图书搜索窗体动作
	public static GiveBackAction GIVE_BACK; // 图书归还窗体动作
	public static BorrowAction BORROW; // 图书借阅窗体动作
	public static BookTypeChangeAction BOOKTYPE_CHANGE; // 图书类型修改窗体动作
	public static BookTypeAddAction BOOKTYPE_ADD; // 图书类型添加窗体动作
	public static ReaderChangeAction READER_CHANGE; // 读者信息修改窗体动作
	public static ReaderAddAction READER_ADD; // 读者信息添加窗体动作
	public static BookModiAction BOOK_MODIFY; // 图书信息修改窗体动作
	public static BookAddAction BOOK_ADD; // 图书信息添加窗体动作
	public static BookSelectAction BOOK_SELECT;
	public static BookCountAction BOOK_COUNT;
	public static ExitAction EXIT; // 系统退出动作

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
			super("用户修改与删除", null);
			putValue(Action.LONG_DESCRIPTION, "修改和删除用户信息");
			putValue(Action.SHORT_DESCRIPTION, "用户修改与删除");
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("用户信息修改与删除")||frames.get("用户信息修改与删除").isClosed()) {
				UserChangeFrame iframe=new UserChangeFrame();
				frames.put("用户信息修改与删除", iframe);
				Library.addFrame(frames.get("用户信息修改与删除"));
			}
		}
	}

	private static class UserAddAction extends AbstractAction {
		UserAddAction() {
			super("用户添加", null);
			putValue(Action.LONG_DESCRIPTION, "添加新的用户");
			putValue(Action.SHORT_DESCRIPTION, "用户添加");
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("用户信息添加")||frames.get("用户信息添加").isClosed()) {
				UserAddFrame iframe=new UserAddFrame();
				frames.put("用户信息添加", iframe);
				Library.addFrame(frames.get("用户信息添加"));
			}
			
		}
	}

	private static class BookSearchAction extends AbstractAction {
		BookSearchAction() {
			super("图书查询", null);
			putValue(Action.LONG_DESCRIPTION, "搜索入库的图书信息");
			putValue(Action.SHORT_DESCRIPTION, "图书搜索");
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("图书查询")||frames.get("图书查询").isClosed()) {
				BookSearchFrame frame=new BookSearchFrame();
				frames.put("图书查询", frame);
				Library.addFrame(frames.get("图书查询"));
			}
		}
	}
	
	public static class BookSelectAction extends AbstractAction {
		BookSelectAction(){
			super("读者查询",null);
			putValue(Action.LONG_DESCRIPTION, "查询读者信息");
			putValue(Action.SHORT_DESCRIPTION, "读者搜索");
		}
			public void actionPerformed(ActionEvent e){
				if (!frames.containsKey("查询信息")||frames.get("查询信息").isClosed()) {
					BookSlectFrame frame=new BookSlectFrame();
					frames.put("查询信息", frame);
					Library.addFrame(frames.get("查询信息"));
			}
			
	}
	}
		public static class BookCountAction extends AbstractAction {
			BookCountAction(){
				super("统计信息",null);
			}
				public void actionPerformed(ActionEvent e){
					if (!frames.containsKey("统计信息")||frames.get("统计信息").isClosed()) {
						CountFrame frame=new CountFrame();
						frames.put("统计信息", frame);
						Library.addFrame(frames.get("统计信息"));
				}				
		}
		}

	private static class GiveBackAction extends AbstractAction {
		GiveBackAction() {
			super("图书归还", null);
			putValue(Action.LONG_DESCRIPTION, "归还借阅的图书");
			putValue(Action.SHORT_DESCRIPTION, "图书归还");
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("图书归还管理")||frames.get("图书归还管理").isClosed()) {
				BookBackFrame iframe=new BookBackFrame();
				frames.put("图书归还管理", iframe);
				Library.addFrame(frames.get("图书归还管理"));
			}
		}
	}

	private static class BorrowAction extends AbstractAction {
		BorrowAction() {
			super("图书借阅", null);
			putValue(Action.LONG_DESCRIPTION, "从图书馆借阅图书");
			putValue(Action.SHORT_DESCRIPTION, "图书借阅");
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("图书借阅管理")||frames.get("图书借阅管理").isClosed()) {
				BookBorrowFrame iframe=new BookBorrowFrame();
				frames.put("图书借阅管理", iframe);
				Library.addFrame(frames.get("图书借阅管理"));
			}
		}
	}


	private static class BookTypeChangeAction extends AbstractAction {
		BookTypeChangeAction() {
			super("图书类别修改", null);
			putValue(Action.LONG_DESCRIPTION, "修改图书的类别信息");
			putValue(Action.SHORT_DESCRIPTION, "图书类别修改");
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("图书类别修改")||frames.get("图书类别修改").isClosed()) {
				BookTypeChange frame=new BookTypeChange();
				frames.put("图书类别修改", frame);
				Library.addFrame(frames.get("图书类别修改"));
			}
		}
	}

	private static class BookTypeAddAction extends AbstractAction {
		BookTypeAddAction() {
			super("图书类别添加", null);
			putValue(Action.LONG_DESCRIPTION, "为图书馆添加新的图书类别");
			putValue(Action.SHORT_DESCRIPTION, "图书类别添加");
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("图书类别添加")||frames.get("图书类别添加").isClosed()) {
				BookTypeAddFrame frame=new BookTypeAddFrame();
				frames.put("图书类别添加", frame);
				Library.addFrame(frames.get("图书类别添加"));
			}
		}
	}
	private static class ReaderChangeAction extends AbstractAction {
		ReaderChangeAction() {
			super("读者修改与删除", null);
			putValue(Action.LONG_DESCRIPTION, "修改和删除读者的基本信息");
			putValue(Action.SHORT_DESCRIPTION, "读者修改与删除");
		}
		public void actionPerformed(ActionEvent e) {
			
			if (!frames.containsKey("读者信息修改与删除")||frames.get("读者信息修改与删除").isClosed()) {
				ReaderChangeFrame frame=new ReaderChangeFrame();
				frames.put("读者信息修改与删除", frame);
				Library.addFrame(frames.get("读者信息修改与删除"));
			}
		}
	}

	private static class ReaderAddAction extends AbstractAction {
		ReaderAddAction() {
			super("读者信息添加", null);
			putValue(Action.LONG_DESCRIPTION, "为图书馆添加新的读者会员信息");
			putValue(Action.SHORT_DESCRIPTION, "读者信息添加");
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("读者相关信息添加")||frames.get("读者相关信息添加").isClosed()) {
				ReaderAddFrame iframe=new ReaderAddFrame();
				frames.put("读者相关信息添加", iframe);
				Library.addFrame(frames.get("读者相关信息添加"));
			}
		}
	}
	//图书修改与删除
	private static class BookModiAction extends AbstractAction {
		BookModiAction() {
			super("图书修改", null);
			putValue(Action.LONG_DESCRIPTION, "修改和删除图书信息");
			putValue(Action.SHORT_DESCRIPTION, "图书修改");
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("图书修改")||frames.get("图书修改").isClosed()) {
				BookChangeFrame iframe=new BookChangeFrame();
				frames.put("图书修改", iframe);
				Library.addFrame(frames.get("图书修改"));
			}
		}
	}
	private static class BookAddAction extends AbstractAction {				// 图书信息添加－－－已经实现，请参照
		BookAddAction() {

			super("图书信息添加", null);
			//super();
			putValue(Action.LONG_DESCRIPTION, "为图书馆添加新的图书信息");
			putValue(Action.SHORT_DESCRIPTION, "图书信息添加");
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("图书信息添加")||frames.get("图书信息添加").isClosed()) {
				BookAddFrame iframe = new BookAddFrame();
				frames.put("图书信息添加", iframe);
				Library.addFrame(frames.get("图书信息添加"));
			}
		}
	}
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