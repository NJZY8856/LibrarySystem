package wsy.model;

import java.util.Date;

public class Borrow {
	private String readerId;
	private String bookId;
	//private String readerISBN;
	//private String num;
	private String borrowDate;
	private String backDate;
	private String bookName;
	private String operatorId;
	private int isBack;
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getBackDate() {
		return backDate;
	}
	public void setBackDate(String backDate) {
		this.backDate = backDate;
	}
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public String getBorrowDate() {
		return borrowDate;
	}
	public void setBorrowDate(String borrowDate) {
		this.borrowDate = borrowDate;
	}
	
	/*public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getReaderISBN() {
		return readerISBN;
	}
	public void setReaderISBN(String readerISBN) {
		this.readerISBN = readerISBN;
	}*/
	public String getReaderId() {
		return readerId;
	}
	public void setId(String readerId) {
		this.readerId = readerId;
	}
	public String getOperatorId(){
		return operatorId;
	}
	public void setOperatorId(String operatorId){
		this.operatorId=operatorId;
	}
	public int getIsBack(){
		return isBack;
	}
	public void setIsBack(int isBack){
		this.isBack=isBack;
	}
}

