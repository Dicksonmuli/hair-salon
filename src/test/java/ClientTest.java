import java.time.LocalDateTime;
import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class ClientTest {
	private Client client1;
	private Client client2;

	@Before
	public void instance() {
		client1 = new Client("Customer1", 23, "Weave", 1);
		client2 = new Client("Customer2", 24, "Weave", 1);
	}

	@Rule
  public DatabaseRule database = new DatabaseRule();

	// creating an instance of Task successfully
	@Test
	public void Client_instanciatesCorrectly_true() {
		assertTrue(client1  instanceof Client);
	}
	// instantiates with a name
	@Test
	public void getName_instantiatesWithName_String() {
		assertEquals("Customer1", client1.getName());
	}
	// instantiates with a phone number
	@Test
	public void getPhone_instantiatesWithDescription_String() {
		assertEquals(23, client1.getPhone());
	}
	// instantiates with hair style
	@Test
	public void getStyle_instantiatesWithDescription_String() {
		assertEquals("Weave", client1.getStyle());
	}
	//getId returns an id of > 0
	@Test
	public void getId_tasksInstantiateWithAnID_greaterthano() {
		assertTrue(client1.getId() > 0);
	}
	//returns a client of a certain id
	@Test
	public void find_returnsClientWithSameId_client2() {
		assertEquals(Client.find(client2.getId()), client2);
	}
	//returns all instanceof a client
	@Test
	public void all_returnsAllInstancesOfClient_true() {
		assertTrue(Client.all().get(0).equals(client1));
		assertTrue(Client.all().get(1).equals(client2));
	}
	@Test
	public void save_savesStyleIdIntoDB_true() {
	 Stylist testStylist = new Stylist("Minne", 4, "chores");
		testStylist.save();
		Client client1 = new Client("Minnem", 23, "stylenew", testStylist.getId());
		client1.save();
		Client savedClient = Client.find(client1.getId());
		assertEquals(savedClient.getStylistId(), testStylist.getId());
	}
	//update  clients
	@Test
	public void update_updatesClientName_true() {
		client1.save();
		client1.update("description", "Viv", 67, "weeve", 1);
		assertEquals("Viv", Client.find(client1.getId()).getName());
	}
	@Test
public void delete_deletesTask_true() {
  client1.save();
  int clientId = client1.getId();
  client1.delete();
  assertEquals(null, Client.find(clientId));
}

}
