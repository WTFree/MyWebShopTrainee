package ua.shop.vitaly.services.Product;

import java.util.ArrayList;

import ua.shop.vitaly.models.Product.Product;
import ua.shop.vitaly.models.Product.DAO.JDBCProductDAO;


public class ProductService implements IProductService {
	
	private JDBCProductDAO productDAO = new JDBCProductDAO();
	
	public Product getProduct(int id) throws Exception {
		return productDAO.getProduct(id);
	}

	public Product getProduct(String name, String price, String type, String img) throws Exception {
		return productDAO.getProduct(name, price, type, img);
	}

	public ArrayList<Product> getAllProducts() throws Exception {
		return productDAO.getAllProducts();
	}

	public ArrayList<Product> getAllProductsByName(String name) throws Exception {
		return productDAO.getAllProductsByName(name);
	}

	public int updateProduct(Product OLDproduct, Product NEWproduct) throws Exception {
		return productDAO.updateProduct(OLDproduct, NEWproduct);
	}

	public boolean deleteProduct(Product product) throws Exception {
		return productDAO.deleteProduct(product);
	}

	public boolean createProduct(String name, String price, String type, String img) {
		return productDAO.createProduct(name, price, type, img);
	}

	public boolean AddToBasket(int userID, int productID) {
		return productDAO.AddToBasket(userID, productID);
	}

	public ArrayList<Product> getFromBasket(int id) {
		return productDAO.getFromBasket(id);
	}

	public boolean RemoveFromBasket(int userID, int productID) {
		return productDAO.RemoveFromBasket(userID, productID);
	}

}
