package com.friends.service;

import com.friends.SubscriptionEnum;
import com.friends.api.FriendsJson;
import com.friends.api.FriendsNotificationJson;
import com.friends.api.JsonResponse;
import com.friends.api.SubscriptionJson;
import com.friends.domain.Friends;
import com.friends.domain.ProfileAccount;
import com.friends.domain.SubscribeFriendNews;
import com.friends.repo.FriendsRepository;
import com.friends.repo.ProfileAccountRepository;
import com.friends.repo.SubscribeFriendNewsRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by seng kai on 3/18/2017.
 */
@Service
public class FriendConnectionService implements IFriendConnectionService {
  private static final Logger logger = LogManager.getLogger(FriendConnectionService.class);
  @Autowired
  private MessageSource messageSource;

  @Autowired
  private ProfileAccountRepository profileAccountRepository;
  @Autowired
  private FriendsRepository friendsRepository;
  @Autowired
  private SubscribeFriendNewsRepository subscribeFriendNewsRepository;

  @Override
  public JsonResponse createFriendsConnection(FriendsJson friendsJson) {

    //Check for any duplicates
    List<String>
        listWithoutDuplicates =
        friendsJson.getFriends().parallelStream().distinct().collect(
            Collectors.toList());

    //Check if the account exist if does not exist create first
    listWithoutDuplicates.stream().forEach(s -> {
      ProfileAccount profileAccount = null;
      profileAccount = profileAccountRepository.findOne(s);

      if (profileAccount == null) {
        profileAccount = new ProfileAccount(s);
        profileAccountRepository.save(profileAccount);
      }
    });

    boolean
        isConnectionExist =
        (friendsRepository
            .isFriendConnectionExist(listWithoutDuplicates.get(0), listWithoutDuplicates.get(1))
            != null ? true : false);

    if (!isConnectionExist) {
      Friends friends = new Friends(listWithoutDuplicates.get(0), listWithoutDuplicates.get(1));
      friendsRepository.save(friends);

      return new JsonResponse.Builder().success(true).build();
    }
    return new JsonResponse.Builder()
        .statusMessage(messageSource.getMessage("connection.exist", null, null)).success(false)
        .build();
  }

  @Override
  public JsonResponse getFriendList(String accountId) {
    List<String> friendList = friendsRepository.getFriendList(accountId);
    if (friendList.isEmpty()) {
      return new JsonResponse.Builder()
          .statusMessage(messageSource.getMessage("friendList.not.found", null, null))
          .success(false).count(0)
          .build();
    } else {
      return new JsonResponse.Builder()
          .success(true)
          .friends(friendList)
          .count(friendList.size())
          .build();
    }
  }

  @Override
  public JsonResponse getCommonFriendList(FriendsJson friendsJson) {
    List<String> friendList1 = friendsRepository.getFriendList(friendsJson.getFriends().get(0));
    List<String> friendList2 = friendsRepository.getFriendList(friendsJson.getFriends().get(1));

    friendList1.retainAll(friendList2);

    if (!friendList1.isEmpty()) {
      return new JsonResponse.Builder()
          .success(true)
          .friends(friendList1)
          .count(friendList1.size())
          .build();
    }
    return new JsonResponse.Builder()
        .success(false)
        .statusMessage(messageSource.getMessage("commonFriendList.not.found", null, null))
        .build();
  }

