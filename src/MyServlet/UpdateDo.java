package MyServlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.MyDb;

/**
 * Servlet implementation class UpdateDo
 */
@WebServlet("/UpdateDo")
public class UpdateDo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateDo() {
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
		int i=0;
		try {
			Connection conn = null;
			PreparedStatement pstmt = null;
			conn = MyDb.getConn();
			if (request.getParameter("usr")!=null) {
				pstmt = conn.prepareStatement("update usr set usr=?,pswd=?,role=? where usr=?");
				pstmt.setString(1, request.getParameter("usr"));
				pstmt.setString(2, request.getParameter("pswd"));
				pstmt.setString(3, request.getParameter("role"));
				pstmt.setString(4, request.getParameter("usr"));
				pstmt.executeUpdate();
				i=1;
			} else {
				pstmt = conn.prepareStatement(
						"update data set chk_date=?,Actual_repository_point=?,Location_No=?,type=?,e1=?,e2=?,e3=?,e4=?,e5=? where serial_number=?");
				pstmt.setString(1, request.getParameter("chk_date"));
				pstmt.setString(2, request.getParameter("actual_repository_point"));
				pstmt.setString(3, request.getParameter("location_no"));
				pstmt.setString(4, request.getParameter("type"));
				pstmt.setString(5, request.getParameter("e1"));
				pstmt.setString(6, request.getParameter("e2"));
				pstmt.setString(7, request.getParameter("e3"));
				pstmt.setString(8, request.getParameter("e4"));
				pstmt.setString(9, request.getParameter("e5"));
				pstmt.setString(10, request.getParameter("serial_number"));
				pstmt.executeUpdate();
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(i==1) {
			response.sendRedirect("Function/updateform0.jsp?usr=" + request.getParameter("usr") + "");
		}
		else {
			response.sendRedirect(
					"Function/updateform.jsp?serial_number=" + request.getParameter("serial_number") + "");
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
