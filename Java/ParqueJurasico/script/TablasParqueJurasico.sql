/*
 * Creación de las tablas 
 */
CREATE TABLE Escuela(
Clave varchar(10) PRIMARY KEY,  
Nombre varchar(50) NOT NULL, 
Entidad varchar(20) NOT NULL, 
Localidad varchar(40) NOT NULL, 
Calle varchar(40) NOT NULL, 
Numero varchar(20) DEFAULT'S/N'
); 
CREATE TABLE Telefono(
Clave varchar(10),
Telefono varchar(15),
FOREIGN KEY(Clave) REFERENCES Escuela
                   ON DELETE CASCADE
                   ON UPDATE CASCADE
);
CREATE TABLE Correo_electronico(
Clave varchar(10), 
Correo_electronico varchar(30),
FOREIGN KEY(Clave) REFERENCES Escuela
		   ON DELETE CASCADE
                   ON UPDATE CASCADE
);
CREATE TABLE Profesor(
RFC_Profesor varchar(13) PRIMARY KEY, 
Nombre varchar(20) NOT NULL, 
Apellido_paterno varchar(20) NOT NULL, 
Apellido_materno varchar(20) NOT NULL
);
CREATE TABLE Alumno(
CURP varchar(18) PRIMARY KEY, 
Nombre varchar(20), 
Apellido_paterno varchar(20), 
Apellido_materno varchar(20), 
Grado numeric CHECK(Grado >0),
Clave varchar(10),
FOREIGN KEY(Clave) REFERENCES Escuela
		   ON DELETE SET NULL
		   ON UPDATE CASCADE
);
CREATE TABLE Zona(
Id_zona numeric PRIMARY KEY, 
Tipo_vegetacion text, 
Superficie_ocupa float, 
Requerimientos_seguridad text 
);
CREATE TABLE Reservacion(
Id_reservacion serial PRIMARY KEY, 
Hora time NOT NULL, 
Tipo_visita varchar(35) DEFAULT 'solo visita', 
Precio float CHECK(Precio>0)
             DEFAULT'2500',
Fecha date NOT NULL
);

CREATE TABLE Zonas_Visita(
Id_reservacion serial,
Id_zona numeric,
FOREIGN KEY(Id_zona) REFERENCES Zona
                            ON DELETE SET NULL
			    ON UPDATE CASCADE,
FOREIGN KEY(Id_reservacion) REFERENCES Reservacion
			    ON DELETE CASCADE
			    ON UPDATE CASCADE
);
CREATE TABLE Lista_Profesores(
Id_reservacion serial, 
Profesor varchar(20),
FOREIGN KEY(Id_reservacion) REFERENCES Reservacion
			    ON DELETE CASCADE
			    ON UPDATE CASCADE
);
CREATE TABLE Lista_Alumnos(
Id_reservacion serial,  
Alumno varchar(20),
FOREIGN KEY(Id_reservacion) REFERENCES Reservacion
                            ON DELETE CASCADE
			    ON UPDATE CASCADE
);
CREATE TABLE Vehiculo(
Id_vehiculo varchar(18) PRIMARY KEY, 
Capacidad_personas numeric CHECK(Capacidad_personas>0), 
Tipo varchar(20) NOT NULL, 
Caracteristicas_especiales text NOT NULL
				DEFAULT'Ninguna'
);
CREATE TABLE Trabajador(
RFC varchar(13) PRIMARY KEY, 
Nombre varchar(20) NOT NULL, 
Apellido_paterno varchar(20) NOT NULL, 
Apellido_materno varchar(20) NOT NULL, 
Puesto varchar(25) NOT NULL, 
Sueldo float CHECK(Sueldo>0), 
Dia_descanso varchar(20) NOT NULL, 
Telefono varchar(15) NOT NULL, 
Correo_electronico varchar(40) NOT NULL, 
Colonia varchar(20) NOT NULL, 
Calle varchar(20) NOT NULL, 
Numero varchar(20) DEFAULT 'S/N', 
Id_zona numeric,
FOREIGN KEY(Id_zona) REFERENCES Zona
		     ON DELETE NO ACTION
		     ON UPDATE CASCADE 
);
CREATE TABLE Dinosaurio(
Nombre varchar(30) PRIMARY KEY, 
Tipo_alimentación text, 
Filo varchar(30),
Clase varchar(30),
Reino varchar(30),
Altura text, 
Peso text DEFAULT 'Desc', 
Distribucion text, 
Id_zona numeric,
FOREIGN KEY(Id_zona) REFERENCES Zona
		     ON DELETE NO ACTION
		     ON UPDATE NO ACTION	
);
CREATE TABLE Realiza(
Id_reservacion serial, 
Clave varchar(10), 
Grado numeric,
FOREIGN KEY(Id_reservacion) REFERENCES Reservacion
			    ON DELETE NO ACTION 
			    ON UPDATE CASCADE,
FOREIGN KEY(Clave) REFERENCES Escuela
                   ON DELETE NO ACTION
		   ON UPDATE CASCADE
);
CREATE TABLE Labora(
RFC_Profesor varchar(13),
Clave varchar(10),
FOREIGN KEY(RFC_Profesor) REFERENCES Profesor
			  ON DELETE CASCADE
			  ON UPDATE CASCADE,
FOREIGN KEY(Clave) REFERENCES Escuela
                   ON DELETE CASCADE
	           ON UPDATE CASCADE
);

CREATE TABLE Dispone(
Id_vehiculo varchar(18),
Id_zona numeric,
FOREIGN KEY(Id_zona) REFERENCES Zona
                            ON DELETE CASCADE
			    ON UPDATE CASCADE,
FOREIGN KEY(Id_vehiculo) REFERENCES Vehiculo
                            ON DELETE CASCADE
			    ON UPDATE CASCADE	
);
--administrador
CREATE TABLE Administrador(
usuario varchar(20) Primary key,
contrasena varchar(20)
);
insert into Administrador values('Recepcion','@klT90_s');
insert into Administrador values('Ger','parque');
insert into Administrador values('Visitas','@ParJur');