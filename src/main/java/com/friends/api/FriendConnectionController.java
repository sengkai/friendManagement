package com.friends.api;

import com.friends.service.FriendConnectionService;
import com.friends.service.IFriendConnectionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by seng kai on 3/18/2017.
 */
@RestController
public class FriendConnectionController {
  private static final Logger logger = LogManager.getLogger(FriendConnectionService.class);
  
  @Autowired
  IFriendConnectionService iFriendConnectionService;
  private MessageSource messageSource;

  @Autowired
  public FriendConnectionController(MessageSource messageSource) {
    this.messageSource = messageSource;
  }

  @RequestMapping(value = "/friends/createConnection", method = RequestMethod.POST)
  @ResponseBody
  public JsonResponse createFriendsConnection(@Valid @RequestBody FriendsJson friendsJson){
    return iFriendConnectionService.createFriendsConnection(friendsJson);
  }

  @RequestMapping(value = "/friends/findFriend", method = RequestMethod.POST)
  @ResponseBody
  public JsonResponse getFriendList(@Valid @RequestBody FriendsJson friendsJson){
    return iFriendConnectionService.getFriendList(friendsJson.getEmail());
  }

  @RequestMapping(value = "/friends/commonFriend", method = RequestMethod.POST)
  @ResponseBody
  public JsonResponse findCommonFriend(@Valid @RequestBody FriendsJson friendsJson){
    return iFriendConnectionService.getCommonFriendList(friendsJson);
  }

  @RequestMapping(value = "/friends/subscribeToFriend", method = RequestMethod.POST)
  @ResponseBody
  public JsonResponse subscribeFriendUpdates(@Valid @RequestBody SubscriptionJson subscriptionJson){
    return iFriendConnectionService.subscribeFriendUpdates(subscriptionJson);
  }

  @RequestMapping(value = "/friends/blockFriendUpdates", method = RequestMethod.POST)
  @ResponseBody
  public JsonResponse blockFriendUpdates(@Valid @RequestBody SubscriptionJson subscriptionJson){
    return iFriendConnectionService.blockFriendUpdates(subscriptionJson);
  }

  @RequestMapping(value = "/friends/broadcastUpdatesToFriends", method = RequestMethod.POST)
  @ResponseBody
  public JsonResponse broadcastUpdatesToFriends(@Valid @RequestBody FriendsNotificationJson friendsNotificationJson){
    return iFriendConnectionService.getAllowNotificationList(friendsNotificationJson);
  }
}
