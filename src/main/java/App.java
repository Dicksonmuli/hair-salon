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
//root route
	get("/", (request, response)  -> {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("stylists", Stylist.all());
		model.put("template", "templates/index.vtl");
		return new ModelAndView(model, layout);
	}, new VelocityTemplateEngine());
//new stylist route
	get("/stylists/new", (request, response) -> {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("template", "templates/stylists-form.vtl");
		return new ModelAndView(model, layout);
	}, new VelocityTemplateEngine());
//post method to add a stylist
get("/stylists", (request, response)  -> {
	Map<String, Object> model = new HashMap<String, Object>();
	String name = request.queryParams("name");
	Integer phone = ;
	int = request.queryParams("name");
	model.put("stylists", Stylist.all());
	model.put("template", "templates/index.vtl");
	return new ModelAndView(model, layout);
}, new VelocityTemplateEngine());

get("/procedures", (request, response) -> {
		 Map<String, Object> model = new HashMap<String, Object>();
		 model.put("procedures", Procedure.all());
		 model.put("template", "templates/procedure.vtl");
		 return new ModelAndView(model, layout);
	 }, new VelocityTemplateEngine());

	 get("/procedures/:id", (request, response) -> {
		 Map<String, Object> model = new HashMap<String, Object>();
		 int id = Integer.parseInt(request.params(":id"));
		 model.put("procedure", Procedure.find(id));
		 model.put("template", "templates/edit-procedure.vtl");
		 return new ModelAndView(model, layout);
		 
	 }, new VelocityTemplateEngine());
//adding a procedure
	 post("/procedures/:id", (request, response) -> {
		 Map<String, Object> model = new HashMap<String, Object>();
		 int id = Integer.parseInt(request.params(":id"));
		 Procedure.find(id).setDescription(request.queryParams("first"));
		 Procedure.find(id).setPrice(Float.parseFloat(request.queryParams("age")));
		 model.put("procedures", Procedure.all());
		 model.put("template", "templates/procedure.vtl");
		 return new ModelAndView(model, layout);
	 }, new VelocityTemplateEngine());
//deleting procedures
	 get("/delete/procedures/:id", (request, response) -> {
		 Map<String, Object> model = new HashMap<String, Object>();
		 int id = Integer.parseInt(request.params(":id"));
		 Procedure.delete(id);
		 response.redirect("/procedures");
		 model.put("template", "templates/procedure.vtl");
		 return new ModelAndView(model, layout);
	 }, new VelocityTemplateEngine());
//adding procedures
	 post("/procedures", (request, response) -> {
		 Map<String, Object> model = new HashMap<String, Object>();
		 Procedure procedure = new Procedure(request.queryParams("procedure"), Float.parseFloat(request.queryParams("price")));
		 model.put("procedure", procedure.getDescription());
		 model.put("price", procedure.getPrice());
		 model.put("procedures", Procedure.all());
		 model.put("template", "templates/procedure.vtl");
		 return new ModelAndView(model, layout);
	 }, new VelocityTemplateEngine());
	}
}
