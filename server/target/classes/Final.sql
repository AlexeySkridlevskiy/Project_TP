CREATE TABLE Users (
    ID INTEGER NOT NULL AUTO_INCREMENT,
    Name VARCHAR(30) NOT NULL,
    Surname VARCHAR(30) NOT NULL,
    Age INTEGER NOT NULL,
    Email VARCHAR(30) NOT NULL UNIQUE,
    Password VARCHAR(20) NOT NULL ,
    PRIMARY KEY (ID)
);

INSERT INTO Users (Name, Surname, Age, Email, Password)
VALUES 
('Егор', 'Яхимович', '18', 'zosh@gmail.com', '1234'),
('Алексей', 'Мироевский', '19', 'vodkamoyazhiznh@gmail.com', '1234'),
('Алексей', 'Скридлевский', '18', 'lublupivo@gmail.com', '1234'),
('Екатерина', 'Ермолаева', '19', 'lubluvino@gmail.com', '1234');

CREATE TABLE Alcohol (
    ID INTEGER NOT NULL AUTO_INCREMENT,
    Name VARCHAR(20) NOT NULL,
    TypeID INTEGER NOT NULL,
    Creator VARCHAR(50) NOT NULL,
    Price DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (ID)
);

INSERT INTO Alcohol (ID, Name, Creator, TypeID, Price)
VALUES 
('Балтика', 'Baltic Beverages Holding', '1', '123'),
('Сваяк', 'Минский завод виноградных вин', '2', '12'),
('Советское', 'ОАО «Минский завод игристых вин»', '3', '1234'),
('Краймс', '19 Crimes','4', '345');

CREATE TABLE Marks (
    Mark INTEGER NOT NULL,
    UserID INTEGER NOT NULL,
    AlcoholID INTEGER NOT NULL,
    CHECK (Mark > 0 AND Mark < 6)
);

INSERT INTO Marks (Mark, UserID, AlcoholID)
VALUES 
('1', '2', '2'),
('4', '3', '4'),
('5', '1', '2'),
('2', '1', '1');


CREATE TABLE AlcoholTypes (
    ID INTEGER NOT NULL AUTO_INCREMENT,
    Type VARCHAR(30) NOT NULL,
	PRIMARY KEY (ID)
);

INSERT INTO AlcoholTypes (ID, Type)
VALUES 
('Пиво'),
('Водка'),
('Шампанское'),
('Вино');

ALTER TABLE Marks ADD FOREIGN KEY (UserID) REFERENCES Users (ID);
ALTER TABLE Marks ADD FOREIGN KEY (AlcoholID) REFERENCES Alcohol (ID);
ALTER TABLE Alcohol ADD FOREIGN KEY (TypeID) REFERENCES AlcoholTypes (ID);

/*поиск по названию*/
SELECT *
FROM Alcohol JOIN AlcoholTypes ON Alcohol.TypeID = AlcoholTypes.ID
WHERE Alcohol.Name LIKE '%NAME%';

/*поиск по ID*/
SELECT *
FROM Alcohol JOIN AlcoholTypes ON Alcohol.TypeID = AlcoholTypes.ID
WHERE Alcohol.ID = 1 /*ID*/;

/*вывод всех видов алкоголя*/
SELECT *
FROM AlcoholTypes;

/*вывод всех оценок пользователя по ID пользователя*/
SELECT *
FROM Marks JOIN Alcohol on Marks.AlcoholID = Alcohol.ID JOIN AlcoholTypes on Alcohol.TypeID = AlcoholTypes.ID
WHERE UserID = 1 /*ID*/;

/*рейтинг напитка по ID*/
SELECT AVG(Mark)
FROM Marks
WHERE AlcoholID = 1 /*ID*/;

/*поиск по ID типа*/
SELECT *
FROM Alcohol JOIN AlcoholTypes ON Alcohol.TypeID = AlcoholTypes.ID
WHERE AlcoholTypes.ID = 1 /*ID*/;

/*поиск по имени типа*/
SELECT *
FROM Alcohol JOIN AlcoholTypes ON Alcohol.TypeID = AlcoholTypes.ID
WHERE AlcoholTypes.Type LIKE '%NAME%';

/*вывод всех напитков*/
SELECT *
FROM Alcohol JOIN AlcoholTypes ON Alcohol.TypeID = AlcoholTypes.ID;

/*вывод всех напитков по рейтингу*/
SELECT *, AVG(Marks.Mark) as Rating
FROM Alcohol JOIN AlcoholTypes ON Alcohol.TypeID = AlcoholTypes.ID JOIN Marks ON Alcohol.ID = Marks.AlcoholID
GROUP BY Alcohol.ID
ORDER BY Rating DESC;

/*вывод оценки пользователя напитку по ID*/
SELECT *
FROM Marks
WHERE Marks.AlcoholID = 1 /*AlcoholID*/ AND Marks.UserID = 1 /*UserID*/;

/*удаление оценки пользователя напитку по ID*/
DELETE
FROM Marks
WHERE Marks.AlcoholID = 1 /*AlcoholID*/ AND Marks.UserID = 1 /*UserID*/;