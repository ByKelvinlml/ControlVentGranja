CREATE DATABASE IF NOT EXISTS control_ventilacion_granja;

USE control_ventilacion_granja;

CREATE TABLE IF NOT EXISTS usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario VARCHAR(50) NOT NULL,
    clave VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS areas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(200),
    capacidad INT
);

CREATE TABLE IF NOT EXISTS temperaturas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_area INT NOT NULL,
    temperatura DECIMAL(5,2) NOT NULL,
    estado VARCHAR(50),
    ventilacion VARCHAR(100),
    fecha DATE,
    FOREIGN KEY (id_area) REFERENCES areas(id)
);

INSERT INTO usuarios (usuario, clave)
SELECT 'admin', '1234'
WHERE NOT EXISTS (
    SELECT 1
    FROM usuarios
    WHERE usuario = 'admin'
);

ALTER TABLE temperaturas
ADD UNIQUE (id_area);

INSERT INTO usuarios (usuario, clave)
SELECT 'kelvin', '20241606'
WHERE NOT EXISTS (
    SELECT 2
    FROM usuarios
    WHERE usuario = 'kelvin'
);

INSERT INTO usuarios (usuario, clave)
SELECT 'anabel', '20252422'
WHERE NOT EXISTS (
    SELECT 3
    FROM usuarios
    WHERE usuario = 'anabel'
);


