package com.kh.view;

import java.util.Scanner;

import com.kh.controller.MemberController;

// View : 사용자가 보게될 시각적인 요소(화면) 출력 및 입력
public class MemberMenu {

	// Scanner 객체 생성 (전역으로 다 쓸 수 있도록)
	private Scanner sc = new Scanner(System.in);
	
	// MemberController 객체 생성 (전역에서 바로 요청할 수 있게끔)
	private MemberController mc = new MemberController();
	
	/**
	 * 사용자가 보게될 첫 화면(메인 화면)
	 */
	public void mainMenu() {
		
		while(true) {
			
			System.out.println("\n== 회원 관리 프로그램 ==");
			System.out.println("1. 회원추가");
			System.out.println("2. 회원 전체 조회");
			System.out.println("3. 회원 아이디 검색");
			System.out.println("4. 회원 이름으로 키워드 검색");
			System.out.println("5. 회원 정보 변경");
			System.out.println("6. 회원 탈퇴");
			System.out.println("0. 프로그램 종료");
			
			System.out.print(">> 메뉴 선택 : ");
			int menu = sc.nextInt();
			sc.nextLine();
			
			switch(menu) {
			case 1: inputMember(); break;
			case 2: mc.selectList(); break;
			case 3: /*  */ break;
			case 4: /*  */ break;
			case 5: /*  */ break;
			case 6: /*  */ break;
			case 0: System.out.println("이용해주셔서 감사합니다."); return;
			default: System.out.println("메뉴를 잘못입력하셨습니다. 다시 입력해주세요.");
			}
			
		}
		
	}
	

	/**
	 * 회원 추가 창(서브화면)
	 * 즉, 추가하고자 하는 회원의 정보를 입력받아서 회원 추가요청하는 창
	 */
	public void inputMember() {
		
		System.out.println("\n==== 회원 추가 ====");
		// 아이디~취미까지 입력받기
		System.out.print("ID : ");
		String userId = sc.nextLine();
		
		System.out.print("Password : ");
		String userPwd = sc.nextLine();
		
		System.out.print("Name : ");
		String userName = sc.nextLine();
		
		System.out.print("Gender(M/F) : ");
		String gender = sc.nextLine().toUpperCase();
		
		System.out.print("Age : ");
		String age = sc.nextLine();
		
		System.out.print("Email : ");
		String email = sc.nextLine();
		
		System.out.print("Phone(without '-') : ");
		String phone = sc.nextLine();
		
		System.out.print("Address : ");
		String address = sc.nextLine();
		
		System.out.print("Hobby(with ',') : ");
		String hobby = sc.nextLine();
		
		// 회원 추가 요청 == Controller메소드 호출
		mc.insertMember(userId, userPwd, userName, gender, age, email, phone, address, hobby);
		
	}
	
	
	
	
	
	
	
	
	// --------------------------- 응답화면 -------------------------------
	/**
	 * 서비스 요청 처리 후 성공했을 경우 사용자가 보게될 응답화면
	 * @param message  성공 메세지
	 */
	public void displaySuccess(String message) {
		System.out.println("\n서비스 요청 성공 : " + message);
	}
	
	/**
	 * 서비스 요청 처리 후 실패했을 경우 사용자가 보게될 응답화면
	 * @param message  실패메세지
	 */
	public void displayFail(String message) {
		System.out.println("\n서비스 요청 실패 : " + message);
	}
	
}
