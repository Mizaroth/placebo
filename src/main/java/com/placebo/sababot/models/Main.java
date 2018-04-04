
package com.placebo.sababot.models;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
  "temp",
  "pressure",
  "humidity",
  "temp_min",
  "temp_max"
})
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name="MAIN")
@SequenceGenerator(name="MainSeqGen", sequenceName="MAIN_SEQ", allocationSize=1)
public class Main {
  @Id
  @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MainSeqGen")
  @Column(name="ID_MAIN")
  @JsonIgnore
  private long id;
  
  @JsonProperty("temp")
  @Column(name="TEMP")
  private Double temp;
  
  @JsonProperty("pressure")
  @Column(name="PRESSURE")
  private Integer pressure;
  
  @JsonProperty("humidity")
  @Column(name="HUMIDITY")
  private Integer humidity;
  
  @JsonProperty("temp_min")
  @Column(name="TEMP_MIN")
  private Double tempMin;
  
  @JsonProperty("temp_max")
  @Column(name="TEMP_MAX")
  private Double tempMax;
  
  @JsonIgnore
  @Transient
  private Map<String, Object> additionalProperties = new HashMap<>();
  
  @OneToOne
  @JoinColumn(name="ID_CITY_WEATHER")
  @JsonIgnore
  private CityWeather cityWeather;
  
  @JsonProperty("temp")
  public Double getTemp() {
    return temp;
  }

  @JsonProperty("temp")
  public void setTemp(Double temp) {
    this.temp = temp;
  }

  @JsonProperty("pressure")
  public Integer getPressure() {
    return pressure;
  }

  @JsonProperty("pressure")
  public void setPressure(Integer pressure) {
    this.pressure = pressure;
  }

  @JsonProperty("humidity")
  public Integer getHumidity() {
    return humidity;
  }

  @JsonProperty("humidity")
  public void setHumidity(Integer humidity) {
    this.humidity = humidity;
  }

  @JsonProperty("temp_min")
  public Double getTempMin() {
    return tempMin;
  }

  @JsonProperty("temp_min")
  public void setTempMin(Double tempMin) {
    this.tempMin = tempMin;
  }

  @JsonProperty("temp_max")
  public Double getTempMax() {
    return tempMax;
  }

  @JsonProperty("temp_max")
  public void setTempMax(Double tempMax) {
    this.tempMax = tempMax;
  }

  @JsonAnyGetter
  public Map<String, Object> getAdditionalProperties() {
    return this.additionalProperties;
  }

  @JsonAnySetter
  public void setAdditionalProperty(String name, Object value) {
    this.additionalProperties.put(name, value);
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public CityWeather getCityWeather() {
    return cityWeather;
  }

  public void setCityWeather(CityWeather cityWeather) {
    this.cityWeather = cityWeather;
  }
}
