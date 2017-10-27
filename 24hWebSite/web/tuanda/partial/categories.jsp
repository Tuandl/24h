<%-- 
    Document   : categories
    Created on : Oct 26, 2017, 11:44:28 AM
    Author     : TUANDASE62310
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="header header-filter" style="background-image: url('${pageContext.request.contextPath}/assets/img/bg4.jpeg');position: absolute;"></div>
<div class="header">
    <div class="container header-title">
        <div class="row">
            <div class="col-md-6">
                <h1 class="title">${param.title}</h1>
                <h4></h4>
            </div>
        </div>
    </div>
    <nav class="navbar navbar-success category-menu">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#category-navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand">
                    Danh mục
                    <div class="ripple-container"></div>
                </a>
            </div>
            <div class="collapse navbar-collapse" id="category-navbar">
                <ul class="nav navbar-nav">
                    <c:choose>
                        <c:when test="${param.categoryId eq 1}">
                            <li class="active"><a href="${pageContext.request.contextPath}/LoadCategoryArticles.action?categoryID=1">Tin hằng ngày</a></li>
                            </c:when>
                            <c:otherwise>
                            <li><a href="${pageContext.request.contextPath}/LoadCategoryArticles.action?categoryID=1">Tin hằng ngày</a></li>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${param.categoryId eq 2}">
                            <li class="active"><a href="${pageContext.request.contextPath}/LoadCategoryArticles.action?categoryID=2">Thể thao</a></li>
                            </c:when>
                            <c:otherwise>
                            <li><a href="${pageContext.request.contextPath}/LoadCategoryArticles.action?categoryID=2">Thể thao</a></li>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${param.categoryId eq 3}">
                            <li class="active"><a href="${pageContext.request.contextPath}/LoadCategoryArticles.action?categoryID=3">Thế giới</a></li>
                            </c:when>
                            <c:otherwise>
                            <li><a href="${pageContext.request.contextPath}/LoadCategoryArticles.action?categoryID=3">Thế giới</a></li>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${param.categoryId eq 4}">
                            <li class="active"><a href="${pageContext.request.contextPath}/LoadCategoryArticles.action?categoryID=4">Thời trang</a></li>
                            </c:when>
                            <c:otherwise>
                            <li><a href="${pageContext.request.contextPath}/LoadCategoryArticles.action?categoryID=4">Thời trang</a></li>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${param.categoryId eq 5}">
                            <li class="active"><a href="${pageContext.request.contextPath}/LoadCategoryArticles.action?categoryID=5">An ninh - xã hội</a></li>
                            </c:when>
                            <c:otherwise>
                            <li><a href="${pageContext.request.contextPath}/LoadCategoryArticles.action?categoryID=5">An ninh - xã hội</a></li>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${param.categoryId eq 6}">
                            <li class="active"><a href="${pageContext.request.contextPath}/LoadCategoryArticles.action?categoryID=6">Hi-tech</a></li>
                            </c:when>
                            <c:otherwise>
                            <li><a href="${pageContext.request.contextPath}/LoadCategoryArticles.action?categoryID=6">Hi-tech</a></li>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${param.categoryId eq 7}">
                            <li class="active"><a href="${pageContext.request.contextPath}/LoadCategoryArticles.action?categoryID=7">Tài chính - Địa ốc</a></li>
                            </c:when>
                            <c:otherwise>
                            <li><a href="${pageContext.request.contextPath}/LoadCategoryArticles.action?categoryID=7">Tài chính - Địa ốc</a></li>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${param.categoryId eq 8}">
                            <li class="active"><a href="${pageContext.request.contextPath}/LoadCategoryArticles.action?categoryID=8">Ẩm thực</a></li>
                            </c:when>
                            <c:otherwise>
                            <li><a href="${pageContext.request.contextPath}/LoadCategoryArticles.action?categoryID=8">Ẩm thực</a></li>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${param.categoryId eq 9}">
                            <li class="active"><a href="${pageContext.request.contextPath}/LoadCategoryArticles.action?categoryID=9">Sắc đẹp</a></li>
                            </c:when>
                            <c:otherwise>
                            <li><a href="${pageContext.request.contextPath}/LoadCategoryArticles.action?categoryID=9">Sắc đẹp</a></li>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${param.categoryId eq 10}">
                            <li class="active"><a href="${pageContext.request.contextPath}/LoadCategoryArticles.action?categoryID=10">Showbiz</a></li>
                            </c:when>
                            <c:otherwise>
                            <li><a href="${pageContext.request.contextPath}/LoadCategoryArticles.action?categoryID=10">Showbiz</a></li>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${param.categoryId eq 11}">
                            <li class="active"><a href="${pageContext.request.contextPath}/LoadCategoryArticles.action?categoryID=11">Giải trí</a></li>
                            </c:when>
                            <c:otherwise>
                            <li><a href="${pageContext.request.contextPath}/LoadCategoryArticles.action?categoryID=11">Giải trí</a></li>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${param.categoryId eq 12}">
                            <li class="active"><a href="${pageContext.request.contextPath}/LoadCategoryArticles.action?categoryID=12">Nhịp sống trẻ</a></li>
                            </c:when>
                            <c:otherwise>
                            <li><a href="${pageContext.request.contextPath}/LoadCategoryArticles.action?categoryID=12">Nhịp sống trẻ</a></li>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${param.categoryId eq 13}">
                            <li class="active"><a href="${pageContext.request.contextPath}/LoadCategoryArticles.action?categoryID=13">Giáo dục</a></li>
                            </c:when>
                            <c:otherwise>
                            <li><a href="${pageContext.request.contextPath}/LoadCategoryArticles.action?categoryID=13">Giáo dục</a></li>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${param.categoryId eq 14}">
                            <li class="active"><a href="${pageContext.request.contextPath}/LoadCategoryArticles.action?categoryID=14">Ô tô</a></li>
                            </c:when>
                            <c:otherwise>
                            <li><a href="${pageContext.request.contextPath}/LoadCategoryArticles.action?categoryID=14">Ô tô</a></li>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${param.categoryId eq 15}">
                            <li class="active"><a href="${pageContext.request.contextPath}/LoadCategoryArticles.action?categoryID=15">Xe máy</a></li>
                            </c:when>
                            <c:otherwise>
                            <li><a href="${pageContext.request.contextPath}/LoadCategoryArticles.action?categoryID=15">Xe máy</a></li>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${param.categoryId eq 16}">
                            <li class="active"><a href="${pageContext.request.contextPath}/LoadCategoryArticles.action?categoryID=16">Thị trường - Tiêu dùng</a></li>
                            </c:when>
                            <c:otherwise>
                            <li><a href="${pageContext.request.contextPath}/LoadCategoryArticles.action?categoryID=16">Thị trường - Tiêu dùng</a></li>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${param.categoryId eq 17}">
                            <li class="active"><a href="${pageContext.request.contextPath}/LoadCategoryArticles.action?categoryID=17">Du lịch</a></li>
                            </c:when>
                            <c:otherwise>
                            <li><a href="${pageContext.request.contextPath}/LoadCategoryArticles.action?categoryID=17">Du lịch</a></li>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${param.categoryId eq 18}">
                            <li class="active"><a href="${pageContext.request.contextPath}/LoadCategoryArticles.action?categoryID=18">Sức khỏe</a></li>
                            </c:when>
                            <c:otherwise>
                            <li><a href="${pageContext.request.contextPath}/LoadCategoryArticles.action?categoryID=18">Sức khỏe</a></li>
                            </c:otherwise>
                        </c:choose>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                            Mục khác
                            <b class="caret"></b>
                            <div class="ripple-container"></div>
                        </a>
                        <ul class="dropdown-menu dropdown-menu-right">
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</div>