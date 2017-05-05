import org.sql2o.*;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
	public static void main(String[] args) {
		staticFileLocation("/public");
		String layout = "templates/layout.vtl";
		// if a port is set for the app, use it else continue with 4567
		ProcessBuilder process = new ProcessBuilder();
		Integer port;
		if (process.environment().get("PORT") !=null) {
			port = Integer.parseInt(process.environment().get("PORT"));
		}else {
			port = 4567;
		}
		setPort(port);

	get("/", (request, response) -> {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("stylists", Stylist.all());
		model.put("template", "templates/index.vtl");
		return new ModelAndView(model, layout);
	}, new VelocityTemplateEngine());
	}
}
