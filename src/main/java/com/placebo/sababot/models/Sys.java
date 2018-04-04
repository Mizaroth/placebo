
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
  "type",
  "id",
  "message",
  "country",
  "sunrise",
  "sunset"
})
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name="SYS")
@SequenceGenerator(name="SysGenSeq", sequenceName="SYS_SEQ", allocationSize=1)
public class Sys {
  @Id
  @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SysGenSeq")
  @Column(name="ID_SYS")
  @JsonIgnore
  private long idSys;
  
  @JsonProperty("type")
  @Column(name="TYPE")
  private Integer type;
  
  @JsonProperty("id")
  @Column(name="ID")
  private Integer id;
  
  @JsonProperty("message")
  @Column(name="MESSAGE")
  private Double message;
  
  @JsonProperty("country")
  @Column(name="COUNTRY")
  private String country;
  
  @JsonProperty("sunrise")
  @Column(name="SUNRISE")
  private Long sunrise;
  
  @JsonProperty("sunset")
  @Column(name="SUNSET")
  private Long sunset;
  
  @JsonIgnore
  @Transient
  private Map<String, Object> additionalProperties = new HashMap<>();
  
  @OneToOne
  @JoinColumn(name="ID_CITY_WEATHER")
  @JsonIgnore
  private CityWeather cityWeather;

  @JsonProperty("type")
  public Integer getType() {
    return type;
  }

  @JsonProperty("type")
  public void setType(Integer type) {
    this.type = type;
  }

  @JsonProperty("id")
  public Integer getId() {
    return id;
  }

  @JsonProperty("id")
  public void setId(Integer id) {
    this.id = id;
  }

  @JsonProperty("message")
  public Double getMessage() {
    return message;
  }

  @JsonProperty("message")
  public void setMessage(Double message) {
    this.message = message;
  }

  @JsonProperty("country")
  public String getCountry() {
    return country;
  }

  @JsonProperty("country")
  public void setCountry(String country) {
    this.country = country;
  }

  @JsonProperty("sunrise")
  public Long getSunrise() {
    return sunrise;
  }

  @JsonProperty("sunrise")
  public void setSunrise(Long sunrise) {
    this.sunrise = sunrise;
  }

  @JsonProperty("sunset")
  public Long getSunset() {
    return sunset;
  }

  @JsonProperty("sunset")
  public void setSunset(Long sunset) {
    this.sunset = sunset;
  }

  @JsonAnyGetter
  public Map<String, Object> getAdditionalProperties() {
    return this.additionalProperties;
  }

  @JsonAnySetter
  public void setAdditionalProperty(String name, Object value) {
    this.additionalProperties.put(name, value);
  }

  public long getIdSys() {
    return idSys;
  }

  public void setIdSys(long idSys) {
    this.idSys = idSys;
  }

  public CityWeather getCityWeather() {
    return cityWeather;
  }

  public void setCityWeather(CityWeather cityWeather) {
    this.cityWeather = cityWeather;
  }
}
