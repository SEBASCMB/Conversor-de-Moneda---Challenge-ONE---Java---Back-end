package main.java.com.conversor.controller;

import main.java.com.conversor.model.ExchangeRateResponse;
import main.java.com.conversor.service.ExchangeRateApiService;
import main.java.com.conversor.service.CurrencyConverter;
import main.java.com.conversor.util.ConsoleUtils;
import main.java.com.conversor.util.ConversionActions;
import main.java.com.conversor.util.LoggerConfig;
import main.java.com.conversor.view.MenuView;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainController {
    
    private static final String BLUE = "\u001B[34m";
    private static final String GREEN = "\u001B[32m";
    private static final String RED = "\u001B[31m";
    private static final String YELLOW = "\u001B[33m";
    private static final String CYAN = "\u001B[36m";
    private static final String MAGENTA = "\u001B[35m";
    private static final String RESET = "\u001B[0m";
    private static final String BOLD = "\u001B[1m";
    private static final int OPCION_LISTAR_DIVISAS = 8;
    private static final int OPCION_SALIR = 7;
    private static final Logger LOGGER = LoggerConfig.getLogger();
    
    private final ExchangeRateApiService api;
    private final Scanner scanner;
    private final MenuView menuView;
    private ExchangeRateResponse rates;
    
    public MainController() {
        this.api = new ExchangeRateApiService();
        this.scanner = new Scanner(System.in);
        this.menuView = new MenuView();
    }
    
    public void iniciar() {
        try {
            rates = api.getLatestRates();
            ejecutarMenu();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            LOGGER.log(Level.SEVERE, "Proceso interrumpido: {0}", e.getMessage());
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error de E/S: {0}", e.getMessage());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al iniciar la aplicación: {0}", e.getMessage());
        } finally {
            scanner.close();
        }
    }
    
    private void ejecutarMenu() {
        final String[] fromTo = new String[2];
        Map<Integer, Runnable> acciones = ConversionActions.build(fromTo);
        final String SEPARATOR = BLUE + "=".repeat(60) + RESET;
        boolean ejecutando = true;
        
        while (ejecutando) {
            menuView.mostrarMenu();
            int option = ConsoleUtils.leerOpcion(scanner, "");
            
            if (option == OPCION_LISTAR_DIVISAS) {
                mostrarTodasLasDivisas();
            } else if (option == OPCION_SALIR) {
                ConsoleUtils.mostrarDespedida("👋 ¡Hasta luego y gracias por usar el conversor!", SEPARATOR, GREEN, RESET);
                ejecutando = false;
            } else {
                procesarConversion(option, fromTo, acciones, SEPARATOR);
            }
        }
    }
    
    private void procesarConversion(int option, String[] fromTo, Map<Integer, Runnable> acciones, String SEPARATOR) {
        double amount = ConsoleUtils.leerMonto(scanner, YELLOW + "💰 Ingrese el monto a convertir: " + RESET);
        
        Runnable accion = acciones.get(option);
        if (accion != null) {
            accion.run();
            String from = fromTo[0];
            String to = fromTo[1];
            
            ConsoleUtils.mostrarSeparador(SEPARATOR);
            
            double result = CurrencyConverter.convertirMoneda(amount, from, to, rates);
            ConsoleUtils.mostrarResultado(amount, from, result, to, GREEN + "\uD83D\uDCB6 Resultado: " + CYAN);
            ConsoleUtils.mostrarSeparador(SEPARATOR);
        } else {
            ConsoleUtils.mostrarError("❌ Opción no válida, por favor seleccione entre 1 y 8.", RED);
        }
    }
    
    private void mostrarTodasLasDivisas() {
        final String SEPARATOR = BLUE + "=".repeat(60) + RESET;
        
        try {
            String[][] codes = api.getSupportedCodes();
            LOGGER.info("\n");
            LOGGER.info(MAGENTA + BOLD + "🌍 Listado de divisas soportadas" + RESET);
            String header = CYAN + BOLD + String.format("| %-8s | %-38s |", "Código", "Nombre") + RESET;
            String border = BLUE + "+----------+----------------------------------------+" + RESET;
            LOGGER.info(border);
            LOGGER.info(header);
            LOGGER.info(border);
            
            for (String[] code : codes) {
                LOGGER.info(String.format("| %-8s | %-38s |", YELLOW + code[0] + RESET, GREEN + code[1] + RESET));
            }
            
            LOGGER.info(border);
            LOGGER.info("\n");
            System.out.print(BOLD + CYAN + "💡 Ingrese el " + YELLOW + "código" + CYAN + " de la moneda de " + GREEN + "origen" + CYAN + ": " + RESET);
            String from = scanner.next().toUpperCase();
            System.out.print(BOLD + CYAN + "💡 Ingrese el " + YELLOW + "código" + CYAN + " de la moneda de " + GREEN + "destino" + CYAN + ": " + RESET);
            String to = scanner.next().toUpperCase();
            System.out.print(BOLD + CYAN + "💸 Ingrese el " + YELLOW + "monto" + CYAN + " a convertir: " + RESET);
            double amount = scanner.nextDouble();
            
            Double rateFrom = rates.getConversionRates().get(from);
            Double rateTo = rates.getConversionRates().get(to);
            
            if (rateFrom == null || rateTo == null) {
                LOGGER.info(RED + BOLD + "❌ Uno de los códigos ingresados no es válido." + RESET);
                return;
            }
            
            double result = (amount / rateFrom) * rateTo;
            LOGGER.info("\n");
            LOGGER.info(GREEN + BOLD + "\uD83D\uDCB6 Resultado de la conversión:" + RESET);
            LOGGER.info(String.format(BOLD + YELLOW + "%.2f %s" + RESET + CYAN + " = " + RESET + BOLD + GREEN + "%.2f %s" + RESET, amount, from, result, to));
            LOGGER.info("\n");
            
        } catch (Exception e) {
            LOGGER.info(RED + BOLD + "Error al obtener la lista de divisas o al convertir: " + e.getMessage() + RESET);
        }
    }
} 