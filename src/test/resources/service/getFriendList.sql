TRUNCATE TABLE profile_account;
INSERT INTO profile_account (email_address) values ('sk@example.com');


TRUNCATE TABLE friends;
INSERT INTO friends (id, owner_id, friend_id) values (null, 'sk@example.com', 'sk_friend1@example.com');
INSERT INTO friends (id, owner_id, friend_id) values (null, 'sk@example.com', 'sk_friend2@example.com');
INSERT INTO friends (id, owner_id, friend_id) values (null, 'sk@example.com', 'sk_friend3@example.com');
INSERT INTO friends (id, owner_id, friend_id) values (null, 'sk@example.com', 'sk_friend4@example.com');