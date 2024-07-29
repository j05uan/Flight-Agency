package pais.infraesfructure.in;

import static utils.Consola.*;

import java.util.List;
import java.util.Scanner;
import pais.Application.PaisUseCase;
import pais.Domain.Entity.Pais;

public class PaisControlador {
    private final PaisUseCase paisUseCase;
    private final Scanner scanner;

    public PaisControlador(PaisUseCase paisUseCase) {
        this.paisUseCase = paisUseCase;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        start();
    }

    public void start() {
        boolean salir = false;

        while (!salir) {
            mostrarMenu();
            try {
                int opcion = Integer.parseInt(scanner.nextLine().trim());

                switch (opcion) {
                    case 1:
                        crearPais();
                        break;
                    case 2:
                        obtenerTodosLosPaises();
                        break;
                    case 3:
                        obtenerPaisPorId();
                        break;
                    case 4:
                        actualizarPais();
                        break;
                    case 5:
                        eliminarPais();
                        break;
                    case 6:
                        cleanScreen();
                        salir = true;
                        System.out.println("Saliendo del sistema...");
                        break;
                    default:
                        System.out.println("Opción no válida. Por favor, elija una opción entre 1 y 6.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número entero.");
            } catch (Exception e) {
                System.out.println("Ha ocurrido un error: " + e.getMessage());
            }
        }

        
    }

    private void mostrarMenu() {
        System.out.println("Seleccione la opción:");
        System.out.println("1. Crear País");
        System.out.println("2. Obtener Todos los Países");
        System.out.println("3. Obtener País por ID");
        System.out.println("4. Actualizar País");
        System.out.println("5. Eliminar País");
        System.out.println("6. Salir");
    }

    private void crearPais() {
        System.out.println("--- Menú Crear País ---");
        System.out.print("Ingrese el nombre del país: ");
        String nombre = scanner.nextLine().trim();
        if (nombre.isEmpty()) {
            System.out.println("El nombre del país no puede estar vacío.");
            return;
        }

        Long id = generarNuevoId();
        Pais pais = new Pais();
        pais.setId(id);
        pais.setNombre(nombre);
        paisUseCase.crearPais(pais);
        System.out.println("País creado con éxito.");
    }

    private Long generarNuevoId() {
        List<Pais> paises = paisUseCase.obtenerTodosLosPaises();
        return paises.isEmpty() ? 1 : paises.get(paises.size() - 1).getId() + 1;
    }

    private void obtenerTodosLosPaises() {
        System.out.println("--- Menú Obtener Todos los Países ---");
        List<Pais> paises = paisUseCase.obtenerTodosLosPaises();
        if (paises.isEmpty()) {
            System.out.println("No hay países registrados.");
        } else {
            paises.forEach(System.out::println);
        }
    }

    private void obtenerPaisPorId() {
        System.out.println("--- Menú Obtener País por ID ---");
        System.out.print("Ingrese el ID del país: ");
        Long id;
        try {
            id = Long.parseLong(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("ID inválido. Por favor, ingrese un número entero.");
            return;
        }

        Pais pais = paisUseCase.obtenerPaisPorId(id);
        if (pais != null) {
            System.out.println(pais);
        } else {
            System.out.println("País no encontrado.");
        }
    }

    private void actualizarPais() {
        System.out.println("--- Menú Actualizar País ---");
        System.out.print("Ingrese el ID del país que desea actualizar: ");
        Long id;
        try {
            id = Long.parseLong(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("ID inválido. Por favor, ingrese un número entero.");
            return;
        }

        Pais pais = paisUseCase.obtenerPaisPorId(id);
        if (pais != null) {
            System.out.print("Ingrese el nuevo nombre del país: ");
            String nuevoNombre = scanner.nextLine().trim();
            if (nuevoNombre.isEmpty()) {
                System.out.println("El nombre del país no puede estar vacío.");
                return;
            }
            pais.setNombre(nuevoNombre);
            paisUseCase.actualizarPais(pais);
            System.out.println("País actualizado con éxito.");
        } else {
            System.out.println("País no encontrado.");
        }
    }

    private void eliminarPais() {
        System.out.println("--- Menú Eliminar País ---");
        System.out.print("Ingrese el ID del país que desea eliminar: ");
        Long id;
        try {
            id = Long.parseLong(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("ID inválido. Por favor, ingrese un número entero.");
            return;
        }

        Pais pais = paisUseCase.obtenerPaisPorId(id);
        if (pais != null) {
            paisUseCase.eliminarPais(id);
            System.out.println("País eliminado con éxito.");
        } else {
            System.out.println("País no encontrado.");
        }
    }
}
