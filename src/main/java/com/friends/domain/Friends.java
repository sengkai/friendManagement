package com.friends.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by seng kai on 3/18/2017.
 */
@Entity
public class Friends implements Serializable {

  private static final long serialVersionUID = 2009639079946007107L;
  private Long id;
  private String ownerId;
  private String friendId;

  public Friends() {
  }

  public Friends(String ownerId, String friendId) {
    this.ownerId = ownerId;
    this.friendId = friendId;
  }

  @Id
  @GeneratedValue
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Column(nullable = false)
  public String getOwnerId() {
    return ownerId;
  }

  public void setOwnerId(String id) {
    this.ownerId = ownerId;
  }

  @Column(nullable = false)
  public String getFriendId() {
    return friendId;
  }

  public void setFriendId(String friendId) {
    this.friendId = friendId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Friends friends = (Friends) o;
    return Objects.equals(id, friends.id) &&
        Objects.equals(ownerId, friends.ownerId) &&
        Objects.equals(friendId, friends.friendId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, ownerId, friendId);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("id", id)
        .append("ownerId", ownerId)
        .append("friendId", friendId)
        .toString();
  }
}
