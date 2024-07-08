package kr.co.greenhotel;

import java.util.Scanner;

public class HotelRoomState {
	Rooms[][] rooms = new Rooms[4][20];
	Scanner scan = new Scanner(System.in);

	// 현재 객실의 정보를 보여주는 메소드
	// 2~5층의 모든 객실의 정보를 모형으로 보여줌
	public void printRoomState(Rooms[][] rooms) {
		System.out.println("A동");
		System.out.println("각 층 호실 번호는 1 ~ 20 입니다.");
		for (int i = rooms.length; i > 0; i--) {
			System.out.print(i + 1 + "층 :");

			for (int j = 0; j < rooms[i - 1].length; j++) {
				if (j % 5 == 0) {
					System.out.print("|");
				}
				if (rooms[i - 1][j].getRoomState() == 0) {
					System.out.print("♠");
				}
				if (rooms[i - 1][j].getRoomState() == 1) {
					System.out.print("○");
				}
				if (rooms[i - 1][j].getRoomState() == 2) {
					System.out.print("□");
				}
				if (rooms[i - 1][j].getRoomState() == 3) {
					System.out.print("●");
				}
				if (rooms[i - 1][j].getRoomState() == -1) {
					System.out.print("▲");
				}
			}
			System.out.println();
		}
		System.out.println("○ : 빈방 | □ : 예약 중 | ● : 사용중  | ♠ : 청소 중 | ▲ : 사용 불가");
	}

	// 방의 등급을 String으로 저장하는 메소드
	public void ratings() {
		for (int i = 0; i < rooms.length; i++) {
			for (int j = 0; j < rooms[i].length; j++) {
				if (i == 0 || i == 1) {
					rooms[i][j].setRating("스탠다드");
					rooms[i][j].setRoomPrice(550000);
				}
				if (i == 2) {
					rooms[i][j].setRating("디럭스");
					rooms[i][j].setRoomPrice(670000);
				}
				if (i == 3) {
					if (j >= 0 && j < 15) {
						rooms[i][j].setRating("프리미어");
						rooms[i][j].setRoomPrice(1400000);
					} else if (j > 14 && j < 20) {
						rooms[i][j].setRating("스위트");
						rooms[i][j].setRoomPrice(8000000);
					}
				}
				rooms[i][j].setBrkPrice(25000);
			}
		}

	}

	// 번호를 객실의 등급 이름으로 바꿔주는 메소드
	public String changeRating(int num) {
		if (num == 1) {
			return "스탠다드";
		}
		if (num == 2) {
			return "디럭스";
		}
		if (num == 3) {
			return "프리미어";
		}
		if (num == 4) {
			return "스위트";
		}
		return "";
	}

	// 입력받은 정보로 예약이 가능한 등급의 방을 보여줌
	public void printRooms(int num) { // 매개변수로 roomNum or rating
		String g = changeRating(num);
		for (int i = 0; i < rooms.length; i++) {

			for (int j = 0; j < rooms[i].length; j++) {
				if (rooms[i][j].getRating().equals(g)) {
					if (rooms[i][j].getRoomState() == 1) {
						System.out.print(rooms[i][j].getRoomNum() + "호\t");
						if (j == 19 || j == 9) {
							System.out.println();
						}
					}
				}

			}
		}
	}

	// 방번호 정확하게 입력 받는 메소드
	public boolean roomCheck(int roomNum) {
		int a = roomNum / 100;
		int b = roomNum % 100;
		if (a < 6 && a > 1) {
			if (b > 0 && b < 21) {
				return true;
			}
		}
		return false;
	}

	// 입력받은 호실을 2차원배열로 바꾸는 메소드
	public Rooms roomNumToArray(int roomNum) {
		int a = roomNum / 100;
		int b = roomNum % 100;
		return rooms[a - 2][b - 1];
	}

	// 1, 2, 3 각각 방의 상태를 확인하는 메소드들
	public boolean roomStateCheck1(int roomNum) {
		if (roomNumToArray(roomNum).getRoomState() == 1) {
			return true;
		}
		return false;
	}

	public boolean roomStateCheck2(int roomNum) {
		if (roomNumToArray(roomNum).getRoomState() == 2) {
			return true;
		}
		return false;
	}

	public boolean roomStateCheck3(int roomNum) {
		if (roomNumToArray(roomNum).getRoomState() == 3) {
			return true;
		}
		return false;
	}

	// 핸드폰 번호 11자리 확인하는 메소드
	public boolean check(String phoneNum) {
		if (phoneNum.length() != 11) {
			return false;
		} else {
			return true;
		}
	}

	// 생년월일 8자리 확인하는메소드
	public boolean check(int brith) {
		if (String.valueOf(brith).length() != 8) {
			return false;
		} else {
			return true;
		}
	}

	// 사용자 선택이 1번이면 true 조식신청
	public boolean breakfastCheck(int roomNum, int eat) {
		if (eat == 1) {
			System.out.println("조식 신청이 되었습니다");
			roomNumToArray(roomNum).setCustom(true);
			return true;
		} else {
			return false;
		}
	}

	// 고객 세팅 (조식 있는 버전)
	public void setCustomer(int roomNum, String useName, String name, String phoneNum, int birth, boolean brk) {
		roomNumToArray(roomNum).setCustom(name, useName, phoneNum, birth, brk);
		roomNumToArray(roomNum).setRoomState(2);
	}

	// 고객 세팅 (조식 없는 버전)
	public void setCustomer(int roomNum, String useName, String name, String phoneNum, int birth) {
		roomNumToArray(roomNum).setCustom(name, useName, phoneNum, birth);
		roomNumToArray(roomNum).setRoomState(2);
	}
	// 고객 세팅(워크인 버전)
	public void setCustomer1(int roomNum, String useName, String name, String phoneNum, int birth) {
		roomNumToArray(roomNum).setCustom(name, useName, phoneNum, birth);
		roomNumToArray(roomNum).setRoomState(3);
	}

