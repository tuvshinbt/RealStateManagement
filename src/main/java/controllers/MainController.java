package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import constants.OHRT;
import models.Property;
import models.dao.PropertyDAO;
import spark.Request;
import spark.Response;
import spark.Route;
import utils.Path;
import utils.ViewUtil;

public class MainController {
	
	public static Route getLayoutPage = (Request rq, Response rs ) -> {
//        if ( !isInitiated(rq) ) {
//            rs.redirect("/home");
//            return null;
//        }

        Map<String, Object> context = new HashMap<>();

        return ViewUtil.render(rq, context, Path.Template.LAYOUT);
    };
    
    // Hello
	public static Route initHome = (Request rq, Response rs ) -> {
		Map<String, Object> map = new HashMap<String, Object>();
		// hot Properties
		List<Property> hotProperties = new ArrayList<>();
		hotProperties = PropertyDAO.getPropertyList(0, null, 0, OHRT.PROPERTY.STATUS.APPROVED, 0, null, 0, 0, 0, 5);
		if(hotProperties != null){
			hotProperties.addAll(hotProperties);
			hotProperties.addAll(hotProperties);
		}		
		map.put("hotProperties", hotProperties);
        return ViewUtil.render(rq, map, Path.Template.INDEX);
    };
    
    public static boolean isInitiated(Request rq) {
        return rq.session().attribute("userId") != null;
    }
}
