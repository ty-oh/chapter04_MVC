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
			<div class="panel-heading">게시글 화면</div>
			<!-- /.panel-heading -->
			<div class="panel-body">
				<div class="form-group">
					<label>글 번호</label>
					<input class="form-control" name="bno" value="${vo.bno }" readonly="readonly">
				</div>
				<div class="form-group">
					<label>글 제목</label>
					<input class="form-control" name="title" value="${vo.title}" readonly="readonly">
				</div>
				<div class="form-group">
					<label>글 내용</label>
					<textarea class="form-control" rows="3" name="content" readonly="readonly">${vo.content}</textarea>
				</div>
				<div class="form-group">
					<label>작성자</label>
					<input class="form-control" name="writer" value="${vo.writer}" readonly="readonly">
				</div>
			</div>
			<div class="modal-footer">
				<button id="boardModBtn" class="btn btn-warning">글 내용 수정</button>
				<button id="listMoveBtn" class="btn btn-default">목록 이동</button>
			</div>
			
			<form action="/board/modify" method="get" id="operForm">
				<input type="hidden" name="pageNum" value="${cri.pageNum }">
				<input type="hidden" name="amount" value="${cri.amount }">
			</form>
			
			
			<!-- /.panel-body -->
		</div>
		<!-- /.panel -->
	</div>
	<!-- /.col-lg-12 -->	
</div>
<!-- /.row -->

<div class = 'row'>
	<div class = "col-lg-12">
		<!-- /.panel -->
		<div class = "panel panel-default">
			<div class= "panel-heading">
				<i class = "fa fa-comments fa-fw"></i> 댓글
				<sec:authorize access="isAuthenticated()">
					<button id="addReplyBtn" class="btn btn-primary btn-xs pull-right">댓글 달기</button>
				</sec:authorize>
			</div>
			<!-- /.panel-heading -->
			<div class = "panel-body">
				<ul class = "chat">
					<!-- start reply -->
					<!-- end reply -->
				</ul>
				<!-- ./ end ul -->
			</div>
			<!-- /.panel .chat-panel -->
		</div>
	</div>
	<!-- ./end row -->
</div>

<!-- Modal -->
<div class="modal fade" id = "MyModal" tabindex = "-1" role = "dialog"
	aria-labelledby = "myModalLabel" aria-hidden = "true">
	<div class = "modal-dialog">
		<div class = "modal-content">
			<div class = "modal-header">
				<button type = "button" class = "close" data-dismiss = "modal" ari-hidden = "true">&times;</button>
				<h4 class = "modal-title" id = "myModalLabel">새 게시글 등록</h4>
			</div>
			<div class = "modal-body">
				<div class = "form-group">
					<label>댓글</label>
					<input class = "form-control" name = 'reply' placeholder = 'New Reply!!!!'>
				</div>
				<div class = "form-group">
					<label>작성자</label>
					<input class = "form-control" name = 'replyer' placeholder = 'replyer'>
				</div>
				<div class = "form-group">
					<label>등록 날짜</label>
					<input class = "form-control" name = 'replyDate' readonly="readonly">
				</div>
			</div>
			<div class = "modal-footer">
				<input name="rno" type="hidden" value=""/>
				<button id = 'modalModBtn' type = "button" class = "btn btn-warning">수정</button>
				<button id = 'modalRemoveBtn' type = "button" class = "btn btn-danger">삭제</button>
				<button id = 'modalRegisterBtn' type = "button" class = "btn btn-primary">등록</button>
				<button id = 'modalCloseBtn' type = "button" class = "btn btn-default">취소</button>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript" src="/resources/js/reply.js"></script>
