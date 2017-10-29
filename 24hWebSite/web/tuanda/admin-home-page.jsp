<%-- 
    Document   : admin-home-page
    Created on : Oct 29, 2017, 4:30:57 PM
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
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/....">
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
                    <%
                        List<UserDTO> editors = (List<UserDTO>) request.getAttribute("EDITOR-LIST");
                        List<UserDTO> journalists = (List<UserDTO>) request.getAttribute("JOURNALIST-LIST");
                        pageContext.setAttribute("editors", editors);
                        pageContext.setAttribute("journalists", journalists);
                    %>

                    <!-- common ìnormation -->
                    <div class="section">
                        <h2>Thông tin chung</h2>
                        <div class="row">
                            <div class="col-md-8">
                                <ul>
                                    <li>Số lượng truy cập:</li>
                                    <li>Số lượng bài báo:</li>
                                    <li>Số lượng cộng tác viên: <c:out value="${editors.size()}"/></li>
                                    <li>Số lượng nhà báo: <c:out value="${journalists.size()}"/></li>
                                </ul>
                            </div>
                            <div class="col-md-4">
                                <a class="btn btn-white btn-sm">Tìm kiếm tài khoản</a>
                                <!-- <button class="btn btn-white btn-sm">Xem tất cả tài khoản</button> -->
                            </div>
                        </div>
                    </div>
                    <!--table information-->

                    <div class="section-small">
                        <div class="row">
                            <!-- start journalist -->
                            <div class="col-md-6">
                                <h3>Nhà báo</h3>
                                <table class="table table-hover table-striped table-responsive">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>Tên</th>
                                            <th>Số thẻ nhà báo</th>
                                            <th>Số bài đã viết</th>
                                            <th>Số bài chưa kiểm duyệt</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${journalists}" var="journalist">
                                            <tr>
                                                <td></td>
                                                <td><c:out value="${journalist.name}"/></td>
                                                <td><c:out value="${journalist.getPressCard()}"/></td>
                                                <td></td>
                                                <td></td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            <!-- end journalist -->
                            <!-- start collaborator -->
                            <div class="col-md-6">
                                <h3>Cộng tác viên</h3>
                                <table class="table table-hover table-responsive table-striped">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>Tên</th>
                                            <th>Số điện thoại</th>
                                            <th>Địa chỉ</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${editors}" var="editor">
                                            <tr>
                                                <td></td>
                                                <td><c:out value="${editor.name}"/></td>
                                                <td><c:out value="${editor.phone}"/></td>
                                                <td><c:out value="${editor.address}"/></td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
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
        <script src="${pageContext.request.contextPath}/assets/js/admin-home-page.js" type="text/javascript"></script>
    </body>


</html>