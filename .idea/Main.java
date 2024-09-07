import javax.swing.text.html.*;
import javax.swing.text.html.parser.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Uso: java Main <ejemplo.html> <perro> ");
            return;
        }
        String htmlFile = args[0];
        String palabraClave = args[1];
        String logFile = "file-" + palabraClave + ".log";
        try {
            File file = new File(htmlFile);
            if (!file.exists()) {
                System.out.println("El archivo no existe: " + file.getAbsolutePath());
                return;
            }
            System.out.println("El archivo fue encontrado en: " + file.getAbsolutePath());

            FileReader reader = new FileReader(file);
            HTMLEditorKit.Parser parser = new ParserDelegator();
            HTMLParserCallback callback = new HTMLParserCallback(palabraClave, logFile);
            parser.parse(reader, callback, true);

        } catch (IOException e) {
            System.out.println("Error al intentar leer el archivo: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

class HTMLParserCallback extends HTMLEditorKit.ParserCallback {
    private String palabraClave;
    private String logFile;
    private int posiciones = 0;

    public HTMLParserCallback(String keyword, String logFile) {
        this.palabraClave = keyword;
        this.logFile = logFile;
    }
    @Override
    public void handleText(char[] data, int pos) {
        String text = new String(data);
        int index = text.indexOf(palabraClave);
        while (index >= 0) {
            int posicion = posiciones + index;
            System.out.println("La palabra '" + palabraClave + "' fue encontrada en la posición número: " + posicion);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFile, true))) {
                writer.write("Archivo html: " + posicion);
                writer.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            index = text.indexOf(palabraClave, index + 1);
        }
        posiciones += data.length;
    }
}