package wsy.framae;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import wsy.dao.Dao;
import wsy.model.Reader;
import wsy.util.MyDocument;

public class ReaderChangeFrame extends JInternalFrame {


	private JTextField buju;
	private ButtonGroup buttonGroup = new ButtonGroup();
	private JTable table;
	private JTextField classes;
	private JTextField tel;
	private JTextField maxnumber;
	private JTextField readerid;
	private JComboBox comboBox;
	private JTextField age;
	private JTextField readername;
	private JRadioButton JRadioButton1;
	private JRadioButton JRadioButton2;
	private String[] columnNames={ "学号", "姓名", "班级", "性别","年龄","电话","最大借书量"};
	String id;
	
	/**
	 * Create the frame
	 */
	private Object[][] getFileStates(List list){
		Object[][]results=new Object[list.size()][columnNames.length];
		for(int i=0;i<list.size();i++){
			Reader reader=(Reader)list.get(i);
			results[i][0]=reader.getreaderId();
			results[i][1]=reader.getName();
			results[i][2]=reader.getClasses();
			results[i][3]=reader.getSex();
			results[i][4]=reader.getAge();
			results[i][5]=reader.getPhone();
			results[i][6]=reader.getMaxNum();
		}
		return results;
	         		
	}
	public ReaderChangeFrame() {
		super();
		setIconifiable(true);
		setClosable(true);
		setTitle("读者信息修改与删除");
		setBounds(100, 100, 550, 380);

		final JPanel panel_1 = new JPanel();
		panel_1.setLayout(new BorderLayout());
		getContentPane().add(panel_1);

		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(0, 100));
		panel_1.add(scrollPane, BorderLayout.NORTH);

		
		final DefaultTableModel model=new DefaultTableModel();
		Object[][] results=getFileStates(Dao.selectReader());
		model.setDataVector(results,columnNames);
		
		table = new JTable();
		table.setModel(model);
		scrollPane.setViewportView(table);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.addMouseListener(new TableListener());
		
		final JPanel panel_2 = new JPanel();
		final GridLayout gridLayout = new GridLayout(0, 4);
		gridLayout.setVgap(9);
		panel_2.setLayout(gridLayout);
		panel_2.setPreferredSize(new Dimension(0, 200));
		panel_1.add(panel_2, BorderLayout.SOUTH);
		
		final JLabel label_6 = new JLabel();
		label_6.setText("  学    号：");
		panel_2.add(label_6);

		readerid = new JTextField();
		readerid.setDocument(new MyDocument(13));
		readerid.addKeyListener(new NumberListener());
		panel_2.add(readerid);

		final JLabel label_1 = new JLabel();
		label_1.setText("  姓    名：");
		panel_2.add(label_1);
		
		readername = new JTextField();
		readername.setDocument(new MyDocument(10));
		panel_2.add(readername);
		
		final JLabel label_5 = new JLabel();
		label_5.setText("   班    级：");
		panel_2.add(label_5);

		classes = new JTextField();
		classes.setDocument(new MyDocument(30));
		panel_2.add(classes);

		final JLabel label_2 = new JLabel();
		label_2.setText("  性    别：");
		panel_2.add(label_2);

		final JPanel panel_3 = new JPanel();
		final FlowLayout flowLayout_1 = new FlowLayout();
		flowLayout_1.setVgap(0);
		panel_3.setLayout(flowLayout_1);
		panel_2.add(panel_3);

		JRadioButton1 = new JRadioButton();
		JRadioButton1.setSelected(true);
		buttonGroup.add(JRadioButton1);
		panel_3.add(JRadioButton1);
		JRadioButton1.setText("男");

		JRadioButton2 = new JRadioButton();
		buttonGroup.add(JRadioButton2);
		panel_3.add(JRadioButton2);
		JRadioButton2.setText("女");

		final JLabel label_3 = new JLabel();
		label_3.setText("  年    龄：");
		panel_2.add(label_3);

		age = new JTextField();
		age.setDocument(new MyDocument(2));
		age.addKeyListener(new NumberListener());
		panel_2.add(age);

		final JLabel label_8 = new JLabel();
		label_8.setText("  电    话：");
		panel_2.add(label_8);

		tel = new JFormattedTextField();
		tel.addKeyListener(new TelListener());
		tel.setDocument(new MyDocument(11));
		panel_2.add(tel);
		
		final JLabel label_9 = new JLabel();
		label_9.setText("  最大借书量：");
		panel_2.add(label_9);

		maxnumber = new JTextField();
		maxnumber.addKeyListener(new NumberListener());
		panel_2.add(maxnumber);

		final JLabel label_14 = new JLabel();
		label_14.setText("");
		label_14.setVisible(false);
		panel_2.add(label_14);

		buju = new JTextField();
		buju.addKeyListener(new KeepmoneyListener());
		buju.setVisible(false);
		panel_2.add(buju);

