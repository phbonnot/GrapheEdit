package modele;

import javafx.scene.shape.Line;

public class Arete{

	private Sommet extremite1,extremite2;
	private String etiquette;
	
	public Arete(Sommet ext1, Sommet ext2){
		this.extremite1=ext1;
		this.extremite2=ext2;
		this.etiquette="";
	}
	
	public Sommet getExtremite_1() {
		return this.extremite1;
	}
	
	public Sommet getExtremite_2() {
		return this.extremite2;
	}
	
}
