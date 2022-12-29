create table order_item (
    id bigint not null auto_increment,
    note varchar(255),
    quantity smallint(6) not null,
    total_value decimal(10,2) not null,
    unit_price decimal(10,2) not null,
    order_id bigint not null,
    product_id bigint not null,
    primary key (id)
) engine=InnoDB default charset=utf8;

create table tb_order (
    id bigint not null auto_increment,
    address_complement varchar(60),
    address_district varchar(60) not null,
    address_number varchar(20) not null,
    address_street varchar(100) not null,
    address_zip_code varchar(9) not null,
    cancel_at datetime,
    confirmed_at datetime,
    created_at datetime not null,
    delivered_at datetime,
    status varchar(10) not null,
    ship_fee decimal(10,2) not null,
    sub_total decimal(10,2) not null,
    total_value decimal(10,2) not null,
    address_city bigint not null,
    client_id bigint not null,
    restaurant_id bigint not null,
    payment_type_id bigint not null,
    primary key (id)
) engine=InnoDB default charset=utf8;

alter table order_item add constraint fk_order_item_order foreign key (order_id) references tb_order (id);

alter table order_item add constraint fk_order_item_product foreign key (product_id) references product (id);

alter table tb_order add constraint fk_order_address_city foreign key (address_city) references city (id);

alter table tb_order add constraint fk_order_payment_type foreign key (payment_type_id) references payment_type (id);

alter table tb_order add constraint fk_order_restaurant foreign key (restaurant_id) references restaurant (id);

alter table tb_order add constraint fk_order_user foreign key (client_id) references tb_user (id);
