package gui;

import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurve;
import modele.Boucle;
import modele.Sommet;

public class VueBoucle extends CubicCurve {

    Boucle boucle;
    Sommet sommet;

    public VueBoucle(Boucle b){
        super(b.getSommet().getPosition().getX(),b.getSommet().getPosition().getY(),b.getSommet().getPosition().getX()-50,
                b.getSommet().getPosition().getY()-50,b.getSommet().getPosition().getX()+50,
                b.getSommet().getPosition().getY()-50,
                b.getSommet().getPosition().getX(),b.getSommet().getPosition().getY());
        this.boucle=b;
        this.sommet=b.getSommet();
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
