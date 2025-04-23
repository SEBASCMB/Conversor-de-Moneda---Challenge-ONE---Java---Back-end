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
            final String RESET  = "\u001B[0m";
            final String SEPARATOR = BLUE + "=".repeat(60) + RESET;


            final String[] fromTo = new String[2];
            Map<Integer, Runnable> acciones = ConversionActions.build(fromTo);

            while ( true ){

                displayMenu();
                int option = ConsoleUtils.leerOpcion(scanner, "");

                if ( option == 7 ){
                    ConsoleUtils.mostrarDespedida("👋 ¡Hasta luego y gracias por usar el conversor!", SEPARATOR, GREEN, RESET);
                    break;
                }

                double amount = ConsoleUtils.leerMonto(scanner, YELLOW + "💰 Ingrese el monto a convertir: " + RESET);
                double result = 0;
                
                Runnable accion = acciones.get(option);
                if (accion == null) {
                    ConsoleUtils.mostrarError("❌ Opción no válida, por favor seleccione entre 1 y 7.", RED);
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
        System.out.println(separator);
        System.out.println(CYAN + "Seleccione una opción válida (1-7):" + RESET);
        System.out.println(separator);

    }
}
