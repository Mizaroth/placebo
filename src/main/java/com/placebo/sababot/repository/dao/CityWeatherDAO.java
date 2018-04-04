package com.placebo.sababot.repository.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.placebo.sababot.models.CityWeather;
import com.placebo.sababot.repository.api.AbstractGenericDAO;

@Repository
@Transactional
public class CityWeatherDAO extends AbstractGenericDAO<CityWeather> {
  private String checkIsCachedCityWeatherQuery;
  public CityWeatherDAO() {
    super();
    setClazz(CityWeather.class);
  }

  public Long getMaxId() {
    CityWeather maxCityWeather =
        getCurrentSession()
        .createQuery("FROM CityWeather WHERE idCityWeather = (SELECT MAX(idCityWeather) FROM CityWeather)", CityWeather.class)
        .uniqueResult();
    return maxCityWeather != null ? maxCityWeather.getIdCityWeather() : null;
  }
  
  public Boolean isCached() {
    return (Boolean)getCurrentSession().getNamedNativeQuery(checkIsCachedCityWeatherQuery).uniqueResult();
  }

  public void setCheckIsCachedCityWeatherQuery(String checkIsCachedCityWeatherQuery) {
    this.checkIsCachedCityWeatherQuery = checkIsCachedCityWeatherQuery;
  }
}
