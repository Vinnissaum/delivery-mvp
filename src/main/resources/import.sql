INSERT INTO kitchen (name) VALUES ('Tailandesa');
INSERT INTO kitchen (name) VALUES ('Indiana');

INSERT INTO state (name) VALUES ('MG');
INSERT INTO state (name) VALUES ('RJ');

INSERT INTO city (name, state_id) VALUES ('Sao Lourenco', 1);
INSERT INTO city (name, state_id) VALUES ('Paraty', 2);

INSERT INTO restaurant (name, shipping_fee, kitchen_id, created_at, updated_at) VALUES ('Indian foodtruck', 5.00, 2, utc_timestamp, utc_timestamp);
INSERT INTO restaurant (name, shipping_fee, kitchen_id, address_zip_code, address_district, address_street, address_number, address_complement, address_city, created_at, updated_at) VALUES ('Sasa Kamar gourmet', 7.00, 1, "12222-222", "Sunset", "My Sunshine", "2806", "Ap: 2002", 1,  utc_timestamp, utc_timestamp);

INSERT INTO product (name, description, price, is_active, restaurant_id) VALUES ('Pizza', 'Samia s pizza', 77.00, true, 2);
INSERT INTO product (name, description, price, is_active, restaurant_id) VALUES ('Barbecue', 'churrascao', 20.00, true, 1);

INSERT INTO payment_type (description) VALUES ('credit card'), ('debit card'), ('money');

INSERT INTO permission (name, description) VALUES ('admin', 'grant all permissions');

INSERT INTO restaurant_payment_type (restaurant_id, payment_type_id) VALUES (2, 1), (2, 2), (1, 3);
