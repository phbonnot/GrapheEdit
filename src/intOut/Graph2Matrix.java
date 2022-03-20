package intOut;


import modele.Arete;
import modele.Graphe;
import modele.Sommet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class Graph2Matrix {

    Graphe graphe;

    public Graph2Matrix(Graphe g){
        this.graphe=g;

    }

    public void ecritureMatrice(File f){
        try {
            PrintWriter writer = new PrintWriter(f.getName());
            int[][] mat = this.graphe.matriceAdjacence();
            for (int i = 0; i < mat.length; i++) {
                for (int j = 0; j < mat[i].length; j++) {
                    writer.print(mat[i][j]);
                }
                writer.println("");
            }
            writer.close();
        }
        catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
}
