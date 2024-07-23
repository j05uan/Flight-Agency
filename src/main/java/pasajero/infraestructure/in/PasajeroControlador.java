package pasajero.infraestructure.in;

import java.util.List;
import java.util.Scanner;

import cliente.Domain.entity.Cliente;
import cliente.infraestructure.out.ClienteRepository;
import pasajero.Applicacion.PasajeroUseCase;
import pasajero.Domain.entity.Pasajero;
import static utils.Consola.cleanScreen;

public class PasajeroControlador {
    private final Scanner scanner = new Scanner(System.in);
    private final PasajeroUseCase pasajeroUseCase;
    private final ClienteRepository clienteRepo;

    public PasajeroControlador(PasajeroUseCase pasajeroUseCase, ClienteRepository clienteRepo) {
        this.pasajeroUseCase = pasajeroUseCase;
        this.clienteRepo = clienteRepo;
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
                    crearPasajero();
                    break;
                case 2:
                    cleanScreen();
                    listarTodosLosPasajeros();
                    break;
                case 3:
                    cleanScreen();
                    buscarPasajeroPorId();
                    break;
                case 4:
                    cleanScreen();
                    actualizarPasajero();
                    break;
                case 5:
                    cleanScreen();
                    eliminarPasajero();
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
        System.out.println("---- Menu de Opciones de Pasajero ----");
        System.out.println("1. Crear Pasajero");
        System.out.println("2. Listar Todos los Pasajeros");
        System.out.println("3. Buscar Pasajero por ID");
        System.out.println("4. Actualizar Pasajero");
        System.out.println("5. Eliminar Pasajero");
        System.out.println("6. Salir");
        System.out.println("Seleccione una opción:");
    }

    public void crearPasajero() {
        System.out.println("Ingrese el ID del Cliente:");
        Long clienteId = scanner.nextLong();
        scanner.nextLine(); // Consume newline character

        Cliente cliente = clienteRepo.obtenerClientePorId(clienteId);
        if (cliente == null) {
            System.out.println("Cliente no encontrado. Por favor, intente de nuevo.");
            return;
        }

        System.out.println("Ingrese el nombre del Pasajero:");
        String nombre = scanner.nextLine();

        System.out.println("Ingrese el apellido del Pasajero:");
        String apellido = scanner.nextLine();

        Pasajero nuevoPasajero = new Pasajero();
        nuevoPasajero.setCliente(cliente);
        nuevoPasajero.setNombre(nombre);
        nuevoPasajero.setApellido(apellido);

        pasajeroUseCase.crearPasajero(nuevoPasajero);
        System.out.println("Pasajero Creado");
    }

    private void listarTodosLosPasajeros() {
        List<Pasajero> pasajeros = pasajeroUseCase.obtenerTodosLosPasajeros();
        if (pasajeros.isEmpty()) {
            System.out.println("No hay pasajeros registrados.");
        } else {
            for (Pasajero pasajero : pasajeros) {
                System.out.printf("ID: %d, Cliente ID: %d, Nombre: %s, Apellido: %s%n",
                        pasajero.getId(),
                        pasajero.getCliente().getId(),
                        pasajero.getNombre(),
                        pasajero.getApellido());
            }
        }
    }

    private void buscarPasajeroPorId() {
        System.out.println("Ingrese el ID del Pasajero:");
        Long id = scanner.nextLong();
        scanner.nextLine(); // Consume newline character

        Pasajero pasajero = pasajeroUseCase.obtenerPasajeroPorId(id);
        if (pasajero != null) {
            System.out.printf("ID: %d, Cliente ID: %d, Nombre: %s, Apellido: %s%n",
                    pasajero.getId(),
                    pasajero.getCliente().getId(),
                    pasajero.getNombre(),
                    pasajero.getApellido());
        } else {
            System.out.println("No se encontró un pasajero con el ID proporcionado.");
        }
    }

    private void actualizarPasajero() {
        System.out.println("Ingrese el ID del Pasajero:");
        Long id = scanner.nextLong();
        scanner.nextLine(); // Consume newline character

        Pasajero pasajero = pasajeroUseCase.obtenerPasajeroPorId(id);
        if (pasajero != null) {
            System.out.println("Ingrese el nuevo nombre del Pasajero:");
            String nuevoNombre = scanner.nextLine();
            pasajero.setNombre(nuevoNombre);

            System.out.println("Ingrese el nuevo apellido del Pasajero:");
            String nuevoApellido = scanner.nextLine();
            pasajero.setApellido(nuevoApellido);

            System.out.println("Ingrese el nuevo ID del Cliente:");
            Long nuevoClienteId = scanner.nextLong();
            scanner.nextLine(); // Consume newline character

            Cliente nuevoCliente = clienteRepo.obtenerClientePorId(nuevoClienteId);
            if (nuevoCliente != null) {
                pasajero.setCliente(nuevoCliente);
            } else {
                System.out.println("Cliente no encontrado. Se mantendrá el cliente actual.");
            }

            pasajeroUseCase.actualizarPasajero(pasajero);
            System.out.println("Pasajero Actualizado");
        } else {
            System.out.println("No se encontró un pasajero con el ID proporcionado.");
        }
    }

    private void eliminarPasajero() {
        System.out.println("Ingrese el ID del Pasajero:");
        Long id = scanner.nextLong();
        scanner.nextLine(); // Consume newline character

        Pasajero pasajero = pasajeroUseCase.obtenerPasajeroPorId(id);
        if (pasajero != null) {
            pasajeroUseCase.eliminarPasajero(id);
            System.out.println("Pasajero Eliminado");
        } else {
            System.out.println("No se encontró un pasajero con el ID proporcionado.");
        }
    }
}
