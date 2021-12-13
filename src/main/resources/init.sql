USE jobeed;

CREATE TABLE users
(
    id         VARCHAR(36) PRIMARY KEY                               NOT NULL,
    email      VARCHAR(255) UNIQUE                                   NOT NULL,
    name       CHAR(24)                                              NOT NULL,
    password   VARCHAR(255)                                          NOT NULL,
    birthday   DATE                                                  NOT NULL,
    picture    VARCHAR(255)                                          NOT NULL,
    gender     ENUM ('MALE', 'FEMALE', 'OTHER')                      NOT NULL,
    employment ENUM ('FULL', 'PART_TIME', 'UNEMPLOYED', 'FREELANCE') NOT NULL,
    role       ENUM ('ADMIN', 'REGISTERED', 'ANONYMOUS')             NOT NULL
);

CREATE TABLE interests
(
    id   VARCHAR(36) PRIMARY KEY NOT NULL,
    name VARCHAR(255)            NOT NULL
);

CREATE TABLE users_interests
(
    id           VARCHAR(36) PRIMARY KEY NOT NULL,
    userId       VARCHAR(36)             NOT NULL,
    interestId   VARCHAR(36)             NOT NULL,
    interestName VARCHAR(255)            NOT NULL,
    FOREIGN KEY (userId) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (interestId) REFERENCES interests (id)
);

CREATE TRIGGER before_insert_user_interest
    BEFORE INSERT
    ON users_interests
    FOR EACH ROW
    SET new.id = UUID();

CREATE TABLE tweets
(
    id            VARCHAR(36) PRIMARY KEY NOT NULL,
    content       VARCHAR(140)            NOT NULL,
    likeCount     INTEGER   DEFAULT 0,
    replyCount    INTEGER   DEFAULT 0,
    authorId      VARCHAR(36)             NOT NULL,
    authorName    CHAR(24)                NOT NULL,
    authorEmail   VARCHAR(255)            NOT NULL,
    authorPicture VARCHAR(255)            NOT NULL,
    parentTweetId VARCHAR(36),
    createdOn     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (authorId) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (parentTweetId) REFERENCES tweets (id) ON DELETE CASCADE
);

CREATE TABLE followed_users
(
    id         VARCHAR(36) PRIMARY KEY                               NOT NULL,
    followerId VARCHAR(36)                                           NOT NULL,
    followedId VARCHAR(36)                                           NOT NULL,
    email      VARCHAR(255)                                          NOT NULL,
    name       CHAR(24)                                              NOT NULL,
    birthday   DATE                                                  NOT NULL,
    picture    VARCHAR(255)                                          NOT NULL,
    employment ENUM ('FULL', 'PART_TIME', 'UNEMPLOYED', 'FREELANCE') NOT NULL,
    FOREIGN KEY (followerId) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (followedId) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TRIGGER before_insert_followed
    BEFORE INSERT
    ON followed_users
    FOR EACH ROW
    SET new.id = UUID();

INSERT INTO users (id, email, name, password, birthday, gender, employment, picture, role)
VALUES ('8221c44f-9ac5-4bb3-88e1-99596ada61ef', 'victor@vimtor.io', 'Victor Navarro', 'Qwer1234!',
        STR_TO_DATE('12-29-1999', '%m-%d-%Y'), 'MALE', 'FULL',
        'https://media-exp3.licdn.com/dms/image/C5603AQGEBM9qeX5KHQ/profile-displayphoto-shrink_100_100/0/1572607525533?e=1629936000&v=beta&t=gP50FJw2OjTGmySze8_ApzBjd52h79wi8httnx7kkEk',
        'ADMIN'),
       ('6835a10b-b4c4-43b0-a047-44293df4468b', 'tonirealp@gmail.com', 'Antoni Realp', 'Qwer1234!',
        STR_TO_DATE('10-21-1997', '%m-%d-%Y'), 'MALE', 'PART_TIME',
        'https://media-exp3.licdn.com/dms/image/C5603AQFivpr3_pwfuw/profile-displayphoto-shrink_200_200/0/1568833248927?e=1629936000&v=beta&t=2P9vArCxlfevIZAJF7txB-g6u0yxpoheW2R3A2ulFt8',
        'REGISTERED');

INSERT INTO interests (id, name)
VALUES ('3f5cbdcd-1c18-4c6d-ad4e-262e32572744', 'Java'),
       ('d7df2522-21c8-4df7-a959-3f14a21bb4f1', 'Cooking'),
       ('43ebde1a-90a8-48af-8a11-b417e9b89722', 'Sports'),
       ('750d9afb-e9ae-4649-a7b1-55522294a6d0', 'Blockchain'),
       ('cced4d0b-cee1-4817-8b89-b9737efe1633', 'Marketing');

INSERT INTO tweets (id, content, authorId, authorName, authorEmail, authorPicture)
VALUES (UUID(), 'This is the first tweet of Jobeed', '8221c44f-9ac5-4bb3-88e1-99596ada61ef', 'Victor Navarro',
        'victor@vimtor.io',
        'https://media-exp3.licdn.com/dms/image/C5603AQGEBM9qeX5KHQ/profile-displayphoto-shrink_100_100/0/1572607525533?e=1629936000&v=beta&t=gP50FJw2OjTGmySze8_ApzBjd52h79wi8httnx7kkEk'),
       (UUID(), 'Hey did you stole my first tweet?', '6835a10b-b4c4-43b0-a047-44293df4468b', 'Antoni Realp',
        'tonirealp@gmail.com',
        'https://media-exp3.licdn.com/dms/image/C5603AQFivpr3_pwfuw/profile-displayphoto-shrink_200_200/0/1568833248927?e=1629936000&v=beta&t=2P9vArCxlfevIZAJF7txB-g6u0yxpoheW2R3A2ulFt8'),
       (UUID(), 'I am gonna conquer the world', '6835a10b-b4c4-43b0-a047-44293df4468b', 'Antoni Realp',
        'tonirealp@gmail.com',
        'https://media-exp3.licdn.com/dms/image/C5603AQFivpr3_pwfuw/profile-displayphoto-shrink_200_200/0/1568833248927?e=1629936000&v=beta&t=2P9vArCxlfevIZAJF7txB-g6u0yxpoheW2R3A2ulFt8'),
       (UUID(), 'When Ibai registers here we will become millionaire', '8221c44f-9ac5-4bb3-88e1-99596ada61ef',
        'Victor Navarro', 'victor@vimtor.io',
        'https://media-exp3.licdn.com/dms/image/C5603AQGEBM9qeX5KHQ/profile-displayphoto-shrink_100_100/0/1572607525533?e=1629936000&v=beta&t=gP50FJw2OjTGmySze8_ApzBjd52h79wi8httnx7kkEk');

INSERT INTO users_interests (id, userId, interestId, interestName)
VALUES (UUID(), '8221c44f-9ac5-4bb3-88e1-99596ada61ef', '3f5cbdcd-1c18-4c6d-ad4e-262e32572744', 'Java'),
       (UUID(), '8221c44f-9ac5-4bb3-88e1-99596ada61ef', '750d9afb-e9ae-4649-a7b1-55522294a6d0', 'Blockchain'),
       (UUID(), '8221c44f-9ac5-4bb3-88e1-99596ada61ef', 'd7df2522-21c8-4df7-a959-3f14a21bb4f1', 'Cooking'),
       (UUID(), '6835a10b-b4c4-43b0-a047-44293df4468b', '3f5cbdcd-1c18-4c6d-ad4e-262e32572744', 'Java');
