<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登录界面</title>
<link href="css/main-style.css" rel="stylesheet" />
</head>
<body class="body">
	<span class="titletext">中储粮质检管理信息系统</span>
	<hr>
	<div>
		<div class="lg_box">
			<div>
				<h1>登录页面</h1>
			</div>
			<div>
				<form class="form" action="login.action">
					<div class="item0">
						<input type="text" name="xm" placeholder="Username">
					</div>
					<div class="item0">
						<input type="password" name="mm" placeholder="Password">
					</div>
					<div class="item1">
						<input class="loginbutton btnstyle" type="reset" value="重置"> <input
							class="loginbutton btnstyle" type="submit" value="登录">
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>