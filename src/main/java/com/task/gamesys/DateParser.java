package com.task.gamesys;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateParser {

  public static LocalDateTime convertStringToLocalDateTime(final String str) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    return LocalDateTime.parse(str, formatter);
  }
}
