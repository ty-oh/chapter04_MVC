<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
						<input class="form-control" name="writer"
							 value='<sec:authentication property="principal.username"/>' readonly="readonly"/>
					</div>
					
					<input type="hidden" name="pageNum" value="${cri.pageNum }"/>
					<input type="hidden" name="amount" value="${cri.amount }"/>
					<button class="btn btn-primary" data-oper="register">등록</button>
					<button class="btn btn-info" data-oper="reset">취소</button>
					<button class="btn btn-success" data-oper="list">목록으로 이동</button>
					<!-- security 를 사용해서 post로 던질때는 항상 _csrf -->
					<!-- 로그인을 해야 접근할수 있는 상황에서는 함께 써주도록 하자 -->
					<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
				</form>
			</div>
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
	$(function() {
		var formObj = $('form');
		var amount = '${cri.amount}';
		var pageNum = '${cri.pageNum}';
		//-------------------------버튼 클릭 스크립트----------------------
		$('button').click(function(e){
			e.preventDefault(); // 기본 이벤트 삭제
			
			var oper = $(this).data('oper');
			if(oper == 'list') {
				location.href='/board/list?pageNum='+pageNum+'&amount='+amount;
				
			} else if (oper == 'reset') {
				formObj[0].reset();
				
			} else { // oper == 'register'
				var str = '';
				$(".uploadResult ul li").each(function(i, obj) {
					var jobj = $(obj);
					str += '<input type="hidden" name="attachList['+i+'].fileName" value="'+jobj.data("filename")+'">';
					str += '<input type="hidden" name="attachList['+i+'].uuid" value="'+jobj.data("uuid")+'">';
					str += '<input type="hidden" name="attachList['+i+'].uploadPath" value="'+jobj.data("path")+'">';
				});
				
				formObj.append(str);
				formObj.submit();
			}
		});
		
		
		
		
		
		
		//--------------------------파일 업로드 스크립트----------------
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
		
		// 파일 업로드 후 업로드 된 파일 결과 화면에 보여주기
		var uploadResult = $('.uploadResult ul');
		function showUploadFile(uploadResultArr) {
			var str = '';
			
			for(var i=0; i<uploadResultArr.length; i++) {
				var obj = uploadResultArr[i];
				var fileCallPath = encodeURIComponent(obj.uploadPath + "/" +
														obj.uuid + "_" + 
														obj.fileName);
				
				str += '<li data-path="'+ obj.uploadPath +'" data-uuid="'+ obj.uuid +'" data-filename="'+ obj.fileName +'">';
				str += '<img src="/resources/img/attach.png" style="width:15px">' + obj.fileName;
				str += '<span data-file="' + fileCallPath + '"> X </span>';
				str += '</li>';
			}
			
			uploadResult.html(str);
		} // end showUploadFile();
		
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
		
	});

</script>
<%@ include file="../include/footer.jsp" %>