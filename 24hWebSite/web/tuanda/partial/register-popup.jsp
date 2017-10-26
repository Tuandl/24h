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
            <form action="${pageContext.request.contextPath}/Register.action" class="form-horizontal">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-xs-10 col-xs-offset-1">
                            <div class="form-group label-floating">
                                <label class="control-label">Tên đăng nhập</label>
                                <input type="text" name="txtUsername" class="form-control" required>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-10 col-xs-offset-1">
                            <div class="form-group label-floating">
                                <label class="control-label">Tên của bạn</label>
                                <input type="text" name="txtName" class="form-control" required>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-10 col-xs-offset-1">
                            <div class="form-group label-floating">
                                <label class="control-label">Mật khẩu</label>
                                <input type="password" name="pwfPassword" class="form-control" required>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-10 col-xs-offset-1">
                            <div class="form-group label-floating">
                                <label class="control-label">Nhập lại mật khẩu</label>
                                <input type="password" name="pwfConfirmPassword" class="form-control" required>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-10 col-xs-offset-1">
                            <div class="form-group label-floating">
                                <label class="control-label">Ngày sinh</label>
                                <input type="text" class="form-control datepicker" value="01/01/1997" name="txtDateOfBirth">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="radio">
                            <label>
                                Giới tính:
                            </label>
                            <label>
                                <input type="radio" name="cbGender" checked value="male">
                                Nam
                            </label>
                            <label>
                                <input type="radio" name="cbGender" value="female">
                                Nữ
                            </label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-10 col-xs-offset-1">
                            <div class="form-group label-floating">
                                <label class="control-label">Địa chỉ</label>
                                <input type="text" name="txtAddress" class="form-control" required>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-10 col-xs-offset-1">
                            <div class="form-group label-floating">
                                <label class="control-label">Số điện thoại</label>
                                <input type="text" name="txtPhoneNumber" class="form-control" required>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-10 col-xs-offset-1">
                            <div class="form-group label-floating">
                                <label class="control-label">Email</label>
                                <input type="email" name="txtEmail" class="form-control" required>
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