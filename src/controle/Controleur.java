package controle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import gui.SurfaceDessin;
import intOut.Graph2LaTeX;
import intOut.Graph2XML;
import javafx.scene.control.*;
import modele.Boucle;
import gui.VueArete;
import gui.VueBoucle;
import gui.VueSommet;
import intOut.Graph2Matrix;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import modele.Arete;
import modele.Graphe;
import modele.Sommet;

public class Controleur implements Initializable {

		private int nombreDeGraphes;

		private String natureLibelles;

		@FXML
		TabPane onglets;
	 	@FXML
	    private AnchorPane surfaceDessin;

		@FXML
		private MenuItem save;

		@FXML
		private MenuItem export;

		@FXML
	    private Button btnSommet;

	    @FXML
	    private Button btnArete;

	    @FXML
	    private Button btnSelect;
	    
	    @FXML
	    private Button nombres;

	    @FXML
	    private Button lettres;

		@FXML
		private MenuItem xml;

		@FXML
		private MenuItem open;
	    @FXML
	    void definirLibellesChiffres(ActionEvent event) {
	    	this.natureLibelles="chiffres";
	    }

	    @FXML
	    void definirLibellesLettres(ActionEvent event) {
	    	this.natureLibelles="lettres";
	    }
	    
	    @FXML
	    void creationSommet(ActionEvent event) {
	    	this.ctnSommet.set(true);
	    	this.ctnArete.set(false);
	    	this.select.set(false);
	    }
	    
	    @FXML
	    void creationArete(ActionEvent event) {
	    	this.ctnSommet.set(false);
	    	this.ctnArete.set(true);
	    	this.select.set(false);
	    }
	    
	    @FXML
	    void selection(ActionEvent event) {
	    	this.ctnSommet.set(false);
	    	this.ctnArete.set(false);
	    	this.select.set(true);
	    }

		@FXML
		void save(ActionEvent event) {

			//serialise(sauvegarde);
		}

		@FXML
		void toXML(ActionEvent event){
			final FileChooser fileChooser = new FileChooser();
			File sauvegarde = fileChooser.showSaveDialog(null);
			System.out.println(sauvegarde);
			Graph2XML graph2XML=new Graph2XML(this.graphe);
			//graph2XML.afficher(graph2XML.toXML());
			graph2XML.makeAFile(sauvegarde,graph2XML.toXML());
		}

		@FXML
		void export(ActionEvent event){
			Graph2LaTeX graph2LaTeX=new Graph2LaTeX(this.graphe);
			System.out.println(graph2LaTeX.toLaTeX());
		}

