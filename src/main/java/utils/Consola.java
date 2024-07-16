package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Consola {
    public static final Scanner SCANNER = new Scanner(System.in);

    public static void pause() {
        System.out.println("Presiona Enter para continuar...");
        SCANNER.nextLine();
    }

    public static void cleanScreen() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            try {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } catch (Exception e) {
                System.out.println("Error al limpiar la pantalla: " + e.getMessage());
            }
        } else {
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
    }

    public static int optionValidation(String statement, int lower, int upper) {
        while (true) {
            try {
                System.out.println(statement);
                int option = SCANNER.nextInt();
                SCANNER.nextLine(); // Limpiar el buffer del scanner
                if (option >= lower && option <= upper) {
                    return option;
                } else {
                    System.out.printf("Elige un número entre %d y %d.%n", lower, upper);
                }
            } catch (Exception e) {
                System.out.println("Introduce un número válido.");
                SCANNER.nextLine(); // Limpiar el buffer del scanner
            }
        }
    }

    public static Date obtenerFechaDeFabricacion() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);  // Establece que el análisis de fechas no sea permisivo
        Date fechaFabricacion = null;
        boolean fechaValida = false;

        while (!fechaValida) {
            System.out.println("Ingrese la fecha de fabricación (DD/MM/AAAA): ");
            String fechaString = SCANNER.nextLine();
            try {
                fechaFabricacion = sdf.parse(fechaString);
                fechaValida = true;  // La fecha es válida si no se lanza una excepción
            } catch (ParseException e) {
                System.out.println("Fecha inválida. Por favor, ingrese la fecha en el formato correcto (DD/MM/AAAA).");
            }
        }

        return fechaFabricacion;
    }

    public static String leerCadena(String mensaje) {
        System.out.println(mensaje);
        return SCANNER.nextLine();
    }

    public static int leerEntero(String mensaje) {
        System.out.println(mensaje);
        while (!SCANNER.hasNextInt()) {
            System.out.println("Introduce un número entero válido.");
            SCANNER.next(); // Limpiar el buffer del scanner
        }
        return SCANNER.nextInt();
    }

    public static String listar(String mensaje){
        
        
        String sql = "SELECT * FROM ?";
        
        return sql;
        
    }
}
