package wsy.framae;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

import wsy.jcompz.MapPz;
import wsy.dao.Dao;
import wsy.model.BookInfo;
import wsy.model.BookType;
import wsy.model.Operater;
import wsy.model.Reader;
import wsy.util.MyDocument;

public class BookBorrowFrame extends JInternalFrame {
	private Operater user = LoginFrame.getUser(); 
	
	private final JTextField operator;

	private JTextField todaydate;

	private JTable table;

	private JTextField price;

	private JTextField bookType;

	private JTextField bookName;

	private JTextField bookId;

	private JTextField keepMoney;

	private JTextField number;

	private JTextField readerName;

	private JTextField readerId;

//	private String[] columnNames = { "图书编号","学号", "借书日期", "应还日期" };

	private Map map = MapPz.getMap();

	//private static int i = 1;

	DefaultTableModel model = new DefaultTableModel();
	SimpleDateFormat myfmt=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

	/**
	 * Create the frame
	 */

	public BookBorrowFrame() {
		super();
		System.out.println(user.getOperaterName());
		setTitle("图书借阅管理");
		setIconifiable(true); // 设置窗体可最小化－－－必须
		setClosable(true); // 设置窗体可关闭－－－必须
		setBounds(100, 100, 500, 300);

		final JPanel panel = new JPanel();
		getContentPane().add(panel);

		final JPanel panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(0, 120));
		getContentPane().add(panel_1, BorderLayout.NORTH);

		final JSplitPane splitPane = new JSplitPane();
		panel_1.add(splitPane);

		final JPanel panel_3 = new JPanel();
		panel_3.setPreferredSize(new Dimension(240, 110));
		splitPane.setLeftComponent(panel_3);

		final JPanel panel_5 = new JPanel();
		final GridLayout gridLayout = new GridLayout(0, 2);
		gridLayout.setHgap(2);
		gridLayout.setVgap(10);
		panel_5.setLayout(gridLayout);
		panel_5.setPreferredSize(new Dimension(150, 100));
		panel_3.add(panel_5);

		final JLabel label = new JLabel();
		label.setText("学号：");
		panel_5.add(label);

		readerId = new JTextField();
		readerId.setDocument(new MyDocument(13));
		readerId.addKeyListener(new ISBNListenerlostFocus());
		panel_5.add(readerId);

		final JLabel label_1 = new JLabel();
		label_1.setText("姓名：");
		panel_5.add(label_1);

		readerName = new JTextField();
		readerName.setEditable(false);
		panel_5.add(readerName);

		final JLabel label_2 = new JLabel();
		label_2.setText("可借数量：");
		panel_5.add(label_2);

		number = new JTextField();
		number.setEditable(false);
		panel_5.add(number);

		final JPanel panel_4 = new JPanel();
		final GridLayout gridLayout_1 = new GridLayout(0, 2);
		gridLayout_1.setVgap(10);
		panel_4.setLayout(gridLayout_1);
		panel_4.setPreferredSize(new Dimension(240, 110));
		splitPane.setRightComponent(panel_4);

		final JLabel label_5 = new JLabel();
		label_5.setText("图书编号：");
		panel_4.add(label_5);

		bookId = new JTextField();
		bookId.setDocument(new MyDocument(13));
		bookId.addKeyListener(new bookISBNListenerlostFocus());
		panel_4.add(bookId);

		final JLabel label_6 = new JLabel();
		label_6.setText("书名：");
		panel_4.add(label_6);

		bookName = new JTextField();
		bookName.setEditable(false);
		panel_4.add(bookName);

		final JLabel label_7 = new JLabel();
		label_7.setText("类别：");
		panel_4.add(label_7);

		bookType = new JTextField();
		bookType.setEditable(false);
		panel_4.add(bookType);

		final JPanel panel_2 = new JPanel();
		panel_2.setPreferredSize(new Dimension(0, 100));
		getContentPane().add(panel_2, BorderLayout.SOUTH);

		final JPanel panel_7 = new JPanel();
		final GridLayout gridLayout_2 = new GridLayout(0, 2);
		gridLayout_2.setVgap(10);
		panel_7.setLayout(gridLayout_2);
		panel_7.setPreferredSize(new Dimension(280, 50));
		panel_2.add(panel_7);

		final JLabel label_9 = new JLabel();
		label_9.setText("当前时间：");
		panel_7.add(label_9);

		todaydate = new JTextField();
		todaydate.setEditable(false);
		todaydate.setPreferredSize(new Dimension(0, 0));
		todaydate.addActionListener(new TimeActionListener());
		todaydate.setFocusable(false);
		panel_7.add(todaydate);

