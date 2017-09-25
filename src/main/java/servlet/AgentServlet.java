package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Agent;

/**
 * Servlet implementation class AgentServlet
 */
@WebServlet("/agents")
public class AgentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AgentServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("agents", Agent.getAgentList());
		RequestDispatcher rq = getServletContext().getRequestDispatcher("/agent.jsp");
		rq.forward(request, response);
	}

}
