package controllers;

import java.util.HashMap;
import java.util.Map;

import models.Agent;
import spark.Request;
import spark.Response;
import spark.Route;
import utils.Path;
import utils.ViewUtil;

public class AgentController {
	public static Route initAgentListPage = (Request rq, Response rs) -> {
        Map<String, Object> context = new HashMap<>();
		context.put("agents", Agent.getAgentList());
        return ViewUtil.render(rq, context, Path.Template.AGENT_LIST);
    };
}
