package kr.co.greenhotel;


public class Rooms {
	private int roomNum;
	private String bSide;
	private int roomState = 1;
	private Customer custom;
	private String rating;
	private int roomPrice;
	private int brkPrice;
	private int count;
	private int brkCount;
	
	
	public int getBrkCount() {
		return brkCount;
	}
	public void setBrkCount(int brkCount) {
		this.brkCount = brkCount;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Rooms(int roomNum, String bSide, int roomState, Customer custom, String rating, int roomPrice, int brkPrice,
			int count) {
		super();
		this.roomNum = roomNum;
		this.bSide = bSide;
		this.roomState = roomState;
		this.custom = custom;
		this.rating = rating;
		this.roomPrice = roomPrice;
		this.brkPrice = brkPrice;
		this.count = count;
	}
	public Rooms(int roomNum, String bSide, int roomState, Customer custom, String rating, int roomPrice, int brkPrice) {
		super();
		this.roomNum = roomNum;
		this.bSide = bSide;
		this.roomState = roomState;
		this.custom = custom;
		this.rating = rating;
		this.roomPrice = roomPrice;
		this.brkPrice = brkPrice;
	}
	public Rooms( String bSide, int roomState, Customer custom, String rating, int roomPrice, int brkPrice) {
		super();
		
		this.bSide = bSide;
		this.roomState = roomState;
		this.custom = custom;
		this.rating = rating;
		this.roomPrice = roomPrice;
		this.brkPrice = brkPrice;
	}
	public Rooms( String bSide, Customer custom, String rating, int roomPrice, int brkPrice) {
		super();
		
		this.bSide = bSide;
		this.custom = custom;
		this.rating = rating;
		this.roomPrice = roomPrice;
		this.brkPrice = brkPrice;
	}
	public Rooms( String bSide,int roomState, Customer custom) {
		super();
		this.bSide = bSide;
		this.roomState = roomState;
		this.custom = custom;
	}
	public String getbSide() {
		return bSide;
	}
	public void setbSide(String bSide) {
		this.bSide = bSide;
	}
	public Rooms(int roomNum, int roomState, Customer custom, String rating) {
		super();
		this.roomNum = roomNum;
		this.roomState = roomState;
		this.custom = custom;
		this.rating = rating;
	}
	public Rooms(int roomNum, int roomState, Customer custom, String rating, int roomPrice, int brkPrice) {
		super();
		this.roomNum = roomNum;
		this.roomState = roomState;
		this.custom = custom;
		this.rating = rating;
		this.roomPrice = roomPrice;
		this.brkPrice = brkPrice;
	}
	public Rooms() {
	}
	public Rooms(int roomNum, Customer custom, int count, int brkCount) {
		super();
		this.roomNum = roomNum;
		this.custom = custom;
		this.count = count;
		this.brkCount =brkCount;
	}
	public Rooms(int roomNum, int roomState, Customer custom) {
		super();
		this.roomNum = roomNum;
		this.roomState = roomState;
		this.custom = custom;
	}
	public Rooms(int roomNum, int roomState) {
		super();
		this.roomNum = roomNum;
		this.roomState = roomState;
	}
	public int getRoomPrice() {
		return roomPrice;
	}

	public void setRoomPrice(int roomPrice) {
		this.roomPrice = roomPrice;
	}

	public int getBrkPrice() {
		return brkPrice;
	}

	public void setBrkPrice(int brkPrice) {
		this.brkPrice = brkPrice;
	}
	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}
	
	public int getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}

	public int getRoomState() {
		return roomState;
	}

	public void setRoomState(int roomState) {
		this.roomState = roomState;
	}

	public Customer getCustom() {
		return custom;
	}
	public void setCustom(Customer custom) {
		this.custom = custom;
	}
	
	public String toString() {
		return "Rooms [roomNum=" + roomNum + ", roomState=" + roomState + ", custom=" + custom + "]";
	}
	// 사용자명 예약자명 다 받기
	public void setCustom(String name, String useName, String phoneNum, int birth, boolean brk) {
		this.custom.setName(name);
		this.custom.setUseName(useName);
		this.custom.setPhoneNum(phoneNum);
		this.custom.setBirth(birth);
		this.custom.setBreakfast(brk);
	}
	public void setCustom(String name, String useName, String phoneNum, int birth) {
		this.custom.setName(name);
		this.custom.setUseName(useName);
		this.custom.setPhoneNum(phoneNum);
		this.custom.setBirth(birth);
	}
	public void setCustom(boolean brk) {
		this.custom.setBreakfast(brk);
	}
	public void setCustom1(Customer custom) {
		this.custom.setName(custom.getName());
		this.custom.setUseName(custom.getUseName());
		this.custom.setPhoneNum(custom.getPhoneNum());
		this.custom.setBirth(custom.getBirth());
		this.custom.setBreakfast(custom.isBreakfast());
	}
	// 사용자 명만 바꾸는 메소드
	public void setCustomer(String useName) {
		this.custom.setUseName(useName);
	}
}
