package kr.co.greenhotel;

import java.util.Scanner;

public class Hotel {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		HotelRoomState hotelRoomState = new HotelRoomState();
		Rooms[][] rooms = hotelRoomState.rooms;
		Console console = new Console();

		HotelRoomStateB hotelRoomStateB = new HotelRoomStateB();

		Rooms[][] roomsB = hotelRoomStateB.roomsB;

		String name = "";
		String useName = "";
		String phoneNum = "";
		String roomChar = "";
		String changeChar = "";
		// String roomChar ="";
		int roomNum = 0;
		int birth = 0;
		int roomRating = 0;
		int changeNum = 0;
		boolean brk = false;

		// null회피
		for (int i = 0; i < rooms.length; i++) {
			for (int j = 0; j < rooms[i].length; j++) {
				rooms[i][j] = new Rooms(((i + 2) * 100) + 1 + j, new Customer(name, useName, phoneNum, birth, brk), 0,
						0);
			}
		}
		// null 회피
		for (int i = 0; i < roomsB.length; i++) {
			for (int j = 0; j < roomsB[i].length; j++) {
				roomsB[i][j] = new Rooms('A', new Customer("", "", "", 0, false), 0, 0);
			}
		}
		rooms[2][3].setRoomState(-1); // 404 밴
		rooms[2][13].setRoomState(-1); // 414 밴

		hotelRoomState.ratings();
		hotelRoomStateB.hotelB();

