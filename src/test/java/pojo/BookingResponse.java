package pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingResponse {
    private int bookingid;
    private Booking booking;

    public BookingResponse () {}
}
