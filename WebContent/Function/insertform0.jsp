<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>插入记录</title>
<link href="../css/main-style.css" rel="stylesheet" />
</head>
<body class="mbody">
	<div>
		<div class="titlebox">
			<img src="../css/logo.png" style="float: left">
			<ul class="nav">
				<li><a href="../login.jsp">注销</a></li>
				<li><a href="../managerui.jsp">返回上一页</a></li>
			</ul>
		</div>
	</div>
	<hr>
	<form action="/SinograinSys/InsertDo">
		<table border="0" align="center">
			<Tr>
				<th class="tbhtext" colspan="2">插入记录</th>
			</Tr>
			<tr>
				<td>用户名</td>
				<td><input type="text" name="usr" value="请输入用户名！"
					required="required" onfocus="if (value =='请输入用户名！'){value =''}" /></td>
			</tr>
			<tr>
				<td>密码</td>
				<td><input type="text" name="pswd" value="请输入密码！"
					onfocus="if (value =='请输入密码！'){value =''}" /></td>
			</tr>
			<tr>
				<td>角色</td>
				<td><input type="text" name="role" value="1"
					onfocus="if (value =='1'){value =''}"
					onblur="if(value==''){value='1'}" /></td>
			</tr>
			<Tr>
				<td colspan="2" align="center"><input class="btnstyle"
					type="submit" value="提交"></td>
			</Tr>
		</table>
	</form>
</body>
</html>