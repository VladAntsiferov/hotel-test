package by.vantsyferov.hotel.dto;

public class HistogramEntry {
  private String value;
  private Long count;

  public HistogramEntry(String value, Long count) {
    this.value = value;
    this.count = count;
  }

  public String getValue() {
    return value;
  }

  public Long getCount() {
    return count;
  }

  public void increment() {
    this.count++;
  }
}
