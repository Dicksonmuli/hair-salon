
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
		model.put("appointments", Appointment.all());
		model.put("template", "templates/index.vtl");
		return new ModelAndView(model, layout);
	}, new VelocityTemplateEngine());
// posting appointments
	post("/", (request, response) -> {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("appointment", request.queryParams("day"));
		model.put("appointments", Appointment.all());
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
	post("/stylists", (request, response) -> {
			Map<String, Object> model = new HashMap<String, Object>();
			String name = request.queryParams("name");
			Integer phone = Integer.parseInt(request.queryParams("phone"));
			String description = request.queryParams("description");
			Stylist stylist = new Stylist(name, phone, description);
			stylist.save();
			// model.put("stylists", Stylist.all());
			model.put("template", "templates/stylists.vtl");
			return new ModelAndView(model, layout);
		}, new VelocityTemplateEngine());
//displays all the stylists
	get("/stylists", (request, response)  -> {
		Map<String, Object> model = new HashMap<String, Object>();
		String name = request.queryParams("name");
		model.put("stylists", Stylist.all());
		model.put("template", "templates/stylists.vtl");
		return new ModelAndView(model, layout);
	}, new VelocityTemplateEngine());
	get("/stylists/:id", (request, response) -> {
		Map<String, Object> model = new HashMap<String, Object>();
		int id = Integer.parseInt(request.params(":id"));
		Stylist stylist = Stylist.find(id);
		model.put("stylist", stylist);
		model.put("clients",  Client.all());
		model.put("template", "templates/stylist.vtl");
		return new ModelAndView(model, layout);
	}, new VelocityTemplateEngine());
	//post adding a client to a stylist
	post("/stylists/:id", (request, response) -> {
		Map<String, Object> model = new HashMap<String, Object>();
		String name = request.queryParams("name");
		String style = request.queryParams("style");
		int phone = Integer.parseInt(request.queryParams("phone"));

		int id = Integer.parseInt(request.params("stylistId"));
		Stylist stylist = Stylist.find(id);
		Client client = new Client(name, phone, style, stylist.getId());

		model.put("stylist", stylist);
		model.put("template", "templates/stylist.vtl");
		return new ModelAndView(model, layout);
	}, new VelocityTemplateEngine());
	//  dynamic route for every client
	get("/stylist/:stylistId/clients/:id", (request, response) -> {
		 Map<String, Object> model = new HashMap<String, Object>();
		 Stylist stylist = Stylist.find(Integer.parseInt(request.params(":stylistId")));
		 Client client = Client.find(Integer.parseInt(request.params(":id")));
		 model.put("stylist", stylist);
		 model.put("client", client);
		 model.put("template", "templates/edit-client.vtl");
		 return new ModelAndView(model, layout);
	 }, new VelocityTemplateEngine());
	 //deleting a client
	 get("/delete/clients/:id", (request, response) -> {
		 Map<String, Object> model = new HashMap<String, Object>();
		 int id = Integer.parseInt(request.params(":id"));
		 Client client = Client.find(id);
		 client.delete();
		 response.redirect("/stylists");
		 model.put("template", "templates/edit-client.vtl");
		 return new ModelAndView(model, layout);
	 }, new VelocityTemplateEngine());
	 //updating a task
	 post("/stylist/:stylistId/clients/:id", (request, response) -> {
			Map<String, Object> model = new HashMap<String, Object>();
			Client client = Client.find(Integer.parseInt(request.params("id")));
			String name = request.queryParams("name");
			String style = request.queryParams("style");
			int phone = Integer.parseInt(request.queryParams("phone"));

			int id = Integer.parseInt(request.params("stylistId"));
			Stylist stylist = Stylist.find(client.getStylistId());
			client.update(name, phone, style, id);
			String url = String.format("/stylist/%d/Clients/%d", stylist.getId(), client.getId());
			response.redirect(url);
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
//deleting procedures/styles
	 get("/delete/procedures/:id", (request, response) -> {
		 Map<String, Object> model = new HashMap<String, Object>();
		 int id = Integer.parseInt(request.params(":id"));
		 Procedure.delete(id);
		 response.redirect("/procedures");
		 model.put("template", "templates/procedure.vtl");
		 return new ModelAndView(model, layout);
	 }, new VelocityTemplateEngine());
//adding procedures/styles
	 post("/procedures", (request, response) -> {
		 Map<String, Object> model = new HashMap<String, Object>();
		 Procedure procedure = new Procedure(request.queryParams("procedure"), Float.parseFloat(request.queryParams("price")));
		 model.put("procedure", procedure.getDescription());
		 model.put("price", procedure.getPrice());
		 model.put("procedures", Procedure.all());
		 model.put("template", "templates/procedure.vtl");
		 return new ModelAndView(model, layout);
	 }, new VelocityTemplateEngine());

//adding an appointmet
	 post("/appointments", (request, response) -> {
		 Map<String, Object> model = new HashMap<String, Object>();
		 int id = Integer.parseInt(request.params(":id"));
		 Procedure.find(id).setDescription(request.queryParams("first"));
		 Procedure.find(id).setPrice(Float.parseFloat(request.queryParams("age")));
		 model.put("procedures", Appointment.all());
		 model.put("template", "templates/appointment.vtl");
		 return new ModelAndView(model, layout);
	 }, new VelocityTemplateEngine());
//deleting appointments
	 get("/delete/appointments/:id", (request, response) -> {
		 Map<String, Object> model = new HashMap<String, Object>();
		 int id = Integer.parseInt(request.params(":id"));
		 Procedure.delete(id);
		 response.redirect("/appointments");
		 model.put("template", "templates/appointment.vtl");
		 return new ModelAndView(model, layout);
	 }, new VelocityTemplateEngine());
//adding appointment
	 post("/appointments", (request, response) -> {
		 Map<String, Object> model = new HashMap<String, Object>();

		 model.put("template", "templates/appointment.vtl");
		 return new ModelAndView(model, layout);
	 }, new VelocityTemplateEngine());
	//  all appointments
	get("/appointments", (request, response) -> {
			 Map<String, Object> model = new HashMap<String, Object>();
			 model.put("appointment", Appointment.all());
			 model.put("template", "templates/appointment.vtl");
			 return new ModelAndView(model, layout);
		 }, new VelocityTemplateEngine());

//contacts
get("/contacts", (request, response)  -> {
	Map<String, Object> model = new HashMap<String, Object>();
	model.put("template", "templates/contact.vtl");
	return new ModelAndView(model, layout);
}, new VelocityTemplateEngine());

	}
}
