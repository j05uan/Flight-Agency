CREATE DATABASE railway;
USE railway;

CREATE TABLE paises (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL UNIQUE  -- Nombre del país
);

CREATE TABLE ciudades (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    pais_id INT,
    FOREIGN KEY (pais_id) REFERENCES paises(id) ON DELETE CASCADE,
    UNIQUE(nombre, pais_id)  -- Cada ciudad debe ser única en su país
);

CREATE TABLE aeropuertos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    ciudad_id INT,
    FOREIGN KEY (ciudad_id) REFERENCES ciudades(id) ON DELETE CASCADE
);

CREATE TABLE fabricantes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL UNIQUE  -- Nombre del fabricante
);

CREATE TABLE modelos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL UNIQUE,  -- Nombre del modelo
    fabricante_id INT,
    FOREIGN KEY (fabricante_id) REFERENCES fabricantes(id) ON DELETE SET NULL
);

CREATE TABLE aerolineas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL UNIQUE  -- Nombre de la aerolínea
);

CREATE TABLE estados_avion (
    id INT AUTO_INCREMENT PRIMARY KEY,
    estado VARCHAR(50) NOT NULL UNIQUE  -- Estado del avión
);

CREATE TABLE tipo_empleado (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tipo VARCHAR(100) NOT NULL UNIQUE  -- Tipo de empleado (ej. Piloto, Azafata, Mecánico)
);

CREATE TABLE empleados (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    fecha_ingreso DATE,
    aerolinea_id INT,
    tipo_empleado_id INT,
    FOREIGN KEY (aerolinea_id) REFERENCES aerolineas(id),
    FOREIGN KEY (tipo_empleado_id) REFERENCES tipo_empleado(id)
);

CREATE TABLE aviones (
    id INT AUTO_INCREMENT PRIMARY KEY,
    matricula VARCHAR(50) NOT NULL UNIQUE,
    capacidad INT NOT NULL,
    fecha_fabricacion DATE,
    aerolinea_id INT,
    modelo_id INT,
    filas, 
    columnas,
    FOREIGN KEY (aerolinea_id) REFERENCES aerolineas(id),
    FOREIGN KEY (modelo_id) REFERENCES modelos(id)
);

CREATE TABLE historial_estado (
    id INT AUTO_INCREMENT PRIMARY KEY,
    avion_id INT,
    estado_avion_id INT,
    fecha_inicio DATE NOT NULL,
    FOREIGN KEY (avion_id) REFERENCES aviones(id),
    FOREIGN KEY (estado_avion_id) REFERENCES estados_avion(id),
    UNIQUE(avion_id, estado_avion_id, fecha_inicio)
);

CREATE TABLE tipos_documentos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tipo VARCHAR(100) NOT NULL UNIQUE  -- Tipo de documento (ej. DNI, Pasaporte)
);

CREATE TABLE clientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    edad INT NOT NULL,
    tipo_documento_id INT,
    documento VARCHAR(50) NOT NULL UNIQUE,
    FOREIGN KEY (tipo_documento_id) REFERENCES tipos_documentos(id)
);

CREATE TABLE tipo_tarifa (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tipo VARCHAR(100) NOT NULL UNIQUE  -- Tipo de tarifa (ej. Económica, Ejecutiva)
);

CREATE TABLE tarifas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tipo_tarifa_id INT,
    valor DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (tipo_tarifa_id) REFERENCES tipo_tarifa(id)
);

CREATE TABLE salidas_aeropuerto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    salida VARCHAR(100),
    aeropuerto_id INT,
    FOREIGN KEY (aeropuerto_id) REFERENCES aeropuertos(id),
    UNIQUE(aeropuerto_id)
);

CREATE TABLE rutas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    salida_aeropuerto_id INT,
    aeropuerto_origen_id INT,
    aeropuerto_destino_id INT,
    FOREIGN KEY (aeropuerto_origen_id) REFERENCES aeropuertos(id),
    FOREIGN KEY (aeropuerto_destino_id) REFERENCES aeropuertos(id),
    FOREIGN KEY (salida_aeropuerto_id) REFERENCES salidas_aeropuerto(id)
);

