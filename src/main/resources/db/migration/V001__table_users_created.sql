create table users
                 (
                 	id serial not null constraint users_pk primary key,
                 	login varchar not null,
                 	password varchar not null,
                 	url varchar default 'https://www.vippng.com/png/detail/202-2026524_person-icon-default-user-icon-png.png'::character varying not null,
                 	name varchar not null,
                 	surname varchar not null,
                 	last_login_time timestamp default timestamptz(CURRENT_DATE, CURRENT_TIME),
                 	job varchar
                 )