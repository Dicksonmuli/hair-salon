import java.time.LocalDateTime;
import org.sql2o.*;

public class Stylist {
	private int id;
	private String name;
	private int phoneNumber;
	private String description;

	public Stylist(int id, String name, int number, String description) {
		this.id = id;
		this.name = name;
		this.phoneNumber = number;
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
	public int getNumber() {
		return phoneNumber;
	}
//returns id of the stylist
	public String getDescription() {
			return description;
	}

}
