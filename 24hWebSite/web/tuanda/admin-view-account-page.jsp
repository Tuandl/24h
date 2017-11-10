<%-- 
    Document   : admin-view-account-page
    Created on : Oct 29, 2017, 5:11:48 PM
    Author     : TUANDASE62310
--%>

<%@page import="tuanvxm.DTOs.UserDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <jsp:include page="partial/common-header.jsp">
            <jsp:param name="title" value="24h - Quản trị viên"/>
        </jsp:include>
        <!--css for current page-->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin-view-account-page.css">
    </head>
    <body class="template-page">
        <jsp:include page="partial/navbar.jsp"/>
        <div class="wrapper">
            <jsp:include page="partial/categories.jsp">
                <jsp:param name="title" value="Quản lý tài khoản"/>
            </jsp:include>
            <div class="main">
                <div class="container">
                    <jsp:include page="partial/notification.jsp">
                        <jsp:param name="ERROR" value="${requestScope.ERROR}"/>
                        <jsp:param name="INFO" value="${requestScope.INFO}"/>
                        <jsp:param name="SUCCESS" value="${requestScope.SUCCESS}"/>
                        <jsp:param name="WARNING" value="${requestScope.WARNING}"/>
                    </jsp:include>

                    <!--search-->
                    <div class="section">
                        <div class="row">
                            <label class="col-md-3">Tìm kiếm:</label>
                            <div class="col-md-9">
                                <form action="${pageContext.request.contextPath}/SearchUserByName.action">
                                    <div class="input-group">
                                        <input type="text" value="${requestScope.txtSearch}" placeholder="Nhập tên người dùng..." class="form-control" name="txtSearch"/>
                                        <span class="input-group-btn">
                                            <button type="submit" class="form-control" style="background: none"><i class="material-icons">search</i></button>
                                        </span>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <%
                        List<UserDTO> users = (List<UserDTO>) request.getAttribute("SEARCHRESULT");
                        pageContext.setAttribute("totalPage", request.getAttribute("MAXPAGE"));
                        pageContext.setAttribute("curPage", request.getAttribute("curPage"));
                        pageContext.setAttribute("users", users);
                    %>
                    <div class="section-small">
                        <c:if test="${not empty users}">
                            <table class="table table-hover table-striped">
                                <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>Username</th>
                                        <th>Tên người dùng</th>
                                        <th>Vai trò</th>
                                        <th>Trạng thái</th>
                                        <th>Thông tin chi tiết</th>
                                    </tr>
                                </thead>

                                <tbody>
                                    <c:forEach items="${users}" var="user">
                                        <tr>
                                            <td></td>
                                            <td>${user.username}</td>
                                            <td>${user.name}</td>
                                            <td>${user.role}</td>
                                            <td>${user.status}</td>
                                            <td>
                                                <a href="${pageContext.request.contextPath}/SearchAccountByUsername.action?txtSearch=${user.username}" class="btn btn-success btn-just-icon btn-round">
                                                    <i class="material-icons">description</i>
                                                </a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                            <div class="row" style="text-align: center;">
                                <jsp:include page="partial/pagination.jsp">
                                    <jsp:param name="curPage" value="${curPage}"/>
                                    <jsp:param name="totalPage" value="${totalPage}"/>
                                    <jsp:param name="baseURL" value="${pageContext.request.contextPath}/SearchUserByName.action?txtSearch=${requestScope.txtSearch}&"/>
                                </jsp:include>
                            </div>
                        </c:if>
                        <c:if test="${empty users}">
                            <h3 class="center-block text-center">Vui lòng nhập tên để tìm kiếm</h3>
                        </c:if>
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
        <script src="${pageContext.request.contextPath}/assets/js/auto-gen-index-table.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/assets/js/admin-view-account-page.js" type="text/javascript"></script>
    </body>


</html>