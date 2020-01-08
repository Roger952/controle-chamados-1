create table seg_admin(
    id INTEGER IDENTITY (1,1) primary key,
    login VARCHAR(100) NOT NULL,
    senha VARCHAR(25)  NOT NULL
)

create unique index ix_seg_admin_login on seg_admin(login asc);