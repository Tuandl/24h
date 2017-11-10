<%-- 
    Document   : profile
    Created on : Oct 27, 2017, 10:26:00 PM
    Author     : TUANDASE62310
--%>

<%@page import="tuanvxm.DTOs.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <jsp:include page="partial/common-header.jsp">
            <jsp:param name="title" value="24h - Thông tin cá nhân"/>
        </jsp:include>
        <!--css for current page-->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/profile-page.css">
    </head>
    <body class="template-page">
        <jsp:include page="partial/navbar.jsp"/>
        <div class="wrapper">
            <jsp:include page="partial/categories.jsp"/>
            <div class="main">
                <div class="container">
                    <jsp:include page="partial/notification.jsp">
                        <jsp:param name="ERROR" value="${requestScope.ERROR}"/>
                        <jsp:param name="INFO" value="${requestScope.INFO}"/>
                        <jsp:param name="SUCCESS" value="${requestScope.SUCCESS}"/>
                        <jsp:param name="WARNING" value="${requestScope.WARNING}"/>
                    </jsp:include>

                    <div class="row">
                        <div class="col-md-4">
                            <!-- Avatar -->
                            <div class="section">
                                <div class="profile-avatar img-circle img-raised">
                                    <img class="https://www.w3schools.com/bootstrap/img_avatar2.png" src="" alt="">
                                </div>
                            </div>
                        </div>
                        <div class="col-md-8">
                            <!-- Personal information -->
                            <div class="section profile-information">
                                <!-- name -->
                                <div class="row">
                                    <div class="col-sm-4 col-sm-offset-1 col-xs-3">
                                        Tên:
                                    </div>
                                    <div class="col-sm-7 col-xs-9">
                                        ${sessionScope.USER.name}
                                    </div>
                                </div>
                                <!-- bỉthday -->
                                <div class="row">
                                    <div class="col-sm-4 col-sm-offset-1 col-xs-3">
                                        Ngày sinh:
                                    </div>
                                    <div class="col-sm-7 col-xs-9">
                                        ${sessionScope.USER.birthdayString}
                                    </div>
                                </div>
                                <!-- Gender -->
                                <div class="row">
                                    <div class="col-sm-4 col-sm-offset-1 col-xs-3">
                                        Giới tính:
                                    </div>
                                    <div class="col-sm-7 col-xs-9">
                                        <c:choose>
                                            <c:when test="${sessionScope.USER.gender == 1}">
                                                <c:out value="Nam"/>
                                            </c:when>
                                            <c:when test="${sessionScope.USER.gender == 2}">
                                                <c:out value="Nữ"/>
                                            </c:when>
                                            <c:otherwise>
                                                <c:out value="Khác"/>
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
                                        ${sessionScope.USER.address}
                                    </div>
                                </div>
                                <!-- Phone -->
                                <div class="row">
                                    <div class="col-sm-4 col-sm-offset-1 col-xs-3">
                                        Số điện thoại:
                                    </div>
                                    <div class="col-sm-7 col-xs-9">
                                        ${sessionScope.USER.phone}
                                    </div>
                                </div>
                                <!-- Email -->
                                <div class="row">
                                    <div class="col-sm-4 col-sm-offset-1 col-xs-3">
                                        Email:
                                    </div>
                                    <div class="col-sm-7 col-xs-9">
                                        ${sessionScope.USER.email}
                                    </div>
                                </div>
                                <!-- People ID Card number -->
                                <div class="row">
                                    <div class="col-sm-4 col-sm-offset-1 col-xs-3">
                                        CMND:
                                    </div>
                                    <div class="col-sm-7 col-xs-9">
                                        ${sessionScope.USER.peopleIndentityCard}
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>


                    <div class="section">
                        <div class="item-center">
                            <button class="btn btn-info" data-toggle="modal" data-target="#edit-profile-modal">Sửa thông tin</button>
                            <button class="btn btn-info" data-toggle="modal" data-target="#edit-password-modal">Sửa mật khẩu</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <jsp:include page="partial/footer.jsp"/>

        <!-- Start edit password modal -->
        <div class="modal fade" id="edit-password-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title" id="myModalLabel">Thay đổi mật khẩu</h4>
                    </div>
                    <form action="${pageContext.request.contextPath}/ChangePassword.action" class="form-horizontal"
                          method="POST">
                        <div class="modal-body">
                            <div class="row">
                                <div class="col-xs-10 col-xs-offset-1">
                                    <div class="form-group label-floating">
                                        <label class="control-label">Mật khẩu cũ</label>
                                        <input type="password" class="form-control" name="pwfOldPassword">
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xs-10 col-xs-offset-1">
                                    <div class="form-group label-floating">
                                        <label class="control-label">Mật khẩu mới</label>
                                        <input type="password" class="form-control" name="pwfNewPassword">
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xs-10 col-xs-offset-1">
                                    <div class="form-group label-floating">
                                        <label class="control-label">Nhập lại mật khẩu mới</label>
                                        <input type="password" class="form-control" name="pwfConfirmPassword">
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
        <!-- end edit password modal -->

        <!-- start edit profile modal -->
        <div class="modal fade" id="edit-profile-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title" id="myModalLabel">Thay đổi thông tin cá nhân</h4>
                    </div>
                    <form action="${pageContext.request.contextPath}/UpdateInformation.action" class="form-horizontal"
                          method="POST">
                        <div class="modal-body">
                            <div class="row">
                                <div class="col-xs-10 col-xs-offset-1">
                                    <div class="form-group label-floating">
                                        <label class="control-label">Tên</label>
                                        <input type="text" class="form-control" value="${sessionScope.USER.name}" name="txtName">
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xs-10 col-xs-offset-1">
                                    <div class="form-group label-floating">
                                        <label class="control-label">Ngày sinh</label>
                                        <input type="text" class="form-control datepicker" value="${sessionScope.USER.birthdayString}" name="txtDateOfBirth" data-date-format="dd/mm/yyyy">
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="radio">
                                    <label>
                                        Giới tính:
                                    </label>
                                    <label>
                                        <c:choose>
                                            <c:when test="${sessionScope.USER.gender == 1}">
                                                <input type="radio" name="cbGender" checked value="Nam">
                                            </c:when>
                                            <c:otherwise>
                                                <input type="radio" name="cbGender" value="Nam">
                                            </c:otherwise>
                                        </c:choose>
                                        Nam
                                    </label>
                                    <label>
                                        <c:choose>
                                            <c:when test="${sessionScope.USER.gender == 2}">
                                                <input type="radio" name="cbGender" value="Nữ" checked>
                                            </c:when>
                                            <c:otherwise>
                                                <input type="radio" name="cbGender" value="Nữ">
                                            </c:otherwise>
                                        </c:choose>
                                        Nữ
                                    </label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xs-10 col-xs-offset-1">
                                    <div class="form-group label-floating">
                                        <label class="control-label">Địa chỉ</label>
                                        <input type="text" class="form-control" value="${sessionScope.USER.address}" name="txtAddress">
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xs-10 col-xs-offset-1">
                                    <div class="form-group label-floating">
                                        <label class="control-label">Số điện thoại</label>
                                        <input type="tel" class="form-control" value="${sessionScope.USER.phone}" name="txtPhoneNumber">
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xs-10 col-xs-offset-1">
                                    <div class="form-group label-floating">
                                        <label class="control-label">Email</label>
                                        <input type="email" class="form-control" value="${sessionScope.USER.email}" name="txtEmail">
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xs-10 col-xs-offset-1">
                                    <div class="form-group label-floating">
                                        <label class="control-label">CMND</label>
                                        <input type="text" class="form-control" value="${sessionScope.USER.peopleIndentityCard}" name="identityCard">
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
        <!-- end edit profile modal -->

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
        <script src="${pageContext.request.contextPath}/assets/js/....." type="text/javascript"></script>
    </body>


</html>