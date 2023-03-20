package modele;

import java.util.ArrayList;

import javafx.geometry.Point2D;

public class Graphe {

	private ArrayList<Sommet> listeSommets;
	private ArrayList<Arete> listeAretes;
	private int idSommet;
	
	public Graphe() {
		this.idSommet=0;
		this.listeSommets=new ArrayList<Sommet>();
		this.listeAretes=new ArrayList<Arete>();
	}
	
	public Sommet ajouterSommet(double x,double y) {
		this.idSommet++;
		Sommet sommet=new Sommet(this.idSommet,""+this.idSommet,new Point2D(x,y));
		this.listeSommets.add(sommet);
		return sommet;
	}
	
	public void ajouterArete(Arete a) {
		this.listeAretes.add(a);
	}
	
	public int getId() {
		return this.idSommet; 
	}

	public int nbreSommets(){
		return this.listeSommets.size();
	}

	public ArrayList<Sommet> getListeSommets(){
		return this.listeSommets;
	}

	public ArrayList<Arete> getListeAretes(){
		return this.listeAretes;
	}

	public int[][] matriceAdjacence(){
		int[][] adj=new int[this.nbreSommets()][this.nbreSommets()];
		for(Arete a:this.listeAretes){
			adj[a.getExtremite_1().getId()-1][a.getExtremite_2().getId()-1]=1;
			adj[a.getExtremite_2().getId()-1][a.getExtremite_1().getId()-1]=1;
		}
		return adj;
	}

	public double getWidth(){
		double width=0;
		for(Sommet s:listeSommets){
			double x=s.getPosition().getX();
			if(x>width) width=x;
		}
		return width;
	}

	public double getHeight(){
		double height=0;
		for(Sommet s:listeSommets){
			double x=s.getPosition().getY();
			if(x>height) height=x;
		}
		return height;
	}

}
