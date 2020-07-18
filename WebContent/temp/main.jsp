<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%String path = request.getContextPath(); %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>后台管理</title>
    <link rel="stylesheet" type="text/css" href="<%=path %>/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="<%=path %>/css/main.css"/>
    <script type="text/javascript" src="<%=path %>/js/libs/modernizr.min.js"></script>
    <style type="text/css">
     iframe{
     	border:none;
     	height:100vh;
     	width:100vw;
     }
    </style>
</head>
<body>
<div class="topbar-wrap white">
    <div class="topbar-inner clearfix">
        <div class="topbar-logo-wrap clearfix">
            <h1 class="topbar-logo none"><a href="index.html" class="navbar-brand">后台管理</a></h1>
            <ul class="navbar-list clearfix">
                <li><a class="on" href="index.html">首页</a></li>
                <li><a href="http://www.mycodes.net/" target="_blank">网站首页</a></li>
            </ul>
        </div>
        <div class="top-info-wrap">
            <ul class="top-info-list clearfix">
                <li><a href="#">管理员</a></li>
                <li><a href="#">修改密码</a></li>
                <li><a href="#">退出</a></li>
            </ul>
        </div>
    </div>
</div>
<div class="container clearfix">
    <div class="sidebar-wrap">
        <div class="sidebar-title">
            <h1>菜单</h1>
        </div>
        <div class="sidebar-content">
            <ul class="sidebar-list">
                <li>
                    <a href="#"><i class="icon-font">&#xe003;</i>常用操作</a>
                    <ul class="sub-menu">
                        <li><a href="index.jsp" target="mainframe"><i class="icon-font">&#xe008;</i>图书管理</a></li>
                        <li><a href="insert.jsp" target="mainframe"><i class="icon-font">&#xe005;</i>客户管理</a></li>
                        <li><a href="design.html" target="mainframe"><i class="icon-font">&#xe006;</i>订单管理</a></li>
                        <li><a href="design.html" target="mainframe"><i class="icon-font">&#xe004;</i>出版社管理</a></li>
                        <li><a href="design.html" target="mainframe"><i class="icon-font">&#xe012;</i>作者管理</a></li>
                        <li><a href="design.html" target="mainframe"><i class="icon-font">&#xe052;</i>友情链接</a></li>
                        <li><a href="design.html" target="mainframe"><i class="icon-font">&#xe033;</i>广告管理</a></li>
                    </ul>
                </li>
                <li>
                    <a href="#"><i class="icon-font">&#xe018;</i>系统管理</a>
                    <ul class="sub-menu">
                        <li><a href="system.html"><i class="icon-font">&#xe017;</i>系统设置</a></li>
                        <li><a href="system.html"><i class="icon-font">&#xe037;</i>清理缓存</a></li>
                        <li><a href="system.html"><i class="icon-font">&#xe046;</i>数据备份</a></li>
                        <li><a href="system.html"><i class="icon-font">&#xe045;</i>数据还原</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
    <!--/sidebar-->
    <div class="main-wrap">
		<iframe src="design.jsp" name="mainframe"></iframe>
        
    </div>
    <!--/main-->
</div>
</body>
</html>