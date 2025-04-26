# Nueva Estructura del Proyecto Conversor de Monedas

## Estructura de Carpetas

```
currency-converter/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── conversor/
│       │           ├── controller/  # Controladores de la aplicación
│       │           │   └── MainController.java
│       │           ├── model/       # Modelos de datos
│       │           │   └── ExchangeRateResponse.java
│       │           ├── service/     # Servicios para lógica de negocio
│       │           │   ├── CurrencyConverter.java
│       │           │   └── ExchangeRateApiService.java
│       │           ├── util/        # Utilidades
│       │           │   ├── ConsoleUtils.java
│       │           │   └── ConversionActions.java
│       │           ├── view/        # Vistas
│       │           │   └── MenuView.java
│       │           └── Main.java    # Punto de entrada de la aplicación
│       └── resources/               # Recursos como archivos de configuración
├── lib/                             # Bibliotecas externas
│   ├── dotenv-java-2.2.4.jar
│   └── gson-2.13.0.jar
└── .env.example                     # Ejemplo de archivo de configuración
```

## Descripción de la Estructura

### Patrón MVC (Modelo-Vista-Controlador)

La nueva estructura sigue el patrón MVC para separar las responsabilidades y hacer el código más mantenible:

1. **Modelo (Model)**:

   - `ExchangeRateResponse.java`: Representa la respuesta de la API de tasas de cambio.

2. **Vista (View)**:

   - `MenuView.java`: Maneja la presentación del menú y la interfaz de usuario.

3. **Controlador (Controller)**:
   - `MainController.java`: Coordina la lógica de negocio entre el modelo y la vista.

### Servicios (Services)

- `CurrencyConverter.java`: Lógica para la conversión de monedas.
- `ExchangeRateApiService.java`: Interacción con la API de tasas de cambio.

### Utilidades (Utils)

- `ConsoleUtils.java`: Métodos utilitarios para la interacción con la consola.
- `ConversionActions.java`: Acciones para los diferentes tipos de conversión.

## Ventajas de la Nueva Estructura

1. **Separación de Responsabilidades**: Cada clase tiene una única responsabilidad, lo que hace el código más mantenible.
2. **Escalabilidad**: Facilita la adición de nuevas funcionalidades.
3. **Reutilización**: Los componentes pueden ser reutilizados en diferentes partes de la aplicación.
4. **Testabilidad**: Es más fácil realizar pruebas unitarias al tener componentes desacoplados.
5. **Organización**: Estructura más clara y organizada que facilita la navegación por el código.

## Cómo Ejecutar la Aplicación

1. Asegúrese de tener Java instalado (versión 11 o superior).
2. Copie el archivo `.env.example` a `.env` y reemplace `su_clave_api_aqui` con su clave API de ExchangeRate-API.
3. Compile la aplicación: `javac -d bin -cp "lib/*" src/main/java/com/conversor/Main.java src/main/java/com/conversor/**/*.java`
4. Ejecute la aplicación: `java -cp "bin:lib/*" main.java.com.conversor.Main`
