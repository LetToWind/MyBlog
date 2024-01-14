-- 第三方登录较复杂，先采用security实现，之后再研究怎么划分出去
-- 我们需要权限表，用户表，角色表
create table t_role(
    id int(11) not null primary key,
    role_name varchar(20) not null,
    role_desc varchar(200) not null,
    create_time datetime default current_timestamp,
    create_by varchar(20) not null,
    update_time datetime default current_timestamp,
    update_by varchar(20) not null
);

-- 用户表 注意我们在blog-service中还有一个用户表，这个表是用来存储用户的详细信息的，
-- 而目前这个表只涉及权限验证的信息
create table t_user(
    id int(11) not null primary key,
    username varchar(20) not null,
    password varchar(200) not null,
    email varchar(50) not null,
    phone varchar(20) not null,
    create_time datetime default current_timestamp,
    create_by varchar(20) not null,
    update_time datetime default current_timestamp,
    update_by varchar(20) not null

);

create table t_permission(
    id int(11) not null primary key,
    permission_name varchar(20) not null,
    permission_desc varchar(200) not null,
    create_time datetime default current_timestamp,
    create_by varchar(20) not null,
    update_time datetime default current_timestamp,
    update_by varchar(20) not null
);
