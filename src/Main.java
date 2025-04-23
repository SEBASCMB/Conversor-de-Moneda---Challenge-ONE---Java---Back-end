import service.ExchangeRateApiService;
import service.ExchangeRateResponse;
import java.util.Scanner;
import java.util.Map;
import util.ConsoleUtils;
import util.ConversionActions;

public class Main {
    public static void main(String[] args) throws Exception {

        ExchangeRateApiService api = new ExchangeRateApiService();
        ExchangeRateResponse rates = api.getLatestRates();

        Scanner scanner = new Scanner(System.in);

        try {

            final String BLUE   = "\u001B[34m";
            final String GREEN  = "\u001B[32m";
            final String YELLOW = "\u001B[33m";
            final String CYAN   = "\u001B[36m";
            final String RED    = "\u001B[31m";
            final String MAGENTA = "\u001B[35m";
            final String RESET  = "\u001B[0m";
            final String SEPARATOR = BLUE + "=".repeat(60) + RESET;


            final String[] fromTo = new String[2];
            Map<Integer, Runnable> acciones = ConversionActions.build(fromTo);

            while ( true ){

                displayMenu();
                int option = ConsoleUtils.leerOpcion(scanner, "");

                if ( option == 8 ) {
                    mostrarTodasLasDivisas(api, rates, scanner);
                    continue;
                }

                if ( option == 7 ){
                    ConsoleUtils.mostrarDespedida("👋 ¡Hasta luego y gracias por usar el conversor!", SEPARATOR, GREEN, RESET);
                    break;
                }

                double amount = ConsoleUtils.leerMonto(scanner, YELLOW + "💰 Ingrese el monto a convertir: " + RESET);
                double result = 0;
                
                Runnable accion = acciones.get(option);
                if (accion == null) {
                    ConsoleUtils.mostrarError("❌ Opción no válida, por favor seleccione entre 1 y 8.", RED);
                    continue;
                }

                accion.run();
                String from = fromTo[0];
                String to = fromTo[1];

                ConsoleUtils.mostrarSeparador(SEPARATOR);
                
                if (from.equals("USD")) {

                    double rate = rates.conversion_rates.get(to);
                    result = amount * rate;
                    ConsoleUtils.mostrarResultado(amount, from, result, to, GREEN + "\uD83D\uDCB6 Resultado: " + CYAN);
                    ConsoleUtils.mostrarSeparador(SEPARATOR);
                    continue;

                }
                
                double rate = rates.conversion_rates.get(from);
                result = amount / rate;
                ConsoleUtils.mostrarResultado(amount, from, result, to, GREEN + "\uD83D\uDCB6 Resultado: " + CYAN);
                ConsoleUtils.mostrarSeparador(SEPARATOR);

            }
        } finally {
            scanner.close(); 
        }

    }

