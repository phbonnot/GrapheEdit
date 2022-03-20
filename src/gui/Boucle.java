package gui;

import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurve;
import modele.Sommet;

import java.awt.*;

public class Boucle extends CubicCurve {

    Sommet sommet;

    public Boucle(Sommet s){
        super(s.getPosition().getX(),s.getPosition().getY(),s.getPosition().getX()-50,
                s.getPosition().getY()-50,s.getPosition().getX()+50,
                s.getPosition().getY()-50,
                s.getPosition().getX(),s.getPosition().getY());
        this.sommet=s;
    }

    public void binder() {
        this.startXProperty().bind(this.sommet.getVue().translateXProperty());
        this.startYProperty().bind(this.sommet.getVue().translateYProperty());
        this.endXProperty().bind(this.sommet.getVue().translateXProperty());
        this.endYProperty().bind(this.sommet.getVue().translateYProperty());
        this.controlX1Property().bind(this.sommet.getVue().translateXProperty().add(-50));
        this.controlY1Property().bind(this.sommet.getVue().translateYProperty().add(-50));
        this.controlX2Property().bind(this.sommet.getVue().translateXProperty().add(50));
        this.controlY2Property().bind(this.sommet.getVue().translateYProperty().add(-50));
    }

    public void dessiner(){
        this.setTranslateX(this.sommet.getPosition().getX());
        this.setTranslateY(this.sommet.getPosition().getY());
        this.setFill(null);
        this.setStroke(Color.BLACK);
    }
}
