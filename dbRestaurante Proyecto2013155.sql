USE master
GO
DROP DATABASE dbRestauranteProyecto2013155
GO
CREATE DATABASE dbRestauranteProyecto2013155
GO
USE dbRestauranteProyecto2013155
GO
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
CREATE TABLE Bebida(
	idBebida INT IDENTITY(1,1),
	nombre VARCHAR(255),
	precio INT NOT NULL,
	PRIMARY KEY(idBebida)
)
CREATE TABLE Pedido(
	idPedido INT IDENTITY(1,1),
	estado VARCHAR (255) DEFAULT 'espera',
	PRIMARY KEY(idPedido)
)
CREATE TABLE Cliente(
	idCliente INT IDENTITY (1,1) NOT NULL,
	nombre VARCHAR (255) NOT NULL,
	PRIMARY KEY (idCliente)
)
CREATE TABLE Factura(
	idFactura INT IDENTITY (1,1) NOT NULL,
	idCliente INT NOT NULL,
	idPedido INT NOT NULL,
	fecha VARCHAR (255)	 NOT NULL,
	PRIMARY KEY (idFactura),
	FOREIGN KEY (idCliente) REFERENCES Cliente(idCliente),
	FOREIGN KEY(idPedido) REFERENCES Pedido(idPedido)
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
CREATE TABLE DetallePedidoIngrediente(
	idIngrediente INT NOT NULL,
	idPedido INT NOT NULL,
	cantidad INT,
	PRIMARY KEY(idIngrediente,idPedido),
	FOREIGN KEY(idIngrediente) REFERENCES Platillo(idPlatillo),
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
CREATE PROCEDURE insertarCliente @nombre VARCHAR (255)
AS
BEGIN
	INSERT INTO Cliente(nombre) VALUES (@nombre)
END
GO
CREATE PROCEDURE insertarFactura @idCliente INT, @fecha VARCHAR (255),@idPedido INT
AS
BEGIN
	INSERT INTO Factura (idCliente,fecha,idPedido) VALUES (@idCliente,@fecha,@idPedido)
END
GO
CREATE PROCEDURE consultarPedidoEstado @idPedido INT
AS
BEGIN
	SELECT Pedido.idPedido, Pedido.estado FROM Pedido WHERE Pedido.idPedido=@idPedido
END
GO
CREATE PROCEDURE consultarPedidoBebida @idPedido INT
AS
BEGIN
	SELECT Bebida.nombre FROM DetallePedidoBebida 
		INNER JOIN Bebida ON DetallePedidoBebida.idBebida=Bebida.idBebida WHERE DetallePedidoBebida.idPedido=@idPedido
END
GO
CREATE PROCEDURE consultarPedidoPlatillo @idPedido INT
AS
BEGIN
	SELECT Platillo.nombre FROM DetallePedidoPlatillo 
		INNER JOIN Platillo ON DetallePedidoPlatillo.idPlatillo=Platillo.idPlatillo WHERE DetallePedidoPlatillo.idPedido=@idPedido
END
GO
CREATE PROCEDURE consultarPedidoIngrediente @idPedido INT
AS
BEGIN
	SELECT Ingrediente.nombre FROM DetallePedidoIngrediente 
		INNER JOIN Ingrediente ON DetallePedidoIngrediente.idIngrediente=Ingrediente.idIngrediente WHERE DetallePedidoIngrediente.idPedido=@idPedido
END
GO
CREATE PROCEDURE ConsultarPedido
AS
BEGIN
	SELECT Pedido.idPedido, Pedido.estado FROM Pedido
	SELECT Bebida.nombre FROM DetallePedidoBebida 
		INNER JOIN Bebida ON DetallePedidoBebida.idBebida=Bebida.idBebida
	SELECT Platillo.nombre FROM DetallePedidoPlatillo 
		INNER JOIN Platillo ON DetallePedidoPlatillo.idPlatillo=Platillo.idPlatillo
	SELECT Ingrediente.nombre FROM DetallePedidoIngrediente 
		INNER JOIN Ingrediente ON DetallePedidoIngrediente.idIngrediente=Ingrediente.idIngrediente
END
GO
--Insertar Rol
EXEC insertarRol 'administrador'
EXEC insertarRol 'chef'
EXEC insertarRol 'empleado'
--Insertar Persona
EXEC insertarPersona 'brandon','brandon',1
EXEC insertarPersona 'admin','admin',1
EXEC insertarPersona 'chef','chef',2
EXEC insertarPersona 'empleado','empleado',3
--Insertar Cliente
EXEC insertarCliente 'Oscarrin'
EXEC insertarCliente 'Jordy'
EXEC insertarCliente 'Hamilton'
--Insertar Pedido
INSERT INTO Pedido(estado) VALUES ('espera')
INSERT INTO Pedido(estado) VALUES ('espera')
INSERT INTO Pedido(estado) VALUES ('espera')
INSERT INTO Pedido(estado) VALUES ('espera')
--Insertar Factura
EXEC insertarFactura 1,'2014-01-01',1
EXEC insertarFactura 2,'2014-02-02',2
EXEC insertarFactura 3,'2014-03-03',3
--Insertar Ingredientes
INSERT INTO Ingrediente(nombre,cantidad,precio) VALUES ('agua',100,120)
INSERT INTO Ingrediente(nombre,cantidad,precio) VALUES ('leche',100,120)
INSERT INTO Ingrediente(nombre,cantidad,precio) VALUES ('pan',100,120)
INSERT INTO Ingrediente(nombre,cantidad,precio) VALUES ('azucar',100,120)
--Insertar Comida
INSERT INTO Platillo(nombre,precio) VALUES ('Pastel',1200)
INSERT INTO Platillo(nombre,precio) VALUES ('Manjar',1305)
--Insertar Bebidas
Insert INTO Bebida(nombre,precio) VALUES('Agua Mineral',125)
INSERT INTO Bebida(nombre,precio) VALUES ('Pastel',1504) 
Insert INTO Bebida(nombre,precio) VALUES('Agua asdf',125)
INSERT INTO Bebida(nombre,precio) VALUES ('sdf',1504) 
--INSERTAR PEDIDOS--
INSERT INTO DetallePedidoPlatillo(idPedido,idPlatillo,cantidad)			VALUES (1,1,5)
INSERT INTO DetallePedidoPlatillo(idPedido,idPlatillo,cantidad)			VALUES (1,2,6)
INSERT INTO DetallePedidoBebida(idPedido,idBebida,cantidad)				VALUES(1,1,1)
INSERT INTO DetallePedidoBebida(idPedido,idBebida,cantidad)				VALUES(1,2,2)
INSERT INTO DetallePedidoBebida(idPedido,idBebida,cantidad)				VALUES(2,1,1)
INSERT INTO DetallePedidoBebida(idPedido,idBebida,cantidad)				VALUES(2,2,2)
INSERT INTO DetallePedidoBebida(idPedido,idBebida,cantidad)				VALUES(2,3,1)
INSERT INTO DetallePedidoBebida(idPedido,idBebida,cantidad)				VALUES(2,4,2)
INSERT INTO DetallePedidoBebida(idPedido,idBebida,cantidad)				VALUES(3,1,1)
INSERT INTO DetallePedidoBebida(idPedido,idBebida,cantidad)				VALUES(3,2,2)
INSERT INTO DetallePedidoIngrediente(idPedido, idIngrediente, cantidad)	VALUES (1,1,10)
INSERT INTO DetallePedidoIngrediente(idPedido, idIngrediente, cantidad) VALUES (1,2,20)
INSERT INTO DetallePedidoIngrediente(idPedido, idIngrediente, cantidad) VALUES (2,1,30)
INSERT INTO DetallePedidoIngrediente(idPedido, idIngrediente, cantidad) VALUES (2,2,40)
INSERT INTO DetallePedidoIngrediente(idPedido, idIngrediente, cantidad) VALUES (3,1,50)
INSERT INTO DetallePedidoIngrediente(idPedido, idIngrediente, cantidad) VALUES (3,2,60)
/*EXEC consultarPedidoEstado		1
EXEC consultarPedidoBebida		1
EXEC consultarPedidoPlatillo	1
EXEC consultarPedidoIngrediente 1*/
EXEC ConsultarPedido
/*SELECT * FROM DetallePedidoBebida
SELECT * FROM DetallePedidoPlatillo
SELECT * FROM DetallePedidoIngrediente*/
/*SELECT * FROM Usuario
SELECT * FROM Pedido
SELECT * FROM Cliente
SELECT * FROM Factura
SELECT * FROM Ingrediente
SELECT * FROM Bebida
SELECT * FROM Platillo*/