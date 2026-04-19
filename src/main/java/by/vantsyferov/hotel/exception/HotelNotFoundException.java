package by.vantsyferov.hotel.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class HotelNotFoundException extends RuntimeException {

  public HotelNotFoundException() {
    super();
  }

  public HotelNotFoundException(String message) {
    super(message);
  }

  public HotelNotFoundException(Throwable cause) {
    super(cause);
  }

  public HotelNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
