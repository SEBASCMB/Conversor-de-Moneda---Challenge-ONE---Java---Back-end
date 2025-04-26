package main.java.com.conversor.service;

import main.java.com.conversor.model.ExchangeRateResponse;

public class CurrencyConverter {

  private CurrencyConverter() {
    // Constructor privado para evitar instanciación
  }

  public static double convertirMoneda(double cantidad, String monedaOrigen, String monedaDestino, ExchangeRateResponse rates){

    Double tasaOrigen  = rates.getConversionRates().get(monedaOrigen);
    Double tasaDestino = rates.getConversionRates().get(monedaDestino);

    if (tasaOrigen == null || tasaDestino == null){
      throw new IllegalArgumentException("Tasa de cambio no disponible");
    }

    double cantidadEnUSD = cantidad / tasaOrigen;
    return cantidadEnUSD * tasaDestino;

  }

} 