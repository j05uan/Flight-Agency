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
    Scanner scanner = new Scanner(System.in);

    private final RutaUseCase rutaUseCase;
    private final RutaRepository rutaRepo;
    private final AeropuertoRepository aeropuertoRepo;
    private final SalidaAeropuertoRepository salidaAeropuertoRepo;
    private final RutaEscalaRepository rutaEscalaRepo;

    public RutaControlador(Scanner scanner, RutaUseCase rutaUseCase, RutaRepository rutaRepo,
            AeropuertoRepository aeropuertoRepo, SalidaAeropuertoRepository sAeropuertoRepo) {
        this.scanner = scanner;
        this.rutaUseCase = rutaUseCase;
        this.rutaRepo = rutaRepo;
        this.aeropuertoRepo = aeropuertoRepo;
        this.salidaAeropuertoRepo = sAeropuertoRepo;
        this.rutaEscalaRepo = new RutaEscalaRepository();
    }

    public void start() {
        int opcion;

        do {
            mostrarMenu();
            opcion = scanner.nextInt();
            scanner.nextLine(); 

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

        } while (opcion != 6);
    }

    private void mostrarMenu() {
        System.out.println("---- Menu de Opciones de Ruta ----");
        System.out.println("1. Crear Ruta");
        System.out.println("2. Listar Todos los Rutas");
        System.out.println("3. Buscar Ruta por ID");
        System.out.println("4. Actualizar Ruta");
        System.out.println("5. Eliminar Ruta");
        System.out.println("6. Salir");
        System.out.println("Seleccione una opción:");
    }

    public void crearRuta() {
        System.out.println("--- Menu Crear Ruta ---");

        Date fecha = Consola.fechaRuta();  // Cambiar a Date si es necesario
        List<Aeropuerto> aeropuertos = aeropuertoRepo.obtenerTodosLosAeropuertos();
        System.out.println("Seleccione el aeropuerto Origen:");
        mostrarAeropuerto(aeropuertos);
        int opcionAeropuertoOrigen = Consola.optionValidation("Ingrese el id del aeropuerto Origen", 1, aeropuertos.size());
        Aeropuerto aeropuertoSeleccionadoOrigen = aeropuertos.get(opcionAeropuertoOrigen - 1);
        
        System.out.println("Seleccione el aeropuerto Destino:");
        mostrarAeropuerto(aeropuertos);
        int opcionAeropuertoDestino = Consola.optionValidation("Ingrese el id del aeropuerto Destino", 1, aeropuertos.size());
        Aeropuerto aeropuertoSeleccionadoDestino = aeropuertos.get(opcionAeropuertoDestino - 1);
        
        List<SalidaAeropuerto> salidaAeropuertos = salidaAeropuertoRepo.obtenerTodosAeropuertoSalidas();
        System.out.println("Seleccione la Salida:");
        mostrarSalidasAeropuerto(salidaAeropuertos);
        int opcionSalidaAeropuerto = Consola.optionValidation("Ingrese el id de la Salida Seleccionada", 1, salidaAeropuertos.size());
        SalidaAeropuerto salidaAeropuertoSeleccionada = salidaAeropuertos.get(opcionSalidaAeropuerto - 1);

        Ruta nuevaRuta = new Ruta();
        nuevaRuta.setFecha(fecha);
        nuevaRuta.setAeropuertoOrigen(aeropuertoSeleccionadoOrigen);
        nuevaRuta.setAeropuertoDestino(aeropuertoSeleccionadoDestino);
        nuevaRuta.setSalidaAeropuerto(salidaAeropuertoSeleccionada);
        rutaRepo.CrearRuta(nuevaRuta);

        System.out.println("Ruta creada exitosamente. Id de la ruta: " + nuevaRuta.getId());
        
        int escalas = scanner.nextInt();
        for(int i =0;i<escalas;i++){
            Ruta idRuta = rutaRepo.obtenerRutaPorId(nuevaRuta.getId());
            RutaEscala rutaEscala = new RutaEscala();
            rutaEscala.setRuta(idRuta);
            rutaEscalaRepo.crearRutaEscala(rutaEscala);
        }
    }

    public void obtenerTodosLosRutas() {
        System.out.println("Listado de Rutas");
        List<Ruta> rutas = rutaUseCase.obtenerTodasLasRutas();

        if (!rutas.isEmpty()) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // Formato de fecha
            for (Ruta ruta : rutas) {
                System.out.printf("ID: %d, Fecha: %s, Aeropuerto Origen: %d, Aeropuerto Destino: %d%n",
                        ruta.getId(),
                        sdf.format(ruta.getFecha()),  // Formatear la fecha usando SimpleDateFormat
                        ruta.getAeropuertoOrigen().getId(), // Obtener ID del aeropuerto
                        ruta.getAeropuertoDestino().getId() // Obtener ID del aeropuerto
                );
            }
        } else {
            System.out.println("No hay rutas registradas.");
        }
    }

    public void obtenerRutaPorId() {
        System.out.println("--- Buscar Ruta por Id ---");
        System.out.println("Ingrese el Id de la ruta:");
        Long id = scanner.nextLong();
        scanner.nextLine();

        Ruta ruta = rutaUseCase.obtenerRutaPorId(id);

        if (ruta != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // Formato de fecha
            System.out.printf("ID: %d, Fecha: %s, Aeropuerto Origen: %d, Aeropuerto Destino: %d%n",
                    ruta.getId(),
                    sdf.format(ruta.getFecha()),  
                    ruta.getAeropuertoOrigen().getId(),
                    ruta.getAeropuertoDestino().getId()
            );
        } else {
            System.out.println("Ruta no encontrada.");
        }
    }

    public void actualizarRuta() {
        System.out.println("--- Actualizar Ruta ---");
        System.out.println("Ingrese el ID de la ruta a actualizar:");
        Long id = scanner.nextLong();
        scanner.nextLine();

        Ruta ruta = rutaUseCase.obtenerRutaPorId(id);

        if (ruta != null) {
            System.out.println("Seleccione los nuevos datos para la ruta:");
            Date fecha = Consola.fechaRuta();  // Cambiar a Date si es necesario
            List<Aeropuerto> aeropuertos = aeropuertoRepo.obtenerTodosLosAeropuertos();
            System.out.println("Seleccione el aeropuerto Origen:");
            mostrarAeropuerto(aeropuertos);
            int opcionAeropuertoOrigen = Consola.optionValidation("Ingrese el id del aeropuerto Origen", 1, aeropuertos.size());
            Aeropuerto aeropuertoSeleccionadoOrigen = aeropuertos.get(opcionAeropuertoOrigen - 1);
            
            System.out.println("Seleccione el aeropuerto Destino:");
            mostrarAeropuerto(aeropuertos);
            int opcionAeropuertoDestino = Consola.optionValidation("Ingrese el id del aeropuerto Destino", 1, aeropuertos.size());
            Aeropuerto aeropuertoSeleccionadoDestino = aeropuertos.get(opcionAeropuertoDestino - 1);
            
            List<SalidaAeropuerto> salidaAeropuertos = salidaAeropuertoRepo.obtenerTodosAeropuertoSalidas();
            System.out.println("Seleccione la Salida:");
            mostrarSalidasAeropuerto(salidaAeropuertos);
            int opcionSalidaAeropuerto = Consola.optionValidation("Ingrese el id de la Salida Seleccionada", 1, salidaAeropuertos.size());
            SalidaAeropuerto salidaAeropuertoSeleccionada = salidaAeropuertos.get(opcionSalidaAeropuerto - 1);

            Ruta nuevaRuta = new Ruta();
            nuevaRuta.setFecha(fecha);
            nuevaRuta.setAeropuertoOrigen(aeropuertoSeleccionadoOrigen);
            nuevaRuta.setAeropuertoDestino(aeropuertoSeleccionadoDestino);
            nuevaRuta.setSalidaAeropuerto(salidaAeropuertoSeleccionada);
            rutaRepo.CrearRuta(nuevaRuta);

            System.out.println("Ruta actualizada exitosamente.");
        } else {
            System.out.println("Ruta no encontrada.");
        }
    }

    public void eliminarRuta() {
        System.out.println("--- Eliminar Ruta ---");
        System.out.println("Ingrese el ID de la ruta a eliminar:");
        Long id = scanner.nextLong();
        scanner.nextLine();

        Ruta ruta = rutaUseCase.obtenerRutaPorId(id);

        if (ruta != null) {
            rutaUseCase.eliminarRuta(id);  
            System.out.println("Ruta eliminada exitosamente.");
        } else {
            System.out.println("Ruta no encontrada.");
        }
    }

    private void mostrarSalidasAeropuerto(List<SalidaAeropuerto> salidaAeropuertos) {
        for (int i = 0; i < salidaAeropuertos.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, salidaAeropuertos.get(i).getSalidaAeropuerto());
        }
    }

    private void mostrarAeropuerto(List<Aeropuerto> aeropuertos) {
        for (int i = 0; i < aeropuertos.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, aeropuertos.get(i).getNombre());
        }
    }
}
