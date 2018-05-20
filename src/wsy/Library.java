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
			new LoginFrame();//��¼����
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void addFrame(JInternalFrame frame){
		//����Ӵ���
		DESKTOP_PANE.add(frame);
	}
	public Library(){
		super();
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLocationByPlatform(true);
		setSize(700,600);
		setTitle("ͼ�����ϵͳ");
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

	//�����˵���
	private JMenuBar createMenu(){
		JMenuBar menubar = new JMenuBar();
		menubar.setLayout(new GridLayout(1,4,70,18));
		menubar.setPreferredSize(new Dimension(50,30));		 
		JMenu baseMenu = new JMenu();// ��ʼ����������ά���˵�
		baseMenu.setText("���ݹ���");
		baseMenu.setHorizontalTextPosition(SwingConstants.CENTER);
		baseMenu.setFont(new Font("����", Font.PLAIN, 20));
		{
			JMenu readerManagerMItem = new JMenu("������Ϣ����");
			readerManagerMItem.add(MenuActions.READER_ADD);
			readerManagerMItem.add(MenuActions.READER_CHANGE);

			JMenu bookTypeManageMItem = new JMenu("ͼ��������");
			bookTypeManageMItem.add(MenuActions.BOOKTYPE_ADD);
			bookTypeManageMItem.add(MenuActions.BOOKTYPE_CHANGE);

			JMenu menu = new JMenu("ͼ����Ϣ����");
			menu.add(MenuActions.BOOK_ADD);
			menu.add(MenuActions.BOOK_MODIFY);

			baseMenu.add(readerManagerMItem);
			baseMenu.add(bookTypeManageMItem);
			baseMenu.add(menu);
			baseMenu.addSeparator();
			baseMenu.add(MenuActions.EXIT);
		}
		JMenu borrowManageMenu = new JMenu(); // ���Ĺ���
		borrowManageMenu.setText("���Ĺ���");
		borrowManageMenu.setHorizontalTextPosition(SwingConstants.CENTER);
		borrowManageMenu.setFont(new Font("����", Font.PLAIN, 20));
		borrowManageMenu.add(MenuActions.BORROW); // ����
		borrowManageMenu.add(MenuActions.GIVE_BACK); // �黹
				
		JMenu selectManageMenu = new JMenu(); // ���Ĺ���
		 selectManageMenu.setText("��ѯ��ͳ��");
		 selectManageMenu.setHorizontalTextPosition(SwingConstants.CENTER);
		 selectManageMenu.setFont(new Font("����", Font.PLAIN, 20));
		 selectManageMenu.add(MenuActions.BOOK_SEARCH); // ����
		 selectManageMenu.add(MenuActions.BOOK_SELECT); // ��ѯ
		 selectManageMenu.add(MenuActions.BOOK_COUNT); // ͳ��
		

		JMenu sysManageMenu = new JMenu(); // ϵͳά��
		sysManageMenu.setText("�û�����");
		sysManageMenu.setHorizontalTextPosition(SwingConstants.CENTER);
		sysManageMenu.setFont(new Font("����", Font.PLAIN, 20));
		sysManageMenu.add(MenuActions.USER_ADD);
		sysManageMenu.add(MenuActions.USER_CHANGE);
		menubar.add(baseMenu); // ��ӻ�������ά���˵����˵���
		menubar.add(borrowManageMenu); // ��ӽ��Ĺ���˵����˵���
		menubar.add(selectManageMenu);
		menubar.add(sysManageMenu); // ���ϵͳά���˵����˵���
		return menubar;
	}
	
}
