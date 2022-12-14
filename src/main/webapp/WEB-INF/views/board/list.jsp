<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../include/header.jsp" %>

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Tables</h1>
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
				<table class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th>#번호</th>
							<th>제목</th>
							<th>작성자</th>
							<th>작성일</th>
							<th>수정일</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="board" items="${list }">
							<tr>
								<td><c:out value="${board.bno}"></c:out></td>
								<td><c:out value="${board.title }"></c:out></td>
								<td><c:out value="${board.writer}"></c:out></td>
								<td><fmt:formatDate value="${board.regdate}" pattern="yyyy-MM-dd"/></td>
								<td><fmt:formatDate value="${board.updatedate}" pattern="yyyy-MM-dd"/></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				
			</div>
			<!-- /.panel-body -->
		</div>
		<!-- /.panel -->
	</div>
	<!-- /.col-lg-12 -->	
</div>
<!-- /.row -->

<script type="text/javascript">
	$(function(){
		if(${result eq 'ok'}) {
			alert('게시글이 작성되었습니다.');
		};
	});
	
	var result = '<c:out value="${result}"/>';
	if (result != '') {
		checkResult(result);
	}
	history.replaceState({}, null, null);
	function checkResult() {
		if(result === '' || history.state){
			return;
		}
		if (result === 'sucess') {
			alert('처리가 완료되었습니다.');
			return;
		}
		if (result === 'ok') {
			alert('게시글이 등록되었습니다.');
			return;
		}
	}
</script>

<%@ include file="../include/footer.jsp" %>