<%-- 
    Document   : admin-view-account-detail
    Created on : Oct 29, 2017, 5:28:21 PM
    Author     : TUANDASE62310
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <jsp:include page="partial/common-header.jsp">
            <jsp:param name="title" value="24h - Thông tin tài khoản"/>
        </jsp:include>
        <!--css for current page-->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/profile-page.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin-view-account-detail-page.css">
    </head>
    <body class="template-page">
        <jsp:include page="partial/navbar.jsp"/>
        <div class="wrapper">
            <jsp:include page="partial/categories.jsp">
                <jsp:param name="title" value="Thông tin chi tiết"/>
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
                        pageContext.setAttribute("user", request.getAttribute("USER-INFORMATION"));
                    %>
                    <div class="row">
                        <div class="col-md-4">
                            <!-- Avatar -->
                            <div class="section">
                                <div class="profile-avatar img-circle img-raised">
                                    <img class="" src="https://www.w3schools.com/bootstrap/img_avatar2.png" alt="">
                                </div>
                            </div>
                        </div>
                        <div class="col-md-8">
                            <!-- Personal information -->
                            <div class="section profile-information">
                                <!-- username -->
                                <div class="row">
                                    <div class="col-sm-4 col-sm-offset-1 col-xs-3">
                                        Username:
                                    </div>
                                    <div class="col-sm-7 col-xs-9">
                                        <c:out value="${user.username}"/>
                                    </div>
                                </div>
                                <!-- name -->
                                <div class="row">
                                    <div class="col-sm-4 col-sm-offset-1 col-xs-3">
                                        Tên:
                                    </div>
                                    <div class="col-sm-7 col-xs-9">
                                        <c:out value="${user.name}"/>
                                    </div>
                                </div>
                                <!-- bỉthday -->
                                <div class="row">
                                    <div class="col-sm-4 col-sm-offset-1 col-xs-3">
                                        Ngày sinh:
                                    </div>
                                    <div class="col-sm-7 col-xs-9">
                                        <c:out value="${user.birthday}"/>
                                    </div>
                                </div>
                                <!-- Gender -->
                                <div class="row">
                                    <div class="col-sm-4 col-sm-offset-1 col-xs-3">
                                        Giới tính:
                                    </div>
                                    <div class="col-sm-7 col-xs-9">
                                        <c:choose>
                                            <c:when test="${user.gender == 1}">
                                                Nam
                                            </c:when>
                                            <c:when test="${user.gender == 2}">
                                                Nữ
                                            </c:when>
                                            <c:otherwise>
                                                Khác
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </div>
                                <!-- Address -->
                                <div class="row">
                                    <div class="col-sm-4 col-sm-offset-1 col-xs-3">
                                        Địa chỉ:
                                    </div>
                                    <div class="col-sm-7 col-xs-9">
                                        <c:out value="${user.address}"/>
                                    </div>
                                </div>
                                <!-- Phone -->
                                <div class="row">
                                    <div class="col-sm-4 col-sm-offset-1 col-xs-3">
                                        Số điện thoại:
                                    </div>
                                    <div class="col-sm-7 col-xs-9">
                                        <c:out value="${user.phone}"/>
                                    </div>
                                </div>
                                <!-- Email -->
                                <div class="row">
                                    <div class="col-sm-4 col-sm-offset-1 col-xs-3">
                                        Email:
                                    </div>
                                    <div class="col-sm-7 col-xs-9">
                                        <c:out value="${user.email}"/>
                                    </div>
                                </div>
                                <!-- People ID Card number -->
                                <div class="row">
                                    <div class="col-sm-4 col-sm-offset-1 col-xs-3">
                                        CMND:
                                    </div>
                                    <div class="col-sm-7 col-xs-9">
                                        <c:out value="${user.getPeopleIndentityCard()}"/>
                                    </div>
                                </div>
                                <!--Role -->
                                <div class="row">
                                    <div class="col-sm-4 col-sm-offset-1 col-xs-3">
                                        Vai trò:
                                    </div>
                                    <div class="col-sm-7 col-xs-9">
                                        <c:out value="${user.role}"/>
                                    </div>
                                </div>
                                <!-- state -->
                                <div class="row">
                                    <div class="col-sm-4 col-sm-offset-1 col-xs-3">
                                        Trạng thái:
                                    </div>
                                    <div class="col-sm-7 col-xs-9">
                                        <c:out value="${user.status}"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>


                    <div class="section">
                        <div class="item-center">
                            <a href="${pageContext.request.contextPath}/tuanda/admin-view-account-page.jsp" class="btn btn-info">Trở lại</a>
                            <button class="btn btn-info" data-toggle="modal" data-target="#edit-role-modal">Thay đổi vai trò</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <jsp:include page="partial/footer.jsp"/>

        <!-- Start edit role modal -->
        <div class="modal fade" id="edit-role-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title" id="myModalLabel">Thay đổi vai trò</h4>
                    </div>
                    <form action="${pageContext.request.contextPath}/ChangeAccountRole.action" class="form-horizontal">
                        <input type="hidden" name="txtUserID" value="${user.userID}"/>
                        <div class="modal-body">
                            <div class="row">
                                <div class="col-xs-10 col-xs-offset-1">
                                    <div class="radio">
                                        <label>
                                            <input type="radio" name="txtRole" value="Reader" <c:if test="${user.roleID == 4}">checked</c:if>>
                                            Đọc giả
                                        </label>
                                    </div>
                                    <div class="radio">
                                        <label>
                                            <input type="radio" name="txtRole" value="Journalist" id="rdJournalist" <c:if test="${user.roleID == 3}">checked</c:if>>
                                            Nhà báo
                                        </label>
                                    </div>
                                    <div class="row hidden" id="press-card-input">
                                        <div class="col-xs-offset-1 col-xs-11">
                                            <div class="form-group label-floating mr-small">
                                                <label class="control-label">Số thẻ nhà báo</label>
                                                <input type="text" name="txtPressCard" class="form-control" value="${user.getPressCard()}">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="radio">
                                        <label>
                                            <input type="radio" name="txtRole" value="Editor" <c:if test="${user.roleID == 2}">checked</c:if>>
                                            Cộng tác viên
                                        </label>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default btn-simple" data-dismiss="modal">Thoát</button>
                            <button type="submit" class="btn btn-info btn-simple">Lưu</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!-- end edit role modal -->

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
        <script src="${pageContext.request.contextPath}/assets/js/admin-view-account-detail.js" type="text/javascript"></script>
    </body>


</html>