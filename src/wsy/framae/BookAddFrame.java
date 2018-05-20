package wsy.framae;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;


import wsy.jcompz.Item;
import wsy.dao.Dao;
import wsy.model.BookType;
import wsy.util.MyDocument;
/**
 * ���ƣ�ͼ����Ӵ���
 * 
 */
public class BookAddFrame extends JInternalFrame {
	private JComboBox publisher;
	private JTextField   price;
	//private JFormattedTextField pubDate;
	private JTextField translator;
	private JTextField writer;
	private JTextField bookId;
	private JTextField bookName;
	private JComboBox bookType;
	private JButton buttonadd;
	private JButton buttonclose;
	DefaultComboBoxModel bookTypeModel;
	
	Map map=new HashMap();
	public BookAddFrame() {
		super();
		final BorderLayout borderLayout = new BorderLayout();
		getContentPane().setLayout(borderLayout);
		setIconifiable(true);							// ���ô������С������������
		setClosable(true);								// ���ô���ɹرգ���������
		setTitle("ͼ����Ϣ���");						// ���ô�����⣭��������
		setBounds(100, 100, 396, 192);					// ���ô���λ�úʹ�С����������

		final JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 10, 5, 10));
		final GridLayout gridLayout = new GridLayout(0, 4);
		gridLayout.setVgap(8);
		gridLayout.setHgap(8);
		panel.setLayout(gridLayout);
		getContentPane().add(panel);

		final JLabel label_2 = new JLabel();
		label_2.setText("ͼ���ţ�");
		panel.add(label_2);

		bookId = new JTextField("������9λ���",9);
		bookId.setDocument(new MyDocument(9)); //��������ı����������ֵΪ13
		
		bookId.setColumns(9);
		bookId.addKeyListener(new ISBNkeyListener());
		bookId.addFocusListener(new ISBNFocusListener());
		panel.add(bookId);

		final JLabel label = new JLabel();
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setText("���");
		panel.add(label);

		bookType = new JComboBox();
		bookTypeModel= (DefaultComboBoxModel)bookType.getModel();
		
		//�����ݿ���ȡ��ͼ�����
		List list=Dao.selectBookType();
		for(int i=0;i<list.size();i++){
			BookType booktype=(BookType)list.get(i);
			Item item=new Item();
			item.setId((String)booktype.gettypeId());
			item.setName((String)booktype.getTypeName());
			System.out.println(item.getId());
			System.out.println(item.getName());
			bookTypeModel.addElement(item);
		}
		panel.add(bookType);

		final JLabel label_1 = new JLabel();
		label_1.setText("������");
		panel.add(label_1);

		bookName = new JTextField();
		panel.add(bookName);

		final JLabel label_3 = new JLabel();
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setText("���ߣ�");
		panel.add(label_3);

		writer = new JTextField();
		writer.setDocument(new MyDocument(10));
		panel.add(writer);

		final JLabel label_2_1 = new JLabel();
		label_2_1.setText("�����磺");
		panel.add(label_2_1);

		publisher = new JComboBox();
		String[]array=new String[]{"***������","**��Ϣ������","**���ͳ�����","***С�ͳ�����"};
		publisher.setModel(new DefaultComboBoxModel(array));
		panel.add(publisher);

		final JLabel label_3_1 = new JLabel();
		label_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_3_1.setText("���ۣ�");
		panel.add(label_3_1);
		  price=   new   JTextField();
		  price.setDocument(new MyDocument(5));
		  price.addKeyListener(new NumberListener());
		panel.add(price);

		final JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(SystemColor.activeCaptionBorder, 1, false));
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		final FlowLayout flowLayout = new FlowLayout();
		flowLayout.setVgap(5);
		flowLayout.setHgap(5);
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel_1.setLayout(flowLayout);

		buttonadd= new JButton();
		buttonadd.addActionListener(new addBookActionListener());
		buttonadd.setText("���");
		panel_1.add(buttonadd);

		buttonclose = new JButton();
		buttonclose.addActionListener(new CloseActionListener());
		buttonclose.setText("�ر�");
		panel_1.add(buttonclose);
		
		setVisible(true);											// ��ʾ����ɹرգ�����������������пؼ�֮��ִ�и����
	}
	class ISBNFocusListener extends FocusAdapter {
		public void focusLost(FocusEvent e){
			if(!Dao.SelectbookInfo(bookId.getText().trim()).isEmpty()){
				JOptionPane.showMessageDialog(null, "�������ظ���");
				return;
			}
		}
	}
	class ISBNkeyListener extends KeyAdapter {
		public void keyPressed(final KeyEvent e) {
			if (e.getKeyCode() == 9){
				buttonadd.doClick();
			}
		
		}
	}
	class CloseActionListener implements ActionListener {			// ��ӹرհ�ť���¼�������
		public void actionPerformed(final ActionEvent e) {
			doDefaultCloseAction();
		}
	}
	class addBookActionListener implements ActionListener {		// ��Ӱ�ť�ĵ����¼�������
		public void actionPerformed(final ActionEvent e) {
			// ����ҵ��
			

			if(bookId.getText().length()==0){
				JOptionPane.showMessageDialog(null, "����ı��򲻿���Ϊ��");
				return;
			}
			if(bookId.getText().length()!=9){
				JOptionPane.showMessageDialog(null, "����ı�������λ��Ϊ9λ");
				return;
			}
			if(bookName.getText().length()==0){
				JOptionPane.showMessageDialog(null, "ͼ�������ı��򲻿���Ϊ��");
				return;
			}
			if(writer.getText().length()==0){
				JOptionPane.showMessageDialog(null, "�����ı��򲻿���Ϊ��");
				return;
			}
			if(price.getText().length()==0){
				JOptionPane.showMessageDialog(null, "�����ı��򲻿���Ϊ��");
				return;
			}

			
			String BookId=bookId.getText().trim();
			
			//����
			Object selectedItem = bookType.getSelectedItem();
			if (selectedItem == null)
				return;
			Item item = (Item) selectedItem;
			System.out.println(item.getId());
			String typeId=item.getId();
			System.out.println(typeId);
			String bookNames=bookName.getText().trim();
			String writers=writer.getText().trim();
			String publishers=(String)publisher.getSelectedItem();
			String prices=price.getText().trim();
			boolean i=Dao.Insertbook(BookId, bookNames,typeId, writers,  publishers, Double.parseDouble(prices));
			System.out.println(typeId);	
			if(i==false){		
				JOptionPane.showMessageDialog(null, "��ӳɹ�");
				doDefaultCloseAction();
			}
		}
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

