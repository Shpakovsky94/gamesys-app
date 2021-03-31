drop
database if exists tweets;
create
database tweets;
use
tweets;


create table if not exists `tweets`.`ELONS_TWEETS`
(
    PK_ROW_ID
    bigint
    primary
    key
    not
    null
    AUTO_INCREMENT,
    tweet_Id
    bigint
    unique
    null,
    created_at
    varchar
(
    100
) null,
    text varchar
(
    1000
) null
    );
