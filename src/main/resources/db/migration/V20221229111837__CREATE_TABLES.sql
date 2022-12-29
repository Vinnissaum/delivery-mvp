create table if not exists payment_type (id bigint not null auto_increment, description varchar(60), primary key (id)) engine=InnoDB;

create table if not exists permission (id bigint not null auto_increment, description varchar(60), name varchar(30), primary key (id)) engine=InnoDB;

create table if not exists permissions_group (permission_id bigint not null, tb_group_id bigint not null) engine=InnoDB;

create table if not exists product (
    id bigint not null auto_increment,
    description varchar(60),
    is_active bit not null,
    name varchar(30),
    price decimal(19,2),
    restaurant_id bigint,
    primary key (id)
) engine=InnoDB;

drop table restaurant;

create table restaurant (
    id bigint not null auto_increment,
    address_complement varchar(60),
    address_district varchar(60),
    address_number varchar(15),
    address_street varchar(60),
    address_zip_code varchar(15),
    created_at datetime not null,
    name varchar(60),
    shipping_fee decimal(19,2),
    updated_at datetime not null,
    address_city bigint,
    kitchen_id bigint, primary key (id)
) engine=InnoDB;

create table restaurant_payment_type (restaurant_id bigint not null, payment_type_id bigint not null) engine=InnoDB;

create table tb_group (id bigint not null auto_increment, name varchar(255), primary key (id)) engine=InnoDB;

create table tb_user (
    id bigint not null,
    email varchar(255) not null,
    name varchar(255) not null,
    password varchar(60) not null,
    register_date datetime not null,
    primary key (id)
) engine=InnoDB;

create table user_group (tb_user_id bigint not null, tb_group_id bigint not null) engine=InnoDB;

alter table permissions_group add constraint fk_group_permissions foreign key (tb_group_id) references permission (id);

alter table permissions_group add constraint fk_permissions_group foreign key (permission_id) references tb_group (id);

alter table product add constraint fk_product_restaurant foreign key (restaurant_id) references restaurant (id);

alter table restaurant add constraint fk_restaurant_city foreign key (address_city) references city (id);

alter table restaurant add constraint fk_restaurant_kitchen foreign key (kitchen_id) references kitchen (id);

alter table restaurant_payment_type add constraint fk_restaurant_payment_type foreign key (payment_type_id) references payment_type (id);

alter table restaurant_payment_type add constraint fk_payment_type_restaurant foreign key (restaurant_id) references restaurant (id);

alter table user_group add constraint fk_user_group foreign key (tb_group_id) references tb_group (id);

alter table user_group add constraint fk_group_user foreign key (tb_user_id) references tb_user (id);
