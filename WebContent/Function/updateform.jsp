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
		rs = stmt.executeQuery(
				"select * from data where serial_number='" + request.getParameter("serial_number") + "'");
		rs.next();//为什么要写这个？使用rs.getstring前一定要加
	%>
	<div>
		<div class="titlebox">
			<img src="../css/logo.png" style="float: left">
			<ul class="nav">
				<li><a href="../login.jsp">注销</a></li>
				<li><a href="../datacenter.jsp">返回上一页</a></li>
			</ul>
		</div>
	</div>
	<hr>
	<form action="/SinograinSys/UpdateDo">
		<table border="0" align="center">
			<Tr>
				<th class="tbhtext" colspan="2">修改记录</th>
			</Tr>
			<tr>
				<td>序号</td>
				<td><input type="text" name="serial_number"
					value=<%=rs.getString("serial_number")%> readonly></td>
			</tr>
			<tr>
				<td>检测日期</td>
				<td><input type="text" name="chk_date"
					value=<%=rs.getString("chk_date")%>></td>
			</tr>
			<tr>
				<td>实际储存库点</td>
				<td><input type="text" name="actual_repository_point"
					value=<%=rs.getString("Actual_repository_point")%>></td>
			</tr>
			<tr>
				<td>货位号</td>
				<td><input type="text" name="location_no"
					value=<%=rs.getString("Location_No")%>></td>
			</tr>
			<tr>
				<td>品种</td>
				<td><input type="text" name="type"
					value=<%=rs.getString("type")%>></td>
			</tr>
			<tr>
				<td>水分及挥发物（%）</td>
				<td><input type="text" name="e1" value=<%=rs.getString("e1")%>></td>
			</tr>
			<tr>
				<td>不溶性杂质（%）</td>
				<td><input type="text" name="e2" value=<%=rs.getString("e2")%>></td>
			</tr>
			<tr>
				<td>溶剂残留量（mg/kg）</td>
				<td><input type="text" name="e3" value=<%=rs.getString("e3")%>></td>
			</tr>
			<tr>
				<td>酸值（mg/g）</td>
				<td><input type="text" name="e4" value=<%=rs.getString("e4")%>></td>
			</tr>
			<tr>
				<td>过氧化值（mmol/kg）</td>
				<td><input type="text" name="e5" value=<%=rs.getString("e5")%>></td>
			</tr>
			<Tr>
				<td colspan="2" align="right"><input class="btnstyle"
					type="submit" value="提交"></td>
			</Tr>
		</table>
	</form>
</body>
</html>