package ua.shop.vitaly.ProductPages.UserProductPages;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.shop.vitaly.models.Product.Product;
import ua.shop.vitaly.models.Product.DAO.JDBCProductDAO;

@WebServlet()
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SearchServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String name = request.getParameter("name");	
		ArrayList<Product> itemListByName = null;
		JDBCProductDAO dao = new JDBCProductDAO();
		String path = request.getContextPath();
		try {
			itemListByName = dao.getAllProductsByName(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(itemListByName.size()>0){
			request.getRequestDispatcher("/WEB-INF/includes/header.jsp").include(request, response);
			PrintWriter out = response.getWriter();
			out.println("<center><h1>Result of your search :)</h1></center>");
			out.print("<div style=\"text-align:center\" id=\"content\">");
			for(Product x : itemListByName){
				out.print("<div class=\"contentForUsers\">");
						out.print("<div>");
				  			out.print("<a href=\""+request.getContextPath()+"/ProductID?id="+x.getId()+"\">");
				  				out.print("<img alt =\"SOME ITEMS\" class=\"top-image\" src =\""+path+"/"+x.getImg()+"\" style=\"border-radius: 25px 25px 0 0;\" width=\"150\" height=\"140\">");
				  			out.print("</a>");
			  			out.print("</div>");
			  			out.print("<div>");
				  			out.print("<a style=\"display:block; padding-top:15px; padding-bottom:15px; border-radius:0 0 25px 25px\" href=\""+path+"/ProductID?id="+x.getId()+"\"><b>"+x.getName()+"</b></a>");
				  		out.print("</div>");
		  		out.print("</div>");
			}
			out.print("</div>");
			request.getRequestDispatcher("/WEB-INF/includes/footer.jsp").include(request, response);
			out.close();
		}else{
			request.getRequestDispatcher("/WEB-INF/includes/header.jsp").include(request, response);
			PrintWriter out = response.getWriter();
			out.print("<div id=\"SearchServletID\"><center><h1>OOOOOoooops.... Anything have not found</h1></center></div>");
			request.getRequestDispatcher("/WEB-INF/includes/footer.jsp").include(request, response);
			out.close();
		}
	}
}
