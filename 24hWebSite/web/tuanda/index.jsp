<%-- 
    Document   : index
    Created on : Oct 26, 2017, 10:46:49 AM
    Author     : TUANDASE62310
--%>

<%@page import="java.util.Map"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="tuanvxm.DTOs.ArticleDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <jsp:include page="partial/common-header.jsp">
            <jsp:param name="title" value="24h Trang Chủ"/>
        </jsp:include>
        <!--css for current page-->
        <link rel="stylesheet" href="../assets/css/index-page.css">
    </head>
    <body class="template-page">
        <jsp:include page="partial/navbar.jsp"/>
        <div class="wrapper">
            <jsp:include page="partial/categories.jsp"/>
            <div class="main">
                <div class="container">
                    <%
                        HashMap<String, ArrayList<ArticleDTO>> articalListByCategory
                                = (HashMap<String, ArrayList<ArticleDTO>>) request.getAttribute("ARTICLE-LIST-BY-CATEGORY");
                        for (Map.Entry<String, ArrayList<ArticleDTO>> entry : articalListByCategory.entrySet()) {
                            String key = entry.getKey();
                            if (entry.getValue().size() == 0) {
                                continue;
                            }
                            pageContext.setAttribute("key", key);
                            pageContext.setAttribute("articleList", entry.getValue());
                            pageContext.setAttribute("firstArticle", entry.getValue().get(0));
                    %>
                    <div class="section">
                        <a href="">
                            <h4 class="section-title">
                                ${key}
                                <i class="material-icons">keyboard_arrow_right</i>
                            </h4>
                        </a>
                        <div class="row">
                            <div class="col-md-7">
                                <div class="article-preview article-highline">
                                    <a href="${firstArticle.articleID}">
                                        <img class="img-responsive" src="${firstArticle.thumbnail}" alt="article thumbnail">
                                        <span class="article-title">${firstArticle.title}</span>
                                        <span class="article-content">${firstArticle.headline}</span>
                                        <span class="article-author">${firstArticle.creatorID}</span>
                                    </a>
                                </div>
                            </div>
                            <div class="col-md-5">
                                <c:forEach items="${articleList}" var="article" varStatus="loop">
                                    <c:if test="${loop.index != 0 && loop.index < 5}">
                                        <div class="row">
                                            <div class="col-xs-12">
                                                <div class="article-preview">
                                                    <a href="${article.articleID}}">
                                                        <div class="square-img">
                                                            <img class="" src="${article.thumbnail}" alt="thumbnail article">
                                                        </div>
                                                        <span class="article-title">${article.title}</span>
                                                        <span class="article-author">${article.creatorID}</span>
                                                    </a>
                                                </div>
                                            </div>
                                        </div>
                                    </c:if>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                    <!-- end a section -->
                    <%}%>
                </div>
            </div>
        </div>

        <jsp:include page="partial/footer.jsp"/>

        <c:if test="${empty sessionScope.USER}">
            <jsp:include page="partial/login-popup.jsp"/>
            <jsp:include page="partial/register-popup.jsp"/>
        </c:if>

        <jsp:include page="partial/common-js.jsp"/>
        <!-- javascript for current page -->
        <script src="../assets/js/index.js" type="text/javascript"></script>
    </body>


</html>
