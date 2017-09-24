package init;

import static spark.Spark.*;
import controllers.AgentController;
import controllers.BookAppointmentController;
//import controllers.BookAppointmentController;
import controllers.LoginController;
import controllers.MainController;
import controllers.OrderController;
import controllers.PropertyController;
import controllers.RequestsPropertiesController;
import utils.Path;

public class Main {

	public static void main(String[] args) {
		boolean localhost = true;
		if (localhost) {
			String projectDir = System.getProperty("user.dir");
			String staticDir = "/src/main/resources/public";
			staticFiles.externalLocation(projectDir + staticDir);
		} else {
			staticFiles.location("/public");
		}

		// routes
		get("/", MainController.initHome);
		get("/login", LoginController.initLoginPage);
		get("/register", LoginController.initRegisterPage);
		get("/agents", AgentController.initAgentListPage);

		post(Path.Web.LOGIN, LoginController.handleLoginPost);
		post("/logout", LoginController.handleLogoutPost);
		post(Path.Web.REGISTER, LoginController.handleRegisterPost);

		path("/property", () -> {
			post("/upload", PropertyController.submitPropertyPost);
			get("/upload", PropertyController.initUploadPage);
			get("/list", PropertyController.getItemListPage);
			post("/list", PropertyController.postItemListPage);
			post("/bookAppt/request", BookAppointmentController.requestBookApptPost);
			get("/bookAppt/requests", BookAppointmentController.initBookApptsRequestsPage);
			get("/bookAppt/approve/:id", BookAppointmentController.approveBookApptPost);
			get("/requests", RequestsPropertiesController.initRequestsPage);
			get("/approve/:id", RequestsPropertiesController.approvePropertyPost);
			get("/:id", PropertyController.getItemPage);
			post("/:id", PropertyController.postItemPageFeedback);
		});

		path("/order", () -> {
			get("/list", OrderController.getOrderListPage);
			get("/register/:propertyId/:type", OrderController.getOrderRegisterPage);
			post("/register", OrderController.postOrderRegisterPage);
			get("/approve/:orderId", OrderController.getApproveOrderPage);
			get("/cancel/:orderId", OrderController.getCancelOrderPage);
			post("/payment", OrderController.postPaymentOrderPage);
			get("/:orderId", OrderController.getOrderPage);
		});
	}
}
