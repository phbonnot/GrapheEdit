package intOut;

import modele.Sommet;
import org.jdom2.Element;

public class SommetElt extends Element {

    Sommet sommet;
    public SommetElt(Sommet s){
        this.sommet=s;

        Element id=new Element("Id");
        id.setAttribute("valeur",""+s.getId());
        Element position=new Element("Position");
        position.setAttribute("posX",String.valueOf(s.getPosition().getX()));
        position.setAttribute("posY",String.valueOf(s.getPosition().getY()));
        this.addContent(id);
        this.addContent(position);
    }
}
