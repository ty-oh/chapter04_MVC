<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>
	<!-- 
		hasRole([role])				해당 권한이 있으면 true
		hasAuthority([authority])
		
		hasAnyRole([role, role2]) 	여러 권한들 중 하나라도 해당하는 권한이 있으면 true
		hasAnyAuthority([authority])
		
		principal 				현재 사용자 정보를 의미
		permitAll				모든 사용자에게 허용
		denyAll					모든 사용자에게 거부
		isAnonymous()			익명의 사용자의 경우(로그인을 하지 않은 경ㅇ우)
		isAuthenticated()		인증된 사용자라면 true
		isFullyAuthenticated()	Remember-me 로 인증된 것이 아닌 사용자의 경우 true
	 -->

	<h1>/sample/all page</h1>
	
	<sec:authorize access="isAnonymous()">
		<a href="/customLogin">로그인</a>
	</sec:authorize>
	<sec:authorize access="isAuthenticated()">
		<a href="/customLogout">로그아웃</a>
	</sec:authorize>
	<!-- 
		Remember-me -> 데이터 베이스에 저장됨.
	
	 -->
</body>
</html>