package aerolinea.Infraestructure.in;

import java.util.List;
import java.util.Scanner;

import aerolinea.Domain.Entity.Aerolinea;
import aerolinea.application.AerolineaUseCase;

public class AerolineaControlador {

    Scanner scanner = new Scanner(System.in);

    private final AerolineaUseCase aerolineaUseCase;

    public AerolineaControlador(AerolineaUseCase aerolineaUseCase) {
        this.aerolineaUseCase = aerolineaUseCase;
    }

    public void crearAerolinea() {
        System.out.println("---- Crear Aerolínea ----");
    
        System.out.println("Ingrese el nombre de la Aerolínea: ");
        String nombre = scanner.nextLine();
    
        Aerolinea aerolinea = new Aerolinea();
        aerolinea.setNombre(nombre);
    
        aerolineaUseCase.crearAerolinea(aerolinea);
    
        System.out.println("Aerolínea creada con éxito.");
    }
    public void listarAerolineas() {
    System.out.println("---- Listado de Aerolíneas ----");

    List<Aerolinea> aerolineas = aerolineaUseCase.obtenerTodasLasAerolineas();

    if (aerolineas.isEmpty()) {
        System.out.println("No hay aerolíneas registradas.");
    } else {
        for (Aerolinea aerolinea : aerolineas) {
            System.out.printf("ID: %d, Nombre: %s%n", aerolinea.getId(), aerolinea.getNombre());
        }
    }
}
public void actualizarAerolinea() {
    System.out.println("---- Actualizar Aerolínea ----");

    // Listar todas las aerolíneas
    listarAerolineas();

    System.out.println("Ingrese el ID de la Aerolínea que desea actualizar: ");
    Long id = Long.parseLong(scanner.nextLine());

    Aerolinea aerolineaExistente = aerolineaUseCase.obtenerAerolineaPorId(id);

    if (aerolineaExistente != null) {
        System.out.println("Ingrese el nuevo nombre de la Aerolínea: ");
        String nuevoNombre = scanner.nextLine();

        aerolineaExistente.setNombre(nuevoNombre);

        aerolineaUseCase.actualizarAerolinea(aerolineaExistente);

        System.out.println("Aerolínea actualizada con éxito.");
    } else {
        System.out.println("No se encontró ninguna aerolínea con el ID proporcionado.");
    }
}



}
