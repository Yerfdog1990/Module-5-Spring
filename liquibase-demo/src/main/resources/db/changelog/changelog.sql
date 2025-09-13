--liquibase formatted sql
--changeset Yerfdog1990:1
DROP TABLE IF EXISTS test_table;
CREATE TABLE test_table (
    test_id INT NOT NULL,
    student_name VARCHAR(255),
    test_column INT,
    PRIMARY KEY (test_id)
);

--changeset Yerfdog1990:2
DROP TABLE IF EXISTS citizen_table;
CREATE TABLE citizen_table (
    citizen_id INT NOT NULL,
    citizen_name VARCHAR(255),
    citizen_age INT,
    citizen_gender VARCHAR(255),
    citizen_address VARCHAR(255),
    citizen_phone VARCHAR(255),
    citizen_email VARCHAR(255),
    PRIMARY KEY (citizen_id)
);

--changeset Yerfdog1990:3
DROP TABLE IF EXISTS school_table;
CREATE TABLE school_table (
    school_id INT NOT NULL,
    school_name VARCHAR(255),
    school_address VARCHAR(255),
    PRIMARY KEY (school_id)
);