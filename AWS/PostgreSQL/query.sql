drop table if exists people;

create table people (
	name varchar(50),
	age int
);

insert into people
values
('John Wick', 27),
('Michael Jackson', 34),
('Jimmy Hendrix', 59);

insert into	people values ('No One', 1);

select * from people;

update people set age = 62 where name = 'Jimmy Hendrix';

delete from people where age = 1;