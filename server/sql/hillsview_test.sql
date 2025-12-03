drop database if exists hillsview_test;
create database hillsview_test;
use hillsview_test;

create table orders (
	order_id int primary key auto_increment,
    full_name varchar (150) not null,
    address varchar (250) not null,
    email varchar (255) not null,
    order_date date not null,
    total decimal(10, 2) not null,
    order_status varchar (10) not null,
    cash_on_delivery bool not null
);

create table products (
	product_id int primary key auto_increment,
    product_name varchar (100) not null,
    price decimal(10, 2) not null,
    quantity int not null
);

create table order_products (
	order_product_id int primary key auto_increment,
    order_id int not null,
    product_id int not null,
    quantity int not null,
    price_at_purchase decimal(10, 2) not null,
    constraint fk_order_products_order_id
		foreign key (order_id)
        references orders(order_id)
        on delete cascade,
	constraint fk_order_products_product_id
		foreign key (product_id)
        references products(product_id)
        on delete cascade
);

delimiter //
create procedure set_known_good_state()
begin
    set sql_safe_updates = 0;

	delete from orders;
		alter table orders auto_increment = 1;
    
    insert into orders (full_name, address, email, order_date, total, order_status, cash_on_delivery)
	values
		('John Doe', "111 1st st, Town, NY, 11111", "doe@email.com", '2001-01-01', 10, "RECEIVED", false),
		('Mary Sue', "222 2nd st, Town, NY, 22222", "sue@email.com", '2002-02-02', 12, "PROCESSING", false),
		('Peter Parker', "333 3rd st, Town, NY, 33333", "parker@email.com", '2003-03-03', 15, "SHIPPED", true),
		('Bruce Wayne', "444 4th st, Town, NY, 44444", "wayne@email.com", '2004-04-04', 18, "COMPLETE", true);

  set sql_safe_updates = 1;
end //
delimiter ;