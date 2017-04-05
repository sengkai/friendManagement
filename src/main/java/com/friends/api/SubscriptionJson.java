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
    "requestor",
    "target"
})
public class SubscriptionJson implements Serializable {

  private static final long serialVersionUID = -4215765659988865697L;
  @JsonProperty("requestor")
  private String requestor;
  @JsonProperty("target")
  private String target;

  public SubscriptionJson() {
  }

  private SubscriptionJson(Builder builder) {
    setRequestor(builder.requestor);
    setTarget(builder.target);
  }

  @JsonProperty("requestor")
  public String getRequestor() {
    return requestor;
  }

  @JsonProperty("requestor")
  public void setRequestor(String requestor) {
    this.requestor = requestor;
  }

  @JsonProperty("target")
  public String getTarget() {
    return target;
  }

  @JsonProperty("target")
  public void setTarget(String target) {
    this.target = target;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SubscriptionJson that = (SubscriptionJson) o;
    return Objects.equals(requestor, that.requestor) &&
        Objects.equals(target, that.target);
  }

  @Override
  public int hashCode() {
    return Objects.hash(requestor, target);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("requestor", requestor)
        .append("target", target)
        .toString();
  }


  public static final class Builder {
    private String requestor;
    private String target;

    public Builder() {
    }

    public Builder requestor(String val) {
      requestor = val;
      return this;
    }

    public Builder target(String val) {
      target = val;
      return this;
    }

    public SubscriptionJson build() {
      return new SubscriptionJson(this);
    }
  }
}
