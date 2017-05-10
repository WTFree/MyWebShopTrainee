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
public class RemoveFromCardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public RemoveFromCardServlet() {
        super();
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String userID = request.getParameter("userID");
		String prodID = request.getParameter("prodID");
		
		User user = (User) request.getSession().getAttribute("user");
		if(Integer.parseInt(userID) != user.getId()){
			response.sendRedirect("ErrorPage.jsp");;
		}else{
			request.getRequestDispatcher("/WEB-INF/includes/header.jsp").include(request, response);
			PrintWriter out = response.getWriter();
			out.print("<a href=\"http://localhost:8080/ua.shop.vitaly/productcard\"><button value=\"back\">>>>Back<<<</button></a>");
			request.getRequestDispatcher("/WEB-INF/includes/footer.jsp").include(request, response);
			out.close();
			JDBCProductDAO dao = new JDBCProductDAO();
			dao.RemoveFromBasket(Integer.parseInt(userID), Integer.parseInt(prodID));
		}
		
	}

}
