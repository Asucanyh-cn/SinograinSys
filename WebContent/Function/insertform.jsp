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
				<li><a href="../datacenter.jsp">返回上一页</a></li>
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
				<td>序号</td>
				<td><input type="text" name="serial_number" value="请输入整数！"
					onfocus="if (value =='请输入整数！'){value =''}"
					onblur="if (value =='请输入整数！'){value='0'}" /></td>
			</tr>
			<tr>
				<td>检测日期</td>
				<td><input type="text" name="chk_date" value="1949-10-1"
					onfocus="if (value =='1949-10-1'){value =''}"
					onblur="if (value ==''){value='1949-10-1'}" /></td>
			</tr>
			<tr>
				<td>实际储存库点</td>
				<td><input type="text" name="actual_repository_point" value="0"
					onfocus="if (value =='0'){value =''}"
					onblur="if (value ==''){value='0'}" /></td>
			</tr>
			<tr>
				<td>货位号</td>
				<td><input type="text" name="location_no" value="0"
					onfocus="if (value =='0'){value =''}"
					onblur="if (value ==''){value='0'}" /></td>
			</tr>
			<tr>
				<td>品种</td>
				<td><input type="text" name="type" value="0"
					onfocus="if (value =='0'){value =''}"
					onblur="if (value ==''){value='0'}" /></td>
			</tr>
			<tr>
				<td>水分及挥发物（%）</td>
				<td><input type="text" name="e1" value="0"
					onfocus="if (value =='0'){value =''}"
					onblur="if (value ==''){value='0'}" /></td>
			</tr>
			<tr>
				<td>不溶性杂质（%）</td>
				<td><input type="text" name="e2" value="0"
					onfocus="if (value =='0'){value =''}"
					onblur="if (value ==''){value='0'}" /></td>
			</tr>
			<tr>
				<td>溶剂残留量（mg/kg）</td>
				<td><input type="text" name="e3" value="0"
					onfocus="if (value =='0'){value =''}"
					onblur="if (value ==''){value='0'}" /></td>
			</tr>
			<tr>
				<td>酸值（mg/g）</td>
				<td><input type="text" name="e4" value="0"
					onfocus="if (value =='0'){value =''}"
					onblur="if (value ==''){value='0'}" /></td>
			</tr>
			<tr>
				<td>过氧化值（mmol/kg）</td>
				<td><input type="text" name="e5" value="0"
					onfocus="if (value =='0'){value =''}"
					onblur="if (value ==''){value='0'}" /></td>
			</tr>
			<Tr>
				<td colspan="2" align="right"><input class="btnstyle"
					type="submit" value="提交"></td>
			</Tr>
		</table>
	</form>
</body>
</html>