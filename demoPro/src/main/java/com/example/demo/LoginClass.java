package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class LoginClass {
	
	private	List<MemberInfoClass> memberlist = new ArrayList();
	
	LoginClass() {
		// 데이터 임의로 넣기
		MemberInfoClass memberInfo1 = new MemberInfoClass();
		memberInfo1.setId("user01");
		memberInfo1.setPassword("12345");
		memberInfo1.setName("사용자01");
//		memberInfo.setAddress(null);
//		memberInfo.setAge();
//		memberInfo.setEmail(null);
		
		MemberInfoClass memberInfo2 = new MemberInfoClass();
		memberInfo2.setId("user02");
		memberInfo2.setPassword("12345");
		memberInfo2.setName("사용자02");
		
		MemberInfoClass memberInfo3 = new MemberInfoClass();
		memberInfo3.setId("user03");
		memberInfo3.setPassword("12345");
		memberInfo3.setName("사용자03");
		memberlist.add(memberInfo1);
		memberlist.add(memberInfo2);
		memberlist.add(memberInfo3);
		
		//System.out.println(memberlist);
	}
	
	public boolean login(String id, String pw) {
		boolean isFinding = false;
		for(MemberInfoClass member : memberlist) {
			if(id.equals(member.getId()) && pw.equals(member.getPassword()))
				isFinding=true;
				return isFinding;
		}
		
		return isFinding;
	}
	
	public boolean logout() {
		
		return true;
	}
}