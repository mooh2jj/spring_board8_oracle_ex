package com.myspring.dsgproj.controller.board;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.myspring.dsgproj.dto.ReplyVO;

//RestController은 스프링 4.0부터 지원
//@Controller, @RestController 차이점은 메서드가 종료되면 화면전환의 유무
//@Controller, ( ※ @Controller인 경우에는 @ResponseBody를 적어줘야 합니다. )

@RestController
//@Controller -> 이걸로 하니까 indert.do 안돼더라. post 에러 뜸 ;;;; => @RestController 써서 해결!
@RequestMapping("/reply/*") 
public class ReplyController {
	
	@Autowired
	private SqlSession sqlSession;
	
	// 댓글 입력
	@RequestMapping(value = "insert.do")
	public void insert(@ModelAttribute("selectViewOne") ReplyVO vo) {
		
		System.out.println("vo 받은 거:" +  vo);
		int result = sqlSession.insert("replymapper.insertReply", vo);
		
		System.out.println("결과 result : "+ result);
	}
	
	
	// 1) Controller 방식 // Rest
	@RequestMapping("list.do")
	public ModelAndView list(ModelAndView mav, @RequestParam("bno") int bno) {
		
		List<ReplyVO> list = sqlSession.selectList("replymapper.listReply", bno);
		
		System.out.println("list :"+ list);
		mav.setViewName("board/replyList");
		mav.addObject("list", list);
		
		return mav;
	}
	// 2) ajax 방식
	@RequestMapping("listJson.do")
	@ResponseBody	// 리턴데이터를 json으로 변환(생략가능)
	public List<ReplyVO> listJson(@RequestParam("bno") int bno) {
		
		List<ReplyVO> list = sqlSession.selectList("replymapper.listReply", bno);
		
		System.out.println("list:"+ list);
		
		return list;
	}
	

}
