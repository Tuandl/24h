<%-- 
    Document   : create-article
    Created on : Oct 28, 2017, 1:19:30 AM
    Author     : TUANDASE62310
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <jsp:include page="partial/common-header.jsp">
            <jsp:param name="title" value="24h - Viết bài"/>
        </jsp:include>
        <!--css for current page-->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/third_party/froala_style.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/third_party/froala_editor.pkgd.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/third_party/codemirror.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/third_party/create-article.css">
    </head>
    <body class="template-page">
        <jsp:include page="partial/navbar.jsp"/>
        <div class="wrapper">
            <jsp:include page="partial/categories.jsp">
                <jsp:param name="title" value="Viết bài mới"/>
            </jsp:include>
            <div class="main">
                <div class="container">
                    <jsp:include page="partial/notification.jsp">
                        <jsp:param name="ERROR" value="${requestScope.ERROR}"/>
                        <jsp:param name="INFO" value="${requestScope.INFO}"/>
                        <jsp:param name="SUCCESS" value="${requestScope.SUCCESS}"/>
                        <jsp:param name="WARNING" value="${requestScope.WARNING}"/>
                    </jsp:include>
                    <div class="section create-article">
                        <div class="row">
                            <div class="col-md-6" style="height: 0px;">
                                
                            </div>
                            <div class="col-md-6 form-upload-thumbnail">
                                <form action="${pageContext.request.contextPath}/upload_image" id="upload-form" class="form-horizontal"  method="POST" enctype="multipart/form-data">
                                    <div class="row">
                                        <label>Tải Thumbnail</label>
                                    </div>
                                    <div class="row">
                                        <input type="file" name="file" id="fileUploader" class="btn btn-success"/>
                                    </div>
                                    <img id="thumbnail-img" src="${requestScope.ARTICLE.thumbnail}"/>
                                </form>
                            </div>    
                        </div>

                        <c:choose>
                            <c:when test="${empty requestScope.ARTICLE}">
                                <c:set var="actionUrl" value="CreateAticle.action"/>
                            </c:when>
                            <c:otherwise>
                                <c:set var="actionUrl" value="UpdateArticle.action"/>
                            </c:otherwise>
                        </c:choose>
                        <form action="${pageContext.request.contextPath}/${actionUrl}" class="form-horizontal" method="POST">
                            <input type="hidden" id="thumbnail-url" name="txtThumbnailURL"
                                   value="${requestScope.ARTICLE.thumbnail}"/>
                            <input type="hidden" name="articleID" value="${requestScope.ARTICLE.articleID}"/>
                            <div class="center-block">
                                <div class="form-group create-article-title">
                                    <label class="control-label control-label-normal col-sm-3" for="title">Tiêu đề:</label>
                                    <div class="col-sm-9">
                                        <input type="text" class="form-control" id="title" placeholder="Ghi tiêu đề ở đây" name="txtTitle"
                                               value="${requestScope.ARTICLE.title}">
                                    </div>
                                </div>
                                <div class="form-group create-article-category">
                                    <label class="control-label control-label-normal col-sm-5" for="category">Thể loại:</label>
                                    <div class="col-sm-7">
                                        <select class="form-control" id="category" name="cbCategory">
                                            <c:choose>
                                                <c:when test="${requestScope.ARTICLE.categoryID eq 1}"><option selected>Tin hàng ngày</option></c:when>
                                                <c:otherwise><option>Tin hàng ngày</option></c:otherwise>
                                            </c:choose>
                                            <c:choose>
                                                <c:when test="${requestScope.ARTICLE.categoryID eq 2}"><option selected>Thể thao</option></c:when>
                                                <c:otherwise><option>Thể thao</option></c:otherwise>
                                            </c:choose>
                                            <c:choose>
                                                <c:when test="${requestScope.ARTICLE.categoryID eq 3}"><option selected>Thế giới</option></c:when>
                                                <c:otherwise><option>Thế giới</option></c:otherwise>
                                            </c:choose>
                                            <c:choose>
                                                <c:when test="${requestScope.ARTICLE.categoryID eq 4}"><option selected>Thời trang</option></c:when>
                                                <c:otherwise><option>Thời trang</option></c:otherwise>
                                            </c:choose>
                                            <c:choose>
                                                <c:when test="${requestScope.ARTICLE.categoryID eq 5}"><option selected>An ninh - xã hội</option></c:when>
                                                <c:otherwise><option>An ninh - xã hội</option></c:otherwise>
                                            </c:choose>
                                            <c:choose>
                                                <c:when test="${requestScope.ARTICLE.categoryID eq 6}"><option selected>Hi-tech</option></c:when>
                                                <c:otherwise><option>Hi-tech</option></c:otherwise>
                                            </c:choose>
                                            <c:choose>
                                                <c:when test="${requestScope.ARTICLE.categoryID eq 7}"><option selected>Tài chính - Địa ốc</option></c:when>
                                                <c:otherwise><option>Tài chính - Địa ốc</option></c:otherwise>
                                            </c:choose>
                                            <c:choose>
                                                <c:when test="${requestScope.ARTICLE.categoryID eq 8}"><option selected>Ẩm thực</option></c:when>
                                                <c:otherwise><option>Ẩm thực</option></c:otherwise>
                                            </c:choose>
                                            <c:choose>
                                                <c:when test="${requestScope.ARTICLE.categoryID eq 9}"><option selected>Sắc đẹp</option></c:when>
                                                <c:otherwise><option>Sắc đẹp</option></c:otherwise>
                                            </c:choose>
                                            <c:choose>
                                                <c:when test="${requestScope.ARTICLE.categoryID eq 10}"><option selected>Showbiz</option></c:when>
                                                <c:otherwise><option>Showbiz</option></c:otherwise>
                                            </c:choose>
                                            <c:choose>
                                                <c:when test="${requestScope.ARTICLE.categoryID eq 11}"><option selected>Giải trí</option></c:when>
                                                <c:otherwise><option>Giải trí</option></c:otherwise>
                                            </c:choose>
                                            <c:choose>
                                                <c:when test="${requestScope.ARTICLE.categoryID eq 12}"><option selected>Nhịp sống trẻ</option></c:when>
                                                <c:otherwise><option>Nhịp sống trẻ</option></c:otherwise>
                                            </c:choose>
                                            <c:choose>
                                                <c:when test="${requestScope.ARTICLE.categoryID eq 13}"><option selected>Giáo dục</option></c:when>
                                                <c:otherwise><option>Giáo dục</option></c:otherwise>
                                            </c:choose>
                                            <c:choose>
                                                <c:when test="${requestScope.ARTICLE.categoryID eq 14}"><option selected>Ô tô</option></c:when>
                                                <c:otherwise><option>Ô tô</option></c:otherwise>
                                            </c:choose>
                                            <c:choose>
                                                <c:when test="${requestScope.ARTICLE.categoryID eq 15}"><option selected>Xe máy</option></c:when>
                                                <c:otherwise><option>Xe máy</option></c:otherwise>
                                            </c:choose>
                                            <c:choose>
                                                <c:when test="${requestScope.ARTICLE.categoryID eq 16}"><option selected>Thị trường - Tiêu dùng</option></c:when>
                                                <c:otherwise><option>Thị trường - Tiêu dùng</option></c:otherwise>
                                            </c:choose>
                                            <c:choose>
                                                <c:when test="${requestScope.ARTICLE.categoryID eq 17}"><option selected>Du lịch</option></c:when>
                                                <c:otherwise><option>Du lịch</option></c:otherwise>
                                            </c:choose>
                                            <c:choose>
                                                <c:when test="${requestScope.ARTICLE.categoryID eq 18}"><option selected>Sức khỏe</option></c:when>
                                                <c:otherwise><option>Sức khỏe</option></c:otherwise>
                                            </c:choose>
                                        </select>
                                    </div>
                                </div>

                                <div class="form-group create-article-main-content">
                                    <label class="control-label control-label-normal" for="main-content">Nội dung chính:</label>
                                    <textarea class="form-control" name="txtHeadline" id="main-content" rows="4" placeholder="Ghi nội tóm tắt nội dung ở đây...">${requestScope.ARTICLE.headline}</textarea>
                                </div>
                            </div>

                            <div class="create-article-content">
                                <textarea name="txtContent" id="new-article">${requestScope.ARTICLE.content}</textarea>
                            </div>

                            <div class="btn-submit-area">
                                <button type="submit" class="btn btn-info">Gởi bài</button>
                            </div>
                        </form>
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
        <!-- javascript for current page -->
        <script src="${pageContext.request.contextPath}/assets/js/third_party/froala_editor.pkgd.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/assets/js/third_party/codemirror/codemirror.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/assets/js/third_party/codemirror/mode/xml.js" type="text/javascript"></script>

        <script src="${pageContext.request.contextPath}/assets/js/jquery.form.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/assets/js/create-article.js" type="text/javascript"></script>
    </body>


</html>