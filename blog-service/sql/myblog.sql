drop database if exists ltw_blog;
create database ltw_blog;
use ltw_blog;

-- 创建一张博客表
-- 现在，我们数据库中的用户信息由sso服务维护，
-- 那么想要建立用户博客表，我们得考虑把用户的唯一id拿到，以此一对多关联博客信息
create table t_user_blog(
    id int(11) not null primary key ,
    user_id int(11) not null ,
    title varchar(50) not null,
    summary varchar(200) not null,
    content text ,
    `rank` int(11) not null,-- 对帖子进行排序
    create_time datetime default current_timestamp,
    create_by varchar(20) not null,
    update_time datetime default current_timestamp,
    update_by varchar(20) not null
) engine = innodb default charset = utf8mb4;

-- 似乎还是要有一个用户表，将用户的信息放到sso中是不合理的，sso应该只管验证
-- 所以，我们需要用户表，存储详细的用户信息
create table t_user
(
    id          int(11)     not null primary key,
    avatar_url  varchar(200) comment '用户头像',
    username    varchar(20) not null comment '用户名',
    -- 这个地方是否要密码？还值得思考，我们在做登录或注册时
    -- 应该是将用户名密码都传给sso服务，sso就能返回一个token
    -- token当中含有用户的一些信息，我们将其取出即可访问资源
    --
    email       varchar(50) not null,
    phone       varchar(20) not null,
    create_time datetime default current_timestamp,
    create_by   varchar(20) not null,
    update_time datetime default current_timestamp,
    update_by   varchar(20) not null
);

-- 建立一个评论表
create table t_comment(
    id int(11) not null primary key,
    parent_id int(11) not null, -- 评论要做成父子关系，即贴吧的一层楼之下有多条评论，
    reply_user_id int(11) not null,-- 评论之间还有回复
    blog_id int(11) not null,
    user_id int(11) not null,
    content text not null,
    create_time datetime default current_timestamp,
    create_by varchar(20) not null,
    update_time datetime default current_timestamp,
    update_by varchar(20) not null
);

