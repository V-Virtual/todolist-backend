create table t_todo
(
    id   bigint not null auto_increment,
    text varchar(255),
    done boolean,
    primary key (id)
);