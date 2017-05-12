package ua.shop.vitaly.ProductPages.ProductCardPages;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.shop.vitaly.models.Product.Product;
import ua.shop.vitaly.models.Product.DAO.JDBCProductDAO;
import ua.shop.vitaly.models.user.User;

@WebServlet()
public class AddToCardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AddToCardServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String productID = request.getParameter("productID");
		User user = (User) request.getSession().getAttribute("user");
		
		String firstRedirectUrl = request.getContextPath()+"/ProductID?id="+productID;
		response.sendRedirect(firstRedirectUrl);

		JDBCProductDAO dao = new JDBCProductDAO();
		
		boolean prodUP = false;
		try {
			for(Product x : dao.getAllProducts()){
				
				if (x.getId()==Integer.parseInt(productID)){
					prodUP = true;
					break;
				}
				
			}
		} 
		catch (Exception e) {
			response.sendRedirect("ErrorPage.jsp");
		}

		if(prodUP=true){
			dao.AddToBasket(user.getId(), Integer.parseInt(productID));
		}else{response.sendRedirect("ErrorPage.jsp");}
	}

}
