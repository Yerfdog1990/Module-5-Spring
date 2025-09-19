-- Table for users
CREATE TABLE users (
                       username VARCHAR(50) PRIMARY KEY,
                       password VARCHAR(100) NOT NULL,
                       enabled BOOLEAN NOT NULL
);

-- Table for authorities (roles)
CREATE TABLE authorities (
                             username VARCHAR(50) NOT NULL,
                             authority VARCHAR(50) NOT NULL,
                             CONSTRAINT fk_user FOREIGN KEY(username) REFERENCES users(username)
);

-- Optional unique index to prevent duplicate roles
CREATE UNIQUE INDEX ix_auth_username ON authorities (username, authority);
