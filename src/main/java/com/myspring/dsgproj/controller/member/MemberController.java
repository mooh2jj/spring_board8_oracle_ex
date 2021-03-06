package com.myspring.dsgproj.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myspring.dsgproj.dto.MemberVO;
import com.myspring.dsgproj.service.UserMailSendService;

//import com.myspring.dsgproj.service.member.MemberService;
//import com.myspring.dsgproj.vo.member.MemberVO;

@Controller
@RequestMapping("/member/*")
public class MemberController {
	
	@Autowired
	SqlSession sqlSession;
	
	@Autowired
	UserMailSendService mailsender;
	
//	@Autowired
//	MemberService memberService;
	
	@RequestMapping("login.do")
	public String login() {
		
		return "member/login";
	}
	
	@RequestMapping("join.do")
	public String join() {
		
		return "member/join";
	}
	
	@ResponseBody
	@RequestMapping(value = "idcheck.do", method = RequestMethod.POST)
	public String idcheck(HttpServletRequest request) {
		
		String id = request.getParameter("id").trim();
		
		System.out.println("id:"+ id);
		
		int resultCount = sqlSession.selectOne("member.idCheck", id);
		// 1, 0
		String result = String.valueOf(resultCount);
		
		System.out.println("idcheck- result :"+ result);
		
		return result;
	}
	
	
	// 회원가입 컨트롤러
	@RequestMapping(value = "/reg", method = RequestMethod.POST)
	public String userRegPass(MemberVO userVO, HttpServletRequest request) {
//		// 회원가입 메서드
//		reg_service.userReg_service(userVO);
//		// 인증 메일 보내기 메서드
		System.out.println("userVO:"+ userVO);		
		sqlSession.insert("member.insert", userVO);
		
//		mailsender.mailSendWithUserKey(userVO.getUserEmail(), userVO.getUserId(), request);
		
		return "redirect:login.do";
	}

	@RequestMapping(value = "/emailAuth.do", method = RequestMethod.POST)
	public void emailAuth(MemberVO userVO, HttpServletRequest request) {
	
		mailsender.mailSendWithUserKey(userVO.getUserEmail(), userVO.getUserId(), request);
		
	}
	
	@RequestMapping("loginCheck.do")
	public String loginCheck(@ModelAttribute MemberVO vo, HttpSession session, Model model) throws Exception {
		
		String userName = sqlSession.selectOne("member.loginCheck", vo);
		System.out.println("userName: "+userName);
		
		boolean result = (userName == null) ? false : true;
		
		if(result == true) {	
			// 세션변수 저장 
			session.setAttribute("userId", vo.getUserId());
			session.setAttribute("userName", vo.getUserName());

			model.addAttribute("msg", "success");
			return "home";
		}else {					// 

			model.addAttribute("msg", "failure");
			return "member/login";
		}	
		
	}
	
	@RequestMapping("logout.do")
	public String logout(HttpSession session, Model model) throws Exception {
		
		session.invalidate();	// 세션 객체 정보 사라짐 x -> 세션의 기능을 중단시키고 무효화 시키는것
		
		model.addAttribute("msg", "logout");
		
		return "member/login";
	}
//	
//	
//	@RequestMapping(value = "write.do", method = RequestMethod.POST)
//	public String insert(MemberVO vo) throws Exception{
//		
//		System.out.println("vo:"+ vo);
//		
//		sqlSession.insert("member.insert", vo);
//		
//		return "redirect:login.do";
//	}
	
}
