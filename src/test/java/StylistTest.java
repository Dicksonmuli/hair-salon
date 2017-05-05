import java.time.LocalDateTime;
import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class StylistTest {

	@Rule
  public DatabaseRule database = new DatabaseRule();
	//defining an object of a stylists
	private Stylist stylist1;
	private Stylist stylist2;
	@Before
	public void instance() {
		stylist1 = new Stylist(1, "Yvone", 254, "works well");
		stylist2 = new Stylist(1, "Yvone", 254, "works well");
	}

	//creating an instance of Stylist
	//checks if the instance is created successfully
	@Test
	public void Stylist_instantiatesCorrectly_true() {
		assertTrue(stylist1 instanceof Stylist);
	}
	//assert if the getId returns id
	@Test
	public void Stylist_getIdReturnsIdOfStylist_true() {
		assertEquals(1, stylist1.getId());
	}
	//assert if the getName method returns name
	@Test
	public void GetNameReturnsNameOfStylist_true() {
		assertEquals("Yvone", stylist1.getName());
	}
	//assert if the getNumber returns number
	@Test
	public void GetNumberReturnsPhoneNumberOfStylist_true() {
		assertEquals(254, stylist1.getNumber());
	}
	//assert if the getDescription method returns description
	@Test
	public void getDescriptionReturnsDescriptionOfStylist_true() {
		assertEquals("works well", stylist1.getDescription());
	}
}
