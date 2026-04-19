package by.vantsyferov.hotel.service.impl;

import by.vantsyferov.hotel.dto.HistogramEntry;
import by.vantsyferov.hotel.dto.HotelShortDto;
import by.vantsyferov.hotel.entity.Hotel;
import by.vantsyferov.hotel.exception.HotelNotFoundException;
import by.vantsyferov.hotel.mapper.HotelMapper;
import by.vantsyferov.hotel.repository.HotelRepository;
import by.vantsyferov.hotel.repository.HotelSpecifications;
import by.vantsyferov.hotel.service.HotelService;
import by.vantsyferov.hotel.utility.HistogramHelper;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HotelServiceImpl implements HotelService {


  private final HotelRepository hotelRepository;
  private final HotelMapper hotelMapper;
  private final HistogramHelper histogramHelper;

  public HotelServiceImpl(HotelRepository hotelRepository, HotelMapper hotelMapper, HistogramHelper histogramHelper) {
    this.hotelRepository = hotelRepository;
    this.hotelMapper = hotelMapper;
    this.histogramHelper = histogramHelper;
  }

  @Override
  public List<HotelShortDto> findAllShort() {
    List<Hotel> allHotels = hotelRepository.findAll();
    List<HotelShortDto> result = new ArrayList<>();

    for (Hotel hotel : allHotels) {
      result.add(hotelMapper.toShortDto(hotel));
    }
    return result;
  }

  @Override
  public Hotel findById(Long id) throws HotelNotFoundException {

    Optional<Hotel> optionalHotel = hotelRepository.findById(id);
    if (optionalHotel.isPresent()) {
      return optionalHotel.get();
    } else {
      throw new HotelNotFoundException("Hotel with id" + id + "not found");
    }
  }

  @Override
  public HotelShortDto createHotel(Hotel hotel) {
    Hotel saved = hotelRepository.save(hotel);
    return hotelMapper.toShortDto(saved);
  }

  @Override
  @Transactional
  public void addAmenities(Long id, List<String> amenities) {
    Hotel hotel = findById(id);
    hotel.getAmenities().addAll(amenities);
    hotelRepository.save(hotel);
  }

  @Override
  public List<HistogramEntry> getHistogram(String param) {
    List<Hotel> allHotels = hotelRepository.findAll();
    List<HistogramEntry> result = new ArrayList<>();

    for (Hotel hotel : allHotels) {
      if (param.equalsIgnoreCase("amenities")) {
        for (String a : hotel.getAmenities()) {
          histogramHelper.updateResultList(result, a);
        }
      } else {
        String value = histogramHelper.getValueByParam(hotel, param);
        histogramHelper.updateResultList(result, value);
      }
    }
    return result;
  }
  @Override
  public List<HotelShortDto> search(String name, String brand, String city, String country, String amenity) {
    Specification<Hotel> spec = Specification.where(HotelSpecifications.hasName(name))
            .and(HotelSpecifications.hasBrand(brand))
            .and(HotelSpecifications.hasCity(city))
            .and(HotelSpecifications.hasCountry(country))
            .and(HotelSpecifications.hasAmenity(amenity));
    List<Hotel> foundHotels = hotelRepository.findAll(spec);
    List<HotelShortDto> result = new ArrayList<>();
    for (Hotel h : foundHotels) {
      result.add(hotelMapper.toShortDto(h));
    }
    return result;
  }

}