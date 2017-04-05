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

/**
 * Created by seng kai on 3/18/2017.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "friends"
})
public class FriendsJson implements Serializable {

  private final static long serialVersionUID = -1189264294506045303L;
  @JsonProperty("friends")
  public List<String> friends = new ArrayList<String>();
  @JsonProperty("email")
  public String email;

  public FriendsJson() {
  }

  public FriendsJson(List<String> friends) {
    this.friends = friends;
  }

  public FriendsJson(String email) {
    this.email = email;
  }

  private FriendsJson(Builder builder) {
    setFriends(builder.friends);
    setEmail(builder.email);
  }

  @JsonProperty("friends")
  public List<String> getFriends() {
    return friends;
  }

  @JsonProperty("friends")
  public void setFriends(List<String> friends) {
    this.friends = friends;
  }

  @JsonProperty("email")
  public String getEmail() {
    return email;
  }

  @JsonProperty("email")
  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FriendsJson that = (FriendsJson) o;
    return Objects.equals(friends, that.friends) &&
        Objects.equals(email, that.email);
  }

  @Override
  public int hashCode() {
    return Objects.hash(friends, email);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("friends", friends)
        .append("email", email)
        .toString();
  }

  public static final class Builder {
    private List<String> friends;
    private String email;

    public Builder() {
    }

    public Builder friends(List<String> val) {
      friends = val;
      return this;
    }

    public Builder email(String val) {
      email = val;
      return this;
    }

    public FriendsJson build() {
      return new FriendsJson(this);
    }
  }
}
