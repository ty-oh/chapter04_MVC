<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Bootstrap Core CSS -->
    <link href="/resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="/resources/vendor/metisMenu/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="/resources/dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="/resources/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<style type="text/css">
	.wrap-div{
		width: 500px;
		margin: auto;
		text-align: center;
	}
</style>
</head>
<body>
	<div class="wrap-div">
		<h1>Custom Login Page</h1>
		<h2>${error }</h2>
		<h2>${logout }</h2>
		
		<form action="/login" method="post">
			<fieldset>
				<div class="form-group">
					<input type="text" name="username" class="form-control" placeholder="userid">
				</div>
				<div class="form-group">
					<input type="password" name="password" class="form-control" placeholder="password">
				</div>
				<div class="checkbox">
					<label>
						<input type="checkbox" name="remember-me">아이디 기억하기
					</label>
				</div>
				
				<a href="index.html" class="btn btn-lg btn-success btn-block">Login</a>
				
				<!-- 데이터 위변조 방지하기 위함 security를 사용할 때 로그인에 반드시 넣어주도록 하자 -->
				<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
			</fieldset>
		</form>
	</div>

	<!-- jQuery -->
    <script src="/resources/vendor/jquery/jquery.min.js"></script>
    <!-- Bootstrap Core JavaScript -->
    <script src="/resources/vendor/bootstrap/js/bootstrap.min.js"></script>
    <!-- Metis Menu Plugin JavaScript -->
    <script src="/resources/vendor/metisMenu/metisMenu.min.js"></script>
    <!-- Custom Theme JavaScript -->
    <script src="/resources/dist/js/sb-admin-2.js"></script>
    
    <script type="text/javascript">
       $(".btn-success").on("click", function(e){
           e.preventDefault();
           $("form").submit();
       });
   </script>

</body>
</html>