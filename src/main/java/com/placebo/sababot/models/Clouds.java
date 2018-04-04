
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
  "all"
})
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name="CLOUDS")
@SequenceGenerator(name="CloudsSeqGen", sequenceName="CLOUDS_SEQ", allocationSize=1)
public class Clouds {
  @Id
  @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CloudsSeqGen")
  @Column(name="ID_CLOUDS")
  @JsonIgnore
  private long id;
  
  @JsonProperty("all")
  @Column(name="CLOUD_PERCENTAGE")
  private Integer all;
  
  @JsonIgnore
  @Transient
  private Map<String, Object> additionalProperties = new HashMap<>();
  
  @OneToOne
  @JoinColumn(name="ID_CITY_WEATHER")
  @JsonIgnore
  private CityWeather cityWeather;

  @JsonProperty("all")
  public Integer getAll() {
    return all;
  }

  @JsonProperty("all")
  public void setAll(Integer all) {
    this.all = all;
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
