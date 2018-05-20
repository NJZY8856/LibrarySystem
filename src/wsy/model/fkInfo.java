package wsy.model;

import java.lang.*;
import java.sql.Timestamp;

public class fkInfo {
	private Timestamp borrowDate;
	private Timestamp backDate;
	private int kejiedays;
	private int realdays;
	private int outreachdays;
	private double fajin;
	private double totalfajin;
	
	public Timestamp getBorrowDate(){
		return borrowDate;
	}
	public void setBorrowDate(Timestamp borrowDate){
		this.borrowDate=borrowDate;
	}
	public Timestamp getBackDate(){
		return backDate;
	}
	public void setBackDate(Timestamp backDate){
		this.backDate=backDate;
	}
	public int getkejiedays(){
		return kejiedays;
	}
	public void setkejiedays(int kejiedays){
		this.kejiedays=kejiedays;
	}
	public int getrealdays(){
		return realdays;
	}
	public void setrealdays(int realdays){
		this.realdays=realdays;
	}
	public int getoutreachdays(){
		return outreachdays;
	}
	public void setoutreachdays(int outreachdays){
		this.outreachdays=outreachdays;
	}
	public double getfajin(){
		return fajin;
	}
	public void setfajin(double fajin){
		this.fajin=fajin;
	}
	public double gettotalfajin(){
		return totalfajin;
	}
	public void settatalfajin(double totalfajin){
		this.totalfajin=totalfajin;
	}
	
}
