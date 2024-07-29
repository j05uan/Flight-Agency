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
import static utils.Consola.cleanScreen;

public class AvionControlador {
    private final Scanner scanner = new Scanner(System.in);
    private final AvionUseCase avionUseCase;
    

    
    public AvionControlador(AvionUseCase avionUseCase) {
        this.avionUseCase = avionUseCase;
    }

    public void start() {
        int opcion;

        do {
            mostrarMenu();
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (opcion) {
                case 1:
                    cleanScreen();
                    crearAvion();
                    break;
                case 2:
                    cleanScreen();
                    obtenerTodosLosAviones();
                    break;
                case 3:
                    cleanScreen();
                    obtenerAvionPorId();
                    break;
                case 4:
                    cleanScreen();
                    actualizarAvion();
                    break;
                case 5:
                    cleanScreen();
                    eliminarAvion();
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
        System.out.println("---- Menu de Opciones de Avión ----");
        System.out.println("1. Crear Avión");
        System.out.println("2. Listar Todos los Aviones");
        System.out.println("3. Buscar Avión por ID");
        System.out.println("4. Actualizar Avión");
        System.out.println("5. Eliminar Avión");
        System.out.println("6. Salir");
        System.out.println("Seleccione una opción:");
    }

    public void crearAvion() {
        System.out.println("----- Crear Avión -----");
    
        // Obtener la matrícula del avión
        System.out.print("Ingrese la matrícula del avión: ");
        String matricula = scanner.nextLine().trim();
    
        // Obtener la capacidad del avión
        int capacidad;
        try {
            System.out.print("Ingrese la capacidad del avión: ");
            capacidad = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Capacidad inválida. Debe ser un número entero.");
            return;
        }
    
        
        int filas;
        try {
            System.out.print("Ingrese el número de filas del avión: ");
            filas = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Número de filas inválido. Debe ser un número entero.");
            return;
        }
    
        // Obtener el número de columnas del avión
        int columnas;
        try {
            System.out.print("Ingrese el número de columnas de asientos del avión: ");
            columnas = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Número de columnas inválido. Debe ser un número entero.");
            return;
        }
    
        
        if (filas * columnas != capacidad) {
            System.out.println("La capacidad no coincide con el número de asientos y filas.");
            System.out.println("Vuelve a intentarlo.");
            return;  // Salir del método en caso de error
        }
    
        
        Date fechaDeFabricacion = Consola.obtenerFechaDeFabricacion();
    
       
        List<Aerolinea> aerolineas = new AerolineaRepository().obtenerTodasLasAerolineas();
        if (aerolineas.isEmpty()) {
            System.out.println("No hay aerolíneas disponibles.");
            return;
        }
        System.out.println("Seleccione una aerolínea:");
        mostrarAerolineas(aerolineas);
        int opcionAerolinea = Consola.optionValidation("Ingrese el número de la aerolínea: ", 1, aerolineas.size());
        Aerolinea aerolineaSeleccionada = aerolineas.get(opcionAerolinea - 1);
    
    
        List<Modelo> modelos = new ModeloRepository().obtenerTodosLosModelos();
        if (modelos.isEmpty()) {
            System.out.println("No hay modelos disponibles.");
            return;
        }
        System.out.println("Seleccione un modelo:");
        mostrarModelos(modelos);
        int opcionModelo = Consola.optionValidation("Ingrese el número del modelo: ", 1, modelos.size());
        Modelo modeloSeleccionado = modelos.get(opcionModelo - 1);
    
        
        Avion nuevoAvion = new Avion();
        nuevoAvion.setMatricula(matricula);
        nuevoAvion.setCapacidad(capacidad);
        nuevoAvion.setFechaFabricacion(fechaDeFabricacion);
        nuevoAvion.setAerolinea(aerolineaSeleccionada);
        nuevoAvion.setModelo(modeloSeleccionado);
        nuevoAvion.setColumnas(columnas);
        nuevoAvion.setFilas(filas);
    
        AvionRepository avionRepo = new AvionRepository();
        avionRepo.CrearAvion(nuevoAvion);
    
        System.out.println("Avión creado con éxito. ID del avión: " + nuevoAvion.getId());
    }
    

    public void obtenerTodosLosAviones() {
        System.out.println("---- Listado de Aviones ----");

        List<Avion> aviones = avionUseCase.obtenerTodosLosAviones();

        if (!aviones.isEmpty()) {
            for (Avion avion : aviones) {
                System.out.printf("ID: %d, Matrícula: %s, Capacidad: %d, Fecha de Fabricación: %s, Aerolínea: %s, Modelo: %s%n",
                        avion.getId(), avion.getMatricula(), avion.getCapacidad(), avion.getFechaFabricacion(),
                        avion.getAerolinea().getNombre(), avion.getModelo().getNombre());
            }
        } else {
            System.out.println("No hay aviones registrados.");
        }
    }

    public void obtenerAvionPorId() {
        System.out.println("---- Buscar Avión por ID ----");
        System.out.println("Ingrese el ID del Avión:");
        Long id = scanner.nextLong();
        scanner.nextLine();  // Consumir el salto de línea después de nextLong

        Avion avion = avionUseCase.obtenerAvionPorId(id);

        if (avion != null) {
            System.out.printf("Avión encontrado:%nID: %d, Matrícula: %s, Capacidad: %d, Fecha de Fabricación: %s, Aerolínea: %s, Modelo: %s%n",
                    avion.getId(), avion.getMatricula(), avion.getCapacidad(), avion.getFechaFabricacion(),
                    avion.getAerolinea().getNombre(), avion.getModelo().getNombre());
        } else {
            System.out.println("No se encontró ningún avión con el ID proporcionado.");
        }
    }

    public void actualizarAvion() {
        System.out.println("---- Actualizar Avión ----");
        System.out.println("Ingrese el ID del Avión a actualizar:");
        Long id = scanner.nextLong();
        scanner.nextLine();  // Consumir el salto de línea después de nextLong

        Avion avion = avionUseCase.obtenerAvionPorId(id);

        if (avion != null) {
            // Mostrar información actual del avión
            System.out.printf("Avión actual:%nID: %d, Matrícula: %s, Capacidad: %d, Fecha de Fabricación: %s, Aerolínea: %s, Modelo: %s%n",
                    avion.getId(), avion.getMatricula(), avion.getCapacidad(), avion.getFechaFabricacion(),
                    avion.getAerolinea().getNombre(), avion.getModelo().getNombre());

            // Pedir nuevos datos del avión
            System.out.println("Ingrese la nueva matrícula del avión:");
            String nuevaMatricula = scanner.nextLine();

            System.out.println("Ingrese la nueva capacidad del avión:");
            int nuevaCapacidad = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea después de nextInt

            Date nuevaFechaFabricacion = Consola.obtenerFechaDeFabricacion();

            List<Aerolinea> aerolineas = new AerolineaRepository().obtenerTodasLasAerolineas();
            System.out.println("Seleccione una aerolínea:");
            mostrarAerolineas(aerolineas);
            int opcionAerolinea = Consola.optionValidation("Ingrese el número de la aerolínea: ", 1, aerolineas.size());
            Aerolinea aerolineaSeleccionada = aerolineas.get(opcionAerolinea - 1);

            List<Modelo> modelos = new ModeloRepository().obtenerTodosLosModelos();
            System.out.println("Seleccione un modelo:");
            mostrarModelos(modelos);
            int opcionModelo = Consola.optionValidation("Ingrese el número del modelo: ", 1, modelos.size());
            Modelo modeloSeleccionado = modelos.get(opcionModelo - 1);

            // Actualizar el avión con los nuevos datos
            avion.setMatricula(nuevaMatricula);
            avion.setCapacidad(nuevaCapacidad);
            avion.setFechaFabricacion(nuevaFechaFabricacion);
            avion.setAerolinea(aerolineaSeleccionada);
            avion.setModelo(modeloSeleccionado);

            avionUseCase.actualizarAvion(avion);

            System.out.println("Avión actualizado correctamente.");
        } else {
            System.out.println("No se encontró ningún avión con el ID proporcionado.");
        }
    }

    public void eliminarAvion() {
        System.out.println("---- Eliminar Avión ----");
        System.out.println("Ingrese el ID del Avión a eliminar:");
        Long id = scanner.nextLong();
        scanner.nextLine();  // Consumir el salto de línea después de nextLong

        Avion avion = avionUseCase.obtenerAvionPorId(id);

        if (avion != null) {
            avionUseCase.eliminarAvion(id);
            System.out.println("Avión eliminado correctamente.");
        } else {
            System.out.println("No se encontró ningún avión con el ID proporcionado.");
        }
    }

    private void mostrarAerolineas(List<Aerolinea> aerolineas) {
        for (int i = 0; i < aerolineas.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, aerolineas.get(i).getNombre());
        }
    }

    private void mostrarModelos(List<Modelo> modelos) {
        for (int i = 0; i < modelos.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, modelos.get(i).getNombre());
        }
    }
}
