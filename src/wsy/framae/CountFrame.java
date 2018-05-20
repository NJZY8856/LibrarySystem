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
import wsy.framae.BookSlectFrame.CloseActionListener;
import wsy.jcompz.MapPz;
import wsy.model.Reader;

public class CountFrame extends JInternalFrame{
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
	
	private Object[][] getselect(List list) {
		Object[][] s = new Object[list.size()][10];
		for (int i = 0; i < list.size(); i++) {
			Reader reader = (Reader) list.get(i);
			s[i][0] = reader.getreaderId();
			s[i][1] = reader.getName();
			s[i][2] = reader.getClasses();
			s[i][3] = reader.getMaxNum();
			s[i][4] = reader.getjieshuNum();
			System.out.println(reader.getjieshuNum());
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

	 public CountFrame() {
		super();
		setIconifiable(true);
		setClosable(true);
		setTitle("借书统计");
		setBounds(100, 100, 500, 400);
		
		final JPanel panel_2 = new JPanel();        
		 scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(450, 250));
		panel_2.add(scrollPane);
		
		Object[][] results=getselect(Dao.jieshuCount());
		String readersearch[] = { "学号", "姓名", "班级","最大借书数量" ,"借书数量"};
		table_1 = new JTable(results,readersearch);
		scrollPane.setViewportView(table_1);
		setVisible(true);
		
		final JButton button_1 = new JButton();
		button_1.setText("退出");
		panel_2.add(button_1);
		button_1.addActionListener(new CloseActionListener());	
		setVisible(true);
		
		getContentPane().add(panel_2);
		}	
	 
	class CloseActionListener implements ActionListener {			// 添加关闭按钮的事件监听器
		public void actionPerformed(final ActionEvent e) {
			doDefaultCloseAction();
		}
	}
	
}
