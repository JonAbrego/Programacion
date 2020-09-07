/*
 * Integrantes:
 * Abrego Alvarez Jonathan
 * Baez Tello Carlos Antonio
 */

--1.Obtener los TODOS los nombres completos de los clientes físicos, ordenados por apellido paterno y
--materno alfabéticamente, que han realizado compras de autos marca Seat o Nissan modelos anteriores
--al año 2000
SELECT nombre, apellidop, apellidom FROM PersonaCliente,(SELECT RFCcliente FROM ventas,(SELECT numeromotor 
FROM  Auto WHERE marca='Seat' or marca='Nissan') AS Tabla 
WHERE  ventas.numeromotor=tabla.numeromotor) AS Tabla2
WHERE Tabla2.rfccliente=personacliente.rfc
ORDER BY apellidop,apellidom;
--2.Obtener los TODOS los nombres completos de los clientes físicos que han realizado venta de autos
--con marca que empiece con la letra D o L o M y modelos iguales o superiores al año 2000 (inclusivo)
SELECT nombre, apellidop, apellidom FROM PersonaCliente,(SELECT RFCcliente FROM Compras,(SELECT numeromotor 
FROM  Auto WHERE marca LIKE 'L%' OR marca LIKE 'D%' OR marca LIKE 'M%' AND año>=2000) 
AS Tabla WHERE Compras.numeromotor=tabla.numeromotor) AS Tabla2
WHERE Tabla2.rfccliente=personacliente.rfc;
--3.Obtener TODOS nombres de las empresas y su fecha de creación de aquellas que han realizado
--ventas de autos rojos pero no han realizado compras de autos rojos o verdes.
SELECT nombre FROM Empresa,((SELECT RFCcliente  FROM Compras,(SELECT numeromotor FROM Auto WHERE color='Rojo') tabla
WHERE Compras.numeromotor=tabla.numeromotor)
EXCEPT
(SELECT RFCcliente  FROM Ventas,(SELECT numeromotor FROM Auto WHERE color='Rojo' OR color='Verde') tabla2
WHERE Ventas.numeromotor=tabla2.numeromotor))AS tabla3
WHERE empresa.rfc=tabla3.rfccliente;
--4.Obtener TODOS los RFC de los empleados,fecha de nacimiento y dirección, de aquellos que sean
--valuadores y vendedores cuya calle sea Insurgentes ordenados por fecha de nacimiento del más joven al
--más viejo
SELECT Persona.rfc,fechanacimiento,calle,numero,colonia FROM Valuador, Persona 
WHERE calle= 'Insurgentes' AND valuador.rfc=persona.rfc
INTERSECT
SELECT Vendedor.rfc,fechanacimiento, calle, numero, colonia FROM vendedor,persona 
WHERE calle= 'Insurgentes' AND vendedor.rfc=persona.rfc
ORDER BY fechanacimiento DESC;
--5.El número de autos y su color, de aquellos que fueron vendidos entre 1950 y 2014, agrupados por
--color
SELECT count(Auto.numeromotor)AS Autos_Vendidos,color FROM Ventas, Auto
WHERE fecha >='1950-01-01' AND fecha<='2014-12-31' 
AND Ventas.numeromotor=Auto.numeromotor GROUP BY Auto.color;
--6.Los datos de los autos con precio de venta mayor al precio de venta promedio de los autos
--comprados por un RFC que comience con la letra G o R hasta el 2014
SELECT Auto.* FROM Auto WHERE precioventa>(
SELECT avg(precioventa) AS promedioventas FROM( 
SELECT Auto.*, rfccliente FROM Auto,Ventas WHERE Auto.numeromotor=Ventas.numeromotor)AS tabla 
WHERE tabla.rfccliente LIKE 'G%' OR tabla.rfccliente LIKE 'R%')
ORDER BY precioventa;
--7.Los datos de los clientes cuyo auto vendido en enero de 2013 tiene precio de compra menor al precio
--de compra promedio de la tabla auto
SELECT rfc,nombre,apellidop,apellidom FROM PersonaCliente,
(SELECT Compras.rfccliente FROM Auto,Compras 
WHERE Auto.numeromotor=Compras.numeromotor 
AND Auto.preciocompra < (SELECT avg(preciocompra) FROM Auto) AND 
Compras.fecha >='2013-01-01' AND Compras.fecha<='2013-01-31')as tabla 
WHERE PersonaCliente.rfc=tabla.rfccliente;
--8.Las marcas de autos que tengan más de dos autos en la tabla auto
SELECT marca FROM Auto GROUP BY marca
HAVING count(marca) >= (SELECT count(marca) FROM (SELECT * FROM Auto 
WHERE marca >=(SELECT max(marca) as mayor FROM Auto)) AS tabla1);
--9.El precio de compra mínimo de los autos comprados por el cliente con RFC 'VEFF920314RT5'
SELECT min(preciocompra)as PrecioMinimo FROM 
(SELECT Auto.* FROM Auto,(SELECT numeromotor, rfccliente FROM Ventas 
WHERE rfccliente='VEFF920314RT5')as tabla 
WHERE auto.numeromotor=tabla.numeromotor)as tabla1;
--10.El total de comisiones captadas por las ventas del empleado con menor número de ventas
SELECT tabla3.rfcempleado,min, totalcomision FROM
(SELECT rfcempleado, count(rfcempleado)AS totalventas,sum(comision) AS totalcomision FROM Ventas
GROUP BY rfcempleado) AS tabla3,
(SELECT min(numventas) FROM (
SELECT rfcempleado,count(rfcempleado)as numventas FROM Ventas
GROUP BY rfcempleado)as tabla) as tabla2
WHERE min=totalventas;
--11 "falta" El promedio del sueldo de los valuadores que valuaron 2 
--autos de color rojo o de marca 'Nissan'
SELECT count(rfc),avg(sueldofijo)FROM (SELECT rfc, sueldofijo
FROM Valuador,(SELECT * FROM (SELECT * FROM evaluar,auto 
WHERE evaluar.numeromotor=auto.numeromotor) AS tabla
WHERE color='Rojo' OR marca='Nissan')AS tabla1
WHERE tabla1.rfcempleado=Valuador.rfc) AS tabla2 
GROUP BY rfc HAVING count(rfc)=2;
--12.El número de ventas promedio por vendedor.
SELECT avg(Ventas),rfcempleado FROM 
(SELECT rfcempleado, count(idventa) AS Ventas 
FROM ventas GROUP BY rfcempleado) AS tabla GROUP BY rfcempleado;
--13.Los distintos autos agrupados por condición
SELECT * FROM Auto ORDER BY Condicion;
--14.El número de pagos efectuados por cliente, incluir su nombre y RFC
SELECT PersonaCliente.nombre,tabla.* FROM PersonaCliente,
(SELECT (Ventas.rfccliente)AS rfc,count(Pago.idpago) AS Pagos FROM ventas,pago
WHERE ventas.idventa=pago.idventa GROUP BY rfccliente)AS tabla
WHERE PersonaCliente.rfc=tabla.rfc;
--15 El número de pagos inferiores al monto promedio de pago.
SELECT count(monto) FROM Pago WHERE monto<(SELECT avg(monto)PromedioMonto FROM Pago);
--16
  --16.1 Los datos de los autos cuyo precio de venta sea mayor al promedio de ventas de la tabla
  --     autos y ademas los que fueron pagado en efectivo
  SELECT Auto.* FROM Ventas,Auto 
  WHERE Auto.numeromotor=Ventas.numeromotor AND tipopago='Efectivo'
  UNION
  SELECT * FROM Auto WHERE precioventa >(SELECT avg(precioventa)AS PromedioVentas FROM Auto);
  --16.2 El numero de clientes de la tabla persona clientes que no hayan pagado a credito
  SELECT count(rfc) FROM (SELECT rfc FROM PersonaCliente
  EXCEPT
  SELECT rfccliente FROM Abonar)AS tabla ;
  --16.3 El número(cantidad) de empleados que son vendedores y valuadores a la ves
  SELECT count(rfc)FROM
  (SELECT rfc FROM valuador 
  INTERSECT 
  SELECT rfc FROM vendedor)AS tabla;
  --16.4 La suma total de precio de venta según el tipo de pago
  SELECT sum(Auto.precioventa)TotalVenta,Ventas.tipopago FROM Auto,Ventas
  WHERE Auto.numeromotor=Ventas.numeromotor GROUP BY tipopago;
  --16.5 El numero de telefonos y rfc de las personas con mas de un numero telefonico
  SELECT count(telefono)cantidadTelefonos,rfc FROM telefonospersona GROUP BY rfc HAVING count(telefono)>1;
