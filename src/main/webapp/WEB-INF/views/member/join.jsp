<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>join</title>
<link rel="stylesheet" href="${contextPath}/resources/css/common.css"  type="text/css">

<script src="http://code.jquery.com/jquery-2.2.1.min.js"></script>
<script type="text/javascript">

$(function(){
	
	$(".id-msg1").hide();
	$(".id-msg2").hide(); 
	
	$("#loginId").blur(function() {
		/* ajax idcheck */
		$.ajax({
			url: "${contextPath}/member/idcheck.do",/*idcheck.do */
			type: "post",
			data: { "id":$("#loginId").val()},
			success: function(result){
				/* alert(parseInt(result)); */
				
				if(parseInt(result) == 1){
					$(".id-msg2").show();
					$(".id-msg1").hide();
				}else{
					$(".id-msg1").show();
					$(".id-msg2").hide();
				}
			}
		});
	});

});

	// 정규 표현식
	function checkForm() {

		/* alert(loginId.value); */
 		form.loginId.value = form.loginId.value.trim();
		if (form.loginId.value.length == 0) {
			alert("로그인 아이디를 입력해주세요.");
			form.loginId.focus();

			return false;
		}
		
		if( form.loginId.value.length < 4){
			alert('로그인 아이디를 4자 이상 입력해주세요.');
			form.loginId.focus();

			return false;
		}
		
		if( isAlphaNumeric(form.loginId.value) == false ){
			alert('로그인 아이디를 영문자 소문자와 숫자만 사용할 수 있습니다.');
			form.loginId.focus();
			
			return false;
		}
		
		form.loginId.value = form.loginId.value.toLowerCase();
		
		form.loginPw.value = form.loginPw.value.trim();
		if (form.loginPw.value.length == 0) {
			alert("로그인 비밀번호를 입력해주세요.");
			form.loginPw.focus();

			return false;
		}

		form.loginPwConfirm.value = form.loginPwConfirm.value.trim();
		if (form.loginPwConfirm.value.length == 0) {
			alert("로그인 비밀번호 확인을 입력해주세요.");
			form.loginPwConfirm.focus();

			return false;
		} 

/* 		if (form.loginPwConfirm.value != form.loginPwConfirm.value) {
			alert('로그인 비밀번호 확인이 일치하지 않습니다.');
			form.loginPwConfirm.focus();

			return false;
		} */

		if (form.name.value != form.name.value) {
			alert('로그인 이름을  입력해주세요.');
			form.name.focus();

			return false;
		}
		
		if( form.name.value.length < 2){
			alert('로그인 이름을 2자 이상 입력해주세요.');
			form.name.focus();

			return false;
		}

		form.submit();

	}
	
	function isAlphaNumeric(str) {
		  var code, i, len;

		  for (i = 0, len = str.length; i < len; i++) {
		    code = str.charCodeAt(i);
		    if (!(code > 47 && code < 58) && // numeric (0-9)
		        !(code > 64 && code < 91) && // upper alpha (A-Z)
		        !(code > 96 && code < 123)) { // lower alpha (a-z)
		      return false;
		    }
		  }
		  return true;
	}
	
</script>

</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
	<form name="form" id="form" action="${contextPath}/member/reg" method="post">
		<!-- action="${contextPath}/member/doWrite" -->
		<table class="table1_class" border="1" width="700px" >	<!-- border="1" width="700px" -->

			<thead>
				<tr>
					<th colspan="2" bgcolor="#E4F7BA">회원 가입</th>
				</tr>
			</thead>
			<tbody>
			<tr>
				<th>아이디</th>
				<td>
					<input maxlength="10" autocomplete="off" autofocus="autofocus" placeholder="로그인 아이디를 입력해주세요." type="text" id="loginId" name="userId">
					<span class="id-msg1" style="color:blue; font-size:0.8rem;">멋진 아이디! 사용가능한 아이디입니다!</span>
					<span class="id-msg2" style="color:red; font-size:0.8rem;">사용중이거나 휴먼상태인 아이디입니다!</span>	
				</td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input placeholder="로그인  패스워드를 입력해주세요." type="password" name="userPw" ></td>
			</tr>
			<!-- <tr>
				<th>비밀번호 확인</th>
				<td><input placeholder="로그인 패스워드 확인을 입력해주세요." type="password" name="loginPwConfirm"></td>
			</tr> -->
			<tr>
				<th>이름</th>
				<td><input placeholder="로그인 이름을 입력해주세요." type="text" name="userName"></td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><input placeholder="이메일 이름을 입력해주세요." type="email" name="userEmail"></td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><input id="emailAuth" type="button" value="이메일 인증받기(이메일 보내기)"/></td>
			</tr>
			<tr>
				<th>회원가입</th>
				<td><input type="submit" value="가입" id="register" /> <input type="reset"  value="취소"></td>
			</tr>
			</tbody>
		</table>
	</form>
	
	<script type="text/javascript">
	$(document).ready(function(){
		var form = $("#form");
		
		$("#emailAuth").on("click", function(e){
			e.preventDefault();
			form.attr("action","${contextPath}/member/emailAuth.do");
			form.attr("method","post");
			form.submit();
		});
	});
	
	</script>
<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body>
</html>