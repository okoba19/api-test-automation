import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import io.qameta.allure.restassured.AllureRestAssured;
import pojo.Booking;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TestBase {

    protected static final String BASE_URL = "https://restful-booker.herokuapp.com";
    protected static final String BASIC_AUTH_TOKEN = "YWRtaW46cGFzc3dvcmQxMjM=";

    @BeforeAll
    public static void setUp() {
        RestAssured.filters(new AllureRestAssured());
    }

    protected Booking getBookingById(int bookingId) {
        return given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .when()
                .get("/booking/{id}", bookingId).as(Booking.class);
    }

    protected void verifyBooking(final Booking actualBooking, final Booking expectedBooking) {
        assertThat(actualBooking.getFirstname(), equalTo(expectedBooking.getFirstname()));
        assertThat(actualBooking.getLastname(), equalTo(expectedBooking.getLastname()));
        assertThat(actualBooking.getTotalprice(), equalTo(expectedBooking.getTotalprice()));
        assertThat(actualBooking.isDepositpaid(), equalTo(expectedBooking.isDepositpaid()));
        assertThat(actualBooking.getCheckin(), equalTo(expectedBooking.getCheckin()));
        assertThat(actualBooking.getCheckout(), equalTo(expectedBooking.getCheckout()));
        assertThat(actualBooking.getCheckout(), equalTo(expectedBooking.getCheckout()));
    }
}
