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
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService uService = new UserService();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("conf_password");
		User user = new User(login,password);
		boolean signUP = false;
		if(confirmPassword.equals(password) && password.length()>4 &&login.length()>4){
			try{
				for(User x : uService.getAllUsers()){
					if(x.getLogin().equals(user.getLogin())) {
						signUP=true;
						break;
					}
				}
			}catch(Exception e){e.getStackTrace();}
		}
		else{
			request.getRequestDispatcher("/WEB-INF/includes/errors/ErrorNotValidFields.jsp").forward(request, response);
		}
		if(signUP==false && password.length()>4 &&login.length()>4){ 
			uService.createUser(user.getLogin(),user.getPassword());
			session.setAttribute("user", user);
			response.sendRedirect("http://localhost:8080/ua.shop.vitaly/");
		}
		else{
			request.getRequestDispatcher("/WEB-INF/includes/errors/ErrorUserRegister.jsp").include(request, response);
		}
	}
}

