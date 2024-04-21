package com.grafica;
import com.modelo.Arco;
import com.modelo.NodoGrafo;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Gerardo Tax
 */
public class GeneradorDotFile {

     void generateDotFile(NodoGrafo root, String filePath, List<String> lista) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("digraph G { \n fontname=\"Helvetica,Arial,sans-serif\"\n"
             + "    bgcolor=\"#f0f0f0\";\n"
             + "    node [fontname=\"Helvetica,Arial,sans-serif\"];\n"
             + "    edge [fontname=\"Helvetica,Arial,sans-serif\"];\n"
             + "    graph [ratio=0.5];\n");
            try{
                writer.write("node [shape = oval, color=red style=filled, fillcolor=indianred1];");
                for (String nodo:lista) {
                    writer.write(nodo+" ");
                }
                writer.write("; \n node [shape = oval color=black style=filled fillcolor=lightblue]");
            }catch (Exception e){
                writer.write("node [shape = oval color=black style=filled fillcolor=lightblue] \n");
            }
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
            System.out.println("Archivo DOT Mapa  generado correctamente.");
            // Ejecutar Graphviz para generar la imagen del gráfico
            String command = "dot -Tpng " + filePath + " -o " + filePath.replace(".dot", ".png");
            Runtime.getRuntime().exec(command);
            System.out.println("Gráfico generado correctamente.");
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }


    public void  archivodot(NodoGrafo root, String filePath, List<String> lista) {
            // Verificar si el archivo ya existe
            File archivo = new File(filePath);
            boolean archivoExiste = archivo.exists();
            try {
                // Crear un objeto FileWriter con la opción de sobrescribir el contenido del archivo si ya existe
                FileWriter writer = new FileWriter(archivo, !archivoExiste);
                // Si el archivo no existe, escribir algo en él (opcional)
                if (!archivoExiste) {
                    generateDotFile(root, filePath, lista);
                }
                // Cerrar el FileWriter para liberar los recursos
                writer.close();
                if (archivoExiste) {
                    //archivo dot limpiado
                    generateDotFile(root, filePath, lista);
                } else {
                    System.out.println("Archivo .dot creado exitosamente.");
                }
            } catch (IOException e) {
                System.err.println("Error al manipular el archivo .dot: " + e.getMessage());
            }
        }

    public void ruta(List<String> lista, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("digraph G {\n");
            writer.write("rankdir=LR;\n");
            writer.write("bgcolor=\"#f0f0f0\";  \n node [margin=0 fontcolor=black fontsize=18 width=0.5 shape=oval style=filled fillcolor=lightblue] \n");
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

}
