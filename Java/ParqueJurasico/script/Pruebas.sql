--Cada lista de alumnos y zonas a visitar dependiendo del id de reservacion 
select Zonas_Visita.id_zona,t2.* from Zonas_Visita,
(select alumno.*,t1.Id_reservacion from Alumno,
(select * from Lista_Alumnos where 
Id_reservacion=1) as t1
where Alumno.curp=t1.alumno) as t2
where Zonas_Visita.id_reservacion=t2.id_reservacion;

select Zonas_Visita.id_zona,t2.* from Zonas_Visita,
(select alumno.*,t1.Id_reservacion from Alumno,
(select * from Lista_Alumnos where 
Id_reservacion=2) as t1
where Alumno.curp=t1.alumno) as t2
where Zonas_Visita.id_reservacion=t2.id_reservacion;

select Zonas_Visita.id_zona,t2.* from Zonas_Visita,
(select alumno.*,t1.Id_reservacion from Alumno,
(select * from Lista_Alumnos where 
Id_reservacion=3) as t1
where Alumno.curp=t1.alumno) as t2
where Zonas_Visita.id_reservacion=t2.id_reservacion;

select Zonas_Visita.id_zona,t2.* from Zonas_Visita,
(select alumno.*,t1.Id_reservacion from Alumno,
(select * from Lista_Alumnos where 
Id_reservacion=4) as t1
where Alumno.curp=t1.alumno) as t2
where Zonas_Visita.id_reservacion=t2.id_reservacion;

select Zonas_Visita.id_zona,t2.* from Zonas_Visita,
(select alumno.*,t1.Id_reservacion from Alumno,
(select * from Lista_Alumnos where 
Id_reservacion=5) as t1
where Alumno.curp=t1.alumno) as t2
where Zonas_Visita.id_reservacion=t2.id_reservacion;

select Zonas_Visita.id_zona,t2.* from Zonas_Visita,
(select alumno.*,t1.Id_reservacion from Alumno,
(select * from Lista_Alumnos where 
Id_reservacion=6) as t1
where Alumno.curp=t1.alumno) as t2
where Zonas_Visita.id_reservacion=t2.id_reservacion;

select Zonas_Visita.id_zona,t2.* from Zonas_Visita,
(select alumno.*,t1.Id_reservacion from Alumno,
(select * from Lista_Alumnos where 
Id_reservacion=7) as t1
where Alumno.curp=t1.alumno) as t2
where Zonas_Visita.id_reservacion=t2.id_reservacion;

select Zonas_Visita.id_zona,t2.* from Zonas_Visita,
(select alumno.*,t1.Id_reservacion from Alumno,
(select * from Lista_Alumnos where 
Id_reservacion=8) as t1
where Alumno.curp=t1.alumno) as t2
where Zonas_Visita.id_reservacion=t2.id_reservacion;

select Zonas_Visita.id_zona,t2.* from Zonas_Visita,
(select alumno.*,t1.Id_reservacion from Alumno,
(select * from Lista_Alumnos where 
Id_reservacion=9) as t1
where Alumno.curp=t1.alumno) as t2
where Zonas_Visita.id_reservacion=t2.id_reservacion;

select Zonas_Visita.id_zona,t2.* from Zonas_Visita,
(select alumno.*,t1.Id_reservacion from Alumno,
(select * from Lista_Alumnos where 
Id_reservacion=10) as t1
where Alumno.curp=t1.alumno) as t2
where Zonas_Visita.id_reservacion=t2.id_reservacion;

--Cada lista de profesores y zonas a visitar dependiendo del id de reservacion 
select Zonas_Visita.id_zona,t2.* from Zonas_Visita,
(select Profesor.*,t1.Id_reservacion from Profesor,
(select * from Lista_Profesores where 
Id_reservacion=1) as t1
where Profesor.rfc_profesor=t1.profesor) as t2
where Zonas_Visita.id_reservacion=t2.id_reservacion;

select Zonas_Visita.id_zona,t2.* from Zonas_Visita,
(select Profesor.*,t1.Id_reservacion from Profesor,
(select * from Lista_Profesores where 
Id_reservacion=2) as t1
where Profesor.rfc_profesor=t1.profesor) as t2
where Zonas_Visita.id_reservacion=t2.id_reservacion;

