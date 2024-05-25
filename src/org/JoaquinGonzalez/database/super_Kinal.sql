drop database if exists super_Kinal;
create database if not exists super_Kinal;
use super_Kinal;
 
create table Cargos (
cargoId int not null auto_increment,
nombreCargo varchar(30) not null,
descripcionCargo varchar (100) not null,
primary key PK_cargoId(cargoId)
);
create table Empleados(
empleadoId int not null auto_increment,
nombreEmpleado varchar (30)not null, 
apellidoEmpleado varchar (30) not null,
sueldo decimal (10,5)not null,
horaEntrada time not null,
horaSalida time not null,
cargoId int not null,
primary key PK_empleadoId(empleadoId),
constraint FK_Empleados_Cargos foreign key Empleados(cargoId)
        references Cargos (cargoId)
);
 
create table Clientes(
clienteId int not null auto_increment,
nombre varchar (30)not null, 
apellido varchar (30) not null,
telefono varchar (15)not null,
direccion varchar (150) not null,
nit varchar (15)default 'cf',
primary key PK_clienteId(clienteId)
);
 
 
create table Facturas(
facturaId int not null auto_increment,
fecha date not null, 
hora time not null,
empleadoId int not null,
total decimal(10,2)not null,
clienteId int not null,
primary key PK_facturaId(facturaId),
constraint FK_Facturas_Clientes foreign key Facturas(clienteId)
        references Clientes (clienteId)
 
);
 
 
create table TicketSoporte(
ticketSoporteId int not null auto_increment,
descripcionTicket varchar (250) not null, 
estatus varchar(30) not null,
cliente varchar(30),
	clienteId int not null,
	facturaId int,
	primary key PK_ticketSoporteId(ticketSoporteId),
	constraint FK_TicketSoporte_Clientes foreign key (clienteId)
        references Clientes (clienteId),
	constraint FK_TicketSoporte_Facturas foreign key (facturaId)
        references Facturas (facturaId)
);
 
create table Distribuidores(
	distribuidorId int not null auto_increment,
	nombreDistribuidor varchar (30) not null, 
	direccionDistribuidor varchar (200) not null,
	nitDistribuidor varchar (15) not null,
	telefonoDistribuidor varchar (15)not null,
	web varchar(50)not null,
	primary key PK_distribuidorId(distribuidorId)
 
 );
 create table CategoriaProductos (
categoriaProductoId int not null auto_increment,
nombreCategoria varchar(30) not null,
descripcionCargo varchar (100) not null,
primary key PK_categoriaProductoId(categoriaProductoId)

);

create table Producto (
    productoId int not null auto_increment,
    nombreProducto varchar(200) not null, 
    descripcionproducto varchar(300) not null,
    cantidadStock int not null,
    precioVentaUnitario decimal(10,2) not null,
    precioVentaMayor decimal(10,2) not null,
    precioCompra decimal(10,2) not null,
    imagenProducto LONGBLOB,
    distribuidorId int not null,
    categoriaProductoId int not null,
    primary key (productoId)-- ,
--     constraint fk_Producto_CategoriaProductos foreign key (categoriaProductoId)
--         references CategoriaProductos (categoriaProductoId)
);        
 


create table DetalleFactura (
detalleFacturaId int not null auto_increment,
facturaId int not null,
productoId int not null,
categoriaProductoId int not null,
  primary key PK_detalleFacturaId(detalleFacturaId),
    constraint FK_DetalleFactura_Facturas foreign key DetalleFactura(facturaId)
		references Facturas(facturaId),
	constraint FK_DetalleFactura_Producto foreign key DetalleFactura(productoId)
		references Producto(productoId)
);

create table Compras (
compraId int not null auto_increment,
fechaCompra date not null,
totalCompra decimal (10,2) not null,
primary key PK_compraId(compraId)

);

create table DetalleCompra(
detalleCompraId int not null auto_increment,
cantidadCompra int(11)not null,
productoId int not null,
compraId int not null,
  primary key PK_detalleCompraId(detalleCompraId),
    constraint FK_DetalleCompra_Producto foreign key DetalleCompra(productoId)
		references Producto(productoId),
    constraint FK_DetalleCompra_Compra foreign key DetalleCompra(compraId)
		references Compras(compraId)
);



