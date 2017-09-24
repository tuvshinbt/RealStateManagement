package controllers;

import models.Property;
import models.User;
import models.dao.PropertyDAO;
import models.House;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import spark.Request;
import spark.Response;
import spark.Route;
import utils.*;

public class RequestsPropertiesController {
	public static Route initRequestsPage = (Request rq, Response rs) -> {
		Map<String, Object> model = new HashMap<>();
		
		List<Property> properties = new ArrayList<Property>();
		
		properties = PropertyDAO.getPropertyList(0, null, 0, 1, 0, null, 0, null, null, null);
		model.put("properties", properties);
		return ViewUtil.render(rq, model, Path.Template.PROPERTIES_REQUESTS);
	};
	
	public static Route approvePropertyPost = (Request rq, Response rs) -> {
		String id = rq.params("id");
		int approvedBy = RequestUtil.getSessionCurrentUser(rq).getId();
		Property.approveProperty(id, approvedBy);
		rs.redirect(Path.Web.PROPERTIES_REQUESTS);
		return null;
	};
}
