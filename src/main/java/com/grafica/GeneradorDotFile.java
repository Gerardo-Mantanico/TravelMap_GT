package com.grafica;
import com.modelo.Arco;
import com.modelo.NodoGrafo;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Gerardo Tax
 */
public class GeneradorDotFile {

    public void generateDotFile(NodoGrafo root, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("digraph G {\n");
            writer.write(" bgcolor=\"#f0f0f0\";  \n node [margin=0 fontcolor=black fontsize=18 width=0.5 shape=oval style=filled fillcolor=lightblue] \n");
            while (root != null) {
                writer.write(root.getNombre() + " ->  {");
                Arco temp = root.getLista().getPrimero();
                while (temp != null) {
                    //writer.write("    A -> B;\n");
                    //A -> {B C}
                    writer.write(temp.getDestino() + "  ");
                    temp = temp.getSiguiente();
                }
                writer.write("} \n ");
                root = root.getSiguiente();
            }
            writer.write("}\n");
            System.out.println("Archivo DOT generado correctamente.");

            // Ejecutar Graphviz para generar la imagen del gráfico
            String command = "dot -Tpng " + filePath + " -o " + filePath.replace(".dot", ".png");
            Runtime.getRuntime().exec(command);
            System.out.println("Gráfico generado correctamente.");
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    public void UbicacionActual(NodoGrafo root, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("digraph G {\n");
            writer.write("bgcolor=\"#f0f0f0\";  \n node [margin=0 fontcolor=black fontsize=18 width=0.5 shape=oval style=filled fillcolor=lightblue] \n");
            writer.write(root.getNombre() + " ->  {");
            Arco temp = root.getLista().getPrimero();
            while (temp != null) {
                //writer.write("    A -> B;\n");
                //A -> {B C}
                writer.write(temp.getDestino() + "  ");
                temp = temp.getSiguiente();
            }
            writer.write("} \n ");
            writer.write("}\n");
            System.out.println("Archivo DOT generado correctamente.");

            // Ejecutar Graphviz para generar la imagen del gráfico
            String command = "dot -Tpng " + filePath + " -o " + filePath.replace(".dot", ".png");
            Runtime.getRuntime().exec(command);
            System.out.println("Gráfico generado correctamente.");
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }

    }
}
