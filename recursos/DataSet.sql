use BancoTp

INSERT INTO UsuarioTipo (Descripcion) VALUES ('Admin'), ('Cliente');
INSERT INTO CuentaTipo (Descripcion) VALUES ('Caja de Ahorro'), ('Cuenta Corriente')
INSERT INTO MovimientoTipo (Descripcion) VALUES ('Transferencia'), ('Pago')



INSERT INTO Usuario (Nombre, Clave, TipoId, Activo) VALUES 
('admin',    'admin',    1, 1),
('admin2',   'admin2',   1, 1),
('admin3',   'admin3',   1, 1),
('admin4',   'admin4',   1, 0),
('admin5',   'admin5',   1, 0),
('admin6',   'admin6',   1, 0),
('cliente',  'cliente',  2, 1),
('cliente2', 'cliente2', 2, 1),
('cliente3', 'cliente3', 2, 1),
('cliente4', 'cliente4', 2, 0),
('cliente5', 'cliente5', 2, 0),
('cliente6', 'cliente6', 2, 0)


INSERT INTO Provincia (Nombre) VALUES
('Buenos Aires'),('Catamarca'),('Chaco'),('Chubut'),('Ciudad Autónoma de Buenos Aires'),('Córdoba'),('Corrientes'),('Entre Ríos'),
('Formosa'),('Jujuy'),('La Pampa'),('La Rioja'),('Mendoza'),('Misiones'),('Neuquén'),('Río Negro'),('Salta'),('San Juan'),('San Luis'),
('Santa Cruz'),('Santa Fe'),('Santiago del Estero'),('Tierra del Fuego'),('Tucumán')

-- Buenos Aires
INSERT INTO Localidad (ProvinciaId, Nombre) VALUES
((SELECT Id FROM Provincia WHERE Nombre = 'Buenos Aires'), 'La Plata'),
((SELECT Id FROM Provincia WHERE Nombre = 'Buenos Aires'), 'Mar del Plata'),
((SELECT Id FROM Provincia WHERE Nombre = 'Buenos Aires'), 'Bahía Blanca'),
((SELECT Id FROM Provincia WHERE Nombre = 'Buenos Aires'), 'Tandil'),
((SELECT Id FROM Provincia WHERE Nombre = 'Buenos Aires'), 'San Nicolás de los Arroyos'),
((SELECT Id FROM Provincia WHERE Nombre = 'Buenos Aires'), 'Junín'),
((SELECT Id FROM Provincia WHERE Nombre = 'Buenos Aires'), 'Pergamino'),
((SELECT Id FROM Provincia WHERE Nombre = 'Buenos Aires'), 'Olavarría'),
((SELECT Id FROM Provincia WHERE Nombre = 'Buenos Aires'), 'Zárate'),
((SELECT Id FROM Provincia WHERE Nombre = 'Buenos Aires'), 'Luján')

-- Catamarca
INSERT INTO Localidad (ProvinciaId, Nombre) VALUES
((SELECT Id FROM Provincia WHERE Nombre = 'Catamarca'), 'San Fernando del Valle de Catamarca'),
((SELECT Id FROM Provincia WHERE Nombre = 'Catamarca'), 'Andalgalá'),
((SELECT Id FROM Provincia WHERE Nombre = 'Catamarca'), 'Belén'),
((SELECT Id FROM Provincia WHERE Nombre = 'Catamarca'), 'Tinogasta'),
((SELECT Id FROM Provincia WHERE Nombre = 'Catamarca'), 'Santa María'),
((SELECT Id FROM Provincia WHERE Nombre = 'Catamarca'), 'Fiambalá'),
((SELECT Id FROM Provincia WHERE Nombre = 'Catamarca'), 'Recreo'),
((SELECT Id FROM Provincia WHERE Nombre = 'Catamarca'), 'San José'),
((SELECT Id FROM Provincia WHERE Nombre = 'Catamarca'), 'Pomán'),
((SELECT Id FROM Provincia WHERE Nombre = 'Catamarca'), 'Saujil')

