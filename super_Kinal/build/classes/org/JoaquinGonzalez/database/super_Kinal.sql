drop database if exists super_Kinal;
 
 
create database if not exists super_Kinal;
 
use super_Kinal;
 
create table Clientes(
clienteId int not null auto_increment,
nombre varchar (40)not null, 
apellido varchar (40) not null,
telefono varchar (15)not null,
direccion varchar (150) not null,
nit varchar (15)default 'cf',
primary key PK_clienteId(clienteId)
 
);



insert into Clientes(nombre, apellido, telefono, direccion)values
	('Mariano', 'Gomez', '456427', 'Guatemala');

select * from Clientes;

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
  

