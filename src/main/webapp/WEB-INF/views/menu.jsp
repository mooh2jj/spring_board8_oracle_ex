<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

</head>
<body>
	<a href="${contextPath}/board/list.do">게시판</a>
	<a href="${contextPath}/member/join.do">회원가입</a>
	<c:choose>
		<c:when test="${sessionScope.userId == null}">
			<a href="${contextPath}/member/login.do">로그인</a>
		</c:when>
		<c:otherwise>
			${sessionScope.userName}님이 로그인중입니다.

			<a href="${contextPath}/member/logout.do">로그아웃</a>
		</c:otherwise>
	</c:choose>

	<hr>
</body>
</html>