package com;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
//����session��Ҫ��
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
		//��ͨ����ʹ��session��Ҫ�����ӿڣ�HttpServletRequest��HttpSession��ServletActionContext
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		
		String[] type_ss = (String[]) session.getAttribute("type_ss");
		// for (int i = 0; i < type_ss.length; i++) System.out.print(type_ss[i] + " ");
		String[] e = (String[]) session.getAttribute("e");
		// for (int i = 0; i < e.length; i++) System.out.print(e[i] + " ");
		int i = -1;// ��ʼ��

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
		//��ʱ������
		sql=sql+" order by chk_date asc";
		System.out.println(sql);
		rs = stmt.executeQuery(sql);
		DefaultCategoryDataset dcd = new DefaultCategoryDataset();
		while (rs.next()) {
			for (i = 0; i < e.length; i++) {
				if (e[i].contentEquals("e1"))
					dcd.setValue(Double.valueOf(rs.getString("e1")), "ˮ�ּ��ӷ���", rs.getString("chk_date"));
			} // (y,ϵ��,x)
			for (i = 0; i < e.length; i++) {
				if (e[i].contentEquals("e2"))
					dcd.setValue(Double.valueOf(rs.getString("e2")), "����������", rs.getString("chk_date"));
			} // (y,ϵ��,x)
			for (i = 0; i < e.length; i++) {
				if (e[i].contentEquals("e3"))
					dcd.setValue(Double.valueOf(rs.getString("e3")), "�ܼ�������", rs.getString("chk_date"));
			} // (y,ϵ��,x)
			for (i = 0; i < e.length; i++) {
				if (e[i].contentEquals("e4"))
					dcd.setValue(Double.valueOf(rs.getString("e4")), "��ֵ", rs.getString("chk_date"));
			} // (y,ϵ��,x)
			for (i = 0; i < e.length; i++) {
				if (e[i].contentEquals("e5"))
					dcd.setValue(Double.valueOf(rs.getString("e5")), "������ֵ", rs.getString("chk_date"));
			} // (y,ϵ��,x)
		}
		JFreeChart chart = ChartFactory.createLineChart("����Ʒ�ֵı仯�����", "ʱ��", "ָ��ֵ", dcd, PlotOrientation.VERTICAL, true,
				false, false);
		return chart;
	}

	public void setChart(JFreeChart chart) {
		this.chart = chart;
	}

}