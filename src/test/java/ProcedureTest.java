import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class ProcedureTest {
  @Rule
  public DatabaseRule database = new DatabaseRule();

  private Procedure procedure;
  private Procedure procedure2;
//runs before each test creating an instance of Procedure
  @Before
  public void setUp() {
    procedure = new Procedure("Abuja", 1500.00f);
    procedure2 = new Procedure("women's haircut", 450.00f);
  }
//check if Procedure instanciates correctly
  @Test
  public void Procedure_instantiates_true() {
    assertEquals(true, procedure instanceof Procedure);
  }
//returns an id
  @Test
  public void save_returnsIdFromDatabase_true() {
    assertEquals(true, procedure.getId()>0);
  }
//returns description
  @Test
  public void getDescription_returnsCorrectDescription_String() {
    assertEquals("Abuja", procedure.getDescription());
  }
//setDescription updates description
  @Test
  public void setDescription_updatesDescription_String() {
    procedure.setDescription("men's haircut");
    assertEquals("men's haircut", Procedure.find(procedure.getId()).getDescription());
  }
//returns price of a procedure/style
  @Test
  public void getPrice_returnsCorrectPrice_Float() {
    assertEquals(1500.00f, procedure.getPrice(), 0.005);
  }
//updates price
  @Test
  public void setPrice_updatesPrice_Float() {
    procedure.setPrice(1700.00f);
    assertEquals(1700.0, Procedure.find(procedure.getId()).getPrice(), 0.005);
  }
//find a procedure form the database
  @Test
  public void find_returnCorrectProcedure_true() {
    assertTrue(Procedure.find(procedure.getId()).getDescription().equals(procedure.getDescription()));
  }
//returns all procedures from the database
  @Test
  public void Procedure_returnsAllInstances_true() {
    assertTrue(Procedure.all().size()>1);
  }

//deletes procedures from the database
  @Test
  public void delete_deletesProcedure_true() {
    int procedureId = procedure2.getId();
    Procedure.delete(procedureId);
    assertEquals(null, Procedure.find(procedureId));
  }

}
