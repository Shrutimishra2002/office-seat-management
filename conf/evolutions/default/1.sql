# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table office (
  id                            bigint auto_increment not null,
  name                          varchar(255),
  rows_length                   integer,
  columns_length                integer,
  created_at                    datetime(6) not null,
  updated_at                    datetime(6) not null,
  constraint pk_office primary key (id)
);

create table seat (
  id                            bigint auto_increment not null,
  office_id                     bigint,
  row_in_number                 integer,
  col_in_number                 integer,
  status                        varchar(255),
  created_at                    datetime(6) not null,
  updated_at                    datetime(6) not null,
  constraint pk_seat primary key (id)
);

create table user (
  id                            bigint auto_increment not null,
  first_name                    varchar(255),
  last_name                     varchar(255),
  email                         varchar(255),
  password                      varchar(255),
  created_at                    datetime(6) not null,
  updated_at                    datetime(6) not null,
  constraint pk_user primary key (id)
);

create table user_seat_assignment (
  id                            bigint auto_increment not null,
  user_id                       bigint,
  seat_id                       bigint,
  assigned_at                   datetime(6),
  created_at                    datetime(6) not null,
  updated_at                    datetime(6) not null,
  constraint pk_user_seat_assignment primary key (id)
);


# --- !Downs

drop table if exists office;

drop table if exists seat;

drop table if exists user;

drop table if exists user_seat_assignment;

