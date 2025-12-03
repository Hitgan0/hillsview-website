drop database if exists hillsview_test;
create database hillsview_test;
use hillsview_test;

create table orders (
	order_id int primary key auto_increment,
    full_name varchar (150) not null,
    address varchar (250) not null,
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