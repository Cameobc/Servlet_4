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
		var curPage=1;
		//more
		$('#more').click(function() {
			curPage++;
			getList(curPage);
		});
		
		//write
		$('#btn').click(function() {
			var no = '${dto.no}';  // if no가 문자열이라면 앞뒤로 '' 넣어줘야함.
			var writer = $('#writer').val();
			var contents = $('#contents').val();
			//post
			$.post("../comments/commentsWrite", {
				writer:writer,
				contents:contents,
				no:no
			}, function(data) {
				if(data.trim()=='1'){
					alert('Success');
					getList(1);
				}else{
					alert('Fail');
				}
			});
		});
		
		//list
		function getList(count) {
			var no = '${dto.no}';
			$.get("../comments/commentsList?no="+no+"&curPage="+count, function(data) {
				var data = data.trim();
				if(count==1){
					$('#table').html(data);
				}else{
					$('#table').append(data);
				}
			});
		}
		
		/////delete
		$('#table').on("click", ".delete", function() {
			var cnum = $(this).attr("id");
			var check = confirm("정말 삭제하시겠습니까?");
			if(check==true){
				$.get("../comments/commentsDelete?cnum="+cnum, function(data) {
					if(data.trim()=='1'){
						alert('Success');
						getList(1);
					}else{
						alert('Fail');
					}
				});
			}
				
		});
		//////
		$('#updatebtn').click(function() {
			var contents =$('#contentsup').val();
			var cnum =$('#cnum').val();
			$.post("../comments/commentsUpdate",{
				contents:contents,
				cnum:cnum
			}, function(data) {
				var data = data.trim();
				if(data=='1'){
					alert('Success');
					//getList(1);
					$('#c'+cnum).html(contents);
				}else{
					alert('Fail');
				}
			});
			});
		/////////////
		$('#table').on("click", ".update", function() {
			var id = $(this).attr('title');
			var con = $('#c'+id).html();
			$('#contentsup').val(con);
			$('#cnum').val(id);
		});
		
	});

</script>
</head>
<body>
<c:import url="../temp/header.jsp"/>
<div class="container">
	<h1>TITLE : ${dto.title }</h1>
	<h1>CONTENTS :</h1>
	<div class="row">
	${dto.contents }
	</div>
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
			<button id="more">더보기</button>
		</div>
	</div>
</c:if>
<div class="container">
 <!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <h4 class="modal-title">${member.id }</h4>
        </div>
        <div class="modal-body">
          <div class="form-group">
			  <label for="contents">Contents:</label>
			  <textarea class="form-control" rows="20" id="contentsup" name="contents"></textarea>
			  <input type="hidden" id="cnum">
			</div>			
        </div>
        <div class="modal-footer">
		  <button class="btn btn-danger" id="updatebtn" data-dismiss="modal">Update</button>
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>
</div>
<div class="container">
	<h1><a href="./${board }Update?no=${dto.no}">UPDATE</a></h1>
</div>

</body>
</html>