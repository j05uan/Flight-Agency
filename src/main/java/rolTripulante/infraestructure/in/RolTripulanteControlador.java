package rolTripulante.infraestructure.in;

import java.util.List;
import java.util.Scanner;

import pais.Domain.Entity.Pais;
import rolTripulante.Application.RolTripulnteUseCase;
import rolTripulante.Domain.entity.RolTripulante;
import rolTripulante.Domain.services.RolTripulanteServices;

public class RolTripulanteControlador {
    private final RolTripulnteUseCase rolTripulanteServices;
    private final Scanner scanner;

    public RolTripulanteControlador(RolTripulnteUseCase rolTripulnteUseCase, Scanner scanner) {
        this.rolTripulanteServices = rolTripulnteUseCase;
        this.scanner = scanner;
    }

    public void start() {
        boolean salir = false;

        while (!salir) {
            mostrarMenu();
            try {
                int opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1:
                        crearRolTripulante();
                        break;
                    case 2:
                        obtenerTodosLosRolTripulantes();
                        break;
                    case 3:
                        obtenerRolTripulantePorId();
                        break;
                    case 4:
                        actualizarRolTripulante();
                        break;
                    case 5:
                        eliminarRolTripulante();
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
        System.out.println("1. Crear Rol de Tripulante");
        System.out.println("2. Obtener Todos los Roles de Tripulantes");
        System.out.println("3. Obtener Rol de Tripulante por ID");
        System.out.println("4. Actualizar Rol de Tripulante");
        System.out.println("5. Eliminar Rol de Tripulante");
        System.out.println("6. Salir");
    }

    private void crearRolTripulante() {
        System.out.println("--- Menú Crear Rol de Tripulante ---");
        System.out.print("Ingrese el nombre del rol de tripulante: ");
        String rol = scanner.nextLine();

        RolTripulante rolTripulante = new RolTripulante();
        rolTripulante.setRol(rol);
        rolTripulanteServices.crearRolTripulante(rolTripulante);
        System.out.println("Rol de tripulante creado con éxito.");
    }

    private void obtenerTodosLosRolTripulantes() {
        System.out.println("--- Menú Obtener Todos los Roles de Tripulantes ---");
        List<RolTripulante> roles = rolTripulanteServices.obtenerTodosLosRolTripulantes();
        if (roles.isEmpty()) {
            System.out.println("No hay roles de tripulantes disponibles.");
        } else {
            roles.forEach(System.out::println);
        }
    }

    private void obtenerRolTripulantePorId() {
        System.out.println("--- Menú Obtener Rol de Tripulante por ID ---");
        System.out.print("Ingrese el ID del rol de tripulante: ");
        try {
            Long id = Long.parseLong(scanner.nextLine());
            RolTripulante rolTripulante = rolTripulanteServices.obtenerRolTripulantePorId(id);
            if (rolTripulante != null) {
                System.out.println(rolTripulante);
            } else {
                System.out.println("Rol de tripulante no encontrado.");
            }
        } catch (NumberFormatException e) {
            System.out.println("ID inválido. Por favor, ingrese un número entero.");
        }
    }

    private void actualizarRolTripulante() {
        System.out.println("--- Menú Actualizar Rol de Tripulante ---");
        System.out.print("Ingrese el ID del rol de tripulante que desea actualizar: ");
        try {
            Long id = Long.parseLong(scanner.nextLine());
            RolTripulante rolTripulante = rolTripulanteServices.obtenerRolTripulantePorId(id);
            if (rolTripulante != null) {
                System.out.print("Ingrese el nuevo nombre del rol de tripulante: ");
                String nuevoNombre = scanner.nextLine();
                rolTripulante.setRol(nuevoNombre);
                rolTripulanteServices.actualizarRolTripulante(rolTripulante);
                System.out.println("Rol de tripulante actualizado con éxito.");
            } else {
                System.out.println("Rol de tripulante no encontrado.");
            }
        } catch (NumberFormatException e) {
            System.out.println("ID inválido. Por favor, ingrese un número entero.");
        }
    }

    private void eliminarRolTripulante() {
        System.out.println("--- Menú Eliminar Rol de Tripulante ---");
        System.out.print("Ingrese el ID del rol de tripulante que desea eliminar: ");
        try {
            Long id = Long.parseLong(scanner.nextLine());
            rolTripulanteServices.eliminaRolTripulante(id);
            System.out.println("Rol de tripulante eliminado con éxito.");
        } catch (NumberFormatException e) {
            System.out.println("ID inválido. Por favor, ingrese un número entero.");
        }
    }
}
