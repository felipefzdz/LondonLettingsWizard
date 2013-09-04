# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table area (
  id                        bigint not null,
  name                      varchar(255),
  rate_transport            integer,
  wealth_scale              integer,
  constraint ck_area_wealth_scale check (wealth_scale in (0,1,2,3,4)),
  constraint pk_area primary key (id))
;

create table price (
  id                        bigint not null,
  bedrooms                  integer,
  min_price                 integer,
  half_price                integer,
  max_price                 integer,
  area_id                   bigint,
  constraint pk_price primary key (id))
;

create table user (
  email                     varchar(255) not null,
  name                      varchar(255),
  password                  varchar(255),
  constraint pk_user primary key (email))
;

create sequence area_seq;

create sequence price_seq;

create sequence user_seq;

alter table price add constraint fk_price_area_1 foreign key (area_id) references area (id) on delete restrict on update restrict;
create index ix_price_area_1 on price (area_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists area;

drop table if exists price;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists area_seq;

drop sequence if exists price_seq;

drop sequence if exists user_seq;

