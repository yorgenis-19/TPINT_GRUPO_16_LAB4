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
    FechaPago DATE,
    FechaVencimiento DATE,
    Estado BOOL,
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

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_PAGO_CUOTA`(	
	IN IdCuota INT, 
	IN NroCuenta INT,
	IN tipoMovimiento INT,
	IN saldo DECIMAL(20,6),
	IN detalle VARCHAR(200),
	IN idCliente INT
)
BEGIN
	-- Actualizar la cuota con estado falso y fecha de pago actual
	UPDATE CUOTA c
	SET c.estado = FALSE,
		c.fechaPago = CURDATE()
	WHERE c.id = IdCuota;

	-- Actualizar el monto de la cuenta
	UPDATE CUENTA cu
	SET cu.monto = saldo
	WHERE cu.Id = NroCuenta;

	-- Insertar un registro en la tabla MOVIMIENTO
	INSERT INTO MOVIMIENTO (cuentaOrigenId, cuentaDestinoId, importe, fecha, detalle, tipoId)
	VALUES (
		NroCuenta, 
		9,
		(SELECT c.monto * -1 FROM CUOTA c WHERE c.id = IdCuota),
		CURDATE(), 
		detalle,
		2
	);
END$$

DELIMITER ;

DELIMITER //
CREATE TRIGGER PrestamoCreado
AFTER INSERT ON prestamo
FOR EACH ROW
BEGIN
	DECLARE x INT DEFAULT 1; 
	DECLARE fecha_venc DATE; -- Fecha de vencimiento de la cuota

	WHILE x <= NEW.CantidadDeCuotas DO
	    -- Calcular la fecha de vencimiento como el 23 de cada mes
	    SET fecha_venc = DATE_ADD(CURRENT_DATE(), INTERVAL (x - 1) MONTH);
	    SET fecha_venc = DATE_FORMAT(fecha_venc, '%Y-%m-23'); -- Ajustar al día 23

	    -- Insertar la cuota en la tabla cuota
	    INSERT INTO cuota (PrestamoId, Numero, Monto, Estado, FechaPago, FechaVencimiento)
	    VALUES (
	        NEW.Id, -- ID del préstamo
	        x, -- Número de la cuota
	        CAST((NEW.MontoSolicitado * 1.15) / NEW.CantidadDeCuotas AS DECIMAL(10,2)), -- Monto de la cuota con interés
	        TRUE, -- Estado: TRUE (indica "pendiente")
	        NULL, -- Fecha de pago: NULL
	        fecha_venc -- Fecha de vencimiento: 23 de ese mes
	    );

	    SET x = x + 1; -- Incrementar el contador
	END WHILE;
END//
DELIMITER ;


DELIMITER $$

CREATE PROCEDURE ObtenerValoresTotales(
    IN fechaInicio DATE,
    IN fechaFin DATE
)
BEGIN
    SELECT DISTINCT 'CantidadClientes' AS Tipo, COUNT(Cliente.Id) AS Valor  
    FROM Cliente
    INNER JOIN Usuario ON Usuario.Id = Cliente.UsuarioId
    WHERE Usuario.Activo = 1

    UNION ALL

    SELECT 'CantidadCuentas', COUNT(*) 
    FROM Cuenta 
    WHERE Cuenta.Activa = 1 AND Cuenta.FechaDeCreacion BETWEEN fechaInicio AND fechaFin

    UNION ALL

    SELECT 'SaldoTotalCuentas', ROUND(SUM(Monto), 2) 
    FROM Cuenta 
    WHERE Activa = 1 AND FechaDeCreacion BETWEEN fechaInicio AND fechaFin

    UNION ALL

    SELECT 'SaldoTotalPrestamos', ROUND(SUM(Prestamo.MontoSolicitado), 2)  
    FROM Prestamo 
    WHERE Prestamo.FechaAlta BETWEEN fechaInicio AND fechaFin

    UNION ALL

    SELECT 'SaldoTotalCuotasPagas', ROUND(SUM(Cuota.Monto), 2)  
    FROM Cuota
    WHERE Cuota.FechaPago IS NOT NULL AND Cuota.FechaPago BETWEEN fechaInicio AND fechaFin

    UNION ALL

    SELECT 'SaldoTotalCuotasPendientes', ROUND(SUM(Cuota.Monto), 2)  
    FROM Cuota
    INNER JOIN Prestamo ON Prestamo.Id = Cuota.PrestamoId
    WHERE Cuota.FechaPago IS NULL AND Prestamo.FechaAlta BETWEEN fechaInicio AND fechaFin;

END$$

DELIMITER ;

