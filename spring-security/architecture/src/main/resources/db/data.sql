INSERT INTO users (username, password, enabled)
VALUES ('alice', '{bcrypt}$2a$10$zHQUrZgXtxrKbbGm3PfZgeuazE38l6Z75bFfF8EwP37WllYOkEjDa', true);
-- password = "mypassword"

INSERT INTO authorities (username, authority) VALUES ('alice', 'ROLE_USER');
