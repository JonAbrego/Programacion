/**
 *Abrego Alvarez Jonathan
 *Baez Tello Carlos Antonio
 */
--1. Determinar los vendedores que han vendido (tabla ventas) dos autos de año mayor a 2000 y que no son valuadores; (vendedores)
SELECT rfc,nombre,apellidop,apellidom,fechanacimiento FROM
(SELECT t2.rfc,t2.nombre,t2.numeromotor,t2.apellidop,t2.apellidom,t2.fechanacimiento,Auto.año,t2.numeroventas FROM
(SELECT * FROM
(SELECT * FROM  Vendedor WHERE rfc NOT IN (SELECT rfc FROM Valuador)) AS t0 
JOIN
(SELECT rfcempleado,numeromotor,count(rfcempleado)NumeroVentas FROM Ventas GROUP BY rfcempleado,numeromotor) AS t1
ON t0.rfc=t1.rfcempleado) AS t2,Auto
WHERE Auto.numeromotor=t2.numeromotor)as t3
WHERE numeroventas=2 AND año > 2000;
--2. Determinar los valuadores que no hayan comprado o vendido un auto a la agencia donde trabajan; (valuador) 
SELECT * FROM Valuador WHERE rfc NOT IN (SELECT rfccliente FROM Compras) AND rfc NOT IN (SELECT rfccliente FROM Ventas);
--3. Determinar los clientes físicos que trabajan para la agencia, ya sea como valuadores o vendedores; (clientes físicos)
SELECT t1.rfc,t1.nombre,t2.rfc,t2.nombre FROM 
(SELECT * FROM Vendedor WHERE rfc NOT IN (SELECT rfc FROM Empresa)) AS t1
FULL JOIN
(SELECT * FROM Valuador WHERE rfc NOT IN (SELECT rfc FROM Empresa)) AS t2
ON t1.rfc=t2.rfc;
--4. Determinar si alguna empresa con domicilio con calle Revolución ha comprado algún auto;(Empresa, auto comprado)
SELECT Empresa.* FROM Empresa NATURAL JOIN Persona
WHERE  Persona.calle='Revolución' AND Empresa.rfc=SOME(SELECT rfccliente FROM  Compras);
--5. Obtener los RFC de la tabla persona que han efectuado compras de autos negros en el mes de abril; (tabla ventas) (RFC)
SELECT t2.rfc FROM (SELECT * FROM Auto JOIN (SELECT * FROM Persona JOIN Ventas ON Persona.rfc=Ventas.rfccliente)AS t1 
ON Auto.numeromotor=t1.numeromotor)AS t2 WHERE color='Negro' AND fecha>='2014-04-01' AND fecha<='2014-04-30';
--6. Obtener los RFC de la tabla persona que han vendido al menos un auto naranja; (tabla compras)(RFC)
SELECT rfc FROM Persona WHERE rfc  IN (SELECT rfccliente FROM Compras,Auto 
				       WHERE Compras.numeromotor=Auto.numeromotor 
				             AND auto.color='Naranja');
--7. Obtener el nombre y apellidos de las personas que no tienen teléfono; (nombre, apellidop,apellidom)
SELECT nombre,apellidop,apellidom
FROM PersonaCliente WHERE rfc NOT IN (SELECT rfc FROM TelefonosPersona);
--8. Obtener los nombres y dirección de las empresas que no tienen correo electrónico y que han vendido (tabla compras)
--autos a la agencia; (nombre, dirección)
SELECT nombre,calle,numero,colonia FROM
(SELECT rfc,nombre FROM
(SELECT * FROM Empresa WHERE rfc NOT IN (SELECT rfc FROM CorreosPersona)) AS t1
JOIN
(SELECT * FROM Compras)AS t2
ON t1.rfc=t2.rfccliente)AS t3
JOIN
(SELECT * FROM Persona)AS t4
ON t3.rfc=t4.rfc;
--9. Obtener el nombre de los valuadores con sueldo igual a 2000, 3000, 4000 usar ANY; (nombre,apellidos)
SELECT Valuador.nombre,Valuador.apellidop, Valuador.apellidom FROM Valuador
WHERE  sueldofijo= ANY(SELECT sueldofijo FROM Valuador
WHERE sueldofijo=2000 OR sueldofijo=3000 OR sueldofijo=4000);
--10. Obtener los clientes que no tienen registro alguno de alguna compra (tabla ventas), usar OUTER JOIN; (clientes)
SELECT * FROM
(SELECT * FROM Empresa WHERE rfc NOT IN (SELECT rfccliente FROM Ventas))AS t1
FULL JOIN
(SELECT * FROM PersonaCliente WHERE rfc NOT IN (SELECT rfccliente FROM Ventas))AS t2
ON t1.rfc=t2.rfc;
--11. Obtener los clientes que tienen registro de alguna venta (tabla compras) usar OUTER JOIN;(clientes)
SELECT * FROM
(SELECT * FROM Empresa WHERE rfc IN (SELECT rfccliente FROM Compras)) AS t2
FULL OUTER JOIN
(SELECT * FROM PersonaCliente WHERE rfc IN (SELECT rfccliente FROM Compras)) AS t1
ON t1.rfc=t2.rfc;
--12. Obtener los clientes que han efectuado compras de autos verdes y compras de autos grises,emplear THETA JOIN; (clientes)
SELECT t1.rfccliente FROM 
(SELECT * FROM Compras,Auto WHERE Compras.numeromotor=Auto.numeromotor AND color='Verde')AS t1
JOIN 
(SELECT * FROM Ventas,Auto WHERE Ventas.numeromotor=Auto.numeromotor AND color='Gris') AS t2
ON t1.rfccliente=t2.rfccliente;
--13. La tabla de ventas más una columna que indique el monto total efectuado por todos los pagos correspondientes a 
--cada venta; (campos de ventas, monto total recibido por esa venta)
SELECT Ventas.*,t1.MontoTotal FROM Ventas
FULL JOIN
(SELECT sum(monto)MontoTotal,idventa FROM Pago GROUP BY idventa)AS t1
ON Ventas.idventa=t1.idventa;
--14. Todos los datos de los clientes que han efectuado abonos.
SELECT pagos,curp,nombre,apellidop,apellidom,fechanacimiento,t4.* FROM
(SELECT pagos,rfc,curp,nombre,apellidop,apellidom,fechanacimiento FROM
(SELECT rfccliente,count(rfccliente)Pagos FROM Abonar GROUP BY rfccliente) AS t1
JOIN
(SELECT * FROM PersonaCliente)AS t2
ON t1.rfccliente=t2.rfc)AS t3
JOIN
(SELECT * FROM Persona)AS t4
ON t3.rfc=t4.rfc;
--15. El número de pagos efectuados por cliente con nombre 'carlos' con un monto mayor al promedio de monto de la 
--tabla pago; (cliente, numPagos). 
SELECT NumeroPagos,t2.rfc,t2.nombre,t2.apellidop,t2.apellidom,Cantidad FROM 
    (SELECT * FROM 
          (SELECT rfccliente,count(Pago.idpago)NumeroPagos,sum(monto)Cantidad 
           FROM Abonar,Pago WHERE Pago.idpago=Abonar.idpago 
           GROUP BY rfccliente)AS t1,
     PersonaCliente WHERE t1.rfccliente=PersonaCliente.rfc)AS t2,
PersonaCliente
WHERE t2.rfccliente=PersonaCliente.rfc AND Cantidad>(SELECT avg(monto)PromedioMonto FROM Pago);