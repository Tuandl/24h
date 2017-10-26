<%-- 
    Document   : error-notification
    Created on : Oct 26, 2017, 2:21:46 PM
    Author     : TUANDASE62310
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<c:if test="${not empty param.INFO}">
    <div class="section">
        <c:forEach var="notification" items="${param.INFO}">
            <div class="alert alert-info">
                <div class="container-fluid">
                    <div class="alert-icon">
                        <i class="material-icons">info_outline</i>
                    </div>
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true"><i class="material-icons">clear</i></span>
                    </button>
                    <b>Thông báo:</b> ${notification}
                </div>
            </div>
        </c:forEach>
    </div>
</c:if>

<c:if test="${not empty param.SUCCESS}">
    <div class="section">
        <c:forEach var="notification" items="${param.SUCCESS}">
            <div class="alert alert-success">
                <div class="container-fluid">
                    <div class="alert-icon">
                        <i class="material-icons">check</i>
                    </div>
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true"><i class="material-icons">clear</i></span>
                    </button>
                    <b>Thông báo:</b> ${notification}
                </div>
            </div>
        </c:forEach>
    </div>
</c:if>

<c:if test="${not empty param.WARNING}">
    <div class="section">
        <c:forEach var="notification" items="${param.WARNING}">
            <div class="alert alert-warning">
                <div class="container-fluid">
                    <div class="alert-icon">
                        <i class="material-icons">warning</i>
                    </div>
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true"><i class="material-icons">clear</i></span>
                    </button>
                    <b>Cảnh báo:</b> ${notification}
                </div>
            </div>
        </c:forEach>
    </div>
</c:if>

<c:if test="${not empty param.ERROR}">
    <div class="section">
        <c:forEach var="notification" items="${param.ERROR}">
            <div class="alert alert-danger">
                <div class="container-fluid">
                    <div class="alert-icon">
                        <i class="material-icons">error_outline</i>
                    </div>
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true"><i class="material-icons">clear</i></span>
                    </button>
                    <b>Lỗi:</b> ${notification}
                </div>
            </div>
        </c:forEach>
    </div>
</c:if>