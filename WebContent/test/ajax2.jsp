<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script type="text/javascript">
	$(function() {
		$('#id').blur(function() {
			//1.xhttprequest 생성
			var xhttp;
			if(window.XMLHttpRequest){
				xhttp = new XMLHttpRequest();
			}else{
				xhttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
			
			//2.요청정보기록 -동기/비동기방식 default = true;
			xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			xhttp.open("POST", "../member/idCheck", false);
			
			//3.전송
			xhttp.send("id="+$('#id').val());
			
			//4.결과처리
			xhttp.onreadystatechange= function() {
				if(this.readyState ==4 && this.status==200){
					if(this.responseText.trim()=='1'){
						$('#result').html("사용할 수 있는 아이디입니다.");
						$('#result').css("color", "blue");
					}else{
						$('#result').html("사용할 수 없는 아이디입니다.");
						$('#result').css("color", "red");
						$('#id').val("").focus();
					}				
				}
			}
				
			
		});
	});

</script>
</head>
<body>
	<input type="text" id="id">
	<div id="result" ></div>
</body>
</html>