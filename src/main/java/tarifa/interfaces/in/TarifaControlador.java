package tarifa.interfaces.in;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import tarifa.Application.TarifaUseCase;
import tarifa.Domain.entity.Tarifa;
import tipoTarifa.Application.TipoTarifaUseCase;
import tipoTarifa.Domain.entity.TipoTarifa;

public class TarifaControlador {
    private final TarifaUseCase tarifaUseCase;
    private final TipoTarifaUseCase tipoTarifaUseCase; // Para listar los tipos de tarifa
    private final Scanner scanner;

    public TarifaControlador(TarifaUseCase tarifaUseCase, TipoTarifaUseCase tipoTarifaUseCase) {
        this.tarifaUseCase = tarifaUseCase;
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
                        crearTarifa();
                        break;
                    case 2:
                        obtenerTodasLasTarifas();
                        break;
                    case 3:
                        obtenerTarifaPorId();
                        break;
                    case 4:
                        actualizarTarifa();
                        break;
                    case 5:
                        eliminarTarifa();
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
        System.out.println("1. Crear Tarifa");
        System.out.println("2. Obtener Todas las Tarifas");
        System.out.println("3. Obtener Tarifa por ID");
        System.out.println("4. Actualizar Tarifa");
        System.out.println("5. Eliminar Tarifa");
        System.out.println("6. Salir");
    }

    private void crearTarifa() {
        System.out.println("--- Menú Crear Tarifa ---");

        // Listar tipos de tarifa
        System.out.println("Seleccione un tipo de tarifa:");
        List<TipoTarifa> tiposTarifa = tipoTarifaUseCase.obtenerTodosLosTiposTarifa();
        for (TipoTarifa tipo : tiposTarifa) {
            System.out.println(tipo.getId() + ": " + tipo.getTipo());
        }

        System.out.print("Ingrese el ID del tipo de tarifa: ");
        Long tipoTarifaId = Long.parseLong(scanner.nextLine());
        TipoTarifa tipoTarifa = tipoTarifaUseCase.obtenerTipoTarifaPorId(tipoTarifaId);

        if (tipoTarifa == null) {
            System.out.println("Tipo de tarifa no encontrado.");
            return;
        }

        System.out.print("Ingrese el valor de la tarifa: ");
        BigDecimal valor = new BigDecimal(scanner.nextLine());

        
        Tarifa tarifa = new Tarifa();
        tarifa.setId((long) (tarifaUseCase.obtenerTodasLasTarifas().size() + 1)); 
        tarifa.setTipoTarifa(tipoTarifa);
        tarifa.setValor(valor);
        tarifa.setHistorialTarifas(Set.of()); 

        tarifaUseCase.crearTarifa(tarifa);
        System.out.println("Tarifa creada con éxito.");
    }

    private void obtenerTodasLasTarifas() {
        System.out.println("--- Menú Obtener Todas las Tarifas ---");
        List<Tarifa> tarifas = tarifaUseCase.obtenerTodasLasTarifas();
        if (tarifas.isEmpty()) {
            System.out.println("No hay tarifas disponibles.");
        } else {
            tarifas.forEach(System.out::println);
        }
    }

    private void obtenerTarifaPorId() {
        System.out.println("--- Menú Obtener Tarifa por ID ---");
        System.out.print("Ingrese el ID de la tarifa: ");
        Long id = Long.parseLong(scanner.nextLine());
        Tarifa tarifa = tarifaUseCase.obtenerTarifaPorId(id);
        if (tarifa != null) {
            System.out.println(tarifa);
        } else {
            System.out.println("Tarifa no encontrada.");
        }
    }

    private void actualizarTarifa() {
        System.out.println("--- Menú Actualizar Tarifa ---");
        System.out.print("Ingrese el ID de la tarifa que desea actualizar: ");
        Long id = Long.parseLong(scanner.nextLine());
        Tarifa tarifa = tarifaUseCase.obtenerTarifaPorId(id);
        if (tarifa != null) {
            System.out.println("Ingrese el nuevo valor de la tarifa: ");
            BigDecimal nuevoValor = new BigDecimal(scanner.nextLine());
            tarifa.setValor(nuevoValor);
            tarifaUseCase.actualizarTarifa(tarifa);
            System.out.println("Tarifa actualizada con éxito.");
        } else {
            System.out.println("Tarifa no encontrada.");
        }
    }

    private void eliminarTarifa() {
        System.out.println("--- Menú Eliminar Tarifa ---");
        System.out.print("Ingrese el ID de la tarifa que desea eliminar: ");
        Long id = Long.parseLong(scanner.nextLine());
        tarifaUseCase.eliminarTarifa(id);
        System.out.println("Tarifa eliminada con éxito.");
    }
}
