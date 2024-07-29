package reserva.infraestructure.in;

import java.util.Scanner;
import reserva.Application.ReservaUseCase;
import static utils.Consola.cleanScreen;

public class ReservaControlador {

    private final Scanner scanner = new Scanner(System.in);
    private final ReservaUseCase reservaUseCase;
    public ReservaControlador(ReservaUseCase reservaUseCase) {
        this.reservaUseCase = reservaUseCase;
    }

    public void start() {
        int opcion;

        do {
            mostrarMenu();
            opcion = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcion) {
                case 1:
                    cleanScreen();
                    break;
                case 2:
                    cleanScreen();
                    break;
                case 3:
                    cleanScreen();
                    break;
                case 4:
                    cleanScreen();
                    break;
                case 5:
                    cleanScreen();
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
        System.out.println("---- Menu de Opciones de Reserva ----");
        System.out.println("1. Crear Reserva");
        System.out.println("2. Listar Todos los Reservas");
        System.out.println("3. Buscar Reserva por ID");
        System.out.println("4. Actualizar Reserva");
        System.out.println("5. Eliminar Reserva");
        System.out.println("6. Salir");
        System.out.println("Seleccione una opción:");
    }

    public void crearReserva(){
        System.out.println("Ingrese ");
    }


}
