package com.aerolinea;

import java.sql.SQLException;
import java.util.Scanner;

import aerolinea.Domain.Services.AerolineaServices;
import aerolinea.Infraestructure.in.AerolineaControlador;
import aerolinea.Infraestructure.out.AerolineaRepository;
import aerolinea.application.AerolineaUseCase;
import aeropuerto.application.AeropueroUseCase;
import aeropuerto.domain.services.AeropuertoServices;
import aeropuerto.infraestructure.in.AeropuertoControlador;
import aeropuerto.infraestructure.out.AeropuertoRepository;
import asiento.Applicacion.AsientoUseCase;
import asiento.Domain.Services.AsientoServices;
import asiento.infraestructure.in.AsientoControlador;
import asiento.infraestructure.out.AsientoRepository;
import ciudad.Application.CiudadUseCase;
import ciudad.Domain.Services.CiudadServices;
import ciudad.infraestructure.in.CiudadControlador;
import ciudad.infraestructure.out.CiudadRepository;
import empleado.Application.EmpleadoUseCase;
import empleado.Domain.services.EmpleadoServices;
import empleado.infraestructure.in.EmpleadoControlador;
import empleado.infraestructure.out.EmpleadoRepository;
import pais.Application.PaisUseCase;
import pais.Domain.Services.PaisServices;
import pais.infraesfructure.in.PaisControlador;
import pais.infraesfructure.out.PaisRepository;
import resource.ConfiguracionBaseDeDatos;
import tipoEmpleado.Application.TipoEmpleadoUseCase;
import tipoEmpleado.Domain.services.TipoEmpleadoServices;
import tipoEmpleado.infraestructure.in.TipoEmpleadoControlador;
import tipoEmpleado.infraestructure.out.TipoEmpleadoRepository;
import static utils.Consola.cleanScreen;

public class Main {
    public static void main(String[] args) {
        try {
            // Establece la conexión a la base de datos
            ConfiguracionBaseDeDatos.getConnection();

            // Inicializa los servicios y controladores
            // aerpouerto
            AeropuertoServices aeropuertoServices = new AeropuertoRepository();
            AeropueroUseCase aeropuertoUseCase = new AeropueroUseCase(aeropuertoServices);
            AeropuertoControlador aeropuertoControlador = new AeropuertoControlador(null, aeropuertoUseCase, null);
            //aerolinea
            AerolineaServices aerolineaServices = new AerolineaRepository();
            AerolineaUseCase aerolineaUseCase = new AerolineaUseCase(aerolineaServices);
            AerolineaControlador aerolineaControlador = new AerolineaControlador(aerolineaUseCase);
            //Ciudad
            CiudadServices ciudadServices = new CiudadRepository();
            CiudadUseCase ciudadUseCase = new CiudadUseCase(ciudadServices);
            CiudadControlador ciudadControlador = new CiudadControlador(ciudadUseCase, null, null);
            //Pais 
            PaisServices paisServices = new PaisRepository();
            PaisUseCase paisUseCase = new PaisUseCase(paisServices);
            PaisControlador paisControlador = new PaisControlador(paisUseCase);
            
            //Asiento
            AsientoServices asientoServices = new AsientoRepository();
            AsientoUseCase asientoUseCase = new AsientoUseCase(asientoServices);
            AsientoControlador asientoControlador = new AsientoControlador(asientoUseCase);
            
            //TipoEmpleado
            TipoEmpleadoServices tipoEmpleadoServices = new TipoEmpleadoRepository();
            TipoEmpleadoUseCase tipoEmpleadoUseCase = new TipoEmpleadoUseCase(tipoEmpleadoServices);
            TipoEmpleadoControlador tipoEmpleadoControlador = new TipoEmpleadoControlador(tipoEmpleadoUseCase);

            //Empleado 
            EmpleadoServices empleadoServices = new EmpleadoRepository();
            EmpleadoUseCase empleadoUseCase = new EmpleadoUseCase(empleadoServices);
            EmpleadoControlador empleadoControlador = new EmpleadoControlador(empleadoUseCase, null, null);
            
            //Rol Tripulante 
            // RolTripulanteServices rolTripulanteServices = new RolTripulanteRepository();
            // RolTripulnteUseCase rolTripulnteUseCase = new RolTripulnteUseCase(rolTripulanteServices);


            // Llama al método de inicio para mostrar el menú

            inicio(aerolineaControlador, ciudadControlador,aeropuertoControlador, paisControlador, asientoControlador, tipoEmpleadoControlador, empleadoControlador);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("No se pudo conectar a la base de datos.");
        }
    }


    private static void inicio(AerolineaControlador aerolineaControlador, CiudadControlador ciudadControlador,AeropuertoControlador aeropuertoControlador, PaisControlador paisControlador, AsientoControlador asientoControlador, TipoEmpleadoControlador tipoEmpleadoControlador, EmpleadoControlador empleadoControlador) {
        try (Scanner scanner = new Scanner(System.in)) {
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
                            mostrarMenuAeropuertos(scanner, aerolineaControlador, aeropuertoControlador,paisControlador,ciudadControlador);
                            break;
                        case 2:
                            cleanScreen();
                            mostrarMenuEntidades(scanner, asientoControlador, tipoEmpleadoControlador, empleadoControlador);
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
        } catch (Exception e) {
            System.out.println("Error al inicializar el scanner: " + e.getMessage());
        }
    }

