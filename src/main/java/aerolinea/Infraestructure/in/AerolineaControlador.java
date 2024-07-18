package aerolinea.Infraestructure.in;

import java.util.List;
import java.util.Scanner;

import aerolinea.Domain.Entity.Aerolinea;
import aerolinea.application.AerolineaUseCase;
import static utils.Consola.cleanScreen;

public class AerolineaControlador {

    private final Scanner scanner = new Scanner(System.in);
    private final AerolineaUseCase aerolineaUseCase;

    public AerolineaControlador(AerolineaUseCase aerolineaUseCase) {
        this.aerolineaUseCase = aerolineaUseCase;
    }

    public void start() {
        boolean salirMenuAerolinea = false;
        while (!salirMenuAerolinea) {
            mostrarMenuAerolinea();
            try {
                int seleccionAereolinea = Integer.parseInt(scanner.nextLine());
                switch (seleccionAereolinea) {
                    case 1:
                        cleanScreen();
                        crearAerolinea();
                        break;
                    case 2:
                        cleanScreen();
                        listarAerolineas();
                        break;
                    case 3:
                        cleanScreen();
                        actualizarAerolinea();
                        break;
                    case 4:
                        cleanScreen();
                        eliminarAerolinea();
                        break;
                    case 5:
                        cleanScreen();
                        salirMenuAerolinea = true;
                        System.out.println("Volviendo al menú principal...");
                        break;
                    default:
                        System.out.println("Opción no válida. Por favor, elija una opción entre 1 y 5.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número entero.");
            } catch (Exception e) {
                System.out.println("Ha ocurrido un error: " + e.getMessage());
            }
        }
        scanner.close(); 
    }

    private void mostrarMenuAerolinea() {
        System.out.println("--- Menú Aerolinea ---");
        System.out.println("Seleccione una opción:");
        System.out.println("1. Crear Aerolinea");
        System.out.println("2. Listar Aerolineas");
        System.out.println("3. Actualizar Aerolinea");
        System.out.println("4. Eliminar Aerolinea");
        System.out.println("5. Volver");
    }

    private void crearAerolinea() {
        System.out.println("---- Crear Aerolínea ----");
        System.out.print("Ingrese el nombre de la Aerolínea: ");
        String nombre = scanner.nextLine().trim();

        if (nombre.isEmpty()) {
            System.out.println("El nombre de la aerolínea no puede estar vacío.");
            return;
        }
        if (!nombre.matches("[a-zA-Z\\s]+")) {
            System.out.println("El nombre de la aerolínea solo puede contener letras y espacios.");
            return;
        }

        Aerolinea aerolinea = new Aerolinea();
        aerolinea.setNombre(nombre);

        aerolineaUseCase.crearAerolinea(aerolinea);

        System.out.println("Aerolínea creada con éxito.");
    }

    private void listarAerolineas() {
        System.out.println("---- Listado de Aerolíneas ----");
        List<Aerolinea> aerolineas = aerolineaUseCase.obtenerTodasLasAerolineas();

        if (aerolineas.isEmpty()) {
            System.out.println("No hay aerolíneas registradas.");
        } else {
            for (Aerolinea aerolinea : aerolineas) {
                System.out.printf("ID: %d, Nombre: %s%n", aerolinea.getId(), aerolinea.getNombre());
            }
        }
    }

    private void actualizarAerolinea() {
        System.out.println("---- Actualizar Aerolínea ----");

        
        listarAerolineas();

        System.out.print("Ingrese el ID de la Aerolínea que desea actualizar: ");
        Long id;
        try {
            id = Long.parseLong(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("ID inválido. Por favor, ingrese un número entero.");
            return;
        }

        Aerolinea aerolineaExistente = aerolineaUseCase.obtenerAerolineaPorId(id);

        if (aerolineaExistente != null) {
            System.out.print("Ingrese el nuevo nombre de la Aerolínea: ");
            String nuevoNombre = scanner.nextLine().trim();

            if (nuevoNombre.isEmpty()) {
                System.out.println("El nombre de la aerolínea no puede estar vacío.");
                return;
            }

            // Validar que el nuevo nombre no contenga caracteres no permitidos
            if (!nuevoNombre.matches("[a-zA-Z\\s]+")) {
                System.out.println("El nombre de la aerolínea solo puede contener letras y espacios.");
                return;
            }

            aerolineaExistente.setNombre(nuevoNombre);

            aerolineaUseCase.actualizarAerolinea(aerolineaExistente);

            System.out.println("Aerolínea actualizada con éxito.");
        } else {
            System.out.println("No se encontró ninguna aerolínea con el ID proporcionado.");
        }
    }

    private void eliminarAerolinea() {
        System.out.println("---- Eliminar Aerolínea ----");
        System.out.print("Ingrese el ID de la aerolínea que desea eliminar: ");
        Long id;
        try {
            id = Long.parseLong(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("ID inválido. Por favor, ingrese un número entero.");
            return;
        }

        // Verificar si la aerolínea existe antes de eliminar
        Aerolinea aerolineaExistente = aerolineaUseCase.obtenerAerolineaPorId(id);
        if (aerolineaExistente != null) {
            aerolineaUseCase.eliminarAerolinea(id);
            System.out.println("Aerolínea eliminada con éxito.");
        } else {
            System.out.println("No se encontró ninguna aerolínea con el ID proporcionado.");
        }
    }
}
