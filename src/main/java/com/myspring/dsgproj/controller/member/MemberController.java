package com.myspring.dsgproj.controller.member;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.myspring.dsgproj.dto.MemberVO;

//import com.myspring.dsgproj.service.member.MemberService;
//import com.myspring.dsgproj.vo.member.MemberVO;

@Controller
@RequestMapping("/member/*")
public class MemberController {
	
	@Autowired
	SqlSession sqlSession;
	
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
	public String userRegPass(MemberVO userVO) {
//		// 회원가입 메서드
//		reg_service.userReg_service(userVO);
//		// 인증 메일 보내기 메서드
//		mailsender.mailSendWithUserKey(userVO.getUserEmail(), userVO.getUserId());
		System.out.println("vo:"+ userVO);
		
		sqlSession.insert("member.insert", userVO);
		
		return "redirect:login.do";
	}

	
	@RequestMapping("loginCheck.do")
	public ModelAndView loginCheck(@ModelAttribute MemberVO vo, HttpSession session) throws Exception {
		
//		boolean result = memberService.loginCheck(vo, session);
//		boolean result = sqlSession.selectOne("member.loginCheck", vo);
		
		String userName = sqlSession.selectOne("member.loginCheck", vo);
		// tptus
		System.out.println("userName: "+userName);
		
		boolean result = (userName == null) ? false : true;
		
		ModelAndView mav = new ModelAndView();
		
		if(result == true) {	// 
			session.setAttribute("userId", vo.getUserId());
			session.setAttribute("userName", vo.getUserName());
			mav.setViewName("home");
			mav.addObject("msg", "success");
		}else {					// 
			mav.setViewName("member/login");	// login.jsp
			mav.addObject("msg", "failure");	// 
		}	
		
		return mav;
	}
	
	@RequestMapping("logout.do")
	public ModelAndView logout(HttpSession session) throws Exception {
		
		session.invalidate();	// 세션 객체 정보 사라짐 x -> 세션의 기능을 중단시키고 무효화 시키는것
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("member/login");
		mav.addObject("msg", "logout");
		
		return mav;
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
