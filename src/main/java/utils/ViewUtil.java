package utils;

import java.util.Map;

import org.apache.velocity.app.*;
import org.apache.velocity.tools.generic.DateTool;
import org.apache.velocity.tools.generic.NumberTool;
import spark.*;
import spark.template.velocity.*;

public class ViewUtil {

	public static String render(Request request, Map<String, Object> model, String templatePath) {
		model.put("currentUser", RequestUtil.getSessionCurrentUser(request));
        model.put("WebPath", Path.Web.class); // Access application URLs from templates
        model.put("numberTool", new NumberTool());
        model.put("dateTool", new DateTool());
        return strictVelocityEngine().render(new ModelAndView(model, templatePath));
    }

    private static VelocityTemplateEngine strictVelocityEngine() {
        VelocityEngine configuredEngine = new VelocityEngine();
        configuredEngine.setProperty("runtime.references.strict", true);
        configuredEngine.setProperty("resource.loader", "class");
        configuredEngine.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        return new VelocityTemplateEngine(configuredEngine);
    }

}