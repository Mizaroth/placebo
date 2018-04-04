
package com.placebo.sababot.models;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@NamedNativeQueries({
  @NamedNativeQuery(
    name="checkIsCachedCityWeatherQuery",
    query="SELECT EXISTS (SELECT 1 FROM CITY_WEATHER WHERE LAST_UPDATE_DATE >= (NOW() - INTERVAL '1 HOUR'));"
  )
})
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
  "coord",
  "weather",
  "base",
  "main",
  "visibility",
  "wind",
  "clouds",
  "dt",
  "sys",
  "id",
  "name",
  "cod"
})
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name="CITY_WEATHER")
@SequenceGenerator(name="CityWeatherSeqGen", sequenceName="CITY_WEATHER_SEQ", allocationSize=1)
public class CityWeather {
  @Id
  @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CityWeatherSeqGen")
  @Column(name="ID_CITY_WEATHER")
  @JsonIgnore
  private long idCityWeather;
  
  @JsonIgnore
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name="LAST_UPDATE_DATE")
  private Date lastUpdateDate;
  
  @JsonProperty("coord")
  @OneToOne(cascade=CascadeType.ALL, mappedBy="cityWeather", orphanRemoval=true)
  private Coord coord;
  
  @JsonProperty("weather")
  @OneToMany(cascade=CascadeType.ALL, mappedBy="cityWeather", orphanRemoval=true)
  private List<Weather> weather = null;
  
  @JsonProperty("base")
  @Column(name="BASE")
  private String base;
  
  @JsonProperty("main")
  @OneToOne(cascade=CascadeType.ALL, mappedBy="cityWeather", orphanRemoval=true)
  private Main main;
  
  @JsonProperty("visibility")
  @Column(name="VISIBILITY")
  private Integer visibility;
  
  @JsonProperty("wind")
  @OneToOne(cascade=CascadeType.ALL, mappedBy="cityWeather", orphanRemoval=true)
  private Wind wind;
  
  @JsonProperty("clouds")
  @OneToOne(cascade=CascadeType.ALL, mappedBy="cityWeather", orphanRemoval=true)
  private Clouds clouds;
  
  @JsonProperty("dt")
  @Column(name="DT")
  private Integer dt;
  
  @JsonProperty("sys")
  @OneToOne(cascade=CascadeType.ALL, mappedBy="cityWeather", orphanRemoval=true)
  private Sys sys;
  
  @JsonProperty("id")
  @Column(name="ID")
  private Integer id;
  
  @JsonProperty("name")
  @Column(name="NAME")
  private String name;
  
  @JsonProperty("cod")
  @Column(name="COD")
  private Integer cod;
  
  @JsonIgnore
  @Transient
  private Map<String, Object> additionalProperties = new HashMap<>();

  @JsonProperty("coord")
  public Coord getCoord() {
    return coord;
  }

  @JsonProperty("coord")
  public void setCoord(Coord coord) {
    this.coord = coord;
  }

  @JsonProperty("weather")
  public List<Weather> getWeather() {
    return weather;
  }

  @JsonProperty("weather")
  public void setWeather(List<Weather> weather) {
    this.weather = weather;
  }

  @JsonProperty("base")
  public String getBase() {
    return base;
  }

  @JsonProperty("base")
  public void setBase(String base) {
    this.base = base;
  }

  @JsonProperty("main")
  public Main getMain() {
    return main;
  }

  @JsonProperty("main")
  public void setMain(Main main) {
    this.main = main;
  }

  @JsonProperty("visibility")
  public Integer getVisibility() {
    return visibility;
  }

  @JsonProperty("visibility")
  public void setVisibility(Integer visibility) {
    this.visibility = visibility;
  }

  @JsonProperty("wind")
  public Wind getWind() {
    return wind;
  }

  @JsonProperty("wind")
  public void setWind(Wind wind) {
    this.wind = wind;
  }

  @JsonProperty("clouds")
  public Clouds getClouds() {
    return clouds;
  }

  @JsonProperty("clouds")
  public void setClouds(Clouds clouds) {
    this.clouds = clouds;
  }

  @JsonProperty("dt")
  public Integer getDt() {
    return dt;
  }

  @JsonProperty("dt")
  public void setDt(Integer dt) {
    this.dt = dt;
  }

  @JsonProperty("sys")
  public Sys getSys() {
    return sys;
  }

  @JsonProperty("sys")
  public void setSys(Sys sys) {
    this.sys = sys;
  }

  @JsonProperty("id")
  public Integer getId() {
    return id;
  }

  @JsonProperty("id")
  public void setId(Integer id) {
    this.id = id;
  }

  @JsonProperty("name")
  public String getName() {
    return name;
  }

  @JsonProperty("name")
  public void setName(String name) {
    this.name = name;
  }

  @JsonProperty("cod")
  public Integer getCod() {
    return cod;
  }

  @JsonProperty("cod")
  public void setCod(Integer cod) {
    this.cod = cod;
  }

  @JsonAnyGetter
  public Map<String, Object> getAdditionalProperties() {
    return this.additionalProperties;
  }

  @JsonAnySetter
  public void setAdditionalProperty(String name, Object value) {
    this.additionalProperties.put(name, value);
  }
  
  public long getIdCityWeather() {
    return idCityWeather;
  }

  public void setIdCityWeather(long idCityWeather) {
    this.idCityWeather = idCityWeather;
  }

  public Date getLastUpdateDate() {
    return lastUpdateDate;
  }

  public void setLastUpdateDate(Date lastUpdateDate) {
    this.lastUpdateDate = lastUpdateDate;
  }
}
