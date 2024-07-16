package fabricante.infraestructure.in;

import java.util.Scanner;

import fabricante.Application.FabricanteUseCase;
import fabricante.Domain.Entity.Fabricante;
import utils.Consola;

public class FabricanteControlador {

    Scanner scanner = new Scanner(System.in);

    private final FabricanteUseCase fabricanteUseCase;

    public FabricanteControlador(FabricanteUseCase fabricanteUseCase) {
        this.fabricanteUseCase = fabricanteUseCase;
    }

    public void crearFabricante (){
        System.out.println("---- Menu Crear Fabricante---");

        System.out.println("Ingrese el nombre del Fabricante");
        String nombre = Consola.SCANNER.nextLine();
        
        Fabricante fabricante = new Fabricante();
        fabricante.setNombre(nombre);


    }

}