CREATE TABLE tarifasRutas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    ruta_id INT,
    tarifa_id INT,
    FOREIGN KEY (ruta_id) REFERENCES rutas(id),
    FOREIGN KEY (tarifa_id) REFERENCES tarifas(id)
);



CREATE TABLE RutaEscala (
    id INT AUTO_INCREMENT PRIMARY KEY,
    idAeropuertoOrigen INT,
    idAeropuertoDestino INT,
    idVuelo INT,
    idAvion INT,
    horaLlegada VARCHAR(200),
    horaSalida VARCHAR (200),
    salidas_aeropuerto_id INT,
    FOREIGN KEY (idAvion) REFERENCES aviones(id),
    FOREIGN KEY (idAeropuertoOrigen) REFERENCES aeropuertos(id),
    FOREIGN KEY (idAeropuertoDestino) REFERENCES aeropuertos(id),
    FOREIGN KEY (idVuelo) REFERENCES rutas(id)
);


CREATE TABLE historial_tarifas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tarifa_id INT,
    fecha_inicio DATE NOT NULL,
    valor DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (tarifa_id) REFERENCES tarifas(id)
);

CREATE TABLE rol_tripulante (
    id INT AUTO_INCREMENT PRIMARY KEY,
    rol VARCHAR(100) NOT NULL UNIQUE  -- Rol del tripulante (ej. Piloto, Copiloto)
);

CREATE TABLE tripulacion_de_vuelo (
    id INT AUTO_INCREMENT PRIMARY KEY,
    vuelo_id INT,
    empleado_id INT,
    rol_id INT,
    FOREIGN KEY (vuelo_id) REFERENCES RutaEscala(id),
    FOREIGN KEY (empleado_id) REFERENCES empleados(id),
    FOREIGN KEY (rol_id) REFERENCES rol_tripulante(id),
    UNIQUE(vuelo_id, empleado_id, rol_id)
);


CREATE TABLE asientos (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL
);

CREATE TABLE asientosAvion (
    id INT PRIMARY KEY AUTO_INCREMENT,
    avion_id INT,
    asieto_id INT,
    columna VARCHAR(1),
    FOREIGN KEY (avion_id) REFERENCES aviones(id),
    FOREIGN KEY (asieto_id) REFERENCES asientos(id)
);

CREATE TABLE reservas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cliente_id INT,
    ruta_id INT,
    fechaReservacion DATE NOT NULL,
    tarifasRutas_id INT,
    FOREIGN KEY (cliente_id) REFERENCES clientes(id),
    FOREIGN KEY (ruta_id) REFERENCES rutas(id),
    FOREIGN KEY (tarifasRutas_id) REFERENCES tarifasRutas(id)
);

CREATE TABLE pasajeros (
    id INT PRIMARY KEY AUTO_INCREMENT,
    cliente_id INT,
    nombre VARCHAR(50),
    apellido VARCHAR(50),
    FOREIGN KEY (cliente_id) REFERENCES clientes(id)
);

CREATE TABLE asientos_reservas (
    id INT PRIMARY KEY AUTO_INCREMENT,
    reserva_id INT,
    asiento_id INT,
    pasajero_id INT,
    FOREIGN KEY (reserva_id) REFERENCES reservas(id),
    FOREIGN KEY (asiento_id) REFERENCES asientosAvion(id),
    FOREIGN KEY (pasajero_id) REFERENCES pasajeros(id),
    UNIQUE (reserva_id, asiento_id, pasajero_id)
);

CREATE TABLE revision (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(200),
    descripcion TEXT
);

CREATE TABLE revisionMantenimiento (
    id INT PRIMARY KEY AUTO_INCREMENT,
    revision_id INT,
    empleado_id INT,
    fecha DATE,
    FOREIGN KEY (revision_id) REFERENCES revision(id),
    FOREIGN KEY (empleado_id) REFERENCES empleados(id)
);

show tables;
