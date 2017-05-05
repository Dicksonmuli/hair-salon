import java.time.LocalDateTime;
import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class ClientTest {
	private Client client1;
	private Client client2;

	@Before
	public void instance() {
		client1 = new Client("Customer1", 23, "Weave");
		client2 = new Client("Customer2", 24, "Weave");
	}

	@Rule
  public DatabaseRule database = new DatabaseRule();

	// creating an instance of Task successfully
	@Test
	public void Client_instanciatesCorrectly_true() {
		assertTrue(client1  instanceof Client);
	}
	// assigning each task a description and then retrieve it
	@Test
	public void Task_instantiatesWithName_String() {
		assertEquals("Customer1", client1.getDescription());
	}
	// assigning each task a description and then retrieve it
	@Test
	public void Task_instantiatesWithDescription_String() {
		Task myTask = new Task("Mow the lawn", 1);
		assertEquals("Mow the lawn", client1.getDescription());
	}
	// assigning each task a description and then retrieve it
	@Test
	public void Task_instantiatesWithDescription_String() {
		Task myTask = new Task("Mow the lawn", 1);
		assertEquals("Mow the lawn", client1.getDescription());
	}
}
