package wsy.framae;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import wsy.dao.Dao;
import wsy.jcompz.MapPz;
import wsy.model.BookInfo;
import wsy.model.Reader;

public class BookSlectFrame extends JInternalFrame{
	private JTextField textField_1;

	private JComboBox comboBox_1;

	private JTable table_1, table_2;

	private JComboBox choice;

	private JTextField textField_2;

	private JScrollPane scrollPane, scrollPane_1;
	private Map m=MapPz.getMap();


	/**
	 * Launch the application
	 * 
	 * @param args
	 */

	/**
	 * Create the frame
	 */
	String readersearch[] = { "学号", "姓名",  "班级", "性别",  "年龄", "电话","最大借书量" };
	private Object[][] getselect(List list) {
		Object[][] s = new Object[list.size()][10];
		for (int i = 0; i < list.size(); i++) {
			Reader reader = (Reader) list.get(i);
			s[i][0] = reader.getreaderId();
			s[i][1] = reader.getName();
			s[i][2] = reader.getClass();
			s[i][3] =reader.getSex();
			s[i][4] =reader.getAge();
			s[i][5] =reader.getPhone();
			s[i][6] = reader.getMaxNum();
		}
		return s;

	}

	private Object[] getselectid(List list) {
		Object[] ids = new Object[list.size()];
		for (int i = 0; i < list.size(); i++) {
			Reader reader= new Reader();
			ids[i] = reader.getreaderId();
		}
		return ids;
	}

	 public BookSlectFrame() {
		super();
		setIconifiable(true);
		setClosable(true);
		setTitle("读者查询");
		setBounds(100, 100, 500, 400);

		
		setVisible(true);

		final JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setPreferredSize(new Dimension(0, 50));
		getContentPane().add(tabbedPane);
		final JPanel panel_1 = new JPanel();
		panel_1.setLayout(new BorderLayout());
		tabbedPane.addTab("条件查询", null, panel_1, null);

		final JPanel panel_1_1 = new JPanel();
		panel_1_1.setBorder(new TitledBorder(null, "请选择查询项目", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		panel_1_1.setPreferredSize(new Dimension(0, 50));
		panel_1.add(panel_1_1, BorderLayout.NORTH);
         choice=new JComboBox();
		String[] array={"学号","姓名"};
		for(int i=0;i<array.length;i++){
			choice.addItem(array[i]);
			
		}
		panel_1_1.add(choice);
		textField_1 = new JTextField();
		textField_1.setColumns(20);
		panel_1_1.add(textField_1);
        
		final JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "查询结果显示", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		panel_1.add(panel);

		
		 scrollPane_1 = new JScrollPane();
		scrollPane_1.setPreferredSize(new Dimension(400, 200));
		panel.add(scrollPane_1);

		final JPanel panel_2_1 = new JPanel();
		panel_2_1.setPreferredSize(new Dimension(0, 50));
		panel_1.add(panel_2_1, BorderLayout.SOUTH);

		final JButton button = new JButton();
		button.setText("查询");
		panel_2_1.add(button);

		 button.addActionListener(new ActionListener(){

				public void actionPerformed(ActionEvent arg0) {
					String name=(String)choice.getSelectedItem();
					if(name.equals("学号")){				
						Object[][] results=getselect(Dao.selectreaderasId(textField_1.getText()));
						table_2 = new JTable(results,readersearch);
						scrollPane_1.setViewportView(table_2);
					}
					else if(name.equals("姓名")){
						
						Object[][] results=getselect(Dao.selectreaderasname(textField_1.getText()));
						table_2 = new JTable(results,readersearch);
						
						scrollPane_1.setViewportView(table_2);
					}
				}
	        	
	        	
	        });
		
		
		final JButton button_1 = new JButton();
		button_1.setText("退出");
		panel_2_1.add(button_1);
		button_1.addActionListener(new CloseActionListener());
		
		setVisible(true);
		
		final JPanel panel_2 = new JPanel();
		tabbedPane.addTab("显示图书全部信息", null, panel_2, null);
         
		 scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(450, 250));
		panel_2.add(scrollPane);
		
		Object[][] results=getselect(Dao.selectReader());
		String [] booksearch = { "学号", "姓名",  "班级", "性别",  "年龄", "电话","最大借书量"};
		table_1 = new JTable(results,booksearch);
		scrollPane.setViewportView(table_1);
		}
	class CloseActionListener implements ActionListener {			// 添加关闭按钮的事件监听器
		public void actionPerformed(final ActionEvent e) {
			doDefaultCloseAction();
		}
	}
}


