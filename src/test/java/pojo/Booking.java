package pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Booking {
    private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;
    private BookingDates bookingdates;
    private String additionalneeds;

    public Booking() {}

    public Booking(String firstname, String lastname, int totalprice, boolean depositpaid,
                   String checkin, String checkout, String additionalneeds) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.totalprice = totalprice;
        this.depositpaid = depositpaid;
        this.bookingdates = new BookingDates(checkin, checkout);
        this.additionalneeds = additionalneeds;
    }

    public String getCheckin() {
       return getBookingdates().getCheckin();
    }

    public String getCheckout() {
        return getBookingdates().getCheckout();
    }
}
