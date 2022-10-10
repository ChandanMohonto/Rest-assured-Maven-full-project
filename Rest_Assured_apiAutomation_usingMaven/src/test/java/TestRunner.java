// ***************NOTE****************
//I have worked on junit and testng both frameworks you can run on as you like

// 1st one is junit

// 2nd testng
import org.junit.Test;
import javax.naming.ConfigurationException;
import java.io.FileNotFoundException;
import java.io.IOException;


public class TestRunner {

    Customer_BookingAPI booker= new Customer_BookingAPI();

    public TestRunner() throws FileNotFoundException {
    }


    @Test
    public void doLoginApi() throws ConfigurationException, IOException, org.apache.commons.configuration.ConfigurationException {
        //booker= new Customer_BookingAPI(); // you can use this line here or upper side
        booker.callCustomerBookingApi();
    }

    @Test
    public void doBooking() throws IOException {
        booker.callBookingGET();

    }

    @Test
    public void doBookingByID() throws IOException {
        booker.getBookerByID();

    }

    @Test
    public void doCreteBooking() throws IOException {
        booker.createBooking();

    }
   @Test
   public  void doUpdateCreate() throws IOException {
         booker.updateCreateBooking();
   }
   @Test
    public  void doPartialUpdateCreate() throws IOException {
        booker.partialUpdateCreateBooking();
    }
    @Test
    public  void doDelete() throws IOException {
        booker.deleteBooking();
    }
    @Test
    public  void doPing() throws IOException {
        booker.Ping();
    }


    //***************TESTNG****************

//    Customer_BookingAPI booker = new Customer_BookingAPI();
//
//    public TestRunner() throws FileNotFoundException {
//    }
//
//    @org.testng.annotations.Test(priority = 1)
//    public void doLoginApi() throws ConfigurationException, IOException, org.apache.commons.configuration.ConfigurationException {
//        //booker= new Customer_BookingAPI(); // you can use this line here or upper side
//        booker.callCustomerBookingApi();
//    }
//
//    @org.testng.annotations.Test(priority = 2)
//    public void doBooking() throws IOException {
//        booker.callBookingGET();
//
//    }
//
//    @org.testng.annotations.Test(priority = 3)
//    public void doBookingByID() throws IOException {
//        booker.getBookerByID();
//
//    }
//
//    @org.testng.annotations.Test(priority = 4)
//    public void doCreteBooking() throws IOException {
//        booker.createBooking();
//
//
//    }
//    @org.testng.annotations.Test(priority = 5)
//    public  void doUpdateCreate() throws IOException {
//        booker.updateCreateBooking();
//    }
//    @org.testng.annotations.Test(priority = 6)
//    public  void doPartialUpdateCreate() throws IOException {
//        booker.partialUpdateCreateBooking();
//    }
//    @org.testng.annotations.Test(priority = 7)
//    public  void doDelete() throws IOException {
//        booker.deleteBooking();
//    }
//    @org.testng.annotations.Test(priority = 8)
//    public  void doPing() throws IOException {
//        booker.Ping();
//    }

  }

