<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../temp/bootstrap.jsp"/>
<script type="text/javascript">
	$(function() {
			var count=1;
			var d1=0;
		
			$('.delete').click(function() {
				var check=$(this).attr('id');
				//요청주소, xmlhttprequst
				var xhttp;
				if(window.XMLHttpRequest){
					xhttp = new XMLHttpRequest();
				}else{
					xhttp = new ActiveXObject("Microsoft.XMLHTTP");
				}
				//연결정보작성
				xhttp.open("GET", "../file/fileDelete?pnum="+check);
				//전송
				xhttp.send();
				//결과처리
				xhttp.onreadystatechange = function() {
					if(this.readyState ==4 && this.status == 200){
						if(this.responseText.trim()=='1'){
							$('#'+check).prev().remove();
							$('#'+check).remove();
							//$(this).prev().remove();
							//$(this).remove();
						}else{
							alert('Delete Fail');
						}
					}
				}

			});
			
		$('#up').click(function() {
			d1++;
				if(count<5){
					$('#addfile').append('<input type="file" class="form-control" name="f'+d1+'"><span class="del">X</span>');
					/*<div><input type="file" class="form-control" name="f1"><span class="del">X</span></div> ->this.parent().remove(); 세번째는 jquery5_html 보기*/
					count++;
				}else{
					alert('5개까지만 추가 가능');
				}
		});
		
 		$('#addfile').on("click", ".del", function() {
			$(this).prev().remove();
			$(this).remove();
			count--;
		});
 		
		
	});
</script>
<style type="text/css">
	.del{
		color : red;
		cursor: pointer;
	}
</style>
</head>
<body>
<c:import url="../temp/header.jsp"/>
<div class="container">
	<h1>${board } Update</h1>
	<form action="./${board }Update" method="post" enctype="multipart/form-data">
		<input type="hidden" name="no" value="${dto.no }">
	    <div class="form-group">
	      <label for="title">Title:</label>
	      <input type="text" class="form-control" id="title" name="title" value="${dto.title }">
	    </div>
	    <div class="form-group">
	      <label for="writer">Writer:</label>
	      <input type="text" class="form-control" id="writer" name="writer" value="${dto.writer }" readonly="readonly">
	    </div>
	    <div class="form-group">
		  <label for="contents">Contents:</label>
		  <textarea class="form-control" rows="20" id="contents" name="contents">${dto.contents }</textarea>
		</div>
		<div class="form-group">
	      <label for="file" id="addfile">File:</label>
	      <c:forEach items="${ar }" var="i">
	    	  <span title="${t.count}" >${i.oName }</span><span class="delete" id="${i.pnum}">X</span>
	      </c:forEach>
	    </div>
	    <div class="form-group">
		    <input type="button" value="Add" id="up" class="btn btn-primary">
	    </div>
		<button class="btn btn-danger">Update</button>
  </form>
</div>
</body>
</html>