insert into Distribuidores(nombreDistribuidor,direccionDistribuidor,nitDistribuidor,telefonoDistribuidor,web)values
    ('Distribuido', 'Calle Principal', 'NIT123', '+234567890', 'www.distribuidora.com'),
    ('Distribuido', 'Calle Secundaria', 'NIT456', '+987654321', 'www.distribuidorb.com'),
    ('Distribuido', 'Avenida Central', 'NIT789', '+876543210', 'www.distribuidorc.com');

insert into CategoriaProductos(nombreCategoria,descripcionCargo)values
('Alimentos', 'Responsable de alimentos y bebidas'),
('Juguetes', 'Encargado de la gestión de juguetes');

insert into Compras(fechaCompra,totalCompra)values 
('2024-05-14', 100.50),
('2024-05-15', 75.25);


insert into Cargos(nombreCargo,descripcionCargo)values
('Gerente', 'Encargado de la gestión general del equipo'),
('Supervisor', 'Responsable de supervisar el trabajo diario');
 
insert into Clientes(nombre, apellido, telefono, direccion)values
('Mariano', 'Gomez', '456427', 'Guatemala');
 
insert into Facturas(fecha, hora, empleadoId, total, clienteId, facturaId) values 
('4/5/24', '4:00', 1, '500', 1, 1);
 
INSERT INTO Producto (nombreProducto, descripcionProducto, cantidadStock, precioVentaUnitario, precioVentaMayor, precioCompra, imagenProducto,distribuidorId, categoriaProductoId)
VALUES ('zanahoria', 'pos no se', 12, '50.0', '60.3', '2.50', null, 1 , 1);
    
insert into TicketSoporte(descripcionTicket, estatus, clienteId, facturaId,cliente) values 
('mal funcionamiento del sistema de nit', 'en proceso', 1, 1,'Mariana');
 
select * from Clientes;
 
select * from Facturas; 

select * from TicketSoporte;
 
select * from Producto;

select * from Cargos;

select * from Compras;
 
select * from CategoriaProductos; 

select * from Distribuidores;
 
DELIMITER $$
CREATE PROCEDURE sp_AgregarCliente(nom varchar(30), ape varchar(30), tel varchar(30), dir varchar(150), nt varchar(15))
	BEGIN
		INSERT INTO Clientes(nombre, apellido, telefono, direccion, nit)VALUES
			 (nom, ape,tel,dir,nit);
END $$
DELIMITER ;
 
CALL sp_AgregarCliente('Fernando', 'Lopez','584351','Guatemala','1564654-5');  
DELIMITER $$
CREATE PROCEDURE sp_ListarClientes()
	BEGIN
		SELECT* from Clientes;
END $$
DELIMITER ;
 
CALL sp_ListarClientes;
 
DELIMITER $$
CREATE PROCEDURE sp_EliminarCliente(IN cliId INT)
	BEGIN
		DELETE FROM Clientes
			WHERE clienteId = cliId;
    END $$
DELIMITER;    
CALL sp_EliminarClientes();
 
 

DELIMITER $$
CREATE PROCEDURE sp_BuscarCliente(IN cliId INT)
	BEGIN
		SELECT*FROM Clientes
					WHERE clienteId = cliId;
	END $$
DELIMITER;
DELIMITER $$
CREATE PROCEDURE sp_EditarCliente(nom varchar (30), ape varchar (30), tel varchar(30), dir varchar(150), nt varchar (15))
	BEGIN 
		UPDATE Clientes
			SET 
				nombre = nom,
                apellido = ape,
                telefono = tel,
                direccion = dir,
                nit = nt
					WHERE clienteId = cliId;
	END $$
DELIMITER ;

  -- cargos--2
  DELIMITER $$ 
CREATE PROCEDURE sp_AgregarCargos(IN nom VARCHAR (30), IN des VARCHAR (100))
BEGIN 	
	INSERT INTO Cargos (nombreCargo , descripcionCargo)VALUES 
		(nom, des);
END $$
DELIMITER ;
 
DELIMITER $$ 
CREATE PROCEDURE sp_ListarCargos()
BEGIN 
	SELECT * FROM Cargos;
END $$
DELIMITER ;
 
 
DELIMITER $$ 
CREATE PROCEDURE sp_EliminarCargos(IN carId INT)
BEGIN
	DELETE  FROM Cargos 
		WHERE cargoId =  carId;
END$$
DELIMITER ;
 
DELIMITER $$ 
CREATE PROCEDURE sp_BuscarCargos(IN carId INT)
BEGIN
	SELECT *FROM Cargos
			WHERE cargoId = carId;
