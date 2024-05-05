
package com.grafica;
import com.modelo.ArbolB;
import com.modelo.NodoArbolB;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Gerardo Tax
 */
public class ImprimirArbol {

    public void imprimirArbolB(ArbolB arbol, String archivoDot) {
        try {
            FileWriter writer = new FileWriter(archivoDot);
            writer.write("digraph ArbolB {\n");
            writer.write("bgcolor=\" #085872\";\n");
            writer.write(" node [shape=record, style=filled, fillcolor=lightblue, height=0.6, width=1.5];\n"
                    + "    edge [arrowhead=none]; \n");
            imprimirRecursivo(arbol.raiz, writer, "");
            writer.write("}\n");
            writer.close();
            System.out.println("Archivo DOT generado correctamente: " + archivoDot);
            String command = "dot -Tpng " + archivoDot + " -o " + archivoDot.replace(".dot", ".png");
            Runtime.getRuntime().exec(command);
            System.out.println("Gráfico generado correctamente.");
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo DOT: " + e.getMessage());
        }
    }

    private void imprimirRecursivo(NodoArbolB nodo, FileWriter writer, String prefijo) throws IOException {
        if (nodo != null) {
            StringBuilder nodoLabel = new StringBuilder();
            nodoLabel.append(prefijo);
            for (int i = 0; i < nodo.claves.size(); i++) {
                nodoLabel.append("<f" + i + "> | " + nodo.nombresNodos.get(i) + " | ");
            }
            writer.write(nodo.hashCode() + " [label=\"" + nodoLabel.toString() + "\"];\n");
            if (!nodo.esHoja) {
                for (NodoArbolB hijo : nodo.hijos) {
                    writer.write(nodo.hashCode() + " -> " + hijo.hashCode() + ";\n");
                    imprimirRecursivo(hijo, writer, prefijo + "    ");
                }
            }
        }
    }
}
