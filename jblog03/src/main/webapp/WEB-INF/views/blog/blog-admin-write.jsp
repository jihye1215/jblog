<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<div id="header">
			<a href="${pageContext.request.contextPath}/${blogvo.userId}"><h1>${blogvo.title}</h1></a>
			<ul>
			<c:choose>
				<c:when test = '${empty authUser}'>
					<li><a href="${pageContext.request.contextPath}/user/login">로그인</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="${pageContext.request.contextPath}/user/logout">로그아웃</a></li>
				</c:otherwise>
			</c:choose>
			<c:if test = "${blogvo.userId eq authUser.id}">
				<li><a href="${pageContext.request.contextPath}/${blogvo.userId}/admin/basic">블로그 관리</a></li>
			</c:if>
			<li><a href="${pageContext.request.contextPath}/${authUser.id}">내 블로그</a></li>
			</ul>
		</div>
		<div id="wrapper">
			<div id="content" class="full-screen">
				<ul class="admin-menu">
					<li><a href="${pageContext.request.contextPath }/${blogvo.userId}/admin/basic">기본설정</a></li>
					<li><a href="${pageContext.request.contextPath }/${blogvo.userId}/admin/category">카테고리</a></li>
					<li class="selected">글작성</li>
				</ul>
				<form action="${pageContext.request.contextPath}/${blogvo.userId}/post/write" method="post">
			      	<table class="admin-cat-write">
			      		<tr>
			      			<td class="t">제목</td>
			      			<td>
			      				<input type="text" size="60" name="title" value = "">
				      			<select name="categoryNo">
				      				<c:forEach items = "${map.clist}" var = "categoryvo" varStatus = "status">
										<option value = "${categoryvo.no}">${categoryvo.name}</option>
									</c:forEach>
				      			</select>
				      		</td>
			      		</tr>
			      		<tr>
			      			<td class="t">내용</td>
			      			<td><textarea name="contents"></textarea></td>
			      		</tr>
			      		<tr>
			      			<td>&nbsp;</td>
			      			<td class="s"><input type="submit" value="포스트하기"></td>
			      		</tr>
			      	</table>
				</form>
			</div>
		</div>
		<div id="footer">
			<p>
				<strong>${blogvo.title}</strong> is powered by JBlog (c)2022
			</p>
		</div>
	</div>
</body>
</html>