		logIn: while (true) {
			System.out.println("어서오세요. 그린 호텔입니다.");
			System.out.println();
			System.out.println("로그인 하실 계정을 선택하세요");
			System.out.println("1. 매니저 | 2. 프론트 | 3. 청소직원 | other. 종료");
			int hoteluser = scan.nextInt();

			primal: switch (hoteluser) {
			// 지배인
			case 1:
				if (console.managerLogIn() == false) {
					System.out.println("초기화면으로 돌아갑니다.");
					System.out.println();
					break primal;
				}
				int count = 0;
				System.out.println("A동 스위트룸 현황");
				for (int i = 15; i < rooms[3].length; i++) {
					if (hotelRoomState.roomStateCheck3(rooms[3][i].getRoomNum())) {
						System.out.println("체크인 고객: ");
						System.out.print(rooms[3][i].getRoomNum() + "호");
						System.out.println(rooms[3][i].getCustom().getUseName() + "님");
						count++;
					} else if (hotelRoomState.roomStateCheck2(rooms[3][i].getRoomNum())) {
						System.out.println("예약고객: ");
						System.out.print(rooms[3][i].getRoomNum() + "호");
						System.out.println(rooms[3][i].getCustom().getName() + "님");
						count++;
					}
				}
				if (count == 0) {
					System.out.println("스위트룸을 이용중인 고객이 없습니다.");
				}
				System.out.println("B동 VVIP룸 현황");
				if (hotelRoomStateB.roomsB[2][7].getRoomState() == 1) {
					System.out.println("VVIP룸을 이용중인 고객님이 없습니다.");
				}
				if (hotelRoomStateB.roomBStateCheck3(roomsB[2][7].getbSide())) {
					System.out.println("체크인 고객: ");
					System.out.println(roomsB[2][7].getCustom().getUseName() + "님");
				} else if (hotelRoomStateB.roomBStateCheck2(roomsB[2][7].getbSide())) {
					System.out.println("예약고객: ");
					System.out.println(roomsB[2][7].getCustom().getName() + "님");
				}
				System.out.println("A동의 총 매출은 :" + hotelRoomState.allPrice() + "입니다.");
				System.out.println("B동의 총 매출은 :" + hotelRoomStateB.allPrice() + "입니다.");
				System.out.println("호텔의 총 매출은 :" + (hotelRoomState.allPrice() + hotelRoomStateB.allPrice()) + "입니다.");
				System.out.println();

				System.out.println("로그아웃을 원하시면 아무 숫자나 입력해 주세요.");
				int logOut = scan.nextInt();
				scan.nextLine();
				if (logOut != -2147483645) {
					System.out.println("입력이 확인되었습니다.");
					break primal;
				}

				// 프론트
			case 2:
				if (console.frontLogIn() == false) {
					System.out.println("초기화면으로 돌아갑니다.");
					System.out.println();
					break primal;
				}
				System.out.println("동을 선택해주세요.");
				System.out.println("1. A동 | 2. B동 | other. 로그아웃");
				int nums = scan.nextInt();
				scan.nextLine();
				switch (nums) {

				case 1: // A동
					while (true) {
						System.out.println("================================");
						System.out.println("현재 예약상태 ");
						hotelRoomState.printRoomState(rooms);
						System.out.println("================================");
						System.out.println("1. 예약 관리 | 2. 체크인, 워크인 | 3. 체크아웃  | other. 로그아웃 ");
						roomNum = scan.nextInt();
						scan.nextLine();

						A: switch (roomNum) {
						// 예약
						case 1:
							reser: while (true) {
								System.out.println();
								System.out.println("예약문의 입니다.");
								System.out.println(
										"1.등급별 예약가능 객실 확인 | 2.예약하기 | 3.예약취소 | 4.예약확인 | 5. 예약 변경 | other. 초기화면");
								int num2 = scan.nextInt();
								scan.nextLine();
								resar2: switch (num2) {
								case 1:
									while (true) {
										System.out.println("확인하실 객실의 등급을 입력하세요.");
										System.out.println("1. 스탠다드 | 2. 디럭스 | 3. 프리미어 | 4.스위트  | other. 돌아가기");
										roomRating = scan.nextInt();
										scan.nextLine();
										if (roomRating > 0 && roomRating < 5) {
											hotelRoomState.printRooms(roomRating);
										} else {
											break;
										}
									}
									break resar2;
								// 예약하기
								case 2:
									hotelRoomState.printRoomState(rooms); // 방상태 이모지 출력메소드
									while (true) {
										System.out.println();
										System.out.println("예약할 방 번호를 입력");
										System.out.println("-1입력으로 초기화면으로 돌아갑니다.");
										roomNum = scan.nextInt();
										scan.nextLine();
										if (roomNum == -1) {
											System.out.println("'-1'을 입력하셨습니다.");
											System.out.println("메인화면으로 돌아갑니다.");
											break reser;
										} else if (hotelRoomState.roomCheck(roomNum)) {
											if (hotelRoomState.roomStateCheck1(roomNum)) {
												break;
											} else {
												System.out.println("빈방이 아닙니다.");
												System.out.println("다시 입력해주세요.");
											}
										} else {
											System.out.println("잘못된 방 번호입니다.");
											System.out.println("다시 입력해주세요.");
										}
									}
									System.out.println("예약자 성명을 입력해 주세요");
									name = scan.nextLine();
									// 프론트 직원 : "예약자분 이랑 사용자분이 같으실 까요?" >> 예 대답시 두번타이핑 하는식
									System.out.println("사용자 성명을 입력해 주세요");
									useName = scan.nextLine();

									while (true) {
										System.out.println("예약자 휴대폰 번호를 하이픈 없이 입력해주세요(11자리 입력)");
										System.out.println("-1입력으로 초기화면으로 돌아갑니다.");
										phoneNum = scan.nextLine();
										if (phoneNum.equals("-1")) {
											System.out.println("'-1'을 입력하셨습니다.");
											break reser;
										} else if (hotelRoomState.check(phoneNum)) {
											break;
										} else {
											System.out.println("잘못된 전화번호 입력입니다.");
											System.out.println("다시 입력해주세요.");
										}
									}
									while (true) {
										System.out.println("예약자 생년월일 8자리를 입력해주세요");
										System.out.println("-1 입력으로 초기화면으로 돌아갑니다.");
										birth = scan.nextInt();
										scan.nextLine();
										if (birth == -1) {
											System.out.println("'-1'을 입력하셨습니다.");
											break reser;
										} else if (hotelRoomState.check(birth)) {
											break;
										} else {
											System.out.println("잘못된 생년월일 입력입니다.");
											System.out.println("다시 입력해주세요.");
										}
									}
									System.out.println("조식을 신청 하겠습니까?");
									System.out.println("1. 예  | 2. 아니요");
									int eat = scan.nextInt();
									scan.nextLine();
									if (hotelRoomState.breakfastCheck(roomNum, eat)) {
										System.out.println("총 가격은 "
												+ (hotelRoomState.roomNumToArray(roomNum).getRoomPrice()
														+ hotelRoomState.roomNumToArray(roomNum).getBrkPrice())
												+ "입니다.");
										System.out.println("예약 하시겠습니까?");
										System.out.println("1. 예  | 2. 아니요");
										int yes = scan.nextInt();
										if (yes == 1) {
											hotelRoomState.setCustomer(roomNum, useName, name, phoneNum, birth);
											hotelRoomState.roomNumToArray(roomNum)
													.setCount(hotelRoomState.roomNumToArray(roomNum).getCount() + 1);
											hotelRoomState.roomNumToArray(roomNum).setBrkCount(
													hotelRoomState.roomNumToArray(roomNum).getBrkCount() + 1);
											System.out.println("예약 완료되었습니다.");
											break resar2;
										} else {
											System.out.println("예약되지 않았습니다.");
											break resar2;
										}

									} else {
										System.out.println("총 가격은 "
												+ hotelRoomState.roomNumToArray(roomNum).getRoomPrice() + "입니다.");
										System.out.println("예약 하시겠습니까?");
										System.out.println("1. 예  | 2. 아니요");
										int yes = scan.nextInt();
										if (yes == 1) {
											hotelRoomState.setCustomer(roomNum, useName, name, phoneNum, birth);
											hotelRoomState.roomNumToArray(roomNum)
													.setCount(hotelRoomState.roomNumToArray(roomNum).getCount() + 1);
											System.out.println("예약 완료되었습니다.");
										} else {
											System.out.println("예약되지 않았습니다.");
											break resar2;
										}
									}

									break resar2;
								// 예약취소
								case 3:
									while (true) {
										System.out.println();
										System.out.println("예약하신 방 번호를 입력해주세요.");
										System.out.println("-1 입력으로 메인화면으로 돌아갑니다");
										roomNum = scan.nextInt();
										scan.nextLine();
										if (roomNum == -1) {
											System.out.println("'-1'을 입력하셨습니다.");
											System.out.println("메인화면으로 돌아갑니다.");
											break reser;
										} else if (hotelRoomState.roomCheck(roomNum)) {
											if (hotelRoomState.roomStateCheck2(roomNum)) {
												break;
											} else {
												System.out.println("예약된 방이 아닙니다.");
												System.out.println("다시 입력해주세요.");
											}
										} else {
											System.out.println("잘못된 방 번호입니다.");
											System.out.println("다시 입력해주세요.");
										}
									}
									while (true) {
										System.out.println("예약자의 성함을 입력해주세요.");
										System.out.println("-1 입력으로 메인화면으로 돌아갑니다");
										name = scan.nextLine();
										if (name.equals("-1")) {
											System.out.println("'-1'을 입력하셨습니다.");
											System.out.println("메인화면으로 돌아갑니다.");
											break reser;
										} else if (!hotelRoomState.checkCustomer(roomNum, name)) {
											System.out.println("예약자의 이름이 아닙니다.");
											System.out.println("다시 입력해주세요");
										}
										break;
									}
									while (true) {
										System.out.println("예약자 휴대폰 번호를 하이픈 없이 입력해주세요(11자리 입력)");
										System.out.println("-1입력으로 초기화면으로 돌아갑니다.");
										phoneNum = scan.nextLine();
										if (phoneNum.equals("-1")) {
											System.out.println("'-1'을 입력하셨습니다.");
											break reser;
										} else if (hotelRoomState.check(phoneNum)) {
											break;
										} else {
											System.out.println("잘못된 전화번호 입력입니다.");
											System.out.println("다시 입력해주세요.");
										}
									}
									System.out.println("예약자의 생년월일을 입력해주세요.");
									birth = scan.nextInt();
									scan.nextLine();
									while (true) {
										if (!hotelRoomState.check(birth)) {
											System.out.println("잘못된 생년월일 입력입니다.");
											System.out.println("다시 입력해 주세요");
										}
										break;
									}
									if (hotelRoomState.roomNumToArray(roomNum).getCustom().isBreakfast()) {
										hotelRoomState.roomNumToArray(roomNum)
												.setBrkCount(hotelRoomState.roomNumToArray(roomNum).getBrkCount() - 1);
										hotelRoomState.roomNumToArray(roomNum)
												.setCount(hotelRoomState.roomNumToArray(roomNum).getCount() - 1);
									} else {
										hotelRoomState.roomNumToArray(roomNum)
												.setCount(hotelRoomState.roomNumToArray(roomNum).getCount() - 1);
									}
									hotelRoomState.deleteRoom(roomNum, name, birth);

									break;
								// 예약확인
								case 4:
									while (true) {
										System.out.println("예약자의 성함를 입력해주세요.");
										System.out.println("-1입력으로 메인화면으로 돌아갑니다.");
										name = scan.nextLine();
										if (hotelRoomState.checkCustomer(name)) {
											break;
										} else if (name.equals("-1")) {
											System.out.println("되돌아가기");
											break reser;
										} else {
											System.out.println("입력하신 성함은 예약된 방이 없습니다.");
											System.out.println("다시 입력해주세요");
										}
									}

									while (true) {
										System.out.println("예약자의 휴대폰 번호를 입력해주세요.");
										System.out.println("-1입력으로 메인화면으로 돌아갑니다.");
										phoneNum = scan.nextLine();
										if (hotelRoomState.check(phoneNum)) {
											if (hotelRoomState.compNamePhone(name, phoneNum)) {
												break;
											} else {
												System.out.println("예약자의 휴대전화와 맞지않습니다.");
												System.out.println("다시 입력해주세요.");
											}
										} else if (phoneNum.equals("-1")) {
											System.out.println("되돌아가기");
											break reser;
										} else {
											System.out.println("잘못된 전화번호 입력입니다.");
											System.out.println("다시 입력해주세요.");
										}

									}
									while (true) {
										System.out.println("예약자의 생년월일을 입력해주세요.");
										System.out.println("-1입력으로 메인화면으로 돌아갑니다.");
										birth = scan.nextInt();
										scan.nextLine();
										if (hotelRoomState.check(birth)) {
											if (hotelRoomState.compNameBirth(name, birth)) {
												break;
											} else {
												System.out.println("예약자의 생년월일과 맞지않습니다.");
												System.out.println("다시 입력해주세요.");
											}
										} else if (birth == -1) {
											System.out.println("되돌아가기");
											break reser;
										} else {
											System.out.println("잘못된 생년월일 입력입니다.");
											System.out.println("다시 입력해주세요.");
										}

									}
									hotelRoomState.checkCustomer(name, phoneNum, birth);
									break;
								// 예약변경
								case 5:
									while (true) {
										System.out.println("예약하신 방 번호를 입력해주세요");
										System.out.println("-1입력으로 초기화면으로 돌아갑니다.");
										roomNum = scan.nextInt();
										scan.nextLine();
										if (hotelRoomState.roomStateCheck2(roomNum)) {
											break;
										} else if (roomNum == -1) {
											System.out.println("되돌아가기.");
											break reser;

										} else {
											System.out.println("예약상태의 방이 아닙니다.");
											System.out.println("다시 입력해주세요.");
										}
									}
									while (true) {
										System.out.println("예약자 성명을 입력해 주세요");
										System.out.println("-1입력으로 초기화면으로 돌아갑니다.");
										name = scan.nextLine();
										if (hotelRoomState.roomNumToArray(roomNum).getCustom().getName().equals(name)) {
											break;
										} else if (name.equals("-1")) {
											System.out.println("되돌아가기.");
											break reser;
										} else {
											System.out.println("예약자명과 다릅니다.");
											System.out.println("다시 입력해주세요.");
										}
									}
									while (true) {
										System.out.println("예약자 휴대폰 번호를 하이픈 없이 입력해주세요(11자리 입력)");
										System.out.println("-1입력으로 초기화면으로 돌아갑니다.");
										phoneNum = scan.nextLine();
										if (hotelRoomState.check(phoneNum)) {
											if (hotelRoomState.compPhone(roomNum, phoneNum)) {
												break;
											} else {
												System.out.println("예약자의 휴대폰 번호와 일치하지 않습니다.");
											}
										} else if (phoneNum.equals("-1")) {
											System.out.println("되돌아가기.");
											break reser;
										} else {
											System.out.println("잘못된 전화번호 입력입니다.");
											System.out.println("다시 입력해주세요.");
										}
									}

									while (true) {
										System.out.println("예약자 생년월일 8자리를 입력해주세요");
										System.out.println("-1입력으로 초기화면으로 돌아갑니다.");
										birth = scan.nextInt();
										scan.nextLine();
										if (hotelRoomState.check(birth)) {
											if (hotelRoomState.compBirth(roomNum, birth)) {
												break;
											} else {
												System.out.println("예약자 생년월일과 다릅니다");
											}
										} else if (birth == -1) {
											System.out.println("되돌아가기.");
											break reser;
										} else {
											System.out.println("잘못된 생년월일 입력입니다.");
											System.out.println("다시 입력해주세요.");
										}
									}
									System.out.println("사용자 명을 변경하시겠습니까?");
									System.out.println("1. 변경한다 | other. 변경하지 않는다");
									int num = scan.nextInt();
									scan.nextLine();
									if (num == 1) {
										System.out.println("변경하실 사용자 명을 입력해주세요");
										useName = scan.nextLine();
										hotelRoomState.roomNumToArray(roomNum).setCustomer(useName);
									}
									System.out.println("객실을 변경하시겠습니까?");
									System.out.println("1. 변경한다 | other. 변경하지 않는다");
									num = scan.nextInt();
									scan.nextLine();
									if (num == 1) {
										System.out.println("1. A동 | 2. B동 ");
										int yes = scan.nextInt();
										if (yes == 1) {
											while (true) {
												hotelRoomState.printRoomState(rooms);
												System.out.println("예약변경하실 객실 번호를 입력해주세요.");
												System.out.println("-1입력으로 초기화면으로 돌아갑니다.");
												changeNum = scan.nextInt();
												scan.nextLine();
												if (hotelRoomState.roomStateCheck1(changeNum)) {
													break;
												} else if (changeNum == -1) {
													System.out.println("되돌아가기.");
													break reser;
												} else {
													System.out.println("빈 방이 아닙니다.");
													System.out.println("다시 입력해주세요.");
												}
											}
											if (hotelRoomState.roomNumToArray(roomNum).getRoomPrice()
													- hotelRoomState.roomNumToArray(changeNum).getRoomPrice() == 0) {
												System.out.println("예약변경하시겠습니까?");
												System.out.println("1. 예 | 2. 아니오");
												int f = scan.nextInt();
												if (f == 1) {
													hotelRoomState.changeRoom(roomNum, changeNum);
													System.out.println("예약변경이 완료되었습니다.");
													break;
												} else {
													System.out.println("예약변경이 취소되었습니다.");
												}
											} else if (hotelRoomState.roomNumToArray(roomNum).getRoomPrice()
													- hotelRoomState.roomNumToArray(changeNum).getRoomPrice() > 0) {
												System.out.println("환불금액은 " + (hotelRoomState.roomNumToArray(roomNum)
														.getRoomPrice()
														- hotelRoomState.roomNumToArray(changeNum).getRoomPrice())
														+ "입니다.");
												System.out.println("예약변경하시겠습니까?");
												System.out.println("1. 예 | 2. 아니오");
												int f = scan.nextInt();
												if (f == 1) {
													hotelRoomState.changeRoom(roomNum, changeNum);
													System.out.println("예약변경이 완료되었습니다.");
													break;
												} else {
													System.out.println("예약변경이 취소되었습니다.");
												}
											} else if (hotelRoomState.roomNumToArray(roomNum).getRoomPrice()
													- hotelRoomState.roomNumToArray(changeNum).getRoomPrice() < 0) {
												System.out.println("추가 금액은 "
														+ (hotelRoomState.roomNumToArray(changeNum).getRoomPrice()
																- hotelRoomState.roomNumToArray(roomNum).getRoomPrice())
														+ "입니다.");
												System.out.println("예약변경하시겠습니까?");
												System.out.println("1. 예 | 2. 아니오");
												int f = scan.nextInt();
												if (f == 1) {
													hotelRoomState.changeRoom(roomNum, changeNum);
													System.out.println("예약변경이 완료되었습니다.");
													break;
												} else {
													System.out.println("예약변경이 취소되었습니다.");
												}

											}
										} else if (yes == 2) {
											while (true) {
												scan.nextLine();
												hotelRoomStateB.printRoomStateB(roomsB);
												System.out.println("예약변경하실 객실 번호를 입력해주세요.");
												System.out.println("-1입력으로 초기화면으로 돌아갑니다.");
												roomChar = scan.nextLine();
												if (hotelRoomStateB.roomBStateCheck1(roomChar)) {
													break;
												} else if (roomChar.equals("-1")) {
													System.out.println("되돌아가기.");
													break reser;
												} else {
													System.out.println("빈 방이 아닙니다.");
													System.out.println("다시 입력해주세요.");
												}
											}
											if (hotelRoomState.roomNumToArray(roomNum).getRoomPrice()
													- hotelRoomStateB.roomCharToArray(roomChar).getRoomPrice() > 0) {
												System.out.println("환불금액은 " + (hotelRoomState.roomNumToArray(roomNum)
														.getRoomPrice()
														- hotelRoomStateB.roomCharToArray(roomChar).getRoomPrice())
														+ "입니다.");
												System.out.println("예약변경하시겠습니까?");
												System.out.println("1. 예 | 2. 아니오");
												int f = scan.nextInt();
												if (f == 1) {
													if (hotelRoomState.roomNumToArray(roomNum).getCustom()
															.isBreakfast()) {
														hotelRoomStateB.roomCharToArray(roomChar).setCustom(true);
														hotelRoomStateB.roomCharToArray(roomChar).setBrkCount(
																hotelRoomStateB.roomCharToArray(roomChar).getBrkCount()
																		+ 1);
													}
													hotelRoomStateB.reservationRoomB(roomChar, name, useName, phoneNum,
															birth);
													hotelRoomStateB.roomCharToArray(roomChar).setCount(
															hotelRoomStateB.roomCharToArray(roomChar).getCount() + 1);
													hotelRoomState.resetRoom(roomNum);

													System.out.println("예약변경이 완료되었습니다.");
													break;
												} else {
													System.out.println("예약변경이 취소되었습니다.");
												}
											} else if (hotelRoomState.roomNumToArray(roomNum).getRoomPrice()
													- hotelRoomStateB.roomCharToArray(roomChar).getRoomPrice() < 0) {
												System.out.println("추가 금액은 "
														+ (hotelRoomStateB.roomCharToArray(roomChar).getRoomPrice()
																- hotelRoomState.roomNumToArray(roomNum).getRoomPrice())
														+ "입니다.");
												System.out.println("예약변경하시겠습니까?");
												System.out.println("1. 예 | 2. 아니오");
												int f = scan.nextInt();
												System.out.println(roomsB[0][0].getbSide());
												if (f == 1) {
													if (hotelRoomState.roomNumToArray(roomNum).getCustom()
															.isBreakfast()) {
														hotelRoomStateB.roomCharToArray(roomChar).setCustom(true);
														hotelRoomStateB.roomCharToArray(roomChar).setBrkCount(
																hotelRoomStateB.roomCharToArray(roomChar).getBrkCount()
																		+ 1);
													}
													hotelRoomStateB.reservationRoomB(roomChar, name, useName, phoneNum,
															birth);
													hotelRoomStateB.roomCharToArray(roomChar).setCount(
															hotelRoomStateB.roomCharToArray(roomChar).getCount() + 1);
													hotelRoomState.resetRoom(roomNum);

													System.out.println("예약변경이 완료되었습니다.");
													break;
												} else {
													System.out.println("예약변경이 취소되었습니다.");
												}

											}

										}

										else {
											System.out.println("사용자명 변경완료");
										}
									}

									break;
								default:
									System.out.println("메뉴번호 외의 입력이 확인되었습니다.");
									System.out.println("처음으로 돌아갑니다.");
									break A;
								}
								break reser;
							}
							break;
						// 체크인 워크인
						case 2:
							System.out.println("1. 체크인 | 2. 워크인 | other. 초기화면");
							int num2 = scan.nextInt();
							scan.nextLine();
							checkIn: switch (num2) {
							case 1:
								System.out.println();
								System.out.println("예약 고객 확인 절차 입니다.");
								while (true) {
									System.out.println("방 번호를 입력해주세요.");
									System.out.println("'-1'을 입력으로 메인화면으로 돌아갑니다.");
									roomNum = scan.nextInt();
									scan.nextLine();
									if (roomNum == -1) {
										System.out.println("'-1'을 입력하셨습니다.");
										System.out.println("메인화면으로 돌아갑니다.");
										break checkIn;
									} else if (hotelRoomState.roomCheck(roomNum)) {
										if (hotelRoomState.roomStateCheck2(roomNum)) {
											break;
										} else {
											System.out.println("예약된 방이 아닙니다.");
											System.out.println("다시 입력해주세요.");
										}
									} else {
										System.out.println("잘못된 방 번호입니다.");
										System.out.println("다시 입력해주세요.");
									}
								}
								while (true) {
									System.out.println("예약자의 성함을 입력해주세요.");
									System.out.println("-1 입력으로 메인화면으로 돌아갑니다");
									name = scan.nextLine();
									if (name.equals("-1")) {
										System.out.println("'-1'을 입력하셨습니다.");
										System.out.println("메인화면으로 돌아갑니다.");
										break checkIn;
									} else if (!hotelRoomState.checkCustomer(roomNum, name)) {
										System.out.println("예약자의 이름이 아닙니다.");
										System.out.println("다시 입력해주세요");
									} else {
										break;
									}
								}
								while (true) {
									System.out.println("예약자 휴대폰 번호를 하이픈 없이 입력해주세요(11자리 입력)");
									System.out.println("-1 입력으로 메인화면으로 돌아갑니다");
									phoneNum = scan.nextLine();
									if (phoneNum.equals("-1")) {
										System.out.println("'-1'을 입력하셨습니다.");
										System.out.println("메인화면으로 돌아갑니다.");
										break checkIn;
									} else if (!hotelRoomState.check(phoneNum)) {
										System.out.println("잘못된 전화번호 입력입니다.");
										System.out.println("다시입력해주세요.");
									} else if (!hotelRoomState.roomNumToArray(roomNum).getCustom().getPhoneNum()
											.equals(phoneNum)) {
										System.out.println("예약자  전화번호와 다릅니다.");
										System.out.println("다시 입력해주세요.");
									} else {
										break;
									}
								}
								hotelRoomState.roomNumToArray(roomNum).setRoomState(3);
								System.out.println("체크인이 완료 되었습니다");
								break;
							// 워크인
							case 2:
								System.out.println("워크인 고객 체크인 절차 입니다.");
								while (true) {
									System.out.println("방 번호를 입력해주세요.");
									System.out.println("-1 입력으로 메인화면으로 돌아갑니다.");
									roomNum = scan.nextInt();
									scan.nextLine();
									if (roomNum == -1) {
										System.out.println("'-1'을 입력하셨습니다.");
										System.out.println("메인화면으로 돌아갑니다.");
										break checkIn;
									} else if (!hotelRoomState.roomCheck(roomNum)) {
										System.out.println("잘못된 방 번호입니다.");
										System.out.println("다시 입력해주세요.");
									} else if (!hotelRoomState.roomStateCheck1(roomNum)) {
										System.out.println("빈 방이 아닙니다.");
										System.out.println("다시 입력해주세요.");
									} else {
										break;
									}
								}
								System.out.println("사용자 성명을 입력해 주세요");
								useName = scan.nextLine();

								while (true) {
									System.out.println("사용자 휴대폰 번호를 하이픈 없이 입력해주세요(11자리 입력)");
									System.out.println("-1 입력으로 메인화면으로 돌아갑니다.");
									phoneNum = scan.nextLine();
									if (phoneNum.equals("-1")) {
										System.out.println("'-1'을 입력하셨습니다.");
										System.out.println("메인화면으로 돌아갑니다.");
										break checkIn;
									} else if (!hotelRoomState.check(phoneNum)) {
										System.out.println("잘못된 전화번호 입력입니다.");
										System.out.println("다시 입력해주세요");
									} else {
										break;
									}
								}
								while (true) {
									System.out.println("사용자 생년월일 8자리를 입력해주세요");
									System.out.println("-1 입력으로 메인화면으로 돌아갑니다.");
									birth = scan.nextInt();
									scan.nextLine();
									if (birth == -1) {
										System.out.println("'-1'을 입력하셨습니다.");
										System.out.println("메인화면으로 돌아갑니다.");
										break checkIn;
									} else if (!hotelRoomState.check(birth)) {
										System.out.println("잘못된 생년월일 입력입니다.");
										System.out.println("다시 입력해주세요.");
									} else {
										break;
									}
								}
								System.out.println("조식을 신청 하겠습니까?");
								System.out.println("1. 예  | 2. 아니요");
								int eat = scan.nextInt();
								scan.nextLine();
								if (hotelRoomState.breakfastCheck(roomNum, eat)) {
									System.out
											.println("총 가격은 "
													+ (hotelRoomState.roomNumToArray(roomNum).getRoomPrice()
															+ hotelRoomState.roomNumToArray(roomNum).getBrkPrice())
													+ "입니다.");
									System.out.println("워크인 하시겠습니까?");
									System.out.println("1. 예  | 2. 아니요");
									int yes = scan.nextInt();
									if (yes == 1) {
										hotelRoomState.setCustomer1(roomNum, useName, name, phoneNum, birth);
										hotelRoomState.roomNumToArray(roomNum)
												.setCount(hotelRoomState.roomNumToArray(roomNum).getCount() + 1);
										hotelRoomState.roomNumToArray(roomNum)
												.setBrkCount(hotelRoomState.roomNumToArray(roomNum).getBrkCount() + 1);
										System.out.println("워크인이 완료되었습니다.");
										break;
									} else {
										System.out.println("워크인되지 않았습니다.");
										break;
									}

								} else {
									System.out.println(
											"총 가격은 " + hotelRoomState.roomNumToArray(roomNum).getRoomPrice() + "입니다.");
									System.out.println("워크인 하시겠습니까?");
									System.out.println("1. 예  | 2. 아니요");
									int yes = scan.nextInt();
									if (yes == 1) {
										hotelRoomState.setCustomer1(roomNum, useName, name, phoneNum, birth);
										hotelRoomState.roomNumToArray(roomNum)
												.setCount(hotelRoomState.roomNumToArray(roomNum).getCount() + 1);
										System.out.println("워크인이 완료되었습니다.");
									} else {
										System.out.println("워크인되지 않았습니다.");
										break;
									}
								}
								break;

							default:
								System.out.println("메뉴번호 외의 입력이 확인되었습니다.");
								System.out.println("초기 화면으로 돌아갑니다.");
								break;
							}
							break;
						// 체크아웃
						case 3:
							System.out.println("체크아웃 서비스 입니다.");
							System.out.println("체크아웃하실 방 번호를 입력해주세요.");
							roomNum = scan.nextInt();
							scan.nextLine();
							if (!hotelRoomState.roomStateCheck3(roomNum)) {
								System.out.println("방번호를 다시 입력해주세요.");
								break;
							}
							System.out.println("사용자 이름을 입력해주세요");
							useName = scan.next();
							if (useName.equals(hotelRoomState.roomNumToArray(roomNum).getCustom().getUseName())) {
								System.out.println("체크아웃 되었습니다.");
								hotelRoomState.roomNumToArray(roomNum).setRoomState(0);
								hotelRoomState.roomNumToArray(roomNum).setCustom("", "", "", 0, false);
								break;
							} else {
								System.out.println("사용자명이 아닙니다.");
								break;
							}
						default:
							System.out.println("메뉴번호 외의 입력이 확인되었습니다.");
							System.out.println("로그아웃 하시겠습니까?");
							System.out.println("1. 예 | other. 아니요");
							int num = scan.nextInt();
							if (num == 1) {
								System.out.println("'예' 입력 확인 되었습니다.");
								System.out.println("로그아웃됩니다.");
								break primal;
							} else {
								System.out.println("'아니요' 입력 확인 되었습니다.");
								break;
							}
						}
					}
				case 2: // B동
					while (true) {
						System.out.println("================================");
						System.out.println("현재 예약상태 ");
						hotelRoomStateB.printRoomStateB(roomsB);
						System.out.println("================================");
						System.out.println("1. 예약 관리 | 2. 체크인, 체크아웃 | other. 로그아웃 ");
						roomNum = scan.nextInt();
						scan.nextLine();

						bside: switch (roomNum) {
						case 1: // 예약
							System.out.println("==============================");
							System.out.println("예약문의 입니다.");
							System.out.println("1.층별 예약가능 객실 확인 | 2.예약하기 | 3.예약취소 | 4.예약확인 | 5. 예약 변경 | other. 초기화면");
							int num2 = scan.nextInt();
							switch (num2) {
							case 1:
								while (true) {
									System.out.println("층별 예약가능한 객실 정보입니다.");
									System.out.println("보고싶은 층을 입력하세요.(1~3층) | other.나가기  ");
									int floor = scan.nextInt();
									if (floor > 0 && floor < 4) {
										hotelRoomStateB.printRoomsB(floor);
										System.out.println();
									} else {
										System.out.println("나가기");
										break bside;
									}
								}

							case 2:
								scan.nextLine();
								System.out.println("객실예약을 시작하겠습니다.");
								while (true) {
									System.out.println("예약하실 객실의 번호를 입력해주세요(A~Z,VVIP)");
									roomChar = scan.nextLine();
									if (hotelRoomStateB.reservationCheckRoomB(roomChar)) {
										break;
									} else {
										System.out.println("다시 입력해주세요.");
									}
								}

								System.out.println("예약자 성명을 입력해주세요.");
								name = scan.nextLine();
								System.out.println("사용자 성명을 입력해주세요.");
								useName = scan.nextLine();
								while (true) {
									System.out.println("예약자 휴대전화 번호를 입력해주세요");
									phoneNum = scan.nextLine();
									if (hotelRoomState.check(phoneNum)) {
										break;
									} else {
										System.out.println("휴대전화번호를 잘못입력하셨습니다.");
										System.out.println("다시 입력해주세요");
									}
								}
								while (true) {
									System.out.println("예약자 생년월일을 입력해주세요");
									birth = scan.nextInt();
									scan.nextLine();
									if (hotelRoomState.check(birth)) {
										break;
									} else {
										System.out.println("생년월일을 잘못입력하셨습니다.");
										System.out.println("다시 입력해주세요");
									}
								}
								System.out.println("조식을 신청 하겠습니까?");
								System.out.println("1. 예  | 2. 아니요");
								int eat = scan.nextInt();
								scan.nextLine();
								if (hotelRoomStateB.breakfastCheck(roomChar, eat)) {
									System.out.println("총 가격은 "
											+ (hotelRoomStateB.roomCharToArray(roomChar).getRoomPrice()
													+ hotelRoomStateB.roomCharToArray(roomChar).getBrkPrice())
											+ "입니다.");
									System.out.println("예약 하시겠습니까?");
									System.out.println("1. 예  | 2. 아니요");
									int yes = scan.nextInt();
									if (yes == 1) {
										hotelRoomStateB.reservationRoomB(roomChar, name, useName, phoneNum, birth);
										hotelRoomStateB.roomCharToArray(roomChar)
												.setCount(hotelRoomStateB.roomCharToArray(roomChar).getCount() + 1);
										hotelRoomStateB.roomCharToArray(roomChar).setBrkCount(
												hotelRoomStateB.roomCharToArray(roomChar).getBrkCount() + 1);
										System.out.println("예약 완료되었습니다.");
										break bside;
									} else {
										System.out.println("예약되지 않았습니다.");
										break bside;
									}

								} else {
									System.out.println("총 가격은 "
											+ hotelRoomStateB.roomCharToArray(roomChar).getRoomPrice() + "입니다.");
									System.out.println("예약 하시겠습니까?");
									System.out.println("1. 예  | 2. 아니요");
									int yes = scan.nextInt();
									if (yes == 1) {
										hotelRoomStateB.reservationRoomB(roomChar, name, useName, phoneNum, birth);
										hotelRoomStateB.roomCharToArray(roomChar)
												.setCount(hotelRoomStateB.roomCharToArray(roomChar).getCount() + 1);
										System.out.println("예약 완료되었습니다.");
									} else {
										System.out.println("예약되지 않았습니다.");
									}
									break bside;
								}

							case 3:
								scan.nextLine();
								System.out.println("예약취소 항목입니다.");
								while (true) {
									System.out.println("예약을 취소하실 객실의 번호를 입력해 주세요.");
									System.out.println("-1. 나가기");
									roomChar = scan.nextLine();
									if (hotelRoomStateB.roomBStateCheck2(roomChar)) {
										break;
									} else if (roomChar.equals("-1")) {
										System.out.println("나가기");
										break bside;
									} else {
										System.out.println("잘못입력하셨습니다.");
										System.out.println("다시입력해주세요.");
									}
								}
								while (true) {
									System.out.println("예약자 성명을 입력해주세요.");
									System.out.println("-1. 나가기");
									name = scan.nextLine();
									if (hotelRoomStateB.checkNameRoomB(name, roomChar)) {
										break;
									} else if (name.equals("-1")) {
										System.out.println("나가기");
										break bside;
									} else {
										System.out.println("고객의 성명과 예약된 객실의 성명이 같지 않습니다.");
									}
								}
								while (true) {
									System.out.println("예약자의 생년월일을 입력해주세요.");
									System.out.println("-1. 나가기");
									birth = scan.nextInt();
									if (hotelRoomStateB.checkBirthRoomB(birth, roomChar)) {
										break;
									} else if (birth == -1) {
										System.out.println("나가기");
										break bside;
									} else {
										System.out.println("고객의 생년월일과 예약된 객실의 예약자의 생년월일이 같지않습니다.");
									}
								}
								if (hotelRoomStateB.roomCharToArray(roomChar).getCustom().isBreakfast()) {
									hotelRoomStateB.roomCharToArray(roomChar)
											.setBrkCount(hotelRoomStateB.roomCharToArray(roomChar).getBrkCount() - 1);
									hotelRoomStateB.roomCharToArray(roomChar)
											.setCount(hotelRoomStateB.roomCharToArray(roomChar).getCount() - 1);
								} else {
									hotelRoomStateB.roomCharToArray(roomChar)
											.setCount(hotelRoomStateB.roomCharToArray(roomChar).getCount() - 1);
								}
								hotelRoomStateB.deleteRoom(roomChar, name, birth);
								break bside;

							case 4:
								scan.nextLine();
								while (true) {
									System.out.println("예약자의 성함를 입력해주세요.");
									System.out.println("-1입력으로 메인화면으로 돌아갑니다.");
									name = scan.nextLine();
									if (hotelRoomStateB.checkCustomer(name)) {
										break;
									} else if (name.equals("-1")) {
										System.out.println("되돌아가기");
										break bside;
									} else {
										System.out.println("입력하신 성함은 예약된 방이 없습니다.");
										System.out.println("다시 입력해주세요");
									}
								}

								while (true) {
									System.out.println("예약자의 휴대폰 번호를 입력해주세요.");
									System.out.println("-1입력으로 메인화면으로 돌아갑니다.");
									phoneNum = scan.nextLine();
									if (hotelRoomState.check(phoneNum)) {
										if (hotelRoomStateB.compNamePhone(name, phoneNum)) {
											break;
										} else {
											System.out.println("예약자의 휴대전화와 맞지않습니다.");
											System.out.println("다시 입력해주세요.");
										}
									} else if (phoneNum.equals("-1")) {
										System.out.println("되돌아가기");
										break bside;
									} else {
										System.out.println("잘못된 전화번호 입력입니다.");
										System.out.println("다시 입력해주세요.");
									}

								}
								while (true) {
									System.out.println("예약자의 생년월일을 입력해주세요.");
									System.out.println("-1입력으로 메인화면으로 돌아갑니다.");
									birth = scan.nextInt();
									scan.nextLine();
									if (hotelRoomState.check(birth)) {
										if (hotelRoomStateB.compNameBirth(name, birth)) {
											break;
										} else {
											System.out.println("예약자의 생년월일과 맞지않습니다.");
											System.out.println("다시 입력해주세요.");
										}
									} else if (birth == -1) {
										System.out.println("되돌아가기");
										break bside;
									} else {
										System.out.println("잘못된 생년월일 입력입니다.");
										System.out.println("다시 입력해주세요.");
									}

								}
								hotelRoomStateB.checkCustomer(name, phoneNum, birth);
								break;

							case 5:
								scan.nextLine();
								while (true) {
									System.out.println("예약하신 방의 이름을 입력해주세요");
									System.out.println("-1입력으로 메인화면으로 돌아갑니다.");
									roomChar = scan.nextLine().toUpperCase();
									if (hotelRoomStateB.roomBStateCheck2(roomChar)) {
										break;
									} else if (roomChar == "-1") {
										System.out.println("'-1'을 입력하셨습니다.");
										System.out.println("메인화면으로 돌아갑니다.");
										break bside;

									} else {
										System.out.println("예약상태의 방이 아닙니다.");
										System.out.println("다시 입력해주세요.");
									}
								}
								while (true) {
									System.out.println("예약자 성명을 입력해 주세요");
									System.out.println("-1입력으로 메인화면으로 돌아갑니다.");
									name = scan.nextLine();
									if (hotelRoomStateB.roomCharToArray(roomChar).getCustom().getName().equals(name)) {
										break;
									} else if (name.equals("-1")) {
										System.out.println("'-1'을 입력하셨습니다.");
										System.out.println("메인화면으로 돌아갑니다.");
										break bside;
									} else {
										System.out.println("예약자명과 다릅니다.");
										System.out.println("다시 입력해주세요.");
									}
								}
								while (true) {
									System.out.println("예약자 휴대폰 번호를 하이픈 없이 입력해주세요(11자리 입력)");
									System.out.println("-1입력으로 메인화면으로 돌아갑니다.");
									phoneNum = scan.nextLine();
									if (hotelRoomState.check(phoneNum)) {
										if (hotelRoomStateB.compPhone(roomChar, phoneNum)) {
											break;
										} else {
											System.out.println("예약자의 휴대폰 번호와 일치하지 않습니다.");
										}
									} else if (phoneNum.equals("-1")) {
										System.out.println("'-1'을 입력하셨습니다.");
										System.out.println("메인화면으로 돌아갑니다.");
										break bside;
									} else {
										System.out.println("잘못된 전화번호 입력입니다.");
										System.out.println("다시 입력해주세요.");
									}
								}

								while (true) {
									System.out.println("예약자 생년월일 8자리를 입력해주세요");
									System.out.println("-1입력으로 메인화면으로 돌아갑니다.");
									birth = scan.nextInt();
									scan.nextLine();
									if (hotelRoomState.check(birth)) {
										if (hotelRoomStateB.compBirth(roomChar, birth)) {
											break;
										} else {
											System.out.println("예약자 생년월일과 다릅니다");
										}
									} else if (birth == -1) {
										System.out.println("'-1'을 입력하셨습니다.");
										System.out.println("메인화면으로 돌아갑니다.");
										break bside;
									} else {
										System.out.println("잘못된 생년월일 입력입니다.");
										System.out.println("다시 입력해주세요.");
									}
								}
								System.out.println("사용자 명을 변경하시겠습니까?");
								System.out.println("1. 변경한다 | other. 변경하지 않는다");
								int num = scan.nextInt();
								scan.nextLine();
								if (num == 1) {
									System.out.println("변경하실 사용자 명을 입력해주세요");
									useName = scan.nextLine();
									hotelRoomStateB.roomCharToArray(roomChar).setCustomer(useName);
								}
								System.out.println("객실을 변경하시겠습니까?");
								System.out.println("1. 변경한다 | other. 변경하지 않는다");
								num = scan.nextInt();
								scan.nextLine();
								if (num == 1) {

									System.out.println("1. A동 | 2. B동");
									int yes2 = scan.nextInt();
									if (yes2 == 2) {
										while (true) {
											hotelRoomStateB.printRoomStateB(roomsB);
											System.out.println("예약변경하실 객실 번호를 입력해주세요.");
											System.out.println("-1입력으로 메인화면으로 돌아갑니다.");
											changeChar = scan.nextLine().toUpperCase();
											if (hotelRoomStateB.roomBStateCheck1(changeChar)) {
												break;
											} else if (changeChar == "-1") {
												System.out.println("'-1'을 입력하셨습니다.");
												System.out.println("메인화면으로 돌아갑니다.");
												break bside;
											} else {
												System.out.println("빈 방이 아닙니다.");
												System.out.println("다시 입력해주세요.");
											}
										} // 여기부터
										if (hotelRoomStateB.roomCharToArray(roomChar).getRoomPrice()
												- hotelRoomStateB.roomCharToArray(changeChar).getRoomPrice() == 0) {
											System.out.println("예약변경하시겠습니까?");
											System.out.println("1. 예 | 2. 아니오");
											int f = scan.nextInt();
											scan.nextLine();
											if (f == 1) {
												hotelRoomStateB.changeRoom(roomChar, changeChar);
												System.out.println("예약변경이 완료되었습니다.");
												break;
											} else {
												System.out.println("예약변경이 취소되었습니다.");
											}
										} else if (hotelRoomStateB.roomCharToArray(roomChar).getRoomPrice()
												- hotelRoomStateB.roomCharToArray(changeChar).getRoomPrice() > 0) {
											System.out.println("환불금액은 " + (hotelRoomStateB.roomCharToArray(roomChar)
													.getRoomPrice()
													- hotelRoomStateB.roomCharToArray(changeChar).getRoomPrice())
													+ "입니다.");
											System.out.println("예약변경하시겠습니까?");
											System.out.println("1. 예 | 2. 아니오");
											int f = scan.nextInt();
											scan.nextLine();
											if (f == 1) {
												hotelRoomStateB.changeRoom(roomChar, changeChar);
												System.out.println("예약변경이 완료되었습니다.");
												break;
											} else {
												System.out.println("예약변경이 취소되었습니다.");
											}
										} else if (hotelRoomStateB.roomCharToArray(roomChar).getRoomPrice()
												- hotelRoomStateB.roomCharToArray(changeChar).getRoomPrice() < 0) {
											System.out.println("추가 금액은 "
													+ (hotelRoomStateB.roomCharToArray(changeChar).getRoomPrice()
															- hotelRoomStateB.roomCharToArray(roomChar).getRoomPrice())
													+ "입니다.");
											System.out.println("예약변경하시겠습니까?");
											System.out.println("1. 예 | 2. 아니오");
											int f = scan.nextInt();
											scan.nextLine();
											if (f == 1) {
												hotelRoomStateB.changeRoom(roomChar, changeChar);
												System.out.println("예약변경이 완료되었습니다.");
												break;
											} else {
												System.out.println("예약변경이 취소되었습니다.");
											}

										}
									} else if (yes2 == 1) {
										while (true) {
											hotelRoomState.printRoomState(rooms);
											System.out.println("예약변경하실 객실 번호를 입력해주세요.");
											System.out.println("-1입력으로 메인화면으로 돌아갑니다.");
											roomNum = scan.nextInt();
											if (hotelRoomState.roomStateCheck1(roomNum)) {
												break;
											} else if (roomNum == -1) {
												System.out.println("'-1'을 입력하셨습니다.");
												System.out.println("메인화면으로 돌아갑니다.");
												break bside;
											} else {
												System.out.println("빈 방이 아닙니다.");
												System.out.println("다시 입력해주세요.");
											}
										} // 여기부터
										if (hotelRoomStateB.roomCharToArray(roomChar).getRoomPrice()
												- hotelRoomState.roomNumToArray(roomNum).getRoomPrice() > 0) {
											System.out.println("환불금액은 "
													+ (hotelRoomStateB.roomCharToArray(roomChar).getRoomPrice()
															- hotelRoomState.roomNumToArray(roomNum).getRoomPrice())
													+ "입니다.");
											System.out.println("예약변경하시겠습니까?");
											System.out.println("1. 예 | 2. 아니오");
											int f = scan.nextInt();
											scan.nextLine();
											if (f == 1) {
												if (hotelRoomStateB.roomCharToArray(roomChar).getCustom()
														.isBreakfast()) {
													hotelRoomState.roomNumToArray(roomNum).setBrkCount(
															hotelRoomState.roomNumToArray(roomNum).getBrkCount());
													hotelRoomState.roomNumToArray(roomNum).setCustom(true);
												}
												hotelRoomState.setCustomer(roomNum, useName, useName, phoneNum, birth);
												hotelRoomStateB.resetRoomB(roomChar);

												System.out.println("예약변경이 완료되었습니다.");
												break;
											} else {
												System.out.println("예약변경이 취소되었습니다.");
											}
										} else if (hotelRoomStateB.roomCharToArray(roomChar).getRoomPrice()
												- hotelRoomState.roomNumToArray(roomNum).getRoomPrice() < 0) {
											System.out.println("추가 금액은 "
													+ (hotelRoomState.roomNumToArray(roomNum).getRoomPrice()
															- hotelRoomStateB.roomCharToArray(roomChar).getRoomPrice())
													+ "입니다.");
											System.out.println("예약변경하시겠습니까?");
											System.out.println("1. 예 | 2. 아니오");
											int f = scan.nextInt();
											scan.nextLine();
											if (f == 1) {
												if (hotelRoomStateB.roomCharToArray(roomChar).getCustom()
														.isBreakfast()) {
													hotelRoomState.roomNumToArray(roomNum).setBrkCount(
															hotelRoomState.roomNumToArray(roomNum).getBrkCount() + 1);
													hotelRoomState.roomNumToArray(roomNum).setCustom(true);
												}
												hotelRoomState.setCustomer(roomNum, useName, useName, phoneNum, birth);
												hotelRoomState.roomNumToArray(roomNum).setCount(
														hotelRoomState.roomNumToArray(roomNum).getCount() + 1);
												hotelRoomStateB.resetRoomB(roomChar);

												System.out.println("예약변경이 완료되었습니다.");
												break;
											} else {
												System.out.println("예약변경이 취소되었습니다.");
											}

										}

									}
								} else {
									System.out.println("사용자명 변경완료");
								}

								break;
							default:
								System.out.println("메뉴번호 외의 입력이 확인되었습니다.");
								System.out.println("초기화면으로 돌아가시겠습니까?");
								System.out.println("1. 예 | other. 아니요");
								num = scan.nextInt();
								if (num == 1) {
									System.out.println("'예' 입력 확인 되었습니다.");
									System.out.println("초기화면으로 돌아갑니다.");
									break bside;
								} else {
									System.out.println("'아니요' 입력 확인 되었습니다.");
									break;
								}
							}
							break;
						case 2: // 체크인 체크아웃
							System.out.println("==============================");
							System.out.println("체크인, 체크아웃 문의입니다.");
							System.out.println("1.체크인 | 2. 체크아웃  | other. 초기화면");
							num2 = scan.nextInt();
							switch (num2) {

							case 1:
								scan.nextLine();
								System.out.println();
								System.out.println("예약 고객 확인 절차 입니다.");
								while (true) {
									System.out.println("방 번호를 입력해주세요.");
									System.out.println("'-1'을 입력으로 메인화면으로 돌아갑니다.");
									roomChar = scan.nextLine();
									if (roomChar.equals("-1")) {
										System.out.println("'-1'을 입력하셨습니다.");
										System.out.println("메인화면으로 돌아갑니다.");
										break bside;
									} else if (hotelRoomStateB.roomBStateCheck2(roomChar)) {
										break;
									} else {
										System.out.println("예약된 방이 아닙니다.");
										System.out.println("다시 입력해주세요.");
									}
								}
								while (true) {
									System.out.println("사용자의 성함을 입력해주세요.");
									System.out.println("-1 입력으로 메인화면으로 돌아갑니다");
									useName = scan.nextLine();
									if (useName.equals("-1")) {
										System.out.println("'-1'을 입력하셨습니다.");
										System.out.println("메인화면으로 돌아갑니다.");
										break bside;
									} else if (hotelRoomStateB.checkUseNameRoomB(useName, roomChar)) {
										break;
									} else {
										System.out.println("사용자의 이름이 아닙니다.");
										System.out.println("다시 입력해주세요");
									}
								}
								while (true) {
									System.out.println("예약자 휴대폰 번호를 하이픈 없이 입력해주세요(11자리 입력)");
									System.out.println("-1 입력으로 메인화면으로 돌아갑니다");
									phoneNum = scan.nextLine();
									if (phoneNum.equals("-1")) {
										System.out.println("'-1'을 입력하셨습니다.");
										System.out.println("메인화면으로 돌아갑니다.");
										break bside;
									} else if (hotelRoomStateB.compNamePhone(name, phoneNum)) {
										break;
									} else {
										System.out.println("잘못된 전화번호 입력입니다.");
										System.out.println("다시입력해주세요.");
									}
								}
								hotelRoomStateB.roomCharToArray(roomChar).setRoomState(3);
								System.out.println("체크인이 완료 되었습니다");
								break;

							case 2:
								scan.nextLine();
								while (true) {
									System.out.println("체크아웃 서비스 입니다.");
									System.out.println("체크아웃하실 방 번호를 입력해주세요.");
									System.out.println("-1 입력으로 메인화면으로 돌아갑니다");
									roomChar = scan.nextLine();
									if (hotelRoomStateB.roomBStateCheck3(roomChar)) {
										break;
									} else {
										System.out.println("예약된 방이 아닙니다.");
										System.out.println("방번호를 다시 입력해주세요.");
									}
									if (phoneNum.equals("-1")) {
										System.out.println("'-1'을 입력하셨습니다.");
										System.out.println("메인화면으로 돌아갑니다.");
										break bside;
									}
								}
								while (true) {
									System.out.println("사용자 이름을 입력해주세요");
									System.out.println("-1 입력으로 메인화면으로 돌아갑니다");
									useName = scan.next();
									if (hotelRoomStateB.checkUseNameRoomB(useName, roomChar)) {
										System.out.println("체크아웃 되었습니다.");
										hotelRoomStateB.roomCharToArray(roomChar).setRoomState(0);
										hotelRoomStateB.roomCharToArray(roomChar).setCustom("", "", "", 0, false);
										break;
									} else {
										System.out.println("사용자명이 아닙니다.");
										System.out.println("다시 입력해주세요.");
									}
								}
								break;
							default:
								System.out.println("메뉴번호 외의 입력이 확인되었습니다.");
								System.out.println("로그아웃 하시겠습니까?");
								System.out.println("1. 예 | other. 아니요");
								int num = scan.nextInt();
								if (num == 1) {
									System.out.println("'예' 입력 확인 되었습니다.");
									System.out.println("로그아웃됩니다.");
									break bside;
								} else {
									System.out.println("'아니요' 입력 확인 되었습니다.");
									break;
								}
							}
							break;// 체크인 체크아웃 끝 중괄호
						default: // 예약 체크인 아웃
							System.out.println("메뉴번호 외의 입력이 확인되었습니다.");
							System.out.println("로그아웃 하시겠습니까?");
							System.out.println("1. 예 | other. 아니요");
							int num = scan.nextInt();
							if (num == 1) {
								System.out.println("'예' 입력 확인 되었습니다.");
								System.out.println("로그아웃됩니다.");
								break primal;
							} else {
								System.out.println("'아니요' 입력 확인 되었습니다.");
								break;
							}
						} // bside 스위치문 끝 중괄호
					} // 프론트 b동 while문 끝 중괄호
				default: // A B동 선택 디폴트
					System.out.println("메뉴번호 외의 입력이 확인되었습니다.");
					System.out.println("로그아웃 하시겠습니까?");
					System.out.println("1. 예 | other. 아니요");
					int num = scan.nextInt();
					if (num == 1) {
						System.out.println("'예' 입력 확인 되었습니다.");
						System.out.println("로그아웃됩니다.");
						break primal;
					} else {
						System.out.println("'아니요' 입력 확인 되었습니다.");
						break;
					}
				}// A B동 선택 스위치문 끝 중괄호

				// 청소부 로그인
			case 3:
				if (console.cleanLogIn() == false) {
					System.out.println("초기화면으로 돌아갑니다.");
					System.out.println();
					break primal;
				}
				while (true) {
					System.out.println("현재 청소가 필요한 방의 호실 출력합니다.");
					for (int i = 0; i < rooms.length; i++) {
						for (int j = 0; j < rooms[i].length; j++) {
							if (rooms[i][j].getRoomState() == 0) {
								System.out.println(rooms[i][j].getRoomNum() + "호");
							}
						}
					}
					for (int i = 0; i < roomsB.length; i++) {
						for (int j = 0; j < roomsB[i].length; j++) {
							if (roomsB[i][j].getRoomState() == 0) {
								System.out.println(roomsB[i][j].getbSide() + "호");
							}
						}
					}
					System.out.println("항목을 선택하세요");
					System.out.println("1. 청소완료 | other. 로그아웃");
					int num = scan.nextInt();
					scan.nextLine();
					clean: switch (num) {
					case 1:
						while (true) {
							System.out.println("동을 선택해 주세요");
							System.out.println("1. A동 | 2. B동");
							num = scan.nextInt();
							scan.nextLine();
							switch (num) {
							case 1:
								System.out.println("청소가 완료된 방의 호실을 입력해 주세요.");
								roomNum = scan.nextInt();
								scan.nextLine();
								if (hotelRoomState.roomCheck(roomNum) == false) {
									System.out.println("잘못된 번호를 입력하셨습니다.");
									System.out.println("다시 입력해주세요.");
									continue;
								}
								if (hotelRoomState.roomNumToArray(roomNum).getRoomState() != 0) {
									System.out.println("청소가 필요한 방이 아닙니다.");
									System.out.println("메인 화면으로 돌아갑니다.");
									break clean;
								} else {
									System.out.println(roomNum + "호실의 청소가 완료되었습니까?");
									System.out.println("1. 예 | other. 아니요");
									num = scan.nextInt();
									scan.nextLine();
									if (num != 1) {
										System.out.println("메뉴번호 외의 입력이 확인되었습니다.");
										System.out.println("메인 화면으로 돌아갑니다");
										break clean;
									} else {
										hotelRoomState.roomNumToArray(roomNum).setRoomState(1);
										System.out.println("청소가 완료되었습니다.");
										System.out.println("수고하셨습니다.");
									}
								}
								break clean;
							case 2:
								System.out.println("청소가 완료된 방의 이름을 입력해 주세요.");
								roomChar = scan.nextLine().toUpperCase();
								if (hotelRoomStateB.roomCheck(roomChar) == false) {
									System.out.println("잘못된 번호를 입력하셨습니다.");
									System.out.println("다시 입력해주세요.");
									continue;
								}
								if (hotelRoomStateB.roomCharToArray(roomChar).getRoomState() != 0) {
									System.out.println("청소가 필요한 방이 아닙니다.");
									System.out.println("메인 화면으로 돌아갑니다.");
									break clean;
								} else {
									System.out.println(roomChar + "호실의 청소가 완료되었습니까?");
									System.out.println("1. 예 | other. 아니요");
									num = scan.nextInt();
									scan.nextLine();
									if (num != 1) {
										System.out.println("메뉴번호 외의 입력이 확인되었습니다.");
										System.out.println("메인 화면으로 돌아갑니다");
										break clean;
									} else {
										hotelRoomStateB.roomCharToArray(roomChar).setRoomState(1);
										System.out.println("청소가 완료되었습니다.");
										System.out.println("수고하셨습니다.");
									}
								}
								break clean;
							}
							break clean;
						}

					default:
						System.out.println("메뉴번호 외의 입력이 확인되었습니다.");
						System.out.println("로그아웃 하시겠습니까?");
						System.out.println("1. 예 | other. 아니요");
						num = scan.nextInt();
						if (num == 1) {
							System.out.println("'예' 입력 확인 되었습니다.");
							System.out.println("로그아웃됩니다.");
							break primal;
						} else {
							System.out.println("'아니요' 입력 확인 되었습니다.");
							break;
						}
					}
				}
				// 프로그램 종료 >> 최초 스위치
			default:
				System.out.println("메뉴번호 외의 입력이 확인되었습니다.");
				System.out.println("프로그램을 종료 하시겠습니까?");
				System.out.println("1. 예 | other. 아니요");
				int num = scan.nextInt();
				scan.nextLine();
				if (num == 1) {
					System.out.println("'예' 입력 확인 되었습니다.");
					System.out.println("프로그램을 종료합니다.");
					break logIn;
				} else {
					System.out.println("'아니요' 입력 확인 되었습니다.");
					break;
				}
			}
		}
	}
}
