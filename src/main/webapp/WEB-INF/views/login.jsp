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
</style>
</head>
<body>
	<div class="login-panel">
		<div class="col-md-12" style="margin-top: 380px;">
			<form action="<c:url value="/admin/login"/>" class="am-form">
				<div class="login-botton am-g am-g-collapse">
					<div class="am-u-sm-3 am-u-md-4">
						<input class="am-input-sm" name="account" type="text" placeholder="email"/>
					</div>
					<div class="am-u-sm-3 am-u-md-4">
						<input class="am-input-sm" name="password" type="password" placeholder="password"/>
					</div>
					<div class="am-u-sm-3 am-u-md-4 am-u-end">
						<div class="am-input-group am-input-group-sm">
							<input class="am-input-sm" name="code" type="text" placeholder="verification code"/>
							 <span class="am-input-group-btn">
						        <button class="am-btn am-btn-default" type="submit">Sign In</button>
						     </span>
					     </div>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
<script src="<c:url value="/resources/js/jquery-3.0.0.min.js" />"></script>
<script src="<c:url value="/resources/amazeui/dist/js/amazeui.min.js" />"></script>
<script type="text/javascript">
$(function(){
	
});
</script>
</html>