use ormlearn;
create table user(
us_id int primary key auto_increment,
us_name varchar(45) not null,
us_email varchar(45)
);

create table attempt(
at_id int primary key auto_increment,
at_date date,
at_us_id int,
at_score double,
foreign key(at_us_id) references user(us_id)
);

create table question(
qt_id int primary key auto_increment,
qt_text varchar(150) not null
);

create table attempt_question(
aq_id int primary key auto_increment,
aq_at_id int,
aq_qt_id int,
foreign key(aq_qt_id) references question(qt_id),
foreign key(aq_at_id) references attempt(at_id)
);

create table options(
op_id int primary key auto_increment,
op_qt_id int,
op_score double,
op_text varchar(100),
foreign key(op_qt_id) references question(qt_id)
);

create table attempt_option(
ao_id int primary key auto_increment,
ao_op_id int,
ao_aq_id int,
ao_selected bit,
foreign key(ao_op_id) references options(op_id),
foreign key(ao_aq_id) references attempt_question(aq_id)
);

insert into user(us_name,us_email) values("Harry","harry@mail.com");

insert into question(qt_text) values("What is the extension of the hyper text markup language file?");
insert into question(qt_text) values("What is the maximum level of heading tag can be used in a HTML page?");
insert into question(qt_text) values("The HTML document itself begins with <html> and ends </html>. State True of False");
insert into question(qt_text) values("Choose the right option to store text value in a variable");

insert into options(op_qt_id,op_score,op_text) values(1,0.0,".xhtm");
insert into options(op_qt_id,op_score,op_text) values(1,0.0,".ht");
insert into options(op_qt_id,op_score,op_text) values(1,1.0,".html");
insert into options(op_qt_id,op_score,op_text) values(1,0.0,".htmx");

insert into options(op_qt_id,op_score,op_text) values(2,0.0,"5");
insert into options(op_qt_id,op_score,op_text) values(2,0.0,"3");
insert into options(op_qt_id,op_score,op_text) values(2,0.0,"4");
insert into options(op_qt_id,op_score,op_text) values(2,1.0,"6");

insert into options(op_qt_id,op_score,op_text) values(3,0.0,"false");
insert into options(op_qt_id,op_score,op_text) values(3,1.0,"true");

insert into options(op_qt_id,op_score,op_text) values(4,0.5,"'John'");
insert into options(op_qt_id,op_score,op_text) values(4,0.0,"John");
insert into options(op_qt_id,op_score,op_text) values(4,0.5,'"John"');
insert into options(op_qt_id,op_score,op_text) values(4,0.0,"/John/");

insert into attempt(at_date,at_us_id,at_score) values("2020-01-29",1,2.5);

insert into attempt_question(aq_at_id,aq_qt_id) values(1,1);
insert into attempt_question(aq_at_id,aq_qt_id) values(1,2);
insert into attempt_question(aq_at_id,aq_qt_id) values(1,3);
insert into attempt_question(aq_at_id,aq_qt_id) values(1,4);

insert into attempt_option(ao_op_id,ao_aq_id,ao_selected) values(1,1,0);
insert into attempt_option(ao_op_id,ao_aq_id,ao_selected) values(2,1,0);
insert into attempt_option(ao_op_id,ao_aq_id,ao_selected) values(3,1,1);
insert into attempt_option(ao_op_id,ao_aq_id,ao_selected) values(4,1,0);

insert into attempt_option(ao_op_id,ao_aq_id,ao_selected) values(5,2,0);
insert into attempt_option(ao_op_id,ao_aq_id,ao_selected) values(6,2,1);
insert into attempt_option(ao_op_id,ao_aq_id,ao_selected) values(7,2,0);
insert into attempt_option(ao_op_id,ao_aq_id,ao_selected) values(8,2,0);

insert into attempt_option(ao_op_id,ao_aq_id,ao_selected) values(9,3,0);
insert into attempt_option(ao_op_id,ao_aq_id,ao_selected) values(10,3,1);

insert into attempt_option(ao_op_id,ao_aq_id,ao_selected) values(11,4,1);
insert into attempt_option(ao_op_id,ao_aq_id,ao_selected) values(12,4,0);
insert into attempt_option(ao_op_id,ao_aq_id,ao_selected) values(13,4,0);
insert into attempt_option(ao_op_id,ao_aq_id,ao_selected) values(14,4,0);