import org.sql2o.*;
import java.util.List;

public class Procedure {
  private int id=0;
  private String description;
  private float price;

  public Procedure(String description, float price) {
    this.description=description;
    this.price=price;
//saves a procedure when an instance is created
    try(Connection cn = DB.sql2o.open()) {
      String sql = "INSERT INTO procedures (description, price) VALUES (:description, :price)";
      this.id = (int) cn.createQuery(sql, true)
        .addParameter("description", this.description)
        .addParameter("price", this.price)
        .executeUpdate()
        .getKey();
    }
  }
// returns the id of the procedure
  public int getId() {
    return id;
  }
//returns procedure's description
  public String getDescription() {
    return description;
  }
//updates procedure description
  public void setDescription(String description) {
    this.description=description;
    try(Connection cn = DB.sql2o.open()) {
      String sql = "UPDATE procedures SET description = :description WHERE id = :id";
      cn.createQuery(sql)
        .addParameter("description", description)
        .addParameter("id", id)
        .executeUpdate();
    }
  }
//returns the price of a certain style/procedure
  public float getPrice() {
    return price;
  }
//updates procedure
  public void setPrice(float price) {
    this.price=price;
    try(Connection cn = DB.sql2o.open()) {
      String sql = "UPDATE procedures SET price = :price WHERE id = :id";
      cn.createQuery(sql)
        .addParameter("price", price)
        .addParameter("id", id)
        .executeUpdate();
    }
  }
//finds and returns a procedure by id
  public static Procedure find(int id) {
    try(Connection cn = DB.sql2o.open()) {
      String sql = "SELECT * FROM procedures WHERE id=:id";
      Procedure procedure = cn.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Procedure.class);
      return procedure;
    }
  }
// returns all the procedures
  public static List<Procedure> all() {
    String sql = "SELECT * FROM procedures ORDER BY price";
    try(Connection cn = DB.sql2o.open()) {
      return cn.createQuery(sql).executeAndFetch(Procedure.class);
    }
  }
//deletes a procedure/hair_style
  public static void delete(int id) {
    try(Connection cn = DB.sql2o.open()) {
      String sql = "DELETE FROM procedures WHERE id = :id;";
      cn.createQuery(sql)
      .addParameter("id", id)
      .executeUpdate();
    }
  }

}
