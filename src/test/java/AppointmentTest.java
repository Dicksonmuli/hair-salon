import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class AppointmentTest {
  private Appointment appointment;
  private Appointment appointment2;

  @Before
  public void setUp() {
    appointment = new Appointment("2017-10-19 10:00:00", 1, 1);
    appointment2 = new Appointment("2017-01-19 9:00:00", 1, 1);
  }
//Appointment instanciates correctly
  @Test
  public void Appointment_instantiates_true() {
    assertEquals(true, appointment instanceof Appointment);
  }
//saves to the database with an id
  @Test
  public void saves_WithAnIdFromDatabase_true() {
    assertEquals(true, appointment.getId()>0);
  }
//find method returns an appointment of the given id
  @Test
  public void find_returnCorrectAppointment_true() {
    assertTrue(Appointment.find(appointment.getId()).getTime().equals(appointment.getTime()));
  }
//all method returns all instances
  @Test
  public void Appointment_returnsAllInstances_true() {
    assertTrue(Appointment.all().size()>1);
  }
//deletes from the database
  @Test
  public void delete_deletesAppointment_true() {
    int appointmentId = appointment2.getId();
    Appointment.delete(appointmentId);
    assertEquals(null, Appointment.find(appointmentId));
  }

}
