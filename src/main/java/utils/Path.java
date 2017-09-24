package utils;

import lombok.Getter;

public class Path {
	public static class Web {
		@Getter
		public static final String INDEX = "/";
		@Getter
		public static final String LOGIN = "/login";
		@Getter
		public static final String REGISTER = "/register";
		@Getter
		public static final String PROPERTY_ITEM = "/property/:id";
		@Getter
		public static final String PROPERTY_LIST = "/property/list";
		@Getter
		public static final String PROPERTY_UPLOAD = "/property/upload";
		@Getter
		public static final String PROPERTIES_REQUESTS = "/property/requests";
		@Getter
		public static final String LIST_APPOINTMENT = "/property/bookAppt/requests";
		@Getter
		public static final String REQUEST_APPOINTMENT = "/property/bookAppt/request";
		@Getter
		public static final String LOGOUT = "/logout";
	}

	public static class Template {
		public final static String LAYOUT = "/velocity/layout.vm";
		public final static String INDEX = "/velocity/index/index.vm";
		public final static String LOGIN = "/velocity/login.vm";
		public final static String SIGN_UP = "/velocity/register.vm";

		public final static String PROPERTY_ITEM = "/velocity/property/property_item.vm";
		public final static String PROPERTY_LIST = "/velocity/property/property_list.vm";
		public final static String PROPERTY_UPLOAD = "/velocity/property/property_upload.vm";
		public final static String PROPERTIES_REQUESTS = "/velocity/property/properties_requests.vm";
		public final static String APPOINTMENTS_REQUESTS = "/velocity/property/appointments_requests.vm";

		// order
		public final static String ORDER_REGISTER = "/velocity/order/order_register.vm";
		public final static String ORDER_LIST = "/velocity/order/order_list.vm";
		public final static String ORDER_ITEM = "/velocity/order/order_item.vm";

		public final static String AGENT_LIST = "velocity/agent/agent_list.vm";
	}
}
