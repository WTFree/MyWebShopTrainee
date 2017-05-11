package ua.shop.vitaly.models.Product.DAO;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

import ua.shop.vitaly.models.Product.NoSuchProductException;
import ua.shop.vitaly.models.Product.Product;

public class JDBCProductDAO implements IProductDAO{
	

	private static final String url ="jdbc:mysql://localhost:3306/onlinewebshop?useSSL=false";
    private static final String GET_PRODUCT = "SELECT * FROM PRODUCTS WHERE name = ? AND price = ? AND type =? AND img = ?";
    private static final String GET_ALL_PRODUCTS = "SELECT * FROM PRODUCTS";
    private static final String CREATE_PRODUCT = "INSERT INTO PRODUCTS (name, price,type,img) values (? , ?, ?, ?)";
    private static final String DELETE = "DELETE FROM PRODUCTS WHERE id = ?";
    private static final String UPDATE = "UPDATE PRODUCTS SET name = ?, price = ?, type = ?, img = ? WHERE id = ?";
    private static final String SELECT_PRODUCT = "SELECT * FROM PRODUCTS WHERE id = ?";
    private static final String ADD_TO_BASKET = "INSERT INTO BASKET(id_user,id_product) values (?, ?)";
    private static final String REMOVE_FROM_BASKET ="DELETE FROM BASKET WHERE id_user = ? and id =?";
    
