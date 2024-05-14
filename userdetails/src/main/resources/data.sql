-- drop table users;
create table users(
    userId int auto_increment primary key,
    userName varchar(20),
    age int,
    height double precision,
    Dob date,
    isDeleted boolean,
    -- gmail varchar(35),
    mobile bigint
);

    insert into users(userName,age,height,Dob,isDeleted,mobile)
    values('alex',23,156.2,'2000-09-14',TRUE,9949260690);
-- drop table roles;
create table roles(
    roleId int auto_increment primary key,
    roleName varchar(20)
);
insert into roles(roleName)
values('user');
-- drop table mails;
create table mails(
    gid int auto_increment primary key,
    gmail varchar(30),
    userid int
);


