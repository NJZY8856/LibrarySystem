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
		setIconifiable(true);							// ���ô������С������������
		setClosable(true);
		setTitle("ͼ��������");
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
		label_0.setText("���ţ�");
		panel_3.add(label_0);
		bookTypeId = new JTextField();
		bookTypeId.setDocument(new MyDocument(20));
		bookTypeId.setColumns(30);
		panel_3.add(bookTypeId);
		
		final JLabel label_2 = new JLabel();
		label_2.setPreferredSize(new Dimension(160, 20));
		label_2.setText("�������");
		panel_3.add(label_2);

		bookTypeName = new JTextField();
		bookTypeName.setDocument(new MyDocument(20));
		bookTypeName.setColumns(30);
		panel_3.add(bookTypeName);

		final JLabel label_3 = new JLabel();
		label_3.setPreferredSize(new Dimension(160, 20));
		label_3.setText("�� �� �� ����");
		panel_3.add(label_3);

		days = new JFormattedTextField(NumberFormat.getIntegerInstance());
		days.setColumns(30);
		days.setValue(3);
		panel_3.add(days);
        
		final JLabel label_5 = new JLabel();
		label_5.setPreferredSize(new Dimension(160, 20));
		label_5.setText("����ٻ�һ��ķ���������");
		panel_3.add(label_5);

		fakuan = new JTextField("��λΪԪ");
		fakuan.setColumns(30);
		  fakuan.addKeyListener(new NumberListener());
		  
		
		panel_3.add(fakuan);

		final JButton button = new JButton();
		button.setText("����");
		button.addActionListener(new ActionListener(){
			public void actionPerformed(final ActionEvent e) {
				if(bookTypeName.getText().length()==0){
					JOptionPane.showMessageDialog(null, "ͼ������ı��򲻿�Ϊ��");
					return;
				}
				if(days.getText().length()==0){
					JOptionPane.showMessageDialog(null, "�ɽ������ı��򲻿�Ϊ��");
					return;
				}
				if(fakuan.getText().length()==0||fakuan.getText().trim().equals("��λΪ��")){
					JOptionPane.showMessageDialog(null, "�����ı��򲻿�Ϊ��");
					return;
				}
				boolean i=Dao.InsertBookTypes(bookTypeId.getText().trim(),bookTypeName.getText().trim(), days.getText().trim(),fakuan.getText().trim());
				if(i==false){
					JOptionPane.showMessageDialog(null, "��ӳɹ���");
					doDefaultCloseAction();
				}
			}
		});
		panel_3.add(button);

		final JButton buttonDel = new JButton();
		buttonDel.setText("�ر�");
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
