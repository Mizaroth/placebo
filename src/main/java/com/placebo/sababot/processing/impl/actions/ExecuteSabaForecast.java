package com.placebo.sababot.processing.impl.actions;

import java.util.Date;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.api.objects.User;

import com.placebo.sababot.constants.ReactionConstants;
import com.placebo.sababot.constants.TelegramMessageType;
import com.placebo.sababot.core.impl.TelegramApiWrapper;
import com.placebo.sababot.models.CityWeather;
import com.placebo.sababot.models.Message;
import com.placebo.sababot.models.UpdateReceivedContext;
import com.placebo.sababot.processing.api.ExecuteAction;

public class ExecuteSabaForecast implements ExecuteAction {
  @Autowired
  private TelegramApiWrapper telegramApiWrapper;
  @Autowired
  private RestTemplate restTemplate;
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
  public <T extends UpdateReceivedContext> T execute(T updateContext) {
    org.telegram.telegrambots.api.objects.Message messageFrom = updateContext.getUpdate().getMessage();
    Long chatId = messageFrom.getChatId();
    User userFrom = messageFrom.getFrom();

    /** Get weather information **/
    String fullApiUrl = String.format(apiUrl, naplesId, metricUnits, apiKey);
    CityWeather cityWeather = null;
    try {
      cityWeather = restTemplate.getForObject(fullApiUrl, CityWeather.class);
    } catch(Exception e) {
      LOGGER.error("Error while trying to get weather information.", e);
    }

    /** Build reply based on weather information **/
    if(cityWeather != null && cityWeather.getMain() != null
        && cityWeather.getMain().getTemp() != null) {
      long truncDegrees = Math.round(cityWeather.getMain().getTemp());
      boolean isRainy = isRainy(cityWeather);
      boolean isDayTime = isDayTime(cityWeather);

      String replyText = new StringBuilder()
          .append(String.format(ReactionConstants.SABA_FORECAST_REPLY, truncDegrees))
          .append(isRainy ? "\n" + ReactionConstants.SABA_FORECAST_PIOVE_ADD : "")
          .append(!isRainy && isDayTime ? "\n" + ReactionConstants.SABA_FORECAST_SOLE_ADD : "")
          .toString();

      Message messageTo = new Message(TelegramMessageType.TEXT, chatId, replyText, userFrom);

      if(!updateContext.isActionPerformed())
        updateContext.setActionPerformed(telegramApiWrapper.send(messageTo));
    }

    return updateContext;
  }

  private boolean isDayTime(CityWeather cityWeather) {
    if(cityWeather.getSys() != null 
        && cityWeather.getSys().getSunset() != null)
    return new Date().getTime() <= cityWeather.getSys().getSunset();
    
    return false;
  }

  private boolean isRainy(CityWeather cityWeather) {
    if(CollectionUtils.isNotEmpty(cityWeather.getWeather())
        && cityWeather.getWeather().get(0).getDescription() != null)
      return cityWeather.getWeather().get(0).getDescription().matches("((?i)\\brain\\b|\\bdrizzle\\b)");
    
    return false;
  }

}
