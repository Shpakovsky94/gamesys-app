package com.task.gamesys;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DataFromJson {

  @JsonProperty("data")
  private List<Tweet> data;

  public DataFromJson() {
  }

  public DataFromJson(List<Tweet> data) {
    this.data = data;
  }

  public List<Tweet> getData() {
    return data;
  }

  public void setData(List<Tweet> data) {
    this.data = data;
  }
}
