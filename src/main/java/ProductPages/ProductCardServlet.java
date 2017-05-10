package ProductPages;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Product.Product;
import models.Product.DAO.JDBCProductDAO;
import models.user.User;

@WebServlet()
public class ProductCardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ProductCardServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User user =(User) request.getSession().getAttribute("user");
		int id = user.getId(); 
		JDBCProductDAO dao =new JDBCProductDAO();
		ArrayList<Product> basketList =null;
		
		try {
			 basketList = dao.getFromBasket(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("basketList", basketList);
		request.getRequestDispatcher("/WEB-INF/includes/header.jsp").include(request, response);
		PrintWriter out = response.getWriter();
		out.print("<h1>It's your basket "+user.getLogin()+"</h1>");
		out.print("<div style=\"text-align:center\">");
		for(Product x : basketList){
			out.print("<div class=\"Remove\">"
					+"<p><img alt =\"SOME ITEMS\" class=\"top-image\" src =\"http://localhost:8080/ua.shop.vitaly/"+x.getImg()+"\" style=\"border-radius: 25px 25px 0 0;\" width=\"150\" height=\"140\"</p>"
					+ "<h1>Name of product : "+x.getName() +"</h1>"
					+ "<h1>Price of product: "+ x.getPrice() +"</h1>"
					+ "<h1>Type of product: "+ x.getType()+"</h1>"
					+"<form action=\"RemoveFromCardServlet\" method=\"post\">"+
			  			"<input type=\"hidden\" name=\"userID\" value=\""+ id +"\">"+
			  			"<input type=\"hidden\" name=\"prodID\" value=\""+x.getId()+"\">"+
			  			"<div style=\"margin-top:10px;\"><button style=\"width:100%\">>>Remove<<</button></div>"+
			  		"</form>"+ 
			  		"</div>");
		}
		out.println("</div>");
		request.getRequestDispatcher("/WEB-INF/includes/footer.jsp").include(request, response);
		out.close();
		
	}
	
}
