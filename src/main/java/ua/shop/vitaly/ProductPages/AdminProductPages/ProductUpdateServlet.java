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
public class ProductUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ProductUpdateServlet() {
        super();
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String OLDnameProd = request.getParameter("OLDnameProd");
		String OLDprice = request.getParameter("OLDprice");
		String OLDtype = request.getParameter("OLDtype");
		String OLDimg = request.getParameter("OLDimg");
		String NEWnameProd = request.getParameter("NEWnameProd");
		String NEWprice = request.getParameter("NEWprice");
		String NEWtype = request.getParameter("NEWtype");
		String NEWimg = request.getParameter("NEWimg");
		
		JDBCProductDAO dao = new JDBCProductDAO();
    	
    	boolean productUP = false;
		if(OLDnameProd!=null && OLDnameProd.length()>4 && OLDprice!=null && OLDprice.length()>0 &&
		   OLDtype !=null && OLDtype.length()>3 && OLDimg!=null && OLDimg.length()>3 &&
		   NEWnameProd!=null && NEWnameProd.length()>4 && NEWprice!=null && NEWprice.length()>0 && 
		   NEWtype !=null && NEWtype.length()>3 && NEWimg!=null && NEWimg.length()>3){
			try{
				for(Product x : dao.getAllProducts()){
					if(x.getName().equals(OLDnameProd)&&x.getPrice().equals(OLDprice)&&
							x.getType().equals(OLDtype)&&x.getImg().equals(OLDimg)) {
						productUP=true;
						break;
					}
				}
			}catch(Exception e){e.getStackTrace();}
		}else{
			request.getRequestDispatcher("/WEB-INF/includes/errors/ErrorNotValidFields.jsp").forward(request, response);
		}
		if(productUP==true&&
		   OLDnameProd!=null && OLDnameProd.length()>4 && OLDprice!=null && OLDprice.length()>0 &&
		   OLDtype !=null && OLDtype.length()>3 && OLDimg!=null && OLDimg.length()>3 &&
		   NEWnameProd!=null && NEWnameProd.length()>4 && NEWprice!=null && NEWprice.length()>0 && 
		   NEWtype !=null && NEWtype.length()>3 && NEWimg!=null && NEWimg.length()>3){
			try {
				Product NEWproduct = new Product(NEWnameProd, NEWprice, NEWtype, NEWimg);
				dao.updateProduct(dao.getProduct(OLDnameProd, OLDprice, OLDtype, OLDimg), NEWproduct);
				
				response.sendRedirect("http://localhost:8080/ua.shop.vitaly/AdminMenu.jsp");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else{
			request.getRequestDispatcher("/WEB-INF/includes/errors/ErrorProductUpdate.jsp").include(request, response);
		}
		
    }

}
