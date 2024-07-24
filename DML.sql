USE railway;

INSERT INTO paises (nombre) VALUES
    ('Estados Unidos'),
    ('España'),
    ('Francia'),
    ('Alemania');
    
    -- Para Estados Unidos
INSERT INTO ciudades (nombre, pais_id) VALUES
    ('Nueva York', 1),
    ('Los Ángeles', 1);

-- Para España
INSERT INTO ciudades (nombre, pais_id) VALUES
    ('Madrid', 2),
    ('Barcelona', 2);

-- Para Francia
INSERT INTO ciudades (nombre, pais_id) VALUES
    ('París', 3),
    ('Marsella', 3);

-- Para Alemania
INSERT INTO ciudades (nombre, pais_id) VALUES
    ('Berlín', 4),
    ('Múnich', 4);


-- Para Nueva York (Estados Unidos)
INSERT INTO aeropuertos (nombre, ciudad_id) VALUES
    ('John F. Kennedy International Airport', 1),
    ('LaGuardia Airport', 1);

-- Para Madrid (España)
INSERT INTO aeropuertos (nombre, ciudad_id) VALUES
    ('Adolfo Suárez Madrid-Barajas Airport', 3),
    ('Madrid-Cuatro Vientos Airport', 3);

-- Para París (Francia)
INSERT INTO aeropuertos (nombre, ciudad_id) VALUES
    ('Charles de Gaulle Airport', 5),
    ('Orly Airport', 5);

-- Para Berlín (Alemania)
INSERT INTO aeropuertos (nombre, ciudad_id) VALUES
    ('Berlin Tegel Airport', 7),
    ('Berlin Schönefeld Airport', 7);


INSERT INTO fabricantes (nombre) VALUES
    ('Boeing'),
    ('Airbus'),
    ('Bombardier'),
    ('Embraer');

-- Modelos de Boeing
INSERT INTO modelos (nombre, fabricante_id) VALUES
    ('737', 1),
    ('747', 1),
    ('787', 1);

-- Modelos de Airbus
INSERT INTO modelos (nombre, fabricante_id) VALUES
    ('A320', 2),
    ('A330', 2),
    ('A380', 2);
    
    INSERT INTO aerolineas (nombre) VALUES
    ('American Airlines'),
    ('Iberia'),
    ('Air France'),
    ('Lufthansa');
    
    INSERT INTO estados_avion (estado) VALUES
    ('En mantenimiento'),
    ('Operativo'),
    ('En revisión');
    
    INSERT INTO tipo_empleado (tipo) VALUES
    ('Tripulante'),
    ('Empleado Aeropuerto'),
    ('Mecánico');
    
    INSERT INTO empleados (nombre, fecha_ingreso, aerolinea_id, tipo_empleado_id) VALUES
    ('Juan Pérez', '2020-01-15', 1, 1),  -- Piloto en American Airlines
    ('María López', '2019-08-20', 2, 1), -- Azafata en Iberia
    ('Pedro Ramírez', '2021-03-10', 3, 3); -- Mecánico en Air France
    
    INSERT INTO aviones (matricula, capacidad, fecha_fabricacion, aerolinea_id, modelo_id, filas,columnas ) VALUES
    ('N12345', 180, '2018-05-20', 1, 1, 6, 30),  -- Avión de American Airlines, modelo 737
    ('E54321', 250, '2019-10-15', 2, 2, 5, 50),  -- Avión de Iberia, modelo A380
    ('F67890', 200, '2020-07-01', 2, 2, 5, 40);  -- Avión de Air France, modelo A320



INSERT INTO tipos_documentos (tipo) VALUES
    ('DNI'),
    ('Pasaporte'),
    ('Carné de identidad');

INSERT INTO tipo_tarifa (tipo) VALUES
    ('Económica'),
    ('Ejecutiva'),
    ('Primera Clase');
    
INSERT INTO tarifas (tipo_tarifa_id, valor) VALUES
    (1, 250.00),  -- Tarifa económica
    (2, 700.00),  -- Tarifa ejecutiva
    (3, 1200.00); -- Tarifa primera clase

INSERT INTO salidas_aeropuerto (salida, aeropuerto_id) VALUES
    ('Vuelo AA1234 a Los Ángeles', 2),  -- Desde LaGuardia Airport (Los Ángeles)
    ('Vuelo IB5678 a Barcelona', 4),    -- Desde Adolfo Suárez Madrid-Barajas Airport (Madrid)
    ('Vuelo AF9123 a París', 6);        -- Desde Orly Airport (París)
    
