package by.vantsyferov.hotel.utility;

import by.vantsyferov.hotel.dto.HistogramEntry;
import by.vantsyferov.hotel.entity.Hotel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HistogramHelper {
  public void updateResultList(List<HistogramEntry> list, String value) {
    if (value == null || value.isBlank()) {
      return;
    }

    for (HistogramEntry entry : list) {
      if (entry.getValue().equalsIgnoreCase(value)) {
        entry.increment();
        return;
      }
    }
    list.add(new HistogramEntry(value, 1L));
  }

  public String getValueByParam(Hotel hotel, String param) {
    return switch (param.toLowerCase()) {
      case "brand" -> hotel.getBrand();
      case "city" -> hotel.getAddress().getCity();
      case "country" -> hotel.getAddress().getCountry();
      default -> null;
    };
  }
}
