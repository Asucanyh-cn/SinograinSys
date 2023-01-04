package MyServlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.components.Else;

import db.MyDb;

/**
 * Servlet implementation class DeleteDo
 */
@WebServlet("/DeleteDo")
public class DeleteDo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteDo() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			Connection conn = null;
			Statement stmt = null;
			conn = MyDb.getConn();
			stmt = conn.createStatement();
			if (((String)request.getParameter("xm")).contentEquals("Admin")) {
				stmt.executeUpdate("delete from usr where usr='" + request.getParameter("usr") + "'");
				response.sendRedirect("managerui.jsp?xm=Admin");
			} else
			{
				stmt.executeUpdate("delete from data where serial_number='" + request.getParameter("serial_number") + "'");
				response.sendRedirect("datacenter.jsp?xm=Commen");
			}
		}
		catch (Exception e) {

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
