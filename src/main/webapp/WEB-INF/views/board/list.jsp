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
			<div class="panel-heading">게시글 목록
				<button id="regBtn" class="btn btn-xs pull-right btn-primary">새 게시글 등록</button>
			</div>
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
								<td><c:out value="${board.bno}"/></td>
								<td>
									<a class="move" href="${board.bno }">
										<c:out value="${board.title }"/>
									</a> 
								</td>
								<td><c:out value="${board.writer}"></c:out></td>
								<td><fmt:formatDate value="${board.regdate}" pattern="yyyy-MM-dd"/></td>
								<td><fmt:formatDate value="${board.updatedate}" pattern="yyyy-MM-dd"/></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<form action="/board/list" method="get" id="actionForm"></form>
			</div>
			<!-- /.panel-body -->
		</div>
		<!-- /.panel -->
	</div>
	<!-- /.col-lg-12 -->	
</div>
<!-- /.row -->

<script type="text/javascript">
	//onload와 같은 jQuery 문법
	$(function(){
		//addEventlistener("click", function() {})
		$("#regBtn").click(function() {
			location.href = '/board/register';
		});
		
		var actionForm = $("#actionForm"); // 데이터 전송을 위한 form 태그
		//-----------상세 보기 페이지 이동 클릭 이벤트
		$(".move").click(function(e) {
			//<a> 클릭시 페이지 이동이 이루어지지 않게 하기 위해
			e.preventDefault(); // 기본 href 경로 이동 방지
			actionForm.attr('action', '/board/get');
			actionForm.html('<input type="hidden" name="bno" value="'+$(this).attr('href')+'"/>');
			actionForm.submit();
		});
	});
	// 결과창 출력을 위한 코드
	// 그냥 el만 써도 결과는 같지만 보안이슈가 있기 때문에 출력할때 c:out 태그를 사용
	var result = '<c:out value="${result}"/>';
	// rttr 객체를 통해 받아온 값이 빈 값이 아닐 때 (데이터 변경) 알림 메소드 실행
	if (result != '') {
		checkResult(result);
	}
	
	//뒤로 가기 할 때 문제가 될 수 있으므로,
	// history 객체를 조작({정보를 담은 객체}, 뒤로 가기 버튼 문자열 형태의 제목, 바꿀 url)
	history.replaceState({}, null, null);
	function checkResult() {
		// 뒤로가기 방지
		if(result === '' || history.state){
			return;
		}
		//수정 및 삭제
		if (result === 'success') {
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