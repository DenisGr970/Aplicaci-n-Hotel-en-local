DROP DATABASE IF EXISTS bbdd_proyectofinal_programación;
CREATE DATABASE bbdd_proyectofinal_programación;
USE bbdd_proyectofinal_programación;

CREATE TABLE usuarios (
    id_cliente INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    pass VARCHAR(255) NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    telefono VARCHAR(20) NOT NULL
);

-- Tabla habitaciones
CREATE TABLE habitaciones (
    id_habitacion INT AUTO_INCREMENT PRIMARY KEY,
    numero INT NOT NULL,
    precio_base DOUBLE NOT NULL,
    tipo ENUM('Simple','Doble','Suite') NOT NULL
);


CREATE TABLE reservas (
    id_reserva INT AUTO_INCREMENT PRIMARY KEY,
    id_cliente INT NOT NULL,
    id_habitacion INT NOT NULL,
    nombre_usuario VARCHAR(100),
    fecha_entrada DATE NOT NULL,
    fecha_salida DATE NOT NULL,
    num_personas VARCHAR(50) NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    precio_total DOUBLE NOT NULL,
    estado ENUM('Activa', 'Cancelada') DEFAULT 'Activa',
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_cliente) REFERENCES usuarios(id_cliente) ON DELETE CASCADE,
    FOREIGN KEY (id_habitacion) REFERENCES habitaciones(id_habitacion) ON DELETE RESTRICT
);


INSERT INTO habitaciones (numero, precio_base, tipo) VALUES 
(1, 50.0, 'Simple'),
(2, 70.0, 'Doble'),
(3, 100.0, 'Suite');