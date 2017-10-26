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
                            <li class="active"><a href="1">Tin hằng ngày</a></li>
                            </c:when>
                            <c:otherwise>
                            <li><a href="1">Tin hằng ngày</a></li>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${param.categoryId eq 2}">
                            <li class="active"><a href="2">Thể thao</a></li>
                            </c:when>
                            <c:otherwise>
                            <li><a href="2">Thể thao</a></li>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${param.categoryId eq 2}">
                            <li class="active"><a href="3">Thế giới</a></li>
                            </c:when>
                            <c:otherwise>
                            <li><a href="3">Thế giới</a></li>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${param.categoryId eq 2}">
                            <li class="active"><a href="4">Thời trang</a></li>
                            </c:when>
                            <c:otherwise>
                            <li><a href="4">Thời trang</a></li>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${param.categoryId eq 2}">
                            <li class="active"><a href="5">An ninh - xã hội</a></li>
                            </c:when>
                            <c:otherwise>
                            <li><a href="5">An ninh - xã hội</a></li>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${param.categoryId eq 2}">
                            <li class="active"><a href="6">Hi-tech</a></li>
                            </c:when>
                            <c:otherwise>
                            <li><a href="6">Hi-tech</a></li>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${param.categoryId eq 2}">
                            <li class="active"><a href="7">Tài chính - Địa ốc</a></li>
                            </c:when>
                            <c:otherwise>
                            <li><a href="7">Tài chính - Địa ốc</a></li>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${param.categoryId eq 2}">
                            <li class="active"><a href="8">Ẩm thực</a></li>
                            </c:when>
                            <c:otherwise>
                            <li><a href="8">Ẩm thực</a></li>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${param.categoryId eq 2}">
                            <li class="active"><a href="9">Sắc đẹp</a></li>
                            </c:when>
                            <c:otherwise>
                            <li><a href="9">Sắc đẹp</a></li>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${param.categoryId eq 2}">
                            <li class="active"><a href="10">Showbiz</a></li>
                            </c:when>
                            <c:otherwise>
                            <li><a href="10">Showbiz</a></li>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${param.categoryId eq 2}">
                            <li class="active"><a href="11">Giải trí</a></li>
                            </c:when>
                            <c:otherwise>
                            <li><a href="11">Giải trí</a></li>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${param.categoryId eq 2}">
                            <li class="active"><a href="12">Nhịp sống trẻ</a></li>
                            </c:when>
                            <c:otherwise>
                            <li><a href="12">Nhịp sống trẻ</a></li>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${param.categoryId eq 2}">
                            <li class="active"><a href="13">Giáo dục</a></li>
                            </c:when>
                            <c:otherwise>
                            <li><a href="13">Giáo dục</a></li>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${param.categoryId eq 2}">
                            <li class="active"><a href="14">Ô tô</a></li>
                            </c:when>
                            <c:otherwise>
                            <li><a href="14">Ô tô</a></li>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${param.categoryId eq 2}">
                            <li class="active"><a href="15">Xe máy</a></li>
                            </c:when>
                            <c:otherwise>
                            <li><a href="15">Xe máy</a></li>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${param.categoryId eq 2}">
                            <li class="active"><a href="16">Thị trường - Tiêu dùng</a></li>
                            </c:when>
                            <c:otherwise>
                            <li><a href="16">Thị trường - Tiêu dùng</a></li>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${param.categoryId eq 2}">
                            <li class="active"><a href="17">Du lịch</a></li>
                            </c:when>
                            <c:otherwise>
                            <li><a href="17">Du lịch</a></li>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${param.categoryId eq 2}">
                            <li class="active"><a href="18">Sức khỏe</a></li>
                            </c:when>
                            <c:otherwise>
                            <li><a href="18">Sức khỏe</a></li>
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