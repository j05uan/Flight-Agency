package empleado.infraestructure.in;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.google.protobuf.TextFormat.ParseException;

import aerolinea.Domain.Entity.Aerolinea;
import aerolinea.application.AerolineaUseCase;
import empleado.Application.EmpleadoUseCase;
import empleado.Domain.entity.Empleado;
import tipoEmpleado.Application.TipoEmpleadoUseCase;
import tipoEmpleado.Domain.entity.TipoEmpleado;


public class EmpleadoControlador {
    Scanner scanner = new Scanner(System.in);
    private final EmpleadoUseCase empleadoUseCase;
    private final AerolineaUseCase aerolineaUseCase;  
    private final TipoEmpleadoUseCase tipoEmpleadoUseCase;  


    public EmpleadoControlador(EmpleadoUseCase empleadoUseCase, AerolineaUseCase aerolineaUseCase, TipoEmpleadoUseCase tipoEmpleadoUseCase) {
        this.empleadoUseCase = empleadoUseCase;
        this.aerolineaUseCase = aerolineaUseCase;
        this.tipoEmpleadoUseCase = tipoEmpleadoUseCase;
    }

    public void start() {
        boolean salir = false;

        while (!salir) {
            mostrarMenu();
            try {
                int opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1:
                        crearEmpleado();
                        break;
                    case 2:
                        obtenerTodosLosEmpleados();
                        break;
                    case 3:
                        obtenerEmpleadoPorId();
                        break;
                    case 4:
                        actualizarEmpleado();
                        break;
                    case 5:
                        eliminarEmpleado();
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
            } catch (ParseException e) {
                System.out.println("Formato de fecha inválido. Por favor, ingrese una fecha en el formato DD/MM/YYYY.");
            } catch (Exception e) {
                System.out.println("Ha ocurrido un error: " + e.getMessage());
            }
        }

        scanner.close();
    }

    private void mostrarMenu() {
        System.out.println("Seleccione la opción:");
        System.out.println("1. Crear Empleado");
        System.out.println("2. Obtener Todos los Empleados");
        System.out.println("3. Obtener Empleado por ID");
        System.out.println("4. Actualizar Empleado");
        System.out.println("5. Eliminar Empleado");
        System.out.println("6. Salir");
    }

    private void crearEmpleado() throws ParseException, java.text.ParseException {
        System.out.println("--- Menú Crear Empleado ---");

        // Listar aerolíneas
        System.out.println("Seleccione una aerolínea:");
        List<Aerolinea> aerolineas = aerolineaUseCase.obtenerTodasLasAerolineas();
        for (Aerolinea aerolinea : aerolineas) {
            System.out.println(aerolinea.getId() + ". " + aerolinea.getNombre());
        }
        Long idAerolinea = Long.parseLong(scanner.nextLine());
        Aerolinea aerolineaSeleccionada = aerolineas.stream().filter(a -> a.getId().equals(idAerolinea)).findFirst().orElse(null);

        // Listar tipos de empleados
        System.out.println("Seleccione un tipo de empleado:");
        List<TipoEmpleado> tiposEmpleado = tipoEmpleadoUseCase.obtenerTodosLosTiposEmpleado();
        for (TipoEmpleado tipo : tiposEmpleado) {
            System.out.println(tipo.getId() + ". " + tipo.getTipo());
        }
        Long idTipoEmpleado = Long.parseLong(scanner.nextLine());
        TipoEmpleado tipoEmpleadoSeleccionado = tiposEmpleado.stream().filter(t -> t.getId().equals(idTipoEmpleado)).findFirst().orElse(null);

        System.out.println("Ingrese el nombre del empleado:");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese la fecha de ingreso (DD/MM/YYYY):");
        Date fechaIngreso = new SimpleDateFormat("dd/MM/yyyy").parse(scanner.nextLine());

        Empleado empleado = new Empleado();
        empleadoUseCase.crearEmpleado(empleado);
        System.out.println("Empleado creado con éxito.");
    }

    private void obtenerTodosLosEmpleados() {
        System.out.println("--- Menú Obtener Todos los Empleados ---");
        empleadoUseCase.obtenerTodosLosEmpleados().forEach(System.out::println);
    }

    private void obtenerEmpleadoPorId() {
        System.out.println("--- Menú Obtener Empleado por ID ---");
        System.out.print("Ingrese el ID del empleado: ");
        Long id = Long.parseLong(scanner.nextLine());
        Empleado empleado = empleadoUseCase.obtenerEmpleadoPorId(id);
        if (empleado != null) {
            System.out.println(empleado);
        } else {
            System.out.println("Empleado no encontrado.");
        }
    }

    private void actualizarEmpleado() throws ParseException, java.text.ParseException {
        System.out.println("--- Menú Actualizar Empleado ---");
        System.out.print("Ingrese el ID del empleado que desea actualizar: ");
        Long id = Long.parseLong(scanner.nextLine());
        Empleado empleado = empleadoUseCase.obtenerEmpleadoPorId(id);
        if (empleado != null) {
            System.out.println("Ingrese el nuevo nombre del empleado:");
            String nuevoNombre = scanner.nextLine();
            empleado.setNombre(nuevoNombre);

            System.out.println("Ingrese la nueva fecha de ingreso (DD/MM/YYYY):");
            Date nuevaFechaIngreso = new SimpleDateFormat("dd/MM/yyyy").parse(scanner.nextLine());
            empleado.setFechaIngreso(nuevaFechaIngreso);

            System.out.println("Seleccione una nueva aerolínea:");
            List<Aerolinea> aerolineas = aerolineaUseCase.obtenerTodasLasAerolineas();
            for (Aerolinea aerolinea : aerolineas) {
                System.out.println(aerolinea.getId() + ". " + aerolinea.getNombre());
            }
            Long idAerolinea = Long.parseLong(scanner.nextLine());
            Aerolinea aerolineaSeleccionada = aerolineas.stream().filter(a -> a.getId().equals(idAerolinea)).findFirst().orElse(null);
            empleado.setAerolinea(aerolineaSeleccionada);

            System.out.println("Seleccione un nuevo tipo de empleado:");
            List<TipoEmpleado> tiposEmpleado = tipoEmpleadoUseCase.obtenerTodosLosTiposEmpleado();
            for (TipoEmpleado tipo : tiposEmpleado) {
                System.out.println(tipo.getId() + ". " + tipo.getTipo());
            }
            Long idTipoEmpleado = Long.parseLong(scanner.nextLine());
            TipoEmpleado tipoEmpleadoSeleccionado = tiposEmpleado.stream().filter(t -> t.getId().equals(idTipoEmpleado)).findFirst().orElse(null);
            empleado.setTipoEmpleado(tipoEmpleadoSeleccionado);

            empleadoUseCase.actualizarEmpleado(empleado);
            System.out.println("Empleado actualizado con éxito.");
        } else {
            System.out.println("Empleado no encontrado.");
        }
    }


    private void eliminarEmpleado() {
        System.out.println("--- Menú Eliminar Empleado ---");
        System.out.print("Ingrese el ID del empleado que desea eliminar: ");
        Long id = Long.parseLong(scanner.nextLine());
        empleadoUseCase.eliminarEmpleado(id);
        System.out.println("Empleado eliminado con éxito.");
    }
}
