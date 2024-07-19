package cliente.infraestructure.in;

import java.util.List;
import java.util.Scanner;

import cliente.Application.ClienteUseCase;
import cliente.Domain.entity.Cliente;
import cliente.infraestructure.out.ClienteRepository;
import tipoDocumento.Domain.entity.TipoDocumento;
import tipoDocumento.infraestructure.out.TipoDocumentoRepository;
import utils.Consola;
import static utils.Consola.cleanScreen;

public class ClienteControlador {
    private final Scanner scanner = new Scanner(System.in);
    private final ClienteUseCase clienteUseCase;
    private final ClienteRepository clienteRepo;
    private final TipoDocumentoRepository tipoDocumentoRepo;

    public ClienteControlador(ClienteRepository clienteRepo, ClienteUseCase clienteUseCase, TipoDocumentoRepository tipoDocumentoRepo) {
        this.clienteRepo = clienteRepo;
        this.clienteUseCase = clienteUseCase;
        this.tipoDocumentoRepo = tipoDocumentoRepo;
    }

    public void start() {
        int opcion;

        do {
            mostrarMenu();
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    cleanScreen();
                    CrearCliente();
                    break;
                case 2:
                    cleanScreen();
                    obtenerTodosLosClientes();
                    break;
                case 3:
                    cleanScreen();
                    obtenerClientePorId();
                    break;
                case 4:
                    cleanScreen();
                    actualizarCliente();
                    break;
                case 5:
                    cleanScreen();
                    eliminarCliente();
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
        System.out.println("---- Menu de Opciones de Cliente ----");
        System.out.println("1. Crear Cliente");
        System.out.println("2. Listar Todos los Clientes");
        System.out.println("3. Buscar Cliente por ID");
        System.out.println("4. Actualizar Cliente");
        System.out.println("5. Eliminar Cliente");
        System.out.println("6. Salir");
        System.out.println("Seleccione una opción:");
    }

    public void CrearCliente() {
        System.out.println("---- Crear Cliente ----");
        System.out.println("Ingrese el nombre del Cliente:");
        String nombre = scanner.nextLine();

        System.out.println("Ingrese la edad del Cliente:");
        int edad = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        List<TipoDocumento> tipoDocumentos = tipoDocumentoRepo.obtenerTodosLosTiposDocumento();
        System.out.println("Seleccione el tipo de Documento:");
        mostrarTipoDocumentos(tipoDocumentos);
        int opcionTipoDocumento = Consola.optionValidation("Ingrese la opción: ", 1, tipoDocumentos.size());
        TipoDocumento documentoSeleccionado = tipoDocumentos.get(opcionTipoDocumento - 1);
        System.out.println("Ingrese el documento:");
        String documento = scanner.nextLine();

        Cliente cliente = new Cliente();
        cliente.setNombre(nombre);
        cliente.setEdad(edad);
        cliente.setTipoDocumento(documentoSeleccionado);
        cliente.setDocumento(documento);
        
        clienteUseCase.CrearCliente(cliente);
        System.out.println("Cliente creado con éxito. ID del cliente: " + cliente.getId());
    }

    public void obtenerTodosLosClientes() {
        System.out.println("---- Listado de Clientes ----");

        List<Cliente> clientes = clienteUseCase.obtenerTodosLosClientes();

        if (!clientes.isEmpty()) {
            for (Cliente cliente : clientes) {
                System.out.printf("ID: %d, Nombre: %s, Edad: %d, Tipo de Documento: %s, Documento: %s%n",
                        cliente.getId(), cliente.getNombre(), cliente.getEdad(),
                        cliente.getTipoDocumento().getTipo(), cliente.getDocumento());
            }
        } else {
            System.out.println("No hay clientes registrados.");
        }
    }

    public void obtenerClientePorId() {
        System.out.println("---- Buscar Cliente por ID ----");
        System.out.println("Ingrese el ID del Cliente:");
        Long id = scanner.nextLong();
        scanner.nextLine();  // Consumir el salto de línea después de nextLong

        Cliente cliente = clienteUseCase.obtenerClientePorId(id);

        if (cliente != null) {
            System.out.printf("Cliente encontrado:%nID: %d, Nombre: %s, Edad: %d, Tipo de Documento: %s, Documento: %s%n",
                    cliente.getId(), cliente.getNombre(), cliente.getEdad(),
                    cliente.getTipoDocumento().getTipo(), cliente.getDocumento());
        } else {
            System.out.println("No se encontró ningún cliente con el ID proporcionado.");
        }
    }

    public void actualizarCliente() {
        System.out.println("---- Actualizar Cliente ----");
        System.out.println("Ingrese el ID del Cliente a actualizar:");
        Long id = scanner.nextLong();
        scanner.nextLine();  // Consumir el salto de línea después de nextLong

        Cliente cliente = clienteUseCase.obtenerClientePorId(id);

        if (cliente != null) {
            // Mostrar información actual del cliente
            System.out.printf("Cliente actual:%nID: %d, Nombre: %s, Edad: %d, Tipo de Documento: %s, Documento: %s%n",
                    cliente.getId(), cliente.getNombre(), cliente.getEdad(),
                    cliente.getTipoDocumento().getTipo(), cliente.getDocumento());

            // Pedir nuevos datos del cliente
            System.out.println("Ingrese el nuevo nombre del Cliente:");
            String nuevoNombre = scanner.nextLine();

            System.out.println("Ingrese la nueva edad del Cliente:");
            int nuevaEdad = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea después de nextInt

            List<TipoDocumento> tipoDocumentos = tipoDocumentoRepo.obtenerTodosLosTiposDocumento();
            System.out.println("Seleccione el nuevo tipo de Documento:");
            mostrarTipoDocumentos(tipoDocumentos);
            int opcionTipoDocumento = Consola.optionValidation("Ingrese la opción: ", 1, tipoDocumentos.size());
            TipoDocumento tipoDocumentoSeleccionado = tipoDocumentos.get(opcionTipoDocumento - 1);
            System.out.println("Ingrese el nuevo documento:");
            String nuevoDocumento = scanner.nextLine();

            // Actualizar el cliente con los nuevos datos
            cliente.setNombre(nuevoNombre);
            cliente.setEdad(nuevaEdad);
            cliente.setTipoDocumento(tipoDocumentoSeleccionado);
            cliente.setDocumento(nuevoDocumento);

            clienteUseCase.actualizarCliente(cliente);

            System.out.println("Cliente actualizado correctamente.");
        } else {
            System.out.println("No se encontró ningún cliente con el ID proporcionado.");
        }
    }

    public void eliminarCliente() {
        System.out.println("---- Eliminar Cliente ----");
        System.out.println("Ingrese el ID del Cliente a eliminar:");
        Long id = scanner.nextLong();
        scanner.nextLine();  // Consumir el salto de línea después de nextLong

        Cliente cliente = clienteUseCase.obtenerClientePorId(id);

        if (cliente != null) {
            clienteUseCase.eliminarCliente(id);
            System.out.println("Cliente eliminado correctamente.");
        } else {
            System.out.println("No se encontró ningún cliente con el ID proporcionado.");
        }
    }

    private void mostrarTipoDocumentos(List<TipoDocumento> tipoDocumentos) {
        for (int i = 0; i < tipoDocumentos.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, tipoDocumentos.get(i).getTipo());
        }
    }
}
