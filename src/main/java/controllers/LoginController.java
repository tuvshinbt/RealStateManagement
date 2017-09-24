package controllers;

import java.util.HashMap;
import java.util.Map;

import models.Admin;
import models.User;
import spark.Request;
import spark.Response;
import spark.Route;
import utils.Path;
import utils.RequestUtil;
import utils.ViewUtil;

public class LoginController {
	public static Route initLoginPage = (Request rq, Response rs) -> {
        Map<String, Object> context = new HashMap<>();
        return ViewUtil.render(rq, context, Path.Template.LOGIN);
    };
    
    public static Route initRegisterPage = (Request rq, Response rs) -> {
    	Map<String, Object> context = new HashMap<>();
        return ViewUtil.render(rq, context, Path.Template.SIGN_UP);
    };
    
    public static Route handleLoginPost = (Request rq, Response rs) -> {
    	Map<String, Object> model = new HashMap<>();
        String email = rq.queryMap().get("email").value();
        String password = rq.queryMap().get("password").value();

        User user = User.find(email, password); 
        if (user != null ) {
        	model.put("msg", "");
            rq.session().attribute("currentUser", user);
            String rurl = RequestUtil.getRedirectUrl(rq);
            if (rurl != null && !rurl.equals("")) {
            	rq.session().removeAttribute("redirecturl");
            	rs.redirect(rurl);
            } else {
            	rs.redirect(Path.Web.INDEX);
            }
            return null;
        } else        	
        	model.put("msg", "Invalid email or password");
        	return ViewUtil.render(rq, model, Path.Template.LOGIN);
    };
    
    public static Route handleRegisterPost = (Request rq, Response rs) -> {
    	Map<String, Object> model = new HashMap<>();
    	
    	String firstName = rq.queryMap().get("firstname").value();
        String lastName = rq.queryMap().get("lastname").value();
        String email = rq.queryMap().get("email").value();
        String phone = rq.queryMap().get("phone").value();
        int role = Integer.parseInt(rq.queryMap().get("role").value());
        String password = rq.queryMap().get("password").value();
        String confirm_password = rq.queryMap().get("confirm_password").value();
        String address = rq.queryMap().get("address").value();
        
        System.out.println(firstName + ", " + lastName + ", " + email + ", " + password + ", " + confirm_password + ", " + address);
        
        if(firstName.isEmpty() || lastName.isEmpty() 
        		|| email.isEmpty() || phone.isEmpty() || password.isEmpty() 
        		|| confirm_password.isEmpty() || address.isEmpty()){
        	rs.redirect(Path.Web.REGISTER);
        	model.put("msg", "All field required");
        	return ViewUtil.render(rq, model, Path.Template.LOGIN);
        }        
        
        User isExist = User.exitst(email);
        
        if(isExist == null){
        	User.create(firstName, lastName, email, phone, password, address, role);
        	rs.redirect(Path.Web.PROPERTY_LIST);        	
        } else {
        	rs.redirect(Path.Web.REGISTER);
        }
        return null;
    };
    
    public static Route handleLogoutPost = (Request rq, Response rs) -> {
        rq.session().removeAttribute("currentUser");
        rq.session().removeAttribute("isAdmin");
        rs.redirect(Path.Web.INDEX);
        return null;
    };
}