-- Chaco
INSERT INTO Localidad (ProvinciaId, Nombre) VALUES
((SELECT Id FROM Provincia WHERE Nombre = 'Chaco'), 'Resistencia'),
((SELECT Id FROM Provincia WHERE Nombre = 'Chaco'), 'Presidencia Roque Sáenz Peña'),
((SELECT Id FROM Provincia WHERE Nombre = 'Chaco'), 'Villa Ángela'),
((SELECT Id FROM Provincia WHERE Nombre = 'Chaco'), 'Charata'),
((SELECT Id FROM Provincia WHERE Nombre = 'Chaco'), 'San Bernardo'),
((SELECT Id FROM Provincia WHERE Nombre = 'Chaco'), 'Las Breñas'),
((SELECT Id FROM Provincia WHERE Nombre = 'Chaco'), 'General San Martín'),
((SELECT Id FROM Provincia WHERE Nombre = 'Chaco'), 'Barranqueras'),
((SELECT Id FROM Provincia WHERE Nombre = 'Chaco'), 'Castelli'),
((SELECT Id FROM Provincia WHERE Nombre = 'Chaco'), 'Puerto Tirol')

-- Chubut
INSERT INTO Localidad (ProvinciaId, Nombre) VALUES
((SELECT Id FROM Provincia WHERE Nombre = 'Chubut'), 'Rawson'),
((SELECT Id FROM Provincia WHERE Nombre = 'Chubut'), 'Trelew'),
((SELECT Id FROM Provincia WHERE Nombre = 'Chubut'), 'Puerto Madryn'),
((SELECT Id FROM Provincia WHERE Nombre = 'Chubut'), 'Comodoro Rivadavia'),
((SELECT Id FROM Provincia WHERE Nombre = 'Chubut'), 'Esquel'),
((SELECT Id FROM Provincia WHERE Nombre = 'Chubut'), 'Gaiman'),
((SELECT Id FROM Provincia WHERE Nombre = 'Chubut'), 'Sarmiento'),
((SELECT Id FROM Provincia WHERE Nombre = 'Chubut'), 'Trevelin'),
((SELECT Id FROM Provincia WHERE Nombre = 'Chubut'), 'Dolavon'),
((SELECT Id FROM Provincia WHERE Nombre = 'Chubut'), 'El Maitén')

-- Ciudad Autónoma de Buenos Aires
INSERT INTO Localidad (ProvinciaId, Nombre) VALUES
((SELECT Id FROM Provincia WHERE Nombre = 'Ciudad Autónoma de Buenos Aires'), 'Palermo'),
((SELECT Id FROM Provincia WHERE Nombre = 'Ciudad Autónoma de Buenos Aires'), 'Recoleta'),
((SELECT Id FROM Provincia WHERE Nombre = 'Ciudad Autónoma de Buenos Aires'), 'Belgrano'),
((SELECT Id FROM Provincia WHERE Nombre = 'Ciudad Autónoma de Buenos Aires'), 'Caballito'),
((SELECT Id FROM Provincia WHERE Nombre = 'Ciudad Autónoma de Buenos Aires'), 'San Telmo'),
((SELECT Id FROM Provincia WHERE Nombre = 'Ciudad Autónoma de Buenos Aires'), 'Almagro'),
((SELECT Id FROM Provincia WHERE Nombre = 'Ciudad Autónoma de Buenos Aires'), 'Balvanera'),
((SELECT Id FROM Provincia WHERE Nombre = 'Ciudad Autónoma de Buenos Aires'), 'Villa Urquiza'),
((SELECT Id FROM Provincia WHERE Nombre = 'Ciudad Autónoma de Buenos Aires'), 'Flores'),
((SELECT Id FROM Provincia WHERE Nombre = 'Ciudad Autónoma de Buenos Aires'), 'Villa Lugano')

-- Córdoba
INSERT INTO Localidad (ProvinciaId, Nombre) VALUES
((SELECT Id FROM Provincia WHERE Nombre = 'Córdoba'), 'Córdoba'),
((SELECT Id FROM Provincia WHERE Nombre = 'Córdoba'), 'Villa Carlos Paz'),
((SELECT Id FROM Provincia WHERE Nombre = 'Córdoba'), 'Río Cuarto'),
((SELECT Id FROM Provincia WHERE Nombre = 'Córdoba'), 'Villa María'),
((SELECT Id FROM Provincia WHERE Nombre = 'Córdoba'), 'San Francisco'),
((SELECT Id FROM Provincia WHERE Nombre = 'Córdoba'), 'Alta Gracia'),
((SELECT Id FROM Provincia WHERE Nombre = 'Córdoba'), 'La Falda'),
((SELECT Id FROM Provincia WHERE Nombre = 'Córdoba'), 'Cosquín'),
((SELECT Id FROM Provincia WHERE Nombre = 'Córdoba'), 'Jesús María'),
((SELECT Id FROM Provincia WHERE Nombre = 'Córdoba'), 'Capilla del Monte');

