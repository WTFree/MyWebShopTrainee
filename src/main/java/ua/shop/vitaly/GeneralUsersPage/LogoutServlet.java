package ua.shop.vitaly.GeneralUsersPage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns={"/logout","/Logout"}, name="LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public LogoutServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		response.sendRedirect("http://localhost:8080/ua.shop.vitaly/");
	}

}
