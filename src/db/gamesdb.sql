DROP DATABASE IF EXISTS gamedb;
CREATE DATABASE gamedb;
    
USE gamedb;
    
DROP TABLE IF EXISTS gUser;
CREATE TABLE gUser(
	idUser INT PRIMARY KEY,
	nameUser VARCHAR(20),
	email VARCHAR(50),
	phoneNumber CHAR(9),
	registrationDate DATE,
	route VARCHAR(50)
);
    
DROP TABLE IF EXISTS gUser;    
CREATE TABLE developer(
	idDeveloper INT PRIMARY KEY,
	nameDeveloper VARCHAR(20),
	country VARCHAR(20),
	fundationYear INT
);

DROP TABLE IF EXISTS gameUser;
CREATE TABLE gameUser(
	idUser INT,
    idGame INT,
    PRIMARY KEY(idUser, idGame),
    FOREIGN KEY(idUser) REFERENCES gUser(idUser) ON UPDATE CASCADE ON DELETE CASCADE,
);

INSERT INTO gUser VALUES
(1, 'CarlosG', 'carlos.g@email.com', '600111222', '2023-01-15', '/profiles/carlosg.jpg'),
(2, 'AnaL', 'ana.lopez@email.com', '611222333', '2023-02-10', '/profiles/anal.jpg'),
(3, 'DavidM', 'david.m@email.com', '622333444', '2023-02-28', '/profiles/davidm.jpg'),
(4, 'LauraS', 'laura.s@email.com', '633444555', '2023-03-05', '/profiles/lauras.jpg'),
(5, 'PedroR', 'pedro.r@email.com', '644555666', '2023-03-20', '/profiles/pedror.jpg'),
(6, 'SofiaB', 'sofia.b@email.com', '655666777', '2023-04-12', '/profiles/sofiab.jpg'),
(7, 'JavierK', 'javier.k@email.com', '666777888', '2023-05-01', '/profiles/javierk.jpg'),
(8, 'ElenaP', 'elena.p@email.com', '677888999', '2023-05-18', '/profiles/elenap.jpg'),
(9, 'DiegoV', 'diego.v@email.com', '688999000', '2023-06-02', '/profiles/diegov.jpg'),
(10, 'MartaC', 'marta.c@email.com', '699000111', '2023-06-25', '/profiles/martac.jpg'),
(11, 'LucasF', 'lucas.f@email.com', '600222444', '2023-07-04', '/profiles/lucasf.jpg'),
(12, 'LuciaH', 'lucia.h@email.com', '611333555', '2023-07-19', '/profiles/luciah.jpg'),
(13, 'HugoN', 'hugo.n@email.com', '622444666', '2023-08-08', '/profiles/hugon.jpg'),
(14, 'CarmenR', 'carmen.r@email.com', '633555777', '2023-08-30', '/profiles/carmenr.jpg'),
(15, 'MateoD', 'mateo.d@email.com', '644666888', '2023-09-11', '/profiles/mateod.jpg'),
(16, 'PaulaM', 'paula.m@email.com', '655777999', '2023-10-05', '/profiles/paulam.jpg'),
(17, 'AlejandroT', 'ale.t@email.com', '666888000', '2023-10-21', '/profiles/alet.jpg'),
(18, 'IreneW', 'irene.w@email.com', '677999111', '2023-11-03', '/profiles/irenew.jpg'),
(19, 'DanielO', 'daniel.o@email.com', '688000222', '2023-11-15', '/profiles/danielo.jpg'),
(20, 'ValeriaZ', 'valeria.z@email.com', '699111333', '2023-12-01', '/profiles/valeriaz.jpg');

INSERT INTO developer (idDeveloper, nameDeveloper, country, fundationYear) VALUES
(1, 'Nintendo', 'Japón', 1889),
(2, 'Ubisoft', 'Francia', 1986),
(3, 'Capcom', 'Japón', 1979),
(4, 'EA', 'Estados Unidos', 1982),
(5, 'Square Enix', 'Japón', 1975),
(6, 'CD Projekt Red', 'Polonia', 1994),
(7, 'Rockstar Games', 'Estados Unidos', 1998),
(8, 'Valve', 'Estados Unidos', 1996),
(9, 'Bethesda', 'Estados Unidos', 1986),
(10, 'Bandai Namco', 'Japón', 1955),
(11, 'Mojang', 'Suecia', 2009),
(12, 'Epic Games', 'Estados Unidos', 1991),
(13, 'Kona', 'Japón', 1969),
(14, 'Sega', 'Japón', 1960),
(15, 'Bioware', 'Canadá', 1995),
(16, 'Remedy', 'Finlandia', 1995),
(17, 'Bungie', 'Estados Unidos', 1991),
(18, 'Insomniac', 'Estados Unidos', 1994),
(19, 'Crytek', 'Alemania', 1999),
(20, 'Atlus', 'Japón', 1986);