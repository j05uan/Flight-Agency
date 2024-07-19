package modelo.Infraestructure.in;

import java.util.List;
import java.util.Scanner;

import fabricante.Domain.Entity.Fabricante;
import fabricante.infraestructure.out.FabricanteRepository;
import modelo.Application.ModeloUseCase;
import modelo.Domain.entity.Modelo;
import utils.Consola;

public class ModeloControlador {

    private final Scanner scanner = new Scanner(System.in);
    private final ModeloUseCase modeloUseCase;
    private final FabricanteRepository fabricanteRepo;

    public ModeloControlador(ModeloUseCase modeloUseCase, FabricanteRepository fabricanteRepo) {
        this.modeloUseCase = modeloUseCase;
        this.fabricanteRepo = fabricanteRepo;
    }

    public void start() {
        int opcion;

        do {
            mostrarMenu();
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (opcion) {
                case 1:
                    crearModelo();
                    break;
                case 2:
                    obtenerTodosLosModelos();
                    break;
                case 3:
                    obtenerModeloPorId();
                    break;
                case 4:
                    actualizarModelo();
                    break;
                case 5:
                    eliminarModelo();
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
        System.out.println("---- Menu de Opciones ----");
        System.out.println("1. Crear Modelo");
        System.out.println("2. Listar Todos los Modelos");
        System.out.println("3. Buscar Modelo por ID");
        System.out.println("4. Actualizar Modelo");
        System.out.println("5. Eliminar Modelo");
        System.out.println("6. Salir");
        System.out.println("Seleccione una opción:");
    }

    public void crearModelo() {
        System.out.println(" ---- Crear Modelo ----");

        // Pedir nombre del modelo
        System.out.println("Ingrese el nombre del modelo: ");
        String nombre = scanner.nextLine();

        // Mostrar fabricantes disponibles
        List<Fabricante> fabricantes = fabricanteRepo.ObtenerTodosLosFabricantes();
        mostrarFabricantes(fabricantes);

        // Pedir ID del fabricante
        System.out.println("Ingrese el ID del Fabricante: ");
        Long idFabricante = scanner.nextLong();
        scanner.nextLine();  // Consumir el salto de línea después de nextLong

        // Buscar fabricante seleccionado por ID
        Fabricante fabricanteSeleccionado = fabricantes.stream()
                .filter(fabricante -> fabricante.getId().equals(idFabricante))
                .findFirst()
                .orElse(null);

        if (fabricanteSeleccionado != null) {
            // Crear el nuevo modelo
            Modelo nuevoModelo = new Modelo();
            nuevoModelo.setNombre(nombre);
            nuevoModelo.setFabricante(fabricanteSeleccionado);

            // Usar ModeloUseCase para crear el modelo
            modeloUseCase.crearModelo(nuevoModelo);
            System.out.println("Modelo creado con éxito.");
        } else {
            System.out.println("Fabricante no encontrado. Verifique el ID y vuelva a intentarlo.");
        }
    }

    public void obtenerTodosLosModelos() {
        System.out.println("---- Listado de Modelos ----");

        List<Modelo> modelos = modeloUseCase.obtenerTodosLosModelos();

        if (!modelos.isEmpty()) {
            for (Modelo modelo : modelos) {
                System.out.printf("ID: %d, Nombre: %s, Fabricante: %s%n",
                        modelo.getId(), modelo.getNombre(), modelo.getFabricante().getNombre());
            }
        } else {
            System.out.println("No hay modelos registrados.");
        }
    }

    public void obtenerModeloPorId() {
        System.out.println("---- Buscar Modelo por ID ----");
        System.out.println("Ingrese el ID del Modelo:");
        Long id = scanner.nextLong();
        scanner.nextLine();  // Consumir el salto de línea después de nextLong

        Modelo modelo = modeloUseCase.obtenerModeloPorId(id);

        if (modelo != null) {
            System.out.printf("Modelo encontrado:%nID: %d, Nombre: %s, Fabricante: %s%n",
                    modelo.getId(), modelo.getNombre(), modelo.getFabricante().getNombre());
        } else {
            System.out.println("No se encontró ningún modelo con el ID proporcionado.");
        }
    }

    public void actualizarModelo() {
        System.out.println("---- Actualizar Modelo ----");
        System.out.println("Ingrese el ID del Modelo a actualizar:");
        Long id = scanner.nextLong();
        scanner.nextLine();  // Consumir el salto de línea después de nextLong

        Modelo modelo = modeloUseCase.obtenerModeloPorId(id);

        if (modelo != null) {
            // Mostrar información actual del modelo
            System.out.printf("Modelo actual:%nID: %d, Nombre: %s, Fabricante: %s%n",
                    modelo.getId(), modelo.getNombre(), modelo.getFabricante().getNombre());

            // Pedir nuevo nombre del modelo
            System.out.println("Ingrese el nuevo nombre del Modelo:");
            String nuevoNombre = scanner.nextLine();

            // Actualizar el modelo con el nuevo nombre
            modelo.setNombre(nuevoNombre);
            modeloUseCase.actualizarModelo(modelo);

            System.out.println("Modelo actualizado correctamente.");
        } else {
            System.out.println("No se encontró ningún modelo con el ID proporcionado.");
        }
    }

    public void eliminarModelo() {
        System.out.println("---- Eliminar Modelo ----");
        System.out.println("Ingrese el ID del Modelo a eliminar:");
        Long id = scanner.nextLong();
        scanner.nextLine();  // Consumir el salto de línea después de nextLong

        Modelo modelo = modeloUseCase.obtenerModeloPorId(id);

        if (modelo != null) {
            modeloUseCase.eliminarModelo(id);
            System.out.println("Modelo eliminado correctamente.");
        } else {
            System.out.println("No se encontró ningún modelo con el ID proporcionado.");
        }
    }

    private void mostrarFabricantes(List<Fabricante> fabricantes) {
        System.out.println("Fabricantes disponibles:");
        for (Fabricante fabricante : fabricantes) {
            System.out.printf("ID: %d, Nombre: %s%n", fabricante.getId(), fabricante.getNombre());
        }
    }
}
