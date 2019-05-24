
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../temp/bootstrap.jsp"/>
</head>
<body>
<c:import url="../temp/header.jsp"/>
<div class="container">
	<h1>${board } List</h1>
		<table class="table table-hover">
			<tr>
				<td>NUM</td><td>TITLE</td><td>WRITER</td><td>DATE</td><td>HIT</td>
			</tr>
			<c:forEach items="${requestScope.list}" var="arlist">
			<tr>
				<td>${arlist.no}</td>
				<td>
				 <c:catch>
				<c:forEach begin="1" end="${arlist.depth}" varStatus="i">
				--
				<c:if test="${i.last}">></c:if>
				</c:forEach>   <!-- &nbsp;&nbsp; -->
				</c:catch>
				<a href="./${board }Select?no=${arlist.no }">${arlist.title}</a></td>
				<td>${arlist.writer}</td>
				<td>${arlist.reg_date}</td>
				<td>${arlist.hit }</td>
			</tr>
			</c:forEach>
		</table>
</div>	
<div class="container">
	<ul class="pager">
	<c:if test="${pager.curBlock gt 1 }">	
    	<li><a href="./${board }List?curPage=${pager.startNum-1}&kind=${pager.search.kind}&search=${pager.search.search}">Previous</a></li>
	</c:if>
	<li>
		<ul class="pagination">
		<c:forEach begin="${pager.startNum }" end="${pager.lastNum }" var="num">		
   			 <li><a href="./${board }List?curPage=${num}&kind=${pager.search.kind}&search=${pager.search.search}">${num }</a></li>
		</c:forEach>
  </ul>
	</li>
	<c:if test="${pager.curBlock lt pager.totalBlock}">	
    	<li><a href="./${board }List?curPage=${pager.lastNum+1}&kind=${pager.search.kind}&search=${pager.search.search}">Next</a></li>
	</c:if>	
  	</ul>
</div>
	<a href="./${board }Write">Go Write</a>
</body>
</html>