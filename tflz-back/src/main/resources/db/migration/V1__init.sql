create table user
(
    id          int(11) unsigned auto_increment primary key comment '用户唯一id',
    email       varchar(320)                not null unique comment '用户邮箱号码',
    name        varchar(12)                 not null unique comment '用户名',
    age         tinyint unsigned                not null comment '年龄',
    sex         tinyint(1)                  not null comment '性别 0男 1女',
    password    varchar(100)                not null comment '用户密码',
    declaration varchar(50) default '垃圾要分类' not null comment '绿色宣言',
    time_create datetime                    not null comment '用户账户创建时间',
    time_modify datetime                    not null comment '用户账户最后一次修改(包括参与讨论)时间'
) engine = innodb
  auto_increment = 1
  default charset = utf8mb4;

create table propose
(
    id          int(11) unsigned auto_increment primary key comment '倡议唯一id',
    uid         int(11) unsigned not null comment '倡议创建者id',
    name        varchar(30)      not null unique comment '倡议主题',
    description varchar(200)     not null comment '倡议概述',
    cnt_like    int(11) unsigned default 0 comment '倡议点赞数',
    time_create datetime         not null comment '倡议创建时间',
    time_reply  datetime         not null comment '倡议最后一次回复时间',
    constraint fk_propose_user_id foreign key (uid) references user (id)
        on delete cascade on update cascade
) engine = innodb
  auto_increment = 1
  default charset = utf8mb4;

create table tag
(
    id   int(11) unsigned auto_increment primary key comment '标签唯一id',
    name varchar(20) not null unique comment '标签名'
) engine = innodb
  auto_increment = 1
  default charset = utf8mb4;

create table reply
(
    id          int(11) unsigned auto_increment primary key comment '回复唯一id',
    sid         int(11) unsigned not null comment '目标倡议id',
    uid         int(11) unsigned not null comment '发起用户id',
    detail      text             not null comment '回复详细文章',
    time_create datetime         not null comment '回复创建时间',
    constraint fk_reply_propose_id foreign key (sid) references propose (id)
        on delete cascade on update cascade,
    constraint fk_reply_user_id foreign key (uid) references user (id)
        on update cascade on delete cascade
) engine = innodb
  auto_increment = 1
  default charset = utf8mb4;

create table propose_tag
(
    id  int(11) unsigned auto_increment primary key comment '倡议绑定标签的唯一id',
    sid int(11) unsigned not null comment '倡议id 这里写错了，应该是pid',
    tid int(11) unsigned not null comment '标签id',
    constraint fk_propose_tag_sid foreign key (sid) references propose (id)
        on delete cascade on update cascade,
    constraint fk_propose_tag_tid foreign key (tid) references tag (id)
        on delete cascade on update cascade
) engine = innodb
  auto_increment = 1
  default charset = utf8mb4;

create table notice
(
    id          int(11) unsigned auto_increment primary key comment '通知唯一id',
    sid         int(11) unsigned not null comment '倡议id',
    uid         int(11) unsigned not null comment '目标用户id',
    isUnread    tinyint(1) default 0 comment '为1表示未读，为0表示已读',
    time_create datetime         not null comment '回复创建时间',
    constraint fk_notice_propose_id foreign key (sid) references propose (id) on delete cascade on update cascade,
    constraint fk_notice_user_id foreign key (uid) references user (id) on delete cascade on update cascade
) engine = innodb
  auto_increment = 1
  default charset = utf8mb4;

create table role
(
    id          int(11) unsigned primary key auto_increment comment '角色唯一id',
    name        varchar(15) unique not null comment '角色名',
    description varchar(50)        not null comment '角色描述'
) engine = innodb
  auto_increment = 1
  default charset = utf8mb4;

create table permission
(
    id          int(11) unsigned primary key auto_increment comment '权限唯一id',
    name        varchar(15) unique not null comment '权限名',
    description varchar(50)        not null comment '权限描述'
) engine = innodb
  auto_increment = 1
  default charset = utf8mb4;

create table user_role
(
    id  int(11) unsigned primary key auto_increment comment '用户与角色关系的唯一id',
    uid int(11) unsigned not null comment '用户id',
    rid int(11) unsigned not null comment '角色id',
    constraint fk_user_role_uid foreign key (uid) references user (id),
    constraint fk_user_role_rid foreign key (rid) references role (id)
) engine = innodb
  auto_increment = 1
  default charset = utf8mb4;

create table user_permission
(
    id  int(11) unsigned primary key auto_increment comment '用户与权限关系的唯一id',
    uid int(11) unsigned not null comment '用户id',
    pid int(11) unsigned not null comment '权限id',
    constraint fk_user_permission_uid foreign key (uid) references user (id),
    constraint fk_user_permission_pid foreign key (pid) references permission (id)
) engine = innodb
  auto_increment = 1
  default charset = utf8mb4;

insert into role
values (null, 'user', '普通用户');
insert into role
values (null, 'admin', '管理员');
insert into permission
values (null, 'user:normal', '普通用户权限');
insert into permission
values (null, 'user:higher', '超级用户权限');
insert into permission
values (null, 'admin:normal', '普通管理员权限');
insert into permission
values (null, 'admin:higher', '超级管理员权限');