INSERT INTO rutas (salida_aeropuerto_id, aeropuerto_origen_id, aeropuerto_destino_id) VALUES
    (1, 2, 1),  -- Desde LaGuardia Airport (Los Ángeles) a John F. Kennedy International Airport (Nueva York)
    (2, 3, 4),  -- Desde Adolfo Suárez Madrid-Barajas Airport (Madrid) a Barcelona
    (3, 5, 6);  -- Desde Orly Airport (París) a Charles de Gaulle Airport (París)

INSERT INTO tarifasRutas (ruta_id, tarifa_id) VALUES
    (1, 1),  -- Tarifa económica para ruta AA1234
    (2, 2),  -- Tarifa ejecutiva para ruta IB5678
    (3, 3);  -- Tarifa primera clase para ruta AF9123


INSERT INTO RutaEscala (idAeropuertoOrigen, idAeropuertoDestino, idVuelo, idAvion, horaLlegada, horaSalida, salidas_aeropuerto_id) VALUES
    (2, 1, 1, 10, '10:00', '08:00', 1),  -- Vuelo AA1234 desde Los Ángeles a Nueva York
    (3, 4, 2, 11, '11:30', '09:30', 2),  -- Vuelo IB5678 desde Madrid a Barcelona
    (5, 6, 3, 12, '13:00', '11:00', 3);  -- Vuelo AF9123 desde París a París

INSERT INTO historial_tarifas (tarifa_id, fecha_inicio, valor) VALUES
    (1, '2023-01-01', 200.00),   -- Tarifa económica iniciada el 1 de enero de 2023
    (2, '2023-01-01', 600.00),   -- Tarifa ejecutiva iniciada el 1 de enero de 2023
    (3, '2023-01-01', 1000.00);  -- Tarifa primera clase iniciada el 1 de enero de 2023

INSERT INTO rol_tripulante (rol) VALUES
    ('Piloto'),
    ('Copiloto'),
    ('Sobrecargo');
    
    
INSERT INTO tripulacion_de_vuelo (vuelo_id, empleado_id, rol_id) VALUES
    (16, 1, 1),  -- Juan Pérez como piloto en vuelo AA1234
    (17, 2, 3),  -- María López como sobrecargo en vuelo IB5678
    (16, 3, 3);  -- Pedro Ramírez como sobrecargo en vuelo AF9123
    
    
INSERT INTO asientos (nombre) VALUES
    ('Clase Ejecutiva'),
    ('Clase Comercial');

INSERT INTO asientosAvion (avion_id, asieto_id, columna) VALUES
    (10, 1, 'A'),  
    (11, 2, 'B'),  
    (12, 2, 'A'),  
    (12, 2, 'B'),  
    (11, 1, 'A');  

INSERT INTO clientes (nombre, edad, tipo_documento_id, documento) VALUES
    ('Ana García', 30, 1, '12345678A'),
    ('Carlos Martínez', 45, 2, 'A98765432'),
    ('Elena López', 25, 1, 'B24681357');

INSERT INTO reservas (cliente_id, ruta_id, fechaReservacion, tarifasRutas_id) VALUES
    (1, 1, '2023-02-15', 1),  -- Reserva de Ana García para vuelo AA1234
    (2, 2, '2023-03-20', 2),  -- Reserva de Carlos Martínez para vuelo IB5678
    (3, 3, '2023-04-25', 3);  -- Reserva de Elena López para vuelo AF9123

INSERT INTO pasajeros (cliente_id, nombre, apellido) VALUES
    (1, 'Ana', 'García'),
    (2, 'Carlos', 'Martínez'),
    (3, 'Elena', 'López');
    
INSERT INTO asientos_reservas (reserva_id, asiento_id, pasajero_id) VALUES
    (1, 11, 1),  -- Asiento A1 reservado por Ana García en vuelo AA1234
    (2, 12, 2),  -- Asiento B2 reservado por Carlos Martínez en vuelo IB5678
    (3, 13, 3);  -- Asiento A1 reservado por Elena López en vuelo AF9123



INSERT INTO revision (nombre, descripcion) VALUES
    ('Mantenimiento de motor', 'Revisión y ajuste del motor principal del avión'),
    ('Revisión de cabina', 'Verificación y mantenimiento de sistemas internos de la cabina'),
    ('Inspección de estructura', 'Inspección de la integridad estructural del avión');


INSERT INTO revisionMantenimiento (revision_id, empleado_id, fecha) VALUES
    (1, 3, '2023-02-10'),  -- Pedro Ramírez realiza mantenimiento de motor
    (2, 2, '2023-03-15'),  -- María López realiza revisión de cabina
    (3, 1, '2023-04-20');  -- Juan Pérez realiza inspección de estructura









    
    
    

