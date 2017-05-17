package ua.shop.vitaly.ProductPages.AdminProductPages;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.shop.vitaly.models.Product.Product;
import ua.shop.vitaly.models.Product.DAO.JDBCProductDAO;

@WebServlet()
public class ProductDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   public ProductDeleteServlet() {
        super();
    }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	   JDBCProductDAO dao = new JDBCProductDAO();
	   String nameProd = request.getParameter("nameProd");
       String price = request.getParameter("price");
	   String type = request.getParameter("type");
	   String img = request.getParameter("img");
	   
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
				}
				catch(Exception e){
					e.getStackTrace();
				}
	   }else{
			request.getRequestDispatcher("/WEB-INF/includes/errors/ErrorNotValidFields.jsp").forward(request, response);
	   }
	   if(productUP==true && 
		  nameProd!=null && nameProd.length()>4 && price!=null && price.length()>0 &&
		  type !=null && type.length()>3 && img!=null && img.length()>3){
		   
		   try {
			dao.deleteProduct(dao.getProduct(nameProd, price, type, img));
		   } catch (Exception e) {
			e.printStackTrace();
		   }
		   response.sendRedirect("http://localhost:8080/ua.shop.vitaly/AdminMenu.jsp");
		   
	   }
	   else{
			request.getRequestDispatcher("/WEB-INF/includes/errors/ErrorProductDelete.jsp").include(request, response);
	   }
	   
   }

}
