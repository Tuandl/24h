<%-- 
    Document   : banner
    Created on : Oct 26, 2017, 6:18:26 PM
    Author     : TUANDASE62310
--%>

<%@page import="tuanvxm.DTOs.ArticleDTO"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!-- begin section banner -->
<div class="section banner">
    <%
        List<ArticleDTO> articles = (List<ArticleDTO>) request.getAttribute("TOP-TREND-LIST");
        pageContext.setAttribute("listContent", articles);
    %>
    <c:if test="${not empty listContent}">
        <c:forEach items="${listContent}" var="item">
            <div class="banner-header">
                ${param.title}
            </div>
            <div class="banner-content">
                <div class="article-preview">
                    <a href="${pageContext.request.contextPath}/ReadArticle.action?articleID=${item.articleID}&articleCreator=${item.creator}">
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="article-preview-img">
                                    <img src="${pageContext.request.contextPath}/assets/img/${item.thumbnail}" alt="">
                                </div>
                                <div class="article-preview-header">${item.title}</div>
                                <div class="article-preview-author">${item.creator}</div>
                            </div>
                        </div>
                    </a>
                </div>
            </div>
        </c:forEach>
    </c:if>
</div>
<!-- end section-banner -->