-- Mendoza
INSERT INTO Localidad (ProvinciaId, Nombre) VALUES
((SELECT Id FROM Provincia WHERE Nombre = 'Mendoza'), 'Mendoza'),
((SELECT Id FROM Provincia WHERE Nombre = 'Mendoza'), 'San Rafael'),
((SELECT Id FROM Provincia WHERE Nombre = 'Mendoza'), 'Godoy Cruz'),
((SELECT Id FROM Provincia WHERE Nombre = 'Mendoza'), 'Guaymallén'),
((SELECT Id FROM Provincia WHERE Nombre = 'Mendoza'), 'Luján de Cuyo'),
((SELECT Id FROM Provincia WHERE Nombre = 'Mendoza'), 'Las Heras'),
((SELECT Id FROM Provincia WHERE Nombre = 'Mendoza'), 'Maipú'),
((SELECT Id FROM Provincia WHERE Nombre = 'Mendoza'), 'Tunuyán'),
((SELECT Id FROM Provincia WHERE Nombre = 'Mendoza'), 'Malargüe'),
((SELECT Id FROM Provincia WHERE Nombre = 'Mendoza'), 'San Martín');

-- Santa Fe
INSERT INTO Localidad (ProvinciaId, Nombre) VALUES
((SELECT Id FROM Provincia WHERE Nombre = 'Santa Fe'), 'Santa Fe'),
((SELECT Id FROM Provincia WHERE Nombre = 'Santa Fe'), 'Rosario'),
((SELECT Id FROM Provincia WHERE Nombre = 'Santa Fe'), 'Rafaela'),
((SELECT Id FROM Provincia WHERE Nombre = 'Santa Fe'), 'Venado Tuerto'),
((SELECT Id FROM Provincia WHERE Nombre = 'Santa Fe'), 'Reconquista'),
((SELECT Id FROM Provincia WHERE Nombre = 'Santa Fe'), 'Villa Gobernador Gálvez'),
((SELECT Id FROM Provincia WHERE Nombre = 'Santa Fe'), 'San Lorenzo'),
((SELECT Id FROM Provincia WHERE Nombre = 'Santa Fe'), 'Sunchales'),
((SELECT Id FROM Provincia WHERE Nombre = 'Santa Fe'), 'Cañada de Gómez'),
((SELECT Id FROM Provincia WHERE Nombre = 'Santa Fe'), 'Esperanza');

-- Salta
INSERT INTO Localidad (ProvinciaId, Nombre) VALUES
((SELECT Id FROM Provincia WHERE Nombre = 'Salta'), 'Salta'),
((SELECT Id FROM Provincia WHERE Nombre = 'Salta'), 'Cafayate'),
((SELECT Id FROM Provincia WHERE Nombre = 'Salta'), 'San Ramón de la Nueva Orán'),
((SELECT Id FROM Provincia WHERE Nombre = 'Salta'), 'Tartagal'),
((SELECT Id FROM Provincia WHERE Nombre = 'Salta'), 'General Güemes'),
((SELECT Id FROM Provincia WHERE Nombre = 'Salta'), 'Metán'),
((SELECT Id FROM Provincia WHERE Nombre = 'Salta'), 'Rosario de la Frontera'),
((SELECT Id FROM Provincia WHERE Nombre = 'Salta'), 'Cerrillos'),
((SELECT Id FROM Provincia WHERE Nombre = 'Salta'), 'San Lorenzo'),
((SELECT Id FROM Provincia WHERE Nombre = 'Salta'), 'El Carril');

