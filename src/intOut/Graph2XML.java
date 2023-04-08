package intOut;

import controle.Controleur;
import gui.SurfaceDessin;
import javafx.scene.control.*;
import modele.Arete;
import modele.Graphe;
import modele.Sommet;
import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import java.io.*;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

public class Graph2XML {

    private Graphe graphe;

    public Graph2XML(Graphe g) {
        this.graphe = g;
    }

    public Graph2XML(){
        this.graphe=new Graphe();
    }

    public static void afficher(Document document) {
        try {
            XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
            sortie.output(document, System.out);
        } catch (java.io.IOException e) {
        }
    }


    public void readAFile(File f){
        Document doc=null;
        try {
            SAXBuilder sax = new SAXBuilder();
            doc=sax.build(f);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Element racineGraphe=doc.getRootElement();
        if(racineGraphe.getName().equals("Graphe")){
            Iterator ite= racineGraphe.getChildren().iterator();
            while(ite.hasNext()){
                Element courant=(Element)ite.next();
                if(courant.getName().equals("Sommet")){
                    String id=courant.getChild("Id").getAttributeValue("valeur");
                    double posX=Double.parseDouble(courant.getChild("Position").getAttributeValue("posX"));
                    double posY=Double.parseDouble(courant.getChild("Position").getAttributeValue("posY"));
                    this.graphe.ajouterSommet(id,posX,posY);
                }
                if(courant.getName().equals("Arête")){
                    int ext1=Integer.parseInt(courant.getChild("Ext_1").getAttributeValue("id"));
                    int ext2=Integer.parseInt(courant.getChild("Ext_2").getAttributeValue("id"));
                    this.graphe.ajouterArete(new Arete(this.graphe.getSommetAvecId(ext1),this.graphe.getSommetAvecId(ext2)));
                }
            }
        }
    }

    public  void makeAFile(File f, Document doc){
        XMLOutputter out = new XMLOutputter(Format.getPrettyFormat());
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

    public Graphe getGraphe(){
        return this.graphe;
    }
}
