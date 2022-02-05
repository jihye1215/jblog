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
			<h1>${blogvo.title}</h1>
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
			<li><a href="${pageContext.request.contextPath}/${blogvo.userId}">블로그 첫 화면</a></li>
			</ul>
		</div>
		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
					<h4>${postvo.title }</h4>
					<p>
						${postvo.contents }
					<p>
				</div>
			<ul class="blog-list">
				<c:forEach items = "${map2.plist}" var = "postvo1" varStatus = "status">
					<tr>
						<li><a href="${pageContext.request.contextPath}/${blogvo.userId}/category/${postvo1.categoryNo}/post/${postvo1.no}">${postvo1.title}</a></li>
						<li><span>${postvo1.regDate}</span></li>
					</tr>
				</c:forEach>
			</ul>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img src="${pageContext.request.contextPath}${blogvo.logo}" height = "210">
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			<ul>
			<c:forEach items = "${map1.clist}" var = "categoryvo" varStatus = "status">
				<tr>
					<li><a href="${pageContext.request.contextPath}/${blogvo.userId}/category/${categoryvo.no}">${categoryvo.name}</a></li>
				</tr>
			</c:forEach>
			</ul>
		</div>
		<div id="footer">
			<p>
				<strong>${blogvo.title}</strong> is powered by JBlog (c)2022
			</p>
		</div>
	</div>
</body>
</html>