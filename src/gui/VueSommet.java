package gui;

import java.awt.Event;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import modele.Sommet;

public class VueSommet extends StackPane {

	private Sommet sommet;
	private Color couleur;
	
	public VueSommet(Sommet s) {
		this.sommet=s;
		this.sommet.setVue(this);
		this.couleur=Color.AQUAMARINE;
	}
	
	public Sommet getSommet() {
		return this.sommet;
	}
	
	public void dessiner(String s) {
		Text text=new Text(s);
		Shape circle=new Circle(10);
		circle.setFill(this.couleur);
		this.getChildren().addAll(circle,text);
		this.setLayoutX(this.sommet.getPosition().getX()-10);
		this.setLayoutY(this.sommet.getPosition().getY()-10);
	}

	public void setCouleur(Color c){
		this.couleur=c;
	}

	public Color getCouleur(){
		return this.couleur;
	}


}
