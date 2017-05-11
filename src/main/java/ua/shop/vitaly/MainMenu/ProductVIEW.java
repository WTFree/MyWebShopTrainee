package ua.shop.vitaly.MainMenu;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.shop.vitaly.models.Product.Product;
import ua.shop.vitaly.models.Product.DAO.JDBCProductDAO;

@WebServlet()
public class ProductVIEW extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ProductVIEW() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		JDBCProductDAO dao = new JDBCProductDAO();	
		ArrayList<Product> itemList = null;
		try {
			 itemList = dao.getAllProducts();
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("itemList", itemList);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
