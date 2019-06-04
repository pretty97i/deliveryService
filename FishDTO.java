package com.fish;

public class FishDTO {
	
	//개인정보
	private String id;
	private String pwd;
	private String name;
	private int gender;
	private String birth;
	private String tel;
	private String add1;
	private String add2;
	private int age;
	
	//메뉴
	private int no;
	private int pat;
	private int shu;
	private int pizza;
	private int fish;
	private int choco;
	private String date;
	private int tot;
	
	
	
	
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int i) {
		this.gender = i;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAdd1() {
		return add1;
	}
	public void setAdd1(String add1) {
		this.add1 = add1;
	}
	public String getAdd2() {
		return add2;
	}
	public void setAdd2(String add2) {
		this.add2 = add2;
	}
	public int getPat() {
		return pat;
	}
	public void setPat(int pat) {
		this.pat = pat;
	}
	public int getShu() {
		return shu;
	}
	public void setShu(int shu) {
		this.shu = shu;
	}
	public int getPizza() {
		return pizza;
	}
	public void setPizza(int pizza) {
		this.pizza = pizza;
	}
	public int getFish() {
		return fish;
	}
	public void setFish(int fish) {
		this.fish = fish;
	}
	public int getChoco() {
		return choco;
	}
	public void setChoco(int choco) {
		this.choco = choco;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getTot(){
		this.tot = (pat+shu+pizza+fish+choco)*5000;
		return tot;
	}
}
