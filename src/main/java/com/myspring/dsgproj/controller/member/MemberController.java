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
		
		System.out.println("result :"+ result);
		
		return result;
	}
	
	
//	@RequestMapping("loginCheck.do")
//	public ModelAndView loginCheck(@ModelAttribute MemberVO vo, HttpSession session) throws Exception {
//		
//		boolean result = memberService.loginCheck(vo, session);
//		
//		ModelAndView mav = new ModelAndView();
//		
//		if(result == true) {	// 
//			mav.setViewName("home");
//			mav.addObject("msg", "success");
//		}else {					// 
//			mav.setViewName("member/login");	// login.jsp
//			mav.addObject("msg", "failure");	// 
//		}	
//		
//		return mav;
//	}
//	
//	@RequestMapping("logout.do")
//	public ModelAndView logout(HttpSession session) throws Exception {
//		
//		memberService.logout(session);
//		
//		ModelAndView mav = new ModelAndView();
//		
//		mav.setViewName("member/login");
//		mav.addObject("msg", "logout");
//		
//		return mav;
//	}
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
