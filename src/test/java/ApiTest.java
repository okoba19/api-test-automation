import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import pojo.Booking;
import pojo.BookingResponse;
import utils.BookingRequestConverter;
import utils.TestContext;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Slf4j
public class ApiTest extends TestBase {

    @Test
    @Description("Verify Booker service is working")
    public void healthCheck() {
        given().
                baseUri(BASE_URL).
                when().
                get("/ping").
                then().
                assertThat().
                statusCode(201);
    }

    @Test
    @Description("Verify all existing Bookings id are not null")
    public void getExistingBookingsId() {
        given().
                baseUri(BASE_URL).
                when().
                get("/booking").
                then().log().body().
                assertThat().statusCode(200).
                body("objects.bookingid", notNullValue());
    }

    @SneakyThrows
    @Test
    @Description("Verify new Booking creation")
    public void createBooking() {
        Booking requestBody = new Booking("Dwight", "Schrute", 300, true,
                "2024-03-21", "2024-06-01", "Massage");

        final String requestBodyJson = BookingRequestConverter.convertToJsonString(requestBody);

        Response response = given().
                contentType(ContentType.JSON).
                baseUri(BASE_URL)
                .body(requestBodyJson)
                .when()
                .post("/booking").
                then().assertThat().statusCode(200)
                .extract().response();

        BookingResponse bookingResponse = response.as(BookingResponse.class);
        Booking createdBooking = bookingResponse.getBooking();

        TestContext.setBookingId(bookingResponse.getBookingid());
        TestContext.setBooking(createdBooking);

      verifyBooking(createdBooking, requestBody);
    }

    @Test
    @Description("Retrieve the booking by ID")
    @Feature("Booking")
    public void retrieveBookingById() {
        int bookingid = TestContext.getBookingId();
        Booking expectedBooking = TestContext.getBooking();

        Response response = given()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URL)
                .when()
                .get("/booking/{id}", bookingid)
                .then()
                .assertThat()
                .statusCode(200)
                .extract().response();

        Booking retrievedBooking = response.as(Booking.class);

        verifyBooking(retrievedBooking, expectedBooking);
    }

    @Test
    @Description("Verify Booking can be updated using bookingid")
    public void updateBookingById() {
        int bookingid = TestContext.getBookingId();

        Booking requestBody = new Booking("Jim", "Halpert", 120, false,
                "2024-03-21", "2024-03-24", "Battlestar Galactica");

        given().header("Authorization", "Basic " + BASIC_AUTH_TOKEN).
                baseUri(BASE_URL).
                contentType(ContentType.JSON).
                body(requestBody).
                when().
                put("/booking/{id}", bookingid).
                then().log().body().
                assertThat().
                statusCode(200)
                .extract().response().prettyPrint();

        Booking updatedBooking = getBookingById(bookingid);

        verifyBooking(updatedBooking, requestBody);
    }

    @Test
    @Description("Verify Booking can be deleted using bookingid")
    public void deleteBookingById() {
        int bookingid = TestContext.getBookingId();

        given().header("Authorization", "Basic " + BASIC_AUTH_TOKEN).
                baseUri(BASE_URL).
                contentType(ContentType.JSON).
                when().
                delete("/booking/{id}", bookingid).
                then().log().body().
                assertThat().
                statusCode(201)
                .extract().response().prettyPrint();
    }

}
