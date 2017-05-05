import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;

public class Client {
	private String name;
	private int phone;
	private String style;
	private int id;
	private int stylistId;

	public Client(String name, int number, String style, int stylistId) {
		this.name = name;
		this.phone = number;
		this.style = style;
		this.stylistId = stylistId;

		try(Connection con = DB.sql2o.open()) {
			String sql = "INSERT INTO clients(name, phone, style, stylistId) VALUES (:name, :phone, :style, :stylistId)";
			this.id = (int) con.createQuery(sql, true)
				.addParameter("name", this.name)
				.addParameter("phone", this.phone)
				.addParameter("style", this.style)
				.addParameter("stylistId", this.stylistId)
				.executeUpdate()
				.getKey();
		}
	}

	//returns the id
	public int getId() {
		return id;
	}
	//returns name of the client
	public String getName() {
		return name;
	}
	//returns phone number
	public int getPhone() {
		return phone;
	}
	//returns the clients hair style
	public String getStyle() {
		return style;
	}
	//returns the client that the client belong
	public int getStylistId() {
		return stylistId;
	}

	@Override
	public boolean equals(Object anotherClient) {
		if (!(anotherClient instanceof Client)) {
			return false;
		} else {
			Client client3 = (Client) anotherClient;
			return this.getName().equals(client3.getName()) && this.getId() == client3.getId() && this.getPhone() == client3.getPhone() && this.getStyle().equals(client3.getStyle());
		}
		}
	public static List<Client> all() {
		String sql = "SELECT id, name, phone, style, stylistId FROM clients";
		try(Connection con = DB.sql2o.open()) {
			return con.createQuery(sql).executeAndFetch(Client.class);
		}
	}
	//save method
	public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO clients(name, phone, style, stylistId) VALUES (:name, :phone, :style, :stylistId)";
			this.id = (int) con.createQuery(sql, true)
				.addParameter("phone", this.phone)
				.addParameter("name", this.name)
        .addParameter("style", this.style)
				.addParameter("stylistId", this.stylistId)
        .executeUpdate()
        .getKey();
    }
  }

public void update(String description) {
	try(Connection con = DB.sql2o.open()) {
		String sql = "UPDATE clients SET name = :name, phone = :phone, style = :style, stylistId = :stylistId  WHERE id = :id";
		con.createQuery(sql)
			.addParameter("phone", phone)
			.addParameter("name", name)
			.addParameter("style", style)
			.addParameter("stylistId", stylistId)
			.addParameter("id", id)
			.executeUpdate();
	}
}
public void delete() {
try(Connection con = DB.sql2o.open()) {
String sql = "DELETE FROM clients WHERE id = :id;";
con.createQuery(sql)
	.addParameter("id", id)
	.executeUpdate();
}
}
public static Client find(int id) {
		try(Connection con = DB.sql2o.open()) {
			String sql = "SELECT * FROM clients where id=:id";
			Client client = con.createQuery(sql)
				.addParameter("id", id)
				.executeAndFetchFirst(Client.class);
			return client;
		}
	}

}
