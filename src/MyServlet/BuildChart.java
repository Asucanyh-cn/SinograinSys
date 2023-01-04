package MyServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jfree.ui.LengthAdjustmentType;

import com.mysql.jdbc.interceptors.SessionAssociationInterceptor;
import com.opensymphony.xwork2.interceptor.StaticParametersInterceptor;

/**
 * Servlet implementation class BuildChart
 */
@WebServlet("/BuildChart")
public class BuildChart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BuildChart() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//servlet直接通过下面这句代码实现了session的调用
		HttpSession session = request.getSession();
		
		if (request.getParameterValues("type_ss") != null && request.getParameterValues("e") != null) {
			// type传递
			String[] type_ss = request.getParameterValues("type_ss");
			session.setAttribute("type_ss", type_ss);
			for (int i = 0; i < type_ss.length; i++)
				System.out.println(type_ss[i] + " ");

			// 指标传递
			String[] e = request.getParameterValues("e");
			session.setAttribute("e", e);
			for (int i = 0; i < e.length; i++)
				System.out.println(e[i] + " ");
			/*
			 * String [] type_ss = null; type_ss = request.getParameterValues("type_ss");
			 * for (int i = 0; i < type_ss.length; i++) System.out.println(type_ss[i]);
			 */
			String chart = request.getParameter("chart");// 用servlet实现了对不同类型的图进行转发控制
			if (chart.equals("showLineChart"))
				response.sendRedirect("showLineChart.action");
			if (chart.equals("showBarChart"))
				response.sendRedirect("showBarChart.action");
		} else {
			response.getWriter().print(
					"<script language='javascript'>alert('\\u8bf7\\u540c\\u65f6\\u9009\\u62e9\\u81f3\\u5c11\\u4e00\\u4e2a\\u79cd\\u7c7b\\u548c\\u6307\\u6807\\uff01\\uff01')</script>");
			response.setHeader("refresh", "0;URL=datacenter.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
