package ua.shop.vitaly.services.User;

import java.util.ArrayList;

import ua.shop.vitaly.models.user.User;
import ua.shop.vitaly.models.user.DAO.JDBCUserDAO;

public class UserService implements IUserService {
	
	private JDBCUserDAO userDAO = new JDBCUserDAO();
	
	@Override
	public User getUser(int id) throws Exception {
		return userDAO.getUser(id);
	}

	@Override
	public User getUser(String login, String password) throws Exception {
		return userDAO.getUser(login, password);
	}

	@Override
	public ArrayList<User> getAllUsers() throws Exception {
		return userDAO.getAllUsers();
	}

	@Override
	public int updateContact(User user) throws Exception {
		return userDAO.updateContact(user);
	}

	@Override
	public boolean deleteContact(User user) throws Exception {
		return userDAO.deleteContact(user);
	}

	@Override
	public boolean createUser(String login, String password) {
		return userDAO.createUser(login, password);
	}

}
