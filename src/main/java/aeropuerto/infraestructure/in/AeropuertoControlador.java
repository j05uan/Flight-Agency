package aeropuerto.infraestructure.in;

import java.util.List;
import java.util.Scanner;

import aeropuerto.application.ActualizarAeropuerto;
import aeropuerto.application.AeropueroUseCase;
import aeropuerto.application.EliminarAeropuertoUseCase;
import aeropuerto.domain.entity.Aeropuerto;
import ciudad.Domain.Entity.Ciudad;
import ciudad.infraestructure.out.CiudadRepository;
import static utils.Consola.cleanScreen;

public class AeropuertoControlador {

    private final AeropueroUseCase aeropueroUseCase;
    private final ActualizarAeropuerto actualizarAeropuerto = new ActualizarAeropuerto(null); 
    private final EliminarAeropuertoUseCase eliminarAeropuertoUseCase = new EliminarAeropuertoUseCase(null);

   
    private final Scanner scanner = new Scanner(System.in);
    
    

    public AeropuertoControlador(AeropueroUseCase aeropueroUseCase) {
        this.aeropueroUseCase = aeropueroUseCase;
    }

    public void start() {
        boolean salir = false;

        while (!salir) {
            mostrarMenuAeropuerto();
            try {
                int opcion = Integer.parseInt(scanner.nextLine().trim());

                switch (opcion) {
                    case 1:
                        cleanScreen();
                        crearAeropuerto();
                        break;
                    case 2:
                        cleanScreen();
                        actualizarAeropuerto();
                        break;
                    case 3:
                        cleanScreen();
                        eliminarAeropuerto();
                        break;
                    case 4:
                        salir = true;
                        System.out.println("Saliendo del sistema...");
                        break;
                    default:
                        System.out.println("Opción no válida. Por favor, elija una opción entre 1 y 4.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número entero.");
            } catch (Exception e) {
                System.out.println("Ha ocurrido un error: " + e.getMessage());
            }
        }

        // Consider removing scanner close here if you plan to use it elsewhere
        // scanner.close(); 
    }

    private void mostrarMenuAeropuerto() {
        System.out.println("--- Menú Aeropuerto ---");
        System.out.println("Seleccione una opción:");
        System.out.println("1. Crear Aeropuerto");
        System.out.println("2. Actualizar Aeropuerto");
        System.out.println("3. Eliminar Aeropuerto");
        System.out.println("4. Salir");
    }

    private void crearAeropuerto() {
        System.out.println("--- Menú Crear Aeropuerto ---");
        System.out.print("Ingrese el nombre del aeropuerto: ");
        String nombre = scanner.nextLine().trim();

        if (nombre.isEmpty()) {
            System.out.println("El nombre del aeropuerto no puede estar vacío.");
            return;
        }

        List<Ciudad> ciudades = new CiudadRepository().obtenerTodasLasCiudades();

        System.out.println("Seleccione la ciudad para el aeropuerto:");
        for (Ciudad ciudad : ciudades) {
            System.out.println(ciudad.getId() + " | " + ciudad.getNombre());
        }

        System.out.print("Ingrese el ID de la ciudad: ");
        Long ciudadId;
        try {
            ciudadId = Long.parseLong(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("ID de ciudad inválido. Por favor, ingrese un número entero.");
            return;
        }

        Ciudad ciudadSeleccionada = ciudades.stream()
            .filter(ciudad -> ciudad.getId().equals(ciudadId))
            .findFirst()
            .orElse(null);

        if (ciudadSeleccionada == null) {
            System.out.println("Ciudad no encontrada.");
            return;
        }

        Aeropuerto aeropuerto = new Aeropuerto();
        aeropuerto.setNombre(nombre);
        aeropuerto.setCiudad(ciudadSeleccionada);
        aeropueroUseCase.crearAeropuerto(aeropuerto);

        System.out.println("Aeropuerto creado con éxito.");
    }

    private void actualizarAeropuerto() {
        System.out.println("--- Menú Actualizar Aeropuerto ---");
        System.out.println("Ingrese el ID del aeropuerto que desea actualizar:");
        Long id;
        try {
            id = Long.parseLong(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("ID inválido. Por favor, ingrese un número entero.");
            return;
        }

        Aeropuerto aeropuertoExistente = aeropueroUseCase.obtenerAeropuertoPorId(id);
        if (aeropuertoExistente == null) {
            System.out.println("Aeropuerto no encontrado.");
            return;
        }

        System.out.print("Ingrese el nuevo nombre del aeropuerto: ");
        String nuevoNombre = scanner.nextLine().trim();

        if (nuevoNombre.isEmpty()) {
            System.out.println("El nombre del aeropuerto no puede estar vacío.");
            return;
        }

        System.out.println("Ingrese el ID de la ciudad:");
        Long ciudadId;
        try {
            ciudadId = Long.parseLong(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("ID de ciudad inválido. Por favor, ingrese un número entero.");
            return;
        }

        List<Ciudad> ciudades = new CiudadRepository().obtenerTodasLasCiudades();
        Ciudad ciudadSeleccionada = ciudades.stream()
            .filter(ciudad -> ciudad.getId().equals(ciudadId))
            .findFirst()
            .orElse(null);

        if (ciudadSeleccionada == null) {
            System.out.println("Ciudad no encontrada.");
            return;
        }

        try {
            aeropuertoExistente.setNombre(nuevoNombre);
            aeropuertoExistente.setCiudad(ciudadSeleccionada);
            aeropueroUseCase.actualizarAeropuerto(aeropuertoExistente);

            System.out.println("Aeropuerto actualizado con éxito.");
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error: " + e.getMessage());
        }
    }

    private void eliminarAeropuerto() {
        System.out.println("--- Menú Eliminar Aeropuerto ---");
        System.out.print("Ingrese el ID del aeropuerto que desea eliminar: ");
        Long id;
        try {
            id = Long.parseLong(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("ID inválido. Por favor, ingrese un número entero.");
            return;
        }

        try {
            aeropueroUseCase.eliminarAeropuerto(id);
            System.out.println("Aeropuerto eliminado con éxito.");
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error: " + e.getMessage());
        }
    }
}