    private static void mostrarMenuAeropuertos(Scanner scanner, AerolineaControlador aerolineaControlador, AeropuertoControlador aeropuertoControlador, PaisControlador paisControlador, CiudadControlador ciudadControlador) {
        boolean salirMenuAeropuertos = false;

        while (!salirMenuAeropuertos) {
            System.out.println("--- Menú Aeropuertos ---");
            System.out.println("Seleccione una opción:");
            System.out.println("1. Aerolinea");
            System.out.println("2. Localidad");
            System.out.println("3. Aeropuerto");
            System.out.println("4. Salidas Aeropuerto");
            System.out.println("5. Volver");

            try {
                int seleccion = Integer.parseInt(scanner.nextLine());

                switch (seleccion) {
                    case 1:
                        cleanScreen();
                        aerolineaControlador.start();
                        break;
                    case 2:
                        cleanScreen();
                        mostrarMenuLocalidad(scanner, paisControlador, ciudadControlador);
                        break;
                    case 3:
                        cleanScreen();
                        aeropuertoControlador.start();
                        break;
                    case 4:
                        cleanScreen();


                        // Incliur la salida Aeropuertos
                    case 5:
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


    private static void mostrarMenuLocalidad(Scanner scanner, PaisControlador paisControlador, CiudadControlador ciudadControlador) {
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
                        paisControlador.start();
                        break;
                    case 2:
                        cleanScreen();
                        ciudadControlador.start();
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


    private static void mostrarMenuEntidades(Scanner scanner, AsientoControlador asientoControlador, TipoEmpleadoControlador tipoEmpleadoControlador, EmpleadoControlador empleadoControlador) {
        boolean salirMenuEntidades = false;

        while (!salirMenuEntidades) {
            System.out.println("--- Menú Entidades ---");
            System.out.println("Seleccione una Entidad:");
            System.out.println("1. Avion");
            System.out.println("2. Empleado");
            System.out.println("3. Cliente");
            System.out.println("4. Vuelo");
            System.out.println("5. Pasajero");
            System.out.println("6. Salida");

            try {
                int seleccionEntidad = Integer.parseInt(scanner.nextLine());

                switch (seleccionEntidad) {
                    case 1:
                        cleanScreen();
                        System.out.println("Seleccione una opccion");
                        System.out.println("1. Fabricantes");
                        System.out.println("2. Modelo");
                        System.out.println("3. Asientos");
                        System.out.println("4. Estado Avion");
                        System.out.println("5. Avion");
                        System.out.println("6. Historial Estado");
                        System.out.println("7. Volver.");

                        try {
                            int opp = Integer.parseInt(scanner.nextLine());
                            switch (opp) {
                                case 1:
                                    // Fabricantes
                                    break;
                                case 2:
                                    //Modelo
                                    break;
                                case 3:
                                    cleanScreen();
                                    asientoControlador.start();
                                    break;
                                case 4:
                                    //Avion
                                    break;
                                case 5: 
                                    //EstadoAvion
                                case 6:
                                    //HistorialEstado               
                                    break;
                                case 7:
                                    cleanScreen();
                                    salirMenuEntidades = true;
                                    break;

                                default:
                                    throw new AssertionError();
                            }

                        } catch (NumberFormatException e) {
                            System.out.println("Entrada inválida. Por favor, ingrese un número entero.");
                        } catch (Exception e) {
                            System.out.println("Ha ocurrido un error: " + e.getMessage());
                        }
                        break;
                    case 2:
                        cleanScreen();
                        System.out.println("Menu Empleado");
                        System.out.println(" Seleccione una opcion");
                        System.out.println("1. Tipo Empleado");
                        System.out.println("2. Empleado");
                        System.out.println("3. Rol Tripulante");
                        System.out.println("4. Salir");
                        try {
                            int op2 = Integer.parseInt(scanner.nextLine());
                            switch (op2) {
                                case 1:
                                    cleanScreen();
                                    tipoEmpleadoControlador.start();
                                    break;
                                case 2:
                                    cleanScreen();
                                    empleadoControlador.start();
                                    break;
                                case 3:
                                    cleanScreen();
                                    // Rol Tripulante 
                                    break;
                                case 4:
                                    cleanScreen();
                                    salirMenuEntidades = true;
                                    break;
                                default:
                                    throw new AssertionError();
                            }

                        } catch (NumberFormatException e) {
                            System.out.println("Entrada inválida. Por favor, ingrese un número entero.");
                        } catch (Exception e) {
                            System.out.println("Ha ocurrido un error: " + e.getMessage());
                        }
                                    break;
                    case 3:
                        cleanScreen();
                        System.out.println("Hola Jp, Vas muy bien, Te amo");

                        // ciudadControlador.actualizarEntidad();
                        break;
                    case 4:
                        cleanScreen();
                        System.out.println("Hola Jp, Vas muy bien, Te amo");
                        // ciudadControlador.eliminarEntidad();
                        break;
                    case 5:
                        cleanScreen();
                        salirMenuEntidades = true;
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

    private static void mostrarMenuReservas(Scanner scanner) {
        boolean salirMenuReservas = false;

        while (!salirMenuReservas) {
            System.out.println("--- Menú Reservas ---");
            System.out.println("Seleccione una opción:");
            System.out.println("1. Crear Reserva");
            System.out.println("2. Listar Reservas");
            System.out.println("3. Actualizar Reserva");
            System.out.println("4. Eliminar Reserva");
            System.out.println("5. Volver");

            try {
                int seleccionReserva = Integer.parseInt(scanner.nextLine());

                switch (seleccionReserva) {
                    case 1:
                        cleanScreen();
                        // Implementar método para crear Reserva
                        break;
                    case 2:
                        cleanScreen();
                        // Implementar método para listar Reservas
                        break;
                    case 3:
                        cleanScreen();
                        // Implementar método para actualizar Reserva
                        break;
                    case 4:
                        cleanScreen();
                        // Implementar método para eliminar Reserva
                        break;
                    case 5:
                        cleanScreen();
                        salirMenuReservas = true;
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
}
