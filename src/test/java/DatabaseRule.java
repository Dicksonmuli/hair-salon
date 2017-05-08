import org.junit.rules.ExternalResource;
import org.sql2o.*;

// overriding before and after method annotations
public class DatabaseRule extends ExternalResource {
	@Override
	protected void before() {
		DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hair_salon_tests","dickson", "dickson");
	}
	@Override
  protected void after() {
    try(Connection con = DB.sql2o.open()) {
			String deleteClientsQuery = "DELETE FROM clients *;";
				 String deleteStylistsQuery = "DELETE FROM stylists *;";
				 String deleteProceduresQuery = "DELETE FROM procedures *;";
				 String deleteAppointmentQuery = "DELETE FROM appointments *;";
				 con.createQuery(deleteClientsQuery).executeUpdate();
				 con.createQuery(deleteStylistsQuery).executeUpdate();
				 con.createQuery(deleteProceduresQuery).executeUpdate();
				 con.createQuery(deleteAppointmentQuery).executeUpdate();
			 }
  }
}
