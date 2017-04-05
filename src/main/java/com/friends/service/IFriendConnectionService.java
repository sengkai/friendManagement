package com.friends.service;

import com.friends.api.FriendsJson;
import com.friends.api.FriendsNotificationJson;
import com.friends.api.JsonResponse;
import com.friends.api.SubscriptionJson;

/**
 * Created by seng kai on 3/18/2017.
 */
public interface IFriendConnectionService {

  JsonResponse createFriendsConnection(FriendsJson friendsJson);

  JsonResponse getFriendList(String accountId);

  JsonResponse getCommonFriendList(FriendsJson friendsJson);

  JsonResponse subscribeFriendUpdates(SubscriptionJson subscriptionJson);

  JsonResponse blockFriendUpdates(SubscriptionJson subscriptionJson);

  JsonResponse getAllowNotificationList(FriendsNotificationJson friendsNotificationJson);
}
