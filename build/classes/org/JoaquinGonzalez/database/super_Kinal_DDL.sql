drop database if exists super_Kinal;
create database if not exists super_Kinal;
use super_Kinal;
 
create table Cargos (
cargoId int not null auto_increment,
nombreCargo varchar(30) not null,
descripcionCargo varchar (100) not null,
primary key PK_cargoId(cargoId)
);

create table Compras (
compraId int not null auto_increment,
fechaCompra date not null,
totalCompra decimal (10,2) not null,
primary key PK_compraId(compraId)

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

create table Producto(
	productoId int not null auto_increment,
    nombreProducto varchar(50),
    descripcionProducto varchar(100) not null,
    cantidadStock int not null,
    precioVentaUnitario decimal(10, 2) not null,
    precioVentaMayor decimal(10, 2) not null,
    precioCompra decimal(10, 2) not null,
    distribuidorId int not null,
    categoriaProductoId int not null,
    primary key PK_productoId (productoId),
    constraint FK_Productos_Distribuidores foreign key Distribuidores (distribuidorId)
	references Distribuidores (distribuidorId),
    constraint FK_Productos_CategoriaProductos foreign key CategoriaProductos (categoriaProductoId)
		references CategoriaProductos (categoriaProductoId)
);

create table Promociones(
	promocionId int not null auto_increment,
    precioPromocion decimal (10,2),
    descripcionPromocion varchar (200),
    fechaInicio date,
    fechaFinalizacion date,
    productoId int not null,
    primary key PK_promocionId (promocionId),
    constraint FK_Promociones_Productos foreign key  (productoId)
		references Producto(productoId)
);

create table DetalleFactura(
	detalleFacturaId int not null auto_increment,
    facturaId int not null,
    productoId int not null,
    primary key PK_detalleFacturaId (detalleFacturaId),
    constraint FK_DetalleFactura_Facturas foreign key Facturas (facturaId)
		references Facturas(facturaId),
    constraint FK_DetalleFactura_Producto foreign key Producto(productoId)
		references Producto(productoId)
);

create table DetalleCompra(
	detalleCompraId int not null auto_increment,
    cantidadCompra int not null,
    productoId int not null,
    compraId int not null,
    Primary key PK_detalleCompraId (detalleCompraId),
    constraint FK_DetalleCompra_Producto foreign key (productoId)
		references Producto (productoId),
	constraint FK_DetalleCompra_Compras foreign key (compraId)
		references Compras (compraId)
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
 
insert into Producto(nombreProducto, descripcionProducto, cantidadStock, precioVentaUnitario, precioVentaMayor, precioCompra, distribuidorId, categoriaProductoId) values
    ('Mouse', 'Mouse para computadora.', 15, 50.00, 100.00, 50.00, 1, 1);
    
insert into TicketSoporte(descripcionTicket, estatus, clienteId, facturaId,cliente) values 
('mal funcionamiento del sistema de nit', 'en proceso', 1, 1,'Mariana');

insert into Promociones(precioPromocion, descripcionPromocion, fechaInicio, fechaFinalizacion, productoId) values
	(15.00, 'Promocion de primavera.', '2024-01-01', '2024-01-02', 1);
    
insert into DetalleFactura(facturaId, productoId) values
	(1, 1);
    
insert into DetalleCompra(cantidadCompra, productoId, compraId) values
	(15, 1, 1);
 
select * from Clientes;
 
select * from Facturas; 

select * from TicketSoporte;
 
select * from Producto;

select * from Cargos;

select * from Compras;
 
select * from CategoriaProductos; 

select * from Distribuidores;

select * from  Promociones;

select * from DetalleFactura;

select * from DetalleCompra;