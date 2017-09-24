package models;

import java.util.List;
import org.sql2o.Connection;
import utils.Sql2Object;

public class User {
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String mobile;
	private int role;
	private String address;
	private String password;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public void setRole(int role){
		this.role = role;
	}
	
	public int getRole(){
		return this.role;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	} 

	public static User exitst(String email) {
		try (Connection conn = Sql2Object.open()) {
			List<User> users = conn.createQuery("select * from user where Email=:email")
					.addParameter("email", email).executeAndFetch(User.class);
			if (users.size() > 0)
				return users.get(0);
			else
				return null;
		}
	}
	
	public static boolean create(String firtName, String lastName, String email, String phoneNumber, String passWord, String address, int role){
		
		boolean isCreated = true;
		String queryString = "INSERT INTO user " +
                "       (  FirstName,  LastName,  Email,  Mobile,  Role, Address, Password)" +
                " values( :FirstName, :LastName, :Email, :Mobile, :Role, :Address, :Password)";

        try {
            Connection conn = Sql2Object.open();
            conn.createQuery(queryString)
                    .addParameter("FirstName", firtName)
                    .addParameter("LastName", lastName)
                    .addParameter("Email", email)
                    .addParameter("Mobile", phoneNumber)
                    .addParameter("Role", role)
                    .addParameter("Address", address)
                    .addParameter("Password", passWord)
                    .executeUpdate();
            
            isCreated = true;
        } catch ( Exception e ) {
            System.err.println(e.getClass());
            System.err.println(e.getMessage());
            System.err.println(e.getStackTrace());
        }
        
		return isCreated;
	}
	
	public static User find(String id) {
        String queryString = "SELECT * FROM user WHERE ID = :id";

        try ( Connection con = Sql2Object.open() ) {
            return con.createQuery(queryString)
                    .addParameter("id", id)
                    .executeAndFetch(User.class).get(0);
        }
    }
	
	public static User find(String email, String password) {
        String queryString = "SELECT * FROM user WHERE Email = :email and Password = :password";

        try ( Connection con = Sql2Object.open() ) {
            return con.createQuery(queryString)
                    .addParameter("email", email)
                    .addParameter("password", password)
                    .executeAndFetch(User.class).get(0);
        } catch (Exception e){
        	return null;
        }
    }

	public static User authenticate(String email2, String password) {
		if ( email2.equals("admin@admin.com") && password.equals("admin") ) {
            return new User();
        }
		
		return null;
	}
}
