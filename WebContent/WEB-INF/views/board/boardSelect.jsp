<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:import url="../temp/bootstrap.jsp"/>
</head>
<body>
<c:import url="../temp/header.jsp"/>
<h1>TITLE : ${dto.title }</h1>
<h1>CONTENTS : ${dto.contents }</h1>
<h1>WRITER : ${dto.writer }</h1>
<h1>DATE : ${dto.reg_date }</h1>
<h1>HIT : ${dto.hit }</h1>
<c:forEach items="${ar }" var="i" >
<h1>FILE : 	<a href="../upload/${i.fName }">${i.oName }</a></h1>
</c:forEach>

<div class="container">
	<h1><a href="./${board }Update?no=${dto.no}">UPDATE</a></h1>
</div>

</body>
</html>