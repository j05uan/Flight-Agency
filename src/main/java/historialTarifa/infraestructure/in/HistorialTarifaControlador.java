package historialTarifa.infraestructure.in;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import historialTarifa.Application.HistorialTarifaUseCase;
import historialTarifa.Domain.entity.HistorialTarifa;
import historialTarifa.Domain.services.HistorialTarifaServices;
import tarifa.Domain.entity.Tarifa;
import tarifa.interfaces.out.TarifaRepository;
import utils.Consola;

public class HistorialTarifaControlador {

    private final Scanner scanner;
    private final HistorialTarifaUseCase historialTarifaUseCase;
    private final HistorialTarifaServices historialTarifaServices;
    private final TarifaRepository tarifaRepository;

    

    public HistorialTarifaControlador(Scanner scanner, HistorialTarifaUseCase historialTarifaUseCase,
            HistorialTarifaServices historialTarifaServices, TarifaRepository tarifaRepository) {
        this.scanner = scanner;
        this.historialTarifaUseCase = historialTarifaUseCase;
        this.historialTarifaServices = historialTarifaServices;
        this.tarifaRepository = tarifaRepository;
    }

    public void start(){
        int opcion;

         do {
            mostrarMenu();
            opcion = Consola.optionValidation("Seleccione una opción: ", 1, 6);

            switch (opcion) {
                case 1:
                    crearHistorialTarifa();
                    break;
                case 2:
                    obtenerTodosLosHistorialTarifas();
                    break;
                case 3:
                    obtenerHistorialTarifaPorId();
                    break;
                case 4:
                    actualizarHistorialTarifa();
                    break;
                case 5:
                    eliminarHistorialTarifa();
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
        System.out.println("---- Menu de Opciones de Historial de Tarifa ----");
        System.out.println("1. Crear Historial de Tarifa");
        System.out.println("2. Listar Todos los Historiales de Tarifa");
        System.out.println("3. Buscar Historial de Tarifa por ID");
        System.out.println("4. Actualizar Historial de Tarifa");
        System.out.println("5. Eliminar Historial de Tarifa");
        System.out.println("6. Salir");
    }

    public void crearHistorialTarifa(){

        System.out.println("---Menu Crear Historial Tarifa---");
        List<Tarifa> tarifas = tarifaRepository.obtenerTodasLasTarifas();
        System.out.println("Seleccione la tarifa");
        mostrarTarifas(tarifas);
        int opcionTarifa = Consola.optionValidation("Ingrese el Id de la Tarifa", 1, tarifas.size());
        Tarifa TarifaSeleccionada = tarifas.get(opcionTarifa -1);

        Date fechaHistorial =Consola.obtenerFechaDeHistorialTarifa();

        System.out.print("Ingrese el valor de la tarifa: ");
        BigDecimal valor = new BigDecimal(scanner.nextLine());

        HistorialTarifa nuevoHistorial = new HistorialTarifa();
        nuevoHistorial.setTarifa(TarifaSeleccionada);
        nuevoHistorial.setFechaInicio(fechaHistorial);
        nuevoHistorial.setValor(valor);

        historialTarifaUseCase.crearHistorialTarifa(nuevoHistorial);




        System.out.println("Historial Tarifa Creado");

    }
   private void mostrarTarifas(List<Tarifa> tarifas) {
        for (int i = 0; i < tarifas.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, tarifas.get(i).getTipoTarifa());
        }
    }

    public void obtenerTodosLosHistorialTarifas() {
        List<HistorialTarifa> historiales = historialTarifaUseCase.obtenerTodosLosHistorialesTarifa();
        if (historiales.isEmpty()) {
            System.out.println("No hay historiales de tarifas registrados.");
        } else {
            System.out.println("Listado de Historiales de Tarifa:");
            for (HistorialTarifa historial : historiales) {
                System.out.println(historial);
            }
        }
    }
    
    public void obtenerHistorialTarifaPorId() {
        System.out.println("Ingrese el ID del historial de tarifa: ");
        long id = scanner.nextLong();
        HistorialTarifa historial = historialTarifaUseCase.obtenertHistorialTarifaPorId(id);
        if (historial != null) {
            System.out.println("Historial de Tarifa encontrado:");
            System.out.println(historial);
        } else {
            System.out.println("No se encontró ningún historial de tarifa con el ID proporcionado.");
        }
    }
    
    public void actualizarHistorialTarifa() {
        System.out.println("Ingrese el ID del historial de tarifa: ");
        long id = scanner.nextLong();
        HistorialTarifa historialExistente = historialTarifaUseCase.obtenertHistorialTarifaPorId(id);
        if (historialExistente != null) {
            System.out.println("Ingrese los nuevos datos:");
    
            List<Tarifa> tarifas = tarifaRepository.obtenerTodasLasTarifas();
            System.out.println("Seleccione la nueva tarifa:");
            mostrarTarifas(tarifas);
            int opcionTarifa = Consola.optionValidation("Ingrese el Id de la Tarifa", 1, tarifas.size());
            Tarifa tarifaSeleccionada = tarifas.get(opcionTarifa - 1);
    
            Date fechaHistorial = Consola.obtenerFechaDeHistorialTarifa();
    
            System.out.print("Ingrese el nuevo valor de la tarifa: ");
            BigDecimal nuevoValor = new BigDecimal(scanner.nextLine());
    
            historialExistente.setTarifa(tarifaSeleccionada);
            historialExistente.setFechaInicio(fechaHistorial);
            historialExistente.setValor(nuevoValor);
    
            historialTarifaUseCase.actualizarHistorialTarifa(historialExistente);
            System.out.println("Historial de Tarifa actualizado correctamente.");
        } else {
            System.out.println("No se encontró ningún historial de tarifa con el ID proporcionado.");
        }
    }
    
    public void eliminarHistorialTarifa() {
        System.out.println("Ingrese el ID del historial de tarifa: ");
        long id = scanner.nextLong();
        HistorialTarifa historialExistente = historialTarifaUseCase.obtenertHistorialTarifaPorId(id);
        if (historialExistente != null) {
            historialTarifaUseCase.eliminarHistorialTarifa(id);
            System.out.println("Historial de Tarifa eliminado correctamente.");
        } else {
            System.out.println("No se encontró ningún historial de tarifa con el ID proporcionado.");
        }
    }
    
}
