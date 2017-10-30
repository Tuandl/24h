<%-- 
    Document   : censor-page
    Created on : Oct 28, 2017, 8:34:45 AM
    Author     : TUANDASE62310
--%>

<%@page import="tuanvxm.DTOs.ArticleDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <jsp:include page="partial/common-header.jsp">
            <jsp:param name="title" value="24h - Kiểm duyệt"/>
        </jsp:include>
        <!--css for current page-->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/censor-page.css">
    </head>
    <body class="template-page">
        <jsp:include page="partial/navbar.jsp"/>
        <div class="wrapper">
            <jsp:include page="partial/categories.jsp">
                <jsp:param name="title" value="Kiểm duyệt"/>
            </jsp:include>
            <div class="main">
                <div class="container">
                    <jsp:include page="partial/notification.jsp">
                        <jsp:param name="ERROR" value="${requestScope.ERROR}"/>
                        <jsp:param name="INFO" value="${requestScope.INFO}"/>
                        <jsp:param name="SUCCESS" value="${requestScope.SUCCESS}"/>
                        <jsp:param name="WARNING" value="${requestScope.WARNING}"/>
                    </jsp:include>

                    <%
                        List<ArticleDTO> newArticles = (List<ArticleDTO>) request.getAttribute("NEW-ARTICLE-LIST");
                        List<ArticleDTO> hidedArticles = (List<ArticleDTO>) request.getAttribute("HIDED-ARTICLE-LIST");
                        newArticles.addAll(hidedArticles);
                        pageContext.setAttribute("articles", newArticles);
                        pageContext.setAttribute("curPage", request.getAttribute("curPage"));
                        int totalPage = Integer.parseInt(request.getAttribute("MAXNEWPAGE").toString());
                        totalPage = Math.max(totalPage, Integer.parseInt(request.getAttribute("MAXHIDEDPAGE").toString()));
                        pageContext.setAttribute("totalPage", totalPage);
                    %>

                    <div class="section">
                        <article>
                            <div class="row">
                                <c:forEach items="${articles}" var="article">
                                    <div class="col-md-6">
                                        <div class="article-preview">
                                            <a href="${pageContext.request.contextPath}/ReadArticle.action?articleID=${article.articleID}&articleCreator=${article.creator}">
                                                <div class="row">
                                                    <div class="col-xs-12">
                                                        <div class="article-preview-img">
                                                            <img src="${article.thumbnail}" alt="">
                                                        </div>
                                                        <div class="article-preview-header">${article.title}</div>
                                                        <div class="article-preview-main-content">${article.headline}</div>
                                                        <div class="article-preview-author">${article.creator}</div>
                                                    </div>
                                                </div>
                                            </a>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>

                            <div class="row" style="text-align: center;">
                                <jsp:include page="partial/pagination.jsp">
                                    <jsp:param name="curPage" value="${curPage}"/>
                                    <jsp:param name="totalPage" value="${totalPage}"/>
                                    <jsp:param name="baseURL" value="${pageContext.request.contextPath}/tuanda/censor-page.jsp?"/>
                                </jsp:include>
                            </div>
                        </article>
                    </div>
                </div>
            </div>
        </div>

        <jsp:include page="partial/footer.jsp"/>

        <c:if test="${empty sessionScope.USER}">
            <jsp:include page="partial/login-popup.jsp">
                <jsp:param name="txtUsername" value="${requestScope.txtUsername}"/>
                <jsp:param name="pwfPassword" value="${requestScope.pwfPassword}"/>
            </jsp:include>
            <jsp:include page="partial/register-popup.jsp">
                <jsp:param name="txtUsername" value="${requestScope.txtUsername}"/>
                <jsp:param name="pwfPassword" value="${requestScope.pwfPassword}"/>
                <jsp:param name="pwfConfirmPassword" value="${requestScope.pwfConfirmPassword}"/>
                <jsp:param name="txtName" value="${requestScope.txtName}"/>
                <jsp:param name="txtAddress" value="${requestScope.txtAddress}"/>
                <jsp:param name="txtPhoneNumber" value="${requestScope.txtPhoneNumber}"/>
                <jsp:param name="txtEmail" value="${requestScope.txtEmail}"/>
            </jsp:include>
        </c:if>

        <jsp:include page="partial/common-js.jsp"/>
    </body>


</html>