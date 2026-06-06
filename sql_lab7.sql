-- ============================================================================
-- 1. CREACIÓN Y SELECCIÓN DE LA BASE DE DATOS
-- ============================================================================
DROP DATABASE IF EXISTS Veterinaria;
CREATE DATABASE Veterinaria CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE Veterinaria;

-- ============================================================================
-- 2. CREACIÓN DE TABLAS
-- ============================================================================

-- especie
CREATE TABLE especie (
    idespecie INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(45) NOT NULL
);

-- veterinario
CREATE TABLE veterinario (
    idveterinario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    especialidad VARCHAR(100) NOT NULL
);

--  dueño
CREATE TABLE dueno (
    iddueno INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    telefono VARCHAR(20)
);

-- Tabla: mascota
CREATE TABLE mascota (
    idmascota INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    edad INT NOT NULL,
    peso DECIMAL(5,2) NOT NULL,
    especie_id INT NOT NULL,
    veterinario_id INT NOT NULL,
    dueno_id INT NOT NULL,
    FOREIGN KEY (especie_id) REFERENCES especie(idespecie),
    FOREIGN KEY (veterinario_id) REFERENCES veterinario(idveterinario),
    FOREIGN KEY (dueno_id) REFERENCES dueno(iddueno)
);

-- ============================================================================
-- 3. INSERCIÓN DE DATOS DE PRUEBA
-- ============================================================================

--  especie
INSERT INTO especie (nombre) VALUES
('Perro'),
('Gato'),
('Conejo'),
('Loro');

--  veterinario
INSERT INTO veterinario (nombre, especialidad) VALUES
('Carlos Perez', 'Cirugia'),
('Ana Torres', 'Dermatologia'),
('Luis Ramirez', 'Medicina General');

--  dueno
INSERT INTO dueno (nombre, telefono) VALUES
('Rodrigo Diaz', '999111222'),
('Lucia Ramos', '988777666'),
('Miguel Castro', '977555444');

-- mascota
INSERT INTO mascota (nombre, edad, peso, especie_id, veterinario_id, dueno_id) VALUES
('Max', 5, 12.50, 1, 1, 1),
('Michi', 2, 4.20, 2, 2, 2),
('Bunny', 1, 1.30, 3, 1, 1),
('Rocky', 7, 18.90, 1, 3, 3),
('Piolin', 3, 0.80, 4, 2, 2);
