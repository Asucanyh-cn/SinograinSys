package com;

import org.apache.struts2.ServletActionContext;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import com.opensymphony.xwork2.ActionSupport;

import db.MyDb;

import java.sql.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ShowBCAction extends ActionSupport {
	private JFreeChart chart;
	public JFreeChart getChart() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String[] type_ss = (String[]) session.getAttribute("type_ss");
		// for (int i = 0; i < type_ss.length; i++) System.out.print(type_ss[i] + " ");
		String[] e = (String[]) session.getAttribute("e");
		// for (int i = 0; i < e.length; i++) System.out.print(e[i] + " ");
		int i=0;
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = null;
		//sql = "Select type,avg(e1) as ae1,avg(e2) as ae2,avg(e3) as ae3,avg(e4) as ae4,avg(e5) as ae5 from data group by type";
		sql = "Select type,avg(e1) as ae1,avg(e2) as ae2,avg(e3) as ae3,avg(e4) as ae4,avg(e5) as ae5 from data where true";
		for (i = 0; i < type_ss.length; i++) {
			if (i == 0) {
				sql = sql + " and type='" + type_ss[i] + "'";
			} else {
				sql = sql + " or type='" + type_ss[i] + "'";
			}
		}
		sql=sql+" group by type";
		
		conn = MyDb.getConn();		
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		DefaultCategoryDataset dcd = new DefaultCategoryDataset();
		while (rs.next()) {
			for (i = 0; i < e.length; i++) {
				if (e[i].contentEquals("e1"))
					dcd.setValue(Double.valueOf(rs.getString("ae1")), "水分及挥发物", rs.getString("type"));
			} // (y,系列,x)
			for (i = 0; i < e.length; i++) {
				if (e[i].contentEquals("e2"))
					dcd.setValue(Double.valueOf(rs.getString("ae2")), "不溶性杂质", rs.getString("type"));
			} // (y,系列,x)
			for (i = 0; i < e.length; i++) {
				if (e[i].contentEquals("e3"))
					dcd.setValue(Double.valueOf(rs.getString("ae3")), "溶剂残留量", rs.getString("type"));
			} // (y,系列,x)
			for (i = 0; i < e.length; i++) {
				if (e[i].contentEquals("e4"))
					dcd.setValue(Double.valueOf(rs.getString("ae4")), "酸值", rs.getString("type"));
			} // (y,系列,x)
			for (i = 0; i < e.length; i++) {
				if (e[i].contentEquals("e5"))
					dcd.setValue(Double.valueOf(rs.getString("ae5")), "过氧化值", rs.getString("type"));
			} // (y,系列,x)
		}
		JFreeChart chart = ChartFactory.createBarChart("待查品种的平均值", "品种", "数值", dcd, PlotOrientation.VERTICAL, true,
				false, false);
		return chart;
	}

	public void setChart(JFreeChart chart) {
		this.chart = chart;
	}

	public String execute() {
		return SUCCESS;
	}
}
