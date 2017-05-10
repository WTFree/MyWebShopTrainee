package ProductPages;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Product.Product;
import models.Product.DAO.JDBCProductDAO;
import models.user.User;

@WebServlet
public class ProductIDServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ProductIDServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		
		String url = request.getParameter("id").toString();
		
		JDBCProductDAO dao = new JDBCProductDAO();
		User user = null ;
		user = (User)request.getSession().getAttribute("user");
		Product product = null;
		try {
			product = dao.getProduct(Integer.parseInt(id));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("/WEB-INF/includes/header.jsp").include(request, response);
		PrintWriter out = response.getWriter();
		if(request.getSession().getAttribute("user")!=null){
			out.println("<div id=\"content\">");
			out.println(
					"<div>"
					+ "<div class=\"ProductByID\">"
					+ "<img style=\"border: 1px solid #d7dadd; border-radius: 20px;\" alt =\"SOME ITEMS\" src =\"http://localhost:8080/ua.shop.vitaly/"+ product.getImg() +"\" width=\"250\" height=\"230\">"
					+ "<div style=\"margin-top:10px;\">"
					+"<form action=\"AddToCardServlet\" method=\"post\">"
					+ "<input type=\"hidden\" name =\"userID\" value=\""+user.getId()+"\">"
					+ "<input type=\"hidden\" name =\"productID\"value=\""+url+"\">"
					+ "<button id=\"ButtonBUY\" type=\"submit\" value=\"click here\">Buy me pls</button>"
					+"</form>"
					+ "</div>" 
					+ "</div>" 
					
					+ "<div style=\"margin-left:280px\">"
					+ "<h1>Name of product : "+product.getName() +"</h1>"
					+ "<h1>Price of product: "+ product.getPrice() +"</h1>"
					+ "<h1>Type of product: "+ product.getType()+"</h1>"
					+ "</div>"
					+ "</div>"		
					
					);
			
			out.println("</div>");
			request.getRequestDispatcher("/WEB-INF/includes/footer.jsp").include(request, response);
			out.close();
		}else{
			out.println("<div id=\"content\">");
			out.println(
					"<div>"
					+ "<div class=\"ProductByID\">"
					+ "<img style=\"border: 1px solid #d7dadd;border-radius:15px;\" alt =\"SOME ITEMS\" src =\"http://localhost:8080/ua.shop.vitaly/"+ product.getImg() +"\" width=\"250\" height=\"230\">"
					+ "<div style=\"margin-top:10px;\"><button id=\"login2\" style=\"width:100%\">If you want to buy me, pls log in :)</button></div>" 
					+ "</div>" 
					
					+ "<div style=\"margin-left:280px\">"
					+ "<h1>Name of product : "+product.getName() +"</h1>"
					+ "<h1>Price of product: "+ product.getPrice() +"</h1>"
					+ "<h1>Type of product: "+ product.getType()+"</h1>"
					+ "</div>"
					+ "</div>"		
					
					);
			out.println("</div>");
			request.getRequestDispatcher("/WEB-INF/includes/footer.jsp").include(request, response);
			out.close();
		}
	}


}
