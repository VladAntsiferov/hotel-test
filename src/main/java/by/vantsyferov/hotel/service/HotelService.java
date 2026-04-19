package by.vantsyferov.hotel.service;

import by.vantsyferov.hotel.dto.HistogramEntry;
import by.vantsyferov.hotel.dto.HotelShortDto;
import by.vantsyferov.hotel.entity.Hotel;
import by.vantsyferov.hotel.exception.HotelNotFoundException;

import java.util.List;

public interface HotelService {
  List<HotelShortDto> findAllShort();
  Hotel findById(Long id) throws HotelNotFoundException;
  HotelShortDto createHotel(Hotel hotel);
  void addAmenities(Long id, List<String> amenities);
  List<HistogramEntry> getHistogram(String param);
  List<HotelShortDto> search(String name, String brand, String city, String country, String amenity);
}
