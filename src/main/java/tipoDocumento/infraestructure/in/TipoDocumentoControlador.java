package tipoDocumento.infraestructure.in;

import java.util.List;
import java.util.Scanner;

import tipoDocumento.Applicacion.TipoDocumentoUseCase;
import tipoDocumento.Domain.entity.TipoDocumento;

public class TipoDocumentoControlador {
    private final TipoDocumentoUseCase tipoDocumentoUseCase;
    private final Scanner scanner;

    public TipoDocumentoControlador(TipoDocumentoUseCase tipoDocumentoUseCase) {
        this.tipoDocumentoUseCase = tipoDocumentoUseCase;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean salir = false;

        while (!salir) {
            mostrarMenu();
            try {
                int opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1:
                        crearTipoDocumento();
                        break;
                    case 2:
                        obtenerTodosLosTiposDocumento();
                        break;
                    case 3:
                        obtenerTipoDocumentoPorId();
                        break;
                    case 4:
                        actualizarTipoDocumento();
                        break;
                    case 5:
                        eliminarTipoDocumento();
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
            } catch (Exception e) {
                System.out.println("Ha ocurrido un error: " + e.getMessage());
            }
        }

        scanner.close();
    }

    private void mostrarMenu() {
        System.out.println("Seleccione la opción:");
        System.out.println("1. Crear Tipo de Documento");
        System.out.println("2. Obtener Todos los Tipos de Documento");
        System.out.println("3. Obtener Tipo de Documento por ID");
        System.out.println("4. Actualizar Tipo de Documento");
        System.out.println("5. Eliminar Tipo de Documento");
        System.out.println("6. Salir");
    }

    private void crearTipoDocumento() {
        System.out.println("--- Menú Crear Tipo de Documento ---");
        System.out.print("Ingrese el nombre del tipo de documento: ");
        String nombre = scanner.nextLine();
        Long id = (long) (tipoDocumentoUseCase.obtenerTodosLosTiposDocumento().size() + 1);  // Generador simple de ID
        TipoDocumento tipoDocumento = new TipoDocumento();
        tipoDocumentoUseCase.crearTipoDocumento(tipoDocumento);
        System.out.println("Tipo de Documento creado con éxito.");
    }

    private void obtenerTodosLosTiposDocumento() {
        System.out.println("--- Menú Obtener Todos los Tipos de Documento ---");
        List<TipoDocumento> tiposDocumento = tipoDocumentoUseCase.obtenerTodosLosTiposDocumento();
        if (tiposDocumento.isEmpty()) {
            System.out.println("No hay tipos de documento disponibles.");
        } else {
            tiposDocumento.forEach(System.out::println);
        }
    }

    private void obtenerTipoDocumentoPorId() {
        System.out.println("--- Menú Obtener Tipo de Documento por ID ---");
        System.out.print("Ingrese el ID del tipo de documento: ");
        try {
            Long id = Long.parseLong(scanner.nextLine());
            TipoDocumento tipoDocumento = tipoDocumentoUseCase.obtenerTipoDocumentoPorId(id);
            if (tipoDocumento != null) {
                System.out.println(tipoDocumento);
            } else {
                System.out.println("Tipo de Documento no encontrado.");
            }
        } catch (NumberFormatException e) {
            System.out.println("ID inválido. Por favor, ingrese un número entero.");
        }
    }

    private void actualizarTipoDocumento() {
        System.out.println("--- Menú Actualizar Tipo de Documento ---");
        System.out.print("Ingrese el ID del tipo de documento que desea actualizar: ");
        try {
            Long id = Long.parseLong(scanner.nextLine());
            TipoDocumento tipoDocumento = tipoDocumentoUseCase.obtenerTipoDocumentoPorId(id);
            if (tipoDocumento != null) {
                System.out.print("Ingrese el nuevo nombre del tipo de documento: ");
                String nuevoNombre = scanner.nextLine();
                tipoDocumento.setTipo(nuevoNombre);
                tipoDocumentoUseCase.actualizarTipoDocumento(tipoDocumento);
                System.out.println("Tipo de Documento actualizado con éxito.");
            } else {
                System.out.println("Tipo de Documento no encontrado.");
            }
        } catch (NumberFormatException e) {
            System.out.println("ID inválido. Por favor, ingrese un número entero.");
        }
    }

    private void eliminarTipoDocumento() {
        System.out.println("--- Menú Eliminar Tipo de Documento ---");
        System.out.print("Ingrese el ID del tipo de documento que desea eliminar: ");
        try {
            Long id = Long.parseLong(scanner.nextLine());
            tipoDocumentoUseCase.eliminarTipoDocumento(id);
            System.out.println("Tipo de Documento eliminado con éxito.");
        } catch (NumberFormatException e) {
            System.out.println("ID inválido. Por favor, ingrese un número entero.");
        }
    }
}
