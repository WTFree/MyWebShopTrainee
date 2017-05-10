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

@WebServlet()
public class ProductRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ProductRegisterServlet() {
        super();
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nameProd = request.getParameter("nameProd");
		String price = request.getParameter("price");
		String type = request.getParameter("type");
		String img = request.getParameter("img");
		Product product = new Product(nameProd,price,type,img);
		JDBCProductDAO dao = new JDBCProductDAO();
		boolean productUP = false;
		if(nameProd!=null && nameProd.length()>4 && price!=null && price.length()>0 &&
		   type !=null && type.length()>3 && img!=null && img.length()>3){
			try{
				for(Product x : dao.getAllProducts()){
					if(x.getName().equals(nameProd) && x.getPrice().equals(price)&&
					   x.getType().equals(type) && x.getImg().equals(img)) {
						productUP=true;
						break;
					}
				}
			}catch(Exception e){e.getStackTrace();}
		}
		else{
			request.getRequestDispatcher("/ErrorPage.jsp").include(request, response);
			PrintWriter out = response.getWriter();
			out.println("<br><br><br><br><br><br><br><br><br><br><br><br><br><center><h3 text-align='center'>Fields weren't correct</h3></center>");
			out.close();
		}
		if(productUP==false && 
		   nameProd!=null && nameProd.length()>4 && price!=null && price.length()>0 &&
		   type !=null && type.length()>3 && img!=null && img.length()>3){ 
			dao.createProduct(product.getName(),
					product.getPrice(),
					product.getType(),
					product.getImg());
			response.sendRedirect("http://localhost:8080/ua.shop.vitaly/AdminMenu.jsp");
		}
		else{
			
			request.getRequestDispatcher("/ErrorPage.jsp").include(request, response);
			PrintWriter out = response.getWriter();
			try{ 
				out.println("<br><br><br><br><br><br><br><br><br><br><br><br><br><center><h3>"+dao.getProduct(nameProd, price, type, img).toString()+" Product has already register</h3></center>");
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			out.close();
		}
	}	
}
