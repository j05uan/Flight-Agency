package fabricante.infraestructure.in;

import fabricante.Application.FabricanteUseCase;
import fabricante.Domain.Entity.Fabricante;
import utils.Consola;

import java.util.Scanner;

public class FabricanteControlador {

    private final FabricanteUseCase fabricanteUseCase;
    private final Scanner scanner;

    public FabricanteControlador(FabricanteUseCase fabricanteUseCase, Scanner scanner) {
        this.fabricanteUseCase = fabricanteUseCase;
        this.scanner = scanner;
    }

    public void start() {
        int opcion;

        do {
            mostrarMenu();
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (opcion) {
                case 1:
                    crearFabricante();
                    break;
                case 2:
                    obtenerTodosLosFabricantes();
                    break;
                case 3:
                    obtenerFabricantePorId();
                    break;
                case 4:
                    actualizarFabricante();
                    break;
                case 5:
                    eliminarFabricante();
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
        System.out.println("1. Crear Fabricante");
        System.out.println("2. Listar Todos los Fabricantes");
        System.out.println("3. Buscar Fabricante por ID");
        System.out.println("4. Actualizar Fabricante");
        System.out.println("5. Eliminar Fabricante");
        System.out.println("6. Salir");
        System.out.println("Seleccione una opción:");
    }

    public void crearFabricante() {
        System.out.println("---- Crear Fabricante ----");
        System.out.println("Ingrese el nombre del Fabricante:");
        String nombre = scanner.nextLine();

        Fabricante fabricante = new Fabricante();
        fabricante.setNombre(nombre);

        fabricanteUseCase.crearFabricante(fabricante);

        System.out.println("Fabricante creado correctamente.");
    }

    public void obtenerTodosLosFabricantes() {
        System.out.println("---- Listado de Fabricantes ----");

        fabricanteUseCase.ObtenerTodosLosFabricantes().forEach(fabricante -> {
            System.out.println("ID: " + fabricante.getId() + ", Nombre: " + fabricante.getNombre());
        });
    }

    public void obtenerFabricantePorId() {
        System.out.println("---- Buscar Fabricante por ID ----");
        System.out.println("Ingrese el ID del Fabricante:");
        Long id = scanner.nextLong();
        scanner.nextLine(); // Consume newline character

        Fabricante fabricante = fabricanteUseCase.obtenerFabricantePorId(id);

        if (fabricante != null) {
            System.out.println("Fabricante encontrado:");
            System.out.println("ID: " + fabricante.getId() + ", Nombre: " + fabricante.getNombre());
        } else {
            System.out.println("No se encontró ningún fabricante con el ID proporcionado.");
        }
    }

    public void actualizarFabricante() {
        System.out.println("---- Actualizar Fabricante ----");
        System.out.println("Ingrese el ID del Fabricante a actualizar:");
        Long id = scanner.nextLong();
        scanner.nextLine(); // Consume newline character

        Fabricante fabricante = fabricanteUseCase.obtenerFabricantePorId(id);

        if (fabricante != null) {
            System.out.println("Ingrese el nuevo nombre del Fabricante:");
            String nuevoNombre = scanner.nextLine();

            fabricante.setNombre(nuevoNombre);
            fabricanteUseCase.actualizarFabricante(fabricante);

            System.out.println("Fabricante actualizado correctamente.");
        } else {
            System.out.println("No se encontró ningún fabricante con el ID proporcionado.");
        }
    }

    public void eliminarFabricante() {
        System.out.println("---- Eliminar Fabricante ----");
        System.out.println("Ingrese el ID del Fabricante a eliminar:");
        Long id = scanner.nextLong();
        scanner.nextLine(); // Consume newline character

        Fabricante fabricante = fabricanteUseCase.obtenerFabricantePorId(id);

        if (fabricante != null) {
            fabricanteUseCase.eliminarFabricante(id);
            System.out.println("Fabricante eliminado correctamente.");
        } else {
            System.out.println("No se encontró ningún fabricante con el ID proporcionado.");
        }
    }
}
