package ua.shop.vitaly.ProductPages.ProductCardPages;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.shop.vitaly.models.Product.Product;
import ua.shop.vitaly.models.user.User;
import ua.shop.vitaly.services.Product.ProductService;

@WebServlet()
public class RemoveFromCardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService pService = new ProductService();    
    
    public RemoveFromCardServlet() {
        super();
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String prodID = request.getParameter("prodID");
		
		String firstRedirectUrl = request.getContextPath()+"/productcard";
		
		
		User user = (User) request.getSession().getAttribute("user");
		response.sendRedirect(firstRedirectUrl);
		
		boolean prodUP = false;
 		try {
 			for(Product x : pService.getAllProducts()){
 				
 				if (x.getId()==Integer.parseInt(prodID)){
					prodUP = true;
 					break;
 				}
 				
 			}
 		} catch (Exception e) {
 			response.sendRedirect("ErrorPage.jsp");
 		}
		if(prodUP=true){
 			pService.RemoveFromBasket(user.getId(), Integer.parseInt(prodID));
 		}else{response.sendRedirect("ErrorPage.jsp");}
	}
}
