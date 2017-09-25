package servlet.property;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import models.Agent;
import models.ApartmentAndHouseFactory;
import models.Property;
import models.PropertyImage;
import models.PropertyStatus;
import models.PurposeFactory;
import models.PurposeType;
import models.User;
import utils.RequestUtil;

/**
 * Servlet implementation class PropertyUploadServlet
 */
@WebServlet("/property/property_upload")
@MultipartConfig
public class PropertyUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PropertyUploadServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().setAttribute("agents", Agent.getAgentList());
		response.sendRedirect("property_upload.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = RequestUtil.getSessionCurrentUser(request);
		if (user == null) {
			request.setAttribute("msg", "Please login in order to upload Property!!!");
			request.getSession().setAttribute("redirecturl",
					getServletContext().getAttribute("ContextPath") + "/property/property_upload");
			ServletContext context = getServletContext();
			RequestDispatcher rq = context.getRequestDispatcher("/login.jsp");
			rq.forward(request, response);
		} else {
			int id = 0;
			String name = request.getParameter("name");
			String address = request.getParameter("address");
			int bedroom = Integer.parseInt(request.getParameter("bedroom"));
			int bathroom = Integer.parseInt(request.getParameter("bathroom"));
			int status = 1;
			int livingroom = Integer.parseInt(request.getParameter("livingroom"));
			int parking = Integer.parseInt(request.getParameter("parking"));
			int kitchen = Integer.parseInt(request.getParameter("kitchen"));
			double utilitiesCost = Double.parseDouble(request.getParameter("utilitiescost"));

			double rentMonth = 0;
			double perMonthPrice = 0;
			double deposit = 0;
			double sellPrice = 0;
			int purpose_id = 0;
			PurposeType purpose = null;
			Property property = null;

			if (request.getParameter("p_type").equals("apartment")) {
				/*
				 * Apartment
				 */
				int floor = Integer.parseInt(request.getParameter("floor"));
				if (request.getParameter("purpose").equals("rent")) {
					// Rent
					rentMonth = Double.parseDouble(request.getParameter("totalMonth"));
					perMonthPrice = Double.parseDouble(request.getParameter("monthlyPrice"));
					deposit = Double.parseDouble(request.getParameter("deposit"));
					purpose = PurposeFactory.getRentPurpose(rentMonth, perMonthPrice, deposit);
					purpose_id = purpose.create();
					property = ApartmentAndHouseFactory.createApartmentRent(id, name, address, user, bedroom, bathroom,
							status, livingroom, parking, kitchen, utilitiesCost, floor, rentMonth, perMonthPrice,
							deposit);
				} else {
					// Sell
					sellPrice = Double.parseDouble(request.getParameter("sellPrice"));
					purpose = PurposeFactory.getSellPurpose(sellPrice);
					purpose_id = purpose.create();
					property = ApartmentAndHouseFactory.createApartmentSell(id, name, address, user, bedroom, bathroom,
							status, livingroom, parking, kitchen, utilitiesCost, floor, sellPrice);
				}
			} else {
				/*
				 * House
				 */
				double yard = Double.parseDouble(request.getParameter("yard"));
				if (request.getParameter("purpose").equals("rent")) {
					// Rent
					rentMonth = Double.parseDouble(request.getParameter("totalMonth"));
					perMonthPrice = Double.parseDouble(request.getParameter("monthlyPrice"));
					deposit = Double.parseDouble(request.getParameter("deposit"));
					purpose = PurposeFactory.getRentPurpose(rentMonth, perMonthPrice, deposit);
					purpose_id = purpose.create();
					property = ApartmentAndHouseFactory.createHouseRent(id, name, address, user, bedroom, bathroom,
							status, livingroom, parking, kitchen, utilitiesCost, yard, rentMonth, perMonthPrice,
							deposit);
				} else {
					// Sell
					sellPrice = Double.parseDouble(request.getParameter("sellPrice"));
					purpose = PurposeFactory.getSellPurpose(sellPrice);
					purpose_id = purpose.create();
					property = ApartmentAndHouseFactory.createHouseSell(id, name, address, user, bedroom, bathroom,
							status, livingroom, parking, kitchen, utilitiesCost, yard, sellPrice);
				}
			}

			PropertyStatus propertyStatus = new PropertyStatus();
			propertyStatus.setId(1);
			propertyStatus.setName("Pending");

			if (request.getParameter("agentaccount") != null && !request.getParameter("agentaccount").equals("0")) {
				property.setAgentAccount(Agent.find(request.getParameter("agentaccount")));
			}

			PurposeType propertyPurpose = property.getPurposeType();
			propertyPurpose.setId(purpose_id);

			property.setStatus(propertyStatus);
			property.setPurposeType(propertyPurpose);
			property.setDescription(request.getParameter("message"));

			int property_id = property.create(property, user.getId());
			Collection<Part> parts = request.getParts();
			BufferedImage img = null;
			File uploadDir = new File(System.getProperty("catalina.base") + "/wtpwebapps/RealStateManagement/public/uploads/");
			uploadDir.mkdirs();

			ArrayList<PropertyImage> images = new ArrayList<PropertyImage>();

			for (Part part : parts) {
				if (part.getName().equals("upload_image[]") && !part.getSubmittedFileName().isEmpty()) {
					try {
						img = ImageIO.read(part.getInputStream());
						String filePath = uploadDir.getPath().concat("/")
								.concat(property_id + "_" + part.getSubmittedFileName());
						ImageIO.write(img, "jpg", new File(filePath));
						System.out.println("File uploaded : " + filePath);
						images.add(new PropertyImage(property_id + "_" + part.getSubmittedFileName()));
					} catch (Exception e) {
						System.out.println("===< ".concat(e.getMessage()).concat(" >==="));
					}
				}

			}

			images.forEach((image) -> {
				image.create(property_id);
			});
			response.sendRedirect(getServletContext().getAttribute("ContextPath") + "/index?id=" + property_id);
			// response.sendRedirect("/property/item?id=" + property_id);
		}
	}

}
