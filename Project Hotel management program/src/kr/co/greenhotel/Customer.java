package kr.co.greenhotel;

public class Customer {
	
	private String name ; // 예약자명
	private String useName; // 사용자명
	private String phoneNum ;
	private int birth ;
	private boolean breakfast;
	
	public String getUseName() {
		return useName;
	}
	public void setUseName(String useName) {
		this.useName = useName;
	}
	public Customer() {
		super();
		this.name = "";
		this.phoneNum = "";
		this.birth = 0;
	}
	public Customer(String name, String useName, String phoneNum, int birth, boolean breakfast) {
		super();
		this.name = name;
		this.useName = useName;
		this.phoneNum = phoneNum;
		this.birth = birth;
		this.breakfast = breakfast;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public int getBirth() {
		return birth;
	}

	public void setBirth(int brith) {
		this.birth = brith;
	}
	public boolean isBreakfast() {
		return breakfast;
	}

	public void setBreakfast(boolean breakfast) {
		this.breakfast = breakfast;
	}
}
