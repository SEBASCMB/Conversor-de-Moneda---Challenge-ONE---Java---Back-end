package main.java.com.conversor.util;

import java.util.Scanner;
import java.util.logging.Logger;

public class ConsoleUtils {
    
    private static final Logger LOGGER = LoggerConfig.getLogger();
    
    private ConsoleUtils() {
        // Constructor privado para evitar instanciación
    }
    
    public static int leerOpcion(Scanner scanner, String mensaje) {
        System.out.print(mensaje);
        return scanner.nextInt();
    }

    public static double leerMonto(Scanner scanner, String mensaje) {
        System.out.print(mensaje);
        return scanner.nextDouble();
    }

    public static void mostrarResultado(double cantidad, String origen, double resultado, String destino, String colores) {
        LOGGER.info(String.format(colores + "%.2f %s = %.2f %s", cantidad, origen, resultado, destino));
    }

    public static void mostrarError(String mensaje, String color) {
        LOGGER.info(color + mensaje);
    }

    public static void mostrarSeparador(String separador) {
        LOGGER.info(separador);
    }

    public static void mostrarDespedida(String mensaje, String separador, String color, String reset) {
        LOGGER.info(separador);
        LOGGER.info(color + mensaje + reset);
        LOGGER.info(separador);
    }
} 