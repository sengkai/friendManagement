package com.friends;

/**
 * Created by seng kai on 3/22/2017.
 */
public enum SubscriptionEnum {

  SUBSCRIBE("SUB"),
  BLOCK("BLOCK")
  ;

  String subscriptionStatus;

  SubscriptionEnum(String subscriptionStatus){
    this.subscriptionStatus = subscriptionStatus;
  }

  public String getSubscriptionStatus() {
    return subscriptionStatus;
  }
}
