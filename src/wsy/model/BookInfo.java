package wsy.model;

import java.sql.Date;

public class BookInfo {
	private String bookId;
	private String bookname;
	private String typeId;
	private String writer;
	private String publisher;
	private String price;
	private String guancang;
	private String kejie;
	private String readerId;
	private String readerName;
	private String readerClass;
	private String borrowDate;
	private String backDate;
	private String operater;
	
	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public String getbookId() {
		return bookId;
	}
	public void setbookId(String bookId) {
		this.bookId = bookId;
	}

	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getGuancang(){
		return guancang;
	}
	public void setGuancang(String guancang){
		this.guancang=guancang;
	}
	public String getKejie(){
		return kejie;
	}
	public void setKejie(String kejie){
		this.kejie=kejie;
	}
	public String getReaderId(){
		return readerId;
	}
	public void setReaderId(String readerId){
		this.readerId=readerId;
	}
	public String getReaderName(){
		return readerName;
	}
	public void setReaderName(String readerName){
		this.readerName=readerName;
	}
	public String getReaderClass(){
		return readerClass;
	}
	public void setReaderClass(String readerClass){
		this.readerClass=readerClass;
	}
	public String getBorrowDate(){
		return borrowDate;
	}
	public void setBorrowDate(String borrowDate){
		this.borrowDate=borrowDate;
	}
	public String getBackDate(){
		return backDate;
	}
	public void setBackDate(String backDate){
		this.backDate=backDate;
	}
	public String getOperater(){
		return operater;
	}
	public void setOperater(String operater){
		this.operater=operater;
	}
}
