# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table area (
  id                        bigint not null,
  name                      varchar(255),
  description               TEXT,
  rate_id                   bigint,
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

create table rate (
  id                        bigint not null,
  rate_transport            integer,
  green_spaces              integer,
  night_life                integer,
  money_value               integer,
  constraint pk_rate primary key (id))
;

create sequence area_seq;

create sequence price_seq;

create sequence rate_seq;

alter table area add constraint fk_area_rate_1 foreign key (rate_id) references rate (id) on delete restrict on update restrict;
create index ix_area_rate_1 on area (rate_id);
alter table price add constraint fk_price_area_2 foreign key (area_id) references area (id) on delete restrict on update restrict;
create index ix_price_area_2 on price (area_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists area;

drop table if exists price;

drop table if exists rate;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists area_seq;

drop sequence if exists price_seq;

drop sequence if exists rate_seq;