END$$
DELIMITER ;
 
DELIMITER $$ 
CREATE PROCEDURE sp_EditarCargos(IN carId INT,IN nom VARCHAR (30), IN des VARCHAR (100))
BEGIN
	UPDATE Cargos
		SET
			nombreCargo  = nom,
			descripcionCargo  = des
			WHERE cargoId  = carId;
END$$
DELIMITER ;
empleados-- Empleados --3
 
DELIMITER $$
CREATE PROCEDURE sp_AgregarEmpleados(in empId int,nom varchar(30), ape varchar(30), sue decimal(10,2), hor time ,hora time)
	BEGIN
		INSERT INTO Empleados(empleadoId,nombreEmpleado, apellidoEmpleado, sueldo, horaEntrada,horaSalida)VALUES
			 (empId,nom, ape,sue,hor,hora);
END $$
DELIMITER ;
 
DELIMITER $$
CREATE PROCEDURE sp_EliminarEmpleados(IN empId INT)
	BEGIN
		DELETE FROM Empleados
			WHERE empleadoId = empId;
    END $$
DELIMITER;    
CALL sp_EliminarEmpleados();
 

DELIMITER $$
CREATE PROCEDURE sp_BuscarEmpleados(IN empId INT)
	BEGIN
		SELECT*FROM Empleados
					WHERE empleadoId = empId;
	END $$
DELIMITER;
DELIMITER $$
CREATE PROCEDURE sp_EditarEmpleado(in empId int,nom varchar(30), ape varchar(30), sue decimal(10,2), hor time ,hora time)
	BEGIN 
		UPDATE Empleados
			SET 
				nombreEmpleado = nom,
                apellidoEmpleado = ape,
                sueldo = sue,
                horaEntrada = hor,
                horaSalida = hora,
                empleadoId = empId
					WHERE empleadoId = empId;
	END $$
DELIMITER ;
 
-- Facturas --4
DELIMITER $$
CREATE PROCEDURE sp_AgregarFacturas(in cliId int,facId int,fec date, hor  time, tot decimal(10,2), empId int )
	BEGIN
		INSERT INTO Facturas (clienteId,facturaId, fecha, hora, total, empleadoId)VALUES
			 (facId, fec, hor,empId,tot,cliId); 
END $$
DELIMITER ;
 
 
DELIMITER $$ 
CREATE PROCEDURE sp_ListarFacturas()
BEGIN 
	SELECT * FROM Facturas;
END $$
DELIMITER ;
 
call sp_listarFacturas();
 
DELIMITER $$
CREATE PROCEDURE sp_EliminarFacturas(IN facId INT)
	BEGIN
		DELETE FROM Facturas
			WHERE facturaId = facId;
    END $$
DELIMITER;    
CALL sp_EliminarFacturas();
 

DELIMITER $$
CREATE PROCEDURE sp_BuscarFacturas(IN facId INT)
	BEGIN
		SELECT*FROM Facturas
					WHERE facturaId = facId;
	END $$
DELIMITER;
DELIMITER $$
CREATE PROCEDURE sp_EditarFactura(in cliId int,facId int,fec date, hor  time, tot decimal(10,2), empId int)
	BEGIN 
		UPDATE Facturas
			SET 
				clienteId = cliId,
                facturaId = facId,
                fecha = fec,
                hora = hor,
                tot = total,
                empleadoId = empId
					WHERE facturaId = facId;
	END $$
DELIMITER ;
 
-- ticketsoporte --5
 
 
DELIMITER $$
create procedure sp_agregarTicketSoporte(in des varchar(250), in cliId int, in facId int)
begin
	insert into TicketSoporte(descripcionTicket,estatus,clienteId,facturaId) values
		(des,'Recien Creado',cliId,facId);
end $$
DELIMITER ;
 
 

DELIMITER $$
create procedure sp_ListarTicketSoporte()
begin
	select TS.ticketSoporteId, TS.descripcionTicket, TS.estatus, 
			concat('Id: ', C.clienteId,'| ',C.nombre, ' ',C.apellido)  AS 'Cliente',
            TS.facturaId from TicketSoporte TS
    join Clientes C on TS.clienteId = C.clienteId;
end $$
DELIMITER ;
 
 

DELIMITER $$
create procedure sp_EliminarTicketSoporte(in ticId int)
begin
	delete from TicketSoporte
			where ticketSoporteId = ticId;
