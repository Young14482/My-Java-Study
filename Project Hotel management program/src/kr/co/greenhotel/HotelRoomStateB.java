package kr.co.greenhotel;

import java.util.Scanner;

public class HotelRoomStateB {
	Scanner scan = new Scanner(System.in);
	Rooms[] rooms1 = new Rooms[10];
	Rooms[] rooms2 = new Rooms[9];
	Rooms[] rooms3 = new Rooms[8];

	Rooms[][] roomsB = { rooms1, rooms2, rooms3 };

	public boolean roomCheck(String roomChar) {
		int a = roomChar.charAt(0);
		if (a < 91 && a > 64) {
			return true;
		}
		return false;
	}

	public void hotelB() {

		char c = 'A';
		for (int i = 0; i < roomsB.length; i++) {
			for (int j = 0; j < roomsB[i].length; j++) {
				roomsB[i][j].setbSide(Character.toString(c));
				roomsB[i][j].setRating("디럭스 스탠다드");
				roomsB[i][j].setRoomPrice(800000);
				roomsB[i][j].setBrkPrice(35000);
				c++;
				if (i == 2 && j == 7) {
					roomsB[i][j].setbSide("VVIP");
					roomsB[i][j].setRating("VVIP");
					roomsB[i][j].setRoomPrice(2800000);

				}
			}
		}
	}

	public Rooms roomCharToArray(String roomNum) {
		for (int i = 0; i < roomsB.length; i++) {
			for (int j = 0; j < roomsB[i].length; j++) {
				if (roomsB[i][j].getbSide().equals(roomNum)) {
					return roomsB[i][j];
				}
			}
		}
		return null;
	}

	public void printRoomStateB(Rooms[][] roomsB) {
		System.out.println("B동");
		for (int i = roomsB.length; i > 0; i--) {
			System.out.print(i + "층 :");
			for (int j = 0; j < roomsB[i - 1].length; j++) {
				if (j % 5 == 0) {
					System.out.print("|");
				}
				if (roomsB[i - 1][j].getRoomState() == 0) {
					System.out.print("♠");
				}
				if (roomsB[i - 1][j].getRoomState() == 1) {
					System.out.print("○");
				}
				if (roomsB[i - 1][j].getRoomState() == 2) {
					System.out.print("□");
				}
				if (roomsB[i - 1][j].getRoomState() == 3) {
					System.out.print("●");
				}
				if (roomsB[i - 1][j].getRoomState() == -1) {
					System.out.print("▲");
				}
			}
			System.out.println();
		}
		System.out.println("○ : 빈방 | □ : 예약 중 | ● : 사용중  | ♠ : 청소 중 | ▲ : 사용 불가");
	}

	// 층의 예약가능한 객실의 알파벳을 출력
	public void printRoomsB(int num) {
		for (int i = 0; i < roomsB[num - 1].length; i++) {
			if (roomsB[num - 1][i].getRoomState() == 1) {
				System.out.print(roomsB[num - 1][i].getbSide() + "객실\t");
			}

		}

	}

