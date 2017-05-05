import java.time.LocalDateTime;
import org.sql2o.*;
import java.util.List;

public class Stylist {
	private int id;
	private String name;
	private int phone;
	private String description;

	public Stylist(String name, int phone, String description) {
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
	public int getPhoneNumber() {
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

public static List<Stylist> all() {
	 String sql = "SELECT id, name, phone, description FROM stylists";
	 try(Connection con = DB.sql2o.open()) {
		 return con.createQuery(sql).executeAndFetch(Stylist.class);
	 }
 }
 @Override
	public boolean equals(Object otherCategory) {
		if (!(otherCategory instanceof Stylist)) {
			return false;
		} else {
			Stylist newStylist = (Stylist) otherCategory;
			return this.getName().equals(newStylist.getName()) && this.getId() == newStylist.getId() && this.getPhoneNumber() == newStylist.getPhoneNumber() && this.getDescription().equals(newStylist.getDescription());
		}
}

}
