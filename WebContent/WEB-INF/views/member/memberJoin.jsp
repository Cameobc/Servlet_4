<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<c:import url="../temp/bootstrap.jsp"/>
<script type="text/javascript">
	$(function() {
		$('#id').blur(function() {
			//1 xhttpRequst
			var xhttp;
			if(window.XMLHttpRequst){
				xhttp = new XMLHttpRequest();
			}else{
				xhttp= new ActiveXObject("Microsoft.XMLHTTP");
			}
			//2요청정보기록
			xhttp.open("GET", "../member/idCheck");
			//3전송
			xhttp.send();
			//4결과처리
			xhttp.onreadystatechange = function() {
				if(this.readyState==4 && this.status==200){
					if(this.responseText.trim()=='1'){
						$('#result').html('사용할 수 있는 아이디입니다.');
					}else{
						$('#result').html('사용할 수 없는 아이디입니다.');
						$('#id').val("").focus();
					}
				}
			}
		});
	});
</script>
</head>
<body>
<c:import url="../temp/header.jsp"/>
<div class="container">
<h1>MemberJoin</h1>
 <form class="form-horizontal" action="./memberJoin" method="post" enctype="multipart/form-data">
    <div class="form-group">
      <label class="control-label col-sm-2" for="id">Id:</label>
      <div class="col-sm-10">
        <input type="text" class="form-control" id="id" placeholder="Enter Id" name="id">
        <div id="result"></div>
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="pw">Password:</label>
      <div class="col-sm-10">          
        <input type="password" class="form-control" id="pw" placeholder="Enter password" name="pw">
      </div>
    </div>
        <div class="form-group">
      <label class="control-label col-sm-2" for="pwd">Password:</label>
      <div class="col-sm-10">          
        <input type="password" class="form-control" id="pwd" placeholder="Enter password" >
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="name">Name:</label>
      <div class="col-sm-10">
        <input type="text" class="form-control" id="name" placeholder="Enter Name" name="name">
      </div>
    </div>
        <div class="form-group">
      <label class="control-label col-sm-2" for="phone">Phone:</label>
      <div class="col-sm-10">
        <input type="text" class="form-control" id="phone" placeholder="Enter Phone" name="phone">
      </div>
    </div>
        <div class="form-group">
      <label class="control-label col-sm-2" for="email">Email:</label>
      <div class="col-sm-10">
        <input type="text" class="form-control" id="email" placeholder="Enter Email" name="email">
      </div>
    </div>
        <div class="form-group">
      <label class="control-label col-sm-2" for="age">Age:</label>
      <div class="col-sm-10">
        <input type="text" class="form-control" id="age" placeholder="Enter Age" name="age">
      </div>
    </div>
        <div class="form-group">
      <label class="control-label col-sm-2" for="photo">Photo:</label>
      <div class="col-sm-10">
        <input type="file" class="form-control" id="photo" name="photo">
      </div>
    </div>
    <div class="form-group">        
      <div class="col-sm-offset-2 col-sm-10">
        <button class="btn btn-primary">Join</button>
      </div>
    </div>
  </form>
</div>
</body>
</html>