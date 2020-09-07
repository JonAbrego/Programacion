--CREATE DATABASE  practica05
CREATE SCHEMA jonathan;

CREATE TABLE Auto(Número_de_motor varchar(17), Modelo varchar(15), Marca varchar(10), Año smallint,
                  Color varchar(10), Kilometraje numeric, Condición char(2), Precio_de_compra float, 
                  Precio_de_venta float, Porcentaje_crédito float);
CREATE TABLE Persona(RFC varchar(13), Calle varchar(40), Número varchar(20), Colonia varchar(20));
CREATE TABLE Persona_Cliente(RFC varchar(13), CURP varchar(18), Nombre varchar(10), Apellido_P varchar(10), 
                             Apellido_M varchar(10), Fecha_de_nacimiento date);
CREATE TABLE Empresa(RFC varchar(13), Nombre varchar(10), Fecha_de_creación date);
CREATE TABLE Valuador(RFC varchar(13), Nombre varchar(10), Apellido_P varchar(10), 
                       Apellido_M varchar(10), Fecha_de_nacimiento date, 
                       Sueldo_fijo float CHECK (Sueldo_fijo > 0), Prestaciones text);
CREATE TABLE Vendedor(RFC varchar(13), Nombre varchar(10), Apellido_P varchar(10), Apellido_M varchar(10), 
                       Fecha_de_nacimiento date);
CREATE TABLE Servicio_Venta(ID numeric, Código numeric, Nombre varchar(10), 
                            Costo float,Fecha_de_adquisición date,Fecha_de_terminación date, 
                            ID_venta numeric );
CREATE TABLE Pago(ID numeric, Fecha date, Monto float, ID_venta numeric);
CREATE TABLE Telefonos_Persona(RFC varchar(13), Teléfono varchar(15));
CREATE TABLE Correos_Persona(RFC varchar(13), Email varchar(20));
CREATE TABLE Ventas(ID numeric, Número_de_motor varchar(17), RFC_cliente varchar(13), 
                     RFC_empleado varchar(13), Fecha date, Comisión float,Tipo_de_Pago varchar(20));
CREATE TABLE Evaluar(ID numeric,Número_de_motor varchar(17), RFC varchar(13), 
                     Comisión float);
CREATE TABLE Compras(ID numeric, Número_de_motor varchar(17), RFC_cliente varchar(13), Fecha date);
CREATE TABLE Abonar(RFC varchar(13), ID_pago numeric);

ALTER TABLE Auto ADD PRIMARY KEY(Número_de_motor); 
ALTER TABLE Auto ALTER COLUMN Condición SET DEFAULT ('B');
ALTER TABLE Auto ADD CHECK (Precio_de_compra >0);
ALTER TABLE Auto ADD CHECK (Precio_de_venta > 0);
ALTER TABLE Auto ADD CHECK (Porcentaje_crédito > 0);

ALTER TABLE Persona ADD PRIMARY KEY(RFC);
ALTER TABLE Persona ALTER COLUMN Calle SET NOT NULL;
ALTER TABLE Persona ALTER COLUMN Número SET NOT NULL;
ALTER TABLE Persona ALTER COLUMN Colonia SET NOT NULL;

ALTER TABLE Persona_Cliente ADD PRIMARY KEY (RFC);
ALTER TABLE Persona_Cliente ALTER COLUMN CURP SET NOT NULL;

ALTER TABLE Empresa ADD PRIMARY KEY (RFC);
ALTER TABLE Empresa ALTER COLUMN Nombre SET NOT NULL;

ALTER TABLE Valuador ADD PRIMARY KEY (RFC);
ALTER TABLE Valuador ADD CHECK (Sueldo_fijo > 0);

ALTER TABLE Vendedor ADD PRIMARY KEY (RFC);

ALTER TABLE Ventas ADD PRIMARY KEY (ID);
ALTER TABLE Ventas ADD FOREIGN KEY (Número_de_motor) REFERENCES Auto(Número_de_motor);
ALTER TABLE Ventas ADD FOREIGN KEY (RFC_cliente) REFERENCES Persona_Cliente(RFC);
ALTER TABLE Ventas ADD FOREIGN KEY (RFC_empleado) REFERENCES Vendedor(RFC);
ALTER TABLE Ventas ADD CHECK (Comisión > 0 );
ALTER TABLE Ventas ALTER COLUMN Tipo_de_pago SET NOT NULL;

ALTER TABLE Servicio_Venta ADD PRIMARY KEY (ID);
ALTER TABLE Servicio_Venta ALTER COLUMN Código SET NOT NULL;
ALTER TABLE Servicio_Venta ADD CHECK (Costo >0);
ALTER TABLE Servicio_Venta ADD FOREIGN KEY (ID_venta) REFERENCES Ventas(ID);

ALTER TABLE Pago ADD PRIMARY KEY (ID);
ALTER TABLE Pago ADD CHECK (Monto>0);
ALTER TABLE Pago ADD FOREIGN KEY (ID_venta) REFERENCES Ventas(ID);

ALTER TABLE Telefonos_Persona ADD FOREIGN KEY (RFC) REFERENCES Persona(RFC);

ALTER TABLE Correos_Persona ADD FOREIGN KEY (RFC) REFERENCES Persona(RFC);

ALTER TABLE Evaluar ADD PRIMARY KEY (ID);
ALTER TABLE Evaluar ADD FOREIGN KEY (Número_de_motor) REFERENCES Auto(Número_de_motor);
ALTER TABLE Evaluar ADD FOREIGN KEY (RFC) REFERENCES Valuador(RFC);
ALTER TABLE Evaluar ADD CHECK (Comisión > 0 );

ALTER TABLE Compras ADD PRIMARY KEY (ID);
ALTER TABLE Compras ADD FOREIGN KEY (Número_de_motor) REFERENCES Auto(Número_de_motor);
ALTER TABLE Compras ADD FOREIGN KEY (RFC_cliente) REFERENCES Persona_Cliente(RFC);

ALTER TABLE Abonar ADD PRIMARY KEY (RFC);
ALTER TABLE Abonar ADD FOREIGN KEY (ID_pago) REFERENCES Pago(ID);