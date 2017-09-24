package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.BookAppointment;
import models.Property;
import models.dao.BookAppointmentDAO;
import models.dao.PropertyDAO;
import spark.Request;
import spark.Response;
import spark.Route;
import utils.Path;
import utils.RequestUtil;
import utils.ViewUtil;

public class BookAppointmentController {
	public static Route requestBookApptPost = (Request rq, Response rs) -> {
		String apptDate = rq.queryMap().get("appointment_date").value();
		String apptInfo = rq.queryMap().get("appointment_info").value();
		String propertyID = rq.queryMap().get("property_id").value();

		int buyerID = RequestUtil.getSessionCurrentUser(rq).getId();

		boolean result = BookAppointment.requestBookAppointment(propertyID, apptDate, apptInfo, buyerID);

		rs.redirect("/property/" + propertyID);
		return null;
	};

	public static Route initBookApptsRequestsPage = (Request rq, Response rs) -> {
		Map<String, Object> model = new HashMap<>();

		List<BookAppointment> appointments = new ArrayList<BookAppointment>();

		appointments = BookAppointmentDAO.findByStatus(1);
		model.put("appointments", appointments);
		return ViewUtil.render(rq, model, Path.Template.APPOINTMENTS_REQUESTS);
	};

	public static Route approveBookApptPost = (Request rq, Response rs) -> {
		String id = rq.params("id");
		// int approvedBy = RequestUtil.getSessionCurrentUser(rq).getId();
		BookAppointment.approveAppointment(id);
		rs.redirect(Path.Web.LIST_APPOINTMENT);
		return null;
	};
}
