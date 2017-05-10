<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>

<div id="ContentJSP">

    <div class="main-registration">
	<c:if test="${empty user}">
		<h1>Sorry, only if you Sign in, you will be able to buy something  !!!</h1>
			<div style="text-align:center">
		  	<c:forEach items="${itemList}" var="item">
		  	<div style="float:left; margin:10px 20px; border:1px solid #808080; border-radius:25px">
		  		<div>
		  			<a href="${pageContext.request.contextPath}/ProductID?id=${item.id}">
		  				<img alt ="SOME ITEMS" class="top-image" src ="${pageContext.request.contextPath}/${item.img}" style="border-radius: 25px 25px 0 0;" width="150" height="140">
		  			</a>
		  		</div>
		  		<div><a style="display:block; padding-top:15px; padding-bottom:15px; border-radius:0 0 25px 25px" href="${pageContext.request.contextPath}/ProductID?id=${item.id}"><b>${item.name}</b></a></div>
		  	</div>

	 		 </c:forEach>
	 	</div>
	</c:if>
	<c:if test="${not empty user }">
		<h1>Hello dear ${user.login}</h1>
		<div style="text-align:center">
		  	<c:forEach items="${itemList}" var="item">
		  	<div style="float:left; margin:10px 20px; border:1px solid #808080; border-radius:25px">
		  		<div>
		  			<a href="${pageContext.request.contextPath}/ProductID?id=${item.id}">
		  				<img alt ="SOME ITEMS" class="top-image" src ="${pageContext.request.contextPath}/${item.img}" style="border-radius: 25px 25px 0 0;" width="150" height="140">
		  			</a>
		  		</div>
		  		<div><a style="display:block; padding-top:15px; padding-bottom:15px; border-radius:0 0 25px 25px" href="${pageContext.request.contextPath}/ProductID?id=${item.id}"><b>${item.name}</b></a></div>
		  	</div>

	 		 </c:forEach>
	 	</div>
	</c:if>
    </div>
	
</div>
