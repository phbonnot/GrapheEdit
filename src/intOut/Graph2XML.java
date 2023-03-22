package intOut;

import modele.Arete;
import modele.Graphe;
import modele.Sommet;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import java.io.*;
import java.io.FileOutputStream;

public class Graph2XML {

    private Graphe graphe;

    public Graph2XML(Graphe g) {
        this.graphe = g;
    }

    public static void afficher(Document document) {
        try {
            XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
            sortie.output(document, System.out);
        } catch (java.io.IOException e) {
        }
    }


    public  void makeAFile(File f, Document doc){
        XMLOutputter out = new XMLOutputter();
        try {
            out.output(doc.detachRootElement(), new FileOutputStream(f));
        }
        catch (IOException e) {
            //Erreur d'écriture à traiter ici
        }
    }
    public Document toXML() {

        Element racine = new Element("Graphe");
        Document doc = new Document(racine);
        for (Sommet s : this.graphe.getListeSommets()){
            Element sommet=new Element("Sommet");
            Element id=new Element("Id");
            id.setAttribute("valeur",""+s.getId());
            Element position=new Element("Position");
            position.setAttribute("posX",String.valueOf(s.getPosition().getX()));
            position.setAttribute("posY",String.valueOf(s.getPosition().getY()));
            sommet.addContent(id);
            sommet.addContent(position);
            racine.addContent(sommet);

        }
        for (Arete a : this.graphe.getListeAretes()) {
            Element arete = new Element("Arête");
            Element sommet1=new Element("Ext_1");
            sommet1.setAttribute("id",String.valueOf(a.getExtremite_1().getId()));
            arete.addContent(sommet1);
            Element sommet2 = new Element("Ext_2");
            sommet2.setAttribute("id",String.valueOf(a.getExtremite_2().getId()));
            arete.addContent(sommet2);
            racine.addContent(arete);
        }
        return doc;
    }
}
