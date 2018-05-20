package wsy.framae;

import java.lang.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

//import wsy.frame.LoginFrame;

import wsy.Library;
import wsy.dao.Dao;
import wsy.model.Operater;
import wsy.util.MyDocument;

import java.awt.event.*;

public class LoginFrame extends JFrame{
	private class ResetAction implements ActionListener {
		public void actionPerformed(final ActionEvent e){
			username.setText("");
			password.setText("");
			
		}
	}
	class LoginAction implements ActionListener {
		public void actionPerformed(final ActionEvent e) {
			user = Dao.check(username.getText(), password.getText());
			System.out.println(user.getOperaterName());
			if (user.getOperaterName()!= null) {

				try {

					Library frame = new Library();
					frame.setVisible(true);
					LoginFrame.this.setVisible(false);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(null, "只有管理员才可以登录！");
				username.setText("");
				password.setText("");
			}
		}
	}
	
	private JPasswordField password;
	private JTextField username;
	private JButton login;
	private JButton reset;
	private static Operater user;
	
	public LoginFrame(){
		super();
		final BorderLayout borderlayout=new BorderLayout();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		borderlayout.setVgap(10);//设置组件之间的垂直间距为10px
		getContentPane().setLayout(borderlayout);
		setTitle("图书管理系统登陆");
		setBounds(400,300,270,150);	
		final JPanel panel1=new JPanel();
		panel1.setLayout(new BorderLayout());
		panel1.setBorder(new EmptyBorder(0,0,0,0));
		getContentPane().add(panel1);
		 
		final JPanel panel2 =new JPanel();
		final GridLayout gridlayout=new GridLayout(0,2);
		gridlayout.setHgap(5);
		gridlayout.setVgap(10);
		panel2.setLayout(gridlayout);
		panel1.add(panel2);
		
		final JLabel label1=new JLabel();
		label1.setHorizontalAlignment(SwingConstants.CENTER);
		label1.setPreferredSize(new Dimension(0,0));
		label1.setMinimumSize(new Dimension(0,0));
		panel2.add(label1);
		label1.setText("用户名:");
		username=new JTextField(20);
		username.setPreferredSize(new Dimension(0,0));
		panel2.add(username);
		
		final JLabel label2=new JLabel();
		label2.setHorizontalAlignment(SwingConstants.CENTER);
		label2.setPreferredSize(new Dimension(0,0));
		label2.setMinimumSize(new Dimension(0,0));
		panel2.add(label2);
		label2.setText("密码:");
		password=new JPasswordField(20);
		password.setDocument(new MyDocument(6));
		password.setEchoChar('*');
		password.addKeyListener(new KeyAdapter(){
			public void keyPressed(final KeyEvent e){
				if(e.getKeyCode()==10)
					login.doClick();
			}
		});
		panel2.add(password);
		
		final JPanel panel3=new JPanel();
		panel1.add(panel3, BorderLayout.SOUTH);
		
		login=new JButton();
		login.addActionListener(new LoginAction());
		
		login.setText("登陆");
		panel3.add(login);
		reset=new JButton();
		reset.addActionListener(new ResetAction());
		reset.setText("重置");
		panel3.add(reset);
		setVisible(true);
	}
		public static Operater getUser() {
			return user;
		}
		public static void setUser(Operater user) {
			LoginFrame.user = user;
		}	
	}
	

