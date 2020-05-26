create table liked
                 (
                 	id serial not null constraint liked_pk primary key,
                 	who int not null,
                 	whom int not null
                 )