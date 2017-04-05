package com.friends.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "success",
    "friends",
    "count",
    "statusMessage"
})
public class JsonResponse implements Serializable {

  private final static long serialVersionUID = 6533355981690220684L;
  @JsonProperty("success")
  private Boolean success;
  @JsonProperty("friends")
  private List<String> friends = new ArrayList<String>();
  @JsonProperty("recipients")
  private List<String> recipients = new ArrayList<String>();
  @JsonProperty("count")
  private Integer count;
  @JsonProperty("statusMessage")
  private String statusMessage;

  public JsonResponse() {

  }

  private JsonResponse(Builder builder) {
    setSuccess(builder.success);
    setFriends(builder.friends);
    setRecipients(builder.recipients);
    setCount(builder.count);
    setStatusMessage(builder.statusMessage);
  }

  @JsonProperty("success")
  public Boolean getSuccess() {
    return success;
  }

  @JsonProperty("success")
  public void setSuccess(Boolean success) {
    this.success = success;
  }

  @JsonProperty("friends")
  public List<String> getFriends() {
    return friends;
  }

  @JsonProperty("friends")
  public void setFriends(List<String> friends) {
    this.friends = friends;
  }

  @JsonProperty("recipients")
  public List<String> getRecipients() {
    return recipients;
  }

  @JsonProperty("recipients")
  public void setRecipients(List<String> recipients) {
    this.recipients = recipients;
  }

  @JsonProperty("count")
  public Integer getCount() {
    return count;
  }

  @JsonProperty("count")
  public void setCount(Integer count) {
    this.count = count;
  }

  @JsonProperty("statusMessage")
  public String getStatusMessage() {
    return statusMessage;
  }

  @JsonProperty("statusMessage")
  public void setStatusMessage(String statusMessage) {
    this.statusMessage = statusMessage;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    JsonResponse that = (JsonResponse) o;
    return Objects.equals(success, that.success) &&
        Objects.equals(friends, that.friends) &&
        Objects.equals(recipients, that.recipients) &&
        Objects.equals(count, that.count) &&
        Objects.equals(statusMessage, that.statusMessage);
  }

  @Override
  public int hashCode() {
    return Objects.hash(success, friends, recipients, count, statusMessage);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("success", success)
        .append("friends", friends)
        .append("recipients", recipients)
        .append("count", count)
        .append("statusMessage", statusMessage)
        .toString();
  }


  public static final class Builder {
    private Boolean success;
    private List<String> friends;
    private List<String> recipients;
    private Integer count;
    private String statusMessage;

    public Builder() {
    }

    public Builder success(Boolean val) {
      success = val;
      return this;
    }

    public Builder friends(List<String> val) {
      friends = val;
      return this;
    }

    public Builder recipients(List<String> val) {
      recipients = val;
      return this;
    }

    public Builder count(Integer val) {
      count = val;
      return this;
    }

    public Builder statusMessage(String val){
      statusMessage = val;
      return this;
    }

    public JsonResponse build() {
      return new JsonResponse(this);
    }
  }
}