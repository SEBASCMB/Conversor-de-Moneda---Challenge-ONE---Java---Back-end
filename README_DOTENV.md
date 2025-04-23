# Cómo usar dotenv-java sin Maven/Gradle

Este documento explica cómo integrar `dotenv-java` en tu proyecto Java si no estás utilizando herramientas de gestión de dependencias como Maven o Gradle.

**Pasos:**

1.  **Descarga el JAR:**
    Descarga directamente el archivo JAR de la librería desde el repositorio central de Maven:
    [https://repo1.maven.org/maven2/io/github/cdimascio/dotenv-java/2.2.4/dotenv-java-2.2.4.jar](https://repo1.maven.org/maven2/io/github/cdimascio/dotenv-java/2.2.4/dotenv-java-2.2.4.jar)

2.  **Coloca el JAR en tu proyecto:**
    Crea una carpeta llamada `lib/` en la raíz de tu proyecto (si no existe) y coloca el archivo `dotenv-java-2.2.4.jar` descargado dentro de esta carpeta.

    ```bash
    tu-proyecto/
    ├── src/
    │   └── ...
    ├── lib/
    │   └── dotenv-java-2.2.4.jar
    └── ...
    ```

3.  **Compila y Ejecuta:**
    Al compilar y ejecutar tu aplicación, debes asegurarte de incluir el JAR de `dotenv-java` en el classpath.

    * **Compilación:**

        ```bash
        javac -cp "lib/dotenv-java-2.2.4.jar" -d bin src/**/*.java
        ```
        *(Este comando asume que tus archivos `.java` están en la carpeta `src/` y que quieres compilar en la carpeta `bin/`)*

    * **Ejecución:**

        ```bash
        java -cp "bin:lib/dotenv-java-2.2.4.jar" Main
        ```
        *(Este comando asume que tu clase principal se llama `Main` y que los archivos compilados están en `bin/`. En Windows, usa `;` en lugar de `:` para separar paths en el classpath: `java -cp "bin;lib/dotenv-java-2.2.4.jar" Main`)*

Siguiendo estos pasos, podrás usar `dotenv-java` en tu proyecto sin necesidad de un gestor de dependencias.
