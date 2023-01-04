<%@page import="java.io.PrintWriter"%>
<%@page import="db.MyDb"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>中储粮质检管理信息系统</title>
<link href="css/main-style.css" rel="stylesheet" />
</head>
<body class="dbody mbody">
	<%
		//数据库连接
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		conn = MyDb.getConn();
		stmt = conn.createStatement();//不能连续执行sql,要使用.next()后才可再使用。

		//分页
		int recordcount = 0;//总记录数
		int pagerecord = 10;//每页记录数
		int pages = 0;//总页面数
		int pagei = 1;//当前页码
		ResultSet rs_n = stmt.executeQuery("select count(*) count from data");
		rs_n.next();//为什么要加这个？
		recordcount = rs_n.getInt("count");
		if (recordcount % pagerecord == 0)
			pages = recordcount / pagerecord;
		else
			pages = recordcount / pagerecord + 1;
		if (request.getParameter("pagei") != null) {
			pagei = Integer.valueOf(request.getParameter("pagei"));
			if (pagei <= 0)
				pagei = 1;
			if (pagei > pages)
				pagei = pages;
		}
	%>
	<div>
		<center>
			<div class="titlebox">
				<img src="css/logo.png" style="float: left">
				<ul class="nav">
					<li><a href="login.jsp">注销</a></li>
					<li><a href="Function/insertform.jsp">插入记录</a></li>
				</ul>
			</div>
			<hr>
			<p class="tbhtext" align="center">检索记录</p>
			<form action="datacenter.jsp">
				<table align="center">
					<tr>
						<td>序号</td>
						<td><input type="text" class="in_srch" name="serial_number"></td>
						<td>日期</td>
						<td><input type="text" class="in_srch" name="chk_date"></td>
						<td>货位号</td>
						<td><input type="text" class="in_srch" name="Location_No"></td>
						<td>品种</td>
						<td><input type="text" class="in_srch" name="type"></td>
						<td>实际储存库点</td>
						<td><input type="text" class="in_srch"
							name="Actual_repository_point"></td>
					</tr>
					<tr>
						<td colspan="10" align="right"><input class="btnstyle"
							type="submit" value="搜索"></td>
					</tr>
				</table>
			</form>
			<hr>
			<div class="dtb-box">
				<table class="tbstyle">
					<tr>
						<td colspan="12"><span class="tbhtext">数据列表</span> <br></td>
					</tr>
					<tr>
						<td colspan="12" class="subtitle"><span align="right">
								总共计<%=recordcount%>条记录
						</span></td>
					</tr>
					<tr>
						<th>序号</th>
						<th>检测日期</th>
						<th>实际储存库点</th>
						<th>货位号</th>
						<th>品种</th>
						<th>水分及挥发物（%）</th>
						<th>不溶性杂质（%）</th>
						<th>溶剂残留量（mg/kg）</th>
						<th>酸值（mg/g）</th>
						<th>过氧化值（mmol/kg）</th>
					</tr>
					<%
						//数据调用
						String serial_number = request.getParameter("serial_number");
						String chk_date = request.getParameter("chk_date");
						String actual_repository_point = request.getParameter("Actual_repository_point");
						String location_no = request.getParameter("Location_No");
						String type = request.getParameter("type");
						String sql = "select * from data where true";
						if (serial_number != null && !serial_number.trim().equals("")) {
							sql = sql + " and serial_number='" + serial_number + "'";
						}
						if (chk_date != null && !chk_date.trim().equals(""))
							sql = sql + " and chk_date='" + chk_date + "'";

						if (actual_repository_point != null && !actual_repository_point.trim().equals(""))
							sql = sql + " and Actual_repository_point='" + actual_repository_point + "'";

						if (location_no != null && !location_no.trim().equals(""))
							sql = sql + " and Location_No='" + location_no + "'";

						if (type != null && !type.trim().equals(""))
							sql = sql + " and type='" + type + "'";
						rs = stmt.executeQuery(sql);
						if (rs.next()) {
							rs.absolute(pagerecord * (pagei - 1) + 1);//为什么这里不写rs.next()?此处是定位到对应页面第一行记录
							for (int i = 1; i <= pagerecord; i++) {
					%>
					<tr>
						<td><%=rs.getString("serial_number")%></td>
						<td><%=rs.getString("chk_date")%></td>
						<td><%=rs.getString("Actual_repository_point")%></td>
						<td><%=rs.getString("Location_No")%></td>
						<td><%=rs.getString("type")%></td>
						<td><%=rs.getString("e1")%></td>
						<td><%=rs.getString("e2")%></td>
						<td><%=rs.getString("e3")%></td>
						<td><%=rs.getString("e4")%></td>
						<td><%=rs.getString("e5")%></td>
						<td><a
							href="Function/updateform.jsp?serial_number=<%=rs.getString("serial_number")%>">修改</a></td>
						<td><a
							href="/SinograinSys/DeleteDo?serial_number=<%=rs.getString("serial_number")%>&xm=Commen">删除</a></td>
					</tr>
					<%
						if (!rs.next())
									break;
							}
						}
					%>
				</table>
			</div>
		</center>

	</div>

	<hr>
	<div align="center">
		<%
			session.setAttribute("type_ss", null);
			session.setAttribute("e", null);
		%>
		<form action="/SinograinSys/BuildChart">
			<table>
				<tr>
					<td>种类:</td>

					<%
						//根据数据库中的数据输出选项“种类”
						ResultSet rs_type = stmt.executeQuery("select distinct type from data");
						while (rs_type.next()) {
					%>
					<td><input type="checkbox"
						value="<%=rs_type.getString("type")%>" name="type_ss"><%=rs_type.getString("type")%>
					</td>
					<%
						}
					%>

				</tr>
				<tr>
					<td>指标:</td>
					<td><input type="checkbox" value="e1" name="e">水分及挥发物</td>
					<td><input type="checkbox" value="e2" name="e">不溶性杂质</td>
					<td><input type="checkbox" value="e3" name="e">溶剂残留量</td>
					<td><input type="checkbox" value="e4" name="e">酸值</td>
					<Td><input type="checkbox" value="e5" name="e">过氧化值</td>
				</tr>
			</table>
			<div class="ftr_loc">
				<button class="submitBtn" type="submit" class="btnstyle"
					value="showLineChart" name="chart">显示线性图</button>
				<button class="submitBtn" type="submit" class="btnstyle"
					value="showBarChart" name="chart">显示条形图</button>
			</div>
		</form>
	</div>
	<hr>
	<div style="text-align: center;">
		<span class="pgtext"> </span> <span class="pgtext"><a
			href="datacenter.jsp?pagei=1">首页</a></span> <span class="pgtext"><a
			href="datacenter.jsp?pagei=<%=pagei - 1%>">上一页</a></span> <span
			class="pgtext"><a href="datacenter.jsp?pagei=<%=pagei + 1%>">下一页</a></span>
		<span class="pgtext"><a href="datacenter.jsp?pagei=<%=pages%>">尾页</a></span><br>
	</div>
	<div class="ftr_loc Rfloat">
		<form action="datacenter.jsp?pagei=<%=request.getParameter("pagei")%>">
			<span class="pgtext">跳转至：<input type="text" class="in_text"
				name="pagei" value="0" onclick="if(value==0){value=''}"
				onblur="if(value==''){value=0}"><input type="submit"
				value="跳转"></span> <span>页数：<%=pagei%>/<%=pages%>页
			</span>
		</form>
	</div>
	</div>
</body>
</html>