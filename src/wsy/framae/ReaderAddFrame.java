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
import java.sql.Date;
import java.text.SimpleDateFormat;

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
import javax.swing.JTextField;

import wsy.dao.Dao;
import wsy.util.MyDocument;

public class ReaderAddFrame extends JInternalFrame {

	private JTextField readerid;
	private ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField tel;
	private JFormattedTextField maxnumber;
	private JComboBox comboBox;
	private JTextField classes;
	private JTextField age;
	private JTextField readername;
	DefaultComboBoxModel comboBoxModel;
	String [] array;	
	
	public ReaderAddFrame() {
		super();
		setTitle("���������Ϣ���");
		setIconifiable(true);							// ���ô������С������������
		setClosable(true);								// ���ô���ɹرգ���������
														// ���ô�����⣭��������
		setBounds(100, 100, 500, 350);

		final JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		getContentPane().add(panel);

		final JPanel panel_1 = new JPanel();
		final GridLayout gridLayout = new GridLayout(0, 4);
		gridLayout.setVgap(15);
		gridLayout.setHgap(10);
		panel_1.setLayout(gridLayout);
		panel_1.setPreferredSize(new Dimension(450, 200));
		panel.add(panel_1);
		
		final JLabel label_7 = new JLabel();
		label_7.setText("ѧ    �ţ�");
		panel_1.add(label_7);

		readerid = new JTextField();
		readerid.setDocument(new MyDocument(13));
		readerid.addKeyListener(new NumberListener());
		panel_1.add(readerid);

		final JLabel label_2 = new JLabel();
		label_2.setText("��    ����");
		panel_1.add(label_2);

		readername = new JTextField();
		readername.setDocument(new MyDocument(10));
		panel_1.add(readername);

		final JLabel label_3 = new JLabel();
		label_3.setText("��    ��");
		panel_1.add(label_3);

		final JPanel label_13 = new JPanel();
		final FlowLayout flowLayout = new FlowLayout();
		flowLayout.setHgap(0);
		flowLayout.setVgap(0);
		label_13.setLayout(flowLayout);
		panel_1.add(label_13);

		final JRadioButton radioButton1 = new JRadioButton();
		label_13.add(radioButton1);
		radioButton1.setSelected(true);
		buttonGroup.add(radioButton1);
		radioButton1.setText("��");

		final JRadioButton radioButton2 = new JRadioButton();
		label_13.add(radioButton2);
		buttonGroup.add(radioButton2);
		radioButton2.setText("Ů");
		


		final JLabel label_4 = new JLabel();
		label_4.setText("��    �䣺");
		panel_1.add(label_4);

		age = new JTextField();
		age.setDocument(new MyDocument(2));//��������ı����������ֵΪ2
		age.addKeyListener(new NumberListener());
		panel_1.add(age);

		final JLabel label_5 = new JLabel();
		label_5.setText("ר	ҵ��");
		panel_1.add(label_5);

		classes = new JTextField();
		classes.setDocument(new MyDocument(30));
		panel_1.add(classes);

		final JLabel label_9 = new JLabel();
		label_9.setText("����������");
		panel_1.add(label_9);
		
		maxnumber = new JFormattedTextField();
		maxnumber.setDocument(new MyDocument(2));
		maxnumber.addKeyListener(new NumberListener());
		panel_1.add(maxnumber);

		final JLabel label_11 = new JLabel();
		label_11.setText("��    ����");
		panel_1.add(label_11);
		

		tel = new JTextField();
		tel.addKeyListener(new TelListener());
		tel.setDocument(new MyDocument(11));

		panel_1.add(tel);

		final JPanel panel_2 = new JPanel();
		panel_2.setPreferredSize(new Dimension(450, 100));
		panel.add(panel_2);

		final JButton save = new JButton();
		panel_2.add(save);
		save.setText("����");
		save.addActionListener(new ButtonAddListener(radioButton1));
		

		final JButton back = new JButton();
		panel_2.add(back);
		back.setText("����");
		back.addActionListener(new CloseActionListener());
		setVisible(true);
	}
	class NumberListener extends KeyAdapter {
		public void keyTyped(KeyEvent e) {
			String numStr="0123456789"+(char)8;
			if(numStr.indexOf(e.getKeyChar())<0){
				e.consume();
			}
		}
	}
	class ButtonAddListener implements ActionListener {
		private final JRadioButton button1;

		ButtonAddListener(JRadioButton button1) {
			this.button1 = button1;
		}

		public void actionPerformed(final ActionEvent e) {
			
			if(readername.getText().length()==0){
				JOptionPane.showMessageDialog(null, "���������ı��򲻿�Ϊ��");
				return;
			}
			if(age.getText().length()==0){
				JOptionPane.showMessageDialog(null, "���������ı��򲻿�Ϊ��");
				return;
			}
			
			if(readerid.getText().length()==0){
				JOptionPane.showMessageDialog(null, "ѧ���ı��򲻿�Ϊ��");
				return;
			}
			if(readerid.getText().length()!=11){
				JOptionPane.showMessageDialog(null, "ѧ��λ��Ϊ11");
				return;
			}

			if(classes.getText().length()==0){
				JOptionPane.showMessageDialog(null, "רҵ�ı��򲻿�Ϊ��");
				return;
			}
			if(tel.getText().length()==0){
				JOptionPane.showMessageDialog(null, "�绰�����ı��򲻿�Ϊ��");
				return;
			}
			if(tel.getText().length()>11||tel.getText().length()<0){
				JOptionPane.showMessageDialog(null, "�绰����λ��С��11λ");
				return;
			}
			if(maxnumber.getText().length()==0){
				JOptionPane.showMessageDialog(null, "���������ı��򲻿�Ϊ��");
				return;
			}
			if(maxnumber.getText().length()>2||tel.getText().length()<0){
				JOptionPane.showMessageDialog(null, "��������Ϊ��λ����");
				return;
			}
			String sex="1";
			if(!button1.isSelected()){
				sex="2";}		
			
			int i=Dao.InsertReader(readerid.getText().trim(),readername.getText().trim(), classes.getText().trim(),sex.trim(), age.getText().trim(),tel.getText().trim(), maxnumber.getText().trim());
			System.out.println(i);
			if(i==1){
				JOptionPane.showMessageDialog(null, "��ӳɹ���");
				doDefaultCloseAction();
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
	class CloseActionListener implements ActionListener {			// ��ӹرհ�ť���¼�������
		public void actionPerformed(final ActionEvent e) {
			doDefaultCloseAction();
		}
	}

}
