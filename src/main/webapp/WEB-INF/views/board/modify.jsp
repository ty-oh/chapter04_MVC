<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../include/header.jsp" %>

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">게시글 수정</h1>
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
<!-- 첨부파일 -->
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">파일 첨부</div>
			<!-- /.panel-heading -->
			<div class="panel-body">
				<div class="form-group uploadDiv">
					<input type="file" name="uploadFile" multiple="multiple">
				</div>
				<div class="uploadResult">
					<ul></ul>
				</div>
			</div>
			<!-- /.panel-body -->
		</div>
		<!-- /.panel -->
	</div>
	<!-- /.col-lg-12 -->	
</div>
<!-- /.row -->

<script type="text/javascript">
	// 첨부파일 스크립트 ------------------------start
	$(function() {
		var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$"); // 확장자를 제한하기 위한 정규식
		var maxSize = 5*1024*1024; // 5mb 크기
		
		function checkExtension(fileName, fileSize) {
			if(fileSize >= maxSize) {
				alert("파일 사이즈 초과");
				return false;
			}
			if(regex.test(fileName)) {
				alert("해당 종류의 파일은 업로드 할 수 없습니다.");
				return false;
			}
			return true;
		} // end checkExtension();
		
		
		$('input[type="file"]').on('change', function() {
			var formData = new FormData(); // 스크립트에서 Form태그 생성
			var inputFile = $('input[name="uploadFile"]');
			var files = inputFile[0].files;
			
			for(var i=0; i<files.length; i++) {
				if(!checkExtension(files[i].name, files[i].size)) {
					return false;
				}
				
				formData.append("uploadFile", files[i]);
			}
			
			$.ajax({
				url: '/uploadAjaxAction',
				processData: false,			//
				contentType: false,			//
				data: formData,
				type: 'post',
				dataType: 'json',
				success: function(result) {
					console.log(result);
					showUploadFile(result);
				}
			});
		});
	
		var bnoValue = '${vo.bno}';
		var uploadResult = $('.uploadResult ul');
		$.ajax({
			url:"/board/getAttachList",
			type:'get',
			data: {bno:bnoValue},
			contentType: 'application/json; charset=utf-8',
			success : function(arr) {
				var str = '';
				
				for(var i=0; i<arr.length; i++) {
					var obj = arr[i];
					var fileCallPath = encodeURIComponent(obj.uploadPath + "/" +
															obj.uuid + "_" + 
															obj.fileName);
					
					str += '<li data-path="'+ obj.uploadPath +'" data-uuid="'+ obj.uuid +'" data-filename="'+ obj.fileName +'">';
					str += '<img src="/resources/img/attach.png" style="width:15px">' + obj.fileName;
					str += '<span data-file="' + fileCallPath + '"> X </span>';
					str += '</li>';
				}
				
				uploadResult.html(str);
			},
			error: function() {}
		});
	
		uploadResult.on("click", "span", function() {
			var targetFile = $(this).data("file");
			var targetLi = $(this).closest("li");
			
			$.ajax({
				url: "/deleteFile",
				data: {fileName: targetFile},
				type: "post",
				dataType: "text",
				success: function(result) {
					targetLi.remove();
				}
			});
		});
	
	
	// 첨부파일 스크립트 ------------------------end

		var operForm = $("#operForm");
		
		$("button").on("click", function(e) {
			e.preventDefault();
			operForm.append('<input type="hidden" name="pageNum" value="' + ${cri.pageNum } + '"/>');
			operForm.append('<input type="hidden" name="amount" value="' + ${cri.amount } + '"/>');
			
			var operation = $(this).data("oper");
			if (operation == 'remove'){
				operForm.attr("action", "/board/remove");
				
			} else if (operation == 'list') {
// 				operForm.append('<input type="hidden" name="pageNum" value="' + ${cri.pageNum } + '"/>');
// 				operForm.append('<input type="hidden" name="amount" value="' + ${cri.amount } + '"/>');
// 				operForm.append('<input type="hidden" name="bno" value="'+ ${vo.bno} + '">');
				operForm.attr("action", "/board/list");
				operForm.attr("method", "get");
				
				var pageNumTag = $("input[name=pageNum]").clone();
				var amountTag = $("input[name=amount]").clone();
				
				operForm.empty(); //내부 값 비워주기
				
				operForm.append(pageNumTag);
				operForm.append(amountTag);
			}
			// 어차피 기본 action이 'board/modify'이기 때문에 굳이 modify는 조건문으로 체크를 해줄 필요가 없다.
			operForm.submit();
		});
	});
</script>

<%@ include file="../include/footer.jsp" %>