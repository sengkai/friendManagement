TRUNCATE TABLE profile_account;
INSERT INTO profile_account (email_address) values ('sk@example.com');
INSERT INTO profile_account (email_address) values ('jm@example.com');


TRUNCATE TABLE friends;
INSERT INTO friends (id, owner_id, friend_id) values (null, 'sk@example.com', 'sk_friend1@example.com');
INSERT INTO friends (id, owner_id, friend_id) values (null, 'sk@example.com', 'sk_friend2@example.com');
INSERT INTO friends (id, owner_id, friend_id) values (null, 'sk@example.com', 'sk_friend3@example.com');
INSERT INTO friends (id, owner_id, friend_id) values (null, 'sk@example.com', 'sk_friend4@example.com');
INSERT INTO friends (id, owner_id, friend_id) values (null, 'sk@example.com', 'kimjio@example.com');
INSERT INTO friends (id, owner_id, friend_id) values (null, 'sk@example.com', 'wx@example.com');

INSERT INTO friends (id, owner_id, friend_id) values (null, 'jm@example.com', 'jm_friend1@example.com');
INSERT INTO friends (id, owner_id, friend_id) values (null, 'jm@example.com', 'jm_friend2@example.com');
INSERT INTO friends (id, owner_id, friend_id) values (null, 'jm@example.com', 'kimjio@example.com');
INSERT INTO friends (id, owner_id, friend_id) values (null, 'jm@example.com', 'wx@example.com');