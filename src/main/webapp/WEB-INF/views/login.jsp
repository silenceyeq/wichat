<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="<c:url value="/resources/amazeui/dist/css/amazeui.min.css" />" rel="stylesheet">
<style type="text/css">
body {
	text-align: center;
}

.login-panel {
	width: 80%;
	margin: 0 auto;
}
.img_panel{
    border: 1px solid #ccc;
    height: 450px;
    width: 80%;
    position: fixed;
    bottom: 32%;
}
.login_op{
    position: fixed;
    bottom: 18%;
}
</style>
</head>
<body>
	<div class="login-panel">
		<div class="col-md-12 img_panel">
			<div class=""></div>
		</div>
		<div class="col-md-12 login_op">
			<form action="<c:url value="/admin/userLogin"/>" method="post" class="am-form">
				<input type="hidden" name="isRememberPassowrd" value="1"/>
				<div class="login-botton am-g am-g-collapse">
					<div class="am-u-sm-2 am-u-md-2 am-u-md-offset-1">
						<input class="am-input-sm" name="account" value="${account }" type="text" placeholder="email"/>
					</div>
					<div class="am-u-sm-2 am-u-md-2">
						<input class="am-input-sm" name="password" value="${password }" type="password" placeholder="password"/>
					</div>
					<div class="am-u-sm-3 am-u-md-3 am-input-group am-input-group-sm">
						<input class="am-input-sm" name="code" type="text" placeholder="verification code"/>
						<span class="am-input-group-label" style="padding:0px">
							<img src="<c:url value="/tools/varifyCode"/>">
						</span>
					</div>
					<div class="am-u-sm-3 am-u-md-1 am-u-end">
				        <button class="am-btn am-btn-secondary am-radius am-btn-sm" type="submit">Sign In</button>
					</div>
					<div class="am-u-sm-3 am-u-md-1 am-u-end">
				        <img src="<c:url value="/tools/code"/>" />
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
<script src="<c:url value="/resources/js/jquery-3.0.0.min.js" />"></script>
<script src="<c:url value="/resources/amazeui/dist/js/amazeui.min.js" />"></script>
	<jsp:include page="./plugins.jsp"></jsp:include>
<script type="text/javascript">
$(function(){
	if('${error_msg}'){
		layer.msg('${error_msg}',{
			icon: 7,
		  	time: 2000 //2秒关闭（如果不配置，默认是3秒）
		});
	}
});
</script>
</html>