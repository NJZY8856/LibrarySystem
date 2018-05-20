package wsy.framae;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.text.NumberFormat;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import wsy.jcompz.Item;
import wsy.jcompz.MapPz;
import wsy.dao.Dao;
import wsy.model.BookInfo;
import wsy.model.BookType;
import wsy.util.MyDocument;

/**
 * ���ƣ�ͼ���޸Ĵ���
 *
 */
public class BookChangeFrame extends JInternalFrame {
	private JTable table;
	private JFormattedTextField   price;
	private JFormattedTextField pubDate;
//	private JTextField translator;
	private JTextField publisher;
	private JTextField writer;
	private JTextField BookId;
	private JTextField bookName;
	private JComboBox bookType;
	DefaultComboBoxModel bookTypeModel;
	private Item item;
	Map map=new HashMap();
	private String[] columnNames;
	private Map m=MapPz.getMap();

	//ȡ���ݿ���ͼ�������Ϣ��������
	private Object[][] getFileStates(List list){
		String[] columnNames = { "ͼ����", "ͼ������","����", "����", "������","�۸�" };
		Object[][]results=new Object[list.size()][columnNames.length];
		
		for(int i=0;i<list.size();i++){
			BookInfo bookinfo=(BookInfo)list.get(i);
			results[i][0]=bookinfo.getbookId();
			results[i][1]=bookinfo.getBookname();
			String booktypename=String.valueOf(MapPz.getMap().get(bookinfo.getTypeId()));
			results[i][2]=booktypename;
			results[i][3]=bookinfo.getWriter();
			results[i][4]=bookinfo.getPublisher();
			results[i][5]=bookinfo.getPrice();
		}
		return results;
	         		
	}
	public BookChangeFrame() {
		super();
		final BorderLayout borderLayout = new BorderLayout();
		getContentPane().setLayout(borderLayout);
		setIconifiable(true);
		setClosable(true);
		setTitle("ͼ����Ϣ�޸�");
		setBounds(100, 100, 593, 406);

		final JPanel panel3 = new JPanel();
		panel3.setBorder(new LineBorder(SystemColor.activeCaptionBorder, 1, false));
		getContentPane().add(panel3, BorderLayout.SOUTH);
		final FlowLayout flowLayout = new FlowLayout();
		flowLayout.setVgap(2);
		flowLayout.setHgap(30);
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel3.setLayout(flowLayout);

		final JButton button = new JButton();
		button.addActionListener(new addBookActionListener());
		button.setText("�޸�");
		panel3.add(button);

		final JButton button_2 = new JButton();
		button_2.addActionListener(new ActionListener(){
			public void actionPerformed(final ActionEvent e) {
				String ISBNs=BookId.getText().trim();
				int i=Dao.Deletebook(ISBNs);
				if(i==1){
					JOptionPane.showMessageDialog(null, "ɾ���ɹ�");
					Object[][] results=getFileStates(Dao.SelectbookInfo());
					//ע�ʹ���Ϊʹ�ñ��ģ��
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					table.setModel(model);
					model.setDataVector(results, columnNames);
				}
			}
		});
		button_2.setText("ɾ��");
		
		panel3.add(button_2);

		final JButton button_1 = new JButton();
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				doDefaultCloseAction();
			}
		});
		button_1.setText("�ر�");
		panel3.add(button_1);

		final JPanel panel_2 = new JPanel();
		final BorderLayout borderLayout_1 = new BorderLayout();
		borderLayout_1.setVgap(5);
		panel_2.setLayout(borderLayout_1);
		panel_2.setBorder(new EmptyBorder(5, 10, 5, 10));
		getContentPane().add(panel_2);

		final JScrollPane scrollPane = new JScrollPane();
		panel_2.add(scrollPane);

		Object[][] results=getFileStates(Dao.SelectbookInfo());
		columnNames = new String[]{"ͼ����","����","����", "����", "������","�۸�"};
		table = new JTable(results,columnNames);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		//��굥������е����ݲ����¼�,������е����ݷ����ı�����
		table.addMouseListener(new TableListener());
		
		scrollPane.setViewportView(table);

		final JPanel bookPanel = new JPanel();
		panel_2.add(bookPanel, BorderLayout.SOUTH);
		final GridLayout gridLayout = new GridLayout(0, 6);
		gridLayout.setVgap(5);
		gridLayout.setHgap(5);
		bookPanel.setLayout(gridLayout);

		final JLabel label_2 = new JLabel();
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setText("��       �ţ�");
		bookPanel.add(label_2);

		BookId = new JTextField();
		BookId.setDocument(new MyDocument(9)); 
		bookPanel.add(BookId);
		final JLabel label = new JLabel();
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setText("��       ��");
		bookPanel.add(label);

		bookType = new JComboBox();
		bookTypeModel= (DefaultComboBoxModel)bookType.getModel();
		List list=Dao.selectBookType();
		for(int i=0;i<list.size();i++){
			BookType booktype=(BookType)list.get(i);
			item=new Item();
			item.setId((String)booktype.gettypeId());
			item.setName((String)booktype.getTypeName());
			bookTypeModel.addElement(item);
			map.put(item.getId(), item);
			
		}
		bookPanel.add(bookType);

		final JLabel label_1 = new JLabel();
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setText("��    ����");
		bookPanel.add(label_1);

		bookName = new JTextField();
		bookPanel.add(bookName);

		final JLabel label_3 = new JLabel();
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setText("��       �ߣ�");
		bookPanel.add(label_3);

		writer = new JTextField();
		bookPanel.add(writer);

		final JLabel label_2_1 = new JLabel();
		label_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_2_1.setText("��  ��  �磺");
		bookPanel.add(label_2_1);

		publisher = new JTextField();
		bookPanel.add(publisher);

		final JLabel label_3_1 = new JLabel();
		label_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_3_1.setText("��      �ۣ�");
		bookPanel.add(label_3_1);

		  price=   new   JFormattedTextField();
		  price.addKeyListener(new NumberListener());
		bookPanel.add(price);
		setVisible(true);
	}
	class TableListener extends MouseAdapter {
		public void mouseClicked(final MouseEvent e) {
			String ISBNs, typeids, bookNames,writers,publishers,prices;
			int selRow = table.getSelectedRow();
			ISBNs = table.getValueAt(selRow, 0).toString().trim();
			bookNames = table.getValueAt(selRow, 1).toString().trim();
			typeids = table.getValueAt(selRow, 2).toString().trim();
			writers = table.getValueAt(selRow, 3).toString().trim();
			publishers = table.getValueAt(selRow, 4).toString().trim();
			prices = table.getValueAt(selRow, 5).toString().trim();
			
			BookId.setText(ISBNs);	
			bookTypeModel.setSelectedItem(typeids);
			bookName.setText(bookNames);
			writer.setText(writers);
			publisher.setText(publishers);
			price.setText(prices);
		}
	}
	class addBookActionListener implements ActionListener {
		public void actionPerformed(final ActionEvent e) {
			// �޸�ͼ����Ϣ��
			if(BookId.getText().length()==0){
				JOptionPane.showMessageDialog(null, "����ı��򲻿���Ϊ��");
				return;
			}
			if(BookId.getText().length()!=9){
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
			if(publisher.getText().length()==0){
				JOptionPane.showMessageDialog(null, "�������ı��򲻿���Ϊ��");
				return;
			}
			if(price.getText().length()==0){
				JOptionPane.showMessageDialog(null, "�����ı��򲻿���Ϊ��");
				return;
			}
				
			String ISBNs=BookId.getText().trim();
			
			//����
			Object selectedItem = bookTypeModel.getSelectedItem();
			System.out.println(selectedItem);
			if (selectedItem == null)
				return;
		//	Item item = (Item) selectedItem;
			String bookTypes=item.getId();
			System.out.println(bookTypes);
			String bookNames=bookName.getText().trim();
			String writers=writer.getText().trim();
			String publishers=publisher.getText().trim();
			String prices=price.getText().trim();
			
			int i=Dao.Updatebook(ISBNs,bookNames,bookTypes, writers,publishers,prices);
			System.out.println(i);
			
			if(i==1){

				JOptionPane.showMessageDialog(null, "�޸ĳɹ�");
				Object[][] results=getFileStates(Dao.SelectbookInfo());
				//ע�ʹ���Ϊʹ�ñ��ģ��
				DefaultTableModel model=new DefaultTableModel();
				table.setModel(model);
				model.setDataVector(results, columnNames);
				

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