	// 예약 취소 메소드
	public void deleteRoom(int roomNum, String name, int birth) {
		if (roomNumToArray(roomNum).getRoomState() == 2) {
			if (roomNumToArray(roomNum).getCustom().getName().equals(name)
					&& roomNumToArray(roomNum).getCustom().getBirth() == birth) {
				System.out.println("예약을 취소하시겠습니까?");
				System.out.println("1. 예, 2.아니오");
				int s = scan.nextInt();
				switch (s) {
				case 1:
					roomNumToArray(roomNum).setRoomState(1);
					roomNumToArray(roomNum).setCustom("", "", "", 0, false);
					break;
				case 2:
					System.out.println("예약을 취소하지 않습니다.");
					break;
				}
			}
		}

	}

	// 예약확인
	public void checkCustomer(String name, String phoneNum, int birth) {
		for (int i = 0; i < rooms.length; i++) {
			for (int j = 0; j < rooms[i].length; j++) {
				if (rooms[i][j].getCustom().getName().equals(name)
						&& rooms[i][j].getCustom().getPhoneNum().equals(phoneNum)
						&& rooms[i][j].getCustom().getBirth() == birth) {

					System.out.println("예약자 " + rooms[i][j].getCustom().getName() + "님의 방 정보입니다.");
					System.out.println("사용자는 " + rooms[i][j].getCustom().getUseName() + "님 입니다.");
					System.out.println(
							rooms[i][j].getCustom().getName() + "님의 방 번호는 " + rooms[i][j].getRoomNum() + "입니다.");
					if ((j + 1) % 2 == 0) {
						System.out.println(rooms[i][j].getCustom().getName() + "님의 방은 더블 배드입니다.");
					} else if (i == 2) {
						System.out.println(rooms[i][j].getCustom().getName() + "님의 방은 더블 배드입니다.");
					} else if ((j + 1) % 2 != 0) {
						System.out.println(rooms[i][j].getCustom().getName() + "님의 방은 싱글 배드입니다.");
					}
					if (rooms[i][j].getCustom().isBreakfast()) {
						System.out.println(rooms[i][j].getCustom().getName() + "님은 조식을 신청하셨습니다.");
					} else {
						System.out.println(rooms[i][j].getCustom().getName() + "님은 조식을 신청하지 않으셨습니다.");
					}
				}
			}
		}
	}

	// 예약 확인용 고객 확인
	public boolean checkCustomer(String name) {
		for (int i = 0; i < rooms.length; i++) {
			for (int j = 0; j < rooms[i].length; j++) {
				if (rooms[i][j].getCustom().getName().equals(name)) {
					return true;
				}
			}
		}
		return false;
	}

	// 방 번호에 대한 고객확인
	public boolean checkCustomer(int roomNum, String name) {
		if (roomNumToArray(roomNum).getCustom().getName().equals(name)) {
			return true;
		}
		return false;
	}

	// 방 변경 메소드
	public void changeRoom(int roomNum, int changeNum) {
		roomNumToArray(changeNum).setCustom1(roomNumToArray(roomNum).getCustom());
		roomNumToArray(changeNum).setRoomState(2);
		roomNumToArray(roomNum).setCustom("", "", "", 0);
		roomNumToArray(roomNum).setRoomState(1);
	}

	// 예약된 고객의 생년월일과 입력한 휴대전화 번호를 비교
	public boolean compPhone(int roomNum, String phoneNum) {
		if (roomNumToArray(roomNum).getCustom().getPhoneNum().equals(phoneNum)) {
			return true;
		}
		return false;
	}

	// 예약된 고객의 생년월일과 입력한 생년월일을 비교
	public boolean compBirth(int roomNum, int birth) {
		if (roomNumToArray(roomNum).getCustom().getBirth() == birth) {
			return true;
		}
		return false;
	}

	public boolean compNamePhone(String name, String phoneNum) {
		for (int i = 0; i < rooms.length; i++) {
			for (int j = 0; j < rooms[i].length; j++) {
				if (rooms[i][j].getCustom().getName().equals(name)) {
					if (rooms[i][j].getCustom().getPhoneNum().equals(phoneNum)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean compNameBirth(String name, int birth) {
		for (int i = 0; i < rooms.length; i++) {
			for (int j = 0; j < rooms[i].length; j++) {
				if (rooms[i][j].getCustom().getName().equals(name)) {
					if (rooms[i][j].getCustom().getBirth() == birth) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public int allPrice() {
		int allCountPrice = 0;
		int allBrkPrice = 0;

		for (int i = 0; i < rooms.length; i++) {
			for (int j = 0; j < rooms[i].length; j++) {
				// if (rooms[i][j].getCount() > 0) {
				allCountPrice += rooms[i][j].getRoomPrice() * rooms[i][j].getCount();
				// }
				// if (rooms[i][j].getBrkCount() > 0) {
				allBrkPrice += rooms[i][j].getBrkPrice() * rooms[i][j].getBrkCount();
				// }
			}
		}
		return allCountPrice + allBrkPrice;
	}

	public void resetRoom(int roomNum) {
		if (roomNumToArray(roomNum).getCustom().isBreakfast()) {
			roomNumToArray(roomNum).setBrkCount(roomNumToArray(roomNum).getBrkCount() - 1);
			roomNumToArray(roomNum).setCustom(false);
		}
		roomNumToArray(roomNum).setCustom("", "", "", 0);
		roomNumToArray(roomNum).setRoomState(1);
		roomNumToArray(roomNum).setCount(roomNumToArray(roomNum).getCount() - 1);
	}
}
