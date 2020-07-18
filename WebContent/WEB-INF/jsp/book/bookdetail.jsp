<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%String path = request.getContextPath(); %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>『豪情』后台管理</title>
    <link rel="stylesheet" type="text/css" href="<%=path %>/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="<%=path %>/css/main.css"/>
    <script type="text/javascript" src="<%=path %>/js/libs/modernizr.min.js"></script>
</head>
<body>

    <!--/sidebar-->
        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font">&#xe06b;</i><span>欢迎使用『豪情』博客程序，建博的首选工具。</span></div>
        </div>
        
        <div class="result-wrap">
            <div class="result-title">
                <h1>图书详细信息</h1>
            </div>
            <div class="result-content">
                <ul class="sys-info-list">
                    <li>
                        <label class="res-lab">图书编号</label><span class="res-info">${book.isbn }</span>
                    </li>
                    <li>
                        <label class="res-lab">图书名称</label><span class="res-info">${book.title }</span>
                    </li>
                    <li>
                        <label class="res-lab">出版日期</label><span class="res-info">${book.pubdate }</span>
                    </li>
                    <li>
                        <label class="res-lab">出版社</label><span class="res-info">${book.publisher.name }</span>
                    </li>
                    <li>
                        <label class="res-lab">作者</label><span class="res-info">
                        <c:forEach items="${book.authors}" var="au">
                        ${au.name}
                        </c:forEach>
                        </span>
                    </li>
                    <li>
                        <label class="res-lab">成本</label><span class="res-info">${book.cost }</span>
                    </li>
                    <li>
                        <label class="res-lab">售价</label><span class="res-info">${book.retail }</span>
                    </li>
                    <li>
                        <label class="res-lab">总类</label><span class="res-info">${book.category }</span>
                    </li>
                </ul>
            </div>
        </div>
        <div class="result-wrap">
         
</body>
</html>