package ua.shop.vitaly.models.user.DAO;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

import ua.shop.vitaly.models.user.NoSuchUserException;
import ua.shop.vitaly.models.user.User;

/*
 *@author WitalyGaiduchok 
 * Created 25-February-2017
 * */
public class JDBCUserDAO implements IUserDAO{ 
	
	private static final String url ="jdbc:mysql://localhost:3306/onlinewebshop?useSSL=false";
    private static final String GET_USER = "SELECT * FROM USER WHERE login = ? AND password = ?";
    private static final String GET_ALL_USERS = "SELECT * FROM USER";
    private static final String CREATE_USER = "INSERT INTO USER (login, password) values (? , ?)";
	


	@Override
	public User getUser(int id) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public User getUser(String login, String password) throws Exception {
		
		Connection conn = null;
		PreparedStatement pStat = null;
		ResultSet resultSet = null;
		try{
			Driver driver = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
			DriverManager.registerDriver(driver);
		}
		catch(Exception e){System.out.println(e.getMessage());}
		
		try{
			conn = DriverManager.getConnection(url, "root", "FileFreK2324");
			pStat = conn.prepareStatement(GET_USER);
	        pStat.setString(1, login);
	        pStat.setString(2, password);
	        resultSet = pStat.executeQuery();
			
			boolean hasUser = resultSet.next();
			if(!hasUser) throw new NoSuchUserException("User not found!");
			return  new User(resultSet.getInt("id"),
					resultSet.getString("login"),
					resultSet.getString("password"));
		}catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
        	if(resultSet!=null){
				try{
					resultSet.close();
				}catch(SQLException ignored){}
			} 
        	if(pStat !=null){
        		try{
        			pStat.close();
        		}catch(SQLException igonored){}
        	}
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ignored) {}
            }
        }
	}
	
	
	public boolean createUser(String login, String password) {
		
		Connection conn = null;
		PreparedStatement pStat = null;
		
		try{
			Driver driver = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
			DriverManager.registerDriver(driver);
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/onlinewebshop?useSSL=false", "root", "FileFreK2324");
			pStat = (PreparedStatement) conn.prepareStatement(CREATE_USER); 
			pStat.setString(1, login);
			pStat.setString(2, password);
			pStat.executeUpdate();
			return true;
		}
		catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
        	if(pStat !=null){
        		try{
        			pStat.close();
        		}catch(SQLException igonored){}
        	}
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ignored) {}
            }
        }
	}
	
	@Override
	public ArrayList<User> getAllUsers() throws Exception {
		Connection conn = null;
		Statement statement = null;
		ResultSet resultSet = null;
		ArrayList<User> AllUsers = new ArrayList<>();
		try{
			Driver driver = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
			DriverManager.registerDriver(driver);
		}
		catch(Exception e){System.out.println(e.getMessage());}
		
		try{
			conn = DriverManager.getConnection(url, "root", "FileFreK2324");
			statement = (Statement) conn.createStatement();
			resultSet = statement.executeQuery(GET_ALL_USERS);
			while(resultSet.next()){
				User user = new User(resultSet.getInt("id"),resultSet.getString("login"),resultSet.getString("password"));
				AllUsers.add(user);
			}
			return AllUsers;
		}
		catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
        	if(resultSet!=null){
				try{
					resultSet.close();
				}catch(SQLException ignored){}
			}
        	if(statement!=null){
				try{
					statement.close();
				}catch(SQLException ignored){}
			}
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ignored) {}
            }
        }
		
	}
	
	@Override
	public int updateContact(User user) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean deleteContact(User user) throws Exception {
		throw new UnsupportedOperationException();
	}


}
