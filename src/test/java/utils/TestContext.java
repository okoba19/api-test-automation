package utils;

import pojo.Booking;

public class TestContext {
    private static int bookingid;
    private static Booking booking;

    public static int getBookingId() {
        return bookingid;
    }

    public static void setBookingId(int id) {
        bookingid = id;
    }

    public static Booking getBooking() {
        return booking;
    }

    public static void setBooking(Booking booking) {
        TestContext.booking = booking;
    }
}