select Zonas_Visita.id_zona,t2.* from Zonas_Visita,
(select Profesor.*,t1.Id_reservacion from Profesor,
(select * from Lista_Profesores where 
Id_reservacion=3) as t1
where Profesor.rfc_profesor=t1.profesor) as t2
where Zonas_Visita.id_reservacion=t2.id_reservacion;

select Zonas_Visita.id_zona,t2.* from Zonas_Visita,
(select Profesor.*,t1.Id_reservacion from Profesor,
(select * from Lista_Profesores where 
Id_reservacion=4) as t1
where Profesor.rfc_profesor=t1.profesor) as t2
where Zonas_Visita.id_reservacion=t2.id_reservacion;

select Zonas_Visita.id_zona,t2.* from Zonas_Visita,
(select Profesor.*,t1.Id_reservacion from Profesor,
(select * from Lista_Profesores where 
Id_reservacion=5) as t1
where Profesor.rfc_profesor=t1.profesor) as t2
where Zonas_Visita.id_reservacion=t2.id_reservacion;

select Zonas_Visita.id_zona,t2.* from Zonas_Visita,
(select Profesor.*,t1.Id_reservacion from Profesor,
(select * from Lista_Profesores where 
Id_reservacion=6) as t1
where Profesor.rfc_profesor=t1.profesor) as t2
where Zonas_Visita.id_reservacion=t2.id_reservacion;

select Zonas_Visita.id_zona,t2.* from Zonas_Visita,
(select Profesor.*,t1.Id_reservacion from Profesor,
(select * from Lista_Profesores where 
Id_reservacion=7) as t1
where Profesor.rfc_profesor=t1.profesor) as t2
where Zonas_Visita.id_reservacion=t2.id_reservacion;

select Zonas_Visita.id_zona,t2.* from Zonas_Visita,
(select Profesor.*,t1.Id_reservacion from Profesor,
(select * from Lista_Profesores where 
Id_reservacion=8) as t1
where Profesor.rfc_profesor=t1.profesor) as t2
where Zonas_Visita.id_reservacion=t2.id_reservacion;

select Zonas_Visita.id_zona,t2.* from Zonas_Visita,
(select Profesor.*,t1.Id_reservacion from Profesor,
(select * from Lista_Profesores where 
Id_reservacion=9) as t1
where Profesor.rfc_profesor=t1.profesor) as t2
where Zonas_Visita.id_reservacion=t2.id_reservacion;

select Zonas_Visita.id_zona,t2.* from Zonas_Visita,
(select Profesor.*,t1.Id_reservacion from Profesor,
(select * from Lista_Profesores where 
Id_reservacion=10) as t1
where Profesor.rfc_profesor=t1.profesor) as t2
where Zonas_Visita.id_reservacion=t2.id_reservacion;

--Numero de alumnos por cada escuela
select t1.No_alumnos, Escuela.* from Escuela
NATURAL JOIN 
(select count(Alumno.curp)No_alumnos,clave from Alumno
group by clave)as t1;

--Numero de zonas a visitar por escuela, dependiendo del grado
select count(id_zona)No_zonas, clave,grado from
(select realiza.*,zonas_visita.id_zona from realiza,zonas_visita
where realiza.id_reservacion=zonas_visita.id_reservacion) as t1
group by clave,grado
order by clave,grado;

--la escuela con mayor numero de telefonos
select count(telefono)No_tel,clave from Telefono
group by clave
having count(telefono)= (select max(No_tel) from (select count(telefono)No_tel,clave from Telefono
group by clave)t1);

--la informacion de las reservaciones que fueron hechas antes de las 9:00, y que tengan un precio menor a 500 
-- ademas estar ordenadas respecto a la fecha, y añadir una columna de la clave de la escuela que pertenece dicha 
--reservación
select t1.*, clave from 
(select * from Reservacion where hora<'09:00' and precio<500
order by fecha) as t1
natural join
Realiza;

--la tabla profesores indicando donde laboran (escuela a que pertenecen), la zona que tienen destinado a visitar y el 
--grado que llevan como responsable
select profesor.*,clave,id_zona,grado from profesor,
(select profesor,clave,id_zona,grado from 
(select * from lista_profesores
natural join
Realiza) as t1 
natural join
zonas_visita) as t1
where Profesor.rfc_profesor=t1.profesor;

