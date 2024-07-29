package ciudad.infraestructure.in;

import java.util.List;
import java.util.Scanner;

import ciudad.Application.CiudadUseCase;
import ciudad.Domain.Entity.Ciudad;
import pais.Application.PaisUseCase;
import pais.Domain.Entity.Pais;

public class CiudadControlador {
    private final CiudadUseCase ciudadUseCase;
    private final PaisUseCase paisUseCase = new PaisUseCase(null) ;
    private final Scanner scanner = new Scanner(System.in);

   

    public CiudadControlador(CiudadUseCase ciudadUseCase) {
        this.ciudadUseCase = ciudadUseCase;
    }

    public void start() {
        boolean salir = false;

        while (!salir) {
            mostrarMenu();
            try {
                int opcion = Integer.parseInt(scanner.nextLine().trim());

                switch (opcion) {
                    case 1:
                        crearCiudad();
                        break;
                    case 2:
                        obtenerTodasLasCiudades();
                        break;
                    case 3:
                        obtenerCiudadPorId();
                        break;
                    case 4:
                        actualizarCiudad();
                        break;
                    case 5:
                        eliminarCiudad();
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
        
        // Cierre del scanner si es creado internamente
        // scanner.close();
    }

    private void mostrarMenu() {
        System.out.println("Seleccione la opción:");
        System.out.println("1. Crear Ciudad");
        System.out.println("2. Obtener Todas las Ciudades");
        System.out.println("3. Obtener Ciudad por ID");
        System.out.println("4. Actualizar Ciudad");
        System.out.println("5. Eliminar Ciudad");
        System.out.println("6. Salir");
    }

    private void crearCiudad() {
        System.out.println("--- Menú Crear Ciudad ---");
        System.out.print("Ingrese el nombre de la ciudad: ");
        String nombre = scanner.nextLine().trim();
        if (nombre.isEmpty()) {
            System.out.println("El nombre de la ciudad no puede estar vacío.");
            return;
        }

        List<Pais> paises = paisUseCase.obtenerTodosLosPaises();
        if (paises.isEmpty()) {
            System.out.println("No hay países disponibles. Cree al menos un país antes de crear una ciudad.");
            return;
        }

        Pais paisSeleccionado = seleccionarPais(paises);

        Long id = ciudadUseCase.obtenerTodasLasCiudades().stream()
                .map(Ciudad::getId)
                .max(Long::compareTo)
                .orElse(0L) + 1;

        Ciudad ciudad = new Ciudad();
        ciudad.setId(id);
        ciudad.setNombre(nombre);
        ciudad.setPais(paisSeleccionado);
        ciudadUseCase.crearCiudad(ciudad);
        System.out.println("Ciudad creada con éxito.");
    }

    private void obtenerTodasLasCiudades() {
        System.out.println("--- Menú Obtener Todas las Ciudades ---");
        List<Ciudad> ciudades = ciudadUseCase.obtenerTodasLasCiudades();
        if (ciudades.isEmpty()) {
            System.out.println("No hay ciudades disponibles.");
        } else {
            ciudades.forEach(System.out::println);
        }
    }

    private void obtenerCiudadPorId() {
        System.out.println("--- Menú Obtener Ciudad por ID ---");
        System.out.print("Ingrese el ID de la ciudad: ");
        Long id = leerLong();
        if (id == null) return;

        Ciudad ciudad = ciudadUseCase.obtenerCiudadPorId(id);
        if (ciudad != null) {
            System.out.println(ciudad);
        } else {
            System.out.println("Ciudad no encontrada.");
        }
    }

    private void actualizarCiudad() {
        System.out.println("--- Menú Actualizar Ciudad ---");
        System.out.print("Ingrese el ID de la ciudad que desea actualizar: ");
        Long id = leerLong();
        if (id == null) return;

        Ciudad ciudad = ciudadUseCase.obtenerCiudadPorId(id);
        if (ciudad != null) {
            System.out.print("Ingrese el nuevo nombre de la ciudad: ");
            String nuevoNombre = scanner.nextLine().trim();
            if (nuevoNombre.isEmpty()) {
                System.out.println("El nombre de la ciudad no puede estar vacío.");
                return;
            }

            Pais nuevoPais = seleccionarPais(paisUseCase.obtenerTodosLosPaises());
            ciudad.setNombre(nuevoNombre);
            ciudad.setPais(nuevoPais);
            ciudadUseCase.actualizarCiudad(ciudad);
            System.out.println("Ciudad actualizada con éxito.");
        } else {
            System.out.println("Ciudad no encontrada.");
        }
    }

    private void eliminarCiudad() {
        System.out.println("--- Menú Eliminar Ciudad ---");
        System.out.print("Ingrese el ID de la ciudad que desea eliminar: ");
        Long id = leerLong();
        if (id == null) return;

        Ciudad ciudad = ciudadUseCase.obtenerCiudadPorId(id);
        if (ciudad != null) {
            ciudadUseCase.eliminarCiudad(id);
            System.out.println("Ciudad eliminada con éxito.");
        } else {
            System.out.println("Ciudad no encontrada.");
        }
    }

    private Pais seleccionarPais(List<Pais> paises) {
        System.out.println("Seleccione un país:");
        for (int i = 0; i < paises.size(); i++) {
            System.out.println((i + 1) + ". " + paises.get(i).getNombre()); // Usa getNombre() o asegúrate de que toString() esté bien implementado
        }

        int paisIndex;
        try {
            paisIndex = Integer.parseInt(scanner.nextLine().trim()) - 1;
            if (paisIndex < 0 || paisIndex >= paises.size()) {
                throw new IndexOutOfBoundsException("Índice de país fuera de rango.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Por favor, ingrese un número entero.");
            return null;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Opción no válida. " + e.getMessage());
            return null;
        }

        return paises.get(paisIndex);
    }

    private Long leerLong() {
        try {
            return Long.parseLong(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("ID inválido. Por favor, ingrese un número entero.");
            return null;
        }
    }
}
