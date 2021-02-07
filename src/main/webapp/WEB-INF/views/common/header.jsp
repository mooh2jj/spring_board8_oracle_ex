<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${contextPath}/resources/css/common.css"  type="text/css">
</head>
<body>

<header class="header_class1">
	<h3>header</h3>
	<a href="${contextPath}/board/list.do" onclick="a()">글목록</a>
	<a href="${contextPath}/board/write.do">글등록하기</a>
	<a href="${contextPath}/member/join.do">회원가입</a>
	
<%-- 	<c:if test="${msg == 'success'}">
		<h2>${sessionScope.userName}님 환영합니다.</h2>
	</c:if> --%>
<%-- 	
	<c:choose>
		<c:when test="${sessionScope.auth == null}">
			<a href="${contextPath}/session/login.do">로그인</a>
		</c:when>
		<c:otherwise>
			 로그인중입니다. <a href="${contextPath}/session/logout.do">로그아웃</a>
			<div style ="font-size:1.2em; font-weight: bold;  color: green;">${sessionScope.username}님 환영합니다!</div>
		</c:otherwise>
	</c:choose>
	 --%>
</header>
</body>
</html>