create table game
(
    id          int(11) unsigned auto_increment primary key comment '游戏记录唯一id',
    uid         int(11) unsigned not null comment '玩家id',
    score       int(11) unsigned not null comment '游戏分数',
    time_create datetime         not null comment '记录创建时间',
    constraint fk_game_user_uid foreign key (uid) references user (id)
);