package com.kh.controller;

import com.kh.model.dao.MemberDao;
import com.kh.model.vo.Member;
import com.kh.view.MemberMenu;

// Controller : View를 통해서 사용자가 요청한 기능에 대해서 처리하는 담당
//				해당 메소드로 전달된 데이터 [가공처리 한 후] Dao로 전달하면서 호출
//				Dao로부터 반환받은 결과에 따라 성공인지 실패인지 판단 후 응답화면 결정 (View 메소드 호출)
// 여기 설명 다시듣기
public class MemberController {

	/**
	 * 사용자의 회원 추가 요청을 처리해주는 메소드
	 * @param userId ~ hobby : 사용자가 입력했던 정보들이 담겨있는 매개변수
	 */
	public void insertMember(String userId, String userPwd, String userName, String gender,
							 String age, String email, String phone, String address, String hobby) {
		
		// view로 부터 전달받을 값을 바로 dao쪽으로 전달 x
		// 어딘가(Member객체)에 주섬주섬 담아서 전달
		
		// 방법 1) 기본생성자로 생성한 후 각필드에 setter메소드를 통해서 일일히 담는 방법
		// 방법 2) 아싸리 매개변수 생성자로 생성과 동시에 담는 방법
		Member m = new Member(userId, userPwd, userName, gender, Integer.parseInt(age), email, phone, address, hobby);
		//System.out.println(m);
		
		int result = new MemberDao().insertMember(m);
		
		if(result > 0) {
			new MemberMenu().displaySuccess("성공적으로 회원 추가 되었습니다.");
		}else {
			new MemberMenu().displayFail("회원 추가를 실패했습니다..");
		}
	}
	
	
	
	/**
	 * 사용자의 회원 전ㄴ체 조회 요청을 처리해주는 메소드
	 */
	public void selectList() {
		
		new MemberDao().selectList();
	}
}