-- Neuquén
INSERT INTO Localidad (ProvinciaId, Nombre) VALUES
((SELECT Id FROM Provincia WHERE Nombre = 'Neuquén'), 'Neuquén'),
((SELECT Id FROM Provincia WHERE Nombre = 'Neuquén'), 'San Martín de los Andes'),
((SELECT Id FROM Provincia WHERE Nombre = 'Neuquén'), 'Villa La Angostura'),
((SELECT Id FROM Provincia WHERE Nombre = 'Neuquén'), 'Plottier'),
((SELECT Id FROM Provincia WHERE Nombre = 'Neuquén'), 'Centenario'),
((SELECT Id FROM Provincia WHERE Nombre = 'Neuquén'), 'Zapala'),
((SELECT Id FROM Provincia WHERE Nombre = 'Neuquén'), 'Junín de los Andes'),
((SELECT Id FROM Provincia WHERE Nombre = 'Neuquén'), 'Cutral Có'),
((SELECT Id FROM Provincia WHERE Nombre = 'Neuquén'), 'Chos Malal'),
((SELECT Id FROM Provincia WHERE Nombre = 'Neuquén'), 'Rincón de los Sauces');

-- Jujuy
INSERT INTO Localidad (ProvinciaId, Nombre) VALUES
((SELECT Id FROM Provincia WHERE Nombre = 'Jujuy'), 'San Salvador de Jujuy'),
((SELECT Id FROM Provincia WHERE Nombre = 'Jujuy'), 'Palpalá'),
((SELECT Id FROM Provincia WHERE Nombre = 'Jujuy'), 'Humahuaca'),
((SELECT Id FROM Provincia WHERE Nombre = 'Jujuy'), 'Tilcara'),
((SELECT Id FROM Provincia WHERE Nombre = 'Jujuy'), 'La Quiaca'),
((SELECT Id FROM Provincia WHERE Nombre = 'Jujuy'), 'Perico'),
((SELECT Id FROM Provincia WHERE Nombre = 'Jujuy'), 'San Pedro de Jujuy'),
((SELECT Id FROM Provincia WHERE Nombre = 'Jujuy'), 'Monterrico'),
((SELECT Id FROM Provincia WHERE Nombre = 'Jujuy'), 'El Carmen'),
((SELECT Id FROM Provincia WHERE Nombre = 'Jujuy'), 'Abra Pampa');

-- La Pampa
INSERT INTO Localidad (ProvinciaId, Nombre) VALUES
((SELECT Id FROM Provincia WHERE Nombre = 'La Pampa'), 'Santa Rosa'),
((SELECT Id FROM Provincia WHERE Nombre = 'La Pampa'), 'General Pico'),
((SELECT Id FROM Provincia WHERE Nombre = 'La Pampa'), 'Toay'),
((SELECT Id FROM Provincia WHERE Nombre = 'La Pampa'), 'Victorica'),
((SELECT Id FROM Provincia WHERE Nombre = 'La Pampa'), '25 de Mayo'),
((SELECT Id FROM Provincia WHERE Nombre = 'La Pampa'), 'Realicó'),
((SELECT Id FROM Provincia WHERE Nombre = 'La Pampa'), 'General Acha'),
((SELECT Id FROM Provincia WHERE Nombre = 'La Pampa'), 'Intendente Alvear'),
((SELECT Id FROM Provincia WHERE Nombre = 'La Pampa'), 'Guatraché'),
((SELECT Id FROM Provincia WHERE Nombre = 'La Pampa'), 'Eduardo Castex');

-- Misiones
INSERT INTO Localidad (ProvinciaId, Nombre) VALUES
((SELECT Id FROM Provincia WHERE Nombre = 'Misiones'), 'Posadas'),
((SELECT Id FROM Provincia WHERE Nombre = 'Misiones'), 'Oberá'),
((SELECT Id FROM Provincia WHERE Nombre = 'Misiones'), 'Eldorado'),
((SELECT Id FROM Provincia WHERE Nombre = 'Misiones'), 'Puerto Iguazú'),
((SELECT Id FROM Provincia WHERE Nombre = 'Misiones'), 'San Vicente'),
((SELECT Id FROM Provincia WHERE Nombre = 'Misiones'), 'Leandro N. Alem'),
((SELECT Id FROM Provincia WHERE Nombre = 'Misiones'), 'Montecarlo'),
((SELECT Id FROM Provincia WHERE Nombre = 'Misiones'), 'Jardín América'),
((SELECT Id FROM Provincia WHERE Nombre = 'Misiones'), 'Apóstoles'),
((SELECT Id FROM Provincia WHERE Nombre = 'Misiones'), 'Aristóbulo del Valle');

