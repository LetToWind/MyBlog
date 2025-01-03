drop database blog_user;

create database blog_user;

use blog_user;

drop table if exists t_permission,t_role,t_user;
-- 第三方登录较复杂，先采用security实现，之后再研究怎么划分出去
-- 我们需要权限表，用户表，角色表
create table t_role(
                       id bigint(20) not null primary key,
                       role_name varchar(20) not null,
                       role_desc varchar(200) not null,
                       create_time datetime default current_timestamp,
                       create_by varchar(20) not null,
                       update_time datetime default current_timestamp,
                       update_by varchar(20) not null
);

-- 用户表 或许可以这样考虑，用户可以有用户详情表，一个用户主表可以对应多个用户详情表，相当于一套认证信息可以开多个小号
-- 而目前这个表只涉及权限验证的信息
create table t_user(
                       id bigint(20) not null primary key,
                       login_name varchar(20) ,
                       username varchar(20) not null,
                       password varchar(200) not null,
                       avatar varchar(200)  ,
                       email varchar(50) ,
                       phone varchar(20) ,
                       role_id int(11)  ,
                       salt varchar(20)

);

create table t_role_user(
    id bigint(20) not null primary key,
    role_id bigint(20) not null,
    user_id bigint(20) not null
);

create table t_permission(
                             id bigint(20) not null primary key,
                             permission_name varchar(20) not null,
                             permission_desc varchar(200) not null,
                             role_id bigint(20) not null,
                             create_time datetime default current_timestamp,
                             create_by varchar(20) not null,
                             update_time datetime default current_timestamp,
                             update_by varchar(20) not null
);