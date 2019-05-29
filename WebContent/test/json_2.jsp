<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<title>Insert title here</title>
<script type="text/javascript">
	$(function() {
		$('#btn').click(function() {
			$.get("https://yts.am/api/v2/list_movies.json?limit=10&sort=year", function(data) {
				
				data.data.movies.forEach(function(d) {
					var img = d.large_cover_image;
					var result = '<div><h3>'+d.title+'<img src="'+img+'"></div>';
					$('#result').append(result);
				});
			});
		});
	});

</script>
</head>
<body>
	<button id="btn">Click</button>
	<div id="result"></div>
</body>
</html>