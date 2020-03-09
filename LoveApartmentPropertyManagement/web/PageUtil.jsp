<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String url=request.getParameter("url");
	if(url.indexOf("?")!=-1){
		url+="&";
	}else{
		url+="?";
	}
	
	pageContext.setAttribute("url", url);
%>

<c:if test="${page.curPage!=page.firstPage }">
	<a href="${url }curPage=${page.firstPage }"><button>首页</button></a>
	<a href="${url }curPage=${page.prevPage }"><button>上一页</button></a>
</c:if>
<c:forEach begin="${page.beginNav }" end="${page.endNav }" var="i">
	<c:choose>
		<c:when test="${page.curPage==i }">
			<font color='red'>${i }</font>
		</c:when>
		<c:otherwise>
			<a href="${url }curPage=${i }" style="text-decoration: none;color: green">${i }</a>
		</c:otherwise>
	</c:choose>
</c:forEach>
<c:if test="${page.curPage!=page.lastPage }">
	<a href="${url }curPage=${page.nextPage}"><button>下一页</button></a>
	<a href="${url }curPage=${page.lastPage}"><button>尾页</button></a>
</c:if>