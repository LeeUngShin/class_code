package com.example.demo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.usertype.LoggableUserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j

public class MyController {

	@Autowired
	MemoRepositoryTest memoTest;

	@Autowired
	MemoRepository memoRepository;

	@Autowired 
	bookRepository bookRepository;

	@Autowired
	UploadFileService uploadFileService;

//	@RequestMapping(value="/test", method = RequestMethod.GET)
//	public String testView() {
//		return "test";
//	}
//	
//	@RequestMapping(value="/testResult/{type1}", method = RequestMethod.GET)
//	public String test(HttpServletRequest request, @PathVariable("type1") String type1) {
//
//		return "test2"; 
//		
//	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "home";
	}  

	@RequestMapping(value = "/login", method = RequestMethod.GET)
//	public String login(Model model, @CookieValue(value = "REMEMBER", required = false) Cookie rCookie) {
	public String login(Model model) {

		model.addAttribute("message", "로그인");
		// model.addAttribute("user", new Login());
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String joinProcess(HttpServletRequest request, HttpSession session, HttpServletResponse response,
			@RequestParam(name = "idMemory", defaultValue = "false") boolean idMemory) {

		boolean login = memoTest.login(request.getParameter("id"), request.getParameter("pw"), session);
		Cookie rememberCookie = new Cookie("REMEMBER", request.getParameter("id"));
		rememberCookie.setPath("/");
		boolean check = idMemory;
		System.out.println("아이디를 기억할까 체크여부 : " + check);

		if (check) {
			rememberCookie.setMaxAge(60 * 60 * 24 * 30);

		} else {
			rememberCookie.setMaxAge(0);
		}
		response.addCookie(rememberCookie); // 쿠키추가

		if (login) {
			String loginId = (String) session.getAttribute("id");

			String id = request.getParameter("id");

			Memo member = memoTest.memberInfo(id);
			if (member != null) {
				request.setAttribute("loginId", member.getId());
				request.setAttribute("password", member.getPw());
				request.setAttribute("name", member.getName());
				request.setAttribute("addr", member.getAddr());
				request.setAttribute("age", member.getAge());
				request.setAttribute("email", member.getEmail());
			}

			return "loginSuccess";
			//return "redirect:/loginok";
		} else {

			return "redirect:/login";
		}
	}

//	@RequestMapping(value="/login", method=RequestMethod.POST)
//	public String loginProcess(Model model, HttpServletRequest request) {
//		
//		String id = request.getParameter("id");
//		String pw = request.getParameter("pw");
////		if(id.equals("user01") && pw.equals("1234")) {
////			request.setAttribute("currentId", id);
////			return "loginSuccess";
////		}
////		model.addAttribute("userId", id);  // 데이터 뷰로 넘기는 방법1  
//		// memoTest.InsertDummies();
//		
//		if(id.equals(null) || pw.equals(null) || id.isEmpty() || pw.isEmpty()) {
//			request.setAttribute("message2", "id또는 pw를 입력하세요");
//			return "login";
//		}
//
//		LoginClass login = new LoginClass();
//		if(login.login(id, pw)) {
//			request.setAttribute("userId", id);  // 데이터 뷰로 넘기는 방법2
//			return "loginSuccess";
//			//return "redirect:/";
//		}
//		
//		return "loginFail";
//	}
//	

	@RequestMapping(value = "/loginok", method = RequestMethod.GET)
	public String loginok(HttpServletRequest request, HttpSession session) {
		String userId = (String) session.getAttribute("id");
		Optional<Memo> result = memoRepository.findById(userId);
		Memo resultMemo = result.get();

		request.setAttribute("loginId", userId);
		// request.setAttribute("addr", resultMemo.getAddr());
		return "loginSuccess";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpSession session) {

		LoginClass loginClass = new LoginClass();
		loginClass.logout();
		// HttpSession session = request.getSession(false);

		session.invalidate();
		return "logout";
	}

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String joinForm(Model model) {
		return "join";
	}

	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String joinProcess(HttpSession session, HttpServletRequest request, Model model, @Valid Memo memo) {

		String loginId = (String) session.getAttribute("id");
		// request.setAttribute("loginId", loginId);
		log.info("로그인 아이디세션 : " + loginId);
		String id = request.getParameter("id");
		String pw = request.getParameter("password");
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		int age = Integer.parseInt(request.getParameter("age"));
		String email = request.getParameter("email");

		System.out.println("가입시도 아이디 : " + memo.getId());

		memoTest.join(memo);

		return "redirect:/login";
	}

	@RequestMapping(value = "/delete/", method = RequestMethod.POST)
	public String delete(HttpServletRequest request, HttpSession session) {

		// loginSuccess.jsp에서 로그인했을 때 사용한 아이디를 value값을 hidden 태그를 사용해서 id로 넘김
		String id = request.getParameter("id");
		System.out.println("컨트롤러에서 request로 받은 아이디 : " + id);
		// String id = (String)session.getAttribute("id");
		// System.out.println("컨트롤러 현재 세션 아이디 : " + id);

		boolean logout = memoTest.delete(id);

		if (logout) {
			return "redirect:/";

		}
		return "logoutFail";
	}

	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String modify(Model model, HttpSession session) {

		String id = (String) session.getAttribute("id");

		Optional<Memo> member = memoRepository.findById(id);
		Memo m = null;
		if (member.isPresent()) {
			m = member.get();
		}

		model.addAttribute("id", m.getId());
		model.addAttribute("name", m.getName());
		model.addAttribute("addr", m.getAddr());
		model.addAttribute("age", m.getAge());
		model.addAttribute("email", m.getEmail());

		return "modify";
	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(HttpServletRequest request) {

		Optional<Memo> member = memoRepository.findById(request.getParameter("id"));
		Memo m = null;
		if (member.isPresent()) {
			m = member.get();
		}

		String id = request.getParameter("id");
		String pw = request.getParameter("password");
		String name = request.getParameter("name");
		String addr = request.getParameter("addr");
		int age = Integer.parseInt(request.getParameter("age"));
		String email = request.getParameter("email");

		m = Memo.builder().num(m.getNum()).id(id).pw(pw).name(name).addr(addr).age(age).email(email).build();

		boolean modify = memoTest.modify(m);

		if (modify) {
			System.out.println("회원 수정 성공");
			return "redirect:/login";
		} else {
			System.out.println("회원 수정 실패");
			return "loginSuccess";
		}
	}

	@RequestMapping(value = "/insertbook", method = RequestMethod.GET)
	public String addBook() {
		return "addBook";
	}

	@RequestMapping(value = "/insertbook", method = RequestMethod.POST)
	public String addBookProcess(HttpServletRequest request, @RequestParam("file") MultipartFile file) {

		String savedFileName = uploadFileService.upload(file, request);

		String title = request.getParameter("title");
		String isbn = request.getParameter("isbn");
		String author = request.getParameter("author");
		String date = request.getParameter("publishedDate");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//		LocalDate publishedDate = LocalDate.parse(date, formatter);
		LocalDate localDate = LocalDate.parse(date, formatter);
		LocalDateTime publishedDate = localDate.atStartOfDay();
		String info = request.getParameter("info");
		System.out.println("발행일자 출력해보기 : " + publishedDate);

		if (savedFileName != null) {

			BookInfo book = BookInfo.builder()
					.title(title)
					.isbn(isbn)
					.author(author)
					.publishedDate(publishedDate)
					.filePathStr(savedFileName)
					.info(info).build();

			bookRepository.save(book);
			System.out.println("책등록하러 옴");
		}

		return "complete_book";
	}

//	@RequestMapping(value="/list", method=RequestMethod.GET)
//	public String list(HttpServletRequest request) {
//		
//		List<BookInfo> list = bookRepository.findAll(Sort.by(Sort.Direction.DESC, "publishedDate"));
//		//List<BookInfo> list = bookRepository.findAllByOrderByPublishedDateDesc();
//		//List<BookInfo> list = bookRepository.findAllByTitleContaining("책");
//		//List<BookInfo> list = bookRepository.findAllByTitleContainingOrderByPublishedDateDesc("책");
//		
//		List<BookForm> bookForm = new ArrayList<>();
//		
//		for(BookInfo book : list) {
//			String date = book.getPublishedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//			BookForm bf = BookForm.builder()
//					.num(book.getNum())
//					.title(book.getTitle())
//					.author(book.getAuthor())
//					.info(book.getInfo())
//					.isbn(book.getIsbn())
//					.publishedDate(date)
//					.build();
//			bookForm.add(bf);
//		}
//		request.setAttribute("list", bookForm);
//		
//		return "bookList";
//	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(HttpServletRequest request, @RequestParam(value = "page", defaultValue = "1") int page) {

		Page<BookInfo> paging = memoTest.getList(page - 1);
		request.setAttribute("paging", paging);
		System.out.println("현재 페이지" + (paging.getNumber() + 1));
		int currentPage = paging.getNumber();
		request.setAttribute("currentPage", currentPage + 1);
		int totalPage = paging.getTotalPages();
		request.setAttribute("totalPage", totalPage);
		System.out.println("전체페이지 수 : " + totalPage);	
		
		// 첫번쨰, 마지막 페이지 계산 -> 하단 숫자 한번에 5개 1~5 -> 1/5, 6~10 -> 6/10
		int startPage = ((page-1)/5) * 5 + 1;
		int endPage = Math.min(startPage + 4, totalPage - 1);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		
		return "bookList";
	}

	@RequestMapping(value = "/deleteBook", method = RequestMethod.GET)
	public String deleteBook(HttpServletRequest request) {

		String strNum = request.getParameter("num");
		Long num = Long.parseLong(strNum);

		bookRepository.deleteByNum(num);

//		List<BookInfo> list = bookRepository.findAll(Sort.by(Sort.Direction.DESC, "publishedDate"));
//		request.setAttribute("list", list);

		return "redirect:/list";
		// return "bookList";
	}

	@RequestMapping(value = "/searchBook", method = RequestMethod.GET)
	public String searchBook(HttpServletRequest request) {

		String key = request.getParameter("key");
		List<BookInfo> searchResult = bookRepository.findAllByTitleContaining(key);

		request.setAttribute("list", searchResult);

		return "bookList";
	}

	@RequestMapping(value = "/modifyBook", method = RequestMethod.GET)
	public String modifyBook(HttpServletRequest request) {

		String num = request.getParameter("num");
		Long lNum = Long.parseLong(num);

		Optional<BookInfo> book = bookRepository.findById(lNum);
		BookInfo b = null;

		if (book.isPresent())
			b = book.get();

		String formatDate = b.getPublishedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		request.setAttribute("title", b.getTitle());
		request.setAttribute("author", b.getAuthor());
		request.setAttribute("isbn", b.getIsbn());
		request.setAttribute("info", b.getInfo());
		request.setAttribute("publishedDate", formatDate);
		System.out.println(b.getPublishedDate());

		return "modifyBook";
	}

	@RequestMapping(value = "/modifytbook", method = RequestMethod.POST)
	public String modifyBookProccess(HttpServletRequest request) {
		
		Optional<BookInfo> book = bookRepository.findByIsbn(request.getParameter("isbn"));
		
		if (!book.isPresent())
			return "fail";
		
		String title = request.getParameter("title");
		String isbn = request.getParameter("isbn");
		String author = request.getParameter("author");
		String publishedDate = request.getParameter("publishedDate");
		String info = request.getParameter("info");

		BookInfo b = book.get();

		String date = request.getParameter("publishedDate"); // "2024-07-12"; 형태

		// DateTimeFormatter를 사용하여 문자열의 포맷을 지정
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		// LocalDate로 문자열을 파싱
		LocalDate localDate = LocalDate.parse(date, formatter);

		// LocalDate를 LocalDateTime으로 변환
		LocalDateTime localDateTime = localDate.atStartOfDay();

		BookInfo b2 = BookInfo.builder().num(b.getNum()).title(title).isbn(isbn).author(author)
				.publishedDate(localDateTime).info(info).build();

		bookRepository.save(b2);

		return "redirect:/list";
	}
}
