package com.task.gamesys.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

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
