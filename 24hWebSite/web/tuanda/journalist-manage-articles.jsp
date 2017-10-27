<%-- 
    Document   : journalist-manage-articles
    Created on : Oct 27, 2017, 11:30:07 PM
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
            <jsp:param name="title" value="24h - Quản lý bài viết"/>
        </jsp:include>
        <!--css for current page-->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/censor-page.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/manage-article-page.css">
    </head>
    <body class="template-page">
        <jsp:include page="partial/navbar.jsp"/>
        <div class="wrapper">
            <jsp:include page="partial/categories.jsp">
                <jsp:param name="title" value="Quản lý bài viết"/>
            </jsp:include>
            <div class="main">
                <div class="container">
                    <jsp:include page="partial/notification.jsp">
                        <jsp:param name="ERROR" value="${requestScope.ERROR}"/>
                        <jsp:param name="INFO" value="${requestScope.INFO}"/>
                        <jsp:param name="SUCCESS" value="${requestScope.SUCCESS}"/>
                        <jsp:param name="WARNING" value="${requestScope.WARNING}"/>
                    </jsp:include>

                    <div class="section">
                        <article>

                            <div class="row">
                                <%
                                    List<ArticleDTO> articles = (List<ArticleDTO>) request.getAttribute("ARTICLE-LIST");
                                    pageContext.setAttribute("articles", articles);
                                %>
                                <c:forEach items="${articles}" var="article">
                                    <div class="col-md-6">
                                        <div class="article-preview">
                                            <div class="btn-area">
                                                <a href="${pageContext.request.contextPath}/UpdateArticle.action" class="btn btn-success btn-white btn-just-icon">
                                                    <i class="material-icons">mode_edit</i>
                                                </a>
                                                <c:choose>
                                                    <c:when test="${article.status == 'Available'}">
                                                        <button class="btn btn-success btn-white btn-just-icon">
                                                            <i class="material-icons">check_circle</i>
                                                        </button>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <button class="btn btn-success btn-white btn-just-icon">
                                                            <i class="material-icons">sync</i>
                                                        </button>
                                                    </c:otherwise>
                                                </c:choose>

                                            </div>
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