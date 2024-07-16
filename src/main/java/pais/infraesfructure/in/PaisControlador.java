package pais.infraesfructure.in;

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

    public void start() {
        boolean salir = false;

        while (!salir) {
            System.out.println("Seleccione la opción:");
            System.out.println("1. Crear País");
            System.out.println("2. Obtener Todos los Países");
            System.out.println("3. Obtener País por ID");
            System.out.println("4. Actualizar País");
            System.out.println("5. Eliminar País");
            System.out.println("6. Salir");

            try {
                int opcion = Integer.parseInt(scanner.nextLine());

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

        scanner.close();
    }

    private void crearPais() {
        System.out.println("--- Menú Crear País ---");
        System.out.println("Ingrese el nombre del país:");
        String nombre = scanner.nextLine();
        Long id = (long) (paisUseCase.obtenerTodosLosPaises().size() + 1);
        Pais pais = new Pais();
        paisUseCase.crearPais(pais);
        System.out.println("País creado con éxito.");
    }

    private void obtenerTodosLosPaises() {
        System.out.println("--- Menú Obtener Todos los Países ---");
        paisUseCase.obtenerTodosLosPaises().forEach(System.out::println);
    }

    private void obtenerPaisPorId() {
        System.out.println("--- Menú Obtener País por ID ---");
        System.out.println("Ingrese el ID del país:");
        Long id = Long.parseLong(scanner.nextLine());
        Pais pais = paisUseCase.obtenerPaisPorId(id);
        if (pais != null) {
            System.out.println(pais);
        } else {
            System.out.println("País no encontrado.");
        }
    }

    private void actualizarPais() {
        System.out.println("--- Menú Actualizar País ---");
        System.out.println("Ingrese el ID del país que desea actualizar:");
        Long id = Long.parseLong(scanner.nextLine());
        Pais pais = paisUseCase.obtenerPaisPorId(id);
        if (pais != null) {
            System.out.println("Ingrese el nuevo nombre del país:");
            String nuevoNombre = scanner.nextLine();
            pais.setNombre(nuevoNombre);
            paisUseCase.actualizarPais(pais);
            System.out.println("País actualizado con éxito.");
        } else {
            System.out.println("País no encontrado.");
        }
    }

    private void eliminarPais() {
        System.out.println("--- Menú Eliminar País ---");
        System.out.println("Ingrese el ID del país que desea eliminar:");
        Long id = Long.parseLong(scanner.nextLine());
        paisUseCase.eliminarPais(id);
        System.out.println("País eliminado con éxito.");
    }
}
