<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<%
	request.setCharacterEncoding("UTF-8");
%>
<html>
<head>
	<title>Home</title>
<%@ include file="/WEB-INF/views/common/header.jsp" %>	
</head>
<script type="text/javascript">
var msg = "${msg}";
var sessionuserName= "${sessionScope.userName}";	// 안잡힘.

if(msg == 'success'){
	
	alert(sessionuserName+"님 게시판에 오신 걸 환영합니다!!");
	location.href = "${contextPath}/board/list.do";
}else{
	location.href = "${contextPath}/member/login.do";
}
</script>
<body>
<%-- <%@ include file="/WEB-INF/views/menu.jsp" %>
	<c:if test="${msg == 'success'}">
		<h2>${sessionScope.userName}(${sessionScope.userId})님 환영합니다.</h2>
	</c:if> --%>
</body>
</html>
