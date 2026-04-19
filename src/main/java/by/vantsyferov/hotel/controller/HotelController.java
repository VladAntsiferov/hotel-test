package by.vantsyferov.hotel.controller;

import by.vantsyferov.hotel.dto.HistogramEntry;
import by.vantsyferov.hotel.dto.HotelShortDto;
import by.vantsyferov.hotel.entity.Hotel;
import by.vantsyferov.hotel.service.HotelService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/property-view")
public class HotelController {

  private final HotelService hotelService;

  public HotelController(HotelService hotelService) {
    this.hotelService = hotelService;
  }

  @GetMapping("/hotels")
  public List<HotelShortDto> getAllHotels() {
    return hotelService.findAllShort();
  }

  @GetMapping("/hotels/{id}")
  public Hotel getHotelById(@PathVariable Long id) {
    return hotelService.findById(id);
  }

  @GetMapping("/search")
  public List<HotelShortDto> searchHotels(
          @RequestParam(required = false) String name,
          @RequestParam(required = false) String brand,
          @RequestParam(required = false) String city,
          @RequestParam(required = false) String country,
          @RequestParam(required = false) String amenity) {
    return hotelService.search(name, brand, city, country, amenity);
  }

  @PostMapping("/hotels")
  public HotelShortDto createHotel(@RequestBody Hotel hotel) {
    return hotelService.createHotel(hotel);
  }

  @PostMapping("/hotels/{id}/amenities")
  public void addAmenities(@PathVariable Long id, @RequestBody List<String> amenities) {
    hotelService.addAmenities(id, amenities);
  }

  @GetMapping("/histogram/{param}")
  public List<HistogramEntry> getHistogram(@PathVariable String param) {
    return hotelService.getHistogram(param);
  }
}
