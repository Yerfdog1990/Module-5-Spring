INSERT INTO users (id, name, email, password, role) VALUES 
(gen_random_uuid(), 'John Doe', 'john@example.com', '$2a$10$YOzgv2QZKNZQH9Qlc.QlUO8X9Qq4QQQ/n7RMiQE8AoKQ/jQKQ4qe', 'ADMIN'),
(gen_random_uuid(), 'Jane Doe', 'jane@example.com', '$2a$10$eZxbC3yLwYQgZQl9.QYQvO2Q8Rc4QXqRv/ZBKhQQQT6RqQQZ7kU2', 'ADMIN'),
(gen_random_uuid(), 'Alice Smith', 'alice@example.com', '$2a$10$7PZQxYBQyEzF3Q6RvUQfPO3QQE.xQQQQQQQQQQQQQQQQQQQQQQQ', 'USER'),
(gen_random_uuid(), 'Bob Johnson', 'bob@example.com', '$2a$10$kZQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQ', 'USER'),
(gen_random_uuid(), 'Emily Wilson', 'emily@example.com', '$2a$10$QQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQ', 'USER'),
(gen_random_uuid(), 'Mark Davis', 'mark@example.com', '$2a$10$QQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQ', 'USER'),
(gen_random_uuid(), 'Sarah Brown', 'sarah@example.com', '$2a$10$QQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQ', 'USER'),
(gen_random_uuid(), 'David Lee', 'david@example.com', '$2a$10$QQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQ', 'USER'),
(gen_random_uuid(), 'Michelle Green', 'michelle@example.com', '$2a$10$QQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQ', 'USER'),
(gen_random_uuid(), 'David Smith', 'david@example.com', '$2a$10$QQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQ', 'USER');