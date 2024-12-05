CREATE SCHEMA BancoTP;

USE BancoTP;

CREATE TABLE UsuarioTipo
(
    Id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    Descripcion VARCHAR(50) NOT NULL
);

CREATE TABLE Usuario
(
    Id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(25),
    Clave VARCHAR(25),
    TipoId INT,
    Activo BOOL DEFAULT 1,
    FOREIGN KEY (TipoId) REFERENCES UsuarioTipo(Id)
);

CREATE TABLE Provincia
(
    Id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(50) NOT NULL
);

CREATE TABLE Localidad
(
    Id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    ProvinciaId INT NOT NULL,
    Nombre VARCHAR(50) NOT NULL,
    FOREIGN KEY (ProvinciaId) REFERENCES Provincia(Id)
);


CREATE TABLE Cliente
(
	Id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	UsuarioId INT,
	Nombre VARCHAR(25),
	Apellido VARCHAR(25),
	Sexo VARCHAR(25),
	DNI VARCHAR(25),
	CUIL VARCHAR(25),
	Telefono VARCHAR(25),
	Email VARCHAR(25),
	FechaNacimiento DATE,
	Direccion VARCHAR(50),
	LocalidadId INT,
	ProvinciaId INT
);

CREATE TABLE CuentaTipo
(
    Id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    Descripcion VARCHAR(50) NOT NULL
);

CREATE TABLE Cuenta
(
    Id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    ClienteId INT,
    Monto FLOAT(10,2),	
    Activa BOOL DEFAULT 1,
    CBU BIGINT,
    TipoId INT,
    FechaDeCreacion DATE,
    FOREIGN KEY (ClienteId) REFERENCES Cliente(Id),
    FOREIGN KEY (TipoId) REFERENCES CuentaTipo(Id)
);

CREATE TABLE MovimientoTipo
(
    Id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    Descripcion VARCHAR(50) NOT NULL
);
CREATE TABLE Movimiento
(
    Id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    CuentaOrigenId INT,
    CuentaDestinoId INT,
    Importe FLOAT(10,2),
    Fecha DATE,
    Detalle VARCHAR(50),
    TipoId INT,
    FOREIGN KEY (TipoId) REFERENCES MovimientoTipo(Id),
    FOREIGN KEY (CuentaOrigenId) REFERENCES Cuenta(Id),
    FOREIGN KEY (CuentaDestinoId) REFERENCES Cuenta(Id)
);

CREATE TABLE EstadoPrestamo
(
    Id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    Descripcion VARCHAR(50) NOT NULL
);
CREATE TABLE Prestamo
(
    Id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    ClienteId INT,
    CuentaId INT,
    FechaAlta DATE,
    MontoSolicitado FLOAT(10,2),
    EstadoId INT,
    CantidadDeCuotas INT,
    ImporteMensualAPagar FLOAT(10,2),
    FOREIGN KEY (ClienteId) REFERENCES Cliente(Id),
    FOREIGN KEY (CuentaId) REFERENCES Cuenta(Id),
    FOREIGN KEY (EstadoId) REFERENCES EstadoPrestamo(Id)
);

CREATE TABLE Cuota
(
    Id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    PrestamoId INT,
    Numero INT,
    Monto FLOAT(10,2),
    Fecha DATE,
    FOREIGN KEY (PrestamoId) REFERENCES Prestamo(Id)
);

DELIMITER $$

CREATE TRIGGER tr_InsertarPrimerMovimiento
AFTER INSERT ON Cuenta
FOR EACH ROW
BEGIN
    INSERT INTO Movimiento (CuentaOrigenId, CuentaDestinoId, Importe, Fecha, Detalle, TipoId)
    VALUES (NULL, NEW.Id, 10000.00, NEW.FechaDeCreacion, 'Alta de cuenta', 3);
END$$

DELIMITER ;

