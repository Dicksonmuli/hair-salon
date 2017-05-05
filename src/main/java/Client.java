import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;

public class Client {
	private String name;
	private int phone;
	private String style;
	private int id;

	public Client(String name, int number, String style) {
		this.name = name;
		this.phone = number;
		this.style = style;

		try(Connection con = DB.sql2o.open()) {
			String sql = "INSERT INTO clients(name, phone, style) VALUES (:name, :phone, :style)";
			this.id = (int) con.createQuery(sql, true)
				.addParameter("name", this.name)
				.addParameter("phone", this.phone)
				.addParameter("style", this.style)
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

	@Override
	public boolean equals(Object anotherClient) {
		if (!(anotherClient instanceof Client)) {
			return false;
		} else {
			Client client3 = (Client) anotherClient;
			return this.getName().equals(client3.getName()) && this.getId() == client3.getId() && this.getPhone() == client3.getPhone() && this.getStyle().equals(client3.getStyle());
		}
		}
}
