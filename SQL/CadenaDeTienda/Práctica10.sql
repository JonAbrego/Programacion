/*
 * Abrego Alvarez Jonathan
 * Problema general: "Una cadena de tiendas(OXXO) donde se pueda saber
 * algunos de los productos que maneja(vende), personas que trabajan
 * en alguna de las tiendas, y los provedores que tienen"
 * 
 */
 
CREATE TABLE Sucursal(
Id_Tienda numeric PRIMARY KEY
		  CHECK(Id_Tienda >0)
		  DEFAULT(1),
Nombre varchar(20),
Colonia varchar(20),
Calle varchar(20),
Número varchar(20)

);
CREATE TABLE Empleado(
RFC_Empleado varchar(13) PRIMARY KEY
                         CHECK(RFC_Empleado ~ '[A-Z]{3,4}[0-9]{6}[A-Z]{3}')
                         NOT NULL,
Nombre varchar(20),
ApellidoP varchar(20),
ApellidoM varchar(20),
F_nacimiento date
);
CREATE TABLE Provedor(
RFC_Provedor varchar(13) PRIMARY KEY
                         CHECK(RFC_Provedor ~ '[A-Z]{3,4}[0-9]{6}[A-Z]{3}')
                         NOT NULL,
Nombre varchar(20),
Paterno varchar(20),
Materno varchar(20),
Compañia varchar(20)
);
CREATE TABLE Producto(
Id_Producto numeric PRIMARY KEY
                    CHECK(Id_Producto>0)
		    DEFAULT(1000),
Nombre varchar(20),
Descripción text,
Precio float,
RFC_Provedor varchar(13) CHECK(RFC_Provedor ~ '[A-Z]{3,4}[0-9]{6}[A-Z]{3}'),
FOREIGN KEY(RFC_Provedor) REFERENCES Provedor
                          ON DELETE CASCADE
                          ON UPDATE SET NULL                      
);
CREATE TABLE Venta(
Id_Producto numeric CHECK(Id_Producto > 0)
                    DEFAULT(1000),
Id_Tienda numeric CHECK(Id_Tienda > 0)
                  DEFAULT(1), 
F_entrega date,
Hora_venta varchar(13),
Cantidad numeric,
FOREIGN KEY(Id_Producto) REFERENCES Producto 
                         ON DELETE RESTRICT 
                         ON UPDATE SET DEFAULT,                         
FOREIGN KEY(Id_Tienda) REFERENCES Sucursal
                       ON DELETE SET NULL
                       ON UPDATE NO ACTION                   
);
CREATE TABLE Trabaja(
RFC_Empleado varchar(13) CHECK(RFC_Empleado ~ '[A-Z]{3,4}[0-9]{6}[A-Z]{3}') ,
Id_Tienda numeric CHECK(Id_Tienda > 0)
                  DEFAULT(1),
Sueldo numeric CHECK(Sueldo > 0),
Puesto varchar(20),
Turno varchar(10),
FOREIGN KEY(RFC_Empleado) REFERENCES Empleado
                          ON DELETE SET DEFAULT
                          ON UPDATE CASCADE,
FOREIGN KEY(Id_Tienda) REFERENCES Sucursal
                       ON DELETE NO ACTION
                       ON UPDATE RESTRICT 
);
-------
-------
INSERT INTO Sucursal VALUES(2,'OXXO','Zapotitlan','Av. Tláhuac','s/n');
INSERT INTO Sucursal VALUES(4,'OXXO','Santa Catarina','Eje10 Sur','103-A');
INSERT INTO Sucursal VALUES(6,'OXXO','Villa Olimpica','Mineral','99');
INSERT INTO Sucursal VALUES(8,'OXXO','Roma','Escape','69-C');
INSERT INTO Sucursal VALUES(10,'OXXO','Pedregal','Leight','1090');

INSERT INTO Empleado VALUES('RETG901212HDF','Gabriel','Rfringer','Tao','1990-12-12');
INSERT INTO Empleado VALUES('REJC941111HDF','Chabelo','Jimenez','Aguilar','1994-11-11');
INSERT INTO Empleado VALUES('REIF630204HDF','Felipe','Rodriguez','Ibarra','1963-02-04');
INSERT INTO Empleado VALUES('SEIF630204HDF','Fidel','Soriana','Ibarra','1963-02-04');
INSERT INTO Empleado VALUES('SEGL920228MDF','Lucia','Badillo','Garcia','1992-02-28');

INSERT INTO Provedor VALUES('VALL680300HDF','Luis','Vazquez','Lazo','Coca-Cola');
INSERT INTO Provedor VALUES('JERH901212HDF','Homero','Jimeno','Ramirez','Pepsi');
INSERT INTO Provedor VALUES('YHUI890214HDF','Irbing','Yhui','Urrutia','Bimbo');
INSERT INTO Provedor VALUES('THAF890618MDF','Fatima','Tapia','Alberto','Sabritas');
INSERT INTO Provedor VALUES('BTEC721212HDF','Carlos','Bustos','Espinoza','Alpura');

INSERT INTO Producto VALUES(1021,'Coca-Cola','Bebida refrescante',10,'VALL680300HDF');
INSERT INTO Producto VALUES(2314,'Manzanita Sol','Bebida refrescante',6.50,'JERH901212HDF');
INSERT INTO Producto VALUES(4622,'Mantecadas','Pan suave horneado',12,'YHUI890214HDF');
INSERT INTO Producto VALUES(3001,'Sabritas','Frituras finamente cortadas',9,'THAF890618MDF');
INSERT INTO Producto VALUES(5002,'Leche','Leche entera de vacas sanas',18,'BTEC721212HDF');

INSERT INTO Venta VALUES(1021,2,'2014-03-07','13:32',10);
INSERT INTO Venta VALUES(2314,4,'2014-01-13','14:56',20);
INSERT INTO Venta VALUES(4622,6,'2014-04-09','17:55',10);
INSERT INTO Venta VALUES(3001,8,'2014-05-04','10:21',5);
INSERT INTO Venta VALUES(5002,10,'2014-02-14','12:00',10);

INSERT INTO Trabaja VALUES('RETG901212HDF',2,1200,'Mixto','Cajero');
INSERT INTO Trabaja VALUES('REJC941111HDF',4,800,'Matutino','Gerente');
INSERT INTO Trabaja VALUES('REIF630204HDF',6,600,'Matutino','Cajero');
INSERT INTO Trabaja VALUES('SEIF630204HDF',8,750,'Nocturno','Cajero');
INSERT INTO Trabaja VALUES('SEGL920228MDF',10,1200,'Mixto','Gerente');

update Empleado set RFC_Empleado='RETG901100OJO' where RFC_Empleado='RETG901100HDF';
update Trabaja set Id_Tienda=4 where Id_Tienda=2;
update Venta set Id_Tienda=6 WHERE Id_Tienda=10;

delete from Trabaja where rfc_empleado='RETG901100OJO';
delete from Trabaja where Id_Tienda=4;
delete from Venta where Id_Tienda=10;
delete from Venta where Id_Producto=2;

