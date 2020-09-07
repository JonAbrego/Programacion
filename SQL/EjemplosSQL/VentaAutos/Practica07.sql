/*
 *Integrantes:
 *Abrego Alvarez Jonathan
 *Carlos Antonio Baez Tello 
 */
 
--Ejercicio 1
SELECT * FROM Valuador WHERE fechanacimiento<'1970-01-01';

--Ejercicio 2
SELECT * FROM Auto WHERE marca like 'F%';

--Ejercicio 3
SELECT * FROM Empresa WHERE fechacreacion>='1999-01-31';

--Ejercicio 4
SELECT * FROM Empresa WHERE nombre='Banca comercial europea'OR fechacreacion<'2012-01-01';

--Ejercicio 5
SELECT * FROM Personacliente WHERE rfc >' AEFF9012RT5' ORDER BY rfc ;

--Ejercicio 6
SELECT * FROM Valuador WHERE NOT sueldofijo > 15000; 

--Ejercicio 7
SELECT Vendedor.rfc FROM Vendedor WHERE fechanacimiento > '1920-01-01' AND fechanacimiento < '1953-01-01';

--Ejercicio 8
SELECT Auto.color FROM Auto,Ventas WHERE Auto.numeromotor=Ventas.numeromotor;

--Ejercicio 9 
SELECT Persona.rfc INTO T1 FROM Persona,Ventas,Compras WHERE Persona.rfc=Ventas.rfccliente AND Persona.rfc=Compras.rfccliente;
SELECT PersonaCliente.nombre,PersonaCliente.apellidop,PersonaCliente.apellidom FROM PersonaCliente,T1 WHERE PersonaCliente.rfc=T1.rfc
GROUP BY nombre,apellidop,apellidom;
--Si se desea borrar la tabla creada (T1), despues de la consulta realizada usamos "DROP TABLE T1" para dejar nuestra base como al principio;

--Ejercicio 10
SELECT Pago.idpago,to_char(Pago.fecha,'dd/mm/yy')AS fecha,to_char(Pago.monto::real,'$9999.99')AS pago,Pago.idventa,
PersonaCliente.nombre,PersonaCliente.apellidop,PersonaCliente.apellidom FROM PersonaCliente,Ventas,Pago 
WHERE PersonaCliente.rfc=Ventas.rfccliente AND Ventas.idventa=Pago.idventa;

--Ejercicio 11
/*
 * Los RFC de los clientes (PersonaCliente y Empresa) con fecha entre 1970 y 1990 ordenados de forma decendente
 * además de la fecha con el formato dd/mm/yy
 */
SELECT rfc,to_char(PersonaCliente.fechanacimiento,'dd/mm/yy')AS fecha FROM PersonaCliente 
WHERE fechanacimiento >='1970-01-01' AND fechanacimiento <='1989-12-31'
UNION
SELECT rfc,to_char(Empresa.fechacreacion,'dd/mm/yy')AS fecha FROM Empresa 
WHERE fechacreacion >='1970-01-01' AND fechacreacion <='1989-12-31' ORDER BY rfc DESC;

/*
 * Nombre, sueldo con formato (es decir la cantidad precedida de alguna signo monetario)
 * año de nacimiento y direccion completa de los valuadores,con nombre distinto de Veronica y que vivan en la Agricola Oriental, 
 * ordenados de forma alfabetica respecto al numero(Dirección)
 */
SELECT valuador.nombre, to_char(Valuador.sueldofijo::real,'$9999D99')Sueldo,to_char(Valuador.fechanacimiento,'YYYY')AñoNacimiento, 
Persona.calle,Persona.numero,Persona.colonia FROM Persona,Valuador 
WHERE  Persona.rfc = Valuador.rfc AND valuador.nombre !='Veronica' 
AND colonia ='Agricola Oriental' ORDER BY numero;
/*
 * El modelo de los autos vendidos,color y año(representado en forma de texto), que no sean de color negro, y tengan año de fabricacion entre 1999 y 2008, ordenados 
 * alfabeticamente respecto al color
 */
SELECT Auto.modelo,Auto.color,to_char(Auto.año,'9999') Año FROM Auto,Ventas 
WHERE Auto.numeromotor=Ventas.numeromotor AND Auto.color!='Negro' 
AND Auto.año BETWEEN 1999 AND 2008  ORDER BY Auto.color;
/*
 * El nombre de las personas,los 2 ultimos digistos de su año de nacimiento, de las personas con correos del dominio gmail, ordenados 
 * respecto a su año de nacimiento
 */
SELECT PersonaCliente.nombre,to_char(PersonaCliente.fechanacimiento,'YY')AñoNacimiento
FROM CorreosPersona,PersonaCliente 
WHERE correo like '%gmail%' AND CorreosPersona.rfc=PersonaCliente.rfc ORDER BY AñoNacimiento;
