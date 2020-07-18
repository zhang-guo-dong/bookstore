<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
%>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>后台管理</title>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/common.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/main.css" />
<style type="text/css">
.errorinfo{
	color:red
}
</style>
<script type="text/javascript" src="<%=path%>/js/libs/modernizr.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("#isbn").blur(function() {
			var isbn = $("#isbn").val();
			var url = "<%=path%>/book/checkisbnjson?isbn="+isbn;
			console.log(url)
			$.get(url, function(json) {
				console.log(json)
				var json = eval('('+json+')');
				if(json.code == 'error'){
					$("#isbninfo").html(json.info)
				}else{
					$("#isbninfo").html("")
				}
			})
		})
		$("#cloneauthor").click(function(){
			//$("#authorspan :text:last").clone().appendTo($("#authorspan"));
			$("#authorspan span.myclass1:last").clone().appendTo($("#authorspan"));
		})
	})
</script>
</head>
<body>

	<div class="crumb-wrap">
		<div class="crumb-list">
			<i class="icon-font"></i><a href="/jscss/admin/design/">首页</a><span
				class="crumb-step">&gt;</span><a class="crumb-name"
				href="/jscss/admin/design/">图书管理</a><span class="crumb-step">&gt;</span><span>新增图书</span>
		</div>
	</div>
	<div class="result-wrap">
		<div class="result-content">
			<form action="<%=path%>/book/addbook" method="post" id="myform" name="myform" >
				<table class="insert-tab" width="100%">
					<tbody>
						<tr>
							<th><i class="require-red">*</i>图书编号：</th>
							<td><input class="common-text required" id="isbn"
								placeholder="必须是10位数字" name="isbn" size="50" value=""
								type="text" required pattern="\d{10}">
								<span id="isbninfo" class="errorinfo"></span></td>		
						</tr>
						<tr>
							<th><i class="require-red">*</i>图书名称：</th>
							<td><input class="common-text required" id="title"
								name="title" size="50" value="" type="text" required="required"></td>
						</tr>
						<tr>
							<th><i class="require-red">*</i>出版日期：</th>
							<td><input class="common-text required" id="pubdate"
								name="pubdate" size="50" value="" type="date" required="required"></td>
						</tr>
						<tr>
							<th width="120"><i class="require-red">*</i>出版社：</th>
							<td><select name="pubid" id="publisherSel" class="required" required="required">
									<option value="-1">---请选择---</option>
									<c:forEach items="${publist}" var="pub">
										<option value="${pub.pubid}">${pub.name}</option>
									</c:forEach>
							</select></td>
						</tr>
						<tr>
							<th width="120"><i class="require-red">*</i>作者：</th>
							<td>
							<span id="authorspan">
							 <span class="myclass1">
							<input type="text" list="author_list" name="authorinfo" />
							 </span>
							</span>
							<button type="button" id="cloneauthor">添加作者</button>
							<datalist id="author_list">
							<c:forEach items="${authorlist}" var="au">
								<option label="${au.name}" value="${au.authorid}"/>
							</c:forEach>
							</datalist>
							</td>
						</tr>
						

						<tr>
							<th>成本：</th>
							<td><input class="common-text" name="cost" size="50"
								value="" type="text"></td>
						</tr>
						<tr>
							<th>售价：</th>
							<td><input class="common-text" name="retail" size="50"
								value="" type="text"></td>
						</tr>
						
						<tr>
							<th width="120">类别：</th>
							<td><select name="category" id="category" class="required">
									<option value="">---请选择---</option>
									<option value="健康">健康</option>
									<option value="家庭生活">家庭生活</option>
									<option value="计算机">计算机</option>
									<option value="儿童">儿童</option>
									<option value="烹饪">烹饪</option>
									<option value="自助">自助</option>
									<option value="商务">商务</option>
									<option value="文学">文学</option>
							</select></td>
						</tr>
						
						<tr>
							<th></th>
							<td><input class="btn btn-primary btn6 mr10" value="提交"
								type="submit"> <input class="btn btn6"
								onclick="history.go(-1)" value="返回" type="button"></td>
						</tr>
						
						
					</tbody>
				</table>
			</form>
		</div>
	</div>


</body>
</html>