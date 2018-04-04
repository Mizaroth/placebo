
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
  "speed",
  "deg"
})
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name="WIND")
@SequenceGenerator(name="WindSeqGen", sequenceName="WIND_SEQ", allocationSize=1)
public class Wind {
  @Id
  @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="WindSeqGen")
  @Column(name="ID_WIND")
  @JsonIgnore
  private long id;
  
  @JsonProperty("speed")
  @Column(name="SPEED")
  private Double speed;
  
  @JsonProperty("deg")
  @Column(name="DEG")
  private Integer deg;
  
  @JsonIgnore
  @Transient
  private Map<String, Object> additionalProperties = new HashMap<>();
  
  @OneToOne
  @JoinColumn(name="ID_CITY_WEATHER")
  @JsonIgnore
  private CityWeather cityWeather;

  @JsonProperty("speed")
  public Double getSpeed() {
    return speed;
  }

  @JsonProperty("speed")
  public void setSpeed(Double speed) {
    this.speed = speed;
  }

  @JsonProperty("deg")
  public Integer getDeg() {
    return deg;
  }

  @JsonProperty("deg")
  public void setDeg(Integer deg) {
    this.deg = deg;
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
