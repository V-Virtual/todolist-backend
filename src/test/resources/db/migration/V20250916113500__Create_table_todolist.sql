create table t_todolist
(
    id   bigint not null auto_increment,
    text varchar(255),
    done boolean,
    primary key (id)
);