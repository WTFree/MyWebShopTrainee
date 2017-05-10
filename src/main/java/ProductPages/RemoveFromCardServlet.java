package ProductPages;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Product.DAO.JDBCProductDAO;
import models.user.User;

@WebServlet()
public class RemoveFromCardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public RemoveFromCardServlet() {
        super();
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String userID = request.getParameter("userID");
		String prodID = request.getParameter("prodID");
		
		String firstRedirectUrl = request.getContextPath()+"/productcard";
		
		
		User user = (User) request.getSession().getAttribute("user");
		if(Integer.parseInt(userID) != user.getId()){
			response.sendRedirect("ErrorPage.jsp");;
		}else{
			response.sendRedirect(firstRedirectUrl);
			JDBCProductDAO dao = new JDBCProductDAO();
			dao.RemoveFromBasket(Integer.parseInt(userID), Integer.parseInt(prodID));
		}
		
	}

}