		final JPanel panel_4 = new JPanel();
		panel_4.setMaximumSize(new Dimension(0, 0));
		final FlowLayout flowLayout = new FlowLayout();
		flowLayout.setVgap(0);
		flowLayout.setHgap(4);
		panel_4.setLayout(flowLayout);
		panel_2.add(panel_4);

		final JButton button = new JButton();
		button.setHorizontalTextPosition(SwingConstants.CENTER);
		panel_4.add(button);
		button.setText("修改");
		button.addActionListener(new ModiButtonListener(model));
	
		final JButton buttonDel = new JButton();
		panel_4.add(buttonDel);
		buttonDel.setText("删除");
		buttonDel.addActionListener(new DelButtonListener(model));
		setVisible(true);
	}
	class TableListener extends MouseAdapter {
		public void mouseClicked(final MouseEvent e) {
			
			int selRow = table.getSelectedRow();
			readerid.setText(table.getValueAt(selRow, 0).toString().trim());
			readername.setText(table.getValueAt(selRow, 1).toString().trim());
			classes.setText(table.getValueAt(selRow, 2).toString().trim());
			if(table.getValueAt(selRow, 3).toString().trim().equals("男"))
				JRadioButton1.setSelected(true);
			else
				JRadioButton2.setSelected(true);
			age.setText(table.getValueAt(selRow, 4).toString().trim());
			tel.setText(table.getValueAt(selRow,5).toString().trim());
			maxnumber.setText(table.getValueAt(selRow,6).toString().trim());
			
		}
	}     
	final class NumberListener extends KeyAdapter {
		public void keyTyped(KeyEvent e) {
			String numStr="0123456789"+(char)8;
			if(numStr.indexOf(e.getKeyChar())<0){
				e.consume();
			}
		}
	}
	private final class DelButtonListener implements ActionListener {
		private final DefaultTableModel model;

		private DelButtonListener(DefaultTableModel model) {
			this.model = model;
		}

		public void actionPerformed(final ActionEvent e) {
			int i=Dao.DeleteReader(readerid.getText().trim());
			if(i==1){
				JOptionPane.showMessageDialog(null, "删除成功");
				Object[][] results=getFileStates(Dao.selectReader());
				model.setDataVector(results,columnNames);
				table.setModel(model);
			}
		}
	}
	class ModiButtonListener implements ActionListener {
		private final DefaultTableModel model;

		ModiButtonListener(DefaultTableModel model) {
			this.model = model;
		}

		public void actionPerformed(final ActionEvent e) {
			if(readername.getText().length()==0){
				JOptionPane.showMessageDialog(null, "读者姓名文本框不可为空");
				return;
			}
			if(age.getText().length()==0){
				JOptionPane.showMessageDialog(null, "读者年龄文本框不可为空");
				return;
			}
			
			if(classes.getText().length()==0){
				JOptionPane.showMessageDialog(null, "班级文本框不可为空");
				return;
			}
			
			if(readerid.getText().length()==0){
				JOptionPane.showMessageDialog(null, "学号文本框不可为空");
				return;
			}
			if(readerid.getText().length()!=11){
				JOptionPane.showMessageDialog(null, "学号文本框为11位");
				return;
			}

			if(tel.getText().length()==0){
				JOptionPane.showMessageDialog(null, "电话号码文本框不可为空");
				return;
			}
			if(tel.getText().length()>11||tel.getText().length()<0){
				JOptionPane.showMessageDialog(null, "电话号码位数小于11位");
				return;
			}
			if(maxnumber.getText().length()==0){
				JOptionPane.showMessageDialog(null, "最大借书量文本框不可为空");
				return;
			}
			if(tel.getText().length()<0){
				JOptionPane.showMessageDialog(null, "最大借书量不能为空");
				return;
			}
			String sex="1";
			if(!JRadioButton1.isSelected()){
				sex="2";}
			int i=Dao.UpdateReader(readerid.getText().trim(), readername.getText().trim(), classes.getText().trim(),sex, age.getText().trim(), tel.getText().trim(),maxnumber.getText().trim());
			System.out.println(i);
			if(i==1){
				JOptionPane.showMessageDialog(null, "修改成功");
				Object[][] results=getFileStates(Dao.selectReader());
				model.setDataVector(results,columnNames);
				table.setModel(model);
			}
		}
	}
	class TelListener extends KeyAdapter {
		public void keyTyped(KeyEvent e) {
			String numStr="0123456789-"+(char)8;
			if(numStr.indexOf(e.getKeyChar())<0){
				e.consume();
			}
		}
	}
	class KeepmoneyListener extends KeyAdapter {
		public void keyTyped(KeyEvent e) {
			String numStr="0123456789"+(char)8;//只允许输入数字与退格键
			if(numStr.indexOf(e.getKeyChar())<0){
				e.consume();
			}
		}
	}

}

