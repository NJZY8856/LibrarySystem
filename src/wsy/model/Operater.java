package wsy.model;

import java.lang.*;
import java.awt.*;
import javax.swing.*;

public class Operater {
	private String operaterId;
	private String operaterName;
	private String grade;
	private String password;
	
	private String getGrade(){
		return grade;
	}
	public void setGrade(String grade){
		this.grade=grade;
	}
	public String getOperaterId(){
		return operaterId;
		
	}
	public void setId(String operaterId){
		this.operaterId=operaterId;
	}
	public String getOperaterName(){
		return operaterName;
	}
	public void setName(String operaterName){
		this.operaterName=operaterName;
	}
	public String getPassword(){
		return password;
	}
	public void setPassword(String password){
		this.password=password;
	}
}
