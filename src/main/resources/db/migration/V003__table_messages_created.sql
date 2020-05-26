create table messages
                 (
                 	sender_id int not null,
                 	receiver_id int not null,
                 	text varchar not null,
                 	message_id serial not null constraint messages_pk primary key,
                 	time timestamp default timestamptz(CURRENT_DATE, CURRENT_TIME) not null
                 )