--pago realizado por cada escuela
select count(id_reservacion)No_reservaciones,sum(precio)Pago_todas_reserva, clave from
(select * from Reservacion
natural join
Realiza) as t1
group by clave;

--cantidad y tipo de de vehículos por cada zona
select count(id_vehiculo)No_vehiculos,tipo,id_zona from
(select * from dispone
natural join
vehiculo) as t1
group by id_zona,tipo
order by id_zona;

--numero de personal por cada zona y el promedio de sueldo también por cada zona
select count(RFC)No_trabajadores,avg(sueldo),id_zona 
from trabajador group by id_zona;

--promedio de pagos realizados a los trabajadores agrupados segun la labor(ingeniero,intendecia,paleontologo,veterinadrio,etc...) 
select avg(sueldo),puesto from Trabajador
group by puesto;

--numero de reservaciones respecto a la hora, y la cantidad de dinero que percibira según el numero 
--de reservaciones respecto a la hora
select count(hora)No_reservaciones,hora,sum(Precio) from Reservacion
group by hora;

--La zona con mas reservaciones (que mas solicitan las escuelas) y quienes la solicitan 
select clave,id_zona from Realiza
natural join
(select * from Zonas_Visita
natural join
(select id_zona from 
(select count(id_zona)No_visitas,id_zona from Zonas_Visita 
group by id_zona) as t1
group by id_zona
having max(no_visitas)= (select max(No_visitas) from (select count(id_zona)No_visitas from zonas_visita 
group by id_zona)as t2))as t3) as t4;

--La zona con menos reservaciones
select id_zona from 
(select count(id_zona)No_visitas,id_zona from Zonas_Visita 
group by id_zona) as t1
group by id_zona
having min(no_visitas)= (select min(No_visitas) from (
select count(id_zona)No_visitas from zonas_visita 
group by id_zona)as t2);

--Lista de los alumnos que no iran al parque
select curp,nombre,apellido_paterno,apellido_materno from Alumno
except
select * from 
(select curp,nombre,apellido_paterno,apellido_materno from Zonas_Visita,
(select alumno.*,t1.Id_reservacion from Alumno,
(select * from Lista_Alumnos) as t1
where Alumno.curp=t1.alumno) as t2
where Zonas_Visita.id_reservacion=t2.id_reservacion
group by curp,nombre,apellido_paterno,apellido_materno) as t3;

--Lista de reservaciones programadas de agosto en adelante
--escuelas que asitiran, numero de zonas 
--a visitar,cantidad de alunmos y profesores   3,4,6,7
create view calendario as
select t5.*, No_profesores from
(select t3.*,No_alumnos from
(select t1.*,No_zonas from
(select * from Reservacion 
natural join 
Realiza) as t1
natural join
(select count(id_zona)No_zonas, id_reservacion from zonas_visita
group by id_reservacion order by id_reservacion) as t2)as t3
natural join
(select count(alumno)No_alumnos,id_reservacion from lista_alumnos
group by id_reservacion) as t4) as t5
natural join
(select count(profesor)No_profesores,id_reservacion from lista_profesores
group by id_reservacion) as t6;
select * from calendario;

create view Reserva as
(select id_reservacion, hora, fecha, clave,nombre from
(SELECT clave,Nombre,id_reservacion FROM Escuela
NATURAL JOIN 
Realiza) as t1
natural join
reservacion);
select * from reserva;

select id_reservacion,profesor,nombre,apellido_paterno from lista_profesores
natural join 
profesor
where lista_profesores.profesor=profesor.rfc_profesor
order by id_reservacion;

select id_reservacion,curp,nombre,apellido_paterno 
from lista_alumnos
natural join 
alumno
where lista_alumnos.alumno=alumno.curp
order by id_reservacion;
 
--La escuela y los grados que no tendran visita al parque
select clave,grado from Alumno
except
select clave,grado from Alumno,
(select * from Lista_Alumnos) as t1
where Alumno.curp=t1.alumno;

select clave from realiza
where id_reservacion=13;