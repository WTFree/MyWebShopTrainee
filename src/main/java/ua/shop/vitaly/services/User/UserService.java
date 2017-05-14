package ua.shop.vitaly.services.User;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.shop.vitaly.models.user.User;
import ua.shop.vitaly.models.user.DAO.IUserDAO;

@Service
public class UserService implements IUserService {
	
	@Autowired
	private IUserDAO userDAO;
	
	@Override
	@Transactional
	public User getUser(int id) throws Exception {
		return userDAO.getUser(id);
	}

	@Override
	@Transactional
	public User getUser(String login, String password) throws Exception {
		return userDAO.getUser(login, password);
	}

	@Override
	@Transactional
	public ArrayList<User> getAllUsers() throws Exception {
		return userDAO.getAllUsers();
	}

	@Override
	@Transactional
	public int updateContact(User user) throws Exception {
		return userDAO.updateContact(user);
	}

	@Override
	@Transactional
	public boolean deleteContact(User user) throws Exception {
		return userDAO.deleteContact(user);
	}

	@Override
	@Transactional
	public boolean createUser(String login, String password) {
		return userDAO.createUser(login, password);
	}

}
