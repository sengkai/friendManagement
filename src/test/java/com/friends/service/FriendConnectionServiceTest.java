package com.friends.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.friends.api.FriendsJson;
import com.friends.api.FriendsNotificationJson;
import com.friends.api.JsonResponse;
import com.friends.api.SubscriptionJson;
import com.friends.repo.SubscribeFriendNewsRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by seng kai on 3/18/2017.
 */
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class FriendConnectionServiceTest {
  private static final Logger logger = LogManager.getLogger(FriendConnectionServiceTest.class);

  @Autowired
  IFriendConnectionService iFriendConnectionService;

  @Autowired
  SubscribeFriendNewsRepository subscribeFriendNewsRepository;

  @Autowired
  ObjectMapper objectMapper;

  @Test
  @Sql("/service/getFriendList.sql")
  public void getFriendList() throws Exception {

    JsonResponse
        jsonResponse =
        iFriendConnectionService.getFriendList("sk@example.com");

    assertThat(jsonResponse.getFriends()).isNotNull();
    assertThat(jsonResponse.getFriends()).hasSize(4);

    String jsonMapperOutput =
        objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonResponse);
    logger.info("Print out Json :: " + jsonMapperOutput);

    assertThat(jsonMapperOutput).isNotNull();
  }

  @Test
  public void getFriendList_Error() throws Exception {

    JsonResponse
        jsonResponse =
        iFriendConnectionService.getFriendList("sk999@example.com");

    assertThat(jsonResponse.getFriends()).isNull();
    assertThat(jsonResponse.getSuccess()).isFalse();
    assertThat(jsonResponse.getStatusMessage()).contains("No Friend List found.");

    String jsonMapperOutput =
        objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonResponse);
    logger.info("Print out Json :: " + jsonMapperOutput);

    assertThat(jsonMapperOutput).isNotNull();
  }

  @Test
  @Sql("/service/getCommonFriendList.sql")
  public void getCommonFriendList() throws Exception {

    List<String> getCommonFriend = new ArrayList<String>() {
      {
        add("sk@example.com");
        add("jm@example.com");
      }
    };

    JsonResponse
        jsonResponse =
        iFriendConnectionService
            .getCommonFriendList(new FriendsJson.Builder().friends(getCommonFriend).build());

    assertThat(jsonResponse.getFriends()).isNotNull();
    assertThat(jsonResponse.getFriends()).hasSize(2);

    String jsonMapperOutput =
        objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonResponse);
    logger.info("Print out Json :: " + jsonMapperOutput);

    assertThat(jsonMapperOutput).isNotNull();
  }

  @Test
  @Sql("/service/getCommonFriendList.sql")
  public void getCommonFriendList_Error() throws Exception {

    List<String> getCommonFriend = new ArrayList<String>() {
      {
        add("sk2@example.com");
        add("jm@example.com");
      }
    };

    JsonResponse
        jsonResponse =
        iFriendConnectionService
            .getCommonFriendList(new FriendsJson.Builder().friends(getCommonFriend).build());

    assertThat(jsonResponse.getFriends()).isNull();
    assertThat(jsonResponse.getSuccess()).isFalse();
    assertThat(jsonResponse.getStatusMessage()).contains("No Common Friend List found.");

    String jsonMapperOutput =
        objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonResponse);
    logger.info("Print out Json :: " + jsonMapperOutput);

    assertThat(jsonMapperOutput).isNotNull();
  }

  @Test
  @Sql("/service/subscribeFriendUpdates.sql")
  public void subscribeFriendUpdates() throws Exception {

    SubscriptionJson
        subscriptionJson =
        new SubscriptionJson.Builder().requestor("sk@example.com").target("jm@example.com").build();

    JsonResponse
        jsonResponse =
        iFriendConnectionService
            .subscribeFriendUpdates(subscriptionJson);

    assertThat(jsonResponse.getSuccess()).isTrue();

    String jsonMapperOutput =
        objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonResponse);
    logger.info("Print out Json :: " + jsonMapperOutput);

    assertThat(jsonMapperOutput).isNotNull();
  }

  @Test
  @Sql("/service/subscribeFriendUpdates.sql")
  public void subscribeFriendUpdates_Error() throws Exception {

    SubscriptionJson
        subscriptionJson =
        new SubscriptionJson.Builder().requestor("unknown@example.com").target("jm@example.com")
            .build();

    JsonResponse
        jsonResponse =
        iFriendConnectionService
            .subscribeFriendUpdates(subscriptionJson);

    assertThat(jsonResponse.getSuccess()).isFalse();
    assertThat(jsonResponse.getStatusMessage()).contains("Invalid Account found.");

    String jsonMapperOutput =
        objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonResponse);
    logger.info("Print out Json :: " + jsonMapperOutput);

    assertThat(jsonMapperOutput).isNotNull();
  }


  @Test
  @Sql("/service/blockFriendUpdates.sql")
  public void blockFriendUpdates() throws Exception {

    SubscriptionJson
        subscriptionJson =
        new SubscriptionJson.Builder().requestor("sk@example.com").target("jm@example.com").build();

    JsonResponse
        jsonResponse =
        iFriendConnectionService
            .blockFriendUpdates(subscriptionJson);

    assertThat(jsonResponse.getSuccess()).isTrue();

    String jsonMapperOutput =
        objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonResponse);
    logger.info("Print out Json :: " + jsonMapperOutput);

    assertThat(jsonMapperOutput).isNotNull();
  }

  @Test
  @Sql("/service/subscribeFriendUpdates.sql")
  public void blockFriendUpdates_Error() throws Exception {

    SubscriptionJson
        subscriptionJson =
        new SubscriptionJson.Builder().requestor("unknown@example.com").target("jm@example.com")
            .build();

    JsonResponse
        jsonResponse =
        iFriendConnectionService
            .blockFriendUpdates(subscriptionJson);

    assertThat(jsonResponse.getSuccess()).isFalse();
    assertThat(jsonResponse.getStatusMessage()).contains("Invalid Account found.");

    String jsonMapperOutput =
        objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonResponse);
    logger.info("Print out Json :: " + jsonMapperOutput);

    assertThat(jsonMapperOutput).isNotNull();
  }

  @Test
  @Sql("/service/friendNotificationUpdates.sql")
  public void friendNotificationUpdates() throws Exception {

    FriendsNotificationJson
        friendsNotificationJson =
        new FriendsNotificationJson.Builder().sender("sk@example.com").text("Hello World! ")
            .build();

    JsonResponse
        jsonResponse =
        iFriendConnectionService
            .getAllowNotificationList(friendsNotificationJson);

    assertThat(jsonResponse.getSuccess()).isTrue();
    assertThat(jsonResponse.getRecipients()).hasSize(3);

    String jsonMapperOutput =
        objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonResponse);
    logger.info("Print out Json :: " + jsonMapperOutput);

    assertThat(jsonMapperOutput).isNotNull();
  }

}
