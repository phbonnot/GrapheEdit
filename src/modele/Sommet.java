package modele;

import gui.VueSommet;
import javafx.geometry.Point2D;

public class Sommet {

	private int id;
	private String nom;
	private Point2D position;
	private VueSommet vueSommet;
	
	public Sommet(int id,String nom, Point2D p) {
		this.id=id;
		this.nom=nom;
		this.position=p;
	}
	
	public Point2D getPosition() {
		return this.position;
	}
	
	public String getNom() {
		return this.nom;
	}
	
	public void setPosition(Point2D p) {
		this.position=p;
	}
	
	public void setVue(VueSommet vs) {
		this.vueSommet=vs;
	}
	
	public VueSommet getVue() {
		return this.vueSommet;
	}
	
	public void setNom(String n) {
		this.nom=n;
	}

	public int getId(){
		return this.id;
	}
}