-- Río Negro
INSERT INTO Localidad (ProvinciaId, Nombre) VALUES
((SELECT Id FROM Provincia WHERE Nombre = 'Río Negro'), 'San Carlos de Bariloche'),
((SELECT Id FROM Provincia WHERE Nombre = 'Río Negro'), 'Viedma'),
((SELECT Id FROM Provincia WHERE Nombre = 'Río Negro'), 'Cipolletti'),
((SELECT Id FROM Provincia WHERE Nombre = 'Río Negro'), 'General Roca'),
((SELECT Id FROM Provincia WHERE Nombre = 'Río Negro'), 'Villa Regina'),
((SELECT Id FROM Provincia WHERE Nombre = 'Río Negro'), 'Catriel'),
((SELECT Id FROM Provincia WHERE Nombre = 'Río Negro'), 'Allen'),
((SELECT Id FROM Provincia WHERE Nombre = 'Río Negro'), 'Choele Choel'),
((SELECT Id FROM Provincia WHERE Nombre = 'Río Negro'), 'El Bolsón'),
((SELECT Id FROM Provincia WHERE Nombre = 'Río Negro'), 'Luis Beltrán');

-- San Juan
INSERT INTO Localidad (ProvinciaId, Nombre) VALUES
((SELECT Id FROM Provincia WHERE Nombre = 'San Juan'), 'San Juan'),
((SELECT Id FROM Provincia WHERE Nombre = 'San Juan'), 'Rivadavia'),
((SELECT Id FROM Provincia WHERE Nombre = 'San Juan'), 'Rawson'),
((SELECT Id FROM Provincia WHERE Nombre = 'San Juan'), 'Chimbas'),
((SELECT Id FROM Provincia WHERE Nombre = 'San Juan'), 'Santa Lucía'),
((SELECT Id FROM Provincia WHERE Nombre = 'San Juan'), 'Pocito'),
((SELECT Id FROM Provincia WHERE Nombre = 'San Juan'), 'Albardón'),
((SELECT Id FROM Provincia WHERE Nombre = 'San Juan'), 'Caucete'),
((SELECT Id FROM Provincia WHERE Nombre = 'San Juan'), 'Jáchal'),
((SELECT Id FROM Provincia WHERE Nombre = 'San Juan'), 'San Martín');

-- San Luis
INSERT INTO Localidad (ProvinciaId, Nombre) VALUES
((SELECT Id FROM Provincia WHERE Nombre = 'San Luis'), 'San Luis'),
((SELECT Id FROM Provincia WHERE Nombre = 'San Luis'), 'Villa Mercedes'),
((SELECT Id FROM Provincia WHERE Nombre = 'San Luis'), 'Merlo'),
((SELECT Id FROM Provincia WHERE Nombre = 'San Luis'), 'La Punta'),
((SELECT Id FROM Provincia WHERE Nombre = 'San Luis'), 'Juana Koslay'),
((SELECT Id FROM Provincia WHERE Nombre = 'San Luis'), 'Santa Rosa del Conlara'),
((SELECT Id FROM Provincia WHERE Nombre = 'San Luis'), 'Concarán'),
((SELECT Id FROM Provincia WHERE Nombre = 'San Luis'), 'Quines'),
((SELECT Id FROM Provincia WHERE Nombre = 'San Luis'), 'Tilisarao'),
((SELECT Id FROM Provincia WHERE Nombre = 'San Luis'), 'Villa de Merlo');

