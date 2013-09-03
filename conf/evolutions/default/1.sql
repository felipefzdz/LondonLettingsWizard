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

create sequence area_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists area;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists area_seq;

