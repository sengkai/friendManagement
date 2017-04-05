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
public class SubscribeFriendNews implements Serializable {

  private static final long serialVersionUID = 1513819381743977949L;
  private Long id;
  private String ownerId;
  private String friendId;
  private String subscribeBlock;

  public SubscribeFriendNews() {
  }

  public SubscribeFriendNews(String ownerId, String friendId, String subscribeBlock) {
    this.ownerId = ownerId;
    this.friendId = friendId;
    this.subscribeBlock = subscribeBlock;
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

  public void setOwnerId(String ownerId) {
    this.ownerId = ownerId;
  }

  @Column(nullable = false)
  public String getFriendId() {
    return friendId;
  }

  public void setFriendId(String friendId) {
    this.friendId = friendId;
  }

  @Column(nullable = false)
  public String getSubscribeBlock() {
    return subscribeBlock;
  }

  public void setSubscribeBlock(String subscribeBlock) {
    this.subscribeBlock = subscribeBlock;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SubscribeFriendNews that = (SubscribeFriendNews) o;
    return Objects.equals(id, that.id) &&
        Objects.equals(ownerId, that.ownerId) &&
        Objects.equals(friendId, that.friendId) &&
        Objects.equals(subscribeBlock, that.subscribeBlock);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, ownerId, friendId, subscribeBlock);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("id", id)
        .append("ownerId", ownerId)
        .append("friendId", friendId)
        .append("subscribeBlock", subscribeBlock)
        .toString();
  }
}
