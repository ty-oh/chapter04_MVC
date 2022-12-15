<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
	<form action="/board/list" method="get" id="startForm">
		<input type="hidden" name="pageNum" value="1">
		<input type="hidden" name="amount" value="10">
	</form>
	<script type="text/javascript">
		document.getElementById('startForm').submit();
	</script>
	<h1>
		Hello world!  
	</h1>
	
	<P>  The time on the server is ${serverTime}. </P>
</body>
</html>
