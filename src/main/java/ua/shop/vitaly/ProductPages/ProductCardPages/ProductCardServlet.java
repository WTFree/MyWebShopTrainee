package ua.shop.vitaly.ProductPages.ProductCardPages;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.shop.vitaly.models.Product.Product;
import ua.shop.vitaly.models.user.User;
import ua.shop.vitaly.services.Product.ProductService;

@WebServlet()
public class ProductCardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService pService = new ProductService();
	
    public ProductCardServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User user =(User) request.getSession().getAttribute("user");
		int id = user.getId(); 
		ArrayList<Product> basketList =null;
		
		try {
			 basketList = pService.getFromBasket(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("basketList", basketList);
		request.getRequestDispatcher("/WEB-INF/includes/basket.jsp").forward(request,response);
		
	}
	
}
