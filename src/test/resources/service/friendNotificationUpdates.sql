TRUNCATE TABLE profile_account;
INSERT INTO profile_account (email_address) values ('sk@example.com');
INSERT INTO profile_account (email_address) values ('jm@example.com');
INSERT INTO profile_account (email_address) values ('wx@example.com');
INSERT INTO profile_account (email_address) values ('cy@example.com');
INSERT INTO profile_account (email_address) values ('mjj@example.com');

TRUNCATE TABLE subscribe_friend_news;
INSERT INTO subscribe_friend_news (id, owner_id, friend_id, subscribe_block) values (null, 'wx@example.com', 'sk@example.com', 'SUB');
INSERT INTO subscribe_friend_news (id, owner_id, friend_id, subscribe_block) values (null, 'cy@example.com', 'sk@example.com', 'BLOCK');
INSERT INTO subscribe_friend_news (id, owner_id, friend_id, subscribe_block) values (null, 'mjj@example.com', 'sk@example.com', 'SUB');

TRUNCATE TABLE friends;
INSERT INTO friends (id, owner_id, friend_id) values (null, 'sk@example.com', 'wx@example.com');
INSERT INTO friends (id, owner_id, friend_id) values (null, 'sk@example.com', 'jm@example.com');
INSERT INTO friends (id, owner_id, friend_id) values (null, 'sk@example.com', 'cy@example.com');
INSERT INTO friends (id, owner_id, friend_id) values (null, 'sk@example.com', 'mjj@example.com');

INSERT INTO friends (id, owner_id, friend_id) values (null, 'jm@example.com', 'sk@example.com');
INSERT INTO friends (id, owner_id, friend_id) values (null, 'mjj@example.com', 'sk@example.com');