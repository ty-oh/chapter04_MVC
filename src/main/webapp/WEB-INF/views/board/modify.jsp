<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../include/header.jsp" %>

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">게시글 화면</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">게시글 목록</div>
			<!-- /.panel-heading -->
			<form action="/board/modify" method="post" id="operForm">
				<div class="panel-body">
					<div class="form-group">
						<label>글 번호</label>
						<input class="form-control" name="bno" value="${vo.bno }" readonly="readonly"/>
					</div>
					<div class="form-group">
						<label>글 제목</label>
						<input class="form-control" name="title" value="${vo.title }"/>
					</div>
					<div class="form-group">
						<label>글 내용</label>
						<textarea rows="3" class="form-control" name="content">${vo.content }</textarea>
					</div>
					<div class="form-group">
						<label>작성자</label>
						<input class="form-control" name="writer" value="${vo.writer }" readonly="readonly"/>
					</div>
				</div>
				<div class="modal-footer">
					<button class="btn btn-warning" type="submit" data-oper="modify">글 수정</button>
					<button class="btn btn-danger" type="submit" data-oper="remove">글 삭제</button>
					<button class="btn btn-primary" type="submit" data-oper="list">목록</button>
				</div>
			</form>
			
			<!-- /.panel-body -->
		</div>
		<!-- /.panel -->
	</div>
	<!-- /.col-lg-12 -->	
</div>
<!-- /.row -->

<script type="text/javascript">
	$(function() {
		var operForm = $("#operForm");
		
		$("button").on("click", function(e) {
			e.preventDefault();
			
			var operation = $(this).data("oper");
			if (operation == 'remove'){
				operForm.attr("action", "/board/remove");
			} else if (operation == 'list') {
				operForm.attr("action", "/board/list");
				operForm.attr("method", "get");
				operForm.empty(); //내부 값 비워주기
				
			}
			// 어차피 기본 action이 'board/modify'이기 때문에 굳이 modify는 조건문으로 체크를 해줄 필요가 없다.
			operForm.submit();
		});
	});
</script>

<%@ include file="../include/footer.jsp" %>