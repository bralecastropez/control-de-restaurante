CREATE DATABASE dbRestauranteProyecto2013155
GO
USE dbRestauranteProyecto2013155
GO
CREATE TABLE Rol(
	idRol INT IDENTITY (1,1) NOT NULL,
	nombre VARCHAR (255) NOT NULL,
	PRIMARY KEY(idRol)
)
CREATE TABLE Persona(
	idPersona INT IDENTITY (1,1) NOT NULL,
	idRol INT NOT NULL,
	nombre VARCHAR (255),
	PRIMARY KEY (idPersona),
	FOREIGN KEY (idRol) REFERENCES Rol(idRol)
	)
CREATE TABLE Mesa(
	idMesa INT IDENTITY (1,1) NOT NULL,
	idPersona INT NOT NULL,
	idCliente INT NOT NULL,
	PRIMARY KEY (idMesa),
	FOREIGN KEY (idPersona) REFERENCES Persona(idPersona)
)
CREATE TABLE Mesero(
	idMesero INT IDENTITY (1,1) NOT NULL,
	nombre VARCHAR (255) NOT NULL,
	PRIMARY KEY (idMesero)
)
CREATE TABLE Factura(
	idFactura INT IDENTITY (1,1) NOT NULL,
	idCliente INT NOT NULL,
	idPersona INT NOT NULL,
	fecha DATE NOT NULL,
	PRIMARY KEY (idFactura),
	FOREIGN KEY (idPersona) REFERENCES Persona (idPersona),
)
CREATE TABLE DetalleMesa (
	idMesa INT NOT NULL,
	idFactura INT NOT NULL,
	PRIMARY KEY (idMesa,idFactura),
	FOREIGN KEY (idFactura) REFERENCES Factura (idFactura),
	FOREIGN KEY (idMesa) REFERENCES Mesa(idMesa)
)
CREATE TABLE DetalleMesero (
	idMesero INT NOT NULL,
	idFactura INT NOT NULL,
	PRIMARY KEY (idMesero,idFactura),
	FOREIGN KEY (idFactura) REFERENCES Factura (idFactura),
	FOREIGN KEY (idMesero) REFERENCES Mesero(idMesero)
)
CREATE TABLE Platillo(
	idPlatillo INT IDENTITY (1,1) NOT NULL,
	nombre VARCHAR (255) NOT NULL,
	extras VARCHAR (255) NOT NULL,
	precio INT NOT NULL,
	PRIMARY KEY (idPlatillo)
)
CREATE TABLE Bebida (
	idBebida INT IDENTITY (1,1) NOT NULL,
	nombre VARCHAR (255) NOT NULL,
	extras VARCHAR(255) NOT NULL,
	precio INT NOT  NULL,
	PRIMARY KEY (idBebida)
)
CREATE TABLE DetallePlatillo(
	idPlatillo INT NOT NULL,
	idMesa INT NOT NULL,
	PRIMARY KEY(idPlatillo,idMesa),
	FOREIGN KEY (idMesa) REFERENCES Mesa (idMesa),
	FOREIGN KEY (idPlatillo) REFERENCES Platillo (idPlatillo)
)
CREATE TABLE DetalleBebida(
	idBebida INT NOT NULL,
	idMesa INT NOT NULL,
	PRIMARY KEY(idBebida,idMesa),
	FOREIGN KEY (idMesa) REFERENCES Mesa (idMesa),
	FOREIGN KEY (idBebida) REFERENCES Bebida (idBebida)
)
GO
--Insertar Rol
CREATE PROCEDURE InsertarRol @nombre VARCHAR (255)
AS
BEGIN
	INSERT INTO Rol (nombre) VALUES (@nombre)
END
GO
--Insertar Persona
CREATE PROCEDURE InsertarPersona @nombre VARCHAR(255),@idRol INT
AS
BEGIN
	INSERT INTO Persona (nombre,idRol) VALUES (@nombre,@idRol)
END
GO
--Insertar Mesa
CREATE PROCEDURE InsertarMesa @idCliente INT,@idPersona INT
AS
BEGIN
	INSERT INTO Mesa(idCliente,idPersona) VALUES (@idCliente,@idPersona)
END
GO
--Insertar Mesero
CREATE PROCEDURE InsertarMesero @nombre VARCHAR(255)
AS
BEGIN
	INSERT INTO  Mesero (nombre) VALUES (@nombre)
