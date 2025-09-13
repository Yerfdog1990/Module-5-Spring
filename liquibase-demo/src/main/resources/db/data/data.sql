--liquibase formatted sql
--changeset your.name:1
INSERT INTO test_table (test_id, student_name, test_column) VALUES (1, 'John Doe', 50);
INSERT INTO test_table (test_id, student_name, test_column) VALUES (2, 'Jane Smith', 60);
INSERT INTO test_table (test_id, student_name, test_column) VALUES (3, 'Bob Johnson', 70);
INSERT INTO test_table (test_id, student_name, test_column) VALUES (4, 'Mary Thompson', 80);
INSERT INTO test_table (test_id, student_name, test_column) VALUES (5, 'Jim Green', 55);
INSERT INTO test_table (test_id, student_name, test_column) VALUES (6, 'Emily Brown', 65);
INSERT INTO test_table (test_id, student_name, test_column) VALUES (7, 'Michael Davis', 75);
INSERT INTO test_table (test_id, student_name, test_column) VALUES (8, 'Sarah Miller', 85);
INSERT INTO test_table (test_id, student_name, test_column) VALUES (9, 'William Anderson', 57);
INSERT INTO test_table (test_id, student_name, test_column) VALUES (10, 'Amanda Johnson', 67);
INSERT INTO test_table (test_id, student_name, test_column) VALUES (11, 'Daniel Lee', 77);
INSERT INTO test_table (test_id, student_name, test_column) VALUES (12, 'Olivia Wilson', 87);


INSERT INTO citizen_table (citizen_id, citizen_name, citizen_age, citizen_gender, citizen_address, citizen_phone, citizen_email) VALUES (13, 'John Doe', 25, 'Male', '123 Main St', '123-456-7890', 'john.doe@example.com');
INSERT INTO citizen_table (citizen_id, citizen_name, citizen_age, citizen_gender, citizen_address, citizen_phone, citizen_email) VALUES (14, 'Jane Smith', 30, 'Female', '456 Elm St', '456-789-0123', 'jane.smith@example.com');
INSERT INTO citizen_table (citizen_id, citizen_name, citizen_age, citizen_gender, citizen_address, citizen_phone, citizen_email) VALUES (15, 'Bob Johnson', 35, 'Male', '789 Oak Ave', '789-012-3456', 'bob.johnson@example.com');
INSERT INTO citizen_table (citizen_id, citizen_name, citizen_age, citizen_gender, citizen_address, citizen_phone, citizen_email) VALUES (16, 'Mary Thompson', 40, 'Female', '101 Pine St', '101-234-5678', 'mary.thompson@example.com');
INSERT INTO citizen_table (citizen_id, citizen_name, citizen_age, citizen_gender, citizen_address, citizen_phone, citizen_email) VALUES (17, 'David Williams', 45, 'Male', '202 Maple St', '202-345-6789', 'david.williams@example.com');
INSERT INTO citizen_table (citizen_id, citizen_name, citizen_age, citizen_gender, citizen_address, citizen_phone, citizen_email) VALUES (18, 'Sarah Taylor', 50, 'Female', '303 Oak St', '303-456-7890', 'sarah.taylor@example.com');
INSERT INTO citizen_table (citizen_id, citizen_name, citizen_age, citizen_gender, citizen_address, citizen_phone, citizen_email) VALUES (19, 'Michael Brown', 55, 'Male', '404 Elm St', '404-567-8901', 'michael.brown@example.com');
INSERT INTO citizen_table (citizen_id, citizen_name, citizen_age, citizen_gender, citizen_address, citizen_phone, citizen_email) VALUES (20, 'Emily Davis', 60, 'Female', '505 Pine St', '505-678-9012', 'emily.davis@example.com');
INSERT INTO citizen_table (citizen_id, citizen_name, citizen_age, citizen_gender, citizen_address, citizen_phone, citizen_email) VALUES (21, 'David Anderson', 65, 'Male', '606 Maple St', '606-789-0123', 'david.anderson@example.com');
INSERT INTO citizen_table (citizen_id, citizen_name, citizen_age, citizen_gender, citizen_address, citizen_phone, citizen_email) VALUES (22, 'Sarah Wilson', 70, 'Female', '707 Oak St', '707-890-1234', 'sarah.wilson@example.com');

INSERT INTO school_table (school_id, school_name, school_address) VALUES (1, 'St. John the Baptist School', '123 Main St');
INSERT INTO school_table (school_id, school_name, school_address) VALUES (2, 'St. Mary Magdalene School', '456 College Ave');
INSERT INTO school_table (school_id, school_name, school_address) VALUES (3, 'St. Joseph School', '789 School Rd');
INSERT INTO school_table (school_id, school_name, school_address) VALUES (4, 'St. Anthony of Padua School', '101 Elm St');
INSERT INTO school_table (school_id, school_name, school_address) VALUES (5, 'St. Catherine of Siena School', '202 Oak St');
INSERT INTO school_table (school_id, school_name, school_address) VALUES (6, 'St. John the Evangelist School', '303 Maple St');
INSERT INTO school_table (school_id, school_name, school_address) VALUES (7, 'St. Patrick School', '404 Pine St');
INSERT INTO school_table (school_id, school_name, school_address) VALUES (8, 'St. Andrew School', '505 Elm St');
INSERT INTO school_table (school_id, school_name, school_address) VALUES (9, 'St. Thomas Aquinas School', '606 Oak St');
INSERT INTO school_table (school_id, school_name, school_address) VALUES (10, 'St. Anthony of Padua School', '707 Maple St');