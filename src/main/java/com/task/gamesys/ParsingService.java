package com.task.gamesys;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ParsingService {

  private static final Logger log = LoggerFactory.getLogger(ParsingService.class);
  private final ObjectMapper mapper;
  @Value("${app.headerName}")
  private String headerName;
  @Value("${app.header}")
  private String header;
  @Value("${app.jwtToken}")
  private String jwtToken;
  @Value("${app.url}")
  private String url;

  public ParsingService(
      @Qualifier("objectMapper") ObjectMapper mapper) {
    this.mapper = mapper;
  }


  public List<Tweet> getDataFromJson() {
    List<Tweet> data = new ArrayList<>();
    try {
      String jsonResult = getJsonFromApi();
      DataFromJson dataFromJson = mapper.readValue(jsonResult, DataFromJson.class);

      data = dataFromJson.getData();
    } catch (Exception e) {
      log.error("", e);
    }
    return data;
  }

  public String getJsonFromApi() {
    String resp = "";
    try {
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);
      headers.add(headerName, header + jwtToken);

      HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
      RestTemplate restTemplate = new RestTemplate();
      ResponseEntity<String> responseEntity =
          restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

      resp = responseEntity.getBody();
      return resp;
    } catch (Exception e) {
      log.error("", e);
    }
    return resp;
  }
}
