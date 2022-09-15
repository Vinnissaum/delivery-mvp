INSERT INTO kitchen (name) VALUES ('Tailandesa')
INSERT INTO kitchen (name) VALUES ('Indiana')

INSERT INTO restaurant (name, shipping_fee, kitchen_id) VALUES ('Indian foodtruck', 5.00, 2)
INSERT INTO restaurant (name, shipping_fee, kitchen_id) VALUES ('Sasa Kamar gourmet', 7.00, 1)

INSERT INTO state (name) VALUES ('MG')
INSERT INTO state (name) VALUES ('RJ')

INSERT INTO city (name, state_id) VALUES ('Sao Lourenco', 1)
INSERT INTO city (name, state_id) VALUES ('Paraty', 2)

INSERT INTO payment_type (description) VALUES ('credit card')

INSERT INTO permission (name, description) VALUES ('admin', 'grant all permissions')
