package com.aerolinea;

import java.sql.SQLException;
import java.util.Scanner;

import aerolinea.Domain.Services.AerolineaServices;
import aerolinea.Infraestructure.in.AerolineaControlador;
import aerolinea.Infraestructure.out.AerolineaRepository;
import aerolinea.application.AerolineaUseCase;
import ciudad.Application.CiudadUseCase;
import ciudad.Domain.Services.CiudadServices;
import ciudad.infraestructure.in.CiudadControlador;
import resource.ConfiguracionBaseDeDatos;
import static utils.Consola.cleanScreen;

public class Main {
    public static void main(String[] args) {
        try {
            // Establece la conexión a la base de datos
            ConfiguracionBaseDeDatos.getConnection();

            // Inicializa los servicios y controladores
            AerolineaServices aerolineaServices = new AerolineaRepository();
            AerolineaUseCase aerolineaUseCase = new AerolineaUseCase(aerolineaServices);
            AerolineaControlador consolaAdaptador = new AerolineaControlador(aerolineaUseCase);
            CiudadServices ciudadServices = new CiudadServices();
            CiudadUseCase ciudadUseCase = new CiudadUseCase(ciudadServices);
            CiudadControlador ciudadControlador = new  CiudadControlador(ciudadUseCase);
            
            // Llama al método de inicio para mostrar el menú
            inicio(consolaAdaptador);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("No se pudo conectar a la base de datos.");
        }
    }

