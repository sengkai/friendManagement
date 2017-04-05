package com.friends.repo;

import com.friends.domain.Friends;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by seng kai on 3/18/2017.
 */
public interface FriendsRepository extends CrudRepository<Friends, String> {

  @Query("select f from Friends f where f.ownerId = :ownerId and f.friendId = :friendId ")
  Friends isFriendConnectionExist(@Param("ownerId") String ownerId, @Param("friendId") String friendId);

  @Query("select f.friendId from Friends f where f.ownerId = :ownerId")
  List<String> getFriendList(@Param("ownerId") String ownerId);
}
