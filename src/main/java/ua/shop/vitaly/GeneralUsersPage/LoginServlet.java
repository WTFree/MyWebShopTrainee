package ua.shop.vitaly.GeneralUsersPage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.shop.vitaly.models.user.User;
import ua.shop.vitaly.models.user.DAO.JDBCUserDAO;


@WebServlet()
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		boolean signUP = false;
		JDBCUserDAO dao = new JDBCUserDAO();
		User user = null;
		
		if(login.length()<4 || password.length()<4){
			request.getRequestDispatcher("ErrorPage.jsp").include(request, response);
			PrintWriter out = response.getWriter();
			out.println("<br><br><br><br><br><br><br><br><br><br><br><br><br><center><h3>Your login or password is invalid</h3></center>");
			out.close();
		}else{
			try{
				for(User x : dao.getAllUsers()){
					if(x.getLogin().equals(login)&& x.getPassword().equals(password)) {
						signUP=true;
						user = dao.getUser(login, password);
						break;
					}
				}
			}catch(Exception e){e.getStackTrace();}
		}
		if(signUP){
			try {
				session.setAttribute("user", user);
			} catch (Exception e) {e.printStackTrace();}
			
			response.sendRedirect("http://localhost:8080/ua.shop.vitaly/");
		}
		else{
			request.getRequestDispatcher("ErrorPage.jsp").include(request, response);
			PrintWriter out = response.getWriter();
			out.println("<br><br><br><br><br><br><br><br><br><br><br><br><br><center><h3>User has not register now </h3></center>");
			out.close();
		}
	}
}


