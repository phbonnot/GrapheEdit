package intOut;

import modele.Arete;
import modele.Graphe;
import modele.Sommet;

public class Graph2LaTeX {

    enum couleursAutorisees {red, green, blue, cyan, yellow, magenta, black, white, gray};

    Graphe graphe;

    public Graph2LaTeX(Graphe g){
        this.graphe=g;
    }

    public String toLaTeX(){
        String packages=" \\usepackage[usenames, dvipsnames]{xcolor}\n\\usepackage{tikz}";
        String codeLaTeX="\\begin{tikzpicture}\n";
        //codeLaTeX+="("+graphe.getWidth()+","+ (graphe.getHeight())+")";
        //codeLaTeX+="("+0+","+(-graphe.getHeight())+")\n";
        //codeLaTeX+="\\put(0,"+(-graphe.getHeight())+"){\\framebox("+graphe.getWidth()+","+graphe.getHeight()+"){}}\n";
        for(Sommet s:graphe.getListeSommets()){
            int x=(int)(s.getPosition().getX()/38);
            int y=(int)(s.getPosition().getY()/38);
            codeLaTeX+="\\node[draw,circle,fill="+couleursAutorisees.blue+"!20] ("+s.getId()+") at ("+x+","+y+"){"+s.getNom()+"};\n";
        }
        for(Arete a:graphe.getListeAretes()){
            codeLaTeX+="\\draw ("+a.getExtremite_1().getId()+") - - ("+a.getExtremite_2().getId()+");\n";
        }
        return packages+"\n\n"+codeLaTeX+"\n\\end{tikzpicture}";
    }
}
