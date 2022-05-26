CREATE DATABASE IF NOT EXISTS ExchangeService CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE ExchangeService;

CREATE TABLE IF NOT EXISTS Profiles(
id int PRIMARY KEY AUTO_INCREMENT,
name varchar(255) NOT NULL,
lastName varchar(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS Roles(
id int PRIMARY KEY AUTO_INCREMENT,
role varchar(10) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS Users(
id int PRIMARY KEY AUTO_INCREMENT,
login varchar(50) UNIQUE NOT NULL,
password varchar(255) NOT NULL,
role_id int NOT NULL,
profile_id int UNIQUE NOT NULL,
FOREIGN KEY (profile_id) REFERENCES Profiles(id),
FOREIGN KEY (role_id) REFERENCES Roles(id)
);

CREATE TABLE IF NOT EXISTS Lessons(
id int PRIMARY KEY AUTO_INCREMENT,
name varchar(255),
description varchar(255),
cost varchar(255)
);

CREATE TABLE IF NOT EXISTS KnowledgeDirectories(
id int PRIMARY KEY AUTO_INCREMENT,
name varchar(255) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS Themes(
id int PRIMARY KEY AUTO_INCREMENT,
theme_name varchar(255) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS Sections(
id int PRIMARY KEY AUTO_INCREMENT,
section_name varchar(255) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS Schedules(
id int PRIMARY KEY AUTO_INCREMENT
);

CREATE TABLE IF NOT EXISTS ProfileKnowledgeDirectories(
id int PRIMARY KEY AUTO_INCREMENT,
profile_id int NOT NULL,
knowledge_directory_id int NOT NULL,
FOREIGN KEY (profile_id) REFERENCES Profiles(id),
FOREIGN KEY (knowledge_directory_id) REFERENCES KnowledgeDirectories(id)
);

CREATE TABLE IF NOT EXISTS LessonsReviews(
id int PRIMARY KEY AUTO_INCREMENT,
lesson_id int NOT NULL,
text varchar(255),
FOREIGN KEY (lesson_id) REFERENCES Lessons(id)
);

CREATE TABLE IF NOT EXISTS KnowledgeDirectoriesThemes(
id int PRIMARY KEY AUTO_INCREMENT,
knowledge_directory_id int NOT NULL,
theme_id int NOT NULL,
FOREIGN KEY (knowledge_directory_id) REFERENCES KnowledgeDirectories(id),
FOREIGN KEY (theme_id) REFERENCES Themes(id)
);

CREATE TABLE IF NOT EXISTS KnowledgeDirectoriesSections(
id int PRIMARY KEY AUTO_INCREMENT,
knowledge_directory_id int NOT NULL,
section_id int NOT NULL,
FOREIGN KEY (knowledge_directory_id) REFERENCES KnowledgeDirectories(id),
FOREIGN KEY (section_id) REFERENCES Sections(id)
);

CREATE TABLE IF NOT EXISTS Courses(
id int PRIMARY KEY AUTO_INCREMENT,
name varchar(255) UNIQUE NOT NULL,
schedule_id int NOT NULL,
cost double NOT NULL,
FOREIGN KEY (schedule_id) REFERENCES Schedules(id)
);

CREATE TABLE IF NOT EXISTS CourseReviews(
id int PRIMARY KEY AUTO_INCREMENT,
course_id int NOT NULL,
text varchar(255) NOT NULL,
FOREIGN KEY (course_id) REFERENCES Courses(id)
);

CREATE TABLE IF NOT EXISTS CourseLessons(
id int PRIMARY KEY AUTO_INCREMENT,
lesson_id int NOT NULL,
course_id int NOT NULL,
FOREIGN KEY (course_id) REFERENCES Courses(id),
FOREIGN KEY (lesson_id) REFERENCES Lessons(id)
);

CREATE TABLE IF NOT EXISTS ProfileCourses(
id int PRIMARY KEY AUTO_INCREMENT,
profile_id int NOT NULL,
course_id int NOT NULL,
FOREIGN KEY (profile_id) REFERENCES Profiles(id),
FOREIGN KEY (course_id) REFERENCES Courses(id)
);

CREATE TABLE IF NOT EXISTS SchedulesDates(
id int PRIMARY KEY AUTO_INCREMENT,
schedule_id int NOT NULL,
date timestamp NOT NULL,
FOREIGN KEY (schedule_id) REFERENCES Schedules(id)
);

/*
//***Заполнение таблиц***
// - пароль password у всех юзеров
*/

INSERT INTO Profiles(name, lastName) VALUES ("ivan", "pupkin");
INSERT INTO Profiles(name, lastName) VALUES ("vitaly", "pupkin");
INSERT INTO Profiles(name, lastName) VALUES ("kirill", "pupkin");
INSERT INTO Profiles(name, lastName) VALUES ("artyom", "pupkin");
INSERT INTO Profiles(name, lastName) VALUES ("evgeniy", "pupkin");

INSERT INTO Roles(role) VALUES ("ROLE_ADMIN");
INSERT INTO Roles(role) VALUES ("ROLE_USER");

INSERT INTO Users(login, password, role_id, profile_id) VALUES ("testuser", "$2a$12$OLhaHMa.GYk.2Bp3OI44S.q3m4JGhEHfdkujm0X1Iu9yhNrdWSkOe", 2, 1);
INSERT INTO Users(login, password, role_id, profile_id) VALUES ("admin1", "$2a$12$OLhaHMa.GYk.2Bp3OI44S.q3m4JGhEHfdkujm0X1Iu9yhNrdWSkOe", 1, 2);
INSERT INTO Users(login, password, role_id, profile_id) VALUES ("admin2", "$2a$12$OLhaHMa.GYk.2Bp3OI44S.q3m4JGhEHfdkujm0X1Iu9yhNrdWSkOe", 1, 3);
INSERT INTO Users(login, password, role_id, profile_id) VALUES ("admin3", "$2a$12$OLhaHMa.GYk.2Bp3OI44S.q3m4JGhEHfdkujm0X1Iu9yhNrdWSkOe", 1, 4);
INSERT INTO Users(login, password, role_id, profile_id) VALUES ("admin4", "$2a$12$OLhaHMa.GYk.2Bp3OI44S.q3m4JGhEHfdkujm0X1Iu9yhNrdWSkOe", 1, 5);

INSERT INTO KnowledgeDirectories(name) VALUES ("литература");
INSERT INTO KnowledgeDirectories(name) VALUES ("математика");
INSERT INTO KnowledgeDirectories(name) VALUES ("физика");

INSERT INTO Themes(theme_name) VALUES ("Булгаков");
INSERT INTO Themes(theme_name) VALUES ("Горький");
INSERT INTO Themes(theme_name) VALUES ("Куприн");

INSERT INTO Themes(theme_name) VALUES ("стереометрия");
INSERT INTO Themes(theme_name) VALUES ("планиметрия");
INSERT INTO Themes(theme_name) VALUES ("производные");

INSERT INTO Themes(theme_name) VALUES ("электричество");
INSERT INTO Themes(theme_name) VALUES ("движение");

INSERT INTO Sections(section_name) VALUES ("механика");
INSERT INTO Sections(section_name) VALUES ("русская");
INSERT INTO Sections(section_name) VALUES ("греческая");
INSERT INTO Sections(section_name) VALUES ("геометрия");

INSERT INTO KnowledgeDirectoriesThemes(knowledge_directory_id, theme_id) VALUES (1, 1);
INSERT INTO KnowledgeDirectoriesThemes(knowledge_directory_id, theme_id) VALUES (1, 2);
INSERT INTO KnowledgeDirectoriesThemes(knowledge_directory_id, theme_id) VALUES (1, 3);

INSERT INTO KnowledgeDirectoriesThemes(knowledge_directory_id, theme_id) VALUES (2, 4);
INSERT INTO KnowledgeDirectoriesThemes(knowledge_directory_id, theme_id) VALUES (2, 5);
INSERT INTO KnowledgeDirectoriesThemes(knowledge_directory_id, theme_id) VALUES (2, 6);

INSERT INTO KnowledgeDirectoriesThemes(knowledge_directory_id, theme_id) VALUES (3, 7);
INSERT INTO KnowledgeDirectoriesThemes(knowledge_directory_id, theme_id) VALUES (3, 8);

INSERT INTO KnowledgeDirectoriesSections(knowledge_directory_id, section_id) VALUES (1, 2);
INSERT INTO KnowledgeDirectoriesSections(knowledge_directory_id, section_id) VALUES (1, 3);
INSERT INTO KnowledgeDirectoriesSections(knowledge_directory_id, section_id) VALUES (2, 4);
INSERT INTO KnowledgeDirectoriesSections(knowledge_directory_id, section_id) VALUES (3, 1);

INSERT INTO ProfileKnowledgeDirectories(profile_id, knowledge_directory_id) VALUES (1, 1);
INSERT INTO ProfileKnowledgeDirectories(profile_id, knowledge_directory_id) VALUES (2, 2);
INSERT INTO ProfileKnowledgeDirectories(profile_id, knowledge_directory_id) VALUES (3, 3);

INSERT INTO Lessons(name, description, cost) VALUES ("урок1", "датадата", 10.0);
INSERT INTO Lessons(name, description, cost) VALUES ("урок2", "датадата", 11.0);
INSERT INTO Lessons(name, description, cost) VALUES ("урок3", "датадата", 12.0);
INSERT INTO Lessons(name, description, cost) VALUES ("урок4", "датадата", 13.0);

INSERT INTO LessonsReviews(lesson_id, text) VALUES (1, "5 звёзд");
INSERT INTO LessonsReviews(lesson_id, text) VALUES (2, "пушка");
INSERT INTO LessonsReviews(lesson_id, text) VALUES (3, "втф");
INSERT INTO LessonsReviews(lesson_id, text) VALUES (4, "серьёзно");

INSERT INTO Schedules() VALUES();
INSERT INTO Schedules() VALUES();
INSERT INTO Schedules() VALUES();
INSERT INTO Schedules() VALUES();

INSERT INTO SchedulesDates(schedule_id, date) VALUES (1,'2022-06-05T12:00');
INSERT INTO SchedulesDates(schedule_id, date) VALUES (1,'2022-06-05T13:00');
INSERT INTO SchedulesDates(schedule_id, date) VALUES (1,'2022-06-05T14:00');
INSERT INTO SchedulesDates(schedule_id, date) VALUES (1,'2022-06-05T15:00');
INSERT INTO SchedulesDates(schedule_id, date) VALUES (1,'2022-06-05T16:00');

INSERT INTO SchedulesDates(schedule_id, date) VALUES (2,'2022-06-05T08:00');
INSERT INTO SchedulesDates(schedule_id, date) VALUES (2,'2022-06-05T09:00');
INSERT INTO SchedulesDates(schedule_id, date) VALUES (2,'2022-06-05T10:00');
INSERT INTO SchedulesDates(schedule_id, date) VALUES (2,'2022-06-05T11:00');
INSERT INTO SchedulesDates(schedule_id, date) VALUES (2,'2022-06-05T12:00');

INSERT INTO SchedulesDates(schedule_id, date) VALUES (3,'2022-06-05T13:00');
INSERT INTO SchedulesDates(schedule_id, date) VALUES (3,'2022-06-05T14:00');
INSERT INTO SchedulesDates(schedule_id, date) VALUES (3,'2022-06-05T15:00');
INSERT INTO SchedulesDates(schedule_id, date) VALUES (3,'2022-06-05T16:00');
INSERT INTO SchedulesDates(schedule_id, date) VALUES (3,'2022-06-05T17:00');

INSERT INTO SchedulesDates(schedule_id, date) VALUES (4,'2022-06-05T09:00');
INSERT INTO SchedulesDates(schedule_id, date) VALUES (4,'2022-06-05T10:00');
INSERT INTO SchedulesDates(schedule_id, date) VALUES (4,'2022-06-05T11:00');
INSERT INTO SchedulesDates(schedule_id, date) VALUES (4,'2022-06-05T12:00');
INSERT INTO SchedulesDates(schedule_id, date) VALUES (4,'2022-06-05T13:00');

INSERT INTO Courses(name, schedule_id, cost) VALUES ("КурсКниги", 1, 40.0);
INSERT INTO Courses(name, schedule_id, cost) VALUES ("КурсПи", 2, 40.0);
INSERT INTO Courses(name, schedule_id, cost) VALUES ("КурсПрироды", 3, 40.0);

INSERT INTO CourseReviews(course_id, text) VALUES (1, "отличный курс");
INSERT INTO CourseReviews(course_id, text) VALUES (2, "такое себе как по мне");
INSERT INTO CourseReviews(course_id, text) VALUES (3, "*****");

INSERT INTO CourseLessons(lesson_id, course_id) VALUES (1, 1);
INSERT INTO CourseLessons(lesson_id, course_id) VALUES (2, 1);
INSERT INTO CourseLessons(lesson_id, course_id) VALUES (3, 1);
INSERT INTO CourseLessons(lesson_id, course_id) VALUES (4, 1);

INSERT INTO CourseLessons(lesson_id, course_id) VALUES (1, 2);
INSERT INTO CourseLessons(lesson_id, course_id) VALUES (2, 2);

INSERT INTO CourseLessons(lesson_id, course_id) VALUES (1, 3);
INSERT INTO CourseLessons(lesson_id, course_id) VALUES (2, 3);
INSERT INTO CourseLessons(lesson_id, course_id) VALUES (3, 3);

INSERT INTO ProfileCourses(profile_id, course_id) VALUES (1, 1);
INSERT INTO ProfileCourses(profile_id, course_id) VALUES (2, 2);
INSERT INTO ProfileCourses(profile_id, course_id) VALUES (3, 1);
INSERT INTO ProfileCourses(profile_id, course_id) VALUES (4, 3);