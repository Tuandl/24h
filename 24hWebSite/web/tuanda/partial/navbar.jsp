<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- Navbar will come here -->
<nav class="navbar navbar-white">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navigation-index">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a href="#">
                <div class="logo-container">
                    <div class="logo">
                        <img src="${pageContext.request.contextPath}/assets/img/logo-white-background.png" alt="24h Logo" />
                    </div>
                </div>
            </a>
        </div>

        <div class="collapse navbar-collapse" id="navigation-index">
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <div class="nav navbar-nav navbar-form">
                        <div class="input-group">
                            <input type="text" placeholder="Tìm kiếm..." class="form-control" />
                            <span class="input-group-btn">
                                <button class="form-control"><i class="material-icons">search</i></button>
                            </span>
                        </div>
                    </div>
                </li>

                <c:choose>
                    <c:when test="${not empty sessionScope.USER}">
                        <!-- logined -->
                        <li>
                            <a href="#">
                                <i class="material-icons">account_circle</i> ${sessionScope.USER.name}
                            </a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <!-- Not login -->
                        <li>
                            <a href="#" data-toggle="modal" data-target="#login-modal">
                                <i class="material-icons">person</i> Đăng nhập
                            </a>
                        </li>
                        <li>
                            <a href="#" data-toggle="modal" data-target="#signup-modal">
                                <i class="material-icons">person_add</i> Đăng ký
                            </a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>
</nav>
<!-- end navbar -->