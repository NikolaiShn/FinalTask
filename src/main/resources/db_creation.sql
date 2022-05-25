CREATE DATABASE IF NOT EXISTS ExchangeService CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE ExchangeService;

CREATE TABLE IF NOT EXISTS Users(
id int PRIMARY KEY AUTO_INCREMENT,
login varchar(50) NOT NULL UNIQUE,
password varchar(255) NOT NULL,
role varchar(50) NOT NULL
);
--
--CREATE TABLE IF NOT EXISTS Rooms(
--id int PRIMARY KEY AUTO_INCREMENT,
--cost double NOT NULL,
--number int NOT NULL UNIQUE,
--capacity int NOT NULL,
--roomState varchar(20) NOT NULL,
--stars int NOT NULL
--);
--
--CREATE TABLE IF NOT EXISTS Services(
--id int PRIMARY KEY AUTO_INCREMENT,
--cost double NOT NULL,
--description varchar(200) NOT NULL UNIQUE
--);
--
--CREATE TABLE IF NOT EXISTS OrderRooms(
--id int PRIMARY KEY AUTO_INCREMENT,
--room_id int NOT NULL,
--person_id int NOT NULL,
--plannedSettleDate timestamp NOT NULL,
--actualSettleDate timestamp,
--plannedEvictDate timestamp NOT NULL,
--actualEvictDate timestamp,
--orderState varchar(20) NOT NULL,
--FOREIGN KEY (room_id) REFERENCES Rooms(id),
--FOREIGN KEY (person_id) REFERENCES Persons(id)
--);
--
--CREATE TABLE IF NOT EXISTS OrderServices(
--id int PRIMARY KEY AUTO_INCREMENT,
--service_id int NOT NULL,
--person_id int NOT NULL,
--plannedSettleDate timestamp NOT NULL,
--actualSettleDate timestamp,
--plannedEvictDate timestamp NOT NULL,
--actualEvictDate timestamp,
--orderState varchar(20) NOT NULL,
--FOREIGN KEY (service_id) REFERENCES Services(id),
--FOREIGN KEY (person_id) REFERENCES Persons(id)
--);

INSERT INTO Users(login, password, role) VALUES ("testuser", "$2a$12$OLhaHMa.GYk.2Bp3OI44S.q3m4JGhEHfdkujm0X1Iu9yhNrdWSkOe", "ROLE_USER");
INSERT INTO Users(login, password, role) VALUES ("admin", "$2a$12$OLhaHMa.GYk.2Bp3OI44S.q3m4JGhEHfdkujm0X1Iu9yhNrdWSkOe", "ROLE_ADMIN");
--
--INSERT INTO Persons (name, lastName, dateOfBirth, otherInfo) VALUES ("Валерий", "Пупкин", '1987-02-03', "юрист") ;
--INSERT INTO Persons (name, lastName, dateOfBirth, otherInfo) VALUES ("Юрий", "Иванов", '1972-09-07', "водитель") ;
--INSERT INTO Persons (name, lastName, dateOfBirth, otherInfo) VALUES ("Сергей", "Пчёлкин", '1988-08-07', "пчеловод") ;
--INSERT INTO Persons (name, lastName, dateOfBirth, otherInfo) VALUES ("Дмитрий", "Пупкин", '2000-08-09', "студент") ;
--INSERT INTO Persons (name, lastName, dateOfBirth, otherInfo) VALUES ("Виктор", "Смолов", '1996-04-03', "менеджер") ;
--INSERT INTO Persons (name, lastName, dateOfBirth, otherInfo) VALUES ("Евгений", "Иванчик", '1988-03-03', "бухгалтер") ;
--
--INSERT INTO Rooms (cost, number, capacity, roomState, stars) VALUES (30.0, 1, 3, "BOOKED", 5) ;
--INSERT INTO Rooms (cost, number, capacity, roomState, stars) VALUES (25.0, 2, 2, "REPAIRED", 4);
--INSERT INTO Rooms (cost, number, capacity, roomState, stars) VALUES (50.0, 3, 3, "BOOKED", 5) ;
--INSERT INTO Rooms (cost, number, capacity, roomState, stars) VALUES (55.0, 25, 2, "BOOKED", 5);
--INSERT INTO Rooms (cost, number, capacity, roomState, stars) VALUES (34.0, 5, 3, "EMPTY", 4);
--
--INSERT INTO Services (cost, description) VALUES (5.0, "завтрак");
--INSERT INTO Services (cost, description) VALUES (7.0, "обед");
--INSERT INTO Services (cost, description) VALUES (6.0, "ужин");
--INSERT INTO Services (cost, description) VALUES (10.0, "экскурсия в музей");
--
--INSERT INTO OrderRooms (room_id, person_id, plannedSettleDate, actualSettleDate, plannedEvictDate, actualEvictDate, orderState) VALUES (1, 1, '2022-04-15T12:20', null, '2022-04-19T12:20', null, "NEW");
--INSERT INTO OrderRooms (room_id, person_id, plannedSettleDate, actualSettleDate, plannedEvictDate, actualEvictDate, orderState) VALUES (3, 2, '2022-03-14T12:20', null, '2022-03-24T12:20', null, "NEW");
--INSERT INTO OrderRooms (room_id, person_id, plannedSettleDate, actualSettleDate, plannedEvictDate, actualEvictDate, orderState) VALUES (4, 3, '2022-05-01T20:00', null, '2022-05-07T13:00', null, "NEW");
--INSERT INTO OrderRooms (room_id, person_id, plannedSettleDate, actualSettleDate, plannedEvictDate, actualEvictDate, orderState) VALUES (4, 3, '2022-06-01T20:00', null, '2022-08-07T13:00', null, "NEW");
--
--INSERT INTO OrderServices (service_id, person_id, plannedSettleDate, actualSettleDate, plannedEvictDate, actualEvictDate, orderState) VALUES (1, 2, '2022-03-14T15:20', null, '2022-03-14T16:20', null, "NEW");
--INSERT INTO OrderServices (service_id, person_id, plannedSettleDate, actualSettleDate, plannedEvictDate, actualEvictDate, orderState) VALUES (2, 3, '2022-05-01T21:00', null, '2022-05-01T22:00', null, "NEW");


