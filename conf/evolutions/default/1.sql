# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table area (
  id                        bigint not null,
  name                      varchar(255),
  min_price                 integer,
  max_price                 integer,
  rate_transport            integer,
  constraint pk_area primary key (id))
;

create table user (
  email                     varchar(255) not null,
  name                      varchar(255),
  password                  varchar(255),
  constraint pk_user primary key (email))
;

create sequence area_seq;

create sequence user_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists area;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists area_seq;

drop sequence if exists user_seq;