    private static void inicio(AerolineaControlador consolaAdaptador) {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("--- Agencia Vuelos Globales ---");
            System.out.println("Bienvenidos");
            System.out.println("Seleccione una Opción:");
            System.out.println("1. Aeropuertos");
            System.out.println("2. Entidades");
            System.out.println("3. Reservas");
            System.out.println("4. Salir");

            try {
                int opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1:
                        cleanScreen();
                        mostrarMenuAeropuertos(scanner, consolaAdaptador);
                        break;
                    case 2:
                        cleanScreen();
                        mostrarMenuEntidades(scanner);
                        break;
                    case 3:
                        cleanScreen();
                        mostrarMenuReservas(scanner);
                        break;
                    case 4:
                        cleanScreen();
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
        scanner.close();
    }

    private static void mostrarMenuAeropuertos(Scanner scanner, AerolineaControlador consolaAdaptador) {
        boolean salirMenuAeropuertos = false;

        while (!salirMenuAeropuertos) {
            System.out.println("--- Menú Aeropuertos ---");
            System.out.println("Seleccione una opción:");
            System.out.println("1. Aerolinea");
            System.out.println("2. Localidad");
            System.out.println("3. Salidas Aeropuerto");
            System.out.println("4. Volver");

            try {
                int seleccion = Integer.parseInt(scanner.nextLine());

                switch (seleccion) {
                    case 1:
                        cleanScreen();
                        mostrarMenuAerolinea(scanner, consolaAdaptador);
                        break;
                    case 2:
                        cleanScreen();
                        mostrarMenuLocalidad(scanner);
                        break;
                    case 3:
                        cleanScreen();
                        // Implementar el menú de Salidas Aeropuerto
                        break;
                    case 4:
                        cleanScreen();
                        salirMenuAeropuertos = true;
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
    }

    private static void mostrarMenuAerolinea(Scanner scanner, AerolineaControlador consolaAdaptador) {
        boolean salirMenuAerolinea = false;

        while (!salirMenuAerolinea) {
            System.out.println("--- Menú Aerolinea ---");
            System.out.println("Seleccione una opción:");
            System.out.println("1. Crear Aerolinea");
            System.out.println("2. Listar Aerolineas");
            System.out.println("3. Actualizar Aerolinea");
            System.out.println("4. Eliminar Aerolinea");
            System.out.println("5. Volver");

            try {
                int seleccionAereolinea = Integer.parseInt(scanner.nextLine());

                switch (seleccionAereolinea) {
                    case 1:
                        cleanScreen();
                        consolaAdaptador.crearAerolinea();
                        break;
                    case 2:
                        cleanScreen();
                        consolaAdaptador.listarAerolineas();
                        break;
                    case 3:
                        cleanScreen();
                        consolaAdaptador.actualizarAerolinea();
                        break;
                    case 4:
                        cleanScreen();
                        consolaAdaptador.eliminarAerolinea();
                        break;
                    case 5:
                        cleanScreen();
                        salirMenuAerolinea = true;
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
    }

    private static void mostrarMenuLocalidad(Scanner scanner) {
        boolean salirMenuLocalidad = false;

        while (!salirMenuLocalidad) {
            System.out.println("--- Menú Localidad ---");
            System.out.println("Seleccione una opción:");
            System.out.println("1. Pais");
            System.out.println("2. Ciudad");
            System.out.println("3. Volver");

            try {
                int seleccionLocalidad = Integer.parseInt(scanner.nextLine());

                switch (seleccionLocalidad) {
                    case 1:
                        cleanScreen();
                        mostrarMenuPais(scanner);
                        break;
                    case 2:
                        cleanScreen();
                        mostrarMenuCiudad(scanner);
                        break;
                    case 3:
                        cleanScreen();
                        salirMenuLocalidad = true;
                        break;
                    default:
                        System.out.println("Opción no válida. Por favor, elija una opción entre 1 y 3.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número entero.");
            } catch (Exception e) {
                System.out.println("Ha ocurrido un error: " + e.getMessage());
            }
        }
    }
    private static void mostrarMenuPais(Scanner scanner) {
        boolean salirMenuPais = false;

        while (!salirMenuPais) {
            System.out.println("--- Menú Pais ---");
            System.out.println("Seleccione una opción:");
            System.out.println("1. Crear Pais");
            System.out.println("2. Listar Paises");
            System.out.println("3. Actualizar Pais");
            System.out.println("4. Eliminar Pais");
            System.out.println("5. Volver");

            try {
                int seleccionPais = Integer.parseInt(scanner.nextLine());

                switch (seleccionPais) {
                    case 1:
                        cleanScreen();

                        break;
                    case 2:
                        cleanScreen();
                        // Implementar método para listar Ciudades
                        break;
                    case 3:
                        cleanScreen();
                        // Implementar método para actualizar Ciudad
                        break;
                    case 4:
                        cleanScreen();
                        // Implementar método para eliminar Ciudad
                        break;
                    case 5:
                        cleanScreen();
                        salirMenuPais = true;
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
    }

    private static void mostrarMenuCiudad(Scanner scanner) {
        boolean salirMenuCiudad = false;

        while (!salirMenuCiudad) {
            System.out.println("--- Menú Ciudad ---");
            System.out.println("Seleccione una opción:");
            System.out.println("1. Crear Ciudad");
            System.out.println("2. Listar Ciudades");
            System.out.println("3. Actualizar Ciudad");
            System.out.println("4. Eliminar Ciudad");
            System.out.println("5. Volver");

            try {
                int seleccionCiudad = Integer.parseInt(scanner.nextLine());

                switch (seleccionCiudad) {
                    case 1:
                        cleanScreen();
                        // Implementar método para crear Ciudad
                        break;
                    case 2:
                        cleanScreen();
                        // Implementar método para listar Ciudades
                        break;
                    case 3:
                        cleanScreen();
                        // Implementar método para actualizar Ciudad
                        break;
                    case 4:
                        cleanScreen();
                        // Implementar método para eliminar Ciudad
                        break;
                    case 5:
                        cleanScreen();
                        salirMenuCiudad = true;
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
    }

    private static void mostrarMenuEntidades(Scanner scanner) {
        // Implementar el menú de Entidades
    }

    private static void mostrarMenuReservas(Scanner scanner) {
        // Implementar el menú de Reservas
    }
}
