<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>

<html>
<%@include file="/WEB-INF/includes/header.jsp" %>
<!--  
	<div id="content">
		<center><h1>Result of your search :)</h1></center>
			<div style="text-align:center" id="content">
				<c:if test="${fn:length(itemListByName) gt 0}">
					<c:forEach items="${itemListByName}" var="item">
						<div class="contentForUsers">
								<div>
						  			<a href="${pageContext.request.contextPath }/ProductID?id=${item.id}">
						  				<img alt ="SOME ITEMS" class="top-image" src ="path"${item.img}" style="border-radius: 25px 25px 0 0;" width="150" height="140">
						  			</a>
					  			</div>
					  			<div>
						  			<a style="display:block; padding-top:15px; padding-bottom:15px; border-radius:0 0 25px 25px" href="${pageContext.request.contextPath}/ProductID?id=${item.id}">
						  				<b>${item.name}</b>
						  			</a>
						  		</div>
				  		</div>
					</c:forEach>
				</c:if>
				<c:otherwise>
					<div id="SearchServletID"><center><h1>OOOOOoooops.... Anything have not found</h1></center></div>
				</c:otherwise>	
			</div>
	</div>
	-->
<%@include file='/WEB-INF/includes/footer.jsp' %>
</html>