end$$
DELIMITER ;
DELIMITER $$
create procedure sp_BuscarTicketSoporte(in ticId int)
begin 
	select
		TicketSoporte.ticketSoporteId,
        TicketSoporte.descripcionTicket,
        TicketSoporte.estatus,
        TicketSoporte.clienteId,
        TicketSoporte.facturaId
			from TicketSoporte
			where ticketSoporteId = ticId;
end $$
DELIMITER ;
DELIMITER $$
create procedure sp_EditarTicketSoporte(in ticId int,in des varchar(250), in est varchar(30), in cliId int, in facId int )
begin
	update TicketSoporte
		set 
			descripcionTicket = des,
            estatus = est,
            clienteId = cliId,
            facturaId = facId
				where ticketSoporteId = ticId;
end $$
DELIMITER ;
-- Productos --6
 
 
DELIMITER $$
CREATE PROCEDURE sp_AgregarProducto(proId int, nom varchar (50),des varchar (100),can int, pre int ,precV int ,preC decimal (10,2),disId int ,cat int ,imaP longblob,cliId int)
	BEGIN
		INSERT INTO Producto (productoId, nombreProducto , descripcionProducto  , cantidadStock  ,precionVentaUnitario , precioVentaMayor,precioCompra ,distribuidorId , categoriaProductos , imaP , clienteId )VALUES
            (proId, nom, des, can, pre, precV, preC, disId ,cat,imaP); 
END $$
DELIMITER ;
 
DELIMITER $$
create procedure sp_ListarProducto()
begin
	select * from Producto;
end $$
DELIMITER ;
 

call  sp_ListarProducto();
DELIMITER $$
CREATE PROCEDURE sp_BuscarProducto(IN proId INT)
	BEGIN
		SELECT*FROM Producto
					WHERE productoId = proId;
	END $$
DELIMITER;
DELIMITER $$
CREATE PROCEDURE sp_EditarProducto(proId int, nom varchar (50),des varchar (100),can int, pre int ,precV int ,preC decimal (10,2),disId int ,cat int ,imaP longblob,cliId int)
	BEGIN 
		UPDATE Producto
			SET 
				nombreProducto = nom,
                descripcionProducto = des,
                cantidadStock = can,
                precionVentaUnitario = pre,
                precioVentaMayor = precV,
                precionCompra = preC,
                distribuidorId = disId,
                categoriaProductos = cat,
				imageProducto = imaP,
                clienteId = cliId
					WHERE ticketSoporteId = ticId;
	END $$
DELIMITER ;
 
DELIMITER $$
CREATE PROCEDURE sp_EliminarProducto(IN ticId INT)
	BEGIN
		DELETE FROM Producto
			WHERE productoId = proId;
    END $$
DELIMITER;    
CALL sp_EliminarProducto();  
DELIMITER $$
create procedure sp_restarStock(in proId int)
	begin 
		update Producto set
        cantidadStock = cantidadStock - 1
        where productoId = proId;
    end $$
DELIMITER ;
 
DELIMITER $$
create procedure sp_agregarStock(in proId int,in cant int)
	begin 
		update Producto set
        cantidadStock = cantidadStock + cant
        where productoId = proId;
    end $$
DELIMITER ;  
-- Detalle Factura --7
DELIMITER $$
CREATE PROCEDURE sp_AgregarDistribuidores(IN nom VARCHAR (30),IN dir VARCHAR (200), IN nit varchar(15), IN tel varchar(15),  IN web varchar(50) )
	BEGIN
		INSERT INTO Distribuidores (dnombreDistribuidor , direccionDistribuidor ,nitDistribuidor ,telefonoDistribuidor ,web )VALUES
			 (nom, dir,nit,tel,web); 
END $$
DELIMITER ;

 DELIMITER $$
create procedure sp_ListarDistribuidores()
begin
	select * from Distribuidores;
end $$
DELIMITER ;
 
 CALL sp_ListarDistribuidores;
 
DELIMITER $$
CREATE PROCEDURE sp_EliminarDistribuidor(IN ticId INT)
	BEGIN
		DELETE FROM Distribuidores
			WHERE distribuidorId = disId;
    END $$
DELIMITER;    
CALL sp_EliminarDistribuidor();
 

DELIMITER $$
CREATE PROCEDURE sp_BuscarDistribuidor(IN disId INT)
	BEGIN
		SELECT*FROM Distribuidores
					WHERE distribuidorId = disId;
	END $$
