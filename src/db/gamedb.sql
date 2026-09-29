DROP DATABASE IF EXISTS gamedb;
CREATE DATABASE gamedb;
    
USE gamedb;
    
DROP TABLE IF EXISTS gUser;
CREATE TABLE gUser(
	idUser INT PRIMARY KEY AUTO_INCREMENT,
	nameUser VARCHAR(20),
	email VARCHAR(50),
	phoneNumber CHAR(9),
	registrationDate DATE DEFAULT (CURRENT_DATE),
	route VARCHAR(50)
);
    
DROP TABLE IF EXISTS developer;    
CREATE TABLE developer(
	idDeveloper INT PRIMARY KEY AUTO_INCREMENT,
	nameDeveloper VARCHAR(20),
	country VARCHAR(20),
	foundationYear INT
);

INSERT INTO gUser VALUES
(1, 'CarlosG', 'carlos.g@email.com', '600111222', '2023-01-15', 'images/pfp1.png'),
(2, 'AnaP', 'ana.p@email.com', '611222333', '2023-02-10', 'images/pfp2.png'),
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

INSERT INTO developer (nameDeveloper, country, foundationYear) VALUES
('Nintendo', 'Japan', 1889),
('Ubisoft', 'France', 1986),
('Capcom', 'Japan', 1979),
('EA', 'USA', 1982),
('Square Enix', 'Japan', 1975),
('CD Projekt Red', 'Poland', 1994),
('Rockstar Games', 'USA', 1998),
('Valve', 'USA', 1996),
('Bethesda', 'USA', 1986),
('Bandai Namco', 'Japan', 1955),
('Mojang', 'Sweden', 2009),
('Epic Games', 'USA', 1991),
('Kona', 'Japan', 1969),
('Sega', 'Japan', 1960),
('Bioware', 'Canada', 1995),
('Remedy', 'Finland', 1995),
('Bungie', 'USA', 1991),
('Insomniac', 'USA', 1994),
('Crytek', 'Germany', 1999),
('Atlus', 'Japan', 1986);