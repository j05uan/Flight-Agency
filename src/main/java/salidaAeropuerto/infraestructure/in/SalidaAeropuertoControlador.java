package salidaAeropuerto.infraestructure.in;

import java.util.List;
import java.util.Scanner;

import aeropuerto.domain.entity.Aeropuerto;
import aeropuerto.infraestructure.out.AeropuertoRepository;
import salidaAeropuerto.Application.SalidaAeropuertoUseCase;
import salidaAeropuerto.Domain.entity.SalidaAeropuerto;
import salidaAeropuerto.infraestructure.out.SalidaAeropuertoRepository;
import utils.Consola;
import static utils.Consola.cleanScreen;

public class SalidaAeropuertoControlador {
    private final Scanner scanner = new Scanner(System.in);
    private final SalidaAeropuertoUseCase salidaAeropuertoUseCase;
    private final SalidaAeropuertoRepository salidaAeropuertoRepo;
    private final AeropuertoRepository aeropuertoRepo;

    public SalidaAeropuertoControlador(SalidaAeropuertoUseCase salidaAeropuertoUseCase,
                                       SalidaAeropuertoRepository salidaAeropuertoRepo, 
                                       AeropuertoRepository aeropuertoRepo) {
        this.salidaAeropuertoUseCase = salidaAeropuertoUseCase;
        this.salidaAeropuertoRepo = salidaAeropuertoRepo;
        this.aeropuertoRepo = aeropuertoRepo;
    }

    public void start() {
        int opcion;

        do {
            mostrarMenu();
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (opcion) {
                case 1:
                    cleanScreen();
                    crearSalidaAeropuerto();
                    break;
                case 2:
                    cleanScreen();
                    listarTodosLasSalidasAeropuerto();
                    break;
                case 3:
                    cleanScreen();
                    buscarSalidaAeropuertoPorId();
                    break;
                case 4:
                    cleanScreen();
                    actualizarSalidaAeropuerto();
                    break;
                case 5:
                    cleanScreen();
                    eliminarSalidaAeropuerto();
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
        System.out.println("---- Menu de Opciones de Salida Aeropuerto ----");
        System.out.println("1. Crear Salida Aeropuerto");
        System.out.println("2. Listar Todos los Salidas Aeropuerto");
        System.out.println("3. Buscar Salida Aeropuerto por ID");
        System.out.println("4. Actualizar Salida Aeropuerto");
        System.out.println("5. Eliminar Salida Aeropuerto");
        System.out.println("6. Salir");
        System.out.println("Seleccione una opción:");
    }

    public void crearSalidaAeropuerto() {
        System.out.println("Ingrese el nombre de la Salida Aeropuerto:");
        String salidaAeropuerto = scanner.nextLine();

        List<Aeropuerto> aeropuertos = aeropuertoRepo.obtenerTodosLosAeropuertos();
        System.out.println("Seleccione el aeropuerto:");
        mostrarAeropuerto(aeropuertos);
        int opcionAeropuerto = Consola.optionValidation("Ingrese el id del aeropuerto", 1, aeropuertos.size());
        Aeropuerto aeropuertoSeleccionado = aeropuertos.get(opcionAeropuerto - 1);

        SalidaAeropuerto nuevaSalida = new SalidaAeropuerto();
        nuevaSalida.setAeropuerto(aeropuertoSeleccionado);
        nuevaSalida.setSalidaAeropuerto(salidaAeropuerto);

        salidaAeropuertoRepo.crearSalidaAeropuerto(nuevaSalida);
        System.out.println("Salida Aeropuerto Creada");
    }

    private void listarTodosLasSalidasAeropuerto() {
        List<SalidaAeropuerto> salidas = salidaAeropuertoRepo.obtenerTodosAeropuertoSalidas();
        if (salidas.isEmpty()) {
            System.out.println("No hay salidas de aeropuerto registradas.");
        } else {
            for (SalidaAeropuerto salida : salidas) {
                System.out.printf("ID: %d, Aeropuerto: %s, Salida: %s%n",
                        salida.getId(),
                        salida.getAeropuerto().getNombre(),
                        salida.getSalidaAeropuerto());
            }
        }
    }

    private void buscarSalidaAeropuertoPorId() {
        System.out.println("Ingrese el ID de la Salida Aeropuerto:");
        Long id = scanner.nextLong();

        SalidaAeropuerto salida = salidaAeropuertoRepo.obtenerSalidaAeropuertoPorId(id);
        if (salida != null) {
            System.out.printf("ID: %d, Aeropuerto: %s, Salida: %s%n",
                    salida.getId(),
                    salida.getAeropuerto().getNombre(),
                    salida.getSalidaAeropuerto());
        } else {
            System.out.println("No se encontró una salida de aeropuerto con el ID proporcionado.");
        }
    }

    private void actualizarSalidaAeropuerto() {
        System.out.println("Ingrese el ID de la Salida Aeropuerto:");
        Long id = scanner.nextLong();
        SalidaAeropuerto salida = salidaAeropuertoRepo.obtenerSalidaAeropuertoPorId(id);
        if (salida != null) {
            System.out.println("Ingrese el nuevo nombre de la Salida Aeropuerto:");
            String nuevoNombre = scanner.nextLine();
            salida.setSalidaAeropuerto(nuevoNombre);

            List<Aeropuerto> aeropuertos = aeropuertoRepo.obtenerTodosLosAeropuertos();
            System.out.println("Seleccione el nuevo aeropuerto:");
            mostrarAeropuerto(aeropuertos);
            int opcionAeropuerto = Consola.optionValidation("Ingrese el id del nuevo aeropuerto", 1, aeropuertos.size());
            Aeropuerto nuevoAeropuerto = aeropuertos.get(opcionAeropuerto - 1);
            salida.setAeropuerto(nuevoAeropuerto);

            salidaAeropuertoRepo.actualizarSalidaAeropuerto(salida);
            System.out.println("Salida Aeropuerto Actualizada");
        } else {
            System.out.println("No se encontró una salida de aeropuerto con el ID proporcionado.");
        }
    }

    private void eliminarSalidaAeropuerto() {
        System.out.println("Ingrese el ID de la Salida Aeropuerto:");
        Long id = scanner.nextLong();
        SalidaAeropuerto salida = salidaAeropuertoRepo.obtenerSalidaAeropuertoPorId(id);
        if (salida != null) {
            salidaAeropuertoRepo.eliminarSalidaAeropuerto(id);
            System.out.println("Salida Aeropuerto Eliminada");
        } else {
            System.out.println("No se encontró una salida de aeropuerto con el ID proporcionado.");
        }
    }

    private void mostrarAeropuerto(List<Aeropuerto> aeropuertos) {
        for (int i = 0; i < aeropuertos.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, aeropuertos.get(i).getNombre());
        }
    }
}
