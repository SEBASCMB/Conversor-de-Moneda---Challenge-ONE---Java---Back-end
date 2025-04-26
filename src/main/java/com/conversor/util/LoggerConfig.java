package main.java.com.conversor.util;

import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.Level;

public class LoggerConfig {
    
    private static final Logger LOGGER = Logger.getLogger("conversor");
    
    static {
        LOGGER.setUseParentHandlers(false);
        ConsoleHandler handler = new ConsoleHandler();
        handler.setFormatter(new Formatter() {
            @Override
            public String format(LogRecord record) {
                return record.getMessage() + "\n";
            }
        });
        LOGGER.addHandler(handler);
        LOGGER.setLevel(Level.INFO);
    }
    
    private LoggerConfig() {
        // Constructor privado para evitar instanciación
    }
    
    public static Logger getLogger() {
        return LOGGER;
    }
} 