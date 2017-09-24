package models;

public class Admin {
    private String id;
    private String email;
    private String password;

    public static Admin authenticate(String email, String password) {
        if ( email.equals("admin@admin.com") && password.equals("admin") ) {
            return new Admin();
        }

        return null;
    }
}
