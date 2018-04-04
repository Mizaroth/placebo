
package com.placebo.sababot.models;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
  "id",
  "main",
  "description",
  "icon"
})
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name="WEATHER")
@SequenceGenerator(name="WeatherSeqGen", sequenceName="WEATHER_SEQ", allocationSize=1)
public class Weather {
  @Id
  @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="WeatherSeqGen")
  @Column(name="ID_WEATHER")
  @JsonIgnore
  private long idWeather;
  
  @JsonProperty("id")
  @Column(name="ID")
  private Integer id;
  
  @JsonProperty("main")
  @Column(name="MAIN")
  private String main;
  
  @JsonProperty("description")
  @Column(name="DESCRIPTION")
  private String description;
  
  @JsonProperty("icon")
  @Column(name="ICON")
  private String icon;
  
  @JsonIgnore
  @Transient
  private Map<String, Object> additionalProperties = new HashMap<>();
  
  @ManyToOne
  @JoinColumn(name="ID_CITY_WEATHER")
  @JsonIgnore
  private CityWeather cityWeather;

  @JsonProperty("id")
  public Integer getId() {
    return id;
  }

  @JsonProperty("id")
  public void setId(Integer id) {
    this.id = id;
  }

  @JsonProperty("main")
  public String getMain() {
    return main;
  }

  @JsonProperty("main")
  public void setMain(String main) {
    this.main = main;
  }

  @JsonProperty("description")
  public String getDescription() {
    return description;
  }

  @JsonProperty("description")
  public void setDescription(String description) {
    this.description = description;
  }

  @JsonProperty("icon")
  public String getIcon() {
    return icon;
  }

  @JsonProperty("icon")
  public void setIcon(String icon) {
    this.icon = icon;
  }

  @JsonAnyGetter
  public Map<String, Object> getAdditionalProperties() {
    return this.additionalProperties;
  }

  @JsonAnySetter
  public void setAdditionalProperty(String name, Object value) {
    this.additionalProperties.put(name, value);
  }

  public long getIdWeather() {
    return idWeather;
  }

  public void setIdWeather(long idWeather) {
    this.idWeather = idWeather;
  }

  public CityWeather getCityWeather() {
    return cityWeather;
  }

  public void setCityWeather(CityWeather cityWeather) {
    this.cityWeather = cityWeather;
  }
}