    @Override
	public Product getProduct(int id) throws Exception {
    	Connection conn = null;
		try{
			Driver driver = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
			DriverManager.registerDriver(driver);
		}
		catch(Exception e){System.out.println(e.getMessage());}
		
		try{
			conn = DriverManager.getConnection(url, "root", "FileFreK2324");
			PreparedStatement statement = (PreparedStatement) conn.prepareStatement(SELECT_PRODUCT);
            statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			
			boolean hasProduct = result.next();
			if(!hasProduct) throw new NoSuchProductException("Product not found!");
			return new Product(result.getInt("id"),
					result.getString("name"),
					result.getString("price"),
					result.getString("type"),
					result.getString("img"));
		}catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ignored) {}
            }
        }
	}


	@Override
	public Product getProduct(String name, String price, String type, String img) throws Exception {
		Connection conn = null;
		PreparedStatement pStat = null;
		try{
			Driver driver = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
			DriverManager.registerDriver(driver);
		}
		catch(Exception e){System.out.println(e.getMessage());}
		
		try{
			conn = DriverManager.getConnection(url, "root", "FileFreK2324");
			pStat = conn.prepareStatement(GET_PRODUCT);
	        pStat.setString(1, name);
	        pStat.setString(2, price);
	        pStat.setString(3, type);
	        pStat.setString(4, img);
	        ResultSet resultSet = pStat.executeQuery();
			
			boolean hasProduct = resultSet.next();
			if(!hasProduct) throw new NoSuchProductException("Product not found!");
			return new Product(resultSet.getInt("id"),
					resultSet.getString("name"),
					resultSet.getString("price"),
					resultSet.getString("type"),
					resultSet.getString("img"));
		}catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ignored) {}
            }
        }
	}

	@Override
	public ArrayList<Product> getAllProducts() throws Exception {
		Connection conn = null;
		ArrayList<Product> AllProducts = new ArrayList<>();
		try{
			Driver driver = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
			DriverManager.registerDriver(driver);
		}
		catch(Exception e){System.out.println(e.getMessage());}
		
		try{
			conn = DriverManager.getConnection(url, "root", "FileFreK2324");
			Statement statement = (Statement) conn.createStatement();
			ResultSet result = statement.executeQuery(GET_ALL_PRODUCTS);
			while(result.next()){
				Product product = new Product(result.getInt("id"),
						result.getString("name"),
						result.getString("price"),
						result.getString("type"),
						result.getString("img"));
				AllProducts.add(product);
			}
		
			return AllProducts;
		}
		catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ignored) {}
            }
        }		
	}

	@Override
	public int updateProduct(Product OLDproduct, Product NEWproduct) throws Exception {
		 if (OLDproduct == null || NEWproduct == null )
	            throw new IllegalArgumentException("Object is null");
	     else if (OLDproduct.getId() == 0)
	            throw new IllegalArgumentException("Contact doesn't have id");
		 
		 Connection conn = null;
	        try{
				Driver driver = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
				DriverManager.registerDriver(driver);
			}
			catch(Exception e){System.out.println(e.getMessage());}
	        try {
				conn = DriverManager.getConnection(url, "root", "FileFreK2324");
	            PreparedStatement statement = conn.prepareStatement(UPDATE);
	            statement.setString(1, NEWproduct.getName());
	            statement.setString(2, NEWproduct.getPrice());
	            statement.setString(3, NEWproduct.getType());
	            statement.setString(4, NEWproduct.getImg());
	            statement.setInt(5, OLDproduct.getId());
	            return statement.executeUpdate();
	        } finally {
	            if (conn != null) {
	                try {
	                    conn.close();
	                } catch (SQLException ignored) {}
	            }
	        }	}

	@Override
	public boolean deleteProduct(Product product) throws Exception {
		if (product == null)
            throw new IllegalArgumentException("Object is null");
        else if (product.getId() == 0)
            throw new IllegalArgumentException("Product doesn't have id or id is invalid");

        Connection conn = null;
        try{
			Driver driver = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
			DriverManager.registerDriver(driver);
		}
		catch(Exception e){System.out.println(e.getMessage());}
        try {
			conn = DriverManager.getConnection(url, "root", "FileFreK2324");
            PreparedStatement statement = conn.prepareStatement(DELETE);
            statement.setInt(1, product.getId());

            return statement.executeUpdate() > 0;
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ignored) {}
            }
        }
	}

	@Override
	public boolean createProduct(String name, String price, String type, String img) {
		Connection conn = null;
		PreparedStatement pStat = null;
		
		try{
			Driver driver = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
			DriverManager.registerDriver(driver);
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/onlinewebshop?useSSL=false", "root", "FileFreK2324");
			pStat = (PreparedStatement) conn.prepareStatement(CREATE_PRODUCT); 
			pStat.setString(1, name);
			pStat.setString(2, price);
			pStat.setString(3, type);
			pStat.setString(4, img);
			pStat.executeUpdate();
			return true;
		}
		catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ignored) {}
            }
        }
	}

	@Override
	public boolean AddToBasket(int userID, int productID) {
		Connection conn = null;
		PreparedStatement pStat = null;
		
		try{
			Driver driver = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
			DriverManager.registerDriver(driver);
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/onlinewebshop?useSSL=false", "root", "FileFreK2324");
			pStat = (PreparedStatement) conn.prepareStatement(ADD_TO_BASKET); 
			pStat.setInt(1, userID);
			pStat.setInt(2, productID);
			pStat.executeUpdate();
			return true;
		}
		catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ignored) {}
            }
        }
}

	@Override
	public ArrayList<Product> getFromBasket(int id) {
		Connection conn = null;
		
		ArrayList<Product> AllProducts = new ArrayList<>();
		try{
			Driver driver = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
			DriverManager.registerDriver(driver);
		}
		catch(Exception e){System.out.println(e.getMessage());}
		
		try{
			conn = DriverManager.getConnection(url, "root", "FileFreK2324");
			Statement statement = (Statement) conn.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM basket INNER JOIN products ON products.id = basket.id_product WHERE basket.id_user ="+id);
			while(result.next()){
				Product product = new Product(result.getInt("id"),
						result.getString("name"),
						result.getString("price"),
						result.getString("type"),
						result.getString("img"));
				AllProducts.add(product);
			}
		
			return AllProducts;
		}
		catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ignored) {}
            }
        }				
	}

	@Override
	public boolean RemoveFromBasket(int userID, int productID) {
		Connection conn = null;
        try{
			Driver driver = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
			DriverManager.registerDriver(driver);
		}
		catch(Exception e){System.out.println(e.getMessage());}
        try {
			conn = DriverManager.getConnection(url, "root", "FileFreK2324");
            PreparedStatement statement = conn.prepareStatement(REMOVE_FROM_BASKET);
            statement.setInt(1, userID);
            statement.setInt(2, productID);

            return statement.executeUpdate() > 0;
        } catch(Exception e){
        	e.getStackTrace();
        	return false;}
        finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ignored) {}
            }
        }
	}

}
