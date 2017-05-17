package ua.shop.vitaly.ProductPages.UserProductPages;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.shop.vitaly.models.Product.Product;
import ua.shop.vitaly.models.Product.DAO.JDBCProductDAO;

@WebServlet
public class ProductIDServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ProductIDServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		
		JDBCProductDAO dao = new JDBCProductDAO();
		Product product = null;
		try {
			product = dao.getProduct(Integer.parseInt(id));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getSession().setAttribute("product", product);
		request.getRequestDispatcher("/WEB-INF/includes/ProductIdContent.jsp").forward(request, response);
		}
	}

