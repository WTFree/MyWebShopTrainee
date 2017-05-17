package ua.shop.vitaly.ProductPages.UserProductPages;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.shop.vitaly.models.Product.Product;
import ua.shop.vitaly.services.Product.ProductService;

@WebServlet()
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService pService = new ProductService();
	
    public SearchServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String name = request.getParameter("name");	
		ArrayList<Product> itemList = null;
		try {
			itemList = pService.getAllProductsByName(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getSession().setAttribute("itemList", itemList);
		request.getRequestDispatcher("/WEB-INF/includes/SearchContent.jsp").forward(request, response);

	}
}
