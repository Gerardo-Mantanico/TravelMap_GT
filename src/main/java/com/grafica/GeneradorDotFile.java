package com.grafica;
import com.modelo.ArbolB;
import com.modelo.Arco;
import com.modelo.NodoGrafo;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Gerardo Tax
 */
public class GeneradorDotFile {

    ImprimirArbol imprimir = new ImprimirArbol();

    void generateDotFile(NodoGrafo root, String filePath, List<String> lista) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("digraph G { \n fontname=\"Helvetica,Arial,sans-serif\"\n"
                    + "    bgcolor=\"#02364E\";\n"
                    + "    node [fontname=\"Helvetica,Arial,sans-serif\"];\n"
                    + "    edge [fontname=\"Helvetica,Arial,sans-serif\"];\n"
                    + "    graph [ratio=0.5];\n");
            try {
                writer.write("node [shape = oval, color=red style=filled, fillcolor=indianred1];\n");
                for (String nodo : lista) {
                    writer.write(nodo + " ");
                }
                writer.write("; \n  node [shape = oval color=black style=filled fillcolor=\"#1CC4C6\" fontcolor=\"#031C26\"]\n");
            } catch (Exception e) {
                writer.write("node [shape = oval color=black style=filled fillcolor=lightblue] \n");
            }
            while (root != null) {
                Arco temp = root.getLista().getPrimero();
                while (temp != null) {
                    //writer.write("    A -> B;\n");
                    //A -> {B C}
                    writer.write(root.getNombre() + " -> " + temp.getDestino() + "[label = \" " + temp.getDetalle().getDistancia() + "  Km  \"  color=\"white\",fontcolor=white]; \n");
                    temp = temp.getSiguiente();
                }
                root = root.getSiguiente();
            }
            writer.write("}\n");
            System.out.println("Archivo DOT Mapa  generado correctamente.");

        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    public void archivodot(NodoGrafo root, String filePath, List<String> lista, ImageView img) {
        // Verificar si el archivo ya existe
        File archivo = new File(filePath);
        boolean archivoExiste = archivo.exists();
        // Si el archivo no existe, escribir algo en él (opcional)
        if (!archivoExiste) {
            generateDotFile(root, filePath, lista);
        } else {
            archivo.delete();
            generateDotFile(root, filePath, lista);
        }

     try {
    String command = "dot -Tpng " + filePath + " -o " + filePath.replace(".dot", ".png");
    ProcessBuilder pb = new ProcessBuilder(command.split("\\s+"));
    pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);
    pb.redirectError(ProcessBuilder.Redirect.INHERIT);
    Process process = pb.start();
    int exitCode = process.waitFor();
    if (exitCode == 0) {
        System.out.println("La conversión se ha completado exitosamente.");
        Image image = new Image(new FileInputStream(filePath.replace(".dot", ".png")));
        img.setImage(image);
    } else {
        System.out.println("Hubo un error al ejecutar el comando.");
    }
} catch (IOException | InterruptedException e) {
    e.printStackTrace();
}

    }

    public void ruta(List<String> lista, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("digraph G {\n");
            writer.write("rankdir=LR;\n");
            writer.write("bgcolor=\"#02364E\";  \n  node [shape = oval color=black style=filled fillcolor=\"#1CC4C6\" fontcolor=\"#031C26\"] \n");
            for(int i=0;i<lista.size();i++){
                if(i==lista.size()-1){
                    writer.write( lista.get(i)+ ";");
                }
                else{
                    writer.write( lista.get(i)+ " -> ");
                }
            }
            writer.write("\n}");
            System.out.println("Archivo DOT de ruta generado corectamente.");
            // Ejecutar Graphviz para generar la imagen del gráfico
            String command = "dot -Tpng " + filePath + " -o " + filePath.replace(".dot", ".png");
            Runtime.getRuntime().exec(command);
            System.out.println("Gráfico generado correctamente.");
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }
    public void generarArbol(ArbolB arbol, String prefijo, String filePath) {
        imprimir.imprimirArbolB(arbol, filePath);     
    }

}
