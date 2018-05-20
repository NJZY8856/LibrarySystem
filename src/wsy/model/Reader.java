package wsy.model;

import java.sql.Date;

public class Reader {
	private String readerId;
	private String name;
	private String classes;
	private String sex;
	private String age;
	private String maxNum;
	private String phone;
	private String jieshuNum;
	
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
/*	public Date getBztime() {
		return bztime;
	}
	public void setBztime(Date bztime) {
		this.bztime = bztime;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getIdentityCard() {
		return identityCard;
	}
	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}*/
	public String getreaderId() {
		return readerId;
	}
	public void setreaderId(String readerId) {
		this.readerId=readerId;
	}

	public String getClasses(){
		return classes;
	}
	public void setClasses(String classes){
		this.classes=classes;
	}
	public String getMaxNum() {
		return maxNum;
	}
	public void setMaxNum(String maxNum) {
		this.maxNum = maxNum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getjieshuNum() {
		return jieshuNum;
	}
	public void setjieshuNum(String jieshuNum) {
		this.jieshuNum = jieshuNum;
	}
}

