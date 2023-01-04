<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="db.MyDb"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>用户管理</title>
<link href="css/main-style.css" rel="stylesheet" />
</head>
<%
	//数据库连接
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	conn = MyDb.getConn();
	stmt = conn.createStatement();//不能连续执行sql,要使用.next()后才可再使用。
	rs = stmt.executeQuery("select * from usr");
%>
<body class="mbody">
	<div class="titlebox">
		<img src="css/logo.png" style="float: left">
		<ul class="nav">
			<li><a href="login.jsp">注销</a></li>
		</ul>
	</div>
	<hr>
	<form>
		<div class="locater">
			<p class="titletext"></p>
			<div>
				<table border="1" class="tbstyle tb">
					<tr>
						<td>用户</td>
						<td>密码</td>
						<td>角色</td>
						<td colspan="2">操作</td>
					</tr>
					<%
						while (rs.next()) {
					%>
					<tr>
						<td><%=rs.getString("usr")%></td>
						<td><%=rs.getString("pswd")%></td>
						<td><%=rs.getString("role")%></td>
						<td><a
							href="/SinograinSys/DeleteDo?usr=<%=rs.getString("usr")%>&xm=Admin">删除</a></td>
						<td><a
							href="/SinograinSys/UpdateDo?usr=<%=rs.getString("usr")%>&pswd=<%=rs.getString("pswd")%>&role=<%=rs.getString("role")%>">修改</a></td>
					</tr>
					<%
						}
					%>
					<tr>
						<td colspan="3"></td>
						<td colspan="2"><a href="./Function/insertform0.jsp">添加</a></td>
					</tr>
				</table>
			</div>
		</div>
	</form>
</body>
</html>