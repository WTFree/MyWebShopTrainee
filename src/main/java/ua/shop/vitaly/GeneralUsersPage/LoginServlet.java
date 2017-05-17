package ua.shop.vitaly.GeneralUsersPage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.shop.vitaly.models.user.User;
import ua.shop.vitaly.services.User.UserService;


@WebServlet()
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService uService = new UserService();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		boolean signUP = false;
		User user = null;
		
		if(login.length()<4 || password.length()<4){
			request.getRequestDispatcher("/WEB-INF/includes/errors/ErrorNotValidFields.jsp").forward(request, response);
		}else{
			try{
				for(User x : uService.getAllUsers()){
					if(x.getLogin().equals(login)&& x.getPassword().equals(password)) {
						signUP=true;
						user = uService.getUser(login, password);
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
			request.getRequestDispatcher("/WEB-INF/includes/errors/ErrorUserLogin.jsp").include(request, response);

		}
	}
}


