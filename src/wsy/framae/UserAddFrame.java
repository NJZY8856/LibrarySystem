package wsy.framae;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import wsy.dao.Dao;
import wsy.util.MyDocument;

public class UserAddFrame extends JInternalFrame {

	private JTextField textField_5;
	private JTextField textField_4;
	private JFormattedTextField textField_3;
	private JTextField textField_2;
	private JTextField textField_1;
	private JTextField textField;
	private JTextField textField_7;
	private  JPasswordField textField_6;
	private  JButton button;
	private ButtonGroup buttonGroup = new ButtonGroup();
	final JRadioButton radioButton1,radioButton2;
	
	/**
	 * Create the frame
	 */
	public UserAddFrame() {
		super();
		
		
		setIconifiable(true);
		setClosable(true);
		setTitle("����Ա��Ϣ����");
		setBounds(120, 120, 450, 320);

		final JPanel panel = new JPanel();
		getContentPane().add(panel);

		  button = new JButton();
		button.setText("����");
		panel.add(button);
		button.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource()==button){
					if(textField.getText().length()==0){
						JOptionPane.showMessageDialog(null, "��������Ϊ��");
						return;
					}
					if(textField.getText().length()>12){
						JOptionPane.showMessageDialog(null, "�û���λ�����ܴ���ʮ��λ");
						return;
					}
					
					if(textField_2.getText().length()==0){
						JOptionPane.showMessageDialog(null, "���䲻��Ϊ��");
						return;
					}
					if(textField_4.getText().length()==0){
						JOptionPane.showMessageDialog(null, "�绰����Ϊ��");
						return;
					}
					if(textField_4.getText().length()!=11){
						JOptionPane.showMessageDialog(null, "�绰�ű�����ʮһλ");
						return;
					}
					if(textField_6.getText().length()==0){
						JOptionPane.showMessageDialog(null, "���벻��Ϊ��");
						return;
					}
					if(textField_6.getText().length()>15){
						JOptionPane.showMessageDialog(null, "���벻�ܴ���ʮ��λ");
						return;
					}		
				String operaterId=textField_7.getText();
				String username=textField.getText();
				String identityCard=textField_5.getText();
				int age=Integer.parseInt(textField_2.getText());
				String tel=textField_4.getText();
				String password=textField_6.getText();
				String sex="��";
				
				if(!radioButton1.isSelected()){
					sex="Ů";
					}
				int i=Dao.Insertoperator(operaterId,username,sex,age,identityCard,tel,password);
				if(i==1){
					JOptionPane.showMessageDialog(null, "���ӳɹ���");
					doDefaultCloseAction();
				}
			}}
			
			
		});
		final JButton button_1 = new JButton();
		button_1.setText("ȡ��");
		panel.add(button_1);
       button_1.addActionListener(new CloseActionListener());	
		setVisible(true);
		
		
		final JPanel panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(0, 250));
		getContentPane().add(panel_1, BorderLayout.NORTH);

		final JPanel panel_2 = new JPanel();
		final GridLayout gridLayout = new GridLayout(0, 2);
		gridLayout.setVgap(10);
		panel_2.setLayout(gridLayout);
		panel_2.setPreferredSize(new Dimension(280, 200));
		panel_1.add(panel_2);
		
		final JLabel label_7 = new JLabel();
		label_7.setText("����Ա��ţ�");
		panel_2.add(label_7);

		textField_7 = new JTextField();
		textField_7.addKeyListener(new NumberListener());
		
		panel_2.add(textField_7);

		final JLabel label = new JLabel();
		label.setText("������");
		panel_2.add(label);
		textField = new JTextField();				
		panel_2.add(textField);
		
		final JLabel label_5 = new JLabel();
		label_5.setText("��    �룺");
		panel_2.add(label_5);

		textField_6 = new  JPasswordField();
		panel_2.add(textField_6);
		setVisible(true);

		final JLabel label_1 = new JLabel();
		label_1.setText("��    ��");
		panel_2.add(label_1);

		final JPanel label_8 = new JPanel();
		final FlowLayout flowLayout = new FlowLayout();
		flowLayout.setHgap(0);
		flowLayout.setVgap(0);
		label_8.setLayout(flowLayout);
		panel_1.add(label_8);

		radioButton1 = new JRadioButton();
		label_8.add(radioButton1);
		radioButton1.setSelected(true);
		buttonGroup.add(radioButton1);
		radioButton1.setText("��");
		radioButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		 radioButton2 = new JRadioButton();
		label_8.add(radioButton2);
		radioButton2.setSelected(true);
		buttonGroup.add(radioButton2);
		radioButton2.setText("Ů");
		panel_2.add(label_8);
		
		final JLabel label_3 = new JLabel();
		label_3.setText("����֤�ţ�");
		panel_2.add(label_3);

		textField_5 = new JTextField();
		textField_5.addKeyListener(new NumberListener());
		
		panel_2.add(textField_5);

		final JLabel label_2 = new JLabel();
		label_2.setText("��    �䣺");
		panel_2.add(label_2);

		textField_2 = new JTextField();
		 textField_2.setDocument(new MyDocument(2)); 
			
			textField_2.setColumns(2);
			textField_2.addKeyListener(new NumberListener());
		
		panel_2.add(textField_2);

		final JLabel label_4 = new JLabel();
		label_4.setText("�ֻ��ţ�");
		panel_2.add(label_4);

		textField_4 = new JTextField("�绰�ű�����11λ",11);		
        textField_4.setDocument(new MyDocument(11)); 
		
		textField_4.setColumns(11);
		textField_4.addKeyListener(new NumberListener());
		panel_2.add(textField_4);   
		
		
	}
	
	
	class CloseActionListener implements ActionListener {			// ���ӹرհ�ť���¼�������
		public void actionPerformed(final ActionEvent e) {
			doDefaultCloseAction();
		
	}}
	}
class NumberListener extends KeyAdapter {
	public void keyTyped(KeyEvent e) {
		String numStr="0123456789."+(char)8;
		if(numStr.indexOf(e.getKeyChar())<0){
			e.consume();
		}
	}
}
