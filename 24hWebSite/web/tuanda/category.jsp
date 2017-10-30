<%-- 
    Document   : category
    Created on : Oct 27, 2017, 10:02:28 AM
    Author     : TUANDASE62310
--%>

<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="tuanvxm.DTOs.ArticleDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <% pageContext.setAttribute("categoryName", request.getAttribute("CATEGORY-NAME")); %>
        <jsp:include page="partial/common-header.jsp">
            <jsp:param name="title" value="${categoryName}"/>
        </jsp:include>
        <!--css for current page-->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/banner.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/category-page.css">
    </head>
    <body class="template-page">
        <jsp:include page="partial/navbar.jsp"/>
        <div class="wrapper">
            <jsp:include page="partial/categories.jsp">
                <jsp:param name="title" value="${categoryName}"/>
                <jsp:param name="categoryId" value="${requestScope.categoryID}"/>
            </jsp:include>
            <div class="main">
                <div class="container">
                    <article class="section">
                        <div class="row">
                            <div class="col-md-9 col-sm-12">
                                <%
                                    List<ArticleDTO> articles = (List<ArticleDTO>) request.getAttribute("CATEGORY-ARTICLE");
                                    pageContext.setAttribute("articles", articles);
                                    if (articles.size() > 0) {
                                        pageContext.setAttribute("highline", articles.get(0));
                                    }
                                    pageContext.setAttribute("totalPage", request.getAttribute("MAX-PAGE"));
                                    pageContext.setAttribute("curPage", request.getAttribute("CUR-PAGE"));
                                %>
                                <c:if test="${not empty highline and curPage eq 0}">
                                    <!-- highline -->
                                    <div class="section article-preview article-preview-highline">
                                        <a href="${pageContext.request.contextPath}/ReadArticle.action?articleID=${highline.articleID}&articleCreator=${highline.creator}">
                                            <div class="row">
                                                <div class="col-sm-6">
                                                    <img class="img-responsive" src="${highline.thumbnail}" alt="">
                                                </div>
                                                <div class="col-sm-6">
                                                    <div class="article-preview-title">${highline.title}</div>
                                                    <div class="article-preview-main-content">${highline.headline}</div>
                                                    <div class="article-preview-author">${highline.creator}</div>
                                                </div>
                                            </div>
                                        </a>
                                    </div>
                                </c:if>
                                <div class="row">
                                    <c:forEach items="${articles}" var="article" varStatus="status">
                                        <c:if test="${(curPage == 0 and status.index > 0) or curPage > 0}">
                                            <div class="col-sm-6">
                                                <div class="article-preview article-preview-small">
                                                    <a href="${pageContext.request.contextPath}/ReadArticle.action?articleID=${article.articleID}&articleCreator=${article.creator}">
                                                        <div class="article-preview-img">
                                                            <img src="${article.thumbnail}" alt="">
                                                        </div>
                                                        <div class="article-preview-title">${article.title}</div>
                                                        <div class="article-preview-author">${article.creator}</div>
                                                    </a>
                                                </div>
                                            </div>
                                        </c:if>
                                    </c:forEach>

                                </div>

                                <div class="row" style="text-align: center;">
                                    <jsp:include page="partial/pagination.jsp">
                                        <jsp:param name="curPage" value="${curPage}"/>
                                        <jsp:param name="totalPage" value="${totalPage}"/>
                                        <jsp:param name="baseURL" value="${pageContext.request.contextPath}/LoadCategoryArticles.action?categoryID=${requestScope.categoryID}&"/>
                                    </jsp:include>
                                </div>
                            </div>
                            <div class="col-md-3 col-sm-12">
                                <jsp:include page="partial/banner.jsp">
                                    <jsp:param name="title" value="Đọc nhiều nhất"/>
                                    <jsp:param name="listContent" value="${requestScope.TOP-TREND-LIST}"/>
                                </jsp:include>
                            </div>
                        </div>
                    </article>
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
