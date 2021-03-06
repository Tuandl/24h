<%-- 
    Document   : search-result
    Created on : Oct 27, 2017, 12:48:41 PM
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
            <jsp:param name="title" value="Kết quả tìm kiếm cho ${requestScope.txtSearch}"/>
        </jsp:include>
        <!--css for current page-->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/banner.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/search-result-page.css">
    </head>
    <body class="template-page">
        <jsp:include page="partial/navbar.jsp"/>
        <div class="wrapper">
            <jsp:include page="partial/categories.jsp">
                <jsp:param name="title" value="Kết quả tìm kiếm"/>
            </jsp:include>
            <div class="main">
                <div class="container">
                    <jsp:include page="partial/notification.jsp">
                        <jsp:param name="ERROR" value="${requestScope.ERROR}"/>
                        <jsp:param name="INFO" value="${requestScope.INFO}"/>
                        <jsp:param name="SUCCESS" value="${requestScope.SUCCESS}"/>
                        <jsp:param name="WARNING" value="${requestScope.WARNING}"/>
                    </jsp:include>
                    <article class="section">
                        <%
                            List<ArticleDTO> articles = (List<ArticleDTO>) request.getAttribute("SEARCH-RESULT-LIST");
                            pageContext.setAttribute("numberResult", articles.size());
                            pageContext.setAttribute("articles", articles);
                        %>
                        <div class="row">
                            <div class="col-md-9 col-sm-12">
                                <!-- key word search -->
                                <h3 class="search-result-keyword">'${requestScope.txtSearch}'</h3>
                                <h6 class="search-result-count">Có ${numberResult} kết quả phù hợp</h6>
                                <div class="section search-results">
                                    <div class="row">
                                        <c:forEach items="${articles}" var="article">
                                            <!-- begin a row -->
                                            <div class="col-sm-6">
                                                <div class="article-preview">
                                                    <a href="${pageContext.request.contextPath}/ReadArticle.action?articleID=${article.articleID}&articleCreator=${article.creator}">
                                                        <div class="article-preview-img">
                                                            <img src="${article.thumbnail}" alt="">
                                                        </div>
                                                        <div class="article-preview-title">${article.title}</div>
                                                        <div class="article-preview-main-content">${article.headline}</div>
                                                        <div class="article-preview-author">${article.creator}</div>
                                                    </a>
                                                </div>
                                            </div>
                                            <!-- end a row -->
                                        </c:forEach>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3 col-sm-12">
                                <%--<%= // "Number of trending: " + ((List<ArticleDTO>) request.getAttribute("TOP-TREND-LIST")).size()%>--%>
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
        <!-- javascript for current page -->
        <script src="${pageContext.request.contextPath}/assets/js/search-result.js" type="text/javascript"></script>
    </body>


</html>