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
		$('#btn').click(function() {
			var num = $('#num').val();
			$.get("../json/jsonTest1?num="+num, function(data) {
				data = data.trim();
				alert(data);
 				data = JSON.parse(data);
 				//javascript1
 				/*for(var i=0;i<data.ar.length;i++){
 					alert(data.ar[i].writer);
 					alert(data.ar[i].contents);
 				}*/
 				//javascript foreach
 				data.ar.forEach(function(d) {  //ar에서 하나 꺼내서 d에 넣고 포문 돌려라
					alert(d.writer);
 					alert(d.contents);
				});
 				
 				/*alert(data.writer);
				alert(data.contents); */
			});
		});
	});
</script>
</head>
<body>
<input type="text" id="num"><button id="btn">Click</button>

</body>
</html>