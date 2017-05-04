import java.time.LocalDateTime;
import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class StylistTest {

	@Rule
  public DatabaseRule database = new DatabaseRule();
	//defining an object of a stylists
	private Stylist testStylist;
	@Before
	public void instance() {
		testStylist = new Stylist(1, "Yvone", 254719364087, "works well");
	}

	//creating an instance of Stylist
	//checks if the instance is created successfully
	@Test
	public void Task_instantiatesCorrectly_true() {
		assertTrue(testStylist instanceof Stylist0);
	}
}
