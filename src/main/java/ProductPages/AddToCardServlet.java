package ProductPages;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Product.DAO.JDBCProductDAO;
import models.user.User;

@WebServlet()
public class AddToCardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AddToCardServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String userID = request.getParameter("userID");
		String productID = request.getParameter("productID");
		User user = (User) request.getSession().getAttribute("user");
		if(Integer.parseInt(userID) != user.getId()){
			response.sendRedirect("ErrorPage.jsp");;
		}else{
			
			String firstRedirectUrl = request.getContextPath()+"ProductID?id="+productID;
			response.sendRedirect(firstRedirectUrl);

			JDBCProductDAO dao = new JDBCProductDAO();
			dao.AddToBasket(Integer.parseInt(userID), Integer.parseInt(productID));
			
		}
	}

}
