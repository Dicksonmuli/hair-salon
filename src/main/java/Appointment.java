import org.sql2o.*;
import java.util.List;
import java.util.Date;

public class Appointment {
  private int id=0;
  private String time;
  private int clientid;
  private int procedureid;
//constructor
  public Appointment (String time, int clientid, int procedureid) {
    this.time = time;
    this.clientid = clientid;
    this.procedureid = procedureid;
//saves an instance when created
    try(Connection cn = DB.sql2o.open()) {
      String sql = "INSERT INTO appointments (time, clientid, procedureid) VALUES (:time, :clientid, :procedureid)";
      this.id = (int) cn.createQuery(sql, true)
        .addParameter("time", this.time)
        .addParameter("clientid", this.clientid)
        .addParameter("procedureid", this.procedureid)
        .executeUpdate()
        .getKey();
    }
  }
//returns an id
  public int getId() {
    return id;
  }
//returns time
  public String getTime() {
    return time;
  }
// returns cliedid of the requested appointment
  public int getClientId() {
    return clientid;
  }
//returns client of the requested appointment
  public Client getClient() {
    return Client.find(clientid);
  }
//returns the stylist assigned to the client
  public Stylist getStylist() {
    return Stylist.find(Client.find(clientid).getStylistId());
  }
//returns procedure id
  public int getProcedureId() {
    return procedureid;
  }
//returns a procedure by id
  public Procedure getProcedure() {
    return Procedure.find(procedureid);
  }
//find an appointment with an id
  public static Appointment find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM appointments WHERE id=:id";
      Appointment appointment = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Appointment.class);
      return appointment;
    }
  }
//retrieves all the instances of appointment
  public static List<Appointment> all() {
    String sql = "SELECT * FROM appointments ORDER BY time";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Appointment.class);
    }
  }
//delete method
  public static void delete(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM appointments WHERE id = :id;";
      con.createQuery(sql)
      .addParameter("id", id)
      .executeUpdate();
    }
  }

}
