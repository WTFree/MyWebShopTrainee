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

		JDBCProductDAO dao = new JDBCProductDAO();
		
		int count = 0; 
		try {
			for(Product x : dao.getAllProducts()){
				
				if (x.getId()==Integer.parseInt(productID)){
					count++;
					break;
				}
				
			}
		} 
		catch (Exception e) {
			response.sendRedirect("ErrorPage.jsp");
		}

		if(count !=0){
			String firstRedirectUrl = request.getContextPath()+"/ProductID?id="+productID;
			response.sendRedirect(firstRedirectUrl);
			dao.AddToBasket(user.getId(), Integer.parseInt(productID));
		}else{response.sendRedirect("ErrorPage.jsp");}
	}

}