		final JLabel label_11 = new JLabel();
		label_11.setText("操作员：");
		panel_7.add(label_11);

		operator =new JTextField(user.getOperaterName());
		operator.setEditable(false);
		panel_7.add(operator);

		final JPanel panel_8 = new JPanel();
		panel_8.setLayout(new FlowLayout());
		panel_8.setPreferredSize(new Dimension(200, 60));
		panel_2.add(panel_8);

		final JButton buttonBorrow = new JButton();
		buttonBorrow.setText("借出当前图书");
		buttonBorrow.addActionListener(new BorrowActionListener());
		panel_8.add(buttonBorrow);
		setVisible(true);
	}

	class bookISBNListenerlostFocus extends KeyAdapter {
		public void keyTyped(KeyEvent e) {
			if (e.getKeyChar() == '\n') { // 判断在文本框是否输入回车。
				if (readerId.getText().trim().length()!=0
						&& bookId.getText().trim().length()!=0) {
					String ISBNs = bookId.getText().trim();
					List list = Dao.SelectbookInfo(ISBNs);
					for (int i = 0; i < list.size(); i++) {
						BookInfo book = (BookInfo) list.get(i);
						bookName.setText(book.getBookname());
						bookType.setText(String.valueOf(map.get(book.getTypeId())));
						price.setText(String.valueOf(book.getPrice()));
					}
					String days = "0";
					List list2 = Dao.selectBookType(bookType.getText()
							.trim());
					for (int j = 0; j < list2.size(); j++) {
						BookType type = (BookType) list2.get(j);
						days = type.getDays();
					}
					String readerISBNs = readerId.getText().trim();
					List list5 = Dao.selectReader(readerISBNs);// 此读者是否在bookReader表中
					List list4 = Dao.SelectbookInfo(ISBNs);// 此书是否在bookInfo表中
					if (!readerISBNs.isEmpty() && list5.isEmpty()) {
						JOptionPane.showMessageDialog(null,
								"没有此学生，查询输入的学号是否有误！");
						return;
					}
					if (list4.isEmpty() && !ISBNs.isEmpty()) {
						JOptionPane.showMessageDialog(null,
								"图书馆没有此书，查询输入图书编号是否有误！");
						return;
					}
					if (Integer.parseInt(number.getText().trim()) <= 0) {
						JOptionPane.showMessageDialog(null, "借书量已经超过最大借书量！");
						return;
					}

				//	add();
					number.setText(String.valueOf(Integer.parseInt(number
							.getText().trim()) - 1));
				}

				else
					JOptionPane.showMessageDialog(null, "请输入学号！");
			}

		}
	}

	class ISBNListenerlostFocus extends KeyAdapter {
		public void keyTyped(KeyEvent e) {
			if (e.getKeyChar() == '\n') { // 判断在文本框是否输入回车。
				String ISBNs = readerId.getText().trim();

				List list = Dao.selectReader(ISBNs);
				if (list.isEmpty() && !ISBNs.isEmpty()) {
					JOptionPane.showMessageDialog(null,
							"此学生没有注册，查询输入学号是否有误！");
				}
				for (int i = 0; i < list.size(); i++) {
					Reader reader = (Reader) list.get(i);
					readerName.setText(reader.getName());
					number.setText(reader.getMaxNum());
				//	keepMoney.setText(reader.getKeepMoney() + "");
					System.out.println("读者可借书量" + number.getText().trim());
				}
			}
		}
	}

	class BorrowActionListener implements ActionListener { 
		public void actionPerformed(final ActionEvent e) {

			String bookISBNs=bookId.getText().trim();
			System.out.println(bookISBNs);
			String readerISBNs=readerId.getText().trim();
			System.out.println(readerISBNs);
			int isback=0;
			String bookNames=bookName.getText().trim();
			String operatorId=user.getOperaterId();
			String borrowDate=myfmt.format(new java.util.Date());
			int i=Dao.InsertBookBorrow(bookISBNs, readerISBNs,isback,java.sql.Timestamp.valueOf(borrowDate),null,operatorId);
			if(i==1){
				JOptionPane.showMessageDialog(null, "图书借阅完成！");
				doDefaultCloseAction();
			}
			else{
				JOptionPane.showMessageDialog(null, "该书没有库存了！");
				doDefaultCloseAction();
			}
		}
	}

	class TimeActionListener implements ActionListener{
		public TimeActionListener(){
			Timer t=new Timer(1000,this);
			t.start();
		}
		public void actionPerformed(ActionEvent ae){
			todaydate.setText(myfmt.format(new java.util.Date()).toString());
		}
	}
}

