package com.kh.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.controller.MemberController;
import com.kh.model.vo.Member;

// View : 사용자가 보게될 시각적인 요소(화면) 출력 및 입력
public class MemberMenu {

	private Scanner sc = new Scanner(System.in);
	
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
			System.out.println("7. 로그인");
			System.out.println("0. 프로그램 종료");
			
			System.out.print(">> 메뉴 선택 : ");
			int menu = sc.nextInt();
			sc.nextLine();
			
			switch(menu) {
			case 1: inputMember(); break;
			case 2: mc.selectList(); break;
			case 3: mc.selectByUserId(inputMemberId());
					break;
			case 4: mc.selectByUserName(inputMemberName());
					break;
			case 5: updateMember(); break;
			case 6: mc.deleteMember(inputMemberId());
					break;
			case 7: loginMember(); break;
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
		
		mc.insertMember(userId, userPwd, userName, gender, age, email, phone, address, hobby);
		
	}
	
	/**
	 * 사용자에게 회원 아이디 입력받은 후 그때 입력된 값을 반환시켜주는 메소드
	 * @return 사용자가 입력한 아이디값
	 */
	public String inputMemberId() {
		System.out.print("\n회원 아이디 입력 : ");
		return sc.nextLine();
	}
	
	/**
	 * 사용자에게 검색할 회원명(키워드) 입력받은 후 그때 입력된 값을 반환시켜주는 메소드
	 * @return  사용자가 입력한 회원명(키워드)
	 */
	public String inputMemberName() {
		System.out.print("\n회원 이름(키워드) 입력 : ");
		return sc.nextLine();
	}
	
	/**
	 * 사용자에게 변경할 정보들(비번, 이메일, 전화번호, 주소)과 해당 회원의 아이디 입력받는 화면
	 */
	public void updateMember() {
		
		System.out.println("\n==== 회원 정보 변경 ====");
		
		// 회원 아이디, 변경할 비밀번호, 변경할 이메일, 변경할 전화번호, 변경할 주소)
		String userId = inputMemberId();
		
		System.out.print("변경할 암호 : ");
		String userPwd = sc.nextLine();
		
		System.out.print("변경할 이메일 : ");
		String email = sc.nextLine();
		
		System.out.print("변경할 전화번호 : ");
		String phone = sc.nextLine();
		
		System.out.print("변경할 주소 : ");
		String address = sc.nextLine();
		
		mc.updateMember(userId, userPwd, email, phone, address);
	}
	
	/**
	 * 로그인 요청하는 창
	 */
	public void loginMember() {
		System.out.println("\n==== 로그인 ====");
		
		String userId = inputMemberId();
		
		System.out.print("회원 비밀번호 입력 : ");
		String userPwd = sc.nextLine();
		
		mc.loginMember(userId, userPwd);
		
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
	
	/**
	 * 조회 서비스 요청시 조회결과가 없을 경우 사용자가 보게될 응답화면
	 * @param message
	 */
	public void displayNoData(String message) {
		System.out.println("\n" + message);
	}
	
	public void displayMemberList(ArrayList<Member> list) {
		System.out.println("\n조회된 데이터는 다음과 같습니다.\n");
		
		
		for(Member m : list) { 
			System.out.println(m);
		}
	}
	
	/**
	 * 조회 서비스 요청시 조회결과가 한 행일 경우 사용자가 보게될 응답화면
	 * @param m
	 */
	public void displayMember(Member m) {
		System.out.println("\n조회된 데이터는 다음과 같습니다.\n");
		System.out.println(m);
	}
	
}
