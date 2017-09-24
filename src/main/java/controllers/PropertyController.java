package controllers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.MultipartConfigElement;
import javax.servlet.http.Part;

import constants.OHRT;
import models.Agent;
import models.ApartmentAndHouseFactory;
import models.Property;
import models.PropertyFeedback;
import models.PropertyImage;
import models.PropertyStatus;
import models.PurposeFactory;
import models.PurposeType;
import models.User;
import models.dao.PropertyDAO;
import models.dao.PropertyFeedbackDAO;
import models.web.property.PropertyListModel;
import models.web.property.PropertyModel;
import spark.Request;
import spark.Response;
import spark.Route;
import utils.Path;
import utils.RequestUtil;
import utils.ViewUtil;

public class PropertyController {
	public static Route initUploadPage = (Request rq, Response rs) -> {
		Map<String, Object> context = new HashMap<>();
		context.put("agents", Agent.getAgentList());
		return ViewUtil.render(rq, context, Path.Template.PROPERTY_UPLOAD);
	};

	public static Route submitPropertyPost = (Request rq, Response rs) -> {
		Map<String, Object> context = new HashMap<>();
		User user = RequestUtil.getSessionCurrentUser(rq);
		if(user == null){
			context.put("msg", "Please login in order to upload Property!!!");
        	return ViewUtil.render(rq, context, Path.Template.LOGIN);
		}
		rq.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/property_images"));
		Map<String, Object> model = new HashMap<>();
		int id = 0;
		String name = rq.queryParams("name");
		String address = rq.queryParams("address");
		User owner = RequestUtil.getSessionCurrentUser(rq);
		int bedroom = Integer.parseInt(rq.queryParams("bedroom"));
		int bathroom = Integer.parseInt(rq.queryParams("bathroom"));
		int status = 1;
		int livingroom = Integer.parseInt(rq.queryParams("livingroom"));
		int parking = Integer.parseInt(rq.queryParams("parking"));
		int kitchen = Integer.parseInt(rq.queryParams("kitchen"));
		double utilitiesCost = Double.parseDouble(rq.queryParams("utilitiescost"));
		
		double rentMonth = 0;
		double perMonthPrice = 0;
		double deposit = 0;
		double sellPrice = 0;
		int purpose_id = 0;
		PurposeType purpose = null;
		Property baishin = null;
		
		if (rq.queryParams("p_type").equals("apartment")) {
			int floor = Integer.parseInt(rq.queryParams("floor"));
			
			if (rq.queryParams("purpose").equals("rent")) {
				rentMonth = Double.parseDouble(rq.queryParams("totalMonth"));
				perMonthPrice = Double.parseDouble(rq.queryParams("monthlyPrice"));
				deposit = Double.parseDouble(rq.queryParams("deposit"));
				purpose = PurposeFactory.getRentPurpose(rentMonth, perMonthPrice, deposit);
				purpose_id = purpose.create();
				baishin = ApartmentAndHouseFactory.createApartmentRent(id, name, address, owner, bedroom, bathroom,
						status, livingroom, parking, kitchen, utilitiesCost, floor, rentMonth, perMonthPrice, deposit);
			} else {
				sellPrice = Double.parseDouble(rq.queryParams("sellPrice"));
				purpose = PurposeFactory.getSellPurpose(sellPrice);
				purpose_id = purpose.create();
				baishin = ApartmentAndHouseFactory.createApartmentSell(id, name, address, owner, bedroom, bathroom,
						status, livingroom, parking, kitchen, utilitiesCost, floor, sellPrice);
			}
		} else {
			double yard = Double.parseDouble(rq.queryParams("yard"));
			if (rq.queryParams("purpose").equals("rent")) {
				rentMonth = Double.parseDouble(rq.queryParams("totalMonth"));
				perMonthPrice = Double.parseDouble(rq.queryParams("monthlyPrice"));
				deposit = Double.parseDouble(rq.queryParams("deposit"));
				purpose = PurposeFactory.getRentPurpose(rentMonth, perMonthPrice, deposit);
				purpose_id = purpose.create();
				baishin = ApartmentAndHouseFactory.createHouseRent(id, name, address, owner, bedroom, bathroom, status,
						livingroom, parking, kitchen, utilitiesCost, yard, rentMonth, perMonthPrice, deposit);
			} else {
				sellPrice = Double.parseDouble(rq.queryParams("sellPrice"));
				purpose = PurposeFactory.getSellPurpose(sellPrice);
				purpose_id = purpose.create();
				baishin = ApartmentAndHouseFactory.createHouseSell(id, name, address, owner, bedroom, bathroom, status,
						livingroom, parking, kitchen, utilitiesCost, yard, sellPrice);
			}
		}
		
		PropertyStatus baishinStatus = new PropertyStatus();
		baishinStatus.setId(1);
		baishinStatus.setName("Pending");
		
		baishin.setAgentAccount(Agent.find(rq.queryParams("agentaccount")));
		
		PurposeType baishinPurpose = baishin.getPurposeType();
		baishinPurpose.setId(purpose_id);
		
		baishin.setStatus(baishinStatus);
		baishin.setPurposeType(baishinPurpose);
		baishin.setDescription(rq.queryParams("message"));
		
		
		int property_id = baishin.create(baishin, user.getId());
		Collection<Part> parts = rq.raw().getParts();
		BufferedImage img = null;
		File uploadDir = new File(
				"src/main/resources/public/uploads/");
		uploadDir.mkdirs();

		ArrayList<PropertyImage> images = new ArrayList<PropertyImage>();

		for (Part part : parts) {
			if (part.getName().equals("upload_image[]") && !part.getSubmittedFileName().isEmpty()) {
				try {
					img = ImageIO.read(part.getInputStream());					
					String filePath = uploadDir.getPath().concat("/").concat(property_id + "_" + part.getSubmittedFileName());
					ImageIO.write(img, "jpg", new File(filePath));
					images.add(new PropertyImage(property_id + "_" + part.getSubmittedFileName()));
				} catch (Exception e) {
					System.out.println("===< ".concat(e.getMessage()).concat(" >==="));
				}
			}

		}
		
		images.forEach((image)-> { image.create(property_id); });
		rs.redirect("/property/".concat(Integer.toString(property_id)));
		return null;
	};

