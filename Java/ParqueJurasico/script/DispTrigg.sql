--Funcion que sirve para hacer un unico listado de las fechas  de 
-- las reservaciones que hay en el sistema 
CREATE OR REPLACE FUNCTION fechas() RETURNS SETOF text AS $$
DECLARE 
r text;
BEGIN 
    FOR r IN SELECT fecha FROM reservacion LOOP
    RETURN NEXT r;
    END LOOP;
    RETURN ;
    
END;
$$
LANGUAGE plpgsql;
--Trigger usada para que cuando se inserte una nueva reservacion
--o acutalizacion sobre algun Reservacion ya echa, solo permita 
--modificacion de fecha u hora
CREATE OR REPLACE FUNCTION chek_res() RETURNS trigger AS $chek_res$
    BEGIN
	IF NEW.hora IS NULL THEN
		RAISE EXCEPTION 'Hora invalida invalido';
	END IF;
	IF NEW.fecha IS NULL THEN
	        RAISE EXCEPTION 'La fecha no puede ser null',NEW.fecha;
	END IF;
	IF EXISTS (select id_reservacion from Reservacion where id_reservacion=NEW.id_reservacion) then
		RAISE EXCEPTION 'Solo puede actualizar fecha u hora';
	END IF;
        RETURN NEW;
    END;
$chek_res$ 
LANGUAGE plpgsql;

CREATE TRIGGER chek_res BEFORE INSERT OR UPDATE ON Reservacion
FOR EACH ROW EXECUTE PROCEDURE chek_res();

--Trigger usado para verificar la longitud del telefono ingresado
CREATE OR REPLACE FUNCTION chek_tel() RETURNS trigger AS $chek_tel$
    BEGIN
	IF NEW.telefono !~'[0-9]{10}' THEN
		RAISE EXCEPTION 'El telefono debe ser de 10 caracteres en caso de solo
		tener 8 digitos, agrege  55 antes de escribir su numero';
	END IF;
	RETURN NEW;
    END;
$chek_tel$ 
LANGUAGE plpgsql;

CREATE TRIGGER chek_tel BEFORE INSERT OR UPDATE ON Telefono
FOR EACH ROW EXECUTE PROCEDURE chek_tel();


--triger usador para verificar el rfc del empleado
CREATE OR REPLACE FUNCTION chek_emp() RETURNS trigger AS $chek_emp$
    BEGIN
	IF NEW.rfc !~ '[A-Z]{4}[0-9]{6}[A-Z]{3}'THEN
		RAISE EXCEPTION 'Formato de rfc invalido';
	END IF;
	IF EXISTS (select rfc from trabajador where rfc=NEW.rfc) then
		RAISE EXCEPTION 'No puede actualiza el rfc, el resto de valores si';
	END IF;
        RETURN NEW;
    END;
$chek_emp$ 
LANGUAGE plpgsql;

CREATE TRIGGER chek_emp BEFORE INSERT OR UPDATE ON Trabajador
FOR EACH ROW EXECUTE PROCEDURE chek_emp();