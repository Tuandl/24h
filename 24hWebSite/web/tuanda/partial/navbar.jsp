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
            <a href="${pageContext.request.contextPath}/tuanda/index.jsp">
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
                        <form action="${pageContext.request.contextPath}/SearchArticle.action">
                            <div class="input-group">
                                <input type="hidden" value="name" name="cbSearchType"/>
                                <input type="text" placeholder="Tìm kiếm..." class="form-control" name="txtSearch"
                                       value="${requestScope.txtSearch}" required/>
                                <span class="input-group-btn">
                                    <button class="form-control"><i class="material-icons">search</i></button>
                                </span>
                            </div>
                        </form>
                    </div>
                </li>

                <c:choose>
                    <c:when test="${not empty sessionScope.USER}">
                        <!-- logined -->
                        <li>
                            <a href="${pageContext.request.contextPath}/tuanda/profile.jsp">
                                <i class="material-icons">account_circle</i> ${sessionScope.USER.name}
                            </a>
                        </li>
                        <c:if test="${sessionScope.ROLE == 'Journalist'}">
                            <li>
                                <a href="${pageContext.request.contextPath}/tuanda/journalist-manage-articles.jsp">
                                    <i class="material-icons">dashboard</i> Quản lý
                                </a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/tuanda/create-article.jsp">
                                    <i class="material-icons">assignment</i> Viết bài
                                </a>
                            </li>
                        </c:if>
                        <c:if test="${sessionScope.ROLE == 'Editor'}">
                            <li>
                                <a href="${pageContext.request.contextPath}/tuanda/censor-page.jsp">
                                    <i class="material-icons">work</i> Kiểm duyệt
                                </a>
                            </li>
                        </c:if>
                        <li>
                            <a href="${pageContext.request.contextPath}/Logout.action">
                                <i class="material-icons">exit_to_app</i> Đăng xuất
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