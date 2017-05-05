import java.time.LocalDateTime;
import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.Arrays;

public class StylistTest {

	@Rule
  public DatabaseRule database = new DatabaseRule();
	//defining an object of a stylists
	private Stylist stylist1;
	private Stylist stylist2;
	@Before
	public void instance() {
		stylist1 = new Stylist( "Yvone", 2, "works well");
		stylist2 = new Stylist( "Yvone", 3, "works well");
	}

	//creating an instance of Stylist
	//checks if the instance is created successfully
	@Test
	public void Stylist_instantiatesCorrectly_true() {
		assertTrue(stylist1 instanceof Stylist);
	}
	//assert if the getName method returns name
	@Test
	public void GetNameReturnsNameOfStylist_true() {
		assertEquals("Yvone", stylist1.getName());
	}
	//assert if the getNumber returns number
	@Test
	public void GetNumberReturnsPhoneNumberOfStylist_true() {
		assertEquals(2, stylist1.getPhoneNumber());
	}
	//assert if the getDescription method returns description
	@Test
	public void getDescriptionReturnsDescriptionOfStylist_true() {
		assertEquals("works well", stylist1.getDescription());
	}
	//gets the correct stylist id from db
	@Test
  public void save_returnsIdFromDatabase_true() {
		stylist1.save();
    assertEquals(true, stylist1.getId()>0);
  }

	// assert if find method returns stylists with same id
	@Test
	public void find_returnsStylistWithSameId_stylists2() {
		stylist1.save();
		stylist2.save();
		assertEquals(Stylist.find(stylist2.getId()), stylist2);
	}
	//saving to DB
	@Test
     public void save_savesIntoDatabase_true() {
       stylist1.save();
       assertTrue(Stylist.all().get(0).equals(stylist1));
  }
	// equal entities
	@Test
	public void equals_returnsTrueIfNamesAretheSame() {
		Stylist stylist1 = new Stylist( "Yvone", 2, "works well");
		Stylist stylist2 = new Stylist( "Yvone", 2, "works well");
		assertTrue(stylist1.equals(stylist2));
	}
	//all method returns all the instances
	@Test
			public void all_returnsAllInstancesOfStylist_true() {
				stylist1.save();
				stylist2.save();
				assertEquals(true, Stylist.all().get(0).equals(stylist1));
				assertEquals(true, Stylist.all().get(1).equals(stylist2));
			}

	//deletes a stylist
	@Test
  public void delete_deletesStylist_true() {
  	stylist1.save();
		int stylistId = stylist1.getId();
    stylist1.delete();
    assertEquals(null, Stylist.find(stylistId));
  }

	//returns the clients belonging to a stylist
	@Test
		public void getTasks_retrievesALlTasksFromDatabase_tasksList() {
			stylist1.save();
			Client client1 = new Client("name", 3, "my style", stylist1.getId());
			client1.save();
			Client client2 = new Client("name", 56, "my style", stylist1.getId());
			client2.save();
			Client[] clients = new Client[] { client1, client2 };
			assertTrue(stylist1.getClients().containsAll(Arrays.asList(clients)));
}

	//check updating stylist
	// @Test
	// public void update_updatesStylistDescription_true() {
	// 	stylist1.save();
	// 	stylist1.update("Lauryne", 1, "Dont work well");
	// 	assertEquals("Lauryne", Stylist.find(stylist1.getId()).getName());
	// 	assertEquals(1, Stylist.find(stylist1.getId()).getPhoneNumber());
	// 	assertEquals("Dont work well", Stylist.find(stylist1.getId()).getDescription());
	// }
}
