package com.placebo.sababot.processing.impl.actions;

import java.util.Date;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.api.objects.User;

import com.placebo.sababot.constants.ReactionConstants;
import com.placebo.sababot.constants.TelegramMessageType;
import com.placebo.sababot.core.impl.TelegramApiWrapper;
import com.placebo.sababot.models.CityWeather;
import com.placebo.sababot.models.Message;
import com.placebo.sababot.models.UpdateReceivedContext;
import com.placebo.sababot.models.Weather;
import com.placebo.sababot.processing.api.ExecuteAction;
import com.placebo.sababot.repository.dao.CityWeatherDAO;

public class ExecuteSabaForecast implements ExecuteAction {
  @Autowired
  private TelegramApiWrapper telegramApiWrapper;
  @Autowired
  private RestTemplate restTemplate;
  @Autowired
  private CityWeatherDAO cityWeatherDAO;
  @Value("#{weatherProps.cityId}")
  private String naplesId;
  @Value("#{weatherProps.units}")
  private String metricUnits;
  @Value("#{weatherProps.apiKey}")
  private String apiKey;
  @Value("#{weatherProps.apiUrl}")
  private String apiUrl;
  private static final Logger LOGGER = Logger.getLogger(ExecuteSabaForecast.class);

  @Override
  @Transactional(propagation=Propagation.REQUIRED)
  public <T extends UpdateReceivedContext> T execute(T updateContext) {
    org.telegram.telegrambots.api.objects.Message messageFrom = updateContext.getUpdate().getMessage();
    Long chatId = messageFrom.getChatId();
    User userFrom = messageFrom.getFrom();

    /** Get weather information **/
    String fullApiUrl = String.format(apiUrl, naplesId, metricUnits, apiKey);
    CityWeather cityWeather = null;

    /** If no CityWeather is available or CityWeather has not been cached in the last 1h, call REST API **/
    Long maxId = null;
    if((maxId = cityWeatherDAO.getMaxId()) == null || !cityWeatherDAO.isCached()) {
      try {
        cityWeather = restTemplate.getForObject(fullApiUrl, CityWeather.class);
        if(maxId == null)
          cityWeatherDAO.create(buildCityWeather(cityWeather, null));
        else
          cityWeatherDAO.update(buildCityWeather(cityWeather, maxId));
      } catch(Exception e) {
        LOGGER.error("Error while trying to get weather information.", e);
      }
    } else {
      cityWeather = cityWeatherDAO.findOne(maxId);
    }

    /** Build reply based on weather information **/
    if(cityWeather != null && cityWeather.getMain() != null
        && cityWeather.getMain().getTemp() != null) {
      String replyText = buildWeatherResponse(cityWeather);
      Message messageTo = new Message(TelegramMessageType.TEXT, chatId, replyText, userFrom);

      if(!updateContext.isActionPerformed())
        updateContext.setActionPerformed(telegramApiWrapper.send(messageTo));
    }

    return updateContext;
  }

  private String buildWeatherResponse(CityWeather cityWeather) {
    long truncDegrees = Math.round(cityWeather.getMain().getTemp());
    boolean isRainy = isRainy(cityWeather);
    boolean isSunnyDay = isDayTime(cityWeather) && !isCloudy(cityWeather) && !isRainy;

    return new StringBuilder()
        .append(String.format(ReactionConstants.SABA_FORECAST_REPLY, truncDegrees))
        .append(isRainy ? "\n" + ReactionConstants.SABA_FORECAST_PIOVE_ADD : "")
        .append(isSunnyDay ? "\n" + ReactionConstants.SABA_FORECAST_SOLE_ADD : "")
        .toString();
  }

  private CityWeather buildCityWeather(CityWeather cityWeather, Long maxId) {
    if(maxId != null)
      cityWeather.setIdCityWeather(maxId);
    cityWeather.setLastUpdateDate(new Date());

    if(cityWeather.getClouds() != null)
      cityWeather.getClouds().setCityWeather(cityWeather);
    if(cityWeather.getCoord() != null)
      cityWeather.getCoord().setCityWeather(cityWeather);
    if(cityWeather.getMain() != null)
      cityWeather.getMain().setCityWeather(cityWeather);
    if(cityWeather.getSys() != null)
      cityWeather.getSys().setCityWeather(cityWeather);
    if(cityWeather.getWind() != null)
      cityWeather.getWind().setCityWeather(cityWeather);

    if(CollectionUtils.isNotEmpty(cityWeather.getWeather())) {
      for(Weather weather : cityWeather.getWeather()) {
        weather.setCityWeather(cityWeather);
      }
    }
    return cityWeather;
  }

  private boolean isDayTime(CityWeather cityWeather) {
    if(cityWeather.getSys() != null 
        && cityWeather.getSys().getSunset() != null)
      return Long.valueOf(String.valueOf(new Date().getTime())
          .substring(0, String.valueOf(cityWeather.getSys().getSunset()).length())) 
          <= cityWeather.getSys().getSunset();

    return false;
  }

  private boolean isRainy(CityWeather cityWeather) {
    if(CollectionUtils.isNotEmpty(cityWeather.getWeather())
        && cityWeather.getWeather().get(0).getDescription() != null)
      return cityWeather.getWeather().get(0).getDescription().matches("((?i)\\brain\\b|\\bdrizzle\\b)");

    return false;
  }


  private boolean isCloudy(CityWeather cityWeather) {
    if(cityWeather.getClouds() != null && cityWeather.getClouds().getAll() != null)
      return cityWeather.getClouds().getAll() <= 35;

    return false;
  }

}
