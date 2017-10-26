<%-- 
    Document   : ảticle
    Created on : Oct 26, 2017, 5:33:39 PM
    Author     : TUANDASE62310
--%>

<%@page import="tuanvxm.DTOs.CommentDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <jsp:include page="partial/common-header.jsp">
            <jsp:param name="title" value="${requestScope.ARTICLE.title}"/>
        </jsp:include>

        <!--css for current page-->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/article-page.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/banner.css">
    </head>
    <body class="template-page">
        <jsp:include page="partial/navbar.jsp"/>
        <div class="wrapper">
            <jsp:include page="partial/categories.jsp">
                <jsp:param name="title" value="${requestScope.ARTICLE.categoryID}"/>
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
                        <div class="row">
                            <div class="col-md-8">
                                <div class="article-header">
                                    <h2>${requestScope.ARTICLE.title}</h2>
                                    <h5><small>${requestScope.ARTICLE.createdTime}</small></h5>
                                </div>
                                <p class="article-main-content">${requestScope.ARTICLE.headline}</p>
                                <div class="article-content">
                                    ${requestScope.ARTICLE.content}
                                </div>
                                <div class="article-author">
                                    Theo <span>${requestScope.ARTICLE.creator}</span>
                                </div>
                                <!--comment section-->
                                <div class="section comment">
                                    <div class="comment-title">
                                        <h2>Ý kiến bạn đọc</h2>
                                    </div>
                                    <%
                                        List<CommentDTO> comments = (List<CommentDTO>) request.getAttribute("COMMENT-LIST");
                                        pageContext.setAttribute("comments", comments);
                                    %>

                                    <c:forEach items="${comments}" var="comment">
                                        <c:choose>
                                            <c:when test="${comment.status == 'Available'}">
                                                <div class="media">
                                                    <c:if test="${sessionScope.USER.userID == comment.creatorID}">
                                                        <div class="comment-buttons">
                                                            <a class="btn btn-simple" href="${pageContext.request.contextPath}/ChangeCommentStatus.action?articleID=${requestScope.ARTICLE.articleID}&commentID=${comment.commentID}&txtStatus=HideByReader">Ẩn</a>
                                                            <a class="btn btn-white" href="${pageContext.request.contextPath}/ChangeCommentStatus.action?articleID=${requestScope.ARTICLE.articleID}&commentID=${comment.commentID}&txtStatus=Available">Hiện</a>
                                                        </div>
                                                    </c:if>
                                                    <div class="media-left">
                                                        <img src="https://www.w3schools.com/bootstrap/img_avatar2.png" class="media-object" style="width:45px">
                                                    </div>
                                                    <div class="media-body">
                                                        <h4 class="media-heading"> ${comment.creatorID} <small><i>${comment.createdTime}</i></small></h4>
                                                        <p>${comment.content}</p>
                                                    </div>
                                                </div>
                                            </c:when>
                                            <c:otherwise>
                                                <div class="media comment-hidden">
                                                    <div class="comment-buttons">
                                                        <a class="btn btn-simple" href="${pageContext.request.contextPath}/ChangeCommentStatus.action?articleID=${requestScope.ARTICLE.articleID}&commentID=${comment.commentID}&txtStatus=HideByReader">Ẩn</a>
                                                        <a class="btn btn-white" href="${pageContext.request.contextPath}/ChangeCommentStatus.action?articleID=${requestScope.ARTICLE.articleID}&commentID=${comment.commentID}&txtStatus=Available">Hiện</a>
                                                    </div>
                                                    <div class="media-left">
                                                        <img src="https://www.w3schools.com/bootstrap/img_avatar2.png" class="media-object" style="width:45px">
                                                    </div>
                                                    <div class="media-body">
                                                        <h4 class="media-heading"> ${comment.creatorID} <small><i>${comment.createdTime}</i></small></h4>
                                                        <p>${comment.content}</p>
                                                    </div>
                                                </div>
                                            </c:otherwise>
                                        </c:choose>

                                    </c:forEach>
                                    <%--</c:if>--%>
                                    <div class="jumbotron new-comment">
                                        <form action="" class="form-group">
                                            <textarea name="newcomment" rows="5" placeholder="Bạn nghĩ gì về tin này?"></textarea>
                                            <div class="submit-comment">
                                                <button class="btn btn-success" type="submit">Gửi bình luận</button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <jsp:include page="partial/banner.jsp"/>
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
        <script src="${pageContext.request.contextPath}/assets/js/comment-button.js" type="text/javascript"></script>
    </body>


</html>