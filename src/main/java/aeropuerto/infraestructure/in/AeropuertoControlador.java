package aeropuerto.infraestructure.in;

import java.util.Scanner;

import aeropuerto.application.ActualizarAeropuerto;
import aeropuerto.application.AeropueroUseCase;
import aeropuerto.application.EliminarAeropuertoUseCase;
import aeropuerto.application.EncontrarAeropuertoUseCase;

public class AeropuertoControlador {

    private AeropueroUseCase aeropueroUseCase;
    private ActualizarAeropuerto actualizarAeropuerto;
    private EncontrarAeropuertoUseCase encontrarAeropuertoUseCase;
    private EliminarAeropuertoUseCase eliminarAeropuertoUseCase;
    

    Scanner scanner = new Scanner(System.in);

    public AeropuertoControlador(ActualizarAeropuerto actualizarAeropuerto, AeropueroUseCase aeropueroUseCase, EliminarAeropuertoUseCase eliminarAeropuertoUseCase, EncontrarAeropuertoUseCase encontrarAeropuertoUseCase) {
        this.actualizarAeropuerto = actualizarAeropuerto;
        this.aeropueroUseCase = aeropueroUseCase;
        this.eliminarAeropuertoUseCase = eliminarAeropuertoUseCase;
        this.encontrarAeropuertoUseCase = encontrarAeropuertoUseCase;
    }
    
    public void start(){

        boolean salir = false;

        while (!salir) {
            System.out.println("Seleccione la opcion ");
            System.out.println("1. Crear Aeropuerto");
            System.out.println("2. Actualizar Aeropuerto");
            System.out.println("3. Eliminar Aeroppuerto ");
            System.out.println("4. Encontrar Aeropuerto");
            System.out.println("5.Salir");
            try {
                int opcion = Integer.parseInt(scanner.nextLine());
                if (opcion > 5 || opcion < 0 || Double.isNaN(opcion)) {
                    break;  
                }
                
                switch (opcion) {
                    case 1:
                        System.out.println("--- Menu Crear Aeropuerto---");
                        System.out.println("Ingrese el nombre del aeropuerto: ");
                        String nombre = scanner.nextLine();
                        System.out.println("Ingrese el Id de la Ciudad: ");
                        int Ciudad = scanner.nextInt();                        
                        break;
                    default:
                        throw new AssertionError();
                }
                
            } catch (Exception e) {
            }
        }

    }
   
    

}
