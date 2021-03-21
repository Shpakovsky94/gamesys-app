drop database if exists tweets;
create database tweets;
use tweets;


create table if not exists `tweets`.`ELONS_TWEETS`
(
    tweet_Id   bigint primary key not null ,
    created_at varchar(100) null,
    text       varchar(255) null
);