DELIMITER;
DELIMITER $$
CREATE PROCEDURE sp_EditarDistribuidor(IN nom VARCHAR (30),IN dir VARCHAR (200), IN nit varchar(15), IN tel varchar(15),  IN web varchar(50) )
	BEGIN 
		UPDATE Distribuidores
			SET nombreDistribuidor = nom,
			direccionDistribuidor = dir,
			nitDistribuidor = nit,
			telefonoDistribuidor = tel ,
            web = web
			WHERE clienteId = cliId;
	END $$
DELIMITER ;
 
 
-- CategoriaProductos --8
 
 
DELIMITER $$
CREATE PROCEDURE sp_AgregarCategoriaProducto(catId int, nomC VARCHAR (30), des VARCHAR (100), disId int )
	BEGIN
		INSERT INTO CategoriaProductos (categoriaProductoId,nombreCategoria , descripcionCargo ,distribuidorId  )VALUES
			 (catId,nomC, des,disId ); 
END $$
DELIMITER ;
 
 DELIMITER $$
create procedure sp_ListarCategoriaProductos()
begin
	select * from CategoriaProductos;
end $$
DELIMITER ;
CALL sp_ListarCategoriaProductos;

DELIMITER $$
CREATE PROCEDURE sp_EliminarCategoriaProductos(IN ticId INT)
	BEGIN
		DELETE FROM CategoriaProductos
			WHERE categoriaProductoId = catId;
    END $$
DELIMITER;    
CALL sp_EliminarCategoriaProductos();
 

DELIMITER $$
CREATE PROCEDURE sp_BuscarCategoriaProductos(IN catId INT)
	BEGIN
		SELECT*FROM CategoriaProductos
					WHERE categoriaProductoId = catId;
	END $$
DELIMITER;
DELIMITER $$
CREATE PROCEDURE sp_EditarCategoriaProductos(catId int, nomC VARCHAR (30), des VARCHAR (100), disId int)
	BEGIN 
		UPDATE Distribuidores
			SET 
            nombreCategoria  = nom,
			descripcionCategoria = ape
			WHERE categoriaProductoId = catId;
	END $$
DELIMITER ;
 
 
-- DetalleFactura --9
 
DELIMITER $$
CREATE PROCEDURE sp_AgregarDetalleFactura(in factId int, in proId int)
	BEGIN
		INSERT INTO DetalleFactura (facturaId, productoId  )VALUES
			 (factId, proId ); 
END $$
DELIMITER ;
 
DELIMITER $$
CREATE PROCEDURE sp_EliminarDetalleFactura(IN detId INT)
	BEGIN
		DELETE FROM DetalleFactura
			WHERE detalleFacturaId = detId;
    END $$
DELIMITER;    
CALL sp_EliminarDetalleFactura();
 

DELIMITER $$
CREATE PROCEDURE sp_BuscarDetalleFactura(IN detId INT)
	BEGIN
		SELECT*FROM DetalleFactura
					WHERE detalleFacturaId = detId;
	END $$
DELIMITER;
DELIMITER $$
CREATE PROCEDURE sp_EditarDetalleFactura(in factId int, in proId int)
	BEGIN 
		UPDATE DetalleFactura
			SET 
           facturaId = factId,
		   productoId = proId
			where detalleFacturaId  = detaFacId;
	END $$
DELIMITER ;
 
 
-- Compras --10
 
 
DELIMITER $$ 
Create procedure sp_AgregarCompras(in fec date, IN tot  decimal (10.2))
begin 	
	insert into Compras (fechaCompra,totalCompra )values 
		(fec, tot);
end $$
delimiter ;
 
delimiter $$ 
Create procedure sp_ListarCompras()
begin 
	SELECT * FROM Compras;
 
 
end $$
delimiter ;
 
DELIMITER $$ 
Create procedure sp_EliminarCompras(IN compId  INT)
begin
	delete from Compras 
		WHERE compraId  =  compId;
end $$
delimiter ;
 
delimiter $$ 
Create procedure sp_BuscarCompras(IN compId INT)
begin
	SELECT * FROM Compras
			WHERE compraId = compId;
end $$
delimiter ;
 
delimiter $$ 
Create procedure sp_EditarCompras( compId INT, fec date, IN tot  decimal (10.2))
BEGIN
	UPDATE Compras
		SET
			fechaCompra   = fec,
			totalCompra   = tot
			WHERE compraId   = compId;
