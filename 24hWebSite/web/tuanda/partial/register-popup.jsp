<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!-- Sart Sign up Modal -->
<div class="modal fade" id="signup-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    <i class="material-icons">clear</i>
                </button>
                <h4 class="modal-title">Đăng ký</h4>
            </div>
            <form action="${pageContext.request.contextPath}/Register.action" class="form-horizontal" method="POST">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-xs-10 col-xs-offset-1">
                            <div class="form-group label-floating">
                                <label class="control-label">Tên đăng nhập</label>
                                <input type="text" name="txtUsername" class="form-control" required
                                       value="${param.txtUsername}">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-10 col-xs-offset-1">
                            <div class="form-group label-floating">
                                <label class="control-label">Tên của bạn</label>
                                <input type="text" name="txtName" class="form-control" required 
                                       value="${param.txtName}">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-10 col-xs-offset-1">
                            <div class="form-group label-floating">
                                <label class="control-label">Mật khẩu</label>
                                <input type="password" name="pwfPassword" class="form-control" required
                                       value="${param.pwfPassword}">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-10 col-xs-offset-1">
                            <div class="form-group label-floating">
                                <label class="control-label">Nhập lại mật khẩu</label>
                                <input type="password" name="pwfConfirmPassword" class="form-control" required
                                       value="${param.pwfConfirmPassword}">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-10 col-xs-offset-1">
                            <div class="form-group label-floating">
                                <label class="control-label">Ngày sinh</label>
                                <c:choose>
                                    <c:when test="${empty param.txtDateOfBirth}">
                                        <input type="text" class="form-control datepicker" value="01/01/1997" name="txtDateOfBirth" data-date-format="dd/mm/yyyy">
                                    </c:when>
                                    <c:otherwise>
                                        <input type="text" class="form-control datepicker" value="${param.txtDateOfBirth}" name="txtDateOfBirth" data-date-format="dd/mm/yyyy">
                                    </c:otherwise>
                                </c:choose>
                                
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="radio">
                            <label>
                                Giới tính:
                            </label>
                            <label>
                                <input type="radio" name="cbGender" checked value="Nam">
                                Nam
                            </label>
                            <label>
                                <input type="radio" name="cbGender" value="Nữ">
                                Nữ
                            </label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-10 col-xs-offset-1">
                            <div class="form-group label-floating">
                                <label class="control-label">Địa chỉ</label>
                                <input type="text" name="txtAddress" class="form-control" required
                                       value="${param.txtAddress}">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-10 col-xs-offset-1">
                            <div class="form-group label-floating">
                                <label class="control-label">Số điện thoại</label>
                                <input type="text" name="txtPhoneNumber" class="form-control" required
                                       value="${param.txtPhoneNumber}">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-10 col-xs-offset-1">
                            <div class="form-group label-floating">
                                <label class="control-label">Email</label>
                                <input type="email" name="txtEmail" class="form-control" required
                                       value="${param.txtEmail}">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-10 col-xs-offset-1">
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" name="cbAgree">
                                    Tôi đồng ý với <a href="#">Điều khoản</a>
                                </label>
                            </div>
                        </div>

                    </div>

                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-default btn-simple">Đăng ký</button>
                    <button type="button" class="btn btn-danger btn-simple" data-dismiss="modal">Thoát</button>
                </div>
            </form>
        </div>
    </div>
</div>
<!--  End Sign up Modal -->