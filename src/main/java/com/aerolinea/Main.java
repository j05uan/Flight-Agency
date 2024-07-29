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
import avion.Application.AvionUseCase;
import avion.Domain.Services.AvionServices;
import avion.Infraestructure.in.AvionControlador;
import avion.Infraestructure.out.AvionRepository;
import ciudad.Application.CiudadUseCase;
import ciudad.Domain.Services.CiudadServices;
import ciudad.infraestructure.in.CiudadControlador;
import ciudad.infraestructure.out.CiudadRepository;
import cliente.Application.ClienteUseCase;
import cliente.Domain.services.ClienteServices;
import cliente.infraestructure.in.ClienteControlador;
import cliente.infraestructure.out.ClienteRepository;
import empleado.Application.EmpleadoUseCase;
import empleado.Domain.services.EmpleadoServices;
import empleado.infraestructure.in.EmpleadoControlador;
import empleado.infraestructure.out.EmpleadoRepository;
import estadoAvion.Application.EstadoAvionUseCase;
import estadoAvion.Domain.services.EstadoAvionServices;
import estadoAvion.interfaces.in.EstadoAvionControlador;
import estadoAvion.interfaces.out.EstadoAvionRepository;
import fabricante.Application.FabricanteUseCase;
import fabricante.Domain.Services.FabricanteServices;
import fabricante.infraestructure.in.FabricanteControlador;
import fabricante.infraestructure.out.FabricanteRepository;
import historialEstado.Domain.services.HistorialEstadoServices;
import historialEstado.application.HistorialEstadoUseCase;
import historialEstado.interfaces.in.HistorialEstadoControlador;
import historialEstado.interfaces.out.HistorialEstadoRepository;
import modelo.Application.ModeloUseCase;
import modelo.Domain.services.ModeloServices;
import modelo.Infraestructure.in.ModeloControlador;
import modelo.Infraestructure.out.ModeloRepository;
import pais.Application.PaisUseCase;
import pais.Domain.Services.PaisServices;
import pais.infraesfructure.in.PaisControlador;
import pais.infraesfructure.out.PaisRepository;
import pasajero.Applicacion.PasajeroUseCase;
import pasajero.Domain.services.PasajeroServices;
import pasajero.infraestructure.in.PasajeroControlador;
import pasajero.infraestructure.out.PasajeroRepository;
import resource.ConfiguracionBaseDeDatos;
import rolTripulante.Application.RolTripulnteUseCase;
import rolTripulante.Domain.services.RolTripulanteServices;
import rolTripulante.infraestructure.in.RolTripulanteControlador;
import rolTripulante.infraestructure.out.RolTripulanteRepository;
import ruta.Application.RutaUseCase;
import ruta.Domain.Services.RutaServices;
import ruta.Infraestructure.in.RutaControlador;
import ruta.Infraestructure.out.RutaRepository;
import rutaEscala.Applicacion.RutaEscalaUseCase;
import rutaEscala.Domain.services.RutaEscalaServices;
import rutaEscala.interfaces.in.RutaEscalaControlador;
import rutaEscala.interfaces.out.RutaEscalaRepository;
import salidaAeropuerto.Application.SalidaAeropuertoUseCase;
import salidaAeropuerto.Domain.services.SalidaAeropuertoServices;
import salidaAeropuerto.infraestructure.in.SalidaAeropuertoControlador;
import salidaAeropuerto.infraestructure.out.SalidaAeropuertoRepository;
import tarifa.Application.TarifaUseCase;
import tarifa.Domain.services.TarifaServices;
import tarifa.interfaces.in.TarifaControlador;
import tarifa.interfaces.out.TarifaRepository;
import tipoDocumento.Applicacion.TipoDocumentoUseCase;
import tipoDocumento.Domain.services.TipoDocumentoServices;
import tipoDocumento.infraestructure.in.TipoDocumentoControlador;
import tipoDocumento.infraestructure.out.TipoDocumentoRepository;
import tipoEmpleado.Application.TipoEmpleadoUseCase;
import tipoEmpleado.Domain.services.TipoEmpleadoServices;
import tipoEmpleado.infraestructure.in.TipoEmpleadoControlador;
import tipoEmpleado.infraestructure.out.TipoEmpleadoRepository;
import tipoTarifa.Application.TipoTarifaUseCase;
import tipoTarifa.Domain.services.TipoTarifaServices;
import tipoTarifa.infraestructure.in.TipoTarifaControlador;
import tipoTarifa.infraestructure.out.TipoTarifaRepository;
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
            AeropuertoControlador aeropuertoControlador = new AeropuertoControlador(aeropuertoUseCase);
            //aerolinea
            AerolineaServices aerolineaServices = new AerolineaRepository();
            AerolineaUseCase aerolineaUseCase = new AerolineaUseCase(aerolineaServices);
            AerolineaControlador aerolineaControlador = new AerolineaControlador(aerolineaUseCase);
            //Ciudad
            CiudadServices ciudadServices = new CiudadRepository();
            CiudadUseCase ciudadUseCase = new CiudadUseCase(ciudadServices);
            CiudadControlador ciudadControlador = new CiudadControlador(ciudadUseCase);
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
            
            //Fabricante
            FabricanteServices fabricanteServices = new FabricanteRepository();
            FabricanteUseCase fabricanteUseCase = new FabricanteUseCase(fabricanteServices);
            FabricanteControlador fabricanteControlador = new FabricanteControlador(fabricanteUseCase);

            // Modelo 
            ModeloServices modeloServices = new ModeloRepository();
            ModeloUseCase modeloUseCase = new ModeloUseCase(modeloServices);
            ModeloControlador modeloControlador = new ModeloControlador(modeloUseCase);


            //Avion
            AvionServices avionServices = new AvionRepository();
            AvionUseCase avionUseCase = new AvionUseCase(avionServices);
            AvionControlador avioncControlador = new AvionControlador(null);

            //EstadoAvion
            EstadoAvionServices estadoAvionServices = new EstadoAvionRepository();
            EstadoAvionUseCase estadoAvionUseCase = new EstadoAvionUseCase(estadoAvionServices);
            EstadoAvionControlador estadoAvionControlador = new EstadoAvionControlador( estadoAvionUseCase);

            //HistorialEstado
            HistorialEstadoServices historialEstadoServices = new HistorialEstadoRepository();
            HistorialEstadoUseCase historialEstadoUseCase = new HistorialEstadoUseCase(historialEstadoServices);
            HistorialEstadoControlador historialEstadoControlador = new HistorialEstadoControlador(historialEstadoUseCase, historialEstadoServices, null, null);
            //Rol Tripulante 
            RolTripulanteServices rolTripulanteServices = new RolTripulanteRepository();
            RolTripulnteUseCase rolTripulnteUseCase = new RolTripulnteUseCase(rolTripulanteServices);
            RolTripulanteControlador rolTripulanteControlador = new RolTripulanteControlador(rolTripulnteUseCase, null);

            //Cliente
            ClienteServices clienteServices = new ClienteRepository();
            ClienteUseCase clienteUseCase = new ClienteUseCase(clienteServices);
            ClienteControlador clienteControlador = new ClienteControlador(null, clienteUseCase, null);

            //Tipo Documento
            TipoDocumentoServices tDocumentoServices = new TipoDocumentoRepository();
            TipoDocumentoUseCase tipoDocumentoUseCase = new TipoDocumentoUseCase(tDocumentoServices);
            TipoDocumentoControlador tipoDocumentoControlador = new TipoDocumentoControlador(tipoDocumentoUseCase);

            //Salidas Aeropuerto
            SalidaAeropuertoServices salidaAeropuertoServices = new SalidaAeropuertoRepository();
            SalidaAeropuertoUseCase salidaAeropuertoUseCase = new SalidaAeropuertoUseCase(salidaAeropuertoServices);
            SalidaAeropuertoControlador salidaAeropuertoControlador = new SalidaAeropuertoControlador(null);

            // Vuelos 
            RutaServices rutaServices = new RutaRepository();
            RutaUseCase rutaUseCase = new RutaUseCase(rutaServices);
            RutaControlador rutaControlador = new RutaControlador(null);

            //Escalas 
            RutaEscalaServices rutaEscalaServices = new RutaEscalaRepository();
            RutaEscalaUseCase rutaEscalaUseCase = new RutaEscalaUseCase(rutaEscalaServices);
            RutaEscalaControlador rutaEscalaControlador = new RutaEscalaControlador(rutaEscalaUseCase);

            //Pasajero
            PasajeroServices pasajeroServices = new PasajeroRepository();
            PasajeroUseCase pasajeroUseCase = new PasajeroUseCase(pasajeroServices);
            PasajeroControlador pasajeroControlador = new PasajeroControlador(pasajeroUseCase);

            //Tarifa
            TarifaServices tarifaServices = new TarifaRepository();
            TarifaUseCase tarifaUseCase = new TarifaUseCase(tarifaServices);
            TarifaControlador tarifaControlador = new TarifaControlador(tarifaUseCase, null);

            //Tipo Tarifa 
            TipoTarifaServices tipoTarifaServices = new TipoTarifaRepository();
            TipoTarifaUseCase tipoTarifaUseCase = new TipoTarifaUseCase(tipoTarifaServices);
            TipoTarifaControlador tipoTarifaControlador = new TipoTarifaControlador(tipoTarifaUseCase);
            // Llama al método de inicio para mostrar el menú

            inicio(aerolineaControlador, ciudadControlador,aeropuertoControlador, paisControlador, asientoControlador, tipoEmpleadoControlador, empleadoControlador, fabricanteControlador, modeloControlador,
            avioncControlador, estadoAvionControlador, historialEstadoControlador,rolTripulanteControlador,clienteControlador, tipoDocumentoControlador, salidaAeropuertoControlador, rutaControlador,
            rutaEscalaControlador, pasajeroControlador, tipoTarifaControlador, tarifaControlador   );

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("No se pudo conectar a la base de datos.");
        }
    }


    private static void inicio(AerolineaControlador aerolineaControlador, CiudadControlador ciudadControlador,AeropuertoControlador aeropuertoControlador, PaisControlador paisControlador, AsientoControlador asientoControlador, TipoEmpleadoControlador tipoEmpleadoControlador, EmpleadoControlador empleadoControlador, FabricanteControlador fabricanteControlador,
        ModeloControlador modeloControlador, AvionControlador avioncControlador, EstadoAvionControlador estadoAvionControlador, HistorialEstadoControlador historialEstadoControlador, RolTripulanteControlador rolTripulanteControlador, ClienteControlador clienteControlador, TipoDocumentoControlador tipoDocumentoControlador, SalidaAeropuertoControlador salidaAeropuertoControlador,
        RutaControlador rutaControlador, RutaEscalaControlador rutaEscalaControlador, PasajeroControlador pasajeroControlador, TipoTarifaControlador tipoTarifaControlador, TarifaControlador tarifaControlador
            
        ) {
        try (Scanner scanner = new Scanner(System.in)) {
            boolean salir = false;

            while (!salir) {
                System.out.println("--- Agencia Vuelos Globales ---");
                System.out.println("Bienvenidos");
                System.out.println("Seleccione una Opción:");
                System.out.println("1. Aeropuertos");
                System.out.println("2. Entidades");
                System.out.println("3. Reservas");
                System.out.println("4. Revision");
                System.out.println("5. Salir");

                try {
                    int opcion = Integer.parseInt(scanner.nextLine());

                    switch (opcion) {
                        case 1:
                            cleanScreen();
                            mostrarMenuAeropuertos(scanner, aerolineaControlador, aeropuertoControlador,paisControlador,ciudadControlador, salidaAeropuertoControlador);
                            break;
                        case 2:
                            cleanScreen();
                            mostrarMenuEntidades(scanner, clienteControlador,rutaEscalaControlador, pasajeroControlador, estadoAvionControlador,rutaControlador, asientoControlador,historialEstadoControlador, tipoEmpleadoControlador, empleadoControlador, fabricanteControlador,modeloControlador,avioncControlador, rolTripulanteControlador, tipoDocumentoControlador);
                            break;
                        case 3:
                            cleanScreen();
                            mostrarMenuReservas(scanner, tarifaControlador, tipoTarifaControlador,pasajeroControlador);
                            break;
                        case 4:
                            cleanScreen();
                            System.out.println("Menu Revision");
                            System.out.println("Seleccione una Opcion");
                            System.out.println("1. Revision");
                            System.out.println("2. Asignar Revision");
                            System.out.println("3. Atras.");
                            try {
                                int opcionRevision = Integer.parseInt(scanner.nextLine());
                                switch (opcionRevision) {
                                    case 1:
                                        cleanScreen();

                                        break;
                                
                                    default:
                                        break;
                                }
                            } catch (Exception e) {
                            }
                        case 5:
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

    private static void mostrarMenuAeropuertos(Scanner scanner, AerolineaControlador aerolineaControlador, AeropuertoControlador aeropuertoControlador, PaisControlador paisControlador, CiudadControlador ciudadControlador, SalidaAeropuertoControlador salidaAeropuertoControlador) {
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
                        aerolineaControlador.run();
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
                        salidaAeropuertoControlador.start();
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
                        paisControlador.run();
                        
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


    private static void mostrarMenuEntidades(Scanner scanner, ClienteControlador clienteControlador, RutaEscalaControlador rutaEscalaControlador, PasajeroControlador pasajeroControlador, EstadoAvionControlador estadoAvionControlador, RutaControlador rutaControlador, AsientoControlador asientoControlador, HistorialEstadoControlador historialEstadoControlador, TipoEmpleadoControlador tipoEmpleadoControlador, EmpleadoControlador empleadoControlador, FabricanteControlador fabricanteControlador, ModeloControlador modeloControlador, AvionControlador avioncControlador, RolTripulanteControlador rolTripulanteControlador, TipoDocumentoControlador tipoDocumentoControlador) 
        {
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
                                    cleanScreen();
                                    fabricanteControlador.start();
                                    
                                    break;
                                case 2:
                                    cleanScreen();
                                    modeloControlador.start();

                                    break;
                                case 3:
                                    asientoControlador.start();

                                    cleanScreen();
                                    break;
                                case 4:
                                    cleanScreen();
                                    estadoAvionControlador.start();

                                    break;
                                case 5: 
                                    cleanScreen();
                                    avioncControlador.start();
                                case 6:
                                    cleanScreen();
                                    historialEstadoControlador.start();
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
                                    rolTripulanteControlador.start(); 
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
                        System.out.println("-- Menu Ciente ---");
                        System.out.println("Seleccione la opcion");
                        System.out.println(" 1. Tipo Docummento");
                        System.out.println(" 2. Cliente");
                        try {
                            int opCliente = Integer.parseInt(scanner.nextLine());

                            switch (opCliente) {
                                case 1:
                                    cleanScreen();
                                    tipoDocumentoControlador.start();

                                    break;
                                case 2:
                                    cleanScreen();
                                    clienteControlador.start();
                                    
                                default:
                                    throw new AssertionError();
                            }

                        } catch (Exception e) {
                        }
                        break;
                        case 4:
                        cleanScreen();
                        System.out.println("Menú Vuelos");
                        System.out.println("Seleccione una opción:");
                        System.out.println("1. Escalas");
                        System.out.println("2. Vuelos");
                        System.out.println("3. Tripulación de Vuelo");
                        System.out.println("4. Salir");
                    
                        try {
                            int opcion = Integer.parseInt(scanner.nextLine());
                            switch (opcion) {
                                case 1:
                                    cleanScreen();
                                    rutaEscalaControlador.start();  // Asegúrate de que `rutaEscalaControlador` esté inicializado
                                    break;
                                case 2:
                                    cleanScreen();
                                    rutaControlador.start();  // Asegúrate de que `rutaControlador` esté inicializado
                                    break;
                                case 3:
                                    cleanScreen();
                                    
                                    System.out.println("Funcionalidad de Tripulación de Vuelo no implementada aún.");
                                    break;
                                case 4:
                                    System.out.println("Saliendo del menú de vuelos...");
                                    break;
                                default:
                                    System.out.println("Opción no válida. Por favor, elija una opción entre 1 y 4.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Entrada inválida. Por favor, ingrese un número entero.");
                        } catch (Exception e) {
                            System.out.println("Ha ocurrido un error: " + e.getMessage());
                        }
                        break;
                    
                    case 5:
                        cleanScreen();
                        pasajeroControlador.start();
                        break;
                    case 6:
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

    private static void mostrarMenuReservas(Scanner scanner, TarifaControlador tarifaControlador, TipoTarifaControlador tipoTarifaControlador, PasajeroControlador pasajeroControlador) {
        boolean salirMenuReservas = false;

        while (!salirMenuReservas) {
            System.out.println("--- Menú Reservas ---");
            System.out.println("Seleccione una opción:");
            System.out.println("1. Tipo Tarifa");
            System.out.println("2. Tarifa");
            System.out.println("3. Pasajero");
            System.out.println("4. Reserva");
            System.out.println("5. Volver");

            try {
                int seleccionReserva = Integer.parseInt(scanner.nextLine());

                switch (seleccionReserva) {
                    case 1:
                        cleanScreen();
                        tipoTarifaControlador.start();
                        break;
                    case 2:
                        cleanScreen();
                        tarifaControlador.start();
                        break;
                    case 3:
                        cleanScreen();
                        pasajeroControlador.start();
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
