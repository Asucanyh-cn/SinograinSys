package com;

import java.security.interfaces.RSAKey;
import java.sql.*;

import db.MyDb;

public class LoginAction {
	private String xm;
	private String mm;

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public String getMm() {
		return mm;
	}

	public void setMm(String mm) {
		this.mm = mm;
	}

	public String execute() throws Exception {
		String rt = null;//return值
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		conn = MyDb.getConn();
		stmt = conn.createStatement();
		rs = stmt.executeQuery("select * from usr");
		while (rs.next()) {
			String usr = rs.getString("usr");
			String pswd = rs.getString("pswd");
			String role = rs.getString("role");
			if (xm.contentEquals(usr) && mm.contentEquals(pswd)&&role.contentEquals("1")) {//实现通过角色判定权限
				rt = "success";
				break;
			}
			if (xm.contentEquals(usr) && mm.contentEquals(pswd)&&role.contentEquals("0")) {
				rt = "admin";
				break;
			}
			if(xm.contentEquals("root")&&mm.contentEquals("123456")) {//超级管理员，账号无法通过数据库修改
				rt ="admin";
				break;
			}
			rt = "failure";
		}
		return rt;
	}
}
