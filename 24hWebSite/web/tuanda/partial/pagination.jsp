<%-- 
    Document   : pagination
    Created on : Oct 30, 2017, 12:15:13 AM
    Author     : TUANDASE62310
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<ul class="pagination pagination-success">
    <c:choose>
        <c:when test="${param.curPage == 0}">
            <li>
                <a><</a>
            </li>
        </c:when>
        <c:otherwise>
            <li>
                <a href="${param.baseURL}txtPage=${param.curPage}"><</a>
            </li>
        </c:otherwise>
    </c:choose>
    <%
        int totalPage = Integer.parseInt(request.getParameter("totalPage").toString());
        int curPage = Integer.parseInt(request.getParameter("curPage").toString());
        for (int i = 0; i < totalPage; i++) {
            if (i == curPage) {
    %>
    <li class="active"><a><%=i+1%></a></li>
            <%
            } else {
            %>
    <li><a href="${param.baseURL}txtPage=<%=i+1%>"><%=i+1%></a></li>
        <%
                }
            }
        %>
        <c:choose>
            <c:when test="${param.curPage == (param.totalPage - 1)}">
            <li>
                <a>></a>
            </li>
        </c:when>
        <c:otherwise>
            <li>
                <a href="${param.baseURL}txtPage=${param.curPage + 2}">></a>
            </li>
        </c:otherwise>
    </c:choose>
</ul>