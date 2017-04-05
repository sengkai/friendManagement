## Background
For any application with a need to build its own social network, "Friends Management" is a common requirement which usually starts off simple but can grow in complexity depending on the application's use case.

Usually, applications would start with features like "Friend", "Unfriend", "Block", "Receive Updates" etc.

#### Prerequisite to start this project.
1. Docker (https://docs.docker.com/docker-for-windows/install/)
2. Maven (https://maven.apache.org/install.html) :: Optional
3. Jenkins (https://jenkins.io/) :: CLI build tool for Automation 

#### Instruction to run docker
* For windows user, open up (e.g. cmd, powershell etc.) whichever you have and type "docker run -p 8090:8080 skylover/friendmanagement"
* Open up any rest clients that you have and type in the following links: (http://localhost:8090)
* Your rest client should show you the following response as follows:
```
 {
  "_links": {
    "profileAccounts": {
      "href": "http://localhost:8080/profileAccounts"
    },
    "friendses": {
      "href": "http://localhost:8080/friendses"
    },
    "subscribeFriendNews": {
      "href": "http://localhost:8080/subscribeFriendNews"
    },
    "profile": {
      "href": "http://localhost:8080/profile"
    }
  }
}
```

#### User Stories
**1. As a user, I need an API to create a friend connection between two email addresses.**
* This is a POST request with the following context path (http://localhost:8090/friends/createConnection)
* If there is already a connection between the two friends. 
API will response with the following message: "Already have existing friend connection found."
```
The API should receive the following JSON request:
{
  "friends":
    [
      "sk@example.com",
      "jm@example.com"
    ]
}
```

**2. As a user, I need an API to retrieve the friends list for an email address.**
* This is a POST request with the following context path (http://localhost:8090/friends/findFriend)
* If there is no friends found. 
API will response with the following message: "No Friend List found."
```
The API should receive the following JSON request:
{
  "email": "sk@example.com"
}
```

**3. As a user, I need an API to retrieve the common friends list between two email addresses.**
* This is a POST request with the following context path (http://localhost:8090/friends/commonFriend)
* If there is no common friends found. 
API will response with the following message: "No Common Friend List found."
```
The API should receive the following JSON request:
{
  "friends":
    [
      "jm@example.com",
      "sk@example.com"
    ]
}
```

**4. As a user, I need an API to subscribe to updates for an email address.**
* This is a POST request with the following context path (http://localhost:8090/friends/subscribeToFriend)
* Subscription will fail if any of the email account details cannot be found.
API will response with the following message: "Invalid Account found."
```
The API should receive the following JSON request:
{
  "requestor": "jm@example.com",
  "target": "sk@example.com"
}
```


**5. As a user, I need an API to block updates for an email address.**
* This is a POST request with the following context path (http://localhost:8090/friends/blockFriendUpdates)
* Block friend update will fail if any of the email account details cannot be found.
API will response with the following message: "Invalid Account found."
```
The API should receive the following JSON request:
{
  "requestor": "cy@example.com",
  "target": "sk@example.com"
}
```

**6. As a user, I need an API to retrieve all email addresses that can receive updates for an email address.**
* This is a POST request with the following context path (http://localhost:8090/friends/broadcastUpdatesToFriends)
* Block friend update will fail if any of the email account details cannot be found.
API will response with the following message: "Invalid Account found."
```
The API should receive the following JSON request:
{
  "sender":  "sk@example.com",
  "text": "Hello World! All my Friends"
}
```
