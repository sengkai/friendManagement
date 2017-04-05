package com.friends.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "sender",
    "text"
})
public class FriendsNotificationJson implements Serializable {

  private static final long serialVersionUID = -4215765659988865697L;
  @JsonProperty("sender")
  private String sender;
  @JsonProperty("text")
  private String text;

  public FriendsNotificationJson() {
  }

  private FriendsNotificationJson(Builder builder) {
    setSender(builder.sender);
    setText(builder.text);
  }

  @JsonProperty("sender")
  public String getSender() {
    return sender;
  }

  @JsonProperty("sender")
  public void setSender(String sender) {
    this.sender = sender;
  }

  @JsonProperty("text")
  public String getText() {
    return text;
  }

  @JsonProperty("text")
  public void setText(String text) {
    this.text = text;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FriendsNotificationJson that = (FriendsNotificationJson) o;
    return Objects.equals(sender, that.sender) &&
        Objects.equals(text, that.text);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sender, text);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("sender", sender)
        .append("text", text)
        .toString();
  }


  public static final class Builder {
    private String sender;
    private String text;

    public Builder() {
    }

    public Builder sender(String val) {
      sender = val;
      return this;
    }

    public Builder text(String val) {
      text = val;
      return this;
    }

    public FriendsNotificationJson build() {
      return new FriendsNotificationJson(this);
    }
  }
}
