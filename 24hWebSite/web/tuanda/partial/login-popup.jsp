<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!-- Sart Login Modal -->
<div class="modal fade" id="login-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    <i class="material-icons">clear</i>
                </button>
                <h4 class="modal-title">Đăng nhập</h4>
            </div>
            <div class="modal-body">
                <form action="" class="form-horizontal">
                    <div class="row">
                        <div class="col-xs-10 col-xs-offset-1">
                            <div class="form-group label-floating">
                                <label class="control-label">Tên đăng nhập</label>
                                <input type="text" class="form-control">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-10 col-xs-offset-1">
                            <div class="form-group label-floating">
                                <label class="control-label">Mật khẩu</label>
                                <input type="password" class="form-control">
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default btn-simple">Đăng nhập</button>
                <button type="button" class="btn btn-danger btn-simple" data-dismiss="modal">Thoát</button>
            </div>
        </div>
    </div>
</div>
<!--  End Login Modal -->