	// 방상태 확인 b동 123
	public boolean roomBStateCheck1(String roomChar) {
		for (int i = 0; i < roomsB.length; i++) {
			for (int j = 0; j < roomsB[i].length; j++) {
				if (roomsB[i][j].getbSide().equals(roomChar) && roomsB[i][j].getRoomState() == 1) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean roomBStateCheck2(String roomChar) {
		for (int i = 0; i < roomsB.length; i++) {
			for (int j = 0; j < roomsB[i].length; j++) {
				if (roomsB[i][j].getRoomState() == 2 && roomsB[i][j].getbSide().equals(roomChar)) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean roomBStateCheck3(String roomChar) {
		for (int i = 0; i < roomsB.length; i++) {
			for (int j = 0; j < roomsB[i].length; j++) {
				if (roomsB[i][j].getRoomState() == 3 && roomsB[i][j].getbSide().equals(roomChar)) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean reservationCheckRoomB(String bSide) {
		for (int i = 0; i < roomsB.length; i++) {
			for (int j = 0; j < roomsB[i].length; j++) {
				if (roomsB[i][j].getbSide().equals(bSide) && roomsB[i][j].getRoomState() == 1) {
					System.out.println("예약가능한 객실입니다.");
					return true;
				}
			}
		}
		return false;

	}

	// 예약된 고객의 생년월일과 입력한 휴대전화 번호를 비교
	public boolean compPhone(String roomChar, String phoneNum) {
		if (roomCharToArray(roomChar).getCustom().getPhoneNum().equals(phoneNum)) {
			return true;
		}
		return false;
	}

	public boolean compBirth(String roomChar, int birth) {
		if (roomCharToArray(roomChar).getCustom().getBirth() == birth) {
			return true;
		}
		return false;
	}

	// 예약 확인용 고객 확인
	public boolean checkCustomer(String name) {
		for (int i = 0; i < roomsB.length; i++) {
			for (int j = 0; j < roomsB[i].length; j++) {
				if (roomsB[i][j].getCustom().getName().equals(name)) {
					return true;
				}
			}
		}
		return false;
	}

	// 고객이름과 핸드폰 번호 대조하기
	public boolean compNamePhone(String name, String phoneNum) {
		for (int i = 0; i < roomsB.length; i++) {
			for (int j = 0; j < roomsB[i].length; j++) {
				if (roomsB[i][j].getCustom().getName().equals(name)) {
					if (roomsB[i][j].getCustom().getPhoneNum().equals(phoneNum)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean compNameBirth(String name, int birth) {
		for (int i = 0; i < roomsB.length; i++) {
			for (int j = 0; j < roomsB[i].length; j++) {
				if (roomsB[i][j].getCustom().getName().equals(name)) {
					if (roomsB[i][j].getCustom().getBirth() == birth) {
						return true;
					}
				}
			}
		}
		return false;
	}

	// 예약확인
	public void checkCustomer(String name, String phoneNum, int birth) {
		for (int i = 0; i < roomsB.length; i++) {
			for (int j = 0; j < roomsB[i].length; j++) {
				if (roomsB[i][j].getCustom().getName().equals(name)
						&& roomsB[i][j].getCustom().getPhoneNum().equals(phoneNum)
						&& roomsB[i][j].getCustom().getBirth() == birth) {

					System.out.println("예약자 " + roomsB[i][j].getCustom().getName() + "님의 방 정보입니다.");
					System.out.println("사용자는 " + roomsB[i][j].getCustom().getUseName() + "님 입니다.");
					System.out.println(
							roomsB[i][j].getCustom().getName() + "님의 방 번호는 " + roomsB[i][j].getbSide() + "입니다.");
					if (j >= 0 && j < 5) {
						System.out.println(roomsB[i][j].getCustom().getName() + "님의 방은 더블 배드입니다.");
					} else {
						System.out.println(roomsB[i][j].getCustom().getName() + "님의 방은 싱글 배드입니다.");
					}
					if (roomsB[i][j].getCustom().isBreakfast()) {
						System.out.println(roomsB[i][j].getCustom().getName() + "님은 조식을 신청하셨습니다.");
					} else {
						System.out.println(roomsB[i][j].getCustom().getName() + "님은 조식을 신청하지 않으셨습니다.");
					}
				}
			}
		}
	}

	public void reservationRoomB(String roomChar, String name, String useName, String phoneNum, int birth) {
		for (int i = 0; i < roomsB.length; i++) {
			for (int j = 0; j < roomsB[i].length; j++) {
				if (roomsB[i][j].getbSide().equals(roomChar)) {
					roomsB[i][j].setCustom(name, useName, phoneNum, birth);
					roomsB[i][j].setRoomState(2);
					System.out.println("예약되었습니다.");
				}
			}
		}
	}

	public void changeRoom(String roomChar, String changeChar) {
		roomCharToArray(changeChar).setCustom1(roomCharToArray(roomChar).getCustom());
		roomCharToArray(changeChar).setRoomState(2);
		roomCharToArray(roomChar).setCustom("", "", "", 0);
		roomCharToArray(roomChar).setRoomState(1);
	}

	public boolean checkNameRoomB(String name, String roomChar) {
		for (int i = 0; i < roomsB.length; i++) {
			for (int j = 0; j < roomsB[i].length; j++) {
				if (roomsB[i][j].getbSide().equals(roomChar)) {
					if (roomsB[i][j].getCustom().getName().equals(name)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean checkUseNameRoomB(String useName, String roomChar) {
		for (int i = 0; i < roomsB.length; i++) {
			for (int j = 0; j < roomsB[i].length; j++) {
				if (roomsB[i][j].getbSide().equals(roomChar)) {
					if (roomsB[i][j].getCustom().getUseName().equals(useName)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean checkBirthRoomB(int birth, String roomChar) {
		for (int i = 0; i < roomsB.length; i++) {
			for (int j = 0; j < roomsB[i].length; j++) {
				if (roomsB[i][j].getbSide().equals(roomChar)) {
					if (roomsB[i][j].getCustom().getBirth() == birth) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public void deleteRoom(String roomChar, String name, int birth) {
		if (roomCharToArray(roomChar).getRoomState() == 2) {
			if (roomCharToArray(roomChar).getCustom().getName().equals(name)
					&& roomCharToArray(roomChar).getCustom().getBirth() == birth) {
				System.out.println("예약을 취소하시겠습니까?");
				System.out.println("1. 예, 2.아니오");
				int s = scan.nextInt();
				switch (s) {
				case 1:
					roomCharToArray(roomChar).setRoomState(1);
					roomCharToArray(roomChar).setCustom("", "", "", 0, false);
					break;
				case 2:
					System.out.println("예약을 취소하지 않습니다.");
					break;
				}
			}
		}

	}

	public int allPrice() {
		int allCountPrice = 0;
		int allBrkPrice = 0;

		for (int i = 0; i < roomsB.length; i++) {
			for (int j = 0; j < roomsB[i].length; j++) {
				// if (rooms[i][j].getCount() > 0) {
				allCountPrice += roomsB[i][j].getRoomPrice() * roomsB[i][j].getCount();
				// }
				// if (rooms[i][j].getBrkCount() > 0) {
				allBrkPrice += roomsB[i][j].getBrkPrice() * roomsB[i][j].getBrkCount();
				// }
			}
		}
		return allCountPrice + allBrkPrice;
	}

	public boolean breakfastCheck(String roomChar, int eat) {
		if (eat == 1) {
			System.out.println("조식 신청이 되었습니다");
			roomCharToArray(roomChar).setCustom(true);
			return true;
		} else {
			return false;
		}
	}

	public void resetRoomB(String roomChar) {
		if (roomCharToArray(roomChar).getCustom().isBreakfast()) {
			roomCharToArray(roomChar).setBrkCount(roomCharToArray(roomChar).getBrkCount() - 1);
			roomCharToArray(roomChar).setCustom(false);
		}
		roomCharToArray(roomChar).setCustom("", "", "", 0);
		roomCharToArray(roomChar).setRoomState(1);
		roomCharToArray(roomChar).setCount(roomCharToArray(roomChar).getCount() - 1);

	}
}
