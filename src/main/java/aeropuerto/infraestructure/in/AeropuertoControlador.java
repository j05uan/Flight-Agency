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

    private AeropueroUseCase aeropueroUseCase;
    private ActualizarAeropuerto actualizarAeropuerto;
    private EliminarAeropuertoUseCase eliminarAeropuertoUseCase;
    private CiudadRepository ciudadRepository = new CiudadRepository();
    private Scanner scanner;

    public AeropuertoControlador(ActualizarAeropuerto actualizarAeropuerto, AeropueroUseCase aeropueroUseCase, EliminarAeropuertoUseCase eliminarAeropuertoUseCase) {
        this.actualizarAeropuerto = actualizarAeropuerto;
        this.aeropueroUseCase = aeropueroUseCase;
        this.eliminarAeropuertoUseCase = eliminarAeropuertoUseCase;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean salir = false;

        while (!salir) {
            System.out.println("Seleccione la opción:");
            System.out.println("1. Crear Aeropuerto");
            System.out.println("2. Actualizar Aeropuerto");
            System.out.println("3. Eliminar Aeropuerto");
            System.out.println("4. Salir");

            try {
                int opcion = Integer.parseInt(scanner.nextLine());

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

    private void crearAeropuerto() {
        System.out.println("--- Menú Crear Aeropuerto ---");
        System.out.println("Ingrese el nombre del aeropuerto:");
        String nombre = scanner.nextLine();

        // Obtener la lista de ciudades disponibles
        List<Ciudad> ciudades = ciudadRepository.obtenerTodasLasCiudades();

        // Mostrar las ciudades disponibles para que el usuario elija
        System.out.println("Seleccione la ciudad para el aeropuerto:");
        for (Ciudad ciudad : ciudades) {
            System.out.println(ciudad.getId() + " | " + ciudad.getNombre());
        }

        System.out.println("Ingrese el ID de la ciudad:");
        Long ciudadId = Long.parseLong(scanner.nextLine());

        // Buscar la ciudad seleccionada por el usuario
        Ciudad ciudadSeleccionada = ciudades.stream()
            .filter(ciudad -> ciudad.getId().equals(ciudadId))
            .findFirst()
            .orElse(null);

        if (ciudadSeleccionada == null) {
            System.out.println("Ciudad no encontrada.");
            return;
        }

        // Crear el aeropuerto
        Aeropuerto aeropuerto = new Aeropuerto();
        aeropuerto.setNombre(nombre);
        aeropuerto.setCiudad(ciudadSeleccionada);
        aeropueroUseCase.crearAeropuerto(aeropuerto);
        System.out.println("Aeropuerto creado con éxito.");
    }

    private void actualizarAeropuerto() {
        System.out.println("--- Menú Actualizar Aeropuerto ---");
        System.out.println("Ingrese el ID del aeropuerto que desea actualizar:");
        int id = Integer.parseInt(scanner.nextLine());

        List<Aeropuerto> aeropuertoExistente = aeropueroUseCase.obtenerTodosLosAeropuertos();

        System.out.println("Ingrese el nuevo nombre del aeropuerto:");
        String nuevoNombre = scanner.nextLine();
        System.out.println("Ingrese el ID de la ciudad:");
        Long ciudadId = Long.parseLong(scanner.nextLine());

        // Buscar la ciudad seleccionada por el usuario
        List<Ciudad> ciudades = ciudadRepository.obtenerTodasLasCiudades();

        for (Ciudad ciudad : ciudades) {
            System.out.println(ciudad.getId() + " | " + ciudad.getNombre());
        }
        Ciudad ciudadSeleccionada = ciudades.stream()
            .filter(ciudad -> ciudad.getId().equals(ciudadId))
            .findFirst()
            .orElse(null);

        if (ciudadSeleccionada == null) {
            System.out.println("Ciudad no encontrada.");
            return;
        }
        try {
            Aeropuerto aeropuerto = new Aeropuerto();
            aeropuerto.setNombre(nuevoNombre);
            aeropuerto.setCiudad(ciudadSeleccionada);
            System.out.println("Aeropuerto actualizado con éxito.");
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error: " + e.getMessage());
        }
    }

    private void eliminarAeropuerto() {
        System.out.println("--- Menú Eliminar Aeropuerto ---");
        System.out.println("Ingrese el ID del aeropuerto que desea eliminar:");
        Long id = Long.parseLong(scanner.nextLine());

        try {
            eliminarAeropuertoUseCase.execute(id);
            System.out.println("Aeropuerto eliminado con éxito.");
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error: " + e.getMessage());
        }
    }

//     private void encontrarAeropuerto() {
//         System.out.println("--- Menú Encontrar Aeropuerto ---");
//         System.out.println("Ingrese el ID del aeropuerto que desea encontrar:");
//         Long id = Long.parseLong(scanner.nextLine());

//         try {
//             Aeropuerto aeropuerto = encontrarAeropuertoUseCase.executed(id);
//             if (aeropuerto != null) {
//                 System.out.println("Detalles del Aeropuerto:");
//                 System.out.println("ID: " + aeropuerto.getId());
//                 System.out.println("Nombre: " + aeropuerto.getNombre());
//                 System.out.println("Ciudad: " + aeropuerto.getCiudad().getNombre());
//             } else {
//                 System.out.println("Aeropuerto no encontrado.");
//             }
//         } catch (Exception e) {
//             System.out.println("Ha ocurrido un error: " + e.getMessage());
//         }
//     }
}
