public class Client {
	private String name;
	private int phone;
	private String style;

	public Client(String name, int number, String style) {
		this.name = name;
		this.phone = number;
		this.style = style;

		// try(Connection con = DB.sql2o.open()) {
		// 	String sql = "INSERT INTO clients(name, phone, style) VALUES (:name, :phone, :style)";
		// 	this.id = (int) con.createQuery(sql, true)
		// 		.addParameter("name", this.name)
		// 		.addParameter("phone", this.phone)
		// 		.executeUpdate()
		// 		.getKey();
		// }
	}
}
