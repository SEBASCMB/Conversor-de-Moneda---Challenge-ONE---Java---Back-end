# Conversor de Moneda - Challenge ONE Java Back-end

Este es un proyecto de consola en Java que permite convertir entre diferentes monedas usando tasas de cambio en tiempo real desde una API pública.

## ¿Cómo funciona?

- Al ejecutar el programa, verás un menú con varias opciones de conversión rápida (USD, COP, BRL, EUR) y una opción especial para convertir entre cualquier divisa soportada por la API.
- Selecciona la opción deseada (1-8).
- Si eliges la opción 8, podrás ver una tabla con todas las divisas disponibles y luego ingresar los códigos de las monedas que deseas convertir (por ejemplo, de JPY a COP).
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
8  Mostrar todas las divisas       Lista de divisas soportadas
============================================================
Seleccione una opción válida (1-8):
============================================================

🌍 Listado de divisas soportadas
+----------+----------------------------------------+
| Código   | Nombre                                 |
+----------+----------------------------------------+
| USD      | United States Dollar                   |
| JPY      | Japanese Yen                           |
| COP      | Colombian Peso                         |
| ...      | ...                                    |
+----------+----------------------------------------+
💡 Ingrese el código de la moneda de origen: JPY
💡 Ingrese el código de la moneda de destino: COP
💸 Ingrese el monto a convertir: 1000

💵 Resultado de la conversión:
1000.00 JPY = 27000.00 COP
============================================================
 Ingrese el monto a convertir: 100
 Resultado: 100.00 USD = 42800.00 COP
============================================================
```

## Variables de entorno y seguridad

Desde la versión actual, este proyecto utiliza un archivo `.env` para gestionar la clave de API de manera segura. Esto evita que tu clave quede expuesta en el repositorio.

- La clave de API se debe colocar en el archivo `.env` en la raíz del proyecto:
  ```
  API_KEY=tu_api_key_aqui
  ```
- El archivo `.env` está incluido en `.gitignore` y no se subirá a GitHub.
- El código utiliza la librería [dotenv-java](https://github.com/cdimascio/dotenv-java) para leer la clave de API desde `.env`.

> **Consulta la guía completa de configuración y ejecución en [`README_DOTENV.md`](README_DOTENV.md)**

## Estructura del Proyecto

- `src/` - Código fuente principal
  - `Main.java` - Clase principal, contiene el flujo del programa
  - `service/` - Lógica de conexión y modelo de tasas de cambio
  - `util/` - Utilidades para interacción por consola y definición de conversiones
- `README.md` - Este archivo

## Compilación y ejecución

1. Descarga la dependencia `dotenv-java` siguiendo las instrucciones en [`README_DOTENV.md`](README_DOTENV.md).
2. Compila todo el proyecto incluyendo el JAR de dotenv-java:
   ```sh
   javac -cp "lib/dotenv-java-2.2.4.jar" -d bin src/service/*.java src/util/*.java src/Main.java
   ```
3. Ejecuta el programa:
   ```sh
   java -cp "bin:lib/dotenv-java-2.2.4.jar" Main
   ```

## Ejemplo:

![2025-04-22-21-43-08](https://github.com/user-attachments/assets/c516a3bb-d605-44ed-8dea-a6dbf1d914a8)


## Requisitos
- Java 17 o superior
- Conexión a internet (para obtener tasas de cambio en tiempo real)

## Créditos
- API de tasas de cambio: [ExchangeRate-API](https://www.exchangerate-api.com/)
- Proyecto realizado como parte del Challenge ONE - Java Back-end de Alura