<script type="text/javascript" src="/resources/js/util.js"></script>
<script type="text/javascript">
	$(function(){
		// 화면 이동 스크립트 -------------------------------start
		var operForm = $("#operForm");
		
		$("#boardModBtn").click(function(){
			operForm.append('<input type="hidden" name="bno" value="'+${vo.bno }+'">');
			operForm.submit();
		});
		
		$("#listMoveBtn").click(function(){
			operForm.attr("action", "/board/list");
			operForm.submit();
		});
		// 화면 이동 스크립트 -------------------------------end
		
		
		// 댓글 등록
		var bnoValue = '${vo.bno}';
		var replyUl = $(".chat");
		
		showList();
		function showList() {
			replyService.getList(
				{bno:bnoValue, page:1},
				function(result) {
					var str = '';
					const TIME_ZONE = 3240 * 10000;

					if (result == null || result.length==0) {
						replyUl.html("");
						return;
					}
					
					for(var i=0; i<result.length; i++){
						str += 	'<li class = "left clearfix" data-rno = '+result[i].rno+'>' + 
									'<div>' + 
										'<div class = "header">' + 
											'<strong class = "primary-font">'+result[i].replyer+'</strong>' +
											'<small class = "pull-right text-muted">'+ displayTime(result[i].replyDate) +'</small>' +
										'</div>' +
										'<p>'+result[i].reply+'</p>' +
									'</div>' +
								'</li>';
						replyUl.html(str);
					}
				}
			);
		}
		
		// 모달 창 관련 스크립트
		var modal = $(".modal");
		var modalInputRno = modal.find('input[name="rno"]')
		var modalInputReply = modal.find('input[name="reply"]');
		var modalInputReplyer = modal.find('input[name="replyer"]');
		var modalInputReplyDate = modal.find('input[name="replyDate"]');
		
		var modalModBtn = $("#modalModBtn");
		var modalRemoveBtn = $("#modalRemoveBtn");
		var modalRegisterBtn = $("#modalRegisterBtn");
		var rno;
		
		//댓글달기 버튼 클릭 이벤트
		$("#addReplyBtn").on("click", function(){
			modal.find('input').val('');						// 입력창 비우기
			modalInputReplyDate.closest("div").hide();			// 날짜 입력창 숨기기 
			modalModBtn.hide();									// 수정버튼 숨기기
			modalRemoveBtn.hide();								// 삭제버튼 숨기기
			modalRegisterBtn.show();
			
			$(modalInputReplyDate).val(new Date());
			
			modal.modal("show");
		});
		
		$("#modalCloseBtn").on("click", function() {
			modal.modal("hide");
		});
		
		$('#modalRegisterBtn').on("click", function() {
			var reply = {
					reply: modalInputReply.val(),
					replyer: modalInputReplyer.val(),
					bno:bnoValue
			};
			
			replyService.add(reply, function(result){
					showList();
					modal.modal("hide");
				}
			);
		});
		
		$(modalModBtn).on("click", function() {
			var reply = {
					rno: rno,
					reply: modalInputReply.val(),
			}
			
			replyService.update(reply, function() {
				alert('댓글을 수정하였습니다.');
				showList();
				modal.modal("hide");
			});
		});
		
		$(modalRemoveBtn).on("click", function() {
			//var rno = rno;
			
			replyService.remove(rno, function() {
				alert('댓글을 삭제하였습니다.');
				showList();
				modal.modal("hide");
			});
		});
		
		//()댓글 리스트 클릭 이벤트
		// on과 click 차이로 인해 시점문제가 발생할 수 있다.
		// on의 두번째 매개변수를 통해서 시점을 뒤로 미룰수 있다.
		$(replyUl).on("click", "li", function(e) {
			//1. rno 가져오기
			//2. rno 통해서 댓글 정보 db에서 가져오기
			//3. 모달창에 데이터 출력하기
			//	- 작성자, 내용, 작성 날짜
			//4. 버튼
			//  - 등록 버튼 숨기기
			//  - 수정 버튼 보이기
			//  - 삭제 버튼 보이기
			rno = e.target.closest("li").dataset.rno;
			
			//모달창 초기화
			modal.find('input').val('');						// 입력창 비우기
			modalInputReplyer.attr('readonly', 'readonly');
			modalInputReplyDate.closest("div").show();
			modalModBtn.show();									// 수정버튼 숨기기
			modalRemoveBtn.show();
			modalRegisterBtn.hide();
			modal.modal("show");
			
			replyService.get(rno, function(result) {
				modalInputRno.val(result.rno);
				modalInputReply.val(result.reply);
				modalInputReplyer.val(result.replyer);
				modalInputReplyDate.val(displayTime(result.replyDate));
			});
		});
		
		// 댓글 삭제
// 		replyService.remove(
// 			7,
// 			function(result) {
// 				console.log(result);
// 				if(result=="success") {
// 					alert("removed");
// 				}
// 			}
// 		);
		
// 		replyService.update(
// 			{rno: 9, reply: '수정했습니다.'},
// 			function(result) {
// 				console.log(result);
// 			}
// 		);
		
		// 댓글 리스트
// 		replyService.getList(
// 		   {bno:bnoValue, page:1},
// 		   function(result) {
// 		      for(var i=0; i<result.length; i++){
// 		         console.log(result[i]);
// 		      }
// 		   }
// 		);
		
// 		replyService.get(
// 			8,
// 			function(result) {
// 				console.log(result);
// 			}
// 		);
	});
</script>

<%@ include file="../include/footer.jsp" %>