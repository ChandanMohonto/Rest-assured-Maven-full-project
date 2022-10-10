import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;

import javax.naming.ConfigurationException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;

    public class Customer_BookingAPI {


        public String token;

        Properties props = new Properties();
        FileInputStream files = new FileInputStream("./src/main/resources/config.properties");

        public Customer_BookingAPI() throws FileNotFoundException {
        }

        public void callCustomerBookingApi() throws ConfigurationException, IOException, org.apache.commons.configuration.ConfigurationException {


            props.load(files);
            RestAssured.baseURI = props.getProperty("baseURL");
            Response response =
                    given()
                            .contentType("application/json")
                            .body("{\n" +
                                    "    \"username\" : \"admin\",\n" +
                                    "    \"password\" : \"password123\"\n" +
                                    "}")
                            .when()
                            .post("/auth")
                            .then()
                            .assertThat().statusCode(200).extract().response();

            System.out.println(response.asString());
            JsonPath jsonPath = response.jsonPath();
            token = jsonPath.get("token");
            Utils.setEnvVariable("token", token);

//        JsonPath resOBJ= response.jsonPath();   // you can use this 4 line or other upper side 4 line
//        String token=resOBJ.get("token");
//        Utils.setEnvVariable("token",token);
//        System.out.println(token);


        }

        public void callBookingGET() throws IOException {

            props.load(files);
            RestAssured.baseURI = props.getProperty("baseURL");
            Response response =
                    given()
                            .contentType("application/json")
                            .header("Authorization", props.getProperty("token"))
                            .when()
                            .get("/booking")
                            .then().assertThat().statusCode(200).extract().response();


            System.out.println(response.asString());
        }

        public void getBookerByID() throws IOException {

            props.load(files);
            RestAssured.baseURI = props.getProperty("baseURL");
            Response response =
                    given()
                            .contentType("application/json")
                            .header("Authorization", props.getProperty("token"))
                            .when()
                            .get("/booking/100")
                            .then().assertThat().statusCode(200).extract().response();

//        System.out.println(response.asString());
//        JsonPath jsonObj = response.jsonPath();
//        String name = jsonObj.get("firstname");
//        Assert.assertEquals("Sally", name);
            JsonPath jsonObj = response.jsonPath();
            String name = jsonObj.get("firstname");
            Assert.assertEquals("Jayjay", name);
            System.out.println(response.asString());

        }


        public void createBooking() throws IOException {
            props.load(files);
            RestAssured.baseURI = props.getProperty("baseURL");
            Response response =
                    given()
                            .contentType("application/json")
                            .header("Authorization", props.getProperty("token"))
                            .body("{\n" +
                                    "    \"firstname\" : \"Jim\",\n" +
                                    "    \"lastname\" : \"Brown\",\n" +
                                    "    \"totalprice\" : 111,\n" +
                                    "    \"depositpaid\" : true,\n" +
                                    "    \"bookingdates\" : {\n" +
                                    "        \"checkin\" : \"2018-01-01\",\n" +
                                    "        \"checkout\" : \"2019-01-01\"\n" +
                                    "    },\n" +
                                    "    \"additionalneeds\" : \"Breakfast\"\n" +
                                    "}")
                            .when()
                            .post("/booking")
                            .then()
                            .log().all()
                            .assertThat().statusCode(200).extract().response();

            System.out.println(response.asString());

        }

        public void updateCreateBooking() throws IOException {
            props.load(files);
            RestAssured.baseURI = props.getProperty("baseURL");
            Response response =
                    given()
                            .contentType("application/json")
                            .header("Authorization", props.getProperty("token"))
                            .header("Cookie", props.getProperty("token"))
                           // .cookie(props.getProperty("token"))

                            .accept("application/json")
                            .body("{\n" +
                                    "    \"firstname\" : \"James\",\n" +
                                    "    \"lastname\" : \"Brown\",\n" +
                                    "    \"totalprice\" : 111,\n" +
                                    "    \"depositpaid\" : true,\n" +
                                    "    \"bookingdates\" : {\n" +
                                    "        \"checkin\" : \"2018-01-01\",\n" +
                                    "        \"checkout\" : \"2019-01-01\"\n" +
                                    "    },\n" +
                                    "    \"additionalneeds\" : \"Breakfast\"\n" +
                                    "}")

                            .when()
                            .put("/booking/1")
                            .then()
                            .log().all()
                            .log().cookies()
                            .assertThat().statusCode(200).extract().response();

            System.out.println(response.asString());
        }

        public void partialUpdateCreateBooking() throws IOException {
            props.load(files);
            RestAssured.baseURI = props.getProperty("baseURL");
            Response response =
                    given()
                            .contentType("application/json")
                            .accept("application/json")
                            .header("Authorization", props.getProperty("token"))
                            .header("Cookie", props.getProperty("token"))
                            .body("{\n" +
                                    "    \"firstname\" : \"James\",\n" +
                                    "    \"lastname\" : \"Brown\"\n" +
                                    "}")
                            .when()
                            .patch("/booking/1")
                            .then()
                            .log().all()
                            .assertThat().statusCode(200).extract().response();

            System.out.println(response.asString());
        }

        public void deleteBooking() throws IOException {
            props.load(files);
            RestAssured.baseURI = props.getProperty("baseURL");
            Response response =
                    given()
                            .contentType("application/json")
                            .header("Authorization", props.getProperty("token"))
                            .header("Cookie", props.getProperty("token"))
                            .when()
                            .delete("/booking/500")
                            .then()
                            .assertThat().statusCode(201).extract().response();

            System.out.println(response.asString());
        }

        public void Ping() throws IOException {
            props.load(files);
            RestAssured.baseURI = props.getProperty("baseURL");
            Response response =
                    given()
                            .contentType("application/json")
                            .header("Authorization", props.getProperty("token"))
                            .when()
                            .get("/ping")
                            .then()
                            .assertThat().statusCode(201).extract().response();

            System.out.println(response.asString());
        }
    }

