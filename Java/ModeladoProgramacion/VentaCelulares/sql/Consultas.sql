--El empleado que ha hecho mas ventas
SELECT RFC_Vendedor,max(Ventas)Ventas,Num_ventas FROM
(SELECT t1.rfc_vendedor,count(rfc_vendedor)Num_Ventas,sum(Pago)Ventas 
FROM Comprar,
(SELECT imei, rfc_vendedor FROM Vender)AS t1
WHERE Comprar.imei=t1.imei
GROUP BY rfc_vendedor)AS t2;

--La marca de telefonos mas vendidad
SELECT Marca,max(Numero_ventas)Ventas FROM
(SELECT count(Marca)Numero_ventas,Marca 
FROM Telefono
GROUP BY Marca)AS T1;

--la compañia que mas telefono ha vendido
SELECT rfc_empresa,max(num_ventas) FROM 
(SELECT rfc_empresa,(count(rfc_empresa)*ventas)num_ventas FROM Trabajar,
(SELECT rfc_vendedor,count(rfc_vendedor)ventas 
FROM Vender
GROUP BY rfc_vendedor) AS t1
WHERE t1.rfc_vendedor=Trabajar.rfc_empleado
GROUP BY rfc_empresa)AS t2

--Encontrar el nombre completo y rfc del empleado que gana mas 
--en  cada compañia 
SELECT Persona.rfc,nombre,apellido_p,apellido_m,MaximoSueldo,rfc_empresa FROM Persona,
(SELECT rfc, max(Sueldo)MaximoSueldo,rfc_empresa FROM Empleado,
(SELECT rfc_empleado,rfc_empresa FROM Trabajar) AS T1
WHERE Empleado.rfc=T1.rfc_empleado
GROUP BY rfc_empresa) AS T1
WHERE Persona.rfc=T1.rfc;

--
SELECT imei,T1.rfc,pago FROM Comprar,
(SELECT rfc FROM Cliente
EXCEPT SELECT rfc FROM Persona) AS T1
WHERE Comprar.rfc=T1.rfc;

