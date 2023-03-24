package gui;


import com.sun.javafx.event.EventHandlerManager;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

import javafx.scene.input.MouseEvent;

public class SurfaceDessin extends AnchorPane {

    private final EventHandlerManager eventDispatcher;

    private int id;



    public SurfaceDessin( int id){
        this.eventDispatcher = new EventHandlerManager(MouseEvent.MOUSE_PRESSED);
        this.id=id;
    }





}
