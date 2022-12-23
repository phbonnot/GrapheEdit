package modele;

import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurve;
import modele.Arete;
import modele.Sommet;

import java.awt.*;

public class Boucle extends Arete {

    Sommet sommet;

    public Boucle(Sommet s){
       super(s,s);
       this.sommet=s;
    }

    public Sommet getSommet(){
        return this.sommet;
    }
}