	public static Route getItemPage = (Request rq, Response rs) -> {
		Map<String, Object> map = new HashMap<String, Object>();

		PropertyModel propertyModel = new PropertyModel();

		// hot Properties
		List<Property> hotProperties = new ArrayList<>();
		hotProperties = PropertyDAO.getPropertyList(0, null, 0, OHRT.PROPERTY.STATUS.APPROVED, 0, null, 0, 0, 0, 5);
		propertyModel.setHotPropertyList(hotProperties);

		// Property
		Property mainProperty = PropertyDAO.findById(Integer.parseInt(rq.params("id")));
		if (mainProperty != null) {
			propertyModel.setProperty(mainProperty);
			List<PropertyFeedback> propertyFeedbackList = PropertyFeedbackDAO
					.findActiveByPropertyId(mainProperty.getId());
			propertyModel.setPropertyFeedbackList(propertyFeedbackList);
		}
		map.put("propertyModel", propertyModel);
		return ViewUtil.render(rq, map, Path.Template.PROPERTY_ITEM);
	};

	/**
	 * HTTP.POST postItem
	 */
	public static Route postItemPageFeedback = (Request rq, Response rs) -> {
		Map<String, Object> map = new HashMap<String, Object>();

		int propertyId = rq.queryMap().get("feedbackPropertyId").integerValue();
		User user = RequestUtil.getSessionCurrentUser(rq);
		if (user != null) {

			PropertyFeedback feedback = new PropertyFeedback();
			feedback.setAccount(user);
			feedback.setComment(rq.queryMap().get("comment").value());
			feedback.setProperty(PropertyDAO.findById(propertyId));
			feedback.setRegisterDate(new Date());
			feedback.setStatus(1);
			PropertyFeedbackDAO.save(feedback);

			PropertyModel propertyModel = new PropertyModel();
			// hot Properties
			List<Property> hotProperties = new ArrayList<>();
			hotProperties = PropertyDAO.getPropertyList(0, null, 0, OHRT.PROPERTY.STATUS.APPROVED, 0, null, 0, 0, 0, 5);
			propertyModel.setHotPropertyList(hotProperties);

			// Property
			Property mainProperty = PropertyDAO.findById(Integer.parseInt(rq.params("id")));
			if (mainProperty != null) {
				propertyModel.setProperty(mainProperty);
				List<PropertyFeedback> propertyFeedbackList = PropertyFeedbackDAO
						.findActiveByPropertyId(mainProperty.getId());
				propertyModel.setPropertyFeedbackList(propertyFeedbackList);
			}
			map.put("propertyModel", propertyModel);
			return ViewUtil.render(rq, map, Path.Template.PROPERTY_ITEM);
		} else {
			rq.session().attribute("redirecturl", "/property/" + propertyId);
			rs.redirect(Path.Web.LOGIN);
			return null;
		}
	};

