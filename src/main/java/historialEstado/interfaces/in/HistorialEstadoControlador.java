package historialEstado.interfaces.in;

import avion.Domain.Entity.Avion;
import avion.Infraestructure.out.AvionRepository;
import estadoAvion.Domain.entity.EstadoAvion;
import estadoAvion.interfaces.out.EstadoAvionRepository;
import historialEstado.Domain.entity.HistorialEstado;
import historialEstado.Domain.services.HistorialEstadoServices;
import historialEstado.application.HistorialEstadoUseCase;
import utils.Consola;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class HistorialEstadoControlador {

    private final Scanner scanner = new Scanner(System.in);
    private final HistorialEstadoUseCase historialEstadoUseCase;
    private final HistorialEstadoServices historialEstadoServices;
    private final AvionRepository avionRepo;
    private final EstadoAvionRepository estadoAvionRepo;

    

    public HistorialEstadoControlador(HistorialEstadoUseCase historialEstadoUseCase,
            HistorialEstadoServices historialEstadoServices, AvionRepository avionRepo,
            EstadoAvionRepository estadoAvionRepo) {
        this.historialEstadoUseCase = historialEstadoUseCase;
        this.historialEstadoServices = historialEstadoServices;
        this.avionRepo = avionRepo;
        this.estadoAvionRepo = estadoAvionRepo;
    }

    public void start() {
        int opcion;

        do {
            mostrarMenu();
            opcion = Consola.optionValidation("Seleccione una opción: ", 1, 6);

            switch (opcion) {
                case 1:
                    crearHistorialEstado();
                    break;
                case 2:
                    obtenerTodosLosHistorialEstados();
                    break;
                case 3:
                    obtenerHistorialEstadoPorId();
                    break;
                case 4:
                    actualizarHistorialEstado();
                    break;
                case 5:
                    eliminarHistorialEstado();
                    break;
                case 6:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
                    break;
            }

        } while (opcion != 6);
    }

    private void mostrarMenu() {
        System.out.println("---- Menu de Opciones de Historial de Estado ----");
        System.out.println("1. Crear Historial de Estado");
        System.out.println("2. Listar Todos los Historiales de Estado");
        System.out.println("3. Buscar Historial de Estado por ID");
        System.out.println("4. Actualizar Historial de Estado");
        System.out.println("5. Eliminar Historial de Estado");
        System.out.println("6. Salir");
    }

    public void crearHistorialEstado() {
        System.out.println("----- Crear Historial de Estado -----");

        List<Avion> aviones = avionRepo.obtenerTodosLosAviones();
        System.out.println("Seleccione el avión:");
        mostrarAviones(aviones);
        int opcionAvion = Consola.optionValidation("Ingrese el número del avión: ", 1, aviones.size());
        Avion avionSeleccionado = aviones.get(opcionAvion - 1);

        List<EstadoAvion> estadosAvion = estadoAvionRepo.obtenerTodosLosEstadosAvion();
        System.out.println("Seleccione el estado del avión:");
        mostrarEstadosAvion(estadosAvion);
        int opcionEstado = Consola.optionValidation("Ingrese el número del estado del avión: ", 1, estadosAvion.size());
        EstadoAvion estadoAvionSeleccionado = estadosAvion.get(opcionEstado - 1);

        Date fechaInicio = Consola.obtenerFechaDeHistorialEstado();

        HistorialEstado historialEstado = new HistorialEstado();
        historialEstado.setAvion(avionSeleccionado);
        historialEstado.setEstadoAvion(estadoAvionSeleccionado);
        historialEstado.setFechaInicio(fechaInicio);

        historialEstadoServices.crearHistorialEstado(historialEstado);

        System.out.println("Historial de Estado creado con éxito. ID del historial: " + historialEstado.getId());
    }

    public void obtenerTodosLosHistorialEstados() {
        System.out.println("---- Listado de Historiales de Estado ----");

        List<HistorialEstado> historialesEstado = historialEstadoServices.obtenerTodosLosHistorialEstados();
        for (HistorialEstado historialEstado : historialesEstado) {
            System.out.println(historialEstado);
            // Aquí puedes personalizar cómo deseas mostrar cada historial de estado
        }
    }

    public void obtenerHistorialEstadoPorId() {
        System.out.println("---- Buscar Historial de Estado por ID ----");

        System.out.println("Ingrese el ID del historial de estado: ");
        Long id = scanner.nextLong();
        scanner.nextLine(); // Consumir el salto de línea después del nextLong()
        HistorialEstado historialEstado = historialEstadoServices.obtenerHistorialEstadoPorId(id);
        if (historialEstado != null) {
            System.out.println("Historial encontrado:");
            System.out.println(historialEstado);
        } else {
            System.out.println("No se encontró ningún historial de estado con el ID proporcionado.");
        }
    }

    public void actualizarHistorialEstado() {
        System.out.println("---- Actualizar Historial de Estado ----");

        System.out.println("Ingrese el ID del historial de estado: ");
        Long id = scanner.nextLong();
        scanner.nextLine(); // Consumir el salto de línea después del nextLong()
        HistorialEstado historialEstado = historialEstadoServices.obtenerHistorialEstadoPorId(id);
        if (historialEstado != null) {
            // Mostrar información actual del historial de estado
            System.out.println("Información actual:");
            System.out.println(historialEstado);

            // Pedir nueva información al usuario
            List<Avion> aviones = avionRepo.obtenerTodosLosAviones();
            System.out.println("Seleccione el nuevo avión:");
            mostrarAviones(aviones);
            int opcionAvion = Consola.optionValidation("Ingrese el número del avión: ", 1, aviones.size());
            Avion nuevoAvion = aviones.get(opcionAvion - 1);

            List<EstadoAvion> estadosAvion = estadoAvionRepo.obtenerTodosLosEstadosAvion();
            System.out.println("Seleccione el nuevo estado del avión:");
            mostrarEstadosAvion(estadosAvion);
            int opcionEstado = Consola.optionValidation("Ingrese el número del estado del avión: ", 1, estadosAvion.size());
            EstadoAvion nuevoEstadoAvion = estadosAvion.get(opcionEstado - 1);

            Date nuevaFechaInicio = Consola.obtenerFechaDeHistorialEstado();

            // Actualizar el historial de estado
            historialEstado.setAvion(nuevoAvion);
            historialEstado.setEstadoAvion(nuevoEstadoAvion);
            historialEstado.setFechaInicio(nuevaFechaInicio);

            historialEstadoServices.actualizarHistorialEstado(historialEstado);
            System.out.println("Historial de Estado actualizado con éxito.");
        } else {
            System.out.println("No se encontró ningún historial de estado con el ID proporcionado.");
        }
    }

    public void eliminarHistorialEstado() {
        System.out.println("---- Eliminar Historial de Estado ----");

        System.out.println("Ingrese el ID del historial de estado: ");
        Long id = scanner.nextLong();
        scanner.nextLine(); // Consumir el salto de línea después del nextLong()
        HistorialEstado historialEstado = historialEstadoServices.obtenerHistorialEstadoPorId(id);
        if (historialEstado != null) {
            historialEstadoServices.eliminarHistorialEstado(id);
            System.out.println("Historial de Estado eliminado con éxito.");
        } else {
            System.out.println("No se encontró ningún historial de estado con el ID proporcionado.");
        }
    }

    private void mostrarAviones(List<Avion> aviones) {
        for (int i = 0; i < aviones.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, aviones.get(i).getMatricula());
        }
    }

    private void mostrarEstadosAvion(List<EstadoAvion> estadosAvion) {
        for (int i = 0; i < estadosAvion.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, estadosAvion.get(i).getEstado());
        }
    }
}
