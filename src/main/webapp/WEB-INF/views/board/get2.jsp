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
			<div class="panel-body">
				<div class="form-group">
					<label>글 번호</label>
					<input class="form-control" name="bno" value="${vo.bno }" readonly="readonly"/>
				</div>
				<div class="form-group">
					<label>글 제목</label>
					<input class="form-control" name="title" value="${vo.title }" readonly="readonly"/>
				</div>
				<div class="form-group">
					<label>글 내용</label>
					<textarea rows="3" class="form-control" name="content" readonly="readonly">${vo.content }</textarea>
				</div>
				<div class="form-group">
					<label>작성자</label>
					<input class="form-control" name="writer" value="${vo.writer }" readonly="readonly"/>
				</div>
			</div>
			<div class="modal-footer">
				<button id="modalModBtn" class="btn btn-warning">수정</button>
				<!-- <button id="modalRemoveBtn" class="btn btn-danger">삭제</button> -->
				<!-- <button id="modalRegisterBtn" class="btn btn-primary">등록</button> -->
				<button id="moveListBtn" class="btn btn-default">목록</button>
			</div>
			<form action="/board/modify" method="get" id="openForm">
			<%-- <input type="hidden" name="bno" value="${vo.bno }"> --%>
				<input type="hidden" name="amount" value="${cri.amount }"/>
				<input type="hidden" name="pageNum" value="${cri.pageNum }"/>
			</form>
			
			<!-- /.panel-body -->
		</div>
		<!-- /.panel -->
	</div>
	<!-- /.col-lg-12 -->	
</div>
<!-- /.row -->

<script type="text/javascript" src="/resources/js/jquery-3.4.1.js"></script>
<script type="text/javascript" src="/resources/js/reply.js"></script>
<script type="text/javascript">
	$(function() {
		// 화면 이동스크립트 ------------------------ start
		var openForm = $("#openForm");
		$('#modalModBtn').click(function(e) {
			openForm.append('<input type="hidden" name="bno" value="'+ ${vo.bno} + '">');
			openForm.submit();
		});
		
		$('#moveListBtn').click(function(e) {
			openForm.attr('action', '/board/list');
			openForm.submit();
		});
		// 화면 이동스크립트 ------------------------ end
		
		//댓글 등록
		var bnoValue = '${vo.bno}';

		replyService.add(
			{reply: "JS TEST", replyer: "tester", bno: bnoValue},
			function(result) {
				alert("result : " + result);
			}
		);
		
	});
</script>

<%@ include file="../include/footer.jsp" %>