package asiento.infraestructure.in;

import java.util.List;
import java.util.Scanner;

import asiento.Applicacion.AsientoUseCase;
import asiento.Domain.Entity.Asiento;

public class AsientoControlador {
    private final AsientoUseCase asientoUseCase;
    private final Scanner scanner = new Scanner(System.in);

    public AsientoControlador(AsientoUseCase asientoUseCase) {
        this.asientoUseCase = asientoUseCase;
    }

    public void crearAsiento() {
        System.out.println("---- Crear Asiento ----");

        System.out.println("Ingrese el nombre del asiento: ");
        String nombre = scanner.nextLine();

        Asiento asiento = new Asiento();
        asiento.setNombre(nombre);

        asientoUseCase.crearAsiento(asiento);

        System.out.println("Asiento creado con éxito.");
    }

    public void listarAsientos() {
        System.out.println("---- Listado de Asientos ----");

        List<Asiento> asientos = asientoUseCase.obtenerTodasLasAsientos();

        if (asientos.isEmpty()) {
            System.out.println("No hay asientos registrados.");
        } else {
            for (Asiento asiento : asientos) {
                System.out.printf("ID: %d, Nombre: %s%n",
                        asiento.getId(), asiento.getNombre());
            }
        }
    }

    public void actualizarAsiento() {
        System.out.println("---- Actualizar Asiento ----");

        listarAsientos();

        System.out.println("Ingrese el ID del asiento que desea actualizar: ");
        Long id = Long.parseLong(scanner.nextLine());

        Asiento asientoExistente = asientoUseCase.obtenerAsientoPorId(id);

        if (asientoExistente != null) {

            System.out.println("Ingrese el nuevo nombre del asiento: ");
            String nuevoNombre = scanner.nextLine();

            asientoExistente.setNombre(nuevoNombre);

            asientoUseCase.actualizarAsiento(asientoExistente);

            System.out.println("Asiento actualizado con éxito.");
        } else {
            System.out.println("No se encontró ningún asiento con el ID proporcionado.");
        }
    }

    public void eliminarAsiento() {
        System.out.println("---- Eliminar Asiento ----");
        System.out.println("Ingrese el ID del asiento que desea eliminar:");
        Long id = Long.parseLong(scanner.nextLine());
        asientoUseCase.eliminarAsiento(id);
        System.out.println("Asiento eliminado con éxito.");
    }

    public void start() {
        boolean salir = false;
    
        while (!salir) {
            System.out.println("Seleccione la opción:");
            System.out.println("1. Crear Asiento");
            System.out.println("2. Listar Asientos");
            System.out.println("3. Actualizar Asiento");
            System.out.println("4. Eliminar Asiento");
            System.out.println("5. Salir");
    
            try {
                int opcion = Integer.parseInt(scanner.nextLine());
    
                switch (opcion) {
                    case 1:
                        crearAsiento();
                        break;
                    case 2:
                        listarAsientos();
                        break;
                    case 3:
                        actualizarAsiento();
                        break;
                    case 4:
                        eliminarAsiento();
                        break;
                    case 5:
                        salir = true;
                        System.out.println("Saliendo del sistema...");
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
}
