package ua.shop.vitaly.ProductPages.AdminProductPages;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.shop.vitaly.models.Product.Product;
import ua.shop.vitaly.services.Product.ProductService;

@WebServlet()
public class ProductRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService pService = new ProductService();    

	
    public ProductRegisterServlet() {
        super();
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nameProd = request.getParameter("nameProd");
		String price = request.getParameter("price");
		String type = request.getParameter("type");
		String img = request.getParameter("img");
		Product product = new Product(nameProd,price,type,img);
		boolean productUP = false;
		if(nameProd!=null && nameProd.length()>4 && price!=null && price.length()>0 &&
		   type !=null && type.length()>3 && img!=null && img.length()>3){
			try{
				for(Product x : pService.getAllProducts()){
					if(x.getName().equals(nameProd) && x.getPrice().equals(price)&&
					   x.getType().equals(type) && x.getImg().equals(img)) {
						productUP=true;
						break;
					}
				}
			}catch(Exception e){e.getStackTrace();}
		}
		else{
			request.getRequestDispatcher("/WEB-INF/includes/errors/ErrorNotValidFields.jsp").forward(request, response);		
		}
		if(productUP==false && 
		   nameProd!=null && nameProd.length()>4 && price!=null && price.length()>0 &&
		   type !=null && type.length()>3 && img!=null && img.length()>3){ 
			pService.createProduct(product.getName(),
					product.getPrice(),
					product.getType(),
					product.getImg());
			response.sendRedirect("http://localhost:8080/ua.shop.vitaly/AdminMenu.jsp");
		}
		else{
			request.getRequestDispatcher("/WEB-INF/includes/errors/ErrorProductRegister.jsp").include(request, response);
		}
	}	
}
