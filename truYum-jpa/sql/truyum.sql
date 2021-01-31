drop database if exists truyum;
create database truyum;
use truyum;

create table MenuItems (
menu_item_id int auto_increment primary key,
menu_item_name varchar(30) not null,
menu_item_price float(2) not null,
menu_item_active boolean not null,
menu_item_date_of_launch date not null,
menu_item_category varchar(30) not null,
menu_item_free_delivery boolean not null
);

create table Users (
user_id int auto_increment primary key,
user_first_name varchar(30) not null,
user_last_name varchar(30) default null
);

create table Cart (
cart_user_id int,
cart_item_id int,
constraint primary key(cart_user_id,cart_item_id),
foreign key (cart_user_id) references Users(user_id),
foreign key (cart_item_id) references MenuItems(menu_item_id)
);

insert into MenuItems (menu_item_name, menu_item_price, menu_item_active, menu_item_date_of_launch, menu_item_category, menu_item_free_delivery) values (
"Sandwich", 99.00, true, "2017-03-15", "Main Course", true);
insert into MenuItems (menu_item_name, menu_item_price, menu_item_active, menu_item_date_of_launch, menu_item_category, menu_item_free_delivery) values (
"Burger", 129.00, true, "2017-12-23", "Main Course", false);
insert into MenuItems (menu_item_name, menu_item_price, menu_item_active, menu_item_date_of_launch, menu_item_category, menu_item_free_delivery) values (
"Pizza", 149.00, true, "2017-08-21", "Main Course", false);
insert into MenuItems (menu_item_name, menu_item_price, menu_item_active, menu_item_date_of_launch, menu_item_category,  menu_item_free_delivery) values (
"French Fries", 57.00, false, "2017-07-02", "Starters", true);
insert into MenuItems (menu_item_name, menu_item_price, menu_item_active, menu_item_date_of_launch, menu_item_category, menu_item_free_delivery) values (
"Chocolate Brownie", 32.00, true, "2022-11-02", "Dessert", true);
insert into Users (user_first_name, user_last_name) values ("William","Turner");
insert into Users (user_first_name, user_last_name) values ("Elizabeth","Swann");

insert into Cart values(1,1);
insert into Cart values(1,2);
insert into Cart values(1,3);
insert into Cart values(2,1);
insert into Cart values(2,2);
insert into Cart values(2,3);