    private static void displayMenu(){

        final String BLUE   = "\u001B[34m";
        final String GREEN  = "\u001B[32m";
        final String YELLOW = "\u001B[33m";
        final String CYAN   = "\u001B[36m";
        final String RED    = "\u001B[31m";
        final String MAGENTA = "\u001B[35m";
        final String RESET  = "\u001B[0m";

        final int WIDTH = 60;
        String separator = BLUE + "=".repeat(WIDTH) + RESET;
        String title = GREEN + "💱 Sea bienvenido al conversor de moneda 💱" + RESET;

        System.out.println();
        System.out.println(separator);
        System.out.println(title);
        System.out.println(separator);
        System.out.println();

        System.out.printf("%-3s %-35s %-25s\n", CYAN+"#"+RESET, CYAN+"Conversión"+RESET, CYAN+"Descripción"+RESET);
        System.out.println(separator);
        System.out.printf("%-3s %-35s %-25s\n", BLUE+"1"+RESET, "Dólar => Peso Colombiano", "💲 USD a COP");
        System.out.printf("%-3s %-35s %-25s\n", GREEN+"2"+RESET, "Peso Colombiano => Dólar", "💲 COP a USD");
        System.out.printf("%-3s %-35s %-25s\n", CYAN+"3"+RESET, "Dólar => Real Brasileño", "💲 USD a BRL");
        System.out.printf("%-3s %-35s %-25s\n", GREEN+"4"+RESET, "Real Brasileño => Dólar", "💲 BRL a USD");
        System.out.printf("%-3s %-35s %-25s\n", YELLOW+"5"+RESET, "Dólar => Euro", "💲 USD a EUR");
        System.out.printf("%-3s %-35s %-25s\n", YELLOW+"6"+RESET, "Euro => Dólar", "💲 EUR a USD");
        System.out.printf("%-3s %-35s %-25s\n", RED+"7"+RESET, "Salir", "🚪 Salir del programa");
        System.out.printf("%-3s %-35s %-25s\n", MAGENTA+"8"+RESET, "Mostrar todas las divisas", "🌍 Lista de divisas soportadas");
        System.out.println(separator);
        System.out.println(CYAN + "Seleccione una opción válida (1-8):" + RESET);
        System.out.println(separator);

    }

    private static void mostrarTodasLasDivisas(ExchangeRateApiService api, ExchangeRateResponse rates, Scanner scanner) {
        final String BLUE   = "\u001B[34m";
        final String GREEN  = "\u001B[32m";
        final String YELLOW = "\u001B[33m";
        final String CYAN   = "\u001B[36m";
        final String RED    = "\u001B[31m";
        final String MAGENTA = "\u001B[35m";
        final String RESET  = "\u001B[0m";
        final String BOLD   = "\u001B[1m";

        try {

            String[][] codes = api.getSupportedCodes();
            System.out.println();
            System.out.println(MAGENTA + BOLD + "🌍 Listado de divisas soportadas" + RESET);
            String header = CYAN + BOLD + String.format("| %-8s | %-38s |", "Código", "Nombre") + RESET;
            String border = BLUE + "+----------+----------------------------------------+" + RESET;
            System.out.println(border);
            System.out.println(header);
            System.out.println(border);

            for (String[] code : codes) {

                System.out.printf("| %-8s | %-38s |%n", YELLOW + code[0] + RESET, GREEN + code[1] + RESET);

            }

            System.out.println(border);

            System.out.println();
            System.out.print(BOLD + CYAN + "💡 Ingrese el " + YELLOW + "código" + CYAN + " de la moneda de " + GREEN + "origen" + CYAN + ": " + RESET);
            String from = scanner.next().toUpperCase();
            System.out.print(BOLD + CYAN + "💡 Ingrese el " + YELLOW + "código" + CYAN + " de la moneda de " + GREEN + "destino" + CYAN + ": " + RESET);
            String to = scanner.next().toUpperCase();
            System.out.print(BOLD + CYAN + "💸 Ingrese el " + YELLOW + "monto" + CYAN + " a convertir: " + RESET);
            double amount = scanner.nextDouble();

            Double rateFrom = rates.conversion_rates.get(from);
            Double rateTo = rates.conversion_rates.get(to);

            if (rateFrom == null || rateTo == null) {

                System.out.println(RED + BOLD + "❌ Uno de los códigos ingresados no es válido." + RESET);
                return;

            }

            double result = (amount / rateFrom) * rateTo;
            System.out.println();
            System.out.println(GREEN + BOLD + "\uD83D\uDCB6 Resultado de la conversión:" + RESET);
            System.out.printf(BOLD + YELLOW + "%.2f %s" + RESET + CYAN + " = " + RESET + BOLD + GREEN + "%.2f %s" + RESET + "%n", amount, from, result, to);
            System.out.println();

        } catch (Exception e) {

            System.out.println(RED + BOLD + "Error al obtener la lista de divisas o al convertir: " + e.getMessage() + RESET);
            
        }
    }
}
