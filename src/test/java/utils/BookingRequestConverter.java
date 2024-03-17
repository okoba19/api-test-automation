package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import pojo.Booking;

public class BookingRequestConverter {
    private static final ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    public static String convertToJsonString(Booking requestBody) throws JsonProcessingException {
            return objectMapper.writeValueAsString(requestBody);
    }
}
