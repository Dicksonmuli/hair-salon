import java.time.LocalDateTime;
import org.sql2o.*;
import java.util.List;

public class Stylist {
	private int id;
	private String name;
	private String phone;
	private String description;

	public Stylist(String name, String phone, String description) {
		this.name = name;
		this.phone = phone;
		this.description = description;
	}
//returns id of the stylist
	public int getId() {
		return id;
	}
//returns id of the stylist
	public String getName() {
		return name;
	}
//returns id of the stylist
	public String getPhoneNumber() {
		return phone;
	}
//returns id of the stylist
	public String getDescription() {
			return description;
	}
//save method
public void save() {
	try(Connection con = DB.sql2o.open()) {
		String sql = "INSERT INTO stylists (name, phone, description) VALUES (:name, :phone, :description)";
		this.id = (int) con.createQuery(sql, true)
		.addParameter("name", this.name)
		.addParameter("phone", this.phone)
		.addParameter("description", this.description)
		.executeUpdate()
		.getKey();
	}
}
//returns stylists with the same id
public static Stylist find(int id) {
	try(Connection con = DB.sql2o.open()) {
		String sql = "SELECT * FROM stylists where id=:id";
		Stylist stylist = con.createQuery(sql)
			.addParameter("id", id)
			.executeAndFetchFirst(Stylist.class);
		return stylist;
	}
}
//returns all the clients assigned to this stylist
public List<Client> getClients() {
	try(Connection con = DB.sql2o.open()) {
		String sql = "SELECT * FROM clients where stylistId=:id";
		return con.createQuery(sql)
		.addParameter("id", this.id)
		.executeAndFetch(Client.class);
	 }
 }
 public static List<Stylist> all() {
		String sql = "SELECT id, name, phone, description FROM stylists";
		try(Connection con = DB.sql2o.open()) {
			return con.createQuery(sql)
			.executeAndFetch(Stylist.class);
		}
	}
 @Override
	public boolean equals(Object otherStylist) {
		if (!(otherStylist instanceof Stylist)) {
			return false;
		} else {
			Stylist newStylist = (Stylist) otherStylist;
			return this.getName().equals(newStylist.getName()) && this.getPhoneNumber().equals(newStylist.getPhoneNumber()) && this.getDescription().equals(newStylist.getDescription()) &
			this.getId() == newStylist.getId();
		}
}
//deleting from the db
public void delete() {
	try(Connection con = DB.sql2o.open()) {
		String sql = "DELETE FROM stylists WHERE id= :id;";
		con.createQuery(sql)
		.addParameter("id", id)
		.executeUpdate();
	}
}
//updating variables in the table
public void update(String name, String phone, String description) {
try(Connection con = DB.sql2o.open()) {
	String sql = "UPDATE stylists SET name = :name, phone = :phone, description = :description WHERE id = :id";
	con.createQuery(sql)
		.addParameter("description", description)
		.addParameter("name", name)
		.addParameter("phone", phone)
		.addParameter("id", id)
		.executeUpdate();
}
}

}
