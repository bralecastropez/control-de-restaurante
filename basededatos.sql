USE master
GO
DROP DATABASE dbRestauranteProyecto
GO
CREATE DATABASE dbRestauranteProyecto
GO
USE dbRestauranteProyecto
GO
CREATE TABLE Cliente(
	idCliente INT IDENTITY (1,1) NOT NULL,
	nombre VARCHAR (255) NOT NULL,
	PRIMARY KEY (idCliente)
)
CREATE TABLE Factura(
	idFactura INT IDENTITY (1,1) NOT NULL,
	idCliente INT NOT NULL,
	PRIMARY KEY (idFactura),
	FOREIGN KEY (idCliente) REFERENCES Cliente(idCliente)
)
CREATE TABLE Rol(
	idRol INT IDENTITY(1,1),
	nombre VARCHAR(255),
	PRIMARY KEY(idRol)
)	
CREATE TABLE Usuario(
	idUsuario INT IDENTITY(1,1),
	nombre VARCHAR(255),
	pass VARCHAR(255),
	idRol INT NOT NULL,
	PRIMARY KEY(idUsuario),
	FOREIGN KEY(idRol) REFERENCES Rol(idRol)
)
CREATE TABLE Ingrediente(
	idIngrediente INT IDENTITY(1,1),
	nombre VARCHAR(255),
	cantidad INT,
	precio INT,
	PRIMARY KEY(idIngrediente)
)
CREATE TABLE Platillo(
	idPlatillo INT IDENTITY(1,1),
	nombre VARCHAR(255),
	precio INT NOT NULL,
	PRIMARY KEY(idPlatillo)
)
CREATE TABLE DetallePlatillo(
	idPlatillo INT NOT NULL,
	idIngrediente INT NOT NULL,
	PRIMARY KEY(idPlatillo,idIngrediente),
	FOREIGN KEY(idPlatillo) REFERENCES Platillo(idPlatillo),
	FOREIGN KEY(idIngrediente) REFERENCES Ingrediente(idIngrediente)
)
CREATE TABLE Bebida(
	idBebida INT IDENTITY(1,1),
	nombre VARCHAR(255),
	precio INT NOT NULL,
	PRIMARY KEY(idBebida)
)
CREATE TABLE DetalleBebida(
	idBebida INT NOT NULL,
	idIngrediente INT NOT NULL,
	PRIMARY KEY(idBebida,idIngrediente),
	FOREIGN KEY(idBebida) REFERENCES Bebida(idBebida),
	FOREIGN KEY(idIngrediente) REFERENCES Ingrediente(idIngrediente)
)
CREATE TABLE Pedido(
	idPedido INT IDENTITY(1,1),
	idFactura INT NOT NULL,
	estado VARCHAR (255) NOT NULL,
	PRIMARY KEY(idPedido),
	FOREIGN KEY(idFactura) REFERENCES Factura(idFactura)
)
CREATE TABLE DetallePedidoBebida(
	idBebida INT NOT NULL,
	idPedido INT NOT NULL,
	cantidad INT,
	PRIMARY KEY(idBebida,idPedido),
	FOREIGN KEY(idBebida) REFERENCES Bebida(idBebida),
	FOREIGN KEY(idPedido) REFERENCES Pedido(idPedido)
)
CREATE TABLE DetallePedidoPlatillo(
	idPlatillo INT NOT NULL,
	idPedido INT NOT NULL,
	cantidad INT,
	PRIMARY KEY(idPlatillo,idPedido),
	FOREIGN KEY(idPlatillo) REFERENCES Platillo(idPlatillo),
	FOREIGN KEY(idPedido) REFERENCES Pedido(idPedido)
)
CREATE TABLE DetallePedido(
	idUsuario INT NOT NULL,
	idPedido INT NOT NULL,
	PRIMARY KEY(idUsuario,idPedido),
	FOREIGN KEY(idUsuario) REFERENCES Usuario(idUsuario),
	FOREIGN KEY(idPedido) REFERENCES Pedido(idPedido)
)
GO
--Usuario
CREATE PROCEDURE insertarPersona @nombre VARCHAR (255), @pass VARCHAR (255), @idRol INT
AS
BEGIN
	INSERT INTO Usuario (nombre,pass,idRol) VALUES (@nombre,@pass, @idRol)
END
GO
--Rol
CREATE PROCEDURE insertarRol @nombre VARCHAR (255)
AS
BEGIN 
	INSERT INTO Rol (nombre) VALUES (@nombre)
END
GO
--Insertar Rol
EXEC insertarRol 'administrador'
EXEC insertarRol 'chef'
EXEC insertarRol 'empleado'
--Insertar Persona
EXEC insertarPersona 'brandon','brandon',1
EXEC insertarPersona 'alexander','alexander',2
EXEC insertarPersona 'castro','castro',3
--SELECT * FROM Persona