package models.Product.DAO;

import java.util.ArrayList;

import models.Product.Product;

public interface IProductDAO {
	public Product getProduct(int id) throws Exception;
	public Product getProduct(String name, String price, String type, String img) throws Exception;
	public ArrayList<Product> getAllProducts() throws Exception;
	public int updateProduct(Product OLDproduct, Product NEWproduct) throws Exception;
	public boolean deleteProduct(Product product) throws Exception;
	public boolean createProduct(String name, String price, String type, String img);
	public boolean AddToBasket(int userID, int productID);
	public ArrayList<Product> getFromBasket(int id);
	public boolean RemoveFromBasket(int userID, int productID);
}