		@FXML
		void open(ActionEvent e) {
			final FileChooser fileChooser = new FileChooser();
			File openFile = fileChooser.showOpenDialog(null);
			String nameFile=openFile.getName();
			String[] tab=nameFile.split("\\.");
			String extension=tab[tab.length-1];
			Alert dBox = new Alert(Alert.AlertType.CONFIRMATION);
			dBox.setTitle("confirmer");
			dBox.setContentText("Souhaitez-vous l'ouvrir dans une nouvelle fenêtre ? ");
			ButtonType btnYes = new ButtonType("Oui");
			ButtonType btnNo = new ButtonType("Non");
			ButtonType btnCancel = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);
			dBox.getButtonTypes().setAll(btnYes, btnNo, btnCancel);
			Optional<ButtonType> answer=dBox.showAndWait();
			if(answer.get()==btnYes){
				nombreDeGraphes++;
				Tab tPane=new Tab("graphe "+nombreDeGraphes);
				this.onglets.getTabs().add(tPane);
				Graphe graphe=new Graphe();
				SurfaceDessin drawMap=new SurfaceDessin(nombreDeGraphes);

				drawMap.setOnMousePressed(event -> {
					detectionSouris(event);
				});
				tPane.setContent(drawMap);
				
			}
			switch(extension){
				case "xml" : {
					Graph2XML graph2XML=new Graph2XML(this.graphe);
					graph2XML.readAFile(openFile);
				}
				break;
				case "json" : System.out.println("fichier json");break;
				case "txt" : System.out.println("fichier texte");break;
				default : System.out.println("fichier inconnu");
			}
/*
			try{
				BufferedReader reader = new BufferedReader(new FileReader(openFile));

				// Lire une à une les lignes contenues dans le fichier
				String ligne = reader.readLine();
				int k=0;
				int[][] matriceAdj=new int[ligne.length()][ligne.length()];
				while (ligne != null) {
					for (int i = 0; i < ligne.length(); i++) {
						matriceAdj[k][i] = ligne.charAt(i) - 48;
					}
					ligne = reader.readLine();
					k++;
				}
				reader.close();
			}
			catch(IOException ioe){
				ioe.printStackTrace();
			}*/
		}
	    @FXML
	    void detectionSouris(MouseEvent e) {
			surfaceDessin=(AnchorPane) this.onglets.getSelectionModel().getSelectedItem().getContent();
	    	if(this.ctnSommet.get()) {
	    		Sommet sommet=this.graphe.ajouterSommet(e.getX(),e.getY());
				//System.out.println("x="+e.getX()+" y="+e.getY());
	    		VueSommet vueSommet=new VueSommet(sommet);
	    		if(this.natureLibelles.equals("lettres")) {
	    			int ascii=this.graphe.getId()+64;
	    			sommet.setNom(""+(char)ascii);
	    		}
	    		else sommet.setNom(""+this.graphe.getId());
	    		vueSommet.dessiner(sommet.getNom());
	    		
	    		vueSommet.setOnMouseClicked(event -> {
	    			if(this.ctnArete.get()) {
	    				if (this.sommetSelectionne_1==null)this.sommetSelectionne_1=vueSommet.getSommet();
	    				else if (this.sommetSelectionne_2==null) {
	    					this.sommetSelectionne_2=vueSommet.getSommet();
							if(this.sommetSelectionne_2!=this.sommetSelectionne_1){
								Arete arete=new Arete(this.sommetSelectionne_1,this.sommetSelectionne_2);
								VueArete vueArete=new VueArete(arete);
								vueArete.dessiner();
								vueArete.binder();
								surfaceDessin.getChildren().add(vueArete);
								vueArete.toBack();
								this.graphe.ajouterArete(arete);
							}
							else{
								Boucle boucle=new Boucle(this.sommetSelectionne_1);
								VueBoucle vueBoucle=new VueBoucle(boucle);
								vueBoucle.dessiner();
								vueBoucle.binder();
								surfaceDessin.getChildren().add(vueBoucle);
								vueBoucle.toBack();
								this.graphe.ajouterArete(boucle);
							}
	    					this.sommetSelectionne_1=null;
	    					this.sommetSelectionne_2=null;
	    				}
	    			}
	    		});
	    		vueSommet.setOnMousePressed(event -> {
	    			if(this.select.get()) {
	    				this.sommetADeplacer=vueSommet.getSommet();
	    				this.sommetADeplacer.setPosition(new Point2D(event.getX(),event.getY()));
	    				this.orgSceneX = event.getSceneX();
	    	            this.orgSceneY = event.getSceneY();
	    	            orgTranslateX = this.sommetADeplacer.getVue().getTranslateX();
	    	            orgTranslateY = this.sommetADeplacer.getVue().getTranslateY();
	    			}
	    		});
	    		vueSommet.setOnMouseDragged(event -> {
	    			if(this.select.get()) {
	    				double offsetX = event.getSceneX() - this.orgSceneX;
	    	            double offsetY = event.getSceneY() - this.orgSceneY;
	    	            double newTranslateX = orgTranslateX + offsetX;
	    	            double newTranslateY = orgTranslateY + offsetY;
	    				this.sommetADeplacer.getVue().setTranslateX(newTranslateX);
	    				this.sommetADeplacer.getVue().setTranslateY(newTranslateY);
	    			}
	    		});
	    		surfaceDessin.getChildren().add(vueSommet);
	    	}
	    }
	private Sommet sommetSelectionne_1,sommetSelectionne_2;
	private Sommet sommetADeplacer;
	private Graphe graphe;
	int val= 50;
    double orgSceneX, orgSceneY;
    double orgTranslateX, orgTranslateY;
    private BooleanProperty ctnSommet,ctnArete,select;

	public void serialise(File f){
		Graph2Matrix graph2m=new Graph2Matrix(this.graphe);
		graph2m.ecritureMatrice(f);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
		this.graphe=new Graphe();
		nombreDeGraphes=1;
		this.natureLibelles="chiffres";
		this.select=new SimpleBooleanProperty(false);
		this.ctnSommet=new SimpleBooleanProperty(false);
		this.ctnArete=new SimpleBooleanProperty(false);

	}
	
}
