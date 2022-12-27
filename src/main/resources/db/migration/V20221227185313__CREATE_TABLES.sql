CREATE TABLE kitchen (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(60) NOT NULL,

    PRIMARY KEY (id)
) engine=InnoDB default charset=utf8;

CREATE TABLE restaurant (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(60) NOT NULL,

    PRIMARY KEY (id)
) engine=InnoDB default charset=utf8;

CREATE TABLE city (
    id BIGINT NOT NULL  AUTO_INCREMENT,
    city_name VARCHAR(80) NOT NULL,
    state_name VARCHAR(80) NOT NULL,

    PRIMARY KEY (id)
) engine=InnoDB default charset=utf8;
