package ruta.Infraestructure.in;

import java.util.Scanner;

import ruta.Application.RutaUseCase;

public class RutaControlador {
    Scanner scanner = new Scanner(System.in);

    private final RutaUseCase rutaUseCase;

    public RutaControlador(Scanner scanner, RutaUseCase rutaUseCase) {
        this.scanner = scanner;
        this.rutaUseCase = rutaUseCase;
    };
    
    public void crearRuta(){
        System.out.println("---Menu Crear Ruta---");
        System.out.println("Ingrese el nombre de la ruta3");
    }
}
