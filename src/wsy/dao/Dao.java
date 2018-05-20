package wsy.dao;

import java.lang.*;
import java.awt.*;
import javax.swing.*;
import wsy.dao.Dao;
import wsy.model.Back;
import wsy.model.BookInfo;
import wsy.model.BookInfoAndKucun;
import wsy.model.BookType;
import wsy.model.Operater;
import wsy.model.Reader;
import wsy.model.user;
import wsy.model.fkInfo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Dao {
	protected static String dbClassName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	protected static String dbUrl = "jdbc:sqlserver://localhost:1433;"
			+ "DatabaseName=ͼ���;SelectMethod=Cursor";
	protected static String dbUser = "sa";
	protected static String dbPwd = "123456";
	protected static String second = null;
	private static Connection conn = null;
	
	private Dao() {
		try {
			if (conn == null) {
				Class.forName(dbClassName).newInstance();
				conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
			}
			else
				return;
		} catch (Exception ee) {
			ee.printStackTrace();
		}

	}
	private static ResultSet executeQuery(String sql){
		try{
			if(conn==null)
				new Dao();
			return conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally{
			
		}
	}
	private static int executeUpdate(String sql){
		try{
			if(conn==null)
				new Dao();
			return conn.createStatement().executeUpdate(sql);
		}catch(SQLException e){
			System.out.println(e.getMessage());
			return -1;
		}finally{
			
		}
	}
	public static void close(){
		try{
			conn.close();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			conn=null;
		}
	}
	//����Ա��½
	public static Operater check(String name,String password){
		int i=0;
		Operater operater=new Operater();
		String sql="select * from bookOperator where ����='" + name
				+ "' and ����='" + password+"'";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				String names = rs.getString(1);
				operater.setId(rs.getString("����Ա���"));
				operater.setName(rs.getString("����"));
				operater.setPassword(rs.getString("����"));
				if (names != null) {
					i = 1;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return operater;
		
	}
	
	//����ͼ�����
		public static boolean InsertBookTypes(String typeId,String typeName,String days,String fk){
			boolean i=true;
			try{
				conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
				CallableStatement sql=conn.prepareCall("{call InsertBookType(?,?,?,?)}");
				sql.setString(1,typeId);
				sql.setString(2, typeName);
				sql.setString(3, days);
				sql.setString(4,fk);
				i=sql.execute();
			}catch(Exception e){
				e.printStackTrace();
			}
			return i;
		}
		
	//��ѯ���
	public static List selectBookType(){
		List list=new ArrayList();
		try{
		conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
		CallableStatement sql=conn.prepareCall("{call selectBookType()}");
		ResultSet rs=sql.executeQuery();
			while(rs.next()){
				BookType booktype=new BookType();
				booktype.settypeId(rs.getString("����"));
				booktype.setTypeName(rs.getString("�����"));
				booktype.setDays(rs.getString("�ɽ�����"));
				booktype.setFk(rs.getString("������"));
				list.add(booktype);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
	
	public static List selectBookType(String booktype){
		List list=new ArrayList();
		String sql="select �ɽ����� from bookType where �����='"+booktype+"'";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while(rs.next()){
				BookType type=new BookType();
				type.setDays(rs.getString("�ɽ�����"));
				list.add(type);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
	
	//�޸�ͼ��������
	public static boolean UpdatebookType(String typeId,String typeName,String days,String fk){
		boolean i=true;
		try{
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
			CallableStatement sql=conn.prepareCall("{call UpdateBookType(?,?,?,?)}");
			sql.setString(1,typeId);
			sql.setString(2,typeName);
			sql.setString(3, days);
			sql.setString(4, fk);
			i=sql.execute();
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
//ɾ��ͼ�����
	public static boolean DeletebookType(String typeId){
		boolean i=true;
		try{
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
			CallableStatement sql=conn.prepareCall("{call delBookType(?)}");
			sql.setString(1,typeId);
			i=sql.execute();
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
	//ÿ���鳬���涨ʱ���i������
	public static List SelectbookTypeFK(String bookType){
		List list=new ArrayList();
		String sql="select * from bookType where �����='"+bookType+"'";
		ResultSet rs=Dao.executeQuery(sql);
		try{
			while(rs.next()){
				BookType type=new BookType();
				type.setFk(rs.getString("������"));
				type.setDays(rs.getString("�ɽ�����"));
				list.add(type);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
	//����ͼ����Ϣ
	public static boolean Insertbook(String bookId,String bookName,String typeId,String writer,String publisher,Double price){
		boolean i=true;
		try{
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
			CallableStatement sql=conn.prepareCall("{call InsertBookInfo(?,?,?,?,?,?)}");
			sql.setString(1, bookId);
			sql.setString(2, bookName);
			sql.setString(3,typeId);
			sql.setString(4, writer);
			sql.setString(5, publisher);
			sql.setDouble(6, price);
			i=sql.execute();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		Dao.close();
		return i;
	}
	//��ѯͼ����Ϣ
	public static List SelectbookInfo(){
		List list =new ArrayList();
		String sql="select * from bookInfo";
		ResultSet rs=Dao.executeQuery(sql);
		try{
			while(rs.next()){
				BookInfo bookinfo=new BookInfo();
				bookinfo.setbookId(rs.getString("ͼ����"));
				bookinfo.setTypeId(rs.getString("����"));
				bookinfo.setBookname(rs.getString("����"));
				bookinfo.setWriter(rs.getString("����"));
				bookinfo.setPublisher(rs.getString("������"));
				bookinfo.setPrice(rs.getString("����"));
				list.add(bookinfo);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
	public static List SelectbookInfo(String bookId){
		List list =new ArrayList();
		String sql="select * from bookInfo where ͼ����='"+bookId+"'";
		ResultSet rs=Dao.executeQuery(sql);
		try{
			while(rs.next()){
				BookInfo bookinfo=new BookInfo();
				bookinfo.setbookId(rs.getString("ͼ����"));
				bookinfo.setTypeId(rs.getString("����"));
				bookinfo.setBookname(rs.getString("����"));
				bookinfo.setWriter(rs.getString("����"));
				bookinfo.setPublisher(rs.getString("������"));
				bookinfo.setPrice(rs.getString("����"));
				list.add(bookinfo);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
	//�޸�ͼ����Ϣ
	public static int Updatebook(String bookId,String bookName,String typeId,String writer,String publisher,String price){
		int i=0;
		try{
			String sql="update bookInfo set ͼ����='"+bookId+"',����='"+bookName+"',����='"+typeId+"',����='"+writer+"',������='"+publisher+"',����="+price+" where ͼ����='"+bookId+"'";
			i=Dao.executeUpdate(sql);
		i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
	//ɾ��ͼ����Ϣ
	public static int Deletebook(String bookId){
		int i=0;
		try{
		String sql="delete from bookInfo where ͼ����='"+bookId+"'";
		i=Dao.executeUpdate(sql);
	}catch(Exception e){
		e.printStackTrace();
	}
	Dao.close();
	return i;
	}
	//���������Ϣ
	public static int InsertReader(String readerId,String name,String professional,String sex,String age,String phone,String maxNum){
		int i=0;
		try{
		String sql="insert into bookReader(ѧ��,����,�༶,�Ա�,����,�绰,����������) values('"+readerId+"','"+name+"','"+professional+"','"+sex+"','"+age+"','"+phone+"','"+maxNum+"')";
		i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
	//��ѯ������Ϣ
	public static List selectReader(){
		List list=new ArrayList();
		String sql="select * from select_reader";
		ResultSet rs=Dao.executeQuery(sql);
		System.out.println(rs);
		try{
			while(rs.next()){
				Reader reader=new Reader();
				reader.setreaderId(rs.getString("ѧ��"));
				reader.setName(rs.getString("����"));
				reader.setClasses(rs.getString("�༶"));
				reader.setAge(rs.getString("����"));
				reader.setSex(rs.getString("�Ա�"));
				reader.setMaxNum(rs.getString("����������"));
				reader.setPhone(rs.getString("�绰"));
				list.add(reader);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
		
		public static List selectreaderasname(String readername){
			List list=new ArrayList();
			String sql="select * from select_reader where ���� like '%"+readername+"%'";
			System.out.print(sql);
			ResultSet s=Dao.executeQuery(sql);
			try {
				while(s.next()){
					Reader reader=new Reader();
					reader.setreaderId(s.getString(1));
					reader.setName(s.getString(2));
					reader.setClasses(s.getString(3));
					reader.setSex(s.getString(4));
					reader.setAge(s.getString(5));
					reader.setPhone(s.getString(6));
					reader.setMaxNum(s.getString(7));
					list.add(reader);
				}
			} catch (SQLException e) {
				// TODO �Զ����� catch ��
				e.printStackTrace();
			}
			Dao.close();
			return list;
		}
		
		public static List selectreaderasId(String readerId){
			List list=new ArrayList();
			String sql="select * from select_reader where ѧ�� like '%"+readerId+"%'";
			System.out.print(sql);
			ResultSet s=Dao.executeQuery(sql);
			try {
				while(s.next()){
					Reader reader=new Reader();
					reader.setName(s.getString(1));
					reader.setName(s.getString(2));
					reader.setClasses(s.getString(3));
					reader.setSex(s.getString(4));
					reader.setAge(s.getString(5));
					reader.setPhone(s.getString(6));
					reader.setMaxNum(s.getString(7));
					list.add(reader);
				}
			} catch (SQLException e) {
				// TODO �Զ����� catch ��
				e.printStackTrace();
			}
			return list;
			
			
		}
	public static List selectReader(String readerId){
		List list=new ArrayList();
		String sql="select * from bookReader whrer ѧ��=��"+readerId+"'";
		ResultSet rs=Dao.executeQuery(sql);
		try{
			while(rs.next()){
				Reader reader=new Reader();
				reader.setreaderId(rs.getString("ѧ��"));
				reader.setName(rs.getString("����"));
				reader.setClasses(rs.getString("�༶"));
				reader.setAge(rs.getString("����"));
				reader.setSex(rs.getString("�Ա�"));
				reader.setMaxNum(rs.getString("����������"));
				reader.setPhone(rs.getString("�绰"));
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
	public static  int UpdateReader(String readerId,String name,String professional,String sex,String age,String phone,String maxNum){
		int i=0;
		try{
			String sql="delete from bookReader where ѧ��='"+readerId+"'";
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
	public static int DeleteReader(String readerId){
		int i=0;
		try{
			String sql="delete from bookReader where ѧ��='"+readerId+"'";
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
	
	public static List jieshuCount(){
		List list=new ArrayList();
		try{
		conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
		Statement stmt = conn.createStatement(); 
		ResultSet rs=stmt.executeQuery("{call getbookcounts}");
			while(rs.next()){
				Reader reader=new Reader();
				reader.setreaderId(rs.getString("ѧ��"));
				reader.setName(rs.getString("����"));
				reader.setClasses(rs.getString("�༶"));
				reader.setMaxNum(rs.getString("����������"));
				reader.setjieshuNum(rs.getString("��������"));
				System.out.println(rs.getString("��������"));
				list.add(reader);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
	
	
	//�Խ��ı���в���
	public static int InsertBookBorrow(String bookId,String readerId,int isBack,Timestamp borrowDate,Timestamp backDate,String operatorId){
		int i=0;
		try{
			String sql="insert into bookBorrow(ͼ����,ѧ��,�Ƿ�黹,��������,�黹����,����Ա���) values('"+bookId+"','"+readerId+"','"+isBack+"','"+borrowDate+"',null,'"+operatorId+"')";
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
	public List selectBorrow(String readerId){
		List list=new ArrayList();
		String sql="SELECT a.ISBN AS bookISBN, a.bookname, a.typeId ,b.id,b.operatorId, b.borrowDate, b.backDate, c.name AS readerName, c.ISBN AS readerISBN FROM tb_bookInfo a INNER JOIN bookBorrow b ON a.ISBN = b.bookISBN INNER JOIN bookReader c ON b.readerISBN = c.ISBN WHERE (c.ISBN = '"+readerId+"' and isback=1)";
		ResultSet rs=Dao.executeQuery(sql);
		try{
			while(rs.next()){
				Back back=new Back();
				back.setBackDate(rs.getString("�黹����"));
				back.setBookname(rs.getString("����"));
				back.setBookISBN(rs.getString("ͼ����"));
				back.setBorrowDate(rs.getString("��������"));
			list.add(back);
			}
			}catch(Exception e){
				e.printStackTrace();
		}
		Dao.close();
		return list;
	}
	/*
	 * ��ѯ��������
	 */
	public static List selectBookBack(String readerId) {
		List list=new ArrayList();
		String sql = "SELECT a.ISBN AS bookISBN, a.bookname, a.typeId ,b.id,b.operatorId, b.borrowDate, b.backDate, c.name AS readerName, c.ISBN AS readerISBN FROM tb_bookInfo a INNER JOIN tb_borrow b ON a.ISBN = b.bookISBN INNER JOIN tb_reader c ON b.readerISBN = c.ISBN WHERE (c.ISBN = '"+readerId+"' and isback=1)";
		System.out.println(sql);
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				Back back=new Back();
				back.setBookISBN(rs.getString("bookISBN"));
				back.setBookname(rs.getString("bookname"));
				back.setTypeId(rs.getInt("typeId"));
				back.setOperatorId(rs.getString("operatorId"));
				back.setBorrowDate(rs.getString("borrowDate"));
				back.setBackDate(rs.getString("backDate"));
				back.setReaderName(rs.getString("readerName"));
				back.setReaderISBN(rs.getString("readerISBN"));
				back.setId(rs.getInt("id"));
				list.add(back);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
	public static boolean UpdateBookBack(Timestamp backDate,String readerId,String bookId){//�黹ͼ�����
		boolean i=false;
		try{
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
			CallableStatement sql=conn.prepareCall("{call backpro(?,?,?,?)}");
			sql.setInt(1, 1);
			sql.setTimestamp(2,backDate);
			sql.setString(3, readerId);
			sql.setString(4, bookId);
			i=sql.execute();
			sql.close();
			System.out.println(i);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;
		
	}
	
	public static List fkInfo(String readerId,String bookId){//������Ϣ��ȡ
		boolean i=true;
		List list=new ArrayList();
		try{
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
			CallableStatement sql=conn.prepareCall("{call pro_fkinfo(?,?,?,?,?,?,?,?,?)}");
			//���洢���̵ĵ�һ�͵ڶ���������ֵ
			sql.setString(1, readerId);
			sql.setString(2, bookId);
			//ע��洢���̵��������
			sql.registerOutParameter(3, java.sql.Types.TIMESTAMP);
			sql.registerOutParameter(4, java.sql.Types.TIMESTAMP);
			sql.registerOutParameter(5, java.sql.Types.INTEGER);
			sql.registerOutParameter(6, java.sql.Types.INTEGER);
			sql.registerOutParameter(7, java.sql.Types.INTEGER);
			sql.registerOutParameter(8, java.sql.Types.DOUBLE);
			sql.registerOutParameter(9, java.sql.Types.DOUBLE);
			i=sql.execute();
			System.out.println(sql.getTimestamp(3));
			System.out.println(sql.getDouble(9));
			if(i==false){
				fkInfo fkinfo=new fkInfo();
				fkinfo.setBorrowDate(sql.getTimestamp(3));
				fkinfo.setBackDate(sql.getTimestamp(4));
				fkinfo.setkejiedays(sql.getInt(5));
				fkinfo.setrealdays(sql.getInt(6));
				fkinfo.setoutreachdays(sql.getInt(7));
				fkinfo.setfajin(sql.getDouble(8));
				fkinfo.settatalfajin(sql.getDouble(9));
				list.add(fkinfo);
			}
			sql.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return list;
		
	}
	
	//new
	public static List selectbookserch() {
		List list=new ArrayList();
		String sql = "select *  from jieyueqingkuang";
		ResultSet s = Dao.executeQuery(sql);
		try {
			while (s.next()) {
				BookInfo bookinfo=new BookInfo();
				bookinfo.setbookId(s.getString(1));
				bookinfo.setBookname(s.getString(2));
				bookinfo.setReaderId(s.getString(3));
				bookinfo.setReaderName(s.getString(4));
				bookinfo.setReaderClass(s.getString(5));
				bookinfo.setWriter(s.getString(6));
				bookinfo.setPublisher(s.getString(7));
				bookinfo.setBorrowDate(s.getString(8));
				bookinfo.setBackDate(s.getString(9));
				bookinfo.setOperater(s.getString(10));
				list.add(bookinfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
	public static List selectbookmohu(String bookname){
		List list=new ArrayList();
		String sql="select * from allBookInfo where ���� like '%"+bookname+"%'";
		System.out.print(sql);
		ResultSet s=Dao.executeQuery(sql);
		try {
			while(s.next()){
				BookInfo bookinfo=new BookInfo();
				bookinfo.setbookId(s.getString(1));
				bookinfo.setBookname(s.getString(2));
				bookinfo.setWriter(s.getString(3));
				bookinfo.setPublisher(s.getString(4));
				bookinfo.setGuancang(s.getString(5));
				bookinfo.setKejie(s.getString(6));
				list.add(bookinfo);
			}
		} catch (SQLException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		return list;
		
		
	}
	
	public static List selectbookmohuwriter(String writer){
		List list=new ArrayList();
		String sql="select * from allBookInfo where ���� like '%"+writer+"%'";
		ResultSet s=Dao.executeQuery(sql);
		try {
			while(s.next()){
				BookInfo bookinfo=new BookInfo();
				bookinfo.setbookId(s.getString(1));
				bookinfo.setBookname(s.getString(2));
				bookinfo.setWriter(s.getString(3));
				bookinfo.setPublisher(s.getString(4));
				bookinfo.setGuancang(s.getString(5));
				bookinfo.setKejie(s.getString(6));
				list.add(bookinfo);
			}
		} catch (SQLException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		return list;		
	}
	public static int Insertoperator(String operaterId,String name,String sex,int age,String identityCard,String tel,String password){
		int i=0;
		try{
			String sql="insert into bookOperator(����Ա���,����,�Ա�,����,���֤��,�ֻ���,����) values('"+operaterId+"','"+name+"','"+sex+"',"+age+",'"+identityCard+"','"+tel+"','"+password+"')";
			System.out.println(sql);
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
	
	public static List selectuser() {
		List list=new ArrayList();
		String sql = "select ����Ա���,����,����,����,�Ա�,���֤��,�ֻ��� from selectoperater";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				user user=new user();
				user.setOperaterId(rs.getString(1));
				user.setPassword(rs.getString(2));
				user.setName(rs.getString(3));
				user.setAge(rs.getInt(4));
				user.setSex(rs.getString(5));
				user.setIdentityCard(rs.getString(6));
				user.setTel(rs.getString(7));				
				list.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
	
	public static int Deluser(String id){
		int i=0;
		try{
			String sql="delete from bookOperator where ����Ա���='"+id+"'";
			//System.out.println(sql);
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
	
	public static int Updateuser(String operaterId,String name,String password,int age,String sex,String identityCard,String tel){
		int i=0;
		try{
			String sql="update bookOperator set ����='"+name+"',�Ա�='"+sex+"',����="+age+",���֤��='"+identityCard+"',�ֻ���='"+tel+"',����='"+password+"' where ����Ա���='"+operaterId+"'";
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
	
	public static int Updatepass(String password,String name){
		int i=0;
		try{
			String sql="update bookOperator set ����='"+password+"' where ����='"+name+"'";
			
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
	public static int Insertbook(String bookId, String bookNames, String typeId, String writers, String publishers,
			Date valueOf, double parseDouble) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}



