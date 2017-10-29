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
                        <div>
                            <form action="${pageContext.request.contextPath}/upload_image" id="upload-form" class="form-horizontal form-upload-thumbnail"  method="POST" enctype="multipart/form-data">
                                <div class="row">
                                    <label>Tải Thumbnail</label>
                                </div>
                                <div class="row">
                                    <input type="file" name="file" id="fileUploader" class="btn btn-success"/>
                                </div>
                                <img id="thumbnail-img" src=""/>
                            </form>
                        </div>
                            <form action="${pageContext.request.contextPath}/CreateAticle.action" class="form-horizontal" method="POST">
                            <input type="hidden" id="thumbnail-url" name="txtThumbnailURL"/>
                            <div class="center-block">
                                <div class="form-group create-article-title">
                                    <label class="control-label control-label-normal col-sm-3" for="title">Tiêu đề:</label>
                                    <div class="col-sm-9">
                                        <input type="text" class="form-control" id="title" placeholder="Ghi tiêu đề ở đây" name="txtTitle">
                                    </div>
                                </div>
                                <div class="form-group create-article-category">
                                    <label class="control-label control-label-normal col-sm-5" for="category">Thể loại:</label>
                                    <div class="col-sm-7">
                                        <select class="form-control" id="category" name="cbCategory">
                                            <option>Tin hàng ngày</option>
                                            <option>Thể thao</option>
                                            <option>Thế giới</option>
                                            <option>Thời trang</option>
                                            <option>An ninh - xã hội</option>
                                            <option>Hi-tech</option>
                                            <option>Tài chính - Địa ốc</option>
                                            <option>Ẩm thực</option>
                                            <option>Sắc đẹp</option>
                                            <option>Showbiz</option>
                                            <option>Giải trí</option>
                                            <option>Nhịp sống trẻ</option>
                                            <option>Giáo dục</option>
                                            <option>Ô tô</option>
                                            <option>Xe máy</option>
                                            <option>Thị trường - Tiêu dùng</option>
                                            <option>Du lịch</option>
                                            <option>Sức khỏe</option>
                                        </select>
                                    </div>
                                </div>

                                <div class="form-group create-article-main-content">
                                    <label class="control-label control-label-normal" for="main-content">Nội dung chính:</label>
                                    <textarea class="form-control" name="txtHeadline" id="main-content" rows="4" placeholder="Ghi nội tóm tắt nội dung ở đây..."></textarea>
                                </div>
                            </div>

                            <div class="create-article-content">
                                <textarea name="txtContent" id="new-article"></textarea>
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