# Proyecto de búsqueda de palabras clave en archivos HTML

Este proyecto implementa un programa en Java que busca una palabra clave en un archivo HTML y registra las posiciones donde aparece. También genera un archivo de bitácora que almacena estas posiciones.

## Explicación del Código

### Clase `Main`

La clase principal del programa recibe dos argumentos de la línea de comandos:

- El nombre del archivo HTML.
- La palabra clave que se busca.

El código utiliza el paquete `javax.swing.text.html` para analizar el contenido del archivo HTML.

#### Componentes clave:

- **Lectura del archivo HTML**: Se utiliza la clase `FileReader` para leer el archivo HTML, y el método `HTMLEditorKit.Parser.parse()` para procesarlo.
  
- **Callback para manejar el contenido**: La clase `HTMLParserCallback` extiende `HTMLEditorKit.ParserCallback` y sobrescribe el método `handleText()` para procesar el texto dentro del HTML. Aquí es donde se realiza la búsqueda de la palabra clave.

### Clase `HTMLParserCallback`

Esta clase es la que efectúa la búsqueda de la palabra clave en el contenido de texto del archivo HTML.

#### Componentes:

- **Variables**:
  - `keyword`: La palabra clave que el usuario especifica para buscar en el archivo.
  - `logFile`: El nombre del archivo de bitácora donde se almacenarán las posiciones de las ocurrencias.
  - `posicion`: Un contador que rastrea la posición actual del análisis dentro del archivo HTML.

- **Método `handleText(char[] data, int pos)`**:
  - Este método se encarga de procesar cada fragmento de texto en el documento HTML.
  - Convierte el texto a minúsculas para asegurar que la búsqueda no distinga entre mayúsculas y minúsculas.
  - Luego, utiliza el método `indexOf()` para localizar las ocurrencias de la palabra clave.
  - Cuando se encuentra una coincidencia, se imprime en la consola y se escribe en el archivo de bitácora.

- **Búsqueda en el texto**:
  - Si la palabra clave aparece en el texto procesado, se registra su posición (relativa al inicio del documento HTML).
  - El archivo de bitácora se actualiza con cada ocurrencia.