--E1 Obtener el número de ventas promedio por año de autos de color rojo o verde con valor de venta
--   mayor a $20000.00 (mostrar año y promedio)
SELECT avg(ventas)PromedioVentas FROM(SELECT count(idventa)ventas,Ventas.numeromotor from ventas, 
(SELECT * FROM auto WHERE precioventa>20000 AND color='Rojo' OR color='Verde')  AS tabla
WHERE tabla.numeromotor=ventas.numeromotor AND fecha >='2014-01-01' AND fecha<='2014-12-31'
GROUP BY Ventas.numeromotor)AS tabla1;
--E2 Obtener el número de empleados cuyo nombre comience con alguna letra a partir de G, ie. G, H,
--   I, ... Agrupados por año de nacimiento, y su total de autos vendidos, a clientes de más de 
--   treinta años,entre todos los vendedores del mismo año (mostrar número de empleados, año de 
--   nacimiento y total de autos vendidos)
SELECT count(rfc)Empleados,count(idventa)Ventas FROM Ventas,(SELECT valuador.*
FROM Valuador,Vendedor WHERE Valuador.rfc=Vendedor.rfc)AS tabla
WHERE Ventas.rfcempleado=tabla.rfc AND tabla.nombre ~ '^[G-Z]'
GROUP BY fechanacimiento,idventa;
