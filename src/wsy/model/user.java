package wsy.model;

import java.sql.Date;

public class user {
	private String operaterId;
	private String operaterName;
	private String password;
	private String sex;
	private int age;
	private String identityCard;
//	private Date workdate;
	private String tel;
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getOperaterId() {
		return operaterId;
	}
	public void setOperaterId(String i) {
		this.operaterId = i;
	}
	public String getIdentityCard() {
		return identityCard;
	}
	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}
	public String getOperaterName() {
		return operaterName;
	}
	public void setName(String operaterName) {
		this.operaterName = operaterName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
}

