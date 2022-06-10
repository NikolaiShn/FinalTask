CREATE DATABASE IF NOT EXISTS ExchangeService CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE ExchangeService;

CREATE TABLE IF NOT EXISTS Roles(
id int PRIMARY KEY AUTO_INCREMENT,
role varchar(10) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS Users(
id int PRIMARY KEY AUTO_INCREMENT,
login varchar(50) UNIQUE NOT NULL,
password varchar(255) NOT NULL,
name varchar(255) NOT NULL,
last_name varchar(255) NOT NULL,
role_id int NOT NULL,
award double,
FOREIGN KEY (role_id) REFERENCES Roles(id)
);

CREATE TABLE IF NOT EXISTS Courses(
id int PRIMARY KEY AUTO_INCREMENT,
course_name varchar(255) UNIQUE NOT NULL,
cost double NOT NULL,
start_date timestamp NOT NULL,
end_date timestamp NOT NULL
);

CREATE TABLE IF NOT EXISTS Lessons_Form(
id int PRIMARY KEY AUTO_INCREMENT,
form_name varchar(255) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS Knowledge_Directories(
id int PRIMARY KEY AUTO_INCREMENT,
name varchar(255) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS Lessons(
id int PRIMARY KEY AUTO_INCREMENT,
course_id int NOT NULL,
lesson_form_id int NOT NULL,
lesson_name varchar(255),
description varchar(255) UNIQUE,
monday_date timestamp,
tuesday_date timestamp,
wednesday_date timestamp,
thursday_date timestamp,
friday_date timestamp,
cost varchar(255) NOT NULL,
FOREIGN KEY (course_id) REFERENCES Courses(id),
FOREIGN KEY (lesson_form_id) REFERENCES Lessons_Form(id)
);

CREATE TABLE IF NOT EXISTS Themes(
id int PRIMARY KEY AUTO_INCREMENT,
knowledge_directory_id int NOT NULL,
theme_name varchar(255) NOT NULL,
FOREIGN KEY (knowledge_directory_id) REFERENCES Knowledge_Directories(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Sections(
id int PRIMARY KEY AUTO_INCREMENT,
knowledge_directory_id int NOT NULL,
section_name varchar(255) NOT NULL,
FOREIGN KEY (knowledge_directory_id) REFERENCES Knowledge_Directories(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Users_Knowledge_Directories(
user_id int NOT NULL,
knowledge_directory_id int NOT NULL,
PRIMARY KEY (user_id, knowledge_directory_id),
FOREIGN KEY (user_id) REFERENCES Users(id),
FOREIGN KEY (knowledge_directory_id) REFERENCES Knowledge_Directories(id)
ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Lessons_Reviews(
id int PRIMARY KEY AUTO_INCREMENT,
lesson_id int NOT NULL,
text varchar(255),
FOREIGN KEY (lesson_id) REFERENCES Lessons(id)
);

CREATE TABLE IF NOT EXISTS Course_Reviews(
id int PRIMARY KEY AUTO_INCREMENT,
course_id int NOT NULL,
text varchar(255) NOT NULL,
FOREIGN KEY (course_id) REFERENCES Courses(id)
);

CREATE TABLE IF NOT EXISTS Users_Courses(
user_id int NOT NULL,
course_id int NOT NULL,
PRIMARY KEY (user_id, course_id),
FOREIGN KEY (user_id) REFERENCES Users(id),
FOREIGN KEY (course_id) REFERENCES Courses(id)
ON DELETE CASCADE
);

/*
//***Заполнение таблиц***
// - пароль password у всех юзеров
*/


INSERT INTO Roles(role) VALUES ("ROLE_ADMIN");
INSERT INTO Roles(role) VALUES ("ROLE_USER");

INSERT INTO Users(login, password, name, last_name, role_id) VALUES ("testuser", "$2a$12$OLhaHMa.GYk.2Bp3OI44S.q3m4JGhEHfdkujm0X1Iu9yhNrdWSkOe", "ivan", "pupkin", 2);
INSERT INTO Users(login, password, name, last_name, role_id) VALUES ("admin1", "$2a$12$OLhaHMa.GYk.2Bp3OI44S.q3m4JGhEHfdkujm0X1Iu9yhNrdWSkOe", "vitaly", "pupkin", 1);
INSERT INTO Users(login, password, name, last_name, role_id) VALUES ("admin2", "$2a$12$OLhaHMa.GYk.2Bp3OI44S.q3m4JGhEHfdkujm0X1Iu9yhNrdWSkOe", "kirill", "pupkin", 1);
INSERT INTO Users(login, password, name, last_name, role_id) VALUES ("admin3", "$2a$12$OLhaHMa.GYk.2Bp3OI44S.q3m4JGhEHfdkujm0X1Iu9yhNrdWSkOe", "artyom", "pupkin", 1);
INSERT INTO Users(login, password, name, last_name, role_id) VALUES ("admin4", "$2a$12$OLhaHMa.GYk.2Bp3OI44S.q3m4JGhEHfdkujm0X1Iu9yhNrdWSkOe", "evgeniy", "pupkin", 1);

INSERT INTO Knowledge_Directories(name) VALUES ("литература");
INSERT INTO Knowledge_Directories(name) VALUES ("математика");
INSERT INTO Knowledge_Directories(name) VALUES ("физика");

INSERT INTO Themes(knowledge_directory_id, theme_name) VALUES (1, "Булгаков");
INSERT INTO Themes(knowledge_directory_id, theme_name) VALUES (1, "Горький");
INSERT INTO Themes(knowledge_directory_id, theme_name) VALUES (1, "Куприн");

INSERT INTO Themes(knowledge_directory_id, theme_name) VALUES (2, "стереометрия");
INSERT INTO Themes(knowledge_directory_id, theme_name) VALUES (2, "планиметрия");
INSERT INTO Themes(knowledge_directory_id, theme_name) VALUES (2, "производные");

INSERT INTO Themes(knowledge_directory_id, theme_name) VALUES (3, "электричество");
INSERT INTO Themes(knowledge_directory_id, theme_name) VALUES (3, "движение");

INSERT INTO Sections(knowledge_directory_id, section_name) VALUES (3, "механика");
INSERT INTO Sections(knowledge_directory_id, section_name) VALUES (1, "русская");
INSERT INTO Sections(knowledge_directory_id, section_name) VALUES (1, "греческая");
INSERT INTO Sections(knowledge_directory_id, section_name) VALUES (2, "геометрия");

INSERT INTO Users_Knowledge_Directories(user_id, knowledge_directory_id) VALUES (1, 1);
INSERT INTO Users_Knowledge_Directories(user_id, knowledge_directory_id) VALUES (2, 2);
INSERT INTO Users_Knowledge_Directories(user_id, knowledge_directory_id) VALUES (3, 3);

INSERT INTO Courses(course_name, cost, start_date, end_date) VALUES ("КурсКниги", 40.0, '2022-06-05T00:00', '2022-06-20T00:00');
INSERT INTO Courses(course_name, cost, start_date, end_date) VALUES ("КурсПи", 40.0, '2022-06-05T00:00', '2022-06-25T00:00');
INSERT INTO Courses(course_name, cost, start_date, end_date) VALUES ("КурсПрироды", 40.0, '2022-06-05T00:00', '2022-06-22T00:00');

INSERT INTO Lessons_Form(form_name) VALUES ("индивидуальное");
INSERT INTO Lessons_Form(form_name) VALUES ("групповое");

INSERT INTO Lessons(course_id, lesson_form_id, lesson_name, description, monday_date, tuesday_date, wednesday_date, thursday_date, friday_date, cost) VALUES (1, 1, "урок1", "датадата", '2022-06-05T12:00', '2022-06-05T12:00', '2022-06-05T12:00', '2022-06-05T12:00', '2022-06-05T12:00', 10.0);
INSERT INTO Lessons(course_id, lesson_form_id, lesson_name, description, monday_date, tuesday_date, wednesday_date, thursday_date, friday_date, cost) VALUES (2, 2, "урок2", "датадата", '2022-06-05T13:00', '2022-06-05T13:00', null, '2022-06-05T13:00', '2022-06-05T13:00', 11.0);
INSERT INTO Lessons(course_id, lesson_form_id, lesson_name, description, monday_date, tuesday_date, wednesday_date, thursday_date, friday_date, cost) VALUES (3, 2, "урок3", "датадата", '2022-06-05T14:00', null, '2022-06-05T14:00', '2022-06-05T14:00', '2022-06-05T14:00', 12.0);
INSERT INTO Lessons(course_id, lesson_form_id, lesson_name, description, monday_date, tuesday_date, wednesday_date, thursday_date, friday_date, cost) VALUES (3, 2, "урок4", "датадата", '2022-06-05T15:00', null, '2022-06-05T15:00', '2022-06-05T15:00', '2022-06-05T15:00', 13.0);

INSERT INTO Lessons_Reviews(lesson_id, text) VALUES (1, "5 звёзд");
INSERT INTO Lessons_Reviews(lesson_id, text) VALUES (2, "пушка");
INSERT INTO Lessons_Reviews(lesson_id, text) VALUES (3, "втф");
INSERT INTO Lessons_Reviews(lesson_id, text) VALUES (4, "серьёзно");


INSERT INTO Course_Reviews(course_id, text) VALUES (1, "отличный курс");
INSERT INTO Course_Reviews(course_id, text) VALUES (2, "такое себе как по мне");
INSERT INTO Course_Reviews(course_id, text) VALUES (3, "*****");

INSERT INTO Users_Courses(user_id, course_id) VALUES (1, 1);
INSERT INTO Users_Courses(user_id, course_id) VALUES (2, 2);
INSERT INTO Users_Courses(user_id, course_id) VALUES (3, 1);
INSERT INTO Users_Courses(user_id, course_id) VALUES (4, 3);