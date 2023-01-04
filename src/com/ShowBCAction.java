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
					dcd.setValue(Double.valueOf(rs.getString("ae1")), "ˮ�ּ��ӷ���", rs.getString("type"));
			} // (y,ϵ��,x)
			for (i = 0; i < e.length; i++) {
				if (e[i].contentEquals("e2"))
					dcd.setValue(Double.valueOf(rs.getString("ae2")), "����������", rs.getString("type"));
			} // (y,ϵ��,x)
			for (i = 0; i < e.length; i++) {
				if (e[i].contentEquals("e3"))
					dcd.setValue(Double.valueOf(rs.getString("ae3")), "�ܼ�������", rs.getString("type"));
			} // (y,ϵ��,x)
			for (i = 0; i < e.length; i++) {
				if (e[i].contentEquals("e4"))
					dcd.setValue(Double.valueOf(rs.getString("ae4")), "��ֵ", rs.getString("type"));
			} // (y,ϵ��,x)
			for (i = 0; i < e.length; i++) {
				if (e[i].contentEquals("e5"))
					dcd.setValue(Double.valueOf(rs.getString("ae5")), "������ֵ", rs.getString("type"));
			} // (y,ϵ��,x)
		}
		JFreeChart chart = ChartFactory.createBarChart("����Ʒ�ֵ�ƽ��ֵ", "Ʒ��", "��ֵ", dcd, PlotOrientation.VERTICAL, true,
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
