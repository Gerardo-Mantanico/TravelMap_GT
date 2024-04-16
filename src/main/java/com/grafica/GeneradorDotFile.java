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
            writer.write("node [margin=0 fontcolor=black fontsize=18 width=0.5 shape=oval style=filled fillcolor=lightblue]");
            while (root != null) {
                Arco temp = root.getLista().getPrimero();
                while (temp != null) {
                    //writer.write("    A -> B;\n");
                    writer.write(root.getNombre() + " ->" + temp.getDestino() + ";\n");
                    temp = temp.getSiguiente();
                }
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

    public void generar(NodoGrafo nodo, String path) {
        try (BufferedWriter file = new BufferedWriter(new FileWriter(path))) {
            file.write("digraph G {\n");
            file.write("    node [shape=circle, style=filled, fillcolor=lightblue, fontcolor=black]; // Configuración de nodos\n");
            file.write("    \n");
            // Escribir los vértices en el archivo DOT
            while (nodo != null) {
                file.write("    " + nodo.getNombre() + " [label=\"" + nodo.getNombre() + "\"];\n");
                nodo = nodo.getSiguiente();
            }
            file.write("}\n");
            System.out.println("Archivo DOT generado correctamente.");
        } catch (IOException e) {
            System.err.println("Error al abrir el archivo: " + e.getMessage());
        }
    }

}