	/**
	 * HTTP.GET ITEM LIST
	 */
	public static Route getItemListPage = (Request rq, Response rs) -> {
		Map<String, Object> map = new HashMap<String, Object>();
		PropertyListModel propertyListModel = new PropertyListModel();
		propertyListModel.setCurrentPage(1);
		// hot Properties
		List<Property> hotProperties = new ArrayList<>();
		hotProperties = PropertyDAO.getPropertyList(0, null, 0, OHRT.PROPERTY.STATUS.APPROVED, 0, null, 0, 0, 0, 5);
		propertyListModel.setHotPropertyList(hotProperties);

		// main propertyList
		List<Property> mainPropertyList = new ArrayList<>();
		mainPropertyList = PropertyDAO.getPropertyList(0, null, 0, OHRT.PROPERTY.STATUS.APPROVED, 0, null, 0, 0, 0,
				OHRT.PROPERTY.LIST.PAGER_COUNT);

		propertyListModel.setPropertyList(mainPropertyList);

		propertyListModel.setTotalItemCount(PropertyDAO.count(0, null, 0, OHRT.PROPERTY.STATUS.APPROVED, 0, null, 0));
		map.put("propertyListModel", propertyListModel);
		return ViewUtil.render(rq, map, Path.Template.PROPERTY_LIST);
	};

	public static Route postItemListPage = (Request rq, Response rs) -> {

		Map<String, Object> map = new HashMap<String, Object>();
		PropertyListModel propertyListModel = new PropertyListModel();
		propertyListModel.setSearchBy(rq.queryMap().get("searchBy").value());
		propertyListModel.setSearchPurposeTypeId(rq.queryMap().get("searchPurposeTypeId").integerValue());
		propertyListModel.setSearchPropertyTypeId(rq.queryMap().get("searchPropertyTypeId").integerValue());
		if (rq.queryMap().get("order") != null) {
			propertyListModel.setOrder(rq.queryMap().get("order").integerValue());
		} else {
			propertyListModel.setOrder(0);
		}
		if (rq.queryMap().get("currentPage") != null) {
			propertyListModel.setCurrentPage(rq.queryMap().get("currentPage").integerValue());
		} else {
			propertyListModel.setCurrentPage(1);
		}

		// hot Properties
		List<Property> hotProperties = new ArrayList<>();
		hotProperties = PropertyDAO.getPropertyList(0, null, 0, OHRT.PROPERTY.STATUS.APPROVED, 0, null, 0, 0, 0, 5);
		propertyListModel.setHotPropertyList(hotProperties);

		// main propertyList
		String purposeKey = null;
		if (propertyListModel.getSearchPurposeTypeId() == 1) {
			purposeKey = "Rent";
		} else if (propertyListModel.getSearchPurposeTypeId() == 2) {
			purposeKey = "Sell";
		}
		propertyListModel.setTotalItemCount(PropertyDAO.count(0, propertyListModel.getSearchBy(), 0,
				OHRT.PROPERTY.STATUS.APPROVED, 0, purposeKey, propertyListModel.getSearchPropertyTypeId()));

		// total page
		int totalPage = propertyListModel.getTotalItemCount() / OHRT.PROPERTY.LIST.PAGER_COUNT;
		if (propertyListModel.getTotalItemCount() % OHRT.PROPERTY.LIST.PAGER_COUNT > 0)
			totalPage++;
		// current page
		if (totalPage < propertyListModel.getCurrentPage())
			propertyListModel.setCurrentPage(propertyListModel.getCurrentPage() - 1);
		if (propertyListModel.getCurrentPage() == 0)
			propertyListModel.setCurrentPage(propertyListModel.getCurrentPage() + 1);
		// offset
		int offset = 0;
		offset = (propertyListModel.getCurrentPage() - 1) * OHRT.PROPERTY.LIST.PAGER_COUNT;

		List<Property> mainPropertyList = new ArrayList<>();
		mainPropertyList = PropertyDAO.getPropertyList(0, propertyListModel.getSearchBy(), 0,
				OHRT.PROPERTY.STATUS.APPROVED, 0, purposeKey, propertyListModel.getSearchPropertyTypeId(),
				propertyListModel.getOrder(), offset, OHRT.PROPERTY.LIST.PAGER_COUNT);
		propertyListModel.setPropertyList(mainPropertyList);

		map.put("propertyListModel", propertyListModel);
		return ViewUtil.render(rq, map, Path.Template.PROPERTY_LIST);
	};

}
