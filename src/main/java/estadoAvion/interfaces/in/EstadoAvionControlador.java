package estadoAvion.interfaces.in;

import estadoAvion.Domain.entity.EstadoAvion;
import estadoAvion.Domain.services.EstadoAvionServices;
import estadoAvion.interfaces.out.EstadoAvionRepository;
import utils.Consola;

import java.util.List;
import java.util.Scanner;

public class EstadoAvionControlador {
    private final Scanner scanner = new Scanner(System.in);
    private final EstadoAvionServices estadoAvionServices;

    public EstadoAvionControlador(EstadoAvionServices estadoAvionServices) {
        this.estadoAvionServices = estadoAvionServices;
    }

    public void start() {
        int opcion;

        do {
            mostrarMenu();
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (opcion) {
                case 1:
                    crearEstadoAvion();
                    break;
                case 2:
                    obtenerTodosLosEstadoAvions();
                    break;
                case 3:
                    obtenerEstadoAvionPorId();
                    break;
                case 4:
                    actualizarEstadoAvion();
                    break;
                case 5:
                    eliminarEstadoAvion();
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
        System.out.println("---- Menu de Opciones de Estado de Avión ----");
        System.out.println("1. Crear Estado de Avión");
        System.out.println("2. Listar Todos los Estados de Avión");
        System.out.println("3. Buscar Estado de Avión por ID");
        System.out.println("4. Actualizar Estado de Avión");
        System.out.println("5. Eliminar Estado de Avión");
        System.out.println("6. Salir");
        System.out.println("Seleccione una opción:");
    }

    public void crearEstadoAvion() {
        System.out.println("----- Crear Estado de Avión -----");

        System.out.println("Ingrese el estado del avión:");
        String estado = scanner.nextLine();

        EstadoAvion nuevoEstado = new EstadoAvion();
        nuevoEstado.setEstado(estado);

        estadoAvionServices.crearEstadoAvion(nuevoEstado);

        System.out.println("Estado de Avión creado con éxito. ID del estado: " + nuevoEstado.getId());
    }

    public void obtenerTodosLosEstadoAvions() {
        System.out.println("---- Listado de Estados de Avión ----");

        List<EstadoAvion> estadosAvion = estadoAvionServices.obtenerTodosLosEstadosAvion();

        if (!estadosAvion.isEmpty()) {
            for (EstadoAvion estadoAvion : estadosAvion) {
                System.out.printf("ID: %d, Estado: %s%n", estadoAvion.getId(), estadoAvion.getEstado());
            }
        } else {
            System.out.println("No hay estados de avión registrados.");
        }
    }

    public void obtenerEstadoAvionPorId() {
        System.out.println("---- Buscar Estado de Avión por ID ----");
        System.out.println("Ingrese el ID del Estado de Avión:");
        Long id = scanner.nextLong();
        scanner.nextLine();  // Consumir el salto de línea después de nextLong

        EstadoAvion estadoAvion = estadoAvionServices.obtenerEstadoAvionPorId(id);

        if (estadoAvion != null) {
            System.out.printf("Estado de Avión encontrado:%nID: %d, Estado: %s%n",
                    estadoAvion.getId(), estadoAvion.getEstado());
        } else {
            System.out.println("No se encontró ningún estado de avión con el ID proporcionado.");
        }
    }

    public void actualizarEstadoAvion() {
        System.out.println("---- Actualizar Estado de Avión ----");
        System.out.println("Ingrese el ID del Estado de Avión a actualizar:");
        Long id = scanner.nextLong();
        scanner.nextLine();  // Consumir el salto de línea después de nextLong

        EstadoAvion estadoAvion = estadoAvionServices.obtenerEstadoAvionPorId(id);

        if (estadoAvion != null) {
            // Mostrar información actual del estado de avión
            System.out.printf("Estado de Avión actual:%nID: %d, Estado: %s%n",
                    estadoAvion.getId(), estadoAvion.getEstado());

            // Pedir nuevo estado de avión
            System.out.println("Ingrese el nuevo estado del avión:");
            String nuevoEstado = scanner.nextLine();

            // Actualizar el estado de avión con el nuevo estado
            estadoAvion.setEstado(nuevoEstado);

            estadoAvionServices.actualizarEstadoAvion(estadoAvion);

            System.out.println("Estado de Avión actualizado correctamente.");
        } else {
            System.out.println("No se encontró ningún estado de avión con el ID proporcionado.");
        }
    }

    public void eliminarEstadoAvion() {
        System.out.println("---- Eliminar Estado de Avión ----");
        System.out.println("Ingrese el ID del Estado de Avión a eliminar:");
        Long id = scanner.nextLong();
        scanner.nextLine();  // Consumir el salto de línea después de nextLong

        EstadoAvion estadoAvion = estadoAvionServices.obtenerEstadoAvionPorId(id);

        if (estadoAvion != null) {
            estadoAvionServices.eliminarEstadoAvion(id);
            System.out.println("Estado de Avión eliminado correctamente.");
        } else {
            System.out.println("No se encontró ningún estado de avión con el ID proporcionado.");
        }
    }
}
