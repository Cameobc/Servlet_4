<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

		<c:forEach items="${commentsList }" var="i">
			<tr>
				<td>${i.writer }</td>
				<td id="c${i.cnum }">${i.contents }</td>
				<td>${i.reg_date }</td>
				<c:if test="${member.id eq i.writer }">
					<td><button data-toggle="modal" class="update" title="${i.cnum }" data-target="#myModal">Update</button> <button class="delete" id="${i.cnum}">Delete</button></td>			
				</c:if>
			</tr>
		</c:forEach>
