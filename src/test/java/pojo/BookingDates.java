package pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingDates {

    private String checkin;
    private String checkout;

    public BookingDates(String checkin, String checkout) {
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public BookingDates() {}

}
