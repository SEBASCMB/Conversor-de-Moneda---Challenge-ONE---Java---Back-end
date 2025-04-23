package service;

public class CurrencyConverter {

  public static double convertirModena(double cantidad, String monedaOrigen, String monedaDestino, ExchangeRateResponse rates){

    Double tasaOrigen  = rates.conversion_rates.get(monedaOrigen);
    Double tasaDestino = rates.conversion_rates.get(monedaDestino);

    if ( tasaOrigen == null || tasaDestino == null ){
      throw new IllegalArgumentException("Tasa de cambio no disponible");
    }

    double cantidadEnUSD = cantidad / tasaOrigen;
    return cantidadEnUSD * tasaDestino;

  }

}
