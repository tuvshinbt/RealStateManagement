package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constants.OHRT;
import models.Property;
import models.PropertyFeedback;
import models.dao.PropertyDAO;
import models.dao.PropertyFeedbackDAO;
import models.web.property.PropertyListModel;
import models.web.property.PropertyModel;
import utils.Path;
import utils.ViewUtil;

/**
 * Servlet implementation class PropertListGetItemPage
 */
@WebServlet("/PropertListGetItemPage")
public class PropertListGetItemPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PropertListGetItemPage() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
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

			propertyListModel
					.setTotalItemCount(PropertyDAO.count(0, null, 0, OHRT.PROPERTY.STATUS.APPROVED, 0, null, 0));
			request.setAttribute("propertyListModel", propertyListModel);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		RequestDispatcher rq = getServletContext().getRequestDispatcher("/property_list.jsp");
		rq.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		PropertyListModel propertyListModel = new PropertyListModel();
		propertyListModel.setSearchBy((String) request.getParameter("searchBy"));
		propertyListModel.setSearchPurposeTypeId(Integer.parseInt(request.getParameter("searchPurposeTypeId")));
		propertyListModel.setSearchPropertyTypeId(Integer.parseInt(request.getParameter("searchPropertyTypeId")));
		if (request.getAttribute("order") != null) {
			propertyListModel.setOrder((int) request.getAttribute("order"));
		} else {
			propertyListModel.setOrder(0);
		}
		if (request.getAttribute("currentPage") != null) {
			propertyListModel.setCurrentPage((int) request.getAttribute("currentPage"));
		} else {
			propertyListModel.setCurrentPage(1);
		}
		try {
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
			request.setAttribute("propertyListModel", propertyListModel);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		ServletContext context = getServletContext();
		RequestDispatcher rq = context.getRequestDispatcher("/property_list.jsp");
		rq.forward(request, response);
	}

}
