/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.control_ventas.travelmapgt1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
    import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


/**
 *
 * @author Gerardo Tax
 */


class BTreeNode {
    List<Integer> keys;
    List<BTreeNode> children;
    boolean leaf;

    public BTreeNode(boolean leaf) {
        this.keys = new ArrayList<>();
        this.children = new ArrayList<>();
        this.leaf = leaf;
    }
}

public class  Generador {
    public static void main(String[] args) {
        List<Integer> dataList = new ArrayList<>(); // Tu lista de datos aquí
        dataList.add(10);
        dataList.add(5);
        dataList.add(15);
         dataList.add(20);
          dataList.add(16);
           dataList.add(18);
    
        // Agrega más datos si es necesario

        BTreeNode root = buildBTree(dataList, 4); // Construye un árbol B con los datos de la lista

        try {
            String filePath="src/main/resources/img/btree.dot";
            generateDotFile(root, "src/main/resources/img/btree.dot");
                String command = "dot -Tpng " + filePath + " -o " + filePath.replace(".dot", ".png");
            Runtime.getRuntime().exec(command);
            System.out.println("Gráfico generado correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para construir un árbol B a partir de una lista de datos
    private static BTreeNode buildBTree(List<Integer> dataList, int degree) {
        BTreeNode root = new BTreeNode(true); // Crea un nodo raíz

        for (Integer data : dataList) {
            insert(root, data, degree);
        }

        return root;
    }

    // Método para insertar un dato en un árbol B
private static BTreeNode insert(BTreeNode node, int data, int degree) {
    // Si el nodo es nulo, crea un nuevo nodo como raíz
    if (node == null) {
        BTreeNode newNode = new BTreeNode(true);
        newNode.keys.add(data);
        return newNode;
    }

    // Si el nodo está lleno, divide el nodo
    if (node.keys.size() == 2 * degree - 1) {
        // Crea un nuevo nodo para mover la mitad de las claves y los hijos
        BTreeNode newNode = new BTreeNode(node.leaf);
        node.children.add(0, newNode);

        // Mueve la mitad de las claves y los hijos al nuevo nodo
        for (int i = degree; i < 2 * degree - 1; i++) {
            newNode.keys.add(node.keys.remove(degree));
            newNode.children.add(node.children.remove(degree));
        }

        // Actualiza la raíz si es necesario
        if (node == node.children.get(0)) {
            BTreeNode newRoot = new BTreeNode(false);
            newRoot.keys.add(node.keys.remove(0));
            newRoot.children.add(node);
            newRoot.children.add(newNode);
            return newRoot;
        }
    }

    // Inserta el dato en el nodo adecuado
    int i = 0;
    while (i < node.keys.size() && data > node.keys.get(i)) {
        i++;
    }
    if (node.leaf) {
        node.keys.add(i, data);
    } else {
        node.children.set(i, insert(node.children.get(i), data, degree));
    }
    return node;
}

    // Método para generar el archivo DOT recursivamente
    private static void generateDotFile(BTreeNode node, String filename) throws IOException {
        FileWriter fileWriter = new FileWriter(filename);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        printWriter.println("digraph G {");
        printDotNode(node, printWriter);
        printWriter.println("}");

        printWriter.close();
    }

    // Método para imprimir un nodo y sus conexiones
    private static void printDotNode(BTreeNode node, PrintWriter printWriter) {
        if (node == null)
            return;

        printWriter.print("\"Node" + node.hashCode() + "\" [label=\"");

        for (int i = 0; i < node.keys.size(); i++) {
            printWriter.print("<f" + i + "> | " + node.keys.get(i) + " |");
        }

        printWriter.print("<f" + node.keys.size() + ">\" shape=\"record\"];\n");

        if (!node.leaf) {
            for (int i = 0; i < node.children.size(); i++) {
                if (node.children.get(i) != null) {
                    printWriter.println("\"Node" + node.hashCode() + "\":f" + i + " -> \"Node" + node.children.get(i).hashCode() + "\";");
                    printDotNode(node.children.get(i), printWriter);
                }
            }
        }
    }
}





