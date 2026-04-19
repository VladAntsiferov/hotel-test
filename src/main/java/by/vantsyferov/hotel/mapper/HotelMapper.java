package by.vantsyferov.hotel.mapper;

import by.vantsyferov.hotel.dto.HotelShortDto;
import by.vantsyferov.hotel.entity.Address;
import by.vantsyferov.hotel.entity.Hotel;
import org.springframework.stereotype.Component;

@Component
public class HotelMapper {

  public HotelShortDto toShortDto(Hotel hotel) {
    if (hotel == null) {
      return null;
    }
    Address address = hotel.getAddress();
    String fullAddress = "";
    if (address != null) {
      fullAddress = address.getHouseNumber() + " " +
              address.getStreet() + ", " +
              address.getCity() + ", " +
              address.getPostcode() + ", " +
              address.getCountry();
    }
    String phone = (hotel.getContacts() != null) ? hotel.getContacts().getPhone() : "";
    return new HotelShortDto(hotel.getId(),
            hotel.getName(),
            hotel.getDescription(),
            fullAddress,
            phone);
  }

}