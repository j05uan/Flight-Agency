package ruta.Infraestructure.in;

import java.util.Scanner;

import aeropuerto.infraestructure.out.AeropuertoRepository;
import ruta.Application.RutaUseCase;
import ruta.Infraestructure.out.RutaRepository;
import static utils.Consola.cleanScreen;

public class RutaControlador {
    Scanner scanner = new Scanner(System.in);

    private final RutaUseCase rutaUseCase;
    private final RutaRepository rutaRepo;
    private final AeropuertoRepository aeropuertoRepo;

    
    public RutaControlador(Scanner scanner, RutaUseCase rutaUseCase, RutaRepository rutaRepo,
            AeropuertoRepository aeropuertoRepo) {
        this.scanner = scanner;
        this.rutaUseCase = rutaUseCase;
        this.rutaRepo = rutaRepo;
        this.aeropuertoRepo = aeropuertoRepo;
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
        System.out.println("3. Buscar Avión por ID");
        System.out.println("4. Actualizar Ruta");
        System.out.println("5. Eliminar Ruta");
        System.out.println("6. Salir");
        System.out.println("Seleccione una opción:");
    }
    public void crearRuta(){
        System.out.println("---Menu Crear Ruta---");

        
        
    }
}
