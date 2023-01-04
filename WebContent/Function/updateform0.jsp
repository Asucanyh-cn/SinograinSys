<%@page import="db.MyDb"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>修改记录</title>
<link href="../css/main-style.css" rel="stylesheet" />
</head>
<body class="mbody">
	<%
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		conn = MyDb.getConn();
		stmt = conn.createStatement();
		String sql="select * from usr where usr='" + request.getParameter("usr") + "'";
		rs = stmt.executeQuery(sql);
		System.out.println(sql);
		rs.next();//为什么要写这个？使用rs.getstring前一定要加
	%>
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
	<form action="/SinograinSys/UpdateDo">
		<table align="center">
			<Tr>
				<th class="tbhtext" colspan="2">修改记录</th>
			</Tr>
			<tr>
				<td>账号</td>
				<td><input type="text" name="usr"
					value=<%=rs.getString("usr")%> readonly></td>
			</tr>
			<tr>
				<td>密码</td>
				<td><input type="text" name="pswd"
					value=<%=rs.getString("pswd")%>></td>
			</tr>
			<tr>
				<td>角色</td>
				<td><input type="text" name="role"
					value=<%=rs.getString("role")%>></td>
			</tr>
			<Tr>
				<td colspan="2" align="center"><input class="btnstyle"
					type="submit" value="提交"></td>
			</Tr>
		</table>
	</form>
</body>
</html>