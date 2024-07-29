package rutaEscala.interfaces.in;

import java.util.List;
import java.util.Scanner;

import aeropuerto.domain.entity.Aeropuerto;
import aeropuerto.infraestructure.out.AeropuertoRepository;
import avion.Domain.Entity.Avion;
import avion.Infraestructure.out.AvionRepository;
import ruta.Infraestructure.out.RutaRepository;
import rutaEscala.Applicacion.RutaEscalaUseCase;
import rutaEscala.Domain.entity.RutaEscala;
import rutaEscala.interfaces.out.RutaEscalaRepository;
import salidaAeropuerto.Domain.entity.SalidaAeropuerto;
import salidaAeropuerto.infraestructure.out.SalidaAeropuertoRepository;
import utils.Consola;

public class RutaEscalaControlador {


    private final RutaEscalaUseCase rutaEscalaUseCase;
    private final Scanner scanner = new Scanner(System.in);
    
    

    public RutaEscalaControlador(RutaEscalaUseCase rutaEscalaUseCase) {
        this.rutaEscalaUseCase = rutaEscalaUseCase;
    }

    public void start(){
        int opcion;

        do {
            mostrarMenu();
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (opcion) {
                case 1:
                    System.out.println("Para crear escalas solo se puede al crear un vuelo");
                    System.out.println("precione enter para volver");
                    scanner.nextLine();
                    break;
                case 2:
                    obtenerTodosLosEscalas();
                    break;
                case 3:
                    obtenerEscalaPorId();
                    break;
                case 4:
                    actualizarEscala();
                    break;
                case 5:
                    eliminarEscala();
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
        System.out.println("---- Menu de Opciones ----");
        System.out.println("1. Crear Escala");
        System.out.println("2. Listar Todos los Escalas");
        System.out.println("3. Buscar Escala por ID");
        System.out.println("4. Actualizar Escala");
        System.out.println("5. Eliminar Escala");
        System.out.println("6. Salir");
        System.out.println("Seleccione una opción:");
    }

    public void crearEscala(){

        System.out.println("--Menu Crear Escala---");
        
        List<Aeropuerto> aeropuertos = new AeropuertoRepository().obtenerTodosLosAeropuertos();
        System.out.println("Seleccione el aeropuerto Origen:");
        mostrarAeropuerto(aeropuertos);
        int opcionAeropuertoOrigen = Consola.optionValidation("Ingrese el id del aeropuerto Origen", 1, aeropuertos.size());
        Aeropuerto aeropuertoSeleccionadoOrigen = aeropuertos.get(opcionAeropuertoOrigen - 1);
        
        System.out.println("Seleccione el aeropuerto Destino:");
        mostrarAeropuerto(aeropuertos);
        int opcionAeropuertoDestino = Consola.optionValidation("Ingrese el id del aeropuerto Destino", 1, aeropuertos.size());
        Aeropuerto aeropuertoSeleccionadoDestino = aeropuertos.get(opcionAeropuertoDestino - 1);
        
        List<Avion> aviones = new AvionRepository().obtenerTodosLosAviones();
        System.out.println("Seleccione el avion");
        mostrarAviones(aviones);
        int opciónAvion = Consola.optionValidation("Ingrese el id del Avion ", 1, aviones.size());
        Avion avionSeleccionado = aviones.get(opciónAvion -1);

        List<SalidaAeropuerto> salidaAeropuertos = new SalidaAeropuertoRepository().obtenerTodosAeropuertoSalidas();
        System.out.println("Seleccione la Salida:");
        mostrarSalidasAeropuerto(salidaAeropuertos);
        int opcionSalidaAeropuerto = Consola.optionValidation("Ingrese el id de la Salida Seleccionada", 1, salidaAeropuertos.size());
        SalidaAeropuerto salidaAeropuertoSeleccionada = salidaAeropuertos.get(opcionSalidaAeropuerto - 1);

        System.out.println("Ingrese la Fecha y Hora de la llegada: ");
        String llegada = scanner.nextLine();

        System.out.println("Ingrese la Fecha y Hora de Salida");
        String Salida = scanner.nextLine();

        RutaEscala escala = new RutaEscala();
        escala.setAeropuertoDestino(aeropuertoSeleccionadoDestino);
        escala.setAeropuertoOrigen(aeropuertoSeleccionadoOrigen);
        escala.setAvion(avionSeleccionado);
        escala.setHoraLlegada(llegada);
        escala.setHoraSalida(Salida);
        escala.setSalidaAeropuerto(salidaAeropuertoSeleccionada);


    }

    public void obtenerTodosLosEscalas() {
        List<RutaEscala> escalas = rutaEscalaUseCase.obtenerTodasRutaEscalas();
        if (escalas.isEmpty()) {
            System.out.println("No se encontraron escalas.");
        } else {
            for (RutaEscala escala : escalas) {
                System.out.println(escala);  // Asumiendo que has implementado el método toString en RutaEscala
            }
        }
    }
    
    public void obtenerEscalaPorId() {
        System.out.println("-- Buscar Escala por ID --");
        System.out.println("Ingrese el ID de la escala:");
        long id = scanner.nextInt();
        scanner.nextLine();  // Consumir el salto de línea
        RutaEscala escala = rutaEscalaUseCase.obtenerEscalaPorId(id);
        if (escala != null) {
            System.out.println(escala);  // Asumiendo que has implementado el método toString en RutaEscala
        } else {
            System.out.println("No se encontró ninguna escala con el ID proporcionado.");
        }
    }
    
    public void actualizarEscala() {
        System.out.println("-- Actualizar Escala --");
        System.out.println("Ingrese el ID de la escala a actualizar:");
        long id = scanner.nextInt();
        scanner.nextLine();  // Consumir el salto de línea
        
        RutaEscala escala = rutaEscalaUseCase.obtenerEscalaPorId(id);
        if (escala == null) {
            System.out.println("No se encontró ninguna escala con el ID proporcionado.");
            return;
        }
        
        List<Aeropuerto> aeropuertos = new AeropuertoRepository().obtenerTodosLosAeropuertos();
        System.out.println("Seleccione el aeropuerto Origen:");
        mostrarAeropuerto(aeropuertos);
        int opcionAeropuertoOrigen = Consola.optionValidation("Ingrese el id del aeropuerto Origen", 1, aeropuertos.size());
        Aeropuerto aeropuertoSeleccionadoOrigen = aeropuertos.get(opcionAeropuertoOrigen - 1);
        
        System.out.println("Seleccione el aeropuerto Destino:");
        mostrarAeropuerto(aeropuertos);
        int opcionAeropuertoDestino = Consola.optionValidation("Ingrese el id del aeropuerto Destino", 1, aeropuertos.size());
        Aeropuerto aeropuertoSeleccionadoDestino = aeropuertos.get(opcionAeropuertoDestino - 1);
        
        List<Avion> aviones = new AvionRepository().obtenerTodosLosAviones();
        System.out.println("Seleccione el avion");
        mostrarAviones(aviones);
        int opciónAvion = Consola.optionValidation("Ingrese el id del Avion ", 1, aviones.size());
        Avion avionSeleccionado = aviones.get(opciónAvion -1);

        List<SalidaAeropuerto> salidaAeropuertos = new SalidaAeropuertoRepository().obtenerTodosAeropuertoSalidas();
        System.out.println("Seleccione la Salida:");
        mostrarSalidasAeropuerto(salidaAeropuertos);
        int opcionSalidaAeropuerto = Consola.optionValidation("Ingrese el id de la Salida Seleccionada", 1, salidaAeropuertos.size());
        SalidaAeropuerto salidaAeropuertoSeleccionada = salidaAeropuertos.get(opcionSalidaAeropuerto - 1);

        System.out.println("Ingrese la Fecha y Hora de la llegada: ");
        String llegada = scanner.nextLine();

        System.out.println("Ingrese la Fecha y Hora de Salida");
        String Salida = scanner.nextLine();

        RutaEscala newescala = new RutaEscala();
        newescala.setAeropuertoDestino(aeropuertoSeleccionadoDestino);
        newescala.setAeropuertoOrigen(aeropuertoSeleccionadoOrigen);
        newescala.setAvion(avionSeleccionado);
        newescala.setHoraLlegada(llegada);
        newescala.setHoraSalida(Salida);
        newescala.setSalidaAeropuerto(salidaAeropuertoSeleccionada);
        rutaEscalaUseCase.actualizarEscala(newescala);
        System.out.println("Escala actualizada correctamente.");
    }
    
    public void eliminarEscala() {
        System.out.println("-- Eliminar Escala --");
        System.out.println("Ingrese el ID de la escala a eliminar:");
        long id = scanner.nextInt();
        scanner.nextLine();  // Consumir el salto de línea
        
        RutaEscala escala = rutaEscalaUseCase.obtenerEscalaPorId(id);
        if (escala == null) {
            System.out.println("No se encontró ninguna escala con el ID proporcionado.");
            return;
        }
        
        // Confirmación de eliminación
        System.out.println("¿Está seguro de eliminar esta escala? (S/N)");
        String confirmacion = scanner.nextLine().trim().toUpperCase();
        
        if (confirmacion.equals("S")) {
            // Eliminar en el repositorio
            rutaEscalaUseCase.eliminarEscala(id);
            System.out.println("Escala eliminada correctamente.");
        } else {
            System.out.println("Eliminación cancelada.");
        }
    }
    


    private void mostrarSalidasAeropuerto(List<SalidaAeropuerto> salidaAeropuertos) {
        for (int i = 0; i < salidaAeropuertos.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, salidaAeropuertos.get(i).getSalidaAeropuerto());
        }
    }

    private void mostrarAviones(List<Avion> aviones) {
        for (int i = 0; i < aviones.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, aviones.get(i).getId());
        }
    }

    

    private void mostrarAeropuerto(List<Aeropuerto> aeropuertos) {
        for (int i = 0; i < aeropuertos.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, aeropuertos.get(i).getNombre());
        }
    }

}
