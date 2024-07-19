package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

//@SpringBootTest
@Service
@Slf4j
public class MemoRepositoryTest {
	
	@Autowired
	MemoRepository memoRepository;
	
	@Autowired
	bookRepository bookRepository;

//	//@Test
//	public void InsertDummies() {
//		IntStream.rangeClosed(1, 10).forEach(i ->{
//			
//				Memo memo = Memo.builder()
//						.memoText("Sample..." + i)
//						.password("12345678")
//						.build();
//			// Create
//			memoRepository.save(memo);
//		});
//	}
	
	public void join(Memo memo) {
		
		memoRepository.save(memo);
	}
	
	public boolean login(String id, String pw, HttpSession session) {
		
		Optional<Memo> userInfo = memoRepository.findByIdAndPw(id, pw);	
		
//		if (userInfo.isPresent()) {
//		    Memo memo = userInfo.get();
//		    return true;
//		} else {
//			return false;
//		}
		Long cnt = memoRepository.countByIdAndPw(id, pw);

		System.out.println("찾은 유저 수1 : " + cnt);
	
		if(cnt==1) {
			System.out.println("찾은 유저 수2 : " + cnt);
			System.out.println("현재 로그인 된 유저정보 : "+ userInfo.get());
			if(userInfo.isPresent()) {
			    Memo memo = userInfo.get();
				session.setAttribute("id", memo.getId());
				session.setAttribute("name", memo.getName());
				log.info("세션1 : " + session.getAttribute("id"));
				log.info("세션2 : " + session.getAttribute("name"));
				return true;
			}
		}
		return false;
	}
	
	public boolean delete(String id) {
		
		Optional<Memo> user = memoRepository.findById(id);
		
		if(user.isPresent()) {
			Memo user1 = user.get();
			memoRepository.delete(user1);
			System.out.println("회원 찾기 성공");
			return true;
		}
		System.out.println("회원 찾기 실패");
		return false;
	}
	
	public Memo memberInfo(String id) {
		Optional<Memo> user = memoRepository.findById(id);
		if(user.isPresent()) {
			Memo member = user.get();
			return member;
		}
		return null;
	}
	
	public boolean modify(Memo memo) {
				
		Optional<Memo> member = memoRepository.findById(memo.getId());
		
		if(memo.getPw().equals("")) {
			memo.setPw(member.get().getPw());
		}
		
		if(member.isPresent()) {
			memo.setNum(member.get().getNum());
			
			memoRepository.save(memo);
			return true;
		}
		return false;
	}
	
	public Page<BookInfo> getList(int page){
		List<Sort.Order> sorts = new ArrayList<>();

		sorts.add(Sort.Order.desc("createDate"));
		
		// page : 조회할 페이지번호, 3 : 한 페이지에 보여줄 게시물 개수
		Pageable pageable = PageRequest.of(page, 2);
		
		return this.bookRepository.findAll(pageable);
	}
}
