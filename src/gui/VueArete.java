package gui;

import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import modele.Arete;

public class VueArete extends Line{
	
	private Arete arete;
	
	public VueArete(Arete a) {
		this.arete=a;
	}
	
	public void binder() {
		this.startXProperty().bind(this.arete.getExtremite_1().getVue().translateXProperty().add(this.arete.getExtremite_1().getVue().layoutXProperty()).add(10));
		this.startYProperty().bind(this.arete.getExtremite_1().getVue().translateYProperty().add(this.arete.getExtremite_1().getVue().layoutYProperty()).add(10));
		this.endXProperty().bind(this.arete.getExtremite_2().getVue().translateXProperty().add(this.arete.getExtremite_2().getVue().layoutXProperty()).add(10));
		this.endYProperty().bind(this.arete.getExtremite_2().getVue().translateYProperty().add(this.arete.getExtremite_2().getVue().layoutYProperty()).add(10));
	}
	
	public void dessiner() {
		this.setStartX(this.arete.getExtremite_1().getPosition().getX());
		this.setStartY(this.arete.getExtremite_1().getPosition().getY());
		this.setEndX(this.arete.getExtremite_2().getPosition().getX());
		this.setEndY(this.arete.getExtremite_2().getPosition().getY());
	}

}
