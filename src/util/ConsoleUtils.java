package util;

import java.util.Scanner;

public class ConsoleUtils {
    public static int leerOpcion(Scanner scanner, String mensaje) {
        System.out.print(mensaje);
        return scanner.nextInt();
    }

    public static double leerMonto(Scanner scanner, String mensaje) {
        System.out.print(mensaje);
        return scanner.nextDouble();
    }

    public static void mostrarResultado(double cantidad, String origen, double resultado, String destino, String colores) {
        System.out.printf(colores + "%.2f %s = %.2f %s\n", cantidad, origen, resultado, destino);
    }

    public static void mostrarError(String mensaje, String color) {
        System.out.println(color + mensaje);
    }

    public static void mostrarSeparador(String separador) {
        System.out.println(separador);
    }

    public static void mostrarDespedida(String mensaje, String separador, String color, String reset) {
        System.out.println(separador);
        System.out.println(color + mensaje + reset);
        System.out.println(separador);
    }
}
