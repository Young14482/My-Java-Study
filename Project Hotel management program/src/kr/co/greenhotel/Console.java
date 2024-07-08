package kr.co.greenhotel;

import java.util.Scanner;

public class Console {

	final String MANAGERPW = "man11";
	final String FRONTPW = "fro22";
	final String CLEANPW = "cle33";
	Scanner scan = new Scanner(System.in);

	// 지배인
	public boolean managerLogIn() {
		int logInCount = 0;
		while (true) {
			System.out.println("비밀번호를 입력해주세요");
			String managerPw = scan.nextLine();
			if (MANAGERPW.equals(managerPw)) {
				System.out.println("지배인님 어서오십시오.");
				return true;
			} else {
				System.out.println("비밀번호가 일치하지 않습니다.");
				logInCount++;
			}
			if (logInCount == 5) {
				System.out.println("비밀번호 입력 횟수를 초과하셨습니다.");
				return false;
			}
		}
	}
	// 프론드 직원 메소드
	public boolean frontLogIn() {
		int logInCount = 0;
		while (true) {
			System.out.println("비밀번호를 입력해주세요");
			String frontPw = scan.nextLine();
			if (FRONTPW.equals(frontPw)) {
				System.out.println("프론트 모드입니다");
				return true;
			} else {
				System.out.println("비밀번호가 일치하지 않습니다.");
				logInCount++;
			}
			if (logInCount == 5) {
				System.out.println("비밀번호 입력 횟수를 초과하셨습니다.");
				return false;
			}
		}
	}
	//청소부
	public boolean cleanLogIn() {
		int logInCount = 0;
		while (true) {
			System.out.println("비밀번호를 입력해주세요");
			String cleanPw = scan.nextLine();
			if (CLEANPW.equals(cleanPw)) {
				System.out.println("청소담당 로그인 확인되었습니다");
				return true;
			} else {
				System.out.println("비밀번호가 일치하지 않습니다.");
				logInCount++;
			}
			if (logInCount == 5) {
				System.out.println("비밀번호 입력 횟수를 초과하셨습니다.");
				return false;
			}
		}
	}
}
