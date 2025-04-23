# Conversor de Moneda - Challenge ONE Java Back-end

Este es un proyecto de consola en Java que permite convertir entre diferentes monedas usando tasas de cambio en tiempo real desde una API pública.

## ¿Cómo funciona?

- Al ejecutar el programa, verás un menú con varias opciones de conversión (USD, COP, BRL, EUR).
- Selecciona la opción deseada (1-7).
- Ingresa el monto que deseas convertir.
- El programa mostrará el resultado utilizando tasas de cambio actualizadas.
- Puedes seguir convirtiendo o salir con la opción 7.

## Ejemplo de uso

```
============================================================
 Sea bienvenido al conversor de moneda 
============================================================

#  Conversión                      Descripción
============================================================
1  Dólar => Peso Colombiano        USD a COP
2  Peso Colombiano => Dólar        COP a USD
3  Dólar => Real Brasileño         USD a BRL
4  Real Brasileño => Dólar         BRL a USD
5  Dólar => Euro                   USD a EUR
6  Euro => Dólar                   EUR a USD
7  Salir                           Salir del programa
============================================================
Seleccione una opción válida (1-7):
============================================================
 Ingrese el monto a convertir: 100
 Resultado: 100.00 USD = 42800.00 COP
============================================================
```

## Estructura del Proyecto

- `src/` - Código fuente principal
  - `Main.java` - Clase principal, contiene el flujo del programa
  - `service/` - Lógica de conexión y modelo de tasas de cambio
  - `util/` - Utilidades para interacción por consola y definición de conversiones
- `README.md` - Este archivo

## Compilación y ejecución

1. Compila todo el proyecto:
   ```sh
   javac -d out src/service/*.java src/util/*.java src/Main.java
   ```
2. Ejecuta el programa:
   ```sh
   java -cp out Main
   ```

## Requisitos
- Java 17 o superior
- Conexión a internet (para obtener tasas de cambio en tiempo real)

## Créditos
- API de tasas de cambio: [ExchangeRate-API](https://www.exchangerate-api.com/)
- Proyecto realizado como parte del Challenge ONE - Java Back-end de Alura
