CREATE TABLE User (
                      ID INTEGER NOT NULL AUTO_INCREMENT,
                      Name VARCHAR(30) NOT NULL,
                      Surname VARCHAR(30) NOT NULL,
                      Age INTEGER NOT NULL,
                      Email VARCHAR(30) NOT NULL UNIQUE,
                      Password VARCHAR(20) NOT NULL ,
                      PRIMARY KEY (ID)
);

INSERT INTO User (Name, Surname, Age, Email, Password)
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

INSERT INTO Alcohol (Name, Creator, TypeID, Price)
VALUES
('Балтика', 'Baltic Beverages Holding', '1', '123'),
('Сваяк', 'Минский завод виноградных вин', '2', '12'),
('Советское', 'ОАО «Минский завод игристых вин»', '3', '1234'),
('Краймс', '19 Crimes','4', '345');

CREATE TABLE Marks (
                       ID INTEGER NOT NULL AUTO_INCREMENT,
                       Mark INTEGER NOT NULL,
                       UserID INTEGER NOT NULL,
                       AlcoholID INTEGER NOT NULL,
                       primary key (ID),
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

INSERT INTO AlcoholTypes (Type)
VALUES
('Пиво'),
('Водка'),
('Шампанское'),
('Вино');

ALTER TABLE Marks ADD FOREIGN KEY (UserID) REFERENCES User (ID);
ALTER TABLE Marks ADD FOREIGN KEY (AlcoholID) REFERENCES Alcohol (ID);
ALTER TABLE Alcohol ADD FOREIGN KEY (TypeID) REFERENCES AlcoholTypes (ID);

Select Mark from Marks;