<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../include/header.jsp" %>

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">게시글 등록</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">게시글 목록</div>
			<!-- /.panel-heading -->
			<div class="panel-body">
				<form action="/board/register" method="post" role="form">
					<div class="form-group">
						<label>Title</label>
						<input class="form-control" name="title"/>
					</div>
					<div class="form-group">
						<label>Textarea</label>
						<textarea rows="3" class="form-control" name="content"></textarea>
					</div>
					<div class="form-group">
						<label>Writer</label>
						<input class="form-control" name="writer"/>
					</div>
					<button class="btn btn-degault" type="submit">등록</button>
					<button class="btn btn-degault" type="reset">취소</button>
				</form>
			</div>
			<!-- /.panel-body -->
		</div>
		<!-- /.panel -->
	</div>
	<!-- /.col-lg-12 -->	
</div>
<!-- /.row -->

<%@ include file="../include/footer.jsp" %>