package admin;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AdminLoginProcess2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int value = Integer.parseInt(request.getParameter("otp"));
		HttpSession session = request.getSession();
		int otp = (int) session.getAttribute("otp");

		RequestDispatcher dispatcher = null;
		if (value == otp) {

			request.setAttribute("a_email", request.getParameter("a_email"));
			request.setAttribute("status", "loggedin");
			dispatcher = request.getRequestDispatcher("admin_section.jsp");
			dispatcher.forward(request, response);
		} else {
			request.setAttribute("message", "wrong otp");
			dispatcher = request.getRequestDispatcher("admin_signin2.jsp");
			dispatcher.forward(request, response);
		}

	}

}
