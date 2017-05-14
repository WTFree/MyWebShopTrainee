package ua.shop.vitaly.services.Product;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.shop.vitaly.models.Product.Product;
import ua.shop.vitaly.models.Product.DAO.IProductDAO;

@Service
public class ProductService implements IProductService {
	
	@Autowired
	private IProductDAO productDAO;
	
	@Transactional
	public Product getProduct(int id) throws Exception {
		return productDAO.getProduct(id);
	}

	@Transactional
	public Product getProduct(String name, String price, String type, String img) throws Exception {
		return productDAO.getProduct(name, price, type, img);
	}

	@Transactional
	public ArrayList<Product> getAllProducts() throws Exception {
		return productDAO.getAllProducts();
	}

	@Transactional
	public ArrayList<Product> getAllProductsByName(String name) throws Exception {
		return productDAO.getAllProductsByName(name);
	}

	@Transactional
	public int updateProduct(Product OLDproduct, Product NEWproduct) throws Exception {
		return productDAO.updateProduct(OLDproduct, NEWproduct);
	}

	@Transactional
	public boolean deleteProduct(Product product) throws Exception {
		return productDAO.deleteProduct(product);
	}

	@Transactional
	public boolean createProduct(String name, String price, String type, String img) {
		return productDAO.createProduct(name, price, type, img);
	}

	@Transactional
	public boolean AddToBasket(int userID, int productID) {
		return productDAO.AddToBasket(userID, productID);
	}

	@Transactional
	public ArrayList<Product> getFromBasket(int id) {
		return productDAO.getFromBasket(id);
	}

	@Transactional
	public boolean RemoveFromBasket(int userID, int productID) {
		return productDAO.RemoveFromBasket(userID, productID);
	}

}
