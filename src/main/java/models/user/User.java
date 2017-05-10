package models.user;
/*
 *@author WitalyGaiduchok 
 * Created 25-February-2017
 * */
public class User {
	
    private int id;
    private String login;
    private String password;
    private int admin =0;

	public User() {
    }

    public User(int id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAdmin() {
		return admin;
	}

	public void setAdmin(int admin) {
		this.admin = admin;
	}
    
    @Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", password=" + password + ", is admin=" + admin + "]";
	}
}