-- Formosa
INSERT INTO Localidad (ProvinciaId, Nombre) VALUES
((SELECT Id FROM Provincia WHERE Nombre = 'Formosa'), 'Formosa'),
((SELECT Id FROM Provincia WHERE Nombre = 'Formosa'), 'Clorinda'),
((SELECT Id FROM Provincia WHERE Nombre = 'Formosa'), 'Pirané'),
((SELECT Id FROM Provincia WHERE Nombre = 'Formosa'), 'El Colorado'),
((SELECT Id FROM Provincia WHERE Nombre = 'Formosa'), 'Las Lomitas'),
((SELECT Id FROM Provincia WHERE Nombre = 'Formosa'), 'Ibarreta'),
((SELECT Id FROM Provincia WHERE Nombre = 'Formosa'), 'Ingeniero Juárez'),
((SELECT Id FROM Provincia WHERE Nombre = 'Formosa'), 'Laguna Blanca'),
((SELECT Id FROM Provincia WHERE Nombre = 'Formosa'), 'Comandante Fontana'),
((SELECT Id FROM Provincia WHERE Nombre = 'Formosa'), 'Estanislao del Campo');

INSERT INTO Cliente (UsuarioId, Nombre, Apellido, Sexo, DNI, CUIL, Telefono, Email, FechaNacimiento, Direccion, LocalidadId, ProvinciaId) VALUES 
((SELECT Id FROM Usuario WHERE Nombre = 'Cliente'), 'Juan', 'Perez', 'Masculino', '33222001', '1332220010', '88881111', 'cliente@gmail.com', '19900101', 
'Libertad 100', (SELECT l.Id FROM Localidad l INNER JOIN Provincia p ON p.Id = l.ProvinciaId WHERE p.Nombre = 'Ciudad Autónoma de Buenos Aires' AND l.Nombre = 'Belgrano'), 
	(SELECT Id FROM Provincia WHERE Nombre = 'Ciudad Autónoma de Buenos Aires')),
((SELECT Id FROM Usuario WHERE Nombre = 'Cliente2'), 'María', 'Perez', 'Femenino', '33222002', '1332220020', '88882222', 'cliente2@gmail.com', '19900202', 
'Libertad 200', (SELECT l.Id FROM Localidad l INNER JOIN Provincia p ON p.Id = l.ProvinciaId WHERE p.Nombre = 'Catamarca' AND l.Nombre = 'Fiambalá'), 
	(SELECT Id FROM Provincia WHERE Nombre = 'Catamarca')),
((SELECT Id FROM Usuario WHERE Nombre = 'Cliente3'), 'Sofía', 'Somer', 'Femenino', '33222003', '1332220030', '88883333', 'cliente3@gmail.com', '19900303', 
'Libertad 300', (SELECT l.Id FROM Localidad l INNER JOIN Provincia p ON p.Id = l.ProvinciaId WHERE p.Nombre = 'Buenos Aires' AND l.Nombre = 'La Plata'), 
	(SELECT Id FROM Provincia WHERE Nombre = 'Buenos Aires')),
((SELECT Id FROM Usuario WHERE Nombre = 'Cliente4'), 'Florencia', 'Arboleda', 'Femenino', '33222004', '1332220040', '88884444', 'cliente4@gmail.com', '19900404', 
'Libertad 400', (SELECT l.Id FROM Localidad l INNER JOIN Provincia p ON p.Id = l.ProvinciaId WHERE p.Nombre = 'Chubut' AND l.Nombre = 'Trelew'), 
	(SELECT Id FROM Provincia WHERE Nombre = 'Chubut')),
((SELECT Id FROM Usuario WHERE Nombre = 'Cliente5'), 'Marcos', 'Vitrales', 'Masculino', '33222005', '1332220050', '88885555', 'cliente5@gmail.com', '19900505', 
'Libertad 500', (SELECT l.Id FROM Localidad l INNER JOIN Provincia p ON p.Id = l.ProvinciaId WHERE p.Nombre = 'Chaco' AND l.Nombre = 'Castelli'), 
	(SELECT Id FROM Provincia WHERE Nombre = 'Chaco')),
((SELECT Id FROM Usuario WHERE Nombre = 'Cliente6'), 'Julio', 'Cesar', 'Masculino', '33222006', '1332220060', '88886666', 'cliente6@gmail.com', '19900606', 
'Libertad 600', (SELECT l.Id FROM Localidad l INNER JOIN Provincia p ON p.Id = l.ProvinciaId WHERE p.Nombre = 'Ciudad Autónoma de Buenos Aires' AND l.Nombre = 'San Telmo'), 
	(SELECT Id FROM Provincia WHERE Nombre = 'Ciudad Autónoma de Buenos Aires'))



