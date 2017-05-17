package ua.shop.vitaly.models.user;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import ua.shop.vitaly.services.User.IUserService;

public class UserAuth {
	
	private static final String SESSION_TAG = "user";

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private IUserService userService;

    public boolean login(String login, String password) {
        try {
            httpSession.setAttribute(SESSION_TAG, userService.getUser(login, password));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public void logout() {
        httpSession.invalidate();
    }

    public boolean isLogged() {
        return httpSession.getAttribute(SESSION_TAG) != null;
    }

    public User getUser() {
        if (!isLogged())
            return null;
        return (User) httpSession.getAttribute(SESSION_TAG);
    }
}
