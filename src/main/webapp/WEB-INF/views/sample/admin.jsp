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
	<h1>/sample/admin page</h1>
	<!-- security context에서 관리하는 로그인 객체 -->
	
	<p>principal : <sec:authentication property="principal"/></p>
	<p>MemberVO : <sec:authentication property="principal.member"/></p>
	<!-- vo에 있는 username -->
	<p>사용자 이름 : <sec:authentication property="principal.member.username"/></p>
	<!-- security context에서는 id를 username에 저장한다. -->
	<!-- vo안에 있는 userid 필드를 통해 가져올 수도있다. -->
	<p>사용자 id : <sec:authentication property="principal.username"/></p>
	<p>사용자 권한 리스트 : <sec:authentication property="principal.member.authList"/></p>
	
	<a href="/customLogoutGet">logout</a>
</body>
</html>