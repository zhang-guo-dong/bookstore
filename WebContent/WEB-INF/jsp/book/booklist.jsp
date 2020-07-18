<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%String path = request.getContextPath();%>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>后台管理</title>
<title>后台管理</title>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/common.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/main.css" />
<style type="text/css">
.blue{
color:blue
}
</style>
<script type="text/javascript" src="<%=path%>/js/libs/modernizr.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#checkall").click(function()
		{
			$(":checkbox[name='isbn']").prop("checked",$(this).prop("checked"));
		}		
		
		)
		$("a.link-update").click(function(){
			var isbn = $(this).parent().parent().find("td:eq(1)").html();
			location = "<%=path%>/book/premodbook?isbn="+isbn;
		})
		$("a.link-del").click(function(){
			var r=confirm("您确认删除吗？")
			if(r){
			var isbn = $(this).parent().parent().find("td:eq(1)").html();
			location = "<%=path%>/book/delbook?isbn="+isbn;
			}
		})
		$("#batchDel").click(function()
		{
			var r=confirm("您确认删除吗？")
			if(r){
			$("#myform").submit();
			}
		})
		
		$("#first").click(function(){
			$("#curPage").val(1);
			$("#queryform").submit();
		})
		
		$("#last").click(function(){
			$("#curPage").val('${totalPages}');
			$("#queryform").submit();
		})
		
		$("#pre").click(function(){
			var curPage=parseInt($("#curPage").val());
			if(curPage==1){
				alert("已是第一页！");
				return;
			}
			curPage--;
			$("#curPage").val(curPage);
			$("#queryform").submit();
		})
		
		$("#next").click(function(){
			var curPage=parseInt($("#curPage").val());
			if(curPage=='${totalPages}'){
				alert("已是最后一页！");
				return;
			}
			curPage++;
			$("#curPage").val(curPage);
			$("#queryform").submit();
		})
		
		$("#rowPerPage").val($("#rowPerPageSel").val());
		$("#rowPerPageSel").change(function(){
			$("#rowPerPage").val($(this).val());
			$("#queryform").submit();
		})
		
		$("#orderbyisbn").click(function(){
			$("#orderBy").val("isbn");
			var order = $("#order").val();
			if(!order || "desc" == order){
				order = "asc";
			}
			else{
				order = "desc";
			}
			$("#order").val(order)
			$("#queryform").submit();
		})
		
	})
</script>

</head>
<body>
	<!--/sidebar-->

	<div class="crumb-wrap">
		<div class="crumb-list">
			<i class="icon-font"></i><a href="index.html">首页</a><span
				class="crumb-step">&gt;</span><span class="crumb-name">图书管理</span>
		</div>
	</div>
	<div class="search-wrap">
		<div class="search-content">
			<form action="<%=path %>/book/querybooks" method="post" id="queryform">
				<table class="search-tab">
					<tr>
						<th width="70">图书编号:</th>
						<td><input class="common-text" name="isbn" value="${con.isbn}" id=""
							type="text"></td>
						<th width="70">书名:</th>
						<td><input class="common-text" name="title" value="${con.title}" id=""
							type="text"></td>
						<th width="70">作者:</th>
						<td><input class="common-text" name="authorname" value="${an}" id=""
							type="text"></td>
						<th width="120">出版社:</th>
						<td><select name="publisher" id="publisherSel">
								<option value="-1">---全部---</option>
								<c:forEach items="${publist}" var="pub">
										<option value="${pub.pubid}" <c:if test="${pub.pubid==con.publisher.pubid}">selected</c:if>>${pub.name}</option>
									</c:forEach>
						</select></td>
						<th width="120">种别:</th>
						<td><select name="category" id="">
								<option value="">---全部---</option>
								<option value="健康"<c:if test="${'健康'==con.category}">selected</c:if>>健康</option>
									<option value="家庭生活"<c:if test="${'家庭生活'==con.category}">selected</c:if>>家庭生活</option>
									<option value="计算机"<c:if test="${'计算机'==con.category}">selected</c:if>>计算机</option>
									<option value="儿童"<c:if test="${'儿童'==con.category}">selected</c:if>>儿童</option>
									<option value="烹饪"<c:if test="${'烹饪'==con.category}">selected</c:if>>烹饪</option>
									<option value="自助"<c:if test="${'自助'==con.category}">selected</c:if>>自助</option>
									<option value="商务"<c:if test="${'商务'==con.category}">selected</c:if>>商务</option>
									<option value="文学"<c:if test="${'文学'==con.category}">selected</c:if>>文学</option>
						</select>
						<input type="hidden" name="curPage" id="curPage" value="${curPage}">
						<input type="hidden" name="rowPerPage" id="rowPerPage" value="${rowPerPage}">
						<input type="hidden" name="orderBy" id="orderBy" value="${orderBy}">
						<input type="hidden" name="order" id="order" value="${order}">
						</td>
						
						<th width="120"></th>
						<td><input class="btn btn-primary btn2" name="sub" value="查询"
							type="submit"></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div class="result-wrap">
		<form action="<%=path%>/book/delbooks" id="myform" method="post">
			<div class="result-title">
				<div class="result-list">
					<a href="<%=path%>/book/forwardaddbook"><i class="icon-font"></i>新增图书</a> <a
						id="batchDel" href="javascript:void(0)"><i class="icon-font"></i>批量删除</a>
				</div>
			</div>
			<div class="result-content">
				<table class="result-tab" width="100%">
					<tr>
						<th class="tc" width="5%"><input class="allChoose" id="checkall" name=""
							type="checkbox"></th>
						<th><a href="#" id="orderbyisbn">图书编号</a></th>
						<th>书名</th>
						<th>出版日期</th>
						<th>出版社</th>
						<th>作者</th>
						<th>成本</th>
						<th>售价</th>
						<th>种别</th>
						<th>操作</th>
					</tr>
					<c:forEach items="${bookList}" var="b">
					<tr>
						<td class="tc"><input name="isbn[]" value="${b.isbn}" type="checkbox"></td>
						<td>${b.isbn}</td>
						<td title=""><a href="<%=path%>/book/queryonebook?isbn=${b.isbn}" title="">${b.title}</a>
						</td>
						<td>${b.pubdate}</td>
						<td>${b.publisher.name}</td>
						<td><c:forEach items="${b.authors}" var="au">
						${au.name} 
						</c:forEach></td>
						<td>${b.cost}</td>
						<td>${b.retail}</td>
						<td>${b.category}</td>
						<td><a class="link-update" href="#">修改</a> 
						<a class="link-del" href="#">删除</a></td>
					</tr>
					</c:forEach>
				</table>
				<p/>
				<div>
				每页<select id="rowPerPageSel">
				<option  value="5"<c:if test="${5==rowPerPage}">selected</c:if>>5</option>
				<option  value="10"<c:if test="${10==rowPerPage}">selected</c:if>>10</option>
				<option  value="20"<c:if test="${20==rowPerPage}">selected</c:if>>20</option>
				</select>条
				</div>
				<div class="list-page">共<span class="blue">${totalRow}</span>条<span class="blue">${curPage}</span>/<span class="blue">${totalPages}</span>页</div>
				<div class="list-page">
				<button class="btn btn-primary btn2" id="first" type="button">首页</button>
				<button class="btn btn-primary btn2" id="pre" type="button">上一页</button>
				<button class="btn btn-primary btn2" id="next" type="button">下一页</button>
				<button class="btn btn-primary btn2" id="last" type="button">尾页</button>
				
			</div>
		</form>
	</div>
	<!--/main-->
</body>
</html>