  @Override
  public JsonResponse subscribeFriendUpdates(SubscriptionJson subscriptionJson) {
    //check if both has an account
    ProfileAccount
        requestorAccount =
        profileAccountRepository.findOne(subscriptionJson.getRequestor());
    ProfileAccount targetAccount = profileAccountRepository.findOne(subscriptionJson.getTarget());

    if (requestorAccount != null && targetAccount != null) {

      SubscribeFriendNews
          subscribeFriendNews =
          subscribeFriendNewsRepository.isAlreadySubscribeBlock(requestorAccount.getEmailAddress(),
              targetAccount.getEmailAddress());

      if (subscribeFriendNews == null) {
        subscribeFriendNews = new SubscribeFriendNews(requestorAccount.getEmailAddress(),
            targetAccount.getEmailAddress(), SubscriptionEnum.SUBSCRIBE.getSubscriptionStatus());
      }

      subscribeFriendNews.setSubscribeBlock(SubscriptionEnum.SUBSCRIBE.getSubscriptionStatus());

      subscribeFriendNewsRepository.save(subscribeFriendNews);

      return new JsonResponse.Builder().success(true).build();
    }
    return new JsonResponse.Builder()
        .success(false)
        .statusMessage(messageSource.getMessage("subscription.invalid.account.error", null, null))
        .build();
  }

  @Override
  public JsonResponse blockFriendUpdates(SubscriptionJson subscriptionJson) {
    //check if both has an account
    ProfileAccount
        requestorAccount =
        profileAccountRepository.findOne(subscriptionJson.getRequestor());
    ProfileAccount targetAccount = profileAccountRepository.findOne(subscriptionJson.getTarget());

    if (requestorAccount != null && targetAccount != null) {

      SubscribeFriendNews
          subscribeFriendNews =
          subscribeFriendNewsRepository.isAlreadySubscribeBlock(requestorAccount.getEmailAddress(),
              targetAccount.getEmailAddress());

      if (subscribeFriendNews != null) {
        subscribeFriendNews.setSubscribeBlock(SubscriptionEnum.BLOCK.getSubscriptionStatus());

        subscribeFriendNewsRepository.save(subscribeFriendNews);
      }else{
        subscribeFriendNews = new SubscribeFriendNews(subscriptionJson.getRequestor(), subscriptionJson.getTarget(),
            SubscriptionEnum.BLOCK.getSubscriptionStatus());
      }

      subscribeFriendNewsRepository.save(subscribeFriendNews);

      return new JsonResponse.Builder().success(true).build();
    }
    return new JsonResponse.Builder()
        .success(false)
        .statusMessage(messageSource.getMessage("subscription.invalid.account.error", null, null))
        .build();
  }

  @Override
  public JsonResponse getAllowNotificationList(FriendsNotificationJson friendsNotificationJson) {
    //1) Check sender has any account
    //2) Check if there is any friends connection
    //3) Check if friends allow updates
    ProfileAccount
        profileAccount =
        profileAccountRepository.findOne(friendsNotificationJson.getSender());

    if (profileAccount != null) {
      List<String>
          friendList =
          friendsRepository.getFriendList(friendsNotificationJson.getSender());

      List<SubscribeFriendNews>
          blockNotificationFriends =
          subscribeFriendNewsRepository
              .friendsWhoSubscribe(friendsNotificationJson.getSender()).stream()
              .filter(subscribeFriendNews -> subscribeFriendNews.getSubscribeBlock()
                  .equalsIgnoreCase(SubscriptionEnum.BLOCK.getSubscriptionStatus()))
              .collect(Collectors.toList());

      List<String>
          blockNotificationFriendsIds =
          blockNotificationFriends.stream().map(SubscribeFriendNews::getOwnerId)
              .collect(Collectors.toList());

      List<String>
          allowNotificationList =
          friendList.stream().filter(s -> !blockNotificationFriendsIds.contains(s)).collect(
              Collectors.toList());

      if(allowNotificationList != null && !allowNotificationList.isEmpty()){
        return new JsonResponse.Builder()
            .success(true)
            .recipients(allowNotificationList.stream().distinct().collect(Collectors.toList()))
            .build();
      }
      return new JsonResponse.Builder()
          .success(false)
          .statusMessage(messageSource.getMessage("notification.allow.list.not.found", null, null))
          .build();
    }
    return new JsonResponse.Builder()
        .success(false)
        .statusMessage(messageSource.getMessage("profile.account.not.found", null, null))
        .build();

  }
}
