---------  users ----------------------
-- Clean up all data in the correct order to avoid foreign key violations
delete from USER_ROLE;
delete from CONTACT;
delete from PROFILE;
delete from ACTIVITY;
delete from USER_BELONG;
delete from TASK;
delete from SPRINT;
delete from PROJECT;
delete from USERS;

insert into USERS (ID, EMAIL, PASSWORD, FIRST_NAME, LAST_NAME, DISPLAY_NAME)
values (1,'user@gmail.com', '{noop}password', 'userFirstName', 'userLastName', 'userDisplayName'),
       (2,'admin@gmail.com', '{noop}admin', 'adminFirstName', 'adminLastName', 'adminDisplayName'),
       (3,'guest@gmail.com', '{noop}guest', 'guestFirstName', 'guestLastName', 'guestDisplayName'),
       (4,'manager@gmail.com', '{noop}manager', 'managerFirstName', 'managerLastName', 'managerDisplayName');

-- 0 DEV
-- 1 ADMIN
-- 2 MANAGER

insert into USER_ROLE (USER_ID, ROLE)
values (1, 0),
       (2, 0),
       (2, 1),
       (4, 2);

insert into PROFILE (ID, LAST_FAILED_LOGIN, LAST_LOGIN, MAIL_NOTIFICATIONS)
values (1, null, null, 49),
       (2, null, null, 14);

insert into CONTACT (ID, CODE, "VALUE")
values (1, 'skype', 'userSkype'),
       (1, 'mobile', '+01234567890'),
       (1, 'website', 'user.com'),
       (2, 'github', 'adminGitHub'),
       (2, 'telegram', 'adminTg'),
       (2, 'facebook', 'adminFb');


insert into PROJECT (ID, CODE, TITLE, DESCRIPTION, TYPE_CODE, PARENT_ID)
values (1,'PR1', 'PROJECT-1', 'test project 1', 'task_tracker', null),
       (2,'PR2', 'PROJECT-2', 'test project 2', 'task_tracker', 1);

insert into SPRINT (ID, STATUS_CODE, START_POINT, END_POINT, PROJECT_ID, TITLE)
values (1,'finished', '2023-05-01 08:05:10', '2023-05-07 17:10:01', 1, 'Sprint 1.001'),
       (2,'active', '2023-05-01 08:06:00', null, 1, 'Sprint 1.002'),
       (3,'active', '2023-05-01 08:07:00', null, 1, 'Sprint 1.003'),
       (4,'planning', '2023-05-01 08:08:00', null, 1, 'Sprint 1.004'),
       (5,'active', '2023-05-10 08:06:00', null, 2, 'Sprint 2.001'),
       (6,'planning', '2023-05-10 08:07:00', null, 2, 'Sprint 2.002'),
       (7,'planning', '2023-05-10 08:08:00', null, 2, 'Sprint 2.003');

insert into TASK (ID, TITLE, DESCRIPTION, TYPE_CODE, STATUS_CODE, PRIORITY_CODE, ESTIMATE, UPDATED, PROJECT_ID, SPRINT_ID, PARENT_ID, STARTPOINT)
values (1,'Data', null, 'epic', 'in_progress', 'normal', 3, '2023-05-15 09:05:10', 1, 1, null, '2023-05-15 09:05:10'),
       (2,'Trees', 'Trees desc', 'epic', 'in_progress', 'normal', 4, '2023-05-15 12:05:10', 1, 1, null, '2023-05-15 12:05:10'),
       (3,'task-3', null, 'task', 'ready_for_test', 'normal', 2, '2023-06-14 09:28:10', 2, 5, null, '2023-06-14 09:28:10'),
       (4,'task-4', null, 'task', 'ready_for_review', 'normal', 2, '2023-06-14 09:28:10', 2, 5, null, '2023-06-14 09:28:10'),
       (5,'task-5', null, 'task', 'todo', 'normal', 2, '2023-06-14 09:28:10', 2, 5, null, '2023-06-14 09:28:10'),
       (6,'task-6', null, 'task', 'done', 'normal', 2, '2023-06-14 09:28:10', 2, 5, null, '2023-06-14 09:28:10'),
       (7,'task-7', null, 'task', 'canceled', 'normal', 2, '2023-06-14 09:28:10', 2, 5, null, '2023-06-14 09:28:10');


insert into ACTIVITY (ID, AUTHOR_ID, TASK_ID, UPDATED, COMMENT, DESCRIPTION, ESTIMATE, TYPE_CODE, STATUS_CODE, PRIORITY_CODE)
values (1, 1, 1, '2023-05-15 09:05:10', null, 'Data', 3, 'epic', 'in_progress', 'low'),
       (2, 2, 1, '2023-05-15 12:25:10', null, 'Data', null, null, null, 'normal'),
       (3, 1, 1, '2023-05-15 14:05:10', null, 'Data', 4, null, null, null),
       (4, 1, 2, '2023-05-15 12:05:10', null, 'Trees', 4, 'epic', 'in_progress', 'normal');

insert into USER_BELONG (ID, OBJECT_ID, OBJECT_TYPE, USER_ID, USER_TYPE_CODE, STARTPOINT, ENDPOINT)
values (1, 1, 2, 2, 'task_developer', '2023-06-14 08:35:10', '2023-06-14 08:55:00'),
       (2, 1, 2, 2, 'task_reviewer', '2023-06-14 09:35:10', null),
       (3, 1, 2, 1, 'task_developer', '2023-06-13 12:35:00', null),
       (4, 1, 2, 1, 'task_tester', '2023-06-14 15:20:00', null),
       (5, 2, 2, 2, 'task_developer', '2023-06-08 07:10:00', null),
       (6, 2, 2, 1, 'task_developer', '2023-06-09 14:48:00', null),
       (7, 2, 2, 1, 'task_tester', '2023-06-10 16:37:00', null);
