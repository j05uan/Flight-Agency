package ruta.Infraestructure.in;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import aeropuerto.domain.entity.Aeropuerto;
import aeropuerto.infraestructure.out.AeropuertoRepository;
import ruta.Application.RutaUseCase;
import ruta.Domain.Entity.Ruta;
import ruta.Infraestructure.out.RutaRepository;
import rutaEscala.Domain.entity.RutaEscala;
import rutaEscala.interfaces.out.RutaEscalaRepository;
import salidaAeropuerto.Domain.entity.SalidaAeropuerto;
import salidaAeropuerto.infraestructure.out.SalidaAeropuertoRepository;
import utils.Consola;
import static utils.Consola.cleanScreen;

public class RutaControlador {
    private final Scanner scanner = new Scanner(System.in);
    private final RutaUseCase rutaUseCase;

    public RutaControlador(RutaUseCase rutaUseCase) {
        this.rutaUseCase = rutaUseCase;
    }

    public void start() {
        int opcion;

        do {
            mostrarMenu();
            opcion = Integer.parseInt(scanner.nextLine());
            
            try {
                switch (opcion) {
                    case 1:
                        cleanScreen();
                        crearRuta();
                        break;
                    case 2:
                        cleanScreen();
                        obtenerTodosLosRutas();
                        break;
                    case 3:
                        cleanScreen();
                        obtenerRutaPorId();
                        break;
                    case 4:
                        cleanScreen();
                        actualizarRuta();
                        break;
                    case 5:
                        cleanScreen();
                        eliminarRuta();
                        break;
                    case 6:
                        System.out.println("Saliendo del programa...");
                        break;
                    default:
                        System.out.println("Opción no válida. Por favor, intente de nuevo.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número entero.");
            } catch (Exception e) {
                System.out.println("Ha ocurrido un error: " + e.getMessage());
            }
        } while (opcion != 6);
    }

    private void mostrarMenu() {
        System.out.println("---- Menú de Opciones de Ruta ----");
        System.out.println("1. Crear Ruta");
        System.out.println("2. Listar Todas las Rutas");
        System.out.println("3. Buscar Ruta por ID");
        System.out.println("4. Actualizar Ruta");
        System.out.println("5. Eliminar Ruta");
        System.out.println("6. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private void crearRuta() {
        System.out.println("--- Menú Crear Ruta ---");

        Date fecha = Consola.fechaRuta();
        List<Aeropuerto> aeropuertos = new AeropuertoRepository().obtenerTodosLosAeropuertos();
        List<SalidaAeropuerto> salidaAeropuertos = new SalidaAeropuertoRepository().obtenerTodosAeropuertoSalidas();

        if (aeropuertos.isEmpty() || salidaAeropuertos.isEmpty()) {
            System.out.println("No hay aeropuertos o salidas disponibles.");
            return;
        }

        System.out.println("Seleccione el aeropuerto Origen:");
        mostrarAeropuertos(aeropuertos);
        int opcionAeropuertoOrigen = Consola.optionValidation("Ingrese el ID del aeropuerto Origen", 1, aeropuertos.size());
        Aeropuerto aeropuertoSeleccionadoOrigen = aeropuertos.get(opcionAeropuertoOrigen - 1);

        System.out.println("Seleccione el aeropuerto Destino:");
        mostrarAeropuertos(aeropuertos);
        int opcionAeropuertoDestino = Consola.optionValidation("Ingrese el ID del aeropuerto Destino", 1, aeropuertos.size());
        Aeropuerto aeropuertoSeleccionadoDestino = aeropuertos.get(opcionAeropuertoDestino - 1);

        System.out.println("Seleccione la Salida:");
        mostrarSalidasAeropuerto(salidaAeropuertos);
        int opcionSalidaAeropuerto = Consola.optionValidation("Ingrese el ID de la Salida Seleccionada", 1, salidaAeropuertos.size());
        SalidaAeropuerto salidaAeropuertoSeleccionada = salidaAeropuertos.get(opcionSalidaAeropuerto - 1);

        Ruta nuevaRuta = new Ruta();
        nuevaRuta.setFecha(fecha);
        nuevaRuta.setAeropuertoOrigen(aeropuertoSeleccionadoOrigen);
        nuevaRuta.setAeropuertoDestino(aeropuertoSeleccionadoDestino);
        nuevaRuta.setSalidaAeropuerto(salidaAeropuertoSeleccionada);
        rutaUseCase.crearRuta(nuevaRuta);

        

        int escalas = Consola.optionValidation("Ingrese el número de escalas", 0, Integer.MAX_VALUE);
        for (int i = 0; i < escalas; i++) {
            RutaEscala rutaEscala = new RutaEscala();
            rutaEscala.setRuta(nuevaRuta);
            new RutaEscalaRepository().crearRutaEscala(rutaEscala);
            System.out.println("Escala Asignada exitosamento.");

        }
        System.out.println("Ruta creada exitosamente. ID de la ruta: " + nuevaRuta.getId());
    }

    private void obtenerTodosLosRutas() {
        System.out.println("Listado de Rutas");
        List<Ruta> rutas = rutaUseCase.obtenerTodasLasRutas();

        if (!rutas.isEmpty()) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            for (Ruta ruta : rutas) {
                System.out.printf("ID: %d, Fecha: %s, Aeropuerto Origen: %d, Aeropuerto Destino: %d%n",
                        ruta.getId(),
                        sdf.format(ruta.getFecha()),
                        ruta.getAeropuertoOrigen().getId(),
                        ruta.getAeropuertoDestino().getId());
            }
        } else {
            System.out.println("No hay rutas registradas.");
        }
    }

    private void obtenerRutaPorId() {
        System.out.println("--- Buscar Ruta por ID ---");
        System.out.print("Ingrese el ID de la ruta: ");
        try {
            Long id = Long.parseLong(scanner.nextLine());
            Ruta ruta = rutaUseCase.obtenerRutaPorId(id);

            if (ruta != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                System.out.printf("ID: %d, Fecha: %s, Aeropuerto Origen: %d, Aeropuerto Destino: %d%n",
                        ruta.getId(),
                        sdf.format(ruta.getFecha()),
                        ruta.getAeropuertoOrigen().getId(),
                        ruta.getAeropuertoDestino().getId());
            } else {
                System.out.println("Ruta no encontrada.");
            }
        } catch (NumberFormatException e) {
            System.out.println("ID inválido. Por favor, ingrese un número entero.");
        }
    }

    private void actualizarRuta() {
        System.out.println("--- Actualizar Ruta ---");
        System.out.print("Ingrese el ID de la ruta a actualizar: ");
        try {
            Long id = Long.parseLong(scanner.nextLine());
            Ruta ruta = rutaUseCase.obtenerRutaPorId(id);

            if (ruta != null) {
                Date fecha = Consola.fechaRuta();
                List<Aeropuerto> aeropuertos = new AeropuertoRepository().obtenerTodosLosAeropuertos();
                List<SalidaAeropuerto> salidaAeropuertos = new SalidaAeropuertoRepository().obtenerTodosAeropuertoSalidas();

                if (aeropuertos.isEmpty() || salidaAeropuertos.isEmpty()) {
                    System.out.println("No hay aeropuertos o salidas disponibles.");
                    return;
                }

                System.out.println("Seleccione el nuevo aeropuerto Origen:");
                mostrarAeropuertos(aeropuertos);
                int opcionAeropuertoOrigen = Consola.optionValidation("Ingrese el ID del aeropuerto Origen", 1, aeropuertos.size());
                Aeropuerto aeropuertoSeleccionadoOrigen = aeropuertos.get(opcionAeropuertoOrigen - 1);

                System.out.println("Seleccione el nuevo aeropuerto Destino:");
                mostrarAeropuertos(aeropuertos);
                int opcionAeropuertoDestino = Consola.optionValidation("Ingrese el ID del aeropuerto Destino", 1, aeropuertos.size());
                Aeropuerto aeropuertoSeleccionadoDestino = aeropuertos.get(opcionAeropuertoDestino - 1);

                System.out.println("Seleccione la nueva Salida:");
                mostrarSalidasAeropuerto(salidaAeropuertos);
                int opcionSalidaAeropuerto = Consola.optionValidation("Ingrese el ID de la Salida Seleccionada", 1, salidaAeropuertos.size());
                SalidaAeropuerto salidaAeropuertoSeleccionada = salidaAeropuertos.get(opcionSalidaAeropuerto - 1);

                ruta.setFecha(fecha);
                ruta.setAeropuertoOrigen(aeropuertoSeleccionadoOrigen);
                ruta.setAeropuertoDestino(aeropuertoSeleccionadoDestino);
                ruta.setSalidaAeropuerto(salidaAeropuertoSeleccionada);
                rutaUseCase.actualizarRuta(ruta);

                System.out.println("Ruta actualizada exitosamente.");
            } else {
                System.out.println("Ruta no encontrada.");
            }
        } catch (NumberFormatException e) {
            System.out.println("ID inválido. Por favor, ingrese un número entero.");
        }
    }

    private void eliminarRuta() {
        System.out.println("--- Eliminar Ruta ---");
        System.out.print("Ingrese el ID de la ruta a eliminar: ");
        try {
            Long id = Long.parseLong(scanner.nextLine());
            Ruta ruta = rutaUseCase.obtenerRutaPorId(id);

            if (ruta != null) {
                rutaUseCase.eliminarRuta(id);
                System.out.println("Ruta eliminada exitosamente.");
            } else {
                System.out.println("Ruta no encontrada.");
            }
        } catch (NumberFormatException e) {
            System.out.println("ID inválido. Por favor, ingrese un número entero.");
        }
    }

    private void mostrarSalidasAeropuerto(List<SalidaAeropuerto> salidaAeropuertos) {
        for (int i = 0; i < salidaAeropuertos.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, salidaAeropuertos.get(i).getSalidaAeropuerto());
        }
    }

    private void mostrarAeropuertos(List<Aeropuerto> aeropuertos) {
        for (int i = 0; i < aeropuertos.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, aeropuertos.get(i).getNombre());
        }
    }
}
