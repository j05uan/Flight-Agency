package tipoTarifa.infraestructure.in;

import java.util.Scanner;
import java.util.List;

import tipoTarifa.Application.TipoTarifaUseCase;
import tipoTarifa.Domain.entity.TipoTarifa;

public class TipoTarifaControlador {
    private final TipoTarifaUseCase tipoTarifaUseCase;
    private final Scanner scanner;

    public TipoTarifaControlador(TipoTarifaUseCase tipoTarifaUseCase) {
        this.tipoTarifaUseCase = tipoTarifaUseCase;
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
                        crearTipoTarifa();
                        break;
                    case 2:
                        obtenerTodosLosTiposTarifa();
                        break;
                    case 3:
                        obtenerTipoTarifaPorId();
                        break;
                    case 4:
                        actualizarTipoTarifa();
                        break;
                    case 5:
                        eliminarTipoTarifa();
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
        System.out.println("1. Crear Tipo de Tarifa");
        System.out.println("2. Obtener Todos los Tipos de Tarifa");
        System.out.println("3. Obtener Tipo de Tarifa por ID");
        System.out.println("4. Actualizar Tipo de Tarifa");
        System.out.println("5. Eliminar Tipo de Tarifa");
        System.out.println("6. Salir");
    }

    private void crearTipoTarifa() {
        System.out.println("--- Menú Crear Tipo de Tarifa ---");
        System.out.print("Ingrese el nombre del tipo de tarifa: ");
        String nombre = scanner.nextLine();
        TipoTarifa tipoTarifa = new TipoTarifa();
        tipoTarifa.setTipo(nombre);
        tipoTarifaUseCase.crearTipoTarifa(tipoTarifa);
        System.out.println("Tipo de Tarifa creado con éxito.");
    }

    private void obtenerTodosLosTiposTarifa() {
        System.out.println("--- Menú Obtener Todos los Tipos de Tarifa ---");
        List<TipoTarifa> tiposTarifa = tipoTarifaUseCase.obtenerTodosLosTiposTarifa();
        if (tiposTarifa.isEmpty()) {
            System.out.println("No hay tipos de tarifa disponibles.");
        } else {
            for (TipoTarifa tipo : tiposTarifa) {
                System.out.println(tipo);
            }
        }
    }

    private void obtenerTipoTarifaPorId() {
        System.out.println("--- Menú Obtener Tipo de Tarifa por ID ---");
        System.out.print("Ingrese el ID del tipo de tarifa: ");
        try {
            Long id = Long.parseLong(scanner.nextLine());
            TipoTarifa tipoTarifa = tipoTarifaUseCase.obtenerTipoTarifaPorId(id);
            if (tipoTarifa != null) {
                System.out.println(tipoTarifa);
            } else {
                System.out.println("Tipo de Tarifa no encontrado.");
            }
        } catch (NumberFormatException e) {
            System.out.println("ID inválido. Por favor, ingrese un número entero.");
        }
    }

    private void actualizarTipoTarifa() {
        System.out.println("--- Menú Actualizar Tipo de Tarifa ---");
        System.out.print("Ingrese el ID del tipo de tarifa que desea actualizar: ");
        try {
            Long id = Long.parseLong(scanner.nextLine());
            TipoTarifa tipoTarifa = tipoTarifaUseCase.obtenerTipoTarifaPorId(id);
            if (tipoTarifa != null) {
                System.out.print("Ingrese el nuevo nombre del tipo de tarifa: ");
                String nuevoNombre = scanner.nextLine();
                tipoTarifa.setTipo(nuevoNombre);
                tipoTarifaUseCase.actualizarTipoTarifa(tipoTarifa);
                System.out.println("Tipo de Tarifa actualizado con éxito.");
            } else {
                System.out.println("Tipo de Tarifa no encontrado.");
            }
        } catch (NumberFormatException e) {
            System.out.println("ID inválido. Por favor, ingrese un número entero.");
        }
    }

    private void eliminarTipoTarifa() {
        System.out.println("--- Menú Eliminar Tipo de Tarifa ---");
        System.out.print("Ingrese el ID del tipo de tarifa que desea eliminar: ");
        try {
            Long id = Long.parseLong(scanner.nextLine());
            tipoTarifaUseCase.eliminarTipoTarifa(id);
            System.out.println("Tipo de Tarifa eliminado con éxito.");
        } catch (NumberFormatException e) {
            System.out.println("ID inválido. Por favor, ingrese un número entero.");
        }
    }
}
