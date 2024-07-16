package tipoEmpleado.infraestructure.in;

import java.util.List;
import java.util.Scanner;

import tipoEmpleado.Application.TipoEmpleadoUseCase;
import tipoEmpleado.Domain.entity.TipoEmpleado;

public class TipoEmpleadoControlador {
     private final TipoEmpleadoUseCase tipoEmpleadoUseCase;
    private final Scanner scanner;

    public TipoEmpleadoControlador(TipoEmpleadoUseCase tipoEmpleadoUseCase) {
        this.tipoEmpleadoUseCase = tipoEmpleadoUseCase;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean salir = false;

        while (!salir) {
            mostrarMenu();
            try {
                int opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1:
                        crearTipoEmpleado();
                        break;
                    case 2:
                        obtenerTodosLosTiposEmpleado();
                        break;
                    case 3:
                        obtenerTipoEmpleadoPorId();
                        break;
                    case 4:
                        actualizarTipoEmpleado();
                        break;
                    case 5:
                        eliminarTipoEmpleado();
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

    private void mostrarMenu() {
        System.out.println("Seleccione la opción:");
        System.out.println("1. Crear Tipo de Empleado");
        System.out.println("2. Obtener Todos los Tipos de Empleado");
        System.out.println("3. Obtener Tipo de Empleado por ID");
        System.out.println("4. Actualizar Tipo de Empleado");
        System.out.println("5. Eliminar Tipo de Empleado");
        System.out.println("6. Salir");
    }

    private void crearTipoEmpleado() {
        System.out.println("--- Menú Crear Tipo de Empleado ---");
        System.out.print("Ingrese el nombre del tipo de empleado: ");
        String nombre = scanner.nextLine();
        Long id = (long) (tipoEmpleadoUseCase.obtenerTodosLosTiposEmpleado().size() + 1);  // Generador simple de ID
        TipoEmpleado tipoEmpleado = new TipoEmpleado();
        tipoEmpleadoUseCase.crearTipoEmpleado(tipoEmpleado);
        System.out.println("Tipo de Empleado creado con éxito.");
    }

    private void obtenerTodosLosTiposEmpleado() {
        System.out.println("--- Menú Obtener Todos los Tipos de Empleado ---");
        List<TipoEmpleado> tiposEmpleado = tipoEmpleadoUseCase.obtenerTodosLosTiposEmpleado();
        if (tiposEmpleado.isEmpty()) {
            System.out.println("No hay tipos de empleado disponibles.");
        } else {
            tiposEmpleado.forEach(System.out::println);
        }
    }

    private void obtenerTipoEmpleadoPorId() {
        System.out.println("--- Menú Obtener Tipo de Empleado por ID ---");
        System.out.print("Ingrese el ID del tipo de empleado: ");
        try {
            Long id = Long.parseLong(scanner.nextLine());
            TipoEmpleado tipoEmpleado = tipoEmpleadoUseCase.obtenerTipoEmpleadoPorId(id);
            if (tipoEmpleado != null) {
                System.out.println(tipoEmpleado);
            } else {
                System.out.println("Tipo de Empleado no encontrado.");
            }
        } catch (NumberFormatException e) {
            System.out.println("ID inválido. Por favor, ingrese un número entero.");
        }
    }

    private void actualizarTipoEmpleado() {
        System.out.println("--- Menú Actualizar Tipo de Empleado ---");
        System.out.print("Ingrese el ID del tipo de empleado que desea actualizar: ");
        try {
            Long id = Long.parseLong(scanner.nextLine());
            TipoEmpleado tipoEmpleado = tipoEmpleadoUseCase.obtenerTipoEmpleadoPorId(id);
            if (tipoEmpleado != null) {
                System.out.print("Ingrese el nuevo nombre del tipo de empleado: ");
                String nuevoNombre = scanner.nextLine();
                tipoEmpleado.setTipo(nuevoNombre);
                tipoEmpleadoUseCase.actualizarTipoEmpleado(tipoEmpleado);
                System.out.println("Tipo de Empleado actualizado con éxito.");
            } else {
                System.out.println("Tipo de Empleado no encontrado.");
            }
        } catch (NumberFormatException e) {
            System.out.println("ID inválido. Por favor, ingrese un número entero.");
        }
    }

    private void eliminarTipoEmpleado() {
        System.out.println("--- Menú Eliminar Tipo de Empleado ---");
        System.out.print("Ingrese el ID del tipo de empleado que desea eliminar: ");
        try {
            Long id = Long.parseLong(scanner.nextLine());
            tipoEmpleadoUseCase.eliminarTipoEmpleado(id);
            System.out.println("Tipo de Empleado eliminado con éxito.");
        } catch (NumberFormatException e) {
            System.out.println("ID inválido. Por favor, ingrese un número entero.");
        }
    }
}
