<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:import url="../temp/bootstrap.jsp"/>
<script type="text/javascript">
	$(function() {
		getList();
		$('#btn').click(function() {
			var no = '${dto.no}';  // if no가 문자열이라면 앞뒤로 '' 넣어줘야함.
			var writer = $('#writer').val();
			var contents = $('#contents').val();
			
			var xhttp;

			//1.xmlhttp
			if(window.XMLHttpRequest){
				xhttp = new XMLHttpRequest();
			}else{
				xhttp = new ActiveXObject("Micorosoft.XMLHTTP");
			}
			//2.정보입력
			xhttp.open("POST", "../comments/commentsWrite", true);
			xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

			//3.정보전송
			xhttp.send("no="+no+"&writer="+writer+"&contents="+contents);
			
			//4.결과처리
			xhttp.onreadystatechange = function() {
				if(this.readyState ==4 && this.status==200){
					if(this.responseText.trim()=='1'){
						alert("입력성공");
						location.reload();
					}else{
						alert("입력실패");
					}
				}
			}
		});
		
		//list
		
		function getList() {
			var no = '${dto.no}';
			//ajax
			var xhttp;
			
			if(window.XMLHttpRequest){
				xhttp = new XMLHttpRequest();
			}else{
				xhttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
			xhttp.open("GET", "../comments/commentsList", true);
			
			xhttp.send();
			
			xhttp.onreadystatechange = function() {
				if(this.readyState==4 && this.status==200){
					if(this.responseText.trim()!=null){
						$('#table').append(this.responseText);
						
					}
				}
			}
		}
		
		$('#table').on("click", ".delete", function() {
			var cnum = $(this).attr("id");
			var check = confirm("정말 삭제하시겠습니까?");
			if(check==true){
				$.get("../comments/commentsDelete?cnum="+cnum, function(data) {
					if(data.trim()=='1'){
						alert('Success');
						location.reload();
					}else{
						alert('Fail');
					}
				});
			}
				
		});
		
	});

</script>
</head>
<body>
<c:import url="../temp/header.jsp"/>
<div class="container">
	<h1>TITLE : ${dto.title }</h1>
	<h1>CONTENTS : ${dto.contents }</h1>
	<h1>WRITER : ${dto.writer }</h1>
	<h1>DATE : ${dto.reg_date }</h1>
	<h1>HIT : ${dto.hit }</h1>
<c:forEach items="${ar }" var="i" >
	<h1>FILE : 	<a href="../upload/${i.fName }">${i.oName }</a></h1>
</c:forEach>
</div>
<c:if test="${board ne 'notice'}">
	<div class="container">
		<!-- 댓글입력폼 -->
		<div class="row">
			<div class="form-group">
		      <label for="writer">Writer:</label>
		      <input type="text" class="form-control" id="writer" name="writer">
		    </div>
		    <div class="form-group">
			  <label for="contents">Contents:</label>
			  <textarea class="form-control" rows="20" id="contents" name="contents"></textarea>
				<button class="btn btn-danger" id="btn">Write</button>
			</div>			
		</div>
		<!-- 댓글리스트 -->
		<div class="row">
			<table class="table table-bordered" id="table">
				
			</table>
		</div>
	</div>
</c:if>
<div class="container">
	<h1><a href="./${board }Update?no=${dto.no}">UPDATE</a></h1>
</div>

</body>
</html>