END
GO
--InsertarPlatillo
CREATE PROCEDURE InsertarPlatillo @nombre VARCHAR (255),@extras VARCHAR (255),@precio INT
AS
BEGIN
	INSERT INTO Platillo (nombre,extras,precio) VALUES (@nombre,@extras,@precio)
END
GO
--InsertarBebida
CREATE PROCEDURE InsertarBebida @nombre VARCHAR (255),@extras VARCHAR (255),@precio INT
AS
BEGIN
	INSERT INTO Bebida(nombre,extras,precio) VALUES (@nombre,@extras,@precio)
END
GO
--Insertar Factura
CREATE PROCEDURE InsertarFactura @idCliente INT,@fecha DATE,@idPersona INT
AS
BEGIN
	INSERT INTO Factura(idCliente,fecha,idPersona) VALUES (@idCliente,@fecha,@idPersona)
END
GO
--Insertar DetallePlatillo
CREATE PROCEDURE InsertarDetallePlatillo @idPlatillo INT,@idMesa INT
AS
BEGIN
	INSERT INTO DetallePlatillo (idMesa,idPlatillo) VALUES (@idMesa,@idPlatillo)
END
GO
--Insertar DetalleBebida
CREATE PROCEDURE InsertarDetalleBebida @idBebida INT,@idMesa INT
AS
BEGIN
	INSERT INTO DetalleBebida (idMesa,idBebida) VALUES (@idBebida,@idMesa)
END
GO
--Insertar DetalleMesa
CREATE PROCEDURE InsertarDetalleMesa @idMesa INT,@idFactura INT
AS
BEGIN
	INSERT INTO DetalleMesa(idMesa,idFactura) VALUES (@idMesa,@idFactura)
END
GO
--Insertar DetalleMesero
CREATE PROCEDURE InsertarDetalleMesero @idMesero INT,@idFactura INT
AS
BEGIN
	INSERT INTO DetalleMesero(idMesero,idFactura) VALUES (@idMesero,@idFactura)
END
GO
CREATE PROCEDURE consultarTodo
AS
BEGIN
	SELECT * FROM Rol
	SELECT * FROM Mesa
	SELECT * FROM Persona
	SELECT * FROM Mesero
	SELECT * FROM Factura
	SELECT * FROM Platillo
	SELECT * FROM Bebida
	SELECT * FROM DetalleBebida
	SELECT * FROM DetalleMesa
	SELECT * FROM DetalleMesero
	SELECT * FROM DetallePlatillo
END
GO
--Rol
EXEC InsertarRol 'Chef'
EXEC InsertarRol 'Empleado'
EXEC InsertarRol 'Cliente'
--Cliente
EXEC InsertarPersona 'Juan Carlos',1
EXEC InsertarPersona 'Brandon Castro',2
EXEC InsertarPersona 'Hamilton Lopez',3
EXEC InsertarPersona 'Pegro Castro',3
--Factura
EXEC InsertarFactura 1,'2011-01-01',3
EXEC InsertarFactura 2,'2012-02-02',4
--Mesa
EXEC InsertarMesa 1,1
EXEC InsertarMesa 2,2
--Mesero
EXEC InsertarMesero 'Mario'
EXEC InsertarMesero 'Luigi'
EXEC InsertarMesero 'Wario'
--Platillo
EXEC InsertarPlatillo 'Sopa de Letras','Sopa con letras en vez de fideos',350
EXEC InsertarPlatillo 'Hamburguesa','Pan con carne y salsa dulce y mayonesa y lechuga y otras cosas que no se',125
EXEC InsertarPlatillo 'Pizza','pizza',35
--Bebida
EXEC InsertarBebida 'Cafe','agua con cafe y azucar',35
EXEC InsertarBebida 'Coca-Cola','agua con colorante artificial sabor a caramelo',15
EXEC InsertarBebida 'Pepsi','es algo igual a la coca',150
--DetalleMesa
EXEC InsertarDetalleMesa 1,1
EXEC InsertarDetalleMesa 2,2
--DetalleMesero
EXEC InsertarDetalleMesero 1,1
EXEC InsertarDetalleMesero 2,2
--DetallePlatillo
EXEC InsertarDetallePlatillo 1,1
EXEC InsertarDetallePlatillo 2,2
--DetalleBebida
EXEC InsertarDetalleBebida 1,1
EXEC InsertarDetalleBebida 2,2
--DROP DATABASE dbRestauranteProyecto2013155
--Consultar
EXEC consultarTodo