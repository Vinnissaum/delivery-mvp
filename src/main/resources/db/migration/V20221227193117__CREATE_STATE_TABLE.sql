CREATE TABLE state (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(80) NOT NULL,

    PRIMARY KEY (id)
) engine=InnoDB default charset=utf8;

INSERT INTO state (name) SELECT DISTINCT state_name FROM city;

ALTER TABLE city ADD COLUMN state_id BIGINT NOT NULL;

UPDATE city SET city.state_id = (SELECT state.id FROM state WHERE state.name = city.state_name);

ALTER TABLE city ADD CONSTRAINT fk_city_state

FOREIGN KEY (state_id) REFERENCES state (id);

ALTER TABLE city DROP COLUMN state_name;

ALTER TABLE city CHANGE city_name name VARCHAR(80) NOT NULL;
