/*
 * Práctica 11
 * Abrego Alvarez Jonathan
 */

--CREATE PROCEDURAL LANGUAGE plpgsql;

--1. Escribir los procedimientos almacenados para elevar al cuadrado un número dado, dividir dos
--   números dados, obtener la suma de los n primeros naturales, obtener la suma de los n primeros
--   cuadrados. (0.5 pts c/u)
     CREATE OR REPLACE FUNCTION cuadrado(int) RETURNS int AS $$
     DECLARE
        numero int :=$1;
     BEGIN
       RETURN numero*numero;
     END;
     $$ 
     LANGUAGE plpgsql;
     SELECT cuadrado(10);
---------------------------------------------------------------------------------     
     CREATE OR REPLACE FUNCTION dividir(float,float) RETURNS text AS $$
     DECLARE 
         numero1 float := $1;
         numero2 float := $2;
     BEGIN
	IF $2 = 0  THEN 
	RETURN 'No se puede dividir entre 0';
	END IF;
        RETURN (numero1/numero2);
     END;
     $$
     LANGUAGE plpgsql;
     SELECT dividir(13,-5);
---------------------------------------------------------------------------------
     CREATE OR REPLACE FUNCTION suma_n(int) RETURNS int AS $$
     DECLARE 
         numero int := $1;
     BEGIN
	RETURN (numero*(numero+1))/2;
     END;
     $$
     LANGUAGE plpgsql;
     SELECT suma_n(13);
---------------------------------------------------------------------------------
     CREATE OR REPLACE FUNCTION suma_cuadrados(int) RETURNS int AS $$
     DECLARE 
         n int := $1;
     BEGIN
        RETURN (n*((n+1)*((2*n)+1)))/6;
     END;
     $$
     LANGUAGE plpgsql;
     SELECT suma_cuadrados(5);

CREATE TABLE Empleado(
RFC varchar(13)
    Primary Key,
Nombre varchar(20),
Paterno varchar(20),
Salario float
);
INSERT INTO Empleado VALUES('AJAS801228HDF','Geovanni','Mengano',3500);
INSERT INTO Empleado VALUES('AJAS880216MDF','Karla','Alvarez',3200);
INSERT INTO Empleado VALUES('AJAS700424HDF','David','Salgado',3700);
INSERT INTO Empleado VALUES('AJAS691029MDF','Gabriela','Zarraga',4000);
INSERT INTO Empleado VALUES('AJAS751113HDF','Ernesto','Figueroa',3000);
INSERT INTO Empleado VALUES('AJAS880701MDF','Monserrat','Martinez',3900);

--2. Escribir una consulta sobre tablas que se obtengan de un procedimiento almacenado sin
--   parámetros (1.5 pts)
CREATE OR REPLACE FUNCTION promSaldos() RETURNS SETOF float AS $$
DECLARE 
r float;
BEGIN 
    FOR r IN SELECT salario FROM empleado LOOP
    RETURN NEXT r;
    END LOOP;
    RETURN ;
    
END;
$$
LANGUAGE plpgsql;
select promSaldos();

SELECT avg(promsaldos)PromedioSaldos FROM (select promSaldos()) AS t1;

--3. Escribir una consulta sobre tablas que se obtengan de un procedimiento almacenado con
--   parámetros (1.5 pts)
CREATE OR REPLACE FUNCTION empMayor(float) RETURNS setof text
AS $$
DECLARE 
r float:=$1;
nom text;
BEGIN 
 IF r > avg(salario) from Empleado then
	FOR nom IN SELECT nombre FROM empleado WHERE salario > r LOOP
	RETURN NEXT nom;
	END LOOP;
	RETURN ;
 END IF;
END;
$$
LANGUAGE plpgsql;
SELECT empMayor(3700);

SELECT * FROM (SELECT empMayor(3700))AS t1
WHERE empMayor ~'[A-G]';

--4. Escribir un disparador para INSERT y UPDATE correspondientes para una(s) tabla(s) de tu
--   elección que ejecuten lo siguiente (cada uno) (1.25 pts):
--      1. Validación de dos datos (check, not null, etc)
--      2. Conteo de las tuplas afectadas en una tabla de log con un contador de tuplas afectada(incremento)
--      3. Alguna otra operación que se te ocurra, distinta a las anteriores
CREATE OR REPLACE FUNCTION chek_emp() RETURNS trigger AS $chek_emp$
    BEGIN
	IF NEW.rfc !~ '[A-Z]{4}[0-9]{6}[A-Z]{3}'THEN
		RAISE EXCEPTION 'Formato de rfc invalido';
	END IF;
	IF NEW.nombre IS NULL THEN
	        RAISE EXCEPTION 'El nombre no puede ser null',NEW.nombre;
	END IF;
	IF EXISTS (select rfc from empleado where rfc=NEW.rfc) then
		RAISE EXCEPTION 'Solo puede actualizar su salario';
	END IF;
        RETURN NEW;
    END;
$chek_emp$ 
LANGUAGE plpgsql;
CREATE TRIGGER chek_emp BEFORE INSERT OR UPDATE ON Empleado
FOR EACH ROW EXECUTE PROCEDURE chek_emp();

--5. Escribir un disparador para DELETE correspondientes para una(s) tabla(s) de tu elección que
--   ejecuten lo siguiente (cada uno) (1.25 pts):
--      1. Conteo de tuplas afectadas en una tabla de log con un contador de tuplas afectadas (decremento)
--      2. Alguna otra operación que se te ocurra
CREATE OR REPLACE FUNCTION borr_emp() RETURNS trigger AS $borr_emp$
BEGIN
	IF (TG_OP = 'DELETE') THEN
	   CREATE VIEW Empleados_completos AS 
	   SELECT * FROM Empleado;
	END IF;
	RETURN Empleado.nombre Empleados_completos;
END;
$borr_emp$ 
LANGUAGE plpgsql;
CREATE TRIGGER borr_emp BEFORE DELETE ON Empleado
FOR EACH ROW EXECUTE PROCEDURE borr_emp();

--6. Escribir dos disparadores de forma libre (1.25 pts c/u)
CREATE OR REPLACE FUNCTION nuevo_Emp() RETURNS trigger AS $nuevo_Emp$
    BEGIN
	IF EXISTS(select rfc from empleado where rfc=new.rfc) THEN
            RAISE EXCEPTION 'ya existe ese rfc';
        END IF;        
        IF NEW.salario IS NULL OR NEW.salario < 0 THEN
            RAISE EXCEPTION '% salario debe ser positivo y distinto de null', NEW.nombre;
        END IF;
        RETURN NEW;
    END;
$nuevo_Emp$ 
LANGUAGE plpgsql;
CREATE TRIGGER nuevo_Emp BEFORE INSERT ON Empleado
FOR EACH ROW EXECUTE PROCEDURE nuevo_Emp();


CREATE OR REPLACE FUNCTION aumenta_salario() RETURNS trigger AS $aumenta_salario$
    BEGIN
        IF NEW.salario < OLD.salario THEN
            RAISE EXCEPTION 'El aumento de % debe ser mayor', NEW.nombre;
        END IF;
        RETURN NEW;
    END;
$aumenta_salario$ 
LANGUAGE plpgsql;
CREATE TRIGGER aumenta_salario BEFORE UPDATE ON Empleado
FOR EACH ROW EXECUTE PROCEDURE aumenta_salario();