package main.java.com.conversor.view;

public class MenuView {
    
    private static final String BLUE = "\u001B[34m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String CYAN = "\u001B[36m";
    private static final String RED = "\u001B[31m";
    private static final String MAGENTA = "\u001B[35m";
    private static final String RESET = "\u001B[0m";
    private static final int WIDTH = 60;
    
    public void mostrarMenu() {
        String separator = BLUE + "=".repeat(WIDTH) + RESET;
        String title = GREEN + "💱 Sea bienvenido al conversor de moneda 💱" + RESET;

        System.out.println();
        System.out.println(separator);
        System.out.println(title);
        System.out.println(separator);
        System.out.println();

        System.out.printf("%-3s %-35s %-25s%n", CYAN+"#"+RESET, CYAN+"Conversión"+RESET, CYAN+"Descripción"+RESET);
        System.out.println(separator);
        System.out.printf("%-3s %-35s %-25s%n", BLUE+"1"+RESET, "Dólar => Peso Colombiano", "💲 USD a COP");
        System.out.printf("%-3s %-35s %-25s%n", GREEN+"2"+RESET, "Peso Colombiano => Dólar", "💲 COP a USD");
        System.out.printf("%-3s %-35s %-25s%n", CYAN+"3"+RESET, "Dólar => Real Brasileño", "💲 USD a BRL");
        System.out.printf("%-3s %-35s %-25s%n", GREEN+"4"+RESET, "Real Brasileño => Dólar", "💲 BRL a USD");
        System.out.printf("%-3s %-35s %-25s%n", YELLOW+"5"+RESET, "Dólar => Euro", "💲 USD a EUR");
        System.out.printf("%-3s %-35s %-25s%n", YELLOW+"6"+RESET, "Euro => Dólar", "💲 EUR a USD");
        System.out.printf("%-3s %-35s %-25s%n", RED+"7"+RESET, "Salir", "🚪 Salir del programa");
        System.out.printf("%-3s %-35s %-25s%n", MAGENTA+"8"+RESET, "Mostrar todas las divisas", "🌍 Lista de divisas soportadas");
        System.out.println(separator);
        System.out.println(CYAN + "Seleccione una opción válida (1-8):" + RESET);
        System.out.println(separator);
    }
} 