end $$
delimiter ;

--  DetalleCompra   -- 11
DELIMITER $$
create procedure sp_AgregarDetalleCompra(in canC int, in proId int, in comId int)
	BEGIN
		insert into DetalleCompra(cantidadCompra, productoId, compraId) values
			(canC, proId, comId);
    END $$
DELIMITER ;
 
DELIMITER $$
create procedure sp_ListarDetalleCompra()
	BEGIN
		select * from DetalleCompra DC;
    END $$
DELIMITER ;
 
DELIMITER $$
create procedure sp_BuscarDetalleCompra(in detCId int)
	BEGIN
		select * from DetalleCompra
				where detalleCompraId = detCId;
    END $$
DELIMITER ;
 
DELIMITER $$
create procedure sp_EliminarDetalleCompra(in detCId int)
	BEGIN
		delete from DetalleCompra
				where detalleCompraId = detCId;
    END $$
DELIMITER ;
 
DELIMITER $$
create procedure sp_EditarDetalleCompra(in detCId int, in canC int, in proId int, in comId int)
	BEGIN
		update DetalleCompra
			set
				cantidadCompra = canC,
                productoId = proId,
                compraId = comId
					where detalleCompraId = detCId;
    END $$
DELIMITER ;
 
 
 
delimiter $$
create function fn_calcularTotal (factId int) returns decimal(10,2) deterministic
begin
	declare total decimal(10,2) default 0.0;
    declare precio decimal(10,2);
    declare i int default 1;
    declare curFacturaId, curProductoId int;
    declare curPromPrecio decimal(10,2);
 
    declare cursorDetalleFactura cursor for 
    select DF.facturaId , DF.productoId from DetalleFactura DF;
    open cursorDetalleFactura;
    totalLoop :loop
    fetch cursorDetalleFactura into curFacturaId, curProductoId;
    select PR.precioPromocion into curPromPrecio
        from Promociones PR
        where PR.productoId = curProductoId
        and NOW() between PR.fechaInicio and PR.fechaFinalizacion
        order by PR.fechaInicio desc
        Limit 1;
	if factId = curFacturaId then
			if curPromPrecio is not null then
				set precio = curPromPrecio;
            else 
				set precio = (select P.precioVentaUnitario from Productos P where P.productoId = curProductoId);
			end if;
        set total = total + precio;
    end if;
    if i = (select count(*) from detalleFactura) then
		leave totalLoop;
	end if;
    set i = i + 1;
    end loop totalLoop;
    call sp_asignarTotalFactura(total, factId);
    return total;
end $$
delimiter ;

 
delimiter $$
create function fn_calcularTotalCompra (comId int) returns decimal(10,2) deterministic
begin
	declare total decimal(10,2) default 0.0;
    declare precio decimal(10,2);
    declare i int default 1;
    declare curCompraId, curProductoId int;
    declare curCantidad decimal(10,2);
 
    declare cursorDetalleCompra cursor for 
    select DC.cantidadCompra, DC.productoId,DC.compraId from DetalleCompra DC;
    open cursorDetalleCompra;
    totalLoop :loop
    fetch cursorDetalleCompra into curCantidad, curProductoId,curCompraId;

	if comId = curCompraId then
		set precio = curCantidad * (select P.precioCompra from Productos P where P.productoId = curProductoId);
        set total = total + precio;
    end if;
    if i = (select count(*) from detalleCompra) then
		leave totalLoop;
	end if;
    set i = i + 1;
    end loop totalLoop;
    call sp_asignarTotalCompra(comId,total);
    return total;
end $$
delimiter ;

delimiter $$
create trigger tg_agregarTotalFactura
after insert on DetalleFactura
for each row
Begin
	declare total decimal(10,2);
    set total = (select fn_calcularTotal(new.facturaId));
end$$
delimiter ;
delimiter $$
create trigger tg_restarStock
after insert on detallefactura
for each row
begin
	call sp_restarStock(New.productoId);
end$$
delimiter ;
delimiter $$
create trigger tg_agregarTotalCompra
after insert on DetalleCompra
for each row 
begin
	declare total decimal(10,2);
    set total = (select fn_calcularTotalCompra(new.compraId));
end$$
delimiter ;

delimiter $$
create trigger tg_agregarStock
after insert on detalleCompra
for each row
begin 
	call sp_agregarStock(New.productoId,new.cantidadCompra);
end$$
delimiter ;select * from Productos LIMIT 0, 5000