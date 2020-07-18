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
    <style type="text/css">
    .errorinfo{
    color:red
    }
    </style>
    <script type="text/javascript" src="<%=path %>/js/libs/modernizr.min.js"></script>
    <script type="text/javascript" src="<%=path %>/js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript">
    $(function()
    {
    	$("#myform").submit(function()
    	{
    	if($("#new1").val()==$("#new2").val())
    		return true;
    	else{
    		$("#new2errorinfo").html("两次输入不一致");
    		return false;
    	}
    	})
    })
    		
    </script>
</head>
<body>

        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="/jscss/admin/design/">首页</a><span class="crumb-step">&gt;</span><a class="crumb-name" href="/jscss/admin/design/">用户管理</a><span class="crumb-step">&gt;</span><span>修改密码</span></div>
        </div>
        <div class="result-wrap">
            <div class="result-content">
                <form action="<%=path%>/user/changepassword" method="post" id="myform" name="myform" enctype="application/x-www-form-urlencoded">
                    <table class="insert-tab" width="100%">
                        <tbody>
                            
                        
                            <tr>
                                <th><i class="require-red">*</i>旧密码：</th>
                                <td>
                                    <input required="required" pattern=".{6,}" placeholder="请填写旧密码" class="common-text required" id="old" name="old" size="50" value="" type="text">
                                	<span id="olderrorinfo" class="errorinfo">${info}</span>
                                </td>
                            </tr>
                            <tr>
                                <th><i class="require-red">*</i>新密码：</th>
                                <td>
                                    <input required="required"  pattern=".{6,}" placeholder="长度必须大于等于6位"  class="common-text required" id="new1" name="new1" size="50" value="" type="text">
                                </td>
                            </tr>
                            <tr>
                                <th><i class="require-red">*</i>确认新密码：</th>
                                <td>
                                    <input required="required" pattern=".{6,}" placeholder="长度必须大于等于6位"  class="common-text required" id="new2" name="new2" size="50" value="" type="text">
                               <span id="new2errorinfo" class="errorinfo"></span>
                                </td>
               
                            </tr>
                            <tr>
                                <th></th>
                                <td>
                                    <input class="btn btn-primary btn6 mr10" value="提交" type="submit">
                                    <input class="btn btn6" onclick="history.go(-1)" value="返回" type="button">
                                </td>
                            </tr>
                        </tbody></table>
                </form>
            </div>
        </div>


</body>
</html>