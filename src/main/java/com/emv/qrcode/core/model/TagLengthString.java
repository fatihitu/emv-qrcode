package com.emv.qrcode.core.model;

import org.apache.commons.lang3.StringUtils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TagLengthString implements SimpleTLV<String> {

  private static final long serialVersionUID = -6482977134879939277L;

  private String tag;

  private Integer length;

  private String value;

  public TagLengthString() {
    super();
  }
  
  public TagLengthString(final String value) {
    this.tag = value.substring(0, 2);
    this.length = Integer.valueOf(value.substring(2, 4));
    this.value = value.substring(4, 4 + length);
  }
  
  @Override
  public String toString() {

    if (StringUtils.isBlank(value)) {
      return StringUtils.EMPTY;
    }

    return String.format("%s%02d%s", tag, value.length(), value);
  }

}