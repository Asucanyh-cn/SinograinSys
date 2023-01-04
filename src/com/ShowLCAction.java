package com;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
//调用session的要求
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
//
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import com.opensymphony.xwork2.ActionSupport;

import db.MyDb;

public class ShowLCAction extends ActionSupport {
	private JFreeChart chart;

	public String execute() {
		return SUCCESS;
	}

	public JFreeChart getChart() throws Exception {
		//普通类中使用session需要三个接口：HttpServletRequest、HttpSession、ServletActionContext
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		
		String[] type_ss = (String[]) session.getAttribute("type_ss");
		// for (int i = 0; i < type_ss.length; i++) System.out.print(type_ss[i] + " ");
		String[] e = (String[]) session.getAttribute("e");
		// for (int i = 0; i < e.length; i++) System.out.print(e[i] + " ");
		int i = -1;// 初始化

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = null;
		conn = MyDb.getConn();
		stmt = conn.createStatement();
		// sql = "select * from data where true order by chk_date asc";

		sql = "select * from data where true";
		// System.out.println(type_ss.length);
		for (i = 0; i < type_ss.length; i++) {
			if (i == 0) {
				sql = sql + " and type='" + type_ss[i] + "'";
			} else {
				sql = sql + " or type='" + type_ss[i] + "'";
			}
		}
		//按时间排序
		sql=sql+" order by chk_date asc";
		System.out.println(sql);
		rs = stmt.executeQuery(sql);
		DefaultCategoryDataset dcd = new DefaultCategoryDataset();
		while (rs.next()) {
			for (i = 0; i < e.length; i++) {
				if (e[i].contentEquals("e1"))
					dcd.setValue(Double.valueOf(rs.getString("e1")), "水分及挥发物", rs.getString("chk_date"));
			} // (y,系列,x)
			for (i = 0; i < e.length; i++) {
				if (e[i].contentEquals("e2"))
					dcd.setValue(Double.valueOf(rs.getString("e2")), "不溶性杂质", rs.getString("chk_date"));
			} // (y,系列,x)
			for (i = 0; i < e.length; i++) {
				if (e[i].contentEquals("e3"))
					dcd.setValue(Double.valueOf(rs.getString("e3")), "溶剂残留量", rs.getString("chk_date"));
			} // (y,系列,x)
			for (i = 0; i < e.length; i++) {
				if (e[i].contentEquals("e4"))
					dcd.setValue(Double.valueOf(rs.getString("e4")), "酸值", rs.getString("chk_date"));
			} // (y,系列,x)
			for (i = 0; i < e.length; i++) {
				if (e[i].contentEquals("e5"))
					dcd.setValue(Double.valueOf(rs.getString("e5")), "过氧化值", rs.getString("chk_date"));
			} // (y,系列,x)
		}
		JFreeChart chart = ChartFactory.createLineChart("待查品种的变化情况表", "时间", "指标值", dcd, PlotOrientation.VERTICAL, true,
				false, false);
		return chart;
	}

	public void setChart(JFreeChart chart) {
		this.chart = chart;
	}

}