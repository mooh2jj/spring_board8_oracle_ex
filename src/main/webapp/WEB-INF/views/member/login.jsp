<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 페이지</title>
<script src="http://code.jquery.com/jquery-2.2.1.min.js"></script>
<script>
	$(document).ready(function() {
		$("#btnLogin").click(function() {
			// 태그.val() : 태그에 입력된 값
			// 태그.val("값") : 태그의 값을 변경
			var usetId = $("#userId").val();
			var usetPw = $("#userPw").val();
         if(userId == ""){
            alert("아이디를 입력하세요.");
            $("#userId").focus();
            return;
         }
         if(userPw == ""){
            alert("비밀번호를 입력하세요.");
            $("#userPw").focus();
            return;
         } 
		 // 폼 내부의 데이터를 전송할 주소		
         document.form1.action ="${contextPath}/member/loginCheck.do";
         document.form1.submit();

		});
	});
</script>

</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
	<h2>로그인</h2>
	<form name="form1" method="post">
		<table border="1" witdh="400px">
			<tr>
				<td>아이디</td>
				<td><input name="userId" id="userId"></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="userPw" id="userPw"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<button type="button" id="btnLogin">로그인</button>
				<c:if test="${msg == 'failure'}">
					<div style="color: red">
						아이디 또는 비밀번호가 일치하지 않습니다.
					</div>
				</c:if>
				<c:if test="${msg == 'logout'}">
					<div style="color: red">
						로그아웃되었습니다.
					</div>
				</c:if>	
				</td>
			</tr>
		</table>
	</form>
<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>	
</body>
</html>