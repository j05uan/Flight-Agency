package avion.Infraestructure.in;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import aerolinea.Domain.Entity.Aerolinea;
import aerolinea.Infraestructure.out.AerolineaRepository;
import avion.Application.AvionUseCase;
import avion.Domain.Entity.Avion;
import avion.Infraestructure.out.AvionRepository;
import modelo.Domain.entity.Modelo;
import modelo.Infraestructure.out.ModeloRepository;
import utils.Consola;


public class AvionControlador {
    Scanner scanner = new Scanner(System.in);

    private final AvionUseCase avionUseCase;

    public AvionControlador(AvionUseCase avionUseCase) {
        this.avionUseCase = avionUseCase;    
    }

    public void crearAvion() {
        AvionRepository avionRepo = new AvionRepository();
        AerolineaRepository aerolineaRepo = new AerolineaRepository();
        ModeloRepository modeloRepo = new ModeloRepository();

        System.out.println("----- Menu Crear Avion --------");
        System.out.println("Ingrese la matrícula del avión: ");
        String matricula = Consola.SCANNER.nextLine();
        System.out.println("Ingrese la capacidad del avión: ");
        int capacidad = Consola.SCANNER.nextInt();
        Consola.SCANNER.nextLine();  // Limpiar el buffer después de leer el entero

        
        Date fechaDeFabricacion = Consola.obtenerFechaDeFabricacion();
        List<Aerolinea> aerolineas = aerolineaRepo.obtenerTodasLasAerolineas();
        System.out.println("Seleccione una aerolínea:");
        for (int i = 0; i < aerolineas.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, aerolineas.get(i).getNombre());
        }
        int opcionAerolinea = Consola.optionValidation("Ingrese el número de la aerolínea: ", 1, aerolineas.size());
        Aerolinea aerolineaSeleccionada = aerolineas.get(opcionAerolinea - 1);

        // Listar modelos disponibles
        List<Modelo> modelos = modeloRepo.obtenerTodosLosModelos();
        System.out.println("Seleccione un modelo:");
        for (int i = 0; i < modelos.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, modelos.get(i).getNombre());
        }
        int opcionModelo = Consola.optionValidation("Ingrese el número del modelo: ", 1, modelos.size());
        Modelo modeloSeleccionado = modelos.get(opcionModelo - 1);

        // Crear el nuevo avión
        Avion nuevoAvion = new Avion();
        nuevoAvion.setMatricula(matricula);
        nuevoAvion.setCapacidad(capacidad);
        nuevoAvion.setFechaFabricacion(fechaDeFabricacion);
        nuevoAvion.setAerolinea(aerolineaSeleccionada);
        nuevoAvion.setModelo(modeloSeleccionado);

        // Guardar el avión en la base de datos
        avionRepo.CrearAvion(nuevoAvion);

        System.out.println("Avión creado con éxito. ID del avión: " + nuevoAvion.getId());
    }

    
}
