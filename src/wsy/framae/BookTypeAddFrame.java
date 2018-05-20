package wsy.framae;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import wsy.dao.Dao;
import wsy.util.MyDocument;

public class BookTypeAddFrame extends JInternalFrame {

	private JTextField bookTypeId;
	private JFormattedTextField days;
	private JTextField bookTypeName;
	private JTextField fakuan;
	
	/**
	 * Create the frame
	 */
	public BookTypeAddFrame() {
		super();
		setIconifiable(true);							// 设置窗体可最小化－－－必须
		setClosable(true);
		setTitle("图书类别添加");
		setBounds(100, 100, 500, 300);

		final JPanel panel_2 = new JPanel();
		panel_2.setPreferredSize(new Dimension(100, 0));
		getContentPane().add(panel_2, BorderLayout.WEST);

		final JLabel label = new JLabel();
		panel_2.add(label);

		final JPanel panel_3 = new JPanel();
		panel_3.setLayout(new FlowLayout());
		getContentPane().add(panel_3, BorderLayout.CENTER);

		final JLabel label_1 = new JLabel();
		label_1.setPreferredSize(new Dimension(390, 50));
		panel_3.add(label_1);
		
		final JLabel label_0=new JLabel();
		label_0.setPreferredSize(new Dimension(160,20));
		label_0.setText("类别号：");
		panel_3.add(label_0);
		bookTypeId = new JTextField();
		bookTypeId.setDocument(new MyDocument(20));
		bookTypeId.setColumns(30);
		panel_3.add(bookTypeId);
		
		final JLabel label_2 = new JLabel();
		label_2.setPreferredSize(new Dimension(160, 20));
		label_2.setText("类别名：");
		panel_3.add(label_2);

		bookTypeName = new JTextField();
		bookTypeName.setDocument(new MyDocument(20));
		bookTypeName.setColumns(30);
		panel_3.add(bookTypeName);

		final JLabel label_3 = new JLabel();
		label_3.setPreferredSize(new Dimension(160, 20));
		label_3.setText("可 借 天 数：");
		panel_3.add(label_3);

		days = new JFormattedTextField(NumberFormat.getIntegerInstance());
		days.setColumns(30);
		days.setValue(3);
		panel_3.add(days);
        
		final JLabel label_5 = new JLabel();
		label_5.setPreferredSize(new Dimension(160, 20));
		label_5.setText("罚款（迟还一天的罚款数）：");
		panel_3.add(label_5);

		fakuan = new JTextField("单位为元");
		fakuan.setColumns(30);
		  fakuan.addKeyListener(new NumberListener());
		  
		
		panel_3.add(fakuan);

		final JButton button = new JButton();
		button.setText("保存");
		button.addActionListener(new ActionListener(){
			public void actionPerformed(final ActionEvent e) {
				if(bookTypeName.getText().length()==0){
					JOptionPane.showMessageDialog(null, "图书类别文本框不可为空");
					return;
				}
				if(days.getText().length()==0){
					JOptionPane.showMessageDialog(null, "可借天数文本框不可为空");
					return;
				}
				if(fakuan.getText().length()==0||fakuan.getText().trim().equals("单位为角")){
					JOptionPane.showMessageDialog(null, "罚款文本框不可为空");
					return;
				}
				boolean i=Dao.InsertBookTypes(bookTypeId.getText().trim(),bookTypeName.getText().trim(), days.getText().trim(),fakuan.getText().trim());
				if(i==false){
					JOptionPane.showMessageDialog(null, "添加成功！");
					doDefaultCloseAction();
				}
			}
		});
		panel_3.add(button);

		final JButton buttonDel = new JButton();
		buttonDel.setText("关闭");
		buttonDel.addActionListener(new ActionListener(){
			public void actionPerformed(final ActionEvent e) {
				doDefaultCloseAction();
			}
		});
		panel_3.add(buttonDel);
		setVisible(true);
		//
	}
	class NumberListener extends KeyAdapter {
		public void keyTyped(KeyEvent e) {
			String numStr="0123456789."+(char)8;
			if(numStr.indexOf(e.getKeyChar())<0){
				e.consume();
			}
		}
	}
}
