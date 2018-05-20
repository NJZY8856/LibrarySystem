package wsy;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.lang.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;

import wsy.MenuActions;
import wsy.framae.LoginFrame;

public class Library extends JFrame{
	private static final JDesktopPane DESKTOP_PANE=new JDesktopPane();
	public static void main(String[] args){
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new LoginFrame();//登录窗口
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void addFrame(JInternalFrame frame){
		//添加子窗体
		DESKTOP_PANE.add(frame);
	}
	public Library(){
		super();
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLocationByPlatform(true);
		setSize(700,600);
		setTitle("图书管理系统");
		JMenuBar menubar=createMenu();
		setJMenuBar(menubar);
		final JLabel label=new JLabel();
		label.setBounds(0,0,0,0);
		label.setIcon(null);
		DESKTOP_PANE.addComponentListener(new ComponentAdapter(){
			public void componentResized(final ComponentEvent e){
				Dimension size = e.getComponent().getSize();
				label.setText("<html><img width=" + size.width + " height="
						+ size.height + " src='"
						+ this.getClass().getResource("/backImg.jpg")
						+ "'></html>");
			}
		});
		DESKTOP_PANE.add(label,new Integer(Integer.MIN_VALUE));
		getContentPane().add(DESKTOP_PANE);
	}

	//创建菜单栏
	private JMenuBar createMenu(){
		JMenuBar menubar = new JMenuBar();
		menubar.setLayout(new GridLayout(1,4,70,18));
		menubar.setPreferredSize(new Dimension(50,30));		 
		JMenu baseMenu = new JMenu();// 初始化基础数据维护菜单
		baseMenu.setText("数据管理");
		baseMenu.setHorizontalTextPosition(SwingConstants.CENTER);
		baseMenu.setFont(new Font("宋体", Font.PLAIN, 20));
		{
			JMenu readerManagerMItem = new JMenu("读者信息管理");
			readerManagerMItem.add(MenuActions.READER_ADD);
			readerManagerMItem.add(MenuActions.READER_CHANGE);

			JMenu bookTypeManageMItem = new JMenu("图书类别管理");
			bookTypeManageMItem.add(MenuActions.BOOKTYPE_ADD);
			bookTypeManageMItem.add(MenuActions.BOOKTYPE_CHANGE);

			JMenu menu = new JMenu("图书信息管理");
			menu.add(MenuActions.BOOK_ADD);
			menu.add(MenuActions.BOOK_MODIFY);

			baseMenu.add(readerManagerMItem);
			baseMenu.add(bookTypeManageMItem);
			baseMenu.add(menu);
			baseMenu.addSeparator();
			baseMenu.add(MenuActions.EXIT);
		}
		JMenu borrowManageMenu = new JMenu(); // 借阅管理
		borrowManageMenu.setText("借阅管理");
		borrowManageMenu.setHorizontalTextPosition(SwingConstants.CENTER);
		borrowManageMenu.setFont(new Font("宋体", Font.PLAIN, 20));
		borrowManageMenu.add(MenuActions.BORROW); // 借阅
		borrowManageMenu.add(MenuActions.GIVE_BACK); // 归还
				
		JMenu selectManageMenu = new JMenu(); // 借阅管理
		 selectManageMenu.setText("查询与统计");
		 selectManageMenu.setHorizontalTextPosition(SwingConstants.CENTER);
		 selectManageMenu.setFont(new Font("宋体", Font.PLAIN, 20));
		 selectManageMenu.add(MenuActions.BOOK_SEARCH); // 搜索
		 selectManageMenu.add(MenuActions.BOOK_SELECT); // 查询
		 selectManageMenu.add(MenuActions.BOOK_COUNT); // 统计
		

		JMenu sysManageMenu = new JMenu(); // 系统维护
		sysManageMenu.setText("用户管理");
		sysManageMenu.setHorizontalTextPosition(SwingConstants.CENTER);
		sysManageMenu.setFont(new Font("宋体", Font.PLAIN, 20));
		sysManageMenu.add(MenuActions.USER_ADD);
		sysManageMenu.add(MenuActions.USER_CHANGE);
		menubar.add(baseMenu); // 添加基础数据维护菜单到菜单栏
		menubar.add(borrowManageMenu); // 添加借阅管理菜单到菜单栏
		menubar.add(selectManageMenu);
		menubar.add(sysManageMenu); // 添加系统维护菜单到菜单栏
		return menubar;
	}
	
}
